package come.tool.Fighting;

import com.tool.tcp.Sprite;
import com.tool.tcp.SpriteFactory;
import java.awt.Graphics;
import com.tool.tcp.GetTcpPath;

public class Buff
{
    private int id;
    private String type;
    private int camp;
    private String skin;
    private long time;
    private boolean isv;
    private int x;
    private int y;
    private float alpha;
    
    public Buff(String[] v) {
        this.alpha = 1.0f;
        this.id = Integer.parseInt(v[1]);
        this.camp = Integer.parseInt(v[2]);
        this.type = v[3];
        this.skin = GetTcpPath.getBuffTcp(v[3]);
    }
    
    public void draw(Graphics g) {
        this.time += 18L;
        if (this.isv) {
            Sprite sprite = SpriteFactory.Prepare(this.skin);
            if (sprite != null) {
                sprite.updateToTime(this.time, 0);
                sprite.draw(g, this.x, this.y, this.alpha);
            }
        }
    }
    
    public void draw2(Graphics g) {
        if (this.isv) {
            Sprite sprite = SpriteFactory.Prepare(this.skin);
            if (sprite != null) {
                sprite.updateToTime(this.time, 0);
                sprite.draw(g, this.x, this.y);
            }
        }
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public int getCamp() {
        return this.camp;
    }
    
    public void setCamp(int camp) {
        this.camp = camp;
    }
    
    public String getSkin() {
        return this.skin;
    }
    
    public void setSkin(String skin) {
        this.skin = skin;
    }
    
    public long getTime() {
        return this.time;
    }
    
    public void setTime(long time) {
        this.time = time;
    }
    
    public boolean isIsv() {
        return this.isv;
    }
    
    public void setIsv(boolean isv) {
        this.isv = isv;
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
    
    public float getAlpha() {
        return this.alpha;
    }
    
    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }
}
