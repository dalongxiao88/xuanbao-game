package org.come.bean;

import java.math.BigDecimal;

public class SummonPetBean
{
    private int opertype;
    private BigDecimal petid;
    private BigDecimal goodid;
    
    public int getOpertype() {
        return this.opertype;
    }
    
    public void setOpertype(int opertype) {
        this.opertype = opertype;
    }
    
    public BigDecimal getPetid() {
        return this.petid;
    }
    
    public void setPetid(BigDecimal petid) {
        this.petid = petid;
    }
    
    public BigDecimal getGoodid() {
        return this.goodid;
    }
    
    public void setGoodid(BigDecimal goodid) {
        this.goodid = goodid;
    }
}
