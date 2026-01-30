package org.come.bean;

import java.awt.Graphics;
import java.awt.Image;

public class ImgZoom
{
    private int edgew;
    private int edgeh;
    private int middlew;
    private int middleh;
    private Image[] imgs;
    
    public ImgZoom() {
    }
    
    public ImgZoom(ImgZoom imgZoom) {
        this.edgew = imgZoom.getEdgew();
        this.edgeh = imgZoom.getEdgeh();
        this.middlew = imgZoom.getMiddlew();
        this.middleh = imgZoom.getMiddleh();
        this.imgs = imgZoom.getImgs();
    }
    
    public void draw(Graphics g) {
        g.drawImage(this.imgs[0], 0, 0, this.edgew, this.edgeh, null);
        g.drawImage(this.imgs[1], this.edgew + this.middlew, 0, this.edgew, this.edgeh, null);
        g.drawImage(this.imgs[2], 0, this.edgeh + this.middleh, this.edgew, this.edgeh, null);
        g.drawImage(this.imgs[3], this.edgew + this.middlew, this.edgeh + this.middleh, this.edgew, this.edgeh, null);
        g.drawImage(this.imgs[4], 0, this.edgeh, this.edgew, this.middleh, null);
        g.drawImage(this.imgs[5], this.edgew + this.middlew, this.edgeh, this.edgew, this.middleh, null);
        g.drawImage(this.imgs[6], this.edgew, 0, this.middlew, this.edgeh, null);
        g.drawImage(this.imgs[7], this.edgew, this.edgeh + this.middleh, this.middlew, this.edgeh, null);
        g.drawImage(this.imgs[8], this.edgew, this.edgeh, this.middlew, this.middleh, null);
    }
    /** 画任意长度的滚动条 */
    public void drawScroll(Graphics g, int w, int h) {
        g.drawImage(this.imgs[0], -1, 0, this.edgew, this.edgeh, null);
        g.drawImage(this.imgs[1], -1 + this.edgew + this.middlew, 0, this.edgew, this.edgeh, null);
        g.drawImage(this.imgs[2], -1, -this.edgeh + h, this.edgew, this.edgeh, null);
        g.drawImage(this.imgs[3], -1 + this.edgew + this.middlew, -this.edgeh + h, this.edgew, this.edgeh, null);
        g.drawImage(this.imgs[4], -1, this.edgeh, this.edgew, h - this.edgeh * 2, null);
        g.drawImage(this.imgs[5], -1 + this.edgew + this.middlew, this.edgeh, this.edgew, h - this.edgeh * 2, null);
        g.drawImage(this.imgs[6], -1 + this.edgew, 0, this.middlew, this.edgeh, null);
        g.drawImage(this.imgs[7], -1 + this.edgew, -this.edgeh + h, this.middlew, this.edgeh, null);
        g.drawImage(this.imgs[8], -1 + this.edgew, this.edgeh, this.middlew, h - this.edgeh * 2, null);
    }
    
    public void draw3(Graphics g) {
        g.drawImage(this.imgs[0], 0, 0, this.edgew, this.edgeh, null);
        g.drawImage(this.imgs[1], this.edgew + this.middlew, 0, this.edgew, this.edgeh, null);
        g.drawImage(this.imgs[2], 0, this.edgeh + this.middleh, this.edgew, this.edgeh, null);
        g.drawImage(this.imgs[3], this.edgew + this.middlew, this.edgeh + this.middleh, this.edgew, this.edgeh, null);
        g.drawImage(this.imgs[4], 0, this.edgeh, this.edgew, this.middleh, null);
        g.drawImage(this.imgs[5], this.edgew + this.middlew, this.edgeh, this.edgew, this.middleh, null);
        g.drawImage(this.imgs[6], this.edgew, 0, this.middlew, this.edgeh, null);
        g.drawImage(this.imgs[7], this.edgew, this.edgeh + this.middleh, this.middlew, this.edgeh, null);
        g.drawImage(this.imgs[8], this.edgew, this.edgeh, this.middlew, this.middleh, null);
    }
    
    public void middleimg(Image icon) {
        this.imgs[8] = icon;
        this.middlew = icon.getWidth(null);
        this.middleh = icon.getHeight(null);
    }
    
    public int getEdgew() {
        return this.edgew;
    }
    
    public void setEdgew(int edgew) {
        this.edgew = edgew;
    }
    
    public int getEdgeh() {
        return this.edgeh;
    }
    
    public void setEdgeh(int edgeh) {
        this.edgeh = edgeh;
    }
    
    public int getMiddlew() {
        return this.middlew;
    }
    
    public void setMiddlew(int middlew) {
        this.middlew = middlew;
    }
    
    public int getMiddleh() {
        return this.middleh;
    }
    
    public void setMiddleh(int middleh) {
        this.middleh = middleh;
    }
    
    public Image[] getImgs() {
        return this.imgs;
    }
    
    public void setImgs(Image[] imgs) {
        this.imgs = imgs;
    }
}
