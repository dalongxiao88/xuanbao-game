package org.come.server;

import cn.hutool.core.util.ArrayUtil;
import come.tool.BangBattle.*;
import come.tool.Battle.BattleData;
import come.tool.Battle.BattleThreadPool;
import come.tool.Mixdeal.CreepsMixdeal;
import come.tool.Role.RoleShow;
import come.tool.Stall.Commodity;
import come.tool.newGang.GangDomain;
import come.tool.newGang.GangUtil;
import come.tool.teamArena.LadderArenaUtil;
import come.tool.teamArena.TeamArenaUtil;
import io.netty.channel.ChannelOutboundInvoker;
import io.netty.channel.ChannelInboundInvoker;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelProgressivePromise;
import io.netty.channel.ChannelPromise;
import io.netty.channel.ChannelFuture;
import java.net.SocketAddress;
import io.netty.channel.ChannelHandler;
import io.netty.util.concurrent.EventExecutor;
import io.netty.channel.Channel;
import org.come.bean.*;
import org.come.entity.*;
import come.tool.Stall.CommodityBean;
import org.come.model.*;
import org.come.pay.ModifyInviteCodeServlet;
import org.come.protocol.AgreementUtil;
import org.come.task.RefreshMonsterTask;
import org.come.until.AutoNameUtil;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.Sepcies_MixDeal;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import org.come.action.summoning.SummonPetAction;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.action.sys.enterGameAction;
import org.come.redis.RedisControl;
import org.come.redis.RedisParameterUtil;
import org.come.tool.Goodtype;
import come.tool.Mixdeal.AnalysisString;
import org.come.tool.Arith;
import come.tool.Good.UsePetAction;
import come.tool.newTask.Task;
import come.tool.Stall.Stall;
import come.tool.Stall.StallPool;
import come.tool.newTeam.TeamUtil;
import come.tool.Calculation.RoleReborn;
import java.util.stream.Collectors;
import org.come.task.MapMonsterBean;
import come.tool.Battle.BattleMixDeal;
import come.tool.newTeam.TeamRole;
import come.tool.newTeam.TeamBean;

import come.tool.Role.RoleData;
import io.netty.channel.ChannelHandlerContext;
import come.tool.Calculation.BaseValue;
import org.come.action.role.RoleTransAction;
import org.apache.commons.lang.StringUtils;
import come.tool.Role.RolePool;
import org.come.protocol.ParamTool;
import org.come.action.IAction;
import org.come.until.GsonUtil;
import org.come.until.StringUtil;
import org.come.until.AllServiceUtil;
import org.apache.commons.logging.LogFactory;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.logging.Log;

public class GolemServer extends Thread{
    public static boolean OPEN; // 机器人开关 true表示运行 false表示关闭
    private static volatile boolean isRunning; // 使用volatile确保多线程间的可见性
    private ConcurrentHashMap<String, String> userNameMap;// 日在线机器人账号集合
    public static ConcurrentHashMap<String, GolemBean> loginGolems; // 在线机器人
    private ConcurrentHashMap<Integer, List<BigDecimal>> taskTeams; // 执行任务的队伍ID

    private String areaName; // 区名称
    private String tuijian;  // 推荐码
    private int num;         // 最大数量
    private int updateNum;   // 更新数量
    private int lvl;         // 初始化等级

    public static GolemServer initAIServer() {
        isRunning = true; // 在机器人服务启动时设置为true
        GolemServer golemServer = null;
        GolemConfig config = GameServer.getGolemConfig();
        golemServer = new GolemServer(config);
        if (OPEN = config.get("开关").equals("开")) {
            golemServer.start();
        }
        return golemServer;
    }


    private GolemServer(GolemConfig config) {
        areaName = config.get("区名称");
        tuijian = config.get("推荐码");
        tuijian = ModifyInviteCodeServlet.getOt_atid();
        //新改的代码
        num = Integer.parseInt(config.get("数量"));
        updateNum = Integer.parseInt(config.get("更新数量"));
        lvl = Integer.parseInt(config.get("初始等级"));

        userNameMap = new ConcurrentHashMap<>();
        loginGolems = new ConcurrentHashMap<>();
        taskTeams = new ConcurrentHashMap<>();
    }

    /** 分配机器人 GolemScript **/
    public void assignGolem() {
        int num = this.num - loginGolems.size();
        if (num > 0) {
            if (num > this.updateNum) num = this.updateNum;
            List<UserTable> userTables = AllServiceUtil.getUserTableService().selectGolemUser();
            String[] userNames = new String[num];
            if (userTables != null) {
                int x = 0;
                for (int i = 0; i < userTables.size() && x < num; i++) {
                    if (!userNameMap.keySet().contains(userTables.get(i).getUsername())) {
                        userNames[x++] = userTables.get(i).getUsername();
                    }
                }
            }
            for (int i = 0; i < userNames.length; i++) {
                String userName = userNames[i];
                if (userName == null)
                    userName = StringUtil.generateUniqueString(15, 4, i + loginGolems.size());
                addGolem(userName);
            }
        }
    }

    /** 添加机器人 **/
    private void addGolem(String userName) {
        try {
            ChannelHandlerContext ctx = new MyChannelHandlerContext(userName);
            // 登录
            LoginUserBean loginUserBean = new LoginUserBean(userName, userName);
            UserTable userTable = AllServiceUtil.getUserTableService().findUserByUserNameAndUserPassword(loginUserBean.getUsername(), loginUserBean.getPassword());
            if (userTable == null) {
                userTable = new UserTable();
                userTable.setType(1);
                userTable.setUsername(userName);
                userTable.setUserpwd(userName);
                userTable.setSafety(userName);
                userTable.setTuiji(tuijian);
                // 注册
                String mes = GsonUtil.getGsonUtil().getgson().toJson(userTable);
                ParamTool.ACTION_MAP.get(AgreementUtil.register).action(ctx, mes);
                userTable = AllServiceUtil.getUserTableService().findUserByUserNameAndUserPassword(loginUserBean.getUsername(), loginUserBean.getPassword());
                if (userTable == null) {return;}
            }
            LoginUserBean userBean = new LoginUserBean();
            userBean.setUsername(userName);
            userBean.setPassword(userName);
            ParamTool.ACTION_MAP.get(AgreementUtil.login).action(ctx, GsonUtil.getGsonUtil().getgson().toJson(userBean));//随风改——适配345登录

            List<LoginResult> allLogin = AllServiceUtil.getRoleTableService().selectRoleByUserid(userTable.getUser_id(), BigDecimal.ZERO.subtract(userTable.getUser_id()));
            boolean init = false;
            if (allLogin == null || allLogin.size() <= 0) {
                // 创建角色
                LoginResult createRole = initGolemRoleInfo(areaName, userTable.getUser_id(), "100");//创建角色
                createRole.setGolem(true);
                String mes = GsonUtil.getGsonUtil().getgson().toJson(createRole);
                ParamTool.ACTION_MAP.get(AgreementUtil.createrole).action(ctx, mes);
                allLogin = AllServiceUtil.getRoleTableService().selectRoleByUserid(userTable.getUser_id(), BigDecimal.ZERO.subtract(userTable.getUser_id()));
                init = true;
            }

            // 进入游戏
            if (allLogin != null && allLogin.size() > 0) {
                LoginResult loginResult = allLogin.get(0);
                String roleId = loginResult.getRole_id().toString();
                ParamTool.ACTION_MAP.get(AgreementUtil.enterGame).action(ctx, roleId);

                loginResult = GameServer.getAllLoginRole().get(ctx);
                loginResult.setGolem(true);
                userNameMap.put(userName,loginResult.getRolename());

                GolemBean golemBean = new GolemBean(loginResult, ctx);
                golemBean.setIdleTime(System.currentTimeMillis());
                RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
                if (loginResult.getGrade() == 0) {
                    loginResult.setGrade(lvl);
                }
                if (StringUtils.isBlank(roleData.getPrivateData().getSkills())) {
                    roleData.getPrivateData().setSkills("S", RoleTransAction.changeSkill(null, loginResult.getSpecies_id(), 10000));
                    roleData.setSkills(BaseValue.reSkill(roleData.getPrivateData(),loginResult));
                }

                if (init) {
                    // 发放物资
//                    loginResult.setGolemLvl(-1);//随风改——机器人物资领取等级
                    provideMaterials(loginResult);
//                    provideFly(loginResult);//随风改——机器人领取飞行器
                }
//                List<Fly> flys = AllServiceUtil.getFlyService().selectFlyByRoleID(loginResult.getRole_id());//随风改——机器人飞行
//                if (flys == null || flys.size()<=0) {
//                    provideFly(loginResult);
//                }
                golemBean.flyAction(1,golemBean.getLoginResult().getTurnAround());
                loginGolems.put(loginResult.getRolename(), golemBean);
                golemBean.initRobotsMap();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void provideAcard(GolemBean golemBean) {
        if (Battlefield.random.nextInt(20)<18)return;
        int[] ids=new int[]{297,309,310,314,315,316,317,318,321,322,324};
        int id =686;
        if (Battlefield.random.nextInt(4)==0){
            id=ids[new Random().nextInt(ids.length)];
        }else {
            id=Battlefield.random.nextInt(17)+330;
        }
        RoleData roleData = RolePool.getRoleData(golemBean.getLoginResult().getRole_id());
        aCard card=GameServer.getCard(id);
        if (card==null) {
            return;
        }
        String value="皮肤="+card.getZskin()+"|"+card.getValue();
        UseCardBean limit=roleData.getLimit("变身卡");
        if (limit==null) {
            limit=new UseCardBean(card.getName(), "变身卡", card.getSkin(), System.currentTimeMillis()+card.getTime()*60000, value);
            roleData.addLimit(limit);
        }else if (card.getName().equals(limit.getName())&&value.equals(limit.getValue())) {
            limit.setTime(limit.getTime()+card.getTime()*60000*24);
        }else {
            limit.setName(card.getName());
            limit.setSkin(card.getSkin());
            limit.setValue(value);
            limit.setTime(System.currentTimeMillis()+card.getTime()*60000*24);

            StringBuffer buffer=new StringBuffer();
            if (golemBean.getLoginResult().getSkin() != null) {
                buffer.append(golemBean.getRoleShow().getSkin()+"|");
            }
            buffer.append("S"+card.getSkin());
            golemBean.getLoginResult().setSkin(buffer.toString());
            // 群发给所有人
            String sendMes=Agreement.getAgreement().upRoleShowAgreement(GsonUtil.getGsonUtil().getgson().toJson(golemBean.getLoginResult().getRoleShow()));
            SendMessage.sendMessageToMapRoles((long) golemBean.getMapId(),sendMes);
        }
        limit.setlCard(null);

    }

    /**
     * 领取称号
     * @param golemBean
     */
    private void provideTitle(GolemBean golemBean) {

        LoginResult loginResult = golemBean.getLoginResult();
        String title = loginResult.getTitle();
        if (title != null){
            if (title.contains("花果山")||title.contains("天宫")||title.contains("帮众")||title.contains("一员")) {
                if (loginResult.getMapid()!=3315 && loginResult.getMapid()!=3201) {//帮派和大闹 水陆冠军
                    chenghao(golemBean);
                }
            }
        }else {
            int lvl = loginResult.getGrade();
            // 2转-3转100级后领取称号
            if (lvl > 338 && lvl <= 400 ) {
                chenghao(golemBean);
            }
        }
    }
    private void chenghao(GolemBean golemBean) {
        if (Battlefield.random.nextInt(10) < 6) {
            GolemConfig config = GameServer.getGolemConfig();
            if (config.get("称谓")!=null&&!config.get("称谓").equals("")) {
                String[] TTs = config.get("称谓").split("\\|");
                LoginResult loginResult = golemBean.getLoginResult();
                String titleName = TTs[Battlefield.random.nextInt(TTs.length)];
                Titletable titletable = new Titletable();
                titletable.setTitlename(titleName);
                titletable.setRoleid(loginResult.getRole_id());
                AllServiceUtil.getTitletableService().createRoleTitle(titletable);

                ParamTool.ACTION_MAP.get(AgreementUtil.titlechange).action(golemBean.getCtx(),titleName );
                loginResult.setTitle(titleName);

                UPRoleShow(golemBean, 1, titleName);
            }

        }
    }

    private void provideWing(GolemBean golemBean) {
        GolemConfig config = GameServer.getGolemConfig();
        String cb = config.get("翅膀");
        if (cb!=null&&!cb.equals("")) {
//            String[] wings = new String[]{"ct6", "yy6", "ym6", "zy6"};//炽天、遥夜、幽冥、昼炎
            String[] wings = cb.split("\\|");//炽天、遥夜、幽冥、昼炎
            for (int i = 0; i < wings.length; i++) {
                String PIFU =golemBean.getLoginResult().getSkin();
                if (PIFU!=null) {
                    if (PIFU.contains(wings[i])) {
                        return;//有此皮肤就返回
                    }
                }

            }
            int n = Battlefield.random.nextInt(4);
            UPRoleShow(golemBean, 2, wings[n]);
        }
    }

    /**
     * 更改角色皮肤
     * @param golemBean
     * @param type  0 变身 1 称号 2 翅膀
     * @param addskin
     */
    private void UPRoleShow(GolemBean golemBean,int type,String addskin) {

        if (addskin == null||addskin.equals("")) {
            return;
        }
        StringBuffer newSb = new StringBuffer();
        if (type == 0) {
            newSb.append("S");//变身
        }else if (type == 1) {
            newSb.append("C");//称号
        } else if (type == 2){
            newSb.append("B");//翅膀
        }
        newSb.append(addskin);


        StringBuffer buffer=new StringBuffer();
        if (golemBean.getLoginResult().getSkin() != null) {
            buffer.append(golemBean.getRoleShow().getSkin()+"|");
        }
        buffer.append(newSb.toString());
        golemBean.getLoginResult().setSkin(buffer.toString());
        // 群发给所有人
        String sendMes=Agreement.getAgreement().upRoleShowAgreement(GsonUtil.getGsonUtil().getgson().toJson(golemBean.getLoginResult().getRoleShow()));
        SendMessage.sendMessageToMapRoles((long) golemBean.getMapId(),sendMes);

    }
    /**更新机器人孩子*/
    private void providebaby(GolemBean golemBean) {
        if (golemBean.getLoginResult().getBabyId() == null && golemBean.getLoginResult().getGrade()>102) {//一转后给孩子
            ParamTool.ACTION_MAP.get(AgreementUtil.babycustoday).action(golemBean.getCtx(),"yes" );
            List<Baby> babys = AllServiceUtil.getBabyService().selectBabyByRolename(golemBean.getRoleId());
            if (babys != null && babys.size() > 0) {
                Baby baby = babys.get(0);
                ParamTool.ACTION_MAP.get(AgreementUtil.rolechange).action(golemBean.getCtx(),"B" + baby.getBabyid() );
                //满级孩子
                baby.setTalents(baby.getTalents());
                baby.setQizhi(1200);
                baby.setNeili(1200);
                baby.setZhili(1200);
                baby.setNaili(1200);
                baby.setMingqi(1200);
                baby.setDaode(1200);
                baby.setPanni(1200);
                baby.setQingmi(1200);
                baby.setXiaoxin(1200);
                baby.setWenbao(baby.getWenbao());
                baby.setBabyage(6580);
                if(baby.getChildSex() ==1){
                    baby.setOutcome("倾国女神");
                    if (Battlefield.random.nextInt(2)==0) {
                        baby.setWanxing(Battlefield.random.nextInt(15)+11);
                    }else {
                        baby.setWanxing(Battlefield.random.nextInt(7)+31);
                    }
                }else {
                    baby.setOutcome("革命领袖");
                    baby.setWanxing(Battlefield.random.nextInt(25)+1);
                }


                String race = RoleTransAction.getSepciesN(golemBean.getLoginResult().getSpecies_id());
                int[] talentids = null ;
                switch (race) {
                    case "男人":
                        talentids= new int[]{1040, 1024, 1038};
                        break;
                    case "女人":
                        talentids= new int[]{1040, 1028, 1038};
                        break;
                    case "男魔":
                        talentids= new int[]{1040, 1002, 1001};
                        break;
                    case "女魔":
                        talentids= new int[]{1040, 1002, 1001};
                        break;
                    case "男仙":
                        talentids= new int[]{1040, 1004, 1006};
                        break;
                    case "女仙":
                        talentids= new int[]{1040, 1008, 1010};
                        break;
                    case "女鬼":
                        talentids= new int[]{1040, 1042, 1041};
                        break;
                    case "男鬼":
                        talentids= new int[]{1040, 1042, 1044};
                        break;
                    case "男龙":
                    case "女龙":
                        talentids= new int[]{1040, 1049, 1050};
                        break;
                    default:
                        talentids= new int[]{1040, 1002, 1001};
                        break;
                }

                List<Integer>skillid=new ArrayList<>();
                for (int i = 0; i < talentids.length; i++) {
                    Talent talent=GameServer.getTalent(talentids[i]);
                    if (talent==null)continue;
                    skillid.add(talent.getId());
                }
                StringBuffer buffer=new StringBuffer();
                buffer.append(skillid.get(0)+"=10");
                buffer.append("|");
                buffer.append(skillid.get(1)+"=10");
                buffer.append("|");
                buffer.append(skillid.get(2)+"=10");
                baby.setTalents(buffer.toString());

                AllServiceUtil.getBabyService().updateBaby(baby);
            }
        }
    }
    /** 机器人下线 **/
    public void deletGolem(String golemName) {
        deletGolem(loginGolems.get(golemName));
    }

    private long count = 0;
    /** 其他操作 **/
    public void otherOperation() {
        for (GolemBean golemBean : loginGolems.values()) {
            if (golemBean.isFighting()) continue;
            if (count % 12 == 0) {
                cleanUpPack(golemBean);
            }
            if (count % 2 == 0) {
                //更新golemBean的属性
//                updateProperty(golemBean, golemBean.getTeamBean() == null);
                updateProperty(golemBean, true);//好了
                //更新golemBean的召唤物属性
                updatePetProperty(golemBean);
                //提供golemBean的登录结果
//                provideMaterials(golemBean.getLoginResult());//随风——没有给role_table加字段，取消总发放物资，只在建号时单发一次就行了
                //更新golemBean的机器人地图
                golemBean.updateRobotsMap();

            }
            if (count % 10 == 0) {
//            if (count % 1 == 0) {//测试
                //更新golemBean的孩子
                providebaby(golemBean);
                //变身卡
//                provideAcard(golemBean);
                //改称谓
                provideTitle(golemBean);
                //翅膀
                provideWing(golemBean);
            }
        }
        count++;

        //机器人建帮、加帮、帮战

        LocalTime now = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
        LocalTime twoAM = LocalTime.of(2, 10);
        LocalTime threeAM = LocalTime.of(3, 25);
//        LocalTime threeAM = LocalTime.of(11, 20);
        LocalTime sixAM = LocalTime.of(6, 10);

        if (now.equals(twoAM)) {
            CreatBang();
            System.out.println("凌晨2点10分建帮");
        } else if (now.equals(threeAM)) {
            AddBang();
            System.out.println("凌晨3点25分加帮");
        } else if (now.equals(sixAM)) {
            addbangfight();
            System.out.println("凌晨6点10分报名帮战");
        }
    }

    /** 0点重置 **/
    public void reset() {
        for (GolemBean golemBean : loginGolems.values()) {
            try {
                // 重置击杀野怪数量限制
                golemBean.initRobotsMap();
                // 重置摆摊验证
                golemBean.setStallState(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        clearUserNames();
    }

    /** 结束战斗 **/
    public void endFighting(String golemName, String value) {
        GolemBean golemBean = loginGolems.get(golemName);
        if (golemBean == null) return;
        if (golemBean.getScript() != null) {
            golemBean.getScript().endFighting();
        }

//        if (StringUtils.isNotBlank(value)) {
//            System.out.println(golemName + ":结束战斗 - " + value);
//        }
    }

    /** 添加队伍完成次数 **/
    public void addSumLimit(String golemName, int taskSetId) {
        GolemBean golemBean = loginGolems.get(golemName);
        if (golemBean == null) return;
        GolemScript script = golemBean.getScript();
        if (script == null) return;
        script.completeTask(taskSetId);
    }

    /** 添加队伍击杀怪物次数 **/
    public void addSumLimit(String golemName, String robotId, boolean isLeading) {
        try {
            GolemBean golemBean = loginGolems.get(golemName);
            if (golemBean == null) return;
            golemBean.updateRobotsMap(Integer.valueOf(robotId));

            if (isLeading) {
                String robot = golemBean.getRobot();
                if (StringUtils.isNotBlank(robot)) {
                    String[] vals = robot.split("-");
                    if (vals[0].equals(robotId)) {
//                        System.out.println(golemBean.getRoleName() + ":击杀怪物【" + vals[1] + "】");
                        golemBean.setRobot(null);
                        golemBean.getScript().createScript(true);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 根据机器人名称获取机器人 **/
    public GolemBean getGolemByName(String golemName) {
        return loginGolems.get(golemName);
    }

    public LoginResult getLoginResultByName(String golemName) {
        GolemBean golemBean = loginGolems.get(golemName);
        if (golemBean == null) return null;
        return golemBean.getLoginResult();
    }

    /** 添加队伍任务记录 **/
    private void addTaskTeams(BigDecimal teamId, Integer taskSetId) {
        List<BigDecimal> teams = taskTeams.get(taskSetId);
        if (teams == null) {
            taskTeams.put(taskSetId, teams = new ArrayList<>());
        }
        if (!teams.contains(teamId)) {
            teams.add(teamId);
        }
    }

    /** 移除队伍任务记录 **/
    private boolean removeTaskTeams(BigDecimal teamId, GolemActive active) {
        if (active != null) {
            List<BigDecimal> teams = taskTeams.get(active.getTasksetId());
            if (teams != null && teams.contains(teamId)) {
                return teams.remove(teamId);
            }
        }
        return false;
    }

    /** 队伍转生监听 **/
    public void transListener(TeamBean teamBean) {
        List<GolemBean> list = new ArrayList<>();
        List<TeamRole> teams = teamBean.getTeams();
        int size = 0;
        for (int i = teamBean.getTeams().size()-1; i > 0; i--) {
            TeamRole teamRole = teamBean.getTeams().get(i);
            GolemBean golem = loginGolems.get(teamRole.getName());
            if (golem == null) {
                continue;
            }
            int lvl = BattleMixDeal.lvlint(golem.getGrade());
            int zs = BattleMixDeal.lvltrue(golem.getGrade());
            if ((zs == 0 && lvl == 102)
                    || (zs == 1 && lvl == 122)
                    || (zs == 2 && lvl == 142)
                    || (zs == 3 && lvl == 180)) {
                list.add(golem);
            }
            size++;
        }
        if (list.size() == size) {
            for (int i = 0; i < list.size(); i++) {
                updateProperty(list.get(i), true);
            }
        }
    }

    /**
     * 队伍配置监听
     *
     * @param teamBean 队伍星系
     * @param applyTeam 申请的组队信息
     * @param is 任务目标不满足条件时是否重新获取其他任务目标
     * @return
     */
    public boolean teamListener(TeamBean teamBean, TeamRole applyTeam, boolean is) {
        GolemBean golemBean = loginGolems.get(teamBean.getTeamName());
        if (golemBean == null) return false;
        GolemActive active = golemBean.getTask(); // 获取当前有效的任务目标
        // 队长验证
        if (active == null) {
            if (applyTeam == null) {
                if (teamBean !=null) {
                    // 清除任务队伍记录
                    removeTaskTeams(teamBean.getTeamId(), golemBean.getTarget());
                    if (is) {
                        // 获取新的任务
                        active = golemBean.getNewTask();
                        if (active != null) {
                            addTaskTeams(teamBean.getTeamId(), active.getTasksetId());
                            golemBean.getScript().createScript(active);
                            return false;
                        }
                    }

                    // 解散队伍
                    List<TeamRole> teams = teamBean.getTeams();
                    for (int i = 0; i < teams.size(); i++) {
                        GolemBean golem = GameServer.golemServer.getGolemByName(teams.get(i).getName());
                        if (golem == null) continue;
                        golem.setIdleTime(System.currentTimeMillis());
                    }
                    teamBean.dismissTeam();
                    return false;
                } else {
                    // 没有任务 设置为闲置
                    golemBean.setIdleTime(System.currentTimeMillis());
                }
            }
            return false;
        }

        Map<String, Integer> speciesMap = new HashMap<>();
        speciesMap.put(RoleTransAction.getSepciesN(golemBean.getSpeciesId()), 1);

        // 成员验证
        List<TeamRole> teamRoles = teamBean.getTeams();
        for (int i = teamRoles.size() - 1; i > 0; i--) {
            TeamRole teamRole = teamRoles.get(i);
            GolemBean golem = getGolemByName(teamRole.getName());
            boolean isAdd;
            if (golem == null) {
                ChannelHandlerContext ctx = GameServer.getRoleNameMap().get(teamRole.getName());
                LoginResult loginResult = null;
                if (ctx != null) {
                    loginResult = GameServer.getAllLoginRole().get(ctx);
                }
                // 玩家只做等级验证
                isAdd = loginResult != null && teamRole.getState() >= 0 && BattleMixDeal.isLvl(loginResult.getGrade(), active.getLvls()) == null;
            } else {
                // 假人队员任务验证
                isAdd = teamRole.getState() >= 0 && golem.taskConditionValidate(active);
            }

            if (isAdd) {
                // 记录种族数量
                String race = RoleTransAction.getSepciesN(teamRole.getSpeciesId());
                Integer count = speciesMap.get(race);
                if (count == null) {
                    count = 0;
                }
                count++;
                speciesMap.put(race, count);
            } else {
                // 不在线、不满足任务条件的队员踢出队伍
                teamBean.removeTeamRole(teamRole.getRoleId());
            }
        }

        if (applyTeam == null) {
            // 队伍条件验证
            if (teamRoles.size() < 5) {
                addTaskTeams(teamBean.getTeamId(), active.getTasksetId());
                return false;
            } else {
                removeTaskTeams(teamBean.getTeamId(), active);
                return true;
            }
        } else {
            // 队伍申请条件验证
            if (teamRoles.size() < 5) {
                if (BattleMixDeal.lvlint(applyTeam.getGrade()) >= lvl) {
                    String race = RoleTransAction.getSepciesN(applyTeam.getSpeciesId());
                    if (speciesMap.get(race) == null) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    /** 分配怪物给机器人 **/
    public void assignedRobot(MapMonsterBean monsterBean) {
        try {
            List<GolemBean> golemBeans = loginGolems.values().stream()
                    .filter(golemBean -> isAssignedRobot(golemBean, monsterBean.getRobotid()))
                    .collect(Collectors.toList());
            if (golemBeans.size() == 0) {
                return;
            }
            GolemBean golemBean = golemBeans.get(GameServer.random.nextInt(golemBeans.size()));

            StringBuffer buffer = new StringBuffer();
            buffer.append(monsterBean.getRobotid());
            buffer.append("-");
            buffer.append(monsterBean.getRobotname());
            buffer.append("-");
            buffer.append(monsterBean.getI());
            buffer.append("-");
            buffer.append(monsterBean.getMap());
            buffer.append("-");
            buffer.append(monsterBean.getX());
            buffer.append("-");
            buffer.append(monsterBean.getY());
            golemBean.setRobot(buffer.toString());
            GolemScript script = golemBean.getScript();
            if (script!=null&&script.isEnd()) {
                // 没有执行脚本的立即执行脚本
                script.createScript(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改机器人的武器造型
     */
    public void ChangeWeapenSkin(GolemBean golemBean,RoleData roleData,int zs){
        if (Battlefield.random.nextInt(5)>2) {
            int SPID = Integer.parseInt(String.valueOf(golemBean.getLoginResult().getSpecies_id()));
            int SPID1 = SPID;
            if (zs > 0 && zs < 3) {
                if (SPID == 20001 || SPID == 20002 || SPID == 20003) {
                    SPID = 20007;
                }
                else if (SPID == 20004 || SPID == 20005 || SPID == 20006) {
                    SPID = 20008;
                }
                else if (SPID == 21001 || SPID == 21002 || SPID == 21003) {
                    SPID = 21007;
                }
                else if (SPID == 21004 || SPID == 21005 || SPID == 21006) {
                    SPID = 21008;
                }
                else if (SPID == 22001 || SPID == 22002 || SPID == 22003) {
                    SPID = 22007;
                }
                else if (SPID == 22004 || SPID == 22005 || SPID == 22006) {
                    SPID = 22008;
                }
            } else if (zs == 3 ) {
                if (SPID == 20001 || SPID == 20002 || SPID == 20003) {
                    SPID = 20009;
                }
                else if (SPID == 20004 || SPID == 20005 || SPID == 20006) {
                    SPID = 20010;
                }
                else if (SPID == 21001 || SPID == 21002 || SPID == 21003) {
                    SPID = 21009;
                }
                else if (SPID == 21004 || SPID == 21005 || SPID == 21006) {
                    SPID = 21010;
                }
                else if (SPID == 22001 || SPID == 22002 || SPID == 22003) {
                    SPID = 22009;
                }
                else if (SPID == 22004 || SPID == 22005 || SPID == 22006) {
                    SPID = 22010;
                }
            } else if (zs >= 4) {
                if (SPID == 20001 || SPID == 20002 || SPID == 20003) {
                    SPID = 20011;
                }
                else if (SPID == 20004 || SPID == 20005 || SPID == 20006) {
                    SPID = 20012;
                }
                else if (SPID == 21001 || SPID == 21002 || SPID == 21003) {
                    SPID = 21011;
                }
                else if (SPID == 21004 || SPID == 21005 || SPID == 21006) {
                    SPID = 21012;
                }
                else if (SPID == 22001 || SPID == 22002 || SPID == 22003) {
                    SPID = 22011;
                }
                else if (SPID == 22004 || SPID == 22005 || SPID == 22006) {
                    SPID = 22012;
                }
                else if (SPID == 23001 || SPID == 23002 || SPID == 23003) {
                    SPID = 23007;
                }
                else if (SPID == 23004 || SPID == 23005 || SPID == 23006) {
                    SPID = 23008;
                }
                else if (SPID == 24001 || SPID == 24002 || SPID == 24003) {
                    SPID = 24007;
                }
                else if (SPID == 24004 || SPID == 24005 || SPID == 24006) {
                    SPID = 24008;
                }
            }

            if (SPID==SPID1) {
                return;
            } else {

                int newwuqi = 0;
                if (SPID == 20007 || SPID == 20008 || SPID == 20010 || SPID == 20011//剑侠客、燕山雪、红拂女、神秀生
                        || SPID == 22009 //紫薇神
                        || SPID == 23007 || SPID == 23008//南冠客、镜花影
                        || SPID == 24007 //游无极
                ) {
                    // 剑
//                    newwuqi = 1;
                    newwuqi = 7006;

                }else if (SPID == 20009){//纯阳子
                    //扇子
//                    newwuqi = 2;
                    newwuqi = 7012;
                }else if (SPID == 21007 || SPID == 21011 || SPID == 22007 || SPID == 22011) {//逆天魔、绝影魔、武尊神、青阳使
                    // 枪
//                    newwuqi = 12;
                    newwuqi = 7011;
                }else if (SPID == 20012 || SPID == 21008 || SPID == 21009 || SPID == 21012){//玲珑女、媚灵狐、混天魔、霜月灵、
                    // 刀
//                    newwuqi = 10;
                    newwuqi = 7007;
                }else if (SPID == 21010 ) {//九尾狐
                    // 爪子
//                    newwuqi = 14;
                    newwuqi = 7010;
                } else if (SPID == 22008 || SPID == 22010 || SPID == 22012 || SPID == 24008){//玄天姬、霓裳仙、云中君、临九渊
                    //飘带
//                    newwuqi = 16;
                    newwuqi = 7015;
                }

//                RoleData roleData= loginResult.getRoleData();
                for (int i = 0; i < roleData.getGoodEquip().length; i++) {//装备加成
                    if (i == 0 ) {
                        if (roleData.getGoodEquip()[i] != null) {
                            Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(roleData.getGoodEquip()[i]);
                            if (good != null) {
                                good.setSkin(String.valueOf(newwuqi));
                                AllServiceUtil.getGoodsTableService().updateGoodRedis(good);
                            }
                        }
                    }
                }

                Species species=Sepcies_MixDeal.getSpecies(BigDecimal.valueOf(SPID));

                golemBean.getLoginResult().setSex(species.getSex());
                golemBean.getLoginResult().setLocalname(species.getLocalname());
                golemBean.getLoginResult().setRace_id(species.getRace_id());
                golemBean.getLoginResult().setSpecies_id(species.getSpecies_id());
                golemBean.getLoginResult().setRace_name(Sepcies_MixDeal.getRaceString(species.getSpecies_id()));

                SendMessage.sendMessageToMapRoles(golemBean.getCtx(),golemBean.getLoginResult().getMapid(),Agreement.getAgreement().upRoleShowAgreement(GsonUtil.getGsonUtil().getgson().toJson(golemBean.getLoginResult().getRoleShow())));

            }

        }

    }


    /** 修改机器人属性点 **/
    public void updateProperty(GolemBean golemBean, boolean isTrans) {
        try {
            LoginResult loginResult = golemBean.getLoginResult();
            int lvl = BattleMixDeal.lvlint(loginResult.getGrade());
            int zs = BattleMixDeal.lvltrue(loginResult.getGrade());
            // 转生
            if (isTrans) {
                if ((zs == 0 && lvl == 102)|| (zs == 1 && lvl == 122)|| (zs == 2 && lvl == 142)|| (zs == 3 && lvl == 180)) {
                    zs++;
                    loginResult.setGrade(loginResult.getGrade() + 1);//直接大于71级做天去
                    lvl = BattleMixDeal.lvlint(loginResult.getGrade());
                    int l = this.lvl - lvl;
                    if (l > 0) loginResult.setGrade(loginResult.getGrade() + l);
                    loginResult.setTurnAround(zs);

                    RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());

                    ChangeWeapenSkin(golemBean, roleData, zs);

                    roleData.setGoodMax(24 * 6);
                    if (zs <= 3) {// 重置转生抗性
                        String v = RoleReborn.reborn(roleData.getPrivateData().getSkill("S"), roleData.getPrivateData().getBorn());
                        roleData.getPrivateData().setBorn(v);
                        roleData.setBorns(BaseValue.reborn(roleData.getPrivateData().getBorn()));
                    }
                    Integer skilled = 10000 + (zs <= 3 ? zs * 5000 : 15000);
                    roleData.getPrivateData().setSkills("S", RoleTransAction.changeSkill(roleData.getPrivateData().getSkill("S"), loginResult.getSpecies_id(), skilled));
                    roleData.getPrivateData().setSkills("F", null);
                    roleData.setSkills(BaseValue.reSkill(roleData.getPrivateData(), loginResult));
                    loginResult.setHp(new BigDecimal(0));
                    loginResult.setMp(new BigDecimal(0));

                    lvl = BattleMixDeal.lvlint(loginResult.getGrade());
                }
            }
            String race = RoleTransAction.getSepciesN(loginResult.getSpecies_id());
            switch (race) {
                case "男人":
                case "女人":
                    loginResult.setBone(lvl * 4);
                    loginResult.setSpir(lvl * 4);
                    loginResult.setPower(lvl * 1);
                    loginResult.setSpeed(lvl * 3);
                    break;
                case "男魔":
                case "女魔":
                    loginResult.setBone(lvl * 3);
                    loginResult.setSpir(lvl * 3);
                    loginResult.setPower(lvl * 1);
                    loginResult.setSpeed(lvl * 5);
                    break;
                case "男仙":
                case "女仙":
                case "女鬼":
                    loginResult.setBone(lvl * 3);
                    loginResult.setSpir(lvl * 4);
                    loginResult.setPower(lvl * 1);
                    loginResult.setSpeed(lvl * 4);
                    break;
                case "男鬼":
                    loginResult.setBone(lvl * 5);
                    loginResult.setSpir(lvl * 5);
                    loginResult.setPower(lvl * 1);
                    loginResult.setSpeed(lvl * 1);
                    break;
                case "男龙":
                case "女龙":
                    loginResult.setBone(lvl * 4);
                    loginResult.setSpir(lvl * 4);
                    loginResult.setPower(lvl * 5);
                    loginResult.setSpeed(lvl * 4);
                    break;
            }
            loginResult.setCalm(lvl * 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private List<GolemBean> tempList = new ArrayList<>();//假人任务队伍
    public static boolean isFighting = false;//帮战
    private ConcurrentHashMap<Integer, List<List<GolemBean>>> bangteams5;//各个帮派的队伍
    public static boolean isSLDH = false;//水陆
    public static boolean SLteam = false;
    public static boolean isStart=false; //全民是否已经开启

    public static boolean isBWDH = false;//比武
    public static boolean BWteam = false;
    @Override
    public void run() {
        while (isRunning) {
            if (!GameServer.OPEN) {
                if (loginGolems.size() > 0) {
                    try {
                        Thread.sleep(500); // 每秒执行一次
//                        Thread.sleep(1000); // 每秒执行一次
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (!isFighting) {
                        tempList.addAll(loginGolems.values());
                        Collections.shuffle(tempList);
                        for (GolemBean golemBean : tempList) {
                            try {
                                if (golemBean.getBangtime() != 0) {
                                    outFighting(golemBean.getLoginResult());//强退帮派战斗
                                    golemBean.setBangtime(0);
                                    RemoveGolems();
                                    DeleteGangTeams(golemBean.getGangid().intValue());

                                    GolemBangFight.outbang(golemBean);//退出帮战
                                    ChangeMapBean change = new ChangeMapBean(1207, 8030, 4230);//出来
                                    ParamTool.ACTION_MAP.get(AgreementUtil.changemap).action(golemBean.getCtx(), GsonUtil.getGsonUtil().getgson().toJson(change));
                                    if (golemBean.getScript() != null) {
                                        golemBean.getScript().clear2();//重置脚本
//                                            golemBean.setScript(new GolemScript(golemBean));//重新脚本
//                                            GameServer.golemServer.assignedRobot(mapMonsterBean);
                                    }
                                    golemBean.flyAction(1,golemBean.getLoginResult().getTurnAround());//出了帮战地图后飞行
                                }
                                // 摆摊验证 正在摆摊
                                if (golemBean.getStall() != null && golemBean.getStallTime() != null && golemBean.getStallTime() > System.currentTimeMillis()) {
                                    continue;
                                }

                                //机器人全民竞技
                                if (isStart ) {
                                    if (QMholds > 1 && haveQMjiqiren && golemBean.getQMtype() > 0) {
                                        QMhandle(golemBean);
                                    }
                                    if (QMholds == 1 ) {//有人匹配不到人
                                        if (golemBean.getQMtype() == 0 && !golemBean.iswait() && !golemBean.isFighting()  && golemBean.getTeamBean() != null) {
                                            haveQMjiqiren = true;
                                            SetQMhold(2);
                                            QMhandle(golemBean);
                                        }
                                    }
                                }
                                if (golemBean.getQMtype() > 0) {
//                                        golemBean.setQMtype(0);
                                    continue;
                                }

                                //机器人水陆
                                if (isSLDH) {
                                    //没有水陆队伍的话，创建水陆队伍
                                    if (!SLteam) {
                                        initSLteams();
                                        SLteam = true;
                                    }
                                    //有队伍了
                                    if (golemBean.getSLtype()>0) {
                                        if (golemBean.getSLtype() == 1) {
                                            if (golemBean.getTeamBean().isCaptian(golemBean.getRoleId())) {
                                                ParamTool.ACTION_MAP.get(AgreementUtil.gangbattle).action(golemBean.getCtx(),"39");
                                            }
                                            golemBean.setSLtype(2);
                                        }
                                        if (golemBean.getSLtype() == 3) {
//                                            HanHua(5,0,golemBean,2);
                                            continue;
                                        }else {
                                            if (golemBean.getSLtype() == 2) {
                                                if (golemBean.getTeamBean().isCaptian(golemBean.getRoleId())) {
                                                    int x = Battlefield.random.nextInt(300) + 50;
                                                    int y = Battlefield.random.nextInt(300) + 50;
                                                    x = Battlefield.random.nextInt(2) > 0 ? x : -x;
                                                    y = Battlefield.random.nextInt(2) > 0 ? y : -y;
                                                    golemBean.setSleeptime(golemBean.move(1280 + x, 600 + y, golemBean.getSp()));
                                                    golemBean.setSLtype(3);
                                                }
                                                continue;
                                            }
                                        }
                                    }
                                }
//                                    else{
//                                        SLteam = false;
//                                        if (golemBean.getSLtype() > 0) {
//                                            golemBean.setSLtype(0);
//                                        }
//                                    }

                                GolemScript script = golemBean.getScript();
                                if (script == null) golemBean.setScript(script = new GolemScript(golemBean));

                                int probability = loginGolems.size() / 3;
                                if (GameServer.random.nextInt(loginGolems.size()) < probability) continue;
                                if (script.isEnd()) { // 脚本停止
                                    handle(golemBean);
                                } else { // 执行脚本
                                    if (golemBean.isFighting()) continue;
                                    String[] names = golemBean.getTeam().split("\\|");
                                    List<GolemBean> golemBeans = new ArrayList<>();//拉取队伍中的机器人
                                    for (int i = 0; i < names.length; i++) {
                                        GolemBean golem = loginGolems.get(names[i]);
                                        if (golem != null) golemBeans.add(golem);
                                    }
                                    script.handle(golemBeans.toArray(new GolemBean[golemBeans.size()]));//创建脚本
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        tempList.clear();
                    }
                    // TODO: 帮战处理
                    else {
                        //帮战分组
                        if (bangteams5 == null) {
                            bangteams5 = groupLoginGolemsByTeamBy5(loginGolems);
                        }
                        bangfight();

                    }
                }
            }
        }
        isRunning = false; // 确保在退出循环后设置 isRunning 为 false
    }

    // 检查线程是否还在运行的方法
    public static boolean isRunning() {
        return isRunning;
    }
    // 停止线程
    public static void stopGolemsThread() {
        isRunning = false;
    }

    public volatile static int QMholds = 0 ;//全民竞技等待了25秒的队伍
    public volatile static boolean haveQMjiqiren = false;//全民竞技等待了25秒的队伍
    public static void SetQMhold(int team){
        QMholds=team;
    }
    public void QMhandle(GolemBean golemBean){
        if (golemBean.getQMtype() == 0) {
            if (golemBean.getLoginResult().getGrade()<=338){
                golemBean.setQMtype(0);
                ChangeMapBean change = new ChangeMapBean(1207, 703+Battlefield.random.nextInt(140), 407+Battlefield.random.nextInt(140));
                ParamTool.ACTION_MAP.get(AgreementUtil.changemap).action(golemBean.getCtx(), GsonUtil.getGsonUtil().getgson().toJson(change));
                System.err.println("机器人等级不够陪打天梯");
            } else {
                //去擂台
                ChangeMapBean change = new ChangeMapBean(4444, 703 + Battlefield.random.nextInt(140), 407 + Battlefield.random.nextInt(140));
                ParamTool.ACTION_MAP.get(AgreementUtil.changemap).action(golemBean.getCtx(), GsonUtil.getGsonUtil().getgson().toJson(change));
                golemBean.setQMtype(1);
            }
            return;
        } else if (golemBean.getQMtype() == 1){
            //报名匹配
//            ParamTool.ACTION_MAP.get(AgreementUtil.teamArena).action(golemBean.getCtx(),"O");
            if (golemBean.getLoginResult().getGrade()<=338){
                golemBean.setQMtype(0);
                ChangeMapBean change = new ChangeMapBean(1207, 703+Battlefield.random.nextInt(140), 407+Battlefield.random.nextInt(140));
                ParamTool.ACTION_MAP.get(AgreementUtil.changemap).action(golemBean.getCtx(), GsonUtil.getGsonUtil().getgson().toJson(change));
                System.err.println("机器人等级不够陪打天梯");
            } else {
                LadderArenaUtil.addAffirm(golemBean.getCtx(), golemBean.getTeamBean());
                golemBean.setQMtype(2);
            }
            return;
        } else if (golemBean.getQMtype() == 2){
            //全都同意
            LadderArenaUtil.addMatch(golemBean.getTeamBean());
            golemBean.setQMtype(3);

//            String[] names = golemBean.getTeam().split("\\|");
//            for (int i = 0; i < names.length; i++) {
//                GolemBean golem = loginGolems.get(names[i]);
//                if (golem != null) ParamTool.ACTION_MAP.get(AgreementUtil.teamArena).action(golem.getCtx(),"A");
//            }
            return;
        } else if (golemBean.getQMtype() == 3) {
            // 同意后开打了，等着就行了
            if (golemBean.getTeamBean()!=null&&golemBean.getTeamBean().getTeamArenaMatch() != null) {
                new Thread(() -> {
                    try {
                        Thread.sleep(3000); // 等待3秒
                        SetQMhold(0); // 执行SetQMhold(0)
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // 重新设置中断状态
                        e.printStackTrace();
                    }
                }).start();
                return;
            } else {
                golemBean.setQMtype(0);
            }
            return;
        }

    }



    /**
     * 帮战操作
     */
    public void bangfight(){
        for (Map.Entry<Integer, List<List<GolemBean>>> gangEntry : bangteams5.entrySet()) {
//            int gangId = gangEntry.getKey();
//            System.out.println("帮派ID: " + gangId);
//            boolean isover=false;
            List<List<GolemBean>> teams = gangEntry.getValue();
            for (List<GolemBean> team : teams) {
                // 访问队长（每个子列表的第一个GolemBean对象）
                if (!team.isEmpty()) { // 确保队伍不为空

                    for (int i = 0; i < team.size(); i++) {

//                        if (isover) {
////                            RemoveGolems();
//                            DeleteGangTeams(gangEntry.getKey());
//                            //帮战结束
//                            return;
//                        }
                        if (team.get(i).getBangtime()==88) {
                            break;
                        }
                        if (!team.get(i).iswait()) {//不是停止时
                            int mapId = team.get(i).getMapId();
                            if (mapId != 3315) {
                                //未进帮战地图时
                                if (mapId != 1207) {
                                    ChangeMapBean change = new ChangeMapBean(1207, 8030, 4230);
                                    ParamTool.ACTION_MAP.get(AgreementUtil.changemap).action(team.get(i).getCtx(), GsonUtil.getGsonUtil().getgson().toJson(change));

                                } else {
                                    if (team.get(i).nearby(1207, 8030, 4230)) {
                                        team.get(i).flyAction(2,team.get(i).getLoginResult().getTurnAround());//下飞行器
                                        GolemBangFight.intobang(team.get(i));//我要参加帮战
                                        team.get(i).setBangtime(2);
                                    } else {
                                        team.get(i).setSleeptime(team.get(i).move(8030, 4230, team.get(i).getSp()));
                                        team.get(i).setBangtime(1);//赶路状态
                                    }
                                }
                            } else {
                                if (i == 0) {
                                    //队长操作
                                    GolemBean captain = team.get(0);
                                    if (captain.getBangtime() == 2) {
                                        if (captain.getTeam().equals(captain.getRoleName()) && team.size()>1) {
                                            GolemBangFight.CreatTeam(team);
                                            GolemBangFight.intoBattle(captain);//进入战场
                                            captain.setBangtime(4);//组好队的队长
                                        }
                                    }

                                    RoleShow roleShow = captain.getRoleShow();
                                    int x=roleShow.getX().intValue();
                                    int y=roleShow.getY().intValue();

                                    //帮战工具
                                    BangBattlePool pool=BangBattlePool.getBangBattlePool();
                                    BangFight bangFight=pool.getBangFight(captain.getLoginResult().getGang_id());
                                    if (bangFight==null) {//帮战结束的话，藏起来
                                        GolemBangFight.outbang(captain);//退出帮战
                                        ChangeMapBean change = new ChangeMapBean(1213, 18030, 14230);//藏在珊瑚海岛
                                        ParamTool.ACTION_MAP.get(AgreementUtil.changemap).action(team.get(i).getCtx(), GsonUtil.getGsonUtil().getgson().toJson(change));
                                        captain.setBangtime(88);
//                                        isover=true;
//                                        System.err.println("请注意：机器人帮派编号为"+captain.getGangid()+"的帮派，帮战已经结束");
                                        break;
                                    }
                                    //左右阵营
                                    boolean zuoyou = bangFight.iscamp(captain.getLoginResult().getGang_id());

                                    if (captain.getBangtime() == 4) {
                                        //第一队去挑战
                                        if (team == teams.get(0)) {
                                            //高手
                                            if(x !=GolemBangFight.XYGAOSHOU[0]|| y!= GolemBangFight.XYGAOSHOU[1]){
                                                GolemBangFight.intomaster(captain);//高手挑战
                                            }else {
                                                if (bangFight.Launch==null) {
                                                    HanHua(3,0,captain,5);
                                                    GolemBangFight.ytz(captain);//要挑战
                                                }else{
                                                    //接受挑战
                                                    if (bangFight.Launch.getCamp().compareTo(captain.getLoginResult().getGang_id())!=0) {
                                                        HanHua(4,0,captain,5);
                                                        GolemBangFight.jstz(captain);
                                                    }
                                                }
//                                                captain.setBangtime(6);//忙碌状态
                                                captain.setSleeptime(60*1000L+System.currentTimeMillis());
                                            }
                                        }
                                        if (team != teams.get(0)){
                                            if (Than500(x,1360) || Than500(y, 960)) {//先去中点
                                                captain.setSleeptime(captain.move(Battlefield.random.nextInt(800)+560, Battlefield.random.nextInt(700)+700, 0.28));//测试停顿
                                                captain.setBangtime(5);//到地点了
                                            }else{
                                                captain.setBangtime(5);//到地点了
                                            }
                                            continue;
                                        }
                                    }

                                    //增加bangtime==6的情况
                                    if (captain.getBangtime() == 6 && !captain.iswait() && !captain.isFighting()) {
                                        Map<String,Member> map=bangFight.getMap(captain.getGangid());
                                        if (map==null)continue;
                                        if (map.get(captain.getRoleName())!=null){
                                            if (map.get(captain.getRoleName()).getState()>1) {
                                                continue;
                                            }
                                            else {
                                                captain.setBangtime(4);
                                            }
                                        }
                                    }


                                    if (captain.getBangtime() == 5 && !captain.iswait() && !captain.isFighting() ) {

                                        //没有在老家
                                        if (Than200(x, zuoyou?GolemBangFight.XYHOME_Left[0] : GolemBangFight.XYHOME_Right[0])&& Than200(y, zuoyou?GolemBangFight.XYHOME_Left[1] : GolemBangFight.XYHOME_Right[1])) {
                                            //TODO 点塔操作
                                            if (zuoyou) {//左侧
                                                Build build=bangFight.getBuild(0);
                                                if (build.getState()==0&&captain.getBangtime()!=6) {//点炮操作
                                                    HanHua(2, 0, captain,5);
                                                    GolemBangFight.cn(captain,0);
                                                    PathPoint point=GolemBangFight.getBuild(0);
                                                    captain.setSleeptime(captain.move(point.getX(), point.getY(), 0.28));
                                                    captain.setSleeptime(30*1000L+System.currentTimeMillis());
                                                    captain.setBangtime(6);
                                                    continue;
                                                }else{
                                                    String launame=build.getRoleName();
                                                    GolemBean loginGolem=loginGolems.get(launame);
                                                    if (loginGolem != null) {
                                                        build=bangFight.getBuild(11);//右城门
                                                        if (GJCM(build,captain,11)) {
                                                            continue;
                                                        } else {
                                                            boolean is=false;
                                                            for (int j = 12; j < 16; j++) {
                                                                build=bangFight.getBuild(j);
                                                                if (GJT(build,captain,j)) {
                                                                    is=true;
                                                                    break;
                                                                }
                                                            }
                                                            if (!is) {
                                                                for (int j = 2; j < 6; j++) {
                                                                    build=bangFight.getBuild(j);
                                                                    if (CNT(build,captain,j)) {
                                                                        is=true;
                                                                        break;
                                                                    }
                                                                }
                                                            }

                                                            if (!is) {
                                                                if (Battlefield.random.nextInt(50) >45) {
                                                                    captain.setSleeptime(captain.move(Battlefield.random.nextInt(800)+560, Battlefield.random.nextInt(700)+700, 0.28));//测试停顿
                                                                    captain.setBangtime(5);//到地点了
                                                                    continue;
                                                                }
                                                            }
                                                        }
                                                    }else {
                                                        if (captain.getBangtime()!=6) {
                                                            //有人点炮，走过去，掐断
                                                            captain.setSleeptime(captain.move(GolemBangFight.XYDAPAO[0], GolemBangFight.XYDAPAO[1] , 0.28));
                                                            HanHua(1,0,captain,5);
                                                            GolemBangFight.qdph(captain);
                                                            captain.setBangtime(6);
                                                            continue;
                                                        }
                                                    }
                                                }
                                            } else {
                                                Build build=bangFight.getBuild(0);
                                                if (build.getState()==0&&captain.getBangtime()!=6) {//点炮操作
                                                    HanHua(2, 0, captain,5);
                                                    GolemBangFight.cn(captain,0);
                                                    PathPoint point=GolemBangFight.getBuild(0);
                                                    captain.setSleeptime(captain.move(point.getX(), point.getY(), 0.28));
                                                    captain.setSleeptime(30*1000L+System.currentTimeMillis());
                                                    captain.setBangtime(6);
                                                    continue;
                                                }else{
                                                    String launame=build.getRoleName();
                                                    GolemBean loginGolem=loginGolems.get(launame);
                                                    if (loginGolem != null) {
                                                        build=bangFight.getBuild(1);//右城门
                                                        if (GJCM(build,captain,1)) {
                                                            continue;
                                                        } else {
                                                            boolean is=false;
                                                            for (int j = 2; j < 6; j++) {
                                                                build=bangFight.getBuild(j);
                                                                if (GJT(build,captain,j)) {
                                                                    is=true;
                                                                    break;
                                                                }
                                                            }
                                                            if (!is) {
                                                                for (int j = 12; j < 16; j++) {
                                                                    build=bangFight.getBuild(j);
                                                                    if (CNT(build,captain,j)) {
                                                                        is=true;
                                                                        break;
                                                                    }
                                                                }
                                                            }
                                                            if (!is) {
                                                                if (Battlefield.random.nextInt(50) >45) {
                                                                    captain.setSleeptime(captain.move(Battlefield.random.nextInt(800)+560, Battlefield.random.nextInt(700)+700, 0.28));//测试停顿
                                                                    captain.setBangtime(5);//到地点了
                                                                    continue;
                                                                }
                                                            }
                                                        }
                                                    }else {
                                                        if (captain.getBangtime()!=6 ) {
                                                            //有人点炮，走过去，掐断
                                                            captain.setSleeptime(captain.move(GolemBangFight.XYDAPAO[0], GolemBangFight.XYDAPAO[1] , 0.28));
                                                            HanHua(1,0,captain,5);
                                                            GolemBangFight.qdph(captain);
                                                            captain.setBangtime(6);
                                                            continue;
                                                        }
                                                    }
                                                }
                                            }
                                        }else {
                                            captain.setBangtime(4);
                                        }
                                    }

                                    //点塔状态
//                                    if (captain.getBangtime() == 6 && !captain.iswait() && !captain.isFighting() ) {
//                                        //被打回来了
//                                        if (!Than200(x, zuoyou?GolemBangFight.XYHOME_Left[0] : GolemBangFight.XYHOME_Right[0])&& !Than200(y, zuoyou?GolemBangFight.XYHOME_Left[1] : GolemBangFight.XYHOME_Right[1])) {
//                                            captain.setBangtime(4);
//                                        }
//                                    }
                                }else {
//                                    System.out.println(team.get(i).getRoleName()+"不是队长");
                                }
                            }
                        }else{
//                            System.out.println(team.get(i).getRoleName()+"在等待");
                        }
                    }
                } else {
                    System.out.println("该队伍为空！");
                }
            }
        }
    }
    public static boolean GJCM(Build  build,GolemBean captain,int bianhao) {
        if (build.getState()==0 && build.getHp()>0&&captain.getBangtime()!=6) {
            GolemBangFight.gjt(captain,bianhao);
            PathPoint point=GolemBangFight.getBuild(bianhao);
            captain.setSleeptime(captain.move(point.getX(), point.getY(), 0.28));
            captain.setSleeptime(30*1000L+System.currentTimeMillis());
            captain.setBangtime(6);
            return true;
        }
        return false;
    }
    public static boolean GJT(Build build,GolemBean captain,int bianhao) {
        String launame=build.getRoleName();

//        GolemBean loginGolem=loginGolems.get(launame);
//        if (loginGolem != null) {
//            //是机器人 不打
//        }else {
        if (launame !=null && !launame.equals("")&& bianhao!=0) {
            //有人守塔，不打
        }else {
            if (build.getState() == 0 && build.getHp() > 0&&captain.getBangtime()!=6) {
                GolemBangFight.gjt(captain, bianhao);
                PathPoint point = GolemBangFight.getBuild(bianhao);
                captain.setSleeptime(captain.move(point.getX(), point.getY(), 0.28));
                captain.setSleeptime(30 * 1000L + System.currentTimeMillis());
                captain.setBangtime(6);
                return true;
            }
        }
//        }
        return false;
    }

    public static boolean CNT(Build build,GolemBean captain,int bianhao) {
        String launame=build.getRoleName();

        GolemBean loginGolem=loginGolems.get(launame);
        if (loginGolem != null) {
            //是机器人 不充能
        }else {
            if (launame !=null && !launame.equals("")&& bianhao!=0) {
                //有人攻击，不充能
            }else {
                if (build.getState() == 0 && build.getHp() > 0&&captain.getBangtime()!=6) {
                    GolemBangFight.cn(captain, bianhao);
                    PathPoint point = GolemBangFight.getBuild(bianhao);
                    captain.setSleeptime(captain.move(point.getX(), point.getY(), 0.28));
                    captain.setSleeptime(30 * 1000L + System.currentTimeMillis());
                    captain.setBangtime(6);
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 队伍喊话
     * @param type 1掐断炮火 2点炮 3挑战 4接受挑战
     * @param pindao 频道 0当前  1队伍 2帮派 3世界 4传音 5系统 6消息 7系统加传音    8广告滚动   9系统滚动
     * @param golemBean 角色
     * @param jilv 几率
     */
    public  void HanHua(int type, int pindao ,GolemBean golemBean,int jilv) {
//        喊话太烦了去掉
//        if (Battlefield.random.nextInt(100) < jilv) {
//            return;
//        }
//        String message =Message(type);
//        NChatBean nchatBean = new NChatBean();
//        nchatBean.setId(pindao);
//        nchatBean.setMessage(message);
//        ParamTool.ACTION_MAP.get(AgreementUtil.chat).action(golemBean.getCtx(), GsonUtil.getGsonUtil().getgson().toJson(nchatBean));
    }
    public String Message(int type){
        //点塔
        if (type == 1) {
            int i = Battlefield.random.nextInt(5);
            if(i==0){
                return "炮台我去杀";
            }else if(i==1) {
                return "我去炮台";
            }else if(i==2) {
                return "把状态加好";
            }else if(i==3) {
                return "抢到了#89";
            }else {
                return "我去试试";
            }
        }
        if (type == 2) {
            int i = Battlefield.random.nextInt(5);
            if(i==0){
                return "你们在这我去炮台#32";
            }else if(i==1) {
                return "看我手速就行了";
            }else if(i==2) {
                return "#17";
            }else if(i==3) {
                return "血法加好看我的就行了#50";
            }else {
                return "这回是我的#28";
            }
        }
        if (type == 3) {
            int i = Battlefield.random.nextInt(5);
            if(i==0){
                return "咱们一队就在这就行了";
            }else if(i==1) {
                return "这个掉血快";
            }else if(i==2) {
                return "咱们挑战";
            }else if(i==3) {
                return "这里才爽#99";
            }else {
                return "输了别赖我啊";
            }
        }
        if (type == 4) {
            int i = Battlefield.random.nextInt(5);
            if(i==0){
                return "咱队试试";
            }else if(i==1) {
                return "大不了输呗#17";
            }else if(i==2) {
                return "点了啊";
            }else if(i==3) {
                return "不等他们队了";
            }else {
                return "直接搞";
            }
        }
        if (type == 5) {
            int i = Battlefield.random.nextInt(20); // 注意这里的数字20表示口号的数量
            switch (i) {
                case 0:
                    return "我去看看";
                case 1:
                    return "有点猛#35";
                case 2:
                    return "#17";
                case 3:
                    return "好";
                case 4:
                    return "嗯";
                case 5:
                    return "不用带克";
                case 6:
                    return "支援带大力的";
                case 7:
                    return "神出吧";
                case 8:
                    return "用反隐套";
                case 9:
                    return "你秒";
                case 10:
                    return "别冰#24";
                case 11:
                    return "混杀";
                case 12:
                    return "我毒#35";
                case 13:
                    return "不用睡";
                case 14:
                    return "不用遗忘直接秒";
                case 15:
                    return "负敏？";
                case 16:
                    return "四千多吧";
                case 17:
                    return "听我的";
                case 18:
                    return "这个给啥";
                case 19:
                    return "积分";
                default:
                    return "别退出去，要不然进不来#35";
            }
        }
        return "";
    }






    /**
     * 强退战斗
     * @param loginResult
     */
    public static void outFighting(LoginResult loginResult) {
        if (loginResult.getFighting () != 0) {
            BattleData battleData2 = BattleThreadPool.BattleDatas.get (loginResult.getFighting ());
            if (battleData2 != null) {
                BattleThreadPool.removeBattleData(battleData2);
            }
        }

    }

    /**
     * 创建水陆队伍
     */
    public void initSLteams(){
        GolemConfig config = GameServer.getGolemConfig();
        int num = 0;
        if (config.get("水陆队伍")!=null&&!config.get("水陆队伍").equals("")) {
            num = Integer.parseInt(config.get("水陆队伍"));
            if (num == 0) {
                SLteam =false;
            }
            // 将 GolemBean 和其等级数值放入一个列表中
            List<Map.Entry<GolemBean, Integer>> golemListWithGrade = new ArrayList<>();
            for (GolemBean golemBean : tempList) {
                int grade = golemBean.getLoginResult().getGrade();
                golemListWithGrade.add(new AbstractMap.SimpleEntry<>(golemBean, grade));
            }
            // 根据等级数值降序排序
            golemListWithGrade.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

            // 取出排名前 num 的 GolemBean
            List<String> topGolems = new ArrayList<>();
            for (int i = 0; i < golemListWithGrade.size(); i++) {
                if (topGolems.size() >= num * 5) {
                    break;
                }
                //找出队长并且队伍人数为5的，放入
                LoginResult loginResult = golemListWithGrade.get(i).getKey().getLoginResult();
                String[] teams=loginResult.getTeam().split("\\|");
                if (teams[0].equals(loginResult.getRolename())&&teams.length == 5) {
                    topGolems.addAll(Arrays.asList(teams));
                }
            }
            // topGolems 现在包含了排名前 num 的 GolemBean 队长
            // 放进水陆机器人里
            for (String gl:topGolems){
                GolemBean golemBean = loginGolems.get(gl);
                if (golemBean!=null){
                    golemBean.setSLtype(1);//参加水陆状态
                    outFighting(golemBean.getLoginResult());
                }
            }
        }else {
            SLteam =false;
        }

    }



    /**
     * 帮派分组队伍
     * @return
     */
    private ConcurrentHashMap<Integer, List<List<GolemBean>>> groupLoginGolemsByTeamBy5(ConcurrentHashMap<String, GolemBean> loginGolems) {
        // 临时Map用来按帮派ID收集GolemBean
        ConcurrentHashMap<Integer, List<GolemBean>> gangToMembers = new ConcurrentHashMap<>();

        // 遍历登录队员，按帮派ID分组
        for (Map.Entry<String, GolemBean> entry : loginGolems.entrySet()) {
            GolemBean golem = entry.getValue();
            int gangId = golem.getGangid().intValue(); // 假设GolemBean有getGangid()方法获取帮派ID
            gangToMembers.computeIfAbsent(gangId, k -> new ArrayList<>()).add(golem);

            ///////设置离队
            if (golem.getTeamBean() != null) {
                disbandTeam(golem);
                golem.getLoginResult().setTeamInfo(null);
//                golem.getLoginResult().setTroop_id(null);//解除队伍关键

                outFighting(golem.getLoginResult());
            }
            ///////设置离队
        }

        // 新的映射，用于存储分组后的队伍
        ConcurrentHashMap<Integer, List<List<GolemBean>>> bangteams2 = new ConcurrentHashMap<>();

        // 对每个帮派的队员列表进行5人分组
        for (Map.Entry<Integer, List<GolemBean>> gangEntry : gangToMembers.entrySet()) {
            int gangId = gangEntry.getKey();
            List<GolemBean> members = gangEntry.getValue();

            List<List<GolemBean>> groupedTeams = new ArrayList<>();
            for (int i = 0; i < members.size(); i += 5) {
                int end = Math.min(i + 5, members.size());
                groupedTeams.add(members.subList(i, end));
            }

            bangteams2.put(gangId, groupedTeams);
        }

        return bangteams2;
    }

    /**
     * 创建队伍
     * @param golemBean
     */
    public static void CreatTeamCaptain(GolemBean golemBean) {
        LoginResult Captain = golemBean.getLoginResult();
        TeamBean teamBean= TeamUtil.getTeam(Captain.getTroop_id());
        if (teamBean==null){
            teamBean= TeamUtil.addTeam(Captain.getTeamRole());//队长创建队伍
            Captain.setTroop_id(teamBean.getTeamId());
            Captain.setTeamInfo(teamBean.getTeamInfo());
        }
    }

    private void handle(GolemBean golemBean) {
        Integer idleTime = golemBean.getIdleTime();
        if (idleTime == null) {// 忙碌状态
//            try {
            GolemActive active = golemBean.getTask();
            if (active != null) {
                TeamBean teamBean = golemBean.getTeamBean();
                if (active.getType() == 2) {
                    // 单人任务处理
                    if (teamBean != null) {
                        if (golemBean.getRoleName().equals(teamBean.getTeamName())) {
                            // 是队长把其他队员踢出
                            List<TeamRole> teams = teamBean.getTeams();
                            for (int i = 1; i < teams.size(); i++) {
                                teamBean.removeTeamRole(teams.get(i).getRoleId());
                            }
                        } else {
                            // 离开队伍
                            teamBean.removeTeamRole(golemBean.getRoleId());
                        }
                    }
                    golemBean.getScript().createScript(true);
                    return;
                } else {
                    if (teamBean != null) {
                        // 组队任务处理
                        if (golemBean.getRoleName().equals(teamBean.getTeamName())) {
                            handleTeamApply(golemBean);// 处理队伍申请
                            teamBean.removeApply();// 清空队伍申请
                            // 队伍是否满足条件
                            if (teamListener(teamBean, null, true)) {
                                golemBean.getScript().createScript(true);
                            } else if (golemBean.getTeamBean() != null) {
                                if (!golemBean.nearby(1)) {
                                    golemBean.getScript().createScript(active);
                                } else {
                                    // 如果没有解散队伍并一直不满足条件则考虑解散队伍
                                    List<BigDecimal> teamIds = taskTeams.get(golemBean.getTaskSetId());
                                    int count = 0;
                                    if (teamIds != null) {
                                        for (int i = 0; i < teamIds.size(); i++) {
                                            TeamBean team = TeamUtil.getTeam(teamIds.get(i));
                                            if (team == null) {
                                                removeTaskTeams(teamIds.get(i),active);
                                                continue;
                                            }
                                            if (team.getTeamSize() < 5) {
                                                TeamRole teamRole = new TeamRole();
                                                teamRole.setRoleId(golemBean.getRoleId());
                                                teamRole.setName(golemBean.getRoleName());
                                                teamRole.setGrade(golemBean.getGrade());
                                                teamRole.setSpeciesId(golemBean.getSpeciesId());
                                                if (team.getTeamSize()>=teamBean.getTeamSize()&&teamListener(team, teamRole, false)) {
                                                    count++;
                                                }
                                            }
                                        }
                                    }
                                    if (!teamIds.contains(teamBean.getTeamId())) {
                                        addTaskTeams(teamBean.getTeamId(),active.getTasksetId());
                                    }
                                    if (count >= 1) {
                                        disbandTeam(golemBean);
                                    }
                                }
                            }
                        }
                        return;
                    }
                }
            }
            golemBean.setIdleTime(System.currentTimeMillis());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        } else { // 闲置状态
            GolemActive active = golemBean.getTask();
            if (active == null) {
                // 任务分配
                active = golemBean.getNewTask();
                if (active == null) {
                    // 解散队伍
                    if (golemBean.getTeamBean() != null) disbandTeam(golemBean);
                    // 是否满足摆摊条件
                    GolemConfig config = GameServer.getGolemConfig();
//                    if (config.get("摆摊开关").equals("开")) {
//                        Stall stall = stallValidate(golemBean, config);
//                        if (stall != null && stall.getState() == StallPool.PREPARE) {
//                            // 是否在摆摊位置
//                            if (stall.pointContains(golemBean.getMapId(), golemBean.getX(), golemBean.getY())) {
//                                // 摆摊
//                                stall(golemBean, config, stall);
//                                int minute = config.getToInt("摆摊时间", 180);
//                                golemBean.setStallTime(System.currentTimeMillis() + minute * 60 * 1000);
//                            } else {
//                                // 移动到摆摊位置
//                                golemBean.getScript().createScript(stall.getMapid(), stall.getX(), stall.getY(), true);
//                                golemBean.getScript().setAccurate(false);
//                            }
//                            return;
//                        }
//                    }
                    if (config.getIdlePoints() != null) {// 有指定闲置区域
                        if (!config.idleContains(golemBean.getMapId(), golemBean.getX(), golemBean.getY())) {
                            // 移动到指定闲置区域
                            Point point = config.getIdleRandomPoint();
                            golemBean.getScript().createScript(point.getMapId(), point.getX(), point.getY(), true);
                            golemBean.getScript().setAccurate(false);
                            return;
                        }
                    }
//                    Integer flyType = golemBean.getLoginResult().getRoleShow().getFlyType();
                    Integer flyType = golemBean.getLoginResult().getRoleShow().getFly_id();//随风改——飞行判定
                    if (flyType != null && flyType == 1) {
                        golemBean.flyAction(2,golemBean.getLoginResult().getTurnAround());
                    }
                    // 等待掉线
                    if (idleTime >= 10) deletGolem(golemBean);
                } else {
                    // 移动到NPC处
                    golemBean.getScript().createScript(active);
                    if (active.getType() == 2) {
                        golemBean.setIdleTime(null); // 设置为忙碌
                    }
                }
            } else {
                TeamBean teamBean = golemBean.getTeamBean();
                if (teamBean == null) {
                    List<BigDecimal> teamIds = taskTeams.get(active.getTasksetId());
                    List<BigDecimal> roleIds = new ArrayList<>();
                    if (teamIds != null) {
                        if (idleTime <= 2) {
                            TeamRole teamRole = new TeamRole();
                            teamRole.setRoleId(golemBean.getRoleId());
                            teamRole.setName(golemBean.getRoleName());
                            teamRole.setGrade(golemBean.getGrade());
                            teamRole.setSpeciesId(golemBean.getSpeciesId());
                            for (int i = 0; i < teamIds.size(); i++) {
                                TeamBean team = TeamUtil.getTeam(teamIds.get(i));
                                if (team == null) {
                                    teamIds.remove(teamIds.get(i));
                                    continue;
                                }
                                // 队伍是否可以加入
                                if (teamListener(team, teamRole, false)) {
                                    roleIds.add(team.getTeams().get(0).getRoleId());
                                }
                            }
                        }
                    }
                    roleIds = roleIds.stream()
                            .sorted((roleId1, roleId2) -> teamCompare(roleId1, roleId2))
                            .collect(Collectors.toList());
                    if (roleIds.size() == 0) {// 创建队伍
                        creatTeam(golemBean, active.getTasksetId());
                    } else {
                        for (int i = 0; i < roleIds.size(); i++) {
                            teamApply(golemBean.getCtx(), roleIds.get(i).toString());
                        }
                    }
                } else {
                    // 如果是队长则检查执行任务的队伍ID列表是否存在该队伍
                    if (active.getType() != 2 && golemBean.getRoleName().equals(teamBean.getTeamName())) {
                        addTaskTeams(teamBean.getTeamId(), active.getTasksetId());
                    }
                    golemBean.setIdleTime(null); // 设置为忙碌
                }
            }
        }
    }

//    /** 机器人移动 **/
//    public static long move(int x, int y, double sp, GolemBean golemBean) {
//        RoleShow roleShow = golemBean.getLoginResult().getRoleShow();
//        RoleMoveBean roleMoveBean = new RoleMoveBean();
//        List<PathPoint> points = new ArrayList<>();
//        points.add(new PathPoint(roleShow.getX().intValue(), roleShow.getY().intValue()));
//        points.add(new PathPoint(x, y));
//        roleMoveBean.setPaths(points);
//        golemBean.getLoginResult().setX(new Long(x));
//        golemBean.getLoginResult().setY(new Long(y));
//        roleMoveBean.setRole(golemBean.getLoginResult().getRolename());
//
//        String msg = Agreement.getAgreement().moveAgreement(GsonUtil.getGsonUtil().getgson().toJson(roleMoveBean));
//        SendMessage.sendMessageToMapRoles(golemBean.getLoginResult().getMapid(), msg);
//        return XLPath.getMoveTime(points, sp) + System.currentTimeMillis();
//    }
    /** 可申请队伍排序 队伍人数多的排前面 **/
    private int teamCompare(BigDecimal roleId1, BigDecimal roleId2) {
        TeamBean teamRole1 = TeamUtil.getTeamRole(roleId1);
        if (teamRole1 == null) {
            return 1;
        }
        TeamBean teamRole2 = TeamUtil.getTeamRole(roleId2);
        if (teamRole2 == null) {
            return -1;
        }
        return teamRole1.getTeams().size() > teamRole2.getTeams().size() ? -1 : teamRole1.getTeams().size() == teamRole2.getTeams().size() ? 0 : 1;
    }

    /** 分配怪物条件 队长并且成员都满足条件的队长有获得怪物分配的资格 **/
    private boolean isAssignedRobot(GolemBean golemBean, int robot) {
        String[] names = golemBean.getTeam().split("\\|");
        // 不是队长
        if (!names[0].equals(golemBean.getRoleName())) {
            return false;
        }
        // 队伍成员不满足五人
        if (names.length < 5) {
            return false;
        }
        // 队长有未击杀的怪物
        if (StringUtils.isNotBlank(golemBean.getRobot())) {
            return false;
        }
        for (int i = 0; i < names.length; i++) {
            GolemBean golem = loginGolems.get(names[i]);
            if (golem == null) {
                continue;
            }
            // 成员不满足击杀要求
            if (!golem.isAssignedRobot(robot)) {
                return false;
            }
        }
        return true;
    }

    /** 创建队伍 **/
    private void creatTeam(GolemBean golemBean, Integer taskSetId) {
        try {
            ChannelHandlerContext cxt = golemBean.getCtx();
            ParamTool.ACTION_MAP.get(AgreementUtil.team1).action(cxt, "");
            TeamBean teamBean = TeamUtil.getTeamRole(golemBean.getRoleId());
            if (teamBean != null) {
                addTaskTeams(teamBean.getTeamId(), taskSetId);
                golemBean.setIdleTime(null); // 设置为忙碌
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /** 申请队伍 **/
    private void teamApply(ChannelHandlerContext cxt, String roleId) {
        ParamTool.ACTION_MAP.get(AgreementUtil.team2).action(cxt, roleId);
    }

    /** 处理组队申请 **/
    private void handleTeamApply(GolemBean golemBean) {

        if (golemBean == null|| golemBean.getTeamBean() == null|| golemBean.getCtx() == null) {
            return;
        }
        TeamBean teamBean = golemBean.getTeamBean();
        List<TeamRole> applyTeams = teamBean.getApplyTeams();
        if (applyTeams != null&& applyTeams.size() > 0) {
            for (int i = applyTeams.size()-1; i >=0; i--) {
                if (applyTeams!=null&&applyTeams.get(i) != null&&applyTeams.size()>=i) {
                    if (teamListener(teamBean, applyTeams.get(i), false)) {
                        ParamTool.ACTION_MAP.get(AgreementUtil.team5).action(golemBean.getCtx(), "A" + applyTeams.get(i).getRoleId());
                    } else {
                        ParamTool.ACTION_MAP.get(AgreementUtil.team5).action(golemBean.getCtx(), "R" + applyTeams.get(i).getRoleId());
                    }
                }
            }
        }
    }

    /** 离开、解散队伍 **/
    private void disbandTeam(GolemBean golemBean) {
        try {
            TeamBean teamBean = TeamUtil.getTeamRole(golemBean.getRoleId());
            if (teamBean == null) return;
            if (teamBean.getTeamName().equals(golemBean.getRoleName())) {
                // 队长取消任务
                cancelTask(golemBean);
                // 解散队伍
                List<TeamRole> teams = teamBean.getTeams();
                for (int i = 0; i < teams.size(); i++) {
                    GolemBean golem = loginGolems.get(teams.get(i).getName());
                    if (golem == null) continue;
                    golem.setIdleTime(System.currentTimeMillis());
                }
                teamBean.dismissTeam();
            } else {
                GolemBean golem = loginGolems.get(teamBean.getTeamName());
                // 队长取消任务
                cancelTask(golem);
                // 设置闲置
                golem.setIdleTime(System.currentTimeMillis());
                golemBean.setIdleTime(System.currentTimeMillis());

                teamBean.removeTeamRole(golemBean.getRoleId());
            }
            ParamTool.ACTION_MAP.get(AgreementUtil.team5).action(golemBean.getCtx(), "D");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 队长取消任务**/
    private void cancelTask(GolemBean golemBean) {
        // 删除执行任务的队伍ID
        GolemActive target = golemBean.getTarget();
        if (target != null) {
            removeTaskTeams(golemBean.getTeamId(), target);
            if (target.getType() != 0) {
                RoleData roleData = RolePool.getRoleData(golemBean.getRoleId());
                Task task = roleData.getTaskBySetId(target.getTasksetId());
                if (task != null) ParamTool.ACTION_MAP.get(AgreementUtil.TASKN).action(golemBean.getCtx(), "E" + task.getTaskId());
            }
            golemBean.getScript().setEnd(true);
        }
    }

    /** 清空机器人登录信息 **/
    private void clearUserNames() {
        try {
            List<String> userNames = new ArrayList<>();
            for (String userName : userNameMap.keySet()) {
                if (loginGolems.get(userNameMap.get(userName)) == null) userNames.add(userName);
            }
            for (int i = 0; i < userNames.size(); i++) userNameMap.remove(userNames.get(i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 修改召唤兽属性 **/
    private void updatePetProperty(GolemBean golemBean) {
        try {
            List<RoleSummoning> pets=AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRoleID(golemBean.getRoleId());

            int toplvl = 0;
            for (int i = 0; i < pets.size(); i++) {
                if (pets.get(i) != null) {
                    toplvl = Math.max(toplvl, pets.get(i).getGrade());
                }
            }
            for (int i = 0; i < pets.size(); i++) {
                if (pets.get(i) != null) {
                    pets.get(i).setGrade(toplvl);
                }
            }


            for (int ii = 0; ii < pets.size(); ii++) {

                RoleSummoning pet = pets.get(ii);
                if (pet == null) return;
                int lvl = BattleMixDeal.petLvlint(pet.getGrade());
                int zs = BattleMixDeal.petTurnRount(pet.getGrade());

                if (zs == 1 && !pet.getSsn().equals("3")&& !pet.getSsn().equals("4")&& !pet.getSsn().equals("6")) {//假人宝宝1转变色
                    ColorScheme colorScheme = GameServer.getColors(Integer.parseInt(pet.getSummoningid()));
                    if (colorScheme != null) {
                        pet.setColorScheme(colorScheme.getValue());
                    }
                }


                // 转生
                if ((zs == 0 && lvl >= 100) || (zs == 1 && lvl >= 120) || (zs == 2 && lvl >= 140) || (zs == 3 && lvl >= 180)) {
                    zs++;
                    pet.setGrade(pet.getGrade() + 1);
                    pet.setTurnRount(zs);
                    //成长率加0.1
                    BigDecimal grow = UsePetAction.mathDouble(Double.parseDouble(pet.getGrowlevel()), 0.1);
                    pet.setGrowlevel(Arith.xiaoshu3(grow.doubleValue()));

                    pet.setBasishp(0);
                    pet.setBasismp(0);
                    pet.setExp(new BigDecimal(0));

                    lvl = BattleMixDeal.petLvlint(pet.getGrade());


                    if (pet.getInnerGoods() != null) {
                        //内丹升级
                        String[] neidanids = pet.getInnerGoods().split("\\|");
                        for (int i = 0; i < neidanids.length; i++) {
                            Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(neidanids[i]));
                            if (good == null) continue;

                            String[] vs = good.getValue().split("\\|");
                            StringBuffer buffer = new StringBuffer();
                            buffer.append(vs[0]);
                            buffer.append("|");
                            buffer.append(vs[1]);
                            buffer.append("|内丹等级=");
                            buffer.append(zs);
                            buffer.append("转");
                            buffer.append(lvl);
                            buffer.append("|经验=0");
                            good.setValue(buffer.toString());

                            AllServiceUtil.getGoodsTableService().updateGoodRedis(good);

                        }
                    }

                }
                pet.setBone(lvl * 2);
                pet.setSpir(lvl * 1);
                pet.setPower(lvl * 4);
                pet.setSpeed(lvl * 2);
                if (zs >= 4) {
                    pet.setCalm(lvl);
                }
                if (zs >= 3) {
                    pet.setFriendliness(20000000L);
                } else if (zs >= 2) {
                    pet.setFriendliness(10000000L);
                } else {
                    pet.setFriendliness(5000000L);
                }
                AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 发放物资 **/
    private void provideMaterials(LoginResult login) {
        // 发放等级物资  发放并且装备  坐骑携带指定技能  召唤兽携带指定技能
//        try {
//            ConcurrentHashMap<String, List<GolemDraw>> golemDrawMap = GameServer.getAllGolemDraw();
//            for (String value : golemDrawMap.keySet()) {
//                int specifyLvl = AnalysisString.lvldirection(value); // 所需等级
//                int receiveLvl = login.getGolemLvl();          // 已领取的最高等级
//                int currentLvl = login.getGrade();             // 当前等级
//                // 所需等级 > 已领取的最高等级 && 所需等级 <= 当前等级
//                if (specifyLvl > receiveLvl && specifyLvl <= currentLvl) {
//                    List<GolemDraw> golemDraws = golemDrawMap.get(value);
//                    for (int i = 0; i < golemDraws.size(); i++) {
//                        GolemDraw golemDraw = golemDraws.get(i);
//                        // 匹配角色
//                        if (golemDraw.isMatching(login.getSpecies_id())) {
//                            switch (golemDraw.getType()) {
//                                case "装备":
//                                    provideEquip(login, golemDraw);
//                                    break;
//                                case "召唤兽":
//                                    providePet(login, golemDraw);
//                                    break;
//                            }
//                        }
//                    }
//                    login.setGolemLvl(specifyLvl);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        //随风改——机器人装备需求
        try {
            ConcurrentHashMap<String, List<GolemDraw>> golemDrawMap = GameServer.getAllGolemDraw();
            for (String value : golemDrawMap.keySet()) {
                int specifyLvl = AnalysisString.lvldirection(value); // 所需等级
//                int receiveLvl = login.getGolemLvl();          // 已领取的最高等级
                int currentLvl = login.getGrade();             // 当前等级
                // 所需等级 > 已领取的最高等级 && 所需等级 <= 当前等级
                if (specifyLvl <= currentLvl) {
                    List<GolemDraw> golemDraws = golemDrawMap.get(value);
                    for (int i = 0; i < golemDraws.size(); i++) {
                        GolemDraw golemDraw = golemDraws.get(i);
                        // 匹配角色
                        if (golemDraw.isMatching(login.getSpecies_id())) {
                            switch (golemDraw.getType()) {
                                case "装备":
                                    provideEquip(login, golemDraw);
                                    break;
                                case "召唤兽":
                                    providePet(login, golemDraw);
                                    break;
                            }
                        }
                    }
//                    login.setGolemLvl(specifyLvl);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 发放装备 **/
    private void provideEquip(LoginResult login, GolemDraw golemDraw) {
        try {
            Goodstable goods = GameServer.getGood(golemDraw.getId());
            int type = Goodtype.EquipmentType(goods.getType());
            if (type == -1) return;
            goods.setRole_id(login.getRole_id());
            AllServiceUtil.getGoodsTableService().insertGoods(goods);

            RoleData data = RolePool.getRoleData(login.getRole_id());
            BigDecimal equipId = data.getGoodEquip()[type];
            if (equipId != null) {
                Goodstable equip = AllServiceUtil.getGoodsTableService().getGoodsByRgID(equipId);
                if (equip != null) {
                    // 删除旧的装备
                    AllServiceUtil.getGoodsTableService().deleteGoodsByRgid(equip.getRgid());
                }
            }

            // 穿戴新的装备
            goods.setStatus(1);
            RedisControl.insertKeyT(RedisParameterUtil.GOODS, goods.getRgid().toString(), goods);
            RedisControl.insertController(RedisParameterUtil.GOODS, goods.getRgid().toString(), RedisControl.CALTER);
            String key = RedisParameterUtil.GOODSST + "_" + goods.getRole_id().toString();
            RedisControl.deletrValue(key, "0", goods.getRgid().toString());
            RedisControl.insertListRedis(key, "1", goods.getRgid().toString());
            data.CEquip(goods.getRgid(), type, true);

            if (Goodtype.EquipmentType(goods.getType()) == 0 || Goodtype.EquipmentType(goods.getType()) == 12) {
                String eSkin=null;  // 角色皮肤

                int newGW=0;
                long weaponSkin = 0;
                if (data.getGoodEquip()[0] != null) {
                    Goodstable weapon = AllServiceUtil.getGoodsTableService().getGoodsByRgID(data.getGoodEquip()[0]);
                    if (weapon != null) {
                        weaponSkin = CreepsMixdeal.good(Integer.parseInt(goods.getSkin()));
                    }
                }

//                if (data.getGoodEquip()[14] != null) {
//                    Goodstable weaponGW = AllServiceUtil.getGoodsTableService().getGoodsByRgID(data.getGoodEquip()[14]);
//                    if (weaponGW != null&&StringUtils.isNumeric(weaponGW.getValue())) {
//                        newGW = Integer.parseInt(weaponGW.getValue());
//                    }
//                }

                long se=login.getSpecies_id().longValue();
                //TODO 获取光武皮肤
                //	剑:1 扇:2 锤:3 斧头:4 拳套:5 书:6 棍:7 鞭:8	钩:9 刀:10 双环:11 枪:12 幡:13 爪:14 浮尘:15 飘带:16 灯笼:17 弓:18
                if ((weaponSkin == 2 && se == 20001 || weaponSkin == 1 && se == 20002 ||
                        weaponSkin == 4 && se == 20003 || weaponSkin == 5 && se == 20003 ||
                        weaponSkin == 9 && se == 20004  || weaponSkin == 8 && se == 20004 || weaponSkin == 10 && se == 20005 ||
                        weaponSkin == 7 && se == 20005  || weaponSkin == 10 && se == 20006 || weaponSkin == 12 && se == 20006 ||
                        weaponSkin == 5 && se == 20007 || weaponSkin == 1 && se == 20008 ||
                        weaponSkin == 10 && se == 20008 || weaponSkin == 2 && se == 20009 || weaponSkin == 6 && se == 20009 ||
                        weaponSkin == 8 && se == 20010  || weaponSkin == 12 && se == 21001 ||
                        weaponSkin == 10 && se == 21002 || weaponSkin == 13 && se == 21002 ||
                        weaponSkin == 10 && se == 21003 || weaponSkin == 12 && se == 21003 || weaponSkin == 9 && se == 21004 ||
                        weaponSkin == 10 && se == 21004 || weaponSkin == 1 && se == 21005 ||
                        weaponSkin == 14 && se == 21006 || weaponSkin == 8 && se == 21006 ||
                        weaponSkin == 4 && se == 21007  || weaponSkin == 10 && se == 21008|| weaponSkin == 11 && se == 21008 ||
                        weaponSkin == 10 && se == 21009 || weaponSkin == 4 && se == 21009 || weaponSkin == 14&& se == 21010||
                        weaponSkin == 9 && se == 21010  || weaponSkin == 3 && se == 22001||
                        weaponSkin == 14 && se == 22002 || weaponSkin == 1 && se == 22002 ||
                        weaponSkin == 14 && se == 22003 || weaponSkin == 10 && se == 22004 || weaponSkin == 5&& se == 22004||
                        weaponSkin == 16&& se == 22005 || weaponSkin == 1&& se == 22006||
                        weaponSkin == 12 && se == 22006 || weaponSkin == 14 && se == 22007 ||
                        weaponSkin == 11 && se == 22008 || weaponSkin == 16 && se == 22008 || weaponSkin == 1&& se == 22009||
                        weaponSkin == 13 && se == 22009 || weaponSkin == 16 && se == 22010|| weaponSkin == 17 && se == 22010 ||
                        weaponSkin == 1&& se == 23001   || weaponSkin == 10 && se == 23001 ||
                        weaponSkin == 5 && se == 23002  || weaponSkin == 13 && se == 23003|| weaponSkin == 6 && se == 23003||
                        weaponSkin == 9 && se == 23004  || weaponSkin == 8 && se == 23004|| weaponSkin == 17&& se == 23005L ||
                        weaponSkin == 11 && se == 23005 || weaponSkin == 11 && se == 23006 || weaponSkin == 16 && se == 23006||
                        weaponSkin == 1 && se == 24001  || weaponSkin == 6 && se == 24001|| weaponSkin == 12 && se == 24002||
                        weaponSkin == 10 && se == 24002 || weaponSkin == 18 && se == 24003|| weaponSkin == 11&& se == 24003||
                        weaponSkin == 9 && se == 24004  || weaponSkin == 18&& se == 24005||
                        weaponSkin == 12 && se == 24005 || weaponSkin == 1&& se == 24006 || weaponSkin == 17 && se == 24006)) {
                    weaponSkin += 18;
                }
                if (se != 20011 &&se != 20012
                        &&se != 21011&&se != 21012
                        &&se != 22011&&se != 22012
                        &&se!=23008 &&se!=24007
                        &&se!=24008&&se!=23007) {
                    eSkin = ((weaponSkin << 32) | se) + "";
                }

                if ((weaponSkin == 12&& se == 23002)||(weaponSkin == 12 && se == 22001)||(weaponSkin == 12 && se == 22007)||(weaponSkin == 7 && se == 21005)||
                        (weaponSkin == 7 && se == 22005)||(weaponSkin == 7&& se == 22003)||(weaponSkin == 7 && se == 21001)||(weaponSkin == 3 && se == 20002)||
                        (weaponSkin == 1 && se == 20010) || (weaponSkin == 12 && se == 21007) ||(weaponSkin == 1 && se == 20001)||(weaponSkin == 1 && se == 20007)
                        || (weaponSkin == 3 && se == 24004)
                ) {
                    weaponSkin += 18;
                    eSkin = ((weaponSkin << 32) | se) + "";
//                    newGW = Integer.parseInt(String.valueOf(se) + Sepcies_MixDeal.getRoleWeapon2((int) se));
//                    Integer roleWeapon = Sepcies_MixDeal.getRoleWeapon(newGW);
//                    if (roleWeapon != null) {
//                        if (roleWeapon == 0) {
//                            long weapon = newGW % 100;
//                            long se1 = login.getSpecies_id().longValue();
//                            eSkin = ((weapon << 32) | se1) + "|G" + newGW;
//                        } else if (roleWeapon == 1){
//                            eSkin = String.valueOf(newGW);
//                        }
//                    }
                }


                String cb=null;     // 翅膀皮肤
                if (data.getGoodEquip()[12] != null) {
                    Goodstable wing = AllServiceUtil.getGoodsTableService().getGoodsByRgID(data.getGoodEquip()[12]);
                    if (wing != null) {
                        cb = wing.getSkin();
                    }
                }

                login.setSkin(enterGameAction.getskin1(eSkin, data.getPackRecord().getPutTX(), login.getTitle(), cb,0));
                // 群发给所有人
                String sendMes=Agreement.getAgreement().upRoleShowAgreement(GsonUtil.getGsonUtil().getgson().toJson(login.getRoleShow()));
                SendMessage.sendMessageToMapRoles(login.getMapid(),sendMes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 更改装备 **/
    public void ChangeEquip(LoginResult login, GolemDraw golemDraw,int lvl) {
        try {
            Goodstable goods = GameServer.getGood(golemDraw.getId().add(new BigDecimal(lvl)));
            int type = Goodtype.EquipmentType(goods.getType());
            if (type == -1) return;
            RoleData data = RolePool.getRoleData(login.getRole_id());
            BigDecimal equipId = data.getGoodEquip()[type];
            if (equipId != null) {
                Goodstable equip = AllServiceUtil.getGoodsTableService().getGoodsByRgID(equipId);
                if (equip != null) {
                    equip.setValue(goods.getValue());
                    AllServiceUtil.getGoodsTableService().updateGoodRedis(equip);
                    data.CEquip(equip.getRgid(), type, true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 发放召唤兽 **/
    private void providePet(LoginResult login, GolemDraw golemDraw) {
        try {
            String petss = golemDraw.getValue();
            if (StringUtils.isNotBlank(petss)) {
                List<String> pets =  Arrays.asList(petss.split("&"));
//                pets = pets.stream().distinct().collect(Collectors.toList());//去除重复项
                int count = 0;
                for (String p : pets){
                    String[] pp = p.split("=");
                    BigDecimal petid = null;
                    String skillss = null;
                    if (pp.length == 1){
                        petid= new BigDecimal(pp[0]);
                        skillss = null;
                    }else{
                        petid= new BigDecimal(pp[0]);
                        skillss = pp[1];
                    }
                    RoleSummoning pet = GameServer.getPet(petid);
                    if (pet == null) return;
                    pet.setBasishp(pet.getHp());
                    pet.setBasismp(pet.getMp());
                    // 设置忠诚
                    pet.setFaithful(100);
                    pet.setGrade(0);
                    pet.setTurnRount(0);
                    pet.setBone(0);
                    pet.setSpir(0);
                    pet.setPower(0);
                    pet.setSpeed(0);
                    pet.setCalm(0);
                    pet.setDragon(0);
                    pet.setSpdragon(0);
                    pet.setAlchemynum(0);
                    pet.setExp(new BigDecimal(0));
                    pet.setOpenSeal(1);
                    pet.setRoleid(login.getRole_id());

                    String yb = pet.getResistance();
                    if (yb == null|| yb.equals("")) {
                        int p1 = GameServer.random.nextInt(SummonPetAction.kxs.length);
                        int p2 = GameServer.random.nextInt(SummonPetAction.kxs.length);
                        while (p2 == p1) {
                            p2 = GameServer.random.nextInt(SummonPetAction.kxs.length);
                        }
                        pet.setResistance(SummonPetAction.kxs[p1] + "|" + SummonPetAction.kxs[p2]);
                    }
                    AllServiceUtil.getRoleSummoningService().insertRoleSummoning(pet);

                    // 添加技能
                    if (StringUtils.isNotBlank(skillss)) {
                        List<String> skills = Arrays.asList(skillss.split("\\|"));
                        skills = skills.stream().distinct().collect(Collectors.toList());
                        pet.setOpenSeal(skills.size());
                        pet.setSkillData(UsePetAction.skillData(skills));
                        //保留机器人召唤兽的天生技能
                        if (pet.getPetSkills()!=null) {
                            pet.setPetSkills(pet.getPetSkills()+"|"+skillss);
                        }else {
                            pet.setPetSkills(skillss);
                        }
                        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
                    }

                    // 设置一个参战
                    if (Battlefield.random.nextInt(100) <30) {
                        login.setSummoning_id(pet.getSid());
                    }
                    if (login.getSummoning_id()==null&&count==pets.size()-1) {
                        login.setSummoning_id(pet.getSid());
                    }
                    count++;
                    //发放内丹
                    provideneidan(login, pet);
                }
            }
            if (login.getSummoning_id()==null) {
                List<RoleSummoning> pets=AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRoleID(login.getRole_id());
                if (pets != null && pets.size()>0) {
                    login.setSummoning_id(pets.get(0).getSid());
                }
            }



//            RoleSummoning pet = GameServer.getPet(golemDraw.getId());
//            if (pet == null) return;
//            pet.setBasishp(pet.getHp());
//            pet.setBasismp(pet.getMp());
//            // 设置忠诚
//            pet.setFaithful(100);
//            pet.setGrade(0);
//            pet.setTurnRount(0);
//            pet.setBone(0);
//            pet.setSpir(0);
//            pet.setPower(0);
//            pet.setSpeed(0);
//            pet.setCalm(0);
//            pet.setDragon(0);
//            pet.setSpdragon(0);
//            pet.setAlchemynum(0);
//            pet.setNums(new BigDecimal(0));
//            pet.setOpenSeal(1);
//            pet.setRoleid(login.getRole_id());
//
//            String yb = pet.getResistance();
//            if (yb == null|| yb.equals("")) {
//                int p = GameServer.random.nextInt(SummonPetAction.kxs.length);
//                int p2 = GameServer.random.nextInt(SummonPetAction.kxs.length);
//                while (p2 == p) {
//                    p2 = GameServer.random.nextInt(SummonPetAction.kxs.length);
//                }
//                pet.setResistance(SummonPetAction.kxs[p] + "|" + SummonPetAction.kxs[p2]);
//            }
//            AllServiceUtil.getRoleSummoningService().insertRoleSummoning(pet);
//
//            // 添加技能
//            String value = golemDraw.getValue();
//            if (StringUtils.isNotBlank(value)) {
//                List<String> skills = Arrays.asList(value.split("\\|"));
//                skills = skills.stream().distinct().collect(Collectors.toList());
//                pet.setOpenSeal(skills.size());
//                pet.setSkillData(UsePetAction.skillData(skills));
//                pet.setPetSkills(value);
//                AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
//            }
//            // 设置参战
//            login.setSummoning_id(pet.getSid());
//
//            //发放内丹
//            provideneidan(login, pet);
//

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**发放内丹*/
    private void provideneidan(LoginResult login, RoleSummoning roleSummoning) {
        int type = compareValues(roleSummoning.getHp(), roleSummoning.getMp(), roleSummoning.getAp(), roleSummoning.getSp());
        int[] goodIds;
        if (type == 1) {
            goodIds = new int[]{415, 411, 412}; // 凌波, 万佛, 浩然
        } else if (type == 2) {
            goodIds = new int[]{410, 408, 409}; // 梅花，红颜，开天
        } else if (type == 3) {
            goodIds = new int[]{416, 413, 412}; // 隔山, 暗度陈仓, 浩然
        } else {
            goodIds = new int[]{415, 413, 412}; // 凌波, 暗度陈仓, 浩然
        }
        for (int i = 0; i < 3; i++) {
            Goodstable goods = GameServer.getGood(BigDecimal.valueOf(goodIds[i]));
            if (goods != null) {
                goods.setRole_id(login.getRole_id());
                AllServiceUtil.getGoodsTableService().insertGoods(goods);
            }

        }

        List<Goodstable> goodstables = new ArrayList<>();
        String[] ndIds = new String[goodIds.length];
        //替换新的内丹
        for (int i = 0; i < goodIds.length; i++) {
            Goodstable goodstable = GameServer.getGood(BigDecimal.valueOf(goodIds[i]));
            if (goodstable != null) {
                goodstable.setUsetime(1);
                goodstable.setRole_id(login.getRole_id());
                goodstable.setStatus(1);
                AllServiceUtil.getGoodsTableService().insertGoods(goodstable);
                ndIds[i] = goodstable.getRgid().toString();
                goodstables.add(goodstable);
            }
        }
        String join = ArrayUtil.join(ndIds, "|");
        roleSummoning.setInnerGoods(join);
        AllServiceUtil.getRoleSummoningService().updateRoleSummoning(roleSummoning);
    }
    public static int compareValues(long h, long m, long a, long s) {
        int result = 0;
        if (h >= m && h >= a && h >= s) {
            result = 1;
        }
        if (m >= h && m >= a && m >= s) {
            result = 2;
        }
        if (a >= h && a >= m && a >= s) {
            result = 3;
        }
        if (s >= h && s >= m && s >= a) {
            result = 4;
        }
        return result;
    }
    /** 发放坐骑 **/
    private void provideMount(LoginResult login, GolemDraw golemDraw) {
        try {
            Mount mount=GameServer.getMount(Sepcies_MixDeal.getRace(login.getSpecies_id()), golemDraw.getId().intValue());
            if (mount == null) return;
            mount.setRoleid(login.getRole_id());
            AllServiceUtil.getMountService().insertMount(mount);

            // 添加技能
            String value = golemDraw.getValue();
            if (StringUtils.isNotBlank(value)) {
                List<String> skills = Arrays.asList(value.split("\\|"));
                skills = skills.stream().distinct().collect(Collectors.toList());
                for (int i = 0; i < skills.size(); i++) {
                    MountSkill mountSkill = new MountSkill();
                    mountSkill.setMid(mount.getMid());
                    mountSkill.setSkillname(skills.get(i));
                    mount.getMountskill().add(mountSkill);
                }
                AllServiceUtil.getMountService().updateMountRedis(mount);
            }
            // 设置乘骑
            login.setMount_id(mount.getMountid());
            RolePool.getRoleData(login.getRole_id()).setMid(mount.getMid());
            SendMessage.sendMessageToMapRoles(login.getMapid(),Agreement.getAgreement().upRoleShowAgreement(GsonUtil.getGsonUtil().getgson().toJson(login.getRoleShow())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int[] flyIds = {1, 6, 11, 16, 21, 26};
    /** 发放飞行器 **/
//    private void provideFly(LoginResult login) {
//        int flyId = flyIds[GameServer.random.nextInt(flyIds.length)];
//        Fly fly = GameServer.getAllFly().get(flyId);
//        if (fly == null) {
//            return;
//        }
//        Gson gson = new Gson();
//        fly = gson.fromJson(gson.toJson(fly), Fly.class);
//
//        if (fly != null) {
//            FlyConfig flyConfig = GameServer.getFlyConfig();
//            fly.setRoleid(login.getRole_id());
//            fly.setFlyLevel(1);
//            fly.setCurrFlyLevel(0);
//            fly.setLdz(flyConfig.getLdzList().get(fly.getStairs() - 1));
//            fly.setCurrLdz(flyConfig.getInitLdz());
//            fly.setFuel(9999);
//            fly.setSkill(null);
//            AllServiceUtil.getFlyService().insertFly(fly);
//
//            int zs = BattleMixDeal.lvltrue(login.getGrade());
//            if (zs > 0) {
//                flyUpLvl(fly, zs + 1);
//            }
//        }
//    }

    /** 飞行器升级 **/
//    private void flyUpLvl(Fly fly, int lvl) {
//        int type = fly.getFlyId() / 5;
//        Fly fly1 = GameServer.getAllFly().get(type * 5 + lvl + 1);
//        fly.setFlyId(fly1.getFlyId());
//        fly.setFlySpeed(fly1.getFlySpeed());
//        fly.setStairs(fly1.getStairs());
//        fly.setSkin(fly1.getSkin());
//        fly.setName(fly1.getName());
//        AllServiceUtil.getFlyService().updateFly(fly);
//    }

//    private void flyUpLvl(GolemBean golemBean, int lvl) {
//        List<Fly> flies = AllServiceUtil.getFlyService().selectFlyByRoleID(golemBean.getRoleId());
//        if (flies != null) {
//            LoginResult loginResult = golemBean.getLoginResult();
//            for (int i = 0; i < flies.size(); i++) {
//                Fly fly = flies.get(i);
//                flyUpLvl(fly, lvl);
//                if (loginResult.getFly_id() != null && loginResult.getFly_id().compareTo(fly.getFid()) == 0) {
//                    if (loginResult.getRoleShow().getFlyType() == 1) {
//                        loginResult.getRoleShow().setFly_id(fly.getFlyId());
//
//                        loginResult.getRoleShow().setFlyType(1);
//                        loginResult.getRoleShow().setFlyY(150);
//                        loginResult.getRoleShow().setFlySpeed(BigDecimal.valueOf(0.24).multiply(BigDecimal.valueOf(fly.getFlySpeed())).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
//                        SendMessage.sendMessageToMapRoles(loginResult.getMapid(), Agreement.getAgreement().upRoleShowAgreement(GsonUtil.getGsonUtil().getgson().toJson(loginResult.getRoleShow())));
//                    }
//                }
//            }
//        }
//    }

    /** 清理背包 **/
    private void cleanUpPack(GolemBean golemBean) {
        try {
            List<Goodstable> goodsTables = AllServiceUtil.getGoodsTableService().getGoodsByRoleID(golemBean.getRoleId());
            for (Goodstable goodsTable : goodsTables) {
                // 穿戴过滤
                if (goodsTable.getStatus() != null&&goodsTable.getStatus()!=0) {
                    continue;
                }
                // 可摆摊物品过滤
//                if (GameServer.getGolemConfig().getPetPrice(goodsTable.getGoodsid()) != null) {
//                    continue;
//                }
                AllServiceUtil.getGoodsTableService().deleteGoodsByRgid(goodsTable.getRgid());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 初始化机器人角色 **/
    private LoginResult initGolemRoleInfo(String areaName, BigDecimal userId, String serverMe) {
        LoginResult createRole = new LoginResult();
        createRole.setServerMeString(serverMe);
        createRole.setUser_id(userId);
        createRole.setRolename(randomRoleName());
        createRole.setTitle(areaName + "一员");


        int zz=5;
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure1 = s.get(1);
        if (configure1.getLzjskg()!=null && !configure1.getLzjskg().equals("")) {//读取种族数
            zz=Integer.parseInt(configure1.getLzjskg());
        }
        // 设置角色信息
        BigDecimal speciesId = randomRole(zz);
        createRole.setSpecies_id(speciesId);
        createRole.setRace_id(BigDecimal.valueOf(Sepcies_MixDeal.getRace(speciesId)));
        createRole.setRace_name(Sepcies_MixDeal.getRaceString(speciesId));
        createRole.setLocalname(Sepcies_MixDeal.getLocalName(speciesId.intValue()));
        createRole.setSex(Sepcies_MixDeal.getSex(speciesId) == 0 ? "女" : "男");

        return createRole;
    }

    /** 获取随机角色 **/
    private BigDecimal randomRole(int roleNum) {
        BigDecimal speciesId = BigDecimal.ZERO;
        int index = Battlefield.random.nextInt(roleNum) + 1;
        switch (index){
            case 1: speciesId = BigDecimal.valueOf(20001 + Battlefield.random.nextInt(6));break;
            case 2: speciesId = BigDecimal.valueOf(21001 + Battlefield.random.nextInt(6));break;
            case 3: speciesId = BigDecimal.valueOf(22001 + Battlefield.random.nextInt(6));break;
            case 4: speciesId = BigDecimal.valueOf(23001 + Battlefield.random.nextInt(6));break;
            case 5: speciesId = BigDecimal.valueOf(24001 + Battlefield.random.nextInt(6));break;
        }
        return speciesId;
    }

    /** 获取随机角色名称 **/
    private String randomRoleName() {
        String roleName = AutoNameUtil.autoSurAndName();
        int size = 0;
        while (true) {
            RoleTable role = AllServiceUtil.getRoleTableService().selectRoleTableByRoleName(roleName);
            if (role == null) {break;}
            if (size < 100) {
                roleName = AutoNameUtil.autoSurAndName();
            } else {
                String tempName = AutoNameUtil.autoSurAndName();
                roleName = tempName.substring(0, tempName.length() - 1);//随风改假人名字
            }
            size++;
        }
        return roleName;
    }

    /** 上架处理 **/
//    private void stall(GolemBean golemBean, GolemConfig config, Stall stall) {
//        // 降落
////        Integer flyType = golemBean.getLoginResult().getRoleShow().getFlyType();
//        Integer flyType = golemBean.getLoginResult().getRoleShow().getFly_id();//随风改——飞行判定
//        if (flyType != null && flyType == 1) {
//            golemBean.flyAction(2,golemBean.getLoginResult().getTurnAround());
//        }
//
//        stall.setGoodstables(getGoodsCommoditys(golemBean, config, 24));
//        stall.setPets(getPetCommoditys(golemBean, config, 5));
//        // 摊位调整
//        stall.setX(stall.getX() - 50);
//        stall.setY(stall.getY() - 120);
//        stall(golemBean, stall, StallPool.OFF);
//    }

    /** 摆摊处理 出摊、收摊**/
    private void stall(GolemBean golemBean, Stall stall, int state) {
        try {
            LoginResult loginResult = golemBean.getLoginResult();
            if (state == StallPool.OFF) {
                StallPool.getPool().addStall(stall, GameServer.getRoleNameMap().get(loginResult.getRolename()));
                loginResult.setBooth_id(new BigDecimal(stall.getId()));
                System.out.println(golemBean.getRoleName() + ":开始摆摊");
            } else if (state == StallPool.NO) {
                // 收摊
                int boothId = loginResult.getBooth_id().intValue();
                loginResult.setBooth_id(null);
                StallPool.getPool().RetreatStall(boothId);
                golemBean.setStallState(-1);
                System.out.println(golemBean.getRoleName() + ":收摊");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 摆摊处理验证 **/
    private Stall stallValidate(GolemBean golemBean, GolemConfig config) {
        Stall stall = golemBean.getStall();
        if (stall == null) {
            if (golemBean.getStallState() == 0) {
                golemBean.setStallState(1);

                if (stallConditionValidate(golemBean, config)) {
                    golemBean.setStall(stall = new Stall());
                    stall.setState(StallPool.PREPARE);
                    stall.setRoleid(golemBean.getRoleId());
                    stall.setRole(golemBean.getRoleName());
                    stall.setStall(golemBean.getRoleName() + "的摊位");
                    Point point = config.getStallRandomPoint();
                    stall.setMapid(point.getMapId());
                    stall.setX(point.getX());
                    stall.setY(point.getY());
                }
            }
        } else if (golemBean.getStallTime() != null) { // 正在摆摊
            if (golemBean.getStallTime() <= System.currentTimeMillis()) {// 摆摊过时
                stall(golemBean, null, StallPool.NO);
                golemBean.setStallTime(null);
                golemBean.setStall(null);

                // 飞行
                golemBean.flyAction(1,golemBean.getLoginResult().getTurnAround());
            }
        }
        return stall;
    }

    /** 创建道具商品 **/
    private Commodity[] getGoodsCommoditys(GolemBean golemBean, GolemConfig config, Integer size) {
        List<Commodity> commodities = new ArrayList<>();
        List<Goodstable> roleGoods = AllServiceUtil.getGoodsTableService().getGoodsByRoleID(golemBean.getRoleId());
        for (int i = 0; i < roleGoods.size(); i++) {
            Commodity commodity = stallUpdateValidate(golemBean, config, roleGoods.get(i));
            if (commodity !=null) {
                commodities.add(commodity);
            }
        }
        if (size == null) {
            size = commodities.size();
        }
        return commodities.toArray(new Commodity[size]);
    }

    /** 创建物品商品 **/
    private Commodity[] getPetCommoditys(GolemBean golemBean, GolemConfig config, Integer size) {
        List<Commodity> commodities = new ArrayList<>();
        List<RoleSummoning> rolePets = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRoleID(golemBean.getRoleId());
        for (int i = 0; i < rolePets.size(); i++) {
            Commodity commodity = stallUpdateValidate(golemBean, config, rolePets.get(i));
            if (commodity !=null) {
                commodities.add(commodity);
            }
        }

        if (size == null) {
            size = commodities.size();
        }
        return commodities.toArray(new Commodity[size]);
    }

    /** 物品上架条件验证 **/
    private Commodity stallUpdateValidate(GolemBean golemBean, GolemConfig config, Goodstable goodstable) {
        // 穿戴过滤
        if (goodstable.getStatus() != null&&goodstable.getStatus()!=0) {
            return null;
        }
        // 不可交易过滤
        if (AnalysisString.isJY(goodstable)) {
            return null;
        }
        BigDecimal money = null;
        if (config != null) {
//            money = config.getPetPrice(goodstable.getGoodsid());
            if (money == null) {
                return null;
            }
        } else {
            money = BigDecimal.valueOf(1);
        }
        Commodity commodity = new Commodity();
        commodity.setGood(goodstable);
        commodity.setMoney(money.longValue());
        commodity.setCurrency("金钱");
        return commodity;
    }

    /** 召唤兽上架条件验证 **/
    private Commodity stallUpdateValidate(GolemBean golemBean, GolemConfig config, RoleSummoning pet) {
        // 参战过滤
        if (golemBean.getLoginResult().getSummoning_id().compareTo(pet.getSid()) == 0) {
            return null;
        }

        BigDecimal money = null;
        if (config != null) {
//            money = config.getPetPrice(new BigDecimal(pet.getSummoningid()));
            if (money == null) {
                return null;
            }
        } else {
            money = BigDecimal.valueOf(1);
        }
        Commodity commodity = new Commodity();
        commodity.setPet(pet);
        commodity.setMoney(money.longValue());
        commodity.setCurrency("金钱");
        return commodity;
    }

    /** 摆摊条件验证 **/
    private boolean stallConditionValidate(GolemBean golemBean, GolemConfig config) {
        try {
            String condition = config.get("摆摊条件");
            if (StringUtils.isNotBlank(condition)) {
                String[] vals = condition.split("\\|");
                for (int i = 0; i < vals.length; i++) {
                    String[] vs = vals[i].split("=");
                    if (vs.length != 2) {
                        continue;
                    }

                    switch (vs[0]) {
                        case "最小等级":
                            int minLvl = AnalysisString.lvldirection(vs[1]); // 所需等级
                            if (golemBean.getGrade() >= minLvl) {
                                break;
                            }
                            return false;
                        case "最大等级":
                            int maxLvl = AnalysisString.lvldirection(vs[1]); // 所需等级
                            if (golemBean.getGrade() <= maxLvl) {
                                break;
                            }
                            return false;
                        case "最小可售物品数量":
                            int minGoodsSize = getGoodsCommoditys(golemBean, config, null).length;
                            if (minGoodsSize >= Integer.parseInt(vs[1])) {
                                break;
                            }
                            return false;
                        case "最小可售召唤兽数量":
                            int minPetSize = getPetCommoditys(golemBean, config, null).length;
                            if (minPetSize >= Integer.parseInt(vs[1])) {
                                break;
                            }
                            return false;
                        case "最小物品数量":
                            int goodsSize = getGoodsCommoditys(golemBean, null, null).length;
                            if (goodsSize >=Integer.parseInt(vs[1])) {
                                break;
                            }
                            return false;
                        case "最小召唤兽数量":
                            int petSize = getPetCommoditys(golemBean, null, null).length;
                            if (petSize >= Integer.parseInt(vs[1])) {
                                break;
                            }
                            return false;
                        case "概率":
                            if (GameServer.random.nextInt(100) < Integer.parseInt(vs[1])) {
                                break;
                            }
                            return false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void AllGolemDown() {
        for (GolemBean golemBean : loginGolems.values()) {
            outFighting(golemBean.getLoginResult());
            if (golemBean.getScript() != null) {
                golemBean.getScript().clear2();//重置脚本
            }
            RefreshMonsterTask.taskReset(golemBean.getCtx(), golemBean.getLoginResult(), 1);
            deletGolem(golemBean);
        }
        loginGolems.clear(); // 清空 loginGolems
        reset();
    }


    /** 机器人下线 **/
    private void deletGolem(GolemBean golemBean) {
        if (golemBean == null) return;
        GolemActive active = golemBean.getTask();
        if (active == null) {
            active = golemBean.getNewTask(0, 1, 2);
        }
        if (active != null) {
            TeamBean teamBean = golemBean.getTeamBean();
            if (teamBean != null && teamBean.getTeamName().equals(golemBean.getRoleName())) {
                removeTaskTeams(teamBean.getTeamId(), active);
            }
            System.out.println("机器人异常下线" + golemBean.getRoleName());
        }
        loginGolems.remove(golemBean.getRoleName());
        GameServer.userDown(golemBean.getCtx());
    }

    private class MyChannelHandlerContext implements ChannelHandlerContext {

        private String name;
        public MyChannelHandlerContext(String name) {this.name = name;}

        @Override
        public Channel channel() {
            return null;
        }

        @Override
        public EventExecutor executor() {
            return null;
        }

        @Override
        public String name() {
            return null;
        }

        @Override
        public ChannelHandler handler() {
            return null;
        }

        @Override
        public boolean isRemoved() {
            return false;
        }

        @Override
        public ChannelHandlerContext fireChannelRegistered() {
            return null;
        }

        @Override
        public ChannelHandlerContext fireChannelUnregistered() {
            return null;
        }

        @Override
        public ChannelHandlerContext fireChannelActive() {
            return null;
        }

        @Override
        public ChannelHandlerContext fireChannelInactive() {
            return null;
        }

        @Override
        public ChannelHandlerContext fireExceptionCaught(Throwable cause) {
            return null;
        }

        @Override
        public ChannelHandlerContext fireUserEventTriggered(Object evt) {
            return null;
        }

        @Override
        public ChannelHandlerContext fireChannelRead(Object msg) {
            return null;
        }

        @Override
        public ChannelHandlerContext fireChannelReadComplete() {
            return null;
        }

        @Override
        public ChannelHandlerContext fireChannelWritabilityChanged() {
            return null;
        }

        @Override
        public ChannelFuture bind(SocketAddress localAddress) {
            return null;
        }

        @Override
        public ChannelFuture connect(SocketAddress remoteAddress) {
            return null;
        }

        @Override
        public ChannelFuture connect(SocketAddress remoteAddress, SocketAddress localAddress) {
            return null;
        }

        @Override
        public ChannelFuture disconnect() {
            return null;
        }

        @Override
        public ChannelFuture close() {
            return null;
        }

        @Override
        public ChannelFuture deregister() {
            return null;
        }

        @Override
        public ChannelFuture bind(SocketAddress localAddress, ChannelPromise promise) {
            return null;
        }

        @Override
        public ChannelFuture connect(SocketAddress remoteAddress, ChannelPromise promise) {
            return null;
        }

        @Override
        public ChannelFuture connect(SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) {
            return null;
        }

        @Override
        public ChannelFuture disconnect(ChannelPromise promise) {
            return null;
        }

        @Override
        public ChannelFuture close(ChannelPromise promise) {
            return null;
        }

        @Override
        public ChannelFuture deregister(ChannelPromise promise) {
            return null;
        }

        @Override
        public ChannelHandlerContext read() {
            return null;
        }

        @Override
        public ChannelFuture write(Object msg) {
            return null;
        }

        @Override
        public ChannelFuture write(Object msg, ChannelPromise promise) {
            return null;
        }

        @Override
        public ChannelHandlerContext flush() {
            return null;
        }

        @Override
        public ChannelFuture writeAndFlush(Object msg, ChannelPromise promise) {
            return null;
        }

        @Override
        public ChannelFuture writeAndFlush(Object msg) {
            return null;
        }

        @Override
        public ChannelPromise newPromise() {
            return null;
        }

        @Override
        public ChannelProgressivePromise newProgressivePromise() {
            return null;
        }

        @Override
        public ChannelFuture newSucceededFuture() {
            return null;
        }

        @Override
        public ChannelFuture newFailedFuture(Throwable cause) {
            return null;
        }

        @Override
        public ChannelPromise voidPromise() {
            return null;
        }

        @Override
        public ChannelPipeline pipeline() {
            return null;
        }

        @Override
        public ByteBufAllocator alloc() {
            return null;
        }

        @Override
        public <T> Attribute<T> attr(AttributeKey<T> key) {
            return null;
        }

        @Override
        public <T> boolean hasAttr(AttributeKey<T> key) {
            return false;
        }
    }


    public static List<Gang> GolemGangs;//机器人帮派集合
    /**
     * 机器人帮派查询
     * @return
     */
    public static List<Gang> GolemGangs(){
        if (GolemGangs==null||GolemGangs.size()==0) {
            GolemGangs = new ArrayList<>();

            String[] bangs1 = null;
            GolemConfig config = GameServer.getGolemConfig();
            if (config.get("帮派") != null) {
                bangs1 = config.get("帮派").split("\\|");
                //需要建立的帮派集合
                for (int j = 0; j < bangs1.length; j++) {
                    String gangname = bangs1[j].split("&")[0];
                    Gang roleGang = AllServiceUtil.getGangService().findGangByGangName(gangname);
                    if (roleGang != null) {
                        GolemGangs.add(roleGang);
                    }
                }
            }
        }
        return GolemGangs;
    }
    /**
     * 机器人帮战队伍
     * @param golems
     * @return
     */
    private Map<Integer, List<GolemBean>> groupGolemsByGang(List<GolemBean> golems) {

        List<Integer> bang = new ArrayList<>();
        List<Gang> GolemBangs = GolemGangs();
        if (!GolemBangs.isEmpty()) {
            for (Gang gang : GolemBangs) {
                bang.add(gang.getGangid().intValue());
            }
        }
        Map<Integer, List<GolemBean>> groupedGolems = new HashMap<>();
        // 按帮派分组
        if (!bang.isEmpty()) {
            for (Integer groupKey : bang) {
                for (GolemBean golem : golems) {
                    if (golem.getLoginResult().getGang_id().intValue() == groupKey) {
                        groupedGolems.computeIfAbsent(groupKey, k -> new ArrayList<>()).add(golem);
                    }

                }
            }
        }
        return groupedGolems;
    }



    /**
     * 距离大于500
     * @param X
     * @param Y
     * @return
     */
    public boolean Than500(int X, int Y) {
        return Math.abs(X - Y) > 500;
    }
    /**
     * 距离大于200
     * @param X
     * @param Y
     * @return
     */
    public boolean Than200(int X, int Y) {
        return Math.abs(X - Y) > 200;
    }
    /**
     * 距离大于100
     * @param X
     * @param Y
     * @return
     */
    public boolean Than100(int X, int Y) {
        return Math.abs(X - Y) > 100;
    }


    /**
     * 阵营获取
     * @param bangFight
     * @param roleInfo
     * @param type 1营地 2点塔 3炮台 4挑战
     * @return
     */
    public static PathPoint BangCamp(BangFight bangFight, LoginResult roleInfo,int type) {

        PathPoint Cmap = new PathPoint();
        boolean is = false;
        int gangid = roleInfo.getGang_id().intValue();
        if (gangid !=0) {
            is=bangFight.iscamp(BigDecimal.valueOf(gangid));//判断机器人阵营
        }

        if (type == 1) {
            if (is) {
                Cmap.setX(Battlefield.random.nextInt(300)+20);
                Cmap.setY(Battlefield.random.nextInt(180)+1650);
            } else {
                Cmap.setX(Battlefield.random.nextInt(350)+2085);
                Cmap.setY(Battlefield.random.nextInt(300)+100);
            }
        } else if ( type == 2 ) {
            Cmap.setX(Battlefield.random.nextInt(300)+800);
            Cmap.setY(Battlefield.random.nextInt(180)+1200);
        } else if ( type == 3 ) {
            Cmap.setX(Battlefield.random.nextInt(150)+300);
            Cmap.setY(Battlefield.random.nextInt(150)+300);
        } else if ( type == 4 ) {
            Cmap.setX(Battlefield.random.nextInt(170)+2300);
            Cmap.setY(Battlefield.random.nextInt(170)+1630);
        }

        return Cmap;
    }

    /**
     * 判断是否同一帮派
     * @param teamRole
     * @param bangid
     * @return
     */
    public boolean isValidTeamMember(TeamRole teamRole, int bangid) {
        GolemBean golem = getGolemByName(teamRole.getName());
        LoginResult loginResult = getLoginResultFromGolemOrContext(golem, teamRole.getName());

        return loginResult != null
                && teamRole.getState() >= 0
                && loginResult.getGang_id().intValue() == bangid;
    }

    /**
     * 判断机器人还是玩家
     * @param golem
     * @param roleName
     * @return
     */
    private LoginResult getLoginResultFromGolemOrContext(GolemBean golem, String roleName) {
        if (golem != null) {
            return golem.getLoginResult();
        } else {
            ChannelHandlerContext ctx = GameServer.getRoleNameMap().get(roleName);
            return ctx != null ? GameServer.getAllLoginRole().get(ctx) : null;
        }
    }


    public void DeleteGangTeams(int gangid){
        if (bangteams5 != null) {
            bangteams5.remove(gangid);
            if (bangteams5.size() == 0) {
                bangteams5 = null;
            }
        }
    }

    public ConcurrentHashMap<String, GolemBean> getLoginGolems() {
        return loginGolems;
    }

    public void setLoginGolems(ConcurrentHashMap<String, GolemBean> loginGolems) {
        this.loginGolems = loginGolems;
    }

    /**
     * 解散机器人所有队伍
     */
    public void RemoveGolems(){
        if (loginGolems != null) {
            for (GolemBean golem : loginGolems.values()) {
                ///////设置离队
                if (golem.getTeamBean() != null) {
//                    disbandTeam(golem);

                    TeamBean teamBean = golem.getTeamBean();
                    if (golem.getRoleName().equals(teamBean.getTeamName())) {
                        // 是队长把其他队员踢出
                        List<TeamRole> teams = teamBean.getTeams();
                        for (int i = 1; i < teams.size(); i++) {
                            teamBean.removeTeamRole(teams.get(i).getRoleId());
                        }
                    } else {
                        // 离开队伍
                        teamBean.removeTeamRole(golem.getRoleId());
                    }
                    golem.getLoginResult().setTeamInfo(null);
//                    golem.getLoginResult().setTroop_id(null);//解除队伍关键
                }
                ///////设置离队
            }
        }
    }
    public static String CreatBang(){
        GolemConfig config = GameServer.getGolemConfig();
        String[] bangs1 = null;
        if (!config.get("帮派").equals("")&&config.get("帮派")!=null) {
            bangs1 = config.get("帮派").split("\\|");
        }
        if (bangs1 != null) {
//            List<UserTable> userTables = AllServiceUtil.getUserTableService().selectGolemUser();
            //需要建立的帮派集合
            for (int j = 0; j < bangs1.length; j++) {
                String gangname = bangs1[j].split("&")[0];
//                // 判断帮派名字是否存在
                Gang roleGang = AllServiceUtil.getGangService().findGangByGangName(gangname);
                if (roleGang!=null) {
                    return gangname+"帮派名称已存在";
                }
                for (Map.Entry<String, GolemBean> entry : loginGolems.entrySet()) {
                    String key = entry.getKey(); // 获取键
                    GolemBean golemBean = entry.getValue(); // 获取值，即GolemBean对象
                    // 现在可以使用golemBean执行你需要的操作

                    ChannelHandlerContext ctx2 = golemBean.getCtx();
                    if (ctx2 != null) {
                        LoginResult roleInfo = golemBean.getLoginResult();
                        if (roleInfo.getGang_id().compareTo(BigDecimal.ZERO)==0) {//如果机器人没帮派
                            Gang gang = new Gang();
                            gang.setGangname(gangname);
                            gang.setIntroduction(bangs1[j].split("&")[1]);
                            gang.setBuilder(BigDecimal.valueOf(1));
                            gang.setProperty(BigDecimal.valueOf(1));
                            gang.setGangnumber(new BigDecimal(1));//成员
                            gang.setGanggrade(new BigDecimal(5));//帮派等级
                            gang.setFounder(roleInfo.getRolename());//创始人
                            gang.setGangbelong(roleInfo.getRolename());//帮主
                            boolean isSuccess = AllServiceUtil.getGangService().createGang(gang);
                            if (isSuccess) {//创建成功
                                GangUtil.addGangDomain(gang, roleInfo.getRole_id(), ctx2);//成员上线
                                RoleTable roleTable = new RoleTable(0, roleInfo);
                                roleTable.setGang_id(gang.getGangid());
                                roleTable.setGangpost("帮主");
                                roleTable.setGangname(gang.getGangname());
                                AllServiceUtil.getRoleTableService().updateGang(roleTable);

                                //发送调整
                                String sendMes = Agreement.getAgreement().upRoleShowAgreement(GsonUtil.getGsonUtil().getgson().toJson(roleInfo.getRoleShow()));
                                SendMessage.sendMessageToMapRoles(ctx2, roleInfo.getMapid(), sendMes);
                                GangChangeBean gangChangeBean = new GangChangeBean(roleInfo, "创建帮派成功");
                                SendMessage.sendMessageByRoleName(roleInfo.getRolename(), Agreement.GangChangeAgreement(GsonUtil.getGsonUtil().getgson().toJson(gangChangeBean)));


                                List<Gang> gangs = GangUtil.getGangs();
                                if (gangs == null) {
                                    gangs = AllServiceUtil.getGangService().findAllGang();
                                }

                                if (gangs != null) {
                                    for (Gang gang11 : gangs) {
                                        if (gang11.getGangname().equals(gangname)) {
                                            roleInfo.setGang_id(gang11.getGangid());
                                            break;
                                        }
                                    }
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
        return "创建帮派成功";
    }

    public static String addbangfight() {
        BangBattlePool pool = BangBattlePool.getBangBattlePool();
//        if (pool.isEnroll()) {
        //获取机器人帮派
        List<Gang> gangs = GolemGangs();
        if (gangs != null) {
            for (Gang gang : gangs) {
                BangPoints bangPoints = pool.getBangPoints(gang.getGangid());
                if (bangPoints == null) {
                    if (pool.group.getlist().size() < 16) {
                        pool.group.addlist(new BangPoints(gang.getGangid()));
                        BangFileSystem.getBangFileSystem().DataSaving(pool);
                    } else {
                        return "名额已经满了";
                    }
                }else{
                    return "帮派已经报过名了";
                }
            }
            return "报名成功";
        } else {
            return "没有帮派";
        }
//        } else {
//            return "不在报名时间段内";
//        }
    }
    public static String AddBang() {
        List<Gang> GolemGangs = GolemGangs();
        int conut = 0;
        if (GolemGangs != null && GolemGangs.size() > 0) {
            //机器人分配到帮派
            List<GolemBean> loginGolemsss = new ArrayList<>();

            for (String key : loginGolems.keySet()) {
                GolemBean golemBean = loginGolems.get(key);
                if (golemBean.getGangid() == null || golemBean.getGangid().compareTo(BigDecimal.ZERO) == 0) {
                    loginGolemsss.add(golemBean);
                }
            }
            if (loginGolemsss.size() < 3) {
                return "机器人加帮人数不足";
            }

            int n = GolemGangs.size();
            int m = loginGolemsss.size();
            // 计算每个帮派平均应分配的人数及余数
            int averagePeoplePerTeam = m / n;
            int extraPeople = m % n;

            // 初始化帮派对应的人员列表
            List<List<GolemBean>> GangMembers = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                GangMembers.add(new ArrayList<>());
            }


            // 遍历人员列表，分配人员到帮派
            Random random = new Random(); // 用于随机分配多余的人
            for (int i = 0; i < m; i++) {
                int teamIndex = i / averagePeoplePerTeam;
                // 如果当前是需要额外分配人的位置，则随机选择一个队伍
                if (i >= averagePeoplePerTeam * n && extraPeople > 0) {
                    teamIndex = random.nextInt(n);
                    extraPeople--;
                }
                GangMembers.get(teamIndex).add(loginGolemsss.get(i));
            }
            //加入申请并接受
            for (int i = 0; i < n; i++) {
                BigDecimal gangid = GolemGangs.get(i).getGangid();
                String gangname = GolemGangs.get(i).getGangname();
                for (int j = 0; j < GangMembers.get(i).size(); j++) {
                    LoginResult roleInfo = GangMembers.get(i).get(j).getLoginResult();
                    if (roleInfo.getGang_id().compareTo(BigDecimal.ZERO) == 0) {
                        //申请
//                        Gangapply gangapply = new Gangapply();
//                        gangapply.setGangid(gangid);
//                        gangapply.setRoleid(roleInfo.getRole_id());
//                        AllServiceUtil.getGangapplyService().insertIntGangapple(gangapply);

                        //接受加入
                        RoleTable roleTable = new RoleTable(0, roleInfo);

                        GangDomain gangDomain = GangUtil.getGangDomain(gangid);
//                        AllServiceUtil.getGangapplyService().deleteGangappleAll(roleInfo.getRole_id());
                        gangDomain.addGangRole();
                        roleTable.setGang_id(gangid);
                        roleTable.setGangpost("帮众");
                        roleTable.setGangname(gangname);
                        AllServiceUtil.getRoleTableService().updateGang(roleTable);
                        ChannelHandlerContext ctxA = GangMembers.get(i).get(j).getCtx();
                        if (ctxA != null) {
                            gangDomain.upGangRole(roleInfo.getRole_id(), ctxA);
                        }
                        roleInfo.setGang_id(gangid);
                        roleInfo.setGangpost("帮众");
                        roleInfo.setGangname(gangname);
                        //发送通知完成职位调整
                        if (ctxA != null) {
                            String sendMes = Agreement.getAgreement().upRoleShowAgreement(GsonUtil.getGsonUtil().getgson().toJson(roleInfo.getRoleShow()));
                            SendMessage.sendMessageToMapRoles(ctxA, roleInfo.getMapid(), sendMes);
                        }
                        GangChangeBean gangChangeBean = new GangChangeBean(roleInfo, "你加入了#G" + gangname);
                        SendMessage.sendMessageToSlef(ctxA, Agreement.GangChangeAgreement(GsonUtil.getGsonUtil().getgson().toJson(gangChangeBean)));
                        conut++;
                    }
                }
            }
        }
        return conut + "个机器人加帮成功";
    }

    public static void ChangeEquid(int lvl){
        try {
            ConcurrentHashMap<String, List<GolemDraw>> golemDrawMap = GameServer.getAllGolemDraw();
            for (String value : golemDrawMap.keySet()) {
                int specifyLvl = AnalysisString.lvldirection(value); // 所需等级
                for (Map.Entry<String, GolemBean> entry : loginGolems.entrySet()) {
                    LoginResult login = entry.getValue().getLoginResult();
                    int currentLvl = login.getGrade();             // 当前等级
                    // 所需等级 > 已领取的最高等级 && 所需等级 <= 当前等级
                    if (specifyLvl <= currentLvl) {
                        List<GolemDraw> golemDraws = golemDrawMap.get(value);
                        for (int i = 0; i < golemDraws.size(); i++) {
                            GolemDraw golemDraw = golemDraws.get(i);
                            // 匹配角色
                            if (golemDraw.isMatching(login.getSpecies_id())) {
                                if ("装备".equals(golemDraw.getType())) {
                                    GameServer.golemServer.ChangeEquip(login, golemDraw, (lvl-1)*100);
                                }
                            }
                        }
                    }
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}

