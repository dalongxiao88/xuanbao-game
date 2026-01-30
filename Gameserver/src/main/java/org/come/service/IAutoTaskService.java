package org.come.service;

import java.util.List;
import org.come.model.AutoActiveReidsBase;
import java.util.concurrent.ConcurrentHashMap;

public interface IAutoTaskService
{
    ConcurrentHashMap<String, AutoActiveReidsBase> getAllList(String p0);
    
    List<AutoActiveReidsBase> getAllComList(String p0);
    
    AutoActiveReidsBase selectByID(String p0, String p1);
    
    void deleteByID(String p0, String p1);
    
    void addReidsLimit(AutoActiveReidsBase p0);
    
    void updateReidsLimit(AutoActiveReidsBase p0);
}
