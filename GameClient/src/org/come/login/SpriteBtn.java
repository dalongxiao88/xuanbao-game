package org.come.login;

import java.io.InputStream;
import com.tool.tcp.SpriteFactory;
import java.awt.event.MouseListener;
import java.awt.Graphics;
import com.tool.tcp.Sprite;
import javax.swing.JLabel;

public class SpriteBtn extends JLabel
{
    private Sprite sprite;
    private Sprite sprite1;
    private int zhen;
    private int sx;
    private int sy;
    private int type;
    private int skx;
    private int sky;
    private boolean choose;
    boolean is;
    long time;
    
    public SpriteBtn() {
        this.is = true;
        this.time = 0L;
    }
    
    public void draw(Graphics g) {
        if (this.zhen == 2 && this.sprite1 != null) {
            if (this.is) {
                this.time += 20L;
                this.sprite1.updateToTime(this.time, 0);
                this.sprite1.draw(g, this.sx, this.sy);
                if (this.time >= 500L) {
                    this.time = 0L;
                    this.is = false;
                    MouseListener[] mouseListeners = this.getMouseListeners();
                    RaceMouslistenn mouseListener = (RaceMouslistenn)mouseListeners[0];
                    mouseListener.raceSelected();
                }
            }
        }
        else {
            this.is = true;
            this.sprite.draw(g, this.sx, this.sy);
        }
    }
    
    public SpriteBtn(String spritepath, int sx, int sy, boolean choose) {
        this.is = true;
        this.time = 0L;
        (this.sprite = SpriteFactory.VloadSprite(spritepath, null)).updateToTime(-1L, 0);
        this.btn(1);
        this.btn(0);
        this.zhen = 0;
        this.sx = sx;
        this.sy = sy;
        this.choose = choose;
    }
    
    public SpriteBtn(String spritepath, String spritepath1, int sx, int sy, boolean choose) {
        this.is = true;
        this.time = 0L;
        this.sprite = SpriteFactory.VloadSprite(spritepath, null);
        this.sprite1 = SpriteFactory.VloadSprite(spritepath1, null);
        this.sprite.updateToTime(-1L, 0);
        this.sprite1.updateToTime(-1L, 0);
        this.btn(1);
        this.btn(0);
        this.zhen = 0;
        this.sx = sx;
        this.sy = sy;
        this.choose = choose;
    }
    
    public SpriteBtn(String spritepath, int sx, int sy, boolean choose, InputStream in) {
        this.is = true;
        this.time = 0L;
        (this.sprite = SpriteFactory.VloadSprite1(spritepath, in, null)).updateToTime(-1L, 0);
        this.btn(1);
        this.btn(0);
        this.zhen = 0;
        this.sx = sx;
        this.sy = sy;
        this.choose = choose;
    }
    
    public SpriteBtn(String spritepath, int sx, int sy, boolean choose, int type, int skx, int sky) {
        this.is = true;
        this.time = 0L;
        (this.sprite = SpriteFactory.VloadSprite(spritepath, null)).updateToTime(-1L, 0);
        if (type == 1) {
            this.sprite.updateToTime(100L, 0);
        }
        this.btn(1);
        this.btn(0);
        this.zhen = 0;
        this.sx = sx;
        this.sy = sy;
        this.choose = choose;
        this.type = type;
        this.skx = skx;
        this.sky = sky;
    }
    
    public void btn(int type) {
        if (this.type == 0) {
            this.uptime(type);
        }
        else if (this.type == 1) {
            if (type == 1) {
                this.uptime(2);
            }
            else {
                this.uptime(type);
            }
        }
        this.zhen = type;
    }
    
    public void uptime(int zhen) {
        if (zhen == 1) {
            this.sprite.updateToTime(-1L, 0);
        }
        else if (zhen == 0) {
            this.sprite.updateToTime(99L, 0);
        }
        else {
            this.sprite.updateToTime(199L, 0);
        }
    }
    
    public Sprite getSprite() {
        return this.sprite;
    }
    
    public void setSprite(String spritepath) {
        (this.sprite = SpriteFactory.VloadSprite(spritepath, null)).updateToTime(-1L, 0);
        if (this.type == 1) {
            this.sprite.updateToTime(100L, 0);
        }
    }
    
    public int getZhen() {
        return this.zhen;
    }
    
    public void setZhen(int zhen) {
        this.zhen = zhen;
    }
    
    public int getSx() {
        return this.sx;
    }
    
    public void setSx(int sx) {
        this.sx = sx;
    }
    
    public int getSy() {
        return this.sy;
    }
    
    public void setSy(int sy) {
        this.sy = sy;
    }
    
    public boolean isChoose() {
        return this.choose;
    }
    
    public void setChoose(boolean choose) {
        this.choose = choose;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public int getSkx() {
        return this.skx;
    }
    
    public void setSkx(int skx) {
        this.skx = skx;
    }
    
    public int getSky() {
        return this.sky;
    }
    
    public void setSky(int sky) {
        this.sky = sky;
    }
}
