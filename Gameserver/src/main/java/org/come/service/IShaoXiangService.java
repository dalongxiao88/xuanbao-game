package org.come.service;

import org.come.model.ShaoXiangLimit;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 烧香限制服务接口。
 */
public interface IShaoXiangService
{
    ConcurrentHashMap<String, ShaoXiangLimit> getAllList(String roleId);
    
    ShaoXiangLimit selectByID(String roleId, String id);
    
    void deleteByID(String roleId, String id);
    
    void addReidsLimit(ShaoXiangLimit shaoXiangLimit);
    
    void updateReidsLimit(ShaoXiangLimit shaoXiangLimit);
}
