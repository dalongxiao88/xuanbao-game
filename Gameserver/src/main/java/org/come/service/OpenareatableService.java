package org.come.service;

import java.math.BigDecimal;
import org.come.entity.Openareatable;
import java.util.List;

public interface OpenareatableService
{
    List<Openareatable> selectAllOpenareatable();
    
    Integer insertOpenareatable(Openareatable p0);
    
    Integer updateOpenareatable(Openareatable p0);
    
    Integer deleteOpenareatable(BigDecimal p0);
    
    List<BigDecimal> selectTuijiNum(String p0);
    
    List<Openareatable> selectAllArea(BigDecimal p0);
    
    String selectBelong(String p0);
    
    String selectAtid(String p0);
    
    Openareatable selectOpenareatable(String p0);
}
