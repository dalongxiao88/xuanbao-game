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
    
    public int getLimitTime() {
        return this.limitTime;
    }
    
    public void setLimitTime(int limitTime) {
        this.limitTime = limitTime;
    }
    
    public Date getRecordTime() {
        return this.recordTime;
    }
    
    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
    
    public String getColor() {
        return this.color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
}
