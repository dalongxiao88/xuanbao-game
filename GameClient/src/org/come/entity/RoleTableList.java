package org.come.entity;

import java.util.List;

public class RoleTableList
{
    private List<RoleTableNew> roleList;
    private String userid;
    private String atid;
    private String username;
    private String pasw;
    
    public List<RoleTableNew> getRoleList() {
        return this.roleList;
    }
    
    public void setRoleList(List<RoleTableNew> roleList) {
        this.roleList = roleList;
    }
    
    public String getUserid() {
        return this.userid;
    }
    
    public void setUserid(String userid) {
        this.userid = userid;
    }
    
    public String getAtid() {
        return this.atid;
    }
    
    public void setAtid(String atid) {
        this.atid = atid;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPasw() {
        return this.pasw;
    }
    
    public void setPasw(String pasw) {
        this.pasw = pasw;
    }
}
