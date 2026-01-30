package com.tool.imagemonitor;

public class ScriptEnd
{
    private int type;
    private int map;
    private int x;
    private int y;
    
    public ScriptEnd(int type, int map, int x, int y) {
        this.type = type;
        this.map = map;
        this.x = x;
        this.y = y;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
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
}
