package org.come.entity;

import java.math.BigDecimal;

public class BuytypeEntity
{
    private BigDecimal tid;
    private BigDecimal buytype;
    private String typename;
    private String time;
    
    public BigDecimal getTid() {
        return this.tid;
    }
    
    public void setTid(BigDecimal tid) {
        this.tid = tid;
    }
    
    public BigDecimal getBuytype() {
        return this.buytype;
    }
    
    public void setBuytype(BigDecimal buytype) {
        this.buytype = buytype;
    }
    
    public String getTypename() {
        return this.typename;
    }
    
    public void setTypename(String typename) {
        this.typename = typename;
    }
    
    public String getTime() {
        return this.time;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
    
    @Override
    public String toString() {
        return "BuytypeEntity [tid=" + this.tid + ", buytype=" + this.buytype + ", typename=" + this.typename + ", time=" + this.time + "]";
    }
}
