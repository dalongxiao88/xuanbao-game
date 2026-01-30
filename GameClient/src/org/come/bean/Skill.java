//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.come.bean;

import org.apache.commons.lang.StringUtils;

public class Skill  implements  Cloneable{
    private String skillid;
    private String skillname;
    private String skilltype;
    private String skilllevel;
    private String grow;
    private String dielectric;
    private String value;
    private String camp;
    private String grow1;
    private String value1;
    private String skillralation;
    private String grow2;
    // 介值3
    private String value2;
    private String grow3;
    private String value3;
    private String remark;
    private String value4;
    private String remark2;
    private Integer Skilled;
    private String skilltype_name;
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
    public Skill() {
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
        if(StringUtils.isBlank(dielectric)){
            return "0";
        }
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

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSkilled() {
        return this.Skilled;
    }

    public void setSkilled(Integer skilled) {
        this.Skilled = skilled;
    }
    public String getGrow1() {
        return this.grow1;
    }
    public void setGrow1(String grow1) {
        this.grow1 = grow1;
    }
    public String getValue1() {
        return this.value1;
    }
    public void setValue1(String value1) { this.value1 = value1;}
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

    public String getGrow3() {
        return grow3;
    }//

    public void setGrow3(String grow3) {
        this.grow3 = grow3;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getValue4() {
        return value4;
    }

    public void setValue4(String value4) {
        this.value4 = value4;
    }


    public String getSkilltype_name() {
        return skilltype_name;
    }

    public void setSkilltype_name(String skilltype_name) {
        this.skilltype_name = skilltype_name;
    }

    public double getS1() {
        return s1;
    }


    public void setS1(double s1) {
        this.s1 = s1;
    }

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



    public Skill clone() {
        try {
            return (Skill) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
