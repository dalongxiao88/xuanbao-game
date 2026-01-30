package org.come.bean;

import java.util.List;
import java.math.BigDecimal;
import org.come.entity.Goodstable;

public class GiveResultBean
{
    private String rolename;
    private Goodstable goodstable;
    private BigDecimal gold;
    private List<Goodstable> goodstables;
    
    public String getRolename() {
        return this.rolename;
    }
    
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    
    public Goodstable getGoodstable() {
        return this.goodstable;
    }
    
    public void setGoodstable(Goodstable goodstable) {
        this.goodstable = goodstable;
    }
    
    public BigDecimal getGold() {
        return this.gold;
    }
    
    public void setGold(BigDecimal gold) {
        this.gold = gold;
    }
    
    public List<Goodstable> getGoodstables() {
        return this.goodstables;
    }
    
    public void setGoodstables(List<Goodstable> goodstables) {
        this.goodstables = goodstables;
    }
}
