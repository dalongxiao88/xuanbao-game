package come.tool.Scene.RC;

import java.math.BigDecimal;

public class RCRole
{
    private BigDecimal Id;
    private String role;
    private boolean isBB;
    private int state;
    private int advance;
    
    public RCRole(BigDecimal id, String role) {
        this.Id = id;
        this.role = role;
        this.state = 0;
        this.advance = 0;
        this.isBB = false;
    }
    
    public BigDecimal getId() {
        return this.Id;
    }
    
    public void setId(BigDecimal id) {
        this.Id = id;
    }
    
    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public int getState() {
        return this.state;
    }
    
    public void setState(int state) {
        this.state = state;
    }
    
    public int getAdvance() {
        return this.advance;
    }
    
    public void setAdvance(int advance) {
        this.advance = advance;
    }
    
    public boolean isBB() {
        return this.isBB;
    }
    
    public void setBB(boolean isBB) {
        this.isBB = isBB;
    }
}
