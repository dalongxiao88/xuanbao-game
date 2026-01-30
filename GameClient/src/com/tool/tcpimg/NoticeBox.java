package com.tool.tcpimg;

import java.awt.Graphics;
import java.awt.Dimension;
import org.come.until.ScrenceUntil;
import org.come.until.Util;
import org.come.until.CutButtonImage;
import com.tool.tcp.SpriteFactory;
import com.tool.tcp.Sprite;
import org.come.bean.ImgZoom;

public class NoticeBox
{
    private RichLabel label;
    private int time;
    private ImgZoom imgZoom;
    private int px;
    private int width;
    private int height;
    private Sprite tcp14;
    
    public NoticeBox(String text) {
        this.tcp14 = SpriteFactory.VloadSprite("resource/emoticons/铃铛.tcp", null);
        (this.label = new RichLabel(text, UIUtils.TEXT_FONT1)).setLocation(10, 7);
        Dimension d = this.label.computeSize(600);
        d.setSize(600.0, d.getHeight());
        this.label.setSize(d);
        this.width = d.width;
        this.height = d.height;
        (this.imgZoom = CutButtonImage.cuts("img/xy2uiimg/notice.png", 6, 6, true)).setMiddlew(d.width);
        this.imgZoom.setMiddleh(d.height + 10);
        this.time = Util.TIME_CHAT2 * 5;
        this.px = ScrenceUntil.Screen_x / 2 - d.width / 2 + 100;
    }
    
    public void paint(Graphics g) {
        g.translate(this.px, 0);
        this.tcp14.updateToTime(System.currentTimeMillis(), 1);
        if ((boolean)this.label.b) {
            this.tcp14.draw(g, 260, 33);
        }
        else {
            this.tcp14.draw(g, 260, 20);
        }
        g.translate(this.label.getX(), this.label.getY());
        this.label.paint(g);
        g.translate(-this.px - this.label.getX(), -this.label.getY());
    }
    
    public RichLabel getLabel() {
        return this.label;
    }
    
    public void setLabel(RichLabel label) {
        this.label = label;
    }
    
    public boolean IsTime() {
        return this.time-- > 0;
    }
    
    public ImgZoom getImgZoom() {
        return this.imgZoom;
    }
    
    public void setImgZoom(ImgZoom imgZoom) {
        this.imgZoom = imgZoom;
    }
    
    public int getPx() {
        return this.px;
    }
    
    public void setPx(int px) {
        this.px = px;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
}
