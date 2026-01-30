package org.come.bean;

import java.util.HashMap;
import java.util.Map;

public class AllSuit
{
    private Map<Integer, RoleSuitBean> rolesuit;
    
    public AllSuit() {
        this.rolesuit = new HashMap<>();
    }
    
    public Map<Integer, RoleSuitBean> getRolesuit() {
        return this.rolesuit;
    }
    
    public void setRolesuit(Map<Integer, RoleSuitBean> rolesuit) {
        this.rolesuit = rolesuit;
    }
}
