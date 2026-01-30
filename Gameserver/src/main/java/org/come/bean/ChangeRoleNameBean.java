package org.come.bean;

import java.math.BigDecimal;

public class ChangeRoleNameBean
{
    private String oldName;
    private String newName;
    private boolean flag;
    private BigDecimal rgid;
    
    public String getOldName() {
        return this.oldName;
    }
    
    public void setOldName(String oldName) {
        this.oldName = oldName;
    }
    
    public String getNewName() {
        return this.newName;
    }
    
    public void setNewName(String newName) {
        this.newName = newName;
    }
    
    public boolean isFlag() {
        return this.flag;
    }
    
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    
    public BigDecimal getRgid() {
        return this.rgid;
    }
    
    public void setRgid(BigDecimal rgid) {
        this.rgid = rgid;
    }
}
