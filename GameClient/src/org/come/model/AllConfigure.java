package org.come.model;

import java.util.concurrent.ConcurrentHashMap;

public class AllConfigure
{
    private ConcurrentHashMap<Integer, Configure> allConfigure;
    
    public ConcurrentHashMap<Integer, Configure> getAllConfigure() {
        return this.allConfigure;
    }
    
    public void setAllConfigure(ConcurrentHashMap<Integer, Configure> allConfigure) {
        this.allConfigure = allConfigure;
    }
}
