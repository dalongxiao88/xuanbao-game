package org.come.readBean;

import org.come.entity.Suit;
import java.util.Map;

public class AllSuit
{
    private Map<Integer, Suit> rolesuit;
    
    public Map<Integer, Suit> getRolesuit() {
        return this.rolesuit;
    }
    
    public void setRolesuit(Map<Integer, Suit> rolesuit) {
        this.rolesuit = rolesuit;
    }
}
