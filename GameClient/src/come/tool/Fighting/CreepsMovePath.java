package come.tool.Fighting;

import com.tool.image.ManimgAttribute;
import org.come.bean.PathPoint;
import java.util.List;

public class CreepsMovePath
{
    protected int x;
    protected int y;
    protected int Z;
    protected int time;
    protected List<PathPoint> points;
    
    public CreepsMovePath(int x, int y, int time, List<PathPoint> points) {
        this.x = x;
        this.y = y;
        this.time = time;
        this.points = points;
    }
    
    public boolean isMove(ManimgAttribute attribute, long addTime, int dir) {
        this.time = (int)((long)this.time + addTime);
        if (this.time <= 0) {
            return false;
        }
        if (this.Z >= this.points.size()) {
            return true;
        }
        PathPoint point = (PathPoint)this.points.get(this.Z);
        int dx = point.getX() - this.x;
        int dy = point.getY() - this.y;
        long endtime = FightingMove2.gettime((long)dx, (long)dy, 0.07);
        if ((long)this.time >= endtime) {
            this.time = (int)((long)this.time - endtime);
            ++this.Z;
            this.x = point.getX();
            this.y = point.getY();
            attribute.setX(point.getX());
            attribute.setY(point.getY());
            attribute.getMapMonsterBean().setX(Integer.valueOf(point.getX()));
            attribute.getMapMonsterBean().setY(Integer.valueOf(point.getY()));
            return this.isMove(attribute, 0L, dir);
        }
        dir = FightingMove2.getdir(dx, dy, dir);
        attribute.setX(FightingMove2.getmove2(this.x, point.getX(), (long)this.time, endtime));
        attribute.setY(FightingMove2.getmove2(this.y, point.getY(), (long)this.time, endtime));
        attribute.getMapMonsterBean().setX(Integer.valueOf(attribute.getX()));
        attribute.getMapMonsterBean().setY(Integer.valueOf(attribute.getY()));
        attribute.setDir(dir);
        attribute.setType(1);
        return false;
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
    
    public int getTime() {
        return this.time;
    }
    
    public void setTime(int time) {
        this.time = time;
    }
    
    public List<PathPoint> getPoints() {
        return this.points;
    }
    
    public void setPoints(List<PathPoint> points) {
        this.points = points;
    }
}
