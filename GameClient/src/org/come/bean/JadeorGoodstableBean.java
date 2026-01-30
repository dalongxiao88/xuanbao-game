package org.come.bean;

import org.come.entity.PartJade;
import org.come.entity.Goodstable;

public class JadeorGoodstableBean
{
    private Goodstable goodstable;
    private PartJade partJade;
    private int type;
    
    public Goodstable getGoodstable() {
        return this.goodstable;
    }
    
    public void setGoodstable(Goodstable goodstable) {
        this.goodstable = goodstable;
    }
    
    public PartJade getPartJade() {
        return this.partJade;
    }
    
    public void setPartJade(PartJade partJade) {
        this.partJade = partJade;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
}
