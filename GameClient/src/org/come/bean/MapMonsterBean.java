package org.come.bean;

import come.tool.Fighting.CreepsMovePath;

public class MapMonsterBean
{
    private Integer x;
    private Integer y;
    private Integer robotid;
    private String robotname;
    private String robottitle;
    private String follow;
    private String robotskin;
    private int robottype;
    private Integer i;
    private int type;
    private transient CreepsMovePath movePath;
    private transient PathPoint HP;
    
    public MapMonsterBean() {
        this.type = 0;
    }
    
    public CreepsMovePath getMovePath() {
        return this.movePath;
    }
    
    public void setMovePath(CreepsMovePath movePath) {
        this.movePath = movePath;
    }
    
    public Integer getX() {
        return this.x;
    }
    
    public void setX(Integer x) {
        this.x = x;
    }
    
    public Integer getY() {
        return this.y;
    }
    
    public void setY(Integer y) {
        this.y = y;
    }
    
    public Integer getRobotid() {
        return this.robotid;
    }
    
    public void setRobotid(Integer robotid) {
        this.robotid = robotid;
    }
    
    public String getRobotname() {
        return this.robotname;
    }
    
    public void setRobotname(String robotname) {
        this.robotname = robotname;
    }
    
    public String getRobotskin() {
        return this.robotskin;
    }
    
    public void setRobotskin(String robotskin) {
        this.robotskin = robotskin;
    }
    
    public Integer getI() {
        return this.i;
    }
    
    public void setI(Integer i) {
        this.i = i;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public int getRobottype() {
        return this.robottype;
    }
    
    public void setRobottype(int robottype) {
        this.robottype = robottype;
    }
    
    public String getRobottitle() {
        return this.robottitle;
    }
    
    public void setRobottitle(String robottitle) {
        this.robottitle = robottitle;
    }
    
    public String getFollow() {
        return this.follow;
    }
    
    public void setFollow(String follow) {
        this.follow = follow;
    }
    
    public PathPoint getHP() {
        return this.HP;
    }
    
    public void setHP(PathPoint hP) {
        this.HP = hP;
    }
}
