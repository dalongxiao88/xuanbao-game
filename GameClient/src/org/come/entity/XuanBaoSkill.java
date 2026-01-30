
package org.come.entity;

public class XuanBaoSkill {
    int skillid;
    String skill1;
    String skill2;
    String skill3;
    String skill4;
    Integer xm;
    Integer type;
    Integer pz;

    public XuanBaoSkill(int skillid, String skill1, String skill2, String skill3, String skill4) {
        this.skillid = skillid;
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.skill3 = skill3;
        this.skill4 = skill4;
    }

    public XuanBaoSkill(int skillid, String skill1, String skill2, String skill3, String skill4, Integer xm, Integer type, Integer pz) {
        this.skillid = skillid;
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.skill3 = skill3;
        this.skill4 = skill4;
        this.xm = xm;
        this.type = type;
        this.pz = pz;
    }

    public String getSkill1() {
        return this.skill1;
    }

    public void setSkill1(String skill1) {
        this.skill1 = skill1;
    }

    public String getSkill2() {
        return this.skill2;
    }

    public void setSkill2(String skill2) {
        this.skill2 = skill2;
    }

    public String getSkill3() {
        return this.skill3;
    }

    public void setSkill3(String skill3) {
        this.skill3 = skill3;
    }

    public String getSkill4() {
        return this.skill4;
    }

    public void setSkill4(String skill4) {
        this.skill4 = skill4;
    }

    public int getSkillid() {
        return skillid;
    }

    public void setSkillid(int skillid) {
        this.skillid = skillid;
    }

    public Integer getXm() {
        return xm;
    }

    public void setXm(Integer xm) {
        this.xm = xm;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPz() {
        return pz;
    }

    public void setPz(Integer pz) {
        this.pz = pz;
    }
}
