package org.come.entity;

import java.math.BigDecimal;

public class UserxyandroledhbcrEntity
{
    private BigDecimal id;
    private BigDecimal userid;
    private String username;
    private BigDecimal roleid;
    private String rolename;
    private BigDecimal type;
    private BigDecimal xsum;
    private BigDecimal xnow;
    private BigDecimal xdsum;
    private BigDecimal dsum;
    private BigDecimal sssum;
    private String time;
    private BigDecimal sid;
    
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    public BigDecimal getUserid() {
        return this.userid;
    }
    
    public void setUserid(BigDecimal userid) {
        this.userid = userid;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public BigDecimal getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }
    
    public String getRolename() {
        return this.rolename;
    }
    
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    
    public BigDecimal getType() {
        return this.type;
    }
    
    public void setType(BigDecimal type) {
        this.type = type;
    }
    
    public BigDecimal getXsum() {
        return this.xsum;
    }
    
    public void setXsum(BigDecimal xsum) {
        this.xsum = xsum;
    }
    
    public BigDecimal getXdsum() {
        return this.xdsum;
    }
    
    public void setXdsum(BigDecimal xdsum) {
        this.xdsum = xdsum;
    }
    
    public BigDecimal getDsum() {
        return this.dsum;
    }
    
    public void setDsum(BigDecimal dsum) {
        this.dsum = dsum;
    }
    
    public BigDecimal getSssum() {
        return this.sssum;
    }
    
    public void setSssum(BigDecimal sssum) {
        this.sssum = sssum;
    }
    
    public String getTime() {
        return this.time;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
    
    public BigDecimal getSid() {
        return this.sid;
    }
    
    public void setSid(BigDecimal sid) {
        this.sid = sid;
    }
    
    public BigDecimal getXnow() {
        return this.xnow;
    }
    
    public void setXnow(BigDecimal xnow) {
        this.xnow = xnow;
    }
    
    @Override
    public String toString() {
        return "UserxyandroledhbcrEntity [id=" + this.id + ", userid=" + this.userid + ", username=" + this.username + ", roleid=" + this.roleid + ", rolename=" + this.rolename + ", type=" + this.type + ", xsum=" + this.xsum + ", xnow=" + this.xnow + ", xdsum=" + this.xdsum + ", dsum=" + this.dsum + ", sssum=" + this.sssum + ", time=" + this.time + ", sid=" + this.sid + "]";
    }
}
