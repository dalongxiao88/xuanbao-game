package org.come.service;

import org.come.entity.RolesummoningRoleUser;
import org.come.entity.Goodstable;
import java.math.BigDecimal;
import org.come.entity.RoleSummoning;
import java.util.List;

public interface IRoleSummoningService
{
    List<RoleSummoning> selectAllRoleSummonings();
    
    List<RoleSummoning> selectRoleSummoningsByRoleID(BigDecimal p0);
    
    RoleSummoning selectRoleSummoningsByRgID(BigDecimal p0);
    
    void updateRoleSummoningIndex(RoleSummoning p0, BigDecimal p1);
    
    void updateRoleSummoning(RoleSummoning p0);
    
    void updatePetRedis(RoleSummoning p0);
    
    void deleteRoleSummoningBySid(BigDecimal p0);
    
    void insertRoleSummoning(RoleSummoning p0);
    
    void insertitem(Goodstable p0);
    
    BigDecimal selectMaxID();
    
    void updateRoleSummoningsql(RoleSummoning p0);
    
    void deleteRoleSummoningBySidsql(BigDecimal p0);
    
    void insertRoleSummoningsql(RoleSummoning p0);
    
    List<RolesummoningRoleUser> selectRsRU(RolesummoningRoleUser p0);
    
    Integer selectRsRUCount(RolesummoningRoleUser p0);
    
    RolesummoningRoleUser selectRoleSummoningById(String p0);
    
    void deleteRoleSummoningBySidList(List<BigDecimal> p0);
    
    void insertRoleSummoningList(List<RoleSummoning> p0);
    
    void updateRoleSummoningList(List<RoleSummoning> p0);
    
    void insertRoleSummoningSingle(RoleSummoning p0);
}
