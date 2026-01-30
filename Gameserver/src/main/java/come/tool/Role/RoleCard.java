package come.tool.Role;

import java.math.BigDecimal;

public class RoleCard
{
    private BigDecimal roleId;
    private String roleName;
    private String userName;
    
    public RoleCard(BigDecimal roleId, String roleName, String userName) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.userName = userName;
    }
    
    public BigDecimal getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(BigDecimal roleId) {
        this.roleId = roleId;
    }
    
    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
