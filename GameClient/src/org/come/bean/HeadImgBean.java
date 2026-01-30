package org.come.bean;

import javax.swing.ImageIcon;

public class HeadImgBean
{
    private int num;
    private ImageIcon headImg;
    
    public HeadImgBean(int num, ImageIcon headImg) {
        this.num = num;
        this.headImg = headImg;
    }
    
    public boolean is() {
        int num = this.num - 1;
        this.num = num;
        return num < 0;
    }
    
    public int getNum() {
        return this.num;
    }
    
    public void setNum(int num) {
        this.num = num;
    }
    
    public ImageIcon getHeadImg() {
        return this.headImg;
    }
    
    public void setHeadImg(ImageIcon headImg) {
        this.headImg = headImg;
    }
}
