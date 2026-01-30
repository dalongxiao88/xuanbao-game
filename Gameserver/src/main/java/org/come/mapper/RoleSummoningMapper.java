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
    
    List<RoleSummoning> selectRoleSummoningsByRoleID(BigDecimal p0);
    
    RoleSummoning selectRoleSummoningsByRgID(BigDecimal p0);
    
    void updateRoleSummoningRoleID(RoleSummoning p0);
    
    void updateRoleSummoning(RoleSummoning p0);
    
    void deleteRoleSummoningBySid(BigDecimal p0);
    
    void insertRoleSummoning(RoleSummoning p0);
    
    BigDecimal selectMaxID();
    
    List<RolesummoningRoleUser> selectRsRU(@Param("rru") RolesummoningRoleUser p0);
    
    Integer selectRsRUCount(@Param("rru") RolesummoningRoleUser p0);
    
    RolesummoningRoleUser selectRoleSummoningById(@Param("summoningid") String p0);
    
    void deleteRoleSummoningBySidList(List<BigDecimal> p0);
    
    void insertRoleSummoningList(List<RoleSummoning> p0);
    
    void updateRoleSummoningList(List<RoleSummoning> p0);
    
    void insertRoleSummoningSingle(RoleSummoning p0);
}
