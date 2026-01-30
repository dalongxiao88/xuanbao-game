package org.come.bean;

import java.math.BigDecimal;

public class SummonGoodsBean
{
    private int opertype;
    private BigDecimal goodsid;
    private BigDecimal goodid;
    
    public int getOpertype() {
        return this.opertype;
    }
    
    public void setOpertype(int opertype) {
        this.opertype = opertype;
    }
    
    public BigDecimal getGoodsid() {
        return this.goodsid;
    }
    
    public void setGoodsid(BigDecimal goodsid) {
        this.goodsid = goodsid;
    }
    
    public BigDecimal getGoodid() {
        return this.goodid;
    }
    
    public void setGoodid(BigDecimal goodid) {
        this.goodid = goodid;
    }
}
