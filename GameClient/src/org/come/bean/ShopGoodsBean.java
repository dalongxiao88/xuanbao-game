package org.come.bean;

import org.come.model.Shop;
import java.util.List;

public class ShopGoodsBean
{
    private Integer nId;
    private int type;
    private List<Shop> shopList;
    
    public Integer getnId() {
        return this.nId;
    }
    
    public void setnId(Integer nId) {
        this.nId = nId;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public List<Shop> getShopList() {
        return this.shopList;
    }
    
    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }
}
