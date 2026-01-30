package org.come.model;

import java.math.BigDecimal;

public class DiceReidsBase
{
    private BigDecimal roleId;
    private String time;
    
    public BigDecimal getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(BigDecimal roleId) {
        this.roleId = roleId;
    }
    
    public String getTime() {
        return this.time;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
}
