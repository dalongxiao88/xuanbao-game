
package org.come.entity;

public class XuanBaoSkill {
    int skillid;
    String skill1;
    String skill2;
    String skill3;
    String skill4;

    public XuanBaoSkill(int skillid, String skill1, String skill2, String skill3, String skill4) {
        this.skillid = skillid;
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.skill3 = skill3;
        this.skill4 = skill4;
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
}
