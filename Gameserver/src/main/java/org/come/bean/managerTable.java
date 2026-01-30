package org.come.bean;

import java.util.Date;
import java.math.BigDecimal;

public class managerTable
{
    private BigDecimal manaeid;
    private String username;
    private String pwd;
    private String relname;
    private Date createtime;
    private Integer flag;
    private int controlStyle;
    private int pageNumber;
    private String newname;
    private String newpwd;
    
    public int getPageNumber() {
        return this.pageNumber;
    }
    
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
    
    public int getControlStyle() {
        return this.controlStyle;
    }
    
    public void setControlStyle(int controlStyle) {
        this.controlStyle = controlStyle;
    }
    
    public Integer getFlag() {
        return this.flag;
    }
    
    public void setFlag(Integer flag) {
        this.flag = flag;
    }
    
    public BigDecimal getManaeid() {
        return this.manaeid;
    }
    
    public void setManaeid(BigDecimal manaeid) {
        this.manaeid = manaeid;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = ((username == null) ? null : username.trim());
    }
    
    public String getPwd() {
        return this.pwd;
    }
    
    public void setPwd(String pwd) {
        this.pwd = ((pwd == null) ? null : pwd.trim());
    }
    
    public String getRelname() {
        return this.relname;
    }
    
    public void setRelname(String relname) {
        this.relname = ((relname == null) ? null : relname.trim());
    }
    
    public Date getCreatetime() {
        return this.createtime;
    }
    
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    
    public String getNewname() {
        return this.newname;
    }
    
    public void setNewname(String newname) {
        this.newname = newname;
    }
    
    public String getNewpwd() {
        return this.newpwd;
    }
    
    public void setNewpwd(String newpwd) {
        this.newpwd = newpwd;
    }
}
