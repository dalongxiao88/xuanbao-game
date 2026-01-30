package org.come.bean;

import java.math.BigDecimal;

public class SellXianyu
{
    public BigDecimal id;
    public BigDecimal xianYuPoint;
    public BigDecimal pricePoint;
    public BigDecimal totalPrice;
    public String addTime;
    public String expireTime;
    public BigDecimal roleId;
    public BigDecimal deposit;
    
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    public BigDecimal getXianYuPoint() {
        return this.xianYuPoint;
    }
    
    public void setXianYuPoint(BigDecimal xianYuPoint) {
        this.xianYuPoint = xianYuPoint;
    }
    
    public BigDecimal getPricePoint() {
        return this.pricePoint;
    }
    
    public void setPricePoint(BigDecimal pricePoint) {
        this.pricePoint = pricePoint;
    }
    
    public BigDecimal getTotalPrice() {
        return this.totalPrice;
    }
    
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public String getAddTime() {
        return this.addTime;
    }
    
    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }
    
    public String getExpireTime() {
        return this.expireTime;
    }
    
    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }
    
    public BigDecimal getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(BigDecimal roleId) {
        this.roleId = roleId;
    }
    
    public BigDecimal getDeposit() {
        return this.deposit;
    }
    
    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }
}
