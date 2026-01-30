package org.come.bean;

import org.come.Jpanel.GoodsMsgJpanel;
import org.come.until.GoodsListFromServerUntil;
import org.apache.commons.lang.StringUtils;
import com.tool.role.RoleData;
import org.come.until.AnalysisString;
import org.come.Frame.ZhuFrame;
import org.come.until.Util;
import org.come.until.JmSum;
import org.come.entity.RoleSummoning;
import java.util.List;
import java.math.BigDecimal;

public class LoginResult
{
    private String resistance;
    private String gangname;
    private BigDecimal role_id;
    private String race_name;
    private BigDecimal user_id;
    private BigDecimal species_id;
    private BigDecimal summoning_id;
    private String pals;
    private BigDecimal gang_id;
    private Integer mount_id;
    private BigDecimal troop_id;
    private BigDecimal race_id;
    private BigDecimal babyId;
    private int TurnAround;
    private BigDecimal booth_id;
    private Integer skill_id;
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
    private String localname;
    private String userName;
    private String userPwd;
    private String sex;
    private Long x;
    private Long y;
    private Long mapid;
    private String gangpost;
    private BigDecimal achievement;
    private BigDecimal contribution;
    private Integer bone;
    private Integer spir;
    private Integer power;
    private Integer speed;
    private Integer calm;
    private Integer xiuwei;
    private String extraPoint;
    private Integer fighting;
    private String uptime;
    private BigDecimal savegold;
    private String password;
    private Integer havebaby;
    private String babyState;
    private String marryObject;
    private String teamInfo;
    private Integer money;
    private String taskDaily;
    private String serverMeString;
    private String Score;
    private String Kill;
    private Integer bangScore;
    private String matchTeam;
    private String skin;
    private BigDecimal Paysum;
    private BigDecimal Daypaysum;
    private BigDecimal Dayandpayorno;
    private int Dayfirstinorno;
    private BigDecimal Daygetorno;
    private String Vipget;
    private int lowOrHihtpack;
    private String meridians;
    private Integer hjmax;
    private Integer fmsld;
    private int attachPack;
    private String xingpans;
    private String qianDao;
    private Long loginTime;
    private Long onlineTime;
    private String gmshoptype;
    private Integer ttVictory;
    private Integer ttFail;
    private Integer gradeincrease;
    private Integer gameTimeRemaining;
    private Integer currentattribute;
    private List<RoleSummoning> showRoleSummoningList;
    private String flyskin;
    private Integer fly_id;

    private BigDecimal extPoint;
    private String liangHao;
    private Integer lianghaotype;
    private String lianghaoValue;
    private String lianghaoexpire;
    private Integer continueprice;
    private Double Bskvalue;
    private BigDecimal Transfergold;
    private String BT;
    private int shouhu;
    private String zhongtian;
    private String qinglong;
    private String baihu;
    private String xuanwu;
    private String zhuque;
    private String jiesuo;
    private String sh;
    private BigDecimal Moneyshop;
    private String equipments;

    private String achieveRecord;
    private String petdebris;
    private String dayDraw;
    private Integer car_id;
    private String car_value;


    public LoginResult() {
        this.resistance = "主-|副-";
        this.TurnAround = 0;
        this.savegold = new BigDecimal(0);
        this.havebaby = Integer.valueOf(0);
        this.money = Integer.valueOf(0);
        this.shouhu = 0;
    }
    
    public String getBT() {
        return this.BT;
    }
    
    public void setBT(String BT) {
        this.BT = BT;
    }
    
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
    
    public int getExtPointInt() {
        if (this.extPoint == null || this.extPoint.compareTo(BigDecimal.ZERO) == 0) {
            return 0;
        }
        return this.extPoint.intValue();
    }
    
    public void setExtPoint(BigDecimal extPoint) {
        this.extPoint = extPoint;
    }
    
    public String getGmshoptype() {
        return this.gmshoptype;
    }
    
    public void setGmshoptype(String gmshoptype) {
        this.gmshoptype = gmshoptype;
    }
    
    public String getQianDao() {
        return this.qianDao;
    }
    
    public void setQianDao(String qianDao) {
        this.qianDao = qianDao;
    }
    
    public Long getLoginTime() {
        return this.loginTime;
    }
    
    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }
    
    public Long getOnlineTime() {
        return this.onlineTime;
    }
    
    public void setOnlineTime(Long onlineTime) {
        this.onlineTime = onlineTime;
    }
    
    public String getScore() {
        return this.Score;
    }
    
    public BigDecimal getScoretype(String type) {
        if (this.Score == null || this.Score.equals("")) {
            return new BigDecimal(0);
        }
        String[] v = this.Score.split("\\|");
        for (int i = 0; i < v.length; ++i) {
            String[] v2 = v[i].split("=");
            if (v2[0].equals(type)) {
                return new BigDecimal(v2[1]);
            }
        }
        return new BigDecimal(0);
    }
    
    public void setScore(String score) {
        this.Score = score;
    }
    
    public String getKill() {
        return this.Kill;
    }
    
    public double getKilltype(String type) {
        if (this.Kill == null || this.Kill.equals("")) {
            return 0.0;
        }
        String[] v = this.Kill.split("\\|");
        for (int i = 0; i < v.length; ++i) {
            String[] v2 = v[i].split("=");
            if (v2[0].equals(type)) {
                return Double.parseDouble(v2[1]);
            }
        }
        return 0.0;
    }
    
    public void setKill(String kill) {
        this.Kill = kill;
    }
    
    public String getServerMeString() {
        return this.serverMeString;
    }
    
    public void setServerMeString(String serverMeString) {
        this.serverMeString = serverMeString;
    }
    
    public String getTeamInfo() {
        return this.teamInfo;
    }
    
    public void setTeamInfo(String teamInfo) {
        this.teamInfo = teamInfo;
    }
    
    public Integer getMoney() {
        return this.money;
    }
    
    public void setMoney(Integer money) {
        this.money = money;
    }
    
    public String getBabyState() {
        return this.babyState;
    }
    
    public void setBabyState(String babyState) {
        this.babyState = babyState;
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
    
    public String getMarryObject() {
        return this.marryObject;
    }
    
    public void setMarryObject(String marryObject) {
        this.marryObject = marryObject;
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
        if (this.mount_id == null) {
            this.mount_id = Integer.valueOf(0);
        }
        return this.mount_id;
    }
    
    public void setMount_id(Integer mount_id) {
        this.mount_id = mount_id;
    }
    public Integer getCar_id() {
        if (this.car_id == null) {
            this.car_id = Integer.valueOf(0);
        }
        return this.car_id;
    }

    public void setCar_id(Integer mount_id) {
        this.car_id = mount_id;
    }

    public BigDecimal getTroop_id() {
        return this.troop_id;
    }
    
    public void setTroop_id(BigDecimal troop_id) {
        this.troop_id = troop_id;
    }
    public String getCar_value() {
        return car_value;
    }

    public void setCar_Value(String car_value) {
        this.car_value = car_value;
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
        return (this.gold == null) ? this.gold : new BigDecimal(JmSum.MZ(this.gold.longValue()));
    }
    
    public void setGold(BigDecimal gold) {
        if (gold.compareTo(Util.GoldUP) > 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("金币上限9999.99亿,多余的金币丢弃");
            gold = Util.GoldUP;
        }
        this.gold = new BigDecimal(JmSum.ZM(gold.longValue()));
    }
    
    public BigDecimal getCodecard() {
        return (this.codecard == null) ? this.codecard : new BigDecimal(JmSum.MZ(this.codecard.longValue()));
    }
    
    public void setCodecard(BigDecimal codecard) {
        this.codecard = new BigDecimal(JmSum.ZM(codecard.longValue()));
    }
    
    public BigDecimal getExperience() {
        return (this.experience == null) ? this.experience : new BigDecimal(JmSum.MZ(this.experience.longValue()));
    }
    
    public void setExperience(BigDecimal experience) {
        this.experience = new BigDecimal(JmSum.ZM(experience.longValue()));
    }
    
    public Integer getGrade() {
        return Integer.valueOf((this.grade == null) ? ((int)this.grade) : ((int)JmSum.MZ((long)this.grade)));
    }
    
    public void setGrade(Integer grade) {
        this.grade = Integer.valueOf((int)JmSum.ZM((long)grade));
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
    
    public String getLocalname() {
        return this.localname;
    }
    
    public void setLocalname(String localname) {
        this.localname = localname;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUserPwd() {
        return this.userPwd;
    }
    
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
    
    public String getSex() {
        return this.sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public String getRace_name() {
        return this.race_name;
    }
    
    public void setRace_name(String race_name) {
        this.race_name = race_name;
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
    
    public String getGangname() {
        return this.gangname;
    }
    
    public void setGangname(String gangname) {
        this.gangname = gangname;
    }
    
    public Integer getBone() {
        return Integer.valueOf((int)JmSum.MZ((long)this.bone));
    }
    
    public void setBone(Integer bone) {
        this.bone = Integer.valueOf((int)JmSum.ZM((long)bone));
    }
    
    public Integer getSpir() {
        return Integer.valueOf((int)JmSum.MZ((long)this.spir));
    }
    
    public void setSpir(Integer spir) {
        this.spir = Integer.valueOf((int)JmSum.ZM((long)spir));
    }
    
    public Integer getPower() {
        return Integer.valueOf((int)JmSum.MZ((long)this.power));
    }
    
    public void setPower(Integer power) {
        this.power = Integer.valueOf((int)JmSum.ZM((long)power));
    }
    
    public Integer getSpeed() {
        return Integer.valueOf((int)JmSum.MZ((long)this.speed));
    }
    
    public void setSpeed(Integer speed) {
        this.speed = Integer.valueOf((int)JmSum.ZM((long)speed));
    }
    
    public Integer getCanpoint() {
        int lvl = (int)this.getGrade();
        int Turn = AnalysisString.lvltrue(lvl);
        lvl = AnalysisString.lvlint(lvl);
        int canpoint = lvl * 8;
        canpoint += this.getExtraPointInt();
        canpoint += this.getExtPointInt();
        if (Turn < 4) {
            canpoint += Turn * 60;
        }
        else {
            canpoint += 180 + lvl;
        }
        int v = (int)this.getBone();
        canpoint -= v;
        if (v < lvl) {}
        v = (int)this.getSpir();
        canpoint -= v;
        if (v < lvl) {}
        v = (int)this.getPower();
        canpoint -= v;
        if (v < lvl) {}
        v = (int)this.getSpeed();
        canpoint -= v;
        if (v < lvl) {}
        v = (int)this.getCalm();
        canpoint -= v;
        if (v < 0 || Turn < 4 || v < lvl) {}
        if (canpoint < 0) {
            JmSum.xiugaiqi();
        }
        return Integer.valueOf(canpoint);
    }
    
    public String getUptime() {
        return this.uptime;
    }
    
    public void setUptime(String uptime) {
        this.uptime = uptime;
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
    
    public Integer getFighting() {
        if (this.fighting == null) {
            this.fighting = Integer.valueOf(0);
        }
        return this.fighting;
    }
    
    public void setFighting(Integer fighting) {
        this.fighting = fighting;
    }
    
    public BigDecimal getBabyId() {
        return this.babyId;
    }
    
    public void setBabyId(BigDecimal babyId) {
        this.babyId = babyId;
    }
    
    public int getTurnAround() {
        return this.TurnAround;
    }
    
    public void setTurnAround(int turnAround) {
        this.TurnAround = turnAround;
    }
    
    public String getTaskDaily() {
        return this.taskDaily;
    }
    
    public void setTaskDaily(String taskDaily) {
        this.taskDaily = taskDaily;
    }
    
    public String getResistance() {
        if (this.resistance == null || this.resistance.equals("")) {
            this.resistance = "主-|副-";
        }
        return this.resistance;
    }
    
    public void setResistance(String resistance) {
        this.resistance = resistance;
    }
    
    public String getTeam() {
        return (this.teamInfo != null && !this.teamInfo.equals("") && !this.teamInfo.equals("|")) ? this.teamInfo : this.rolename;
    }
    
    public Integer getSkill_id() {
        return this.skill_id;
    }
    
    public void setSkill_id(Integer skill_id) {
        this.skill_id = skill_id;
    }
    
    public Integer getBangScore() {
        return this.bangScore;
    }
    
    public void setBangScore(Integer bangScore) {
        this.bangScore = bangScore;
    }
    
    public String getMatchTeam() {
        return this.matchTeam;
    }
    
    public void setMatchTeam(String matchTeam) {
        this.matchTeam = matchTeam;
    }
    
    public Integer getCalm() {
        return Integer.valueOf((this.calm == null) ? 0 : ((int)JmSum.MZ((long)this.calm)));
    }
    
    public void setCalm(Integer calm) {
        this.calm = Integer.valueOf((int)JmSum.ZM((long)calm));
    }
    
    public Integer getXiuwei() {
        return Integer.valueOf((this.xiuwei == null) ? 0 : ((int)JmSum.MZ((long)this.xiuwei)));
    }
    
    public void setXiuwei(Integer xiuwei) {
        this.xiuwei = Integer.valueOf((int)JmSum.ZM((long)xiuwei));
    }
    
    public String getExtraPoint() {
        return this.extraPoint;
    }
    
    public void setExtraPoint(String extraPoint) {
        this.extraPoint = extraPoint;
    }
    
    public String[] getResistance(String type) {
        if (this.resistance == null || this.resistance.equals("")) {
            return null;
        }
        String[] vs = this.resistance.split("\\|");
        for (int i = 0; i < vs.length; ++i) {
            if (vs[i].startsWith(type)) {
                String[] v = vs[i].split("#");
                v[0] = v[0].substring(1);
                return v;
            }
        }
        return null;
    }
    
    public int getExtraPointInt(String type) {
        if (this.extraPoint == null || this.extraPoint.equals("")) {
            return 0;
        }
        String[] vs = this.extraPoint.split("\\|");
        for (int i = 0; i < vs.length; ++i) {
            if (vs[i].startsWith(type)) {
                return Integer.parseInt(vs[i].substring(1));
            }
        }
        return 0;
    }
    
    public int getExtraPointInt() {
        if (this.extraPoint == null || this.extraPoint.equals("")) {
            return 0;
        }
        String[] vs = this.extraPoint.split("\\|");
        int p = 0;
        for (int i = 0; i < vs.length; ++i) {
            if (vs[i].startsWith("F")) {
                p += Integer.parseInt(vs[i].substring(1));
            }
        }
        return p;
    }
    
    public void setExtraPoint(String type, int p) {
        type = type.substring(0, 1);
        if (this.extraPoint == null || this.extraPoint.equals("")) {
            this.extraPoint = type + p;
        }
        String[] vs = this.extraPoint.split("\\|");
        int i = 0;
        while (i < vs.length) {
            if (vs[i].startsWith(type)) {
                vs[i] = type + (Integer.parseInt(vs[i].substring(1)) + p);
                StringBuffer buffer = new StringBuffer();
                for (int j = 0; j < vs.length; ++j) {
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append(vs[j]);
                }
                this.extraPoint = buffer.toString();
                return;
            }
            else {
                ++i;
            }
        }
        StringBuffer buffer2 = new StringBuffer();
        for (int k = 0; k < vs.length; ++k) {
            if (buffer2.length() != 0) {
                buffer2.append("|");
            }
            buffer2.append(vs[k]);
        }
        if (buffer2.length() != 0) {
            buffer2.append("|");
        }
        buffer2.append(type);
        buffer2.append(p);
        this.extraPoint = buffer2.toString();
    }
    
    public void setFGExtraPoint(String type, int p) {
        type = type.substring(0, 1);
        if (this.extraPoint == null || this.extraPoint.equals("")) {
            this.extraPoint = type + p;
        }
        String[] vs = this.extraPoint.split("\\|");
        int i = 0;
        while (i < vs.length) {
            if (vs[i].startsWith(type)) {
                vs[i] = type + p;
                StringBuffer buffer = new StringBuffer();
                for (int j = 0; j < vs.length; ++j) {
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append(vs[j]);
                }
                this.extraPoint = buffer.toString();
                return;
            }
            else {
                ++i;
            }
        }
        StringBuffer buffer2 = new StringBuffer();
        for (int k = 0; k < vs.length; ++k) {
            if (buffer2.length() != 0) {
                buffer2.append("|");
            }
            buffer2.append(vs[k]);
        }
        if (buffer2.length() != 0) {
            buffer2.append("|");
        }
        buffer2.append(type);
        buffer2.append(p);
        this.extraPoint = buffer2.toString();
    }
    
    public String getSkin() {
        return this.skin;
    }
    
    public void setSkin(String skin) {
        this.skin = skin;
    }
    
    public BigDecimal getPaysum() {
        return (this.Paysum == null) ? this.Paysum : new BigDecimal(this.Paysum.longValue());
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
    
    public String getPals() {
        return this.pals;
    }
    
    public void setPals(String pals) {
        this.pals = pals;
    }
    
    public String getMeridians() {
        return this.meridians;
    }
    
    public void setMeridians(String meridians) {
        this.meridians = meridians;
    }
    
    public int getAttachPack() {
        return this.attachPack;
    }
    
    public void setAttachPack(int attachPack) {
        this.attachPack = attachPack;
    }
    
    public Integer getHjmax() {
        return this.hjmax;
    }
    
    public void setHjmax(Integer hjmax) {
        this.hjmax = hjmax;
    }
    
    public Integer getFmsld() {
        if (this.fmsld == null) {
            return Integer.valueOf(0);
        }
        return this.fmsld;
    }
    
    public void setFmsld(Integer fmsld) {
        this.fmsld = fmsld;
    }
    
    public String getXingpans() {
        return this.xingpans;
    }
    
    public void setXingpans(String xingpans) {
        this.xingpans = xingpans;
    }
    
    public Integer getTtVictory() {
        return this.ttVictory;
    }
    
    public void setTtVictory(Integer ttVictory) {
        this.ttVictory = ttVictory;
    }
    
    public Integer getTtFail() {
        return this.ttFail;
    }
    
    public void setTtFail(Integer ttFail) {
        this.ttFail = ttFail;
    }
    
    public Integer getGradeincrease() {
        return this.gradeincrease;
    }
    
    public void setGradeincrease(Integer gradeincrease) {
        this.gradeincrease = gradeincrease;
    }
    
    public Integer getGameTimeRemaining() {
        return this.gameTimeRemaining;
    }
    
    public void setGameTimeRemaining(Integer gameTimeRemaining) {
        this.gameTimeRemaining = gameTimeRemaining;
    }
    
    public Integer getCurrentattribute() {
        return this.currentattribute;
    }
    
    public void setCurrentattribute(Integer currentattribute) {
        this.currentattribute = currentattribute;
    }
    
    public List<RoleSummoning> getShowRoleSummoningList() {
        return this.showRoleSummoningList;
    }
    
    public void setShowRoleSummoningList(List<RoleSummoning> showRoleSummoningList) {
        this.showRoleSummoningList = showRoleSummoningList;
    }
    
    public String getFlyskin() {
        return this.flyskin;
    }
    
    public void setFlyskin(String flyskin) {
        this.flyskin = flyskin;
    }
    
    public Integer getFly_id() {
        if (this.fly_id == null) {
            this.fly_id = Integer.valueOf(0);
        }
        return this.fly_id;
    }
    
    public void setFly_id(Integer fly_id) {
        this.fly_id = fly_id;
    }
    
    public String getEquipments() {
        return this.equipments;
    }
    
    public void setEquipments(String equipments) {
        this.equipments = equipments;
    }
    
    public void addEquipments() {
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isBlank(RoleData.getRoleData().getLoginResult().getEquipments())) {
            for (int i = 0; i < GoodsListFromServerUntil.getChoseGoodsList().length; ++i) {
                if (sb.length() > 0) {
                    sb.append("|");
                }
                sb.append(i);
                sb.append("=");
                if (GoodsListFromServerUntil.getChoseGoodsList()[i] != null) {
                    sb.append(GoodsListFromServerUntil.getChoseGoodsList()[i].getRgid().toString());
                }
            }
            RoleData.getRoleData().getLoginResult().setEquipments("0$套装一#" + sb.toString());
        }
        else {
            String[] split = this.equipments.split("&");
            sb = new StringBuffer(this.equipments);
            sb.append("&套装" + GoodsMsgJpanel.NUMS[split.length + 1] + "#");
            for (int j = 0; j < 12; ++j) {
                if (j > 0) {
                    sb.append("|");
                }
                sb.append(j);
                sb.append("=");
            }
            RoleData.getRoleData().getLoginResult().setEquipments(sb.toString());
        }
    }
    
    public boolean isSuitEquipment(BigDecimal rid) {
        String[][] equipmentsAll = this.getEquipmentsAll();
        if (equipmentsAll != null) {
            for (int i = 0; i < equipmentsAll.length; ++i) {
                for (int j = 1; j < equipmentsAll[i].length; ++j) {
                    if (StringUtils.isNotBlank(equipmentsAll[i][j]) && equipmentsAll[i][j].equals(rid.toString())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public String[] getEquipments(int index) {
        String[][] equipments = this.getEquipmentsAll();
        if (equipments != null && equipments.length > index) {
            return equipments[index];
        }
        return new String[13];
    }
    
    public String[][] getEquipmentsAll() {
        if (StringUtils.isBlank(this.equipments)) {
            return (String[][])null;
        }
        String[] values = this.equipments.split("\\$")[1].split("&");
        String[][] equipments = new String[values.length][13];
        for (int i = 0; i < equipments.length; ++i) {
            equipments[i][0] = values[i].split("#")[0];
            String[] vals = values[i].split("#")[1].split("\\|");
            for (int j = 1; j < equipments[i].length; ++j) {
                String[] vs = vals[j - 1].split("=");
                if (vs.length == 2) {
                    equipments[i][j] = vs[1];
                }
            }
        }
        return equipments;
    }
    
    public void equipmentsTh(int index) {
        String[] split1 = this.equipments.split("&");
        String s = split1[index].split("#")[0];
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < GoodsListFromServerUntil.getChoseGoodsList().length; ++i) {
            if (sb.length() > 0) {
                sb.append("|");
            }
            sb.append(i);
            sb.append("=");
            if (GoodsListFromServerUntil.getChoseGoodsList()[i] != null) {
                sb.append(GoodsListFromServerUntil.getChoseGoodsList()[i].getRgid().toString());
            }
        }
        split1[index] = s + "#" + sb.toString();
        String Equipments = StringUtils.join(split1, "&");
        RoleData.getRoleData().getLoginResult().setEquipments(Equipments);
    }
    
    public boolean equipmentsTh(int index, int equipmentIndex, BigDecimal rid) {
        if (index >= 0 && StringUtils.isNotBlank(this.equipments)) {
            String[] values = this.equipments.split("&");
            String[] vals = values[index].split("#");
            String[] vs = vals[1].split("\\|");
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < vs.length; ++i) {
                if (sb.length() > 0) {
                    sb.append("|");
                }
                if (equipmentIndex == i) {
                    sb.append(i);
                    sb.append("=");
                    if (rid != null) {
                        sb.append(rid);
                    }
                }
                else {
                    sb.append(vs[i]);
                }
            }
            values[index] = vals[0] + "#" + sb;
            this.equipments = StringUtils.join(values, "&");
            return true;
        }
        else {
            return false;
        }
    }
    
    public String getLianghaoValue() {
        return this.lianghaoValue;
    }
    
    public void setLianghaoValue(String lianghaoValue) {
        this.lianghaoValue = lianghaoValue;
    }
    
    public Double getBskvalue() {
        return this.Bskvalue;
    }
    
    public void setBskvalue(Double bskvalue) {
        this.Bskvalue = bskvalue;
    }
    
    public BigDecimal getTransfergold() {
        return this.Transfergold;
    }
    
    public void setTransfergold(BigDecimal transfergold) {
        this.Transfergold = transfergold;
    }
    
    public String getZhongtian() {
        return this.zhongtian;
    }
    
    public void setZhongtian(String zhongtian) {
        this.zhongtian = zhongtian;
    }
    
    public String getQinglong() {
        return this.qinglong;
    }
    
    public void setQinglong(String qinglong) {
        this.qinglong = qinglong;
    }
    
    public String getBaihu() {
        return this.baihu;
    }
    
    public void setBaihu(String baihu) {
        this.baihu = baihu;
    }
    
    public String getXuanwu() {
        return this.xuanwu;
    }
    
    public void setXuanwu(String xuanwu) {
        this.xuanwu = xuanwu;
    }
    
    public String getZhuque() {
        return this.zhuque;
    }
    
    public void setZhuque(String zhuque) {
        this.zhuque = zhuque;
    }
    
    public int getShouhu() {
        return this.shouhu;
    }
    
    public void setShouhu(int shouhu) {
        this.shouhu = shouhu;
    }
    
    public String getJiesuo() {
        return this.jiesuo;
    }
    
    public void setJiesuo(String jiesuo) {
        this.jiesuo = jiesuo;
    }
    
    public String getSh() {
        return this.sh;
    }
    
    public void setSh(String sh) {
        this.sh = sh;
    }
    
    public BigDecimal getMoneyshop() {
        return (this.Moneyshop == null) ? new BigDecimal(0) : new BigDecimal(JmSum.MZ(this.Moneyshop.longValue()));
    }
    
    public void setMoneyshop(BigDecimal moneyshop) {
        if (moneyshop.compareTo(Util.GoldUP) > 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("金币上限9999.99亿,多余的金币丢弃");
            moneyshop = Util.GoldUP;
        }
        this.Moneyshop = new BigDecimal(JmSum.ZM(moneyshop.longValue()));
    }

    public String getAchieveRecordtype(String type) {
        if (achieveRecord == null || achieveRecord.equals(""))
            return "0";
        String[] v = achieveRecord.split("\\|");
        for (int i = 0; i < v.length; i++) {
            String[] v2 = v[i].split("=");
            if (v2[0].equals(type))
                return v2[1];
        }
        return "0";
    }
    public String getAchieveRecord() {
        return achieveRecord;
    }

    public void setAchieveRecord(String achieveRecord) {
        this.achieveRecord = achieveRecord;
    }

    public String getPetdebris() {
        return petdebris;
    }
    public void setPetdebris(String petdebris) {
        this.petdebris = petdebris;
    }
    public int getDayDrawtype(String type) {
        if (dayDraw == null || dayDraw.equals(""))
            return 0;
        String[] v = dayDraw.split("\\|");
        for (int i = 0; i < v.length; i++) {
            String[] v2 = v[i].split("=");
            if (v2[0].equals(type))
                return Integer.parseInt(v2[1]);
        }
        return 0;
    }
    public String getDayDraw() {
        return dayDraw;
    }
    public void setDayDraw(String dayDraw) {
        this.dayDraw = dayDraw;
    }
    public void setDifficultrecord(String difficultrecord) {
        this.difficultrecord = difficultrecord;
    }
    public String getDifficultrecord() {
        return difficultrecord;
    }
    private int difficultLevel;
    private String difficultrecord;
    public int getDifficultLevel() {
        return difficultLevel;
    }

    public void setDifficultLevel(int difficultLevel) {
        this.difficultLevel = difficultLevel;
    }

}
