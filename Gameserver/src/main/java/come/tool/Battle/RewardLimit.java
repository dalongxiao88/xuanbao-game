package come.tool.Battle;

import come.tool.Stall.AssetUpdate;
import org.come.action.chat.FriendChatAction;
import org.come.model.Door;
import come.tool.Scene.Scene;
import org.come.entity.Mount;
import org.come.entity.Lingbao;
import org.come.entity.Goodstable;
import come.tool.Good.DropModel;
import org.come.model.TaskSet;
import come.tool.newTask.Task;
import org.come.model.Dorp;
import org.come.bean.UseCardBean;
import org.come.entity.RoleSummoning;
import come.tool.FightingData.ManData;
import come.tool.Role.RoleData;
import java.util.List;
import com.gl.util.EggUtil;
import org.come.action.sys.ChangeMapAction;
import org.come.bean.ChangeMapBean;
import org.come.protocol.ParamTool;
import org.come.action.IAction;
import come.tool.FightingData.PK_MixDeal;
import come.tool.Scene.SceneUtil;
import come.tool.Role.Hang;
import come.tool.Good.ExpUtil;
import come.tool.Good.UsePetAction;
import org.come.model.TaskProgress;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import come.tool.newTask.TaskUtil;
import org.come.bean.Middle;
import come.tool.newTask.TaskRecord;
import org.come.model.Robots;
import come.tool.Good.DropUtil;
import org.come.action.suit.SuitMixdeal;
import org.come.action.monitor.MonitorUtil;
import org.come.until.AllServiceUtil;
import come.tool.Role.RolePool;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import java.util.ArrayList;
import come.tool.FightingData.Battlefield;
import org.come.server.GameServer;
import org.come.model.Boos;
import com.gl.util.GLUtil;
import come.tool.oneArena.OneArenaNotes;
import come.tool.oneArena.OneArenaBean;
import come.tool.oneArena.OneArenaAction;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

public class RewardLimit
{
    private static ConcurrentHashMap<String, Integer> boosDropMap;//boos掉落
    private static ConcurrentHashMap<Integer, ConcurrentHashMap<BigDecimal, Integer>> rewardMap;
    static String KRD;//枯荣丹
    static String VIP;//VIP
    static String JVIP;//JVIP
    static String HPY;//六脉化神湾
    static String MPY;//玉枢返虚丸
    static String blood;//超级六脉化神丸
    static String Mana;//超级玉枢返虚丸
    
    public static void Reward(BattleEnd battleEnd, BattleData battleData) {
        OneArenaBean arenaBean = null;
        if (battleData.getBattleType() == 101) {
            OneArenaNotes notes = OneArenaAction.OneArenaEnd(battleData.getOneArenaRole1(), battleData.getOneArenaRole2(), (battleEnd.getCamp() == 1) ? 0 : 1);
            if (battleEnd.getCamp() == 1) {
                arenaBean = OneArenaAction.getOneArenaRole(battleData.getOneArenaRole1());
            }
            if (arenaBean == null) {
                arenaBean = new OneArenaBean();
            }
            arenaBean.setNotes(notes);
        }
        if (battleEnd.getCamp() == 1) {
            RewardSL(battleEnd, battleData, battleData.getTeam1(), arenaBean);
        }
        else {
            RewardSB(battleEnd, battleData, battleData.getTeam1(), arenaBean);
        }
        if (battleData.getTeam2().length != 0) {
            if (battleEnd.getCamp() == 2) {
                RewardSL(battleEnd, battleData, battleData.getTeam2(), null);
            }
            else {
                RewardSB(battleEnd, battleData, battleData.getTeam2(), null);
            }
        }
    }
    
    public static void RewardSL(BattleEnd battleEnd, BattleData battleData, String[] teams, OneArenaBean arenaBean) {
        if (battleData.getWssType() != 0) {
            GLUtil.getStringToRole(battleData, teams, battleData.getWssType());
        }
        Boos boos = (battleData.getBoosID() != null) ? GameServer.boosesMap.get(battleData.getBoosID()) : null;
        int sj = (boos != null) ? Battlefield.random.nextInt(teams.length) : -1;
        int doorId = 0;
        int Tsize = teams.length;
        //TODO 装载本次战斗所参与的玩家(元气蛋判定)
        List<RoleData> list = new ArrayList<>();
        if (teams.length != 0) {
            ChannelHandlerContext ctx = GameServer.getRoleNameMap().get(teams[0]);
            LoginResult loginResult = (ctx != null) ? GameServer.getAllLoginRole().get(ctx) : null;
            RoleData roleData = (loginResult != null) ? RolePool.getRoleData(loginResult.getRole_id()) : null;
            if (roleData != null) {
                Tsize += roleData.PSize();
            }
        }
        for (int i = teams.length - 1; i >= 0; --i) {
            battleEnd.clean();
            doorId = 0;
            if (battleData.getParticipantlist().remove(teams[i])) {
                ManData manData = battleData.getBattlefield().getBattleEndData(teams[i]);
                ChannelHandlerContext ctx2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(teams[i]);
                LoginResult loginResult2 = (ctx2 != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx2)) : null;
                if (manData != null && loginResult2 != null) {
                    RoleData roleData2 = RolePool.getRoleData(loginResult2.getRole_id());
                    list.add(roleData2);
                    //TODO 普通时效药
                    boolean isH = roleData2.getLimit(RewardLimit.HPY) == null;
                    boolean isM = roleData2.getLimit(RewardLimit.MPY) == null;
                    //TODO 月药
                    boolean isHS = roleData2.getLimit(RewardLimit.blood) == null;
                    boolean isMS = roleData2.getLimit(RewardLimit.Mana) == null;
                    if (battleData.getBattlefield().isFightType()) {
                        if (manData.getHp() <= 0) {
                            loginResult2.setHp(new BigDecimal((int)((double)manData.getHp_z() * 0.25)));
                            loginResult2.setMp(new BigDecimal((int)((double)manData.getMp_z() * 0.25)));
                        }
                        //TODO 普通时效药
                        else {
                            loginResult2.setHp(new BigDecimal(isHS ? manData.getHp() : manData.getHp_z()));
                            loginResult2.setMp(new BigDecimal(isMS ? manData.getMp() : manData.getMp_z()));
                        }
                        if (roleData2.getLimit(RewardLimit.blood) == null && roleData2.getLimit(RewardLimit.Mana) == null) {
                            loginResult2.setHp(new BigDecimal(isH ? manData.getHp() : manData.getHp_z()));
                            loginResult2.setMp(new BigDecimal(isM ? manData.getMp() : manData.getMp_z()));
                        }
                    }
                    long goodExp = 0L;
                    RoleSummoning pet = null;
                    if (loginResult2.isGolem()) {
                        loginResult2.setHp(new BigDecimal(manData.getHp_z()));
                        loginResult2.setMp(new BigDecimal(manData.getMp_z()));
                        pet=manData.getPet(false, false,false,false);
                    } else if (battleData.getBattlefield().isFightType()) {
                        UseCardBean limit = roleData2.getLimit(RewardLimit.KRD);
                        if (limit != null) {
                            if (roleData2.getLimit(RewardLimit.VIP) == null&&roleData2.getLimit(RewardLimit.JVIP) == null) {
                                pet = manData.getPet(isH, isM, isHS, isMS);
                                if (pet != null) {
                                    if (!isH && !isM && !isHS && !isMS) {
                                        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
                                    }
                                    battleEnd.upAssetData("P" + pet.getSid() + "=" + pet.getGrade() + "=" + pet.getExp() + "=" + pet.getFriendliness() + "=" + pet.getBasishp() + "=" + pet.getBasismp());
                                }
                            }
                            else {
                                pet = manData.getPet(false, false, false, false);
                                if (pet != null) {
                                    battleEnd.upAssetData("P" + pet.getSid() + "=" + pet.getGrade() + "=" + pet.getExp() + "=" + pet.getFriendliness() + "=" + pet.getBasishp() + "=" + pet.getBasismp());
                                }
                            }
                            pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(new BigDecimal(limit.getValue()));
                        }
                        else if (roleData2.getLimit(RewardLimit.VIP) != null||roleData2.getLimit(RewardLimit.JVIP) != null) {
                            pet = manData.getPet(false, false, false, false);
                        }
                        else {
                            pet = manData.getPet(isH, isM, isHS, isMS);
                        }
                    }
                    boolean isDrop = true;
                    if (battleData.getBattleType() == 4) {
                        if (manData.getMoney() == -1L) {
                            isDrop = false;
                        }
                        else if (manData.getMoney2() != 0L) {
                            if (Battlefield.isV(60.0)) {
                                long money = manData.getMoney2() * 2L;
                                battleEnd.upAssetData("D=" + money);
                                battleEnd.upAssetData("偷钱=" + money);
                                loginResult2.setGold(loginResult2.getGold().add(new BigDecimal(money)));
                                MonitorUtil.getMoney().addD(money, 0);
                                if (money >= 10000000L) {
                                    SuitMixdeal.Stealing(loginResult2.getRolename(), money);
                                }
                                battleEnd.upAssetMsg("你获得金钱" + money);
                                MonitorUtil.getDropHM().add(money);
                                isDrop = false;
                            }
                            else {
                                if (manData.getMoney2() > 1000000000L) {
                                    Dorp dorp = GameServer.getDorp("30001");
                                    DropUtil.getDrop4(loginResult2, dorp.getDorpValue(), "", 25, 1.0, null, "", "", "");
                                }
                                else if (manData.getMoney2() >= 500000000L && manData.getMoney2() <= 1000000000L) {
                                    Dorp dorp = GameServer.getDorp("30002");
                                    DropUtil.getDrop4(loginResult2, dorp.getDorpValue(), "", 25, 1.0, null, "", "", "");
                                }
                                else if (manData.getMoney2() >= 100000000L && manData.getMoney2() <= 500000000L) {
                                    Dorp dorp = GameServer.getDorp("30003");
                                    DropUtil.getDrop4(loginResult2, dorp.getDorpValue(), "", 25, 1.0, null, "", "", "");
                                }
                                else if (manData.getMoney2() < 100000000L) {
                                    Dorp dorp = GameServer.getDorp("30004");
                                    DropUtil.getDrop4(loginResult2, dorp.getDorpValue(), "", 25, 1.0, null, "", "", "");
                                }
                                isDrop = false;
                            }
                        }
                    }
                    if (sj == i) {
                        Robots robots = (Robots)GameServer.getAllRobot().get(boos.getBoosrobot());
                        goodExp += DropUtil.getDrop(loginResult2, pet, boos.getDropModel(), (robots != null) ? robots.getUnknow() : null, battleEnd, (double)manData.getExpXS(), 0.0, 0, 25, 0, 0, 0);
                    }
                    double expXS = (double)manData.getExp2XS();
                    int ndXS = 0;
                    Robots robots2 = battleData.getRobots();
                    int size = 0;
                    int BSum = MonitorUtil.getBSum(loginResult2.getRole_id());
                    //队长加成

                     //自动机器人

                    String robotName = "大话精灵";
                    if (i == 0 && teams != null) {
                        int sum = teams.length - 2;
                        if (sum > 0) {}
                        if (battleData.getBattlefield().dropModel != null) {
                            battleData.getBattlefield().dropModel.setTeam(Boolean.TRUE);
                        }
                    }
                    if (robots2 != null) {
                        //自动机器人
                        robotName = robots2.getRobotname();
                        // 记录Robot击杀次数
                        TaskRecord robotsRecord = roleData2.getTaskRecord(robots2.getRobotID());
                        if (robotsRecord == null) {
                            robotsRecord = new TaskRecord(robots2.getRobotID());
                            roleData2.addTaskRecord(robotsRecord);
                        }
                        robotsRecord.addCSum(1);
                        Middle middle = new Middle();
                        String taskComplete = TaskUtil.toTaskRecord(roleData2.getTaskRecordMap());
                        middle.setTaskComplete(taskComplete);
                        String mes = Agreement.getAgreement().MiddleAgreement(GsonUtil.getGsonUtil().getgson().toJson(middle));
                        SendMessage.sendMessageToSlef(ctx2, mes);
                        if (robots2.getDropLimit() == 1) {//需要参与任务才有奖励
                            isDrop = false;
                        }
                        if (battleData.getHJv() > 0) {
                            TaskRecord taskRecord = roleData2.getTaskRecord(6);
                            if (taskRecord != null) {
                                int num = 5;
                                num += taskRecord.getrSum();
                                num -= taskRecord.getcSum();
                                if (num <= 0) {
                                    isDrop = false;
                                }
                            }
                        }
                        if (robots2.getTaskIds() != null && battleData.getBattlefield().yename != null) {
                            int j = robots2.getTaskIds().size() - 1;
                            while (j >= 0) {
                                Task task = roleData2.getTask((int)robots2.getTaskIds().get(j));
                                if (task == null) {
                                    --j;
                                }
                                else {
                                    int part = task.PartFinish(Integer.parseInt(robots2.getRobotid()), 1, "击杀");
                                    if (part == 3) {
                                        ndXS = battleData.getBattlefield().ndXS;
                                        if (ndXS > task.getTaskData().getNd()) {
                                            ndXS = task.getTaskData().getNd();
                                        }
                                        isDrop = true;
                                        StringBuffer buffer = new StringBuffer();
                                        boolean is = task.isFinish();
                                        buffer.append(task.getTaskId());
                                        buffer.append("=");
                                        if (is) {
                                            buffer.append(4);
                                            roleData2.removeTask(task.getTaskId());
                                            TaskUtil.addSumLimit(task.getTaskData(), new LoginResult[] { loginResult2 });
                                            StringBuffer buffer2 = TaskUtil.addTaskL(null, task.getTaskId(), task.getTaskSet());
                                            if (buffer2 != null) {
                                                buffer.append("|");
                                                buffer.append(buffer2);
                                            }
                                            if (task.getTaskData().getDropModel() != null) {
                                                int num2 = roleData2.getTaskWC(task.getTaskData().getTaskSetID());
                                                expXS += task.getTaskSet().XSReward(num2);
                                                goodExp += DropUtil.getDrop(loginResult2, pet, task.getTaskData().getDropModel(), task.getTaskData().getTalk(), battleEnd, (double)manData.getExpXS(), expXS, num2, 23, BSum, battleData.getBattlefield().DropXS, ndXS);
                                            }
                                            //判断是否有下一个任务
                                            int newTaskId = task.getTaskData().getNewTaskId();
                                            if (newTaskId >= 8008 && newTaskId <= 8011) {
                                                Task task2 = TaskUtil.TaskReceive(newTaskId, Tsize, 0, loginResult2, roleData2, buffer, (TaskProgress)task.getProgress().get(0));
                                                if (i == 0 && task2 != null) {
                                                    doorId = task2.getTaskData().getDoorID();
                                                }
                                            }
                                            else if (newTaskId != 0) {
                                                Task task2 = TaskUtil.TaskReceive(newTaskId, Tsize, 0, loginResult2, roleData2, buffer);
                                                if (i == 0 && task2 != null) {
                                                    doorId = task2.getTaskData().getDoorID();
                                                }
                                            }
                                        }
                                        else {
                                            buffer.append(task.getTaskState());
                                            TaskUtil.Progress(task, buffer);
                                        }
                                        battleEnd.setTaskn(buffer.toString());
                                        //自动机器人
                                        if (loginResult2.isGolem() && i == 0) {
                                            GameServer.golemServer.addSumLimit(loginResult2.getRolename(), task.getTaskSetId());
                                        }
                                        break;
                                    }
                                    else {
                                        break;
                                    }
                                }
                            }
                        }
                        TaskSet taskSet = GameServer.getRobotTaskSet(robots2.getRobotID());
                        if (taskSet != null) {
                            size = roleData2.addTaskRecordWC(taskSet.getTaskSetID());
                            if (taskSet.getSumlimit() != 0 && size >= taskSet.getSumlimit()) {
                                isDrop = false;
                            }
                            battleEnd.upTaskn("C" + taskSet.getTaskSetID() + "=L");
                        }
                        if (battleData.getHJv() > 0) {// 幻境试炼
                            if (isDrop) {
                                TaskRecord taskRecord2 = roleData2.getTaskRecord(6);
                                if (taskRecord2 == null) {
                                    taskRecord2 = new TaskRecord(6);
                                    roleData2.addTaskRecord(taskRecord2);
                                }
                                int curr = battleData.getRobots().getRobotID() - 9000;
                                if (taskRecord2.getNewID() < curr) {// 晋级挑战不扣除次数
                                    taskRecord2.setNewID(curr);
                                    if (roleData2.getLoginResult().getHjmax() < curr) {
                                        roleData2.getLoginResult().setHjmax(curr);
                                    }
                                    battleEnd.upTaskn("C6=N" + curr);
                                }
                                else {
                                    taskRecord2.addCSum(1);
                                    battleEnd.upTaskn("C6=L");
                                }
                            }
                            battleEnd.upMsg("幻境试炼挑战成功");
                        }
                        if(robots2.getRobotID()>=998101 && robots2.getRobotID()<=998181) {
                            if((robots2.getRobotID() - 998100)-loginResult2.getDifficultLevel() == 1) {
                                loginResult2.setDifficultLevel(loginResult2.getDifficultLevel()+1);
                                AssetUpdate assetUpdate=new AssetUpdate();
                                assetUpdate.setType(AssetUpdate.USEGOOD);
                                assetUpdate.setDifficultLevel(loginResult2.getDifficultLevel()+"");
//						assetUpdate.updata("T挑战成功");
                                SendMessage.sendMessageToSlef(ctx2,Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                            }
                        }
                        //自动机器人
                        if (loginResult2.isGolem()) {
                            GameServer.golemServer.addSumLimit(loginResult2.getRolename(), robots2.getRobotid(), i == 0);
                        }
                    }
                    if (battleData.getBattlefield().dropModel != null && isDrop) {
                        if (size == 0 && robots2 != null && robots2.isJL()) {
                            ConcurrentHashMap<BigDecimal, Integer> map = get(robots2.getRobotID());
                            Integer num3 = (Integer)map.get(loginResult2.getRole_id());
                            if (num3 == null) {
                                num3 = 0;
                            }
                            else {
                                num3 = (int) num3 + 1;
                            }
                            map.put(loginResult2.getRole_id(), num3);
                            size = (int)num3;
                        }
                        goodExp += DropUtil.getDrop(loginResult2, pet, battleData.getBattlefield().dropModel, (robots2 != null) ? robots2.getUnknow() : null, battleEnd, (double)manData.getExpXS(), expXS, size, 24, BSum, battleData.getBattlefield().DropXS, ndXS, robots2);
                        if (battleData.getBattlefield().dropModel != null) {
                            battleData.getBattlefield().dropModel.setTeam(Boolean.FALSE);
                        }
                        if (battleEnd != null&&battleEnd.getAssetUpdate()!=null) {
                            battleEnd.getAssetUpdate().setPet(pet);
                        }
                    }
                    if (arenaBean != null) {
                        battleEnd.setArenaBean(arenaBean);
                        TaskRecord taskRecord3 = roleData2.getTaskRecord(4);
                        if (taskRecord3 == null) {
                            taskRecord3 = new TaskRecord(4);
                            roleData2.addTaskRecord(taskRecord3);
                        }
                        taskRecord3.addRSum(1);
                        taskRecord3.addCSum(1);
                        battleEnd.upTaskn("C4=R=L");
                        if (arenaBean.getPlace() != 0) {
                            battleEnd.upMsg("你当前竞技场排名:" + arenaBean.getPlace());
                        }
                    }
                    if (battleData.getMonsterBean() != null && battleData.getMonsterBean().getExp() != null) {
                        DropModel dropModel = battleData.getMonsterBean().getExp().getDropModel(teams.length);
                        if (dropModel != null) {
                            battleEnd.upAssetMsg("你参与击杀" + battleData.getMonsterBean().getRobotname() + "瓜分累积经验:" + dropModel.getExp());
                            goodExp += DropUtil.getDrop(loginResult2, pet, dropModel, null, battleEnd, (double)manData.getExpXS(), expXS, size, 24, BSum, 0, 0);
                        }
                    }
                    battleEnd.upAssetData("R" + loginResult2.getGrade() + "=" + loginResult2.getExperience() + "=" + loginResult2.getHp() + "=" + loginResult2.getMp());
                    if (pet != null) {
                        if (battleData.getBattleType() == 1 || battleData.getBattleType() == 2) {
                            if (BSum < 1000) {
                                pet.addqm(50);
                            }
                            int type = 0;
                            if ((long)loginResult2.getMapid() == 3321L && roleData2.getTask(3154) != null) {
                                type = 2;
                            }
                            else if ((long)loginResult2.getMapid() == 3322L && roleData2.getTask(3153) != null) {
                                type = 1;
                            }
                            if (!UsePetAction.PetSkill(pet, ctx2, loginResult2, type)) {
                                AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
                            }
                            if (robots2 != null) {
                                UsePetAction.battleOpenSkillSeal(pet, ctx2, loginResult2);
                                AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
                            }
                        }
                        else if (battleData.getBattleType() == 0) {
                            if (BSum < 1000) {
                                pet.addqm(50);
                            }
                            AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
                        }
                        else if (battleData.getBattlefield().isFightType()) {
                            AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
                        }
                        battleEnd.upAssetData("P" + pet.getSid() + "=" + pet.getGrade() + "=" + pet.getExp() + "=" + pet.getFriendliness() + "=" + pet.getBasishp() + "=" + pet.getBasismp());
                        if (goodExp != 0L && pet.getInnerGoods() != null && !pet.getInnerGoods().equals("")) {
                            String[] vs = pet.getInnerGoods().split("\\|");
                            for (int k = 0; k < vs.length; ++k) {
                                Goodstable goodstable = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(vs[k]));
                                if (goodstable != null && goodstable.getType() == 750L) {
                                    ExpUtil.IncreaseNedanExp(pet, goodstable, battleEnd, goodExp);
                                }
                            }
                        }
                    }
                    if (BSum < 1000 && (battleData.getBattleType() == 0 || battleData.getBattleType() == 1 || battleData.getBattleType() == 2)) {
                        Lingbao lingbao = (roleData2.getLs() != null) ? AllServiceUtil.getLingbaoService().selectLingbaoByID(roleData2.getLs().getId()) : null;
                        if (lingbao != null) {
                            lingbao.setLingbaoqihe(lingbao.getLingbaoqihe() + 20L);
                            int lvl = lingbao.getLingbaolvl().intValue();
                            long exp = lingbao.getLingbaoexe().longValue();
                            long maxexp = ExpUtil.LFExp(lvl);
                            if (exp >= maxexp && lvl != 0 && lvl % 30 == 0) {
                                battleEnd.upMsg("灵宝突破后才可继续升级");
                            }
                            else if (lvl < 200) {
                                battleEnd.upMsg(ExpUtil.LFExp(lingbao, 15L));
                            }
                            AllServiceUtil.getLingbaoService().updateLingbaoRedis(lingbao);
                            battleEnd.upAssetData("L" + lingbao.getBaoid() + "=" + lingbao.getLingbaolvl() + "=" + lingbao.getLingbaoexe() + "=" + lingbao.getLingbaoqihe());
                        }
                        for (int l = 0; l < roleData2.getFs().size() && l < 2; ++l) {
                            Lingbao fabao = AllServiceUtil.getLingbaoService().selectLingbaoByID(((Hang)roleData2.getFs().get(l)).getId());
                            if (fabao != null) {
                                int lvl2 = fabao.getLingbaolvl().intValue();
                                long exp2 = fabao.getLingbaoexe().longValue();
                                long maxexp2 = ExpUtil.LFExp(lvl2);
                                if (exp2 >= maxexp2 && lvl2 != 0 && lvl2 % 30 == 0) {
                                    battleEnd.upMsg("法宝突破后才可继续升级");
                                }
                                else if (lvl2 < 200) {
                                    battleEnd.upMsg(ExpUtil.LFExp(fabao, 15L));
                                    AllServiceUtil.getLingbaoService().updateLingbaoRedis(fabao);
                                    battleEnd.upAssetData("L" + fabao.getBaoid() + "=" + fabao.getLingbaolvl() + "=" + fabao.getLingbaoexe() + "=" + fabao.getLingbaoqihe());
                                }
                            }
                        }
                        Mount mount = (roleData2.getMid() != null) ? AllServiceUtil.getMountService().selectMountsByMID(roleData2.getMid()) : null;
                        if (mount != null) {
                            ExpUtil.MountAddES(battleEnd, mount, 15, 5);
                        }
                    }
                    if (battleData.getBattleType() == 10 && manData.getCamp() == 1) {
                        int[] dailys = loginResult2.getDaily();
                        int n = 0;
                        ++dailys[n];
                        if (dailys[0] >= 8) {
                            doorId = 1;
                            dailys[1] = 1;
                            int n2 = 2;
                            ++dailys[n2];
                            int n3 = 3;
                            ++dailys[n3];
                        }
                        battleEnd.setTaskDaily(loginResult2.upDaily(dailys));
                        battleEnd.upAssetMsg("你获得 #G 1 #Y点PK标识,现在PK标志有 #G " + dailys[0] + " #Y点,PK标志超过4点时进城有概率遇到捕快,8点强制坐牢");
                    }
                    if (battleData.getSceneId() != null) {
                        Scene scene = SceneUtil.getScene((int)battleData.getSceneId());
                        if (scene != null) {
                            int door = 0;
                            if ((int)battleData.getSceneId() == 1011 && PK_MixDeal.isPK(battleData.getBattlefield().BattleType)) {
                                door = scene.battleEnd(battleEnd, loginResult2, null, (i == 0) ? 0 : 1);
                            }
                            else {
                                door = scene.battleEnd(battleEnd, loginResult2, battleData.getMonsterBean(), (i == 0) ? 0 : 1);
                            }
                            if (i == 0 && door != 0) {
                                doorId = door;
                            }
                        }
                    }
                    String msg = Agreement.FightingendAgreement(GsonUtil.getGsonUtil().getgson().toJson(battleEnd));
                    SendMessage.sendMessageToSlef(ctx2, msg);
                    if (doorId == 1) {
                        IAction action = (IAction)ParamTool.ACTION_MAP.get("getout");
                        if (action != null) {
                            action.action(ctx2, loginResult2.getTaskDaily());
                        }
                    }
                    else if (i == 0 && doorId != 0) {
                        Door door2 = GameServer.getDoor(doorId);
                        if (door2 != null) {
                            ChangeMapBean changeMapBean = new ChangeMapBean();
                            changeMapBean.setMapid(door2.getDoormap());
                            String[] vs2 = door2.getDoorpoint().split("\\|");
                            changeMapBean.setMapx(Integer.parseInt(vs2[0]));
                            changeMapBean.setMapy(Integer.parseInt(vs2[1]));
                            ChangeMapAction.ChangeMap(changeMapBean, ctx2);
                        }
                    }
                    if (i == 0 && loginResult2.isGolem()) {
                        GameServer.golemServer.endFighting(teams[i], "成功击杀【" + robotName + "】");
                    }
                }
            }
        }
        EggUtil.success(list);
        battleEnd.clean();
    }
    
    public static void RewardSB(BattleEnd battleEnd, BattleData battleData, String[] teams, OneArenaBean arenaBean) {
        int doorId = 0;
        for (int i = teams.length - 1; i >= 0; --i) {
            battleEnd.clean();
            doorId = 0;
            if (battleData.getParticipantlist().remove(teams[i])) {
                ManData manData = battleData.getBattlefield().getBattleEndData(teams[i]);
                ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(teams[i]);
                LoginResult loginResult = (ctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx)) : null;
                if (manData != null && loginResult != null) {
                    if (battleData.getBattlefield().isFightType()) {
                        if (manData.getHp() <= 0) {
                            loginResult.setHp(new BigDecimal((int)((double)manData.getHp_z() * 0.25)));
                            loginResult.setMp(new BigDecimal((int)((double)manData.getMp_z() * 0.25)));
                        }
                        else {
                            loginResult.setHp(new BigDecimal((manData.getHp() > 0) ? manData.getHp() : 1));
                            loginResult.setMp(new BigDecimal(manData.getMp()));
                        }
                    }
                    battleEnd.upAssetData("R" + loginResult.getGrade() + "=" + loginResult.getExperience() + "=" + loginResult.getHp() + "=" + loginResult.getMp());
                    RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
                    //TODO 普通时效药
                    boolean isH = roleData.getLimit(RewardLimit.HPY) == null;
                    boolean isM = roleData.getLimit(RewardLimit.MPY) == null;
                    //TODO 月药
                    boolean isHS = roleData.getLimit(RewardLimit.blood) == null;
                    boolean isMS = roleData.getLimit(RewardLimit.Mana) == null;
                    if (battleData.getBattlefield().isFightType()) {//药瓶回血回蓝
                        if (manData.getHp() <= 0) {
                            loginResult.setHp(new BigDecimal((int)((double)manData.getHp_z() * 0.25)));
                            loginResult.setMp(new BigDecimal((int)((double)manData.getMp_z() * 0.25)));
                        }
                        else {
                            //TODO 普通时效药
                            loginResult.setHp(new BigDecimal(isHS ? manData.getHp() : manData.getHp_z()));
                            loginResult.setMp(new BigDecimal(isMS ? manData.getMp() : manData.getMp_z()));
                        }
                        if (roleData.getLimit(RewardLimit.blood) == null && roleData.getLimit(RewardLimit.Mana) == null) {
                            loginResult.setHp(new BigDecimal(isH ? manData.getHp() : manData.getHp_z()));
                            loginResult.setMp(new BigDecimal(isM ? manData.getMp() : manData.getMp_z()));
                        }
                    }
                    if (battleData.getBattlefield().isFightType() && roleData.getLimit(RewardLimit.VIP) == null&& roleData.getLimit(RewardLimit.JVIP) == null) {
                        RoleSummoning pet = manData.getPet(true, true, true, true);
                        if (pet != null) {
                            battleEnd.upAssetData("P" + pet.getSid() + "=" + pet.getGrade() + "=" + pet.getExp() + "=" + pet.getFriendliness() + "=" + pet.getBasishp() + "=" + pet.getBasismp());
                            AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
                        }
                    }
                    if (arenaBean != null) {
                        battleEnd.setArenaBean(arenaBean);
                        TaskRecord taskRecord = roleData.getTaskRecord(4);
                        if (taskRecord == null) {
                            taskRecord = new TaskRecord(4);
                            roleData.addTaskRecord(taskRecord);
                        }
                        taskRecord.addRSum(1);
                        battleEnd.upTaskn("C4=R");
                    }
                    if (battleData.getHJv() > 0) {// 幻境试炼
                        TaskRecord taskRecord = roleData.getTaskRecord(6);
                        if (taskRecord == null) {
                            taskRecord = new TaskRecord(6);
                            roleData.addTaskRecord(taskRecord);
                        }
                        int curr = battleData.getRobots().getRobotID() - 9000;
                        if (taskRecord.getNewID() >= curr) {
                            taskRecord.addCSum(1);
                            battleEnd.upTaskn("C6=L");
                        }
                        battleEnd.upMsg("幻境试炼挑战失败");
                    }
                    if (battleData.getBattleType() == 10) {//强P经验损失
                        doorId = 365;//送回轮回司
                        long expmax = ExpUtil.getRoleExp(loginResult.getTurnAround(), BattleMixDeal.lvlint((int)loginResult.getGrade()));
                        expmax = (long)((double)expmax * ((manData.getCamp() == 1) ? 1.5 : 0.5));
                        int lvl = (int)loginResult.getGrade();
                        ExpUtil.RoleRemoveExp(loginResult, expmax);
                        lvl -= (int)loginResult.getGrade();
                        StringBuffer buffer = new StringBuffer();
                        buffer.append("你在强行杀人PK中被杀了!");
                        if (manData.getCamp() == 1) {
                            buffer.append("由于你是发起方惩罚加重.");
                        }
                        long moeny = loginResult.getGold().longValue();
                        moeny = (long)((double)moeny * ((manData.getCamp() == 1) ? 0.5 : 0.3));
                        loginResult.setGold(loginResult.getGold().subtract(new BigDecimal(moeny)));
                        MonitorUtil.getMoney().useD(moeny);
                        buffer.append("你损失了" + moeny + "大话币.");
                        buffer.append("你损失了" + expmax + "经验.");
                        if (lvl != 0) {
                            buffer.append("由于经验不足掉落了");
                            buffer.append(lvl);
                            buffer.append("级");
                        }
                        FriendChatAction.createChatBeanForServer(buffer.toString(), loginResult.getRolename());
                        StringBuffer roleBuffer = new StringBuffer();
                        roleBuffer.append("R");
                        roleBuffer.append(loginResult.getGrade());
                        roleBuffer.append("=");
                        roleBuffer.append(loginResult.getExperience());
                        roleBuffer.append("=");
                        roleBuffer.append(loginResult.getHp());
                        roleBuffer.append("=");
                        roleBuffer.append(loginResult.getMp());
                        roleBuffer.append("=");
                        roleBuffer.append(loginResult.getBone());
                        roleBuffer.append("=");
                        roleBuffer.append(loginResult.getSpir());
                        roleBuffer.append("=");
                        roleBuffer.append(loginResult.getPower());
                        roleBuffer.append("=");
                        roleBuffer.append(loginResult.getSpeed());
                        roleBuffer.append("=");
                        roleBuffer.append(loginResult.getCalm());
                        battleEnd.upAssetData("D=-" + moeny);
                        battleEnd.upAssetData(roleBuffer.toString());
                    }
                    else if (battleData.getBattleType() == 15 && i == 0) {//抓捕
                        doorId = 1;
                        int[] dailys = loginResult.getDaily();
                        dailys[1] = 1;
                        ++dailys[3];
                        battleEnd.setTaskDaily(loginResult.upDaily(dailys));
                    }
                    if (battleData.getSceneId() != null) {
                        Scene scene = SceneUtil.getScene((int)battleData.getSceneId());
                        if (scene != null) {
                            int door = scene.battleEnd(battleEnd, loginResult, null, (i == 0) ? 2 : 3);
                            if (i == 0 && door != 0) {
                                doorId = door;
                            }
                        }
                    }
                    String msg = Agreement.FightingendAgreement(GsonUtil.getGsonUtil().getgson().toJson(battleEnd));
                    SendMessage.sendMessageToSlef(ctx, msg);
                    if (i == 0) {
                        if (battleData.getBattleType() == 11) {//回帮派营地
                            IAction action = (IAction)ParamTool.ACTION_MAP.get("gangbattle");
                            if (action != null) {//回监狱
                                action.action(ctx, "5");
                            }
                        }
                        else if (doorId == 1) {
                            IAction action = (IAction)ParamTool.ACTION_MAP.get("getout");
                            if (action != null) {
                                action.action(ctx, loginResult.getTaskDaily());
                            }
                        }
                        else if (doorId != 0) {
                            Door door2 = GameServer.getDoor(doorId);
                            if (door2 != null) {
                                ChangeMapBean changeMapBean = new ChangeMapBean();
                                changeMapBean.setMapid(door2.getDoormap());
                                String[] vs = door2.getDoorpoint().split("\\|");
                                changeMapBean.setMapx(Integer.parseInt(vs[0]));
                                changeMapBean.setMapy(Integer.parseInt(vs[1]));
                                ChangeMapAction.ChangeMap(changeMapBean, ctx);
                            }
                        }
                    }
                    String robotName = (battleData.getRobots() == null) ? "" : battleData.getRobots().getRobotname();
                    if (i == 0 && loginResult.isGolem()) {
//                        GameServer.golemServer.endFighting(teams[i], "成功被【" + robotName + "】击杀");
                    }
                }
            }
        }
    }
    
    public static void reward(String[] team, String value) {
        int wei = Battlefield.random.nextInt(team.length);
        ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(team[wei]);
        if (ctx != null) {
            LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
            if (loginResult == null) {
                return;
            }
            DropUtil.getDrop(loginResult, value, null, 22, 1.0, null);
        }
    }
    
    public static void rewardS(String[] team, String value) {
        for (int i = 0; i < team.length; ++i) {
            ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(team[i]);
            if (ctx != null) {
                LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
                if (loginResult != null) {
                    DropUtil.getDrop(loginResult, value, null, 22, 1.0, null);
                }
            }
        }
    }
    
    public static synchronized ConcurrentHashMap<BigDecimal, Integer> get(int robotid) {
        ConcurrentHashMap<BigDecimal, Integer> map = (ConcurrentHashMap<BigDecimal, Integer>)RewardLimit.rewardMap.get(robotid);
        if (map == null) {
            map = new ConcurrentHashMap<>();
            RewardLimit.rewardMap.put(robotid, map);
        }
        return map;
    }
    /**判断怪物是否产生掉落*/
    public static String isBoosDrop(Boos boos) {
        if (boos.getBoosDropMax() == 0) {
            return null;
        }
        Integer num = (Integer)RewardLimit.boosDropMap.get(boos.getBoosid());
        if (num == null) {
            num = 0;
        }
        if ((int)num >= boos.getBoosDropMax()) {
            return null;
        }
        num = (int) num + 1;
        RewardLimit.boosDropMap.put(boos.getBoosid(), num);
        return boos.getBoosid();
    }
    
    public static void reset() {
        RewardLimit.rewardMap.clear();
        RewardLimit.boosDropMap.clear();
    }
    
    static {
        RewardLimit.boosDropMap = new ConcurrentHashMap<>();
        RewardLimit.rewardMap = new ConcurrentHashMap<>();
        RewardLimit.KRD = "枯荣丹";
        RewardLimit.VIP = "VIP";
        RewardLimit.JVIP = "JVIP";
        RewardLimit.HPY = "六脉化神丸";
        RewardLimit.MPY = "玉枢返虚丸";
        RewardLimit.blood = "超级六脉化神丸_月";
        RewardLimit.Mana = "超级玉枢返虚丸_月";
    }
}
