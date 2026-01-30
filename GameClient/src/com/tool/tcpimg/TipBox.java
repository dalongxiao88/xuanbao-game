package com.tool.tcpimg;

import java.awt.Graphics;
import java.awt.Dimension;
import org.come.until.Util;
import org.come.until.ScrenceUntil;
import org.come.until.CutButtonImage;
import org.come.until.Music;
import org.come.bean.ImgZoom;

public class TipBox
{
    private RichLabel label;
    private int time;
    private ImgZoom imgZoom;
    private int px;
    private int width;
    private int height;
    
    public TipBox(String text) {
        Music.addyinxiao("获得提示.mp3");
        (this.label = new RichLabel(text, UIUtils.TEXT_FONT2)).setLocation(16, 12);
        Dimension d = this.label.computeSize(640);
        this.label.setSize(d);
        this.width = d.width - 12;
        this.height = d.height + 25;
        this.imgZoom = CutButtonImage.cuts("img/xy2uiimg/88_png.xy2uiimg.png", 6, 6, true);
        int w = 0;
        if (this.width > 300) {
            w = ScrenceUntil.Screen_x / 2;
            this.imgZoom.setMiddlew(this.width + 25);
        }
        else {
            w = ScrenceUntil.Screen_x / 2 - (300 - this.width) / 2;
            this.imgZoom.setMiddlew(300);
        }
        this.imgZoom.setMiddleh(d.height + 15);
        this.time = Util.TIME_CHAT2;
        this.px = w - d.width / 2;
    }
    
    public void paint(Graphics g) {
        g.translate(this.px, 0);
        this.imgZoom.draw(g);
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
