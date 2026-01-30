package org.come.bean;

import java.math.BigDecimal;
import org.come.entity.RoleSummoning;
import org.come.entity.Goodstable;
import java.util.List;

public class TransactionBean
{
    private String roleName;
    private List<Goodstable> goodstables;
    private List<RoleSummoning> roleSummonings;
    private BigDecimal gold;
    
    public List<Goodstable> getGoodstables() {
        return this.goodstables;
    }
    
    public void setGoodstables(List<Goodstable> goodstables) {
        this.goodstables = goodstables;
    }
    
    public List<RoleSummoning> getRoleSummonings() {
        return this.roleSummonings;
    }
    
    public void setRoleSummonings(List<RoleSummoning> roleSummonings) {
        this.roleSummonings = roleSummonings;
    }
    
    public BigDecimal getGold() {
        return this.gold;
    }
    
    public void setGold(BigDecimal gold) {
        this.gold = gold;
    }
    
    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
