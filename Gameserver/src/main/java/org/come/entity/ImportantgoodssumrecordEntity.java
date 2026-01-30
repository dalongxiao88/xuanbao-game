package org.come.entity;

import java.math.BigDecimal;

public class ImportantgoodssumrecordEntity
{
    private BigDecimal id;
    private BigDecimal gid;
    private BigDecimal goodnumber;
    private String datetime;
    private BigDecimal sid;
    
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    public BigDecimal getGid() {
        return this.gid;
    }
    
    public void setGid(BigDecimal gid) {
        this.gid = gid;
    }
    
    public BigDecimal getGoodnumber() {
        return this.goodnumber;
    }
    
    public void setGoodnumber(BigDecimal goodnumber) {
        this.goodnumber = goodnumber;
    }
    
    public String getDatetime() {
        return this.datetime;
    }
    
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
    
    public BigDecimal getSid() {
        return this.sid;
    }
    
    public void setSid(BigDecimal sid) {
        this.sid = sid;
    }
}
