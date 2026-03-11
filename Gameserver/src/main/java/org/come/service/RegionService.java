package org.come.service;

import org.come.entity.RoleTableNew;
import org.come.entity.Region;
import java.util.List;
import java.math.BigDecimal;

/**
 * 分区查询服务接口。
 */
public interface RegionService
{
    List<Region> selectRegion(BigDecimal roleId, String regionName);
    
    List<RoleTableNew> selectRole(BigDecimal roleId, Integer pageNo);
    
    List<String> selectRegionAll();
}
