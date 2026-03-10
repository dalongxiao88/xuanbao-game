package org.come.entity;

import java.math.BigDecimal;

/**
 * 客户端飞行器实体。
 */
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

    /** 语义化别名：飞行器模板 ID。 */
    public Integer getFlyTypeId() {
        return this.flytid;
    }
    
    public void setFlytid(Integer flytid) {
        this.flytid = flytid;
    }

    public void setFlyTypeId(Integer flyTypeId) {
        this.flytid = flyTypeId;
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

    /** 语义化别名：飞行器等级。 */
    public Integer getFlyLevel() {
        return this.flylvl;
    }
    
    public void setFlylvl(Integer flylvl) {
        this.flylvl = flylvl;
    }

    public void setFlyLevel(Integer flyLevel) {
        this.flylvl = flyLevel;
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

    /** 兼容旧命名：返回飞行器等级。 */
    public Integer getFlyLevelValue() {
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

    /** 语义化别名：飞行器皮肤。 */
    public String getFlySkin() {
        return this.skin;
    }
    
    public void setflyskin(String skin) {
        this.skin = skin;
    }

    public void setFlySkin(String skin) {
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
