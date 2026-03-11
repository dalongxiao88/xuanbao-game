package org.come.mapper;

import java.util.List;
import org.come.bean.ServiceArea;
import java.math.BigDecimal;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface ServiceAreaMapper
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
