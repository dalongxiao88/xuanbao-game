package org.come.bean;

import org.come.entity.SellLianghaoAuc;
import java.util.List;

public class MyLiangHaoAucBean
{
    private List<SellLianghaoAuc> sellLianghaoAucs;
    
    public List<SellLianghaoAuc> getSellLianghaoAucs() {
        return this.sellLianghaoAucs;
    }
    
    public void setSellLianghaoAucs(List<SellLianghaoAuc> sellLianghaoAucs) {
        this.sellLianghaoAucs = sellLianghaoAucs;
    }
}
