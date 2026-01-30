package come.tool.newTeam;

import org.come.bean.LoginResult;
import java.math.BigDecimal;

public class TeamRole
{
    private BigDecimal roleId;
    private String name;
    private BigDecimal speciesId;
    private int grade;
    private int state;
    private int txk;
    
    public TeamRole() {
    }
    
    public TeamRole(LoginResult loginResult) {
        this.roleId = loginResult.getRole_id();
        this.name = loginResult.getRolename();
        this.speciesId = loginResult.getSpecies_id();
        this.grade = (int)loginResult.getGrade();
        this.state = 0;
        this.txk = 0;
    }
    
    public void upTeamRole(LoginResult loginResult) {
        this.name = loginResult.getRolename();
        this.speciesId = loginResult.getSpecies_id();
        this.grade = (int)loginResult.getGrade();
        this.txk = loginResult.getRoleData().getPackRecord().getPutTXKid();
    }
    
    public BigDecimal getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(BigDecimal roleId) {
        this.roleId = roleId;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public BigDecimal getSpeciesId() {
        return this.speciesId;
    }
    
    public void setSpeciesId(BigDecimal speciesId) {
        this.speciesId = speciesId;
    }
    
    public int getGrade() {
        return this.grade;
    }
    
    public void setGrade(int grade) {
        this.grade = grade;
    }
    
    public int getState() {
        return this.state;
    }
    
    public void setState(int state) {
        this.state = state;
    }
    
    public int getTxk() {
        return this.txk;
    }
    
    public void setTxk(int txk) {
        this.txk = txk;
    }
}
