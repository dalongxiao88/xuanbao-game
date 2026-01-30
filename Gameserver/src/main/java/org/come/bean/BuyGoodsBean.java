package org.come.bean;

import java.math.BigDecimal;

public class BuyGoodsBean
{
    private int sum;
    private int Originate;
    private BigDecimal goodId;
    private BigDecimal price;
    private String Score;
    
    public String getScore() {
        return this.Score;
    }
    
    public void setScore(String score) {
        this.Score = score;
    }
    
    public int getSum() {
        return this.sum;
    }
    
    public void setSum(int sum) {
        this.sum = sum;
    }
    
    public int getOriginate() {
        return this.Originate;
    }
    
    public void setOriginate(int originate) {
        this.Originate = originate;
    }
    
    public BigDecimal getGoodId() {
        return this.goodId;
    }
    
    public void setGoodId(BigDecimal goodId) {
        this.goodId = goodId;
    }
    
    public BigDecimal getPrice() {
        return this.price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
