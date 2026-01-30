package org.come.model;

import java.util.List;

public class Escort
{
    private int id;
    private int type;
    private List<EscortPath> escortPaths;
    private List<EscortEvents> escortEvents;
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public List<EscortPath> getEscortPaths() {
        return this.escortPaths;
    }
    
    public void setEscortPaths(List<EscortPath> escortPaths) {
        this.escortPaths = escortPaths;
    }
    
    public List<EscortEvents> getEscortEvents() {
        return this.escortEvents;
    }
    
    public void setEscortEvents(List<EscortEvents> escortEvents) {
        this.escortEvents = escortEvents;
    }
}
