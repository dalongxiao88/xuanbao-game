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
    LoginResult selectRoleID(BigDecimal p0);
    
    RoleAttribute selectRoleAttributeRoleId(BigDecimal p0);
    
    void updateRoleAttributeRoleId(RoleAttribute p0);
    
    void insertRoleAttribute(RoleAttribute p0);
    
    List<LoginResult> selectOrderByType(Integer p0);
    
    List<LoginResult> selectSLDH();
    
    RoleTable selectRoleTableByRoleName(@Param("rolename") String p0);
    
    boolean insertIntoRoleTable(LoginResult p0);
    
    List<LoginResult> findGangManberByGangID(BigDecimal p0);
    
    boolean updateRole(RoleTable p0);
    
    void updateRoleWhenExit(LoginResult p0);
    
    BigDecimal selectMoneyRoleID(@Param("role_id") BigDecimal p0);
    
    int updateMoneyRoleID(@Param("role_id") BigDecimal p0, @Param("gold") BigDecimal p1);
    
    int updateMoneyUserID(@Param("USER_ID") BigDecimal p0, @Param("MONEY") BigDecimal p1);
    
    UserTable selectForUserId(BigDecimal p0);
    
    void updateByPrimaryKey(RoleTable p0);
    
    int updateRolePwdForRid(RoleTable p0);
    
    int updateRoleGMForRid(RoleTable p0);
    
    int deleteRolePwdForRid(RoleTable p0);
    
    RoleTable selectGang(BigDecimal p0);
    
    int updateGang(RoleTable p0);
    
    int deleteSQL(RoleTable p0);
    
    BigDecimal selectRoleMax();
    
    List<LoginResult> selectRoleByUserid(@Param("userid") BigDecimal p0, @Param("fuserid") BigDecimal p1);
    
    int updateRoleStatues(@Param("roleid") BigDecimal p0);
    
    LoginResult selectRoleByRoleId(@Param("roleid") BigDecimal p0);
    
    int updateRoleBelong(@Param("roleid") BigDecimal p0, @Param("userid") BigDecimal p1);
    
    LoginResult selectRoleName(String p0);
    
    void addQMJJ(@Param("roleid") BigDecimal p0, @Param("add") int p1);
    
    List<Map<String, Object>> selectConfigure();
    
    List<Map<String, Object>> selectadminUserList(Map<String, Object> p0);
    
    int insertUser(Map<String, Object> p0);
    
    void deleteUser(Map<String, Object> p0);
    
    void updateUserAmount(Map<String, Object> p0);
    
    List<LoginResult> selectRoleByRoleNum(@Param("count") int p0, @Param("notInStr") String p1);
    
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
