package org.come.model;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class NewequipMapObject
{
    ConcurrentHashMap<String, List<Newequip>> sameNewequipMap;
    ConcurrentHashMap<String, List<Newequip>> witchNewequipMap;
    
    public ConcurrentHashMap<String, List<Newequip>> getSameNewequipMap() {
        return this.sameNewequipMap;
    }
    
    public void setSameNewequipMap(ConcurrentHashMap<String, List<Newequip>> sameNewequipMap) {
        this.sameNewequipMap = sameNewequipMap;
    }
    
    public ConcurrentHashMap<String, List<Newequip>> getWitchNewequipMap() {
        return this.witchNewequipMap;
    }
    
    public void setWitchNewequipMap(ConcurrentHashMap<String, List<Newequip>> witchNewequipMap) {
        this.witchNewequipMap = witchNewequipMap;
    }
}
