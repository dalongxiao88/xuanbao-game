package org.come.task;

import org.come.bean.PathPoint;
import java.math.BigDecimal;

public class MonsterFollow
{
    private BigDecimal followID;
    private PathPoint[] points;
    private long time;
    public static long PROTECTTIME;
    
    public MonsterFollow(PathPoint[] points) {
        this.points = points;
    }
    
    public BigDecimal getFollowID() {
        return this.followID;
    }
    
    public void setFollowID(BigDecimal followID) {
        this.followID = followID;
    }
    
    public PathPoint[] getPoints() {
        return this.points;
    }
    
    public void setPoints(PathPoint[] points) {
        this.points = points;
    }
    
    public long getTime() {
        return this.time;
    }
    
    public void setTime(long time) {
        this.time = time;
    }
    
    public String isTime() {
        if (this.time == 0L) {
            return null;
        }
        long JG = MonsterFollow.PROTECTTIME - (System.currentTimeMillis() - this.time) / 1000L;
        if (JG > 0L) {
            return "剩余" + JG + "秒的保护期";
        }
        return null;
    }
    
    static {
        MonsterFollow.PROTECTTIME = 4L;
    }
}
