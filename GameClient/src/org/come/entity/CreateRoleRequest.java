package org.come.entity;

import java.math.BigDecimal;

public class CreateRoleRequest
{
    private BigDecimal user_id;
    private BigDecimal species_id;
    private Integer ServerMeString;
    private String rolename;
    
    public BigDecimal getUser_id() {
        return this.user_id;
    }
    
    public void setUser_id(BigDecimal user_id) {
        this.user_id = user_id;
    }
    
    public BigDecimal getSpecies_id() {
        return this.species_id;
    }
    
    public void setSpecies_id(BigDecimal species_id) {
        this.species_id = species_id;
    }
    
    public Integer getServerMeString() {
        return this.ServerMeString;
    }
    
    public void setServerMeString(Integer serverMeString) {
        this.ServerMeString = serverMeString;
    }
    
    public String getRolename() {
        return this.rolename;
    }
    
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
