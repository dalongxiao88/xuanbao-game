package org.come.model;

import java.math.BigDecimal;

public class HDXRoleRecord
{
    private BigDecimal role_id;
    private String prizeNumber;
    private int stage;
    private int money;
    private String ifWin;
    
    public BigDecimal getRole_id() {
        return this.role_id;
    }
    
    public void setRole_id(BigDecimal role_id) {
        this.role_id = role_id;
    }
    
    public String getPrizeNumber() {
        return this.prizeNumber;
    }
    
    public void setPrizeNumber(String prizeNumber) {
        this.prizeNumber = prizeNumber;
    }
    
    public int getStage() {
        return this.stage;
    }
    
    public void setStage(int stage) {
        this.stage = stage;
    }
    
    public int getMoney() {
        return this.money;
    }
    
    public void setMoney(int money) {
        this.money = money;
    }
    
    public String getIfWin() {
        return this.ifWin;
    }
    
    public void setIfWin(String ifWin) {
        this.ifWin = ifWin;
    }
}
