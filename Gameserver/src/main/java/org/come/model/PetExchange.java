package org.come.model;

import org.come.entity.RoleSummoning;

public class PetExchange
{
    private int eId;
    private String type;
    private String consume;
    private String pid;
    private String multiSkin;
    private String multiName;
    private String name;
    private String skin;
    private double grow;
    private int hp;
    private int mp;
    private int ap;
    private int sp;
    private String five;
    private String skill;
    
    public void initPetRadom(RoleSummoning pet, PetExchange petExchange) {
        this.name = petExchange.getMultiName();
        this.skin = petExchange.getMultiSkin();
    }
    
    public void initPet(RoleSummoning pet) {
        this.name = pet.getSummoningname();
        this.skin = pet.getSummoningskin();
        this.grow = Double.parseDouble(pet.getGrowlevel());
        this.hp = pet.getHp();
        this.mp = pet.getMp();
        this.ap = pet.getAp();
        this.sp = pet.getSp();
        StringBuffer buffer = new StringBuffer();
        if (!pet.getGold().equals("") && !pet.getGold().equals("0")) {
            if (buffer.length() != 0) {
                buffer.append(" ");
            }
            buffer.append("金:");
            buffer.append(pet.getGold());
        }
        if (!pet.getWood().equals("") && !pet.getWood().equals("0")) {
            if (buffer.length() != 0) {
                buffer.append(" ");
            }
            buffer.append("木:");
            buffer.append(pet.getWood());
        }
        if (!pet.getSoil().equals("") && !pet.getSoil().equals("0")) {
            if (buffer.length() != 0) {
                buffer.append(" ");
            }
            buffer.append("土:");
            buffer.append(pet.getSoil());
        }
        if (!pet.getWater().equals("") && !pet.getWater().equals("0")) {
            if (buffer.length() != 0) {
                buffer.append(" ");
            }
            buffer.append("水:");
            buffer.append(pet.getWater());
        }
        if (!pet.getFire().equals("") && !pet.getFire().equals("0")) {
            if (buffer.length() != 0) {
                buffer.append(" ");
            }
            buffer.append("火:");
            buffer.append(pet.getFire());
        }
        this.five = buffer.toString();
        this.skill = pet.getSkill();
    }
    
    public int geteId() {
        return this.eId;
    }
    
    public void seteId(int eId) {
        this.eId = eId;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getConsume() {
        return this.consume;
    }
    
    public void setConsume(String consume) {
        this.consume = consume;
    }
    
    public String getPid() {
        return this.pid;
    }
    
    public void setPid(String pid) {
        this.pid = pid;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getGrow() {
        return this.grow;
    }
    
    public void setGrow(double grow) {
        this.grow = grow;
    }
    
    public int getHp() {
        return this.hp;
    }
    
    public void setHp(int hp) {
        this.hp = hp;
    }
    
    public int getMp() {
        return this.mp;
    }
    
    public void setMp(int mp) {
        this.mp = mp;
    }
    
    public int getAp() {
        return this.ap;
    }
    
    public void setAp(int ap) {
        this.ap = ap;
    }
    
    public int getSp() {
        return this.sp;
    }
    
    public void setSp(int sp) {
        this.sp = sp;
    }
    
    public String getFive() {
        return this.five;
    }
    
    public void setFive(String five) {
        this.five = five;
    }
    
    public String getSkill() {
        return this.skill;
    }
    
    public void setSkill(String skill) {
        this.skill = skill;
    }
    
    public String getMultiSkin() {
        return this.multiSkin;
    }
    
    public void setMultiSkin(String multiSkin) {
        this.multiSkin = multiSkin;
    }
    
    public String getMultiName() {
        return this.multiName;
    }
    
    public void setMultiName(String multiName) {
        this.multiName = multiName;
    }
}
