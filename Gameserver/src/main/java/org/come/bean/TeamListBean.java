package org.come.bean;

import java.util.List;

public class TeamListBean
{
    private LoginResult roleInfo;
    private List<LoginResult> teamRoles;
    
    public List<LoginResult> getTeamRoles() {
        return this.teamRoles;
    }
    
    public void setTeamRoles(List<LoginResult> teamRoles) {
        this.teamRoles = teamRoles;
    }
    
    public LoginResult getRoleInfo() {
        return this.roleInfo;
    }
    
    public void setRoleInfo(LoginResult roleInfo) {
        this.roleInfo = roleInfo;
    }
}
