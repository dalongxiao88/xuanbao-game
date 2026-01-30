package come.tool.newTask;

import org.come.model.Achieve;
import org.come.model.ActiveAward;
import org.come.action.suit.SuitPalEquip;
import come.tool.Good.DropUtil;
import org.come.entity.Goodstable;
import org.come.model.Door;
import org.come.bean.UseCardBean;
import come.tool.Role.RoleData;
import org.come.until.AchievemUtil;
import org.come.until.GsonUtil;
import come.tool.Stall.AssetUpdate;
import org.come.until.AllServiceUtil;
import java.math.BigDecimal;
import org.come.action.sys.ChangeMapAction;
import org.come.bean.ChangeMapBean;
import come.tool.Battle.BattleMixDeal;
import java.util.Random;
import org.come.model.TaskData;
import org.come.tool.WriteOut;
import org.come.action.Keju.KejuAction;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import come.tool.Role.RolePool;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class TaskAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        if (roleData == null) {
            return;
        }
        if (message.startsWith("T")) {
            String[] vs = message.split("\\|");
            vs[0] = vs[0].substring(1);
            String value = roleData.removeTasks(9, vs);
            if (value != null) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().TaskNAgreement(value));
            }
            return;
        }
        else {
            if (message.startsWith("R")) {
                message = message.substring(1);
                this.receive(ctx, loginResult, roleData, message);
                return;
            }
            if (message.startsWith("Y1")) {
                message = message.substring(1);
                this.receive(ctx, loginResult, roleData, message);
                return;
            }
            String[] teams = loginResult.getTeam().split("\\|");
            if (!teams[0].equals(loginResult.getRolename())) {
                return;
            }
            String type = message.substring(0, 1);
            String type2 = message.substring(1, message.length());
            if (type2.equals("4338") && type.equals("L")) {
                Date now = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd-HH-mm-ss");
                String hehe = dateFormat.format(now);
                String[] k = hehe.split("-");
                boolean m = false;
                if (KejuAction.role.size() != 0) {
                    for (int i = 0; i <= KejuAction.role.size() - 1; ++i) {
                        if (loginResult.getRole_id().toString().equals(((String[])KejuAction.role.get(i))[0])) {
                            m = true;
                        }
                    }
                }
                if (m) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("#Y每天只能参加一次科举"));
                    return;
                }
                if (Integer.parseInt(k[1]) < 20) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("#Y请在8点之后前来"));
                    return;
                }
            }
            if (type.equals("L") || type.equals("N")) {
                int nsize = 0;
                int taskID = 0;
                if (type.equals("L")) {
                    taskID = Integer.parseInt(message.substring(1));
                }
                else {
                    String[] vs2 = message.substring(1).split("\\|");
                    nsize = Integer.parseInt(vs2[0]);
                    taskID = Integer.parseInt(vs2[1]);
                    if (nsize >= 5) {
                        WriteOut.addtxt("下一个任务次数过多:" + nsize + ":" + loginResult.getRole_id() + ":" + loginResult.getRolename(), 9999L);
                        return;
                    }
                }
                if (taskID < 1000) {
                    WriteOut.addtxt("非法领取任务" + taskID + ":" + loginResult.getRole_id() + ":" + loginResult.getRolename(), 9999L);
                    return;
                }
                TaskData taskData = GameServer.getTaskData(taskID);
                if (taskData == null) {
                    return;
                }
                try {
                    if (taskData.getTaskSetID() == 600) {
                        UseCardBean limit = roleData.getLimit("VIP");
                        if (limit != null) {
                            taskData = (TaskData)GameServer.taskData.get(new Random().nextInt(GameServer.taskData.size()));
                            taskID = taskData.getTaskID();
                        }
                        else {
                            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("请先开通月卡功能#46"));
                            return;
                        }
                    }
                }
                catch (NullPointerException e) {
                    e.printStackTrace();
                }
                int max = 0;
                int length = (taskData.getTeamSum() <= 1) ? 1 : teams.length;
                int size = teams.length + roleData.PSize();
                if (size > 5) {
                    size = 5;
                }
                LoginResult[] logs = new LoginResult[length];
                for (int j = 0; j < length; ++j) {
                    LoginResult login = null;
                    if (j == 0) {
                        login = loginResult;
                    }
                    else {
                        ChannelHandlerContext ctx2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(teams[j]);
                        if (ctx2 != null) {
                            login = (LoginResult)GameServer.getAllLoginRole().get(ctx2);
                        }
                    }
                    if (login == null) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(teams[j] + "处于异常状态"));
                        return;
                    }
                    logs[j] = login;
                    int lvl = BattleMixDeal.lvlint((int)login.getGrade());
                    if (lvl > max) {
                        max = lvl;
                    }
                }
                TaskConsume taskConsume = null;
                Object object = TaskUtil.isTaskReceive(nsize == 0, taskData, max, size, logs);
                if (object != null) {
                    if (object instanceof String) {
                        SendMessage.sendMessageToSlef(ctx, (String)object);
                        return;
                    }
                    if (object instanceof TaskConsume) {
                        taskConsume = (TaskConsume)object;
                    }
                }
                TaskUtil.taskConsume(taskConsume, logs);
                if (taskData.getDoorID() != 0) {
                    Door door = GameServer.getDoor(taskData.getDoorID());
                    if (door != null) {
                        ChangeMapBean changeMapBean = new ChangeMapBean();
                        changeMapBean.setMapid(door.getDoormap());
                        String[] vs3 = door.getDoorpoint().split("\\|");
                        changeMapBean.setMapx(Integer.parseInt(vs3[0]));
                        changeMapBean.setMapy(Integer.parseInt(vs3[1]));
                        ChangeMapAction.ChangeMap(changeMapBean, ctx);
                    }
                }
                if (nsize == 0) {
                    TaskUtil.addSumReceive(taskData, logs);
                }
                Task task = TaskUtil.createTask(taskID, max);
                task.setTaskState(2);
                if (task.getProgress() == null) {
                    task.setTaskState(4);
                    TaskUtil.addSumLimit(taskData, logs);
                    if (taskData.getTaskSet().getSumreceive() != 0 || taskData.getTaskSet().getSumlimit() != 0) {
                        StringBuffer buffer = new StringBuffer();
                        buffer.append(task.getTaskId());
                        buffer.append("=");
                        buffer.append(task.getTaskState());
                        StringBuffer buffer2 = null;
                        if (nsize == 0 && taskData.getTaskSet().getSumreceive() != 0) {
                            if (buffer2 == null) {
                                buffer2 = new StringBuffer("C");
                                buffer2.append(taskData.getTaskSetID());
                            }
                            buffer2.append("=R");
                        }
                        buffer2 = TaskUtil.addTaskL(buffer2, taskData.getTaskID(), taskData.getTaskSet());
                        if (buffer2 != null) {
                            buffer.append("|");
                            buffer.append(buffer2);
                        }
                        String msg = Agreement.getAgreement().TaskNAgreement(buffer.toString());
                        for (int l = 0; l < logs.length; ++l) {
                            SendMessage.sendMessageByRoleName(logs[l].getRolename(), msg);
                        }
                    }
                    int newTaskId = taskData.getNewTaskId();
                    ++nsize;
                    if (newTaskId != 0) {
                        this.action(ctx, "N" + nsize + "|" + newTaskId);
                    }
                }
                else {
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(task.getTaskId());
                    buffer.append("=");
                    buffer.append(task.getTaskState());
                    if (task.getTime() != 0L) {
                        buffer.append("=T");
                        buffer.append(task.getTime() / 1000L);
                    }
                    TaskUtil.Progress(task, buffer);
                    if (nsize == 0 && taskData.getTaskSet().getSumreceive() != 0) {
                        buffer.append("|C");
                        buffer.append(taskData.getTaskSetID());
                        buffer.append("=R");
                    }
                    String msg2 = Agreement.getAgreement().TaskNAgreement(buffer.toString());
                    for (int i2 = 0; i2 < logs.length; ++i2) {
                        RoleData data = RolePool.getRoleData(logs[i2].getRole_id());
                        data.addTask(task, i2 == 0);
                        SendMessage.sendMessageByRoleName(logs[i2].getRolename(), msg2);
                    }
                }
                if(type.equals("L") && taskData.getTaskSetID() == 111) {
                    AchievemUtil.detectionAchievem(loginResult, "五环任务");
                }
                if(type.equals("L") && taskData.getTaskSetID() == 600) {
                    AchievemUtil.detectionAchievem(loginResult, "200环任务");
                }
            }
            else if (type.equals("E")) {
                int taskID2 = Integer.parseInt(message.substring(1));
                TaskData taskData2 = GameServer.getTaskData(taskID2);
                if (taskData2 == null) {
                    return;
                }
                String msg3 = Agreement.getAgreement().TaskNAgreement(taskID2 + "=8");
                for (int i3 = 0; i3 < teams.length; ++i3) {
                    ChannelHandlerContext ctx3 = null;
                    if (i3 == 0) {
                        roleData.removeTask(taskID2);
                        ctx3 = ctx;
                    }
                    else {
                        ctx3 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(teams[i3]);
                        if (ctx3 == null) {
                            continue;
                        }
                        else {
                            LoginResult log2 = (LoginResult)GameServer.getAllLoginRole().get(ctx3);
                            if (log2 == null) {
                                continue;
                            }
                            else {
                                RoleData data2 = RolePool.getRoleData(log2.getRole_id());
                                if (data2 == null) {
                                    continue;
                                }
                                else {
                                    data2.removeTask(taskID2);
                                }
                            }
                        }
                    }
                    SendMessage.sendMessageToSlef(ctx3, msg3);
                    if (i3 == 0 && taskData2.getTeamSum() <= 1) {
                        break;
                    }
                }
            }
            else if (type.equals("G")) {
                String[] vs4 = message.split("\\|");
                vs4[0] = vs4[0].substring(1);
                int taskID = Integer.parseInt(vs4[0]);
                TaskData taskData = GameServer.getTaskData(taskID);
                if (taskData == null) {
                    return;
                }
                BigDecimal rgid = new BigDecimal(vs4[1]);
                int sum = Integer.parseInt(vs4[2]);
                Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(rgid);
                if (good == null || good.getRole_id().compareTo(loginResult.getRole_id()) != 0 || sum > (int)good.getUsetime()) {
                    return;
                }
                good.goodxh(sum);
                AllServiceUtil.getGoodsTableService().updateGoodRedis(good);
                AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
                assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                String name = good.getGoodsname();
                MMM(taskData, teams, name, sum, "给予物品");
            }
            else if (type.equals("W")) {
                String[] vs4 = message.split("\\|");
                vs4[0] = vs4[0].substring(1);
                int taskID = Integer.parseInt(vs4[0]);
                TaskData taskData = GameServer.getTaskData(taskID);
                if (taskData == null) {
                    return;
                }
                String name2 = vs4[1];
                MMM(taskData, teams, name2, 1, "问候");
            }
            return;
        }
    }
    
    public static void MMM(TaskData taskData, String[] teams, String name, int sum, String type) {
        int length = (taskData.getTeamSum() <= 1) ? 1 : teams.length;
        for (int i = length - 1; i >= 0; --i) {
            LoginResult login = null;
            ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(teams[i]);
            if (ctx != null) {
                login = (LoginResult)GameServer.getAllLoginRole().get(ctx);
                if (login != null) {
                    RoleData data = RolePool.getRoleData(login.getRole_id());
                    if (data != null) {
                        Task task = data.getTask(taskData.getTaskID());
                        if (task != null) {
                            int part = task.PartFinish(name, sum, type);
                            if (part != 0) {
                                StringBuffer buffer = new StringBuffer();
                                boolean is = task.isFinish();
                                buffer.append(task.getTaskId());
                                buffer.append("=");
                                if (is) {
                                    buffer.append(4);
                                    data.removeTask(task.getTaskId());
                                    if (taskData.getTaskAward() != null && !taskData.getTaskAward().equals("")) {
                                        DropUtil.getDrop(login, taskData.getTaskAward(), taskData.getTalk(), 22, 1.0, null);
                                    }
                                    if (taskData.getTaskSet().getSumlimit() != 0) {
                                        TaskUtil.addSumLimit(taskData, new LoginResult[] { login });
                                        StringBuffer buffer2 = TaskUtil.addTaskL(null, taskData.getTaskID(), taskData.getTaskSet());
                                        if (buffer2 != null) {
                                            buffer.append("|");
                                            buffer.append(buffer2);
                                        }
                                    }
                                    int newTaskId = taskData.getNewTaskId();
                                    if (newTaskId != 0) {
                                        if (taskData.getTaskSet().getTaskSetID() == 600) {
                                            TaskData taskData2 = (TaskData)GameServer.taskData.get(new Random().nextInt(GameServer.taskData.size()));
                                            newTaskId = taskData2.getTaskID();
                                        }
                                        Task task2 = TaskUtil.TaskReceive(newTaskId, teams.length, 0, login, data, buffer);
                                        if (i == 0 && task2 != null && task2.getTaskData().getDoorID() != 0) {
                                            Door door = GameServer.getDoor(task2.getTaskData().getDoorID());
                                            if (door != null) {
                                                ChangeMapBean changeMapBean = new ChangeMapBean();
                                                changeMapBean.setMapid(door.getDoormap());
                                                String[] vs = door.getDoorpoint().split("\\|");
                                                changeMapBean.setMapx(Integer.parseInt(vs[0]));
                                                changeMapBean.setMapy(Integer.parseInt(vs[1]));
                                                ChangeMapAction.ChangeMap(changeMapBean, ctx);
                                            }
                                        }
                                    }
                                }
                                else {
                                    buffer.append(task.getTaskState());
                                    TaskUtil.Progress(task, buffer);
                                }
                                String msg = Agreement.getAgreement().TaskNAgreement(buffer.toString());
                                SendMessage.sendMessageByRoleName(login.getRolename(), msg);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void receive(ChannelHandlerContext ctx, LoginResult loginResult, RoleData roleData, String v) {
        int id = v.indexOf("=");
        int bs = 0;
        if (id == -1) {
            id = Integer.parseInt(v);
        }
        else {
            bs = Integer.parseInt(v.substring(id + 1));
            id = Integer.parseInt(v.substring(0, id));
        }
        if (id == 2) {
            ActiveAward award = GameServer.getActiveAward(bs);
            if (award == null) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("没有该档次的活跃奖励"));
                return;
            }
            TaskRecord taskRecord = roleData.getTaskRecord(2);
            if (taskRecord != null && (taskRecord.getNewID() >> bs & 0x1) == 0x1) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你已领取过该奖励"));
                return;
            }
            int value = GameServer.getActiveValue(roleData);
            if (value < award.getActive()) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你活跃度不够"));
                return;
            }
            if (taskRecord == null) {
                taskRecord = new TaskRecord(2);
            }
            taskRecord.setNewID(taskRecord.getNewID() | 1 << bs);
            roleData.addTaskRecord(taskRecord);
            AssetUpdate assetUpdate = DropUtil.getDrop(loginResult, award.getDropModel(), award.getActive() + "活跃礼包", null, 1.0, 0.0, 1, 22);
            if (assetUpdate == null) {
                assetUpdate = new AssetUpdate(AssetUpdate.USERGOOD);
            }
            assetUpdate.setTask("C2=N" + taskRecord.getNewID());
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
        else if (id == 4) {
            Achieve achieve = GameServer.getAchieve(bs);
            if (achieve == null) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("没有该档次的奖励"));
                return;
            }
            TaskRecord taskRecord = roleData.getTaskRecord(4);
            if (taskRecord != null && (taskRecord.getNewID() >> bs & 0x1) == 0x1) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你已领取过该奖励"));
                return;
            }
            if (achieve.getNum() > ((taskRecord != null) ? taskRecord.getcSum() : 0)) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你还没达到条件"));
                return;
            }
            if (taskRecord == null) {
                taskRecord = new TaskRecord(4);
            }
            taskRecord.setNewID(taskRecord.getNewID() | 1 << bs);
            roleData.addTaskRecord(taskRecord);
            AssetUpdate assetUpdate2 = DropUtil.getDrop(loginResult, achieve.getDropModel(), achieve.getName(), null, 1.0, 0.0, 1, 22);
            if (assetUpdate2 == null) {
                assetUpdate2 = new AssetUpdate(AssetUpdate.USERGOOD);
            }
            assetUpdate2.setTask("C4=N" + taskRecord.getNewID());
            if (bs % 3 == 0) {
                int lvl = bs / 3;
                String value2 = SuitPalEquip.getOneArena(null, lvl);
                UseCardBean cardBean = roleData.getLimit("单人竞技场");
                if (cardBean == null) {
                    cardBean = new UseCardBean(achieve.getName(), "单人竞技场", "cwzs", 0L, null);
                    for (int i = 1; i < lvl; ++i) {
                        String value3 = SuitPalEquip.getOneArena(null, i);
                        cardBean.upValue(value3, i - 1);
                    }
                    roleData.addLimit(cardBean);
                }
                cardBean.upValue(value2, lvl - 1);
                cardBean.setName(achieve.getName());
                assetUpdate2.setUseCard(cardBean);
                assetUpdate2.upmsg("你的" + cardBean.getName() + "BUFF刷新了");
                AllServiceUtil.getGoodsrecordService().insertGoodsrecord(loginResult.getRole_id(), null, 50201, loginResult.getRole_id(), "单人竞技场属性", cardBean.getValue(), 1);
            }
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
        }
        else if (id == 10) {
            ActiveAward award = GameServer.getActiveAward(bs);
            if (award == null) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("系统没有设置该礼包！"));
                return;
            }
            UseCardBean limit = roleData.getLimit("VIP");
            if (limit != null) {
                TaskRecord taskRecord2 = roleData.getTaskRecord(2);
                if (taskRecord2 != null && (taskRecord2.getNewID() >> bs & 0x1) == 0x1) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("您今日已领取过该奖励，请明日再来领取！"));
                    return;
                }
                if (taskRecord2 == null) {
                    taskRecord2 = new TaskRecord(2);
                }
                taskRecord2.setNewID(taskRecord2.getNewID() | 1 << bs);
                roleData.addTaskRecord(taskRecord2);
                AssetUpdate assetUpdate = DropUtil.getDropYk(loginResult, award.getDropModel(), award.getActive() + "活跃礼包", null, 1.0, 0.0, 1, 22);
                if (assetUpdate == null) {
                    assetUpdate = new AssetUpdate(AssetUpdate.USERGOOD);
                }
                assetUpdate.setTask("C2=N" + taskRecord2.getNewID());
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            }
            else {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("您未激活月卡，无法领取该礼包！请联系群主购买月卡！"));
                return;
            }
        }
    }
}
