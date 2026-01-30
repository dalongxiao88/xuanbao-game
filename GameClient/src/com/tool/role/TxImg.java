package com.tool.role;

import javax.swing.ImageIcon;

public class TxImg
{
    private int id;
    private ImageIcon icon;
    private String wingSkin;
    
    public TxImg() {
    }
    
    public TxImg(int id, ImageIcon icon) {
        this.id = id;
        this.icon = icon;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public String getWingSkin() {
        return this.wingSkin;
    }
    
    public void setWingSkin(String wingSkin) {
        this.wingSkin = wingSkin;
    }
}
