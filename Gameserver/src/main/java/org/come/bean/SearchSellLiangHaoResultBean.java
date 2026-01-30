package org.come.bean;

import java.math.BigDecimal;
import java.util.Map;
import org.come.model.SellLiangHaoBase;
import java.util.List;

public class SearchSellLiangHaoResultBean
{
    private List<SellLiangHaoBase> sellLiangHaos;
    private Map<String, String> xjDate;
    private int total;
    private List<BigDecimal> collections;
    
    public List<SellLiangHaoBase> getSellLiangHaos() {
        return this.sellLiangHaos;
    }
    
    public void setSellLiangHaos(List<SellLiangHaoBase> sellLiangHaos) {
        this.sellLiangHaos = sellLiangHaos;
    }
    
    public int getTotal() {
        return this.total;
    }
    
    public void setTotal(int total) {
        this.total = total;
    }
    
    public List<BigDecimal> getCollections() {
        return this.collections;
    }
    
    public void setCollections(List<BigDecimal> collections) {
        this.collections = collections;
    }
    
    public Map<String, String> getXjDate() {
        return this.xjDate;
    }
    
    public void setXjDate(Map<String, String> xjDate) {
        this.xjDate = xjDate;
    }
}
