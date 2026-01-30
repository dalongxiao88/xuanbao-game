package com.tool.tcpimg;

public class Effects
{
    private String EffectsName;
    private long time;
    private int x;
    private int y;
    
    public Effects(String effectsName, int x, int y) {
        this.EffectsName = effectsName;
        this.time = 0L;
        this.x = x;
        this.y = y;
    }
    
    public String getEffectsName() {
        return this.EffectsName;
    }
    
    public void setEffectsName(String effectsName) {
        this.EffectsName = effectsName;
    }
    
    public long getTime() {
        return this.time;
    }
    
    public void setTime(long time) {
        this.time = time;
    }
    
    public void addTime(long time) {
        this.time += time;
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
}
