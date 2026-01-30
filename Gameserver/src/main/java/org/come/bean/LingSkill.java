package org.come.bean;

public class LingSkill
{
    private String skillname;
    private int skillsum;
    private int Effectsum;
    private boolean shanchang;
    
    public LingSkill() {
        this.shanchang = false;
    }
    
    public String getSkillname() {
        return this.skillname;
    }
    
    public void setSkillname(String skillname) {
        this.skillname = skillname;
    }
    
    public int getSkillsum() {
        return this.skillsum;
    }
    
    public void setSkillsum(int skillsum) {
        this.skillsum = skillsum;
    }
    
    public int getEffectsum() {
        return this.Effectsum;
    }
    
    public void setEffectsum(int effectsum) {
        this.Effectsum = effectsum;
    }
    
    public boolean isShanchang() {
        return this.shanchang;
    }
    
    public void setShanchang(boolean shanchang) {
        this.shanchang = shanchang;
    }
}
