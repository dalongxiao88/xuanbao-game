package org.come.entity;

import java.math.BigDecimal;

public class Rufenghaocontrol
{
    private BigDecimal id;
    private BigDecimal quid;
    private BigDecimal rid;
    private String username;
    private String rolename;
    private String reason;
    private BigDecimal type;
    private String registerip;
    private String lasstloginip;
    private String controlobject;
    private BigDecimal dailiid;
    private BigDecimal totalsum;
    private String datetime;
    
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    public BigDecimal getQuid() {
        return this.quid;
    }
    
    public void setQuid(BigDecimal quid) {
        this.quid = quid;
    }
    
    public BigDecimal getRid() {
        return this.rid;
    }
    
    public void setRid(BigDecimal rid) {
        this.rid = rid;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getRolename() {
        return this.rolename;
    }
    
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    
    public String getReason() {
        return this.reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public BigDecimal getType() {
        return this.type;
    }
    
    public void setType(BigDecimal type) {
        this.type = type;
    }
    
    public String getRegisterip() {
        return this.registerip;
    }
    
    public void setRegisterip(String registerip) {
        this.registerip = registerip;
    }
    
    public String getLasstloginip() {
        return this.lasstloginip;
    }
    
    public void setLasstloginip(String lasstloginip) {
        this.lasstloginip = lasstloginip;
    }
    
    public String getControlobject() {
        return this.controlobject;
    }
    
    public void setControlobject(String controlobject) {
        this.controlobject = controlobject;
    }
    
    public BigDecimal getDailiid() {
        return this.dailiid;
    }
    
    public void setDailiid(BigDecimal dailiid) {
        this.dailiid = dailiid;
    }
    
    public BigDecimal getTotalsum() {
        return this.totalsum;
    }
    
    public void setTotalsum(BigDecimal totalsum) {
        this.totalsum = totalsum;
    }
    
    public String getDatetime() {
        return this.datetime;
    }
    
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
