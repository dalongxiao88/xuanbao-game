package org.come.entity;

import java.math.BigDecimal;

public class BattleRole
{
    private BigDecimal roleid;
    private String rolename;
    private Integer teamid;
    private String property;
    private String petproperty;
    private String babyproperty;
    private String lingbaoproperty;
    private long starttime;
    
    public BigDecimal getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }
    
    public String getRolename() {
        return this.rolename;
    }
    
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    
    public Integer getTeamid() {
        return this.teamid;
    }
    
    public void setTeamid(int teamid) {
        this.teamid = Integer.valueOf(teamid);
    }
    
    public String getProperty() {
        return this.property;
    }
    
    public void setProperty(String property) {
        this.property = property;
    }
    
    public String getPetproperty() {
        return this.petproperty;
    }
    
    public void setPetproperty(String petproperty) {
        this.petproperty = petproperty;
    }
    
    public String getBabyproperty() {
        return this.babyproperty;
    }
    
    public void setBabyproperty(String babyproperty) {
        this.babyproperty = babyproperty;
    }
    
    public String getLingbaoproperty() {
        return this.lingbaoproperty;
    }
    
    public void setLingbaoproperty(String lingbaoproperty) {
        this.lingbaoproperty = lingbaoproperty;
    }
    
    public long getStarttime() {
        return this.starttime;
    }
    
    public void setStarttime(long starttime) {
        this.starttime = starttime;
    }
}
