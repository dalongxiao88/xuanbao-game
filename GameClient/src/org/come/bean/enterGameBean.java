package org.come.bean;

import org.come.entity.*;
import com.tool.role.RoleSystem;
import com.tool.Stall.Stall;
import com.tool.Stall.StallBean;
import org.come.model.Lingbao;

import java.util.List;

public class enterGameBean {
    private LoginResult loginResult;
    private RoleAttribute roleAttribute;
    private PrivateData privateData;
    private List<RoleShow> roleShows;
    private List<Goodstable> list;
    private List<RoleSummoning> roleSummonings;
    private List<RoleSummoning> roleDepositSummonings;
    private List<Mount> mounts;
    private List<Car> cars;
    private String monster;
    private List<Lingbao> lingbaos;
    private List<Baby> babys;
    private List<Pal> pals;
    private List<StallBean> stallBeans;
    private Stall stall;
    private PackRecord packRecord;
    private RoleSystem roleSystem;
    private String sceneMsg;
    private List<org.come.entity.Fly> flys;

    public String getSceneMsg() {
        return this.sceneMsg;
    }

    public void setSceneMsg(String sceneMsg) {
        this.sceneMsg = sceneMsg;
    }

    public List<RoleShow> getRoleShows() {
        return this.roleShows;
    }

    public void setRoleShows(List<RoleShow> roleShows) {
        this.roleShows = roleShows;
    }

    public List<Goodstable> getList() {
        return this.list;
    }

    public void setList(List<Goodstable> list) {
        this.list = list;
    }

    public List<RoleSummoning> getRoleSummonings() {
        return this.roleSummonings;
    }

    public void setRoleSummonings(List<RoleSummoning> roleSummonings) {
        this.roleSummonings = roleSummonings;
    }

    public List<Mount> getMounts() {
        return this.mounts;
    }

    public void setMounts(List<Mount> mounts) {
        this.mounts = mounts;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public String getMonster() {
        return this.monster;
    }

    public void setMonster(String monster) {
        this.monster = monster;
    }

    public List<Lingbao> getLingbaos() {
        return this.lingbaos;
    }

    public void setLingbaos(List<Lingbao> lingbaos) {
        this.lingbaos = lingbaos;
    }

    public List<Baby> getBabys() {
        return this.babys;
    }

    public void setBabys(List<Baby> babys) {
        this.babys = babys;
    }

    public List<StallBean> getStallBeans() {
        return this.stallBeans;
    }

    public void setStallBeans(List<StallBean> stallBeans) {
        this.stallBeans = stallBeans;
    }

    public Stall getStall() {
        return this.stall;
    }

    public void setStall(Stall stall) {
        this.stall = stall;
    }

    public PackRecord getPackRecord() {
        return this.packRecord;
    }

    public void setPackRecord(PackRecord packRecord) {
        this.packRecord = packRecord;
    }

    public RoleSystem getRoleSystem() {
        return this.roleSystem;
    }

    public void setRoleSystem(RoleSystem roleSystem) {
        this.roleSystem = roleSystem;
    }

    public LoginResult getLoginResult() {
        return this.loginResult;
    }

    public void setLoginResult(LoginResult loginResult) {
        this.loginResult = loginResult;
    }

    public PrivateData getPrivateData() {
        return this.privateData;
    }

    public void setPrivateData(PrivateData privateData) {
        this.privateData = privateData;
    }

    public List<Pal> getPals() {
        return this.pals;
    }

    public void setPals(List<Pal> pals) {
        this.pals = pals;
    }

    public RoleAttribute getRoleAttribute() {
        return this.roleAttribute;
    }

    public void setRoleAttribute(RoleAttribute roleAttribute) {
        this.roleAttribute = roleAttribute;
    }

    public List<org.come.entity.Fly> getFlys() {
        return this.flys;
    }

    public void setFlys(List<org.come.entity.Fly> flys) {
        this.flys = flys;
    }

    public List<RoleSummoning> getRoleDepositSummonings() {
        return this.roleDepositSummonings;
    }

    public void setRoleDepositSummonings(List<RoleSummoning> roleDepositSummonings) {
        this.roleDepositSummonings = roleDepositSummonings;
    }
    private List<XuanBao> xuanBaos;
    public List<XuanBao> getXuanBaos() {
        return this.xuanBaos;
    }

    public void setXuanBaos(List<XuanBao> xuanBaos) {
        this.xuanBaos = xuanBaos;
    }
}
