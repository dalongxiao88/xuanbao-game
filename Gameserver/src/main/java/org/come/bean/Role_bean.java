package org.come.bean;

import java.math.BigDecimal;

public class Role_bean
{
    private BigDecimal role_id;
    private String rolename;
    private Integer grade;
    private String race_name;
    private String title;
    private String gangname;
    private int turnAround;
    private BigDecimal speciesId;
    private String skin;
    
    public String getGangname() {
        return this.gangname;
    }
    
    public void setGangname(String gangname) {
        this.gangname = gangname;
    }
    
    public BigDecimal getRole_id() {
        return this.role_id;
    }
    
    public void setRole_id(BigDecimal role_id) {
        this.role_id = role_id;
    }
    
    public Integer getGrade() {
        return this.grade;
    }
    
    public void setGrade(Integer grade) {
        this.grade = grade;
    }
    
    public String getRace_name() {
        return this.race_name;
    }
    
    public void setRace_name(String race_name) {
        this.race_name = race_name;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getRolename() {
        return this.rolename;
    }
    
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    
    public String getSkin() {
        return this.skin;
    }
    
    public void setSkin(String skin) {
        this.skin = skin;
    }
    
    public int getTurnAround() {
        return this.turnAround;
    }
    
    public void setTurnAround(int turnAround) {
        this.turnAround = turnAround;
    }
    
    public BigDecimal getSpeciesId() {
        return this.speciesId;
    }
    
    public void setSpeciesId(BigDecimal speciesId) {
        this.speciesId = speciesId;
    }
}
