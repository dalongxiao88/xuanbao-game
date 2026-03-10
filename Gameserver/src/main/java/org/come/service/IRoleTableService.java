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
    
    LoginResult selectRoleByRoleId(BigDecimal roleId);
    
    UserTable selectForUserId(BigDecimal userId);
    
    int updateRoleBelong(BigDecimal roleId, BigDecimal userId);
    
    LoginResult selectRoleName(String roleName);
    
    void addQMJJ(@Param("roleid") BigDecimal roleId, @Param("add") int addValue);
    
    void addTTJJ(@Param("roleid") BigDecimal roleId, @Param("state") int state, @Param("add") int addValue);
    
    void upTTJJ(@Param("roleid") BigDecimal roleId);
    
    void updateTTJiangli(String ttJiangli);
    
    List<LoginResult> selectRoleByRoleNum(@Param("count") int count, @Param("notInStr") String excludedRoleIds);
    
    void deleteTableSQL(RoleTable roleTable);
    
    List<Map<String, Object>> selectConfigure();
    
    List<Map<String, Object>> selectadminUserList(Map<String, Object> queryMap);
    
    int insertUser(Map<String, Object> userMap);
    
    boolean deleteUser(Map<String, Object> userMap);
    
    void updateUserAmount(Map<String, Object> userMap);
    
    RoleAttribute selectRoleAttributeRoleId(BigDecimal roleId);
    
    void updateRoleAttributeRoleId(RoleAttribute roleAttribute);
    
    void insertRoleAttribute(RoleAttribute roleAttribute);
    
    void updateRoleExtPoint(RoleTable roleTable);
    
    void updateRoleLiangHao(RoleTable roleTable);
    
    void getLiangHao(RoleTable roleTable);
    
    void addLiangHaoExp(RoleTable roleTable);
    
    void updateRoleLiangHaoType(RoleTable roleTable);
    
    void dropLiangHao(RoleTable roleTable);
    
    List<RoleTable> getRoleTaleByLiangHao(RoleTable roleTable);
    
    List<String> allLiangHao();
    
    LoginResult selectRoleIdOrLiangHao(RoleTable roleTable);
    
    List<RoleTable> selectExpLh();
    void updateDifficult(@Param("role_id")BigDecimal role_id);
    void updateDAYDRAW(@Param("DAYDRAW")String DAYDRAW);
}
