package org.come.bean;

import org.come.model.Title;
import java.util.Map;

public class AllTitleBean
{
    private Map<String, Title> titleMap;
    
    public Map<String, Title> getTitleMap() {
        return this.titleMap;
    }
    
    public void setTitleMap(Map<String, Title> titleMap) {
        this.titleMap = titleMap;
    }
}
