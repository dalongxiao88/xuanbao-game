package org.come.model;

import org.come.entity.Goodstable;
import java.math.BigDecimal;

public class GoodModel
{
    private BigDecimal goodsid;
    private String goodsname;
    private String skin;
    private long type;
    private Long quality;
    private String value;
    private String instruction;
    
    public GoodModel(Goodstable good) {
        this.goodsid = good.getGoodsid();
        this.goodsname = good.getGoodsname();
        this.skin = good.getSkin();
        this.type = good.getType();
        this.quality = good.getQuality();
        if (this.type == 112L || this.type == 60001L) {
            this.value = good.getValue();
            if (this.type == 60001L || this.type == 60002L) {
                this.instruction = good.getInstruction();
            }
        }
        this.instruction = good.getInstruction();
    }
    
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
        this.goodsname = goodsname;
    }
    
    public String getSkin() {
        return this.skin;
    }
    
    public void setSkin(String skin) {
        this.skin = skin;
    }
    
    public long getType() {
        return this.type;
    }
    
    public void setType(long type) {
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
        this.value = value;
    }
    
    public String getInstruction() {
        return this.instruction;
    }
    
    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
