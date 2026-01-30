package org.come.model;

public class TaskProgress
{
    private int sum;
    private int max;
    private int type;
    private int DId;
    private String DName;
    private int map;
    private int x;
    private long y;
    private int GId;
    private String GName;
    
    public TaskProgress() {
    }
    
    public TaskProgress(TaskProgress progress) {
        this.sum = progress.sum;
        this.max = progress.max;
        this.type = progress.type;
        this.DId = progress.DId;
        this.DName = progress.DName;
        this.map = progress.map;
        this.x = progress.x;
        this.y = progress.y;
        this.GId = progress.GId;
        this.GName = progress.GName;
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
    
    public long getY() {
        return this.y;
    }
    
    public void setY(long l) {
        this.y = l;
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
}
