package org.come.bean;

import org.come.model.GMshopItem;
import java.math.BigDecimal;
import java.util.HashMap;

public class GMshopItemBean
{
    private HashMap<BigDecimal, GMshopItem> gMshopItemConcurrentHashMap;
    
    public HashMap<BigDecimal, GMshopItem> getAllGMshopItem() {
        return this.gMshopItemConcurrentHashMap;
    }
    
    public void setAllPetExchange(HashMap<BigDecimal, GMshopItem> gMshopItemConcurrentHashMap) {
        this.gMshopItemConcurrentHashMap = gMshopItemConcurrentHashMap;
    }
}
