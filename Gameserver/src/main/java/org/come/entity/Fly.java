package org.come.entity;

import java.util.List;
import java.math.BigDecimal;

public class Fly implements Cloneable
{
    private BigDecimal mid;
    private Integer flytid;
    private String flyname;
    private Integer flystate;
    private Integer flylvl;
    private Integer exp;
    private BigDecimal roleid;
    private Integer skin;
    private Integer gradeexp;
    List<FlySkill> flyskill;
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
    
    public void setFlytid(Integer mountid) {
        this.flytid = mountid;
    }
    
    public String getFlyname() {
        return this.flyname;
    }
    
    public void setFlyname(String mountname) {
        this.flyname = mountname;
    }
    
    public Integer getFlylvl() {
        return this.flylvl;
    }
    
    public void setFlylvl(Integer mountlvl) {
        this.flylvl = mountlvl;
    }
    
    public Integer getExp() {
        return this.exp;
    }
    
    public void setExp(Integer exp) {
        this.exp = exp;
    }
    
    public Integer getSkin() {
        return this.skin;
    }
    
    public void setSkin(Integer skin) {
        this.skin = skin;
    }
    
    public BigDecimal getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }
    
    public Integer getGradeexp() {
        return this.gradeexp;
    }
    
    public void setGradeexp(Integer gradeexp) {
        this.gradeexp = gradeexp;
    }
    
    public List<FlySkill> getFlyskill() {
        return this.flyskill;
    }
    
    public void setMountskill(List<FlySkill> flyskill) {
        this.flyskill = flyskill;
    }
    
    public Integer getFlystate() {
        return this.flystate;
    }
    
    public void setFlystate(Integer flystate) {
        this.flystate = flystate;
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
    
    public Long getFuel() {
        if (this.fuel == null) {
            this.fuel = Long.valueOf(0L);
        }
        return this.fuel;
    }
    
    public void setFuel(Long fuel) {
        this.fuel = fuel;
    }
    
    public void setFlyskill(List<FlySkill> flyskill) {
        this.flyskill = flyskill;
    }
}
