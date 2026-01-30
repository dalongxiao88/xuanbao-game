package org.come.entity;

import come.tool.Calculation.BaseQl;

public class StarPalace
{
    private String type;
    private String value;
    private BaseQl[] vs;
    
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public BaseQl[] getVs() {
        return this.vs;
    }
    
    public void setVs(BaseQl[] vs) {
        this.vs = vs;
    }
}
