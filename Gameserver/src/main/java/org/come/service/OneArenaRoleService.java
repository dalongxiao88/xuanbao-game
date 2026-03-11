package org.come.service;

import java.math.BigDecimal;
import java.util.List;
import come.tool.oneArena.OneArenaRole;

public interface OneArenaRoleService
{
    int insertOneArenaRole(OneArenaRole oneArenaRole);
    
    int updateDayReset();
    
    List<OneArenaRole> selectRankRoles(List<Integer> rankList);
    
    OneArenaRole selectRole(BigDecimal roleId);
    
    int updateRankRole(BigDecimal roleId, int rank, String skin, String name, int level);
    
    int selectRank(BigDecimal roleId);
    
    int selectRankPast(BigDecimal roleId);
    
    int updateDayResetRole(BigDecimal roleId);
}
