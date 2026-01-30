package org.come.bean;

import java.util.List;
import java.math.BigDecimal;

public class AuctionGoodsForGoodsBean
{
    private int id;
    private BigDecimal itemid;
    private String nameString;
    private int type;
    private String delte;
    private String getGoods;
    private List getApplyGoods;
    private List delGoodstables;
    private AuctionExchange goodsExchange;
    private BigDecimal money;
    
    public AuctionGoodsForGoodsBean() {
        this.nameString = null;
    }
    
    public List getGetApplyGoods() {
        return this.getApplyGoods;
    }
    
    public void setGetApplyGoods(List getApplyGoods) {
        this.getApplyGoods = getApplyGoods;
    }
    
    public List getDelGoodstables() {
        return this.delGoodstables;
    }
    
    public void setDelGoodstables(List delGoodstables) {
        this.delGoodstables = delGoodstables;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public BigDecimal getItemid() {
        return this.itemid;
    }
    
    public void setItemid(BigDecimal itemid) {
        this.itemid = itemid;
    }
    
    public String getNameString() {
        return this.nameString;
    }
    
    public void setNameString(String nameString) {
        this.nameString = nameString;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public String getDelte() {
        return this.delte;
    }
    
    public void setDelte(String delte) {
        this.delte = delte;
    }
    
    public String getGetGoods() {
        return this.getGoods;
    }
    
    public void setGetGoods(String getGoods) {
        this.getGoods = getGoods;
    }
    
    public AuctionExchange getAuctionExchange() {
        return this.goodsExchange;
    }
    
    public void setAuctionExchange(AuctionExchange goodsExchange) {
        this.goodsExchange = goodsExchange;
    }
    
    public BigDecimal getMoney() {
        return this.money;
    }
    
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
