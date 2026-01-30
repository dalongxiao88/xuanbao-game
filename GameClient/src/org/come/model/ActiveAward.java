package org.come.model;

public class ActiveAward
{
    private int active;
    private String value;
    private String jvValue;//奖励描述
    public int getActive() {
        return this.active;
    }
    
    public void setActive(int active) {
        this.active = active;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    public String getJvValue() {
        return jvValue;
    }

    public void setJvValue(String jvValue) {
        this.jvValue = jvValue;
    }
}
