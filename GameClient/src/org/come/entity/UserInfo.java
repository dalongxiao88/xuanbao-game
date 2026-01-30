package org.come.entity;

public class UserInfo
{
    private Integer user_ID;
    private String user_name;
    private String user_pwd;
    private Integer role_ID;
    
    public Integer getUser_ID() {
        return this.user_ID;
    }
    
    public void setUser_ID(Integer user_ID) {
        this.user_ID = user_ID;
    }
    
    public String getUser_name() {
        return this.user_name;
    }
    
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    
    public String getUser_pwd() {
        return this.user_pwd;
    }
    
    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }
    
    public Integer getRole_ID() {
        return this.role_ID;
    }
    
    public void setRole_ID(Integer role_ID) {
        this.role_ID = role_ID;
    }
}
