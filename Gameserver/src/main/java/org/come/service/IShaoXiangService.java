package org.come.service;

import org.come.model.ShaoXiangLimit;
import java.util.concurrent.ConcurrentHashMap;

public interface IShaoXiangService
{
    ConcurrentHashMap<String, ShaoXiangLimit> getAllList(String p0);
    
    ShaoXiangLimit selectByID(String p0, String p1);
    
    void deleteByID(String p0, String p1);
    
    void addReidsLimit(ShaoXiangLimit p0);
    
    void updateReidsLimit(ShaoXiangLimit p0);
}
