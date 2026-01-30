package org.come.bean;

import java.util.Map;

public class AllMapInfoBean
{
    private Map<String, MapInfoBean> allMapInfo;
    
    public Map<String, MapInfoBean> getAllMapInfo() {
        return this.allMapInfo;
    }
    
    public void setAllMapInfo(Map<String, MapInfoBean> allMapInfo) {
        this.allMapInfo = allMapInfo;
    }
}
