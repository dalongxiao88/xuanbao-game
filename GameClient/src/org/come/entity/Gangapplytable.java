package org.come.entity;

import java.math.BigDecimal;

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
    
    public void setRole_id(BigDecimal role_id) {
        this.role_id = role_id;
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
    
    public void setRace_name(String race_name) {
        this.race_name = race_name;
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
    
    public void setGangid(BigDecimal gangid) {
        this.gangid = gangid;
    }
}
