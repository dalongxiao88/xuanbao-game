package org.come.bean;

import org.come.entity.Goodstable;
import org.come.entity.RoleSummoning;

public class PetAlchemyBean
{
    private RoleSummoning roleSummoning;
    private Goodstable goodstable;
    
    public RoleSummoning getRoleSummoning() {
        return this.roleSummoning;
    }
    
    public void setRoleSummoning(RoleSummoning roleSummoning) {
        this.roleSummoning = roleSummoning;
    }
    
    public Goodstable getGoodstable() {
        return this.goodstable;
    }
    
    public void setGoodstable(Goodstable goodstable) {
        this.goodstable = goodstable;
    }
}
