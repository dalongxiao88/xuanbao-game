package org.come.entity;

import java.math.BigDecimal;

/**
 * 客户端好友关系实体。
 */
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

    /** 语义化别名：角色 ID。 */
    public BigDecimal getRoleId() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }

    public void setRoleId(BigDecimal roleId) {
        this.roleid = roleId;
    }
    
    public BigDecimal getFriendid() {
        return this.friendid;
    }

    /** 语义化别名：好友角色 ID。 */
    public BigDecimal getFriendRoleId() {
        return this.friendid;
    }
    
    public void setFriendid(BigDecimal friendid) {
        this.friendid = friendid;
    }

    public void setFriendRoleId(BigDecimal friendRoleId) {
        this.friendid = friendRoleId;
    }
}
