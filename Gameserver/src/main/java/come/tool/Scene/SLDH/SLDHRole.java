package come.tool.Scene.SLDH;

import come.tool.Role.RoleShow;
import java.math.BigDecimal;

public class SLDHRole
{
    private BigDecimal roleID;
    private RoleShow roleShow;
    private SLDHTeam sldhTeam;
    private int I;
    
    public SLDHRole(BigDecimal roleID, RoleShow roleShow) {
        this.roleID = roleID;
        this.roleShow = roleShow;
        this.I = 0;
    }
    
    public BigDecimal getRoleID() {
        return this.roleID;
    }
    
    public void setRoleID(BigDecimal roleID) {
        this.roleID = roleID;
    }
    
    public RoleShow getRoleShow() {
        return this.roleShow;
    }
    
    public void setRoleShow(RoleShow roleShow) {
        this.roleShow = roleShow;
    }
    
    public SLDHTeam getSldhTeam() {
        return this.sldhTeam;
    }
    
    public void setSldhTeam(SLDHTeam sldhTeam) {
        this.sldhTeam = sldhTeam;
    }
    
    public int getI() {
        return this.I;
    }
    
    public void setI(int i) {
        this.I = i;
    }
}
