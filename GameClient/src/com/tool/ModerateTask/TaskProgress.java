package com.tool.ModerateTask;

public class TaskProgress
{
    private int sum;
    private int max;
    private int type;
    private int DId;
    private String DName;
    private String title;
    private int map;
    private int x;
    private int y;
    private int GId;
    private String GName;
    private Task task;
    
    public TaskProgress(Task task) {
        this.task = task;
    }
    
    public void addSum(int add) {
        this.sum += add;
        if (this.sum > this.max) {
            this.sum = this.max;
        }
    }
    
    public int getSum() {
        return this.sum;
    }
    
    public void setSum(int sum) {
        this.sum = sum;
    }
    
    public int getMax() {
        return this.max;
    }
    
    public void setMax(int max) {
        this.max = max;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public int getDId() {
        return this.DId;
    }
    
    public void setDId(int dId) {
        this.DId = dId;
    }
    
    public String getDName() {
        return this.DName;
    }
    
    public void setDName(String dName) {
        this.DName = dName;
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
    
    public int getGId() {
        return this.GId;
    }
    
    public void setGId(int gId) {
        this.GId = gId;
    }
    
    public String getGName() {
        return this.GName;
    }
    
    public void setGName(String gName) {
        this.GName = gName;
    }
    
    public Task getTask() {
        return this.task;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
}
