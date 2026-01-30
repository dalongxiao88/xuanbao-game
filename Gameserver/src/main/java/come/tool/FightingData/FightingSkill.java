package come.tool.FightingData;

import org.apache.commons.lang.StringUtils;
import org.come.server.GameServer;
import org.come.tool.CustomFunction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.come.model.Skill;
import org.come.model.Talent;

public class FightingSkill implements Cloneable
{
    private int skillid;// 技能id
    private String skillname;// 技能名
    private int skilllvl; // 技能等级
    private double skillhurt; // 技能伤害
    private double skillgain;// 附加状态百分比
    private double skillgain1; // 附加状态百分比1
    private double skillhitrate;// 技能命中率
    private int skillsum; // 技能命中个数
    private double skillsld; // 技能熟练度
    private int skillcontinued; // 技能持续回合数
    private String skilltype;// 技能类型
    private int skillblue; // 技能耗蓝
    private int camp;  // 技能阵营 技能选择的人的不标准
    private int skillbeidong; // 该技能是否被动使用 0允许使用 1被动使用
    private double value1;
    private double value2;
    public boolean isCopy;
    public double s1;
    public double s2;
    public double s3;
    public double s4;
    public double s5;
    public double s6;
    public double s7;
    public double p1;
    public double p2;
    public double p3;
    public double p4;
    public double p5;
    public double p6;
    public double p7;
    public double e1;
    public double e2;
    public double e3;
    public double e4;
    public double e5;
    public double e6;
    public double e7;
    public int lvl = 0;
    public int born = 0;
    public long qm = 0L;
    public int pz = 0;
    public double lingshisld = 0.0;
    public boolean isCopy() {
        return isCopy;
    }

    public void setCopy(boolean copy) {
        isCopy = copy;
    }
    public FightingSkill() {
        this.skillbeidong = 0;
        this.value1 = 0.0;
        this.value2 = 0.0;
        this.isCopy = false;
    }

    public FightingSkill(Skill skill) {
        this.lvl = 0;
        this.born = 0;
        this.sld = 0.0D;
        this.qm = 0L;
        this.pz = 0;
        this.lingshisld = 0.0D;
        this.skillid = skill.getSkillid();
        this.skillname = skill.getSkillname();
        this.skilltype = skill.getSkillname();
        this.skillblue = (int) skill.getDielectric();
        this.s1 = skill.getS1();
        this.s2 = skill.getS2();
        this.s3 = skill.getS3();
        this.s4 = skill.getS4();
        this.s5 = skill.getS5();
        this.s6 = skill.getS6();
        this.s7 = skill.getS7();
        this.p1 = skill.getP1();
        this.p2 = skill.getP2();
        this.p3 = skill.getP3();
        this.p4 = skill.getP4();
        this.p5 = skill.getP5();
        this.p6 = skill.getP6();
        this.p7 = skill.getP7();
        this.e1 = skill.getE1();
        this.e2 = skill.getE2();
        this.e3 = skill.getE3();
        this.e4 = skill.getE4();
        this.e5 = skill.getE5();
        this.e6 = skill.getE6();
        this.e7 = skill.getE7();
        switch (this.skillid) {
            case 30000:
                this.skillhurt = skill.getValue();
                this.skillcontinued = (int) skill.getValue3();
                break;
            case 30001:
                this.skillcontinued = (int) skill.getValue();
                this.skillgain = skill.getValue1();
                this.skillbeidong = 1;
                break;
            case 30002:
                this.skillgain = skill.getValue();
                this.skillcontinued = 3;
                this.skillgain1 = skill.getValue1();
                this.skillhitrate = skill.getValue2();
                this.skillhurt = skill.getValue3();
                break;
            case 30003:
                this.skillcontinued = (int) skill.getValue();
                break;
            case 30004:
                this.skillcontinued = (int) skill.getValue();
                break;
            case 30005:
                this.skillsum = (int) skill.getValue();
                this.skillgain = skill.getValue1();
                this.skillhurt = skill.getValue2();
                break;
            case 30006:
            case 30007:
                this.skillgain = skill.getValue();
                this.skillgain1 = skill.getValue1();
                this.skillbeidong = 1;
                break;
            case 30008:
                this.skillgain = skill.getValue();
                this.skillgain1 = skill.getValue1();
                break;
            case 30009:
                this.skillgain = skill.getValue();
                this.skillgain1 = skill.getValue1();
                this.skillhitrate = skill.getValue2();
                this.skillcontinued = (int) skill.getValue3();
                break;
            case 30010:
            case 30011:
            case 30012:
                this.skillhurt = skill.getValue();
                this.skillbeidong = 1;
                break;
            case 30013:
                this.skillhurt = skill.getValue();
                this.skillgain = skill.getValue1();
                this.skillgain1 = skill.getValue2();
                break;
            case 30014:
                this.skillsum = (int) skill.getValue();
                this.skillgain = skill.getValue1();
                this.skillbeidong = 1;
                break;
            case 30015:
                this.skillgain = skill.getValue();
                this.skillgain1 = skill.getValue1();
                this.skillbeidong = 1;
                break;
            case 30016:
                this.skillgain = skill.getValue();
                break;
            case 30017:
                this.skillgain = skill.getValue();
                this.skillgain1 = skill.getValue1();
                this.skillhurt = skill.getValue2();
                this.skillhitrate = skill.getValue3();
                break;
            case 30018:
                this.skillgain = skill.getValue();
                this.skillgain1 = skill.getValue1();
                this.skillhurt = skill.getValue2();
                this.skillbeidong = 1;
                break;
            case 30019:
                this.skillgain = skill.getValue();
                this.skillcontinued = (int) skill.getValue1();
                break;
            case 30020:
                this.skillcontinued = (int) skill.getValue();
                break;
            case 30021:
                this.skillhurt = skill.getValue();
                this.skillgain = skill.getValue1();
                this.skillgain1 = skill.getValue2();
                break;
            case 30022:
                this.skillgain = skill.getValue();
                this.skillgain1 = skill.getValue1();
                this.skillhurt = skill.getValue2();
                break;
            case 30023:
                this.skillgain = skill.getValue();
                this.skillgain1 = skill.getValue1();
                this.skillhurt = skill.getValue2();
                this.skillbeidong = 1;
                break;
            case 30024:
                this.skillcontinued = (int) skill.getValue();
                this.skillgain = skill.getValue1();
                break;
            case 30025:
                this.skillgain = skill.getValue();
                this.skillgain1 = skill.getValue1();
                break;
            case 30026:
                this.skillgain = skill.getValue();
                break;
            case 30027:
                this.skillgain = skill.getValue();
                break;
            case 30028:
                this.skillgain = skill.getValue();
                this.skillcontinued = (int) skill.getValue1();
                this.skillbeidong = 1;
                break;
            case 30029:
                this.skillgain = skill.getValue();
                this.skillgain1 = skill.getValue1();
                this.skillhurt = skill.getValue2();
                this.skillbeidong = 1;
                break;
            case 30030:
                this.skillgain = skill.getValue();
                break;
            case 30031:
                this.skillgain = skill.getValue();
                this.skillgain1 = skill.getValue1();
                this.skillhitrate = skill.getValue2();
                this.skillhurt = skill.getValue3();
                this.skillbeidong = 1;
                break;
            case 30032:
                this.skillgain = skill.getValue();
                this.skillgain1 = skill.getValue1();
                break;
            case 30033:
            case 30034:
                this.skillcontinued = (int) skill.getValue();
                this.skillgain = skill.getValue1();
                break;
            case 30035:
                this.skillgain = skill.getValue();
                this.skillgain1 = skill.getValue1();
                this.skillhurt = skill.getValue2();
                this.skillbeidong = 1;
                break;
            case 30036:
                this.skillgain = skill.getValue();
                this.skillcontinued = (int) skill.getValue1();
                break;
            case 30037:
                this.skillcontinued = (int) skill.getValue();
                this.skillgain1 = skill.getValue1();
                break;
        }
    }
    /**
     * 1001-1024天资技能加载
     */
    public FightingSkill(Talent talent, int lvl) {
        this.skillbeidong = 0;
        this.value1 = 0.0;
        this.value2 = 0.0;
        this.isCopy = false;
        this.skillbeidong = 1;
        this.skillid = talent.getId();
        this.skillname = talent.getTalentName();
        this.skilllvl = lvl;
        this.skillhitrate = talent.getTouch() * (double)lvl;
        String[] v = talent.getValue().split("\\|");
        String[] v2 = v[0].split("=")[1].split("\\+");
        this.skillhurt = Double.parseDouble(v2[0]) + Double.parseDouble(v2[1]) * (double)lvl;
        v2 = v[1].split("=")[1].split("\\+");
        this.skillgain = Double.parseDouble(v2[0]) + Double.parseDouble(v2[1]) * (double)lvl;
        int type = (this.skillid - 1001) / 2;
        switch (type) {
            case 0: {
                this.skilltype = "加敏序";
                break;
            }
            case 1: {
                this.skilltype = "忽视风";
                this.skillcontinued = 1;
                break;
            }
            case 2: {
                this.skilltype = "忽视水";
                this.skillcontinued = 1;
                break;
            }
            case 3: {
                this.skilltype = "忽视火";
                this.skillcontinued = 1;
                break;
            }
            case 4: {
                this.skilltype = "忽视雷";
                this.skillcontinued = 1;
                break;
            }
            case 5: {
                this.skilltype = "强震慑";
                this.skillcontinued = 1;
                break;
            }
            case 6: {
                this.skilltype = "抗水";
                break;
            }
            case 7: {
                this.skilltype = "抗火";
                break;
            }
            case 8: {
                this.skilltype = "抗雷";
                break;
            }
            case 9: {
                this.skilltype = "抗风";
                break;
            }
            case 10: {
                this.skilltype = "强普攻";
                this.skillcontinued = 1;
                break;
            }
            case 11: {
                this.skilltype = "忽视混乱";
                this.skillcontinued = 1;
                break;
            }
            case 12: {
                this.skilltype = "忽视封印";
                this.skillcontinued = 1;
                break;
            }
            case 13: {
                this.skilltype = "忽视中毒";
                this.skillcontinued = 1;
                break;
            }
            case 14: {
                this.skilltype = "忽视昏睡";
                this.skillcontinued = 1;
                break;
            }
            case 15: {
                this.skilltype = "解混乱";
                this.skillcontinued = 3;
                break;
            }
            case 16: {
                this.skilltype = "解封印";
                this.skillcontinued = 3;
                break;
            }
            case 17: {
                this.skilltype = "解中毒";
                break;
            }
            case 18: {
                this.skilltype = "抗震慑";
                break;
            }
            case 19: {
                this.skilltype = "免疫物理";
                this.skillcontinued = 1;
                break;
            }
            case 20: {
                this.skilltype = "忽视鬼火";
                this.skillcontinued = 1;
                break;
            }
            case 21: {
                this.skilltype = "强三尸虫";
                this.skillcontinued = 1;
                break;
            }
            case 22: {
                this.skilltype = "忽视遗忘";
                this.skillcontinued = 1;
                break;
            }
            case 23: {
                this.skilltype = "抗鬼火";
                break;
            }
            case 24: {
                this.skilltype = "霹雳连击";
                this.skillcontinued = 1;
                break;
            }
            case 25: {
                this.skilltype = "甘霖回血";
                this.skillcontinued = 1;
                break;
            }
        }
    }
    /**
     * 内丹技能加载
     */
    public FightingSkill(String goodname, double skillgain, double skillhurt) {
        this.skillbeidong = 0;
        this.value1 = 0.0;
        this.value2 = 0.0;
        this.isCopy = false;
        this.camp = -1;
        this.skillname = goodname;
        this.skillbeidong = 1;
        this.skillgain = skillgain;
        this.skillhurt = (int)skillhurt;
        this.skillsum = 1;
        if (this.skillname.equals("乘风破浪")) {
            this.skillid = 1044;
            this.skilltype = "风";
        }
        else if (this.skillname.equals("霹雳流星")) {
            this.skillid = 1049;
            this.skilltype = "雷";
        }
        else if (this.skillname.equals("大海无量")) {
            this.skillid = 1054;
            this.skilltype = "水";
        }
        else if (this.skillname.equals("祝融取火")) {
            this.skillid = 1059;
            this.skilltype = "火";
        }
        else if (this.skillname.equals("分光化影") || this.skillname.equals("天魔解体") || this.skillname.equals("小楼夜哭") || this.skillname.equals("青面獠牙")) {
            this.skillbeidong = 0;
            this.skilltype = "魔界内丹";
        }
    }
    
    public int getsum2(int sum) {// 1 2 3 5 7
        if (sum <= 3) {
            return sum;
        }
        return (sum << 1) - 3;
    }
    
    public int getsum3(int sum) {
        return (sum << 1) - 1;
    }
    
    public int getsum4(int sum) {// 1 3 5 7 10
        if (sum >= 5) {
            return 10;
        }
        return (sum << 1) - 1;
    }
    /**获取系数 人物等级 转生次数 熟练度  亲密 法宝等级 法宝品质*/
    /**灵宝                         合计数       1擅长                                         */
    /**
     * 获取系数
     */
    public FightingSkill(Skill skill, int lvl, int born, double sld, long qm, int pz) {
        this.skillbeidong = 0;
        this.value1 = 0.0;
        this.value2 = 0.0;
        this.isCopy = false;
        double sv = skill.getGrow();
        double sv2 = skill.getGrow1();
        double sv3 = skill.getGrow2();
        double sv4 = skill.getValue3();
        double value = skill.getValue();
        double value2 = skill.getValue1();
        double value3 = skill.getValue2();
        double value4 = skill.getValue3();
        this.camp = skill.getCamp();
        this.skillid = skill.getSkillid();
        this.skilllvl = skill.getSkilllevel();
        this.skillblue = (int)skill.getDielectric();
        this.skillname = skill.getSkillname();
        this.skillsld = sld;
        this.skilltype = this.skillname;
        if (this.skillid > 1000 && this.skillid <= 1100) {//师门技能
            this.ShumenSkill(lvl, value, sv, sld);
        }
        else if (this.skillid >= 4000 && this.skillid <= 4022) {
            this.skilltype = skill.getSkillid() + "";
            this.value1 = value2;
            this.value2 = value3;
        }
        else if (this.skillid >= 1500 && this.skillid < 2000) {//召唤兽技能
            this.PetSkill(value, sv, lvl, born, qm,skill);
        }
        else if ((this.skillid >= 1200 && this.skillid < 1300) || this.skillid == 450051 || this.skillid == 450050) {//召唤兽天生技能
            this.PetTalentSkill(skill, value, sv, qm);
        }
        else if (this.skillid >= 1300 && this.skillid < 1400) {//召唤兽装备技能
            this.skilltype = this.skillid + "";
            this.PetEquipSkill(value, sld, lvl);
        }
        else if (this.skillid >= 4000 && this.skillid <= 4046) {//坐骑守护石技能
            this.shouhu(lvl);
        }
        else if (this.skillid > 3000 && this.skillid < 3100) {//灵宝技能
            this.LingSkill(value, sv, born, lvl, sld);
        }
        else if (this.skillid > 5000 && this.skillid < 5100) {//法宝技能
            this.FaSkill(value, sv, pz, lvl);
        }
        else if (this.skillid >= 8069 && this.skillid <= 8080) {
            this.skillbeidong = 1;
            this.skilltype = this.skillid + "";
        }
        else if ((this.skillid > 7000 && this.skillid < 8000) || this.skillid == 6038 || this.skillid == 6029) {//套装主动技能
            this.skilltype = this.skillid + "";
            this.SuitInitiative(value, sv, pz);
        }
        else if (this.skillid > 6000 && this.skillid < 7000) {//6038是主动技能6029
            //套装被动技能 6001-6017 6030-6032 6035-6036 6039加属性
            this.skillbeidong = 1;
            this.skilltype = this.skillid + "";
            this.SuitPassive(value, sv, (double)pz);
        }
        else if (this.skillid == 8043 || this.skillid == 8028 || this.skillid == 8029) {//落井下石（主动）减少对方单人300点怨气
            this.skilltype = this.skillid + "";
            this.skillsum = 1;
        }
        else if (this.skillid == 8055) {//冰刃数
            this.skilltype = this.skillid + "";
            this.skillsum = 2;
            this.skillbeidong = 0;
            this.skillhurt = skill.getValue();
        }
        else if (this.skillid == 8056) {//锦上添花
            this.skillhitrate = skill.getValue();
            this.skillbeidong = 1;
        }
        else if (this.skillid == 8066) {//强化浩然正气
            this.camp = -1;
            this.skillname = "浩然正气";
            this.skillhurt = 25.0;
            this.skillgain = 40.0;
            this.skillbeidong = 1;
        }
        else if (this.skillid == 8037 || this.skillid == 8038 || this.skillid == 8041 || this.skillid == 8042 || this.skillid == 8045 || (this.skillid >= 8060 && this.skillid <= 8065) || this.skillid == 8068 || (this.skillid >= 8053 && this.skillid <= 8059)) {
            this.skillbeidong = 1;
            this.skilltype = this.skillid + "";
            if (this.skillid == 8042) {
                this.skillgain = 35.0;
                this.skillsum = 2;
            }
            else if (this.skillid == 8068) {
                this.skillgain = 40.0;
                this.skillsum = 2;
            }
            else if (this.skillid == 8045) {
                this.camp = -1;
                this.skillname = "浩然正气";
                this.skillhurt = 20.0;
                this.skillgain = 35.0;
                this.skillbeidong = 1;
            }
        }
        else if (this.skillid >= 9000 && this.skillid <= 10166) {//天演策
            this.skilltype = this.skillid + "";
            this.skillbeidong = 1;
            this.TYC(value, sv, (int)sld, value2, sv2, value3, sv3, value4, sv4);
        }
        else if (this.skillid >= 22000 && this.skillid <= 23000) {//法门
            //	this.skilltype=this.skillid+"";//类型等于技能ID
            //单数被动，双数主动
            if (this.skillid == 22001 || this.skillid == 22003 || this.skillid == 22005 || this.skillid == 22007 || this.skillid == 22009 || this.skillid == 22011 || this.skillid == 22013 || this.skillid == 22015 || this.skillid == 22017 || this.skillid == 22019 || this.skillid == 22021 || this.skillid == 22023 || this.skillid == 22025 || this.skillid == 22027 || this.skillid == 22029 || this.skillid == 22031 || this.skillid == 22033 || this.skillid == 22035) {
                this.skillbeidong = 1;//都是被动
            }
        }
        else if (this.skillid > 23000 && this.skillid < 23010) {
            if (this.skillid == 23001) {
                this.skillbeidong = 1;
            }
            else if (this.skillid == 23002) {
                this.skillbeidong = 1;
            }
            else if (this.skillid == 23003) {
                this.skillbeidong = 1;
            }
            else if (this.skillid == 23004) {
                this.skilltype = "23004";
                this.skillcontinued = 2;//持续回合
                this.skillsum = 1;
            }
            else if (this.skillid == 23005) {
                this.skillbeidong = 1;
            }
            else if (this.skillid == 23006) {
                this.skillbeidong = 1;
            }
            else if (this.skillid == 23007) {
                this.skillbeidong = 1;
            }
            else if (this.skillid == 23008) {
                this.skillbeidong = 1;
            }
            else if (this.skillid == 23009) {
                this.skilltype = "23009";
                this.skillgain = 30.0;
                this.skillcontinued = 3;
            }
        }
        else if (this.skillid >= 25000 && this.skillid <= 25017) {
            this.skillbeidong = 1;//都是被动
            this.skilltype = this.skillid + "";
            this.PetXinYuanSkill(value, sv, qm);
        }
    }
    
    public void shouhu(int lvl) {
        this.skilltype = this.skillid + "";
        this.skillbeidong = 1;
        switch (this.skillid) {
            case 4000: {
                this.skillgain = 2.0 * (double)lvl;
                break;
            }
            case 4003: {
                this.skillgain = 0.3 * (double)lvl;
                this.skillhurt = 200.0 * (double)lvl;
                break;
            }
            case 4004: {
                this.skillgain = 4.0 * (double)lvl;
                this.skillhurt = 4.0 * (double)lvl / 100.0;
                break;
            }
            case 4005: {
                this.skillgain = 2.0 * (double)lvl;
                this.skillhurt = 3.0 * (double)lvl;
                break;
            }
            case 4009: {
                this.skillgain = 1.6 * (double)lvl;
                break;
            }
            case 4011: {
                this.skillgain = 3.0 * (double)lvl;
                this.skillhurt = 0.4 * (double)lvl;
                break;
            }
            case 4012: {
                this.skillgain = 5.0 * (double)lvl;
                this.skillhurt = 1.0 * (double)lvl;
                break;
            }
            case 4016: {
                this.skillgain = 1.5 * (double)lvl;
                break;
            }
            case 4021: {
                this.skillgain = 4.0 * (double)lvl;
                this.skillhurt = 2.0 * (double)lvl;
                break;
            }
        }
    }
    
    private void PetXinYuanSkill(double value, double sv, long qm) {
        switch (this.skillid) {
            case 25003: {
                this.skillbeidong = 1;
                this.skillsum = new Random().nextInt(3) + 3;
                return;
            }
            case 25004:
            case 25007:
            case 25008:
            case 25011:
            case 25012:
            case 25013:
            case 25014:
            case 25015:
            case 25016: {
                this.skillbeidong = 1;
                return;
            }
            default: {
                return;
            }
        }
    }
    //TODO 法术伤害及命中计算
    //师门法术  单位等级  介质 成长 熟练度//基础法术强度
    public void ShumenSkill(int lvl, double value, double sv, double sld) {
        this.skillsum = 1;
        if (this.skillid >= 1001 && this.skillid <= 1005) {//混乱法术
            this.skilltype = "混乱";
            this.skillcontinued = 7;//持续回合
            //命中概率
            this.skillhitrate = value + sv * new BigDecimal(Math.pow(sld, 0.25)).setScale(2, 4).doubleValue();
            //命中个数
            this.skillsum = geshu(this.skilllvl, (int)sld, this.skilltype);
        }
        else if (this.skillid >= 1006 && this.skillid <= 1010) {//封印
            this.skilltype = "封印";
            this.skillcontinued = 7;//持续回合
            //命中概率
            this.skillhitrate = value + sv * new BigDecimal(Math.pow(sld, 0.25)).setScale(2, 4).doubleValue();
            //命中个数
            this.skillsum = geshu(this.skilllvl, (int)sld, this.skilltype);
        }
        else if (this.skillid >= 1011 && this.skillid <= 1015) {//昏睡
            this.skilltype = "昏睡";
            this.skillcontinued = 7;
            this.skillhitrate = value + sv * new BigDecimal(Math.pow(sld, 0.25)).setScale(2, 4).doubleValue();
            this.skillsum = geshu(this.skilllvl, (int)sld, this.skilltype);
        }
        else if (this.skillid >= 1016 && this.skillid <= 1020) {//中毒
            this.skilltype = "中毒";
            this.skillcontinued = 3;
            this.skillhitrate = (double)(int)((value + sld * sv / 1000.0) * 1000.0) / 15.0 * 17.0;
            this.skillsum = geshu(this.skilllvl, (int)sld, this.skilltype);
            //总生命百分比伤害
            this.skillhurt = ((this.skilllvl > 3) ? 15.0 : ((this.skilllvl > 1) ? 12.5 : 10.0));
            //毒伤上限
            this.skillgain = ((double)lvl + sld / 100.0) * 8.0 + (double)((this.skilllvl == 5) ? 10000 : ((this.skilllvl == 4) ? 12000 : 8000));
        }
        else if (this.skillid >= 1021 && this.skillid <= 1025) {
            this.skilltype = "震慑";
            this.skillsum = geshu(this.skilllvl, (int)sld, this.skilltype);
            //当前生命百分比伤害
            this.skillhurt = (double)(int)((value + sld * sv / 1000.0) * 1000.0) / 10.0;
        }
        else if (this.skillid >= 1026 && this.skillid <= 1030) {
            this.skilltype = "力量";
            this.skillcontinued = 7;
            this.skillsum = geshu(this.skilllvl, (int)sld, this.skilltype);
            //附加状态振幅百分比
            this.skillgain = (double)(int)((value + sld * sv / 1000.0) * 1000.0) / 10.0;
        }
        else if (this.skillid >= 1031 && this.skillid <= 1035) {
            this.skilltype = "抗性";
            this.skillcontinued = 7;//持续回合
            //命中个数
            this.skillsum = geshu(this.skilllvl, (int)sld, this.skilltype);
            this.skillgain = (double)(int)((value + sld * sv / 1000.0) * 1000.0) / 10.0;
        }
        else if (this.skillid >= 1036 && this.skillid <= 1040) {
            this.skilltype = "加速";
            this.skillcontinued = 7;
            this.skillsum = geshu(this.skilllvl, (int)sld, this.skilltype);
            //附加状态振幅百分比
            this.skillgain = (double)(int)((value + sld * sv / 1000.0) * 1000.0) / 10.0;
        }
        else if (this.skillid >= 1041 && this.skillid <= 1065) {
            //风火水雷鬼火系
            if (this.skillid < 1046) {
                this.skilltype = "风";
            }
            else if (this.skillid < 1051) {
                this.skilltype = "雷";
            }
            else if (this.skillid < 1056) {
                this.skilltype = "水";
            }
            else if (this.skillid < 1061) {
                this.skilltype = "火";
            }
            else {
                this.skilltype = "鬼火";
            }
            this.skillsum = geshu(this.skilllvl, (int)sld, this.skilltype);
            //伤害
            this.skillhurt = (int)((value + sv * (1.0 + 5.0 * sld / 5000.0 * (10.0 - sld / 5000.0) / 2.0)) * (double)lvl);
        }
        else if (this.skillid >= 1066 && this.skillid <= 1070) {
            this.skilltype = "三尸虫";
            this.skillsum = geshu(this.skilllvl, (int)sld, this.skilltype);
            //伤害
            this.skillhurt = (int)((value * (double)lvl + sld * sv / 1000.0) * 1000.0) / 10;
            //附加状态振幅百分比
            this.skillgain = value * 100.0 + (double)(int)(sld / 250.0);
        }
        else if (this.skillid >= 1071 && this.skillid <= 1075) {
            this.skilltype = "遗忘";
            this.skillcontinued = 7;
            this.skillhitrate = value + sv * new BigDecimal(Math.pow(sld, 0.3)).setScale(2, 4).doubleValue();
            //   命中个数
            this.skillsum = geshu(this.skilllvl, (int)sld, this.skilltype);
        }
        else if (this.skillid >= 1076 && this.skillid <= 1080) {//魅惑
            this.skilltype = "smmh";
            this.skillcontinued = 7;//持续回合
            //   命中个数
            this.skillsum = geshu(this.skilllvl, (int)sld, this.skilltype);
            //附加状态振幅百分比
            this.skillgain = (double)(int)((value + sld * sv / 1000.0) * 1000.0) / 10.0;
        }
        else if (this.skillid >= 1081 && this.skillid <= 1085) {
            this.skilltype = "霹雳";
            //命中概率
            this.skillhurt = value + sv * new BigDecimal(Math.pow(sld, 0.25)).setScale(2, 4).doubleValue();
            this.skillgain = ((this.skilllvl == 5) ? 30.0 : ((this.skilllvl == 4) ? 35.0 : 25.0));
            this.skillcontinued = ((this.skilllvl == 5) ? 3 : ((this.skilllvl == 4) ? 5 : 2));
            this.skillsum = geshu(this.skilllvl, (int)sld, this.skilltype);
        }
        else if (this.skillid >= 1086 && this.skillid <= 1090) {
            this.skilltype = "沧波";
            //命中概率
            this.skillhurt = value + sv * new BigDecimal(Math.pow(sld, 0.25)).setScale(2, 4).doubleValue();
            this.skillgain = ((this.skilllvl == 5) ? 30.0 : ((this.skilllvl == 4) ? 35.0 : 25.0));
            this.skillhitrate = ((this.skilllvl == 5) ? 20.0 : ((this.skilllvl == 4) ? 25.0 : 15.0));
            this.skillcontinued = 3;
            this.skillsum = geshu(this.skilllvl, (int)sld, this.skilltype);
        }
        else if (this.skillid >= 1091 && this.skillid <= 1095) {
            this.skilltype = "甘霖";
            this.skillhurt = (double)((int)((value * (double)lvl + sld * sv / 1000.0) * 1000.0) / 60);
            this.skillcontinued = 3;
            this.skillsum = geshu(this.skilllvl, (int)sld, this.skilltype);
        }
        else if (this.skillid >= 1096 && this.skillid <= 1100) {
            this.skilltype = "扶摇";
            this.skillhurt = value + sv * new BigDecimal(Math.pow(sld, 0.25)).setScale(2, 4).doubleValue();
            this.skillgain = ((this.skilllvl == 5) ? 30.0 : ((this.skilllvl == 4) ? 35.0 : 25.0));
            this.skillhitrate = ((this.skilllvl == 5) ? 15.0 : ((this.skilllvl == 4) ? 20.0 : 10.0));
            this.skillcontinued = 3;
            this.skillsum = geshu(this.skilllvl, (int)sld, this.skilltype);
        }
        this.skillblue = (int)((double)this.skillblue * (sld / 25000.0 + 1.0));
    }
    
    public void PetTalentSkill(Skill skill, double value, double sv, long qm) {
        switch (this.skillid) {
            case 1201: {//顾影自怜
                this.skillbeidong = 1;
                this.skillhurt = 40.0;
                return;
            }
            case 1206: {//天见犹怜
                this.skillbeidong = 1;
                this.skillhurt = 40.0;
                return;
            }
            case 1210: {//如来神掌
                this.skillbeidong = 1;
                return;
            }
            case 1211: {//浪子回头
                this.skillbeidong = 1;
                this.skillhurt = 20.0;
                return;
            }
            case 1212: {//秒转乾坤
                this.skillbeidong = 1;
                this.skillhurt = 50.0;
                return;
            }
            case 1200: {//泽屁天下
                this.skilltype = "庇护";
                this.skillcontinued = 3;
                this.skillsum = 10;
                return;
            }
            case 1202: {//慧眼菩提
                this.skilltype = "减魔鬼";
                this.skillsum = 3;
                this.skillcontinued = 2;
                this.skillhurt = 1.0;
                return;
            }
            case 1203: {//醍醐灌顶
                this.skilltype = "减魔鬼";
                //技能个数
                this.skillsum = 1;
                //持续回合
                this.skillcontinued = 3;
                this.skillhurt = 2.0;
                return;
            }
            case 1204: {
                this.skilltype = "减人仙";
                this.skillsum = 3;
                this.skillcontinued = 2;
                this.skillhurt = 1.0;
                return;
            }
            case 1205: {//红衰翠减
                this.skilltype = "减人仙";
                this.skillsum = 1;
                this.skillcontinued = 3;
                this.skillhurt = 2.0;
                return;
            }
            case 1214://恭行天罚
                this.skilltype = "加狂暴";
                this.skillcontinued = skilllvl == 4 ? 2 : 3; //持续回合
                //命中个数
                this.skillsum = geshu(this.skilllvl, 1, this.skilltype);
                this.skillgain = (double) ((int) ((value + 1 * sv / 1000) * 1000)) / 10;
                return;
            case 1280://开山裂石
            case 1281://戟指怒目
            case 1282: {
                this.skilltype = "加狂暴";
                this.skillcontinued = ((this.skilllvl == 4) ? 2 : 3);
                this.skillsum = geshu(this.skilllvl, 1, this.skilltype);
                this.skillgain = (double)(int)((value + 1.0 * sv / 1000.0) * 1000.0) / 10.0;
                return;
            }
            case 1215: {//移花接木
                this.skillsum = 1;
                return;
            }
            case 1216: {//暗影离魂
                this.skillsum = 2;
                this.skillhurt = 40.0 + CustomFunction.XS(qm, 1.0);
                this.skillgain = 20.0 + CustomFunction.XS(qm, 0.7);
                this.skillbeidong = 1;
                return;
            }
            case 1217: {//亢龙有悔
                //技能个数
                this.skillsum = 9;
                this.skillhurt = 30.0 + CustomFunction.XS(qm, 1.0);
                this.skillgain = 20.0 + CustomFunction.XS(qm, 0.7);
                this.skillbeidong = 1;
                return;
            }
            case 1218: {//水中探月
                //技能个数
                this.skillhurt = 20.0 + CustomFunction.XS(qm, 0.7);
                this.skillgain = 20.0 + CustomFunction.XS(qm, 0.7);
                this.skillbeidong = 1;
                return;
            }
            case 1219: {//雾里看花
                //技能个数
                this.skillhurt = 30.0;
                this.skillbeidong = 1;
                return;
            }
            case 1220: {//杀神成仁
                //技能个数
                this.skillhurt = 25.0;
                this.skillbeidong = 1;
                return;
            }
            case 1221: {//神迟魂钝

                this.skillgain = 15.0;
                this.skilltype = "减速";
                //技能个数
                this.skillsum = 2;
                //持续回合
                this.skillcontinued = 2;
                return;
            }
            case 1222://震慑抵抗
            case 1223: {//千松扫尾
                this.skillhurt = 15.0;
                this.skilltype = "伤害加深";
                this.skillcontinued = 2;
                this.skillbeidong = 1;
                return;
            }
            case 1224: {//梵音初晓
                this.skillhurt = 40.0;
                this.skilltype = "解除控制";
                this.skillsum = 1;
                return;
            }
            case 1225: {//鼓音三叠
                this.skillhurt = 33.0;
                this.skillgain = 66.0;
                this.skillbeidong = 1;
                return;
            }
            case 1227: {//义薄云天
                this.skillhurt = 30.0;//加成
                this.skillbeidong = 1;
                return;
            }
            case 1228: {//昆山玉碎
                this.skilltype = "bbksys";
                //技能个数     1 1.2 1.45 2   1.2成长  10的基础
                this.skillhitrate = value + sv * CustomFunction.XS(qm, 0.66);//命中率
                this.skillhurt = this.skillhitrate * 1.3;//伤害加成 法力*加成/100
                this.skillbeidong = 1;
                return;
            }
            case 1229: {//云起潮生不用
                this.skilltype = "1229";
                this.skillhitrate = value + sv * CustomFunction.XS(qm, 0.8);
                this.skillbeidong = 1;
                return;
            }
            case 1888: {//云起潮生
                this.skilltype = "1888";
                this.skillhitrate = value + sv * CustomFunction.XS(qm, 0.8);//命中率
                this.skillbeidong = 1;
                return;
            }
            case 1230: {//势不可挡
                this.skilltype = "1230";
                this.skillhurt = 30.0 + CustomFunction.XS(qm, 0.8);//命中率
                this.skillgain = 10.0 + CustomFunction.XS(qm, 0.4);
                return;
            }
            case 1231: {//天降脱兔
                this.skilltype = "1231";
                this.skillhurt = (double)(int)CustomFunction.XS(qm, 0.1);
                this.skillgain = 35.0 + CustomFunction.XS(qm, 0.8);
                this.skillbeidong = 1;
                return;
            }
            case 1232: {//灵魂封魔
                this.skilltype = "1232";
                this.skillsum = 1 + (int)CustomFunction.XS(qm, 0.07);
                this.skillgain = 20.0 + CustomFunction.XS(qm, 1.3);
                this.skillcontinued = 1 + (int)CustomFunction.XS(qm, 0.05);
                this.skillbeidong = 1;
                return;
            }
            case 1233: {//重振旗鼓
                this.skilltype = "1233";
                this.skillgain = value + CustomFunction.XS(qm, sv);
                this.skillbeidong = 1;
                return;
            }
            case 1234: {//防不慎防
                this.skilltype = "1234";
                this.skillgain = value + CustomFunction.XS(qm, sv);
                this.skillcontinued = 3;
                return;
            }
            case 1235://坚若磐石

            case 1236: {//扭转乾坤
                this.skilltype = this.skillid + "";
                this.skillbeidong = 1;
                return;
            }
            case 1237: {//天降流火触发概率
                this.skilltype = "1237";
                this.skillhurt = 20.0 + CustomFunction.XS(qm, 0.8);
                this.skillgain = 20.0 + CustomFunction.XS(qm, 0.9);
                this.skillbeidong = 1;
                return;
            }
            case 1238: { //归去来兮
                this.skilltype = "1238";
                this.skillgain = value + CustomFunction.XS(qm, sv);
                this.skillhurt = (double)qm;//伤害加成 法力*加成/100;
                this.skillbeidong = 1;
                this.skillcontinued = 3;
                return;
            }
            case 1240: {//一御当千
                this.skilltype = this.skillid + "";
                this.skillgain = skill.getValue() + CustomFunction.XS(qm, skill.getGrow());
                this.skillhurt = skill.getValue1() + CustomFunction.XS(qm, skill.getGrow1());
                this.skillbeidong = 1;
                this.skillcontinued = 2;
                return;
            }
            case 1243: {//百草竞发
                this.skilltype = String.valueOf(this.skillid);
                this.skillgain = 40.0 + CustomFunction.XS(qm, 1.0);
                this.skillhurt = 100.0 + CustomFunction.XS(qm, 0.7);
                this.skillbeidong = 1;
                this.skillcontinued = 2;
                return;
            }
            case 1244: {// 剑荡八荒
                this.skillhurt = 20.0 + CustomFunction.XS(qm, 0.7);
                this.skillgain = 20.0 + CustomFunction.XS(qm, 0.7);
                this.skillbeidong = 1;
                return;
            }
            case 1246: {//法天象地
                this.skillhurt = (double)(qm / 400L);
                this.skillbeidong = 1;
                return;
            }
            case 1247://不动明王
            case 1248://大威天龙
            case 1249://妙手空空
            case 1253: {//大日如来神掌
                this.skillbeidong = 1;
                return;
            }
            case 1250://偷梁换柱
            case 1251://遮天蔽日
            case 1252: {//大闹天宫
                this.skilltype = String.valueOf(this.skillid);
                this.skillbeidong = 1;
                this.skillgain = 20.0 + CustomFunction.XS(qm, 0.7);
                return;
            }
            case 1254: {//万剑归宗
                this.skillhurt = (double)(qm / 100L);
                this.skillsum = 5;
                this.skillbeidong = 0;
                return;
            }
            case 1260: {//万佛朝宗
                this.skillhurt = (double)(qm / 100L);
                this.skillsum = 7;
                this.skillbeidong = 0;
                return;
            }
            case 1261: {//一刀流
                this.skillhurt = (double)(qm / 100L);
                this.skillcontinued = 2;
                this.skillsum = 3;
                this.skillbeidong = 0;
                return;
            }
            case 1262: {//泰山压顶
                this.skillhurt = (double)(qm / 100L);
                this.skillcontinued = 2;
                this.skillsum = 3;
                this.skillbeidong = 0;
                return;
            }
            case 1263: {//金身不灭
                //技能个数
                this.skillsum = 3;
                //持续回合
                this.skillbeidong = 0;
                this.skillcontinued = 3;
                this.skillhurt = 2.0;
                break;
            }
            case 1264: {//万剑穿心
                this.skillhurt = (double)(qm / 100L);
                this.skillsum = 5;
                this.skillbeidong = 0;
                return;
            }
            case 1265: {
                return;
            }
            case 1266: {
                return;
            }
            case 1267: {
                return;
            }
            case 1268: {
                return;
            }
            case 1269: {
                return;
            }
            case 1270: {//藏锋蓄势
                return;
            }
            case 1271: {
                return;
            }
            case 1272: {
                return;
            }
            case 1273: {
                return;
            }
            case 1279://流连忘返
                this.skilltype = "流连忘返";
                this.skillhurt=15+CustomFunction.XS(qm, 0.38);
                this.skillcontinued=3;
                this.skillsum=10;
                return;
            case 1286://明月分辉

                this.skilltype="1286";
                this.skillcontinued=1;
                this.skillsum=1;
                this.skillgain = value + CustomFunction.XS(qm, 0.5);//几率
                this.skillhurt = value + CustomFunction.XS(qm, 0.05);;//伤害
                this.skillbeidong=1;
                return;
            case 1288://素手
                this.skilltype="素手";
                this.skillcontinued=1;
                this.skillsum=1;
                this.skillhurt = value+CustomFunction.XS(qm, 3.1);

                return;
            case 1289://青鳞
                this.skilltype="青鳞";
                this.skillcontinued=1;
                this.skillsum=1;
                this.skillhurt = value+CustomFunction.XS(qm, 8.4);
                return;
            case 1287://度厄
                this.skilltype="度厄";
                this.skillcontinued=1;
                this.skillsum=1;
                this.skillhurt = value;
                return;
            case 1290: {//仙音缕缕
                this.skilltype = "1290";
                this.skillsum = 2;
                this.skillhurt = 30.0 + CustomFunction.XS(qm, 1.0);
                this.skillgain = 100.0 + CustomFunction.XS(qm, 0.7);
                this.skillbeidong = 1;
                return;
            }
            case 1291: {
                this.skilltype = "1291";
                this.skillbeidong = 1;
                return;
            }
            case 1292: {
                this.skilltype = "1292";
                this.skillbeidong = 1;
                return;
            }
            case 1293: {
                return;
            }
            case 1294: {//振羽惊雷
                this.skilltype = "振羽惊雷";
                this.skillbeidong = 1;
                return;
            }
            case 1295: {
                this.skilltype = "行云流水";
                this.skillbeidong = 1;
                return;
            }
            case 1296: {//枪出如龙
                this.skilltype = this.skillname;
                this.skillbeidong = 1;
                this.skillhurt = 45.0;
                this.skillgain = 10.0 + CustomFunction.XS(qm, 0.7);
                return;
            }
            case 1297: {//乘黄被动
                this.skilltype = "1297";
                this.skillgain = CustomFunction.XS(qm, 1.51);
                this.skillbeidong = 1;
                return;
            }
            case 1299: {//碧海滔天
                this.skilltype = "1299";
                this.skillgain = 330.0;
                this.skillhurt = 60.0;
                this.skillbeidong = 1;
                return;
            }
            case 450050: {
                this.skilltype = "450050";
                this.skillbeidong = 0;
                this.skillgain = sv;
                this.skillsum = (int)skill.getValue1();
                return;
            }
            case 450051: {
                this.skilltype = "450051";
                this.skillbeidong = 1;
                this.skillgain = skill.getValue() + CustomFunction.XS(qm, skill.getGrow());
                this.skillsum = (int)skill.getValue1();
                return;
            }
        }
    }
    public Double[] getWl(Skill skill) {
        try {
            if (skill == null || StringUtils.isBlank(skill.getPetSkillswl()))
                return null;
            String wlstr ="";
            if (StringUtils.isNotBlank(skill.getPetSkillswl())) {
                String[] v = skill.getValue4().split("\\|");
                for (String s : skill.getPetSkillswl().split("\\|")) {
                    if(s.startsWith(skill.getSkillid()+"")){
                        wlstr = s;
                        break;
                    }
                }

                Integer lvl = Integer.parseInt(wlstr.split("=")[1]);
                Double[] add = new Double[v.length];
                for (int i = 0; i < v.length; i++) {
                    double v1 = Double.parseDouble(v[i]) * lvl;
                    add[i] = v1;
                }
                return add;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
    public void PetSkill(double value, double sv, int lvl, int born, long qm) {
        PetSkill(value, sv, lvl, born, qm, null);
    }
    //召唤兽技能
    public void PetSkill(double value, double sv, int lvl, int born, long qm, Skill skill) {
        Double[] wlsx = null;
        if (skill != null) {
            wlsx = getWl(skill);
        }
        if (this.skillid == 1833 || this.skillid == 1832) {
            if (this.skillid == 1832) {
                this.skillhurt = 15.0;
                this.skillhurt += CustomFunction.XS(qm, 0.3);
            }
            else {
                this.skillhurt = 30.0;
                this.skillhurt += CustomFunction.XS(qm, 0.6);
            }
            this.skilltype = "分裂";
            this.skillbeidong = 1;
            if (wlsx != null) {
                skillhurt += wlsx[0];
            }
            return;
        }
        else {
            if (this.skillid == 1890) {
                this.skillhurt = 10.0 + CustomFunction.XS(qm, 1.0);
                this.skillbeidong = 1;
            }
            else {
                if (this.skillid == 1831) {
                    this.skilltype = "追击";
                    this.skillhurt = 35.0 + CustomFunction.XS(qm, 0.5);
                    this.skillbeidong = 1;
                    if (wlsx != null) {
                        skillgain = wlsx[0];
                        skillgain1 = wlsx[1];
                    }
                    return;
                }
                if (this.skillid == 1888) {
                    this.skilltype = "1888";
                    this.skillhitrate = value + CustomFunction.XS(qm, sv);//命中率
                    this.skillbeidong = 1;
                    if (wlsx != null) {
                        skillhitrate += wlsx[0];
                    }
                    return;
                }
                if (this.skillid == 1887) {
                    this.skilltype = "高级禅机顿悟";
                    this.skillhurt = value + CustomFunction.XS(qm, 0.4);
                    this.skillbeidong = 1;
                    return;
                }
                if (this.skillid == 1509) {
                    this.skilltype = "复活";
                    this.skillbeidong = 1;
                    if (wlsx != null && Battlefield.isV(wlsx[0])) {
                        skillgain1 = wlsx[0];
                    }
                    return;
                }
                if (this.skillid == 1829 || this.skillid == 1878) {
                    this.skilltype = "作鸟兽散";
                    this.skillsum = ((this.skillid == 1829) ? 10 : 3);
                    this.skillgain = value;
                    this.skillbeidong = 1;
                    if (wlsx != null && Battlefield.isV(wlsx[0])) {
                        this.skillsum = 4;
                    }
                    return;
                }
                if (this.skillid == 1828) {
                    this.skilltype = "化无";
                    this.skillbeidong = 1;
                    return;
                }
                if (this.skillid == 1882) {
                    this.skilltype = "高级帐饮东都";
                    this.skillbeidong = 1;
                    return;
                }
                if (this.skillid == 1883) {
                    this.skilltype = "高级源泉万斛";
                    this.skillbeidong = 1;
                    return;
                }
                if (this.skillid == 1884) {
                    this.skilltype = "高级神功鬼力";
                    this.skillbeidong = 1;
                    return;
                }
                if (this.skillid == 1885) {
                    this.skilltype = "高级倍道兼行";
                    this.skillbeidong = 1;
                    return;
                }
                if (this.skillid == 1809 || (this.skillid >= 1825 && this.skillid <= 1827)) {
                    this.skilltype = "隐身";
                    this.skillbeidong = 1;
                    this.skillhitrate = 40.0 + CustomFunction.XS(qm, 0.7);
                    return;
                }
                if (this.skillid == 1607 || this.skillid == 1877) {
                    this.skillsum = ((this.skillid == 1607) ? 2 : 1);
                    this.skilltype = "隐身技";
                    return;
                }
                if (this.skillid == 1808 || this.skillid == 1824) {
                    this.skillbeidong = 1;
                    this.skilltype = "遗产";
                    this.skillhitrate = 40.0 + CustomFunction.XS(qm, 0.7);
                    return;
                }
                if (this.skillid == 1804) {
                    this.skillbeidong = 1;
                    this.skilltype = "忠诚";
                    return;
                }
                if (this.skillid == 1823 || this.skillid == 1805 || this.skillid == 1821) {
                    this.skillbeidong = 1;
                    this.skilltype = "自医";
                    this.skillhitrate = 40.0 + CustomFunction.XS(qm, 0.7);
                    return;
                }
                if (this.skillid == 1822 || this.skillid == 1820 || this.skillid == 1807) {
                    this.skillbeidong = 1;
                    this.skilltype = "击其不意";
                    this.skillhitrate = 40.0 + CustomFunction.XS(qm, 0.7);
                    if (wlsx != null) {
                        skillgain1 = wlsx[0];
                    }
                    return;
                }
                if (this.skillid == 1606 || this.skillid == 1611) {
                    this.skillsum = ((this.skillid == 1611) ? 3 : 10);
                    this.skillgain = ((this.skillid == 1611) ? 0.5 : 0.6);
                    this.skilltype = "回血技";
                    if (skillid == 1611) {
                        if (wlsx != null) {
                            skillhurt = wlsx[0];
                            skillgain1 = wlsx[1];
                        }
                    }
                    return;
                }
                if (this.skillid == 1612 || this.skillid == 1608) {
                    this.skillhurt = 111.0;
                    this.skillsum = ((this.skillid == 1608) ? 10 : 3);
                    this.skilltype = "解除控制";
                    if (skillid == 1612 && wlsx != null) {
                        skillgain1 = wlsx[0];
                    }
                    return;
                }
                if ((this.skillid >= 1600 && this.skillid <= 1605) || this.skillid == 1876 || this.skillid == 1889) {
                    this.skillsum = 10;
                    this.skilltype = "五行";
                    if (wlsx != null) {
                        skillhurt = wlsx[0];
                    }
                    return;
                }
                if (this.skillid == 1806 || (this.skillid >= 1820 && this.skillid <= 1827)) {
                    Skill skillXls = GameServer.getSkill("1806");//获取技能表格参数
                    this.skillbeidong = 1;
                    this.skilltype = "闪现";
//                    this.skillhitrate = 10.0 + CustomFunction.XS(qm, skillXls.getValue() / 100.0);
                    this.skillhitrate = 15.0 + (35.0 / 100000000.0) * qm;
                    return;
                }
                if (this.skillid == 1237) {
                    this.skilltype = "天降流火";
                    return;
                }
                if (this.skillid == 1335) {
                    this.skilltype = "莲火流星";
                    return;
                }
                if (this.skillid == 1609) {
                    this.skilltype = "兵临城下";
                    return;
                }
                if (this.skillid == 1610) {
                    this.skilltype = "奋蹄扬威";
                    return;
                }
                if (this.skillid == 1830 || this.skillid == 1872) {
                    this.skilltype = "bbjs";
                    this.skillsum = ((this.skillid == 1830) ? 5 : 3);//视死如归
                    this.skillbeidong = 1;
                    if (wlsx != null && Battlefield.isV(wlsx[0])) {//视死如归
                        skillsum = 3;
                    }
                    return;
                }
                if (this.skillid == 1842 || this.skillid == 1871) {
                    this.skilltype = "bbdt";
                    this.skillsum = ((this.skillid == 1842) ? 5 : 3);
                    this.skillbeidong = 1;
                    if (wlsx != null && Battlefield.isV(wlsx[0])) {
                        skillsum = 3;
                    }
                    return;
                }
                if (this.skillid == 1839) {
                    this.skilltype = "bbss";
                    this.skillsum = 1;
                    this.skillhitrate = 28.0 + CustomFunction.XS(qm, 0.6);
                    this.skillcontinued = 2;
                    return;
                }
                if (this.skillid == 1838) {
                    this.skilltype = "bbjr";
                    this.skillbeidong = 1;
                    if (wlsx != null) {
                        skillgain1 = wlsx[0];
                    }
                    return;
                }
                if (this.skillid == 1835 || this.skillid == 1875) {//报复  回源
                    this.skillbeidong = 1;
                    this.skillhitrate = 10.0;
                    if (this.skillid == 1835) {
                        this.skilltype = "bbbf";
                        this.skillhurt = 250.0;
                    }
                    else {
                        this.skilltype = this.skillid + "";
                        this.skillhurt = 50.0;
                    }
                    if (wlsx != null) {
                        skillgain = wlsx[0];
                        skillgain1 = wlsx[1];
                    }
                    return;
                }
                else {
                    if (this.skillid == 1834) {
                        this.skilltype = "bbxr";
                        this.skillhurt = 250.0;
                        this.skillbeidong = 1;
                        if (wlsx != null) {
                            skillgain += wlsx[0];
                        }
                        return;
                    }
                    if (this.skillid == 1836) {
                        this.skilltype = "bbyh";
                        this.skillhurt = 250.0;
                        this.skillbeidong = 1;
                        if (wlsx != null) {
                            skillgain += wlsx[0];
                        }
                        return;
                    }
                    if (this.skillid == 1837) {
                        this.skilltype = "bbtm";
                        this.skillhurt = 250.0;
                        this.skillbeidong = 1;
                        return;
                    }
                    if (this.skillid == 1840) {
                        this.skilltype = "bbmcqh";
                        this.skillhurt = 250.0;
                        this.skillbeidong = 1;
                        return;
                    }
                    if (this.skillid == 1841) {
                        this.skilltype = "bbsgqx";
                        this.skillhurt = 250.0;
                        this.skillbeidong = 1;
                        return;
                    }
                    if (this.skillid == 1843 || this.skillid == 1844 || this.skillid == 1847) {
                        this.skilltype = this.skillid + "";
                        this.skillbeidong = 1;
                        return;
                    }
                    if (this.skillid == 1848 || this.skillid == 1858) {
                        this.skilltype = this.skillid + "";
                        this.skillbeidong = 1;
                        this.skillhitrate = value + CustomFunction.XS(qm, 0.16);
                        if (wlsx != null) {
                            skillhitrate += wlsx[0];
                        }
                        return;
                    }
                    if (this.skillid >= 1849 && this.skillid <= 1854) {
                        this.skilltype = ((this.skillid % 2 != 0) ? this.skillid : (this.skillid - 1)) + "";
                        this.skillbeidong = 1;
                        this.skillhitrate = value + CustomFunction.XS(qm, (this.skillid % 2 != 0) ? 0.16 : 0.2);
                        if (wlsx != null) {
                            skillhitrate += wlsx[0];
                        }
                    }
                    else if (this.skillid >= 1861 && this.skillid <= 1864) {
                        this.skilltype = ((this.skillid % 2 != 0) ? this.skillid : (this.skillid - 1)) + "";
                        this.skillbeidong = 1;
                        this.skillhitrate = value;
                    }
                    else if (this.skillid == 1866) {
                        this.skilltype = this.skillid + "";
                        this.skillbeidong = 1;//人法基础    仙法鬼法4倍   三尸虫 100倍
                        this.skillhurt = value + CustomFunction.XS(qm, sv);
                        this.skillcontinued = 3;
                    }
                    else if (this.skillid == 1867) {
                        this.skilltype = this.skillid + "";
                        this.skillbeidong = 1;
                    }
                    else if (this.skillid == 1868) {
                        this.skilltype = this.skillid + "";
                        this.skillsum = 1;
                    }
                    else if (this.skillid == 1869) {
                        this.skilltype = this.skillid + "";
                        this.skillsum = 1;
                        this.skillhitrate = value + CustomFunction.XS(qm, sv);
                        this.skillcontinued = 2;
                    }
                    else if (this.skillid == 1870) {
                        this.skilltype = this.skillid + "";
                        this.skillbeidong = 1;
                        this.skillsum = 2;
                        this.skillhurt = value + CustomFunction.XS(qm, sv);
                    }
                    else if (this.skillid == 1873 || this.skillid == 1874) {
                        this.skilltype = this.skillid + "";
                        this.skillbeidong = 1;
                        this.skillhurt = value;
                        if (wlsx != null) {
                            this.skillhurt += wlsx[0];
                        }
                    }
                    else if (this.skillid == 1879 || this.skillid == 1880) {
                        this.skilltype = this.skillid + "";
                        this.skillbeidong = 1;
                        this.skillhurt = value + CustomFunction.XS(qm, sv);
                        if (wlsx != null) {
                            this.skillhurt += wlsx[0];
                        }
                    }
                    else if (this.skillid == 1881) {
                        this.skilltype = this.skillid + "";
                        this.skillbeidong = 1;
                    }
                    else if (this.skillid == 1886) {
                        this.skilltype = "抗反震";
                        this.skillbeidong = 1;
                        this.skillhurt = 25.0;
                        this.skillgain = 100.0;
                    }
                }
            }
            return;
        }
    }
    /**
     * 召唤兽装备技能 基础值  星级  等级
     */
    public void PetEquipSkill(double value, double sld, int lvl) {
        this.skillgain = (double)(int)(value * sld / 5.0 * Math.sqrt((double)lvl));
        if (this.skillid != 1313 && (this.skillid < 1332 || this.skillid > 1334)) {
            this.skillbeidong = 1;
        }
        if (this.skillid == 1306 || this.skillid == 1307) {
            this.skillhurt = this.skillgain * 0.8;
        }
        else if (this.skillid == 1309) {
            this.skillhurt = this.skillgain * 0.7;
        }
        else if (this.skillid == 1309) {
            this.skillhurt = this.skillgain * 0.7;
        }
        else if (this.skillid == 1309) {
            this.skillhurt = this.skillgain * 0.7;
        }
        else if (this.skillid == 1333) {
            this.skillhurt = 100.0 + this.skillgain;
            this.skillgain = (double)(int)(1.0 + this.skillgain * 0.07);
        }
        else if (this.skillid == Integer.parseInt(TypeUtil.BB_TJLH) || this.skillid == Integer.parseInt(TypeUtil.BB_SGLX)) {
            this.skillbeidong = 1;
        }
        else if (this.skillid == 1335) {
            //莲火流星
            this.skillhurt = this.skillgain * 0.8;
        }
        else if (this.skillid == 1336) {
            //百战重生
            this.skillhurt = this.skillgain * 0.7;
        }
        else if (this.skillid == 1332) {
            this.skillbeidong = 0;
        }
        else if (this.skillid == 1324) {
            this.skillbeidong = 0;
        }
        else if (this.skillid == 1336) {
            this.setSkilltype("强化封印");
            this.skillbeidong = 1;
            this.skillgain = 0.9 * (double)lvl * sld;
        }
        else if (this.skillid == 1337) {
            setSkilltype("灯火阑珊");
            this.skillbeidong = 1;
            this.skillhurt = 0.1 * lvl * sld;
            this.skillgain = 0.45 * lvl * sld;
        }
    }
    //灵宝技能                    born合计数 sld大于0就是擅长
    public void LingSkill(double value, double sv, int born, int lvl, double sld) {
        this.skilllvl = born;
        if (this.skillid == 3034 || this.skillid == 3040 || this.skillid == 3035) {
            this.skillblue = 1;
            return;
        }
        if (sld > 0.0) {
            this.skillblue = 1;
        }
        else {
            this.skillblue = 0;
        }
        int sl = lvl * 100;
        this.skillhurt = (value + sv * (double)(1 + 5 * sl / 5000 * (10 - sl / 5000) / 2)) * (double)lvl;
        switch (this.skillid) {
            case 3001:
            case 3002:
            case 3003: {
                this.skillsum = this.getsum2(this.skilllvl) + ((this.skillblue == 0) ? 0 : 1);
                break;
            }
            case 3004: {
                this.skillsum = this.getsum2(this.skilllvl);
                break;
            }
            case 3005:
            case 3006: {
                this.skillsum = this.getsum2(this.skilllvl);
                this.skillhurt *= ((this.skillblue == 0) ? 1.0 : 1.3);
                break;
            }
            case 3023: {
                this.skilltype = "骨盾";
                this.skillgain = this.skillhurt * ((this.skillblue == 0) ? 1.0 : 1.3);
                this.skillcontinued = 3;
                this.skillsum = this.getsum2(this.skilllvl);
                this.skillhurt = 0.0;
                break;
            }
            case 3007:
            case 3008: {
                this.skillsum = this.getsum2(this.skilllvl);
                this.skilltype = "烧法";
                this.skillgain = value;
                this.skillhurt *= ((this.skillblue == 0) ? 1.0 : 1.3);
                break;
            }
            case 3009: {
                this.skillsum = this.getsum2(this.skilllvl);
                this.skilltype = "化羽";
                this.skillcontinued = ((this.skillblue == 0) ? 1 : 2);
                break;
            }
            case 3010: {
                this.skillgain = this.skillhurt;
                this.skillsum = this.skilllvl;
                this.skillcontinued = ((this.skillblue == 0) ? 2 : 3);
                break;
            }
            case 3021: {
                this.skillgain = value + (double)((this.skillblue == 0) ? 0 : -10);
                this.skillsum = this.skilllvl;
                break;
            }
            case 3022: {
                this.skillgain = this.skillhurt / 2.0 * ((this.skillblue == 0) ? 1.0 : 1.5);
                this.skillsum = this.skilllvl;
                this.skillcontinued = 2;
                break;
            }
            case 3024: {
                this.skillgain = (double)(this.skilllvl + ((this.skillblue == 0) ? 0 : 1));
                this.skillcontinued = 3;
                break;
            }
            case 3033: {
                this.skillhurt = 30.0;
                this.skillhurt += (double)((this.skillblue == 0) ? 0 : 10);
                this.skillsum = this.skilllvl;
                break;
            }
            case 3011:
            case 3012: {
                this.skillhurt *= 1.0 + (double)this.skilllvl / 2.0;
                break;
            }
            case 3031:
            case 3032: {
                this.skillgain = ((this.skillblue == 0) ? 0.0 : 0.3);
                break;
            }
            case 3013:
            case 3014:
            case 3015: {
                this.skillsum = this.getsum3(this.skilllvl) + ((this.skillblue == 0) ? 0 : 1);
                break;
            }
            case 3016: {
                this.skilltype = "风水";
                this.skillsum = this.getsum4(this.skilllvl);
                this.skillgain = (double)(75 + ((this.skillblue == 0) ? 0 : 10));
                if (this.skilllvl > 4) {
                    this.skillcontinued = 3;
                    break;
                }
                else {
                    this.skillcontinued = 2;
                    break;
                }
            }
            case 3017: {
                this.skilltype = "雷火";
                this.skillsum = this.getsum4(this.skilllvl);
                this.skillgain = (double)(75 + ((this.skillblue == 0) ? 0 : 10));
                if (this.skilllvl > 4) {
                    this.skillcontinued = 3;
                    break;
                }
                else {
                    this.skillcontinued = 2;
                    break;
                }
            }
            case 3018: {
                this.skilltype = "鬼力";
                this.skillsum = this.getsum4(this.skilllvl);
                this.skillgain = (double)(75 + ((this.skillblue == 0) ? 0 : 10));
                if (this.skilllvl > 4) {
                    this.skillcontinued = 3;
                    break;
                }
                else {
                    this.skillcontinued = 2;
                    break;
                }
            }
            case 3019: {
                this.skillsum = this.getsum4(this.skilllvl);
                this.skillgain = (double)(30 + ((this.skillblue == 0) ? 0 : 10));
                if (this.skilllvl > 4) {
                    this.skillcontinued = 3;
                    break;
                }
                else {
                    this.skillcontinued = 2;
                    break;
                }
            }
            case 3020: {
                this.skillgain = (double)(33 + ((this.skillblue == 0) ? 0 : 5));
                this.skillsum = this.getsum4(this.skilllvl);
                break;
            }
            case 3025: {
                this.skilltype = "禁言";
                this.skillcontinued = 3 + ((this.skillblue == 0) ? 0 : 3);
                this.skillsum = 10;
                break;
            }
            case 3028: {
                this.skillsum = this.skilllvl - 3;
                this.skillcontinued = this.skilllvl - 3;
                this.skillgain = 5.0;
                break;
            }
            case 3029: {
                this.skillsum = this.skilllvl - 3;
                if (this.skilllvl > 1) {
                    this.skillcontinued = 2;
                }
                else {
                    this.skillcontinued = 1;
                }
                this.skillgain = (double)(50 + ((this.skillblue == 0) ? 0 : 25));
                break;
            }
            case 3030: {
                this.skillsum = this.skilllvl - 3;
                break;
            }
            case 3026: {
                this.skillsum = 2;
                this.skillcontinued = 2 + ((this.skillblue == 0) ? 0 : 1);
                break;
            }
            case 3027: {
                this.skillsum = this.getsum2(this.skilllvl) + ((this.skillblue == 0) ? 0 : 1);
                this.skillcontinued = this.skilllvl;
                break;
            }
        }
    }
    //法宝技能
    public void FaSkill(double value, double sv, int pz, int blvl) {
        this.skillhurt = value + (double)pz * sv;
        this.skillblue = 300;//修改法宝消耗怨气
        this.skillcontinued = FBUtil.getFBcx(this.skillid, blvl);
        this.initFB(this.skillid, blvl);
        if (this.skillid == 5014) {
            this.skillgain = this.skillhurt * 650.0;
        }
        else if (this.skillid == 5015) {
            this.skillgain = this.skillhurt * 2.0 / 3.0;
        }
        this.skillsum = FBUtil.getFBsum(this.skillid, blvl);
    }
    /**
     * 套装被动技能
     */
    public void SuitPassive(double value, double sv, double pz) {
        pz *= 1.0 + (pz - 1.0) * 0.02;
        switch (this.skillid) {
            case 6018: {//减抗
                this.skillhurt = value + sv * pz;//控制性法术
                this.skillgain = this.skillhurt * 2.0 + 1.0;//伤害性法术
                this.skillcontinued = 1;
                break;
            }
            case 6019: {
                this.skillhurt = value;
                this.skillhitrate = sv * pz;
                this.skillcontinued = 1;
                break;
            }
            case 6022: {
                this.skillhurt = value;
                this.skillhitrate = sv * pz;
                break;
            }
            case 6024: {
                this.skillhurt = value + sv * pz;
                break;
            }
            case 6025: {
                this.skillhurt = value + sv * pz;
                break;
            }
            case 6026: {
                this.skillhurt = value + sv * pz;
                break;
            }
            case 6027: {
                this.skillcontinued = 2;
                this.skillhurt = value;
                this.skillhitrate = sv * pz;
                break;
            }
            case 6028: {
                this.skillcontinued = 2;
                this.skillhurt = value;
                this.skillhitrate = sv * pz;
                break;
            }
            case 6033: {
                this.skillhurt = value + sv * pz;
                break;
            }
            case 6034: {
                this.skillhurt = value + sv * pz;
                break;
            }
            case 6037: {
                this.skillhurt = value + sv * pz;
                break;
            }
            case 6040: {
                this.skillhurt = value + sv * pz;
                this.skillcontinued = 2;
                break;
            }
            case 6041:
            case 6042: {
                this.skillhurt = value + sv * pz;
                break;
            }
            case 6045: {
                break;
            }
        }
    }
    /**
     * 套装主动技能
     */
    public void SuitInitiative(double value, double sv, int pz) {
        switch (this.skillid) {
            case 6029: {
                this.skillcontinued = 1;//持续回合
                this.skillgain = value + sv * (double)pz;
                this.skillsum = 1;
                break;
            }
            case 6038: {
                this.skillcontinued = 7;//持续回合
                this.skillgain = value + sv * (double)pz;
                this.skillsum = 1;
                break;
            }
            case 7002: {
                this.skillcontinued = 2;//持续回合
                this.skillgain = value + sv * (double)pz;
                this.skillsum = 1;
                break;
            }
            case 7003: {
                this.skillhurt = value;
                this.skillhitrate = sv * (double)pz;
                break;
            }
            case 7004: {
                this.skillhurt = value;
                this.skillhitrate = sv * (double)pz;
                break;
            }
            case 7007: {
                this.skillhurt = value + sv * (double)pz;
                break;
            }
            case 7008: {
                this.skillcontinued = 1;//持续回合
                this.skillhurt = value + sv * (double)pz;
                this.skillsum = 1;
                break;
            }
            case 7009: {
                this.skillcontinued = 1;//持续回合
                this.skillhurt = value + sv * (double)pz;
                this.skillsum = 1;
                break;
            }
            case 7011: {
                this.skillcontinued = 2;
                break;
            }
            case 7012: {
                this.skillhurt = value - sv * (double)pz;
                this.skillgain = value + sv * (double)pz;
                break;
            }
            case 7019: {
                this.skilltype = "隐身技";
                this.skillcontinued = 1;//持续回合
                this.skillsum = 10;
                break;
            }
            case 7020: {
                this.skillhurt = value + sv * (double)pz;
                this.skillsum = 1;
                break;
            }
            case 7025: {
                this.skillcontinued = 2;//持续回合
                this.skillhurt = value + sv * (double)pz;
                this.skillsum = 1;
                break;
            }
            case 7026: {
                this.skillcontinued = 3;//持续回合
                this.skillgain = value + sv * (double)pz;
                this.skillsum = 1;
                break;
            }
            case 7028: {
                this.skillcontinued = 1;//持续回合
                this.skillsum = 1;
                break;
            }
        }
    }
    
    public int getSkillid() {
        return this.skillid;
    }
    
    public void setSkillid(int skillid) {
        this.skillid = skillid;
    }
    
    public double getSkillhurt() {
        return this.skillhurt;
    }
    
    public void setSkillhurt(double skillhurt) {
        this.skillhurt = skillhurt;
    }
    
    public String getSkillname() {
        return this.skillname;
    }
    
    public void setSkillname(String skillname) {
        this.skillname = skillname;
    }
    
    public double getSkillgain() {
        return this.skillgain;
    }
    
    public void setSkillgain(double skillgain) {
        this.skillgain = skillgain;
    }
    
    public double getSkillgain1() {
        return this.skillgain1;
    }
    
    public void setSkillgain1(double skillgain1) {
        this.skillgain1 = skillgain1;
    }
    
    public String getSkilltype() {
        if (this.skilltype == null) {
            this.skilltype = "";
        }
        return this.skilltype;
    }
    
    public void setSkilltype(String skilltype) {
        this.skilltype = skilltype;
    }
    
    public int getSkillsum() {
        return this.skillsum;
    }
    
    public void setSkillsum(int l) {
        this.skillsum = l;
    }
    
    public void initFB(int id, int blvl) {
        switch (id) {
            case 5001: {
                this.skilltype = "fbYsjl";
                break;
            }
            case 5002: {
                this.skilltype = "fbJjl";
                break;
            }
            case 5003: {
                this.skilltype = "fbDsc";
                break;
            }
            case 5004: {
                this.skilltype = "fbQbllt";
                break;
            }
            case 5005: {
                this.skilltype = "fbHlz";
                break;
            }
            case 5006: {
                this.skilltype = "fbYmgs";
                break;
            }
            case 5007: {
                this.skilltype = "fbDsy";
                break;
            }
            case 5008: {
                this.skilltype = "fbJqb";
                break;
            }
            case 5009: {
                this.skilltype = "fbQw";
                break;
            }
            case 5010: {
                this.skilltype = "fbBld";
                break;
            }
            case 5011: {
                this.skilltype = "fbJge";
                break;
            }
            case 5012: {
                this.skilltype = "fbFty";
                break;
            }
            case 5013: {
                this.skilltype = "fbJljs";
                break;
            }
            case 5014: {
                this.skilltype = "fbBgz";
                break;
            }
            case 5015: {
                this.skilltype = "fbHd";
                break;
            }
        }
    }
    /**
     * 天演策
     */
    public void TYC(double value, double sv, int lvl, double value1, double sv1, double value2, double sv2, double value3, double sv3) {
        this.skillhurt = value + sv * (double)lvl;
        this.skillgain = value1 + sv1 * (double)lvl;
        this.skillgain1 = value2 + sv2 * (double)lvl;
        this.skillhitrate = value3 + sv3 * (double)lvl;
        if (this.skillid == 9123) {//使用5睡命中超过5个目标时有（4%*等级）几率返还消耗法力的（10%*等级）
            this.skillgain = this.skillhurt * 2.5;
        }
        else if (this.skillid == 9125) {//被昏睡的目标摆脱昏睡状态时（非物理，法术攻击解除）有（4%*等级）几率扣除最大法量的（1.5%*等级）。（仅在与玩家之间战斗有效。）
            this.skillgain = this.skillhurt * 0.375;
        }
        else if (this.skillid == 9162) {//减少毒的回合数（1*等级），获得加强毒伤害（2%*等级）。（仅在与NPC之间战斗有效）
            this.skillgain = this.skillhurt * 2.0;
        }
        else if (this.skillid == 9511) {
            this.skillhurt = sv * (double)(lvl + 1);
        }
        else if (this.skillid == 9612) {
            this.skillgain = ((lvl <= 2) ? 1.0 : ((lvl <= 4) ? 3.0 : 4.0));
        }
        else if (this.skillid == 9711) {
            this.skillhurt = value - sv * (double)lvl;
        }
        if (this.skillid == 9110 || this.skillid == 9111 || this.skillid == 9126 || this.skillid == 9130 || this.skillid == 9151 || this.skillid == 9169 || this.skillid == 9170 || this.skillid == 9171 || this.skillid == 9189 || this.skillid == 9190 || this.skillid == 9207 || this.skillid == 9208 || this.skillid == 9209 || this.skillid == 9231 || this.skillid == 9232 || this.skillid == 9250 || this.skillid == 9251 || this.skillid == 9252 || this.skillid == 9262 || this.skillid == 9270 || this.skillid == 9286 || this.skillid == 9287 || this.skillid == 9307 || this.skillid == 9350 || this.skillid == 9352 || this.skillid == 9372 || this.skillid == 9389 || this.skillid == 9412 || this.skillid == 9510 || this.skillid == 9512 || this.skillid == 9610 || this.skillid == 9611 || this.skillid == 9612 || this.skillid == 9710 || this.skillid == 9711 || this.skillid == 9811 || this.skillid == 9812 || this.skillid == 9857 || this.skillid == 9870 || this.skillid == 9881 || this.skillid == 9132 || this.skillid == 9957 || this.skillid == 10015 || this.skillid == 10016 || this.skillid == 10055 || this.skillid == 10080 || this.skillid == 10121 || this.skillid == 10129 || this.skillid == 10138 || this.skillid == 9810 || this.skillid == 10047) {
            this.skillbeidong = 0;
        }
    }
    
    public int getSkillcontinued() {
        return this.skillcontinued;
    }
    
    public void setSkillcontinued(int skillcontinued) {
        this.skillcontinued = skillcontinued;
    }
    
    public int getSkillblue() {
        return this.skillblue;
    }
    
    public void setSkillblue(int skillblue) {
        this.skillblue = skillblue;
    }
    
    public double getSkillhitrate() {
        return this.skillhitrate;
    }
    
    public void setSkillhitrate(double skillhitrate) {
        this.skillhitrate = skillhitrate;
    }
    
    public double getSkillsld() {
        return this.skillsld;
    }
    
    public void setSkillsld(double skillsld) {
        this.skillsld = skillsld;
    }
    /**
     * 为增益状态
     */
    public boolean Goodskill() {
        return (this.skillid >= 1026 && this.skillid <= 1040) || (this.skillid >= 1076 && this.skillid <= 1080) || this.skillid == 1606 || this.skillid == 1607 || this.skillid == 1608 || this.skillid == 1611 || this.skillid == 1612 || this.skillid == 1804 || this.skillid == 1805 || this.skillid == 1808 || this.skillid == 1809 || this.skillid == 1821 || this.skillid == 1823 || this.skillid == 1824 || this.skillid == 1825 || this.skillid == 1826 || this.skillid == 1827 || this.skillid == 1829 || this.skillid == 1830;
    }
    /**
     * 根据熟练度计算个数
     */
    public static int geshu(int level, int ed, String type) {
        if (type.equals("鬼火") || type.equals("火") || type.equals("水") || type.equals("雷") || type.equals("风")) {
            return xian(ed, level);
        }
        if (type.equals("震慑")) {
            return moz(ed, level);
        }
        if (type.equals("力量") || type.equals("抗性") || type.equals("加速") || type.equals("smmh") || type.equals("加狂暴")) {
            return moq(ed, level);
        }
        if (type.equals("中毒") || type.equals("封印") || type.equals("昏睡") || type.equals("遗忘")) {
            return renq(ed, level);
        }
        if (type.equals("混乱")) {
            return renh(ed, level);
        }
        if (type.equals("三尸虫")) {
            return guis(ed, level);
        }
        if (type.equals("霹雳")) {
            return lpl(ed, level);
        }
        return xian(ed, level);
    }
    /**
     * 仙法个数
     */
    public static int xian(int ed, int lvl) {
        if (lvl == 3) {
            if (ed < 720) {
                return 2;
            }
            if (ed < 5215) {
                return 3;
            }
            if (ed < 16610) {
                return 4;
            }
            return 5;
        }
        else if (lvl == 5) {
            if (ed < 558) {
                return 3;
            }
            if (ed < 5621) {
                return 4;
            }
            return 5;
        }
        else {
            return 1;
        }
    }
    /**
     * 震慑
     */
    public static int moz(int ed, int lvl) {
        if (lvl == 3) {
            if (ed < 426) {
                return 2;
            }
            if (ed < 3098) {
                return 3;
            }
            if (ed < 9866) {
                return 4;
            }
            return 5;
        }
        else if (lvl == 5) {
            if (ed < 226) {
                return 3;
            }
            if (ed < 1638) {
                return 4;
            }
            if (ed < 5215) {
                return 5;
            }
            if (ed < 11868) {
                return 6;
            }
            return 7;
        }
        else {
            return 1;
        }
    }
    /**
     * 附攻、附防、加速
     */
    public static int moq(int ed, int lvl) {
        if (lvl == 3) {
            if (ed < 214) {
                return 2;
            }
            if (ed < 2155) {
                return 3;
            }
            if (ed < 8324) {
                return 4;
            }
            return 5;
        }
        else if (lvl == 5) {
            if (ed < 117) {
                return 3;
            }
            if (ed < 1174) {
                return 4;
            }
            if (ed < 4533) {
                return 5;
            }
            if (ed < 11826) {
                return 6;
            }
            return 7;//目标数7个
        }
        else {
            return 1;
        }
    }
    /**
     * 封印、昏睡、毒系 遗忘法术
     */
    public static int renq(int ed, int lvl) {
        if (lvl == 3) {
            if (ed < 428) {
                return 2;
            }
            if (ed < 3098) {
                return 3;
            }
            if (ed < 9866) {
                return 4;
            }
            return 5;
        }
        else if (lvl == 5) {
            if (ed < 226) {
                return 3;
            }
            if (ed < 1638) {
                return 4;
            }
            if (ed < 5215) {
                return 5;
            }
            if (ed < 11864) {
                return 6;
            }
            return 7;
        }
        else {
            return 1;
        }
    }
    /**
     * 混乱法术
     */
    public static int renh(int ed, int lvl) {
        if (lvl == 3) {
            if (ed < 1362) {
                return 2;
            }
            if (ed < 9866) {
                return 3;
            }
            return 4;
        }
        else if (lvl == 5) {
            if (ed < 973) {
                return 3;
            }
            if (ed < 7051) {
                return 4;
            }
            return 5;
        }
        else {
            return 1;
        }
    }
    /**
     * 鬼魅惑
     */
    public static int guiv(int ed, int lvl) {
        if (lvl == 3) {
            if (ed < 2200) {
                return 2;
            }
            if (ed < 4600) {
                return 3;
            }
            if (ed < 9600) {
                return 4;
            }
            return 5;
        }
        else if (lvl == 5) {
            if (ed < 2200) {
                return 3;
            }
            if (ed < 4600) {
                return 4;
            }
            if (ed < 9600) {
                return 5;
            }
            if (ed < 12000) {
                return 6;
            }
            return 7;
        }
        else {
            return 1;
        }
    }
    /**
     * 鬼三尸
     */
    public static int guis(int ed, int lvl) {
        if (lvl == 3) {
            if (ed < 5200) {
                return 2;
            }
            if (ed < 6800) {
                return 3;
            }
            return 4;
        }
        else if (lvl == 5) {
            if (ed < 2200) {
                return 3;
            }
            if (ed < 6800) {
                return 4;
            }
            return 5;
        }
        else {
            return 1;
        }
    }
    /**
     * 龙霹雳
     */
    public static int lpl(int ed, int lvl) {
        if (lvl == 3 || lvl == 5) {
            if (ed < 5200) {
                return 2;
            }
            if (ed < 6800) {
                return 3;
            }
            return 4;
        }
        else {
            return 1;
        }
    }
    
    public int getSkilllvl() {
        return this.skilllvl;
    }
    
    public void setSkilllvl(int skilllvl) {
        this.skilllvl = skilllvl;
    }
    
    public int getCamp() {
        return this.camp;
    }
    
    public void setCamp(int camp) {
        this.camp = camp;
    }
    
    public int getSkillbeidong() {
        return this.skillbeidong;
    }
    
    public void setSkillbeidong(int skillbeidong) {
        this.skillbeidong = skillbeidong;
    }
    
    public double getValue1() {
        return this.value1;
    }
    
    public void setValue1(double value1) {
        this.value1 = value1;
    }
    
    public double getValue2() {
        return this.value2;
    }
    
    public void setValue2(double value2) {
        this.value2 = value2;
    }
    
    public FightingSkill clone() {
        try {
            return (FightingSkill)super.clone();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 为增益状态
     */
    public boolean isBD() { //机器人指定攻击排除技能id
        if (skillid == 1833 || skillid == 30005 ){
            return true;
        }
        return false;
    }

    public int xyz;
    private double sld=0;

    public int getXyz() {
        return xyz;
    }

    public void setXyz(int xyz) {
        this.xyz = xyz;
    }

    public void setSld(double sld) {
        this.sld = sld;
    }

    public double getS1() {
        return s1;
    }

    public void setS1(double s1) {
        this.s1 = s1;
    }

    public double getS2() {
        return s2;
    }

    public void setS2(double s2) {
        this.s2 = s2;
    }

    public double getS3() {
        return s3;
    }

    public void setS3(double s3) {
        this.s3 = s3;
    }

    public double getS4() {
        return s4;
    }

    public void setS4(double s4) {
        this.s4 = s4;
    }

    public double getS5() {
        return s5;
    }

    public void setS5(double s5) {
        this.s5 = s5;
    }

    public double getS6() {
        return s6;
    }

    public void setS6(double s6) {
        this.s6 = s6;
    }

    public double getS7() {
        return s7;
    }

    public void setS7(double s7) {
        this.s7 = s7;
    }

    public double getP1() {
        return p1;
    }

    public void setP1(double p1) {
        this.p1 = p1;
    }

    public double getP2() {
        return p2;
    }

    public void setP2(double p2) {
        this.p2 = p2;
    }

    public double getP3() {
        return p3;
    }

    public void setP3(double p3) {
        this.p3 = p3;
    }

    public double getP4() {
        return p4;
    }

    public void setP4(double p4) {
        this.p4 = p4;
    }

    public double getP5() {
        return p5;
    }

    public void setP5(double p5) {
        this.p5 = p5;
    }

    public double getP6() {
        return p6;
    }

    public void setP6(double p6) {
        this.p6 = p6;
    }

    public double getP7() {
        return p7;
    }

    public void setP7(double p7) {
        this.p7 = p7;
    }

    public double getE1() {
        return e1;
    }

    public void setE1(double e1) {
        this.e1 = e1;
    }

    public double getE2() {
        return e2;
    }

    public void setE2(double e2) {
        this.e2 = e2;
    }

    public double getE3() {
        return e3;
    }

    public void setE3(double e3) {
        this.e3 = e3;
    }

    public double getE4() {
        return e4;
    }

    public void setE4(double e4) {
        this.e4 = e4;
    }

    public double getE5() {
        return e5;
    }

    public void setE5(double e5) {
        this.e5 = e5;
    }

    public double getE6() {
        return e6;
    }

    public void setE6(double e6) {
        this.e6 = e6;
    }

    public double getE7() {
        return e7;
    }

    public void setE7(double e7) {
        this.e7 = e7;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getBorn() {
        return born;
    }

    public void setBorn(int born) {
        this.born = born;
    }

    public long getQm() {
        return qm;
    }

    public void setQm(long qm) {
        this.qm = qm;
    }
    private List<ManData> fightingManData;
    public List<ManData> getFightingManData() {
        if (this.fightingManData == null) {
            this.fightingManData = new ArrayList<>();
        }
        return this.fightingManData;
    }
    public void setFightingManData(List<ManData> fightingManData) {
        this.fightingManData = fightingManData;
    }
}
