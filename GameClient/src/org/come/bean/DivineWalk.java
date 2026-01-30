package org.come.bean;

import com.tool.tcp.NewPart;

public class DivineWalk
{
    private NewPart part2;
    private int x;
    private int y;
    private int dir;
    
    public DivineWalk(NewPart part2, int x, int y, int dir) {
        this.part2 = part2;
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
    
    public NewPart getPart2() {
        return this.part2;
    }
    
    public void setPart2(NewPart part2) {
        this.part2 = part2;
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
    
    public int getDir() {
        return this.dir;
    }
    
    public void setDir(int dir) {
        this.dir = dir;
    }
}
