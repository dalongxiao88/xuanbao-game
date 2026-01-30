package org.come.mapper;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import come.tool.oneArena.OneArenaRole;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface OneArenaRoleMapper
{
    int insertOneArenaRole(@Param("role") OneArenaRole p0);
    
    int updateDayReset();
    
    List<OneArenaRole> selectRankRoles(@Param("list") List<Integer> p0);
    
    OneArenaRole selectRole(@Param("roleID") BigDecimal p0);
    
    int updateRankRole(@Param("roleID") BigDecimal p0, @Param("rank") int p1, @Param("skin") String p2, @Param("name") String p3, @Param("lvl") int p4);
    
    Integer selectRank(@Param("roleID") BigDecimal p0);
    
    Integer selectRankPast(@Param("roleID") BigDecimal p0);
    
    int updateDayResetRole(@Param("roleID") BigDecimal p0);
}
