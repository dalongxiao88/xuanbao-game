package org.come.bean;

import org.come.model.EventModel;
import java.util.Map;

public class AllEventModelBean
{
    private Map<Integer, EventModel> allEventModelMap;
    
    public Map<Integer, EventModel> getAllEventModelMap() {
        return this.allEventModelMap;
    }
    
    public void setAllEventModelMap(Map<Integer, EventModel> allEventModelMap) {
        this.allEventModelMap = allEventModelMap;
    }
}
