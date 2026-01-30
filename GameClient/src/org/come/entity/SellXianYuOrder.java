package org.come.entity;

import java.math.BigDecimal;

public class SellXianYuOrder
{
    private BigDecimal id;
    private BigDecimal buyRoleId;
    private BigDecimal sellRoleId;
    private BigDecimal xianYuPoint;
    private BigDecimal pricePoint;
    private BigDecimal totalPrice;
    private String buyTime;
    private String addSellTime;
    private BigDecimal deposit;
    private BigDecimal sellId;
    
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    public BigDecimal getBuyRoleId() {
        return this.buyRoleId;
    }
    
    public void setBuyRoleId(BigDecimal buyRoleId) {
        this.buyRoleId = buyRoleId;
    }
    
    public BigDecimal getSellRoleId() {
        return this.sellRoleId;
    }
    
    public void setSellRoleId(BigDecimal sellRoleId) {
        this.sellRoleId = sellRoleId;
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
    
    public String getBuyTime() {
        return this.buyTime;
    }
    
    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }
    
    public String getAddSellTime() {
        return this.addSellTime;
    }
    
    public void setAddSellTime(String addSellTime) {
        this.addSellTime = addSellTime;
    }
    
    public BigDecimal getDeposit() {
        return this.deposit;
    }
    
    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }
    
    public BigDecimal getSellId() {
        return this.sellId;
    }
    
    public void setSellId(BigDecimal sellId) {
        this.sellId = sellId;
    }
}
