package org.come.mapper;

import java.util.List;
import org.come.bean.ServiceArea;
import java.math.BigDecimal;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface ServiceAreaMapper
{
    int deleteByPrimaryKey(BigDecimal p0);
    
    int insert(ServiceArea p0);
    
    int insertSelective(ServiceArea p0);
    
    ServiceArea selectByPrimaryKey(BigDecimal p0);
    
    int updateByPrimaryKeySelective(ServiceArea p0);
    
    int updateByPrimaryKey(ServiceArea p0);
    
    List<BigDecimal> selectServiceAreaid(ServiceArea p0);
    
    List<ServiceArea> selectAllService();
    
    List<ServiceArea> selectListAreaForUid(BigDecimal p0);
    
    List<ServiceArea> selectServiceForPage(int p0);
}
