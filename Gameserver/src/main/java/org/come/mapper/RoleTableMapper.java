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
    
//    void deleteTableSQL1(RoleTable p0);
//
//    void deleteTableSQL2(RoleTable p0);
//
//    void deleteTableSQL3(RoleTable p0);
//
//    void deleteTableSQL4(RoleTable p0);
//
//    void deleteTableSQL5(RoleTable p0);
//
//    void deleteTableSQL6(RoleTable p0);
//
//    void deleteTableSQL7(RoleTable p0);
//
//    void deleteTableSQL8(RoleTable p0);
//
//    void deleteTableSQL9(RoleTable p0);
//
//    void deleteTableSQL10(RoleTable p0);
//
//    void deleteTableSQL11(RoleTable p0);
//
//    void deleteTableSQL12(RoleTable p0);
//
//    void deleteTableSQL13(RoleTable p0);
//
//    void deleteTableSQL14(RoleTable p0);
//
//    void deleteTableSQL15(RoleTable p0);
//
//    void deleteTableSQL16(RoleTable p0);
//
//    void deleteTableSQL17(RoleTable p0);
//
//    void deleteTableSQL18(RoleTable p0);
//
//    void deleteTableSQL19(RoleTable p0);
//
//    void deleteTableSQL20(RoleTable p0);
//
//    void deleteTableSQL21(RoleTable p0);
//
//    void deleteTableSQL22(RoleTable p0);
//
//    void deleteTableSQL23(RoleTable p0);
//
//    void deleteTableSQL24(RoleTable p0);
//
//    void deleteTableSQL25(RoleTable p0);
//
//    void deleteTableSQL26(RoleTable p0);
//
//    void deleteTableSQL27(RoleTable p0);
//
//    void deleteTableSQL28(RoleTable p0);
//
//    void deleteTableSQL29(RoleTable p0);
//
//    void deleteTableSQL30(RoleTable p0);
//
//    void deleteTableSQL31(RoleTable p0);
//
//    void deleteTableSQL32(RoleTable p0);
//
//    void deleteTableSQL33(RoleTable p0);
//
//    void deleteTableSQL34(RoleTable p0);
//
//    void deleteTableSQL35(RoleTable p0);
//
//    void deleteTableSQL36(RoleTable p0);
//
//    void deleteTableSQL37(RoleTable p0);
//
//    void deleteTableSQL38(RoleTable p0);
//
//    void deleteTableSQL39(RoleTable p0);
//
//    void deleteTableSQL40(RoleTable p0);
//
//    void deleteTableSQL41(RoleTable p0);
//
//    void deleteTableSQL42(RoleTable p0);
//
//    void deleteTableSQL43(RoleTable p0);
//
//    void deleteTableSQL44(RoleTable p0);
//
//    void deleteTableSQL45(RoleTable p0);
//
//    void deleteTableSQL46(RoleTable p0);
    
    void addTTJJ(@Param("roleid") BigDecimal p0, @Param("add") int p1, @Param("state") int p2);
    
    void upTTJJ(@Param("roleid") BigDecimal p0);
    
    void updateTTJiangli(@Param("TTJIANGLI") String p0);
    
    void updateRoleExtPoint(RoleTable p0);
    
    void updateRoleLiangHao(RoleTable p0);
    
    void getLiangHao(RoleTable p0);
    
    void addLiangHaoExp(RoleTable p0);
    
    void updateRoleFullGrade(BigDecimal p0);
    
    List<RoleTable> getRoleTaleByLiangHao(RoleTable p0);
    
    List<String> allLiangHao();
    
    LoginResult selectRoleIdOrLiangHao(RoleTable p0);
    
    void updateRoleLiangHaoType(RoleTable p0);
    
    void dropLiangHao(RoleTable p0);
    
    List<RoleTable> selectExpLh();
    void updateDifficult(@Param("role_id")BigDecimal role_id);
    void updateDAYDRAW(@Param("DAYDRAW") String DAYDRAW);
}
