package org.come.bean;

public class RegisterBean
{
    private String userName;
    private String password;
    private String safeCode;
    private long Idcard;
    
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getSafeCode() {
        return this.safeCode;
    }
    
    public void setSafeCode(String safeCode) {
        this.safeCode = safeCode;
    }
    
    public long getIdcard() {
        return this.Idcard;
    }
    
    public void setIdcard(long idcard) {
        this.Idcard = idcard;
    }
}
