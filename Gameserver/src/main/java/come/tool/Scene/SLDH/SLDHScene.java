package come.tool.Scene.SLDH;

import come.tool.Battle.BattleData;
import come.tool.Battle.BattleEnd;
import come.tool.Battle.BattleThreadPool;
import come.tool.FightingData.Battlefield;
import come.tool.Role.RoleShow;
import come.tool.Scene.Scene;
import come.tool.Stall.AssetUpdate;
import come.tool.Title.TitleUtil;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.chat.FriendChatAction;
import org.come.action.reward.DrawnitemsAction;
import org.come.action.sys.ChangeMapAction;
import org.come.bean.*;
import org.come.handler.MainServerHandler;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.readUtil.ReadPoolUtil;
import org.come.server.GameServer;
import org.come.server.GolemBean;
import org.come.server.GolemServer;
import org.come.task.MapMonsterBean;
import org.come.task.MonsterMoveBase;
import org.come.tool.NewAESUtil;
import org.come.tool.ReadExelTool;
import org.come.tool.WriteOut;
import org.come.until.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import static org.come.server.GolemServer.isSLDH;
public class SLDHScene implements Scene
{
    public static long JG;
    public ChangeMapBean INTO;
    private int I;
    private int JS;
    private int CI;
    private RoleShow[] lastShows;
    private ConcurrentHashMap<BigDecimal, SLDHRole> roleMap;
    private List<SLDHTeam> teams;
    private List<SLDHGroup> groups;
    private int teamNum;
    private int teamTotal;
    private int groupNum;
    private int endNum;
    private int ppNum;
    private StringBuffer SLbuffer;
    
    public SLDHScene() {
        this.I = 4;
        this.roleMap = new ConcurrentHashMap<>();
        this.teams = new ArrayList<>();
        this.groups = new ArrayList<>();
        this.INTO = new ChangeMapBean("3320", 1328, 1026);
        String text = ReadTxtUtil.readFile1(ReadExelTool.class.getResource("/").getPath() + "sldh.txt");
        if (text == null || text.equals("")) {
            this.JS = 1;
            this.CI = 1;
        }
        else {
            SLDHBean sldhBean = (SLDHBean)GsonUtil.getGsonUtil().getgson().fromJson(text, SLDHBean.class);
            this.JS = sldhBean.getJS();
            this.CI = sldhBean.getCI();
            this.lastShows = sldhBean.getLastShows();
        }
        if (GameServer.allBangList == null) {
            GameServer.allBangList = new ConcurrentHashMap<>();
        }
        GameServer.allBangList.put(Integer.valueOf(4), AllServiceUtil.getRoleTableService().selectSLDH());
        this.loadStatue();
    }
    
    public synchronized String addEnroll(ChannelHandlerContext ctxRole, LoginResult loginResult) {
        if (this.I != 0) {
            return "进场通道已关闭";
        }
        String[] teams = loginResult.getTeam().split("\\|");
        if (!teams[0].equals(loginResult.getRolename())) {
            return "你不是队长";
        }
        if (loginResult.isGolem()) {

        }else {
            if (loginResult.getGold().longValue() < 3000000L) {
                return "需缴纳300W入场费";
            }
        }
        if (teams.length != 5) {
            return "人数不足5人";
        }
        if ((int)loginResult.getGrade() < 70) {
            return loginResult.getRolename() + "未满70级";
        }
        SLDHRole cRole = this.getRole(loginResult.getRole_id());
        SLDHRole[] roles = null;
        if (cRole == null) {
            roles = new SLDHRole[teams.length];
            roles[0] = new SLDHRole(loginResult.getRole_id(), loginResult.getRoleShow());
        }
        for (int i = 1; i < teams.length; ++i) {
            ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(teams[i]);
            LoginResult login = (ctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx)) : null;
            if (login == null) {
                return teams[i] + "处于异常状态";
            }
            if ((int)login.getGrade() < 70) {
                return teams[i] + "未满70级";
            }
            SLDHRole role = this.getRole(login.getRole_id());
            if (roles == null) {
                if (role == null || cRole.getSldhTeam() != role.getSldhTeam()) {
                    return "你已经报名了水路大会且" + teams[i] + "不是你的队友";
                }
            }
            else {
                if (role != null) {
                    return teams[i] + "已和其他玩家参与本次水路大会报名";
                }
                roles[i] = new SLDHRole(login.getRole_id(), login.getRoleShow());
            }
        }
        if (loginResult .isGolem()) {

        }else {
            AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
            loginResult.setGold(new BigDecimal(loginResult.getGold().longValue() - 3000000L));
            assetUpdate.updata("D=-3000000");
            assetUpdate.upmsg("扣除300W金钱");
            SendMessage.sendMessageToSlef(ctxRole, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
        if (roles != null) {
            SLDHTeam team = new SLDHTeam(roles);
            this.teams.add(team);
            ++this.teamTotal;
            for (int j = 0; j < roles.length; ++j) {
                this.addRole(roles[j]);
                AllServiceUtil.getPackRecordService().addSLDH(roles[j].getRoleID(), 200);
            }
        }
        ChangeMapAction.ChangeMap(this.INTO, ctxRole);
        return null;
    }
    
    public void open() {
        if (this.I != 4) {
            WriteOut.addtxt("开启水陆大会时:水陆大会状态处于非关闭状态", 9999L);
            return;
        }
        if (this.CI == 1) {
            AllServiceUtil.getPackRecordService().emptySLDH();
        }
        this.SLbuffer = new StringBuffer();
        this.roleMap.clear();
        this.teams.clear();
        this.groups.clear();
        this.teamNum = 0;
        this.teamTotal = 0;
        this.groupNum = 0;
        this.endNum = 0;
        this.ppNum = 0;
        this.I = 0;
        SLDHThread thread = new SLDHThread(this);
        Thread T1 = new Thread(thread);
        T1.start();

        isSLDH = true;
    }
    
    public void end() {
        try {
            //这里写机器人水陆线程停止
            isSLDH = false;
            GolemServer.SLteam = false;
            synchronized (this) {
                if (GolemServer.loginGolems != null&& GolemServer.loginGolems.size() > 0) {
                    for (GolemBean golemBean : GolemServer.loginGolems.values()) {
                        if (golemBean.getSLtype() > 0) {
                            golemBean.setSLtype(0);
                        }
                    }
                }
            }
        } catch (Exception e) {
            isSLDH = false;
            GolemServer.SLteam = false;
            throw new RuntimeException(e);
        }



        StringBuffer buffer = new StringBuffer();
        buffer.append("本次水陆大会比赛结束");
        if (this.groups.size() >= 1) {
            SLDHGroup group = (SLDHGroup)this.groups.get(0);
            SLDHTeam team = group.getTeam(true);
            if (team != null) {
                buffer.append("#r#R冠军队伍:#G");
                buffer.append(team.getTeamMsg());
            }
            team = group.getTeam(false);
            if (team != null) {
                buffer.append("#r#R亚军队伍:#G");
                buffer.append(team.getTeamMsg());
            }
            if (this.groups.size() >= 2) {
                group = (SLDHGroup)this.groups.get(1);
                team = group.getTeam(true);
                if (team != null) {
                    buffer.append("#r#R季军队伍:#G");
                    buffer.append(team.getTeamMsg());
                }
            }
        }
        this.SLbuffer.append("\r\n比赛结果");
        this.SLbuffer.append(buffer.toString());
        WriteOut.writeTxtFile(this.SLbuffer.toString(), "sldh");
        this.SLbuffer = null;
        NChatBean bean = new NChatBean();
        bean.setId(5);
        bean.setMessage(buffer.toString());
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
        this.I = 4;
        List<LoginResult> results = AllServiceUtil.getRoleTableService().selectSLDH();
        GameServer.allBangList.put(Integer.valueOf(4), results);
        ++this.CI;
        if (this.CI >= 5) {
            ++this.JS;
            this.CI = 1;
            if (results.size() == 0) {
                this.lastShows = null;
            }
            else {
                this.lastShows = new RoleShow[(results.size() < 5) ? results.size() : 5];
                for (int i = 0; i < this.lastShows.length; ++i) {
                    LoginResult result = (LoginResult)results.get(i);
                    (this.lastShows[i] = new RoleShow()).setRole_id(result.getRole_id());
                    this.lastShows[i].setRolename(result.getRolename());
                    this.lastShows[i].setSpecies_id(result.getSpecies_id());
                }
            }
            this.loadStatue();
            SLDHBean sldhBean = new SLDHBean();
            sldhBean.setJS(this.JS);
            sldhBean.setCI(this.CI);
            sldhBean.setLastShows(this.lastShows);
            try {
                CreateTextUtil.createFile(ReadExelTool.class.getResource("/").getPath() + "sldh.txt", GsonUtil.getGsonUtil().getgson().toJson(sldhBean).getBytes());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void grouping() {
        if (this.I == 1) {
            this.SLbuffer.append("\r\n");
            this.SLbuffer.append("第");
            this.SLbuffer.append(this.ppNum);
            this.SLbuffer.append("轮淘汰的队伍:");
            for (int i = 0, length = this.groups.size(); i < length; ++i) {
                SLDHGroup group = (SLDHGroup)this.groups.get(i);
                SLDHTeam team = group.getTeam(false);
                if (team != null) {
                    this.teams.remove(team);
                    this.SLbuffer.append("\r\n状态:");
                    this.SLbuffer.append(group.getState());
                    this.SLbuffer.append(" ");
                    this.SLbuffer.append(team.getTeamMsg());
                }
            }
        }
        ++this.ppNum;
        this.teamNum = this.teams.size();
        this.groupNum = 0;
        this.endNum = 0;
        if (this.I == 1) {
            this.groups.clear();
            if (this.teams.size() <= 2) {
                this.I = 3;
            }
            else if (this.teams.size() <= 4) {
                this.I = 2;
            }
            List<Integer> list = new ArrayList<>();
            for (int j = 0, length2 = this.teams.size(); j < length2; ++j) {
                list.add(Integer.valueOf(j));
            }
            while (list.size() != 0) {
                int size = list.size();
                int t1 = (int)list.remove(Battlefield.random.nextInt(size));
                SLDHTeam team2 = (SLDHTeam)this.teams.get(t1);
                SLDHTeam team3 = null;
                if (--size != 0) {
                    int t2 = (int)list.remove(Battlefield.random.nextInt(size));
                    team3 = (SLDHTeam)this.teams.get(t2);
                }
                SLDHGroup group2 = new SLDHGroup(team2, team3);
                this.groups.add(group2);
            }
            this.groupNum = this.groups.size();
            this.PPAll();
            if (this.groupNum != 0) {
                SLDHBattleThread battleThread = new SLDHBattleThread(this);
                Thread T1 = new Thread(battleThread);
                T1.start();
            }
            else {
                this.grouping();
            }
        }
        else if (this.I == 2) {
            if (this.groups.size() <= 1) {
                this.I = 3;
                this.grouping();
                return;
            }
            this.I = 3;
            SLDHGroup group3 = (SLDHGroup)this.groups.get(0);
            SLDHGroup group4 = (SLDHGroup)this.groups.get(1);
            this.groups.clear();
            SLDHGroup pgroup1 = new SLDHGroup(group3.getTeam(true), group4.getTeam(true));
            SLDHGroup pgroup2 = new SLDHGroup(group3.getTeam(false), group4.getTeam(false));
            this.groups.add(pgroup1);
            this.groups.add(pgroup2);
            this.groupNum = this.groups.size();
            this.PPAll();
            SLDHBattleThread battleThread2 = new SLDHBattleThread(this);
            Thread T2 = new Thread(battleThread2);
            T2.start();
        }
        else if (this.I == 3) {
            this.end();
        }
        else {
            try {
                WriteOut.addtxt("水陆报错:" + this.I + ":" + MainServerHandler.getErrorMessage(new Exception()), 9999L);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void PPAll() {
        this.SLbuffer.append("\r\n");
        this.SLbuffer.append("第");
        this.SLbuffer.append(this.ppNum);
        this.SLbuffer.append("轮匹配的性质");
        if (this.I == 1) {
            this.SLbuffer.append("淘汰赛");
        }
        else if (this.I == 2) {
            this.SLbuffer.append("四强初赛");
        }
        else if (this.I == 3) {
            this.SLbuffer.append("四强终赛");
        }
        else {
            this.SLbuffer.append("未知性质:" + this.I);
        }
        this.SLbuffer.append("参与匹配的队伍数量");
        this.SLbuffer.append(this.teamNum);
        this.SLbuffer.append("分组数量");
        this.SLbuffer.append(this.groupNum);
        this.SLbuffer.append("匹配的队伍:");
        for (int i = 0, length = this.groups.size(); i < length; ++i) {
            this.PPMsg((SLDHGroup)this.groups.get(i));
        }
    }
    
    public void PPMsg(SLDHGroup group) {
        SLDHTeam team1 = group.getTeam1();
        SLDHTeam team2 = group.getTeam2();
        StringBuffer buffer = new StringBuffer();
        if (team2 == null) {
            buffer.append("队伍:");
            if (team1 != null) {
                buffer.append(team1.getTeamMsg());
                buffer.append(" ");
            }
            buffer.append("本次轮空");
        }
        else {
            buffer.append("队伍:");
            buffer.append(team1.getTeamMsg());
            buffer.append(" -VS- ");
            buffer.append("队伍:");
            buffer.append(team2.getTeamMsg());
        }
        this.SLbuffer.append("\r\n");
        this.SLbuffer.append(buffer.toString());
        buffer.append("#r有匹配到的队伍请做好准备,3分钟后开始战斗,队伍中有玩家状态异常视为本轮比赛认输");
        this.TZ(buffer.toString(), team1, team2);
    }
    
    public void TZ(String msg, SLDHTeam team1, SLDHTeam team2) {
        for (int i = 0; i < team1.getRoles().length; ++i) {
            FriendChatAction.createChatBeanForServer(msg, team1.getRoles()[i].getRoleShow().getRolename());
        }
        if (team2 != null) {
            for (int i = 0; i < team2.getRoles().length; ++i) {
                FriendChatAction.createChatBeanForServer(msg, team2.getRoles()[i].getRoleShow().getRolename());
            }
        }
    }
    
    public synchronized void groupEnd(SLDHGroup group, int i, String msg) {
        if (!this.groups.contains(group) || group.getState() != 1 || this.I == 4) {
            this.SLbuffer.append("\r\n非正常分组调用战斗结束接口:");
            this.SLbuffer.append(i);
            this.SLbuffer.append(" ");
            this.SLbuffer.append(this.I);
            this.SLbuffer.append(" ");
            this.SLbuffer.append(group.getState());
            this.SLbuffer.append(" ");
            this.SLbuffer.append((group.getTeam1() != null) ? group.getTeam1().getTeamMsg() : "null");
            this.SLbuffer.append(" ");
            this.SLbuffer.append((group.getTeam2() != null) ? group.getTeam2().getTeamMsg() : "null");
            WriteOut.writeTxtFile(this.SLbuffer.toString(), "sldh");
            return;
        }
        boolean is = this.groups.size() <= 4;
        this.SLbuffer.append("\r\n队伍:");
        this.SLbuffer.append((group.getTeam1() != null) ? group.getTeam1().getTeamMsg() : "null");
        this.SLbuffer.append(" ");
        this.SLbuffer.append((group.getTeam2() != null) ? group.getTeam2().getTeamMsg() : "null");
        this.SLbuffer.append(" 结果:");
        if (msg != null) {
            group.setState(i + 1);
            if (group.getTeam2() == null) {
                this.SLbuffer.append("一队因轮空获胜");
                this.getReward(group.getTeam1(), is ? 60 : 40, this.getRewardLvl());
                StringBuffer buffer = new StringBuffer();
                buffer.append(msg);
                this.endAppend(group, group.getTeam1(), buffer, true);
                this.TZ(buffer.toString(), group.getTeam1(), null);
            }
            else {
                this.SLbuffer.append((i == 1) ? "一队因" : "二队因");
                this.SLbuffer.append(msg);
                this.SLbuffer.append("获胜");
                SLDHTeam team1 = (i == 1) ? group.getTeam1() : group.getTeam2();
                SLDHTeam team2 = (i == 1) ? group.getTeam2() : group.getTeam1();
                this.getReward(team1, is ? 60 : 40, this.getRewardLvl());
                this.getReward(team2, is ? 30 : 20, this.getRewardLvl());
                StringBuffer buffer2 = new StringBuffer();
                buffer2.append("对面队伍还未准备好,你们直接获得本次胜利。");
                this.endAppend(group, team1, buffer2, true);
                this.TZ(buffer2.toString(), team1, null);
                buffer2.setLength(0);
                buffer2.append(msg);
                this.endAppend(group, team2, buffer2, false);
                this.TZ(buffer2.toString(), team2, null);
            }
        }
        else {
            if (i != 1 && i != 2) {
                this.SLbuffer.append("战斗获胜异常:" + i);
                i = 1;
                this.SLbuffer.append("默认一队获胜");
            }
            else {
                this.SLbuffer.append((i == 1) ? "一队通过战斗获胜" : "二队通过战斗获胜");
            }
            group.setState(i + 1);
            SLDHTeam team1 = (i == 1) ? group.getTeam1() : group.getTeam2();
            SLDHTeam team2 = (i == 1) ? group.getTeam2() : group.getTeam1();
            this.getReward(team1, is ? 60 : 40, this.getRewardLvl());
            this.getReward(team2, is ? 30 : 20, this.getRewardLvl());
            StringBuffer buffer2 = new StringBuffer();
            buffer2.append("你们队伍战胜了" + team2.getTeamMsg() + "。");
            this.endAppend(group, team1, buffer2, true);
            this.TZ(buffer2.toString(), team1, null);
            buffer2.setLength(0);
            buffer2.append("你们队伍惜败于" + team1.getTeamMsg() + "。");
            this.endAppend(group, team2, buffer2, false);
            this.TZ(buffer2.toString(), team2, null);
        }
        ++this.endNum;
        if (this.endNum == this.groupNum) {
            this.grouping();
        }
    }
    
    public void endAppend(SLDHGroup group, SLDHTeam team, StringBuffer buffer, boolean is) {
        buffer.append("#r");
        if (this.I == 3) {
            buffer.append("本次水陆大会结束,你们队伍成绩");
            int v = 2;
            if (is) {
                if (this.groups.size() >= 1 && this.groups.get(0) == group) {
                    v = 5;
                }
                else if (this.groups.size() >= 2 && this.groups.get(1) == group) {
                    v = 3;
                }
            }
            else if (this.groups.size() >= 1 && this.groups.get(0) == group) {
                v = 4;
            }
            team.setValue(v);
            buffer.append((v == 5) ? "冠军" : ((v == 4) ? "亚军" : ((v == 3) ? "季军" : "8强")));
        }
        else if (this.I == 2) {
            if (is) {
                buffer.append("请等待下一轮匹配,角逐冠军和亚军名次");
            }
            else {
                buffer.append("请等待下一轮匹配,角逐季军名次");
            }
        }
        else if (is) {
            buffer.append("请等待下一轮匹配");
        }
        else {
            buffer.append("你们队伍本次水陆大会结束,可以退场或者留下来观战其他队伍比赛。");
            if (this.groups.size() <= 4) {
                buffer.append("你们队伍成绩8强");
            }
            else {
                buffer.append("你们队伍未取得名次");
            }
        }
    }
    
    public int getRewardLvl() {
        if (this.groups.size() <= 4) {
            return 2;
        }
        return 1;
    }
    
    public void getReward(SLDHTeam team, int add, int value) {
        if (value > team.getValue()) {
            team.setValue(value);
        }
        if (add != 0) {
            AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
            assetUpdate.updata("水陆积分=" + add);
            assetUpdate.setMsg("你获得" + add + "水陆积分");
            String msg = Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate));
            SLDHRole[] roles = team.getRoles();
            for (int i = 0; i < roles.length; ++i) {
                SLDHRole role = roles[i];
                AllServiceUtil.getPackRecordService().addSLDH(role.getRoleID(), add);
                ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(role.getRoleShow().getRolename());
                LoginResult roleInfo = (ctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx)) : null;
                if (roleInfo != null) {
                    roleInfo.setScore(DrawnitemsAction.Splice(roleInfo.getScore(), "水陆积分=" + add, 2));
                    SendMessage.sendMessageToSlef(ctx, msg);
                }
            }
        }
    }
    
    public void PKOpen() {
        for (int i = 0, length = this.groups.size(); i < length; ++i) {
            SLDHGroup group = (SLDHGroup)this.groups.get(i);
            this.isPK(group);
            try {
                Thread.sleep(40L);
            }
            catch (Exception ex) {}
        }
    }
    
    public SLDHRole getRole(BigDecimal roleID) {
        return (SLDHRole)this.roleMap.get(roleID);
    }
    
    public SLDHRole addRole(SLDHRole role) {
        return (SLDHRole)this.roleMap.put(role.getRoleID(), role);
    }
    
    public void isPK(SLDHGroup group) {
        if (group.getTeam2() == null) {
            group.setState(1);
            this.groupEnd(group, 1, "本次轮空");
            return;
        }
        String name1 = null;
        String name2 = null;
        SLDHTeam team1 = group.getTeam1();
        int i = 0;
        while (i < team1.getRoles().length) {
            name1 = this.isAbnormal(team1.getRoles()[i].getRoleShow().getRolename());
            if (name1 != null) {
                break;
            }
            else {
                ++i;
            }
        }
        if (name1 == null) {
            SLDHTeam team2 = group.getTeam2();
            int j = 0;
            while (j < team2.getRoles().length) {
                name2 = this.isAbnormal(team1.getRoles()[j].getRoleShow().getRolename());
                if (name2 != null) {
                    break;
                }
                else {
                    ++j;
                }
            }
        }
        if (name1 == null && name2 == null) {
            BattleThreadPool.PKContest(group);
            return;
        }
        if (name2 != null) {
            group.setState(1);
            this.groupEnd(group, 1, name2);
        }
        else if (name1 != null) {
            group.setState(1);
            this.groupEnd(group, 2, name1);
        }
    }
    
    public String isAbnormal(String name) {
        ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(name);
        LoginResult roleInfo = (ctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx)) : null;
        if (roleInfo == null) {
            return "玩家#R" + name + "#W处于离线状态,导致本次匹配失败";
        }
        if ((long)roleInfo.getMapid() != 3320L) {
            return "玩家#R" + name + "#W不在当前地图内,导致本次匹配失败";
        }
        if ((int)roleInfo.getFighting() != 0) {
            return "玩家#R" + name + "#W处于战斗状态中,导致本次匹配失败";
        }
        return null;
    }
    
    public String getMsg() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("本次参赛队伍总数:");
        buffer.append(this.teamTotal);
        buffer.append("队。");
        if (this.I == 0) {
            buffer.append("现在处于进场阶段,");
        }
        else if (this.I == 1) {
            buffer.append("现在处于淘汰阶段,");
        }
        else if (this.I != 4) {
            buffer.append("现在处于4强选拔阶段,");
        }
        else if (this.I == 4) {
            buffer.append("本次水陆大会已经结束,请找魏征获取奖励");
        }
        if (this.I == 1 || this.I == 2 || this.I == 3) {
            buffer.append("当前阶段参与队伍数:");
            buffer.append(this.teamNum);
            buffer.append("队。还有");
            buffer.append((this.groupNum - this.endNum) * 2);
            buffer.append("个队伍还未结束战斗。");
        }
        return buffer.toString();
    }
    
    public void cx(int type) {
        if (this.I == 4 || this.I == 0) {
            return;
        }
        if (type == 0) {
            WriteOut.writeTxtFile(this.SLbuffer.toString(), "sldhGY");
            StringBuffer buffer = new StringBuffer();
            buffer.append(this.endNum + "/" + this.groupNum);
            for (int i = 0, length = this.groups.size(); i < length; ++i) {
                SLDHGroup group = (SLDHGroup)this.groups.get(i);
                this.SLbuffer.append("\r\n");
                this.SLbuffer.append(group.getTeam1().getTeamMsg());
                this.SLbuffer.append(" -VS- ");
                this.SLbuffer.append((group.getTeam2() != null) ? group.getTeam2().getTeamMsg() : "null");
                this.SLbuffer.append(" ");
                this.SLbuffer.append(group.getState());
                this.SLbuffer.append(" ");
                this.SLbuffer.append(group.getFightId());
                this.SLbuffer.append(" ");
                BattleData battleData = (BattleData)BattleThreadPool.BattleDatas.get(Integer.valueOf(group.getFightId()));
                this.SLbuffer.append((battleData != null) ? "还在战斗" : "找不到战斗");
            }
            WriteOut.writeTxtFile(this.SLbuffer.toString(), "sldhCX");
        }
        else if (type == 1) {
            for (int j = 0, length2 = this.groups.size(); j < length2; ++j) {
                SLDHGroup group2 = (SLDHGroup)this.groups.get(j);
                if (group2.getState() == 1 || group2.getState() == 0) {
                    group2.setState(2);
                    this.SLbuffer.append("\r\n队伍:");
                    this.SLbuffer.append((group2.getTeam1() != null) ? group2.getTeam1().getTeamMsg() : "null");
                    this.SLbuffer.append(" ");
                    this.SLbuffer.append((group2.getTeam2() != null) ? group2.getTeam2().getTeamMsg() : "null");
                    this.SLbuffer.append(" 结果:强行结束默认一队获胜");
                }
            }
            this.grouping();
        }
    }
    
    @Override
    public void getAward(ChannelHandlerContext ctx, LoginResult loginResult) {
    }
    
    @Override
    public boolean isEnd() {
        return this.I != 4;
    }
    
    @Override
    public boolean isTime(long time) {
        return false;
    }
    
    @Override
    public int battleEnd(BattleEnd battleEnd, LoginResult loginResult, MapMonsterBean bean, int v) {
        return 0;
    }
    
    @Override
    public String getSceneMsg(LoginResult loginResult, long oldMapId, long mapId) {
        return null;
    }
    
    public int getJS() {
        return this.JS;
    }
    
    public int getCI() {
        return this.CI;
    }
    
    public int getI() {
        return this.I;
    }
    
    public void setI(int i) {
        this.I = i;
    }
    
    public void loadStatue() {
        if (this.lastShows == null) {
            TitleUtil.addTitle(TitleUtil.SL, new BigDecimal[0]);
            return;
        }
        byte[] npcs = ReadTxtUtil.InputStream2ByteArray(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\npc.txt");
        try {
            if (npcs.length > 10) {
                byte a = npcs[npcs.length - 4];
                npcs[npcs.length - 4] = npcs[4];
                npcs[4] = a;
            }
            String npcss = NewAESUtil.AESJDKDncode(new String(npcs));
            npcs = npcss.getBytes();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        String mapNpcs = MessageGZIP.uncompressToString(npcs, "utf-8");
        AllNpcBean allNpcBean = (AllNpcBean)GsonUtil.getGsonUtil().getgson().fromJson(mapNpcs, AllNpcBean.class);
        BigDecimal[] ids = new BigDecimal[this.lastShows.length];
        for (int i = 0; i < this.lastShows.length; ++i) {
            RoleShow show = this.lastShows[i];
            ids[i] = show.getRole_id();
            int npcid = 400096 + i;
            NpcInfoBean infoBean = (NpcInfoBean)allNpcBean.getAllNpcInfo().get(npcid + "");
            if (infoBean != null) {
                infoBean.getNpctable().setNpcname(show.getRolename());
                infoBean.getNpctable().setSkin(show.getSpecies_id() + "0");
            }
        }
        TitleUtil.addTitle(TitleUtil.SL, ids);
        String msg = GsonUtil.getGsonUtil().getgson().toJson(allNpcBean);
        ReadPoolUtil.text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\npc.txt", msg);
    }
    
    @Override
    public String UPMonster(BattleData battleData, String[] teams, int type, StringBuffer buffer) {
        return null;
    }
    
    @Override
    public Map<Integer, MonsterMoveBase> getMapMonster(StringBuffer buffer, Map<Integer, MonsterMoveBase> moveMap, long mapId) {
        return null;
    }
    
    static {
        SLDHScene.JG = 300000L;
    }
}
