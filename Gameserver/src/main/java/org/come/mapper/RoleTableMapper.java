package org.come.mapper;

import java.util.Map;
import org.come.entity.UserTable;
import org.come.entity.RoleTable;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import org.come.entity.RoleAttribute;
import org.come.bean.LoginResult;
import java.math.BigDecimal;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface RoleTableMapper
{
    LoginResult selectRoleID(BigDecimal roleId);
    
    RoleAttribute selectRoleAttributeRoleId(BigDecimal roleId);
    
    void updateRoleAttributeRoleId(RoleAttribute roleAttribute);
    
    void insertRoleAttribute(RoleAttribute roleAttribute);
    
    List<LoginResult> selectOrderByType(Integer orderType);
    
    List<LoginResult> selectSLDH();
    
    RoleTable selectRoleTableByRoleName(@Param("rolename") String roleName);
    
    boolean insertIntoRoleTable(LoginResult loginResult);
    
    List<LoginResult> findGangManberByGangID(BigDecimal gangId);
    
    boolean updateRole(RoleTable roleTable);
    
    void updateRoleWhenExit(LoginResult loginResult);
    
    BigDecimal selectMoneyRoleID(@Param("role_id") BigDecimal roleId);
    
    int updateMoneyRoleID(@Param("role_id") BigDecimal roleId, @Param("gold") BigDecimal gold);
    
    int updateMoneyUserID(@Param("USER_ID") BigDecimal userId, @Param("MONEY") BigDecimal money);
    
    UserTable selectForUserId(BigDecimal userId);
    
    void updateByPrimaryKey(RoleTable roleTable);
    
    int updateRolePwdForRid(RoleTable roleTable);
    
    int updateRoleGMForRid(RoleTable roleTable);
    
    int deleteRolePwdForRid(RoleTable roleTable);
    
    RoleTable selectGang(BigDecimal roleId);
    
    int updateGang(RoleTable roleTable);
    
    int deleteSQL(RoleTable roleTable);
    
    BigDecimal selectRoleMax();
    
    List<LoginResult> selectRoleByUserid(@Param("userid") BigDecimal userId, @Param("fuserid") BigDecimal fromUserId);
    
    int updateRoleStatues(@Param("roleid") BigDecimal roleId);
    
    LoginResult selectRoleByRoleId(@Param("roleid") BigDecimal roleId);
    
    int updateRoleBelong(@Param("roleid") BigDecimal roleId, @Param("userid") BigDecimal userId);
    
    LoginResult selectRoleName(String roleName);
    
    void addQMJJ(@Param("roleid") BigDecimal roleId, @Param("add") int addValue);
    
    List<Map<String, Object>> selectConfigure();
    
    List<Map<String, Object>> selectadminUserList(Map<String, Object> queryMap);
    
    int insertUser(Map<String, Object> userMap);
    
    void deleteUser(Map<String, Object> userMap);
    
    void updateUserAmount(Map<String, Object> userMap);
    
    List<LoginResult> selectRoleByRoleNum(@Param("count") int count, @Param("notInStr") String excludedRoleIds);
    
    void addTTJJ(@Param("roleid") BigDecimal roleId, @Param("add") int addValue, @Param("state") int state);
    
    void upTTJJ(@Param("roleid") BigDecimal roleId);
    
    void updateTTJiangli(@Param("TTJIANGLI") String ttJiangli);
    
    void updateRoleExtPoint(RoleTable roleTable);
    
    void updateRoleLiangHao(RoleTable roleTable);
    
    void getLiangHao(RoleTable roleTable);
    
    void addLiangHaoExp(RoleTable roleTable);
    
    void updateRoleFullGrade(BigDecimal roleId);
    
    List<RoleTable> getRoleTaleByLiangHao(RoleTable roleTable);
    
    List<String> allLiangHao();
    
    LoginResult selectRoleIdOrLiangHao(RoleTable roleTable);
    
    void updateRoleLiangHaoType(RoleTable roleTable);
    
    void dropLiangHao(RoleTable roleTable);
    
    List<RoleTable> selectExpLh();
    void updateDifficult(@Param("role_id")BigDecimal role_id);
    void updateDAYDRAW(@Param("DAYDRAW") String DAYDRAW);
}
