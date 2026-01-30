package org.come.bean;

import org.come.entity.Gang;
import java.util.List;

public class GangListBean
{
    private List<Gang> gangs;
    
    public List<Gang> getGangs() {
        return this.gangs;
    }
    
    public void setGangs(List<Gang> gangs) {
        this.gangs = gangs;
    }
}
