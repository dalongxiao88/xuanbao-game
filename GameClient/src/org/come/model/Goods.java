package org.come.model;

import java.math.BigDecimal;

public class Goods
{
    private BigDecimal goodsid;
    private String goodsname;
    private String skin;
    private Long type;
    private Long quality;
    private String value;
    private String instruction;
    private BigDecimal price;
    
    public BigDecimal getGoodsid() {
        return this.goodsid;
    }
    
    public void setGoodsid(BigDecimal goodsid) {
        this.goodsid = goodsid;
    }
    
    public String getGoodsname() {
        return this.goodsname;
    }
    
    public void setGoodsname(String goodsname) {
        this.goodsname = ((goodsname == null) ? null : goodsname.trim());
    }
    
    public String getSkin() {
        return this.skin;
    }
    
    public void setSkin(String skin) {
        this.skin = skin;
    }
    
    public Long getType() {
        return this.type;
    }
    
    public void setType(Long type) {
        this.type = type;
    }
    
    public Long getQuality() {
        return this.quality;
    }
    
    public void setQuality(Long quality) {
        this.quality = quality;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = ((value == null) ? null : value.trim());
    }
    
    public String getInstruction() {
        return this.instruction;
    }
    
    public void setInstruction(String instruction) {
        this.instruction = ((instruction == null) ? null : instruction.trim());
    }
    
    public BigDecimal getPrice() {
        return this.price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
