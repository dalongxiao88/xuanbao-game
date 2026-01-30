package org.come.bean;

import java.math.BigDecimal;

public class Middle
{
    private String rolename;
    private String taskComplete;
    private String taskDaily;
    private BigDecimal Dayandpayorno;
    private BigDecimal Daypaysum;
    private BigDecimal Daygetorno;
    private String Vipget;
    private int Dayfirstinorno;
    
    public String getRolename() {
        return this.rolename;
    }
    
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    
    public String getTaskComplete() {
        return this.taskComplete;
    }
    
    public void setTaskComplete(String taskComplete) {
        this.taskComplete = taskComplete;
    }
    
    public String getTaskDaily() {
        return this.taskDaily;
    }
    
    public void setTaskDaily(String taskDaily) {
        this.taskDaily = taskDaily;
    }
    
    public BigDecimal getDayandpayorno() {
        return this.Dayandpayorno;
    }
    
    public void setDayandpayorno(BigDecimal dayandpayorno) {
        this.Dayandpayorno = dayandpayorno;
    }
    
    public BigDecimal getDaypaysum() {
        return this.Daypaysum;
    }
    
    public void setDaypaysum(BigDecimal daypaysum) {
        this.Daypaysum = daypaysum;
    }
    
    public BigDecimal getDaygetorno() {
        return this.Daygetorno;
    }
    
    public void setDaygetorno(BigDecimal daygetorno) {
        this.Daygetorno = daygetorno;
    }
    
    public String getVipget() {
        return this.Vipget;
    }
    
    public void setVipget(String vipget) {
        this.Vipget = vipget;
    }
    
    public int getDayfirstinorno() {
        return this.Dayfirstinorno;
    }
    
    public void setDayfirstinorno(int dayfirstinorno) {
        this.Dayfirstinorno = dayfirstinorno;
    }
}
