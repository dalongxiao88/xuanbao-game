package org.come.entity;

import java.util.Date;
import java.math.BigDecimal;

public class Message
{
    private BigDecimal mesid;
    private BigDecimal roleid;
    private BigDecimal saleid;
    private String mescontent;
    private Date gettime;
    
    public BigDecimal getMesid() {
        return this.mesid;
    }
    
    public void setMesid(BigDecimal mesid) {
        this.mesid = mesid;
    }
    
    public BigDecimal getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }
    
    public BigDecimal getSaleid() {
        return this.saleid;
    }
    
    public void setSaleid(BigDecimal saleid) {
        this.saleid = saleid;
    }
    
    public String getMescontent() {
        return this.mescontent;
    }
    
    public void setMescontent(String mescontent) {
        this.mescontent = ((mescontent == null) ? null : mescontent.trim());
    }
    
    public Date getGettime() {
        return this.gettime;
    }
    
    public void setGettime(Date gettime) {
        this.gettime = gettime;
    }
}
