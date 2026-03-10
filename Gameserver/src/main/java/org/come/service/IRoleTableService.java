package org.come.service;

import org.come.entity.RoleAttribute;
import java.util.Map;
import org.come.entity.UserTable;
import org.apache.ibatis.annotations.Param;
import org.come.entity.RoleTable;
import java.util.List;
import org.come.bean.LoginResult;
import java.math.BigDecimal;

public interface IRoleTableService
{
    LoginResult selectRoleID(BigDecimal roleId);
    
    List<LoginResult> selectOrderByType(Integer orderType);
    
    List<LoginResult> selectSLDH();
    
    RoleTable selectRoleTableByRoleName(String roleName);
    
    boolean insertIntoRoleTable(LoginResult loginResult);
    
    List<LoginResult> findGangManberByGangID(BigDecimal gangId);
    
    boolean updateRole(RoleTable roleTable);
    
    void updateRoleWhenExit(LoginResult loginResult);
    
    BigDecimal selectMoneyRoleID(@Param("role_id") BigDecimal roleId);
    
    int updateMoneyRoleID(@Param("role_id") BigDecimal roleId, @Param("gold") BigDecimal gold);
    
    int updateMoneyUserID(@Param("USER_ID") BigDecimal userId, @Param("MONEY") BigDecimal money);
    
    void updateByPrimaryKey(RoleTable roleTable);
    
    int updateRolePwdForRid(RoleTable roleTable);
    
    int updateRoleGMForRid(RoleTable roleTable);
    
    int deleteRolePwdForRid(RoleTable roleTable);
    
    RoleTable selectGang(BigDecimal roleId);
    
    int updateGang(RoleTable roleTable);
    
    BigDecimal selectRoleMax();
    
    List<LoginResult> selectRoleByUserid(BigDecimal userId, BigDecimal fromUserId);
    
    int updateRoleStatues(BigDecimal roleId);
    
    LoginResult selectRoleByRoleId(BigDecimal p0);
    
    UserTable selectForUserId(BigDecimal p0);
    
    int updateRoleBelong(BigDecimal p0, BigDecimal p1);
    
    LoginResult selectRoleName(String p0);
    
    void addQMJJ(@Param("roleid") BigDecimal p0, @Param("add") int p1);
    
    void addTTJJ(@Param("roleid") BigDecimal p0, @Param("state") int p1, @Param("add") int p2);
    
    void upTTJJ(@Param("roleid") BigDecimal p0);
    
    void updateTTJiangli(String p0);
    
    List<LoginResult> selectRoleByRoleNum(@Param("notInStr") int p0, @Param("notInStr") String p1);
    
    void deleteTableSQL(RoleTable p0);
    
    List<Map<String, Object>> selectConfigure();
    
    List<Map<String, Object>> selectadminUserList(Map<String, Object> p0);
    
    int insertUser(Map<String, Object> p0);
    
    boolean deleteUser(Map<String, Object> p0);
    
    void updateUserAmount(Map<String, Object> p0);
    
    RoleAttribute selectRoleAttributeRoleId(BigDecimal p0);
    
    void updateRoleAttributeRoleId(RoleAttribute p0);
    
    void insertRoleAttribute(RoleAttribute p0);
    
    void updateRoleExtPoint(RoleTable p0);
    
    void updateRoleLiangHao(RoleTable p0);
    
    void getLiangHao(RoleTable p0);
    
    void addLiangHaoExp(RoleTable p0);
    
    void updateRoleLiangHaoType(RoleTable p0);
    
    void dropLiangHao(RoleTable p0);
    
    List<RoleTable> getRoleTaleByLiangHao(RoleTable p0);
    
    List<String> allLiangHao();
    
    LoginResult selectRoleIdOrLiangHao(RoleTable p0);
    
    List<RoleTable> selectExpLh();
    void updateDifficult(@Param("role_id")BigDecimal role_id);
    void updateDAYDRAW(@Param("DAYDRAW")String DAYDRAW);
}
