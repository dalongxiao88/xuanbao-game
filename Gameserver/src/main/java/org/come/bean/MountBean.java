package org.come.bean;

import org.come.entity.Mount;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

public class MountBean
{
    private ConcurrentHashMap<BigDecimal, ConcurrentHashMap<Integer, Mount>> allMount;
    
    public ConcurrentHashMap<BigDecimal, ConcurrentHashMap<Integer, Mount>> getAllMount() {
        return this.allMount;
    }
    
    public void setAllMount(ConcurrentHashMap<BigDecimal, ConcurrentHashMap<Integer, Mount>> allMount) {
        this.allMount = allMount;
    }
}
