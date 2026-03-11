package org.come.service;

import java.math.BigDecimal;
import org.come.entity.Openareatable;
import java.util.List;

public interface OpenareatableService
{
    List<Openareatable> selectAllOpenareatable();
    
    Integer insertOpenareatable(Openareatable openAreaTable);
    
    Integer updateOpenareatable(Openareatable openAreaTable);
    
    Integer deleteOpenareatable(BigDecimal areaId);
    
    List<BigDecimal> selectTuijiNum(String areaName);
    
    List<Openareatable> selectAllArea(BigDecimal userId);
    
    String selectBelong(String areaName);
    
    String selectAtid(String qid);
    
    Openareatable selectOpenareatable(String areaName);
}
