package org.come.bean;

import java.util.Map;

public class AllNpcBean
{
    private Map<String, NpcInfoBean> allNpcInfo;
    
    public Map<String, NpcInfoBean> getAllNpcInfo() {
        return this.allNpcInfo;
    }
    
    public void setAllNpcInfo(Map<String, NpcInfoBean> allNpcInfo) {
        this.allNpcInfo = allNpcInfo;
    }
}
