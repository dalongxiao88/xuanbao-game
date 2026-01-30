package org.come.readBean;

import org.come.model.Configure;
import java.math.BigDecimal;
import java.util.Map;

public class AllConfigure
{
    private Map<BigDecimal, Configure> configureConcurrentHashMap;
    
    public Map<BigDecimal, Configure> getAllConfigure() {
        return this.configureConcurrentHashMap;
    }
    
    public void setAllConfigure(Map<BigDecimal, Configure> configureConcurrentHashMap) {
        this.configureConcurrentHashMap = configureConcurrentHashMap;
    }
}
