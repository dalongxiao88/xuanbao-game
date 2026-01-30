package org.come.model;

import org.come.bean.PathPoint;

public class Sghostpoint
{
    private int pointidString;
    private int pointkey;
    private String pointmap;
    private String pointtype;
    private String pointpoint;
    private PathPoint[] points;
    
    public int getPointidString() {
        return this.pointidString;
    }
    
    public void setPointidString(int pointidString) {
        this.pointidString = pointidString;
    }
    
    public int getPointkey() {
        return this.pointkey;
    }
    
    public void setPointkey(int pointkey) {
        this.pointkey = pointkey;
    }
    
    public String getPointmap() {
        return this.pointmap;
    }
    
    public void setPointmap(String pointmap) {
        this.pointmap = pointmap;
    }
    
    public String getPointtype() {
        return this.pointtype;
    }
    
    public void setPointtype(String pointtype) {
        this.pointtype = pointtype;
    }
    
    public String getPointpoint() {
        return this.pointpoint;
    }
    
    public void setPointpoint(String pointpoint) {
        this.pointpoint = pointpoint;
    }
    
    public PathPoint[] getPoints() {
        return this.points;
    }
    
    public void setPoints(PathPoint[] points) {
        this.points = points;
    }
}
