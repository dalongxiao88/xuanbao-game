package org.come.bean;

import org.come.model.Dorp;
import java.util.Map;

public class DorpBean
{
    private Map<String, Dorp> dorpInfo;
    
    public Map<String, Dorp> getDorpInfo() {
        return this.dorpInfo;
    }
    
    public void setDorpInfo(Map<String, Dorp> dorpInfo) {
        this.dorpInfo = dorpInfo;
    }
}
