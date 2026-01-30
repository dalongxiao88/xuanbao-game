package org.come.bean;

import org.come.model.Door;
import org.come.model.Talk;
import java.util.List;
import org.come.model.Npctable;

public class NpcInfoBean
{
    private Npctable npctable;
    private List<Talk> talks;
    private List<Door> doors;
    
    public Npctable getNpctable() {
        return this.npctable;
    }
    
    public void setNpctable(Npctable npctable) {
        this.npctable = npctable;
    }
    
    public List<Talk> getTalks() {
        return this.talks;
    }
    
    public void setTalks(List<Talk> talks) {
        this.talks = talks;
    }
    
    public List<Door> getDoors() {
        return this.doors;
    }
    
    public void setDoors(List<Door> doors) {
        this.doors = doors;
    }
}
