package org.come.bean;

public class RoleSuitBean
{
    private int SuitID;
    private String Sname;
    private int Sysex;
    private String HaveParts;
    private String HaveSkill;
    private String Introduce;
    
    public int getSuitID() {
        return this.SuitID;
    }
    
    public void setSuitID(int suitID) {
        this.SuitID = suitID;
    }
    
    public String getSname() {
        return this.Sname;
    }
    
    public void setSname(String sname) {
        this.Sname = sname;
    }
    
    public int getSysex() {
        return this.Sysex;
    }
    
    public void setSysex(int sysex) {
        this.Sysex = sysex;
    }
    
    public String getHaveParts() {
        return this.HaveParts;
    }
    
    public void setHaveParts(String haveParts) {
        this.HaveParts = haveParts;
    }
    
    public String getHaveSkill() {
        return this.HaveSkill;
    }
    
    public void setHaveSkill(String haveSkill) {
        this.HaveSkill = haveSkill;
    }
    
    public String getIntroduce() {
        return this.Introduce;
    }
    
    public void setIntroduce(String introduce) {
        this.Introduce = introduce;
    }
}
