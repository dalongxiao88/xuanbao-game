package org.come.service;

import org.come.entity.RoleTableNew;
import org.come.entity.Region;
import java.util.List;
import java.math.BigDecimal;

public interface RegionService
{
    List<Region> selectRegion(BigDecimal p0, String p1);
    
    List<RoleTableNew> selectRole(BigDecimal p0, Integer p1);
    
    List<String> selectRegionAll();
}
