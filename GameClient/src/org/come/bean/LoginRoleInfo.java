package org.come.bean;

import java.math.BigDecimal;

public class LoginRoleInfo
{
    private BigDecimal areaId;
    private String atId;
    private String areaName;
    private String account;
    private String password;
    private BigDecimal roleId;
    private String roleName;
    private Integer roleLvl;
    private BigDecimal raceId;
    private String raceName;
    private String roleIp;
    private int rolePort;
    
    public boolean contrast(LoginRoleInfo loginRoleInfo) {
        return this.account != null && loginRoleInfo.account != null && this.account.equals(loginRoleInfo.account) && this.password != null && loginRoleInfo.password != null && this.password.equals(loginRoleInfo.password) && this.roleId != null && loginRoleInfo.roleId != null && this.roleId.equals(loginRoleInfo.roleId);
    }
    
    public BigDecimal getAreaId() {
        return this.areaId;
    }
    
    public void setAreaId(BigDecimal areaId) {
        this.areaId = areaId;
    }
    
    public String getAtId() {
        return this.atId;
    }
    
    public void setAtId(String atId) {
        this.atId = atId;
    }
    
    public String getAreaName() {
        return this.areaName;
    }
    
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    
    public String getAccount() {
        return this.account;
    }
    
    public void setAccount(String account) {
        this.account = account;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public BigDecimal getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(BigDecimal roleId) {
        this.roleId = roleId;
    }
    
    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public Integer getRoleLvl() {
        return this.roleLvl;
    }
    
    public void setRoleLvl(Integer roleLvl) {
        this.roleLvl = roleLvl;
    }
    
    public BigDecimal getRaceId() {
        return this.raceId;
    }
    
    public void setRaceId(BigDecimal raceId) {
        this.raceId = raceId;
    }
    
    public String getRaceName() {
        return this.raceName;
    }
    
    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }
    
    public String getRoleIp() {
        return this.roleIp;
    }
    
    public void setRoleIp(String roleIp) {
        this.roleIp = roleIp;
    }
    
    public int getRolePort() {
        return this.rolePort;
    }
    
    public void setRolePort(int rolePort) {
        this.rolePort = rolePort;
    }
}
