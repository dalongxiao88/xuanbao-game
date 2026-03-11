package org.come.service;

import java.util.List;
import org.come.bean.ServiceArea;
import java.math.BigDecimal;

public interface ServiceAreaService
{
    int deleteByPrimaryKey(BigDecimal serviceAreaId);
    
    int insert(ServiceArea serviceArea);
    
    int insertSelective(ServiceArea serviceArea);
    
    ServiceArea selectByPrimaryKey(BigDecimal serviceAreaId);
    
    int updateByPrimaryKeySelective(ServiceArea serviceArea);
    
    int updateByPrimaryKey(ServiceArea serviceArea);
    
    List<BigDecimal> selectServiceAreaid(ServiceArea serviceArea);
    
    List<ServiceArea> selectAllService();
    
    List<ServiceArea> selectListAreaForUid(BigDecimal userId);
    
    List<ServiceArea> selectServiceForPage(int pageNum);
}
