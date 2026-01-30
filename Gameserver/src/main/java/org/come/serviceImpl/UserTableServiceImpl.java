package org.come.serviceImpl;

import java.util.Map;
import org.come.entity.Rufenghaocontrol;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.come.entity.UserxyandroledhbcrEntity;
import org.come.entity.Ipaddressmac;
import org.come.entity.Haters;
import org.come.service.IHatersService;
import org.come.server.GameServer;
import java.util.Iterator;
import org.come.entity.RoleTable;
import java.util.ArrayList;
import org.come.bean.BackRoleInfo;
import java.math.BigDecimal;
import com.github.pagehelper.BasePageHelper;
import com.github.pagehelper.PageInfo;
import org.come.redis.RedisControl;
import org.come.until.GsonUtil;
import org.come.until.TimeUntil;
import org.come.bean.LoginResult;
import java.util.List;
import org.come.entity.UserTable;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.UsertableMapper;
import org.come.service.IUserTableService;

public class UserTableServiceImpl implements IUserTableService
{
    private UsertableMapper userTableMapper;
    
    public UserTableServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.userTableMapper = (UsertableMapper)ctx.getBean("usertableMapper");
    }
    
    @Override
    public int updatePwdUserForRid(UserTable userTable) {
        return this.userTableMapper.updatePwdUserForRid(userTable);
    }
    
    @Override
    public List<LoginResult> findRoleByUserNameAndPassword(String username, String userpwd, String serverMeString) {
        List<LoginResult> roles = this.userTableMapper.findRoleByUserNameAndPassword(username, userpwd, serverMeString);
        return roles;
    }
    
    @Override
    public UserTable findUserByUserNameAndUserPassword(String userName, String userPwd) {
        UserTable userTable = this.userTableMapper.findUserByUserNameAndUserPassword(userName, userPwd);
        return userTable;
    }
    
    @Override
    public int insertIntoUser(UserTable userTable) {
        userTable.setUserregidtsertime(TimeUntil.getPastDate());
        int message = this.userTableMapper.insertIntoUser(userTable);
        RedisControl.userController("U", userTable.getUser_id().toString(), "1", GsonUtil.getGsonUtil().getgson().toJson(userTable));
        return message;
    }
    
    @Override
    public List<LoginResult> findAllUserRoles() {
        List<LoginResult> roles = this.userTableMapper.findAllUserRoles();
        return roles;
    }
    
    @Override
    public List<UserTable> findAllUser() {
        List<UserTable> userTable = this.userTableMapper.findAllUser();
        return userTable;
    }
    
    @Override
    public List<UserTable> selectGolemUser() {
        return this.userTableMapper.selectGolemUser();
    }
    
    @Override
    public void updateUser(UserTable userTable) {
        this.userTableMapper.updateUser(userTable);
    }
    
    @Override
    public PageInfo<LoginResult> selectLogintableByCondition(Integer pageNum, String condition) {
        LoginResult loginResult = (LoginResult)GsonUtil.getGsonUtil().getgson().fromJson(condition, LoginResult.class);
        BasePageHelper.startPage((int)pageNum, 8);
        List<LoginResult> list = this.userTableMapper.selectLogintableByCondition(loginResult);
        PageInfo<LoginResult> pageinfo = new PageInfo(list);
        return pageinfo;
    }
    
    @Override
    public UserTable selectForUsername(String username) {
        return this.userTableMapper.selectForUsername(username);
    }
    
    @Override
    public UserTable selectByPrimaryKey(BigDecimal userid) {
        return this.userTableMapper.selectByPrimaryKey(userid);
    }
    
    @Override
    public BackRoleInfo selectByCondition(String qid, String rolename, int pageNum, String statues) {
        BackRoleInfo jb = new BackRoleInfo();
        if (statues != null) {
            List<RoleTable> roles = this.userTableMapper.selectByCondition(qid, null);
            this.roleState(roles);
            List<RoleTable> selectRoles = new ArrayList<>();
            List<RoleTable> returnRoles = new ArrayList<>();
            int n = -1;
            switch (statues.hashCode()) {
                case 49: {
                    if (statues.equals("1")) {
                        n = 0;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 50: {
                    if (statues.equals("2")) {
                        n = 1;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 51: {
                    if (statues.equals("3")) {
                        n = 2;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 52: {
                    if (statues.equals("4")) {
                        n = 3;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 53: {
                    if (statues.equals("5")) {
                        n = 4;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 54: {
                    if (statues.equals("6")) {
                        n = 5;
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
            switch (n) {
                case 0: {
                    for (RoleTable roleTable : roles) {
                        if (roleTable.getStatues().indexOf("1") != -1) {
                            selectRoles.add(roleTable);
                        }
                    }
                    break;
                }
                case 1: {
                    for (RoleTable roleTable : roles) {
                        if (roleTable.getStatues().indexOf("2") != -1) {
                            selectRoles.add(roleTable);
                        }
                    }
                    break;
                }
                case 2: {
                    for (RoleTable roleTable : roles) {
                        if (roleTable.getStatues().indexOf("3") != -1) {
                            selectRoles.add(roleTable);
                        }
                    }
                    break;
                }
                case 3: {
                    for (RoleTable roleTable : roles) {
                        if (roleTable.getStatues().indexOf("4") != -1) {
                            selectRoles.add(roleTable);
                        }
                    }
                    break;
                }
                case 4: {
                    for (RoleTable roleTable : roles) {
                        if (roleTable.getStatues().indexOf("4") == -1) {
                            selectRoles.add(roleTable);
                        }
                    }
                    break;
                }
                case 5: {
                    for (RoleTable roleTable : roles) {
                        if (roleTable.getStatues().indexOf("3") == -1) {
                            selectRoles.add(roleTable);
                        }
                    }
                    break;
                }
                default: {
                    System.out.println("标识错误！！！");
                    break;
                }
            }
            if (rolename != null) {
                for (RoleTable roleTable2 : selectRoles) {
                    if (roleTable2.getRolename().indexOf(rolename) == -1) {
                        returnRoles.add(roleTable2);
                    }
                }
            }
            else {
                returnRoles.addAll(selectRoles);
            }
            int size = returnRoles.size();
            int pages = 0;
            if (size < 8) {
                if (size == 0) {
                    pages = 0;
                }
                else {
                    pages = 1;
                }
                jb.setList(returnRoles);
            }
            else if (size % 8 == 0) {
                pages = size / 8;
                jb.setList(returnRoles.subList((pageNum - 1) * 8, pageNum * 8 - 1));
            }
            else {
                pages = (int)Math.floor((double)(size / 8)) + 1;
                if (pageNum == pages) {
                    jb.setList(returnRoles.subList(pageNum * 8, size - 1));
                }
                else {
                    jb.setList(returnRoles.subList((pageNum - 1) * 8, pageNum * 8 - 1));
                }
            }
            jb.setPageNum(pageNum);
            jb.setPages(pages);
        }
        else {
            BasePageHelper.startPage(pageNum, 8);
            List<RoleTable> list = this.userTableMapper.selectByCondition(qid, rolename);
            PageInfo<RoleTable> pageInfo = new PageInfo(list);
            this.roleState(pageInfo.getList());
            jb.setList(pageInfo.getList());
            jb.setPageNum(pageInfo.getPageNum());
            jb.setPages(pageInfo.getPages());
        }
        return jb;
    }
    
    public void roleState(List<RoleTable> list) {
        IHatersService hatersService = new HatersServiceImpl();
        for (RoleTable roleInfo : list) {
            String status = "";
            if (GameServer.getRoleNameMap().get(roleInfo.getRolename()) != null) {
                status += "/1";
            }
            else {
                status += "/2";
            }
            Haters hater = hatersService.selectByPrimaryKey(roleInfo.getRole_id());
            if (hater != null) {
                status += "/3";
            }
            else {
                status += "/6";
            }
            UserTable userInfo = this.userTableMapper.selectByPrimaryKey(roleInfo.getUser_id());
            if ((short)userInfo.getActivity() != 0) {
                status += "/4";
            }
            else {
                status += "/5";
            }
            roleInfo.setStatues(status.replaceFirst("/", ""));
        }
    }
    
    @Override
    public int selectSumForRoleUserHaterNumber(RoleTable roleTable) {
        return this.userTableMapper.selectSumForRoleUserHaterNumber(roleTable);
    }
    
    @Override
    public List<RoleTable> selectSumForRoleUserHaterList(RoleTable roleTable) {
        return this.userTableMapper.selectSumForRoleUserHaterList(roleTable);
    }
    
    @Override
    public int selectUsterTableForConcition(UserTable table) {
        return this.userTableMapper.selectUsterTableForConcition(table);
    }
    
    @Override
    public List<UserTable> selectForConditionForUsertable(UserTable table) {
        return this.userTableMapper.selectForConditionForUsertable(table);
    }
    
    @Override
    public int updateUsterWithUid(UserTable table) {
        return this.userTableMapper.updateUsterWithUid(table);
    }
    
    @Override
    public int updateUsterWithUidforuserpasswd(UserTable table) {
        return this.userTableMapper.updateUsterWithUidforuserpasswd(table);
    }
    
    @Override
    public int delectUsertableForUsername(String username) {
        return this.userTableMapper.delectUsertableForUsername(username);
    }
    
    @Override
    public int deleteRoletableForUid(BigDecimal user_id) {
        return this.userTableMapper.deleteRoletableForUid(user_id);
    }
    
    @Override
    public Ipaddressmac selectFromIpaddressmac(String ip) {
        return this.userTableMapper.selectFromIpaddressmac(ip);
    }
    
    @Override
    public int insertFromIpaddressmac(String ip) {
        return this.userTableMapper.insertFromIpaddressmac(ip);
    }
    
    @Override
    public int deleteFromIpaddressmac(String ip) {
        return this.userTableMapper.deleteFromIpaddressmac(ip);
    }
    
    @Override
    public List<RoleTable> selectAllRoleTable(String userName) {
        return this.userTableMapper.selectAllRoleTable(userName);
    }
    
    @Override
    public int roleChangeUser(String userName, BigDecimal user_id, String roleId) {
        return this.userTableMapper.roleChangeUser(userName, user_id, roleId);
    }
    
    @Override
    public long selectAllCodecard() {
        return this.userTableMapper.selectAllCodecard();
    }
    
    @Override
    public long selectAllPayintegration() {
        return this.userTableMapper.selectAllPayintegration();
    }
    
    @Override
    public long selectAllGold() {
        return this.userTableMapper.selectAllGold();
    }
    
    @Override
    public int selectPhoneNumberSum(String phoneumber) {
        return this.userTableMapper.selectPhoneNumberSum(phoneumber);
    }
    
    @Override
    public List<UserxyandroledhbcrEntity> selectAccountRechargeList(String time, String weekendsum, int page, String username) {
        BasePageHelper.startPage(page, 10);
        if (username != null && !"".equals(username) && (time == null || "".equals(time))) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            time = sdf.format(new Date());
        }
        return this.userTableMapper.selectAccountRechargeList(time, weekendsum, username);
    }
    
    @Override
    public List<UserxyandroledhbcrEntity> selectAccountRechargeUser(BigDecimal userid) {
        return this.userTableMapper.selectAccountRechargeUser(userid);
    }
    
    @Override
    public List<UserxyandroledhbcrEntity> selectUserRoleXianyuDahuabiList() {
        return this.userTableMapper.selectUserRoleXianyuDahuabiList();
    }
    
    @Override
    public int addUserRoleXianyuDahuabi(UserxyandroledhbcrEntity userxyandroledhbcrEntity) {
        return this.userTableMapper.addUserRoleXianyuDahuabi(userxyandroledhbcrEntity);
    }
    
    @Override
    public int addRufenghaoControl(UserTable userTable, String roleName, String reason, String controlname, int type) {
        return this.userTableMapper.addRufenghaoControl(userTable, roleName, reason, controlname, type);
    }
    
    @Override
    public List<Rufenghaocontrol> selectRufenghaoControlList(String type, String time, String userName, String roleName, int page, int sort) {
        BasePageHelper.startPage(page, 10);
        return this.userTableMapper.selectRufenghaoControlList(type, time, userName, roleName, sort);
    }
    
    @Override
    public int deleteFenghaoRecord(BigDecimal id) {
        return this.userTableMapper.deleteFenghaoRecord(id);
    }
    
    @Override
    public List<UserxyandroledhbcrEntity> selectRechargeConsumeSum(String time) {
        return this.userTableMapper.selectRechargeConsumeSum(time);
    }
    
    @Override
    public int selectRechargeConsumeSumNum() {
        return this.userTableMapper.selectRechargeConsumeSumNum();
    }
    
    @Override
    public UserxyandroledhbcrEntity selectRechargeConsumeNowSum() {
        return this.userTableMapper.selectRechargeConsumeNowSum();
    }
    
    @Override
    public BigDecimal selectUserMax() {
        return this.userTableMapper.selectUserMax();
    }
    
    @Override
    public List<UserTable> findUserByPhoneNum(String phonenum) {
        return this.userTableMapper.findUserByPhoneNum(phonenum);
    }
    
    @Override
    public int updateUnSeal(String username) {
        return this.userTableMapper.updateUnSeal(username);
    }
    
    @Override
    public UserTable selectByFlag(String flag) {
        return this.userTableMapper.selectByFlag(flag);
    }
    
    @Override
    public UserTable selectByBinding(String username, String userpasw, String safety) {
        return this.userTableMapper.selectByBinding(username, userpasw, safety);
    }
    
    @Override
    public int updateByBinding(UserTable userTable) {
        return this.userTableMapper.updateByBinding(userTable);
    }
    
    @Override
    public String selectUserFlagById(BigDecimal userid) {
        return this.userTableMapper.selectUserFlagById(userid);
    }
    
    @Override
    public int updateConfigure(Map<String, Object> map) {
        return this.userTableMapper.updateConfigure(map);
    }
}
