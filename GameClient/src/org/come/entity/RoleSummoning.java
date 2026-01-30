package org.come.entity;

import org.come.until.GoodsListFromServerUntil;
import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.model.Configure;
import org.come.until.AnalysisString;
import org.come.until.JmSum;
import org.come.bean.ColorScheme;
import org.come.until.UserMessUntil;
import com.tool.tcp.SpriteFactory;
import com.tool.tcp.NewPart;
import com.gl.util.LingXiUtil;
import org.apache.commons.lang.StringUtils;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import org.come.Jpanel.spot.Commodity;

public class RoleSummoning implements Commodity
{
    private BigDecimal sid;
    private String summoningid;
    private String summoningskin;
    private String stye;
    private int hp;
    private int mp;
    private int ap;
    private int sp;
    private String growlevel;
    private long basishp;
    private long basismp;
    private int dragon;
    private int spdragon;
    private String zqk;
    private String lyk;
    private String resistance;
    private String skillData;
    private int alchemynum;
    private String skill;
    private String gold;
    private String wood;
    private String soil;
    private String water;
    private String fire;
    private String summoningname;
    private String ssn;
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
    private Integer openSeal;
    private Integer openql;
    private String innerGoods;
    private String petSkills;
    private String petQlSkills;
    private String beastSkills;
    private int revealNum;
    private int flyupNum;
    private int czjjd;
    private String fourattributes;
    private int growUpDanNum;
    private String ColorScheme;
    private int draC;
    private int trainNum;
    private int petlock;
    private String lingxi;
    private String petSkillswl;
    private String quality;
    private String surplusTimes;
    private boolean show;
    private String petSkilllock;
    private int talentLvl;
    private int extPoint;
    private Integer deposit;
    private String xy;
    private int dicenum;
    private BigDecimal commodityId;
    private List<String> otherInfo;
    private List<Goodstable> innerGoodList;
    private Integer foPenSeal;
    
    public RoleSummoning() {
        this.innerGoodList = new ArrayList<>();
        this.foPenSeal = Integer.valueOf(3);
    }
    
    public List<String> getOtherInfo() {
        return this.otherInfo;
    }
    
    public void setOtherInfo(List<String> otherInfo) {
        this.otherInfo = otherInfo;
    }
    
    public List<Goodstable> getInnerGoodList() {
        return this.innerGoodList;
    }
    
    public void setInnerGoodList(List<Goodstable> innerGoodList) {
        this.innerGoodList = innerGoodList;
    }
    
    public int getTrainNum() {
        return this.trainNum;
    }
    
    public void setTrainNum(int trainNum) {
        this.trainNum = trainNum;
    }
    
    public int getExtPoint() {
        return this.extPoint;
    }
    
    public void setExtPoint(int extPoint) {
        this.extPoint = extPoint;
    }
    
    public void getSI(int[] pets) {
        try {
            List<Integer> skills = new ArrayList<>();
            if (this.getPetSkills() != null && !this.getPetSkills().equals("")) {
                String[] vs = this.getPetSkills().split("\\|");
                for (int j = 0; j < vs.length; ++j) {
                    if (!vs[j].equals("")) {
                        skills.add(Integer.valueOf(Integer.parseInt(vs[j])));
                    }
                }
            }
            String s1 = BattskillData(skills, this.getBeastSkills());
            this.skillData = s1;
        }
        catch (Exception ex) {}
        if (this.skillData == null || this.skillData.equals("")) {
            return;
        }
        String[] v = this.skillData.split("\\|");
        for (int i = 0; i < v.length; ++i) {
            String[] v2 = v[i].split("=");
            if (v2[0].equals("HP")) {
                int n = 0;
                pets[n] = (int)((double)pets[n] + Double.parseDouble(v2[1]));
            }
            else if (v2[0].equals("MP")) {
                int n2 = 1;
                pets[n2] = (int)((double)pets[n2] + Double.parseDouble(v2[1]));
            }
            else if (v2[0].equals("AP")) {
                int n3 = 2;
                pets[n3] = (int)((double)pets[n3] + Double.parseDouble(v2[1]));
            }
            else if (v2[0].equals("SP")) {
                int n4 = 3;
                pets[n4] = (int)((double)pets[n4] + Double.parseDouble(v2[1]));
            }
        }
    }
    
    public static String Splice(String v, String b, int type) {
        boolean s = true;
        boolean s2 = false;
        if (type == 11 || type == 2 || type == 3 || type == 5) {
            s2 = true;
        }
        List<String> jihe = new ArrayList<>();
        if (v == null) {
            v = "";
        }
        String[] vs = v.split("\\|");
        for (int i = 0; i < vs.length; ++i) {
            if (type == 0) {
                if (!vs[i].equals(b)) {
                    jihe.add(vs[i]);
                }
                else {
                    s = false;
                }
            }
            else {
                String[] vs2 = vs[i].split("=");
                String[] vs3 = b.split("=");
                if (vs2[0].equals(vs3[0])) {
                    if (type == 1 || type == 11) {
                        if (type == 11) {
                            s2 = false;
                        }
                        jihe.add(b);
                    }
                    else if (type == 2) {
                        s2 = false;
                        double x1 = Double.parseDouble(vs2[1]);
                        double x2 = Double.parseDouble(vs3[1]);
                        x1 += x2;
                        if (x1 % 1.0 == 0.0) {
                            jihe.add(vs2[0] + "=" + (int)x1);
                        }
                        else {
                            jihe.add(vs2[0] + "=" + x1);
                        }
                    }
                    else if (type == 3) {
                        s2 = false;
                        double x1 = Double.parseDouble(vs2[1]);
                        double x2 = Double.parseDouble(vs3[1]);
                        x1 -= x2;
                        if (x1 % 1.0 == 0.0) {
                            jihe.add(vs2[0] + "=" + (int)x1);
                        }
                        else {
                            jihe.add(vs2[0] + "=" + x1);
                        }
                    }
                    else if (type == 5) {
                        s2 = false;
                        double x1 = Double.parseDouble(vs2[1]);
                        double x2 = Double.parseDouble(vs3[1]);
                        if (x2 > x1) {
                            x1 = x2;
                        }
                        if (x1 % 1.0 == 0.0) {
                            jihe.add(vs2[0] + "=" + (int)x1);
                        }
                        else {
                            jihe.add(vs2[0] + "=" + x1);
                        }
                    }
                }
                else {
                    jihe.add(vs[i]);
                }
            }
        }
        if (s && type == 0) {
            jihe.add(b);
        }
        if (s2) {
            jihe.add(b);
        }
        StringBuffer genggai = new StringBuffer();
        for (int j = 0; j < jihe.size(); ++j) {
            if (!genggai.toString().equals("")) {
                genggai.append("|" + (String)jihe.get(j));
            }
            else {
                genggai.append((String)jihe.get(j));
            }
        }
        return genggai.toString();
    }
    
    public static String BattskillData(List<Integer> skills, String b3) {
        String skilldata = null;
        Boolean b4 = Boolean.valueOf(false);
        Boolean b5 = Boolean.valueOf(false);
        skills = (List)skills.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        if (StringUtils.isNotBlank(b3)) {
            skills.add(0, Integer.valueOf(Integer.parseInt(b3)));
        }
        for (int i = 0; i < skills.size(); ++i) {
            String string = skills.get(i) + "";
            int n = -1;
            switch (string.hashCode()) {
                case 1515111: {
                    if (string.equals("1800")) {
                        n = 0;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515178: {
                    if (string.equals("1825")) {
                        n = 1;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515112: {
                    if (string.equals("1801")) {
                        n = 2;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515113: {
                    if (string.equals("1802")) {
                        n = 3;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515179: {
                    if (string.equals("1826")) {
                        n = 4;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515144: {
                    if (string.equals("1812")) {
                        n = 5;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515146: {
                    if (string.equals("1814")) {
                        n = 6;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515361: {
                    if (string.equals("1882")) {
                        n = 7;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515362: {
                    if (string.equals("1883")) {
                        n = 8;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515363: {
                    if (string.equals("1884")) {
                        n = 9;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515364: {
                    if (string.equals("1885")) {
                        n = 10;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515114: {
                    if (string.equals("1803")) {
                        n = 11;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515180: {
                    if (string.equals("1827")) {
                        n = 12;
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
            switch (n) {
                case 0:
                case 1: {
                    skilldata = Splice(skilldata, "HP=27000", 11);
                    break;
                }
                case 2: {
                    skilldata = Splice(skilldata, "MP=27000", 11);
                    break;
                }
                case 3:
                case 4: {
                    skilldata = Splice(skilldata, "AP=11000", 11);
                    break;
                }
                case 5: {
                    skilldata = Splice(skilldata, "SP=-170", 11);
                    break;
                }
                case 6: {
                    skilldata = Splice(skilldata, "SP=250", 11);
                    b5 = Boolean.valueOf(true);
                    break;
                }
                case 7: {
                    skilldata = Splice(skilldata, "HP=32000", 11);
                    break;
                }
                case 8: {
                    skilldata = Splice(skilldata, "MP=32000", 11);
                    break;
                }
                case 9: {
                    skilldata = Splice(skilldata, "AP=15000", 11);
                    break;
                }
                case 10: {
                    if (!(boolean)b5) {
                        skilldata = Splice(skilldata, "SP=200", 11);
                        b5 = Boolean.valueOf(true);
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 11:
                case 12: {
                    if (!(boolean)b4 && !(boolean)b5) {
                        skilldata = Splice(skilldata, "SP=170", 11);
                        b4 = Boolean.valueOf(true);
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
        }
        return skilldata;
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
    
    public NewPart getPart() {
        NewPart part = SpriteFactory.createPart(this.summoningskin, 9, 1, this.ColorScheme);
        part = part.addPart(this.getAdornPart());
        return part;
    }

    public NewPart getPart(int action) {
        NewPart part = SpriteFactory.createPart(this.summoningskin, action, 1, this.ColorScheme);
        part = part.addPart(this.getAdornPart());
        return part;
    }
    public NewPart getAdornPart() {
        if (this.stye == null || this.stye.equals("")) {
            return null;
        }
        String[] v = this.stye.split("\\|");
        int i = 1;
        while (i < v.length) {
            String[] vs = v[i].split("-");
            if (vs[0].equals("3")) {
                if (vs.length <= 2) {
                    return null;
                }
                String color = null;
                if (vs.length > 3) {
                    ColorScheme value = UserMessUntil.getColor(vs[3]);
                    if (value != null) {
                        color = value.getValue();
                    }
                }
                NewPart part = SpriteFactory.createPart(vs[2], 9, 1, color);
                return part;
            }
            else {
                ++i;
            }
        }
        return null;
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
    
    public long getBasishp() {
        return this.basishp;
    }
    
    public void setBasishp(long basishp) {
        this.basishp = ((basishp <= 0L) ? 1L : basishp);
    }
    
    public long getBasismp() {
        return this.basismp;
    }
    
    public void setBasismp(long mp_Current) {
        this.basismp = mp_Current;
    }
    
    public long getBasishps() {
        return this.basishp;
    }
    
    public void setBasishps(int basishp) {
        this.basishp = ((basishp <= 0) ? 1L : ((long)basishp));
    }
    
    public long getBasismps() {
        return this.basismp;
    }
    
    public void setBasismps(int basismp) {
        this.basismp = (long)basismp;
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
            this.gold = "0";
        }
        return this.gold;
    }
    
    public void setGold(String gold) {
        this.gold = gold;
    }
    
    public String getWood() {
        if (this.wood == null || this.wood.equals("")) {
            this.wood = "0";
        }
        return this.wood;
    }
    
    public void setWood(String wood) {
        this.wood = wood;
    }
    
    public String getSoil() {
        if (this.soil == null || this.soil.equals("")) {
            this.soil = "0";
        }
        return this.soil;
    }
    
    public void setSoil(String soil) {
        this.soil = soil;
    }
    
    public String getWater() {
        if (this.water == null || this.water.equals("")) {
            this.water = "0";
        }
        return this.water;
    }
    
    public void setWater(String water) {
        this.water = water;
    }
    
    public String getFire() {
        if (this.fire == null || this.fire.equals("")) {
            this.fire = "0";
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
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        if ((long)friendliness > (long)Integer.parseInt(configure.getZhsqmsx())) {
            friendliness = Long.valueOf(Long.parseLong(configure.getZhsqmsx()));
        }
        this.friendliness = Long.valueOf(JmSum.ZM((long)friendliness));
    }
    
    public Integer getFaithful() {
        return this.faithful;
    }
    
    public void setFaithful(Integer faithful) {
        this.faithful = faithful;
    }
    
    public void addFaithful(Integer faithful) {
        if (faithful == null) {
            this.faithful = Integer.valueOf(0);
        }
        this.faithful = Integer.valueOf((int)this.faithful + (int)faithful);
        if ((int)this.faithful < 0) {
            this.faithful = Integer.valueOf(0);
        }
        if ((int)this.faithful > 100) {
            this.faithful = Integer.valueOf(100);
        }
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
    
    public int getAlchemynum() {
        return this.alchemynum;
    }
    
    public void setAlchemynum(int alchemynum) {
        this.alchemynum = alchemynum;
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
    
    public String getPetSkills() {
        return this.petSkills;
    }
    
    public String getPetQlSkills() {
        return this.petQlSkills;
    }
    
    public int getTurnRount() {
        return AnalysisString.petTurnRount((int)this.getGrade());
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
    
    public int getCzjjd() {
        return this.czjjd;
    }
    
    public void setCzjjd(int czjjd) {
        this.czjjd = czjjd;
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
    
    public String getPetSkillswl() {
        return this.petSkillswl;
    }
    
    public void setPetSkillswl(String petSkillswl) {
        this.petSkillswl = petSkillswl;
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
    
    public int getDraC() {
        return this.draC;
    }
    
    public void setDraC(int draC) {
        this.draC = draC;
    }
    
    public int getPetlock() {
        return this.petlock;
    }
    
    public void setPetlock(int petlock) {
        this.petlock = petlock;
    }
    
    public String getLingxi() {
        return this.lingxi;
    }
    
    public void setLingxi(String lingxi) {
        this.lingxi = lingxi;
    }
    
    public Integer getZBone() {
        return Integer.valueOf((int)this.getBone() + this.getExtra("根骨"));
    }
    
    public void setZBone(Integer bone) {
        this.setBone(Integer.valueOf((int)bone - this.getExtra("根骨")));
    }
    
    public Integer getZSpir() {
        return Integer.valueOf((int)this.getSpir() + this.getExtra("灵性"));
    }
    
    public void setZSpir(Integer spir) {
        this.setSpir(Integer.valueOf((int)spir - this.getExtra("灵性")));
    }
    
    public Integer getZPower() {
        return Integer.valueOf((int)this.getPower() + this.getExtra("力量"));
    }
    
    public void setZPower(Integer power) {
        this.setPower(Integer.valueOf((int)power - this.getExtra("力量")));
    }
    
    public Integer getZSpeed() {
        return Integer.valueOf((int)this.getSpeed() + this.getExtra("敏捷"));
    }
    
    public void setZSpeed(Integer speed) {
        this.setSpeed(Integer.valueOf((int)speed - this.getExtra("敏捷")));
    }
    
    public Integer getZCalm() {
        return Integer.valueOf((int)this.getCalm() + this.getExtra("定力"));
    }
    
    public void setZCalm(Integer calm) {
        this.setCalm(Integer.valueOf((int)calm - this.getExtra("定力")));
    }
    
    public int getExtra(String type) {
        if (this.stye == null || this.stye.length() <= 1) {
            return 0;
        }
        type += "=";
        int value = 0;
        String[] v = this.stye.split("\\|");
        for (int i = 1; i < v.length; ++i) {
            String[] vs = v[i].split("-");
            if (vs.length >= 2) {
                Goodstable goodstable = (Goodstable)GoodsListFromServerUntil.fushis.get(new BigDecimal(vs[1]));
                if (goodstable != null) {
                    String[] t = goodstable.getValue().split("\\|");
                    for (int j = 0; j < t.length; ++j) {
                        if (t[j].startsWith(type)) {
                            String[] ts = t[j].split("=");
                            value += Integer.parseInt(ts[1]);
                        }
                    }
                }
            }
        }
        return value;
    }
    
    public List<BigDecimal> getGoods() {
        if ((this.innerGoods == null || this.innerGoods.equals("")) && (this.stye == null || this.stye.length() <= 1)) {
            return null;
        }
        List<BigDecimal> goods = new ArrayList<>();
        if (this.innerGoods != null && this.innerGoods.equals("")) {
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
        if (goods.size() == 0) {
            return null;
        }
        return goods;
    }
    
    public BigDecimal ChangePart(Goodstable goodstable, int type) {
        if ((this.stye == null || this.stye.length() <= 1) && goodstable == null) {
            return null;
        }
        BigDecimal id = null;
        String ts = type + "";
        String[] v = this.stye.split("\\|");
        StringBuffer buffer = new StringBuffer();
        buffer.append(v[0]);
        for (int i = 1; i < v.length; ++i) {
            String[] vs = v[i].split("-");
            if (ts.equals(vs[0])) {
                id = new BigDecimal(vs[1]);
            }
            else {
                buffer.append("|");
                buffer.append(v[i]);
            }
        }
        if (goodstable != null) {
            buffer.append("|");
            buffer.append(type);
            buffer.append("-");
            buffer.append(goodstable.getRgid());
            String skin = null;
            String color = null;
            if (type == 3) {
                String[] gv = goodstable.getValue().split("\\|");
                for (int j = 0; j < gv.length; ++j) {
                    String[] gvs = gv[j].split("=");
                    if (gvs[0].equals("皮肤")) {
                        skin = gvs[1];
                    }
                    else if (gvs[0].equals("颜色")) {
                        color = gvs[1];
                    }
                }
                if (skin != null) {
                    buffer.append("-");
                    buffer.append(skin);
                    if (color != null) {
                        buffer.append("-");
                        buffer.append(color);
                    }
                }
            }
        }
        this.stye = buffer.toString();
        return id;
    }
    
    public BigDecimal getGoodId(int type) {
        if (this.stye == null || this.stye.length() <= 1) {
            return null;
        }
        String ts = type + "";
        String[] v = this.stye.split("\\|");
        for (int i = 1; i < v.length; ++i) {
            String[] vs = v[i].split("-");
            if (ts.equals(vs[0])) {
                return new BigDecimal(vs[1]);
            }
        }
        return null;
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
    
    public String getPetSkilllock() {
        return this.petSkilllock;
    }
    
    public void setPetSkilllock(String petSkilllock) {
        this.petSkilllock = petSkilllock;
    }
    
    public String getXy() {
        return this.xy;
    }
    
    public void setXy(String xy) {
        this.xy = xy;
    }
    
    public Integer getDeposit() {
        return this.deposit;
    }
    
    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }
    
    public int getTalentLvl() {
        return this.talentLvl;
    }
    
    public void setTalentLvl(int talentLvl) {
        this.talentLvl = talentLvl;
    }
    
    @Override
    public void setCommodityId(BigDecimal commodityId) {
        this.commodityId = commodityId;
    }
    
    @Override
    public BigDecimal getCommodityId() {
        return this.commodityId;
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
}
