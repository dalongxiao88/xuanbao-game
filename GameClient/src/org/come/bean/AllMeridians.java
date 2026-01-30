package org.come.bean;

import java.util.Iterator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllMeridians
{
    private Map<Integer, List<Meridians>> meridiansMap;
    
    public AllMeridians() {
        this.meridiansMap = new HashMap<>();
    }
    
    public Map<Integer, List<Meridians>> getMeridiansMap() {
        return this.meridiansMap;
    }
    
    public void setMeridiansMap(Map<Integer, List<Meridians>> meridiansMap) {
        this.meridiansMap = meridiansMap;
    }
    
    public Meridians getByQuality(int id, int quality) {
        for (Meridians m : (List<Meridians>)this.meridiansMap.get(Integer.valueOf(id))) {
            if (m.getQuality() == quality) {
                return m;
            }
        }
        return null;
    }
}
