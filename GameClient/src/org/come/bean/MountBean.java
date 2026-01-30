package org.come.bean;

import org.come.entity.Mount;
import java.math.BigDecimal;
import java.util.Map;

public class MountBean
{
    private Map<BigDecimal, Map<Integer, Mount>> allMount;
    
    public Map<BigDecimal, Map<Integer, Mount>> getAllMount() {
        return this.allMount;
    }
    
    public void setAllMount(Map<BigDecimal, Map<Integer, Mount>> allMount) {
        this.allMount = allMount;
    }
}
