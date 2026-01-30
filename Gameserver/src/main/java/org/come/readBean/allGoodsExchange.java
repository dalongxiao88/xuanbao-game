package org.come.readBean;

import org.come.model.GoodsExchange;
import java.util.concurrent.ConcurrentHashMap;

public class allGoodsExchange
{
    private ConcurrentHashMap<Integer, GoodsExchange> allGoodsExchange;
    
    public ConcurrentHashMap<Integer, GoodsExchange> getAllGoodsExchange() {
        return this.allGoodsExchange;
    }
    
    public void setAllGoodsExchange(ConcurrentHashMap<Integer, GoodsExchange> allGoodsExchange2) {
        this.allGoodsExchange = allGoodsExchange2;
    }
}
