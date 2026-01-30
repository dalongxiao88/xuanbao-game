package come.tool.teamArena;

import come.tool.Stall.AssetUpdate;
import come.tool.Good.DropUtil;
import come.tool.newTask.TaskRecord;
import java.math.BigDecimal;
import org.come.model.Dorp;
import org.come.server.GameServer;
import come.tool.Battle.BattleData;
import java.util.ArrayList;
import org.come.entity.Record;
import org.come.until.AllServiceUtil;
import org.come.action.suit.SuitComposeAction;
import come.tool.Battle.BattleThreadPool;
import org.come.bean.LoginResult;
import come.tool.Role.RoleData;
import org.come.until.GsonUtil;
import come.tool.Role.RolePool;
import come.tool.newTeam.TeamRole;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import io.netty.channel.ChannelHandlerContext;
import come.tool.newTeam.TeamBean;
import java.util.List;

public class TeamArenaUtil
{
    public static long TIME;
    public static long ATIME;
    public static long PTIME;
    public static Object Thread_LOCK;
    public static Object List_LOCK;
    public static Object Jf_LOCK;
    public static TeamArenaThread teamArenaThread;
    private static List<TeamBean> affirmList;
    private static List<TeamBean> matchList;
    private static List<TeamArenaGroup> prepareList;
    
    public static void addAffirm(ChannelHandlerContext ctx, TeamBean bean) {
        List<TeamRole> teams = null;
        synchronized (bean) {
            TeamArenaMatch arenaMatch = bean.getTeamArenaMatch();
            if (arenaMatch != null) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你的队伍已在匹配"));
                return;
            }
            teams = bean.getTeams();
            if (teams.size() != 5) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你的队伍不足5人"));
                return;
            }
            int size = teams.size();
            int max = 0;
            for (int i = 0; i < size; ++i) {
                TeamRole teamRole = (TeamRole)teams.get(i);
                RoleData roleData = RolePool.getRoleData(teamRole.getRoleId());
                LoginResult loginResult = (roleData != null) ? roleData.getLoginResult() : null;
                if (teamRole.getState() < 0 || loginResult == null) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(teamRole.getName() + "不在队伍中"));
                    return;
                }
                if ((int)loginResult.getGrade() <= 102) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(teamRole.getName() + "还未转生,无法参与匹配"));
                    return;
                }
                if (roleData.getTaskWC(5) - roleData.getTaskLQ(5) >= 3) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("#W每日最大匹配10次#R" + teamRole.getName() + "#W次数已满！"));
                    return;
                }
                synchronized (TeamArenaUtil.Jf_LOCK) {
                    int jf = (loginResult.getPkrecord() != null) ? loginResult.getPkrecord().intValue() : 0;
                    if (i == 0) {
                        max = jf;
                    }
                    else if (max < jf) {
                        max = jf;
                    }
                }
            }
            arenaMatch = new TeamArenaMatch(size);
            arenaMatch.setJF(max);
            bean.setTeamArenaMatch(arenaMatch);
            synchronized (TeamArenaUtil.List_LOCK) {
                TeamArenaUtil.affirmList.add(bean);
            }
        }
        bean.sendTeam(Agreement.getAgreement().teamArenaAgreement("0" + GsonUtil.getGsonUtil().getgson().toJson(teams)));
    }
    
    public static void addMatch(TeamBean bean) {
        synchronized (TeamArenaUtil.List_LOCK) {
            bean.getTeamArenaMatch().setState(1);
            bean.getTeamArenaMatch().setTime(System.currentTimeMillis());
            TeamArenaUtil.affirmList.remove(bean);
            for (int i = 0; i < TeamArenaUtil.matchList.size(); ++i) {
                if (((TeamBean)TeamArenaUtil.matchList.get(i)).getTeamArenaMatch().getJF() < bean.getTeamArenaMatch().getJF()) {
                    TeamArenaUtil.matchList.add(i, bean);
                    return;
                }
            }
            TeamArenaUtil.matchList.add(bean);
        }
    }
    
    public static void quitTeamArena(TeamBean bean, TeamRole role) {
        boolean isV = false;
        TeamBean bean2 = null;
        synchronized (TeamArenaUtil.List_LOCK) {
            TeamArenaMatch arenaMatch = bean.getTeamArenaMatch();
            if (arenaMatch == null) {
                return;
            }
            if (arenaMatch.getState() == 0) {
                bean.setTeamArenaMatch(null);
                isV = TeamArenaUtil.affirmList.remove(bean);
            }
            else if (arenaMatch.getState() == 1) {
                bean.setTeamArenaMatch(null);
                isV = TeamArenaUtil.matchList.remove(bean);
            }
            else if (arenaMatch.getState() == 2) {
                bean.setTeamArenaMatch(null);
                int i = TeamArenaUtil.prepareList.size() - 1;
                while (i >= 0) {
                    TeamArenaGroup teamArenaGroup = (TeamArenaGroup)TeamArenaUtil.prepareList.get(i);
                    if (teamArenaGroup.getTeam1() == bean) {
                        bean2 = teamArenaGroup.getTeam2();
                        bean2.setTeamArenaMatch(null);
                        TeamArenaUtil.prepareList.remove(i);
                        isV = true;
                        break;
                    }
                    else if (teamArenaGroup.getTeam2() == bean) {
                        bean2 = teamArenaGroup.getTeam1();
                        bean2.setTeamArenaMatch(null);
                        TeamArenaUtil.prepareList.remove(i);
                        isV = true;
                        break;
                    }
                    else {
                        --i;
                    }
                }
            }
            else {
                return;
            }
        }
        if (isV) {
            String send = Agreement.getAgreement().teamArenaAgreement("D玩家" + role.getName() + "状态异常导致匹配失败");
            bean.sendTeam(send);
            if (bean2 != null) {
                bean2.sendTeam(send);
            }
        }
    }
    
    public static boolean isOperate(ChannelHandlerContext ctx, TeamBean bean) {
        if (bean.getTeamArenaMatch() != null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你的队伍正在匹配,无法操作"));
            return true;
        }
        return false;
    }
    
    public static void prepare(long time) {
        while (true) {
            TeamArenaGroup teamArenaGroup = null;
            synchronized (TeamArenaUtil.List_LOCK) {
                if (TeamArenaUtil.prepareList.size() > 0) {
                    teamArenaGroup = (TeamArenaGroup)TeamArenaUtil.prepareList.get(0);
                    if (time - teamArenaGroup.getTime() < TeamArenaUtil.PTIME) {
                        break;
                    }
                    else {
                        TeamArenaUtil.prepareList.remove(0);
                    }
                }
            }
            if (teamArenaGroup == null) {
                break;
            }
            else {
                String value = BattleThreadPool.addTeamArenaBattle(teamArenaGroup);
                if (value == null) {
                    synchronized (TeamArenaUtil.List_LOCK) {
                        teamArenaGroup.getTeam1().getTeamArenaMatch().setTime(time);
                        teamArenaGroup.getTeam1().getTeamArenaMatch().setState(3);
                        teamArenaGroup.getTeam2().getTeamArenaMatch().setTime(time);
                        teamArenaGroup.getTeam2().getTeamArenaMatch().setState(3);
                    }
                    String send = Agreement.getAgreement().teamArenaAgreement("D") + Agreement.getAgreement().TaskNAgreement("C5=L");
                    teamArenaGroup.getTeam1().addTaskAndSendTeam(5, send);
                    teamArenaGroup.getTeam2().addTaskAndSendTeam(5, send);
                }
                else {
                    synchronized (TeamArenaUtil.List_LOCK) {
                        teamArenaGroup.getTeam1().setTeamArenaMatch(null);
                        teamArenaGroup.getTeam2().setTeamArenaMatch(null);
                    }
                    String send = Agreement.getAgreement().teamArenaAgreement("D" + value);
                    teamArenaGroup.getTeam1().sendTeam(send);
                    teamArenaGroup.getTeam2().sendTeam(send);
                }
            }
        }
    }
    
    public static void match(long time) {
        int index = 0;
        while (true) {
            TeamBean team1 = null;
            TeamBean team2 = null;
            synchronized (TeamArenaUtil.List_LOCK) {
                int size = TeamArenaUtil.matchList.size();
                if (size > index + 1) {
                    team1 = (TeamBean)TeamArenaUtil.matchList.get(index);
                    int jf = team1.getTeamArenaMatch().getJF();
                    long gap = (time - team1.getTeamArenaMatch().getTime()) / 1000L;
                    if (gap < 30L) {
                        gap = 40L + 2L * gap;
                    }
                    else if (gap < 60L) {
                        gap = 100L;
                    }
                    else {
                        gap = 2L * gap - 20L;
                        if (gap > 150L) {
                            gap = 150L;
                        }
                    }
                    int i = ++index;
                    while (i < size) {
                        TeamBean team3 = (TeamBean)TeamArenaUtil.matchList.get(i);
                        if ((long)(team3.getTeamArenaMatch().getJF() - jf) > gap || i == size - 1) {
                            i -= index;
                            team2 = (TeamBean)TeamArenaUtil.matchList.get(index + ((i > 0) ? SuitComposeAction.random.nextInt(i) : 0));
                            break;
                        }
                        else {
                            ++i;
                        }
                    }
                }
                if (team1 == null) {
                    break;
                }
                else if (team2 == null) {
                    continue;
                }
                else {
                    team1.getTeamArenaMatch().setTime(time);
                    team1.getTeamArenaMatch().setState(2);
                    team2.getTeamArenaMatch().setTime(time);
                    team2.getTeamArenaMatch().setState(2);
                    TeamArenaGroup teamArenaGroup = new TeamArenaGroup(team1, team2, time);
                    TeamArenaUtil.matchList.remove(team1);
                    TeamArenaUtil.matchList.remove(team2);
                    TeamArenaUtil.prepareList.add(teamArenaGroup);
                }
            }
            team1.sendTeam(Agreement.getAgreement().teamArenaAgreement("E" + GsonUtil.getGsonUtil().getgson().toJson(team2.getTeams())));
            team2.sendTeam(Agreement.getAgreement().teamArenaAgreement("E" + GsonUtil.getGsonUtil().getgson().toJson(team1.getTeams())));
        }
    }
    
    public static void confirmTimeOut(long time) {
        for (int i = TeamArenaUtil.affirmList.size() - 1; i >= 0; --i) {
            TeamBean bean = (TeamBean)TeamArenaUtil.affirmList.get(i);
            synchronized (bean) {
                if (time - bean.getTeamArenaMatch().getTime() > TeamArenaUtil.ATIME) {
                    synchronized (TeamArenaUtil.List_LOCK) {
                        bean.setTeamArenaMatch(null);
                        TeamArenaUtil.affirmList.remove(bean);
                    }
                    bean.sendTeam(Agreement.getAgreement().teamArenaAgreement("D队伍中有人长时间不同意退出匹配队列"));
                }
            }
        }
    }
    
    public static void confirm(TeamBean bean, LoginResult roleInfo, boolean is) {
        synchronized (bean) {
            TeamArenaMatch arenaMatch = bean.getTeamArenaMatch();
            if (arenaMatch == null) {
                SendMessage.sendMessageByRoleName(roleInfo.getRolename(), Agreement.getAgreement().PromptAgreement("你的队伍不在匹配队列中"));
                return;
            }
            if (arenaMatch.getState() == 0) {
                if (is) {
                    int type = arenaMatch.isIds(roleInfo.getRole_id());
                    if (type == 0) {
                        AllServiceUtil.getRecordService().insert(new Record(9, "同意数据异常:" + roleInfo.getRole_id() + ":" + GsonUtil.getGsonUtil().getgson().toJson(arenaMatch)));
                        return;
                    }
                    if (type == 2) {
                        addMatch(bean);
                    }
                    bean.sendTeam(Agreement.getAgreement().teamArenaAgreement("A" + roleInfo.getRole_id()));
                }
                else {
                    boolean isV = false;
                    synchronized (TeamArenaUtil.List_LOCK) {
                        bean.setTeamArenaMatch(null);
                        isV = TeamArenaUtil.affirmList.remove(bean);
                    }
                    if (isV) {
                        bean.sendTeam(Agreement.getAgreement().teamArenaAgreement("D" + roleInfo.getRolename() + "拒绝匹配"));
                    }
                    else {
                        AllServiceUtil.getRecordService().insert(new Record(9, "拒绝匹配异常:" + roleInfo.getRole_id() + ":" + GsonUtil.getGsonUtil().getgson().toJson(arenaMatch)));
                        return;
                    }
                }
            }
            else if (arenaMatch.getState() == 1 && !is) {
                boolean isV = false;
                synchronized (TeamArenaUtil.List_LOCK) {
                    bean.setTeamArenaMatch(null);
                    isV = TeamArenaUtil.matchList.remove(bean);
                }
                if (isV) {
                    bean.sendTeam(Agreement.getAgreement().teamArenaAgreement("D" + roleInfo.getRolename() + "取消匹配"));
                }
                else {
                    AllServiceUtil.getRecordService().insert(new Record(9, "取消匹配异常:" + roleInfo.getRole_id() + ":" + GsonUtil.getGsonUtil().getgson().toJson(arenaMatch)));
                    return;
                }
            }
        }
    }
    
    public static void teamArenaOpen() {
        synchronized (TeamArenaUtil.Thread_LOCK) {
            synchronized (TeamArenaUtil.List_LOCK) {
                TeamArenaUtil.affirmList = new ArrayList<>();
                TeamArenaUtil.matchList = new ArrayList<>();
                TeamArenaUtil.prepareList = new ArrayList<>();
            }
            TeamArenaUtil.teamArenaThread = new TeamArenaThread();
            Thread T1 = new Thread(TeamArenaUtil.teamArenaThread);
            T1.start();
        }
    }
    
    public static void teamArenaEnd() {
        synchronized (TeamArenaUtil.Thread_LOCK) {
            TeamArenaUtil.teamArenaThread = null;
            try {
                Thread.sleep(1000L);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            List<TeamBean> beans = new ArrayList<>();
            synchronized (TeamArenaUtil.List_LOCK) {
                for (int i = 0, length = TeamArenaUtil.affirmList.size(); i < length; ++i) {
                    TeamBean bean = (TeamBean)TeamArenaUtil.affirmList.get(i);
                    bean.setTeamArenaMatch(null);
                    beans.add(bean);
                }
                for (int i = 0, length = TeamArenaUtil.matchList.size(); i < length; ++i) {
                    TeamBean bean = (TeamBean)TeamArenaUtil.matchList.get(i);
                    bean.setTeamArenaMatch(null);
                    beans.add(TeamArenaUtil.matchList.get(i));
                }
                for (int i = 0, length = TeamArenaUtil.prepareList.size(); i < length; ++i) {
                    TeamBean bean2 = ((TeamArenaGroup)TeamArenaUtil.prepareList.get(i)).getTeam1();
                    TeamBean bean3 = ((TeamArenaGroup)TeamArenaUtil.prepareList.get(i)).getTeam2();
                    bean2.setTeamArenaMatch(null);
                    bean3.setTeamArenaMatch(null);
                    beans.add(bean2);
                    beans.add(bean3);
                }
                TeamArenaUtil.affirmList = null;
                TeamArenaUtil.matchList = null;
                TeamArenaUtil.prepareList = null;
            }
            if (beans.size() != 0) {
                String send = Agreement.getAgreement().teamArenaAgreement("D活动结束匹配通道关闭");
                for (int i = 0, length = beans.size(); i < length; ++i) {
                    ((TeamBean)beans.get(i)).sendTeam(send);
                }
            }
        }
    }
    
    public static void teamArenaBattleEnd(BattleData battleData, int camp) {
        TeamArenaGroup teamArenaGroup = battleData.getTeamArenaGroup();
        if (camp == -1) {
            teamArenaGroup.getTeam1().setTeamArenaMatch(null);
            teamArenaGroup.getTeam2().setTeamArenaMatch(null);
            return;
        }
        TeamBean team1 = (camp == 1) ? teamArenaGroup.getTeam1() : teamArenaGroup.getTeam2();
        TeamArenaMatch match1 = team1.getTeamArenaMatch();
        team1.setTeamArenaMatch(null);
        TeamBean team2 = (camp == 1) ? teamArenaGroup.getTeam2() : teamArenaGroup.getTeam1();
        TeamArenaMatch match2 = team2.getTeamArenaMatch();
        team2.setTeamArenaMatch(null);
        int fc = match1.getJF() - match2.getJF();
        int sl = 12 - fc / 35;
        int dw = match1.getJF() / 100;
        sl += ((dw >= 7) ? -4 : ((dw >= 5) ? -3 : ((dw >= 3) ? -2 : ((dw >= 1) ? -1 : 0))));
        if (sl <= 0) {
            sl = 1;
        }
        int sb = -7 + fc / 35;
        dw = match2.getJF() / 100;
        sb -= ((dw >= 6) ? -3 : ((dw >= 4) ? -2 : ((dw >= 2) ? -1 : 0)));
        if (sb >= 0) {
            sb = -1;
        }
        teamArenaDraw(team1, match1, GameServer.getDorp("2039"), sl);
        teamArenaDraw(team2, match2, GameServer.getDorp("2040"), sb);
    }
    
    public static void teamArenaDraw(TeamBean team, TeamArenaMatch match, Dorp dorp, int add) {
        BigDecimal[] ids = match.getIds();
        for (int i = 0; i < ids.length; ++i) {
            AllServiceUtil.getRoleTableService().addQMJJ(ids[i], add);
            TeamRole teamRole = team.getTeamRole(ids[i]);
            RoleData roleData = (teamRole != null) ? RolePool.getRoleData(teamRole.getRoleId()) : null;
            LoginResult loginResult = (roleData != null) ? roleData.getLoginResult() : null;
            if (loginResult != null) {
                String value = (dorp != null) ? dorp.getDorpValue() : "";
                int jf = loginResult.getPkrecord().intValue() + add;
                loginResult.setPkrecord(new BigDecimal(jf));
                String data = "K=" + add;
                String msg = ((add > 0) ? ("你增加了" + add + "点段位积分") : ("你损失了了" + -add + "点段位积分")) + ",你当前段位为" + getTilte(jf);
                String task = null;
                if (add > 0) {
                    TaskRecord taskRecord = roleData.getTaskRecord(5);
                    if (taskRecord == null) {
                        taskRecord = new TaskRecord(5);
                        roleData.addTaskRecord(taskRecord);
                    }
                    taskRecord.addRSum(1);
                    task = "C5=R";
                    if (taskRecord.getcSum() <= 3) {
                        if (value != null && value.length() != 0) {
                            value += "|";
                        }
                        int dw = jf / 100;
                        if (dw < 0) {
                            dw = 0;
                        }
                        else if (dw > 7) {
                            dw = 7;
                        }
                        value = value + "竞技积分=" + (3 + dw);
                    }
                }
                if (roleData.getTaskWC(5) <= 3) {
                    DropUtil.getDrop4(loginResult, value, (add > 0) ? "#G{角色名}#Y在天梯竞技中,大杀四方,将对方打的抱头痛哭,幸运的获得了#R{物品名}" : "#G{角色名}#Y在全民竞技场中虽败犹荣,意外获得安慰奖励#R{物品名}", 25, 1.0, null, data, msg, task);
                }
                else {
                    AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
                    assetUpdate.upmsg(msg);
                    assetUpdate.updata(data);
                    assetUpdate.setTask(task);
                    SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                }
            }
        }
    }
    
    public static String getTilte(int pk) {
        if (pk < 100) {
            return "入门" + pk + "点";
        }
        if (pk < 200) {
            return "进阶" + (pk - 100) + "点";
        }
        if (pk < 300) {
            return "精锐" + (pk - 200) + "点";
        }
        if (pk < 400) {
            return "英杰" + (pk - 300) + "点";
        }
        if (pk < 500) {
            return "豪侠" + (pk - 400) + "点";
        }
        if (pk < 600) {
            return "宗师" + (pk - 500) + "点";
        }
        if (pk < 700) {
            return "武圣" + (pk - 600) + "点";
        }
        return "王者" + (pk - 700) + "点";
    }
    
    static {
        TeamArenaUtil.TIME = 43200000L;
        TeamArenaUtil.ATIME = 30000L;
        TeamArenaUtil.PTIME = 5000L;
        TeamArenaUtil.Thread_LOCK = new Object();
        TeamArenaUtil.List_LOCK = new Object();
        TeamArenaUtil.Jf_LOCK = new Object();
    }
}
