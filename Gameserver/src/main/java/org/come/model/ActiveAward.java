package org.come.model;

import come.tool.Good.DropModel;

public class ActiveAward
{
    private int active;
    private String value;
    private String jvValue;//奖励描述
    private transient DropModel dropModel;
    
    public ActiveAward(int active) {
        this.active = active;
    }
    
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
    
    public DropModel getDropModel() {
        return this.dropModel;
    }
    
    public void setDropModel(DropModel dropModel) {
        this.dropModel = dropModel;
    }
    public String getJvValue() {
        return jvValue;
    }

    public void setJvValue(String jvValue) {
        this.jvValue = jvValue;
    }
}
