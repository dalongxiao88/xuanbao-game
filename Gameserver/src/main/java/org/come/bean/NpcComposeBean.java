package org.come.bean;

import java.math.BigDecimal;
import java.util.List;

public class NpcComposeBean
{
    private String composetype;
    private List<BigDecimal> goodstables;
    
    public String getComposetype() {
        return this.composetype;
    }
    
    public void setComposetype(String composetype) {
        this.composetype = composetype;
    }
    
    public List<BigDecimal> getGoodstables() {
        return this.goodstables;
    }
    
    public void setGoodstables(List<BigDecimal> goodstables) {
        this.goodstables = goodstables;
    }
}
