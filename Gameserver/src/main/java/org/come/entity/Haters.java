package org.come.entity;

import java.math.BigDecimal;

public class Haters
{
    private BigDecimal roleid;
    private String unknown;
    
    public BigDecimal getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }
    
    public String getUnknown() {
        return this.unknown;
    }
    
    public void setUnknown(String unknown) {
        this.unknown = ((unknown == null) ? null : unknown.trim());
    }
}
