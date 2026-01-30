package org.come.bean;

import java.math.BigDecimal;
import org.come.entity.SellXianyu;
import java.util.List;

public class SearchSellXianYuResultBean
{
    private List<SellXianyu> sellxianyus;
    private int total;
    private List<BigDecimal> collections;
    
    public List<SellXianyu> getSellxianyus() {
        return this.sellxianyus;
    }
    
    public void setSellxianyus(List<SellXianyu> sellxianyus) {
        this.sellxianyus = sellxianyus;
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
