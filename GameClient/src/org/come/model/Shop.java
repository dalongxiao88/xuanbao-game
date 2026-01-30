package org.come.model;

import java.math.BigDecimal;

public class Shop
{
    private String shopid;
    private BigDecimal shopiid;
    private String shopname;
    private int shoptype;
    private long shopprice;
    private String shopskin;
    private String shoptext;
    private Boolean isPrice;
    private String value;
    private String limitTime;
    private Integer nid;
    
    public String getShopid() {
        return this.shopid;
    }
    
    public void setShopid(String shopid) {
        this.shopid = shopid;
    }
    
    public BigDecimal getShopiid() {
        return this.shopiid;
    }
    
    public void setShopiid(BigDecimal shopiid) {
        this.shopiid = shopiid;
    }
    
    public String getShopname() {
        return this.shopname;
    }
    
    public void setShopname(String shopname) {
        this.shopname = shopname;
    }
    
    public int getShoptype() {
        return this.shoptype;
    }
    
    public void setShoptype(int shoptype) {
        this.shoptype = shoptype;
    }
    
    public long getShopprice() {
        return this.shopprice;
    }
    
    public void setShopprice(long shopprice) {
        this.shopprice = shopprice;
    }
    
    public String getShopskin() {
        return this.shopskin;
    }
    
    public void setShopskin(String shopskin) {
        this.shopskin = shopskin;
    }
    
    public String getShoptext() {
        return this.shoptext;
    }
    
    public void setShoptext(String shoptext) {
        this.shoptext = shoptext;
    }
    
    public Boolean getIsPrice() {
        return this.isPrice;
    }
    
    public void setIsPrice(Boolean isPrice) {
        this.isPrice = isPrice;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public String getLimitTime() {
        return this.limitTime;
    }
    
    public void setLimitTime(String limitTime) {
        this.limitTime = limitTime;
    }
    
    public Integer getNid() {
        return this.nid;
    }
    
    public void setNid(Integer nid) {
        this.nid = nid;
    }
}
