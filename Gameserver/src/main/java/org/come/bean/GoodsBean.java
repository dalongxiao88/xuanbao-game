package org.come.bean;

import org.come.model.GoodModel;
import java.math.BigDecimal;
import java.util.Map;

public class GoodsBean
{
    private Map<BigDecimal, GoodModel> allGoodsMap;
    
    public Map<BigDecimal, GoodModel> getAllGoodsMap() {
        return this.allGoodsMap;
    }
    
    public void setAllGoodsMap(Map<BigDecimal, GoodModel> allGoodsMap) {
        this.allGoodsMap = allGoodsMap;
    }
}
