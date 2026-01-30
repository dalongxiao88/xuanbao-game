package org.come.bean;

import come.tool.Role.PartJade;
import java.math.BigDecimal;
import java.util.List;

public class SuitOperBean
{
    private int type;
    private List<BigDecimal> goods;
    private PartJade jade;
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public List<BigDecimal> getGoods() {
        return this.goods;
    }
    
    public void setGoods(List<BigDecimal> goods) {
        this.goods = goods;
    }
    
    public PartJade getJade() {
        return this.jade;
    }
    
    public void setJade(PartJade jade) {
        this.jade = jade;
    }
}
