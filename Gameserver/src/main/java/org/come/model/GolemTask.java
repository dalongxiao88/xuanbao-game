package org.come.model;

import java.math.BigDecimal;

public class GolemTask
{
    private BigDecimal id;
    private String name;
    private String guide;
    private int lvl;
    private int type;
    private int sum;
    private int taskSetID;
    private String value;
    
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getGuide() {
        return this.guide;
    }
    
    public void setGuide(String guide) {
        this.guide = guide;
    }
    
    public int getLvl() {
        return this.lvl;
    }
    
    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public int getSum() {
        return this.sum;
    }
    
    public void setSum(int sum) {
        this.sum = sum;
    }
    
    public int getTaskSetID() {
        return this.taskSetID;
    }
    
    public void setTaskSetID(int taskSetID) {
        this.taskSetID = taskSetID;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
}
