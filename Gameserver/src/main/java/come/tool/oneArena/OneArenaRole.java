package come.tool.oneArena;

import org.come.bean.LoginResult;
import java.math.BigDecimal;

public class OneArenaRole
{
    private BigDecimal roleId;
    private int place;
    private String skin;
    private String name;
    private int lvl;
    
    public OneArenaRole() {
    }
    
    public OneArenaRole(LoginResult role) {
        this.roleId = role.getRole_id();
        this.skin = role.getSpecies_id().toString();
        this.name = role.getRolename();
        this.lvl = (int)role.getGrade();
    }
    
    public BigDecimal getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(BigDecimal roleId) {
        this.roleId = roleId;
    }
    
    public int getPlace() {
        return this.place;
    }
    
    public void setPlace(int place) {
        this.place = place;
    }
    
    public String getSkin() {
        return this.skin;
    }
    
    public void setSkin(String skin) {
        this.skin = skin;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getLvl() {
        return this.lvl;
    }
    
    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
}
