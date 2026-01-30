package org.come.bean;

import java.math.BigDecimal;

public class BuyShopBean
{
    private int sum;
    private int ate;
    private Integer nId;
    private String cd;
    private BigDecimal goodsId;
    private long price;
    
    public int getSum() {
        return this.sum;
    }
    
    public void setSum(int sum) {
        this.sum = sum;
    }
    
    public int getAte() {
        return this.ate;
    }
    
    public void setAte(int ate) {
        this.ate = ate;
    }
    
    public String getCd() {
        return this.cd;
    }
    
    public void setCd(String cd) {
        this.cd = cd;
    }
    
    public Integer getnId() {
        return this.nId;
    }
    
    public void setnId(Integer nId) {
        this.nId = nId;
    }
    
    public BigDecimal getGoodsId() {
        return this.goodsId;
    }
    
    public void setGoodsId(BigDecimal goodsId) {
        this.goodsId = goodsId;
    }
    
    public long getPrice() {
        return this.price;
    }
    
    public void setPrice(long price) {
        this.price = price;
    }
}
