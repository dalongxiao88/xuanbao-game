package org.come.bean;

import java.math.BigDecimal;

public class QuackGameBean
{
    private int type;
    private String petmsg;
    private BigDecimal money;
    private String petmsg2;
    private String endTime;//补充字段
    private String g1;//补充字段
    private String g2;//补充字段
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public String getPetmsg() {
        return this.petmsg;
    }
    
    public void setPetmsg(String petmsg) {
        this.petmsg = petmsg;
    }
    
    public BigDecimal getMoney() {
        return this.money;
    }
    
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    
    public String getPetmsg2() {
        return this.petmsg2;
    }
    
    public void setPetmsg2(String petmsg2) {
        this.petmsg2 = petmsg2;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getG1() {
        return g1;
    }

    public void setG1(String g1) {
        this.g1 = g1;
    }

    public String getG2() {
        return g2;
    }

    public void setG2(String g2) {
        this.g2 = g2;
    }
}
