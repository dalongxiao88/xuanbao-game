package com.tool.imagemonitor;

public class ScriptTask
{
    private int taskSetID;
    private int sum;
    private int npcID;
    private int map;
    private int x;
    private int y;
    private int ygRobotId;
    private int ygMapId;
    private int ygX;
    private int ygY;
    private int taskIdExt;
    
    public int getTaskIdExt() {
        return this.taskIdExt;
    }
    
    public void setTaskIdExt(int taskIdExt) {
        this.taskIdExt = taskIdExt;
    }
    
    public ScriptTask() {
    }
    
    public ScriptTask(String[] vs, int taskSetID, int sum) {
        this.taskSetID = taskSetID;
        this.map = Integer.parseInt(vs[0]);
        this.x = Integer.parseInt(vs[1]);
        this.y = Integer.parseInt(vs[2]);
        this.npcID = Integer.parseInt(vs[3]);
        this.sum = sum;
    }
    
    public ScriptTask(String[] vs, int taskSetID) {
        // TODO Auto-generated constructor stub
        this.taskSetID = taskSetID;
        this.map = Integer.parseInt(vs[0]);
        this.x = Integer.parseInt(vs[1]);
        this.y = Integer.parseInt(vs[2]);
        this.npcID = Integer.parseInt(vs[3]);
        this.sum = Integer.parseInt(vs[4]);
    }
    
    public int getTaskSetID() {
        return this.taskSetID;
    }
    
    public void setTaskSetID(int taskSetID) {
        this.taskSetID = taskSetID;
    }
    
    public int upSum() {
        return this.sum--;
    }
    
    public int getNpcID() {
        return this.npcID;
    }
    
    public void setNpcID(int npcID) {
        this.npcID = npcID;
    }
    
    public int getMap() {
        return this.map;
    }
    
    public void setMap(int map) {
        this.map = map;
    }
    
    public int getX() {
        return this.x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public int getSum() {
        return this.sum;
    }
    
    public void setSum(int sum) {
        this.sum = sum;
    }
    
    public int getYgRobotId() {
        return this.ygRobotId;
    }
    
    public void setYgRobotId(int ygRobotId) {
        this.ygRobotId = ygRobotId;
    }
    
    public int getYgMapId() {
        return this.ygMapId;
    }
    
    public void setYgMapId(int ygMapId) {
        this.ygMapId = ygMapId;
    }
    
    public int getYgX() {
        return this.ygX;
    }
    
    public void setYgX(int ygX) {
        this.ygX = ygX;
    }
    
    public int getYgY() {
        return this.ygY;
    }
    
    public void setYgY(int ygY) {
        this.ygY = ygY;
    }
}
