package org.come.entity;

import java.math.BigDecimal;

/**
 * 服务端坐骑技能实体。
 */
public class MountSkill
{
    private BigDecimal skillid;
    private String skillname;
    private BigDecimal mid;
    
    public BigDecimal getSkillid() {
        return this.skillid;
    }

    /** 语义化别名：技能 ID。 */
    public BigDecimal getSkillId() {
        return this.skillid;
    }
    
    public void setSkillid(BigDecimal skillid) {
        this.skillid = skillid;
    }

    public void setSkillId(BigDecimal skillId) {
        this.skillid = skillId;
    }
    
    public String getSkillname() {
        return this.skillname;
    }
    
    public void setSkillname(String skillname) {
        this.skillname = skillname;
    }
    
    public BigDecimal getMid() {
        return this.mid;
    }

    /** 语义化别名：坐骑 ID。 */
    public BigDecimal getMountId() {
        return this.mid;
    }
    
    public void setMid(BigDecimal mid) {
        this.mid = mid;
    }

    public void setMountId(BigDecimal mountId) {
        this.mid = mountId;
    }
}
