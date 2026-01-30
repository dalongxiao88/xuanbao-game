package org.come.model;

public class HDXData
{
    private String prizeNumber;
    private int stage;
    private int total;
    private long totalMoney;
    private long totalMoney1;
    
    public HDXData() {
        this.stage = 0;
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
    
    public int getTotal() {
        return this.total;
    }
    
    public void setTotal(int total) {
        this.total = total;
    }
    
    public long getTotalMoney() {
        return this.totalMoney;
    }
    
    public void setTotalMoney(long totalMoney) {
        this.totalMoney = totalMoney;
    }
    
    public long getTotalMoney1() {
        return this.totalMoney1;
    }
    
    public void setTotalMoney1(long totalMoney1) {
        this.totalMoney1 = totalMoney1;
    }
}
