package com.tool.tcp;

import java.awt.Graphics;

/**
 * 客户端组合部件绘制接口。
 *
 * 该接口用于统一人物、坐骑、飞行器、光环、阴影等组合部件的绘制与叠加行为。
 */
public interface NewPart
{
    boolean contains(int x, int y);
    
    void recycle();
    
    int getTime();
    
    void loadTcp();
    
    int getAct();
    
    void setAct(int action);
    
    NewPart addPart(NewPart part);
    
    NewPart removePart(String partName);
    
    int getLvl();
    
    NewPart getPart();
    
    void setPart(NewPart part);
    
    void clearGwPart(NewPart part);
    
    NewPart setPart(int index, String resourcePath);
    
    NewPart setPart(int index, long skinId, HHOne[] hhOnes);
    
    int getPy();
    
    NewPart clonePart();
    
    int getAnimationCount();
    
    void draw(Graphics graphics, int x, int y, int direction, long nowTime);
    
    void draw(Graphics graphics, int x, int y, int direction, long nowTime, float alpha);
    
    void drawEnd(Graphics graphics, int x, int y, int direction, float alpha);
    
    void drawBattle(Graphics graphics, int x, int y, int direction, long nowTime, float alpha);
    
    void setFly(String skin, int action, Boolean visible);
    
    void drawFly(Graphics graphics, int x, int y, int direction, long nowTime, float alpha);
    
    void setFlyShadow(String skin, int action);
    
    void drawFlyShadow(Graphics graphics, int x, int y, int direction, long nowTime, float alpha);
    
    Sprite getTCP();
    
    void setGl(Boolean visible);
}
