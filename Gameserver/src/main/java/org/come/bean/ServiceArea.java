package org.come.bean;

import java.util.Date;
import java.math.BigDecimal;

public class ServiceArea
{
    private BigDecimal sid;
    private String sname;
    private Date sdate;
    private String agents;
    private String dividedinto;
    private int controlStyle;
    private BigDecimal manaeid;
    private int pageNum;
    
    public int getPageNum() {
        return this.pageNum;
    }
    
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    
    public int getControlStyle() {
        return this.controlStyle;
    }
    
    public void setControlStyle(int controlStyle) {
        this.controlStyle = controlStyle;
    }
    
    public BigDecimal getManaeid() {
        return this.manaeid;
    }
    
    public void setManaeid(BigDecimal manaeid) {
        this.manaeid = manaeid;
    }
    
    public String getAgents() {
        return this.agents;
    }
    
    public void setAgents(String agents) {
        this.agents = agents;
    }
    
    public String getDividedinto() {
        return this.dividedinto;
    }
    
    public void setDividedinto(String dividedinto) {
        this.dividedinto = dividedinto;
    }
    
    public BigDecimal getSid() {
        return this.sid;
    }
    
    public void setSid(BigDecimal sid) {
        this.sid = sid;
    }
    
    public String getSname() {
        return this.sname;
    }
    
    public void setSname(String sname) {
        this.sname = ((sname == null) ? null : sname.trim());
    }
    
    public Date getSdate() {
        return this.sdate;
    }
    
    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }
}
