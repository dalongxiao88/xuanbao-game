package come.tool.Stall;

import org.come.entity.Lingbao;
import org.come.entity.RoleSummoning;
import org.come.entity.Goodstable;

public class Commodity
{
    private Goodstable good;
    private RoleSummoning pet;
    private Lingbao lingbao;
    private String currency;
    private long money;
    
    public Goodstable getGood() {
        return this.good;
    }
    
    public void setGood(Goodstable good) {
        this.good = good;
    }
    
    public RoleSummoning getPet() {
        return this.pet;
    }
    
    public void setPet(RoleSummoning pet) {
        this.pet = pet;
    }
    
    public Lingbao getLingbao() {
        return this.lingbao;
    }
    
    public void setLingbao(Lingbao lingbao) {
        this.lingbao = lingbao;
    }
    
    public String getCurrency() {
        return this.currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public long getMoney() {
        return this.money;
    }
    
    public void setMoney(long money) {
        this.money = money;
    }
}
