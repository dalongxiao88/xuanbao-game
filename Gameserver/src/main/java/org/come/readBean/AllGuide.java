package org.come.readBean;

import java.util.HashMap;
import java.util.Map;

public class AllGuide
{
    private Map<Integer, RookieGuideBean> rookieguide;
    
    public AllGuide() {
        this.rookieguide = new HashMap<>();
    }
    
    public Map<Integer, RookieGuideBean> getRookieguide() {
        return this.rookieguide;
    }
    
    public void setRookieguide(Map<Integer, RookieGuideBean> rookieguide) {
        this.rookieguide = rookieguide;
    }
}
