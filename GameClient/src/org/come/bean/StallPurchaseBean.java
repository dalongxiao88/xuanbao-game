package org.come.bean;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
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
    
    public StallPurchase getStallPurchaseByGoodsId(BigDecimal goodsId) {
        return (StallPurchase)this.stallPurchaseMap.get(goodsId);
    }
    
    public Set<String> getStallPurchaseKeys(String key) {
        LinkedHashMap<String, LinkedList<StallPurchase>> value = (LinkedHashMap<String, LinkedList<StallPurchase>>)this.stallPurchaseTree.get(key);
        if (value != null) {
            return value.keySet();
        }
        return new HashSet<>();
    }
    
    public StallPurchase[] getStallPurchases(String rootKey, String key) {
        List<StallPurchase> stallPurchases = new ArrayList<>();
        LinkedHashMap<String, LinkedList<StallPurchase>> stallPurchaseMap = (LinkedHashMap<String, LinkedList<StallPurchase>>)this.stallPurchaseTree.get(rootKey);
        if (stallPurchaseMap != null) {
            LinkedList<StallPurchase> stallPurchasesList = (LinkedList<StallPurchase>)stallPurchaseMap.get(key);
            if (stallPurchasesList != null) {
                stallPurchases.addAll(stallPurchasesList);
            }
        }
        return (StallPurchase[])stallPurchases.toArray(new StallPurchase[0]);
    }
    
    public StallPurchase[] getStallPurchases(String name) {
        List<StallPurchase> stallPurchaseList = new ArrayList<>();
        for (LinkedHashMap<String, LinkedList<StallPurchase>> linkedHashMap : this.stallPurchaseTree.values()) {
            for (LinkedList<StallPurchase> value : linkedHashMap.values()) {
                stallPurchaseList.addAll(value);
            }
        }
        return this.getStallPurchases(stallPurchaseList, name);
    }
    
    private StallPurchase[] getStallPurchases(List<StallPurchase> stallPurchaseList, String name) {
        return (StallPurchase[])stallPurchaseList.stream().filter(stallPurchase/* org.come.bean.StallPurchase, */ -> stallPurchase.getGoodsName().indexOf(name) != -1).toArray(StallPurchase[]::new);
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
