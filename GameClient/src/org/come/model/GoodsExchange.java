package org.come.model;

import org.come.entity.Goodstable;
import java.math.BigDecimal;

/**
 * 客户端物品兑换展示对象。
 */
public class GoodsExchange
{
    private int eId;
    private String type;
    private String consume;
    private BigDecimal goods;
    private String goodsname;
    private String skin;
    private Long quality;
    private BigDecimal rgid;
    private Integer status;
    private String value;
    private String instruction;
    
    @Override
    public String toString() {
        return "goodsExchange{eId=" + this.eId + ", type='" + this.type + '\'' + ", consume='" + this.consume + '\'' + ", goods=" + this.goods + ", goodsname='" + this.goodsname + '\'' + ", skin='" + this.skin + '\'' + ", quality=" + this.quality + ", rgid=" + this.rgid + ", status=" + this.status + ", value='" + this.value + '\'' + ", instruction='" + this.instruction + '\'' + '}';
    }
    
    public void initPet(Goodstable goods) {
        this.goodsname = goods.getGoodsname();
        this.skin = goods.getSkin();
        this.quality = goods.getQuality();
        this.value = goods.getValue();
        this.instruction = goods.getInstruction();
        this.rgid = goods.getRgid();
        this.status = goods.getStatus();
    }
    
    public int geteId() {
        return this.eId;
    }

    /** 语义化别名：兑换 ID。 */
    public int getExchangeId() {
        return this.eId;
    }
    
    public void seteId(int eId) {
        this.eId = eId;
    }

    public void setExchangeId(int exchangeId) {
        this.eId = exchangeId;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getConsume() {
        return this.consume;
    }
    
    public void setConsume(String consume) {
        this.consume = consume;
    }
    
    public BigDecimal getGoods() {
        return this.goods;
    }

    /** 语义化别名：物品 ID。 */
    public BigDecimal getGoodsId() {
        return this.goods;
    }
    
    public void setGoods(BigDecimal goods) {
        this.goods = goods;
    }

    public void setGoodsId(BigDecimal goodsId) {
        this.goods = goodsId;
    }
    
    public String getGoodsname() {
        return this.goodsname;
    }

    /** 语义化别名：物品名称。 */
    public String getGoodsName() {
        return this.goodsname;
    }
    
    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public void setGoodsName(String goodsName) {
        this.goodsname = goodsName;
    }
    
    public String getSkin() {
        return this.skin;
    }
    
    public void setSkin(String skin) {
        this.skin = skin;
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
