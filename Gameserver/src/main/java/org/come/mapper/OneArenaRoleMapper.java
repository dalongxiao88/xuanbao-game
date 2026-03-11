package org.come.mapper;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import come.tool.oneArena.OneArenaRole;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface OneArenaRoleMapper
{
    int insertOneArenaRole(@Param("role") OneArenaRole oneArenaRole);
    
    int updateDayReset();
    
    List<OneArenaRole> selectRankRoles(@Param("list") List<Integer> rankList);
    
    OneArenaRole selectRole(@Param("roleID") BigDecimal roleId);
    
    int updateRankRole(@Param("roleID") BigDecimal roleId, @Param("rank") int rank, @Param("skin") String skin, @Param("name") String name, @Param("lvl") int level);
    
    Integer selectRank(@Param("roleID") BigDecimal roleId);
    
    Integer selectRankPast(@Param("roleID") BigDecimal roleId);
    
    int updateDayResetRole(@Param("roleID") BigDecimal roleId);
}
