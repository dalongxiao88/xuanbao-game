package org.come.service;

/**
 * 经脉服务接口。
 */
public interface MeridiansService
{
    String selectMeridians(Long roleId);
    
    void saveMeridians(Long roleId, String meridians);
}
