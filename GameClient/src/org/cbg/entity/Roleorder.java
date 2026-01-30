package org.cbg.entity;

import java.util.Date;
import java.math.BigDecimal;

public class Roleorder
{
    private BigDecimal orderid;
    private BigDecimal saleid;
    private Date buytime;
    private Integer status;
    private BigDecimal roleid;
    private String ordernumber;
    private String salename;
    private String saleskin;
    private BigDecimal saleprice;
    private Integer saletype;
    private BigDecimal otherid;
    
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
    
    public BigDecimal getOrderid() {
        return this.orderid;
    }
    
    public void setOrderid(BigDecimal orderid) {
        this.orderid = orderid;
    }
    
    public BigDecimal getSaleid() {
        return this.saleid;
    }
    
    public void setSaleid(BigDecimal saleid) {
        this.saleid = saleid;
    }
    
    public Date getBuytime() {
        return this.buytime;
    }
    
    public void setBuytime(Date buytime) {
        this.buytime = buytime;
    }
    
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public BigDecimal getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }
    
    public String getOrdernumber() {
        return this.ordernumber;
    }
    
    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ((ordernumber == null) ? null : ordernumber.trim());
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
