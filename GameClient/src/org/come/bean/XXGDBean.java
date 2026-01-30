package org.come.bean;

import java.math.BigDecimal;

public class XXGDBean
{
    private BigDecimal id;
    private int sum;
    private long tag;
    private BigDecimal price;
    
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    public int getSum() {
        return this.sum;
    }
    
    public void setSum(int sum) {
        this.sum = sum;
    }
    
    public long getTag() {
        return this.tag;
    }
    
    public void setTag(long tag) {
        this.tag = tag;
    }
    
    public BigDecimal getPrice() {
        return this.price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
