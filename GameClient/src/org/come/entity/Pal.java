package org.come.entity;

import java.util.List;
import org.come.model.Lingbao;
import java.math.BigDecimal;

/**
 * 客户端守护对象实体。
 */
public class Pal
{
    private BigDecimal id;
    private int pId;
    private double grow;
    private long exp;
    private String parts;
    private BigDecimal roleId;
    private RoleSummoning summoning;
    private Lingbao lingbao;
    private Baby baby;
    private String palPetSkill;
    private List<String> palSkillAI;
    
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    public double getGrow() {
        return this.grow;
    }
    
    public void setGrow(double grow) {
        this.grow = grow;
    }
    
    public long getExp() {
        return this.exp;
    }
    
    public void setExp(long exp) {
        this.exp = exp;
    }
    
    public String getParts() {
        return this.parts;
    }
    
    public void setParts(String parts) {
        this.parts = parts;
    }
    
    public int getpId() {
        return this.pId;
    }

    /** 语义化别名：守护类型 ID。 */
    public int getPalTypeId() {
        return this.pId;
    }
    
    public void setpId(int pId) {
        this.pId = pId;
    }

    public void setPalTypeId(int palTypeId) {
        this.pId = palTypeId;
    }
    
    public BigDecimal getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(BigDecimal roleId) {
        this.roleId = roleId;
    }
    
    public RoleSummoning getSummoning() {
        return this.summoning;
    }
    
    public void setSummoning(RoleSummoning summoning) {
        this.summoning = summoning;
    }
    
    public Lingbao getLingbao() {
        return this.lingbao;
    }
    
    public void setLingbao(Lingbao lingbao) {
        this.lingbao = lingbao;
    }
    
    public Baby getBaby() {
        return this.baby;
    }
    
    public void setBaby(Baby baby) {
        this.baby = baby;
    }
    
    public String getPalPetSkill() {
        return this.palPetSkill;
    }

    /** 语义化别名：守护技能串。 */
    public String getPalSkillConfig() {
        return this.palPetSkill;
    }
    
    public void setPalPetSkill(String palPetSkill) {
        this.palPetSkill = palPetSkill;
    }

    public void setPalSkillConfig(String palSkillConfig) {
        this.palPetSkill = palSkillConfig;
    }
    
    public List<String> getPalSkillAI() {
        return this.palSkillAI;
    }
    
    public void setPalSkillAI(List<String> palSkillAI) {
        this.palSkillAI = palSkillAI;
    }
}
