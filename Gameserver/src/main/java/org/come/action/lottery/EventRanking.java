package org.come.action.lottery;

import come.tool.Role.RoleCard;
import java.util.Map;

public class EventRanking
{
    private Map<Integer, RoleCard[]> map;
    
    public Map<Integer, RoleCard[]> getMap() {
        return this.map;
    }
    
    public void setMap(Map<Integer, RoleCard[]> map) {
        this.map = map;
    }
}
