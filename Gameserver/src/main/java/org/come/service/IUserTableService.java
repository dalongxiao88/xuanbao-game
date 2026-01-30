package org.come.service;

import java.util.Map;
import org.come.entity.Rufenghaocontrol;
import org.come.entity.UserxyandroledhbcrEntity;
import org.come.entity.Ipaddressmac;
import org.come.entity.RoleTable;
import org.come.bean.BackRoleInfo;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import org.come.bean.LoginResult;
import com.github.pagehelper.PageInfo;
import org.come.entity.UserTable;
import java.math.BigDecimal;

public interface IUserTableService
{
    UserTable selectByPrimaryKey(BigDecimal p0);
    
    PageInfo<LoginResult> selectLogintableByCondition(Integer p0, String p1);
    
    List<LoginResult> findAllUserRoles();
    
    List<UserTable> findAllUser();
    
    List<UserTable> selectGolemUser();
    
    UserTable findUserByUserNameAndUserPassword(@Param("userName") String p0, @Param("userpwd") String p1);
    
    List<LoginResult> findRoleByUserNameAndPassword(@Param("username") String p0, @Param("userpwd") String p1, @Param("serverMeString") String p2);
    
    int insertIntoUser(UserTable p0);
    
    void updateUser(UserTable p0);
    
    UserTable selectForUsername(String p0);
    
    BackRoleInfo selectByCondition(String p0, String p1, int p2, String p3);
    
    int selectSumForRoleUserHaterNumber(RoleTable p0);
    
    List<RoleTable> selectSumForRoleUserHaterList(RoleTable p0);
    
    int selectUsterTableForConcition(UserTable p0);
    
    List<UserTable> selectForConditionForUsertable(UserTable p0);
    
    int updateUsterWithUid(UserTable p0);
    
    int updateUsterWithUidforuserpasswd(UserTable p0);
    
    int updatePwdUserForRid(UserTable p0);
    
    int delectUsertableForUsername(String p0);
    
    int deleteRoletableForUid(BigDecimal p0);
    
    Ipaddressmac selectFromIpaddressmac(String p0);
    
    int insertFromIpaddressmac(String p0);
    
    int deleteFromIpaddressmac(String p0);
    
    List<RoleTable> selectAllRoleTable(String p0);
    
    int roleChangeUser(String p0, BigDecimal p1, String p2);
    
    long selectAllCodecard();
    
    long selectAllPayintegration();
    
    long selectAllGold();
    
    int selectPhoneNumberSum(String p0);
    
    List<UserxyandroledhbcrEntity> selectAccountRechargeList(String p0, String p1, int p2, String p3);
    
    List<UserxyandroledhbcrEntity> selectAccountRechargeUser(BigDecimal p0);
    
    List<UserxyandroledhbcrEntity> selectUserRoleXianyuDahuabiList();
    
    int addUserRoleXianyuDahuabi(UserxyandroledhbcrEntity p0);
    
    int addRufenghaoControl(UserTable p0, String p1, String p2, String p3, int p4);
    
    List<Rufenghaocontrol> selectRufenghaoControlList(String p0, String p1, String p2, String p3, int p4, int p5);
    
    int deleteFenghaoRecord(BigDecimal p0);
    
    List<UserxyandroledhbcrEntity> selectRechargeConsumeSum(String p0);
    
    int selectRechargeConsumeSumNum();
    
    UserxyandroledhbcrEntity selectRechargeConsumeNowSum();
    
    BigDecimal selectUserMax();
    
    List<UserTable> findUserByPhoneNum(String p0);
    
    int updateUnSeal(String p0);
    
    UserTable selectByFlag(String p0);
    
    UserTable selectByBinding(String p0, String p1, String p2);
    
    int updateByBinding(UserTable p0);
    
    String selectUserFlagById(BigDecimal p0);
    
    int updateConfigure(Map<String, Object> p0);
}
