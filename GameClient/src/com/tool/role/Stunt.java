package com.tool.role;

public class Stunt
{
    private int skillId;
    private int suitid;
    private double value;
    private int lvl;
    private int sum;
    private int maxSum;
    
    public Stunt(int skillId, int suitid, int lvl, int maxSum) {
        this.skillId = skillId;
        this.suitid = suitid;
        this.lvl = lvl;
        this.maxSum = maxSum;
    }
    
    public boolean isValid() {
        return this.sum >= this.maxSum;
    }
    
    public boolean isAffect() {
        return (this.skillId >= 6001 && this.skillId <= 6017) || this.skillId == 6030 || this.skillId == 6035 || this.skillId == 6036 || this.skillId == 6044 || this.skillId == 6039 || this.skillId == 6031 || this.skillId == 6032 || (this.skillId >= 8001 && this.skillId <= 8023) || (this.skillId >= 8030 && this.skillId <= 8036) || (this.skillId >= 8038 && this.skillId <= 8039);
    }
    
    public int getSuitid() {
        return this.suitid;
    }
    
    public void setSuitid(int suitid) {
        this.suitid = suitid;
    }
    
    public int getSkillId() {
        return this.skillId;
    }
    
    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }
    
    public int getLvl() {
        return this.lvl;
    }
    
    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
    
    public int getSum() {
        return this.sum;
    }
    
    public void setSum(int sum) {
        this.sum = sum;
    }
    
    public void addSum() {
        ++this.sum;
    }
    
    public int getMaxSum() {
        return this.maxSum;
    }
    
    public void setMaxSum(int maxSum) {
        this.maxSum = maxSum;
    }
    
    public double getValue() {
        return this.value;
    }
    
    public void setValue(double value) {
        this.value = value;
    }
}
