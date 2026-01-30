package org.come.model;

import java.util.concurrent.ConcurrentHashMap;

public class allGoodsExchange
{
    private ConcurrentHashMap<Integer, GoodsExchange> allGoodsExchange;
    
    public ConcurrentHashMap<Integer, GoodsExchange> getAllGoodsExchange() {
        return this.allGoodsExchange;
    }
    
    public void setAllGoodsExchange(ConcurrentHashMap<Integer, GoodsExchange> concurrentHashMap) {
        this.allGoodsExchange = concurrentHashMap;
    }
}
