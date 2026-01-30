package come.tool.Fighting;

import java.awt.Graphics;
import org.come.bean.PathPoint;
import java.util.List;
import com.tool.tcp.NewPart;

public class ShadowMode
{
    private NewPart part;
    private int x;
    private int y;
    private List<PathPoint> paths;
    private int time;
    private int dir;
    
    public ShadowMode(NewPart part, int x, int y, List<PathPoint> paths, int dir) {
        this.part = part;
        this.x = x;
        this.y = y;
        this.paths = paths;
        this.dir = dir;
    }
    
    public void draw(Graphics g) {
        this.part.draw(g, this.x, this.y, this.dir, (long)this.time);
    }
    
    public boolean addTime(long time) {
        this.time = (int)((double)this.time + (double)time * 2.5);
        if (this.paths != null) {
            return FightingMove2.getmovetime(this, this.paths);
        }
        if (this.time >= this.part.getTime()) {
            this.time = this.part.getTime();
            return true;
        }
        return false;
    }
    
    public NewPart getPart() {
        return this.part;
    }
    
    public void setPart(NewPart part) {
        this.part = part;
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
    
    public List<PathPoint> getPaths() {
        return this.paths;
    }
    
    public void setPaths(List<PathPoint> paths) {
        this.paths = paths;
    }
    
    public int getTime() {
        return this.time;
    }
    
    public void setTime(int time) {
        this.time = time;
    }
    
    public int getDir() {
        return this.dir;
    }
    
    public void setDir(int dir) {
        this.dir = dir;
    }
}
