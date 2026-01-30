package org.come.bean;

import java.math.BigDecimal;
import org.come.model.Lingbao;
import org.come.entity.RoleSummoning;
import org.come.entity.Goodstable;

public class GoodTrans2
{
    private boolean i;
    private Goodstable goods;
    private RoleSummoning pet;
    private Lingbao lingbao;
    private BigDecimal money;
    
    public boolean isI() {
        return this.i;
    }
    
    public void setI(boolean i) {
        this.i = i;
    }
    
    public Goodstable getGoods() {
        return this.goods;
    }
    
    public void setGoods(Goodstable goods) {
        this.goods = goods;
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
    
    public BigDecimal getMoney() {
        return this.money;
    }
    
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
