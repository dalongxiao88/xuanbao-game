package org.come.bean;

import java.math.BigDecimal;

public class itemBean
{
    private int opertype;
    private BigDecimal zhuangbeiid;
    private BigDecimal goodid;
    
    public int getOpertype() {
        return this.opertype;
    }
    
    public void setOpertype(int opertype) {
        this.opertype = opertype;
    }
    
    public BigDecimal getzhuangbeiid() {
        return this.zhuangbeiid;
    }
    
    public void setzhuangbeiid(BigDecimal zhuangbeiid) {
        this.zhuangbeiid = zhuangbeiid;
    }
    
    public BigDecimal getGoodid() {
        return this.goodid;
    }
    
    public void setGoodid(BigDecimal goodid) {
        this.goodid = goodid;
    }
}
