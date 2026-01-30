package org.come.bean;

import java.math.BigDecimal;

public class TeamApplyBean
{
    private BigDecimal roleid;
    private LoginResult rolInfo;
    
    public BigDecimal getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }
    
    public LoginResult getRolInfo() {
        return this.rolInfo;
    }
    
    public void setRolInfo(LoginResult rolInfo) {
        this.rolInfo = rolInfo;
    }
}
