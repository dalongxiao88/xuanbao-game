package org.come.bean;

import org.apache.commons.lang.StringUtils;

public class TurnNameBean
{
    private String index;
    private String name;
    private String color;
    
    public String getIndex() {
        return this.index;
    }
    
    public void setIndex(String index) {
        this.index = index;
    }
    
    public String getName() {
        if (this.name == null) {
            this.name = "";
        }
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getColor() {
        if (StringUtils.isBlank(this.color)) {
            this.color = "00ff00";
        }
        return this.color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
}
