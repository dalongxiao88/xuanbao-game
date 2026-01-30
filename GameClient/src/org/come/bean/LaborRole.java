package org.come.bean;

import java.math.BigDecimal;

public class LaborRole
{
    private BigDecimal roleId;
    private String name;
    private int lvl;
    private int cz;
    private int cj;
    
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
    
    public int getLvl() {
        return this.lvl;
    }
    
    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
    
    public int getCz() {
        return this.cz;
    }
    
    public void setCz(int cz) {
        this.cz = cz;
    }
    
    public int getCj() {
        return this.cj;
    }
    
    public void setCj(int cj) {
        this.cj = cj;
    }
}
