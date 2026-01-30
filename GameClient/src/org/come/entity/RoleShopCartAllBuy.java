package org.come.entity;

import java.math.BigDecimal;

public class RoleShopCartAllBuy
{
    private Integer sum;
    private BigDecimal roleID;
    private BigDecimal cd;
    private BigDecimal S_cart_Id;
    private Integer type;
    
    public Integer getSum() {
        return this.sum;
    }
    
    public void setSum(Integer sum) {
        this.sum = sum;
    }
    
    public BigDecimal getRoleID() {
        return this.roleID;
    }
    
    public void setRoleID(BigDecimal roleID) {
        this.roleID = roleID;
    }
    
    public BigDecimal getCd() {
        return this.cd;
    }
    
    public void setCd(BigDecimal cd) {
        this.cd = cd;
    }
    
    public BigDecimal getS_cart_Id() {
        return this.S_cart_Id;
    }
    
    public void setS_cart_Id(BigDecimal s_cart_Id) {
        this.S_cart_Id = s_cart_Id;
    }
    
    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
}
