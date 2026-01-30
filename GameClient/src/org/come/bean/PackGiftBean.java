package org.come.bean;

import java.util.List;
import org.come.entity.Goodstable;

public class PackGiftBean
{
    private Goodstable goodstable;
    private List<String> goods;
    
    public Goodstable getGoodstable() {
        return this.goodstable;
    }
    
    public void setGoodstable(Goodstable goodstable) {
        this.goodstable = goodstable;
    }
    
    public List<String> getGoods() {
        return this.goods;
    }
    
    public void setGoods(List<String> goods) {
        this.goods = goods;
    }
}
