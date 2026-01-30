package org.cbg.entity;

import java.util.Date;
import java.math.BigDecimal;

public class Collection
{
    private BigDecimal colid;
    private BigDecimal saleid;
    private BigDecimal roleid;
    private String salename;
    private String saleskin;
    private BigDecimal saleprice;
    private Integer saletype;
    private Date uptime;
    private BigDecimal otherid;
    private BigDecimal buyrole;
    
    public BigDecimal getBuyrole() {
        return this.buyrole;
    }
    
    public void setBuyrole(BigDecimal buyrole) {
        this.buyrole = buyrole;
    }
    
    public BigDecimal getOtherid() {
        return this.otherid;
    }
    
    public void setOtherid(BigDecimal otherid) {
        this.otherid = otherid;
    }
    
    public Integer getSaletype() {
        return this.saletype;
    }
    
    public void setSaletype(Integer saletype) {
        this.saletype = saletype;
    }
    
    public Date getUptime() {
        return this.uptime;
    }
    
    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }
    
    public BigDecimal getColid() {
        return this.colid;
    }
    
    public void setColid(BigDecimal colid) {
        this.colid = colid;
    }
    
    public BigDecimal getSaleid() {
        return this.saleid;
    }
    
    public void setSaleid(BigDecimal saleid) {
        this.saleid = saleid;
    }
    
    public BigDecimal getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }
    
    public String getSalename() {
        return this.salename;
    }
    
    public void setSalename(String salename) {
        this.salename = salename;
    }
    
    public String getSaleskin() {
        return this.saleskin;
    }
    
    public void setSaleskin(String saleskin) {
        this.saleskin = saleskin;
    }
    
    public BigDecimal getSaleprice() {
        return this.saleprice;
    }
    
    public void setSaleprice(BigDecimal saleprice) {
        this.saleprice = saleprice;
    }
}
