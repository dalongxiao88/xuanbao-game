package org.come.readBean;

import org.come.model.AuctionExchange;
import java.util.concurrent.ConcurrentHashMap;
import org.come.entity.AuctionTime;

public class AllAuctionGoodsExchange
{
    private AuctionTime auctionTime;
    private ConcurrentHashMap<Integer, AuctionExchange> allAuctionAuctionExchange;
    
    public ConcurrentHashMap<Integer, AuctionExchange> getAllAuctionAuctionExchange() {
        return this.allAuctionAuctionExchange;
    }
    
    public void setAllAuctionAuctionExchange(ConcurrentHashMap<Integer, AuctionExchange> allAuctionAuctionExchange) {
        this.allAuctionAuctionExchange = allAuctionAuctionExchange;
    }
    
    public AuctionTime getAuctionTime() {
        return this.auctionTime;
    }
    
    public void setAuctionTime(AuctionTime auctionTime) {
        this.auctionTime = auctionTime;
    }
}
