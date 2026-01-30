package org.come.bean;

import java.math.BigDecimal;

public class FightOperation
{
    private int type;
    private int camp;
    private int man;
    private String Spell;
    private BigDecimal good;
    
    public FightOperation() {
        this.camp = -1;
        this.man = -1;
    }
    
    public void Record(int camp, int man, int type, String name) {
        this.camp = camp;
        this.man = man;
        this.type = type;
        if (type == 1) {
            this.Spell = name;
        }
        else if (type == 2) {
            this.good = new BigDecimal(name);
        }
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public int getCamp() {
        return this.camp;
    }
    
    public void setCamp(int camp) {
        this.camp = camp;
    }
    
    public int getMan() {
        return this.man;
    }
    
    public void setMan(int man) {
        this.man = man;
    }
    
    public String getSpell() {
        return this.Spell;
    }
    
    public void setSpell(String spell) {
        this.Spell = spell;
    }
    
    public BigDecimal getGood() {
        return this.good;
    }
    
    public void setGood(BigDecimal good) {
        this.good = good;
    }
}
