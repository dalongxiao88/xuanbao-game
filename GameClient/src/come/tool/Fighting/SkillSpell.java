package come.tool.Fighting;

import com.tool.tcp.SpriteFactory;
import java.awt.Graphics;
import com.tool.tcp.Sprite;

public class SkillSpell
{
    private String skillid;
    private Sprite skill;
    private long dietime;
    private int skinpath;
    private int dir;
    private int x;
    private int y;
    private ShadowMode shadowMode;
    private int Round;
    
    public void draw(Graphics g) {
        if (this.shadowMode != null) {
            this.shadowMode.draw(g);
        }
        else if (this.skill != null) {
            this.skill.updateToTime(this.dietime, this.dir);
            this.skill.draw(g, this.x, this.y, this.skillid);
        }
    }
    
    public SkillSpell() {
    }
    
    public SkillSpell(String skinid, int x, int y) {
        this.skillid = skinid;
        this.dietime = 0L;
        this.x = x;
        this.y = y;
    }
    
    public String getSkillid() {
        return this.skillid;
    }
    
    public void setSkillid(String skillid) {
        this.skillid = skillid;
    }
    
    public boolean getDietime(long dietime) {
        if (this.shadowMode != null) {
            return this.shadowMode.addTime(dietime);
        }
        this.dietime += dietime;
        if (this.skill == null) {
            this.skill = SpriteFactory.Prepare(this.skillid);
        }
        if (this.skill != null) {
            return this.dietime > (long)this.skill.getTime();
        }
        return this.dietime > 3000L;
    }
    
    public void setDietime(long dietime) {
        this.dietime = dietime;
    }
    
    public int getSkinpath() {
        return this.skinpath;
    }
    
    public void setSkinpath(int skinpath) {
        this.skinpath = skinpath;
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
    
    public ShadowMode getShadowMode() {
        return this.shadowMode;
    }
    
    public void setShadowMode(ShadowMode shadowMode) {
        this.shadowMode = shadowMode;
    }
    
    public Sprite getSkill() {
        return this.skill;
    }
    
    public void setSkill(Sprite skill) {
        this.skill = skill;
    }
    
    public int getDir() {
        return this.dir;
    }
    
    public void setDir(int dir) {
        this.dir = dir;
    }
    
    public long getDietime() {
        return this.dietime;
    }
    
    public int getRound() {
        return this.Round;
    }
    
    public void setRound(int round) {
        this.Round = round;
    }
}
