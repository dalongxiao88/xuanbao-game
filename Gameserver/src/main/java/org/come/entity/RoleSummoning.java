package org.come.entity;

import come.tool.FightingData.Ql;
import come.tool.Mixdeal.AnalysisString;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import org.come.model.Configure;
import org.come.server.GameServer;
import org.come.tool.JmSum;
import come.tool.Battle.BattleMixDeal;
import com.gl.util.LingXiUtil;
import java.util.List;
import java.math.BigDecimal;

public class RoleSummoning implements Cloneable
{
    private String summoningid;
    private String summoningname;
    private String summoningskin;
    private String ssn;
    private String quality;
    private String stye;
    private int hp;
    private int mp;
    private int ap;
    private int sp;
    private String growlevel;
    private String resistance;
    private String skill;
    private String gold;
    private String wood;
    private String soil;
    private String water;
    private String fire;
    private String surplusTimes;
    private String ColorScheme;
    private BigDecimal roleid;
    private Integer bone;
    private Integer spir;
    private Integer power;
    private Integer speed;
    private Integer calm;
    private Integer grade;
    private BigDecimal exp;
    private Integer faithful;
    private Long friendliness;
    private BigDecimal sid;
    private int basishp;
    private int basismp;
    private Integer openSeal;
    private Integer openql;
    private String innerGoods;
    private int dragon;
    private int spdragon;
    private int czjjd;
    private String petSkills;
    private String petQlSkills;
    private int turnRount;
    private String NedanResistance;
    private int revealNum;
    private int flyupNum;
    private String beastSkills;
    private String fourattributes;
    private String skillData;
    private String zqk;
    private String lyk;
    private String glyk;
    private int alchemynum;
    private int galchemynum;
    private int growUpDanNum;
    private int draC;
    private int trainNum;
    private int petlock;
    private String lingxi;
    private Integer deposit;
    private boolean show;
    private String petSkillswl;
    private String petSkilllock;
    private int talentLvl;
    private List<String> otherInfo;
    private int extPoint;
    private String xy;
    private BigDecimal commodityId;
    private int dicenum;
    private Integer foPenSeal;
    
    public RoleSummoning() {
        this.bone = Integer.valueOf(0);
        this.spir = Integer.valueOf(0);
        this.power = Integer.valueOf(0);
        this.speed = Integer.valueOf(0);
        this.grade = Integer.valueOf(0);
        this.openSeal = Integer.valueOf(1);
        this.openql = Integer.valueOf(0);
        this.foPenSeal = Integer.valueOf(3);
    }
    
    public List<String> getOtherInfo() {
        return this.otherInfo;
    }
    
    public void setOtherInfo(List<String> otherInfo) {
        this.otherInfo = otherInfo;
    }
    
    public int getExtPoint() {
        return this.extPoint;
    }
    
    public void setExtPoint(int extPoint) {
        this.extPoint = extPoint;
    }
    
    public void addqm(int v) {
        this.setFriendliness(Long.valueOf((long)this.getFriendliness() + (long)v));
    }
    
    public int getSI2(String type) {
        if (this.fourattributes == null || this.fourattributes.equals("")) {
            return 0;
        }
        String[] v = this.fourattributes.split("\\|");
        for (int i = 0; i < v.length; ++i) {
            String[] v2 = v[i].split("=");
            if (v2[0].equals(type)) {
                return (int)Double.parseDouble(v2[1]);
            }
        }
        return 0;
    }
    
    public void getLX(int[] pets) {
        if (this.lingxi == null || this.lingxi.equals("")) {
            return;
        }
        String[] lx = this.lingxi.split("&");
        String[] v = lx[3].split("=")[1].split("\\|");
        for (int i = 0; i < v.length; ++i) {
            String[] v2 = v[i].split("_");
            int lvl = Integer.parseInt(v2[1]);
            if (lvl != 0) {
                if (v2[0].equals("11001")) {
                    int n = 0;
                    pets[n] += LingXiUtil.getNumberBySkillId(v2[0], 1, lvl);
                }
                else if (v2[0].equals("11002")) {
                    int n2 = 1;
                    pets[n2] += LingXiUtil.getNumberBySkillId(v2[0], 1, lvl);
                }
                else if (v2[0].equals("11003")) {
                    int n3 = 2;
                    pets[n3] += LingXiUtil.getNumberBySkillId(v2[0], 1, lvl);
                }
                else if (v2[0].equals("11004")) {
                    int n4 = 3;
                    pets[n4] += LingXiUtil.getNumberBySkillId(v2[0], 1, lvl);
                }
            }
        }
    }
    
    public void SB() {
        this.setHp(this.hp);
        this.setMp(this.mp);
        this.setAp(this.ap);
        this.setSp(this.sp);
    }
    
    public int getPetlock() {
        return this.petlock;
    }
    
    public void setPetlock(int petlock) {
        this.petlock = petlock;
    }
    
    public int getTrainNum() {
        return this.trainNum;
    }
    
    public void setTrainNum(int trainNum) {
        this.trainNum = trainNum;
    }
    
    public int getTurnRount() {
        return BattleMixDeal.petTurnRount((int)this.getGrade());
    }
    
    public void setTurnRount(int turnRount) {
        this.turnRount = turnRount;
    }
    
    public BigDecimal getSid() {
        return this.sid;
    }
    
    public void setSid(BigDecimal sid) {
        this.sid = sid;
    }
    
    public String getSummoningid() {
        return this.summoningid;
    }
    
    public void setSummoningid(String summoningid) {
        this.summoningid = summoningid;
    }
    
    public String getSummoningskin() {
        return this.summoningskin;
    }
    
    public void setSummoningskin(String summoningskin) {
        this.summoningskin = summoningskin;
    }
    
    public String getStye() {
        return this.stye;
    }
    
    public void setStye(String stye) {
        this.stye = stye;
    }
    
    public int getBasishp() {
        return this.basishp;
    }
    
    public void setBasishp(int basishp) {
        this.basishp = basishp;
    }
    
    public int getBasismp() {
        return this.basismp;
    }
    
    public void setBasismp(int basismp) {
        this.basismp = basismp;
    }
    
    public String getGrowlevel() {
        return this.growlevel;
    }
    
    public void setGrowlevel(String growlevel) {
        this.growlevel = growlevel;
    }
    
    public String getResistance() {
        return this.resistance;
    }
    
    public void setResistance(String resistance) {
        this.resistance = resistance;
    }
    
    public String getSkill() {
        return this.skill;
    }
    
    public void setSkill(String skill) {
        this.skill = skill;
    }
    
    public String getGold() {
        if (this.gold == null || this.gold.equals("")) {
            return "0";
        }
        return this.gold;
    }
    
    public void setGold(String gold) {
        this.gold = gold;
    }
    
    public String getWood() {
        if (this.wood == null || this.wood.equals("")) {
            return "0";
        }
        return this.wood;
    }
    
    public void setWood(String wood) {
        this.wood = wood;
    }
    
    public String getSoil() {
        if (this.soil == null || this.soil.equals("")) {
            return "0";
        }
        return this.soil;
    }
    
    public void setSoil(String soil) {
        this.soil = soil;
    }
    
    public String getWater() {
        if (this.water == null || this.water.equals("")) {
            return "0";
        }
        return this.water;
    }
    
    public void setWater(String water) {
        this.water = water;
    }
    
    public String getFire() {
        if (this.fire == null || this.fire.equals("")) {
            return "0";
        }
        return this.fire;
    }
    
    public void setFire(String fire) {
        this.fire = fire;
    }
    
    public String getSummoningname() {
        return this.summoningname;
    }
    
    public void setSummoningname(String summoningname) {
        this.summoningname = summoningname;
    }
    
    public String getSsn() {
        return this.ssn;
    }
    
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
    
    public BigDecimal getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }
    
    public Integer getFaithful() {
        return this.faithful;
    }
    
    public void setFaithful(Integer faithful) {
        this.faithful = faithful;
    }
    
    public Integer getOpenSeal() {
        return this.openSeal;
    }
    
    public void setOpenSeal(Integer openSeal) {
        this.openSeal = openSeal;
    }
    
    public Integer getOpenql() {
        return this.openql;
    }
    
    public void setOpenql(Integer openql) {
        this.openql = openql;
    }
    
    public String getInnerGoods() {
        return this.innerGoods;
    }
    
    public void setInnerGoods(String innerGoods) {
        this.innerGoods = innerGoods;
    }
    
    public int getDragon() {
        return this.dragon;
    }
    
    public void setDragon(int dragon) {
        this.dragon = dragon;
    }
    
    public int getSpdragon() {
        return this.spdragon;
    }
    
    public void setSpdragon(int spdragon) {
        this.spdragon = spdragon;
    }
    
    public int getCzjjd() {
        return this.czjjd;
    }
    
    public void setCzjjd(int czjjd) {
        this.czjjd = czjjd;
    }
    
    public String getPetSkills() {
        return this.petSkills;
    }
    
    public void setPetSkills(String petSkills) {
        this.petSkills = petSkills;
    }
    
    public String getPetQlSkills() {
        return this.petQlSkills;
    }
    
    public void setPetQlSkills(String petQlSkills) {
        this.petQlSkills = petQlSkills;
    }
    
    public String getNedanResistance() {
        return this.NedanResistance;
    }
    
    public void setNedanResistance(String nedanResistance) {
        this.NedanResistance = nedanResistance;
    }
    
    public int getRevealNum() {
        return this.revealNum;
    }
    
    public void setRevealNum(int revealNum) {
        this.revealNum = revealNum;
    }
    
    public int getFlyupNum() {
        return this.flyupNum;
    }
    
    public void setFlyupNum(int flyupNum) {
        this.flyupNum = flyupNum;
    }
    
    public String getBeastSkills() {
        return this.beastSkills;
    }
    
    public void setBeastSkills(String beastSkills) {
        this.beastSkills = beastSkills;
    }
    
    public String getFourattributes() {
        return this.fourattributes;
    }
    
    public void setFourattributes(String fourattributes) {
        this.fourattributes = fourattributes;
    }
    
    public String getSkillData() {
        return this.skillData;
    }
    
    public void setSkillData(String skillData) {
        this.skillData = skillData;
    }
    
    public String getZqk() {
        return this.zqk;
    }
    
    public void setZqk(String zqk) {
        this.zqk = zqk;
    }
    
    public String getLyk() {
        return this.lyk;
    }
    
    public void setLyk(String lyk) {
        this.lyk = lyk;
    }
    
    public String getGlyk() {
        return this.glyk;
    }
    
    public void setGlyk(String glyk) {
        this.glyk = glyk;
    }
    
    public int getAlchemynum() {
        return this.alchemynum;
    }
    
    public void setAlchemynum(int alchemynum) {
        this.alchemynum = alchemynum;
    }
    
    public int getGAlchemynum() {
        return this.galchemynum;
    }
    
    public void setGAlchemynum(int galchemynum) {
        this.galchemynum = galchemynum;
    }
    
    public int getGrowUpDanNum() {
        return this.growUpDanNum;
    }
    
    public void setGrowUpDanNum(int growUpDanNum) {
        this.growUpDanNum = growUpDanNum;
    }
    
    public String getColorScheme() {
        return this.ColorScheme;
    }
    
    public void setColorScheme(String colorScheme) {
        this.ColorScheme = colorScheme;
    }
    
    public String getLingxi() {
        return this.lingxi;
    }
    
    public void setLingxi(String lingxi) {
        this.lingxi = lingxi;
    }
    
    public int getDraC() {
        return this.draC;
    }
    
    public void setDraC(int draC) {
        this.draC = draC;
    }
    
    public int getHp() {
        return (int)JmSum.MZ((long)this.hp);
    }
    
    public void setHp(int hp) {
        this.hp = (int)JmSum.ZM((long)hp);
    }
    
    public int getMp() {
        return (int)JmSum.MZ((long)this.mp);
    }
    
    public void setMp(int mp) {
        this.mp = (int)JmSum.ZM((long)mp);
    }
    
    public int getAp() {
        return (int)JmSum.MZ((long)this.ap);
    }
    
    public void setAp(int ap) {
        this.ap = (int)JmSum.ZM((long)ap);
    }
    
    public int getSp() {
        return (int)JmSum.MZ((long)this.sp);
    }
    
    public void setSp(int sp) {
        this.sp = (int)JmSum.ZM((long)sp);
    }
    
    public Integer getBone() {
        return Integer.valueOf((int)JmSum.MZ((long)(int)this.bone));
    }
    
    public void setBone(Integer bone) {
        this.bone = Integer.valueOf((int)JmSum.ZM((long)(int)bone));
    }
    
    public Integer getSpir() {
        return Integer.valueOf((int)JmSum.MZ((long)(int)this.spir));
    }
    
    public void setSpir(Integer spir) {
        this.spir = Integer.valueOf((int)JmSum.ZM((long)(int)spir));
    }
    
    public Integer getPower() {
        return Integer.valueOf((int)JmSum.MZ((long)(int)this.power));
    }
    
    public void setPower(Integer power) {
        this.power = Integer.valueOf((int)JmSum.ZM((long)(int)power));
    }
    
    public Integer getSpeed() {
        return Integer.valueOf((int)JmSum.MZ((long)(int)this.speed));
    }
    
    public void setSpeed(Integer speed) {
        this.speed = Integer.valueOf((int)JmSum.ZM((long)(int)speed));
    }
    
    public Integer getCalm() {
        if (this.calm == null) {
            this.setCalm(Integer.valueOf(0));
        }
        return Integer.valueOf((int)JmSum.MZ((long)(int)this.calm));
    }
    
    public void setCalm(Integer calm) {
        this.calm = Integer.valueOf((int)JmSum.ZM((long)(int)calm));
    }
    
    public Integer getGrade() {
        return Integer.valueOf((int)JmSum.MZ((long)(int)this.grade));
    }
    
    public void setGrade(Integer grade) {
        this.grade = Integer.valueOf((int)JmSum.ZM((long)(int)grade));
    }
    
    public BigDecimal getExp() {
        return new BigDecimal(JmSum.MZ(this.exp.longValue()));
    }
    
    public void setExp(BigDecimal exp) {
        this.exp = new BigDecimal(JmSum.ZM(exp.longValue()));
    }
    
    public Long getFriendliness() {
        if (this.friendliness == null) {
            this.setFriendliness(Long.valueOf(0L));
        }
        return Long.valueOf(JmSum.MZ((long)this.friendliness));
    }
    
    public void setFriendliness(Long friendliness) {
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure = (Configure)s.get(Integer.valueOf(1));
        if ((long)friendliness > (long)Integer.parseInt(configure.getZhsqmsx())) {
            friendliness = Long.valueOf(Long.parseLong(configure.getZhsqmsx()));
        }
        this.friendliness = Long.valueOf(JmSum.ZM((long)friendliness));
    }
    
    public String getPetSkillswl() {
        return this.petSkillswl;
    }
    
    public void setPetSkillswl(String petSkillswl) {
        this.petSkillswl = petSkillswl;
    }
    
    public String getPetSkilllock() {
        return this.petSkilllock;
    }
    
    public void setPetSkilllock(String petSkilllock) {
        this.petSkilllock = petSkilllock;
    }
    
    public List<BigDecimal> getGoods() {
        if ((this.innerGoods == null || this.innerGoods.equals("")) && (this.stye == null || this.stye.length() <= 1)) {
            return null;
        }
        List<BigDecimal> goods = new ArrayList<>();
        if (this.innerGoods != null && !this.innerGoods.equals("")) {
            String[] v = this.innerGoods.split("\\|");
            for (int i = 0; i < v.length; ++i) {
                if (!v[i].equals("")) {
                    goods.add(new BigDecimal(v[i]));
                }
            }
        }
        if (this.stye != null && this.stye.length() > 1) {
            String[] v = this.stye.split("\\|");
            for (int i = 1; i < v.length; ++i) {
                String[] vs = v[i].split("-");
                if (vs.length >= 2) {
                    goods.add(new BigDecimal(vs[1]));
                }
            }
        }
        return goods;
    }
    
    public int getCanpoint() {
        int lvl = (int)this.getGrade();
        int Turn = AnalysisString.petTurnRount(lvl);
        lvl = AnalysisString.petLvlint(lvl);
        int canpoint = lvl * 8;
        if (Turn >= 4) {
            canpoint += lvl;
        }
        canpoint += this.getExtPoint();
        canpoint += Turn * 30;
        canpoint -= (int)this.getBone();
        canpoint -= (int)this.getSpir();
        canpoint -= (int)this.getPower();
        canpoint -= (int)this.getSpeed();
        canpoint -= (int)this.getCalm();
        return canpoint;
    }
    
    public RoleSummoning clone() {
        try {
            return (RoleSummoning)super.clone();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public String getQuality() {
        return this.quality;
    }
    
    public void setQuality(String quality) {
        this.quality = quality;
    }
    
    public String getSurplusTimes() {
        return this.surplusTimes;
    }
    
    public void setSurplusTimes(String surplusTimes) {
        this.surplusTimes = surplusTimes;
    }
    
    public boolean isShow() {
        return this.show;
    }
    
    public void setShow(boolean show) {
        this.show = show;
    }
    
    public Integer getDeposit() {
        return this.deposit;
    }
    
    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }
    
    public String getXy() {
        return this.xy;
    }
    
    public void setXy(String xy) {
        this.xy = xy;
    }
    
    public BigDecimal getCommodityId() {
        return this.commodityId;
    }
    
    public void setCommodityId(BigDecimal commodityId) {
        this.commodityId = commodityId;
    }
    
    public int getTalentLvl() {
        return this.talentLvl;
    }
    
    public void setTalentLvl(int talentLvl) {
        this.talentLvl = talentLvl;
    }
    
    public int getDicenum() {
        return this.dicenum;
    }
    
    public void setDicenum(int dicenum) {
        this.dicenum = dicenum;
    }
    
    public Integer getFoPenSeal() {
        return this.foPenSeal;
    }
    
    public void setFoPenSeal(Integer foPenSeal) {
        this.foPenSeal = foPenSeal;
    }
    public Ql getQl() {
        return ql;
    }
    private Ql ql;
    public void setQl(Ql ql) {
        this.ql = ql;
    }


}
