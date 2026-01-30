package org.come.bean;

import org.come.model.Save;
import java.util.Map;

public class SaveBean
{
    private Map<Integer, Save> allSaveMap;
    
    public Map<Integer, Save> getAllSaveMap() {
        return this.allSaveMap;
    }
    
    public void setAllSaveMap(Map<Integer, Save> allSaveMap) {
        this.allSaveMap = allSaveMap;
    }
}
