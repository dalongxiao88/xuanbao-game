package come.tool.Calculation;

import java.util.ArrayList;
import org.come.entity.Goodstable;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import org.come.tool.Goodtype;
import org.come.tool.WriteOut;
import org.come.until.GsonUtil;
import org.come.model.Skill;
import org.come.server.GameServer;
import org.come.action.suit.SuitComposeAction;
import java.util.List;

public class BaseEquip
{
    private static String[] BaseLimits;
    //限制条件
    private BaseLimit baseLimit;
    //基础属性
    private List<BaseQl> qls;
    //额外属性
    private List<BaseQl> qlews;
    //特技
    private List<BaseSkill> baseSkills;
    //套装id
    private BaseSuit baseSuit;
    //星阵
    private BaseStar baseStar;
    //强化等级
    private Integer qhv;
    private Integer NJ;//耐久度
    
    public BaseEquip(String value, long type) {
        try {
            String[] vs = value.split("\\|");
            if (type == 8888L) {//翅膀
                this.initWing(vs);
            }
            else if (type == 520L) {//星卡
                this.initStar(vs);
            }
            else if (type == 510L || type == 511L || type == 512L) {//召唤兽装备
                this.initPetEquip(vs);
            }
            else {//正常装备
                this.init(vs, type);
            }
        }
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static double getQHGemXS(int lvl) {
        double xs = 0.0;
        for (int i = 1; i <= lvl; ++i) {
            xs += (double)((i - 1) / 3 + 1) * 0.8;
        }
        return xs;
    }
    /**正常装备*/
    public void init(String[] vs, long type) {
        for (int i = 0; i < vs.length; ++i) {
            if (vs[i].startsWith(SuitComposeAction.Extras[0])) {//炼化属性
                String[] vStrings = vs[i].split("&");
                for (int j = 1; j < vStrings.length; ++j) {
                    String[] mes = vStrings[j].split("=");
                    if (mes[0].equals("特技")) {
                        for (int l = 1; l < mes.length; ++l) {
                            Skill skill = GameServer.getSkill(mes[l]);
                            if (skill != null) {
                                if (skill.getSkillid() == 8016 || skill.getSkillid() == 8017) {//无属性无级别
                                    this.addLimit(skill.getSkillid(), null);
                                }
                                else {
                                    if (skill.getSkillid() == 8055) {
                                        int length = vStrings.length;
                                        int n = 0;
                                        while (n < length) {
                                            String vString = vStrings[n];
                                            if (vString.startsWith("冰刃术伤害")) {
                                                skill = (Skill)GsonUtil.getGsonUtil().getgson().fromJson(GsonUtil.getGsonUtil().getgson().toJson(skill), Skill.class);
                                                skill.setValue(Double.parseDouble(vString.split("=")[1]));
                                                break;
                                            }
                                            else {
                                                ++n;
                                            }
                                        }
                                    }
                                    BaseSkill baseSkill = new BaseSkill(skill.getSkillid(), 1, skill, null);
                                    this.addSkill(baseSkill);
                                }
                            }
                        }
                    }
                    else if (mes.length == 2) {
                        try {
                            this.addQlEW(mes[0], Double.parseDouble(mes[1]));
                        }
                        catch (Exception e) {
                            WriteOut.addtxt("转换报错:" + mes[0] + "=" + mes[1], 9999L);
                        }
                    }
                }
            }
            else if (vs[i].startsWith(SuitComposeAction.Extras[1])) {//炼器
                int i2 = Goodtype.EquipmentType(type);
                if (i2 == 0) {
                    String[] vStrings2 = vs[i].split("&");
                    for (int k = 2; k < vStrings2.length; ++k) {
                        this.addKeyEW(vStrings2[k]);
                    }
                }
            }
            else if (vs[i].startsWith(SuitComposeAction.Extras[2])) {//神兵
                String[] vStrings = vs[i].split("&");
                for (int j = 1; j < vStrings.length; ++j) {
                    this.addKeyEW(vStrings[j]);
                }
            }
            else if (vs[i].startsWith(SuitComposeAction.Extras[3])) {//套装
                String[] vStrings = vs[i].split("&");
                for (int j = 4; j < vStrings.length; ++j) {
                    this.addKeyEW(vStrings[j]);
                }
                this.baseSuit = new BaseSuit(Integer.parseInt(vStrings[1]), BaseValue.getQv(vStrings[3]) / 10);
            }
            else if (vs[i].startsWith(SuitComposeAction.Extras[4])) {//宝石
                String[] vStrings = vs[i].split("&");
                for (int j = 1; j < vStrings.length; ++j) {
                    Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(vStrings[j]));
                    if (good != null) {
                        String[] mes2 = good.getValue().split("\\|");
                        for (int m = 1; m < mes2.length; ++m) {
                            String[] mess = mes2[m].split("=");
                            if (mess.length == 2) {
                                try {
                                    this.addQlEW(mess[0], Double.parseDouble(mess[1]));
                                }
                                catch (Exception e2) {
                                    WriteOut.addtxt("转换报错:" + mess[0] + "=" + mess[1], 9999L);
                                }
                            }
                        }
                    }
                }
            }
            else if (vs[i].startsWith(SuitComposeAction.Extras[9])) {//巫铸属性
                String[] vStrings = vs[i].split("&");
                for (int j = 1; j < vStrings.length; ++j) {
                    String[] mes = vStrings[j].split("=");
                    try {
                        this.addQlEW(mes[0], Double.parseDouble(mes[1]));
                    }
                    catch (Exception e) {
                        WriteOut.addtxt("转换报错:" + mes[0] + "=" + mes[1], 9999L);
                    }
                }
            }
            else if (vs[i].startsWith(SuitComposeAction.Extras[10])) {//点粹属性
                String[] vStrings = vs[i].split("&");
                for (int j = 1; j < vStrings.length; ++j) {
                    this.addKeyEW(vStrings[j]);
                }
            }
            else {
                this.addKey(vs[i]);
            }
        }
    }
    /**召唤兽装备*/
    public void initPetEquip(String[] vs) {
        //		等级=1|装备部位=兽环|等级需求=0转100级|敏捷=9|品质=90|通灵=230|炼化属性&分光化影等级=1&强力克金=0.7|觉醒技&1333&2.2&0
        for (int i = 3; i < vs.length; ++i) {
            if (vs[i].startsWith(SuitComposeAction.Extras[0])) {//炼化属性
                String[] vStrings = vs[i].split("&");
                for (int j = 1; j < vStrings.length; ++j) {
                    String[] mes = vStrings[j].split("=");
                    this.addQl(mes[0], Double.parseDouble(mes[1]));
                }
            }
            else if (vs[i].startsWith(SuitComposeAction.Extras[5])) {//觉醒技
                String[] vStrings = vs[i].split("&");
                Skill skill = GameServer.getSkill(vStrings[1]);
                if (skill != null) {
                    int skillId = skill.getSkillid();
                    double pz = Double.parseDouble(vStrings[2]);
                    int exp = Integer.parseInt(vStrings[3]);
                    BaseSkill baseSkill = new BaseSkill(skillId, exp, pz, skill);
                    this.addSkill(baseSkill);
                }
            }
            else {
                String[] mes2 = vs[i].split("=");
                if (mes2.length == 2) {
                    if (!mes2[0].equals("根骨") && !mes2[0].equals("灵性") && !mes2[0].equals("力量") && !mes2[0].equals("敏捷") && !mes2[0].equals("定力")) {
                        continue;
                    }
                    try {
                        this.addQl(mes2[0], Double.parseDouble(mes2[1]));
                    }
                    catch (Exception e) {
                        WriteOut.addtxt("转换报错:" + mes2[0] + "=" + mes2[1], 9999L);
                    }
                }
            }
        }
    }
    /**星卡*/
    public void initStar(String[] vs) {
        String xz = null;
        String wx = null;
        this.NJ = Integer.parseInt(vs[2].split("=")[1]);
        for (int i = 3; i < vs.length; ++i) {
            if (vs[i].startsWith(SuitComposeAction.Extras[0])) {
                String[] vStrings = vs[i].split("&");
                for (int j = 2; j < vStrings.length; ++j) {
                    if (vStrings[j].startsWith(SuitComposeAction.Extras[7])) {
                        xz = vStrings[j];
                    }
                    else {
                        String[] mes = vStrings[j].split("=");
                        if (mes.length == 2) {
                            try {
                                this.addQl(mes[0], Double.parseDouble(mes[1]));
                            }
                            catch (Exception e) {
                                WriteOut.addtxt("转换报错:" + mes[0] + "=" + mes[1], 9999L);
                            }
                        }
                    }
                }
            }
            else if (vs[i].startsWith(SuitComposeAction.Extras[6])) {
                wx = vs[i];
            }
        }
        if (xz != null && wx != null) {
            this.baseStar = new BaseStar(xz, wx);
        }
    }
    /**翅膀*/
    public void initWing(String[] vs) {
        int pz = BaseValue.getQv(vs[0].split("=")[1]) / 10;
        int lvl = Integer.parseInt(vs[1].split("=")[1]);
        for (int j = 4; j < vs.length; ++j) {
            if (vs[j].startsWith(SuitComposeAction.Extras[0])) {
                String[] vStrings = vs[j].split("&");
                for (int k = 1; k < vStrings.length; ++k) {
                    String[] mes = vStrings[k].split("=");
                    try {
                        this.addQl(mes[0], Double.parseDouble(mes[1]));
                    }
                    catch (Exception e) {
                        WriteOut.addtxt("转换报错:" + mes[0] + "=" + mes[1], 9999L);
                    }
                }
            }
            else {
                String[] mes2 = vs[j].split("=");
                if (mes2.length == 2 && (mes2[0].equals("根骨") || mes2[0].equals("灵性") || mes2[0].equals("力量") || mes2[0].equals("敏捷") || mes2[0].equals("定力"))) {
                    int zhi = Integer.parseInt(mes2[1]);
                    zhi = (int)((double)zhi * (1.0 + (double)lvl * 0.1));
                    zhi = (int)((double)zhi * (1.0 + ((pz == 6) ? 3.2 : ((pz == 5) ? 1.6 : ((pz == 4) ? 0.8 : ((pz == 3) ? 0.4 : ((pz == 2) ? 0.2 : 0.0)))))));
                    this.addQl(mes2[0], (double)zhi);
                }
            }
        }
    }
    /**添加*/
    public void addKey(String v) {
        try {
            String[] mes = v.split("=");
            if (mes.length == 2) {
                int i = 0;
                while (i < BaseEquip.BaseLimits.length) {
                    if (mes[0].startsWith(BaseEquip.BaseLimits[i])) {
                        if (i == 4 && mes[0].indexOf("减少") != -1) {
                            this.addLimit(i, "-" + mes[1]);
                        }
                        else {
                            this.addLimit(i, mes[1]);
                        }
                        return;
                    }
                    else {
                        ++i;
                    }
                }
                this.addQl(mes[0], Double.parseDouble(mes[1]));
            }
        }
        catch (Exception e) {
            WriteOut.addtxt("转换报错:" + v, 0L);
        }
    }
    /**添加*/
    public void addKeyEW(String v) {
        try {
            String[] mes = v.split("=");
            if (mes.length == 2) {
                int i = 0;
                while (i < BaseEquip.BaseLimits.length) {
                    if (mes[0].startsWith(BaseEquip.BaseLimits[i])) {
                        if (i == 4 && mes[0].indexOf("减少") != -1) {
                            this.addLimit(i, "-" + mes[1]);
                        }
                        else {
                            this.addLimit(i, mes[1]);
                        }
                        return;
                    }
                    else {
                        ++i;
                    }
                }
                this.addQlEW(mes[0], Double.parseDouble(mes[1]));
            }
        }
        catch (Exception e) {
            WriteOut.addtxt("转换报错:" + v, 0L);
        }
    }
    /**添加完成条件*/
    public void addLimit(int i, String value) {
        if (this.baseLimit == null) {
            this.baseLimit = new BaseLimit();
        }
        if (i < 4) {//"力量要求","灵性要求","根骨要求","敏捷要求"
            int zhi = Integer.parseInt(value);
            if (i == 0) {
                this.baseLimit.setLm(this.baseLimit.getLm() + zhi);
            }
            else if (i == 1) {
                this.baseLimit.setLx(this.baseLimit.getLx() + zhi);
            }
            else if (i == 2) {
                this.baseLimit.setGg(this.baseLimit.getGg() + zhi);
            }
            else {
                this.baseLimit.setMj(this.baseLimit.getMj() + zhi);
            }
        }
        else if (i == 4) {//"属性需求"
            if (this.baseLimit.getXs() != -999.0) {
                double zhi2 = Double.parseDouble(value);
                this.baseLimit.setXs(this.baseLimit.getXs() + zhi2);
            }
        }
        else if (i != 5) {//"装备角色"
            if (i == 6) {//"等级要求"
                String[] lvls = value.split("转");
                if (lvls.length == 1) {
                    this.baseLimit.setLvl(Integer.parseInt(lvls[0]));
                }
                else {
                    if (lvls[0].equals("飞升")) {
                        this.baseLimit.setZs(4);
                    }
                    else {
                        this.baseLimit.setZs(Integer.parseInt(lvls[0]));
                    }
                    this.baseLimit.setLvl(Integer.parseInt(lvls[1]));
                }
            }
            else if (i == 7) {//"最高携带等级"
                String[] lvls = value.split("转");
                if (lvls.length == 1) {
                    this.baseLimit.setLvlMax(Integer.parseInt(lvls[0]));
                }
                else {
                    if (lvls[0].equals("飞升")) {
                        this.baseLimit.setZsMax(4);
                    }
                    else {
                        this.baseLimit.setZsMax(Integer.parseInt(lvls[0]));
                    }
                    this.baseLimit.setLvlMax(Integer.parseInt(lvls[1]));
                }
            }
            else if (i == 8) {//"性别"
                if (value.equals("1") || value.equals("男")) {
                    this.baseLimit.setSex(1);
                }
                else {
                    this.baseLimit.setSex(0);
                }
            }
            else if (i == 8016) {//无属性
                this.baseLimit.setXs(-999.0);
            }
            else if (i == 8017) {//无级别
                this.baseLimit.setL(true);
            }
        }
    }
    /**添加属性*/
    public void addQl(String key, double value) {
        if (this.qls == null) {
            this.qls = new ArrayList<>();
        }
        for (int i = this.qls.size() - 1; i >= 0; --i) {
            BaseQl baseQl = (BaseQl)this.qls.get(i);
            if (baseQl.getKey().equals(key)) {
                baseQl.setValue(baseQl.getValue() + value);
                return;
            }
        }
        this.qls.add(new BaseQl(key, value));
    }
    /**添加额外属性*/
    public void addQlEW(String key, double value) {
        if (this.qlews == null) {
            this.qlews = new ArrayList<>();
        }
        for (int i = this.qlews.size() - 1; i >= 0; --i) {
            BaseQl baseQl = (BaseQl)this.qlews.get(i);
            if (baseQl.getKey().equals(key)) {
                baseQl.setValue(baseQl.getValue() + value);
                return;
            }
        }
        this.qlews.add(new BaseQl(key, value));
    }
    /**添加特技*/
    public void addSkill(BaseSkill baseSkill) {
        if (this.baseSkills == null) {
            this.baseSkills = new ArrayList<>();
        }
        this.baseSkills.add(baseSkill);
    }
    
    public BaseLimit getBaseLimit() {
        return this.baseLimit;
    }
    
    public void setBaseLimit(BaseLimit baseLimit) {
        this.baseLimit = baseLimit;
    }
    
    public List<BaseQl> getQls() {
        return this.qls;
    }
    
    public void setQls(List<BaseQl> qls) {
        this.qls = qls;
    }
    
    public List<BaseSkill> getBaseSkills() {
        return this.baseSkills;
    }
    
    public void setBaseSkills(List<BaseSkill> baseSkills) {
        this.baseSkills = baseSkills;
    }
    
    public BaseStar getBaseStar() {
        return this.baseStar;
    }
    
    public void setBaseStar(BaseStar baseStar) {
        this.baseStar = baseStar;
    }
    
    public Integer getNJ() {
        return this.NJ;
    }
    
    public void setNJ(Integer nJ) {
        this.NJ = nJ;
    }
    
    public static String[] getBaseLimits() {
        return BaseEquip.BaseLimits;
    }
    
    public static void setBaseLimits(String[] baseLimits) {
        BaseEquip.BaseLimits = baseLimits;
    }
    
    public BaseSuit getBaseSuit() {
        return this.baseSuit;
    }
    
    public void setBaseSuit(BaseSuit baseSuit) {
        this.baseSuit = baseSuit;
    }
    
    public List<BaseQl> getQlews() {
        return this.qlews;
    }
    
    public void setQlews(List<BaseQl> qlews) {
        this.qlews = qlews;
    }
    
    public Integer getQhv() {
        return this.qhv;
    }
    
    public void setQhv(Integer qhv) {
        this.qhv = qhv;
    }
    
    static {
        BaseEquip.BaseLimits = new String[] { "力量要求", "灵性要求", "根骨要求", "敏捷要求", "属性需求", "装备角色", "等级要求", "最高携带等级", "性别" };
    }
}
