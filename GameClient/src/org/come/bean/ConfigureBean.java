package org.come.bean;

import org.come.model.Configure;
import java.math.BigDecimal;
import java.util.HashMap;

public class ConfigureBean
{
    private HashMap<BigDecimal, Configure> configureConcurrentHashMap;
    
    public HashMap<BigDecimal, Configure> getAllConfigure() {
        return this.configureConcurrentHashMap;
    }
    
    public void setConfigure(HashMap<BigDecimal, Configure> configureConcurrentHashMap) {
        this.configureConcurrentHashMap = configureConcurrentHashMap;
    }
}
