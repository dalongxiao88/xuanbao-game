package org.come.entity;

import java.math.BigDecimal;

public class ShangchengshopEntity
{
    private BigDecimal gid;
    private String goodsname;
    private BigDecimal goodtype;
    private BigDecimal goodsprice;
    private String skin;
    private String text;
    
    public BigDecimal getGid() {
        return this.gid;
    }
    
    public void setGid(BigDecimal gid) {
        this.gid = gid;
    }
    
    public String getGoodsname() {
        return this.goodsname;
    }
    
    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }
    
    public BigDecimal getGoodtype() {
        return this.goodtype;
    }
    
    public void setGoodtype(BigDecimal goodtype) {
        this.goodtype = goodtype;
    }
    
    public BigDecimal getGoodsprice() {
        return this.goodsprice;
    }
    
    public void setGoodsprice(BigDecimal goodsprice) {
        this.goodsprice = goodsprice;
    }
    
    public String getSkin() {
        return this.skin;
    }
    
    public void setSkin(String skin) {
        this.skin = skin;
    }
    
    public String getText() {
        return this.text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    @Override
    public String toString() {
        return "ShangchengshopEntity [gid=" + this.gid + ", goodsname=" + this.goodsname + ", goodtype=" + this.goodtype + ", goodsprice=" + this.goodsprice + ", skin=" + this.skin + ", text=" + this.text + "]";
    }
}
