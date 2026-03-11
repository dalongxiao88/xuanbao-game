package org.come.service;

import java.util.List;
import org.come.model.AutoActiveReidsBase;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自动任务限制服务接口。
 */
public interface IAutoTaskService
{
    ConcurrentHashMap<String, AutoActiveReidsBase> getAllList(String roleId);
    
    List<AutoActiveReidsBase> getAllComList(String roleId);
    
    AutoActiveReidsBase selectByID(String roleId, String id);
    
    void deleteByID(String roleId, String id);
    
    void addReidsLimit(AutoActiveReidsBase autotaskLimit);
    
    void updateReidsLimit(AutoActiveReidsBase autotaskLimit);
}
