package org.come.bean;

import org.come.entity.RoleSummoning;
import org.come.entity.Goodstable;

public class StallBuyBean
{
    private Goodstable goodstable;
    private String stallName;
    private RoleSummoning roleSummoning;
    
    public Goodstable getGoodstable() {
        return this.goodstable;
    }
    
    public void setGoodstable(Goodstable goodstable) {
        this.goodstable = goodstable;
    }
    
    public RoleSummoning getRoleSummoning() {
        return this.roleSummoning;
    }
    
    public void setRoleSummoning(RoleSummoning roleSummoning) {
        this.roleSummoning = roleSummoning;
    }
    
    public String getStallName() {
        return this.stallName;
    }
    
    public void setStallName(String stallName) {
        this.stallName = stallName;
    }
}
