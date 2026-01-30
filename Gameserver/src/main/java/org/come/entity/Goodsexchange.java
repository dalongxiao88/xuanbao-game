package org.come.entity;

import java.util.Date;

public class Goodsexchange
{
    private String goodsguid;
    private Integer flag;
    private String goodsid;
    private Date outtime;
    
    public String getGoodsguid() {
        return this.goodsguid;
    }
    
    public void setGoodsguid(String goodsguid) {
        this.goodsguid = ((goodsguid == null) ? null : goodsguid.trim());
    }
    
    public Integer getFlag() {
        return this.flag;
    }
    
    public void setFlag(Integer flag) {
        this.flag = flag;
    }
    
    public String getGoodsid() {
        return this.goodsid;
    }
    
    public void setGoodsid(String goodsid) {
        this.goodsid = ((goodsid == null) ? null : goodsid.trim());
    }
    
    public Date getOuttime() {
        return this.outtime;
    }
    
    public void setOuttime(Date outtime) {
        this.outtime = outtime;
    }
}
