package org.come.entity;

import java.util.List;
import java.math.BigDecimal;

public class Mount implements Cloneable
{
    private BigDecimal mid;
    private Integer mountid;
    private String mountname;
    private Integer mountlvl;
    private Integer live;
    private Integer spri;
    private Integer power;
    private Integer bone;
    private Integer exp;
    private BigDecimal roleid;
    private BigDecimal sid;
    private BigDecimal othrersid;
    private Integer gradeexp;
    List<MountSkill> mountskill;
    private Integer useNumber;
    private Integer Proficiency;
    private Integer moveGrade;
    private BigDecimal sid3;
    private BigDecimal sid4;
    private BigDecimal sid5;
    private int shouhu;
    private int sh;
    
    public Mount() {
        this.shouhu = 0;
        this.sh = 0;
    }
    
    public BigDecimal getMid() {
        return this.mid;
    }
    
    public void setMid(BigDecimal mid) {
        this.mid = mid;
    }
    
    public Integer getMountid() {
        return this.mountid;
    }
    
    public void setMountid(Integer mountid) {
        this.mountid = mountid;
    }
    
    public String getMountname() {
        return this.mountname;
    }
    
    public void setMountname(String mountname) {
        this.mountname = mountname;
    }
    
    public Integer getMountlvl() {
        return this.mountlvl;
    }
    
    public void setMountlvl(Integer mountlvl) {
        this.mountlvl = mountlvl;
    }
    
    public Integer getLive() {
        return this.live;
    }
    
    public void setLive(Integer live) {
        this.live = live;
    }
    
    public Integer getSpri() {
        return this.spri;
    }
    
    public void setSpri(Integer spri) {
        this.spri = spri;
    }
    
    public Integer getPower() {
        return this.power;
    }
    
    public void setPower(Integer power) {
        this.power = power;
    }
    
    public Integer getBone() {
        return this.bone;
    }
    
    public void setBone(Integer bone) {
        this.bone = bone;
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
    
    public BigDecimal getSid() {
        return this.sid;
    }
    
    public void setSid(BigDecimal sid) {
        this.sid = sid;
    }
    
    public BigDecimal getOthrersid() {
        return this.othrersid;
    }
    
    public void setOthrersid(BigDecimal othrersid) {
        this.othrersid = othrersid;
    }
    
    public Integer getGradeexp() {
        return this.gradeexp;
    }
    
    public void setGradeexp(Integer gradeexp) {
        this.gradeexp = gradeexp;
    }
    
    public List<MountSkill> getMountskill() {
        return this.mountskill;
    }
    
    public void setMountskill(List<MountSkill> mountskill) {
        this.mountskill = mountskill;
    }
    
    public Integer getUseNumber() {
        return this.useNumber;
    }
    
    public void setUseNumber(Integer useNumber) {
        this.useNumber = useNumber;
    }
    
    public Integer getProficiency() {
        return this.Proficiency;
    }
    
    public void setProficiency(Integer proficiency) {
        this.Proficiency = proficiency;
    }
    
    public BigDecimal getSid3() {
        return this.sid3;
    }
    
    public void setSid3(BigDecimal sid3) {
        this.sid3 = sid3;
    }
    
    public BigDecimal getSid4() {
        return this.sid4;
    }
    
    public void setSid4(BigDecimal sid4) {
        this.sid4 = sid4;
    }
    
    public BigDecimal getSid5() {
        return this.sid5;
    }
    
    public void setSid5(BigDecimal sid5) {
        this.sid5 = sid5;
    }
    
    public Integer getMoveGrade() {
        return this.moveGrade;
    }
    
    public void setMoveGrade(Integer moveGrade) {
        this.moveGrade = moveGrade;
    }
    
    public int getShouhu() {
        return this.shouhu;
    }
    
    public void setShouhu(int shouhu) {
        this.shouhu = shouhu;
    }
    
    public int getSh() {
        return this.sh;
    }
    
    public void setSh(int sh) {
        this.sh = sh;
    }
    
    public Mount clone() {
        try {
            return (Mount)super.clone();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
