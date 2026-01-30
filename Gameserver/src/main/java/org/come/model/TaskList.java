package org.come.model;
//副本
public class TaskList {
    private int taskID;
    private int taskSetID;
    private String taskName;
    private int role;
    private String lvl;
    private int num;
    private int resetcycle;
    private String drops;
    private String hard;
    private String time;
    private String NPCID;

    public TaskList() {
        super();
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public int getTaskSetID() {
        return taskSetID;
    }

    public void setTaskSetID(int taskSetID) {
        this.taskSetID = taskSetID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getLvl() {
        return lvl;
    }

    public void setLvl(String lvl) {
        this.lvl = lvl;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getResetcycle() {
        return resetcycle;
    }

    public void setResetcycle(int resetcycle) {
        this.resetcycle = resetcycle;
    }

    public String getDrops() {
        return drops;
    }

    public void setDrops(String drops) {
        this.drops = drops;
    }
    public String getHard() {
        return hard;
    }

    public void setHard(String hard) {
        this.hard = hard;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
