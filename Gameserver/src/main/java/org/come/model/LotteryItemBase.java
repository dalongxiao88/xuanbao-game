package org.come.model;

import java.math.BigDecimal;

public class LotteryItemBase
{
    private BigDecimal id;
    private int quality;
    private int rate;
    private boolean has;
    
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    public int getQuality() {
        return this.quality;
    }
    
    public void setQuality(int quality) {
        this.quality = quality;
    }
    
    public int getRate() {
        return this.rate;
    }
    
    public void setRate(int rate) {
        this.rate = rate;
    }
    
    public boolean isHas() {
        return this.has;
    }
    
    public void setHas(boolean has) {
        this.has = has;
    }
}
