package come.tool.newTask;

import org.come.model.TaskSet;
import come.tool.Battle.BattleMixDeal;
import org.come.server.GameServer;
import java.util.Iterator;
import come.tool.Role.RoleData;
import come.tool.Role.RolePool;
import org.come.handler.SendMessage;
import org.come.until.AchievemUtil;
import org.come.until.GsonUtil;
import org.come.until.AllServiceUtil;
import org.come.entity.Goodstable;
import org.come.action.monitor.MonitorUtil;
import java.math.BigDecimal;
import come.tool.Stall.AssetUpdate;
import org.come.protocol.Agreement;
import org.come.bean.LoginResult;
import org.come.model.TaskTerm;
import org.come.model.TaskData;
import org.come.bean.Coordinates;
import org.come.model.TaskProgress;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.List;

public class TaskUtil
{
    public static List<Task> initTask(String msg) {
        List<Task> tasks = new ArrayList<>();
        if (msg == null || msg.equals("")) {
            return tasks;
        }
        String[] values = msg.split("\\|");
        for (int i = 0; i < values.length; ++i) {
            try {
                String[] vs = values[i].split("=");
                int taskid = Integer.parseInt(vs[0]);
                int state = Integer.parseInt(vs[1]);
                Task task = new Task(taskid, state, vs);
                tasks.add(task);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tasks;
    }
    
    public static ConcurrentHashMap<Integer, TaskRecord> initTaskRecord(String msg) {
        ConcurrentHashMap<Integer, TaskRecord> map = new ConcurrentHashMap<>();
        if (msg == null || msg.equals("")) {
            return map;
        }
        String[] values = msg.split("\\|");
        for (int i = 0; i < values.length; ++i) {
            try {
                String[] vs = values[i].split("-");
                TaskRecord record = new TaskRecord(Integer.parseInt(vs[0]));
                record.addRSum(Integer.parseInt(vs[1]));
                record.addCSum(Integer.parseInt(vs[2]));
                if (vs.length == 4) {
                    record.setNewID(Integer.parseInt(vs[3]));
                }
                map.put(Integer.valueOf(record.getTaskId()), record);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }
    
    public static Task createTask(int Taskid, int max) {
        return createTask(Taskid, max, null);
    }
    
    public static Task createTask(int Taskid, int max, TaskProgress taskProgress) {
        Task task = new Task(Taskid);
        TaskData taskData = task.getTaskData();
        if (taskData == null) {
            return null;
        }
        if (taskData.getTime() != 0) {
            task.setTime((long)taskData.getTime() * 60L * 1000L + System.currentTimeMillis() + 120000L);
        }
        TaskTerm[] terms = taskData.getTaskTerms();
        if (terms != null) {
            List<TaskProgress> progress = new ArrayList<>();
            for (int i = 0; i < terms.length; ++i) {
                if (taskProgress != null) {
                    Coordinates zB = new Coordinates(taskProgress.getMap(), taskProgress.getX(), taskProgress.getY());
                    terms[i].setZB(zB);
                }
                progress.add(terms[i].create(taskData, max));
            }
            task.setProgress(progress);
        }
        return task;
    }
    
    public static Object isTaskReceive(boolean is, TaskData taskData, int max, int size, LoginResult... logs) {
        if (taskData.isTime()) {
            return (Object)Agreement.getAgreement().PromptAgreement("未到开放时间！");
        }
        String value = taskData.isLvl(logs, size);
        if (value != null) {
            return (Object)value;
        }
        value = taskData.isSum(is, logs);
        if (value != null) {
            return (Object)value;
        }
        Object object = taskData.isConsume(logs);
        return object;
    }
    
    public static void taskConsume(TaskConsume taskConsume, LoginResult... logs) {
        if (taskConsume == null) {
            return;
        }
        for (int i = 0; i < logs.length; ++i) {
            StringBuffer buffer = new StringBuffer();
            AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
            LoginResult login = logs[i];
            if (taskConsume.getMoney() != 0L) {
                login.setGold(login.getGold().add(new BigDecimal(-taskConsume.getMoney())));
                MonitorUtil.getMoney().useD(taskConsume.getMoney());
                assetUpdate.updata("D=-" + taskConsume.getMoney());
                if (buffer != null) {
                    buffer.append("|");
                }
                buffer.append("扣除");
                buffer.append(taskConsume.getMoney());
                buffer.append("银两");
            }
            if (taskConsume.getGoods() != null) {
                for (int j = taskConsume.getGoods().size() - 1; j >= 0; --j) {
                    Goodstable good = (Goodstable)taskConsume.getGoods().get(j);
                    if (good.getRole_id().compareTo(login.getRole_id()) == 0) {
                        AllServiceUtil.getGoodsTableService().updateGoodRedis(good);
                        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
                        taskConsume.getGoods().remove(j);
                    }
                }
            }
            if (buffer.length() != 0) {
                assetUpdate.setMsg(buffer.toString());
            }
            SendMessage.sendMessageByRoleName(login.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
    }
    
    public static void addSumReceive(TaskData taskData, LoginResult... logs) {
        if (taskData.getTaskSet().getSumreceive() == 0) {
            return;
        }
        for (int i = 0; i < logs.length; ++i) {
            LoginResult log = logs[i];
            RoleData data = RolePool.getRoleData(log.getRole_id());
            TaskRecord record = data.getTaskRecord(taskData.getTaskSetID());
            if (record == null) {
                record = new TaskRecord(taskData.getTaskSetID());
                data.addTaskRecord(record);
            }
            record.addRSum(1);
            record.setNewID(0);
        }
    }
    
    public static void addSumLimit(TaskData taskData, LoginResult... logs) {
        if (taskData.getTaskSet().getSumlimit() == 0) {
            return;
        }
        for (int i = 0; i < logs.length; ++i) {
            LoginResult log = logs[i];
            RoleData data = RolePool.getRoleData(log.getRole_id());
            TaskRecord record = data.getTaskRecord(taskData.getTaskSetID());
            if (record == null) {
                record = new TaskRecord(taskData.getTaskSetID());
                data.addTaskRecord(record);
            }
            if (taskData.getTaskID() >= 3157 && taskData.getTaskID() <= 3500) {
                record.setNewID(taskData.getTaskID());
            }
            else {
                record.addCSum(1);
            }
            if(record.getTaskId() == 100) {
                AchievemUtil.detectionAchievem(log, "阿三");
            }else if(record.getTaskId() == 101) {
                AchievemUtil.detectionAchievem(log, "小鬼");
            }else if(record.getTaskId() == 102) {
                AchievemUtil.detectionAchievem(log, "天庭");
            }else if(record.getTaskId() == 103) {
                AchievemUtil.detectionAchievem(log, "鬼王");
            }else if(record.getTaskId() == 104) {
                AchievemUtil.detectionAchievem(log, "修罗");
            }else if(record.getTaskId() == 105) {
                AchievemUtil.detectionAchievem(log, "域外");
            }else if(record.getTaskId() == 145) {
                AchievemUtil.detectionAchievem(log, "宝象");
            }

        }
    }
    
    public static void Progress(Task task, StringBuffer buffer) {
        List<TaskProgress> progress = task.getProgress();
        if (progress.size() == 0) {
            return;
        }
        for (int i = 0; i < progress.size(); ++i) {
            TaskProgress taskProgress = (TaskProgress)progress.get(i);
            buffer.append("=P");
            buffer.append(taskProgress.getType());
            if (taskProgress.getSum() != 0 || taskProgress.getMax() != 1) {
                buffer.append("_S");
                buffer.append(taskProgress.getSum());
                buffer.append("-");
                buffer.append(taskProgress.getMax());
            }
            if (taskProgress.getMap() != 0) {
                buffer.append("_M");
                buffer.append(taskProgress.getMap());
                buffer.append("-");
                buffer.append(taskProgress.getX());
                buffer.append("-");
                buffer.append(taskProgress.getY());
            }
            buffer.append("_D");
            buffer.append(taskProgress.getDId());
            buffer.append("-");
            buffer.append(taskProgress.getDName());
            if (taskProgress.getGId() != 0) {
                buffer.append("_G");
                buffer.append(taskProgress.getGId());
                buffer.append("-");
                buffer.append(taskProgress.getGName());
            }
        }
    }
    
    public static String toTaskRecord(ConcurrentHashMap<Integer, TaskRecord> map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        for (TaskRecord record : map.values()) {
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append(record.getTaskId());
            buffer.append("-");
            buffer.append(record.getrSum());
            buffer.append("-");
            buffer.append(record.getcSum());
            if (record.getNewID() != 0) {
                buffer.append("-");
                buffer.append(record.getNewID());
            }
        }
        return buffer.toString();
    }
    
    public static Task TaskReceive(int taskID, int size, int nsize, LoginResult log, RoleData roleData, StringBuffer buffer) {
        return TaskReceive(taskID, size, nsize, log, roleData, buffer, null);
    }
    
    public static Task TaskReceive(int taskID, int size, int nsize, LoginResult log, RoleData roleData, StringBuffer buffer, TaskProgress taskProgress) {
        if (nsize >= 5) {
            return null;
        }
        TaskData taskData = GameServer.getTaskData(taskID);
        if (taskData == null) {
            return null;
        }
        TaskConsume taskConsume = null;
        Object object = isTaskReceive(false, taskData, BattleMixDeal.lvlint((int)log.getGrade()), size, new LoginResult[] { log });
        if (object != null) {
            if (object instanceof String) {
                SendMessage.sendMessageByRoleName(log.getRolename(), (String)object);
                return null;
            }
            if (object instanceof TaskConsume) {
                taskConsume = (TaskConsume)object;
            }
        }
        taskConsume(taskConsume, new LoginResult[] { log });
        Task task = createTask(taskID, BattleMixDeal.lvlint((int)log.getGrade()), taskProgress);
        task.setTaskState(2);
        if (task.getProgress() == null) {
            task.setTaskState(4);
            addSumLimit(taskData, new LoginResult[] { log });
            StringBuffer buffer2 = addTaskL(null, taskID, taskData.getTaskSet());
            if (buffer2 != null) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append(buffer2);
            }
            int newTaskId = taskData.getNewTaskId();
            ++nsize;
            return TaskReceive(newTaskId, nsize, nsize, log, roleData, buffer);
        }
        else {
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append(task.getTaskId());
            buffer.append("=");
            buffer.append(task.getTaskState());
            if (task.getTime() != 0L) {
                buffer.append("=T");
                buffer.append(task.getTime() / 1000L);
            }
            Progress(task, buffer);
            roleData.addTask(task, true);
            return task;
        }
    }
    
    public static StringBuffer addTaskL(StringBuffer buffer, int taskId, TaskSet taskSet) {
        if (taskSet.getSumlimit() == 0) {
            return null;
        }
        if (buffer == null) {
            buffer = new StringBuffer("C");
            buffer.append(taskSet.getTaskSetID());
        }
        if (taskId >= 3157 && taskId <= 3500) {
            buffer.append("=N");
            buffer.append(taskId);
        }
        else {
            buffer.append("=L");
        }
        return buffer;
    }
}
