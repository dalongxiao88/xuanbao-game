package org.come.model;

import come.tool.Good.DropModel;

public class Boos
{
    private String boosid;
    private String boostype;
    private String boosname;
    private String boosmapname;
    private String isMove;
    private String boosrobot;
    private String boosweekday;
    private int boosstime;
    private int boosetime;
    private int boosrtime;
    private int boosnum;
    private int boosgpk;
    private String boostext;
    private String boosGGtext;
    private String boosDrop;
    private int boosDropMax;
    private String fyDrop;
    private transient DropModel dropModel;
    
    public String getBoosid() {
        return this.boosid;
    }
    
    public void setBoosid(String boosid) {
        this.boosid = boosid;
    }
    
    public String getBoostype() {
        return this.boostype;
    }
    
    public void setBoostype(String boostype) {
        this.boostype = boostype;
    }
    
    public String getBoosname() {
        return this.boosname;
    }
    
    public void setBoosname(String boosname) {
        this.boosname = boosname;
    }
    
    public String getBoosmapname() {
        return this.boosmapname;
    }
    
    public void setBoosmapname(String boosmapname) {
        this.boosmapname = boosmapname;
    }
    
    public String getBoosrobot() {
        return this.boosrobot;
    }
    
    public void setBoosrobot(String boosrobot) {
        this.boosrobot = boosrobot;
    }
    
    public String getBoosweekday() {
        return this.boosweekday;
    }
    
    public void setBoosweekday(String boosweekday) {
        this.boosweekday = boosweekday;
    }
    
    public String getBoostext() {
        return this.boostext;
    }
    
    public void setBoostext(String boostext) {
        this.boostext = boostext;
    }
    
    public String getBoosGGtext() {
        return this.boosGGtext;
    }
    
    public void setBoosGGtext(String boosGGtext) {
        this.boosGGtext = boosGGtext;
    }
    
    public String getBoosDrop() {
        return this.boosDrop;
    }
    
    public void setBoosDrop(String boosDrop) {
        this.boosDrop = boosDrop;
    }
    
    public int getBoosDropMax() {
        return this.boosDropMax;
    }
    
    public void setBoosDropMax(int boosDropMax) {
        this.boosDropMax = boosDropMax;
    }
    
    public DropModel getDropModel() {
        if (this.dropModel == null && this.boosDrop != null && !this.boosDrop.equals("")) {
            this.dropModel = new DropModel(this.boosDrop.split("\\|"));
        }
        return this.dropModel;
    }
    
    public String getFyDrop() {
        return this.fyDrop;
    }
    
    public void setFyDrop(String fyDrop) {
        this.fyDrop = fyDrop;
    }
    
    public int getBoosstime() {
        return this.boosstime;
    }
    
    public void setBoosstime(int boosstime) {
        this.boosstime = boosstime;
    }
    
    public int getBoosetime() {
        return this.boosetime;
    }
    
    public void setBoosetime(int boosetime) {
        this.boosetime = boosetime;
    }
    
    public int getBoosrtime() {
        return this.boosrtime;
    }
    
    public void setBoosrtime(int boosrtime) {
        this.boosrtime = boosrtime;
    }
    
    public int getBoosnum() {
        return this.boosnum;
    }
    
    public void setBoosnum(int boosnum) {
        this.boosnum = boosnum;
    }
    
    public int getBoosgpk() {
        return this.boosgpk;
    }
    
    public void setBoosgpk(int boosgpk) {
        this.boosgpk = boosgpk;
    }
    
    public String getIsMove() {
        return this.isMove;
    }
    
    public void setIsMove(String isMove) {
        this.isMove = isMove;
    }
}
