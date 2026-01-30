package org.come.entity;

import java.math.BigDecimal;

public class GoodsbuyrecordEntity
{
    private BigDecimal bid;
    private BigDecimal gid;
    private BigDecimal price;
    private BigDecimal buytype;
    private BigDecimal goodnumber;
    private BigDecimal numbermoney;
    private String recordtime;
    private BigDecimal userid;
    private BigDecimal roleid;
    private BigDecimal sid;
    
    public BigDecimal getBid() {
        return this.bid;
    }
    
    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }
    
    public BigDecimal getGid() {
        return this.gid;
    }
    
    public void setGid(BigDecimal gid) {
        this.gid = gid;
    }
    
    public BigDecimal getPrice() {
        return this.price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public BigDecimal getBuytype() {
        return this.buytype;
    }
    
    public void setBuytype(BigDecimal buytype) {
        this.buytype = buytype;
    }
    
    public BigDecimal getGoodnumber() {
        return this.goodnumber;
    }
    
    public void setGoodnumber(BigDecimal goodnumber) {
        this.goodnumber = goodnumber;
    }
    
    public BigDecimal getNumbermoney() {
        return this.numbermoney;
    }
    
    public void setNumbermoney(BigDecimal numbermoney) {
        this.numbermoney = numbermoney;
    }
    
    public String getRecordtime() {
        return this.recordtime;
    }
    
    public void setRecordtime(String recordtime) {
        this.recordtime = recordtime;
    }
    
    public BigDecimal getUserid() {
        return this.userid;
    }
    
    public void setUserid(BigDecimal userid) {
        this.userid = userid;
    }
    
    public BigDecimal getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }
    
    public BigDecimal getSid() {
        return this.sid;
    }
    
    public void setSid(BigDecimal sid) {
        this.sid = sid;
    }
}
