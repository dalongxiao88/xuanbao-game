package org.come.bean;

import org.come.model.Gamemap;
import java.util.Map;

public class AllMapBean
{
    private Map<String, Gamemap> allMap;
    
    public Map<String, Gamemap> getAllMap() {
        return this.allMap;
    }
    
    public void setAllMap(Map<String, Gamemap> allMap) {
        this.allMap = allMap;
    }
}
