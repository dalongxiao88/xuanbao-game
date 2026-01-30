package com.tool.tcp;

import org.come.until.Arith;
import java.awt.Composite;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.AlphaComposite;
import java.awt.geom.AffineTransform;

public class Sprite
{
    private int Use;
    private int load;
    private Frame[] frames;
    private Frame currFrame;
    private int animCount;
    private int frameCount;
    private int refPixelX;
    private int refPixelY;
    private int hightMax;
    private SpriteHead head;
    private Boolean b;
    private boolean isHigh;
    private boolean isHigh1;
    private static AffineTransform transform;
    public static AlphaComposite Composite06;
    
    public Sprite(Image image) {
        this.Use = 0;
        this.load = -1;
        this.isHigh = false;
        this.isHigh1 = false;
        (this.frames = new Frame[1])[0] = new Frame(image, 0, 0);
        this.currFrame = this.frames[0];
        this.animCount = 1;
        this.frameCount = 1;
    }
    
    public Sprite(Frame[] frames, int animCount, int frameCount, SpriteHead head, int refPixelX, int refPixelY) {
        this.Use = 0;
        this.load = -1;
        this.isHigh = false;
        this.isHigh1 = false;
        this.frames = frames;
        this.currFrame = frames[0];
        this.animCount = animCount;
        this.frameCount = frameCount;
        this.head = head;
        this.refPixelX = refPixelX;
        this.refPixelY = refPixelY;
    }
    
    public Sprite(Frame[] frames, int animCount, int frameCount, SpriteHead head, int refPixelX, int refPixelY, Boolean b) {
        this.Use = 0;
        this.load = -1;
        this.isHigh = false;
        this.isHigh1 = false;
        this.frames = frames;
        this.currFrame = frames[0];
        this.animCount = animCount;
        this.frameCount = frameCount;
        this.head = head;
        this.refPixelX = refPixelX;
        this.refPixelY = refPixelY;
        this.b = b;
    }
    
    public boolean contains(int x, int y) {
        try {
            if (this.currFrame.getImage() == null) {
                return false;
            }
            Image bi = this.currFrame.getImage();
            if (this.currFrame.getPos() != 0) {
                x = this.currFrame.getRefPixelX() - x;
            }
            else {
                x += this.currFrame.getRefPixelX();
            }
            y += this.currFrame.getRefPixelY();
            if (x >= 0 && x < bi.getWidth(null) && y >= 0 && y < bi.getHeight(null)) {
                if (bi instanceof BufferedImage) {
                    BufferedImage bimage = (BufferedImage)bi;
                    return bimage.getRGB(x, y) != 0;
                }
                return true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Boolean getB() {
        return this.b;
    }
    
    public void setB(Boolean b) {
        this.b = b;
    }
    
    public void draw(Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D)g.create();
        if (this.currFrame.getImage() != null) {
            if (this.currFrame.getPos() == 0) {
                x -= this.currFrame.getRefPixelX();
                y -= this.currFrame.getRefPixelY();
                if (this.isHigh || this.isHigh1) {
                    g.drawImage(this.currFrame.HighTcp(this.currFrame.getImage()), x, y, this.currFrame.getWidth(), this.currFrame.getHeight(), null);
                    g.drawImage(this.currFrame.HighTcp(this.currFrame.getImage()), x, y, this.currFrame.getWidth(), this.currFrame.getHeight(), null);
                }
                else {
                    g.drawImage(this.currFrame.getImage(), x, y, this.currFrame.getWidth(), this.currFrame.getHeight(), null);
                }
            }
            else {
                x += this.currFrame.getRefPixelX();
                y -= this.currFrame.getRefPixelY();
                Sprite.transform.setToIdentity();
                Sprite.transform.scale(-1.0, 1.0);
                Sprite.transform.translate((double)(-x), (double)y);
                if (this.isHigh || this.isHigh1) {
                    g2.drawImage(this.currFrame.HighTcp(this.currFrame.getImage()), Sprite.transform, null);
                    g2.drawImage(this.currFrame.HighTcp(this.currFrame.getImage()), Sprite.transform, null);
                }
                else {
                    g2.drawImage(this.currFrame.getImage(), Sprite.transform, null);
                }
            }
        }
    }
    
    public void drawfly(Graphics g, int x, int y) {
        if (this.currFrame.getImage() != null) {
            if (this.currFrame.getPos() == 0) {
                x -= this.currFrame.getRefPixelX();
                y -= this.currFrame.getRefPixelY();
                g.drawImage(this.currFrame.getImage(), x, y, this.currFrame.getWidth(), this.currFrame.getHeight(), null);
            }
            else {
                Graphics2D g2 = (Graphics2D)g;
                x += this.currFrame.getRefPixelX();
                y -= this.currFrame.getRefPixelY();
                Sprite.transform.setToIdentity();
                Sprite.transform.scale(-1.0, 1.0);
                Sprite.transform.translate((double)(-x), (double)y);
                g2.drawImage(this.currFrame.getImage(), Sprite.transform, null);
            }
        }
    }
    
    public void drawflyShadow(Graphics g, int x, int y) {
        if (this.currFrame.getImage() != null) {
            if (this.currFrame.getPos() == 0) {
                x -= this.currFrame.getRefPixelX();
                y -= this.currFrame.getRefPixelY();
                g.drawImage(this.currFrame.getImage(), x, y, this.currFrame.getWidth(), this.currFrame.getHeight(), null);
            }
            else {
                Graphics2D g2 = (Graphics2D)g;
                x += this.currFrame.getRefPixelX();
                y -= this.currFrame.getRefPixelY();
                Sprite.transform.setToIdentity();
                Sprite.transform.scale(-1.0, 1.0);
                Sprite.transform.translate((double)(-x), (double)y);
                g2.drawImage(this.currFrame.getImage(), Sprite.transform, null);
            }
        }
    }
    
    public void draw(Graphics g, int x, int y, float alpha) {
        Graphics2D g2 = (Graphics2D)g;
        if (alpha == 1.0f) {
            this.draw(g2, x, y);
        }
        else {
            Composite composite = null;
            try {
                if (alpha == 0.6f) {
                    composite = g2.getComposite();
                    g2.setComposite(Sprite.Composite06);
                }
                else {
                    composite = g2.getComposite();
                    g2.setComposite(AlphaComposite.getInstance(3, alpha));
                }
                this.draw(g2, x, y);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                if (composite != null) {
                    g2.setComposite(composite);
                }
            }
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
    }
    
    public void updateToTime(long playTime, int dir) {
        try {
            if (playTime < 0L) {
                playTime %= (long)this.getTime();
                playTime += (long)this.getTime();
            }
            this.Use = 1;
            dir %= this.animCount;
            playTime /= 100L;
            playTime %= (long)this.frameCount;
            this.currFrame = this.frames[(int)((long)(dir * this.frameCount) + playTime)];
            if (this.load == -1 && this.currFrame.getImage() == null) {
                this.load = dir;
                SpriteFactory.addLoad(this);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int getAnimationCount() {
        return this.animCount;
    }
    
    public int getFrameCount() {
        return this.frameCount;
    }
    
    public int getTime() {
        return this.frameCount * 100;
    }
    
    public SpriteHead getHead() {
        return this.head;
    }
    
    public void setHead(SpriteHead head) {
        this.head = head;
    }
    
    public int getLoad() {
        return this.load;
    }
    
    public void setLoad(int load) {
        this.load = load;
    }
    
    public Frame getLoadFrame(int dir) {
        return this.frames[dir * this.frameCount];
    }
    
    public Frame[] getFrames() {
        return this.frames;
    }
    
    public void removeHead() {
        this.load = -1;
        for (int i = 0; i < this.frames.length; ++i) {
            if (this.frames[i].getImage() == null) {
                this.setHightMax();
                return;
            }
        }
        this.head.remve();
        this.head = null;
        this.setHightMax();
    }
    
    public void setHightMax() {
        int max = -99999;
        for (int i = 0; i < this.frames.length; ++i) {
            Frame frame = this.frames[i];
            if (frame.getImage() != null && frame.getRefPixelY() + frame.getHeight() > max) {
                max = frame.getRefPixelY() + frame.getHeight();
            }
        }
        if (max == -99999) {
            max = 0;
        }
        this.hightMax = max;
        if (this.hightMax >= 200) {
            this.hightMax -= 110;
        }
    }
    
    public int getHightMax() {
        return this.hightMax;
    }
    
    public int getUse() {
        return this.Use;
    }
    
    public void setUse(int use) {
        this.Use = use;
    }
    
    public Frame getCurrFrame() {
        return this.currFrame;
    }
    
    public int getRefPixelX() {
        return this.refPixelX;
    }
    
    public int getRefPixelY() {
        return this.refPixelY;
    }
    
    public void translate(int dir1, int dir2) {
        for (int i = 0; i < this.frameCount; ++i) {
            Frame frame = this.frames[dir1 * this.frameCount + i];
            Frame frame2 = this.frames[dir2 * this.frameCount + i];
            frame2.setImage(frame.getImage(), frame.getRefPixelX(), frame.getRefPixelY());
        }
    }
    
    public void draw(Graphics g, int x, int y, String skillid) {
        if (GetTcpPath.STRTMPXJ != null) {
            if (GetTcpPath.STRTMPXJ.equals("1")) {
                if (this.currFrame.getImage() != null) {
                    if (this.currFrame.getPos() == 0) {
                        x -= this.currFrame.getRefPixelX();
                        y -= this.currFrame.getRefPixelY();
                        Image image = this.currFrame.getImage();
                        if (skillid.contains("1045.") || skillid.contains("1050.") || skillid.contains("1020.") || skillid.contains("1040.") || skillid.contains("1025.")) {
                            int width = (int)Arith.mul((double)this.currFrame.getWidth(), 1.0);
                            int height = (int)Arith.mul((double)this.currFrame.getHeight(), 1.0);
                            if (skillid.contains("1050.")) {}
                            System.out.println(x + "--" + y);
                            if (x > 0) {
                            }
                            g.drawImage(image, x, y, width, height, null);
                        }
                        else if (skillid.contains("1015.") || skillid.contains("1010.")) {
                            int width = (int)Arith.mul((double)this.currFrame.getWidth(), 1.0);
                            int height = (int)Arith.mul((double)this.currFrame.getHeight(), 1.0);
                            g.drawImage(image, x, y, width, height, null);
                        }
                        else if (skillid.contains("1030.tcp")) {
                            int width = (int)Arith.mul((double)this.currFrame.getWidth(), 1.0);
                            int height = (int)Arith.mul((double)this.currFrame.getHeight(), 1.0);
                            g.drawImage(image, x, y, width, height, null);
                        }
                        else {
                            g.drawImage(image, x, y, this.currFrame.getWidth(), this.currFrame.getHeight(), null);
                        }
                    }
                    else {
                        Graphics2D g2 = (Graphics2D)g;
                        x += this.currFrame.getRefPixelX();
                        y -= this.currFrame.getRefPixelY();
                        Sprite.transform.setToIdentity();
                        Sprite.transform.scale(-1.0, 1.0);
                        Sprite.transform.translate((double)(-x), (double)y);
                        g2.drawImage(this.currFrame.getImage(), Sprite.transform, null);
                    }
                }
            }
            else if (this.currFrame.getImage() != null) {
                if (this.currFrame.getPos() == 0) {
                    x -= this.currFrame.getRefPixelX();
                    y -= this.currFrame.getRefPixelY();
                    Image image = this.currFrame.getImage();
                    if (skillid.contains("1045.") || skillid.contains("1050.") || skillid.contains("1020.") || skillid.contains("1040.") || skillid.contains("1025.")) {
                        int width = (int)Arith.mul((double)this.currFrame.getWidth(), 1.0);
                        int height = (int)Arith.mul((double)this.currFrame.getHeight(), 1.0);
                        if (skillid.contains("1050.")) {}
                        System.out.println(x + "--" + y);
                        if (x > 0) {
                        }
                        g.drawImage(image, x, y, width, height, null);
                    }
                    else if (skillid.contains("1015.") || skillid.contains("1010.")) {
                        int width = (int)Arith.mul((double)this.currFrame.getWidth(), 1.0);
                        int height = (int)Arith.mul((double)this.currFrame.getHeight(), 1.0);
                        g.drawImage(image, x, y, width, height, null);
                    }
                    else if (skillid.contains("1030.tcp")) {
                        int width = (int)Arith.mul((double)this.currFrame.getWidth(), 1.0);
                        int height = (int)Arith.mul((double)this.currFrame.getHeight(), 1.0);
                        g.drawImage(image, x, y, width, height, null);
                    }
                    else {
                        g.drawImage(image, x, y, this.currFrame.getWidth(), this.currFrame.getHeight(), null);
                    }
                }
                else {
                    Graphics2D g2 = (Graphics2D)g;
                    x += this.currFrame.getRefPixelX();
                    y -= this.currFrame.getRefPixelY();
                    Sprite.transform.setToIdentity();
                    Sprite.transform.scale(-1.0, 1.0);
                    Sprite.transform.translate((double)(-x), (double)y);
                    g2.drawImage(this.currFrame.getImage(), Sprite.transform, null);
                }
            }
        }
    }
    
    public void drawFD(Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D)g.create();
        if (this.currFrame.getImage() != null) {
            if (this.currFrame.getPos() == 0) {
                x -= this.currFrame.getRefPixelX();
                y -= this.currFrame.getRefPixelY();
                g2.drawImage(this.currFrame.getImage(), x, y, (int)((double)this.currFrame.getWidth() * 1.0), (int)((double)this.currFrame.getHeight() * 1.0), null);
            }
            else {
                x += this.currFrame.getRefPixelX();
                y -= this.currFrame.getRefPixelY();
                Sprite.transform.setToIdentity();
                Sprite.transform.scale(-1.0, 1.0);
                Sprite.transform.translate((double)(-x), (double)y);
                g2.drawImage(this.currFrame.getImage(), Sprite.transform, null);
            }
        }
    }
    
    public Image draw1(Graphics g, int x, int y) {
        x += this.currFrame.getRefPixelX();
        y -= this.currFrame.getRefPixelY();
        Sprite.transform.setToIdentity();
        Sprite.transform.translate((double)x, (double)y);
        return this.currFrame.getImage();
    }
    
    public void setisHigh(boolean t) {
        this.isHigh = t;
    }
    
    public void setisHigh1(boolean t) {
        this.isHigh1 = t;
    }
    
    static {
        Sprite.transform = new AffineTransform();
        Sprite.Composite06 = AlphaComposite.getInstance(3, 0.6f);
    }
}
