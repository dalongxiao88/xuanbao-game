package org.come.readBean;

import org.come.model.Door;
import java.util.Map;

public class AllDoorBean
{
    private Map<String, Door> alldoor;
    
    public Map<String, Door> getAlldoor() {
        return this.alldoor;
    }
    
    public void setAlldoor(Map<String, Door> alldoor) {
        this.alldoor = alldoor;
    }
}
