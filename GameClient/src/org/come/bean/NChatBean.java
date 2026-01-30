package org.come.bean;

import java.math.BigDecimal;

public class NChatBean
{
    private int id;
    private BigDecimal roleId;
    private String role;
    private String skin;
    private String message;
    private String goodNum;
    private Integer goodNumType;
    
    public String getGoodNum() {
        return this.goodNum;
    }
    
    public void setGoodNum(String goodNum) {
        this.goodNum = goodNum;
    }
    
    public Integer getGoodNumType() {
        return this.goodNumType;
    }
    
    public void setGoodNumType(Integer goodNumType) {
        this.goodNumType = goodNumType;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public BigDecimal getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(BigDecimal roleId) {
        this.roleId = roleId;
    }
    
    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getSkin() {
        return this.skin;
    }
    
    public void setSkin(String skin) {
        this.skin = skin;
    }
}
