package org.come.entity;

import org.come.bean.LoginResult;

public class GangGiveMoneyBean
{
    private LoginResult loginResult;
    private Gang gang;
    
    public Gang getGang() {
        return this.gang;
    }
    
    public void setGang(Gang gang) {
        this.gang = gang;
    }
    
    public LoginResult getLoginResult() {
        return this.loginResult;
    }
    
    public void setLoginResult(LoginResult loginResult) {
        this.loginResult = loginResult;
    }
}
