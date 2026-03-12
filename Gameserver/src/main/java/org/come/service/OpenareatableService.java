package org.come.service;

import java.math.BigDecimal;
import org.come.entity.Openareatable;
import java.util.List;
/**
 * OpenareatableService 接口/数据访问定义。
 *
 * 当前文件已完成首轮反编译命名收口，后续继续按业务链路补充更细的行为注释。
 */

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

