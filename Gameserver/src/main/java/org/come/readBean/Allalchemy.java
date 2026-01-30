package org.come.readBean;

import org.come.model.Alchemy;
import java.math.BigDecimal;
import java.util.Map;

public class Allalchemy
{
    private Map<BigDecimal, Alchemy> alchemyConcurrentHashMap;
    
    public Map<BigDecimal, Alchemy> getAllalchemy() {
        return this.alchemyConcurrentHashMap;
    }
    
    public void setAllalchemy(Map<BigDecimal, Alchemy> alchemyConcurrentHashMap) {
        this.alchemyConcurrentHashMap = alchemyConcurrentHashMap;
    }
}
