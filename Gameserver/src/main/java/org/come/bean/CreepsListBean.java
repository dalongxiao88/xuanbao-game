package org.come.bean;

import java.math.BigDecimal;
import org.come.entity.Goodstable;
import org.come.model.Monster;
import java.util.List;

public class CreepsListBean
{
    private List<Monster> fightCreeps;
    private Goodstable goodstable;
    private BigDecimal exp;
    private BigDecimal gold;
    private Integer skilled;
    private int maxlvl;
    
    public List<Monster> getFightCreeps() {
        return this.fightCreeps;
    }
    
    public void setFightCreeps(List<Monster> fightCreeps) {
        this.fightCreeps = fightCreeps;
    }
    
    public Goodstable getGoodstable() {
        return this.goodstable;
    }
    
    public void setGoodstable(Goodstable goodstable) {
        this.goodstable = goodstable;
    }
    
    public BigDecimal getExp() {
        return this.exp;
    }
    
    public void setExp(BigDecimal exp) {
        this.exp = exp;
    }
    
    public BigDecimal getGold() {
        return this.gold;
    }
    
    public void setGold(BigDecimal gold) {
        this.gold = gold;
    }
    
    public Integer getSkilled() {
        return this.skilled;
    }
    
    public void setSkilled(Integer skilled) {
        this.skilled = skilled;
    }
    
    public int getMaxlvl() {
        return this.maxlvl;
    }
    
    public void setMaxlvl(int maxlvl) {
        this.maxlvl = maxlvl;
    }
}
