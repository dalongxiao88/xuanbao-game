package org.come.bean;

import org.come.entity.RoleSummoning;
import java.util.List;

public class PetResultBean
{
    private List<RoleSummoning> roleSummonings;
    
    public List<RoleSummoning> getRoleSummonings() {
        return this.roleSummonings;
    }
    
    public void setRoleSummonings(List<RoleSummoning> roleSummonings) {
        this.roleSummonings = roleSummonings;
    }
}
