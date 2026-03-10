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
    List<LoginResult> selectLogintableByCondition(LoginResult loginCondition);
    
    List<LoginResult> findAllUserRoles();
    
    List<UserTable> findAllUser();
    
    List<UserTable> selectGolemUser();
    
    UserTable findUserByUserNameAndUserPassword(@Param("username") String username, @Param("userpwd") String userPassword);
    
    List<LoginResult> findRoleByUserNameAndPassword(@Param("userName") String userName, @Param("userPwd") String userPassword, @Param("serverMeString") String serverMessage);
    
    int insertIntoUser(UserTable userTable);
    
    void updateUser(UserTable userTable);
    
    UserTable selectForUsername(String username);
    
    UserTable selectByPrimaryKey(BigDecimal userId);
    
    List<RoleTable> selectByCondition(@Param("qid") String qid, @Param("rolename") String roleName);
    
    int selectSumForRoleUserHaterNumber(RoleTable roleTable);
    
    List<RoleTable> selectSumForRoleUserHaterList(RoleTable roleTable);
    
    int selectUsterTableForConcition(UserTable userTable);
    
    List<UserTable> selectForConditionForUsertable(UserTable userTable);
    
    int updateUsterWithUid(UserTable userTable);
    
    int updatePwdUserForRid(UserTable userTable);
    
    int updateUsterWithUidforuserpasswd(UserTable userTable);
    
    int delectUsertableForUsername(String username);
    
    int deleteRoletableForUid(BigDecimal userId);
    
    Ipaddressmac selectFromIpaddressmac(String ipAddress);
    
    int insertFromIpaddressmac(String ipAddress);
    
    int deleteFromIpaddressmac(String ipAddress);
    
    List<RoleTable> selectAllRoleTable(String username);
    
    int roleChangeUser(@Param("userName") String username, @Param("user_id") BigDecimal userId, @Param("roleId") String roleId);
    
    long selectAllCodecard();
    
    long selectAllPayintegration();
    
    long selectAllGold();
    
    int selectPhoneNumberSum(String phoneNumber);
    
    List<UserxyandroledhbcrEntity> selectAccountRechargeList(@Param("time") String timeRange, @Param("weekendsum") String weekendSummary, @Param("username") String username);
    
    List<UserxyandroledhbcrEntity> selectAccountRechargeUser(BigDecimal userId);
    
    List<UserxyandroledhbcrEntity> selectUserRoleXianyuDahuabiList();
    
    int addUserRoleXianyuDahuabi(UserxyandroledhbcrEntity rechargeRecord);
    
    int addRufenghaoControl(@Param("userTable") UserTable userTable, @Param("roleName") String roleName, @Param("reason") String reason, @Param("controlname") String controlName, @Param("type") int type);
    
    List<Rufenghaocontrol> selectRufenghaoControlList(@Param("type") String type, @Param("time") String timeRange, @Param("userName") String userName, @Param("roleName") String roleName, @Param("sort") int sortType);
    
    int deleteFenghaoRecord(BigDecimal recordId);
    
    List<UserxyandroledhbcrEntity> selectRechargeConsumeSum(String queryType);
    
    int selectRechargeConsumeSumNum();
    
    UserxyandroledhbcrEntity selectRechargeConsumeNowSum();
    
    List<UserTable> findUserByPhoneNum(@Param("phonenum") String phoneNumber);
    
    BigDecimal selectUserMax();
    
    int updateUnSeal(String flag);
    
    UserTable selectByFlag(@Param("flag") String flag);
    
    UserTable selectByBinding(@Param("username") String username, @Param("userpasw") String userPassword, @Param("safety") String safetyPassword);
    
    int updateByBinding(@Param("userTable") UserTable userTable);
    
    String selectUserFlagById(@Param("userid") BigDecimal userId);
    
    int updateConfigure(Map<String, Object> configureMap);
}
