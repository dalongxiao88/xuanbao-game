package org.come.task;

public class MonsterHp
{
    private long hp;
    private long hpMax;
    private boolean isMuch;
    
    public MonsterHp() {
    }
    
    public MonsterHp(long hp, boolean isMuch) {
        this.hp = hp;
        this.hpMax = hp;
        this.isMuch = isMuch;
    }
    
    public void addHp(long add) {
        this.hp += add;
    }
    
    public long getHp() {
        return this.hp;
    }
    
    public void setHp(long hp) {
        this.hp = hp;
    }
    
    public long getHpMax() {
        return this.hpMax;
    }
    
    public void setHpMax(long hpMax) {
        this.hpMax = hpMax;
    }
    
    public boolean isMuch() {
        return this.isMuch;
    }
    
    public void setMuch(boolean isMuch) {
        this.isMuch = isMuch;
    }
}
