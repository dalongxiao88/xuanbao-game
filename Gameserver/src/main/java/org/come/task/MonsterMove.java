package org.come.task;

import org.come.bean.PathPoint;

public class MonsterMove
{
    private MonsterMoveBase moveBase;
    private int endTime;
    private int time;
    
    public MonsterMove(MonsterMoveBase moveBase, int time, int x, long l) {
        this.moveBase = moveBase;
        this.time = time;
        this.endTime = moveBase.getEndTime();
        PathPoint point = (PathPoint)this.moveBase.getPoints().get(0);
        this.endTime = (int)((double)this.endTime + MonsterMoveBase.gettime((long)(point.getX() - x), (long)point.getY() - l, 0.07));
    }
    
    public boolean isMove(int addTime) {
        this.time += addTime;
        return this.time >= this.endTime;
    }
    
    public int getBh() {
        return this.moveBase.getBh();
    }
    
    public MonsterMoveBase getMoveBase() {
        return this.moveBase;
    }
    
    public int getTime() {
        return this.time;
    }
    
    public void setTime(int time) {
        this.time = time;
    }
}
