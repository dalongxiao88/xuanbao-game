package come.tool.Fighting;

import java.util.List;
import java.util.ArrayList;
import org.come.bean.PathPoint;
import com.tool.image.ManimgAttribute;
import org.come.bean.Mapmodel;
import java.util.Random;

public class RandomCreepsMovePath extends CreepsMovePath
{
    private long sleep;
    Random random;
    Mapmodel mapmodel;
    
    public RandomCreepsMovePath(int x, int y) {
        super(x, y, 100, null);
        this.sleep = 0L;
        this.random = new Random();
        this.random();
        this.sleep = System.currentTimeMillis() / 1000L + (long)this.random.nextInt(5);
    }
    
    @Override
    public boolean isMove(ManimgAttribute attribute, long addTime, int dir) {
        if ("".equals(attribute.getName()) || "花果山下塔".equals(attribute.getName()) || "大本营".equals(attribute.getName()) || "天庭下塔".equals(attribute.getName()) || "天庭中塔".equals(attribute.getName()) || "天庭上塔".equals(attribute.getName()) || "花果山中塔".equals(attribute.getName()) || "花果山上塔".equals(attribute.getName())) {
            attribute.setX((int)attribute.getMapMonsterBean().getX());
            attribute.setY((int)attribute.getMapMonsterBean().getY());
            attribute.setType(2);
            return false;
        }
        if (addTime > 0L && this.random.nextInt(50) == 0) {
            this.sleep = System.currentTimeMillis() / 500L + (long)this.random.nextInt(7) + 3L;
            this.random();
            attribute.setType(2);
        }
        if (System.currentTimeMillis() / 500L < this.sleep) {
            return false;
        }
        this.time = (int)((long)this.time + addTime);
        if (this.time <= 0) {
            return false;
        }
        if (this.Z >= this.points.size()) {
            this.random();
            this.Z = 0;
        }
        PathPoint point = (PathPoint)this.points.get(this.Z);
        int dx = point.getX() - this.x;
        int dy = point.getY() - this.y;
        long endtime = FightingMove2.gettime((long)dx, (long)dy, 0.05);
        if ((long)this.time >= endtime) {
            if ((long)this.time > endtime * 100L) {
                this.time = (int)(endtime * 100L);
            }
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
        else {
            dir = FightingMove2.getdir(dx, dy, dir);
            attribute.setX(FightingMove2.getmove2(this.x, point.getX(), (long)this.time, endtime));
            attribute.setY(FightingMove2.getmove2(this.y, point.getY(), (long)this.time, endtime));
            attribute.getMapMonsterBean().setX(Integer.valueOf(attribute.getX()));
            attribute.getMapMonsterBean().setY(Integer.valueOf(attribute.getY()));
            attribute.setDir(dir);
            attribute.setType(1);
            return false;
        }
    }
    
    public void random() {
        float addX = (float)((0.5 - (double)this.random.nextFloat()) * 6.0);
        float addY = (float)((0.5 - (double)this.random.nextFloat()) * 6.0);
        if (Math.abs(addX) <= 1.0f) {
            if (addX > 0.0f) {
                ++addX;
            }
            if (addX < 0.0f) {
                --addX;
            }
        }
        if (Math.abs(addY) <= 1.0f) {
            if (addY > 0.0f) {
                ++addY;
            }
            if (addY < 0.0f) {
                --addY;
            }
        }
        this.points = new ArrayList<>();
        for (int i = 1; i <= 80; ++i) {
            this.points.add(new PathPoint(Math.round((float)this.x + addX * (float)i), Math.round((float)this.y + addY * (float)i)));
        }
    }
}
