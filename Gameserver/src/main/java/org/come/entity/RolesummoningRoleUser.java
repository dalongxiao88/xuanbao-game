package org.come.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.come.model.Configure;
import org.come.server.GameServer;
import org.come.tool.JmSum;
import java.math.BigDecimal;

public class RolesummoningRoleUser
{
    private String summoningid;
    private String summoningname;
    private String summoningskin;
    private String ssn;
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
    private Integer openSeal;
    private String innerGoods;
    private int dragon;
    private String petSkills;
    private int turnRount;
    private String NedanResistance;
    private int revealNum;
    private int flyupNum;
    private String beastSkills;
    private String fourattributes;
    private String skillData;
    private String zqk;
    private String lyk;
    private int alchemynum;
    private Integer growUpDanNum;
    private int draC;
    private int trainNum;
    private int petlock;
    private BigDecimal price;
    private String basisap;
    private String basissp;
    private int basishp;
    private int basismp;
    private String extrahp;
    private String extramp;
    private String extraap;
    private String extrasp;
    private String rolename;
    private BigDecimal user_id;
    private String username;
    private Integer start;
    private Integer end;
    private Integer pageNow;
    private String orderBy;
    
    public RolesummoningRoleUser() {
        this.growlevel = "0";
        this.skill = "";
        this.bone = Integer.valueOf(0);
        this.spir = Integer.valueOf(0);
        this.power = Integer.valueOf(0);
        this.speed = Integer.valueOf(0);
        this.grade = Integer.valueOf(0);
        this.openSeal = Integer.valueOf(1);
        this.fourattributes = "";
        this.skillData = "";
        this.zqk = "";
        this.lyk = "";
        this.alchemynum = 0;
        this.growUpDanNum = Integer.valueOf(0);
        this.basisap = "0";
        this.basissp = "0";
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
        return this.turnRount;
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
    
    public String getPetSkills() {
        return this.petSkills;
    }
    
    public void setPetSkills(String petSkills) {
        this.petSkills = petSkills;
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
    
    public int getAlchemynum() {
        return this.alchemynum;
    }
    
    public void setAlchemynum(int alchemynum) {
        this.alchemynum = alchemynum;
    }
    
    public Integer getGrowUpDanNum() {
        return this.growUpDanNum;
    }
    
    public void setGrowUpDanNum(Integer growUpDanNum) {
        this.growUpDanNum = growUpDanNum;
    }
    
    public String getColorScheme() {
        return this.ColorScheme;
    }
    
    public void setColorScheme(String colorScheme) {
        this.ColorScheme = colorScheme;
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
        return this.grade;
    }
    
    public void setGrade(Integer grade) {
        this.grade = grade;
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
    
    public RoleSummoning clone() {
        try {
            return (RoleSummoning)super.clone();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public BigDecimal getPrice() {
        return this.price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public String getBasisap() {
        return this.basisap;
    }
    
    public void setBasisap(String basisap) {
        this.basisap = basisap;
    }
    
    public String getBasissp() {
        return this.basissp;
    }
    
    public void setBasissp(String basissp) {
        this.basissp = basissp;
    }
    
    public String getExtrahp() {
        return this.extrahp;
    }
    
    public void setExtrahp(String extrahp) {
        this.extrahp = extrahp;
    }
    
    public String getExtramp() {
        return this.extramp;
    }
    
    public void setExtramp(String extramp) {
        this.extramp = extramp;
    }
    
    public String getExtraap() {
        return this.extraap;
    }
    
    public void setExtraap(String extraap) {
        this.extraap = extraap;
    }
    
    public String getExtrasp() {
        return this.extrasp;
    }
    
    public void setExtrasp(String extrasp) {
        this.extrasp = extrasp;
    }
    
    public String getRolename() {
        return this.rolename;
    }
    
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    
    public BigDecimal getUser_id() {
        return this.user_id;
    }
    
    public void setUser_id(BigDecimal user_id) {
        this.user_id = user_id;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public Integer getStart() {
        return this.start;
    }
    
    public void setStart(Integer start) {
        this.start = start;
    }
    
    public Integer getEnd() {
        return this.end;
    }
    
    public void setEnd(Integer end) {
        this.end = end;
    }
    
    public Integer getPageNow() {
        return this.pageNow;
    }
    
    public void setPageNow(Integer pageNow) {
        this.pageNow = pageNow;
    }
    
    public String getOrderBy() {
        return this.orderBy;
    }
    
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
