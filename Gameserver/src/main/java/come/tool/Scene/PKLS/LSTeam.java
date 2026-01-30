package come.tool.Scene.PKLS;

import java.math.BigDecimal;

public class LSTeam
{
    private BigDecimal[] roleids;
    
    public LSTeam(BigDecimal[] roleids) {
        this.roleids = roleids;
    }
    
    public boolean contains(BigDecimal roleid) {
        for (int i = 0; i < this.roleids.length; ++i) {
            if (this.roleids[i].compareTo(roleid) == 0) {
                return true;
            }
        }
        return false;
    }
    
    public BigDecimal[] getRoleids() {
        return this.roleids;
    }
    
    public void setRoleids(BigDecimal[] roleids) {
        this.roleids = roleids;
    }
}
