package org.come.bean;

public class LoginUserBean
{
    private String username;
    private String password;
    private String serverMeString;
    
    public String getServerMeString() {
        return this.serverMeString;
    }
    
    public void setServerMeString(String serverMeString) {
        this.serverMeString = serverMeString;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
