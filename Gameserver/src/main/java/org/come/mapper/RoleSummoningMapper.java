package org.come.mapper;

import org.apache.ibatis.annotations.Param;
import org.come.entity.RolesummoningRoleUser;
import java.math.BigDecimal;
import org.come.entity.RoleSummoning;
import java.util.List;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface RoleSummoningMapper
{
    List<RoleSummoning> selectAllRoleSummonings();
    
    List<RoleSummoning> selectRoleSummoningsByRoleID(BigDecimal roleId);
    
    RoleSummoning selectRoleSummoningsByRgID(BigDecimal summoningId);
    
    void updateRoleSummoningRoleID(RoleSummoning roleSummoning);
    
    void updateRoleSummoning(RoleSummoning roleSummoning);
    
    void deleteRoleSummoningBySid(BigDecimal summoningId);
    
    void insertRoleSummoning(RoleSummoning roleSummoning);
    
    BigDecimal selectMaxID();
    
    List<RolesummoningRoleUser> selectRsRU(@Param("rru") RolesummoningRoleUser roleSummoningRoleUser);
    
    Integer selectRsRUCount(@Param("rru") RolesummoningRoleUser roleSummoningRoleUser);
    
    RolesummoningRoleUser selectRoleSummoningById(@Param("summoningid") String summoningId);
    
    void deleteRoleSummoningBySidList(List<BigDecimal> summoningIds);
    
    void insertRoleSummoningList(List<RoleSummoning> roleSummoningList);
    
    void updateRoleSummoningList(List<RoleSummoning> roleSummoningList);
    
    void insertRoleSummoningSingle(RoleSummoning roleSummoning);
}
