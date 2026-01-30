package org.come.bean;

import java.util.List;
import java.math.BigDecimal;

public class AuctionSceneInfo
{
    private BigDecimal role_id;
    private BigDecimal money;
    private List<AuctionLog> auctionLogList;
    
    public BigDecimal getRole_id() {
        return this.role_id;
    }
    
    public void setRole_id(BigDecimal role_id) {
        this.role_id = role_id;
    }
    
    public BigDecimal getMoney() {
        return this.money;
    }
    
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    
    public List<AuctionLog> getAuctionLogList() {
        return this.auctionLogList;
    }
    
    public void setAuctionLogList(List<AuctionLog> auctionLogList) {
        this.auctionLogList = auctionLogList;
    }
}
