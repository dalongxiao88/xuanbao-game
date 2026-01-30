package come.tool.Scene.LaborDay;

import java.util.List;

public class LaborRank
{
    private int type;
    private List<LaborRole> roles;
    private LaborRole role;
    private int rank;
    private String value;
    private String value1;
    
    public LaborRank(int type) {
        this.type = type;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public List<LaborRole> getRoles() {
        return this.roles;
    }
    
    public void setRoles(List<LaborRole> roles) {
        this.roles = roles;
    }
    
    public LaborRole getRole() {
        return this.role;
    }
    
    public void setRole(LaborRole role) {
        this.role = role;
    }
    
    public int getRank() {
        return this.rank;
    }
    
    public void setRank(int rank) {
        this.rank = rank;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public String getValue1() {
        return this.value1;
    }
    
    public void setValue1(String value1) {
        this.value1 = value1;
    }
}
