package org.come.bean;

import org.come.model.Alchemy;
import java.util.List;
import java.util.Map;

public class AlchemyBean
{
    private Map<String, List<Alchemy>> allAlchemy;
    
    public Map<String, List<Alchemy>> getAllAlchemy() {
        return this.allAlchemy;
    }
    
    public void setAllAlchemy(Map<String, List<Alchemy>> allAlchemy) {
        this.allAlchemy = allAlchemy;
    }
}
