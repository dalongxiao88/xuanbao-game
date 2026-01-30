package org.come.model;

public class Door
{
    private String doorid;
    private String doormap;
    private String doorrect;
    private String doorpoint;
    private String doorkey;
    private int[] rects;
    
    public String getDoorid() {
        return this.doorid;
    }
    
    public void setDoorid(String doorid) {
        this.doorid = doorid;
    }
    
    public String getDoormap() {
        return this.doormap;
    }
    
    public void setDoormap(String doormap) {
        this.doormap = doormap;
    }
    
    public String getDoorrect() {
        return this.doorrect;
    }
    
    public void setDoorrect(String doorrect) {
        this.doorrect = doorrect;
    }
    
    public String getDoorpoint() {
        return this.doorpoint;
    }
    
    public void setDoorpoint(String doorpoint) {
        this.doorpoint = doorpoint;
    }
    
    public String getDoorkey() {
        return this.doorkey;
    }
    
    public void setDoorkey(String doorkey) {
        this.doorkey = doorkey;
    }
    
    public int[] getRects() {
        if (this.rects == null && this.doorrect != null) {
            if (!this.doorrect.equals("") && !this.doorrect.equals("0|0|0|0")) {
                String[] rect = this.doorrect.split("\\|");
                this.rects = new int[4];
                for (int i = 0; i < this.rects.length; ++i) {
                    this.rects[i] = Math.abs(Integer.parseInt(rect[i]));
                }
            }
            this.doorrect = null;
        }
        if (this.rects != null) {
            if (this.rects[1] < this.rects[0]) {
                int temp = this.rects[1];
                this.rects[1] = this.rects[0];
                this.rects[0] = temp;
            }
            if (this.rects[3] < this.rects[2]) {
                int temp = this.rects[3];
                this.rects[3] = this.rects[2];
                this.rects[2] = temp;
            }
        }
        return this.rects;
    }
    
    public void setRects(int[] rects) {
        this.rects = rects;
    }
    
    public void getDoor(byte[][] point) {
        if (this.rects == null) {
            this.getRects();
        }
        for (int x = this.rects[0] / 20; x < this.rects[1] / 20; ++x) {
            for (int y = this.rects[2] / 20; y < this.rects[3] / 20; ++y) {
                if (point[y][x] != 0) {
                    point[y][x] = 3;
                }
            }
        }
    }
}
