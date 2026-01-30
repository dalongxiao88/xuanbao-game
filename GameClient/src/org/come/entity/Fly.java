package org.come.entity;

import java.math.BigDecimal;

public class Fly
{
    private BigDecimal mid;
    private Integer flytid;
    private String flyname;
    private Integer flystate;
    private Integer flylvl;
    private Integer exp;
    private BigDecimal roleid;
    private Integer gradeexp;
    private String skin;
    private Long fuel;
    
    public BigDecimal getMid() {
        return this.mid;
    }
    
    public void setMid(BigDecimal mid) {
        this.mid = mid;
    }
    
    public Integer getFlytid() {
        return this.flytid;
    }
    
    public void setFlytid(Integer flytid) {
        this.flytid = flytid;
    }
    
    public String getFlyname() {
        return this.flyname;
    }
    
    public void setFlyname(String flyname) {
        this.flyname = flyname;
    }
    
    public Integer getFlylvl() {
        return this.flylvl;
    }
    
    public void setFlylvl(Integer flylvl) {
        this.flylvl = flylvl;
    }
    
    public Integer getExp() {
        return this.exp;
    }
    
    public void setExp(Integer exp) {
        this.exp = exp;
    }
    
    public BigDecimal getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }
    
    public Integer getFlylvlString() {
        return this.flylvl;
    }
    
    public Integer getFlystate() {
        return this.flystate;
    }
    
    public void setFlystate(Integer flystate) {
        this.flystate = flystate;
    }
    
    public Integer getGradeexp() {
        return this.gradeexp;
    }
    
    public void setGradeexp(Integer gradeexp) {
        this.gradeexp = gradeexp;
    }
    
    public String getflyskin() {
        return this.skin;
    }
    
    public void setflyskin(String skin) {
        this.skin = skin;
    }
    
    public Fly clone() {
        try {
            return (Fly)super.clone();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public String getSkin() {
        return this.skin;
    }
    
    public void setSkin(String skin) {
        this.skin = skin;
    }
    
    public Long getFuel() {
        if (this.fuel == null) {
            this.fuel = Long.valueOf(0L);
        }
        return this.fuel;
    }
    
    public void setFuel(Long fuel) {
        this.fuel = fuel;
    }
}
