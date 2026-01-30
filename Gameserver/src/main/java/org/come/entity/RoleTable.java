package org.come.entity;

import org.come.bean.LoginResult;
import javax.persistence.Transient;
import java.math.BigDecimal;

public class RoleTable
{
    private BigDecimal role_id;
    private BigDecimal user_id;
    private BigDecimal species_id;
    private BigDecimal summoning_id;
    private BigDecimal gang_id;
    private Integer mount_id;
    private BigDecimal troop_id;
    private BigDecimal race_id;
    private BigDecimal booth_id;
    private BigDecimal hp;
    private BigDecimal mp;
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
    private Integer calm;
    private Integer captain;
    private BigDecimal savegold;
    private String password;
    private Integer havebaby;
    private Long x;
    private Long y;
    private Long mapid;
    private Integer newrole;
    private String racename;
    private BigDecimal maxexp;
    private String marryObject;
    private String Skills;
    private String TimingGood;
    private String born;
    private String resistance;
    private String taskDaily;
    private String serverMeString;
    private String taskComplete;
    private String taskData;
    private BigDecimal Paysum;
    private BigDecimal Daypaysum;
    private BigDecimal Dayandpayorno;
    private int Dayfirstinorno;
    private int hjmax;
    private int dianka;
    private BigDecimal Daygetorno;
    private String Vipget;
    private int lowOrHihtpack;
    private Integer xiuwei;
    private String extraPoint;
    private String statues;
    private BigDecimal qid;
    private Short activity;
    private String PAYINTEGRATION;
    private String USERREGIDTSERTIME;
    private Integer fmsld;
    private Integer gameTimeRemaining;
    private BigDecimal Transfergold;
    private int start;
    private int end;
    private String unknown;
    private String userString;
    private String gmshoptype;
    private BigDecimal extPoint;
    private String liangHao;
    private Integer lianghaotype;
    private String lianghaoexpire;
    private Integer continueprice;
    @Transient
    private String accountName;
    @Transient
    private String accountPwd;
    @Transient
    private String otAreaid;
    @Transient
    private String otAreaname;
    @Transient
    private String otAtid;
    
    public Integer getContinueprice() {
        return this.continueprice;
    }
    
    public void setContinueprice(Integer continueprice) {
        this.continueprice = continueprice;
    }
    
    public String getLianghaoexpire() {
        return this.lianghaoexpire;
    }
    
    public void setLianghaoexpire(String lianghaoexpire) {
        this.lianghaoexpire = lianghaoexpire;
    }
    
    public Integer getLianghaotype() {
        return this.lianghaotype;
    }
    
    public void setLianghaotype(Integer lianghaotype) {
        this.lianghaotype = lianghaotype;
    }
    
    public String getLiangHao() {
        return this.liangHao;
    }
    
    public void setLiangHao(String liangHao) {
        this.liangHao = liangHao;
    }
    
    public BigDecimal getExtPoint() {
        return this.extPoint;
    }
    
    public void setExtPoint(BigDecimal extPoint) {
        this.extPoint = extPoint;
    }
    
    public RoleTable() {
    }
    
    public RoleTable(int gang, LoginResult result) {
        this.role_id = result.getRole_id();
        this.rolename = result.getRolename();
        this.gang_id = result.getGang_id();
        this.gangpost = result.getGangpost();
        this.gangname = result.getGangname();
    }
    
    public String getUserString() {
        return this.userString;
    }
    
    public void setUserString(String userString) {
        this.userString = userString;
    }
    
    public String getUnknown() {
        return this.unknown;
    }
    
    public void setUnknown(String unknown) {
        this.unknown = unknown;
    }
    
    public int getStart() {
        return this.start;
    }
    
    public void setStart(int start) {
        this.start = start;
    }
    
    public int getEnd() {
        return this.end;
    }
    
    public void setEnd(int end) {
        this.end = end;
    }
    
    public BigDecimal getQid() {
        return this.qid;
    }
    
    public void setQid(BigDecimal qid) {
        this.qid = qid;
    }
    
    public Short getActivity() {
        return this.activity;
    }
    
    public void setActivity(Short activity) {
        this.activity = activity;
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
    
    public Integer getMount_id() {
        return this.mount_id;
    }
    
    public void setMount_id(Integer mount_id) {
        this.mount_id = mount_id;
    }
    
    public BigDecimal getTroop_id() {
        return this.troop_id;
    }
    
    public void setTroop_id(BigDecimal troop_id) {
        this.troop_id = troop_id;
    }
    
    public BigDecimal getRace_id() {
        return this.race_id;
    }
    
    public void setRace_id(BigDecimal race_id) {
        this.race_id = race_id;
    }
    
    public BigDecimal getBooth_id() {
        return this.booth_id;
    }
    
    public void setBooth_id(BigDecimal booth_id) {
        this.booth_id = booth_id;
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
    
    public BigDecimal getGold() {
        return this.gold;
    }
    
    public void setGold(BigDecimal gold) {
        if (gold.compareTo(new BigDecimal(2000000000)) > 0) {
            this.setGold(new BigDecimal(2000000000));
        }
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
        this.rolename = rolename;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
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
    
    public String getUptime() {
        return this.uptime;
    }
    
    public void setUptime(String uptime) {
        this.uptime = uptime;
    }
    
    public String getGangname() {
        return this.gangname;
    }
    
    public void setGangname(String gangname) {
        this.gangname = gangname;
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
    
    public Integer getCalm() {
        return this.calm;
    }
    
    public void setCalm(Integer calm) {
        this.calm = calm;
    }
    
    public Integer getCaptain() {
        return this.captain;
    }
    
    public void setCaptain(Integer captain) {
        this.captain = captain;
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
    
    public BigDecimal getMaxexp() {
        return this.maxexp;
    }
    
    public void setMaxexp(BigDecimal maxexp) {
        this.maxexp = maxexp;
    }
    
    public String getMarryObject() {
        return this.marryObject;
    }
    
    public void setMarryObject(String marryObject) {
        this.marryObject = marryObject;
    }
    
    public String getSkills() {
        return this.Skills;
    }
    
    public void setSkills(String skills) {
        this.Skills = skills;
    }
    
    public String getBorn() {
        return this.born;
    }
    
    public void setBorn(String born) {
        this.born = born;
    }
    
    public String getResistance() {
        return this.resistance;
    }
    
    public void setResistance(String resistance) {
        this.resistance = resistance;
    }
    
    public String getTimingGood() {
        return this.TimingGood;
    }
    
    public void setTimingGood(String timingGood) {
        this.TimingGood = timingGood;
    }
    
    public String getTaskDaily() {
        return this.taskDaily;
    }
    
    public void setTaskDaily(String taskDaily) {
        this.taskDaily = taskDaily;
    }
    
    public String getServerMeString() {
        return this.serverMeString;
    }
    
    public void setServerMeString(String serverMeString) {
        this.serverMeString = serverMeString;
    }
    
    public String getTaskComplete() {
        return this.taskComplete;
    }
    
    public void setTaskComplete(String taskComplete) {
        this.taskComplete = taskComplete;
    }
    
    public String getTaskData() {
        return this.taskData;
    }
    
    public void setTaskData(String taskData) {
        this.taskData = taskData;
    }
    
    public String getStatues() {
        return this.statues;
    }
    
    public void setStatues(String statues) {
        this.statues = statues;
    }
    
    public String getPAYINTEGRATION() {
        return this.PAYINTEGRATION;
    }
    
    public void setPAYINTEGRATION(String pAYINTEGRATION) {
        this.PAYINTEGRATION = pAYINTEGRATION;
    }
    
    public BigDecimal getPaysum() {
        return this.Paysum;
    }
    
    public void setPaysum(BigDecimal paysum) {
        this.Paysum = paysum;
    }
    
    public BigDecimal getDaypaysum() {
        return this.Daypaysum;
    }
    
    public void setDaypaysum(BigDecimal daypaysum) {
        this.Daypaysum = daypaysum;
    }
    
    public BigDecimal getDayandpayorno() {
        return this.Dayandpayorno;
    }
    
    public void setDayandpayorno(BigDecimal dayandpayorno) {
        this.Dayandpayorno = dayandpayorno;
    }
    
    public int getDayfirstinorno() {
        return this.Dayfirstinorno;
    }
    
    public void setDayfirstinorno(int dayfirstinorno) {
        this.Dayfirstinorno = dayfirstinorno;
    }
    
    public BigDecimal getDaygetorno() {
        return this.Daygetorno;
    }
    
    public void setDaygetorno(BigDecimal daygetorno) {
        this.Daygetorno = daygetorno;
    }
    
    public String getVipget() {
        return this.Vipget;
    }
    
    public void setVipget(String vipget) {
        this.Vipget = vipget;
    }
    
    public int getLowOrHihtpack() {
        return this.lowOrHihtpack;
    }
    
    public void setLowOrHihtpack(int lowOrHihtpack) {
        this.lowOrHihtpack = lowOrHihtpack;
    }
    
    public Integer getXiuwei() {
        return this.xiuwei;
    }
    
    public void setXiuwei(Integer xiuwei) {
        this.xiuwei = xiuwei;
    }
    
    public String getExtraPoint() {
        return this.extraPoint;
    }
    
    public void setExtraPoint(String extraPoint) {
        this.extraPoint = extraPoint;
    }
    
    public int getHjmax() {
        return this.hjmax;
    }
    
    public void setHjmax(int hjmax) {
        this.hjmax = hjmax;
    }
    
    public int getDianka() {
        return this.dianka;
    }
    
    public void setDianka(int dianka) {
        this.dianka = dianka;
    }
    
    public Integer getFmsld() {
        return this.fmsld;
    }
    
    public void setFmsld(Integer fmsld) {
        this.fmsld = fmsld;
    }
    
    public String getUSERREGIDTSERTIME() {
        return this.USERREGIDTSERTIME;
    }
    
    public void setUSERREGIDTSERTIME(String uSERREGIDTSERTIME) {
        this.USERREGIDTSERTIME = uSERREGIDTSERTIME;
    }
    
    public String getGmshoptype() {
        return this.gmshoptype;
    }
    
    public void setGmshoptype(String gmshoptype) {
        this.gmshoptype = gmshoptype;
    }
    
    public Integer getGameTimeRemaining() {
        return this.gameTimeRemaining;
    }
    
    public void setGameTimeRemaining(Integer gameTimeRemaining) {
        this.gameTimeRemaining = gameTimeRemaining;
    }
    
    public String getOtAreaid() {
        return this.otAreaid;
    }
    
    public void setOtAreaid(String otAreaid) {
        this.otAreaid = otAreaid;
    }
    
    public String getOtAreaname() {
        return this.otAreaname;
    }
    
    public void setOtAreaname(String otAreaname) {
        this.otAreaname = otAreaname;
    }
    
    public String getOtAtid() {
        return this.otAtid;
    }
    
    public void setOtAtid(String otAtid) {
        this.otAtid = otAtid;
    }
    
    public String getAccountName() {
        return this.accountName;
    }
    
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    
    public String getAccountPwd() {
        return this.accountPwd;
    }
    
    public void setAccountPwd(String accountPwd) {
        this.accountPwd = accountPwd;
    }
    
    public BigDecimal getTransfergold() {
        return this.Transfergold;
    }
    
    public void setTransfergold(BigDecimal transfergold) {
        this.Transfergold = transfergold;
    }
}
