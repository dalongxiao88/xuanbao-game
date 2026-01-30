package org.come.model;

import come.tool.FightingData.AI;
import java.util.List;
import come.tool.Calculation.PalQl;

public class PalData
{
    private int palId;
    private String name;
    private String skin;
    private String trait;
    private transient int sex;
    private int hp;
    private int mp;
    private int ap;
    private int sp;
    private String kx;
    private transient String jd;
    private String skill;
    private String xh;
    private transient String ai;
    private transient PalQl[] qls;
    private transient String[] skills;
    private int[] jds;
    private transient int size;
    private transient List<AI> ais;
    
    public int getPalId() {
        return this.palId;
    }
    
    public void setPalId(int palId) {
        this.palId = palId;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSkin() {
        return this.skin;
    }
    
    public void setSkin(String skin) {
        this.skin = skin;
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
    
    public String getKx() {
        return this.kx;
    }
    
    public void setKx(String kx) {
        this.kx = kx;
    }
    
    public String getJd() {
        return this.jd;
    }
    
    public void setJd(String jd) {
        this.jd = jd;
    }
    
    public String getSkill() {
        return this.skill;
    }
    
    public void setSkill(String skill) {
        this.skill = skill;
    }
    
    public String getAi() {
        return this.ai;
    }
    
    public void setAi(String ai) {
        this.ai = ai;
    }
    
    public String getXh() {
        return this.xh;
    }
    
    public void setXh(String xh) {
        this.xh = xh;
    }
    
    public PalQl[] getQls() {
        return this.qls;
    }
    
    public void setQls(PalQl[] qls) {
        this.qls = qls;
    }
    
    public int[] getJds() {
        return this.jds;
    }
    
    public void setJds(int[] jds) {
        this.jds = jds;
        if (jds != null) {
            this.size = 0;
            for (int i = 0; i < jds.length; ++i) {
                this.size += jds[i];
            }
        }
    }
    
    public int getSize() {
        return this.size;
    }
    
    public String[] getSkills() {
        return this.skills;
    }
    
    public void setSkills(String[] skills) {
        this.skills = skills;
    }
    
    public List<AI> getAis() {
        return this.ais;
    }
    
    public void setAis(List<AI> ais) {
        this.ais = ais;
    }
    
    public String getTrait() {
        return this.trait;
    }
    
    public void setTrait(String trait) {
        this.trait = trait;
    }
    
    public int getSex() {
        return this.sex;
    }
    
    public void setSex(int sex) {
        this.sex = sex;
    }
}
