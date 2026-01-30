package org.come.model;

import org.come.bean.UseCardBean;
import org.come.until.AllServiceUtil;
import java.util.ArrayList;
import org.come.entity.Goodstable;
import java.math.BigDecimal;
import come.tool.newTask.TaskConsume;
import come.tool.newTask.TaskRecord;
import come.tool.Role.RoleData;
import come.tool.Good.DropUtil;
import org.apache.commons.lang.StringUtils;
import org.come.server.GameServer;
import come.tool.Role.RolePool;
import come.tool.Battle.BattleMixDeal;
import org.come.protocol.Agreement;
import org.come.bean.LoginResult;
import org.come.task.RefreshMonsterTask;
import come.tool.Good.DropModel;
import java.util.List;

public class TaskData
{
    private int taskID;
    private int taskSetID;
    private String taskName;
    private String taskOpen;
    private transient String consume;
    private transient String finishTerm;
    private transient String taskAward;
    private String taskText;
    private String taskEnd;
    private transient int postTaskTerm;
    private transient String postTaskId;
    private String lvl;
    private int TeamSum;
    private transient int DoorID;
    private int isTP;
    private int time;
    private String openTime;
    private transient String talk;
    private int nd;
    private String goods;
    private String snames;
    private String sex;
    private transient int[] lvls;
    private transient String[] consumes;
    private transient TaskTerm[] taskTerms;
    private transient TaskTime[] taskTimes;
    private transient List<Integer> postTaskIds;
    private transient DropModel dropModel;
    private transient TaskSet taskSet;
    
    public boolean isTime() {
        if (this.taskTimes == null) {
            return false;
        }
        for (int i = 0; i < this.taskTimes.length; ++i) {
            TaskTime taskTime = this.taskTimes[i];
            if (taskTime.getWeek() == RefreshMonsterTask.day - 1 && RefreshMonsterTask.hour >= taskTime.getStartTime() && RefreshMonsterTask.hour < taskTime.getEndTime()) {
                return false;
            }
        }
        return true;
    }
    
    public String isLvl(LoginResult[] logs, int size) {
        if (this.TeamSum == 0 && size != 1) {
            return Agreement.getAgreement().PromptAgreement("该任务只能一个人完成，请检查组队和伙伴");
        }
        if (this.TeamSum != 1 && this.TeamSum > size) {
            return Agreement.getAgreement().PromptAgreement("队伍人数不够" + this.TeamSum + "个人,先凑齐人数在来吧");
        }
        if (this.lvls != null) {
            for (int i = 0; i < logs.length; ++i) {
                String value = BattleMixDeal.isLvl((int)logs[i].getGrade(), this.lvls);
                if (value != null) {
                    return value;
                }
            }
        }
        return null;
    }
    
    public String isSum(boolean is, LoginResult[] logs) {
        if (this.taskSet.getSumlimit() == 0 && this.taskSet.getSumreceive() == 0) {
            return null;
        }
        for (int i = 0; i < logs.length; ++i) {
            RoleData data = RolePool.getRoleData(logs[i].getRole_id());
            TaskRecord record = data.getTaskRecord(this.taskSetID);
            if (record != null) {
                if (is && this.taskSet.getSumreceive() != 0 && record.getrSum() >= this.taskSet.getSumreceive()) {
                    return Agreement.getAgreement().PromptAgreement(logs[i].getRolename() + "达到最大领取任务次数");
                }
                if (this.taskSet.getSumlimit() != 0 && record.getcSum() >= this.taskSet.getSumlimit()) {
                    if (!is && this.taskSet.getTaskSetID() == 600) {
                        Dorp dorp = GameServer.getDorp("6000");//200环奖励
                        if (dorp != null) {
                            DropUtil.getDrop(data.getLoginResult(), dorp.getDorpValue(), StringUtils.isNotBlank(dorp.getMsg()) ? dorp.getMsg() : "", 999, 1.0, null);
                        }
                    }
                    return Agreement.getAgreement().PromptAgreement(logs[i].getRolename() + "达到最大任务完成次数");
                }
            }
        }
        return null;
    }
    
    public Object isConsume(LoginResult[] logs) {
        if (this.consumes == null) {
            return null;
        }
        TaskConsume taskConsume = null;
        for (int i = 0; i < this.consumes.length; ++i) {
            String[] value = this.consumes[i].split("=");
            if (value[0].equals("前置任务")) {
                int qzmax = Integer.parseInt(value[2]);
                TaskData taskData = GameServer.getTaskData(Integer.parseInt(value[1]));
                int j = 0;
                while (j < logs.length) {
                    RoleData data = RolePool.getRoleData(logs[j].getRole_id());
                    TaskRecord record = data.getTaskRecord(taskData.getTaskSetID());
                    int qz = 0;
                    if (record != null) {
                        if (record.getNewID() != 0) {
                            qz = ((record.getNewID() >= taskData.getTaskID()) ? 1 : 0);
                        }
                        else {
                            qz = record.getcSum();
                        }
                    }
                    if (qz < qzmax) {
                        StringBuffer buffer = new StringBuffer();
                        buffer.append(logs[j].getRolename());
                        if (qzmax == 1) {
                            buffer.append("需要完成前置任务");
                            buffer.append(taskData.getTaskName());
                            buffer.append("才能领取该任务!");
                        }
                        else {
                            buffer.append("需要完成");
                            buffer.append(qzmax);
                            buffer.append("次");
                            buffer.append(taskData.getTaskName());
                            buffer.append("才能领取该任务!已完成");
                            buffer.append(qz);
                            buffer.append("次");
                        }
                        return (Object)Agreement.getAgreement().PromptAgreement(buffer.toString());
                    }
                    else {
                        ++j;
                    }
                }
            }
            else if (value[0].equals("金钱")) {
                long money = Long.parseLong(value[1]);
                for (int j = 0; j < logs.length; ++j) {
                    if (logs[j].getGold().longValue() < money) {
                        return (Object)Agreement.getAgreement().PromptAgreement(logs[j].getRolename() + "的金钱不够" + value[1]);
                    }
                }
                if (taskConsume == null) {
                    taskConsume = new TaskConsume();
                }
                taskConsume.setMoney(money);
            }
            else if (value[0].equals("活跃")) {
                long money = Long.parseLong(value[1]);
                for (int j = 0; j < logs.length; ++j) {
                    RoleData data = RolePool.getRoleData(logs[j].getRole_id());
                    int hy = GameServer.getActiveValue(data);
                    if ((long)hy < money) {
                        return (Object)Agreement.getAgreement().PromptAgreement("当前任务需要活跃" + value[1] + "你的活跃值#R:" + hy);
                    }
                }
            }
            else if (value[0].equals("月卡")) {
                for (int j = 0; j < logs.length; ++j) {
                    RoleData data = RolePool.getRoleData(logs[j].getRole_id());
                    UseCardBean limit = data.getLimit("VIP");
                    if (limit==null) {
                        return (Object)Agreement.getAgreement().PromptAgreement("当前任务需要月卡用户才能领取");
                    }
                }
            }
            else if (value[0].equals("季卡")) {
                for (int j = 0; j < logs.length; ++j) {
                    RoleData data = RolePool.getRoleData(logs[j].getRole_id());
                    UseCardBean limit = data.getLimit("JVIP");
                    if (limit==null) {
                        return (Object)Agreement.getAgreement().PromptAgreement("当前任务需要季卡用户才能领取");
                    }
                }
            }
            else if (value[0].equals("物品")) {
                String[] vs = value[1].split("\\*");
                BigDecimal goodid = new BigDecimal(vs[0]);
                int sum = Integer.parseInt(vs[1]);
                Goodstable goodstable = (Goodstable)GameServer.getAllGoodsMap().get(goodid);
                if (goodstable != null) {
                    List<Goodstable> list = new ArrayList<>();
                    for (int k = 0; k < logs.length; ++k) {
                        List<Goodstable> goods = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(logs[k].getRole_id(), goodid);
                        int SYsum = sum;
                        int l = 0;
                        while (l < goods.size()) {
                            Goodstable good = (Goodstable)goods.get(l);
                            if ((int)good.getUsetime() >= SYsum) {
                                good.setUsetime(Integer.valueOf((int)good.getUsetime() - SYsum));
                                SYsum = 0;
                                list.add(good);
                                break;
                            }
                            else {
                                SYsum -= (int)good.getUsetime();
                                good.setUsetime(Integer.valueOf(0));
                                list.add(good);
                                ++l;
                            }
                        }
                        if (SYsum > 0) {
                            return (Object)Agreement.getAgreement().PromptAgreement(logs[k].getRolename() + "不够" + sum + "个" + goodstable.getGoodsname());
                        }
                    }
                    if (taskConsume == null) {
                        taskConsume = new TaskConsume();
                    }
                    taskConsume.setGoods(list);
                }
            }
        }
        return (Object)taskConsume;
    }
    
    public int getTaskID() {
        return this.taskID;
    }
    
    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }
    
    public int getTaskSetID() {
        return this.taskSetID;
    }
    
    public void setTaskSetID(int taskSetID) {
        this.taskSetID = taskSetID;
    }
    
    public String getTaskOpen() {
        return this.taskOpen;
    }
    
    public void setTaskOpen(String taskOpen) {
        this.taskOpen = taskOpen;
    }
    
    public String getConsume() {
        return this.consume;
    }
    
    public void setConsume(String consume) {
        this.consume = consume;
    }
    
    public String getFinishTerm() {
        return this.finishTerm;
    }
    
    public void setFinishTerm(String finishTerm) {
        this.finishTerm = finishTerm;
    }
    
    public String getTaskAward() {
        return this.taskAward;
    }
    
    public void setTaskAward(String taskAward) {
        this.taskAward = taskAward;
    }
    
    public String getTaskText() {
        return this.taskText;
    }
    
    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }
    
    public String getTaskEnd() {
        return this.taskEnd;
    }
    
    public void setTaskEnd(String taskEnd) {
        this.taskEnd = taskEnd;
    }
    
    public int getPostTaskTerm() {
        return this.postTaskTerm;
    }
    
    public void setPostTaskTerm(int postTaskTerm) {
        this.postTaskTerm = postTaskTerm;
    }
    
    public String getPostTaskId() {
        return this.postTaskId;
    }
    
    public void setPostTaskId(String postTaskId) {
        this.postTaskId = postTaskId;
    }
    
    public String getLvl() {
        return this.lvl;
    }
    
    public void setLvl(String lvl) {
        this.lvl = lvl;
    }
    
    public int getTeamSum() {
        return this.TeamSum;
    }
    
    public void setTeamSum(int teamSum) {
        this.TeamSum = teamSum;
    }
    
    public int getDoorID() {
        return this.DoorID;
    }
    
    public void setDoorID(int doorID) {
        this.DoorID = doorID;
    }
    
    public int getTime() {
        return this.time;
    }
    
    public void setTime(int time) {
        this.time = time;
    }
    
    public String getOpenTime() {
        return this.openTime;
    }
    
    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }
    
    public String getTalk() {
        return this.talk;
    }
    
    public void setTalk(String talk) {
        this.talk = talk;
    }
    
    public int[] getLvls() {
        return this.lvls;
    }
    
    public void setLvls(int[] lvls) {
        this.lvls = lvls;
    }
    
    public String[] getConsumes() {
        return this.consumes;
    }
    
    public void setConsumes(String[] consumes) {
        this.consumes = consumes;
    }
    
    public TaskTerm[] getTaskTerms() {
        return this.taskTerms;
    }
    
    public void setTaskTerms(TaskTerm[] taskTerms) {
        this.taskTerms = taskTerms;
    }
    
    public List<Integer> getPostTaskIds() {
        return this.postTaskIds;
    }
    
    public void setPostTaskIds(List<Integer> postTaskIds) {
        this.postTaskIds = postTaskIds;
    }
    
    public DropModel getDropModel() {
        return this.dropModel;
    }
    
    public void setDropModel(DropModel dropModel) {
        this.dropModel = dropModel;
    }
    
    public int getIsTP() {
        return this.isTP;
    }
    
    public void setIsTP(int isTP) {
        this.isTP = isTP;
    }
    
    public String getTaskName() {
        return this.taskName;
    }
    
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    
    public TaskTime[] getTaskTimes() {
        return this.taskTimes;
    }
    
    public void setTaskTimes(TaskTime[] taskTimes) {
        this.taskTimes = taskTimes;
    }
    
    public TaskSet getTaskSet() {
        if (this.taskSet == null) {
            this.taskSet = GameServer.getTaskSet(this.taskSetID);
        }
        return this.taskSet;
    }
    
    public void setTaskSet(TaskSet taskSet) {
        this.taskSet = taskSet;
    }
    
    public int getNewTaskId() {
        if (this.postTaskIds == null || this.postTaskIds.size() == 0) {
            return 0;
        }
        return (int)this.postTaskIds.get(GameServer.random.nextInt(this.postTaskIds.size()));
    }
    
    public int getNd() {
        return this.nd;
    }
    
    public void setNd(int nd) {
        this.nd = nd;
    }
    
    public String getSnames() {
        return this.snames;
    }
    
    public void setSnames(String snames) {
        this.snames = snames;
    }
    
    public String getSex() {
        return this.sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }
}
