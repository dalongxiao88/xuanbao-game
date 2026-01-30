package org.come.model;

public class LotteryData
{
    private String prizeNumber;
    private int stage;
    private int total;
    private int totalMoney;
    
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
    
    public int getTotalMoney() {
        return this.totalMoney;
    }
    
    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }
}
