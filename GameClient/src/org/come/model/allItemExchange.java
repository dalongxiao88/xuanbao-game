package org.come.model;

import java.util.concurrent.ConcurrentHashMap;

public class allItemExchange
{
    private ConcurrentHashMap<Integer, ItemExchange> allItemExchange;
    
    public ConcurrentHashMap<Integer, ItemExchange> getAllItemExchange() {
        return this.allItemExchange;
    }
    
    public void setAllItemExchange(ConcurrentHashMap<Integer, ItemExchange> concurrentHashMap) {
        this.allItemExchange = concurrentHashMap;
    }
}
