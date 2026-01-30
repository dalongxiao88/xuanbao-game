package org.come.entity;

import java.math.BigDecimal;

public class RegionResult
{
    private String RE_NAME;
    private BigDecimal RA_ID;
    private String RA_NAME;
    private String OT_BELONG;
    
    public String getRE_NAME() {
        return this.RE_NAME;
    }
    
    public void setRE_NAME(String rE_NAME) {
        this.RE_NAME = rE_NAME;
    }
    
    public BigDecimal getRA_ID() {
        return this.RA_ID;
    }
    
    public void setRA_ID(BigDecimal rA_ID) {
        this.RA_ID = rA_ID;
    }
    
    public String getRA_NAME() {
        return this.RA_NAME;
    }
    
    public void setRA_NAME(String rA_NAME) {
        this.RA_NAME = rA_NAME;
    }
    
    public String getOT_BELONG() {
        return this.OT_BELONG;
    }
    
    public void setOT_BELONG(String oT_BELONG) {
        this.OT_BELONG = oT_BELONG;
    }
}
