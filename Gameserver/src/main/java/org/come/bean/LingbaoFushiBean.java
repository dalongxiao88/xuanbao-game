package org.come.bean;

import org.come.entity.Goodstable;
import java.math.BigDecimal;
import java.util.Map;

public class LingbaoFushiBean
{
    private Map<BigDecimal, Goodstable> allLingbaoFushi;
    
    public Map<BigDecimal, Goodstable> getAllLingbaoFushi() {
        return this.allLingbaoFushi;
    }
    
    public void setAllLingbaoFushi(Map<BigDecimal, Goodstable> allLingbaoFushi) {
        this.allLingbaoFushi = allLingbaoFushi;
    }
}
