package org.come.entity;

import java.math.BigDecimal;

public class RoleSkill
{
    private BigDecimal skiid;
    private BigDecimal roleid;
    private Integer skillid;
    private String skillname;
    private Integer skilltype;
    private Integer skilllevel;
    private Double grow;
    private Integer dielectric;
    private Double value;
    private Integer camp;
    private String skillralation;
    private String remark;
    private Integer skilled;
    
    public RoleSkill() {
        this.skilled = Integer.valueOf(0);
    }
    
    public BigDecimal getSkiid() {
        return this.skiid;
    }
    
    public void setSkiid(BigDecimal skiid) {
        this.skiid = skiid;
    }
    
    public BigDecimal getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }
    
    public Integer getSkillid() {
        return this.skillid;
    }
    
    public void setSkillid(Integer skillid) {
        this.skillid = skillid;
    }
    
    public String getSkillname() {
        return this.skillname;
    }
    
    public void setSkillname(String skillname) {
        this.skillname = skillname;
    }
    
    public Integer getSkilltype() {
        return this.skilltype;
    }
    
    public void setSkilltype(Integer skilltype) {
        this.skilltype = skilltype;
    }
    
    public Integer getSkilllevel() {
        return this.skilllevel;
    }
    
    public void setSkilllevel(Integer skilllevel) {
        this.skilllevel = skilllevel;
    }
    
    public Integer getDielectric() {
        return this.dielectric;
    }
    
    public void setDielectric(Integer dielectric) {
        this.dielectric = dielectric;
    }
    
    public Integer getCamp() {
        return this.camp;
    }
    
    public void setCamp(Integer camp) {
        this.camp = camp;
    }
    
    public String getSkillralation() {
        return this.skillralation;
    }
    
    public void setSkillralation(String skillralation) {
        this.skillralation = skillralation;
    }
    
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public Integer getSkilled() {
        return this.skilled;
    }
    
    public void setSkilled(Integer skilled) {
        this.skilled = skilled;
    }
    
    public Double getGrow() {
        return this.grow;
    }
    
    public void setGrow(Double grow) {
        this.grow = grow;
    }
    
    public Double getValue() {
        return this.value;
    }
    
    public void setValue(Double value) {
        this.value = value;
    }
}
