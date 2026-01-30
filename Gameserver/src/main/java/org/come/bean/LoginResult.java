package org.come.bean;

import org.apache.commons.collections.CollectionUtils;
import java.util.HashSet;
import org.come.model.ColorScheme;
import org.come.until.AllServiceUtil;
import org.apache.commons.lang.StringUtils;
import java.util.ArrayList;
import org.come.entity.Goodstable;
import come.tool.Calculation.BaseQl;
import java.util.Iterator;
import io.netty.channel.ChannelHandlerContext;
import org.come.protocol.ParamTool;
import org.come.action.IAction;
import org.come.server.GameServer;
import org.come.tool.JmSum;
import org.come.entity.RoleSummoning;
import java.util.List;
import come.tool.Calculation.BaseXingpans;
import come.tool.Calculation.BaseMeridians;
import java.util.LinkedHashMap;
import come.tool.Scene.LaborDay.LaborRole;
import come.tool.Role.RoleData;
import come.tool.newTeam.TeamRole;
import come.tool.Role.RoleShow;
import java.util.Date;
import java.math.BigDecimal;

public class LoginResult
{
    private Integer ttVictory;
    private Integer ttFail;
    private String TTJIANGLI;
    private BigDecimal ttRecord;
    private String gangname;
    private BigDecimal role_id;
    private String race_name;
    private BigDecimal user_id;
    private BigDecimal species_id;
    private BigDecimal summoning_id;
    private Integer fly_id;
    private String flyskin;
    private String pals;
    private BigDecimal gang_id;
    private Integer mount_id;
    private BigDecimal troop_id;
    private BigDecimal race_id;
    private BigDecimal booth_id;
    private Integer skill_id;
    private BigDecimal hp;
    private BigDecimal mp;
    private BigDecimal gold;
    private BigDecimal savegold;
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
    private Integer activity;
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
    private String password;
    private Integer havebaby;
    private long makeloveTime;
    private String marryObject;
    private BigDecimal babyId;
    private BigDecimal SkillS_Id;
    private String babyState;
    private Integer money;
    private String teamInfo;
    private String Skills;
    private String TimingGood;
    private int TurnAround;
    private String taskDaily;
    private String born;
    private String born1;
    private String resistance;
    private String serverMeString;
    private String taskComplete;
    private String taskData;
    private Integer DBExp;
    private String Score;
    private String Kill;
    private Date drawing;
    private Integer bangScore;
    private String skin;
    private String meridians;
    private BigDecimal Paysum;
    private BigDecimal Daypaysum;
    private BigDecimal Dayandpayorno;
    private int Dayfirstinorno;
    private int attachPack;
    private int hjmax;
    private int dianka;
    private BigDecimal Daygetorno;
    private String Vipget;
    private int lowOrHihtpack;
    private transient RoleShow roleShow;
    private transient TeamRole teamRole;
    private transient int[] dailys;
    private transient RoleData roleData;
    private transient LaborRole laborRole;
    private String gmshoptype;
    private LinkedHashMap<Integer, BaseMeridians> meridiansMap;
    private Integer fmsld;
    private String qianDao;
    private Long loginTime;
    private Long onlineTime;
    private LoginQD loginQD;
    private String xingpans;
    private LinkedHashMap<Integer, BaseXingpans> xingpansMap;
    private BigDecimal tiantipkrecord;
    private Integer tiantiyisheng;
    private Integer tiantisansheng;
    private Integer tiantilingqu;
    private Integer gradeincrease;
    private Integer gameTimeRemaining;
    private String gameStartTime;
    private Integer currentattribute;
    private List<RoleSummoning> showRoleSummoningList;
    private BigDecimal extPoint;
    private String liangHao;
    private Integer lianghaotype;
    private String lianghaoValue;
    private String lianghaoexpire;
    private Integer continueprice;
    private BigDecimal Transfergold;
    private String zhongtian;
    private String qinglong;
    private String baihu;
    private String xuanwu;
    private String zhuque;
    private int shouhu;
    private String jiesuo;
    private String sh;
    private boolean isGolem;
    private Integer golemLvl;
    private BigDecimal Moneyshop;
    private String xy;
    private String achieveRecord;
    private Boolean divineRune;
    private String equipments;
    static BigDecimal MAX;
    static BigDecimal MIN;
    private int difficultLevel;
    private String dayDraw;
    private String fuben;



    private Integer car_id;
    private String car_value;

    public LoginResult() {
        this.loginQD = new LoginQD();
        this.shouhu = 0;
        this.sh = "1-0-0|2-0-0|3-0-0|4-0-0";
        this.divineRune = Boolean.valueOf(false);
    }

    public String getFuben() {
        return fuben;
    }
    public boolean CheakFuben(String fuben) {
        if (this.fuben == null||this.fuben.equals("")) {
            return true;
        }else {
            String[] f = this.fuben.split("\\|");
            for (int i = 0; i < f.length; i++) {
//                String id = f[i].split("_")[0];
//                if (id.equals(fuben)) {
//                    return false;
//                }
                if (f[i].equals(fuben)) {
                    return false;
                }
            }
            return true;
        }
    }
    public void addfuben(String fb){
        if (fuben == null||fuben.equals("")) {
            fuben=fb;
        }else {
            StringBuffer fb1=new StringBuffer();
            fb1.append(fuben+"|");
            fb1.append(fb);
            fuben=fb1.toString();
        }
    }
    public void setFuben(String fuben) {
        this.fuben = fuben;
    }

    public boolean isGolem() {
        return this.isGolem;
    }
    
    public void setGolem(boolean golem) {
        this.isGolem = golem;
    }
    
    public Integer getGolemLvl() {
        if (this.golemLvl == null) {
            this.golemLvl = this.getGrade();
        }
        return this.golemLvl;
    }
    
    public void setGolemLvl(Integer golemLvl) {
        this.golemLvl = golemLvl;
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
    
    public int[] getDaily() {
        if (this.dailys == null) {
            this.dailys = new int[4];
            try {
                String[] v = this.taskDaily.split("\\|");
                this.dailys[0] = Integer.parseInt(v[0]);
                this.dailys[0] = Integer.parseInt(v[1]);
                this.dailys[0] = Integer.parseInt(v[2]);
                this.dailys[0] = Integer.parseInt(v[3]);
            }
            catch (Exception ex) {}
        }
        return this.dailys;
    }
    
    public String upDaily(int[] dailys) {
        if (dailys.length != 4) {
            return "0|0|0|0";
        }
        this.dailys = dailys;
        return this.taskDaily = dailys[0] + "|" + dailys[1] + "|" + dailys[2] + "|" + dailys[3];
    }
    
    public String getBattleSkin(long weapon) {
        StringBuffer buffer = new StringBuffer();
        boolean is = true;
        if (this.skin != null && !this.skin.equals("")) {
            String[] vs = this.skin.split("\\|");
            for (int i = 0; i < vs.length; ++i) {
                if (vs[i].startsWith("X") || vs[i].startsWith("P")) {
                    if (buffer.length() != 0) {
                        buffer.append("&");
                    }
                    String[] ts = vs[i].substring(1).split("_");
                    buffer.append("tx/tx");
                    buffer.append(ts[0]);
                    buffer.append("_");
                    buffer.append(ts[1]);
                }
                else if (!vs[i].startsWith("SGW")) {
                    if (vs[i].startsWith("S")) {
                        is = false;
                        if (buffer.length() != 0) {
                            buffer.append("&");
                        }
                        buffer.append(vs[i].substring(1));
                        buffer.append("_1_7");
                    }
                    else if (vs[i].startsWith("B")) {
                        if (buffer.length() != 0) {
                            buffer.append("&");
                        }
                        String cb = vs[i].substring(1);
                        buffer.append("tx/");
                        buffer.append(cb);
                        buffer.append("0_-5&tx/");
                        buffer.append(cb);
                        buffer.append("1_5");
                    }
                }
            }
        }
        if (is) {
            if (buffer.length() != 0) {
                buffer.append("&");
            }
            if (weapon != 0L) {
                buffer.append(weapon << 32 | this.species_id.longValue());
            }
            else {
                buffer.append(this.species_id);
            }
            buffer.append("_1_7");
        }
        if (this.getSkill_id() != null) {
            buffer.append("&W");
            buffer.append(this.getSkill_id());
        }
        if (this.skin != null && !this.skin.equals("")) {
            String[] vs = this.skin.split("\\|");
            for (int i = 0; i < vs.length; ++i) {
                if (vs[i].startsWith("GW") || vs[i].startsWith("SGW")) {
                    if (buffer.length() != 0) {
                        buffer.append("&");
                    }
                    buffer.append(vs[i].substring(vs[i].startsWith("GW") ? 3 : 4).replace("^", "|"));
                }
            }
        }
        return buffer.toString();
    }

    private String difficultrecord;
    public void transfer(LoginResult login) {
        this.species_id = login.species_id;
        this.summoning_id = login.summoning_id;
        this.mount_id = login.mount_id;
        this.race_id = login.race_id;
        this.race_name = login.race_name;
        this.localname = login.localname;
        this.sex = login.sex;
        this.skill_id = login.skill_id;
        this.title = login.title;
        this.x = login.x;
        this.y = login.y;
        this.mapid = login.mapid;
        this.achievement = login.achievement;
        this.contribution = login.contribution;
        this.troop_id = login.troop_id;
        this.fighting = login.fighting;
        this.savegold = login.savegold;
        this.password = login.password;
        this.hp = login.hp;
        this.mp = login.mp;
        this.bone = login.bone;
        this.spir = login.spir;
        this.power = login.power;
        this.speed = login.speed;
        this.calm = login.calm;
        this.xiuwei = login.xiuwei;
        this.extraPoint = login.extraPoint;
        if (this.getGold().compareTo(login.getGold()) > 0) {
            this.gold = login.gold;
        }
        this.experience = login.experience;
        this.grade = login.grade;
        this.TurnAround = login.TurnAround;
        this.havebaby = login.havebaby;
        this.makeloveTime = login.makeloveTime;
        this.marryObject = login.marryObject;
        this.babyId = login.babyId;
        this.SkillS_Id = login.SkillS_Id;
        this.babyState = login.babyState;
        this.teamInfo = login.teamInfo;
        this.taskDaily = login.taskDaily;
        this.resistance = login.resistance;
        this.drawing = login.drawing;
        this.skin = login.skin;
        this.hjmax = login.hjmax;
        this.meridians = login.meridians;
        this.fmsld = login.fmsld;
        this.gmshoptype = login.gmshoptype;
        if (this.getMoneyshop().compareTo(login.getMoneyshop()) > 0) {
            this.Moneyshop = login.Moneyshop;
        }
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
    
    public int getJQId(String type) {
        if (this.taskComplete == null || this.taskComplete.equals("")) {
            return 0;
        }
        String[] v = this.taskComplete.split("\\|");
        for (int i = 0; i < v.length; ++i) {
            if (v[i].startsWith(type)) {
                return Integer.parseInt(v[i].substring(1));
            }
        }
        return 0;
    }
    
    public Integer getBangScore() {
        return this.bangScore;
    }
    
    public void setBangScore(Integer bangScore) {
        this.bangScore = bangScore;
    }
    
    public String getScore() {
        return this.Score;
    }
    
    public void setScore(String score) {
        this.Score = score;
    }
    
    public String getKill() {
        return this.Kill;
    }
    
    public void setKill(String kill) {
        this.Kill = kill;
    }
    
    public Integer getDBExp() {
        return this.DBExp;
    }
    
    public void setDBExp(Integer dBExp) {
        this.DBExp = dBExp;
    }
    
    public String getGangname() {
        return this.gangname;
    }
    
    public void setGangname(String gangname) {
        this.gangname = gangname;
        if (this.roleShow != null) {
            this.roleShow.setGangname(gangname);
        }
    }
    
    public BigDecimal getRole_id() {
        return this.role_id;
    }
    
    public void setRole_id(BigDecimal role_id) {
        this.role_id = role_id;
    }
    
    public String getRace_name() {
        return this.race_name;
    }
    
    public void setRace_name(String race_name) {
        this.race_name = race_name;
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
        if (this.roleShow != null) {
            this.roleShow.setSpecies_id(species_id);
        }
        if (this.teamRole != null) {
            this.teamRole.setSpeciesId(species_id);
        }
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
        if (this.roleShow != null) {
            this.roleShow.setGang_id(gang_id);
        }
    }
    
    public Integer getMount_id() {
        return this.mount_id;
    }
    
    public void setMount_id(Integer mount_id) {
        this.mount_id = mount_id;
        if (this.roleShow != null) {
            this.roleShow.setMount_id(mount_id);
        }
    }
    
    public BigDecimal getTroop_id() {
        return this.troop_id;
    }
    
    public void setTroop_id(BigDecimal troop_id) {
        this.troop_id = troop_id;
        if (this.roleShow != null) {
            this.roleShow.setTroop_id(troop_id);
        }
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
        if (this.roleShow != null) {
            this.roleShow.setBooth_id(booth_id);
        }
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
        return new BigDecimal(JmSum.MZ(this.gold.longValue()));
    }
    
    public void setGold(BigDecimal gold) {
        if (gold.compareTo(LoginResult.MAX) > 0) {
            gold = new BigDecimal("999999999999");
        }
        else if (gold.compareTo(LoginResult.MIN) < 0 && this.userName != null && GameServer.getInlineUserNameMap().get(this.userName) != null) {
            this.gold = new BigDecimal(JmSum.ZM(gold.longValue()));
            ((IAction)ParamTool.ACTION_MAP.get("accountstop")).action((ChannelHandlerContext)GameServer.getInlineUserNameMap().get(this.userName), this.userName);
            return;
        }
        this.gold = new BigDecimal(JmSum.ZM(gold.longValue()));
    }
    
    public BigDecimal getCodecard() {
        return new BigDecimal(JmSum.MZ(this.codecard.longValue()));
    }
    
    public void setCodecard(BigDecimal codecard) {
        if (codecard.compareTo(new BigDecimal(0)) < 0 && this.userName != null && GameServer.getInlineUserNameMap().get(this.userName) != null) {
            ((IAction)ParamTool.ACTION_MAP.get("accountstop")).action((ChannelHandlerContext)GameServer.getInlineUserNameMap().get(this.userName), this.userName);
        }
        this.codecard = new BigDecimal(JmSum.ZM(codecard.longValue()));
    }
    
    public BigDecimal getExperience() {
        return new BigDecimal(JmSum.MZ(this.experience.longValue()));
    }
    
    public void setExperience(BigDecimal experience) {
        this.experience = new BigDecimal(JmSum.ZM(experience.longValue()));
    }
    
    public Integer getGrade() {
        return Integer.valueOf((int)JmSum.MZ((long)this.grade));
    }
    
    public void setGrade(Integer grade) {
        this.grade = Integer.valueOf((int)JmSum.ZM((long)grade));
        if (this.roleShow != null) {
            this.roleShow.setGrade(grade);
        }
        if (this.teamRole != null) {
            this.teamRole.setGrade((int)grade);
        }
        if (this.laborRole != null) {
            this.laborRole.setLvl((int)grade);
        }
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
        if (this.roleShow != null) {
            this.roleShow.setRolename(rolename);
        }
        if (this.teamRole != null) {
            this.teamRole.setName(rolename);
        }
        if (this.laborRole != null) {
            this.laborRole.setName(rolename);
        }
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
        if (this.roleShow != null) {
            this.roleShow.setTitle(title);
        }
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
    
    public Long getX() {
        return this.x;
    }
    
    public void setX(Long x) {
        this.x = x;
        if (this.roleShow != null) {
            this.roleShow.setX(x);
        }
    }
    
    public Long getY() {
        return this.y;
    }
    
    public void setY(Long y) {
        this.y = y;
        if (this.roleShow != null) {
            this.roleShow.setY(y);
        }
    }
    
    public Long getMapid() {
        return this.mapid;
    }
    
    public void setMapid(Long mapid) {
        this.mapid = mapid;
        if (this.roleShow != null) {
            this.roleShow.setMapid(mapid);
        }
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
    
    public Integer getFighting() {
        return this.fighting;
    }
    
    public void setFighting(Integer fighting) {
        this.fighting = fighting;
        if (this.roleShow != null) {
            this.roleShow.setFighting(fighting);
        }
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
    
    public long getMakeloveTime() {
        return this.makeloveTime;
    }
    
    public void setMakeloveTime(long makeloveTime) {
        this.makeloveTime = makeloveTime;
    }
    
    public String getMarryObject() {
        return this.marryObject;
    }
    
    public void setMarryObject(String marryObject) {
        this.marryObject = marryObject;
    }
    
    public BigDecimal getBabyId() {
        return this.babyId;
    }
    
    public void setBabyId(BigDecimal babyId) {
        this.babyId = babyId;
    }
    
    public BigDecimal getSkillS_Id() {
        return this.SkillS_Id;
    }
    
    public void setSkillS_Id(BigDecimal s) {
    }
    
    public String getBabyState() {
        return this.babyState;
    }
    
    public void setBabyState(String babyState) {
        this.babyState = babyState;
    }
    
    public Integer getMoney() {
        return this.money;
    }
    
    public void setMoney(Integer money) {
        this.money = money;
    }
    
    public String getTeamInfo() {
        return this.teamInfo;
    }
    
    public void setTeamInfo(String teamInfo) {
        this.teamInfo = teamInfo;
        if (this.roleShow != null) {
            this.roleShow.setTeamInfo(teamInfo);
        }
    }
    
    public String getSkills() {
        return this.Skills;
    }
    
    public void setSkills(String skills) {
        this.Skills = skills;
    }
    
    public String getTimingGood() {
        return this.TimingGood;
    }
    
    public void setTimingGood(String timingGood) {
        this.TimingGood = timingGood;
    }
    
    public int getTurnAround() {
        return this.TurnAround;
    }
    
    public void setTurnAround(int turnAround) {
        this.TurnAround = turnAround;
        if (this.roleShow != null) {
            this.roleShow.setTurnAround(turnAround);
        }
    }
    
    public String getTaskDaily() {
        return this.taskDaily;
    }
    
    public void setTaskDaily(String taskDaily) {
        this.taskDaily = taskDaily;
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
    
    public String getServerMeString() {
        return this.serverMeString;
    }
    
    public void setServerMeString(String serverMeString) {
        this.serverMeString = serverMeString;
    }
    
    public String getTaskComplete() {
        if (this.taskComplete == null) {
            this.taskComplete = "";
        }
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
    
    public Integer getActivity() {
        return this.activity;
    }
    
    public void setActivity(Integer activity) {
        this.activity = activity;
    }
    
    public Date getDrawing() {
        return this.drawing;
    }
    
    public void setDrawing(Date drawing) {
        this.drawing = drawing;
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
    
    public String getTeam() {
        if (this.teamInfo == null || this.teamInfo.equals("") || this.teamInfo.equals("|")) {
            return this.rolename;
        }
        return this.teamInfo;
    }
    
    public Integer getSkill_id() {
        return this.skill_id;
    }
    
    public void setSkill_id(Integer skill_id) {
        this.skill_id = skill_id;
        if (this.roleShow != null) {
            this.roleShow.setSkill_id(skill_id);
        }
    }
    
    public String getSkin() {
        return this.skin;
    }
    
    public void setSkin(String skin) {
        this.skin = skin;
        if (this.roleShow != null) {
            this.roleShow.setSkin(skin);
        }
    }
    
    public Integer getCalm() {
        return Integer.valueOf((int)JmSum.MZ((long)this.calm));
    }
    
    public void setCalm(Integer calm) {
        this.calm = Integer.valueOf((int)JmSum.ZM((long)calm));
    }
    
    public Integer getXiuwei() {
        return Integer.valueOf((int)JmSum.MZ((long)this.xiuwei));
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
    
    public RoleShow getRoleShow() {
        return this.roleShow;
    }
    
    public void setRoleShow(RoleShow roleShow) {
        this.roleShow = roleShow;
    }
    
    public TeamRole getTeamRole() {
        if (this.teamRole == null) {
            this.teamRole = new TeamRole(this);
        }
        return this.teamRole;
    }
    
    public void initTeamRole(TeamRole teamRole) {
        (this.teamRole = teamRole).upTeamRole(this);
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
    
    public String setResistance(int type, String data) {
        String QZ = (type == 1) ? "X" : "D";
        if (this.resistance == null || this.resistance.equals("")) {
            if (data == null) {
                return "";
            }
            return this.resistance = QZ + data;
        }
        else {
            String[] vs = this.resistance.split("\\|");
            int i = 0;
            while (i < vs.length) {
                if (vs[i].startsWith(QZ)) {
                    vs[i] = null;
                    break;
                }
                else {
                    ++i;
                }
            }
            StringBuffer buffer = new StringBuffer();
            for (int j = 0; j < vs.length; ++j) {
                if (vs[j] != null) {
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append(vs[j]);
                }
            }
            if (data != null) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append(QZ);
                buffer.append(data);
            }
            return this.resistance = buffer.toString();
        }
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
    
    public int getExtPointInt() {
        if (this.extPoint == null || this.extPoint.compareTo(BigDecimal.ZERO) == 0) {
            return 0;
        }
        return this.extPoint.intValue();
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
    
    public void removeVipget(String type) {
        if (this.Vipget == null || this.Vipget.equals("")) {
            return;
        }
        StringBuffer buffer = new StringBuffer();
        String[] vs = this.Vipget.split("&&");
        for (int i = 0; i < vs.length; ++i) {
            if (vs[i].split("=")[0].equals(type)) {
                vs[i] = null;
            }
            else {
                if (buffer.length() != 0) {
                    buffer.append("&&");
                }
                buffer.append(vs[i]);
            }
        }
        if (buffer.length() == 0) {
            this.Vipget = null;
        }
        else {
            this.Vipget = buffer.toString();
        }
    }
    
    public int getLowOrHihtpack() {
        return this.lowOrHihtpack;
    }
    
    public void setLowOrHihtpack(int lowOrHihtpack) {
        this.lowOrHihtpack = lowOrHihtpack;
    }
    
    public int getDayfirstinorno() {
        return this.Dayfirstinorno;
    }
    
    public String getPals() {
        return this.pals;
    }
    
    public void setPals(String pals) {
        this.pals = pals;
    }
    
    public void setDayfirstinorno(int dayfirstinorno) {
        this.Dayfirstinorno = dayfirstinorno;
    }
    
    public RoleData getRoleData() {
        return this.roleData;
    }
    
    public void setRoleData(RoleData roleData) {
        this.roleData = roleData;
    }
    
    public void setLaborRole(LaborRole laborRole) {
        this.laborRole = laborRole;
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
    
    public double getMeridiansValue(String key) {
        if (this.meridiansMap == null) {
            return 0.0;
        }
        double v = 0.0;
        for (BaseMeridians meridian : this.meridiansMap.values()) {
            if (meridian.getKey().equals(key)) {
                v += meridian.getKeyValue();
            }
        }
        return v;
    }
    
    public BaseQl[] getBaseQl() {
        if (this.meridiansMap == null || this.meridiansMap.size() == 0) {
            return null;
        }
        BaseQl[] base = new BaseQl[this.meridiansMap.size()];
        for (int i = 0; i < this.meridiansMap.size(); ++i) {
            base[i] = new BaseQl(((BaseMeridians)this.meridiansMap.get(Integer.valueOf(i + 1))).getKey(), ((BaseMeridians)this.meridiansMap.get(Integer.valueOf(i + 1))).getKeyValue());
        }
        return base;
    }
    
    public Integer getFmsld() {
        return this.fmsld;
    }
    
    public void setFmsld(Integer fmsld) {
        this.fmsld = fmsld;
    }
    
    public String getXingpans() {
        return this.xingpans;
    }
    
    public void setXingpans(String xingpans) {
        this.upXingpans(this.xingpans = xingpans);
    }
    
    public void setXingpans(int num, BaseXingpans xingpans) {
        this.xingpansMap.put(Integer.valueOf(num), xingpans);
        StringBuilder temp = new StringBuilder();
        for (BaseXingpans xingpan : this.xingpansMap.values()) {
            if (temp.length() > 0) {
                temp.append("|");
            }
            temp.append(xingpan.toString());
        }
        this.xingpans = temp.toString();
    }
    
    public BaseXingpans getXingpans(int num) {
        if (this.xingpansMap == null) {
            this.xingpansMap = new LinkedHashMap<>();
        }
        return (BaseXingpans)this.xingpansMap.get(Integer.valueOf(num));
    }
    
    public void upXingpans(String xingpans) {
        if (this.xingpansMap == null) {
            this.xingpansMap = new LinkedHashMap<>();
        }
        if (xingpans == null || xingpans.equals("")) {
            return;
        }
        BaseXingpans baseXingpansR = null;
        String[] vs = xingpans.split("\\|");
        for (int i = 0; i < vs.length; ++i) {
            String[] vss = vs[i].split("_");
            if (vss.length == 7) {
                int bh = Integer.parseInt(vss[0]);
                baseXingpansR = new BaseXingpans(bh, vss[1], Integer.parseInt(vss[2]), vss[3], Double.parseDouble(vss[4]), vss[5], Double.parseDouble(vss[6]));
                this.xingpansMap.put(Integer.valueOf(bh), baseXingpansR);
            }
        }
    }
    
    public BaseQl[] getBaseQl1() {
        if (this.xingpans == null) {
            return null;
        }
        String[] vs = this.xingpans.split("\\|");
        BaseQl[] base = new BaseQl[vs.length * 2];
        for (int i = 0; i < base.length; ++i) {
            if (i < vs.length) {
                String[] vss = vs[i].split("_");
                int bh = Integer.parseInt(vss[0]);
                base[i] = new BaseQl(((BaseXingpans)this.xingpansMap.get(Integer.valueOf(bh))).getKey1(), ((BaseXingpans)this.xingpansMap.get(Integer.valueOf(bh))).getKeyValue1());
            }
            else {
                String[] vss = vs[i - vs.length].split("_");
                int bh = Integer.parseInt(vss[0]);
                base[i] = new BaseQl(((BaseXingpans)this.xingpansMap.get(Integer.valueOf(bh))).getKey(), ((BaseXingpans)this.xingpansMap.get(Integer.valueOf(bh))).getKeyValue());
            }
        }
        return base;
    }
    
    public double getXingpansValue(String key) {
        if (this.xingpansMap == null) {
            return 0.0;
        }
        double v = 0.0;
        for (BaseXingpans xingpan : this.xingpansMap.values()) {
            if (xingpan.getKey().equals(key)) {
                v += xingpan.getKeyValue();
            }
        }
        return v;
    }
    
    public String getQianDao() {
        return this.qianDao;
    }
    
    public LoginQD getQianDaoObject() {
        if (this.loginQD == null) {
            this.loginQD = new LoginQD();
        }
        this.loginQD.init();
        return this.loginQD;
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
    
    public LoginQD getLoginQD() {
        return this.loginQD;
    }
    
    public void setLoginQD(LoginQD loginQD) {
        this.loginQD = loginQD;
    }
    
    public String getGmshoptype() {
        return this.gmshoptype;
    }
    
    public void setGmshoptype(String gmshoptype) {
        this.gmshoptype = gmshoptype;
    }
    
    public void saveQiandao() {
        this.qianDao = this.loginQD.end();
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
    
    public String getTTJIANGLI() {
        return this.TTJIANGLI;
    }
    
    public void setTTJIANGLI(String tTJIANGLI) {
        this.TTJIANGLI = tTJIANGLI;
    }
    
    public BigDecimal getTtRecord() {
        return this.ttRecord;
    }
    
    public void setTtRecord(BigDecimal ttRecord) {
        this.ttRecord = ttRecord;
    }
    
    public BigDecimal getTiantipkrecord() {
        return this.tiantipkrecord;
    }
    
    public void setTiantipkrecord(BigDecimal tiantipkrecord) {
        this.tiantipkrecord = tiantipkrecord;
    }
    
    public Integer getTiantiyisheng() {
        return this.tiantiyisheng;
    }
    
    public void setTiantiyisheng(Integer tiantiyisheng) {
        this.tiantiyisheng = tiantiyisheng;
    }
    
    public Integer getTiantisansheng() {
        return this.tiantisansheng;
    }
    
    public void setTiantisansheng(Integer tiantisansheng) {
        this.tiantisansheng = tiantisansheng;
    }
    
    public Integer getTiantilingqu() {
        return this.tiantilingqu;
    }
    
    public void setTiantilingqu(Integer tiantilingqu) {
        this.tiantilingqu = tiantilingqu;
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
    
    public String getGameStartTime() {
        return this.gameStartTime;
    }
    
    public void setGameStartTime(String gameStartTime) {
        this.gameStartTime = gameStartTime;
    }
    
    public Integer getCurrentattribute() {
        return this.currentattribute;
    }
    
    public void setCurrentattribute(Integer currentattribute) {
        this.currentattribute = currentattribute;
    }
    
    public Integer getFly_id() {
        return this.fly_id;
    }
    
    public void setFly_id(Integer fly_id) {
        this.fly_id = fly_id;
        if (this.roleShow != null) {
            this.roleShow.setFly_id(fly_id);
        }
    }
    public Integer getCar_id() {
        return car_id;
    }

    public void setCar_id(Integer car_id) {
        this.car_id = car_id;
        if (this.roleShow != null) {
            this.roleShow.setCar_id(car_id);
        }
    }

    public String getCar_value() {
        return car_value;
    }

    public void setCar_Value(String car_value) {
        this.car_value = car_value;
    }

    public String getFlyskin() {
        return this.flyskin;
    }
    
    public void setFlyskin(String flyskin) {
        this.flyskin = flyskin;
        if (this.roleShow != null) {
            this.roleShow.setFlyskin(flyskin);
        }
    }
    
    public List<RoleSummoning> getShowRoleSummoningList() {
        return this.showRoleSummoningList;
    }
    
    public void setShowRoleSummoningList(List<RoleSummoning> showRoleSummoningList) {
        this.showRoleSummoningList = showRoleSummoningList;
        if (this.roleShow != null) {
            this.roleShow.setShowRoleSummoningList(showRoleSummoningList);
        }
    }
    
    public Boolean getDivineRune() {
        return this.divineRune;
    }
    
    public void setDivineRune(Boolean divineRune) {
        this.divineRune = divineRune;
    }
    
    public String getXY() {
        return this.xy;
    }
    
    public void setXY(String XY) {
        this.xy = XY;
    }
    
    public String getEquipments() {
        return this.equipments;
    }
    
    public void setEquipments(String equipments) {
        this.equipments = equipments;
    }
    
    public List<Goodstable> getEquipmentsByIndex(Integer index) {
        List<Goodstable> goods = new ArrayList<>();
        if (StringUtils.isNotBlank(this.equipments)) {
            String[] split = this.equipments.split("&")[(int)index].split("#");
            if (split.length == 2) {
                String[] split2;
                for (String s : split2 = split[1].split("\\|")) {
                    String[] split3 = s.split("=");
                    if (split3.length == 2) {
                        Goodstable goodstable = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(split3[1]));
                        goods.add(goodstable);
                    }
                }
                return goods;
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }
    
    public void addEquipments(RoleData roleData) {
        String[] NUMS = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isBlank(this.equipments)) {
            for (int i = 0; i < 12; ++i) {
                if (sb.length() > 0) {
                    sb.append("|");
                }
                sb.append(i);
                sb.append("=");
                if (roleData.getGoodEquip()[i] != null) {
                    sb.append(roleData.getGoodEquip()[i].toString());
                }
            }
            this.setEquipments("0$套装一#" + sb.toString());
        }
        else {
            String[] split = this.equipments.split("&");
            sb = new StringBuffer(this.equipments);
            sb.append("&套装" + NUMS[split.length + 1] + "#");
            for (int j = 0; j < 12; ++j) {
                if (j > 0) {
                    sb.append("|");
                }
                sb.append(j);
                sb.append("=");
            }
            this.setEquipments(sb.toString());
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
        return new String[12];
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
    
    public String getLianghaoValue() {
        if (StringUtils.isBlank(this.lianghaoValue)) {
            return "";
        }
        return this.lianghaoValue;
    }
    
    public void setLianghaoValue(String lianghaoValue) {
        this.lianghaoValue = lianghaoValue;
    }
    
    public String getBorn1() {
        return this.born1;
    }
    
    public void setBorn1(String born1) {
        this.born1 = born1;
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
        if (moneyshop.compareTo(LoginResult.MAX) > 0) {
            moneyshop = new BigDecimal("999999999999");
        }
        else if (moneyshop.compareTo(LoginResult.MIN) < 0 && this.userName != null && GameServer.getInlineUserNameMap().get(this.userName) != null) {
            this.Moneyshop = new BigDecimal(JmSum.ZM(moneyshop.longValue()));
            ((IAction)ParamTool.ACTION_MAP.get("accountstop")).action((ChannelHandlerContext)GameServer.getInlineUserNameMap().get(this.userName), this.userName);
            return;
        }
        this.Moneyshop = new BigDecimal(JmSum.ZM(moneyshop.longValue()));
    }
    
    public static String getRoleId(String roleId, LoginResult loginResult) {
        BigDecimal colorId = loginResult.getScoretype("光武颜色");
        if (colorId == null || colorId.intValue() == 0) {
            colorId = new BigDecimal(1);
        }
        ColorScheme color = (ColorScheme)GameServer.getAllListColor().get(colorId.intValue() - 1);
        String skin = "";
        int n = -1;
        switch (roleId.hashCode()) {
            case 29561761: {
                if (roleId.equals("猎魂引")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 38281421: {
                if (roleId.equals("飞剑侠")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 27183226: {
                if (roleId.equals("武尊神")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 31961340: {
                if (roleId.equals("红挑友")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 39066655: {
                if (roleId.equals("骨精灵")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
            case 40065607: {
                if (roleId.equals("龙战将")) {
                    n = 5;
                    break;
                }
                else {
                    break;
                }
            }
            case 33773636: {
                if (roleId.equals("虎头怪")) {
                    n = 6;
                    break;
                }
                else {
                    break;
                }
            }
            case 30586698: {
                if (roleId.equals("神天兵")) {
                    n = 7;
                    break;
                }
                else {
                    break;
                }
            }
            case 20906515: {
                if (roleId.equals("剑侠客")) {
                    n = 8;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                skin = "500800";
                break;
            }
            case 1: {
                skin = "500799";
                break;
            }
            case 2: {
                skin = "500801";
                break;
            }
            case 3: {
                skin = "500220";
                break;
            }
            case 4: {
                skin = "500802";
                break;
            }
            case 5: {
                skin = "500803";
                break;
            }
            case 6: {
                skin = "500804";
                break;
            }
            case 7: {
                skin = "500805";
                break;
            }
            case 8: {
                skin = "600034";
                break;
            }
        }
        if (color != null) {
            String replace = color.getValue().replace("|", "^");
            skin = skin + "#" + replace;
        }
        return skin;
    }
    
    static {
        LoginResult.MAX = new BigDecimal("999999999999");
        LoginResult.MIN = new BigDecimal(0);
    }
    public String getAchieveRecordtype(String type) {
        if (this.achieveRecord == null || this.achieveRecord.equals(""))
            return "0";
        String[] v = this.achieveRecord.split("\\|");
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
    public String getDifficultrecord() {
        return difficultrecord;
    }

    public void setDifficultrecord(String difficultrecord) {
        this.difficultrecord = difficultrecord;
    }
    public class LoginQD
    {
        private HashSet<Integer> ed;
        private HashSet<Integer> lq;
        
        public LoginQD() {
            this.ed = new HashSet<>();
            this.lq = new HashSet<>();
        }
        
        private void init() {
            if (StringUtils.isEmpty(LoginResult.this.qianDao)) {
                return;
            }
            String[] split = LoginResult.this.qianDao.split("&");
            String[] eds = split[0].split("=");
            if (eds.length > 1) {
                this.ed = new HashSet<>();
                for (String s : eds[1].split(",")) {
                    this.ed.add(Integer.valueOf(s));
                }
            }
            String[] lqs = split[1].split("=");
            if (lqs.length > 1) {
                this.lq = new HashSet<>();
                for (String s2 : lqs[1].split(",")) {
                    this.lq.add(Integer.valueOf(s2));
                }
            }
        }
        
        private String end() {
            if (CollectionUtils.isEmpty(this.ed) && CollectionUtils.isEmpty(this.lq)) {
                return null;
            }
            StringBuffer os = new StringBuffer("ED=");
            if (CollectionUtils.isNotEmpty(this.ed)) {
                this.ed.forEach(e/* java.lang.Integer, */ -> os.append(e + ","));
            }
            os.append("&LQ=");
            if (CollectionUtils.isNotEmpty(this.lq)) {
                this.lq.forEach(e/* java.lang.Integer, */ -> os.append(e + ","));
            }
            return os.toString().substring(0, os.length() - 1);
        }
        
        public HashSet<Integer> getEd() {
            return this.ed;
        }
        
        public HashSet<Integer> getLq() {
            return this.lq;
        }
        
        public void setEd(HashSet<Integer> ed) {
            this.ed = ed;
        }
        
        public void setLq(HashSet<Integer> lq) {
            this.lq = lq;
        }
    }
    public int getDifficultLevel() {
        return difficultLevel;
    }

    public void setDifficultLevel(int difficultLevel) {
        this.difficultLevel = difficultLevel;
    }
    public String getDayDraw() {
        return dayDraw;
    }

    public void setDayDraw(String dayDraw) {
        this.dayDraw = dayDraw;
    }
    public int getDayDrawtype(String type) {
        if (this.dayDraw == null || this.dayDraw.equals(""))
            return 0;
        String[] v = this.dayDraw.split("\\|");
        for (int i = 0; i < v.length; i++) {
            String[] v2 = v[i].split("=");
            if (v2[0].equals(type))
                return Integer.parseInt(v2[1]);
        }
        return 0;
    }
}
