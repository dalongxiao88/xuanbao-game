package org.come.bean;

import java.math.BigDecimal;
import org.come.entity.RoleSummoning;
import org.come.entity.Goodstable;

public class TransPlanBean
{
    private Goodstable goodstable;
    private RoleSummoning summoning;
    private BigDecimal gold;
    private BigDecimal roleID;
    
    public Goodstable getGoodstable() {
        return this.goodstable;
    }
    
    public void setGoodstable(Goodstable goodstable) {
        this.goodstable = goodstable;
    }
    
    public RoleSummoning getSummoning() {
        return this.summoning;
    }
    
    public void setSummoning(RoleSummoning summoning) {
        this.summoning = summoning;
    }
    
    public BigDecimal getRoleID() {
        return this.roleID;
    }
    
    public void setRoleID(BigDecimal roleID) {
        this.roleID = roleID;
    }
    
    public BigDecimal getGold() {
        return this.gold;
    }
    
    public void setGold(BigDecimal gold) {
        this.gold = gold;
    }
}
