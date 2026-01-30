package org.come.entity;

import java.math.BigDecimal;

public class RegionResult
{
    private String RE_NAME;
    private BigDecimal RA_ID;
    private boolean isRec;
    private String RA_NAME;
    private String ip;
    private int port;
    private String dowport;
    private String ifNew;
    private String OT_BELONG;
    
    public String getIp() {
        return this.ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public int getPort() {
        return this.port;
    }
    
    public void setPort(int port) {
        this.port = port;
    }
    
    public String getDowport() {
        return this.dowport;
    }
    
    public void setDowport(String dowport) {
        this.dowport = dowport;
    }
    
    public boolean isRec() {
        return this.isRec;
    }
    
    public void setRec(boolean rec) {
        this.isRec = rec;
    }
    
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
    
    public String getIfNew() {
        return this.ifNew;
    }
    
    public void setIfNew(String ifNew) {
        this.ifNew = ifNew;
    }
    
    public String getOT_BELONG() {
        return this.OT_BELONG;
    }
    
    public void setOT_BELONG(String OT_BELONG) {
        this.OT_BELONG = OT_BELONG;
    }
}
