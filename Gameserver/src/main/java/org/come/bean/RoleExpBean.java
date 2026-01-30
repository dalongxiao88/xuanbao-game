package org.come.bean;

import java.math.BigDecimal;
import java.util.Map;

public class RoleExpBean
{
    private Map<Integer, BigDecimal> rolePetExpMap;
    
    public Map<Integer, BigDecimal> getRolePetExpMap() {
        return this.rolePetExpMap;
    }
    
    public void setRolePetExpMap(Map<Integer, BigDecimal> rolePetExpMap) {
        this.rolePetExpMap = rolePetExpMap;
    }
}
