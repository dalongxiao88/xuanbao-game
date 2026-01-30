package org.come.entity;

import java.util.Date;
import java.math.BigDecimal;

public class Titletable
{
    private BigDecimal titleid;
    private BigDecimal roleid;
    private String titlename;
    private int limitTime;
    private Date recordTime;
    private String color;
    
    public Titletable() {
    }
    
    public Titletable(BigDecimal roleid, String titlename) {
        this.roleid = roleid;
        this.titlename = titlename;
    }
    
    public Titletable(BigDecimal roleid, String titlename, int limitTime) {
        this.roleid = roleid;
        this.titlename = titlename;
        this.limitTime = limitTime;
    }
    
    public Titletable(BigDecimal roleid, String titlename, int limitTime, Date recordTime) {
        this.roleid = roleid;
        this.titlename = titlename;
        this.limitTime = limitTime;
        this.recordTime = recordTime;
    }
    
    public BigDecimal getTitleid() {
        return this.titleid;
    }
    
    public void setTitleid(BigDecimal titleid) {
        this.titleid = titleid;
    }
    
    public BigDecimal getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }
    
    public String getTitlename() {
        return this.titlename;
    }
    
    public void setTitlename(String titlename) {
        this.titlename = titlename;
    }
    
    public int getLimittime() {
        return this.limitTime;
    }
    
    public void setLimittime(int limitTime) {
        this.limitTime = limitTime;
    }
    
    public Date getRecordtime() {
        return this.recordTime;
    }
    
    public void setRecordtime(Date recordTime) {
        this.recordTime = recordTime;
    }
    
    public String getColor() {
        return this.color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
}
