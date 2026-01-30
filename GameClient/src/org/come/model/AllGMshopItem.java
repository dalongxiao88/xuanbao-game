package org.come.model;

import java.util.concurrent.ConcurrentHashMap;

public class AllGMshopItem
{
    private ConcurrentHashMap<Integer, GMshopItem> allGMshopItem;
    
    public ConcurrentHashMap<Integer, GMshopItem> getAllGMshopItem() {
        return this.allGMshopItem;
    }
    
    public void setAllGMshopItem(ConcurrentHashMap<Integer, GMshopItem> allGMshopItem) {
        this.allGMshopItem = allGMshopItem;
    }
}
