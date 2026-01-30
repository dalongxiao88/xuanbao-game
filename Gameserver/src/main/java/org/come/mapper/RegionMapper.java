package org.come.mapper;

import org.come.entity.RoleTableNew;
import org.come.entity.Region;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import java.math.BigDecimal;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface RegionMapper
{
    List<Region> selectRegion(@Param("quId") BigDecimal p0, @Param("raName") String p1);
    
    List<RoleTableNew> selectRole(@Param("userId") BigDecimal p0, @Param("quid") Integer p1);
    
    List<String> selectRegionAll();
}
