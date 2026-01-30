package org.come.bean;

import java.math.BigDecimal;
import java.util.Map;

public class NeidanExpBean
{
    private Map<Integer, BigDecimal> neidanExp;
    
    public Map<Integer, BigDecimal> getNeidanExp() {
        return this.neidanExp;
    }
    
    public void setNeidanExp(Map<Integer, BigDecimal> neidanExp) {
        this.neidanExp = neidanExp;
    }
}
