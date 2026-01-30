package org.come.bean;

import java.math.BigDecimal;

public class QualityClBean
{
    private BigDecimal rgid;
    private int type;
    private String newAttr;
    
    public BigDecimal getRgid() {
        return this.rgid;
    }
    
    public void setRgid(BigDecimal rgid) {
        this.rgid = rgid;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public String getNewAttr() {
        return this.newAttr;
    }
    
    public void setNewAttr(String newAttr) {
        this.newAttr = newAttr;
    }
}
