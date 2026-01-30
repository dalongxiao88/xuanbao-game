package org.come.mapper;

import java.util.Map;
import org.come.entity.Rufenghaocontrol;
import org.come.entity.UserxyandroledhbcrEntity;
import org.come.entity.Ipaddressmac;
import org.come.entity.RoleTable;
import java.math.BigDecimal;
import org.apache.ibatis.annotations.Param;
import org.come.entity.UserTable;
import java.util.List;
import org.come.bean.LoginResult;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface UsertableMapper
{
    List<LoginResult> selectLogintableByCondition(LoginResult p0);
    
    List<LoginResult> findAllUserRoles();
    
    List<UserTable> findAllUser();
    
    List<UserTable> selectGolemUser();
    
    UserTable findUserByUserNameAndUserPassword(@Param("username") String p0, @Param("userpwd") String p1);
    
    List<LoginResult> findRoleByUserNameAndPassword(@Param("userName") String p0, @Param("userPwd") String p1, @Param("serverMeString") String p2);
    
    int insertIntoUser(UserTable p0);
    
    void updateUser(UserTable p0);
    
    UserTable selectForUsername(String p0);
    
    UserTable selectByPrimaryKey(BigDecimal p0);
    
    List<RoleTable> selectByCondition(@Param("qid") String p0, @Param("rolename") String p1);
    
    int selectSumForRoleUserHaterNumber(RoleTable p0);
    
    List<RoleTable> selectSumForRoleUserHaterList(RoleTable p0);
    
    int selectUsterTableForConcition(UserTable p0);
    
    List<UserTable> selectForConditionForUsertable(UserTable p0);
    
    int updateUsterWithUid(UserTable p0);
    
    int updatePwdUserForRid(UserTable p0);
    
    int updateUsterWithUidforuserpasswd(UserTable p0);
    
    int delectUsertableForUsername(String p0);
    
    int deleteRoletableForUid(BigDecimal p0);
    
    Ipaddressmac selectFromIpaddressmac(String p0);
    
    int insertFromIpaddressmac(String p0);
    
    int deleteFromIpaddressmac(String p0);
    
    List<RoleTable> selectAllRoleTable(String p0);
    
    int roleChangeUser(@Param("userName") String p0, @Param("user_id") BigDecimal p1, @Param("roleId") String p2);
    
    long selectAllCodecard();
    
    long selectAllPayintegration();
    
    long selectAllGold();
    
    int selectPhoneNumberSum(String p0);
    
    List<UserxyandroledhbcrEntity> selectAccountRechargeList(@Param("time") String p0, @Param("weekendsum") String p1, @Param("username") String p2);
    
    List<UserxyandroledhbcrEntity> selectAccountRechargeUser(BigDecimal p0);
    
    List<UserxyandroledhbcrEntity> selectUserRoleXianyuDahuabiList();
    
    int addUserRoleXianyuDahuabi(UserxyandroledhbcrEntity p0);
    
    int addRufenghaoControl(@Param("userTable") UserTable p0, @Param("roleName") String p1, @Param("reason") String p2, @Param("controlname") String p3, @Param("type") int p4);
    
    List<Rufenghaocontrol> selectRufenghaoControlList(@Param("type") String p0, @Param("time") String p1, @Param("userName") String p2, @Param("roleName") String p3, @Param("sort") int p4);
    
    int deleteFenghaoRecord(BigDecimal p0);
    
    List<UserxyandroledhbcrEntity> selectRechargeConsumeSum(String p0);
    
    int selectRechargeConsumeSumNum();
    
    UserxyandroledhbcrEntity selectRechargeConsumeNowSum();
    
    List<UserTable> findUserByPhoneNum(@Param("phonenum") String p0);
    
    BigDecimal selectUserMax();
    
    int updateUnSeal(String p0);
    
    UserTable selectByFlag(@Param("flag") String p0);
    
    UserTable selectByBinding(@Param("username") String p0, @Param("userpasw") String p1, @Param("safety") String p2);
    
    int updateByBinding(@Param("userTable") UserTable p0);
    
    String selectUserFlagById(@Param("userid") BigDecimal p0);
    
    int updateConfigure(Map<String, Object> p0);
}
