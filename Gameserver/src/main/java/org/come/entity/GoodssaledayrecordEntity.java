package org.come.entity;

import java.math.BigDecimal;

public class GoodssaledayrecordEntity
{
    private BigDecimal id;
    private BigDecimal gid;
    private BigDecimal buysum;
    private BigDecimal paysum;
    private String datetime;
    
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    public BigDecimal getGid() {
        return this.gid;
    }
    
    public void setGid(BigDecimal gid) {
        this.gid = gid;
    }
    
    public BigDecimal getBuysum() {
        return this.buysum;
    }
    
    public void setBuysum(BigDecimal buysum) {
        this.buysum = buysum;
    }
    
    public BigDecimal getPaysum() {
        return this.paysum;
    }
    
    public void setPaysum(BigDecimal paysum) {
        this.paysum = paysum;
    }
    
    public String getDatetime() {
        return this.datetime;
    }
    
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
