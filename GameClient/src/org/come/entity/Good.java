package org.come.entity;

import javax.swing.ImageIcon;

public class Good implements Comparable<Good>
{
    private Goodstable goodstable;
    private ImageIcon img;
    
    public Goodstable getGoodstable() {
        return this.goodstable;
    }
    
    public void setGoodstable(Goodstable goodstable) {
        this.goodstable = goodstable;
    }
    
    public ImageIcon getImg() {
        return this.img;
    }
    
    public void setImg(ImageIcon img) {
        this.img = img;
    }
    
    @Override
    public int compareTo(Good arg0) {
        return this.getGoodstable().getType().compareTo(arg0.getGoodstable().getType());
    }
}
