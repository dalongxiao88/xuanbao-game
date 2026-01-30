package org.come.bean;

import org.come.entity.RoleSummoning;
import org.come.entity.Goodstable;
import java.util.List;

public class StallBean
{
    private List<Goodstable> goodstables;
    private List<RoleSummoning> roleSummonings;
    private String rolename;
    
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
    
    public String getRolename() {
        return this.rolename;
    }
    
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
