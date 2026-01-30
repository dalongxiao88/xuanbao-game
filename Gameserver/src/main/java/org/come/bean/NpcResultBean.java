package org.come.bean;

import org.come.model.Door;
import java.util.List;
import org.come.model.Npctable;

public class NpcResultBean
{
    private Npctable npctable;
    private List<Door> doors;
    
    public Npctable getNpctable() {
        return this.npctable;
    }
    
    public void setNpctable(Npctable npctable) {
        this.npctable = npctable;
    }
    
    public List<Door> getDoors() {
        return this.doors;
    }
    
    public void setDoors(List<Door> doors) {
        this.doors = doors;
    }
}
