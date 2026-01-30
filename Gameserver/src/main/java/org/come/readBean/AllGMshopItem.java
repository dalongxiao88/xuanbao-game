package org.come.readBean;

import org.come.model.GMshopItem;
import java.math.BigDecimal;
import java.util.Map;

public class AllGMshopItem
{
    private Map<BigDecimal, GMshopItem> gMshopItemConcurrentHashMap;
    
    public Map<BigDecimal, GMshopItem> getAllGMshopItem() {
        return this.gMshopItemConcurrentHashMap;
    }
    
    public void setAllGMshopItem(Map<BigDecimal, GMshopItem> gMshopItemConcurrentHashMap) {
        this.gMshopItemConcurrentHashMap = gMshopItemConcurrentHashMap;
    }
}
