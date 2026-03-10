package org.come.entity;

import java.util.Date;

/**
 * 服务端兑换记录实体。
 */
public class Goodsexchange
{
    private String goodsguid;
    private Integer flag;
    private String goodsid;
    private Date outtime;
    
    public String getGoodsguid() {
        return this.goodsguid;
    }

    /** 语义化别名：兑换记录 GUID。 */
    public String getGoodsGuid() {
        return this.goodsguid;
    }
    
    public void setGoodsguid(String goodsguid) {
        this.goodsguid = ((goodsguid == null) ? null : goodsguid.trim());
    }

    public void setGoodsGuid(String goodsGuid) {
        this.goodsguid = ((goodsGuid == null) ? null : goodsGuid.trim());
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

    /** 语义化别名：物品 ID。 */
    public String getGoodsId() {
        return this.goodsid;
    }
    
    public void setGoodsid(String goodsid) {
        this.goodsid = ((goodsid == null) ? null : goodsid.trim());
    }

    public void setGoodsId(String goodsId) {
        this.goodsid = ((goodsId == null) ? null : goodsId.trim());
    }
    
    public Date getOuttime() {
        return this.outtime;
    }
    
    public void setOuttime(Date outtime) {
        this.outtime = outtime;
    }
}
