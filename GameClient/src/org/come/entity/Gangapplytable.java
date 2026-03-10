package org.come.entity;

import java.math.BigDecimal;

/**
 * 客户端帮派申请展示对象。
 */
public class Gangapplytable
{
    private BigDecimal role_id;
    private String rolename;
    private String race_name;
    private BigDecimal grade;
    private BigDecimal gangid;
    
    public BigDecimal getRole_id() {
        return this.role_id;
    }

    /** 语义化别名：申请人角色 ID。 */
    public BigDecimal getRoleId() {
        return this.role_id;
    }
    
    public void setRole_id(BigDecimal role_id) {
        this.role_id = role_id;
    }

    public void setRoleId(BigDecimal roleId) {
        this.role_id = roleId;
    }
    
    public String getRolename() {
        return this.rolename;
    }
    
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    
    public String getRace_name() {
        return this.race_name;
    }

    /** 语义化别名：种族名称。 */
    public String getRaceName() {
        return this.race_name;
    }
    
    public void setRace_name(String race_name) {
        this.race_name = race_name;
    }

    public void setRaceName(String raceName) {
        this.race_name = raceName;
    }
    
    public BigDecimal getGrade() {
        return this.grade;
    }
    
    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }
    
    public BigDecimal getGangid() {
        return this.gangid;
    }

    /** 语义化别名：帮派 ID。 */
    public BigDecimal getGangId() {
        return this.gangid;
    }
    
    public void setGangid(BigDecimal gangid) {
        this.gangid = gangid;
    }

    public void setGangId(BigDecimal gangId) {
        this.gangid = gangId;
    }
}
