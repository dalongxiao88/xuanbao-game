package org.come.bean;

import java.math.BigDecimal;

public class AutoActiveReidsBase
{
    private int id;
    private String aName;
    private int tasksetId;
    private int goodNum;
    private int maxNum;
    private int comNum;
    private String numData;
    public BigDecimal roleId;
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getaName() {
        return this.aName;
    }
    
    public void setaName(String aName) {
        this.aName = aName;
    }
    
    public int getTasksetId() {
        return this.tasksetId;
    }
    
    public void setTasksetId(int tasksetId) {
        this.tasksetId = tasksetId;
    }
    
    public int getGoodNum() {
        return this.goodNum;
    }
    
    public void setGoodNum(int goodNum) {
        this.goodNum = goodNum;
    }
    
    public int getMaxNum() {
        return this.maxNum;
    }
    
    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }
    
    public int getComNum() {
        return this.comNum;
    }
    
    public void setComNum(int comNum) {
        this.comNum = comNum;
    }
    
    public String getNumData() {
        return this.numData;
    }
    
    public void setNumData(String numData) {
        this.numData = numData;
    }
    
    public BigDecimal getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(BigDecimal roleId) {
        this.roleId = roleId;
    }
}
