package org.come.bean;

import java.math.BigDecimal;

public class DiceReidsBase
{
    private BigDecimal roleId;
    private static String time;
    
    public BigDecimal getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(BigDecimal roleId) {
        this.roleId = roleId;
    }
    
    public static String getTime() {
        return DiceReidsBase.time;
    }
    
    public static void setTime(String time) {
        DiceReidsBase.time = time;
    }
}
