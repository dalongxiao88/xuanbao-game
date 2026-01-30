package org.come.entity;

import java.math.BigDecimal;

public class FlyRoleUser
{
    private BigDecimal mid;
    private BigDecimal flyid;
    private String flyname;
    private String flylvl;
    private String exp;
    private BigDecimal roleid;
    private String rolename;
    private BigDecimal user_id;
    private String username;
    private Integer start;
    private Integer end;
    private Integer pageNum;
    private String orderBy;
    
    public BigDecimal getMid() {
        return this.mid;
    }
    
    public void setMid(BigDecimal mid) {
        this.mid = mid;
    }
    
    public BigDecimal getFlyid() {
        return this.flyid;
    }
    
    public void setFlyid(BigDecimal flyid) {
        this.flyid = flyid;
    }
    
    public String getFlyname() {
        return this.flyname;
    }
    
    public void setFlyname(String flyname) {
        this.flyname = flyname;
    }
    
    public String getFlylvl() {
        return this.flylvl;
    }
    
    public void setFlylvl(String flylvl) {
        this.flylvl = flylvl;
    }
    
    public String getExp() {
        return this.exp;
    }
    
    public void setExp(String exp) {
        this.exp = exp;
    }
    
    public BigDecimal getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }
    
    public String getRolename() {
        return this.rolename;
    }
    
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    
    public BigDecimal getUser_id() {
        return this.user_id;
    }
    
    public void setUser_id(BigDecimal user_id) {
        this.user_id = user_id;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public Integer getStart() {
        return this.start;
    }
    
    public void setStart(Integer start) {
        this.start = start;
    }
    
    public Integer getEnd() {
        return this.end;
    }
    
    public void setEnd(Integer end) {
        this.end = end;
    }
    
    public Integer getPageNum() {
        return this.pageNum;
    }
    
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
    
    public String getOrderBy() {
        return this.orderBy;
    }
    
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
