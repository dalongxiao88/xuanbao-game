package org.come.bean;

import java.math.BigDecimal;

public class GiveGoodsBean
{
    private BigDecimal rgid;
    private int sum;
    private BigDecimal gold;
    private BigDecimal otherID;
    private String otherName;
    private int type;
    
    public BigDecimal getOtherID() {
        return this.otherID;
    }
    
    public void setOtherID(BigDecimal otherID) {
        this.otherID = otherID;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public BigDecimal getGold() {
        return this.gold;
    }
    
    public void setGold(BigDecimal gold) {
        this.gold = gold;
    }
    
    public String getOtherName() {
        return this.otherName;
    }
    
    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }
    
    public int getSum() {
        return this.sum;
    }
    
    public void setSum(int sum) {
        this.sum = sum;
    }
    
    public BigDecimal getRgid() {
        return this.rgid;
    }
    
    public void setRgid(BigDecimal rgid) {
        this.rgid = rgid;
    }
}
