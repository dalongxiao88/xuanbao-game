package org.come.service;

import java.math.BigDecimal;
import java.util.List;
import come.tool.oneArena.OneArenaRole;

public interface OneArenaRoleService
{
    int insertOneArenaRole(OneArenaRole p0);
    
    int updateDayReset();
    
    List<OneArenaRole> selectRankRoles(List<Integer> p0);
    
    OneArenaRole selectRole(BigDecimal p0);
    
    int updateRankRole(BigDecimal p0, int p1, String p2, String p3, int p4);
    
    int selectRank(BigDecimal p0);
    
    int selectRankPast(BigDecimal p0);
    
    int updateDayResetRole(BigDecimal p0);
}
