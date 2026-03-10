package org.come.service;

import org.come.entity.RolesummoningRoleUser;
import org.come.entity.Goodstable;
import java.math.BigDecimal;
import org.come.entity.RoleSummoning;
import java.util.List;

public interface IRoleSummoningService
{
    List<RoleSummoning> selectAllRoleSummonings();
    
    List<RoleSummoning> selectRoleSummoningsByRoleID(BigDecimal roleId);
    
    RoleSummoning selectRoleSummoningsByRgID(BigDecimal summoningId);
    
    void updateRoleSummoningIndex(RoleSummoning roleSummoning, BigDecimal roleId);
    
    void updateRoleSummoning(RoleSummoning roleSummoning);
    
    void updatePetRedis(RoleSummoning roleSummoning);
    
    void deleteRoleSummoningBySid(BigDecimal summoningId);
    
    void insertRoleSummoning(RoleSummoning roleSummoning);
    
    void insertitem(Goodstable goodstable);
    
    BigDecimal selectMaxID();
    
    void updateRoleSummoningsql(RoleSummoning roleSummoning);
    
    void deleteRoleSummoningBySidsql(BigDecimal summoningId);
    
    void insertRoleSummoningsql(RoleSummoning roleSummoning);
    
    List<RolesummoningRoleUser> selectRsRU(RolesummoningRoleUser roleSummoningRoleUser);
    
    Integer selectRsRUCount(RolesummoningRoleUser roleSummoningRoleUser);
    
    RolesummoningRoleUser selectRoleSummoningById(String summoningId);
    
    void deleteRoleSummoningBySidList(List<BigDecimal> summoningIds);
    
    void insertRoleSummoningList(List<RoleSummoning> roleSummoningList);
    
    void updateRoleSummoningList(List<RoleSummoning> roleSummoningList);
    
    void insertRoleSummoningSingle(RoleSummoning roleSummoning);
}
