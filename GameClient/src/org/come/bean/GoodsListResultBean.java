package org.come.bean;

import org.come.entity.Goodstable;
import java.util.List;

public class GoodsListResultBean
{
    private List<Goodstable> goodstables;
    
    public List<Goodstable> getGoodstables() {
        return this.goodstables;
    }
    
    public void setGoodstables(List<Goodstable> goodstables) {
        this.goodstables = goodstables;
    }
}
