package org.come.serviceImpl;

import org.come.entity.RoleAttribute;
import java.util.Map;
import org.come.tool.WriteOut;
import org.come.entity.Record;
import org.come.until.AllServiceUtil;
import org.come.entity.UserTable;
import java.util.List;
import org.come.redis.RedisControl;
import org.come.until.GsonUtil;
import org.come.bean.LoginResult;
import java.math.BigDecimal;
import org.come.entity.RoleTable;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.RoleTableMapper;
import org.come.service.IRoleTableService;

public class RoleTableServiceImpl implements IRoleTableService
{
    private RoleTableMapper roleTableMapper;
    
    public RoleTableServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.roleTableMapper = (RoleTableMapper)ctx.getBean("roleTableMapper", RoleTableMapper.class);
    }
    
    public static void main(String[] args) {
        RoleTableServiceImpl impl = new RoleTableServiceImpl();
        RoleTable roleTable = new RoleTable();
        roleTable.setRole_id(new BigDecimal(11));
        impl.updateByPrimaryKey(roleTable);
    }
    
    @Override
    public boolean insertIntoRoleTable(LoginResult loginResult) {
        boolean exist = this.roleTableMapper.insertIntoRoleTable(loginResult);
        RedisControl.userController("R", loginResult.getRole_id().toString(), "1", GsonUtil.getGsonUtil().getgson().toJson(loginResult));
        return exist;
    }
    
    @Override
    public List<LoginResult> findGangManberByGangID(BigDecimal gang_id) {
        List<LoginResult> gangRoles = this.roleTableMapper.findGangManberByGangID(gang_id);
        return gangRoles;
    }
    
    @Override
    public boolean updateRole(RoleTable roleTable) {
        boolean isSuccess = this.roleTableMapper.updateRole(roleTable);
        return isSuccess;
    }
    
    @Override
    public RoleTable selectRoleTableByRoleName(String rolename) {
        RoleTable roleTable = this.roleTableMapper.selectRoleTableByRoleName(rolename);
        return roleTable;
    }
    
    @Override
    public void updateRoleWhenExit(LoginResult loginResult) {
        try {
            this.roleTableMapper.updateRoleWhenExit(loginResult);
        }
        catch (Exception e) {
            UserTable table = new UserTable();
            table.setUsername(loginResult.getUserName());
            table.setActivity(Short.valueOf((short)1));
            AllServiceUtil.getUserTableService().updateUser(table);
            AllServiceUtil.getRecordService().insert(new Record(5, loginResult.getUserName()));
            WriteOut.addtxt("最新修改人物角色发生错误:" + e.getMessage() + GsonUtil.getGsonUtil().getgson().toJson(loginResult), 9999L);
            e.printStackTrace();
        }
    }
    
    @Override
    public BigDecimal selectMoneyRoleID(BigDecimal role_id) {
        return this.roleTableMapper.selectMoneyRoleID(role_id);
    }
    
    @Override
    public int updateMoneyRoleID(BigDecimal role_id, BigDecimal gold) {
        return this.roleTableMapper.updateMoneyRoleID(role_id, gold);
    }
    
    @Override
    public void updateByPrimaryKey(RoleTable roleTable) {
        this.roleTableMapper.updateByPrimaryKey(roleTable);
    }
    
    @Override
    public List<LoginResult> selectOrderByType(Integer type) {
        return this.roleTableMapper.selectOrderByType(type);
    }
    
    @Override
    public LoginResult selectRoleID(BigDecimal role_id) {
        LoginResult S = this.roleTableMapper.selectRoleID(role_id);
        return S;
    }
    
    @Override
    public int updateRolePwdForRid(RoleTable roleTable) {
        return this.roleTableMapper.updateRolePwdForRid(roleTable);
    }
    
    @Override
    public int updateRoleGMForRid(RoleTable roleTable) {
        return this.roleTableMapper.updateRoleGMForRid(roleTable);
    }
    
    @Override
    public int deleteRolePwdForRid(RoleTable roleTable) {
        return this.roleTableMapper.deleteRolePwdForRid(roleTable);
    }
    
    @Override
    public List<LoginResult> selectSLDH() {
        return this.roleTableMapper.selectSLDH();
    }
    
    @Override
    public RoleTable selectGang(BigDecimal role_id) {
        return this.roleTableMapper.selectGang(role_id);
    }
    
    @Override
    public int updateGang(RoleTable roleTable) {
        return this.roleTableMapper.updateGang(roleTable);
    }
    
    @Override
    public BigDecimal selectRoleMax() {
        return this.roleTableMapper.selectRoleMax();
    }
    
    @Override
    public List<LoginResult> selectRoleByUserid(BigDecimal userid, BigDecimal fuserid) {
        return this.roleTableMapper.selectRoleByUserid(userid, fuserid);
    }
    
    @Override
    public int updateRoleStatues(BigDecimal roleid) {
        return this.roleTableMapper.updateRoleStatues(roleid);
    }
    
    @Override
    public int updateRoleBelong(BigDecimal roleid, BigDecimal userid) {
        return this.roleTableMapper.updateRoleBelong(roleid, userid);
    }
    
    @Override
    public int updateMoneyUserID(BigDecimal roleid, BigDecimal userid) {
        return this.roleTableMapper.updateMoneyUserID(roleid, userid);
    }
    
    @Override
    public LoginResult selectRoleByRoleId(BigDecimal roleid) {
        return this.roleTableMapper.selectRoleByRoleId(roleid);
    }
    
    @Override
    public LoginResult selectRoleName(String rolename) {
        return this.roleTableMapper.selectRoleName(rolename);
    }
    
    @Override
    public List<Map<String, Object>> selectConfigure() {
        return this.roleTableMapper.selectConfigure();
    }
    
    @Override
    public void addQMJJ(BigDecimal roleid, int add) {
        this.roleTableMapper.addQMJJ(roleid, add);
    }
    
    @Override
    public List<LoginResult> selectRoleByRoleNum(int count, String notInStr) {
        return this.roleTableMapper.selectRoleByRoleNum(count, notInStr);
    }
    
    @Override
    public void deleteTableSQL(RoleTable roleTable) {
//        this.roleTableMapper.deleteTableSQL1(roleTable);
//        this.roleTableMapper.deleteTableSQL2(roleTable);
//        this.roleTableMapper.deleteTableSQL3(roleTable);
//        this.roleTableMapper.deleteTableSQL4(roleTable);
//        this.roleTableMapper.deleteTableSQL5(roleTable);
//        this.roleTableMapper.deleteTableSQL6(roleTable);
//        this.roleTableMapper.deleteTableSQL7(roleTable);
//        this.roleTableMapper.deleteTableSQL8(roleTable);
//        this.roleTableMapper.deleteTableSQL9(roleTable);
//        this.roleTableMapper.deleteTableSQL10(roleTable);
//        this.roleTableMapper.deleteTableSQL11(roleTable);
//        this.roleTableMapper.deleteTableSQL12(roleTable);
//        this.roleTableMapper.deleteTableSQL13(roleTable);
//        this.roleTableMapper.deleteTableSQL14(roleTable);
//        this.roleTableMapper.deleteTableSQL15(roleTable);
//        this.roleTableMapper.deleteTableSQL16(roleTable);
//        this.roleTableMapper.deleteTableSQL17(roleTable);
//        this.roleTableMapper.deleteTableSQL18(roleTable);
//        this.roleTableMapper.deleteTableSQL19(roleTable);
//        this.roleTableMapper.deleteTableSQL20(roleTable);
//        this.roleTableMapper.deleteTableSQL21(roleTable);
//        this.roleTableMapper.deleteTableSQL22(roleTable);
//        this.roleTableMapper.deleteTableSQL23(roleTable);
//        this.roleTableMapper.deleteTableSQL24(roleTable);
//        this.roleTableMapper.deleteTableSQL25(roleTable);
//        this.roleTableMapper.deleteTableSQL26(roleTable);
//        this.roleTableMapper.deleteTableSQL27(roleTable);
//        this.roleTableMapper.deleteTableSQL28(roleTable);
//        this.roleTableMapper.deleteTableSQL29(roleTable);
//        this.roleTableMapper.deleteTableSQL30(roleTable);
//        this.roleTableMapper.deleteTableSQL31(roleTable);
//        this.roleTableMapper.deleteTableSQL32(roleTable);
//        this.roleTableMapper.deleteTableSQL33(roleTable);
//        this.roleTableMapper.deleteTableSQL34(roleTable);
//        this.roleTableMapper.deleteTableSQL35(roleTable);
//        this.roleTableMapper.deleteTableSQL36(roleTable);
//        this.roleTableMapper.deleteTableSQL37(roleTable);
//        this.roleTableMapper.deleteTableSQL38(roleTable);
//        this.roleTableMapper.deleteTableSQL39(roleTable);
//        this.roleTableMapper.deleteTableSQL40(roleTable);
//        this.roleTableMapper.deleteTableSQL41(roleTable);
//        this.roleTableMapper.deleteTableSQL42(roleTable);
//        this.roleTableMapper.deleteTableSQL43(roleTable);
//        this.roleTableMapper.deleteTableSQL44(roleTable);
//        this.roleTableMapper.deleteTableSQL45(roleTable);
//        this.roleTableMapper.deleteTableSQL46(roleTable);
    }
    
    @Override
    public List<Map<String, Object>> selectadminUserList(Map<String, Object> param) {
        return this.roleTableMapper.selectadminUserList(param);
    }
    
    @Override
    public int insertUser(Map<String, Object> param) {
        int isSuccess = this.roleTableMapper.insertUser(param);
        return isSuccess;
    }
    
    @Override
    public boolean deleteUser(Map<String, Object> param) {
        System.out.println(param.get("ACCOUNT"));
        this.roleTableMapper.deleteUser(param);
        return true;
    }
    
    @Override
    public void updateUserAmount(Map<String, Object> param) {
        this.roleTableMapper.updateUserAmount(param);
    }
    
    @Override
    public void addTTJJ(BigDecimal roleid, int add, int state) {
        this.roleTableMapper.addTTJJ(roleid, add, state);
    }
    
    @Override
    public void upTTJJ(BigDecimal roleid) {
        this.roleTableMapper.upTTJJ(roleid);
    }
    
    @Override
    public void updateTTJiangli(String TTJIANGLI) {
        this.roleTableMapper.updateTTJiangli(TTJIANGLI);
    }
    
    @Override
    public UserTable selectForUserId(BigDecimal roleid) {
        return this.roleTableMapper.selectForUserId(roleid);
    }
    
    @Override
    public RoleAttribute selectRoleAttributeRoleId(BigDecimal roleid) {
        return this.roleTableMapper.selectRoleAttributeRoleId(roleid);
    }
    
    @Override
    public void updateRoleAttributeRoleId(RoleAttribute roleAttribute) {
        this.roleTableMapper.updateRoleAttributeRoleId(roleAttribute);
    }
    
    @Override
    public void insertRoleAttribute(RoleAttribute roleAttribute) {
        this.roleTableMapper.insertRoleAttribute(roleAttribute);
    }
    
    @Override
    public void updateRoleExtPoint(RoleTable roleTable) {
        this.roleTableMapper.updateRoleExtPoint(roleTable);
    }
    
    @Override
    public void updateRoleLiangHao(RoleTable roleTable) {
        this.roleTableMapper.updateRoleLiangHao(roleTable);
    }
    
    @Override
    public void getLiangHao(RoleTable roleTable) {
        this.roleTableMapper.getLiangHao(roleTable);
    }
    
    @Override
    public void addLiangHaoExp(RoleTable roleTable) {
        this.roleTableMapper.addLiangHaoExp(roleTable);
    }
    
    @Override
    public void updateRoleLiangHaoType(RoleTable roleTable) {
        this.roleTableMapper.updateRoleLiangHaoType(roleTable);
    }
    
    @Override
    public void dropLiangHao(RoleTable roleTable) {
        this.roleTableMapper.dropLiangHao(roleTable);
    }
    
    @Override
    public List<RoleTable> getRoleTaleByLiangHao(RoleTable roleTable) {
        return this.roleTableMapper.getRoleTaleByLiangHao(roleTable);
    }
    
    @Override
    public List<String> allLiangHao() {
        return this.roleTableMapper.allLiangHao();
    }
    
    @Override
    public LoginResult selectRoleIdOrLiangHao(RoleTable roleTable) {
        return this.roleTableMapper.selectRoleIdOrLiangHao(roleTable);
    }
    
    @Override
    public List<RoleTable> selectExpLh() {
        return this.roleTableMapper.selectExpLh();
    }
    @Override
    public void updateDifficult(BigDecimal roleid) {
        roleTableMapper.updateDifficult(roleid);
    }
    @Override
    public void updateDAYDRAW(String DAYDRAW) {
        roleTableMapper.updateDAYDRAW(DAYDRAW);
    }
}
