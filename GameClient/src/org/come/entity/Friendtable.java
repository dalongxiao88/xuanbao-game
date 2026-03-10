package org.come.entity;

import java.math.BigDecimal;

/**
 * 客户端好友展示对象。
 */
public class Friendtable
{
    private BigDecimal roleid;
    private BigDecimal role_id;
    private String rolename;
    private String race_name;
    private BigDecimal grade;
    private int OnlineState;
    private BigDecimal species_id;
    
    public BigDecimal getRoleid() {
        return this.roleid;
    }

    /** 语义化别名：好友角色 ID。 */
    public BigDecimal getFriendRoleId() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }

    public void setFriendRoleId(BigDecimal friendRoleId) {
        this.roleid = friendRoleId;
    }
    
    public BigDecimal getRole_id() {
        return this.role_id;
    }

    /** 语义化别名：当前角色 ID。 */
    public BigDecimal getOwnerRoleId() {
        return this.role_id;
    }
    
    public void setRole_id(BigDecimal role_id) {
        this.role_id = role_id;
    }

    public void setOwnerRoleId(BigDecimal ownerRoleId) {
        this.role_id = ownerRoleId;
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
    
    public int getOnlineState() {
        return this.OnlineState;
    }
    
    public void setOnlineState(int onlineState) {
        this.OnlineState = onlineState;
    }
    
    public BigDecimal getSpecies_id() {
        return this.species_id;
    }

    /** 语义化别名：种族 ID。 */
    public BigDecimal getSpeciesId() {
        return this.species_id;
    }
    
    public void setSpecies_id(BigDecimal species_id) {
        this.species_id = species_id;
    }

    public void setSpeciesId(BigDecimal speciesId) {
        this.species_id = speciesId;
    }
}
