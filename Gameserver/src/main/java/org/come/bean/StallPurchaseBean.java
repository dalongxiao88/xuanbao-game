package org.come.bean;

import java.util.Iterator;
import java.util.Set;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import java.util.LinkedList;
import java.util.LinkedHashMap;

public class StallPurchaseBean
{
    private LinkedHashMap<String, LinkedHashMap<String, LinkedList<StallPurchase>>> stallPurchaseTree;
    private ConcurrentHashMap<BigDecimal, StallPurchase> stallPurchaseMap;
    
    public Set<String> getStallPurchaseKeys() {
        return this.stallPurchaseTree.keySet();
    }
    
    public ConcurrentHashMap<BigDecimal, StallPurchase> initStallPurchaseMap() {
        this.stallPurchaseMap = new ConcurrentHashMap<>();
        for (LinkedHashMap<String, LinkedList<StallPurchase>> rootValue : this.stallPurchaseTree.values()) {
            for (LinkedList<StallPurchase> list : rootValue.values()) {
                for (StallPurchase stallPurchase : list) {
                    this.stallPurchaseMap.put(stallPurchase.getGoodsId(), stallPurchase);
                }
            }
        }
        return this.stallPurchaseMap;
    }
    
    public LinkedHashMap<String, LinkedHashMap<String, LinkedList<StallPurchase>>> getStallPurchaseTree() {
        return this.stallPurchaseTree;
    }
    
    public void setStallPurchaseTree(LinkedHashMap<String, LinkedHashMap<String, LinkedList<StallPurchase>>> stallPurchaseTree) {
        this.stallPurchaseTree = stallPurchaseTree;
    }
    
    public ConcurrentHashMap<BigDecimal, StallPurchase> getStallPurchaseMap() {
        return this.stallPurchaseMap;
    }
    
    public void setStallPurchaseMap(ConcurrentHashMap<BigDecimal, StallPurchase> stallPurchaseMap) {
        this.stallPurchaseMap = stallPurchaseMap;
    }
}
