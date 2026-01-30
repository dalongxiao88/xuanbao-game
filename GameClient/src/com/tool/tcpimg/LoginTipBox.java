package com.tool.tcpimg;

import java.awt.Graphics;
import java.awt.Dimension;
import org.come.test.Main;
import org.come.until.Util;
import org.come.until.CutButtonImage;
import org.come.bean.ImgZoom;

public class LoginTipBox
{
    private RichLabel label;
    private int time;
    private ImgZoom imgZoom;
    private int px;
    private int width;
    private int height;
    private int type;
    
    public LoginTipBox(String text) {
        this.type = 0;
        (this.label = new RichLabel(text, UIUtils.TEXT_NAME_FONT, 12)).setLocation(6, 6);
        Dimension d = this.label.computeSize(300);
        d.setSize(300.0, d.getHeight());
        this.label.setSize(d);
        this.width = d.width + 12;
        this.height = d.height + 12;
        (this.imgZoom = CutButtonImage.cuts("inkImg/hongmu/2/88_png.xy2uiimg.png", 6, 6, true)).setMiddlew(d.width);
        this.imgZoom.setMiddleh(d.height);
        this.time = Util.TIME_CHAT2;
        this.px = Main.frame.getLoginJpanel().getWidth() / 2 - d.width / 2;
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
        return this.type == 1 || this.time-- > 0;
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
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public int getTime() {
        return this.time;
    }
    
    public void setTime(int time) {
        this.time = time;
    }
}
