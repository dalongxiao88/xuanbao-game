package com.tool.imagemonitor;

import org.come.Jpanel.ZhuShouTaskJpanel;

public class ScriptOpen
{
    private int type;
    private int x;
    private int y;
    private int npc;
    private int door;
    private ZhuShouTaskJpanel zhuShouTaskJpanel;
    
    public ScriptOpen() {
    }
    
    public ScriptOpen(int type) {
        this.type = type;
    }
    
    public ScriptOpen(int type, int npc, int door) {
        this.type = type;
        this.npc = npc;
        this.door = door;
    }
    
    public ScriptOpen(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
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
    
    public int getNpc() {
        return this.npc;
    }
    
    public void setNpc(int npc) {
        this.npc = npc;
    }
    
    public int getDoor() {
        return this.door;
    }
    
    public void setDoor(int door) {
        this.door = door;
    }
    
    public ZhuShouTaskJpanel getZhuShouTaskJpanel() {
        return this.zhuShouTaskJpanel;
    }
    
    public void setZhuShouTaskJpanel(ZhuShouTaskJpanel zhuShouTaskJpanel) {
        this.zhuShouTaskJpanel = zhuShouTaskJpanel;
    }
}
