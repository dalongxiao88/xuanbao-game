package com.tool.tcp;

import java.awt.Graphics;

public interface NewPart
{
    boolean contains(int p0, int p1);
    
    void recycle();
    
    int getTime();
    
    void loadTcp();
    
    int getAct();
    
    void setAct(int p0);
    
    NewPart addPart(NewPart p0);
    
    NewPart removePart(String p0);
    
    int getLvl();
    
    NewPart getPart();
    
    void setPart(NewPart p0);
    
    void clearGwPart(NewPart p0);
    
    NewPart setPart(int p0, String p1);
    
    NewPart setPart(int p0, long p1, HHOne[] p2);
    
    int getPy();
    
    NewPart clonePart();
    
    int getAnimationCount();
    
    void draw(Graphics p0, int p1, int p2, int p3, long p4);
    
    void draw(Graphics p0, int p1, int p2, int p3, long p4, float p5);
    
    void drawEnd(Graphics p0, int p1, int p2, int p3, float p4);
    
    void drawBattle(Graphics p0, int p1, int p2, int p3, long p4, float p5);
    
    void setFly(String p0, int p1, Boolean p2);
    
    void drawFly(Graphics p0, int p1, int p2, int p3, long p4, float p5);
    
    void setFlyShadow(String p0, int p1);
    
    void drawFlyShadow(Graphics p0, int p1, int p2, int p3, long p4, float p5);
    
    Sprite getTCP();
    
    void setGl(Boolean p0);
}
