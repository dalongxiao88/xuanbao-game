package org.come.entity;

import java.math.BigDecimal;

public class ImportantrecordgoodsEntity
{
    private static final long serialVersionUID = 5533461929429133696L;
    private BigDecimal iid;
    private BigDecimal goodsid;
    private BigDecimal goodsnumber;
    private BigDecimal rocordtype;
    private BigDecimal userid;
    private BigDecimal roleid;
    private BigDecimal sid;
    private String datetime;
    
    public BigDecimal getIid() {
        return this.iid;
    }
    
    public void setIid(BigDecimal iid) {
        this.iid = iid;
    }
    
    public BigDecimal getGoodsid() {
        return this.goodsid;
    }
    
    public void setGoodsid(BigDecimal goodsid) {
        this.goodsid = goodsid;
    }
    
    public BigDecimal getGoodsnumber() {
        return this.goodsnumber;
    }
    
    public void setGoodsnumber(BigDecimal goodsnumber) {
        this.goodsnumber = goodsnumber;
    }
    
    public BigDecimal getRocordtype() {
        return this.rocordtype;
    }
    
    public void setRocordtype(BigDecimal rocordtype) {
        this.rocordtype = rocordtype;
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
    
    public String getDatetime() {
        return this.datetime;
    }
    
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
