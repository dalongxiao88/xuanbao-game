package org.come.bean;

import java.math.BigDecimal;
import org.come.entity.Salegoods;
import java.util.List;

public class SearchGoodsResultBean
{
    private List<Salegoods> salegoods;
    private int total;
    private List<BigDecimal> collections;
    
    public List<Salegoods> getSalegoods() {
        return this.salegoods;
    }
    
    public void setSalegoods(List<Salegoods> salegoods) {
        this.salegoods = salegoods;
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
}
