package com.tool.ModerateTask;

public class TaskSet
{
    private int taskSetID;
    private String taskType;
    private int sumcycle;
    private int sumlimit;
    private int sumreceive;
    private int resetcycle;
    private int taskMsgID;
    private String taskMsg;
    
    public double XSReward(int ComSum) {
        if (this.sumcycle == 0) {
            ComSum = 0;
        }
        else {
            ComSum %= this.sumcycle;
        }
        double xs = 1.0;
        xs += 0.05 * (double)ComSum;
        return xs;
    }
    
    public int getTaskSetID() {
        return this.taskSetID;
    }
    
    public void setTaskSetID(int taskSetID) {
        this.taskSetID = taskSetID;
    }
    
    public String getTaskType() {
        return this.taskType;
    }
    
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
    
    public int getSumcycle() {
        return this.sumcycle;
    }
    
    public void setSumcycle(int sumcycle) {
        this.sumcycle = sumcycle;
    }
    
    public int getSumlimit() {
        return this.sumlimit;
    }
    
    public void setSumlimit(int sumlimit) {
        this.sumlimit = sumlimit;
    }
    
    public int getSumreceive() {
        return this.sumreceive;
    }
    
    public void setSumreceive(int sumreceive) {
        this.sumreceive = sumreceive;
    }
    
    public int getResetcycle() {
        return this.resetcycle;
    }
    
    public void setResetcycle(int resetcycle) {
        this.resetcycle = resetcycle;
    }
    
    public int getTaskMsgID() {
        return this.taskMsgID;
    }
    
    public void setTaskMsgID(int taskMsgID) {
        this.taskMsgID = taskMsgID;
    }
    
    public String getTaskMsg() {
        return this.taskMsg;
    }
    
    public void setTaskMsg(String taskMsg) {
        this.taskMsg = taskMsg;
    }
}
