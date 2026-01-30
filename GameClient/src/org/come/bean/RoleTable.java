package org.come.bean;

import java.util.Date;
import java.math.BigDecimal;

public class RoleTable
{
    private BigDecimal role_id;
    private BigDecimal user_id;
    private BigDecimal species_id;
    private BigDecimal summoning_id;
    private BigDecimal gang_id;
    private BigDecimal mount_id;
    private BigDecimal troop_id;
    private BigDecimal race_id;
    private BigDecimal skill_id;
    private BigDecimal booth_id;
    private BigDecimal task_id;
    private BigDecimal hp;
    private BigDecimal mp;
    private BigDecimal ap;
    private BigDecimal sp;
    private BigDecimal mpv;
    private BigDecimal apv;
    private BigDecimal spv;
    private BigDecimal hpv;
    private BigDecimal gold;
    private BigDecimal codecard;
    private BigDecimal experience;
    private BigDecimal grade;
    private BigDecimal prestige;
    private BigDecimal pkrecord;
    private String rolename;
    private String title;
    private String sex;
    private String localname;
    private String skill;
    private Date uptime;
    private String gangpost;
    private BigDecimal achievement;
    private BigDecimal contribution;
    
    public BigDecimal getHpv() {
        return this.hpv;
    }
    
    public void setHpv(BigDecimal hpv) {
        this.hpv = hpv;
    }
    
    public String getSex() {
        return this.sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public String getLocalname() {
        return this.localname;
    }
    
    public void setLocalname(String localname) {
        this.localname = localname;
    }
    
    public String getSkill() {
        return this.skill;
    }
    
    public void setSkill(String skill) {
        this.skill = skill;
    }
    
    public BigDecimal getRole_id() {
        return this.role_id;
    }
    
    public void setRole_id(BigDecimal role_id) {
        this.role_id = role_id;
    }
    
    public BigDecimal getUser_id() {
        return this.user_id;
    }
    
    public void setUser_id(BigDecimal user_id) {
        this.user_id = user_id;
    }
    
    public BigDecimal getSpecies_id() {
        return this.species_id;
    }
    
    public void setSpecies_id(BigDecimal species_id) {
        this.species_id = species_id;
    }
    
    public BigDecimal getSummoning_id() {
        return this.summoning_id;
    }
    
    public void setSummoning_id(BigDecimal summoning_id) {
        this.summoning_id = summoning_id;
    }
    
    public BigDecimal getGang_id() {
        return this.gang_id;
    }
    
    public void setGang_id(BigDecimal gang_id) {
        this.gang_id = gang_id;
    }
    
    public BigDecimal getMount_id() {
        return this.mount_id;
    }
    
    public void setMount_id(BigDecimal mount_id) {
        this.mount_id = mount_id;
    }
    
    public BigDecimal getTroop_id() {
        return this.troop_id;
    }
    
    public void setTroop_id(BigDecimal troop_id) {
        this.troop_id = troop_id;
    }
    
    public BigDecimal getSkill_id() {
        return this.skill_id;
    }
    
    public void setSkill_id(BigDecimal skill_id) {
        this.skill_id = skill_id;
    }
    
    public BigDecimal getBooth_id() {
        return this.booth_id;
    }
    
    public void setBooth_id(BigDecimal booth_id) {
        this.booth_id = booth_id;
    }
    
    public BigDecimal getTask_id() {
        return this.task_id;
    }
    
    public void setTask_id(BigDecimal task_id) {
        this.task_id = task_id;
    }
    
    public BigDecimal getRace_id() {
        return this.race_id;
    }
    
    public void setRace_id(BigDecimal race_id) {
        this.race_id = race_id;
    }
    
    public BigDecimal getHp() {
        return this.hp;
    }
    
    public void setHp(BigDecimal hp) {
        this.hp = hp;
    }
    
    public BigDecimal getMp() {
        return this.mp;
    }
    
    public void setMp(BigDecimal mp) {
        this.mp = mp;
    }
    
    public BigDecimal getAp() {
        return this.ap;
    }
    
    public void setAp(BigDecimal ap) {
        this.ap = ap;
    }
    
    public BigDecimal getSp() {
        return this.sp;
    }
    
    public void setSp(BigDecimal sp) {
        this.sp = sp;
    }
    
    public BigDecimal getMpv() {
        return this.mpv;
    }
    
    public void setMpv(BigDecimal mpv) {
        this.mpv = mpv;
    }
    
    public BigDecimal getApv() {
        return this.apv;
    }
    
    public void setApv(BigDecimal apv) {
        this.apv = apv;
    }
    
    public BigDecimal getSpv() {
        return this.spv;
    }
    
    public void setSpv(BigDecimal spv) {
        this.spv = spv;
    }
    
    public BigDecimal getGold() {
        return this.gold;
    }
    
    public void setGold(BigDecimal gold) {
        this.gold = gold;
    }
    
    public BigDecimal getCodecard() {
        return this.codecard;
    }
    
    public void setCodecard(BigDecimal codecard) {
        this.codecard = codecard;
    }
    
    public BigDecimal getExperience() {
        return this.experience;
    }
    
    public void setExperience(BigDecimal experience) {
        this.experience = experience;
    }
    
    public BigDecimal getGrade() {
        return this.grade;
    }
    
    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }
    
    public BigDecimal getPrestige() {
        return this.prestige;
    }
    
    public void setPrestige(BigDecimal prestige) {
        this.prestige = prestige;
    }
    
    public BigDecimal getPkrecord() {
        return this.pkrecord;
    }
    
    public void setPkrecord(BigDecimal pkrecord) {
        this.pkrecord = pkrecord;
    }
    
    public String getRolename() {
        return this.rolename;
    }
    
    public void setRolename(String rolename) {
        this.rolename = ((rolename == null) ? null : rolename.trim());
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = ((title == null) ? null : title.trim());
    }
    
    public Date getUptime() {
        return this.uptime;
    }
    
    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }
    
    public String getGangpost() {
        return this.gangpost;
    }
    
    public void setGangpost(String gangpost) {
        this.gangpost = gangpost;
    }
    
    public BigDecimal getAchievement() {
        return this.achievement;
    }
    
    public void setAchievement(BigDecimal achievement) {
        this.achievement = achievement;
    }
    
    public BigDecimal getContribution() {
        return this.contribution;
    }
    
    public void setContribution(BigDecimal contribution) {
        this.contribution = contribution;
    }
}
