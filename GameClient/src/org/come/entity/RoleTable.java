package org.come.entity;

import java.math.BigDecimal;

public class RoleTable
{
    private BigDecimal role_id;
    private BigDecimal user_id;
    private BigDecimal species_id;
    private BigDecimal summoning_id;
    private BigDecimal gang_id;
    private String mount_id;
    private BigDecimal troop_id;
    private BigDecimal race_id;
    private BigDecimal booth_id;
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
    private Integer grade;
    private BigDecimal prestige;
    private BigDecimal pkrecord;
    private String rolename;
    private String title;
    private String sex;
    private String localname;
    private String skill;
    private String uptime;
    private String gangname;
    private String gangpost;
    private BigDecimal achievement;
    private BigDecimal contribution;
    private Integer bone;
    private Integer spir;
    private Integer power;
    private Integer speed;
    private Integer canpoint;
    private Integer captain;
    private BigDecimal extrahp;
    private BigDecimal gradehp;
    private BigDecimal extramp;
    private BigDecimal grademp;
    private BigDecimal savegold;
    private String password;
    private Integer havebaby;
    private Long x;
    private Long y;
    private Long mapid;
    private Integer newrole;
    private String racename;
    private Integer gameTimeRemaining;
    private Integer fmsld;
    private BigDecimal Transfergold;
    
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
    
    public BigDecimal getSavegold() {
        return this.savegold;
    }
    
    public void setSavegold(BigDecimal savegold) {
        this.savegold = savegold;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
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
    
    public BigDecimal getTroop_id() {
        return this.troop_id;
    }
    
    public void setTroop_id(BigDecimal troop_id) {
        this.troop_id = troop_id;
    }
    
    public BigDecimal getBooth_id() {
        return this.booth_id;
    }
    
    public void setBooth_id(BigDecimal booth_id) {
        this.booth_id = booth_id;
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
    
    public Integer getGrade() {
        return this.grade;
    }
    
    public void setGrade(Integer grade) {
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
    
    public String getMount_id() {
        return this.mount_id;
    }
    
    public void setMount_id(String mount_id) {
        this.mount_id = mount_id;
    }
    
    public String getUptime() {
        return this.uptime;
    }
    
    public void setUptime(String uptime) {
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
    
    public Integer getBone() {
        return this.bone;
    }
    
    public void setBone(Integer bone) {
        this.bone = bone;
    }
    
    public Integer getSpir() {
        return this.spir;
    }
    
    public void setSpir(Integer spir) {
        this.spir = spir;
    }
    
    public Integer getPower() {
        return this.power;
    }
    
    public void setPower(Integer power) {
        this.power = power;
    }
    
    public Integer getSpeed() {
        return this.speed;
    }
    
    public void setSpeed(Integer speed) {
        this.speed = speed;
    }
    
    public Integer getCanpoint() {
        return this.canpoint;
    }
    
    public void setCanpoint(Integer canpoint) {
        this.canpoint = canpoint;
    }
    
    public Integer getCaptain() {
        return this.captain;
    }
    
    public void setCaptain(Integer captain) {
        this.captain = captain;
    }
    
    public BigDecimal getExtrahp() {
        return this.extrahp;
    }
    
    public void setExtrahp(BigDecimal extrahp) {
        this.extrahp = extrahp;
    }
    
    public BigDecimal getGradehp() {
        return this.gradehp;
    }
    
    public void setGradehp(BigDecimal gradehp) {
        this.gradehp = gradehp;
    }
    
    public BigDecimal getExtramp() {
        return this.extramp;
    }
    
    public void setExtramp(BigDecimal extramp) {
        this.extramp = extramp;
    }
    
    public BigDecimal getGrademp() {
        return this.grademp;
    }
    
    public void setGrademp(BigDecimal grademp) {
        this.grademp = grademp;
    }
    
    public String getGangname() {
        return this.gangname;
    }
    
    public void setGangname(String gangname) {
        this.gangname = gangname;
    }
    
    public Integer getHavebaby() {
        return this.havebaby;
    }
    
    public void setHavebaby(Integer havebaby) {
        this.havebaby = havebaby;
    }
    
    public Long getX() {
        return this.x;
    }
    
    public void setX(Long x) {
        this.x = x;
    }
    
    public Long getY() {
        return this.y;
    }
    
    public void setY(Long y) {
        this.y = y;
    }
    
    public Long getMapid() {
        return this.mapid;
    }
    
    public void setMapid(Long mapid) {
        this.mapid = mapid;
    }
    
    public Integer getNewrole() {
        return this.newrole;
    }
    
    public void setNewrole(Integer newrole) {
        this.newrole = newrole;
    }
    
    public String getRacename() {
        return this.racename;
    }
    
    public void setRacename(String racename) {
        this.racename = racename;
    }
    
    public Integer getFmsld() {
        return this.fmsld;
    }
    
    public void setFmlsd(Integer fmsld) {
        this.fmsld = fmsld;
    }
    
    public Integer getGameTimeRemaining() {
        return this.gameTimeRemaining;
    }
    
    public void setGameTimeRemaining(Integer gameTimeRemaining) {
        this.gameTimeRemaining = gameTimeRemaining;
    }
    
    public BigDecimal getTransfergold() {
        return this.Transfergold;
    }
    
    public void setTransfergold(BigDecimal transfergold) {
        this.Transfergold = transfergold;
    }
}
