package org.come.entity;

import java.util.Date;
import java.math.BigDecimal;

public class RewardHall
{
    private BigDecimal id;
    private String goodstable;
    private BigDecimal goodnum;
    private BigDecimal goodprice;
    private BigDecimal roleId;
    private Date throwtime;
    private int version;
    
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    public String getGoodstable() {
        return this.goodstable;
    }
    
    public void setGoodstable(String goodstable) {
        this.goodstable = ((goodstable == null) ? null : goodstable.trim());
    }
    
    public BigDecimal getGoodnum() {
        return this.goodnum;
    }
    
    public void setGoodnum(BigDecimal goodnum) {
        this.goodnum = goodnum;
    }
    
    public BigDecimal getGoodprice() {
        return this.goodprice;
    }
    
    public void setGoodprice(BigDecimal goodprice) {
        this.goodprice = goodprice;
    }
    
    public BigDecimal getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(BigDecimal roleId) {
        this.roleId = roleId;
    }
    
    public Date getThrowtime() {
        return this.throwtime;
    }
    
    public void setThrowtime(Date throwtime) {
        this.throwtime = throwtime;
    }
    
    public int getVersion() {
        return this.version;
    }
    
    public void setVersion(int version) {
        this.version = version;
    }
}
