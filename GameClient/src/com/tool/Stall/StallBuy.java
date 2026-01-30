package com.tool.Stall;

import java.math.BigDecimal;

public class StallBuy
{
    private int id;
    private BigDecimal roleid;
    private BigDecimal commodityId;
    private int type;
    private BigDecimal buyid;
    private int sum;
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public BigDecimal getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }
    
    public BigDecimal getCommodityId() {
        return this.commodityId;
    }
    
    public void setCommodityId(BigDecimal commodityId) {
        this.commodityId = commodityId;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public BigDecimal getBuyid() {
        return this.buyid;
    }
    
    public void setBuyid(BigDecimal buyid) {
        this.buyid = buyid;
    }
    
    public int getSum() {
        return this.sum;
    }
    
    public void setSum(int sum) {
        this.sum = sum;
    }
}
