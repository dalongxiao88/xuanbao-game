package org.come.readBean;

import org.come.model.Talent;
import java.util.Map;

public class AllTalent
{
    private Map<Integer, Talent> allTalent;
    
    public Map<Integer, Talent> getAllTalent() {
        return this.allTalent;
    }
    
    public void setAllTalent(Map<Integer, Talent> allTalent) {
        this.allTalent = allTalent;
    }
}
