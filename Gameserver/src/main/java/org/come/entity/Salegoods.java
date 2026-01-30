package org.come.entity;

import java.util.Date;
import java.math.BigDecimal;

public class Salegoods
{
    private BigDecimal saleid;
    private String salename;
    private Integer saletype;
    private BigDecimal otherid;
    private String contiontype;
    private Integer flag;
    private Date uptime;
    private BigDecimal roleid;
    private BigDecimal buyrole;
    private BigDecimal saleprice;
    private String saleskin;
    
    public BigDecimal getSaleid() {
        return this.saleid;
    }
    
    public void setSaleid(BigDecimal saleid) {
        this.saleid = saleid;
    }
    
    public String getSalename() {
        return this.salename;
    }
    
    public void setSalename(String salename) {
        this.salename = ((salename == null) ? null : salename.trim());
    }
    
    public Integer getSaletype() {
        return this.saletype;
    }
    
    public void setSaletype(Integer saletype) {
        this.saletype = saletype;
    }
    
    public BigDecimal getOtherid() {
        return this.otherid;
    }
    
    public void setOtherid(BigDecimal otherid) {
        this.otherid = otherid;
    }
    
    public String getContiontype() {
        return this.contiontype;
    }
    
    public void setContiontype(String contiontype) {
        this.contiontype = ((contiontype == null) ? null : contiontype.trim());
    }
    
    public Integer getFlag() {
        return this.flag;
    }
    
    public void setFlag(Integer flag) {
        this.flag = flag;
    }
    
    public Date getUptime() {
        return this.uptime;
    }
    
    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }
    
    public BigDecimal getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }
    
    public BigDecimal getBuyrole() {
        return this.buyrole;
    }
    
    public void setBuyrole(BigDecimal buyrole) {
        this.buyrole = buyrole;
    }
    
    public BigDecimal getSaleprice() {
        return this.saleprice;
    }
    
    public void setSaleprice(BigDecimal saleprice) {
        this.saleprice = saleprice;
    }
    
    public String getSaleskin() {
        return this.saleskin;
    }
    
    public void setSaleskin(String saleskin) {
        this.saleskin = ((saleskin == null) ? null : saleskin.trim());
    }
}
