package come.tool.Battle;

import come.tool.Good.DropModel;
import come.tool.Scene.CJ.CJGroup;
import come.tool.Scene.CJ.CJScene;
import come.tool.Scene.CJ.CJTeam;
import come.tool.Scene.LTS.LTSRole;
import come.tool.Scene.LTS.LTSScene;
import come.tool.Scene.LTS.LTSArena;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.FightingManData;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.MixDeal;
import come.tool.Scene.DNTG.DNTGScene;
import come.tool.PK.PkMatch;
import org.come.action.monitor.MonitorUtil;
import come.tool.Scene.TGDB.TGDBScene;
import come.tool.BangBattle.BangFight;
import come.tool.BangBattle.BangBattlePool;
import org.come.model.Gamemap;
import java.util.Map;
import come.tool.FightingData.FightingLingbao;
import org.come.entity.Lingbao;
import org.come.entity.Baby;
import java.util.HashMap;
import come.tool.FightingData.ManData;
import come.tool.FightingData.Battlefield;
import org.come.model.Monster;
import java.util.ArrayList;
import come.tool.Scene.ZZS.ZZSScene;
import java.util.Iterator;
import come.tool.Scene.Scene;
import org.come.tool.WriteOut;
import org.come.handler.MainServerHandler;
import come.tool.teamArena.LadderArenaUtil;
import come.tool.Scene.SLDH.SLDHScene;
import come.tool.Scene.SceneUtil;
import org.come.task.MonsterUtil;
import come.tool.PK.PKPool;
import come.tool.newTeam.TeamRole;
import come.tool.newTeam.TeamBean;
import come.tool.teamArena.TeamArenaGroup;
import come.tool.Role.RoleData;
import org.come.entity.Record;
import come.tool.Role.RolePool;
import come.tool.oneArena.OneArenaRole;
import org.come.model.Robots;
import come.tool.Scene.SLDH.SLDHGroup;
import org.come.task.MapMonsterBean;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.entity.Goodstable;
import org.come.until.AllServiceUtil;
import java.math.BigDecimal;
import come.tool.Stall.AssetUpdate;
import org.apache.commons.lang.StringUtils;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
//战斗线程池
public class BattleThreadPool
{
    static String CHECKTS6 = Agreement.getAgreement().PromptAgreement("你在匹配中，无法PK别人");
    static String CHECKTS7 = Agreement.getAgreement().PromptAgreement("此队在匹配中，无法被PK");

    static String CHECKTS1;
    static String CHECKTS2;
    static String CHECKTS3;
    static String CHECKTS4;
    static String CHECKTS5;
    private static Object Thread_LOCK;
    /** 单线程处理个数限制 */
    public static final int LIMITMAX = 20;
    public static final int LIMITMIN = 5;
    /** 常驻线程数 */
    public static final int FORMAL = 20;
    /** 自增长战斗编号 */
    public static int increasesum;
    /** 记录正在战斗数据 */
    public static ConcurrentHashMap<Integer, BattleData> BattleDatas;
    /** 记录在运行的线程 */
    public static List<BattleThread> pools;
    public static boolean isGXh;
    
    public static void addBattle(ChannelHandlerContext ctx, FightingForesee fightingForesee) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        addBattle(loginResult, fightingForesee);
    }
    /** 吃鸡直送通道 */
    public static void cjPKContest(CJGroup group) {
        BattleData battleData = new BattleData();
        battleData.setBattleType(104);
        battleData.setBattleNumber(getIncreasesum());
        battleData.setBattletime(System.currentTimeMillis());

        group.setState(1);
        group.setFightId(battleData.getBattleNumber());
        battleData.setCjpkGroup(group);

        battleData.setTeam1(group.getTeam1().getTeams());
        battleData.setTeam2(group.getTeam2().getTeams());
        isRole(battleData.getTeam1(), battleData,1);
        isRole(battleData.getTeam2(), battleData,2);
        battleData.setCalculator(battleData.getParticipantlist().size());
        addPool(battleData);
    }
    public static void addBattle(LoginResult loginResult, FightingForesee fightingForesee) {
        BattleData battleData = BattleMixDeal.initData(fightingForesee, (long)loginResult.getMapid());
        battleData.setFightingForesee(fightingForesee);
        // 地图限制
        String value = isMap(loginResult, battleData);
        /**
         * 自动机器人
         * */
        if (value != null) {
            sendErrorMessage(loginResult, value);
            return;
        }

        value = isFollow(battleData);
        if (value != null) {
            sendErrorMessage(loginResult, value);
            return;
        }
        value = isRole(battleData.getTeam1(), battleData, 1);
        if (value != null) {
            sendErrorMessage(loginResult, value);
            return;
        }
        value = isRole(battleData.getTeam2(), battleData, 2);
        if (value != null) {
            sendErrorMessage(loginResult, value);
            return;
        }
        value = isMonster(fightingForesee, loginResult, battleData);
        if (value != null) {
            if (!value.equals("")) {
                sendErrorMessage(loginResult, value);
            }
            return;
        }
        else {
            if (isBangFight(loginResult, battleData)) {
                return;
            }
            battleData.setCalculator(battleData.getParticipantlist().size());
            List<String> list = createArray(fightingForesee, battleData);
            double XS = 1.0;
            if (fightingForesee.getType() == 2 && fightingForesee.getNd() != 0) {
                battleData.getBattlefield().ndXS = fightingForesee.getNd();
                if (fightingForesee.getNd() == 1) {
                    XS = 1.8;
                }
                else if (fightingForesee.getNd() == 2) {
                    XS = 2.8;
                }
                else if (fightingForesee.getNd() == 3) {
                    XS = 4.0;
                }
                else {
                    XS = 5.4;
                }
            }
            loadCreep(list, fightingForesee.getAlias(), battleData.getMaxLvl(), battleData, XS);
            value = addPool(battleData);
            MapMonsterBean bean = battleData.getMonsterBean();
            if (bean != null && StringUtils.isNotBlank(bean.getGoodId())) {
                String[] teams = loginResult.getTeam().split("\\|");
                LoginResult[] logs = new LoginResult[teams.length];
                for (int i = 0; i < teams.length; ++i) {
                    LoginResult login = null;
                    if (i == 0) {
                        login = loginResult;
                    }
                    else {
                        ChannelHandlerContext ctx2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(teams[i]);
                        if (ctx2 != null) {
                            login = (LoginResult)GameServer.getAllLoginRole().get(ctx2);
                        }
                    }
                    if (login == null) {}
                    logs[i] = login;
                }
                for (LoginResult log : logs) {
                    String[] split = bean.getGoodId().split("\\|");
                    AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
                    for (String s : split) {
                        String[] split2 = s.split("=");
                        BigDecimal goodId = new BigDecimal(split2[0]);
                        List<Goodstable> sameGoodstable = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(log.getRole_id(), goodId);
                        Goodstable good = sameGoodstable.get(0);
                        good.setUsetime(Integer.valueOf((int)good.getUsetime() - Integer.parseInt(split2[1])));
                        AllServiceUtil.getGoodsTableService().updateGoodRedis(good);
                        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
                    }
                    SendMessage.sendMessageByRoleName(log.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                }
            }
            if (value != null) {
                sendErrorMessage(loginResult, value);
                return;
            }
            return;
        }
    }
    /**击杀*/
    public static boolean addBattle(LoginResult role, String[] teams, MapMonsterBean bean) {
        BattleData battleData = BattleMixDeal.initData(bean, teams, (long)role.getMapid());
        if (battleData.getRobots() == null) {
            return false;
        }
        String value = isRole(battleData.getTeam1(), battleData, 1);
        if (value != null) {
            return false;
        }
        battleData.setCalculator(battleData.getParticipantlist().size());
        FightingForesee fightingForesee = new FightingForesee();
        List<String> list = createCreep(battleData.getRobots(), fightingForesee, battleData);
        loadCreep(list, fightingForesee.getAlias(), battleData.getMaxLvl(), battleData, 1.0);
        addPool(battleData);
        return true;
    }
    /** 水陆直送通道 */
    public static void PKContest(SLDHGroup group) {
        BattleData battleData = new BattleData();
        battleData.setBattleType(21);
        battleData.setBattleNumber(getIncreasesum());
        battleData.setBattletime(System.currentTimeMillis());
        group.setState(1);
        group.setFightId(battleData.getBattleNumber());
        battleData.setPkGroup(group);
        battleData.setTeam1(group.getTeam1().getTeams());
        battleData.setTeam2(group.getTeam2().getTeams());
        isRole(battleData.getTeam1(), battleData, 1);
        isRole(battleData.getTeam2(), battleData, 2);
        battleData.setCalculator(battleData.getParticipantlist().size());
        addPool(battleData);
    }
    /** 日常副本 */
    public static boolean RCFB(String[] team1, Robots robots, int v) {
        BattleData battleData = BattleMixDeal.initData(robots, team1);
        String value = isRole(battleData.getTeam1(), battleData, 1);
        if (value != null) {
            SendMessage.sendMessageByRoleName(team1[0], value);
            return false;
        }
        battleData.setRCv(Integer.valueOf(v));
        battleData.setCalculator(battleData.getParticipantlist().size());
        FightingForesee fightingForesee = new FightingForesee();
        List<String> list = createCreep(robots, fightingForesee, battleData);
        /** 直接加载野怪数据 */
        loadCreep(list, fightingForesee.getAlias(), battleData.getMaxLvl(), battleData, 1.0);
        addPool(battleData);
        return true;
    }
    /** 召唤兽副本 */
    public static boolean BBFB(String[] team1, Robots robots, int v) {
        BattleData battleData = BattleMixDeal.initData(robots, team1);
        battleData.setBattleType(34);
        String value = isRole(battleData.getTeam1(), battleData, 1);
        if (value != null) {
            SendMessage.sendMessageByRoleName(team1[0], value);
            return false;
        }
        battleData.setBBv(v);
        battleData.setCalculator(battleData.getParticipantlist().size());
        FightingForesee fightingForesee = new FightingForesee();
        List<String> list = createCreep(robots, fightingForesee, battleData);
        loadCreep(list, fightingForesee.getAlias(), battleData);
        JQCreep(battleData, v);
        addPool(battleData);
        return true;
    }
    /** 大闹天宫击杀野怪 */
    public static boolean addDNTGBattle(LoginResult role, String[] teams, MapMonsterBean bean, double xs) {
        BattleData battleData = BattleMixDeal.initData(bean, teams, (long)role.getMapid());
        if (battleData.getRobots() == null) {
            return false;
        }
        String value = isRole(battleData.getTeam1(), battleData, 1);
        if (value != null) {
            return false;
        }
        /** 大闹id */
        battleData.setSceneId(1011);
        battleData.setCalculator(battleData.getParticipantlist().size());
        FightingForesee fightingForesee = new FightingForesee();
        List<String> fightCreeps = createCreep(battleData.getRobots(), fightingForesee, battleData);
        /** 直接加载野怪数据 */
        loadCreep(fightCreeps, fightingForesee.getAlias(), battleData.getMaxLvl(), battleData, xs);
        addPool(battleData);
        return true;
    }
    /** 幻境试炼 */
    public static boolean HJSL(LoginResult role, String[] teams, Robots robots, int v) {
        BattleData battleData = BattleMixDeal.initData(robots, teams);
        String value = isRole(battleData.getTeam1(), battleData, 1);
        if (value != null) {
            SendMessage.sendMessageByRoleName(teams[0], value);
            return false;
        }
        battleData.setHJv(v);
        battleData.setCalculator(battleData.getParticipantlist().size());
        FightingForesee fightingForesee = new FightingForesee();
        List<String> list = createCreep(robots, fightingForesee, battleData);
        /** 直接加载野怪数据 */
        loadCreep(list, fightingForesee.getAlias(), battleData.getMaxLvl(), battleData, (double)((robots.getRobotID() - 9000) / 6 + 1));
        addPool(battleData);
        return true;
    }
    /** 单人竞技场 */
    public static String addOneArenaBattle(LoginResult role, String[] teams, BigDecimal otherRole) {
        OneArenaRole myArenaRole = new OneArenaRole(role);
        OneArenaRole otherArenaRole = null;
        if (otherRole.longValue() < 0L) {
            otherArenaRole = ((otherRole.longValue() >= -1000L) ? AllServiceUtil.getOneArenaRoleService().selectRole(otherRole) : null);
        }
        else {
            RoleData roleData = RolePool.getLineRoleData(otherRole);
            if (roleData == null) {
                return Agreement.getAgreement().PromptAgreement("找不到该玩家 换个对手挑战吧");
            }
            otherArenaRole = new OneArenaRole(roleData.getLoginResult());
        }
        if (otherArenaRole == null) {
            AllServiceUtil.getRecordService().insert(new Record(10, "挑战ID:" + otherRole));
            return Agreement.getAgreement().PromptAgreement("挑战出了问题换个人试试");
        }
        BattleData battleData = BattleMixDeal.initData(teams, myArenaRole, otherArenaRole, (long)role.getMapid());
        String value = isRole(battleData.getTeam1(), battleData, 1);
        if (value != null) {
            return value;
        }
        /**加载被挑战人的数据*/
        value = loadCreep(otherArenaRole, battleData);
        if (value != null) {
            return value;
        }
        battleData.setCalculator(battleData.getParticipantlist().size());
        addPool(battleData);
        return null;
    }
    /** 多人竞技场竞技场 */
    public static String addTeamArenaBattle(TeamArenaGroup teamArenaGroup) {
        String[] team1 = new String[teamArenaGroup.getTeam1().getTeamSize()];
        String[] team2 = new String[teamArenaGroup.getTeam2().getTeamSize()];
        BattleData battleData = new BattleData();
        String value = isTeamArena(teamArenaGroup.getTeam1(), team1, battleData, 1);
        if (value != null) {
            return value;
        }
        value = isTeamArena(teamArenaGroup.getTeam2(), team2, battleData, 2);
        if (value != null) {
            return value;
        }
        battleData.setTeamArenaGroup(teamArenaGroup);
        battleData.setBattleNumber(getIncreasesum());
        battleData.setBattleType(102);
        battleData.setTeam1(team1);
        battleData.setTeam2(team2);
        battleData.setBattletime(System.currentTimeMillis());
        battleData.setCalculator(battleData.getParticipantlist().size());
        addPool(battleData);
        return null;
    }
    /**检测是否处于异常状态*/
    public static String isTeamArena(TeamBean team, String[] teams, BattleData battleData, int type) {
        int i = 0;
        while (i < teams.length) {
            TeamRole teamRole = (TeamRole)team.getTeams().get(i);
            LoginResult roleInfo = (teamRole != null) ? RolePool.getLoginResult(teamRole.getRoleId()) : null;
            if (roleInfo == null) {
                if (teamRole != null) {
                    return "玩家#R" + teamRole.getName() + "#W处于离线状态,导致本次匹配失败";
                }
                return "有玩家处于离线状态,导致本次匹配失败";
            }
            else {
                if ((int)roleInfo.getFighting() != 0) {
                    return "玩家#R" + roleInfo.getRolename() + "#W处于战斗状态中,导致本次匹配失败";
                }
                if (PKPool.getPkPool().seekPkMatch(roleInfo.getRole_id()) != null) {
                    return "玩家#R" + roleInfo.getRolename() + "#W携带战斗,导致本次匹配失败";
                }
                teams[i] = roleInfo.getRolename();
                battleData.getParticipantlist().add(teams[i]);
                if (i == 0) {
                    if (type == 1) {
                        battleData.setRoleId1(roleInfo.getRole_id());
                    }
                    else {
                        battleData.setRoleId2(roleInfo.getRole_id());
                    }
                }
                ++i;
            }
        }
        return null;
    }
    /**将战斗添加进线程*/
    public static String addPool(BattleData battleData) {
        try {
            BattleThreadPool.BattleDatas.put(battleData.getBattleNumber(), battleData);
            FightingForesee fightingForesee = new FightingForesee();
            fightingForesee.setType(battleData.getBattleType());
            if (battleData.getRoleId1() != null) {
                fightingForesee.setYidui(battleData.getTeam1()[0]);
            }
            if (battleData.getRoleId1() != null) {
                fightingForesee.setYidui(battleData.getTeam1()[0]);
            }
            if (battleData.getRobots() != null) {
                fightingForesee.setRobotid(battleData.getRobots().getRobotid());
                fightingForesee.setAlias(battleData.getRobots().getRobotname());
            }
            battleData.setFightingForesee(fightingForesee);
            StringBuffer buffer = new StringBuffer();
            if (battleData.getMonsterBean() != null) {
                String value = MonsterUtil.UPMonster(battleData, null, 1, buffer);
                if (value != null) {
                    return value;
                }
            }
            sendBattleState2(battleData.getBattleNumber(), battleData.getParticipantlist(), buffer);
            synchronized (BattleThreadPool.Thread_LOCK) {
                int type = isextra();
                if (type == -1) {
                    BattleThread battleThread = new BattleThread(battleData.getBattleNumber());
                    BattleThreadPool.pools.add(battleThread);
                    Thread t1 = new Thread(battleThread);
                    t1.start();
                    return null;
                }
                ((BattleThread)BattleThreadPool.pools.get(type)).getNumbers().add(Integer.valueOf(battleData.getBattleNumber()));
            }
        }
        catch (Exception e) {
            battleData.setWinCamp(-1);
            removeBattleData(battleData);
        }
        return null;
    }
    /**删除战斗数据*/
    public static void removeBattleData(BattleData battleData) {
        try {
            BattleThreadPool.BattleDatas.remove(battleData.getBattleNumber());
            StringBuffer buffer = new StringBuffer();
            if (battleData.getMonsterBean() != null) {
                MonsterUtil.UPMonster(battleData, (battleData.getWinCamp() < 0) ? BattleMixDeal.Teams : ((battleData.getWinCamp() <= 1) ? battleData.getTeam1() : battleData.getTeam2()), (battleData.getWinCamp() <= 0) ? 0 : 2, buffer);
            }
            sendBattleState2(0, battleData.getParticipantlist(), buffer);
            //-2异常结束 -1初始化报错   0野怪胜利或者默认胜利   1玩家队伍一胜利 2玩家队伍二胜利
            if (battleData.getWinCamp() < 0) {
                if (battleData.getBattleType() == 21) {
                    Scene scene = SceneUtil.getScene(1012);
                    if (scene != null) {
                        SLDHScene sldhScene = (SLDHScene)scene;
                        sldhScene.groupEnd(battleData.getPkGroup(), battleData.getWinCamp(), null);
                    }
                }else if (battleData.getBattleType()==104){
                    Scene scene = SceneUtil.getScene(SceneUtil.CJID);
                    if (scene != null) {
                        CJScene cjScene = (CJScene) scene;
                        cjScene.groupEnd(battleData.getCjpkGroup(), battleData.getWinCamp());
                    }
                }
                else if (battleData.getBattleType() == 33) {
                    PKPool.getPkPool().cancelPkMatch(battleData.getPkMatch());
                }
                if (battleData.getRCv() != null || battleData.getBBv() != null) {
                    Scene scene = SceneUtil.getScene(1009);
                    if (scene != null) {
                        MapMonsterBean bean1 = new MapMonsterBean();
                        if (battleData.getRCv() != null) {
                            bean1.setType(0);
                            bean1.setI(battleData.getRCv());
                        }
                        else {
                            bean1.setType(1);
                            bean1.setI(battleData.getBBv());
                        }
                        battleData.setMonsterBean(bean1);
                        scene.UPMonster(battleData, battleData.getTeam1(), 0, null);
                    }
                }
                if (battleData.getWinCamp() == -2) {
                    String msg1 = Agreement.getAgreement().PromptAgreement("战斗出现异常强制退出战斗");
                    BattleEnd battleEnd = new BattleEnd(battleData.getBattleNumber(), 0);
                    String msg2 = Agreement.FightingendAgreement(GsonUtil.getGsonUtil().getgson().toJson(battleEnd));
                    List<String> fightList = battleData.getParticipantlist();
                    for (String roleName : fightList) {
                        SendMessage.sendMessageByRoleName(roleName, msg1);
                        SendMessage.sendMessageByRoleName(roleName, msg2);
                    }
                }
                if (battleData.getWinCamp() == -3) {
                    String msg1 = Agreement.getAgreement().PromptAgreement("超过战斗回合限制,退出战斗！");
                    BattleEnd battleEnd = new BattleEnd(battleData.getBattleNumber(), 0);
                    String msg2 = Agreement.FightingendAgreement(GsonUtil.getGsonUtil().getgson().toJson(battleEnd));
                    List<String> fightList = battleData.getParticipantlist();
                    for (String roleName : fightList) {
                        SendMessage.sendMessageByRoleName(roleName, msg1);
                        SendMessage.sendMessageByRoleName(roleName, msg2);
                    }
                }
                if (battleData.getTeamArenaGroup() != null) {
                    LadderArenaUtil.teamArenaBattleEnd(battleData, -1);
                }
                return;
            }
            else {
                if (battleData.getBattleType() == 31 || battleData.getBattleType() == 32) {
                    if (battleData.getWinCamp() <= 0) {
                        ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(battleData.getTeam1()[0]);
                        LoginResult log1 = (ctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx)) : null;
                        ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(battleData.getTeam2()[0]);
                        LoginResult log2 = (ctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx)) : null;
                        if (log1 != null || log2 != null) {
                            ZZSScene zzsScene = null;
                            if (log1 != null) {
                                zzsScene = SceneUtil.getZZS(log1);
                            }
                            else if (log2 != null) {
                                zzsScene = SceneUtil.getZZS(log2);
                            }
                            if (zzsScene != null && zzsScene.getI() != 2) {
                                zzsScene.BattleEnd(null, battleData.getTeam1()[0], 31);
                                zzsScene.BattleEnd(null, battleData.getTeam2()[0], 31);
                                BattleEnd battleEnd2 = new BattleEnd(battleData.getBattleNumber(), 0);
                                String msg3 = Agreement.getAgreement().PromptAgreement("匹配赛已经结束强制中止本场比赛");
                                String msg4 = Agreement.FightingendAgreement(GsonUtil.getGsonUtil().getgson().toJson(battleEnd2));
                                List<String> fightList2 = battleData.getParticipantlist();
                                for (String roleName2 : fightList2) {
                                    SendMessage.sendMessageByRoleName(roleName2, msg3);
                                    SendMessage.sendMessageByRoleName(roleName2, msg4);
                                }
                            }
                        }
                    }
                    else if (battleData.getWinCamp() == 1) {
                        SceneUtil.ZZSBattleEnd(battleData.getTeam1()[0], battleData.getTeam2()[0], battleData.getBattleType());
                    }
                    else {
                        SceneUtil.ZZSBattleEnd(battleData.getTeam2()[0], battleData.getTeam1()[0], battleData.getBattleType());
                    }
                }
                else if (battleData.getBattleType() == 21) {
                    Scene scene = SceneUtil.getScene(1012);
                    if (scene != null) {
                        SLDHScene sldhScene = (SLDHScene)scene;
                        sldhScene.groupEnd(battleData.getPkGroup(), battleData.getWinCamp(), null);
                    }
                }else if (battleData.getBattleType()==104){
                    Scene scene = SceneUtil.getScene(SceneUtil.CJID);
                    if (scene != null) {
                        CJScene cjScene = (CJScene) scene;
                        cjScene.groupEnd(battleData.getCjpkGroup(), battleData.getWinCamp());
                    }
                }else if (battleData.getBattleType() == 33) {
                    if (battleData.getWinCamp() == 1) {
                        PKPool.getPkPool().endPkMatch(battleData.getRoleId1(), battleData.getRoleId2());
                    }
                    else {
                        PKPool.getPkPool().endPkMatch(battleData.getRoleId2(), battleData.getRoleId1());
                    }
                }
                else if (battleData.getBangFight() != null) {
                    battleData.getBangFight().PKP((battleData.getWinCamp() == 1) ? battleData.getBang1() : battleData.getBang2());
                }
                // 获得战斗人
                List<String> fightList3 = battleData.getParticipantlist();
                BattleEnd battleEnd = new BattleEnd(battleData.getBattleNumber(), battleData.getWinCamp());
                /**获取奖励*/
                RewardLimit.Reward(battleEnd, battleData);
                if (fightList3.size() > 0) {
                    battleEnd.clean();
                    String msg5 = Agreement.FightingendAgreement(GsonUtil.getGsonUtil().getgson().toJson(battleEnd));
                    for (String roleName3 : fightList3) {
                        SendMessage.sendMessageByRoleName(roleName3, msg5);
                    }
                }
                if (battleData.getRCv() != null || battleData.getBBv() != null) {
                    Scene scene2 = SceneUtil.getScene(1009);
                    if (scene2 != null) {
                        MapMonsterBean bean2 = new MapMonsterBean();
                        if (battleData.getRCv() != null) {
                            bean2.setType(0);
                            bean2.setI(battleData.getRCv());
                        }
                        else {
                            bean2.setType(1);
                            bean2.setI(battleData.getBBv());
                        }
                        battleData.setMonsterBean(bean2);
                        scene2.UPMonster(battleData, battleData.getTeam1(), (battleData.getWinCamp() == 1) ? 2 : 0, null);
                    }
                }
                if (battleData.getTeamArenaGroup() != null) {
                    LadderArenaUtil.teamArenaBattleEnd(battleData, battleData.getWinCamp());
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            WriteOut.addtxt("战斗报错:" + MainServerHandler.getErrorMessage(e), 9999L);
        }
    }
    /**判断是否需要另开线程还是存在现有的线程 */
    public static int isextra() {
        for (int i = BattleThreadPool.pools.size() - 1; i >= 0; --i) {
            if (((BattleThread)BattleThreadPool.pools.get(i)).getNumbers().size() < 5) {
                return i;
            }
        }
        if (BattleThreadPool.pools.size() < 20) {
            for (int j = BattleThreadPool.pools.size() - 1; j >= 0; --j) {
                if (((BattleThread)BattleThreadPool.pools.get(j)).getNumbers().size() < 20) {
                    return j;
                }
            }
        }
        return -1;
    }
    /**删除线程*/
    public static boolean removeBattleThread(BattleThread battleThread) {
        synchronized (BattleThreadPool.Thread_LOCK) {
            if (battleThread.getNumbers().size() == 0) {
                BattleThreadPool.pools.remove(battleThread);
                return true;
            }
        }
        return false;
    }
    /** 封装的战斗状态广播 */
    public static void sendBattleState(int BattleNumber, String rolename) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(GameServer.getRoleNameMap().get(rolename));
        loginResult.setFighting(BattleNumber);
        SendMessage.sendMessageToMapRoles(loginResult.getMapid(), Agreement.getAgreement().battleStateAgreement("R" + BattleNumber + "#" + rolename));
    }
    /** 封装的战斗状态广播 */
    public static void sendBattleState2(int BattleNumber, List<String> vs, StringBuffer buffer) {
        if (buffer.length() != 0) {
            buffer.append("|");
        }
        buffer.append("R");
        buffer.append(BattleNumber);
        List<Long> maps = new ArrayList<>();
        for (int i = vs.size() - 1; i >= 0; --i) {
            LoginResult login = (LoginResult)GameServer.getAllLoginRole().get(GameServer.getRoleNameMap().get(vs.get(i)));
            if (!maps.contains(login.getMapid())) {
                maps.add(login.getMapid());
            }
            login.setFighting(BattleNumber);
            buffer.append("#");
            buffer.append(login.getRolename());
        }
        String v = Agreement.getAgreement().battleStateAgreement(buffer.toString());
        for (int j = maps.size() - 1; j >= 0; --j) {
            SendMessage.sendMessageToMapRoles((Long)maps.get(j), v);
        }
    }
    /** 封装的战斗状态广播 */
    public static void sendBattleState3(int BattleNumber, String[] v1) {
        List<Long> maps = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();
        buffer.append("R");
        buffer.append(BattleNumber);
        for (int i = 0; i < v1.length; ++i) {
            ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(v1[i]);
            LoginResult login = (ctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx)) : null;
            if (login != null) {
                if (!maps.contains(login.getMapid())) {
                    maps.add(login.getMapid());
                }
                login.setFighting(BattleNumber);
                buffer.append("#");
                buffer.append(v1[i]);
            }
        }
        String v2 = Agreement.getAgreement().battleStateAgreement(buffer.toString());
        for (int j = maps.size() - 1; j >= 0; --j) {
            SendMessage.sendMessageToMapRoles((Long)maps.get(j), v2);
        }
    }
    /** 单人竞技场野怪数据加载 */
    public static String loadCreep(OneArenaRole oneArenaRole, BattleData battleData) {
        if (oneArenaRole.getRoleId().longValue() < 0L) {
            List<String> list = createCreep(battleData.getRobots(), 5);
            list.add(battleData.getRobots().getRobotboss());
            double xs = 1.0;
            int rank = 1000 - oneArenaRole.getPlace();
            if (rank < 0) {
                rank = 0;
            }
            xs += (double)(rank / 250);
            loadCreep(list, oneArenaRole.getName(), BattleMixDeal.lvlint(oneArenaRole.getLvl()), battleData, xs);
        }
        return null;
    }
    /** 加载野怪数据 */
    public static void loadCreep(List<String> fightCreeps, String alias, int maxLvl, BattleData battleData, double xs) {
        if (fightCreeps == null || fightCreeps.size() == 0) {
            return;
        }
        for (int i = 0; i < fightCreeps.size(); ++i) {
            Monster monster = (Monster)GameServer.getMonsterMap().get(fightCreeps.get(i));
            if (monster != null) {
                ManData data = new ManData(monster, 0, Battlefield.Fightingpath(0, i), maxLvl);
                if (monster.getMonsterid() == 5421) {
                    data.setHp(1);
                    data.setHp_z(1);
                }
                if (xs != 1.0) {
                    data.JQ(xs);
                }
                if (i == 0) {
                    if (alias != null && !alias.equals("")) {
                        data.setManname(alias);
                    }
                    battleData.getBattlefield().yename = alias;
                }
                ManData Baby1 = null;
                if (monster.getBaby() != null && !monster.getBaby().equals("")) {
                    String[] babystrs = monster.getBaby().split("&");
                    Map<String, Double> map = new HashMap<>();
                    Baby babya = new Baby();
                    babya.setBabyage(6700);
                    babya.setBabyid(new BigDecimal(data.getId() + 9999));
                    babya.setDaode(9999);
                    babya.setMingqi(9999);
                    babya.setNaili(9999);
                    babya.setNeili(9999);
                    babya.setQizhi(9999);
                    babya.setRoleid(new BigDecimal(data.getId()));
                    babya.setXiaoxin(9999);
                    babya.setZhili(9999);
                    BattleThreadPool.isGXh = true;
                    for (String vs : babystrs) {
                        String[] v = vs.split("\\$");
                        if (v[0].equals("宝宝")) {
                            babya.setBabyname(v[1]);
                        }
                        if (v[0].equals("性别")) {
                            babya.setChildSex(Integer.parseInt(v[1]));
                        }
                        if (v[0].equals("结局")) {
                            babya.setOutcome(v[1]);
                        }
                        if (v[0].equals("天资")) {
                            babya.setTalents(v[1]);
                        }
                    }
                    Baby1 = new ManData(babya, data.getCamp(), data.getMan(), map, null);
                    data.setChild(Baby1);
                    battleData.addFightingdata(Baby1);
                }
                ManData lb = null;
                if (monster.getLingbao() != null && !monster.getLingbao().equals("")) {
                    String[] lingbaostrs = monster.getLingbao().split("&");
                    Lingbao lingbao = new Lingbao();
                    lingbao.setRoleid(new BigDecimal(data.getId()));
                    lingbao.setEquipment(1);
                    lingbao.setBaoreply("0");
                    lingbao.setBaoshot("0");
                    lingbao.setGethard("中");
                    lingbao.setAssistance("0.1");
                    lingbao.setBaoquality("珍藏");
                    lingbao.setResistshot("0.1");
                    lingbao.setBaoid(new BigDecimal(data.getId() + 8888));
                    for (String vs : lingbaostrs) {
                        String[] v = vs.split("\\$");
                        if (v[0].equals("伤害")) {
                            lingbao.setBaoap(v[1]);
                        }
                        if (v[0].equals("活跃")) {
                            lingbao.setBaoactive(Integer.parseInt(v[1]));
                        }
                        if (v[0].equals("名字")) {
                            lingbao.setBaoname(v[1]);
                        }
                        if (v[0].equals("皮肤")) {
                            lingbao.setSkin(v[1]);
                        }
                        if (v[0].equals("契合度")) {
                            lingbao.setLingbaoqihe(Long.parseLong(v[1]));
                        }
                        if (v[0].equals("类型")) {
                            lingbao.setBaotype(v[1]);
                        }
                        if (v[0].equals("擅长技能")) {
                            lingbao.setGoodskill(v[1]);
                        }
                        if (v[0].equals("灵宝等级")) {
                            lingbao.setLingbaolvl(new BigDecimal(v[1]));
                        }
                        if (v[0].equals("技能")) {
                            lingbao.setSkills(v[1]);
                        }
                        if (v[0].equals("目标")) {
                            lingbao.setSkillsum(Integer.parseInt(v[1]));
                        }
                        if (v[0].equals("速度")) {
                            lingbao.setBaospeed(v[1]);
                        }
                    }
                    lb = new ManData(lingbao, data);
                    FightingLingbao ling = new FightingLingbao(lb, 1);
                    List<FightingLingbao> lings = new ArrayList<>();
                    lings.add(ling);
                    data.setLings(lings);
                    battleData.addFightingdata(lb);
                }
                battleData.addFightingdata(data);
            }
        }
    }
    /**生成野怪序列*/
    public static ManData loadCreep(String monsterId, String alias, int maxLvl, BattleData battleData, double xs, int i) {
        Monster monster = (Monster)GameServer.getMonsterMap().get(monsterId);
        if (monster == null) {
            return null;
        }
        ManData data = new ManData(monster, 0, i, maxLvl);
        if (monster.getMonsterid() == 5421) {
            data.setHp(1);
            data.setHp_z(1);
        }
        if (xs != 1.0) {
            data.JQ(xs);
        }
        ManData Baby1 = null;
        if (monster.getBaby() != null && !monster.getBaby().equals("")) {
            String[] babystrs = monster.getBaby().split("&");
            Map<String, Double> map = new HashMap<>();
            Baby babya = new Baby();
            babya.setBabyage(6700);
            babya.setBabyid(new BigDecimal(data.getId() + 9999));
            babya.setDaode(9999);
            babya.setMingqi(9999);
            babya.setNaili(9999);
            babya.setNeili(9999);
            babya.setQizhi(9999);
            babya.setRoleid(new BigDecimal(data.getId()));
            babya.setXiaoxin(9999);
            babya.setZhili(9999);
            BattleThreadPool.isGXh = true;
            for (String vs : babystrs) {
                String[] v = vs.split("\\$");
                if (v[0].equals("宝宝")) {
                    babya.setBabyname(v[1]);
                }
                if (v[0].equals("性别")) {
                    babya.setChildSex(Integer.parseInt(v[1]));
                }
                if (v[0].equals("结局")) {
                    babya.setOutcome(v[1]);
                }
                if (v[0].equals("天资")) {
                    babya.setTalents(v[1]);
                }
            }
            Baby1 = new ManData(babya, data.getCamp(), data.getMan(), map, null);
            data.setChild(Baby1);
            battleData.addFightingdata(Baby1);
        }
        ManData lb = null;
        if (monster.getLingbao() != null && !monster.getLingbao().equals("")) {
            String[] lingbaostrs = monster.getLingbao().split("&");
            Lingbao lingbao = new Lingbao();
            lingbao.setRoleid(new BigDecimal(data.getId()));
            lingbao.setEquipment(1);
            lingbao.setBaoreply("0");
            lingbao.setBaoshot("0");
            lingbao.setGethard("中");
            lingbao.setAssistance("0.1");
            lingbao.setBaoquality("珍藏");
            lingbao.setResistshot("0.1");
            lingbao.setBaoid(new BigDecimal(data.getId() + 8888));
            for (String vs : lingbaostrs) {
                String[] v = vs.split("\\$");
                if (v[0].equals("伤害")) {
                    lingbao.setBaoap(v[1]);
                }
                if (v[0].equals("活跃")) {
                    lingbao.setBaoactive(Integer.parseInt(v[1]));
                }
                if (v[0].equals("名字")) {
                    lingbao.setBaoname(v[1]);
                }
                if (v[0].equals("皮肤")) {
                    lingbao.setSkin(v[1]);
                }
                if (v[0].equals("契合度")) {
                    lingbao.setLingbaoqihe(Long.parseLong(v[1]));
                }
                if (v[0].equals("类型")) {
                    lingbao.setBaotype(v[1]);
                }
                if (v[0].equals("擅长技能")) {
                    lingbao.setGoodskill(v[1]);
                }
                if (v[0].equals("灵宝等级")) {
                    lingbao.setLingbaolvl(new BigDecimal(v[1]));
                }
                if (v[0].equals("技能")) {
                    lingbao.setSkills(v[1]);
                }
                if (v[0].equals("目标")) {
                    lingbao.setSkillsum(Integer.parseInt(v[1]));
                }
                if (v[0].equals("速度")) {
                    lingbao.setBaospeed(v[1]);
                }
            }
            lb = new ManData(lingbao, data);
            FightingLingbao ling = new FightingLingbao(lb, 1);
            List<FightingLingbao> lings = new ArrayList<>();
            lings.add(ling);
            data.setLings(lings);
            battleData.addFightingdata(lb);
        }
        battleData.addFightingdata(data);
        return data;
    }
    /**生成野怪序列*/
    public static List<String> createArray(FightingForesee fightingForesee, BattleData battleData) {
        if (fightingForesee.getCreepids() != null) {
            Gamemap map = (Gamemap)GameServer.getGameMap().get(battleData.getMapid() + "");
            if (map != null) {
                battleData.getBattlefield().dropModel = map.getDropModel();
                battleData.setMaxLvl(Integer.parseInt(map.getMaplvl()));
            }
            return fightingForesee.getCreepids();
        }
        else {
            if (battleData.getRobots() != null) {
                return createCreep(battleData.getRobots(), fightingForesee, battleData);
            }
            return null;
        }
    }
    /**robot生成野怪*/
    public static List<String> createCreep(Robots robots, FightingForesee fightingForesee, BattleData battleData) {
        List<String> fightCreeps = new ArrayList<>();
        if (robots.getRobotname().indexOf(38) > 0) {
            robots.setRobotname(robots.getRobotname().substring(0, robots.getRobotname().indexOf(38)));
        }
        fightCreeps.add(robots.getRobotboss());
        List<String> monsterList = robots.getMonsterList();
        if (monsterList != null && monsterList.size() != 0) {
            int sum = Integer.parseInt(robots.getRobotcount());
            if (monsterList.size() == sum) {	//当左别填的怪物多少和怪物总数一样时，顺序生成怪
                for (int i = 0; i < 9 && i < monsterList.size(); ++i) {
                    fightCreeps.add(monsterList.get(i));//顺序生成野怪
                }
            }
            else if (sum > monsterList.size()) {
                for (int i = 0; i < 9 && i < sum; ++i) {
                    fightCreeps.add(monsterList.get(Battlefield.random.nextInt(monsterList.size())));
                }
            }
            else {//否则随机生成怪
                for (int i = 0; i < 9 && i < monsterList.size() && i < sum; ++i) {
                    fightCreeps.add(monsterList.get(Battlefield.random.nextInt(monsterList.size())));
                }
            }
        }
        int lvl = Integer.parseInt(robots.getRobotlvl());
        if (lvl != 0) {
            battleData.setMaxLvl(lvl);
        }
        if (fightingForesee.getAlias() == null || fightingForesee.getAlias().equals("")) {
            fightingForesee.setAlias(robots.getRobotname());
        }
        return fightCreeps;
    }
    
    public static List<String> createCreep(Robots robots, int num) {
        List<String> fightCreeps = new ArrayList<>();
        if (num <= 0) {
            return fightCreeps;
        }
        List<String> monsterList = robots.getMonsterList();
        if (num > 9) {
            num = 9;
        }
        if (num == monsterList.size() || num == 0) {
            for (int i = 0; i < 9 && i < monsterList.size(); ++i) {
                fightCreeps.add(monsterList.get(i));
            }
        }
        else if (monsterList.size() > num) {//不重复出现
            int ci = 0;
            int size = monsterList.size();
            while (num > 0 && ++ci < 99999) {//避免死循环
                String value = (String)monsterList.get(Battlefield.random.nextInt(size));
                if (!fightCreeps.contains(value)) {
                    fightCreeps.add(value);
                    --num;
                }
            }
        }
        else {
            for (int i = 0; i < num; ++i) {
                fightCreeps.add(monsterList.get(Battlefield.random.nextInt(monsterList.size())));
            }
        }
        return fightCreeps;
    }
    
    public static boolean isBangFight(LoginResult loginResult, BattleData battleData) {
        if (battleData.getBattleType() == 11) {
            BangBattlePool pool = BangBattlePool.getBangBattlePool();
            BangFight bangFight = pool.getBangFight(loginResult.getGang_id());
            if (bangFight != null) {
                ChannelHandlerContext ctx2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(battleData.getTeam2()[0]);
                LoginResult log2 = (ctx2 != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx2)) : null;
                if (log2 == null) {
                    return true;
                }
                if (loginResult.getGang_id().compareTo(log2.getGang_id()) == 0) {
                    SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("自己人啊"));
                    return true;
                }
                for (int i = 0; i < battleData.getTeam1().length; ++i) {
                    String msg = bangFight.daduan2(battleData.getTeam1()[i], 0);
                    if (msg != null) {
                        SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement(msg));
                        return true;
                    }
                }
                for (int i = 0; i < battleData.getTeam2().length; ++i) {
                    String msg = bangFight.daduan2(battleData.getTeam2()[i], 0);
                    if (msg != null) {
                        SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement(msg));
                        return true;
                    }
                }
            }
        }
        else if (battleData.getBattleType() == 12) {
            BangBattlePool pool = BangBattlePool.getBangBattlePool();
            BangFight bangFight = pool.getBangFight(loginResult.getGang_id());
            if (bangFight != null) {
                battleData.setBangFight(bangFight);
                battleData.setBang1(battleData.getTeam1()[0]);
                battleData.setBang2(battleData.getTeam2()[0]);
            }
            StringBuffer buffer = new StringBuffer();
            buffer.append(BangFight.TIME_GAO);
            buffer.append("|");
            for (int j = 0; j < battleData.getTeam1().length; ++j) {
                ChannelHandlerContext ctxx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(battleData.getTeam1()[j]);
                LoginResult log3 = (ctxx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctxx)) : null;
                if (log3 != null) {
                    if (j != 0) {
                        buffer.append("=");
                    }
                    buffer.append(log3.getRolename());
                    buffer.append("&");
                    buffer.append(log3.getSpecies_id());
                    buffer.append("&");
                    buffer.append(log3.getGrade());
                }
            }
            buffer.append("|");
            for (int j = 0; j < battleData.getTeam2().length; ++j) {
                ChannelHandlerContext ctxx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(battleData.getTeam2()[j]);
                LoginResult log3 = (ctxx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctxx)) : null;
                if (log3 != null) {
                    if (j != 0) {
                        buffer.append("=");
                    }
                    buffer.append(log3.getRolename());
                    buffer.append("&");
                    buffer.append(log3.getSpecies_id());
                    buffer.append("&");
                    buffer.append(log3.getGrade());
                }
            }
            bangFight.Msg(Agreement.getAgreement().bangtzAgreement(buffer.toString()));
        }
        return false;
    }
    
    public static String isMonster(FightingForesee fightingForesee, LoginResult loginResult, BattleData battleData) {
        MapMonsterBean bean = battleData.getMonsterBean();
        if (bean == null) {
            if (fightingForesee.getI() == 0) {
                return null;
            }
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().battleStateAgreement("M" + fightingForesee.getI() + "^2"));
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().getAutoTaskAgreement(""));
            return Agreement.getAgreement().PromptAgreement("怪物收拾东西回家了");
        }
        else {
            if (StringUtils.isNotBlank(bean.getGoodId())) {
                String[] teams = loginResult.getTeam().split("\\|");
                LoginResult[] logs = new LoginResult[teams.length];
                for (int i = 0; i < teams.length; ++i) {
                    LoginResult login = null;
                    if (i == 0) {
                        login = loginResult;
                    }
                    else {
                        ChannelHandlerContext ctx2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(teams[i]);
                        if (ctx2 != null) {
                            login = (LoginResult)GameServer.getAllLoginRole().get(ctx2);
                        }
                    }
                    if (login == null) {
                        return Agreement.getAgreement().PromptAgreement(teams[i] + "处于异常状态");
                    }
                    logs[i] = login;
                }
                for (LoginResult log : logs) {
                    String[] split;
                    for (String s : split = bean.getGoodId().split("\\|")) {
                        String[] split2 = s.split("=");
                        BigDecimal goodId = new BigDecimal(split2[0]);
                        Goodstable getgoods = GameServer.getgoods(goodId);
                        List<Goodstable> sameGoodstable = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(log.getRole_id(), goodId);
                        if (sameGoodstable.size() <= 0) {
                            return Agreement.getAgreement().PromptAgreement("#W击杀#R" + bean.getRobotname() + "#W需要消耗#R" + split2[1] + "#W个#R" + getgoods.getGoodsname());
                        }
                    }
                }
            }
            if (bean.getSX() == 1002) {
                Scene scene = SceneUtil.getScene(1002);
                if (scene != null) {
                    TGDBScene tgdbScene = (TGDBScene)scene;
                    String msg = tgdbScene.isBattle(bean);
                    if (msg != null) {
                        return msg;
                    }
                    return null;
                }
                else {
                    return Agreement.getAgreement().PromptAgreement("活动结束");
                }
            }
            else {
                if (bean.getType() != 0) {
                    SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().getAutoTaskAgreement(""));
                    return Agreement.getAgreement().PromptAgreement("怪物正在忙碌中");
                }
                String msg2 = bean.isCreateTime();
                if (msg2 != null) {//加入匹配队列中
                    return Agreement.getAgreement().PromptAgreement(msg2);
                }
                if (bean.getMatch() != null) {//加入匹配队列中
                    MonsterUtil.addMatch(loginResult, bean);
                    return "";
                }
                if (bean.getFollow() != null) {// 跟随怪
                    msg2 = bean.getFollow().isTime();
                    if (msg2 != null) {
                        return Agreement.getAgreement().PromptAgreement(msg2);
                    }
                }
                else if (bean.getHp() != null) {
                    if (bean.getHp().getHp() <= 0L) {
                        SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().getAutoTaskAgreement(""));
                        return Agreement.getAgreement().PromptAgreement("该怪物已经投降");
                    }
                    if (bean.getHp().isMuch()) {
                        return null;
                    }
                }
                return null;
            }
        }
    }
    
    public static String isRole(String[] team, BattleData battleData, int camp) {
        if (team.length > 5) {
            WriteOut.addtxt("战斗人数超标:" + GsonUtil.getGsonUtil().getgson().toJson(team), 9999L);
            return BattleThreadPool.CHECKTS1;
        }
        int[] lvls = (int[])((battleData.getRobots() != null) ? battleData.getRobots().getLvls() : null);
        for (int i = 0; i < team.length; ++i) {
            ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(team[i]);
            LoginResult loginResult = (ctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx)) : null;
            if (loginResult == null) {
                loginResult = GameServer.golemServer.getLoginResultByName(team[i]);
            }
            if (loginResult == null) {
                return BattleThreadPool.CHECKTS1;
            }
            if ((int)loginResult.getFighting() != 0) {
                BattleData battleData2 = (BattleData)BattleThreadPool.BattleDatas.get(loginResult.getFighting());
                if (battleData2 != null) {
                    return BattleThreadPool.CHECKTS1;
                }
                loginResult.setFighting(0);
            }
            if (battleData.getRobots() != null) {
                int lvl = (int)loginResult.getGrade();
                if (lvls != null) {
                    String value = BattleMixDeal.isLvl(lvl, lvls);
                    if (value != null) {
                        return value;
                    }
                }
                lvl = BattleMixDeal.lvlint(lvl);
                if (battleData.getMaxLvl() < lvl) {
                    battleData.setMaxLvl(lvl);
                }
                int sum = MonitorUtil.getBSumBase(loginResult.getRole_id());
                if (sum > battleData.getMaxSum()) {
                    battleData.setMaxSum(sum);
                }
            }
            if (i == 0) {
                PkMatch match = PKPool.getPkPool().seekPkMatch(loginResult.getRole_id());
                if (match != null) {
                    return BattleThreadPool.CHECKTS5;
                }
                if (camp == 1) {
                    battleData.setRoleId1(loginResult.getRole_id());
                }
                else {
                    battleData.setRoleId2(loginResult.getRole_id());
                }
                if (battleData.getMonsterBean() == null && battleData.getBattleType() == 5) {
                    RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
                    if ((int)roleData.getRoleSystem().getIsPk() == 0) {
                        return BattleThreadPool.CHECKTS2;
                    }
                }
            }
            if (battleData.getBattleType() == 10 && camp == 1 && loginResult.getRoleData().getLimit("杀人香") == null) {//队员未携带杀人香  队员未关掉PK
                return Agreement.getAgreement().PromptAgreement("玩家" + loginResult.getRolename() + "未吃杀人香");
            }
            battleData.getParticipantlist().add(team[i]);
        }
        return null;
    }
    /** 跟随怪物抢夺 */
    public static String isFollow(BattleData battleData) {
        if (battleData.getTeam2().length != 0) {
            battleData.setRobots(null);
            battleData.setMonsterBean(null);
            battleData.getBattlefield().dropModel = null;
            MapMonsterBean bean = MonsterUtil.getFollowMonster(battleData.getTeam1());
            if (bean == null) {
                bean = MonsterUtil.getFollowMonster(battleData.getTeam2());
            }
            battleData.setMonsterBean(bean);
        }
        else {
            MapMonsterBean bean = battleData.getMonsterBean();
            if (bean != null && bean.getFollow() != null && bean.getFollow().getFollowID() != null) {
                MapMonsterBean bean2 = MonsterUtil.getFollowMonster(battleData.getTeam1());
                if (bean2 != null) {
                    return Agreement.getAgreement().PromptAgreement("有了还要抢啥");
                }
                LoginResult log2 = RolePool.getLoginResult(bean.getFollow().getFollowID());
                if (log2 == null) {
                    bean.getFollow().setFollowID(null);
                    return Agreement.getAgreement().PromptAgreement("状态异常");
                }
                ChannelHandlerContext ctx2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(log2.getRolename());
                log2 = ((ctx2 != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx2)) : null);
                if (log2 == null) {
                    bean.getFollow().setFollowID(null);
                    return Agreement.getAgreement().PromptAgreement("状态异常");
                }
                battleData.setRobots(null);
                battleData.setTeam2(log2.getTeam().split("\\|"));
                battleData.setBattleType(5);
            }
        }
        return null;
    }
    /** 地图监测 */
    public static String isMap(LoginResult role, BattleData battleData) {
        if ((long)role.getMapid() == 3320L && battleData.getBattleType() != 21) {
            return BattleThreadPool.CHECKTS3;
        }
        if ((long)role.getMapid() == 3201L) {
            return BattleThreadPool.CHECKTS3;
        }
        if ((long)role.getMapid() >= 3329L && (long)role.getMapid() <= 3333L && battleData.getBattleType() != 31) {
            battleData.setBattleType(32);
            ZZSScene zzsScene = SceneUtil.getZZS(role);
            if (zzsScene == null) {
                return BattleThreadPool.CHECKTS3;
            }
            if (zzsScene.getI() != 4) {
                return BattleThreadPool.CHECKTS4;
            }
        }else if (role.getMapid() == 3371 && battleData.getBattleType() == 5) {
            if (CJScene.getI()!=1){
                return Agreement.getAgreement().PromptAgreement("吃鸡活动还未开始不能PK！");
            }
            battleData.setBattleType(104);
            CJTeam team1 = null;
            CJTeam team2 = null;
            for (CJTeam team : CJScene.getTeams()){
                if (team.getTeams()[0].equals(battleData.getTeam1()[0])){
                    team1 = team;
                }
                if (team.getTeams()[0].equals(battleData.getTeam2()[0])){
                    team2 = team;
                }
            }
            if (team1 != null && team1.isPK()) {
                return CHECKTS6;
            }
            if (team2 != null && team2.isPK()){
                return CHECKTS7;
            }
            CJGroup group = new CJGroup(team1, team2);
            group.setState(1);
            battleData.setCjpkGroup(group);
        } else if ((long)role.getMapid() == 3336L) {
            battleData.setSceneId(1011);
            Scene scene = SceneUtil.getScene(1011);
            if (scene == null) {
                return BattleThreadPool.CHECKTS3;
            }
            DNTGScene dntgScene = (DNTGScene)scene;
            String value = dntgScene.isJG(battleData.getTeam1());
            if (value == null) {
                value = dntgScene.isJG(battleData.getTeam2());
            }
            return (value != null) ? Agreement.getAgreement().PromptAgreement(value) : value;
        }
        return null;
    }
    
    public static synchronized int getIncreasesum() {
        ++BattleThreadPool.increasesum;
        //错开武神山战斗序号
        while (BattleThreadPool.increasesum < 4) {
            ++BattleThreadPool.increasesum;
        }
        if (BattleThreadPool.increasesum > 99999999) {
            BattleThreadPool.increasesum = 100;
        }
        return BattleThreadPool.increasesum;
    }
    /** 添加重连 */
    public static void addConnection(ChannelHandlerContext ctx, int BattleNumber, String rolename) {
        BattleData battleData = (BattleData)BattleThreadPool.BattleDatas.get(BattleNumber);
        if (battleData == null || battleData.getBattleState() == 0) {
            sendBattleState(0, rolename);
            return;
        }
        BattleConnection battleConnection = getBattleConnection(battleData, rolename);
        if (!battleData.getParticipantlist().contains(rolename)) {
            battleData.getParticipantlist().add(rolename);
        }
        String msg1 = Agreement.FightingRoundInfoAgreement(GsonUtil.getGsonUtil().getgson().toJson(battleData.getFightingForesee()));
        SendMessage.sendMessageByRoleName(rolename, msg1);
        /** 发送重连数据 */
        sendBattleState(BattleNumber, rolename);
        SendMessage.sendMessageByRoleName(rolename, Agreement.getAgreement().battleConnectionAgreement(GsonUtil.getGsonUtil().getgson().toJson(battleConnection)));
    }
    /** 组队观战 */
    public static void addConnection(int BattleNumber, String[] teams) {
        BattleData battleData = (BattleData)BattleThreadPool.BattleDatas.get(BattleNumber);
        if (battleData == null || battleData.getBattleState() == 0) {
            sendBattleState3(0, teams);
            return;
        }
        sendBattleState3(BattleNumber, teams);
        BattleConnection battleConnection = getBattleConnection(battleData, teams[0]);
        String mes = Agreement.getAgreement().battleConnectionAgreement(GsonUtil.getGsonUtil().getgson().toJson(battleConnection));
        for (int i = 0; i < teams.length; ++i) {
            if (!battleData.getParticipantlist().contains(teams[i])) {
                battleData.getParticipantlist().add(teams[i]);
            }
            SendMessage.sendMessageByRoleName(teams[i], mes);
        }
    }
    /** 获取重连数据 */
    public static BattleConnection getBattleConnection(BattleData battleData, String rolename) {
        BattleConnection connection = new BattleConnection();
        connection.setBattleType(battleData.getBattleType());
        connection.setFightingNumber(battleData.getBattleNumber());
        connection.setBuff(MixDeal.getBuffStr(battleData.getBattlefield().buffs, true));
        int round = battleData.getRound();
        connection.setRound(round);
        int state = battleData.getBattleState();
        connection.setState(state);
        List<FightingManData> datas = (List<FightingManData>)battleData.getPlayDatas().get(round);
        connection.setDatas(datas);
        connection.setTime(System.currentTimeMillis() - battleData.getBattletime());
        if (state == 1) {
            List<FightingEvents> events = battleData.getRoundPolicy(round);
            if (events == null) {
                events = new ArrayList<>();
            }
            List<ManData> manDatas = battleData.getBattlefield().fightingdata;
            for (int i = manDatas.size() - 1; i >= 0; --i) {
                ManData data = (ManData)manDatas.get(i);
                if (data.getType() == 0 && data.getStates() != 2 && data.getManname().equals(rolename)) {
                    int camp = data.getCamp();
                    int man = data.getMan();
                    int eventState = 0;
                    for (int j = events.size() - 1; j >= 0; --j) {
                        FightingEvents event = (FightingEvents)events.get(j);
                        if (event != null) {
                            FightingState state2 = event.getOriginator();
                            if (state2 != null && camp == state2.getCamp()) {
                                if (eventState == 0 && state2.getMan() == man) {
                                    eventState = 1;
                                }
                                else if (state2.getMan() == man + 5) {
                                    eventState = 2;
                                }
                            }
                        }
                    }
                    if (eventState == 2) {
                        eventState = 0;
                    }
                    else if (eventState == 0) {
                        eventState = 1;
                    }
                    else if (eventState == 1) {
                        eventState = 0;
                        man += 5;
                        for (int k = manDatas.size() - 1; k >= 0; --k) {
                            ManData data2 = (ManData)manDatas.get(k);
                            if (data2.getType() == 1 && camp == data2.getCamp() && man == data2.getMan() && data2.getStates() == 0) {
                                eventState = 2;
                                break;
                            }
                        }
                    }
                    connection.setEventState(eventState);
                    break;
                }
            }
        }
        else if (state != 0) {
            List<FightingEvents> events = (List<FightingEvents>)battleData.getBattlefieldPlay().get(round);
            connection.setPlayeEvents(events);
        }
        return connection;
    }
    /** 检测是否有人不能进入战斗 */
    public static String check2(String[] v, BattleData battleData, int type, LTSArena arena, LTSScene ltsScene) {
        long time = System.currentTimeMillis();
        for (int i = 0; i < v.length; ++i) {
            ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(v[i]);
            LoginResult loginResult = (ctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx)) : null;
            if (loginResult == null) {
                return BattleThreadPool.CHECKTS1;
            }
            battleData.getParticipantlist().add(v[i]);
            if ((int)loginResult.getFighting() != 0) {
                BattleData battleData2 = (BattleData)BattleThreadPool.BattleDatas.get(loginResult.getFighting());
                if (battleData2 != null) {
                    return BattleThreadPool.CHECKTS1;
                }
            }
            if (type == 0) {
                if (i == 0) {
                    battleData.setRoleId1(loginResult.getRole_id());
                }
                if (ltsScene != null) {
                    if ((int)loginResult.getGrade() > arena.getMaxLvl()) {
                        return Agreement.getAgreement().PromptAgreement("擂主队伍里面有过高等级玩家");
                    }
                    if ((int)loginResult.getGrade() < 399) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("擂主队伍里面有过低等级玩家"));
                    }
                    LTSRole ltsRole = ltsScene.getRole(loginResult.getRolename());
                    if (ltsRole == null || ltsRole.getZBnum() >= 3) {
                        return Agreement.getAgreement().PromptAgreement(loginResult.getRolename() + "失败次数过多没有参赛资格了");
                    }
                }
            }
            else {
                if (i == 0) {
                    battleData.setRoleId2(loginResult.getRole_id());
                }
                if (ltsScene != null) {
                    if ((int)loginResult.getGrade() > arena.getMaxLvl()) {
                        return Agreement.getAgreement().PromptAgreement("队伍里面有过高等级玩家");
                    }
                    if ((int)loginResult.getGrade() < 399) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("队伍里面有过低等级玩家"));
                    }
                    LTSRole ltsRole = ltsScene.getRole(loginResult.getRolename());
                    if (ltsRole == null || ltsRole.getZBnum() >= 3) {
                        return Agreement.getAgreement().PromptAgreement(loginResult.getRolename() + "失败次数过多没有参赛资格了");
                    }
                    if (time <= ltsRole.getTime()) {
                        return Agreement.getAgreement().PromptAgreement(loginResult.getRolename() + "处于调整时间内,无法战斗");
                    }
                }
            }
        }
        return null;
    }
    /** 加载BB副本野怪数据 */
    public static void loadCreep(List<String> fightCreeps, String alias, BattleData battleData) {
        if (fightCreeps == null || fightCreeps.size() == 0) {
            return;
        }
        for (int i = 0; i < fightCreeps.size() && i < 6; ++i) {
            Monster monster = (Monster)GameServer.getMonsterMap().get(fightCreeps.get(i));
            if (monster != null) {
                ManData data = new ManData(monster, 0, Battlefield.Fightingpath(0, i + ((i == 0) ? 0 : 4)), 140);
                if (i == 0) {
                    if (alias != null && !alias.equals("")) {
                        data.setManname(alias);
                    }
                    battleData.getBattlefield().yename = alias;
                }
                battleData.addFightingdata(data);
            }
        }
    }
    /** 强化野怪属性 */
    public static void JQCreep(BattleData battleData, int JQ) {
        for (int i = 0; i < battleData.getBattlefield().fightingdata.size(); ++i) {
            ManData data = (ManData)battleData.getBattlefield().fightingdata.get(i);
            if (data.getType() == 2 && data.getMan() >= 5) {
                int hp = data.getHp() / 2;
                for (int j = 0; j < JQ; ++j) {
                    hp = (int)((double)hp * 1.25);
                }
                if (hp <= 0) {
                    hp = 500000000;
                }
                data.setHp(hp);
                data.setHp_z(hp);
                data.setSp(data.getSp() + 20 * JQ);
                double ejs = data.getQuality().getEjs();
                double ezs = data.getQuality().getEzs();
                double hfyv = data.getQuality().getRolehsfyv();
                double hfyl = data.getQuality().getRolehsfyl();
                double hxfcd = data.getQuality().getRolehsxfcd();
                double mzl = data.getQuality().getRolefmzl();
                ejs += 2.8 * (double)JQ;
                ezs += (double)(15 * JQ);
                hfyv += (double)(12 * JQ);
                hfyl += (double)(12 * JQ);
                hxfcd += (double)(14 * JQ);
                mzl += (double)(10 * JQ);
                if (JQ > 5) {
                    ejs += 0.4 * (double)JQ;
                    ezs += (double)(4 * JQ);
                    hfyv += (double)(1 * JQ);
                    hfyl += (double)(1 * JQ);
                    hxfcd += (double)(1 * JQ);
                    mzl += (double)(1 * JQ);
                    data.setAp(data.getAp() + 110 * JQ);
                    data.setSp(data.getSp() + 12 * JQ);
                    if (JQ > 10) {
                        ezs += (double)(1 * JQ);
                        hfyv += (double)(1 * JQ);
                        hfyl += (double)(1 * JQ);
                        hxfcd += (double)(1 * JQ);
                        mzl += (double)(1 * JQ);
                        data.setAp(data.getAp() + 75 * JQ);
                        data.setSp(data.getSp() + 10 * JQ);
                    }
                }
                if (ejs >= 90.0) {
                    ejs = 90.0;
                }
                if (hfyv >= 300.0) {
                    hfyv = 300.0;
                }
                if (hfyl >= 300.0) {
                    hfyl = 300.0;
                }
                if (hxfcd >= 400.0) {
                    hxfcd = 400.0;
                }
                data.getQuality().setEjs(ejs);
                data.getQuality().setEzs(ezs);
                data.getQuality().setRolehsfyl(100.0);
                data.getQuality().setRolehsxfkl(100.0);
                data.getQuality().setRolehsfyv(hfyv);
                data.getQuality().setRolehsfyl(hfyl);
                data.getQuality().setRolehsxfcd(hxfcd);
                data.getQuality().setRolefmzl(mzl);
            }
        }
    }
    
    private static void sendErrorMessage(LoginResult loginResult, String value) {
        if (loginResult.isGolem()) {
            GameServer.golemServer.endFighting(loginResult.getRolename(), value);
        }
        else {
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), value);
        }
    }
    
    static {
        BattleThreadPool.CHECKTS1 = Agreement.getAgreement().PromptAgreement("已经有玩家处于战斗中");
        BattleThreadPool.CHECKTS2 = Agreement.getAgreement().PromptAgreement("玩家不开启PK");
        BattleThreadPool.CHECKTS3 = Agreement.getAgreement().PromptAgreement("该场景不允许私自PK");
        BattleThreadPool.CHECKTS4 = Agreement.getAgreement().PromptAgreement("还未到淘汰赛阶段");
        BattleThreadPool.CHECKTS5 = Agreement.getAgreement().PromptAgreement("检测到战书,战斗功能受到限制");
        BattleThreadPool.Thread_LOCK = new Object();
        BattleThreadPool.BattleDatas = new ConcurrentHashMap<>();
        BattleThreadPool.pools = new ArrayList<>();
        BattleThreadPool.isGXh = false;
    }
}
