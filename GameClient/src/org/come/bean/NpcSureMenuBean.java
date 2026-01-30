package org.come.bean;

import java.util.HashMap;
import java.util.Map;

public class NpcSureMenuBean
{
    private String conditions;
    private Map<String, String> menuAndMethod;
    
    public NpcSureMenuBean() {
        this.menuAndMethod = new HashMap<>();
    }
    
    public String getConditions() {
        return this.conditions;
    }
    
    public void setConditions(String conditions) {
        this.conditions = conditions;
    }
    
    public Map<String, String> getMenuAndMethod() {
        return this.menuAndMethod;
    }
    
    public void setMenuAndMethod(Map<String, String> menuAndMethod) {
        this.menuAndMethod = menuAndMethod;
    }
}
