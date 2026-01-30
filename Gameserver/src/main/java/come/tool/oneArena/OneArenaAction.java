package come.tool.oneArena;

import org.come.bean.NChatBean;
import org.come.entity.Record;
import org.come.redis.RedisCacheUtil;
import come.tool.FightingData.Battlefield;
import java.util.ArrayList;
import java.util.List;
import org.come.model.Dorp;
import come.tool.Good.DropUtil;
import come.tool.Title.TitleUtil;
import org.come.until.AllServiceUtil;
import org.come.action.monitor.MonitorUtil;
import come.tool.Stall.AssetUpdate;
import org.come.bean.ConfirmBean;
import come.tool.Role.RoleData;
import come.tool.newTask.TaskRecord;
import come.tool.Battle.BattleThreadPool;
import come.tool.Role.RolePool;
import org.come.until.GsonUtil;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import come.tool.Battle.BattleMixDeal;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import org.come.action.IAction;

public class OneArenaAction implements IAction
{
    private static Object LOCK;
    private static ConcurrentHashMap<BigDecimal, OneArenaLock> concurrentHashMap;
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (roleInfo == null) {
            return;
        }
        if (BattleMixDeal.lvltrue((int)roleInfo.getGrade()) < 1) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("1转及以上玩家才可参与竞技场"));
            return;
        }
        OneArenaBean oneArenaBean = null;
        if (message.startsWith("P")) {
            battleOneArena(ctx, roleInfo, new BigDecimal(message.substring(1)));
        }
        else if (message.equals("1")) {
            OneArenaRole role = new OneArenaRole();
            role.setRoleId(roleInfo.getRole_id());
            role.setSkin(roleInfo.getSpecies_id() + "");
            role.setLvl((int)roleInfo.getGrade());
            role.setName(roleInfo.getRolename());
            oneArenaBean = getOneArenaRole(role);
        }
        else if (message.equals("2")) {
            oneArenaBean = getOneArenaNotes(roleInfo.getRole_id());
        }
        else if (message.equals("3")) {
            addNumOneArena(ctx, roleInfo, null);
        }
        else if (message.equals("4")) {
            dayAwardOneArena(ctx, roleInfo);
        }
        if (oneArenaBean == null) {
            return;
        }
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().oneArenaAgreement(GsonUtil.getGsonUtil().getgson().toJson(oneArenaBean)));
    }
    
    public static void battleOneArena(ChannelHandlerContext ctx, LoginResult roleInfo, BigDecimal otherRole) {
        RoleData roleData = RolePool.getRoleData(roleInfo.getRole_id());
        if (roleData == null) {
            return;
        }
        String[] teams = roleInfo.getTeam().split("\\|");
        if (teams.length != 1) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("组队状态不能挑战"));
            return;
        }
        int num = 10;
        TaskRecord taskRecord = roleData.getTaskRecord(3);
        if (taskRecord != null) {
            num += taskRecord.getrSum();
            num -= taskRecord.getcSum();
        }
        if (num <= 0) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("今日挑战次数已用完"));
            return;
        }
        String value = BattleThreadPool.addOneArenaBattle(roleInfo, teams, otherRole);
        if (value != null) {
            SendMessage.sendMessageToSlef(ctx, value);
            return;
        }
        if (taskRecord == null) {
            taskRecord = new TaskRecord(3);
            roleData.addTaskRecord(taskRecord);
        }
        taskRecord.addCSum(1);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().TaskNAgreement("C3=L"));
    }
    
    public static void addNumOneArena(ChannelHandlerContext ctx, LoginResult roleInfo, Object object) {
        RoleData roleData = RolePool.getRoleData(roleInfo.getRole_id());
        if (roleData == null) {
            return;
        }
        TaskRecord taskRecord = roleData.getTaskRecord(3);
        if (taskRecord != null && taskRecord.getrSum() >= 10) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("达到每日购买上限"));
            return;
        }
        int moeny = (((taskRecord != null) ? taskRecord.getrSum() : 0) + 1) * 2000;
        if (roleInfo.getCodecard().longValue() < (long)moeny) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你的仙玉不足" + moeny));
            return;
        }
        if (object == null) {
            ConfirmBean confirmBean = new ConfirmBean();
            confirmBean.setMSG("你是否要消耗#G" + moeny + "仙玉#W购买挑战次数");
            confirmBean.setType(101);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().confirmAgreement(GsonUtil.getGsonUtil().getgson().toJson(confirmBean)));
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        roleInfo.setCodecard(new BigDecimal(roleInfo.getCodecard().longValue() - (long)moeny));
        assetUpdate.updata("X=-" + moeny);
        MonitorUtil.getMoney().useX((long)moeny);
        if (taskRecord == null) {
            taskRecord = new TaskRecord(3);
            roleData.addTaskRecord(taskRecord);
        }
        taskRecord.addRSum(1);
        assetUpdate.setTask("C3=R");
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void dayAwardOneArena(ChannelHandlerContext ctx, LoginResult roleInfo) {
        int value = AllServiceUtil.getOneArenaRoleService().selectRankPast(roleInfo.getRole_id());
        if (value == 0) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你今日已领取过或者不满足领取条件"));
            return;
        }
        Dorp dorp = getDrop(value);
        if (dorp == null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你昨日单人竞技场排名:" + value + ".不在领取范围内"));
            return;
        }
        if (AllServiceUtil.getOneArenaRoleService().updateDayResetRole(roleInfo.getRole_id()) == 0) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你今日已领取"));
            return;
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append("你昨日单人竞技场排名:");
        buffer.append(value);
        if (value == 1 || value == 2 || value == 3) {
            buffer.append(".额外获得称谓#R");
            if (value == 1) {
                buffer.append("横扫六合");
                TitleUtil.addTitle("横扫六合", new BigDecimal[] { roleInfo.getRole_id() });
            }
            else if (value == 2) {
                buffer.append("天下第一");
                TitleUtil.addTitle("天下第一", new BigDecimal[] { roleInfo.getRole_id() });
            }
            else if (value == 3) {
                buffer.append("超凡入圣");
                TitleUtil.addTitle("超凡入圣", new BigDecimal[] { roleInfo.getRole_id() });
            }
        }
        DropUtil.getDrop3(roleInfo, dorp.getDorpValue(), "单人竞技场礼包", 22, 1.0, null, null, buffer.toString());
    }
    
    public static Dorp getDrop(int rank) {
        if (rank == 1) {
            return GameServer.getDorp("2024");
        }
        if (rank == 2) {
            return GameServer.getDorp("2025");
        }
        if (rank == 3) {
            return GameServer.getDorp("2026");
        }
        if (rank <= 50) {
            return GameServer.getDorp("2027");
        }
        if (rank <= 200) {
            return GameServer.getDorp("2028");
        }
        if (rank <= 1000) {
            return GameServer.getDorp("2029");
        }
        return null;
    }
    
    public static OneArenaBean getOneArenaRole(OneArenaRole role) {
        OneArenaBean oneArenaBean = new OneArenaBean();
        oneArenaBean.setPlace(AllServiceUtil.getOneArenaRoleService().selectRank(role.getRoleId()));
        if (oneArenaBean.getPlace() == 0) {
            AllServiceUtil.getOneArenaRoleService().insertOneArenaRole(role);
            oneArenaBean.setPlace(AllServiceUtil.getOneArenaRoleService().selectRank(role.getRoleId()));
        }
        List<Integer> list = getRankInts(oneArenaBean.getPlace());
        for (int i = 1; i <= 5; ++i) {
            if (i != oneArenaBean.getPlace()) {
                list.add(Integer.valueOf(i));
            }
        }
        List<OneArenaRole> oneArenaRoles = AllServiceUtil.getOneArenaRoleService().selectRankRoles(list);
        oneArenaBean.setArenaList(oneArenaRoles);
        return oneArenaBean;
    }
    
    public static List<Integer> getRankInts(int rank) {
        List<Integer> list = new ArrayList<>();
        if (rank >= 5) {
            int rankValue = (int)((double)rank * 0.08) + 1;
            rankValue = rank + Battlefield.random.nextInt(rankValue);
            if (rankValue <= rank) {
                rankValue = rank + 1;
            }
            list.add(Integer.valueOf(rankValue));
        }
        if (rank > 6) {
            int rankValue = rank;
            for (int i = 0; i < 4; ++i) {
                int rankValue2 = (int)((double)rankValue * 0.985);
                int value = rankValue2 + Battlefield.random.nextInt(rankValue - rankValue2);
                if (value > 5) {
                    list.add(Integer.valueOf(value));
                }
                rankValue = rankValue2;
            }
        }
        return list;
    }
    
    public static OneArenaBean getOneArenaNotes(BigDecimal roleId) {
        OneArenaBean oneArenaBean = new OneArenaBean();
        List<OneArenaNotes> notesList = AllServiceUtil.getOneArenaNotesService().selectRole(roleId, RedisCacheUtil.oneAreanNotes_min);
        oneArenaBean.setNotesList(notesList);
        return oneArenaBean;
    }
    
    public static void OneArenaReset() {
        synchronized (OneArenaAction.LOCK) {
            TitleUtil.addTitle("武圣", new BigDecimal[0]);
            TitleUtil.addTitle("天下第一", new BigDecimal[0]);
            TitleUtil.addTitle("超凡入圣", new BigDecimal[0]);
            AllServiceUtil.getOneArenaRoleService().updateDayReset();
        }
    }
    
    public static OneArenaNotes OneArenaEnd(OneArenaRole role1, OneArenaRole role2, int isV) {
        OneArenaLock oneArenaLock = new OneArenaLock(role1.getRoleId(), role2.getRoleId());
        OneArenaLock lock = getLock(oneArenaLock);
        OneArenaNotes oneArenaNotes = null;
        int place = 0;
        int roleRank1 = 0;
        int roleRank2 = 0;
        synchronized (lock) {
            if (isV == 0) {
                roleRank1 = AllServiceUtil.getOneArenaRoleService().selectRank(role1.getRoleId());
                roleRank2 = AllServiceUtil.getOneArenaRoleService().selectRank(role2.getRoleId());
                if (roleRank1 == 0 || roleRank2 == 0) {
                    AllServiceUtil.getRecordService().insert(new Record(10, role1.getRoleId() + ":" + roleRank1 + "_" + role2.getRoleId() + ":" + roleRank2));
                    roleRank1 = 0;
                    roleRank2 = 0;
                }
                if (roleRank2 < roleRank1) {
                    place = roleRank1 - roleRank2;
                    AllServiceUtil.getOneArenaRoleService().updateRankRole(role1.getRoleId(), roleRank2, role1.getSkin(), role1.getName(), role1.getLvl());
                    AllServiceUtil.getOneArenaRoleService().updateRankRole(role2.getRoleId(), roleRank1, role2.getSkin(), role2.getName(), role2.getLvl());
                }
            }
            removeLock(lock, oneArenaLock, role1.getRoleId(), role2.getRoleId());
        }
        if (place != 0 && roleRank2 <= 3) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("#Y力拔山兮气盖世,#R");
            buffer.append(role1.getName());
            buffer.append("#Y武艺超群，力战群雄，成功夺取排行榜第#R");
            buffer.append((roleRank2 == 1) ? "一" : ((roleRank2 == 2) ? "二" : "三"));
            buffer.append("#Y位，实力属实令人惊叹不已！！");
            NChatBean bean = new NChatBean();
            bean.setId(9);
            bean.setMessage(buffer.toString());
            String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
            SendMessage.sendMessageToAllRoles(msg);
            if (role2.getRoleId().intValue() > 0) {
                ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(role2.getName());
                if (ctx != null) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你单人竞技场的名次被#R" + role1.getName() + "#Y夺走了"));
                }
            }
        }
        oneArenaNotes = new OneArenaNotes(role1, role2, isV, place);
        AllServiceUtil.getOneArenaNotesService().insertOneArenaNotes(oneArenaNotes);
        return oneArenaNotes;
    }
    
    public static OneArenaLock getLock(OneArenaLock oneArenaLock) {
        synchronized (OneArenaAction.LOCK) {
            OneArenaLock arenaLock = (OneArenaLock)OneArenaAction.concurrentHashMap.get(oneArenaLock.role1);
            if (arenaLock == null) {
                arenaLock = (OneArenaLock)OneArenaAction.concurrentHashMap.get(oneArenaLock.role2);
            }
            if (arenaLock != null) {
                arenaLock.addLock(oneArenaLock);
            }
            else {
                arenaLock = oneArenaLock;
            }
            OneArenaAction.concurrentHashMap.put(oneArenaLock.role1, arenaLock);
            OneArenaAction.concurrentHashMap.put(oneArenaLock.role2, arenaLock);
            return arenaLock;
        }
    }
    
    public static Object removeLock(OneArenaLock lock, OneArenaLock oneArenaLock, BigDecimal role1, BigDecimal role2) {
        synchronized (OneArenaAction.LOCK) {
            lock.removeLock(oneArenaLock);
            if (lock.isLock(role1)) {
                OneArenaAction.concurrentHashMap.remove(role1);
            }
            if (lock.isLock(role2)) {
                OneArenaAction.concurrentHashMap.remove(role2);
            }
            return null;
        }
    }
    
    static {
        OneArenaAction.LOCK = new Object();
        OneArenaAction.concurrentHashMap = new ConcurrentHashMap<>();
    }
    
    static class OneArenaLock
    {
        private BigDecimal role1;
        private BigDecimal role2;
        private OneArenaLock Lock;
        
        public OneArenaLock(BigDecimal role1, BigDecimal role2) {
            this.role1 = role1;
            this.role2 = role2;
        }
        
        public void addLock(OneArenaLock lock) {
            if (this.Lock == null) {
                this.Lock = lock;
            }
            else {
                this.Lock.addLock(lock);
            }
        }
        
        public OneArenaLock removeLock(OneArenaLock lock) {
            if (this == lock) {
                this.role1 = null;
                this.role2 = null;
                return this.Lock;
            }
            if (this.Lock != null) {
                this.Lock = this.Lock.removeLock(lock);
            }
            return this;
        }
        
        public boolean isLock(BigDecimal role) {
            return (this.role1 == null || this.role1.compareTo(role) != 0) && (this.role2 == null || this.role2.compareTo(role) != 0) && (this.Lock == null || this.Lock.isLock(role));
        }
    }
}
