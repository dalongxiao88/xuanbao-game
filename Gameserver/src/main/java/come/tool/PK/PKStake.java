package come.tool.PK;

public class PKStake
{
    private long charge;
    private long money;
    private long xianYu;
    private long exp;
    
    public PKStake(long charge, long money, long xianYu) {
        this.charge = charge;
        this.money = money;
        this.xianYu = xianYu;
    }
    
    public PKStake(long charge, long money, long xianYu, long exp) {
        this.charge = charge;
        this.money = money;
        this.xianYu = xianYu;
        this.exp = exp;
    }
    
    public void qk() {
        this.charge = 0L;
        this.money = 0L;
        this.xianYu = 0L;
        this.exp = 0L;
    }
    
    public long getCharge() {
        return this.charge;
    }
    
    public void setCharge(long charge) {
        this.charge = charge;
    }
    
    public long getMoney() {
        return this.money;
    }
    
    public void setMoney(long money) {
        this.money = money;
    }
    
    public long getXianYu() {
        return this.xianYu;
    }
    
    public void setXianYu(long xianYu) {
        this.xianYu = xianYu;
    }
    
    public long getExp() {
        return this.exp;
    }
    
    public void setExp(long exp) {
        this.exp = exp;
    }
}
