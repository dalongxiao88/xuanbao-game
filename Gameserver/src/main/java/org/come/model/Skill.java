package org.come.model;

import java.util.Optional;
import java.util.Arrays;

public class Skill implements Cloneable{
    private int skillid;
    private String skillname;
    private int skilltype;
    private int skilllevel;
    private double grow;
    private double dielectric;
    private double value;
    private int camp;
    private String skillralation;
    private double grow1;
    private double value1;
    private double grow2;
    private double value2;
    private double grow3;
    private double value3;
    private String remark;
    private String value4;
    private String remark2;//悟灵
    private String petSkillswl;

    public double s1;

    public double s2;
    public double s3;
    public double s4;
    public double s5;
    public double s6;
    public double s7;
    public double p1;
    public double p2;
    public double p3;
    public double p4;
    public double p5;
    public double p6;
    public double p7;
    public double e1;
    public double e2;
    public double e3;
    public double e4;
    public double e5;
    public double e6;
    public double e7;


    public double getS2() {
        return s2;
    }

    public void setS2(double s2) {
        this.s2 = s2;
    }

    public double getS3() {
        return s3;
    }

    public void setS3(double s3) {
        this.s3 = s3;
    }

    public double getS4() {
        return s4;
    }

    public void setS4(double s4) {
        this.s4 = s4;
    }

    public double getS5() {
        return s5;
    }

    public void setS5(double s5) {
        this.s5 = s5;
    }

    public double getS6() {
        return s6;
    }

    public void setS6(double s6) {
        this.s6 = s6;
    }

    public double getS7() {
        return s7;
    }

    public void setS7(double s7) {
        this.s7 = s7;
    }

    public double getP1() {
        return p1;
    }

    public void setP1(double p1) {
        this.p1 = p1;
    }

    public double getP2() {
        return p2;
    }

    public void setP2(double p2) {
        this.p2 = p2;
    }

    public double getP3() {
        return p3;
    }

    public void setP3(double p3) {
        this.p3 = p3;
    }

    public double getP4() {
        return p4;
    }

    public void setP4(double p4) {
        this.p4 = p4;
    }

    public double getP5() {
        return p5;
    }

    public void setP5(double p5) {
        this.p5 = p5;
    }

    public double getP6() {
        return p6;
    }

    public void setP6(double p6) {
        this.p6 = p6;
    }

    public double getP7() {
        return p7;
    }

    public void setP7(double p7) {
        this.p7 = p7;
    }

    public double getE1() {
        return e1;
    }

    public void setE1(double e1) {
        this.e1 = e1;
    }

    public double getE2() {
        return e2;
    }

    public void setE2(double e2) {
        this.e2 = e2;
    }

    public double getE3() {
        return e3;
    }

    public void setE3(double e3) {
        this.e3 = e3;
    }

    public double getE4() {
        return e4;
    }

    public void setE4(double e4) {
        this.e4 = e4;
    }

    public double getE5() {
        return e5;
    }

    public void setE5(double e5) {
        this.e5 = e5;
    }

    public double getE6() {
        return e6;
    }

    public void setE6(double e6) {
        this.e6 = e6;
    }

    public double getE7() {
        return e7;
    }

    public void setE7(double e7) {
        this.e7 = e7;
    }


    public double getS1() {
        return this.s1;
    }

    public void setS1(double s1) {
        this.s1 = s1;
    }

    public int getSkillid() {
        return this.skillid;
    }

    public void setSkillid(int skillid) {
        this.skillid = skillid;
    }

    public String getSkillname() {
        return this.skillname;
    }

    public void setSkillname(String skillname) {
        this.skillname = skillname;
    }

    public int getSkilltype() {
        return this.skilltype;
    }

    public void setSkilltype(int skilltype) {
        this.skilltype = skilltype;
    }

    public int getSkilllevel() {
        return this.skilllevel;
    }

    public void setSkilllevel(int skilllevel) {
        this.skilllevel = skilllevel;
    }

    public double getGrow() {
        return this.grow;
    }

    public void setGrow(double grow) {
        this.grow = grow;
    }

    public double getDielectric() {
        return this.dielectric;
    }

    public void setDielectric(double dielectric) {
        this.dielectric = dielectric;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getCamp() {
        return this.camp;
    }

    public void setCamp(int camp) {
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

    public double getGrow1() {
        return this.grow1;
    }

    public void setGrow1(double grow1) {
        this.grow1 = grow1;
    }

    public double getValue1() {
        return this.value1;
    }

    public void setValue1(double value1) {
        this.value1 = value1;
    }

    private String getPetSkillswlLevel(String petSkillswl, String skillId) {
        String[] split = petSkillswl.split("\\|");
        Optional<String> first = Arrays.stream(split).filter(item/* java.lang.String, */ -> item.contains(skillId)).findFirst();
        if (first != null) {
            String s = (String) first.get();
            return s.split("=")[1];
        }
        return null;
    }

    public String getPetSkillswl() {
        return this.petSkillswl;
    }

    public void setPetSkillswl(String petSkillswl) {
        this.petSkillswl = petSkillswl;
    }

    public double getGrow2() {
        return this.grow2;
    }

    public void setGrow2(double grow2) {
        this.grow2 = grow2;
    }

    public double getValue2() {
        return this.value2;
    }

    public void setValue2(double value2) {
        this.value2 = value2;
    }

    public double getGrow3() {
        return this.grow3;
    }

    public void setGrow3(double grow3) {
        this.grow3 = grow3;
    }

    public double getValue3() {
        return this.value3;
    }

    public void setValue3(double value3) {
        this.value3 = value3;
    }

    public String getValue4() {
        return value4;
    }

    public void setValue4(String value4) {
        this.value4 = value4;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }


    public Skill clone() {
        try {
            return (Skill) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
