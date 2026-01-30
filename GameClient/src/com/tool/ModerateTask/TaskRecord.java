package com.tool.ModerateTask;

public class TaskRecord
{
    private int taskId;
    private int rSum;
    private int cSum;
    private int newID;
    
    public TaskRecord(int taskId) {
        this.taskId = taskId;
    }
    
    public int getTaskId() {
        return this.taskId;
    }
    
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
    
    public int getrSum() {
        return this.rSum;
    }
    
    public void addRSum(int add) {
        this.rSum += add;
    }
    
    public int getcSum() {
        return this.cSum;
    }
    
    public void addCSum(int add) {
        this.cSum += add;
    }
    
    public int getNewID() {
        return this.newID;
    }
    
    public void setNewID(int newID) {
        this.newID = newID;
    }
}
