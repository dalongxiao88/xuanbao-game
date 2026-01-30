package org.come.bean;

import org.come.entity.Salegoods;
import org.come.entity.Lingbao;
import org.come.entity.RoleSummoning;
import org.come.entity.Goodstable;

public class GoodsDetailsBean
{
    private Goodstable goodstable;
    private RoleSummoning roleSummoning;
    private Lingbao lingbao;
    private Salegoods salegoods;
    
    public Goodstable getGoodstable() {
        return this.goodstable;
    }
    
    public void setGoodstable(Goodstable goodstable) {
        this.goodstable = goodstable;
    }
    
    public Salegoods getSalegoods() {
        return this.salegoods;
    }
    
    public void setSalegoods(Salegoods salegoods) {
        this.salegoods = salegoods;
    }
    
    public RoleSummoning getRoleSummoning() {
        return this.roleSummoning;
    }
    
    public void setRoleSummoning(RoleSummoning roleSummoning) {
        this.roleSummoning = roleSummoning;
    }
    
    public Lingbao getLingbao() {
        return this.lingbao;
    }
    
    public void setLingbao(Lingbao lingbao) {
        this.lingbao = lingbao;
    }
}
