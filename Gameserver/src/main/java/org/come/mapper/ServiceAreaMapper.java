package org.come.mapper;

import java.util.List;
import org.come.bean.ServiceArea;
import java.math.BigDecimal;
import org.come.annotation.MyBatisAnnotation;
/**
 * ServiceAreaMapper 接口/数据访问定义。
 *
 * 当前文件已完成首轮反编译命名收口，后续继续按业务链路补充更细的行为注释。
 */

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

