package org.come.entity;

import java.math.BigDecimal;

public class Friend
{
    private BigDecimal fid;
    private BigDecimal roleid;
    private BigDecimal friendid;
    
    public BigDecimal getFid() {
        return this.fid;
    }
    
    public void setFid(BigDecimal fid) {
        this.fid = fid;
    }
    
    public BigDecimal getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }
    
    public BigDecimal getFriendid() {
        return this.friendid;
    }
    
    public void setFriendid(BigDecimal friendid) {
        this.friendid = friendid;
    }
}
