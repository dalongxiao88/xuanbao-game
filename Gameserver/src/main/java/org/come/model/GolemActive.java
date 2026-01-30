package org.come.model;

import java.math.BigDecimal;

public class GolemActive
{
    private BigDecimal id;
    private String name;
    private String guide;
    private String lvl;
    private int type;
    private int sum;
    private int tasksetId;
    private String week;
    private transient int[] lvls;
    
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
    
    public String getLvl() {
        return this.lvl;
    }
    
    public void setLvl(String lvl) {
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
    
    public int getTasksetId() {
        return this.tasksetId;
    }
    
    public void setTasksetId(int tasksetId) {
        this.tasksetId = tasksetId;
    }
    
    public String getWeek() {
        return this.week;
    }
    
    public void setWeek(String week) {
        this.week = week;
    }
    
    public int[] getLvls() {
        return this.lvls;
    }
    
    public void setLvls(int[] lvls) {
        this.lvls = lvls;
    }
}
