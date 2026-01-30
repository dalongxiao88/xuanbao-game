package org.come.entity;

public class LotteryGood extends Goodstable implements Cloneable
{
    private int lotterytype;
    private int lotterylevel;
    private int lotterymultiple;
    private int lotterystep;
    private int lotterrate;
    private String lotterybg;
    
    public int getLotterrate() {
        return this.lotterrate;
    }
    
    public void setLotterrate(int lotterrate) {
        this.lotterrate = lotterrate;
    }
    
    public int getLotterystep() {
        return this.lotterystep;
    }
    
    public void setLotterystep(int lotterystep) {
        this.lotterystep = lotterystep;
    }
    
    public String getLotterybg() {
        return this.lotterybg;
    }
    
    public void setLotterybg(String lotterybg) {
        this.lotterybg = lotterybg;
    }
    
    public int getLotterymultiple() {
        return this.lotterymultiple;
    }
    
    public void setLotterymultiple(int lotterymultiple) {
        this.lotterymultiple = lotterymultiple;
    }
    
    public int getLotterytype() {
        return this.lotterytype;
    }
    
    public void setLotterytype(int lotterytype) {
        this.lotterytype = lotterytype;
    }
    
    public int getLotterylevel() {
        return this.lotterylevel;
    }
    
    public void setLotterylevel(int lotterylevel) {
        this.lotterylevel = lotterylevel;
    }
}
