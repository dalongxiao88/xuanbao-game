package org.come.bean;

import org.come.model.Linbaoskill;
import java.util.Map;

public class LingbaoSKillBean
{
    private Map<String, Linbaoskill> allLingbaoskill;
    
    public Map<String, Linbaoskill> getAllLingbaoskill() {
        return this.allLingbaoskill;
    }
    
    public void setAllLingbaoskill(Map<String, Linbaoskill> allLingbaoskill) {
        this.allLingbaoskill = allLingbaoskill;
    }
}
