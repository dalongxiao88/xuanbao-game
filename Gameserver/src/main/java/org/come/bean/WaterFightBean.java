package org.come.bean;

public class WaterFightBean
{
    private String teamGroup;
    private String fightNumber;
    private int fightNow;
    private int sumFight;
    private int fightOrNo;
    private int state;
    
    public WaterFightBean() {
        this.fightOrNo = 0;
        this.state = 0;
    }
    
    public int getState() {
        return this.state;
    }
    
    public void setState(int state) {
        this.state = state;
    }
    
    public String getTeamGroup() {
        return this.teamGroup;
    }
    
    public void setTeamGroup(String teamGroup) {
        this.teamGroup = teamGroup;
    }
    
    public String getFightNumber() {
        return this.fightNumber;
    }
    
    public void setFightNumber(String fightNumber) {
        this.fightNumber = fightNumber;
    }
    
    public int getFightNow() {
        return this.fightNow;
    }
    
    public void setFightNow(int fightNow) {
        this.fightNow = fightNow;
    }
    
    public int getSumFight() {
        return this.sumFight;
    }
    
    public void setSumFight(int sumFight) {
        this.sumFight = sumFight;
    }
    
    public int getFightOrNo() {
        return this.fightOrNo;
    }
    
    public void setFightOrNo(int fightOrNo) {
        this.fightOrNo = fightOrNo;
    }
}
