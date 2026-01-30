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
    LoginResult selectRoleID(BigDecimal p0);
    
    List<LoginResult> selectOrderByType(Integer p0);
    
    List<LoginResult> selectSLDH();
    
    RoleTable selectRoleTableByRoleName(String p0);
    
    boolean insertIntoRoleTable(LoginResult p0);
    
    List<LoginResult> findGangManberByGangID(BigDecimal p0);
    
    boolean updateRole(RoleTable p0);
    
    void updateRoleWhenExit(LoginResult p0);
    
    BigDecimal selectMoneyRoleID(@Param("role_id") BigDecimal p0);
    
    int updateMoneyRoleID(@Param("role_id") BigDecimal p0, @Param("gold") BigDecimal p1);
    
    int updateMoneyUserID(@Param("USER_ID") BigDecimal p0, @Param("MONEY") BigDecimal p1);
    
    void updateByPrimaryKey(RoleTable p0);
    
    int updateRolePwdForRid(RoleTable p0);
    
    int updateRoleGMForRid(RoleTable p0);
    
    int deleteRolePwdForRid(RoleTable p0);
    
    RoleTable selectGang(BigDecimal p0);
    
    int updateGang(RoleTable p0);
    
    BigDecimal selectRoleMax();
    
    List<LoginResult> selectRoleByUserid(BigDecimal p0, BigDecimal p1);
    
    int updateRoleStatues(BigDecimal p0);
    
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
