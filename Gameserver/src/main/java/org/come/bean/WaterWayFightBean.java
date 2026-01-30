package org.come.bean;

import java.util.HashMap;
import java.util.Map;

public class WaterWayFightBean
{
    private Map<Integer, WaterFightBean> gropuTeam;
    
    public WaterWayFightBean() {
        this.gropuTeam = new HashMap<>();
    }
    
    public Map<Integer, WaterFightBean> getGropuTeam() {
        return this.gropuTeam;
    }
    
    public void setGropuTeam(Map<Integer, WaterFightBean> gropuTeam) {
        this.gropuTeam = gropuTeam;
    }
}
