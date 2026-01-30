package org.come.readBean;

import org.come.model.ItemExchange;
import java.util.concurrent.ConcurrentHashMap;

public class allItemExchange
{
    private ConcurrentHashMap<Integer, ItemExchange> allItemExchange;
    
    public ConcurrentHashMap<Integer, ItemExchange> getAllItemExchange() {
        return this.allItemExchange;
    }
    
    public void setAllItemExchange(ConcurrentHashMap<Integer, ItemExchange> allItemExchange) {
        this.allItemExchange = allItemExchange;
    }
}
