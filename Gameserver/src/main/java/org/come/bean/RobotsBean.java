package org.come.bean;

import org.come.model.Robots;
import java.util.Map;

public class RobotsBean
{
    private Map<String, Robots> robotsMap;
    
    public Map<String, Robots> getRobotsMap() {
        return this.robotsMap;
    }
    
    public void setRobotsMap(Map<String, Robots> robotsMap) {
        this.robotsMap = robotsMap;
    }
}
