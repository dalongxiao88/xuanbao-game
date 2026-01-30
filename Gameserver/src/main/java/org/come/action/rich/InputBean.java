package org.come.action.rich;

import java.math.BigDecimal;

public class InputBean
{
    private int type;
    private BigDecimal id;
    private String name;
    private String color;
    private String goodNum;
    private String goodNumType;
    
    public String getGoodNum() {
        return this.goodNum;
    }
    
    public void setGoodNum(String goodNum) {
        this.goodNum = goodNum;
    }
    
    public InputBean() {
    }
    
    public InputBean(int type, BigDecimal id, String name, String color) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.color = color;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
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
    
    public String getColor() {
        return this.color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public String getGoodNumType() {
        return this.goodNumType;
    }
    
    public void setGoodNumType(String goodNumType) {
        this.goodNumType = goodNumType;
    }
}
