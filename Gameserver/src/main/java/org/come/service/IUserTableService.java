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
    UserTable selectByPrimaryKey(BigDecimal userId);
    
    PageInfo<LoginResult> selectLogintableByCondition(Integer pageNum, String condition);
    
    List<LoginResult> findAllUserRoles();
    
    List<UserTable> findAllUser();
    
    List<UserTable> selectGolemUser();
    
    UserTable findUserByUserNameAndUserPassword(@Param("userName") String userName, @Param("userpwd") String userPassword);
    
    List<LoginResult> findRoleByUserNameAndPassword(@Param("username") String username, @Param("userpwd") String userPassword, @Param("serverMeString") String serverMessage);
    
    int insertIntoUser(UserTable userTable);
    
    void updateUser(UserTable userTable);
    
    UserTable selectForUsername(String username);
    
    BackRoleInfo selectByCondition(String qid, String roleName, int pageNum, String userName);
    
    int selectSumForRoleUserHaterNumber(RoleTable roleTable);
    
    List<RoleTable> selectSumForRoleUserHaterList(RoleTable roleTable);
    
    int selectUsterTableForConcition(UserTable userTable);
    
    List<UserTable> selectForConditionForUsertable(UserTable userTable);
    
    int updateUsterWithUid(UserTable userTable);
    
    int updateUsterWithUidforuserpasswd(UserTable userTable);
    
    int updatePwdUserForRid(UserTable userTable);
    
    int delectUsertableForUsername(String username);
    
    int deleteRoletableForUid(BigDecimal userId);
    
    Ipaddressmac selectFromIpaddressmac(String ipAddress);
    
    int insertFromIpaddressmac(String ipAddress);
    
    int deleteFromIpaddressmac(String ipAddress);
    
    List<RoleTable> selectAllRoleTable(String username);
    
    int roleChangeUser(String username, BigDecimal userId, String roleId);
    
    long selectAllCodecard();
    
    long selectAllPayintegration();
    
    long selectAllGold();
    
    int selectPhoneNumberSum(String phoneNumber);
    
    List<UserxyandroledhbcrEntity> selectAccountRechargeList(String timeRange, String weekendSummary, int pageNum, String username);
    
    List<UserxyandroledhbcrEntity> selectAccountRechargeUser(BigDecimal userId);
    
    List<UserxyandroledhbcrEntity> selectUserRoleXianyuDahuabiList();
    
    int addUserRoleXianyuDahuabi(UserxyandroledhbcrEntity rechargeRecord);
    
    int addRufenghaoControl(UserTable userTable, String roleName, String reason, String controlName, int type);
    
    List<Rufenghaocontrol> selectRufenghaoControlList(String type, String timeRange, String userName, String roleName, int sortType, int pageNum);
    
    int deleteFenghaoRecord(BigDecimal recordId);
    
    List<UserxyandroledhbcrEntity> selectRechargeConsumeSum(String queryType);
    
    int selectRechargeConsumeSumNum();
    
    UserxyandroledhbcrEntity selectRechargeConsumeNowSum();
    
    BigDecimal selectUserMax();
    
    List<UserTable> findUserByPhoneNum(String phoneNumber);
    
    int updateUnSeal(String flag);
    
    UserTable selectByFlag(String flag);
    
    UserTable selectByBinding(String username, String userPassword, String safetyPassword);
    
    int updateByBinding(UserTable userTable);
    
    String selectUserFlagById(BigDecimal userId);
    
    int updateConfigure(Map<String, Object> configureMap);
}
