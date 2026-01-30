package org.come.entity;

import java.math.BigDecimal;

public class ImportantgoodsluEntity
{
    private BigDecimal id;
    private BigDecimal gid;
    private String goodsname;
    private String value;
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
    
    public String getGoodsname() {
        return this.goodsname;
    }
    
    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public String getDatetime() {
        return this.datetime;
    }
    
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
    
    @Override
    public String toString() {
        return "ImportantgoodsluEntity [id=" + this.id + ", gid=" + this.gid + ", goodsname=" + this.goodsname + ", value=" + this.value + ", datetime=" + this.datetime + "]";
    }
}
