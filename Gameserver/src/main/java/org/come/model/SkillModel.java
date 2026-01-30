package org.come.model;

public class SkillModel
{
    // 技能ID
    private String skillid;
    // 技能名称
    private String skillname;
    // 技能类型
    private String skilltype;
    // 技能等级
    private String skilllevel;
    // 成长
    private String grow;
    // 耗蓝介质
    private String dielectric;
    // 介值
    private String value;
    // 阵营
    private String camp;
    // 技能关系
    private String skillralation;
    private String grow1;
    private String value1;
    private String grow2;
    private String value2;
    private String remark2;
    private String value3;
    // 备注
    private String remark;
    public SkillModel(Skill skill) {
        // TODO Auto-generated constructor stub
        this.skillid=skill.getSkillid()+"";
        this.skillname=skill.getSkillname();
        this.skilltype=skill.getSkilltype()+"";
        this.skilllevel=skill.getSkilllevel()+"";
        this.grow=skill.getGrow()+"";
        this.dielectric=skill.getDielectric()+"";
        this.value=skill.getValue()+"";
        this.camp=skill.getCamp()+"";
        this.skillralation=skill.getSkillralation();
        this.value1 = skill.getValue1() + "";
        this.grow1 = skill.getGrow1() + "";
        this.value2 = skill.getValue2() + "";
        this.grow2 = skill.getGrow2() + "";
        this.remark=skill.getRemark();
        this.remark2=skill.getRemark2();
        this.value3=skill.getValue4();
    }
    public String getSkillid() {
        return this.skillid;
    }
    public void setSkillid(String skillid) {
        this.skillid = skillid;
    }
    public String getSkillname() {
        return this.skillname;
    }
    public void setSkillname(String skillname) {
        this.skillname = skillname;
    }
    public String getSkilltype() {
        return this.skilltype;
    }
    public void setSkilltype(String skilltype) {
        this.skilltype = skilltype;
    }
    public String getSkilllevel() {
        return this.skilllevel;
    }
    public void setSkilllevel(String skilllevel) {
        this.skilllevel = skilllevel;
    }
    public String getGrow() {
        return this.grow;
    }
    public void setGrow(String grow) {
        this.grow = grow;
    }
    public String getDielectric() {
        return this.dielectric;
    }
    public void setDielectric(String dielectric) {
        this.dielectric = dielectric;
    }
    public String getValue() {
        return this.value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getCamp() {
        return this.camp;
    }
    public void setCamp(String camp) {
        this.camp = camp;
    }
    public String getSkillralation() {
        return this.skillralation;
    }
    public void setSkillralation(String skillralation) {
        this.skillralation = skillralation;
    }
    public String getGrow1() {
        return grow1;
    }

    public void setGrow1(String grow1) {
        this.grow1 = grow1;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getGrow2() {
        return grow2;
    }

    public void setGrow2(String grow2) {
        this.grow2 = grow2;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }
}
