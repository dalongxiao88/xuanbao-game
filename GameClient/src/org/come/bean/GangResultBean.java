package org.come.bean;

import org.come.entity.Gangapplytable;
import java.util.List;
import org.come.entity.GangGroup;
import org.come.entity.Gang;

public class GangResultBean
{
    private Gang gang;
    private GangGroup gangGroup;
    private List<LoginResult> roleTables;
    private List<Gangapplytable> gangapplytables;
    
    public Gang getGang() {
        return this.gang;
    }
    
    public void setGang(Gang gang) {
        this.gang = gang;
    }
    
    public GangGroup getGangGroup() {
        return this.gangGroup;
    }
    
    public void setGangGroup(GangGroup gangGroup) {
        this.gangGroup = gangGroup;
    }
    
    public List<LoginResult> getRoleTables() {
        return this.roleTables;
    }
    
    public void setRoleTables(List<LoginResult> roleTables) {
        this.roleTables = roleTables;
    }
    
    public List<Gangapplytable> getGangapplytables() {
        return this.gangapplytables;
    }
    
    public void setGangapplytables(List<Gangapplytable> gangapplytables) {
        this.gangapplytables = gangapplytables;
    }
}
