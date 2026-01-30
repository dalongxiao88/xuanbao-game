package org.come.entity;

import java.math.BigDecimal;

public class Gangapply
{
    private BigDecimal gaid;
    private BigDecimal gangid;
    private BigDecimal roleid;
    
    public BigDecimal getGaid() {
        return this.gaid;
    }
    
    public void setGaid(BigDecimal gaid) {
        this.gaid = gaid;
    }
    
    public BigDecimal getGangid() {
        return this.gangid;
    }
    
    public void setGangid(BigDecimal gangid) {
        this.gangid = gangid;
    }
    
    public BigDecimal getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }
}
