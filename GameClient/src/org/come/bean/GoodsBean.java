package org.come.bean;

import org.come.entity.Goodstable;
import java.math.BigDecimal;
import java.util.Map;

public class GoodsBean
{
    private Map<BigDecimal, Goodstable> allGoodsMap;
    
    public Map<BigDecimal, Goodstable> getAllGoodsMap() {
        return this.allGoodsMap;
    }
    
    public void setAllGoodsMap(Map<BigDecimal, Goodstable> allGoodsMap) {
        this.allGoodsMap = allGoodsMap;
    }
    
    public Goodstable getgoods(BigDecimal id) {
        Goodstable goods = (Goodstable)this.allGoodsMap.get(id);
        return goods;
    }
}
