package org.come.bean;

import org.come.model.Shop;
import java.util.List;

public class ShopResultBean
{
    private List<Shop> shops;
    
    public List<Shop> getShops() {
        return this.shops;
    }
    
    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }
}
