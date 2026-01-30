package come.tool.FightingData;

import javax.swing.DefaultListModel;
import java.util.Optional;

import come.tool.Role.RoleData;
import come.tool.Role.Hang;
import come.tool.Role.RolePool;
import come.tool.Battle.BattleData;
import com.gl.util.FaMenUtil;
import come.tool.FightingDataAction.Fabao;
import org.come.bean.PathPoint;
import org.come.tool.CustomFunction;
import come.tool.Mixdeal.PetGrade;
import org.come.model.Monster;
import org.come.until.GsonUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import come.tool.Calculation.CalculationPal;
import org.come.entity.Pal;
import org.come.model.PalData;
import come.tool.Calculation.BaseEquip;
import org.come.tool.EquipTool;
import come.tool.Calculation.BaseValue;
import come.tool.Calculation.BaseQl;
import org.come.entity.Lingbao;
import org.come.model.Talent;
import org.come.entity.Baby;
import org.come.entity.Mount;
import come.tool.Calculation.CalculationUtil;
import org.come.tool.TalentTool;
import com.gl.util.LingXiUtil;
import come.tool.Mixdeal.AnalysisString;
import org.come.entity.Goodstable;

import java.util.Iterator;

import come.tool.Mixdeal.CreepsMixdeal;
import come.tool.Battle.BattleMixDeal;
import org.come.bean.LoginResult;
import org.come.model.Skill;
import org.apache.commons.lang.StringUtils;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import io.netty.channel.ChannelHandlerContext;
import org.come.until.AllServiceUtil;
import come.tool.Calculation.CalculationPet;
import org.come.server.GameServer;

import java.util.stream.Collectors;
import java.util.Arrays;

import org.come.entity.RoleSummoning;

import java.util.HashMap;
import java.util.ArrayList;

import come.tool.Calculation.BaseStar;

import java.util.Map;
import java.math.BigDecimal;
import java.util.List;
//TODO 战斗数据（人、宠、孩子、灵宝）

/**
 * 战斗中人物数据
 *
 * @author Administrator
 */
public class ManData implements Cloneable {
    public static String[] values = new String[]{"遗忘", "封印", "中毒", "昏睡", "混乱"};
    public int ddhe = 9999;
    public int ddhe_ = 9999;
    public int xxywl = 0;
    public int xxyfs = 0;
    public int huodeMp;
    public int chushiMP;
    public boolean isjv = false;
    public long fzsh = 0L;
    public double LLGL = 0.0D;
    // 种族名称
    private String race_name;
    private Boolean isDZ = false;
    public boolean isPalPet = false;
    public boolean isAi;
    private boolean QHFY;
    private int lingbaoshanxian;
    private int lingbaotianfujineng;
    public List<String> yinian;
    private int xingtai;
    private boolean QKZT;
    private boolean bianshen;
    private boolean TPZS;
    private int type;// 类型 0玩家 1召唤兽 2野怪 3灵宝 4小孩
    private int id;
    private int camp;// 战斗1阵营
    private int man;// 战斗位置 1玩家 6召唤兽 11灵宝 16小孩
    private String manname;// 名称
    private int hp;// 血量
    private int mp;
    private int hp_z;
    private int mp_z;// 总蓝量
    private Ql Quality;// 人物的所有抗性
    private int ap;// ap 普攻伤害
    private int sp;// sp 敏捷度
    private int sp2;// sp 敏捷度
    private int States;// 当前状态 0表示活着 1表示死亡 2表示已经逃跑
    private List<AddState> addStates;// 附加状态集合
    private List<FightingSkill> skills;// 已有技能集合
    private List<FightingSkill> skillsCopy;// 已有技能集合
    private List<FightingSummon> pets;// 玩家已有的召唤兽集合
    private int departure;//记录离场召唤兽
    private int consumedNq;// 初始化已消耗的怒气值为0
    private ManData Child;// 孩子
    private String model;// 人物模型
    private int spellSum;// 法术攻击的次数
    private int yqz;// 怨气值 初始149 上限500
    public int nqz;
    public int nqzad;// 怒气值衰减
    private List<FightingLingbao> lings;// 玩家已有的灵宝集合
    // 灵宝属性字段 -人物 4项属性
    private long qihe;// 契合度
    private double huoyue;// 活跃 根骨
    private double shanghai;// 伤害或者回复或者落宝 灵性
    private double kangluobao;// 抗落宝 力量
    private double yuanzhu;// 支援 敏捷
    private int lvl;// 等级
    private int zs;// 转生字段
    private String petType;// 召唤兽类型
    private List<AI> ais;// AI
    private int sr;// 技能释放的概率
    private String msg;// 喊话
    private long money;// 被偷取的金钱
    private long money2;// 被偷取的金钱2
    private List<String> bearSkills; // 承受的法术类型
    private List<String> TFSkills;
    private BigDecimal se;
    private BigDecimal xk_id;//星卡id
    private Map<String, AddAttack> attacks;// 追加攻击
    /**
     * 是否已经执行过技能
     */
    public boolean methodExecuted;
    private BaseStar baseStar;
    private Double expXS;
    private Double exp2XS;
    private int HF;
    private int zy;
    private int lzy;
    private String sfPet;
    private final String[] HZT;//"赤芒", "金石", "青峰"
    private int talentLvl; // 天赋等级
    private Integer fmsld;// 法门主动技能熟练度
    private Integer fmsld2;// 法门被动技能熟练度
    private int famencs; // 法门层数
    private int huiHeFamencs;// 回合内添加的法门层数
    private int attackDie;// 回合内打死几个
    private boolean isPhyAttack; // 法门物理攻击标记
    private boolean isSpellAttack; // 法门法术攻击标记
    private boolean isFmPhyAttack; // 法门物理技能释放标记
    private boolean isFmAttack;// 法门被攻击标记
    private int ewhuoyue;// 活跃 根骨
    private int ewshanghai;// 伤害或者回复或者落宝 灵性
    private int ewkangluobao;// 抗落宝 力量
    private int ewyuanzhu;// 支援 敏捷
    private int dffmsld; // 主动法门的存储熟练
    private int fmFHHT; //法门 法魂护体存储伤害吸收
    private int nrlrjs;//法门 利刃加身存储回合伤害
    private int nrjycz; //法门 积羽沉舟存储累加伤害
    private int nrfhht;//法门 法魂护体存储伤害吸收
    private int fmshfs;//法门 兽魂俯首
    // 用完即消的属性
    public double zm;// 附加致命
    public double kb;
    public double mz;
    public double pwljl;// 附加破物理几率
    public double pwlcd;// 附加破物理程度
    public double hsfz; // 附加忽视反震概率
    public int ljv; // 附加连击率
    public double qf;// 强仙法鬼火
    public double hs; // 忽视仙法鬼火
    public double fsmz;  // 法术命中
    public double fskbcd;// 法术狂暴程度
    public double fskbjl; // 法术狂暴几率
    public double hsjv; // 忽视法术抗性几率
    public double bhws; // 附加冰混忘睡抗性
    public int lsf;
    public int lsd;
    public int lsh;
    public int lss;
    public int ltf;
    public int ltd;
    public int lth;
    public int lts;
    public double tl;
    public double tf;
    public double ts;
    public double th;
    public double nqyy;
    public int lxzt;
    public int jlss;
    public int jlmh;
    public int jlgh;
    public int jlyw;
    public int tempJlss;
    public int tempJlmh;
    public int tempJlgh;
    public int tempJlyw;
    public double yyhomax;//记录每复活一个单位增加血量上限
    public double fyhomax;//记录每复活一个单位增加血量上限
    public double tzcf; //孩子天资触发几率
    // 惊涛拍岸标记
    public boolean jtpa;// 惊涛拍岸标记
    private int cd; // 灵犀通用冷却
    private String lingxi;// 存放灵犀字符串
    public boolean jtcl;// 卷土重来触发标记
    public boolean fhft; // 兽魂俯首触发标记
    public boolean ymjr;// 一鸣惊人触发标记
    public boolean fbgs;// 奋不顾身标记允许攻击加成
    public String param;// 属性以及保留的回合
    public boolean fskbbj;// 本次攻击是否造成了法术狂暴
    public boolean yijibiming; //一击毙命判断
    private static String petSkillswl; // 悟灵设定
    private Integer fzSum;
    public int zFMsld;
    public int bFMsld;
    private String skin;
    private boolean isHS;
    private BigDecimal speciesid; //种族ID，如果是召唤兽则是召唤兽ID
    private FightingEvents fightingEvents;
    private ManData baohu;
    private int skill_count_z; // 记录技能次数_震
    private int skill_count_s; // 记录技能次数_速
    private int skill_count_g;// 记录技能次数_功
    private int skill_count_p;// 记录技能次数_盘
    public int beatenNum;
    private Map<String, Double> skill_ss_tmp_mp;// 记录下回合法力消耗减少的法术-记录当前回合
    private Map<String, Double> skill_ss_mp;// 记录下回合法力消耗减少的法术-记录下回合
    public ManData noMandata;
    public static String[] values1;
    public static String[] values2;
    public static String[] values3;
    public static String[] values4;
    public String XZ;
    private Original original;
    public static Map<String, List<String>> shuxingmap;

    public BigDecimal getSpeciesid() {
        return this.speciesid;
    }

    public void setSpeciesid(BigDecimal speciesid) {
        this.speciesid = speciesid;
    }

    public ManData(int camp, int man) {
        this.QHFY = false;
        this.lingbaoshanxian = 0;
        this.lingbaotianfujineng = 0;
        this.yinian = new ArrayList<>();
        this.xingtai = 0;
        this.QKZT = false;
        this.bianshen = false;
        this.consumedNq = 0;
        this.spellSum = 0;
        this.yqz = 149;
        this.nqz = 30;
        this.nqzad = 0;
        this.methodExecuted = false;
        this.HF = 0;
        this.zy = 0;
        this.lzy = 0;
        this.sfPet = "0";
        this.HZT = new String[]{"赤芒", "金石", "青峰"};
        this.fmsld = Integer.valueOf(0);
        this.fmsld2 = Integer.valueOf(0);
        this.famencs = 0;
        this.huiHeFamencs = 0;
        this.attackDie = 0;
        this.ewhuoyue = 0;
        this.ewshanghai = 0;
        this.ewkangluobao = 0;
        this.ewyuanzhu = 0;
        this.dffmsld = 0;
        this.fmFHHT = 0;
        this.nrlrjs = 0;
        this.nrjycz = 0;
        this.nrfhht = 0;
        this.fmshfs = 0;
        this.zm = 0.0;
        this.kb = 0.0;
        this.mz = 0.0;
        this.pwljl = 0.0;
        this.pwlcd = 0.0;
        this.hsfz = 0.0;
        this.ljv = 0;
        this.qf = 0.0;
        this.hs = 0.0;
        this.fsmz = 0.0;
        this.fskbcd = 0.0;
        this.fskbjl = 0.0;
        this.hsjv = 0.0;
        this.bhws = 0.0;
        this.lsf = 0;
        this.lsd = 0;
        this.lsh = 0;
        this.lss = 0;
        this.ltf = 0;
        this.ltd = 0;
        this.lth = 0;
        this.lts = 0;
        this.tl = 0.0;
        this.tf = 0.0;
        this.ts = 0.0;
        this.th = 0.0;
        this.nqyy = 0.0;
        this.lxzt = 0;
        this.jlss = 0;
        this.jlmh = 0;
        this.jlgh = 0;
        this.jlyw = 0;
        this.tempJlss = 0;
        this.tempJlmh = 0;
        this.tempJlgh = 0;
        this.tempJlyw = 0;
        this.yyhomax = 0.0;
        this.fyhomax = 0.0;
        this.tzcf = 0.0;
        this.jtpa = false;
        this.cd = 0;
        this.jtcl = false;
        this.fhft = false;
        this.ymjr = false;
        this.fbgs = true;
        this.param = "";
        this.fskbbj = false;
        this.yijibiming = true;
        this.fzSum = 0;
        this.isHS = false;
        this.skill_count_z = 0;
        this.skill_count_s = 0;
        this.skill_count_g = 0;
        this.skill_count_p = 0;
        this.beatenNum = 0;
        this.skill_ss_tmp_mp = new HashMap<>();
        this.skill_ss_mp = new HashMap<>();
        this.yqz = 149;
        this.nqz = 30;
        this.expXS = 1.0;
        this.exp2XS = 0.0;
        this.camp = camp;
        this.man = man;
        this.type = 0;
        this.skills = new ArrayList<>();
        this.bearSkills = new ArrayList<>();
        this.addStates = new ArrayList<>();
    }

    /**
     * 召唤兽技能加载
     */
    public void addPetSkill(String petSkills, long qm, FightingSummon summon, String petSkillswl, RoleSummoning pet) {
        if (petSkills == null || petSkills.equals("")) {
            return;
        }
        String[] v = petSkills.split("\\|");

        int j1 = Integer.parseInt(pet.getGold());
        int m = Integer.parseInt(pet.getWood());
        int s = Integer.parseInt(pet.getWater());
        int h = Integer.parseInt(pet.getFire());
        int t = Integer.parseInt(pet.getSoil());
        int xz = 0;
        for (int i = 0; i < v.length; i++) {
            if (v[i].equals("1815")) {
                xz = 1;
                continue;
            } else if (v[i].equals("1816")) {
                xz = 2;
                continue;
            } else if (v[i].equals("1817")) {
                xz = 3;
                continue;
            } else if (v[i].equals("1818")) {
                xz = 4;
                continue;
            } else if (v[i].equals("1819")) {
                xz = 5;
                continue;
            }
        }

        if (xz != 0) {
            if (xz == 1) {
                if (j1 < 50) {
                    j1 = j1 + 50;
                } else {
                    j1 = 100;
                }
                m = m / 2;
                s = s / 2;
                h = h / 2;
                t = t / 2;
            } else if (xz == 2) {
                if (m < 50) {
                    m = m + 50;
                } else {
                    m = 100;
                }
                j1 = j1 / 2;
                s = s / 2;
                h = h / 2;
                t = t / 2;
            } else if (xz == 3) {
                if (s < 50) {
                    s = s + 50;
                } else {
                    s = 100;
                }
                j1 = j1 / 2;
                m = m / 2;
                h = h / 2;
                t = t / 2;
            } else if (xz == 4) {
                if (h < 50) {
                    h = h + 50;
                } else {
                    h = 100;
                }
                j1 = j1 / 2;
                s = s / 2;
                m = m / 2;
                t = t / 2;
            } else if (xz == 5) {
                if (t < 50) {
                    t = t + 50;
                } else {
                    t = 100;
                }
                j1 = j1 / 2;
                s = s / 2;
                h = h / 2;
                m = m / 2;
            }

        }


        List<String> list = (List) Arrays.stream(v).collect(Collectors.toList());
        for (int i = 0; i < v.length; ++i) {
            Skill skill = GameServer.getSkill(v[i]);
            if (skill != null) {
                skill.setPetSkillswl(petSkillswl);
                CalculationPet.addQlSkill(this.Quality, skill.getSkillid(), qm);
                int skilltype = this.skilltype(skill.getSkillid());
                if (skilltype != 1) {
                    if (skilltype == 1207) {
                        for (int j = 0; j < this.skills.size(); ++j) {
                            if (((FightingSkill) this.skills.get(j)).getSkillid() == 1831 || ((FightingSkill) this.skills.get(j)).getSkillid() == 1833) {
                                ((FightingSkill) this.skills.get(j)).setSkillhurt(((FightingSkill) this.skills.get(j)).getSkillhurt() + 15.0);
                            }
                        }
                    } else {
                        // 灵犀-法术-大开杀戒判定熟练度提升
                        int sld = (int) ((double) this.executeDksj(1) * 3.5);
                        FightingSkill fightingSkill = new FightingSkill(skill, this.lvl, this.zs, (double) (350 + sld), qm, 0);
                        int skillid = fightingSkill.getSkillid();
                        if (skillid == 1806 || (skillid >= 1820 && skillid <= 1827) || (skillid == 1839)) {

                            //刚开始生效是因为没有这个新加的判断， 实际上学习沧海诀没有增加水属性
                            if (skillid == 1827 && (s >= 50 || this.Quality.getRolewxs() >= 50.0)) {
                                summon.setHelpV(fightingSkill.getSkillhitrate());
                            }//鬼神莫测  隐身
                            if (skillid == 1826 && (h >= 50 || this.Quality.getRolewxh() >= 50.0)) {
                                summon.setHelpV(fightingSkill.getSkillhitrate());
                            }//大隐于朝  隐身
                            if (skillid == 1825 && (m >= 50 || this.Quality.getRolewxm() >= 50.0)) {
                                summon.setHelpV(fightingSkill.getSkillhitrate());
                            }//义之金叶神  击其不意
                            if (skillid == 1820 && (j1 >= 50 || this.Quality.getRolewxj() >= 50.0)) {
                                summon.setHelpV(fightingSkill.getSkillhitrate());
                            }//仁之木叶神  自医
                            if (skillid == 1821 && (m >= 50 || this.Quality.getRolewxm() >= 50.0)) {
                                summon.setHelpV(fightingSkill.getSkillhitrate());
                            }//信之土叶神  击其不意
                            if (skillid == 1822 && (t >= 50 || this.Quality.getRolewxt() >= 50.0)) {
                                summon.setHelpV(fightingSkill.getSkillhitrate());
                            } //智之水叶神  自医
                            if (skillid == 1823 && (s >= 50 || this.Quality.getRolewxs() >= 50.0)) {
                                summon.setHelpV(fightingSkill.getSkillhitrate());
                            }//礼之火叶神  遗产
                            if (skillid == 1824 && (h >= 50 || this.Quality.getRolewxh() >= 50.0)) {
                                summon.setHelpV(fightingSkill.getSkillhitrate());
                            }//舍生取义  限制行动
                            if (skillid == 1839 && (t >= 50 || this.Quality.getRolewxt() >= 50.0) || (int) pet.getBone() >= 450) {
                                summon.setHelpV(fightingSkill.getSkillhitrate());
                            }
                        }

                        if ((skillid >= 1831 && skillid <= 1832)) {
                            //分花拂柳  追击
                            if (skillid == 1831 && (int) pet.getPower() < 450) {
                                String skilldata = null;
                                fightingSkill.setSkilltype("");
                                LoginResult loginResult = AllServiceUtil.getRoleTableService().selectRoleID(pet.getRoleid());
                                ChannelHandlerContext ctx = (ChannelHandlerContext) GameServer.getRoleNameMap().get(loginResult.getRolename());
                                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("力量不足450，分花拂柳无法生效"));
                            } else {
                                summon.setHelpV(fightingSkill.getSkillhitrate());
                            }//分裂攻击
                            if (skillid == 1832 && (int) pet.getPower() < 450) {
                                String skilldata = null;
                                fightingSkill.setSkilltype("");
                                LoginResult loginResult = AllServiceUtil.getRoleTableService().selectRoleID(pet.getRoleid());
                                ChannelHandlerContext ctx = (ChannelHandlerContext) GameServer.getRoleNameMap().get(loginResult.getRolename());
                                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("力量不足450，分裂攻击无法生效"));
                            } else {
                                summon.setHelpV(fightingSkill.getSkillhitrate());
                            }//高级分裂攻击
                            if (skillid == 1833 && pet.getPower() < 450) {
                                String skilldata = null;
                                fightingSkill.setSkilltype("");
                                LoginResult loginResult = AllServiceUtil.getRoleTableService().selectRoleID(pet.getRoleid());
                                ChannelHandlerContext ctx = (ChannelHandlerContext) GameServer.getRoleNameMap().get(loginResult.getRolename());
                                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("力量不足450，高级分裂攻击无法生效"));
                            } else {
                                summon.setHelpV(fightingSkill.getSkillhitrate());
                            }
                        }

                        if (skillid == 1293 || skillid == 1296) {
                            if (pet.getXy() != null) {
                                String[] mes = pet.getXy().split("#");
                                for (int k = 1; k <= mes.length - 2; ++k) {
                                    if (!mes[k].equals("")) {
                                        switch (Integer.parseInt(mes[k])) {
                                            case 0: {
                                                this.yinian.add("腾云");
                                                break;
                                            }
                                            case 1: {
                                                this.yinian.add("破云");
                                                break;
                                            }
                                            case 2: {
                                                this.yinian.add("踏云");
                                                break;
                                            }
                                            case 3: {
                                                this.yinian.add("圣佑");
                                                break;
                                            }
                                            case 4: {
                                                this.yinian.add("情心");
                                                break;
                                            }
                                            case 5: {
                                                this.yinian.add("神通");
                                                break;
                                            }
                                            case 6: {
                                                this.yinian.add("义心");
                                                break;
                                            }
                                            case 7: {
                                                this.yinian.add("悟影");
                                                break;
                                            }
                                            case 8: {
                                                this.yinian.add("追云");
                                                break;
                                            }
                                            case 9: {
                                                this.yinian.add("潜云");
                                                break;
                                            }
                                            case 10: {
                                                this.yinian.add("纵云");
                                                break;
                                            }
                                            case 11: {
                                                this.yinian.add("魔威");
                                                break;
                                            }
                                            case 12: {
                                                this.yinian.add("逍心");
                                                break;
                                            }
                                            case 13: {
                                                this.yinian.add("灵通");
                                                break;
                                            }
                                            case 14: {
                                                this.yinian.add("遥心");
                                                break;
                                            }
                                            case 15: {
                                                this.yinian.add("六识");
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            if (pet.getSummoningskin().equals("500195")) {
                                this.yinian.add("腾云");
                                this.yinian.add("六识");
                                this.yinian.add("遥心");
                                this.yinian.add("灵通");
                                this.yinian.add("逍心");
                                this.yinian.add("魔威");
                                this.yinian.add("纵云");
                                this.yinian.add("潜云");
                                this.yinian.add("追云");
                                this.yinian.add("悟影");
                                this.yinian.add("义心");
                                this.yinian.add("神通");
                                this.yinian.add("情心");
                                this.yinian.add("圣佑");
                                this.yinian.add("破云");
                                this.yinian.add("踏云");
                            }
                        }
                        if (fightingSkill.getSkillid() >= 1041 && fightingSkill.getSkillid() <= 1065) {
                            fightingSkill.setSkillhurt(fightingSkill.getSkillhurt() * 2.0);
                        }
                        String s1 = delNoConditionSkill(skill, pet, list, false);
                        if (!StringUtils.isNotBlank(s1)) {
                            this.addSkill(fightingSkill);
                        }
                    }
                }
            }
        }
    }

    public ManData(int camp, int man, RoleSummoning pet, String petAi) {
        this.QHFY = false;
        this.lingbaoshanxian = 0;
        this.lingbaotianfujineng = 0;
        this.yinian = new ArrayList<>();
        this.xingtai = 0;
        this.QKZT = false;
        this.bianshen = false;
        this.consumedNq = 0;
        this.spellSum = 0;
        this.yqz = 149;
        this.nqz = 30;
        this.nqzad = 0;
        this.methodExecuted = false;
        this.HF = 0;
        this.zy = 0;
        this.lzy = 0;
        this.sfPet = "0";
        this.HZT = new String[]{"赤芒", "金石", "青峰"};
        this.fmsld = 0;
        this.fmsld2 = 0;
        this.famencs = 0;
        this.huiHeFamencs = 0;
        this.attackDie = 0;
        this.ewhuoyue = 0;
        this.ewshanghai = 0;
        this.ewkangluobao = 0;
        this.ewyuanzhu = 0;
        this.dffmsld = 0;
        this.fmFHHT = 0;
        this.nrlrjs = 0;
        this.nrjycz = 0;
        this.nrfhht = 0;
        this.fmshfs = 0;
        this.zm = 0.0;
        this.kb = 0.0;
        this.mz = 0.0;
        this.pwljl = 0.0;
        this.pwlcd = 0.0;
        this.hsfz = 0.0;
        this.ljv = 0;
        this.qf = 0.0;
        this.hs = 0.0;
        this.fsmz = 0.0;
        this.fskbcd = 0.0;
        this.fskbjl = 0.0;
        this.hsjv = 0.0;
        this.bhws = 0.0;
        this.lsf = 0;
        this.lsd = 0;
        this.lsh = 0;
        this.lss = 0;
        this.ltf = 0;
        this.ltd = 0;
        this.lth = 0;
        this.lts = 0;
        this.tl = 0.0;
        this.tf = 0.0;
        this.ts = 0.0;
        this.th = 0.0;
        this.nqyy = 0.0;
        this.lxzt = 0;
        this.jlss = 0;
        this.jlmh = 0;
        this.jlgh = 0;
        this.jlyw = 0;
        this.tempJlss = 0;
        this.tempJlmh = 0;
        this.tempJlgh = 0;
        this.tempJlyw = 0;
        this.yyhomax = 0.0;
        this.fyhomax = 0.0;
        this.tzcf = 0.0;
        this.jtpa = false;
        this.cd = 0;
        this.jtcl = false;
        this.fhft = false;
        this.ymjr = false;
        this.fbgs = true;
        this.param = "";
        this.fskbbj = false;
        this.yijibiming = true;
        this.fzSum = 0;
        this.isHS = false;
        this.skill_count_z = 0;
        this.skill_count_s = 0;
        this.skill_count_g = 0;
        this.skill_count_p = 0;
        this.beatenNum = 0;
        this.skill_ss_tmp_mp = new HashMap<>();
        this.skill_ss_mp = new HashMap<>();
        this.camp = camp;
        this.man = man;
        this.type = 2;
        this.skills = new ArrayList<>();
        this.addStates = new ArrayList<>();
        this.manname = pet.getSummoningname();
        this.model = pet.getSummoningskin();
        this.skin = this.model;
        this.lvl = BattleMixDeal.petLvlint((int) pet.getGrade());
        this.zs = BattleMixDeal.petTurnRount((int) pet.getGrade());
        this.manname = pet.getSummoningname();
        this.skin = pet.getSummoningskin();
        this.Quality = new Ql();
        this.huoyue = (double) (int) pet.getBone();
        this.shanghai = (double) (int) pet.getSpir();
        this.kangluobao = (double) (int) pet.getPower();
        this.yuanzhu = (double) (int) pet.getSpeed();
        this.mp_z = pet.getBasismp();
        this.hp_z = pet.getBasishp();
        if (this.mp_z == 0) {
            this.mp_z = pet.getMp();
        }
        if (this.hp_z == 0) {
            this.hp_z = pet.getHp();
        }
        int[] hmas = getPetHMASp(pet);
        this.hp = hmas[0];
        this.mp = hmas[1];
        this.ap = hmas[2];
        this.sp = hmas[3];
        FightingSummon summon = new FightingSummon();
        long qms = 20000000L;
        this.addPetSkill(pet.getPetSkills(), qms, summon, "", pet);
        this.addPetSkill(pet.getPetQlSkills(), qms, summon, "", pet);
        this.addPetSkill(pet.getBeastSkills(), qms, summon, "", pet);
        this.addPetSkill(pet.getSkill(), qms, summon, "", pet);
        boolean isTX = false;
        this.lingxi = pet.getLingxi();
        this.addPetLX(pet.getLingxi(), qms);
        if (pet.getSkill() != null) {
            String[] v = pet.getSkill().split("\\|");
            double sld = (double) (this.lvl * 125);
            if (this.id >= 2000 && this.id <= 2105) {
                sld = 1.0;
            }
            for (int i = 0; i < v.length; ++i) {
                try {
                    if (!v[i].equals("")) {
                        String newid = v[i];
                        int id = Integer.parseInt(v[i]);
                        if (id == 100) {
                            newid = "1030";
                        } else if (id == 101) {
                            newid = "1035";
                        } else if (id == 102) {
                            newid = "1205";
                        } else if (id == 103) {
                            newid = "1203";
                        } else if (id == 104) {
                            newid = "1056";
                        }
                        int skilltype = this.skilltype(Integer.parseInt(newid));
                        if (skilltype != 1) {
                            if (skilltype == 3) {
                                CreepsMixdeal.addNeiDanSkill(this, id);
                            } else {
                                if (skilltype == 4) {
                                    isTX = true;
                                } else if (skilltype == 1207 || skilltype == 24006 || skilltype == 24030) {
                                    for (int j = 0; j < this.skills.size(); ++j) {
                                        FightingSkill skill2 = (FightingSkill) this.skills.get(j);
                                        if (skilltype == 1207 && (skill2.getSkillid() == 1831 || skill2.getSkillid() == 1833)) {
                                            skill2.setSkillhurt(skill2.getSkillhurt() + 15.0);
                                        }
                                        if (skilltype == 24006 && (skill2.getSkillid() == 1831 || skill2.getSkillid() == 1833)) {
                                            skill2.setSkillhurt(skill2.getSkillhurt() + 5.0);
                                        }
                                        if (skilltype == 24030 && (skill2.getSkillid() == 1831 || skill2.getSkillid() == 1833)) {
                                            skill2.setSkillhurt(skill2.getSkillhurt() + 10.0);
                                        }
                                    }
                                    continue;
                                }
                                Skill skill3 = GameServer.getSkill(newid);
                                FightingSkill fightingSkill = new FightingSkill(skill3, this.lvl, 3, sld, 20000000L, 20);
                                if (this.skills != null) {
                                    for (FightingSkill sk : this.skills) {
                                        if (sk.getSkillid() == skill3.getSkillid()) {
                                            fightingSkill = sk;
                                        }
                                    }
                                }
                                if (skill3 != null) {
                                    if (id >= 100 && id <= 104) {
                                        fightingSkill.setSkillsum(10);
                                        fightingSkill.setSkillcontinued(100);
                                        if (id == 104) {
                                            fightingSkill.setSkillhurt(fightingSkill.getSkillhurt() + 300000.0);
                                        }
                                    }
                                    fightingSkill.setSkillblue(0);
                                    this.addSkill(fightingSkill);
                                }
                            }
                        }
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(this.model);
        buffer.append("_1_1");
        if (isTX) {
            buffer.append("&jx_6_-1");
        }
        this.model = buffer.toString();
        Map<String, Object> map = new HashMap<>();
        if (pet.getInnerGoods() != null && !pet.getInnerGoods().equals("")) {
            String[] vv = pet.getInnerGoods().split("\\|");
            for (int i = 0; i < vv.length; ++i) {
                Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(vv[i]));
                if (good != null) {
                    String goodname = good.getGoodsname();
                    boolean type = goodname.equals("红颜白发") || goodname.equals("梅花三弄") || goodname.equals("开天辟地") || goodname.equals("万佛朝宗") || goodname.equals("暗渡陈仓") || goodname.equals("借力打力") || goodname.equals("凌波微步");
                    String[] strings = good.getValue().split("\\|");
                    String[] stringLevel = strings[2].split("\\=");
                    String[] stringLevel2 = stringLevel[1].split("\\转");
                    int nddj = 170;
                    if (stringLevel2.length > 1) {
                        nddj = Integer.parseInt(stringLevel2[1]);
                    }
                    if (map != null) {
                    }
                    int ndzscs = Integer.parseInt(stringLevel2[0]);
                    if (goodname.equals("红颜白发") || goodname.equals("梅花三弄") || goodname.equals("开天辟地") || goodname.equals("万佛朝宗")) {
                        this.neidang("tj", ndzscs);
                    } else if (goodname.equals("分光化影") || goodname.equals("天魔解体") || goodname.equals("小楼夜哭") || goodname.equals("青面獠牙")) {
                        this.neidang("mj", ndzscs);
                    } else if (goodname.equals("乘风破浪") || goodname.equals("霹雳流星") || goodname.equals("大海无量") || goodname.equals("祝融取火")) {
                        this.neidang("xl", ndzscs);
                    } else {
                        this.neidang("rj", ndzscs);
                    }
                    if (type) {
                        Ql ql = new Ql();
                        CalculationPet.addNedanMsg(pet, ql, nddj, ndzscs, goodname);
                        if (goodname.equals("暗渡陈仓")) {
                            int zhsdj = BattleMixDeal.petLvlint((int) pet.getGrade());
                            int zhszscs = pet.getTurnRount();
                            long zhsqm = (long) pet.getFriendliness();
                            double ndjl = Math.floor((Math.pow((double) (zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double) ndzscs) + Math.pow((double) zhsqm, 0.16666666666666666) * CalculationPet.xstz(zhszscs, ndzscs) * (double) nddj / 50.0) * 1000.0) * 5.0E-6;
                            FightingSkill skill4 = new FightingSkill();
                            skill4.setCamp(-1);
                            skill4.setSkillbeidong(1);
                            skill4.setSkillname(goodname);
                            skill4.setSkilltype(goodname);
                            skill4.setSkillhurt((double) Math.round(ndjl * 10000.0) / 100.0);
                            skill4.setSkillid(0);
                            this.addSkill(skill4);
                        }
                    }
                }
            }
        }
    }

    public static int[] getPetHMASp(RoleSummoning pet) {
        int[] pets = new int[5];
        if (pet == null) {
            return pets;
        }
        int lvl = AnalysisString.petLvlint((int) pet.getGrade());
        double grow = (double) Double.valueOf(pet.getGrowlevel());
        pets[0] = pet.getHp();
        pets[1] = pet.getMp();
        pets[2] = pet.getAp();
        pets[3] = pet.getSp();
        pets[4] = 0;
        int zBone = (int) pet.getBone();
        int zSpir = (int) pet.getSpir();
        int zPower = (int) pet.getPower();
        int zSpeed = (int) pet.getSpeed();
        int zCalm = (int) pet.getCalm();
        int addhp = 0;
        int addmp = 0;
        int addap = 0;
        pets[0] = getRoleValue(lvl, zBone, grow, pets[0], 0) + addhp;
        pets[1] = getRoleValue(lvl, zSpir, grow, pets[1], 1) + addmp;
        pets[2] = getRoleValue(lvl, zPower, grow, pets[2], 2) + addap;
        pets[3] = getRoleValue(lvl, zSpeed, grow, pets[3], 3);
        pets[4] = getRoleValue(lvl, zCalm, grow, pets[4], 4);
        pet.getLX(pets);
        if (pet.getPetSkills() != null && pet.getPetSkills().contains("1248")) {
            int n = 2;
            pets[n] = (int) ((double) pets[n] * 1.3);
        }
        return pets;
    }

    public static int getRoleValue(int lvl, int P, double G, int base, int type) {
        if (type == 0 || type == 1) {
            return (int) ((double) (lvl * P) * G) + (int) ((0.7 * (double) lvl * G + 1.0) * (double) base);
        }
        if (type == 2) {
            return (int) ((double) (lvl * P) * G / 5.0) + (int) ((0.14 * (double) lvl * G + 1.0) * (double) base);
        }
        if (type == 3) {
            return (int) ((double) (base + P) * G);
        }
        return P;
    }

    public static String delNoConditionSkill(Skill skill, RoleSummoning pet, List<String> lists, boolean l) {
        String skillID = skill.getSkillid() + "";
        int id = skill.getSkillid();
        if (id == 1820) {
            if (Integer.parseInt(pet.getGold()) < 50 && !lists.contains("1815")) {
                return "你的召唤兽金属性不足50";
            }
        } else if (id == 1821) {
            if (Integer.parseInt(pet.getWood()) < 50 && !lists.contains("1816")) {
                return "你的召唤兽木属性不足50";
            }
        } else if (id == 1822) {
            if (Integer.parseInt(pet.getSoil()) < 50 && !lists.contains("1819")) {
                return "你的召唤兽土属性不足50";
            }
        } else if (id == 1823) {
            if (Integer.parseInt(pet.getWater()) < 50 && !lists.contains("1817")) {
                return "你的召唤兽水属性不足50";
            }
        } else if (id == 1824) {
            if (Integer.parseInt(pet.getFire()) < 50 && !lists.contains("1818")) {
                return "你的召唤兽火属性不足50";
            }
        } else if (id == 1825) {
            if (Integer.parseInt(pet.getWood()) < 50 && !lists.contains("1816")) {
                return "你的召唤兽木属性不足50";
            }
        } else if (id == 1826) {
            if (Integer.parseInt(pet.getFire()) < 50 && !lists.contains("1818")) {
                return "你的召唤兽火属性不足50";
            }
        } else if (id == 1827) {
            if (Integer.parseInt(pet.getWater()) < 50 && !lists.contains("1817")) {
                return "你的召唤兽水属性不足50";
            }
        } else if (id == 1246) {
            if (pet.getTurnRount() < 4) {
                return "你的召唤兽未飞升";
            }
            if ((int) pet.getSpir() < 500) {
                return "你的召唤兽灵性不足500";
            }
        } else if (id == 1247) {
            if (pet.getTurnRount() < 4) {
                return "你的召唤兽未飞升";
            }
            if ((int) pet.getBone() < 500) {
                return "你的召唤兽根骨不足500";
            }
        } else if (id == 1248) {
            if (pet.getTurnRount() < 4) {
                return "你的召唤兽未飞升";
            }
            if ((int) pet.getPower() < 500) {
                return "你的召唤兽力量不足500";
            }
        } else if (id == 1249) {
            if (pet.getTurnRount() < 4) {
                return "你的召唤兽未飞升";
            }
            if ((int) pet.getSpeed() < 500) {
                return "你的召唤兽敏捷不足500";
            }
        } else if (id == 1839) {
            if (Integer.parseInt(pet.getSoil()) < 50 && !lists.contains("1819")) {
                return "你的召唤兽土属性不足50";
            }
            if ((int) pet.getBone() < 450) {
                return "你的召唤兽根骨不足450";
            }
        } else if (id == 1831 || id == 1833) {
            if ((int) pet.getPower() < 450) {
                return "你的召唤兽力量不足450";
            }
        } else if (id == 1832 && (int) pet.getPower() < 450) {
            return "你的召唤兽力量不足450";
        }
        return null;
    }

    public void addPetLX(String lingxi, long qm) {
        if (lingxi == null || lingxi.equals("")) {
            return;
        }
        List<Skill> skills = LingXiUtil.getLingXiSkill(lingxi);
        for (Skill skill : skills) {
            if (skill != null) {
                FightingSkill fightingSkill = new FightingSkill(skill, this.lvl, this.zs, 250.0, qm, 0);
                fightingSkill.setSkillbeidong(1);
                if (skill.getSkillid() == 11047) {
                    fightingSkill.setSkillbeidong(0);
                    fightingSkill.setCamp(1);
                }
                this.addSkill(fightingSkill);
                if (skill.getSkillid() == 11006 || skill.getSkillid() == 11007 || skill.getSkillid() == 11010 || skill.getSkillid() == 11012 || skill.getSkillid() == 11013 || skill.getSkillid() == 11014 || skill.getSkillid() == 11015 || skill.getSkillid() == 11017 || skill.getSkillid() == 11018 || skill.getSkillid() == 11019 || skill.getSkillid() == 11025 || skill.getSkillid() == 11027 || skill.getSkillid() == 11029 || skill.getSkillid() == 11031 || skill.getSkillid() == 11032 || skill.getSkillid() == 11033 || skill.getSkillid() == 11034 || skill.getSkillid() == 11035) {
                    AddState addState = new AddState();
                    addState.setType(1);
                    addState.setStatename(skill.getSkillid() + "");
                    addState.setStateEffect((double) skill.getSkilllevel());
                    addState.setSurplus(3);
                    this.addStates.add(addState);
                } else {
                    continue;
                }
            }
        }
    }

    public ManData(RoleSummoning pet, FightingSummon summon) {
        this.QHFY = false;
        this.lingbaoshanxian = 0;
        this.lingbaotianfujineng = 0;
        this.yinian = new ArrayList<>();
        this.xingtai = 0;
        this.QKZT = false;
        this.bianshen = false;
        this.consumedNq = 0;
        this.spellSum = 0;
        this.yqz = 149;
        this.nqz = 30;
        this.nqzad = 0;
        this.methodExecuted = false;
        this.HF = 0;
        this.zy = 0;
        this.lzy = 0;
        this.sfPet = "0";
        this.HZT = new String[]{"赤芒", "金石", "青峰"};
        this.fmsld = Integer.valueOf(0);
        this.fmsld2 = Integer.valueOf(0);
        this.famencs = 0;
        this.huiHeFamencs = 0;
        this.attackDie = 0;
        this.ewhuoyue = 0;
        this.ewshanghai = 0;
        this.ewkangluobao = 0;
        this.ewyuanzhu = 0;
        this.dffmsld = 0;
        this.fmFHHT = 0;
        this.nrlrjs = 0;
        this.nrjycz = 0;
        this.nrfhht = 0;
        this.fmshfs = 0;
        this.zm = 0.0;
        this.kb = 0.0;
        this.mz = 0.0;
        this.pwljl = 0.0;
        this.pwlcd = 0.0;
        this.hsfz = 0.0;
        this.ljv = 0;
        this.qf = 0.0;
        this.hs = 0.0;
        this.fsmz = 0.0;
        this.fskbcd = 0.0;
        this.fskbjl = 0.0;
        this.hsjv = 0.0;
        this.bhws = 0.0;
        this.lsf = 0;
        this.lsd = 0;
        this.lsh = 0;
        this.lss = 0;
        this.ltf = 0;
        this.ltd = 0;
        this.lth = 0;
        this.lts = 0;
        this.tl = 0.0;
        this.tf = 0.0;
        this.ts = 0.0;
        this.th = 0.0;
        this.nqyy = 0.0;
        this.lxzt = 0;
        this.jlss = 0;
        this.jlmh = 0;
        this.jlgh = 0;
        this.jlyw = 0;
        this.tempJlss = 0;
        this.tempJlmh = 0;
        this.tempJlgh = 0;
        this.tempJlyw = 0;
        this.yyhomax = 0.0;
        this.fyhomax = 0.0;
        this.tzcf = 0.0;
        this.jtpa = false;
        this.cd = 0;
        this.jtcl = false;
        this.fhft = false;
        this.ymjr = false;
        this.fbgs = true;
        this.param = "";
        this.fskbbj = false;
        this.yijibiming = true;
        this.fzSum = Integer.valueOf(0);
        this.isHS = false;
        this.skill_count_z = 0;
        this.skill_count_s = 0;
        this.skill_count_g = 0;
        this.skill_count_p = 0;
        this.beatenNum = 0;
        this.skill_ss_tmp_mp = new HashMap<>();
        this.skill_ss_mp = new HashMap<>();
        this.camp = summon.getCamp();
        this.man = summon.getMan();
        this.type = 1;
        this.skills = new ArrayList<>();
        this.skillsCopy = new ArrayList<>();
        this.addStates = new ArrayList<>();
        this.id = pet.getSid().intValue();
        this.lvl = BattleMixDeal.petLvlint((int) pet.getGrade());
        this.zs = BattleMixDeal.petTurnRount((int) pet.getGrade());
        this.petType = pet.getSsn();
        this.manname = pet.getSummoningname();
        this.skin = pet.getSummoningskin();
        this.Quality = new Ql();
        this.speciesid = new BigDecimal(pet.getSummoningid());
        this.bearSkills = new ArrayList<>();
        this.huoyue = (double) (int) pet.getBone();
        this.shanghai = (double) (int) pet.getSpir();
        this.kangluobao = (double) (int) pet.getPower();
        this.yuanzhu = (double) (int) pet.getSpeed();
        ManData.petSkillswl = pet.getPetSkillswl();
        this.talentLvl = TalentTool.getPetTalentLvl(pet);
        long qm = (long) pet.getFriendliness();
        Mount mount = (summon.getHang().getMid() != null) ? AllServiceUtil.getMountService().selectMountsByMID(summon.getHang().getMid()) : null;
        this.addPetSkill(pet.getPetSkills(), qm, summon, pet.getPetSkillswl(), pet);
        this.addPetSkill(pet.getPetQlSkills(), qm, summon, pet.getPetSkillswl(), pet);
        this.addPetSkill(pet.getBeastSkills(), qm, summon, pet.getPetSkillswl(), pet);
        this.addPetSkill(pet.getSkill(), qm, summon, pet.getPetSkillswl(), pet);
        this.addPetLX(pet.getLingxi(), qm);
        String lxStr = LingXiUtil.isFull(pet.getLingxi());
        if (lxStr != "") {
            String[] lx = lxStr.split("=");
            int point = Integer.parseInt(lx[1]);
            String s = lx[0];
            int n = -1;
            switch (s.hashCode()) {
                case 49: {
                    if (s.equals("1")) {
                        n = 0;
                        break;
                    } else {
                        break;
                    }
                }
            }
            switch (n) {
                case 0: {
                    this.Quality.setRolehsfyv(this.Quality.getRolehsfyv() + (double) (point / 3));
                    this.Quality.setRolehsfyl(this.Quality.getRolehsfyl() + (double) (point / 3));
                    break;
                }
            }
        }
        CalculationUtil.getPet(this.Quality, pet, mount, this);
        this.skillsCopy.addAll(this.skills);
    }

    public ManData(Baby baby, int camp, int man, Map<String, Double> map) {
        this.QHFY = false;
        this.lingbaoshanxian = 0;
        this.lingbaotianfujineng = 0;
        this.yinian = new ArrayList<>();
        this.xingtai = 0;
        this.QKZT = false;
        this.bianshen = false;
        this.consumedNq = 0;
        this.spellSum = 0;
        this.yqz = 149;
        this.nqz = 30;
        this.nqzad = 0;
        this.methodExecuted = false;
        this.HF = 0;
        this.zy = 0;
        this.lzy = 0;
        this.sfPet = "0";
        this.HZT = new String[]{"赤芒", "金石", "青峰"};
        this.fmsld = Integer.valueOf(0);
        this.fmsld2 = Integer.valueOf(0);
        this.famencs = 0;
        this.huiHeFamencs = 0;
        this.attackDie = 0;
        this.ewhuoyue = 0;
        this.ewshanghai = 0;
        this.ewkangluobao = 0;
        this.ewyuanzhu = 0;
        this.dffmsld = 0;
        this.fmFHHT = 0;
        this.nrlrjs = 0;
        this.nrjycz = 0;
        this.nrfhht = 0;
        this.fmshfs = 0;
        this.zm = 0.0;
        this.kb = 0.0;
        this.mz = 0.0;
        this.pwljl = 0.0;
        this.pwlcd = 0.0;
        this.hsfz = 0.0;
        this.ljv = 0;
        this.qf = 0.0;
        this.hs = 0.0;
        this.fsmz = 0.0;
        this.fskbcd = 0.0;
        this.fskbjl = 0.0;
        this.hsjv = 0.0;
        this.bhws = 0.0;
        this.lsf = 0;
        this.lsd = 0;
        this.lsh = 0;
        this.lss = 0;
        this.ltf = 0;
        this.ltd = 0;
        this.lth = 0;
        this.lts = 0;
        this.tl = 0.0;
        this.tf = 0.0;
        this.ts = 0.0;
        this.th = 0.0;
        this.nqyy = 0.0;
        this.lxzt = 0;
        this.jlss = 0;
        this.jlmh = 0;
        this.jlgh = 0;
        this.jlyw = 0;
        this.tempJlss = 0;
        this.tempJlmh = 0;
        this.tempJlgh = 0;
        this.tempJlyw = 0;
        this.yyhomax = 0.0;
        this.fyhomax = 0.0;
        this.tzcf = 0.0;
        this.jtpa = false;
        this.cd = 0;
        this.jtcl = false;
        this.fhft = false;
        this.ymjr = false;
        this.fbgs = true;
        this.param = "";
        this.fskbbj = false;
        this.yijibiming = true;
        this.fzSum = Integer.valueOf(0);
        this.isHS = false;
        this.skill_count_z = 0;
        this.skill_count_s = 0;
        this.skill_count_g = 0;
        this.skill_count_p = 0;
        this.beatenNum = 0;
        this.skill_ss_tmp_mp = new HashMap<>();
        this.skill_ss_mp = new HashMap<>();
        this.id = baby.getBabyid().intValue();
        this.camp = camp;
        this.man = man + 15;
        this.hp = 1;
        this.hp_z = 1;
        this.type = 4;
        String nan = "100001";
        String nv = "100002";
        BigDecimal[] bids = new BigDecimal[4];
        String[] vv = baby.getParts().split("\\|");
        for (int i = 0; i < 4; ++i) {
            if (i < vv.length) {
                bids[i] = new BigDecimal(vv[i]);
            } else {
                bids[i] = new BigDecimal(-1);
            }
        }
        for (BigDecimal bid : bids) {
            Goodstable goodstable = AllServiceUtil.getGoodsTableService().getGoodsByRgID(bid);
            if (goodstable != null) {
                String goodsname = goodstable.getGoodsname();
                int n = -1;
                switch (goodsname.hashCode()) {
                    case 928475488: {
                        if (goodsname.equals("翎毛扇（男）")) {
                            n = 0;
                            break;
                        } else {
                            break;
                        }
                    }
                    case 1773181683: {
                        if (goodsname.equals("白羽扇（男）")) {
                            n = 1;
                            break;
                        } else {
                            break;
                        }
                    }
                    case 868924745: {
                        if (goodsname.equals("鹅毛扇（男）")) {
                            n = 2;
                            break;
                        } else {
                            break;
                        }
                    }
                    case 1756428426: {
                        if (goodsname.equals("鹤毛扇（男）")) {
                            n = 3;
                            break;
                        } else {
                            break;
                        }
                    }
                    case 913500156: {
                        if (goodsname.equals("塵尾扇（男）")) {
                            n = 4;
                            break;
                        } else {
                            break;
                        }
                    }
                    case -690158539: {
                        if (goodsname.equals("木剑（女）")) {
                            n = 5;
                            break;
                        } else {
                            break;
                        }
                    }
                    case -300103802: {
                        if (goodsname.equals("竹剑（女）")) {
                            n = 6;
                            break;
                        } else {
                            break;
                        }
                    }
                    case -1835375657: {
                        if (goodsname.equals("青铜剑（女）")) {
                            n = 7;
                            break;
                        } else {
                            break;
                        }
                    }
                    case -1669254730: {
                        if (goodsname.equals("越女剑（女）")) {
                            n = 8;
                            break;
                        } else {
                            break;
                        }
                    }
                    case 2110753117: {
                        if (goodsname.equals("龙泉剑（女）")) {
                            n = 9;
                            break;
                        } else {
                            break;
                        }
                    }
                    case 850229: {
                        if (goodsname.equals("木筝")) {
                            n = 10;
                            break;
                        } else {
                            break;
                        }
                    }
                    case 23647040: {
                        if (goodsname.equals("宝螺筝")) {
                            n = 11;
                            break;
                        } else {
                            break;
                        }
                    }
                    case -37287886: {
                        if (goodsname.equals("楠木花奔筝")) {
                            n = 12;
                            break;
                        } else {
                            break;
                        }
                    }
                    case -261994389: {
                        if (goodsname.equals("红木山水画筝")) {
                            n = 13;
                            break;
                        } else {
                            break;
                        }
                    }
                    case -901995419: {
                        if (goodsname.equals("骨雕飞天筝")) {
                            n = 14;
                            break;
                        } else {
                            break;
                        }
                    }
                    case 773452: {
                        if (goodsname.equals("庄子")) {
                            n = 15;
                            break;
                        } else {
                            break;
                        }
                    }
                    case 748497: {
                        if (goodsname.equals("孟子")) {
                            n = 16;
                            break;
                        } else {
                            break;
                        }
                    }
                    case 1144691: {
                        if (goodsname.equals("论语")) {
                            n = 17;
                            break;
                        } else {
                            break;
                        }
                    }
                    case 36298123: {
                        if (goodsname.equals("道德经")) {
                            n = 18;
                            break;
                        } else {
                            break;
                        }
                    }
                    case 695979: {
                        if (goodsname.equals("周易")) {
                            n = 19;
                            break;
                        } else {
                            break;
                        }
                    }
                }
                switch (n) {
                    case 0:
                    case 1: {
                        nan = "100011";
                        break;
                    }
                    case 2:
                    case 3: {
                        nan = "100012";
                        break;
                    }
                    case 4: {
                        nan = "100009";
                        break;
                    }
                    case 5:
                    case 6: {
                        nv = "100013";
                        break;
                    }
                    case 7:
                    case 8: {
                        nv = "100014";
                        break;
                    }
                    case 9: {
                        nv = "100006";
                        break;
                    }
                    case 10:
                    case 11: {
                        nan = "100015";
                        nv = "100016";
                        break;
                    }
                    case 12:
                    case 13: {
                        nan = "100017";
                        nv = "100018";
                        break;
                    }
                    case 14: {
                        nan = "100007";
                        nv = "100008";
                        break;
                    }
                    case 15:
                    case 16: {
                        nan = "100019";
                        nv = "100020";
                        break;
                    }
                    case 17:
                    case 18: {
                        nan = "100021";
                        nv = "100022";
                        break;
                    }
                    case 19: {
                        nan = "100023";
                        nv = "100024";
                        break;
                    }
                }
            }
        }
        this.model = (((int) baby.getChildSex() == 0) ? nan : nv);
        this.skin = this.model;
        this.manname = baby.getBabyname();
        this.skills = new ArrayList<>();
        String Talents = baby.getTalents();
        if (Talents != null && !Talents.equals("")) {
            String[] v = Talents.split("\\|");
            for (int j = 0; j < v.length; ++j) {
                String[] vs = v[j].split("=");
                int id = Integer.parseInt(vs[0]);
                if (id == 1) {
                    CalculationUtil.addValue(map, "AP成长", 0.01);
                } else if (id == 2) {
                    CalculationUtil.addValue(map, "HP成长", 0.01);
                } else if (id == 3) {
                    CalculationUtil.addValue(map, "MP成长", 0.01);
                } else {
                    Talent talent = GameServer.getTalent(id);
                    if (talent != null) {
                        int lvl = Integer.parseInt(vs[1]);
                        FightingSkill fightingSkill = new FightingSkill(talent, lvl);
                        this.addSkill(fightingSkill);
                    }
                }
            }
        }
        this.addStates = new ArrayList<>();
    }

    public ManData(Baby baby, int camp, int man, Map<String, Double> map, String text) {
        this.QHFY = false;
        this.lingbaoshanxian = 0;
        this.lingbaotianfujineng = 0;
        this.yinian = new ArrayList<>();
        this.xingtai = 0;
        this.QKZT = false;
        this.bianshen = false;
        this.consumedNq = 0;
        this.spellSum = 0;
        this.yqz = 149;
        this.nqz = 30;
        this.nqzad = 0;
        this.methodExecuted = false;
        this.HF = 0;
        this.zy = 0;
        this.lzy = 0;
        this.sfPet = "0";
        this.HZT = new String[]{"赤芒", "金石", "青峰"};
        this.fmsld = Integer.valueOf(0);
        this.fmsld2 = Integer.valueOf(0);
        this.famencs = 0;
        this.huiHeFamencs = 0;
        this.attackDie = 0;
        this.ewhuoyue = 0;
        this.ewshanghai = 0;
        this.ewkangluobao = 0;
        this.ewyuanzhu = 0;
        this.dffmsld = 0;
        this.fmFHHT = 0;
        this.nrlrjs = 0;
        this.nrjycz = 0;
        this.nrfhht = 0;
        this.fmshfs = 0;
        this.zm = 0.0;
        this.kb = 0.0;
        this.mz = 0.0;
        this.pwljl = 0.0;
        this.pwlcd = 0.0;
        this.hsfz = 0.0;
        this.ljv = 0;
        this.qf = 0.0;
        this.hs = 0.0;
        this.fsmz = 0.0;
        this.fskbcd = 0.0;
        this.fskbjl = 0.0;
        this.hsjv = 0.0;
        this.bhws = 0.0;
        this.lsf = 0;
        this.lsd = 0;
        this.lsh = 0;
        this.lss = 0;
        this.ltf = 0;
        this.ltd = 0;
        this.lth = 0;
        this.lts = 0;
        this.tl = 0.0;
        this.tf = 0.0;
        this.ts = 0.0;
        this.th = 0.0;
        this.nqyy = 0.0;
        this.lxzt = 0;
        this.jlss = 0;
        this.jlmh = 0;
        this.jlgh = 0;
        this.jlyw = 0;
        this.tempJlss = 0;
        this.tempJlmh = 0;
        this.tempJlgh = 0;
        this.tempJlyw = 0;
        this.yyhomax = 0.0;
        this.fyhomax = 0.0;
        this.tzcf = 0.0;
        this.jtpa = false;
        this.cd = 0;
        this.jtcl = false;
        this.fhft = false;
        this.ymjr = false;
        this.fbgs = true;
        this.param = "";
        this.fskbbj = false;
        this.yijibiming = true;
        this.fzSum = Integer.valueOf(0);
        this.isHS = false;
        this.skill_count_z = 0;
        this.skill_count_s = 0;
        this.skill_count_g = 0;
        this.skill_count_p = 0;
        this.beatenNum = 0;
        this.skill_ss_tmp_mp = new HashMap<>();
        this.skill_ss_mp = new HashMap<>();
        this.id = baby.getBabyid().intValue();
        this.camp = camp;
        this.man = man + 15;
        this.hp = 1;
        this.hp_z = 1;
        this.type = 4;
        String nan = "100001";
        String nv = "100002";
        Integer xiaoxin = this.getTotalXiaoxin(baby);
        if ((int) xiaoxin > 1000) {
            nan = "100007";
            nv = "100008";
            if ((int) xiaoxin > 2000) {
                nan = "100009";
                nv = "100006";
            }
        }
        this.model = (((int) baby.getChildSex() == 0) ? nan : nv);
        this.skin = this.model;
        this.manname = baby.getBabyname();
        this.skills = new ArrayList<>();
        String Talents = baby.getTalents();
        if (Talents != null && !Talents.equals("")) {
            String[] v = Talents.split("\\|");
            for (int i = 0; i < v.length; ++i) {
                String[] vs = v[i].split("=");
                int id = Integer.parseInt(vs[0]);
                if (id == 1) {
                    CalculationUtil.addValue(map, "AP成长", 0.01);
                } else if (id == 2) {
                    CalculationUtil.addValue(map, "HP成长", 0.01);
                } else if (id == 3) {
                    CalculationUtil.addValue(map, "MP成长", 0.01);
                } else {
                    Talent talent = GameServer.getTalent(id);
                    if (talent != null) {
                        int lvl = Integer.parseInt(vs[1]);
                        FightingSkill fightingSkill = new FightingSkill(talent, lvl);
                        this.addSkill(fightingSkill);
                    }
                }
            }
        }
        this.addStates = new ArrayList<>();
    }

    private Integer getTotalXiaoxin(Baby baby) {
        BigDecimal[] bids = new BigDecimal[4];
        String[] vs = baby.getParts().split("\\|");
        for (int i = 0; i < 4; ++i) {
            if (i < vs.length) {
                bids[i] = new BigDecimal(vs[i]);
            } else {
                bids[i] = new BigDecimal(-1);
            }
        }
        Integer xiaoxin = baby.getXiaoxin();
        if (bids != null) {
            int size = 0;
            for (int j = 0; j < bids.length; ++j) {
                Goodstable goodstable = AllServiceUtil.getGoodsTableService().getGoodsByRgID(bids[j]);
                if (goodstable != null) {
                    String[] v = goodstable.getValue().split("\\|");
                    for (int k = 1; k < v.length; ++k) {
                        String[] v2 = v[k].split("=");
                        if (v2[0].equals("孝心")) {
                            xiaoxin = Integer.valueOf((int) xiaoxin + Integer.parseInt(v2[1]));
                        }
                    }
                }
            }
        }
        return xiaoxin;
    }

    public ManData(Lingbao lingbao, ManData manData) {
        this.QHFY = false;
        this.lingbaoshanxian = 0;
        this.lingbaotianfujineng = 0;
        this.yinian = new ArrayList<>();
        this.xingtai = 0;
        this.QKZT = false;
        this.bianshen = false;
        this.consumedNq = 0;
        this.spellSum = 0;
        this.yqz = 149;
        this.nqz = 30;
        this.nqzad = 0;
        this.methodExecuted = false;
        this.HF = 0;
        this.zy = 0;
        this.lzy = 0;
        this.sfPet = "0";
        this.HZT = new String[]{"赤芒", "金石", "青峰"};
        this.fmsld = Integer.valueOf(0);
        this.fmsld2 = Integer.valueOf(0);
        this.famencs = 0;
        this.huiHeFamencs = 0;
        this.attackDie = 0;
        this.ewhuoyue = 0;
        this.ewshanghai = 0;
        this.ewkangluobao = 0;
        this.ewyuanzhu = 0;
        this.dffmsld = 0;
        this.fmFHHT = 0;
        this.nrlrjs = 0;
        this.nrjycz = 0;
        this.nrfhht = 0;
        this.fmshfs = 0;
        this.zm = 0.0;
        this.kb = 0.0;
        this.mz = 0.0;
        this.pwljl = 0.0;
        this.pwlcd = 0.0;
        this.hsfz = 0.0;
        this.ljv = 0;
        this.qf = 0.0;
        this.hs = 0.0;
        this.fsmz = 0.0;
        this.fskbcd = 0.0;
        this.fskbjl = 0.0;
        this.hsjv = 0.0;
        this.bhws = 0.0;
        this.lsf = 0;
        this.lsd = 0;
        this.lsh = 0;
        this.lss = 0;
        this.ltf = 0;
        this.ltd = 0;
        this.lth = 0;
        this.lts = 0;
        this.tl = 0.0;
        this.tf = 0.0;
        this.ts = 0.0;
        this.th = 0.0;
        this.nqyy = 0.0;
        this.lxzt = 0;
        this.jlss = 0;
        this.jlmh = 0;
        this.jlgh = 0;
        this.jlyw = 0;
        this.tempJlss = 0;
        this.tempJlmh = 0;
        this.tempJlgh = 0;
        this.tempJlyw = 0;
        this.yyhomax = 0.0;
        this.fyhomax = 0.0;
        this.tzcf = 0.0;
        this.jtpa = false;
        this.cd = 0;
        this.jtcl = false;
        this.fhft = false;
        this.ymjr = false;
        this.fbgs = true;
        this.param = "";
        this.fskbbj = false;
        this.yijibiming = true;
        this.fzSum = Integer.valueOf(0);
        this.isHS = false;
        this.skill_count_z = 0;
        this.skill_count_s = 0;
        this.skill_count_g = 0;
        this.skill_count_p = 0;
        this.beatenNum = 0;
        this.skill_ss_tmp_mp = new HashMap<>();
        this.skill_ss_mp = new HashMap<>();
        this.id = lingbao.getBaoid().intValue();
        this.hp = 1;
        this.hp_z = 1;
        this.type = 3;
        this.id = lingbao.getBaoid().intValue();
        this.model = lingbao.getSkin();
        this.skin = this.model;
        this.manname = lingbao.getBaoname();
        this.camp = manData.getCamp();
        this.man = manData.getMan() + 10;
        this.sp = (int) Double.parseDouble(lingbao.getBaospeed());
        this.huoyue = (double) (int) lingbao.getBaoactive();
        this.qihe = lingbao.getLingbaoqihe();
        int lx = 0;
        if (lingbao.getBaotype().equals("攻击")) {
            lx = 2;
            this.shanghai = Double.parseDouble(lingbao.getBaoap());
        } else if (lingbao.getBaotype().equals("辅助")) {
            lx = 1;
            this.shanghai = Double.parseDouble(lingbao.getBaoreply());
        } else {
            this.shanghai = Double.parseDouble(lingbao.getBaoshot());
        }
        this.kangluobao = Double.parseDouble(lingbao.getResistshot());
        this.yuanzhu = Double.parseDouble(lingbao.getAssistance());
        if (lingbao.getFushis() != null && !lingbao.getFushis().equals("")) {
            String[] fs = lingbao.getFushis().split("\\|");
            for (int i = 0; i < fs.length; ++i) {
                Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(fs[i]));
                BaseEquip baseEquip = (good != null) ? good.getEquip() : null;
                if (baseEquip != null && baseEquip.getQls() != null) {
                    for (int j = baseEquip.getQls().size() - 1; j >= 0; --j) {
                        BaseQl baseQl = (BaseQl) baseEquip.getQls().get(j);
                        if (baseQl.getKey().equals("活跃")) {
                            this.huoyue += baseQl.getValue();
                        } else if (baseQl.getKey().equals("速度")) {
                            this.sp = (int) ((double) this.sp + baseQl.getValue());
                        } else if (baseQl.getKey().equals("负敏")) {
                            this.sp = (int) ((double) this.sp - baseQl.getValue());
                        } else if (baseQl.getKey().equals("支援")) {
                            this.yuanzhu += baseQl.getValue() / 100.0;
                        } else if (baseQl.getKey().equals("抗落宝")) {
                            this.kangluobao += baseQl.getValue() / 100.0;
                        } else if ((lx == 2 && baseQl.getKey().equals("伤害")) || (lx == 1 && baseQl.getKey().equals("回复")) || (lx == 0 && baseQl.getKey().equals("落宝"))) {
                            this.shanghai += baseQl.getValue() / 100.0;
                        }
                    }
                }
            }
        }
        this.skills = new ArrayList<>();
        int pz = BaseValue.getQv(lingbao.getBaoquality());
        if (lingbao.getSkills() != null && !lingbao.getSkills().equals("")) {
            String[] scs = null;
            if (lingbao.getGoodskill() != null && !lingbao.getGoodskill().equals("")) {
                scs = lingbao.getGoodskill().split("\\|");
            }
            String[] v = lingbao.getSkills().split("\\|");
            for (int k = 0; k < v.length; ++k) {
                if (!v[k].equals("")) {
                    String[] vs = v[k].split("=");
                    Skill skill = GameServer.getSkill(vs[0]);
                    if (skill != null) {
                        int sc = EquipTool.contains(scs, vs[0]) ? 1 : 0;
                        FightingSkill fightingSkill = new FightingSkill(skill, lingbao.getLingbaolvl().intValue() + pz, Integer.parseInt(vs[1]), (double) sc, 0L, 0);
                        this.addSkill(fightingSkill);
                    }
                }
            }
        }
        this.addStates = new ArrayList<>();
        this.TFSkills = new ArrayList<>();
        if (lingbao.getTianfuskill() != null && !lingbao.getTianfuskill().equals("")) {
            String[] tfs = lingbao.getTianfuskill().split("\\|");
            for (int l = 0; l < tfs.length; ++l) {
                int lvl = tfs[l].startsWith("高") ? 2 : 1;
                if (tfs[l].endsWith("契合")) {
                    this.qihe = (long) ((double) this.qihe * (1.0 + 0.1 * (double) lvl));
                } else if (tfs[l].endsWith("闪现")) {
                    this.yuanzhu += 0.1 * (double) lvl;
                } else if (tfs[l].endsWith("活跃")) {
                    this.huoyue *= 1.0 + 0.1 * (double) lvl;
                } else if (tfs[l].endsWith("抵抗")) {
                    this.kangluobao += 0.1 * (double) lvl;
                } else if (tfs[l].endsWith("敏捷")) {
                    this.sp += ((lvl == 2) ? 300 : 200);
                } else if ((lx == 2 && tfs[l].endsWith("增强")) || (lx == 1 && tfs[l].endsWith("回生"))) {
                    if (tfs[l].indexOf("根骨") != -1) {
                        this.shanghai += 5.0E-4 * (double) lvl * (double) (int) (manData.getHuoyue() / 4.0);
                    } else if (tfs[l].indexOf("灵性") != -1) {
                        this.shanghai += 5.0E-4 * (double) lvl * (double) (int) (manData.getShanghai() / 4.0);
                    } else if (tfs[l].indexOf("力量") != -1) {
                        this.shanghai += 5.0E-4 * (double) lvl * (double) (int) (manData.getKangluobao() / 4.0);
                    } else if (tfs[l].indexOf("敏捷") != -1) {
                        this.shanghai += 5.0E-4 * (double) lvl * (double) (int) (manData.getYuanzhu() / 4.0);
                    }
                } else if (tfs[l].contains("招魂")) {
                    Skill skill2 = GameServer.getSkill("招魂");
                    FightingSkill fightingSkill2 = new FightingSkill(skill2, lingbao.getLingbaolvl().intValue() + pz, 1, 10000.0, 0L, 0);
                    this.addSkill(fightingSkill2);
                } else if (tfs[l].contains("高级挣扎") || tfs[l].contains("低级挣扎")) {
                    Skill skill2 = GameServer.getSkill(tfs[l]);
                    FightingSkill fightingSkill2 = new FightingSkill(skill2, lingbao.getLingbaolvl().intValue() + pz, 1, 10000.0, 0L, 0);
                    this.addSkill(fightingSkill2);
                } else if (tfs[l].contains("高级回灵")) {
                    this.addAddState(tfs[l], 100.0, 0.0, 9999);
                } else {
                    this.TFSkills.add(tfs[l]);
                }
            }
        }
    }

    public ManData(Lingbao lingbao, PalData palData) {
        this.QHFY = false;
        this.lingbaoshanxian = 0;
        this.lingbaotianfujineng = 0;
        this.yinian = new ArrayList<>();
        this.xingtai = 0;
        this.QKZT = false;
        this.bianshen = false;
        this.consumedNq = 0;
        this.spellSum = 0;
        this.yqz = 149;
        this.nqz = 30;
        this.nqzad = 0;
        this.methodExecuted = false;
        this.HF = 0;
        this.zy = 0;
        this.lzy = 0;
        this.sfPet = "0";
        this.HZT = new String[]{"赤芒", "金石", "青峰"};
        this.fmsld = Integer.valueOf(0);
        this.fmsld2 = Integer.valueOf(0);
        this.famencs = 0;
        this.huiHeFamencs = 0;
        this.attackDie = 0;
        this.ewhuoyue = 0;
        this.ewshanghai = 0;
        this.ewkangluobao = 0;
        this.ewyuanzhu = 0;
        this.dffmsld = 0;
        this.fmFHHT = 0;
        this.nrlrjs = 0;
        this.nrjycz = 0;
        this.nrfhht = 0;
        this.fmshfs = 0;
        this.zm = 0.0;
        this.kb = 0.0;
        this.mz = 0.0;
        this.pwljl = 0.0;
        this.pwlcd = 0.0;
        this.hsfz = 0.0;
        this.ljv = 0;
        this.qf = 0.0;
        this.hs = 0.0;
        this.fsmz = 0.0;
        this.fskbcd = 0.0;
        this.fskbjl = 0.0;
        this.hsjv = 0.0;
        this.bhws = 0.0;
        this.lsf = 0;
        this.lsd = 0;
        this.lsh = 0;
        this.lss = 0;
        this.ltf = 0;
        this.ltd = 0;
        this.lth = 0;
        this.lts = 0;
        this.tl = 0.0;
        this.tf = 0.0;
        this.ts = 0.0;
        this.th = 0.0;
        this.nqyy = 0.0;
        this.lxzt = 0;
        this.jlss = 0;
        this.jlmh = 0;
        this.jlgh = 0;
        this.jlyw = 0;
        this.tempJlss = 0;
        this.tempJlmh = 0;
        this.tempJlgh = 0;
        this.tempJlyw = 0;
        this.yyhomax = 0.0;
        this.fyhomax = 0.0;
        this.tzcf = 0.0;
        this.jtpa = false;
        this.cd = 0;
        this.jtcl = false;
        this.fhft = false;
        this.ymjr = false;
        this.fbgs = true;
        this.param = "";
        this.fskbbj = false;
        this.yijibiming = true;
        this.fzSum = Integer.valueOf(0);
        this.isHS = false;
        this.skill_count_z = 0;
        this.skill_count_s = 0;
        this.skill_count_g = 0;
        this.skill_count_p = 0;
        this.beatenNum = 0;
        this.skill_ss_tmp_mp = new HashMap<>();
        this.skill_ss_mp = new HashMap<>();
        this.id = lingbao.getBaoid().intValue();
        this.hp = 1;
        this.hp_z = 1;
        this.type = 3;
        this.id = lingbao.getBaoid().intValue();
        this.model = lingbao.getSkin();
        this.skin = this.model;
        this.manname = lingbao.getBaoname();
        this.sp = (int) Double.parseDouble(lingbao.getBaospeed());
        this.huoyue = (double) (int) lingbao.getBaoactive();
        this.qihe = lingbao.getLingbaoqihe();
        int lx = 0;
        if (lingbao.getBaotype().equals("攻击")) {
            lx = 2;
            this.shanghai = Double.parseDouble(lingbao.getBaoap());
        } else if (lingbao.getBaotype().equals("辅助")) {
            lx = 1;
            this.shanghai = Double.parseDouble(lingbao.getBaoreply());
        } else {
            this.shanghai = Double.parseDouble(lingbao.getBaoshot());
        }
        this.kangluobao = Double.parseDouble(lingbao.getResistshot());
        this.yuanzhu = Double.parseDouble(lingbao.getAssistance());
        if (lingbao.getFushis() != null && !lingbao.getFushis().equals("")) {
            String[] fs = lingbao.getFushis().split("\\|");
            for (int i = 0; i < fs.length; ++i) {
                Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(fs[i]));
                BaseEquip baseEquip = (good != null) ? good.getEquip() : null;
                if (baseEquip != null && baseEquip.getQls() != null) {
                    for (int j = baseEquip.getQls().size() - 1; j >= 0; --j) {
                        BaseQl baseQl = (BaseQl) baseEquip.getQls().get(j);
                        if (baseQl.getKey().equals("活跃")) {
                            this.huoyue += baseQl.getValue();
                        } else if (baseQl.getKey().equals("速度")) {
                            this.sp = (int) ((double) this.sp + baseQl.getValue());
                        } else if (baseQl.getKey().equals("负敏")) {
                            this.sp = (int) ((double) this.sp - baseQl.getValue());
                        } else if (baseQl.getKey().equals("支援")) {
                            this.yuanzhu += baseQl.getValue() / 100.0;
                        } else if (baseQl.getKey().equals("抗落宝")) {
                            this.kangluobao += baseQl.getValue() / 100.0;
                        } else if ((lx == 2 && baseQl.getKey().equals("伤害")) || (lx == 1 && baseQl.getKey().equals("回复")) || (lx == 0 && baseQl.getKey().equals("落宝"))) {
                            this.shanghai += baseQl.getValue() / 100.0;
                        }
                    }
                }
            }
        }
        this.skills = new ArrayList<>();
        int pz = BaseValue.getQv(lingbao.getBaoquality());
        if (lingbao.getSkills() != null && !lingbao.getSkills().equals("")) {
            String[] scs = null;
            if (lingbao.getGoodskill() != null && !lingbao.getGoodskill().equals("")) {
                scs = lingbao.getGoodskill().split("\\|");
            }
            String[] v = lingbao.getSkills().split("\\|");
            for (int k = 0; k < v.length; ++k) {
                if (!v[k].equals("")) {
                    String[] vs = v[k].split("=");
                    Skill skill = GameServer.getSkill(vs[0]);
                    if (skill != null) {
                        int sc = EquipTool.contains(scs, vs[0]) ? 1 : 0;
                        FightingSkill fightingSkill = new FightingSkill(skill, lingbao.getLingbaolvl().intValue() + pz, Integer.parseInt(vs[1]), (double) sc, 0L, 0);
                        this.addSkill(fightingSkill);
                    }
                }
            }
        }
        long point = (long) (this.lvl + this.zs * 40);
        if (lingbao.getTianfuskill() != null && !lingbao.getTianfuskill().equals("")) {
            String[] tfs = lingbao.getTianfuskill().split("\\|");
            for (int l = 0; l < tfs.length; ++l) {
                int lvl = tfs[l].startsWith("高") ? 2 : 1;
                if (tfs[l].endsWith("契合")) {
                    this.qihe = (long) ((double) this.qihe * (1.0 + 0.1 * (double) lvl));
                } else if (tfs[l].endsWith("闪现")) {
                    this.yuanzhu += 0.1 * (double) lvl;
                } else if (tfs[l].endsWith("活跃")) {
                    this.huoyue *= 1.0 + 0.1 * (double) lvl;
                } else if (tfs[l].endsWith("抵抗")) {
                    this.kangluobao += 0.1 * (double) lvl;
                } else if (tfs[l].endsWith("敏捷")) {
                    this.sp += ((lvl == 2) ? 300 : 200);
                } else if ((lx == 2 && tfs[l].endsWith("增强")) || (lx == 1 && tfs[l].endsWith("回生"))) {
                    if (tfs[l].indexOf("根骨") != -1) {
                        this.shanghai += 5.0E-4 * (double) lvl * (double) (long) ((double) ((long) lvl + point / (long) palData.getSize() * (long) palData.getJds()[0]) / 4.0);
                    } else if (tfs[l].indexOf("灵性") != -1) {
                        this.shanghai += 5.0E-4 * (double) lvl * (double) (long) ((double) ((long) lvl + point / (long) palData.getSize() * (long) palData.getJds()[1]) / 4.0);
                    } else if (tfs[l].indexOf("力量") != -1) {
                        this.shanghai += 5.0E-4 * (double) lvl * (double) (long) ((double) ((long) lvl + point / (long) palData.getSize() * (long) palData.getJds()[2]) / 4.0);
                    } else if (tfs[l].indexOf("敏捷") != -1) {
                        this.shanghai += 5.0E-4 * (double) lvl * (double) (long) ((double) ((long) lvl + point / (long) palData.getSize() * (long) palData.getJds()[3]) / 4.0);
                    }
                }
            }
        }
        this.addStates = new ArrayList<>();
    }

    public ManData(int camp, int man, Pal pal, int lvl, ManData master) {
        this.QHFY = false;
        this.lingbaoshanxian = 0;
        this.lingbaotianfujineng = 0;
        this.yinian = new ArrayList<>();
        this.xingtai = 0;
        this.QKZT = false;
        this.bianshen = false;
        this.consumedNq = 0;
        this.spellSum = 0;
        this.yqz = 149;
        this.nqz = 30;
        this.nqzad = 0;
        this.methodExecuted = false;
        this.HF = 0;
        this.zy = 0;
        this.lzy = 0;
        this.sfPet = "0";
        this.HZT = new String[]{"赤芒", "金石", "青峰"};
        this.fmsld = Integer.valueOf(0);
        this.fmsld2 = Integer.valueOf(0);
        this.famencs = 0;
        this.huiHeFamencs = 0;
        this.attackDie = 0;
        this.ewhuoyue = 0;
        this.ewshanghai = 0;
        this.ewkangluobao = 0;
        this.ewyuanzhu = 0;
        this.dffmsld = 0;
        this.fmFHHT = 0;
        this.nrlrjs = 0;
        this.nrjycz = 0;
        this.nrfhht = 0;
        this.fmshfs = 0;
        this.zm = 0.0;
        this.kb = 0.0;
        this.mz = 0.0;
        this.pwljl = 0.0;
        this.pwlcd = 0.0;
        this.hsfz = 0.0;
        this.ljv = 0;
        this.qf = 0.0;
        this.hs = 0.0;
        this.fsmz = 0.0;
        this.fskbcd = 0.0;
        this.fskbjl = 0.0;
        this.hsjv = 0.0;
        this.bhws = 0.0;
        this.lsf = 0;
        this.lsd = 0;
        this.lsh = 0;
        this.lss = 0;
        this.ltf = 0;
        this.ltd = 0;
        this.lth = 0;
        this.lts = 0;
        this.tl = 0.0;
        this.tf = 0.0;
        this.ts = 0.0;
        this.th = 0.0;
        this.nqyy = 0.0;
        this.lxzt = 0;
        this.jlss = 0;
        this.jlmh = 0;
        this.jlgh = 0;
        this.jlyw = 0;
        this.tempJlss = 0;
        this.tempJlmh = 0;
        this.tempJlgh = 0;
        this.tempJlyw = 0;
        this.yyhomax = 0.0;
        this.fyhomax = 0.0;
        this.tzcf = 0.0;
        this.jtpa = false;
        this.cd = 0;
        this.jtcl = false;
        this.fhft = false;
        this.ymjr = false;
        this.fbgs = true;
        this.param = "";
        this.fskbbj = false;
        this.yijibiming = true;
        this.fzSum = Integer.valueOf(0);
        this.isHS = false;
        this.skill_count_z = 0;
        this.skill_count_s = 0;
        this.skill_count_g = 0;
        this.skill_count_p = 0;
        this.beatenNum = 0;
        this.skill_ss_tmp_mp = new HashMap<>();
        this.skill_ss_mp = new HashMap<>();
        this.id = pal.getId().intValue();
        this.yqz = 149;
        this.nqz = 30;
        this.camp = camp;
        this.man = man;
        this.type = 2;
        this.skills = new ArrayList<>();
        this.addStates = new ArrayList<>();
        PalData palData = GameServer.getPalData(pal.getpId());
        this.manname = palData.getName();
        this.model = palData.getSkin();
        this.skin = this.model;
        CalculationPal.getPal(pal, palData, this, lvl, master);
        if (pal.getPalSkillAI() != null) {
            String ai = this.initPalAI(pal.getPalSkillAI());
            String[] vs = ai.split("\\|");
            for (int i = 0; i < vs.length; ++i) {
                this.addAI(vs[i]);
            }
            palData.setAis(this.ais);
        } else if (palData.getAi() != null && !palData.getAi().equals("")) {
            String[] vs2 = palData.getAi().split("\\|");
            for (int j = 0; j < vs2.length; ++j) {
                this.addAI(vs2[j]);
            }
            palData.setAis(this.ais);
        }
    }

    public String initPalAI(List<String> skillsAi) {
        String aiData = "";
        for (int x = 0; x < 10; ++x) {
            int h = x * skillsAi.size();
            if (skillsAi != null) {
                for (int i = 0; i < skillsAi.size(); ++i) {
                    String skillId = (String) this.dbSkillMap().get(skillsAi.get(i));
                    String ai = "条件=回合-" + (i + 1 + h) + "=法术-" + skillId + "-25000|";
                    aiData += ai;
                }
            }
        }
        return aiData;
    }

    public Map<String, String> dbSkillMap() {
        Map<String, String> map = new HashMap<>();
        map.put("反间之计", "1001");
        map.put("情真意切", "1002");
        map.put("谗言相加", "1003");
        map.put("借刀杀人", "1004");
        map.put("失心狂乱", "1005");
        map.put("作茧自缚", "1006");
        map.put("金蛇缠丝", "1007");
        map.put("天罗地网", "1008");
        map.put("作壁上观", "1009");
        map.put("四面楚歌", "1010");
        map.put("催眠咒", "1011");
        map.put("瞌睡咒", "1012");
        map.put("离魂咒", "1013");
        map.put("迷魂醉", "1014");
        map.put("百日眠", "1015");
        map.put("蛇蝎美人", "1016");
        map.put("追魂迷香", "1017");
        map.put("断肠烈散", "1018");
        map.put("鹤顶红粉", "1019");
        map.put("万毒攻心", "1020");
        map.put("夺命勾魂", "1021");
        map.put("追神摄魄", "1022");
        map.put("魔音摄心", "1023");
        map.put("销魂蚀骨", "1024");
        map.put("阎罗追命", "1025");
        map.put("妖之魔力", "1026");
        map.put("力神复苏", "1027");
        map.put("狮王之怒", "1028");
        map.put("兽王神力", "1029");
        map.put("魔神附身", "1030");
        map.put("红袖添香", "1031");
        map.put("莲步轻舞", "1032");
        map.put("楚楚可怜", "1033");
        map.put("魔神护体", "1034");
        map.put("含情脉脉", "1035");
        map.put("魔之飞步", "1036");
        map.put("急速之魔", "1037");
        map.put("魔神飞舞", "1038");
        map.put("天外飞魔", "1039");
        map.put("乾坤借速", "1040");
        map.put("飞砂走石", "1041");
        map.put("乘风破浪", "1042");
        map.put("太乙生风", "1043");
        map.put("风雷涌动", "1044");
        map.put("袖里乾坤", "1045");
        map.put("雷霆霹雳", "1046");
        map.put("日照光华", "1047");
        map.put("雷神怒击", "1048");
        map.put("电闪雷鸣", "1049");
        map.put("天诛地灭", "1050");
        map.put("龙卷雨击", "1051");
        map.put("龙腾水溅", "1052");
        map.put("龙啸九天", "1053");
        map.put("蛟龙出海", "1054");
        map.put("九龙冰封", "1055");
        map.put("地狱烈火", "1056");
        map.put("天雷怒火", "1057");
        map.put("三味真火", "1058");
        map.put("烈火骄阳", "1059");
        map.put("九阴纯火", "1060");
        map.put("幽冥鬼火", "1061");
        map.put("火影迷踪", "1062");
        map.put("冥烟销骨", "1063");
        map.put("落日熔金", "1064");
        map.put("血海深仇", "1065");
        map.put("吸血水蛭", "1066");
        map.put("六翅毒蝉", "1067");
        map.put("啮骨抽髓", "1068");
        map.put("血煞之蛊", "1069");
        map.put("吸星大法", "1070");
        map.put("麻沸散", "1071");
        map.put("鬼失惊", "1072");
        map.put("乱魂钉", "1073");
        map.put("失心疯", "1074");
        map.put("孟婆汤", "1075");
        map.put("幽怜魅影", "1076");
        map.put("醉生梦死", "1077");
        map.put("一曲销魂", "1078");
        map.put("秦丝冰雾", "1079");
        map.put("倩女幽魂", "1080");
        map.put("平地生雷", "1081");
        map.put("惊霆贯顶", "1082");
        map.put("列缺霹雳", "1083");
        map.put("风雷万钧", "1084");
        map.put("震天动地", "1085");
        map.put("碧海潮生", "1086");
        map.put("怒涛拍岸", "1087");
        map.put("洪波涌起", "1088");
        map.put("白浪滔天", "1089");
        map.put("沧海横流", "1090");
        map.put("久旱初雨", "1091");
        map.put("兴云致雨", "1092");
        map.put("润物无声", "1093");
        map.put("沛然莫御", "1094");
        map.put("泽被万物", "1095");
        map.put("激浊扬清", "1096");
        map.put("狂飙怒号", "1097");
        map.put("扶摇而上", "1098");
        map.put("凌虚御风", "1099");
        map.put("飞举九天", "1100");
        return map;
    }

    public void JQ(double xs) {
        this.hp_z = (int) ((double) this.hp_z * xs);
        this.hp = this.hp_z;
        this.ap = (int) ((double) this.ap * xs);
        this.sp = (int) ((double) this.sp * xs);
        --xs;
        xs *= 2.0;
        this.Quality.addap((int) xs);
        this.Quality.addkang((int) (xs * 2.0));
        this.Quality.addQ(xs * 2.0);
        this.Quality.addHS(xs * 2.0);
        this.Quality.setEjs(this.Quality.getEjs() + xs);
        this.Quality.setEzs(this.Quality.getEzs() + xs);
    }

    public ManData(JSONObject man, boolean isHaizi, double xs) {
        this.QHFY = false;
        this.lingbaoshanxian = 0;
        this.lingbaotianfujineng = 0;
        this.yinian = new ArrayList<>();
        this.xingtai = 0;
        this.QKZT = false;
        this.bianshen = false;
        this.consumedNq = 0;
        this.spellSum = 0;
        this.yqz = 149;
        this.nqz = 30;
        this.nqzad = 0;
        this.methodExecuted = false;
        this.HF = 0;
        this.zy = 0;
        this.lzy = 0;
        this.sfPet = "0";
        this.HZT = new String[]{"赤芒", "金石", "青峰"};
        this.fmsld = Integer.valueOf(0);
        this.fmsld2 = Integer.valueOf(0);
        this.famencs = 0;
        this.huiHeFamencs = 0;
        this.attackDie = 0;
        this.ewhuoyue = 0;
        this.ewshanghai = 0;
        this.ewkangluobao = 0;
        this.ewyuanzhu = 0;
        this.dffmsld = 0;
        this.fmFHHT = 0;
        this.nrlrjs = 0;
        this.nrjycz = 0;
        this.nrfhht = 0;
        this.fmshfs = 0;
        this.zm = 0.0;
        this.kb = 0.0;
        this.mz = 0.0;
        this.pwljl = 0.0;
        this.pwlcd = 0.0;
        this.hsfz = 0.0;
        this.ljv = 0;
        this.qf = 0.0;
        this.hs = 0.0;
        this.fsmz = 0.0;
        this.fskbcd = 0.0;
        this.fskbjl = 0.0;
        this.hsjv = 0.0;
        this.bhws = 0.0;
        this.lsf = 0;
        this.lsd = 0;
        this.lsh = 0;
        this.lss = 0;
        this.ltf = 0;
        this.ltd = 0;
        this.lth = 0;
        this.lts = 0;
        this.tl = 0.0;
        this.tf = 0.0;
        this.ts = 0.0;
        this.th = 0.0;
        this.nqyy = 0.0;
        this.lxzt = 0;
        this.jlss = 0;
        this.jlmh = 0;
        this.jlgh = 0;
        this.jlyw = 0;
        this.tempJlss = 0;
        this.tempJlmh = 0;
        this.tempJlgh = 0;
        this.tempJlyw = 0;
        this.yyhomax = 0.0;
        this.fyhomax = 0.0;
        this.tzcf = 0.0;
        this.jtpa = false;
        this.cd = 0;
        this.jtcl = false;
        this.fhft = false;
        this.ymjr = false;
        this.fbgs = true;
        this.param = "";
        this.fskbbj = false;
        this.yijibiming = true;
        this.fzSum = Integer.valueOf(0);
        this.isHS = false;
        this.skill_count_z = 0;
        this.skill_count_s = 0;
        this.skill_count_g = 0;
        this.skill_count_p = 0;
        this.beatenNum = 0;
        this.skill_ss_tmp_mp = new HashMap<>();
        this.skill_ss_mp = new HashMap<>();
        this.id = 1;
        this.camp = 0;
        this.hp = 1;
        this.hp_z = 1;
        if (isHaizi) {
            this.man = man.getIntValue("MAN");
            this.type = 4;
            this.model = man.getString("MODEL");
            this.skin = man.getString("SKIN");
            this.manname = man.getString("NAME");
            this.skills = new ArrayList<>();
            JSONArray jineng = man.getJSONArray("FASHU");
            for (int i = 0; i < jineng.size(); ++i) {
                int id = jineng.getIntValue(i);
                if (id != 1 && id != 2 && id != 3) {
                    Talent talent = GameServer.getTalent(id);
                    if (talent != null) {
                        int lvl = 10;
                        FightingSkill fightingSkill = new FightingSkill(talent, lvl);
                        this.addSkill(fightingSkill);
                    }
                }
            }
        } else {
            this.id = 1;
            this.hp = 1;
            this.hp_z = 1;
            this.type = 3;
            this.model = man.getString("MODEL");
            this.skin = man.getString("SKIN");
            this.manname = man.getString("NAME");
            this.camp = 0;
            this.man = man.getIntValue("MAN");
            this.sp = man.getIntValue("SP");
            this.huoyue = man.getDoubleValue("HUOYUE") * ((xs / 5.0 < 1.0) ? 1.0 : (xs / 5.0));
            this.qihe = man.getLongValue("QIHE");
            this.shanghai = man.getDoubleValue("SHANGHAI") * ((xs / 4.0 < 1.0) ? 1.0 : (xs / 4.0));
            this.kangluobao = man.getDoubleValue("KANGLUOBAO");
            this.yuanzhu = man.getDoubleValue("YUANZHU");
            this.skills = new ArrayList<>();
            JSONArray jineng = man.getJSONArray("FASHU");
            for (int i = 0; i < jineng.size(); ++i) {
                Skill skill = GameServer.getSkill(jineng.getString(i));
                if (skill != null) {
                    FightingSkill fightingSkill2 = new FightingSkill(skill, 50, 1, 0.0, 0L, 0);
                    this.addSkill(fightingSkill2);
                }
            }
        }
        this.addStates = new ArrayList<>();
    }

    public ManData(JSONObject shou, double xs) {
        this.QHFY = false;
        this.lingbaoshanxian = 0;
        this.lingbaotianfujineng = 0;
        this.yinian = new ArrayList<>();
        this.xingtai = 0;
        this.QKZT = false;
        this.bianshen = false;
        this.consumedNq = 0;
        this.spellSum = 0;
        this.yqz = 149;
        this.nqz = 30;
        this.nqzad = 0;
        this.methodExecuted = false;
        this.HF = 0;
        this.zy = 0;
        this.lzy = 0;
        this.sfPet = "0";
        this.HZT = new String[]{"赤芒", "金石", "青峰"};
        this.fmsld = Integer.valueOf(0);
        this.fmsld2 = Integer.valueOf(0);
        this.famencs = 0;
        this.huiHeFamencs = 0;
        this.attackDie = 0;
        this.ewhuoyue = 0;
        this.ewshanghai = 0;
        this.ewkangluobao = 0;
        this.ewyuanzhu = 0;
        this.dffmsld = 0;
        this.fmFHHT = 0;
        this.nrlrjs = 0;
        this.nrjycz = 0;
        this.nrfhht = 0;
        this.fmshfs = 0;
        this.zm = 0.0;
        this.kb = 0.0;
        this.mz = 0.0;
        this.pwljl = 0.0;
        this.pwlcd = 0.0;
        this.hsfz = 0.0;
        this.ljv = 0;
        this.qf = 0.0;
        this.hs = 0.0;
        this.fsmz = 0.0;
        this.fskbcd = 0.0;
        this.fskbjl = 0.0;
        this.hsjv = 0.0;
        this.bhws = 0.0;
        this.lsf = 0;
        this.lsd = 0;
        this.lsh = 0;
        this.lss = 0;
        this.ltf = 0;
        this.ltd = 0;
        this.lth = 0;
        this.lts = 0;
        this.tl = 0.0;
        this.tf = 0.0;
        this.ts = 0.0;
        this.th = 0.0;
        this.nqyy = 0.0;
        this.lxzt = 0;
        this.jlss = 0;
        this.jlmh = 0;
        this.jlgh = 0;
        this.jlyw = 0;
        this.tempJlss = 0;
        this.tempJlmh = 0;
        this.tempJlgh = 0;
        this.tempJlyw = 0;
        this.yyhomax = 0.0;
        this.fyhomax = 0.0;
        this.tzcf = 0.0;
        this.jtpa = false;
        this.cd = 0;
        this.jtcl = false;
        this.fhft = false;
        this.ymjr = false;
        this.fbgs = true;
        this.param = "";
        this.fskbbj = false;
        this.yijibiming = true;
        this.fzSum = Integer.valueOf(0);
        this.isHS = false;
        this.skill_count_z = 0;
        this.skill_count_s = 0;
        this.skill_count_g = 0;
        this.skill_count_p = 0;
        this.beatenNum = 0;
        this.skill_ss_tmp_mp = new HashMap<>();
        this.skill_ss_mp = new HashMap<>();
        this.yqz = 149;
        this.nqz = 30;
        this.id = 1;
        this.manname = shou.getString("NAME");
        this.sr = 1;
        this.zs = shou.getIntValue("ZHUANSHENG");
        this.lvl = shou.getIntValue("DENGJI");
        this.model = shou.getString("MODEL");
        this.skin = shou.getString("SKIN");
        this.lingxi = shou.getString("LINGXI");
        this.type = 2;
        this.camp = 0;
        this.man = shou.getIntValue("MAN");
        this.States = 0;
        this.hp = (int) ((double) shou.getIntValue("HP") * xs);
        this.hp_z = this.hp;
        this.mp = (int) ((double) shou.getIntValue("MP") * xs);
        this.mp_z = this.mp;
        this.ap = (int) ((double) shou.getIntValue("AP") * xs);
        this.sp = (int) ((double) shou.getIntValue("SP") * xs / 3.0);
        this.Quality = (Ql) GsonUtil.getGsonUtil().getgson().fromJson(shou.getString("KANG"), Ql.class);
        this.bearSkills = new ArrayList<>();
        this.huoyue = (double) shou.getIntValue("HUOYUE");
        this.shanghai = (double) shou.getIntValue("SHANGHAI");
        this.kangluobao = (double) shou.getIntValue("KANGLUOBAO");
        this.yuanzhu = (double) shou.getIntValue("YUANZHU");
        this.skills = new ArrayList<>();
        this.addStates = new ArrayList<>();
        JSONArray jineng = shou.getJSONArray("FASHU");
        for (int i = 0; i < jineng.size(); ++i) {
            Skill skill = GameServer.getSkill(jineng.getString(i));
            if (skill != null) {
                FightingSkill fightingSkill = new FightingSkill(skill, this.lvl, 3, xs * 100.0, 2000000L, 2000);
                if (this.id >= 100 && this.id <= 104) {
                    fightingSkill.setSkillsum(10);
                    fightingSkill.setSkillcontinued(100);
                    if (this.id == 104) {
                        fightingSkill.setSkillhurt(fightingSkill.getSkillhurt() + 300000.0);
                    }
                }
                fightingSkill.setSkillblue(0);
                this.addSkill(fightingSkill);
                if (skill.getSkillid() == 11006 || skill.getSkillid() == 11007 || skill.getSkillid() == 11010 || skill.getSkillid() == 11012 || skill.getSkillid() == 11013 || skill.getSkillid() == 11014 || skill.getSkillid() == 11015 || skill.getSkillid() == 11017 || skill.getSkillid() == 11018 || skill.getSkillid() == 11019 || skill.getSkillid() == 11025) {
                    AddState addState = new AddState();
                    addState.setType(1);
                    addState.setStatename(skill.getSkillid() + "");
                    addState.setStateEffect((double) skill.getSkilllevel());
                    addState.setSurplus(3);
                    this.addStates.add(addState);
                }
            }
        }
    }

    public ManData(int id, String name, JSONObject ren, double xs) {
        this.QHFY = false;
        this.lingbaoshanxian = 0;
        this.lingbaotianfujineng = 0;
        this.yinian = new ArrayList<>();
        this.xingtai = 0;
        this.QKZT = false;
        this.bianshen = false;
        this.consumedNq = 0;
        this.spellSum = 0;
        this.yqz = 149;
        this.nqz = 30;
        this.nqzad = 0;
        this.methodExecuted = false;
        this.HF = 0;
        this.zy = 0;
        this.lzy = 0;
        this.sfPet = "0";
        this.HZT = new String[]{"赤芒", "金石", "青峰"};
        this.fmsld = Integer.valueOf(0);
        this.fmsld2 = Integer.valueOf(0);
        this.famencs = 0;
        this.huiHeFamencs = 0;
        this.attackDie = 0;
        this.ewhuoyue = 0;
        this.ewshanghai = 0;
        this.ewkangluobao = 0;
        this.ewyuanzhu = 0;
        this.dffmsld = 0;
        this.fmFHHT = 0;
        this.nrlrjs = 0;
        this.nrjycz = 0;
        this.nrfhht = 0;
        this.fmshfs = 0;
        this.zm = 0.0;
        this.kb = 0.0;
        this.mz = 0.0;
        this.pwljl = 0.0;
        this.pwlcd = 0.0;
        this.hsfz = 0.0;
        this.ljv = 0;
        this.qf = 0.0;
        this.hs = 0.0;
        this.fsmz = 0.0;
        this.fskbcd = 0.0;
        this.fskbjl = 0.0;
        this.hsjv = 0.0;
        this.bhws = 0.0;
        this.lsf = 0;
        this.lsd = 0;
        this.lsh = 0;
        this.lss = 0;
        this.ltf = 0;
        this.ltd = 0;
        this.lth = 0;
        this.lts = 0;
        this.tl = 0.0;
        this.tf = 0.0;
        this.ts = 0.0;
        this.th = 0.0;
        this.nqyy = 0.0;
        this.lxzt = 0;
        this.jlss = 0;
        this.jlmh = 0;
        this.jlgh = 0;
        this.jlyw = 0;
        this.tempJlss = 0;
        this.tempJlmh = 0;
        this.tempJlgh = 0;
        this.tempJlyw = 0;
        this.yyhomax = 0.0;
        this.fyhomax = 0.0;
        this.tzcf = 0.0;
        this.jtpa = false;
        this.cd = 0;
        this.jtcl = false;
        this.fhft = false;
        this.ymjr = false;
        this.fbgs = true;
        this.param = "";
        this.fskbbj = false;
        this.yijibiming = true;
        this.fzSum = Integer.valueOf(0);
        this.isHS = false;
        this.skill_count_z = 0;
        this.skill_count_s = 0;
        this.skill_count_g = 0;
        this.skill_count_p = 0;
        this.beatenNum = 0;
        this.skill_ss_tmp_mp = new HashMap<>();
        this.skill_ss_mp = new HashMap<>();
        this.yqz = 149;
        this.nqz = 30;
        this.id = id;
        this.manname = name;
        this.sr = 1;
        this.zs = ren.getIntValue("ZHUANSHENG");
        this.lvl = ren.getIntValue("DENGJI");
        this.model = ren.getString("MODEL");
        this.skin = ren.getString("SKIN");
        this.type = 2;
        this.camp = 0;
        this.man = ren.getIntValue("MAN");
        this.States = 0;
        this.hp = (int) ((double) ren.getIntValue("HP") * xs);
        this.hp_z = this.hp;
        this.mp = (int) ((double) ren.getIntValue("MP") * xs);
        this.mp_z = this.mp;
        this.ap = (int) ((double) ren.getIntValue("AP") * xs);
        this.sp = (int) ((double) ren.getIntValue("SP") * xs / 2.5);
        this.msg = ((this.man == 2) ? "#18现在的我们拥有很强的力量，打不过的话等一刻钟后再来尝试吧。" : "");
        this.Quality = (Ql) GsonUtil.getGsonUtil().getgson().fromJson(ren.getString("KANG"), Ql.class);
        this.bearSkills = new ArrayList<>();
        this.skills = new ArrayList<>();
        this.addStates = new ArrayList<>();
        JSONArray jineng = ren.getJSONArray("FASHU");
        List<Integer> jinenglist = new ArrayList<>();
        for (int i = 0; i < jineng.size(); ++i) {
            Skill skill = GameServer.getSkill(jineng.getString(i));
            if (skill != null) {
                FightingSkill fightingSkill = new FightingSkill(skill, this.lvl, 3, (double) (this.zs * 5000 + 10000), 2000000L, (int) xs);
                if (id >= 100 && id <= 104) {
                    fightingSkill.setSkillsum(10);
                    fightingSkill.setSkillcontinued(100);
                    if (id == 104) {
                        fightingSkill.setSkillhurt(fightingSkill.getSkillhurt() + 300000.0);
                    }
                }
                fightingSkill.setSkillblue(0);
                this.addSkill(fightingSkill);
                if (fightingSkill.getSkillbeidong() == 0) {
                    jinenglist.add(Integer.valueOf(fightingSkill.getSkillid()));
                }
            }
        }
    }

    public ManData(Monster fightMonsterBean, int camp, int man, int lvl) {
        this.QHFY = false;
        this.lingbaoshanxian = 0;
        this.lingbaotianfujineng = 0;
        this.yinian = new ArrayList<>();
        this.xingtai = 0;
        this.QKZT = false;
        this.bianshen = false;
        this.consumedNq = 0;
        this.spellSum = 0;
        this.yqz = 149;
        this.nqz = 30;
        this.nqzad = 0;
        this.methodExecuted = false;
        this.HF = 0;
        this.zy = 0;
        this.lzy = 0;
        this.sfPet = "0";
        this.HZT = new String[]{"赤芒", "金石", "青峰"};
        this.fmsld = Integer.valueOf(0);
        this.fmsld2 = Integer.valueOf(0);
        this.famencs = 0;
        this.huiHeFamencs = 0;
        this.attackDie = 0;
        this.ewhuoyue = 0;
        this.ewshanghai = 0;
        this.ewkangluobao = 0;
        this.ewyuanzhu = 0;
        this.dffmsld = 0;
        this.fmFHHT = 0;
        this.nrlrjs = 0;
        this.nrjycz = 0;
        this.nrfhht = 0;
        this.fmshfs = 0;
        this.zm = 0.0;
        this.kb = 0.0;
        this.mz = 0.0;
        this.pwljl = 0.0;
        this.pwlcd = 0.0;
        this.hsfz = 0.0;
        this.ljv = 0;
        this.qf = 0.0;
        this.hs = 0.0;
        this.fsmz = 0.0;
        this.fskbcd = 0.0;
        this.fskbjl = 0.0;
        this.hsjv = 0.0;
        this.bhws = 0.0;
        this.lsf = 0;
        this.lsd = 0;
        this.lsh = 0;
        this.lss = 0;
        this.ltf = 0;
        this.ltd = 0;
        this.lth = 0;
        this.lts = 0;
        this.tl = 0.0;
        this.tf = 0.0;
        this.ts = 0.0;
        this.th = 0.0;
        this.nqyy = 0.0;
        this.lxzt = 0;
        this.jlss = 0;
        this.jlmh = 0;
        this.jlgh = 0;
        this.jlyw = 0;
        this.tempJlss = 0;
        this.tempJlmh = 0;
        this.tempJlgh = 0;
        this.tempJlyw = 0;
        this.yyhomax = 0.0;
        this.fyhomax = 0.0;
        this.tzcf = 0.0;
        this.jtpa = false;
        this.cd = 0;
        this.jtcl = false;
        this.fhft = false;
        this.ymjr = false;
        this.fbgs = true;
        this.param = "";
        this.fskbbj = false;
        this.yijibiming = true;
        this.fzSum = Integer.valueOf(0);
        this.isHS = false;
        this.skill_count_z = 0;
        this.skill_count_s = 0;
        this.skill_count_g = 0;
        this.skill_count_p = 0;
        this.beatenNum = 0;
        this.skill_ss_tmp_mp = new HashMap<>();
        this.skill_ss_mp = new HashMap<>();
        this.yqz = 149;
        this.nqz = 30;
        this.bearSkills = new ArrayList<>();
        if (StringUtils.isNotEmpty(fightMonsterBean.getMonsterpet())) {
            this.speciesid = new BigDecimal(fightMonsterBean.getMonsterpet());
        }
        if (StringUtils.isNotBlank(fightMonsterBean.getMonsterget()) && fightMonsterBean.getMonsterget().equals("1")) {
            this.isHS = true;
        }
        double grow = 5.0;
        double chuhp = 20000.0;
        double chump = 10000.0;
        double chuap = 1000.0;
        double chusp = 1000.0;
        int chulvl = 180;
        String ColorScheme = null;
        try {
            this.id = fightMonsterBean.getMonsterid();
            this.sr = fightMonsterBean.getMonstersr();
            grow = fightMonsterBean.getMonstergrow();
            chuhp = (double) fightMonsterBean.getMonsterhp();
            chump = (double) fightMonsterBean.getMonstermp();
            chuap = (double) fightMonsterBean.getMonsterap();
            chusp = (double) fightMonsterBean.getMonstersp();
            chulvl = ((lvl == 0) ? fightMonsterBean.getMonsterlvl() : lvl);
            if (!fightMonsterBean.getMonsterpet().equals("")) {
                this.zs = Integer.parseInt(fightMonsterBean.getMonsterpet());
            }
            if (fightMonsterBean.getColor() != null && !fightMonsterBean.getColor().equals("")) {
                ColorScheme = fightMonsterBean.getColor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.model = fightMonsterBean.getMonsterskin();
        try {
            if (fightMonsterBean.getMonstername().contains("星")) {
                String s = (String) Sepcies_MixDeal.localNameMap.get(Integer.valueOf(Integer.parseInt(fightMonsterBean.getMonsterskin())));
                String[] strings = this.GetWeapons(s);
                long weaponSkin = this.getWq(strings[0]);
                long se = (long) Integer.parseInt(fightMonsterBean.getMonsterskin());
                if ((weaponSkin == 1L && se == 20001L) || (weaponSkin == 2L && se == 20001L) || (weaponSkin == 1L && se == 20002L) || (weaponSkin == 3L && se == 20002L) || (weaponSkin == 4L && se == 20003L) || (weaponSkin == 5L && se == 20003L) || (weaponSkin == 9L && se == 20004L) || (weaponSkin == 8L && se == 20004L) || (weaponSkin == 10L && se == 20005L) || (weaponSkin == 7L && se == 20005L) || (weaponSkin == 10L && se == 20006L) || (weaponSkin == 12L && se == 20006L) || (weaponSkin == 1L && se == 20007L) || (weaponSkin == 5L && se == 20007L) || (weaponSkin == 1L && se == 20008L) || (weaponSkin == 10L && se == 20008L) || (weaponSkin == 2L && se == 20009L) || (weaponSkin == 6L && se == 20009L) || (weaponSkin == 8L && se == 20010L) || (weaponSkin == 1L && se == 20010L) || (weaponSkin == 12L && se == 21001L) || (weaponSkin == 7L && se == 21001L) || (weaponSkin == 10L && se == 21002L) || (weaponSkin == 13L && se == 21002L) || (weaponSkin == 10L && se == 21003L) || (weaponSkin == 12L && se == 21003L) || (weaponSkin == 9L && se == 21004L) || (weaponSkin == 10L && se == 21004L) || (weaponSkin == 7L && se == 21005L) || (weaponSkin == 1L && se == 21005L) || (weaponSkin == 14L && se == 21006L) || (weaponSkin == 8L && se == 21006L) || (weaponSkin == 12L && se == 21007L) || (weaponSkin == 4L && se == 21007L) || (weaponSkin == 10L && se == 21008L) || (weaponSkin == 11L && se == 21008L) || (weaponSkin == 10L && se == 21009L) || (weaponSkin == 4L && se == 21009L) || (weaponSkin == 14L && se == 21010L) || (weaponSkin == 9L && se == 21010L) || (weaponSkin == 12L && se == 2200L) || (weaponSkin == 3L && se == 22001L) || (weaponSkin == 14L && se == 22002L) || (weaponSkin == 1L && se == 22002L) || (weaponSkin == 7L && se == 22003L) || (weaponSkin == 14L && se == 22003L) || (weaponSkin == 10L && se == 22004L) || (weaponSkin == 5L && se == 22004L) || (weaponSkin == 7L && se == 22005L) || (weaponSkin == 16L && se == 22005L) || (weaponSkin == 1L && se == 22006L) || (weaponSkin == 12L && se == 22006L) || (weaponSkin == 12L && se == 22007L) || (weaponSkin == 14L && se == 22007L) || (weaponSkin == 11L && se == 22008L) || (weaponSkin == 16L && se == 22008L) || (weaponSkin == 1L && se == 22009L) || (weaponSkin == 13L && se == 22009L) || (weaponSkin == 16L && se == 22010L) || (weaponSkin == 17L && se == 22010L) || (weaponSkin == 1L && se == 23001L) || (weaponSkin == 10L && se == 23001L) || (weaponSkin == 12L && se == 23002L) || (weaponSkin == 5L && se == 23002L) || (weaponSkin == 13L && se == 23003L) || (weaponSkin == 6L && se == 23003L) || (weaponSkin == 9L && se == 23004L) || (weaponSkin == 8L && se == 23004L) || (weaponSkin == 17L && se == 23005L) || (weaponSkin == 11L && se == 23005L) || (weaponSkin == 11L && se == 23006L) || (weaponSkin == 16L && se == 23006L) || (weaponSkin == 1L && se == 24001L) || (weaponSkin == 6L && se == 24001L) || (weaponSkin == 12L && se == 24002L) || (weaponSkin == 10L && se == 24002L) || (weaponSkin == 18L && se == 24003L) || (weaponSkin == 11L && se == 24003L) || (weaponSkin == 9L && se == 24004L) || (weaponSkin == 3L && se == 24004L) || (weaponSkin == 18L && se == 24005L) || (weaponSkin == 12L && se == 24005L) || (weaponSkin == 1L && se == 24006L) || (weaponSkin == 17L && se == 24006L)) {
                    weaponSkin += 18L;
                }
                String eSkin = (weaponSkin << 32 | se) + "";
                this.model = eSkin;
            }
        } catch (Exception ex) {
        }
        this.skin = this.model;
        this.manname = fightMonsterBean.getMonstername();
        this.type = 2;
        this.camp = camp;
        this.man = man;
        this.States = 0;
        this.lvl = chulvl;
        this.hp = PetGrade.getRoleValue(chulvl, chulvl * 8, grow, (int) chuhp, 0);
        this.hp_z = this.hp;
        this.mp = PetGrade.getRoleValue(chulvl, chulvl * 4, grow, (int) chump, 1);
        this.mp_z = this.mp;
        this.ap = PetGrade.getRoleValue(chulvl, chulvl * 3, grow, (int) chuap, 2);
        this.sp = PetGrade.getRoleValue(chulvl, chulvl, grow, (int) chusp, 3);
        this.addStates = new ArrayList<>();
        boolean isTX = false;
        this.skills = new ArrayList<>();
        if (fightMonsterBean.getMonsterskill() != null && !fightMonsterBean.getMonsterskill().equals("")) {
            String[] v = fightMonsterBean.getMonsterskill().split("\\|");
            double sld = (double) (this.lvl * 125);
            if (this.id >= 2000 && this.id <= 2105) {
                sld = 1.0;
            }
            for (int i = 0; i < v.length; ++i) {
                try {
                    if (!v[i].equals("") && !v[i].equals("")) {
                        String newid = v[i].split("=")[0];
                        int id = Integer.parseInt(v[i].split("=")[0]);
                        if (id == 100) {
                            newid = "1030";
                        } else if (id == 101) {
                            newid = "1035";
                        } else if (id == 102) {
                            newid = "1205";
                        } else if (id == 103) {
                            newid = "1203";
                        } else if (id == 104) {
                            newid = "1056";
                        }
                        int skilltype = this.skilltype(Integer.parseInt(newid));
                        if (skilltype != 1) {
                            if (skilltype == 3) {
                                if (v[i].split("=").length > 1) {
                                    CreepsMixdeal.addNeiDanSkill(this, id, Integer.parseInt(v[i].split("=")[1]), Integer.parseInt(v[i].split("=")[2]));
                                    continue;
                                }
                            } else if (skilltype == 4) {
                                isTX = true;
                            } else if (skilltype == 1207) {
                                for (int j = 0; j < this.skills.size(); ++j) {
                                    FightingSkill skill2 = (FightingSkill) this.skills.get(j);
                                    if (skill2.getSkillid() == 1831 || skill2.getSkillid() == 1833) {
                                        skill2.setSkillhurt(skill2.getSkillhurt() + 15.0);
                                    }
                                }
                                continue;
                            }
                            Skill skill3 = GameServer.getSkill(newid);
                            if (skill3 != null) {
                                FightingSkill fightingSkill = new FightingSkill(skill3, this.lvl, 3, sld, 2000000L, 20);
                                if (id >= 100 && id <= 104) {
                                    fightingSkill.setSkillsum(10);
                                    fightingSkill.setSkillcontinued(100);
                                    if (id == 104) {
                                        fightingSkill.setSkillhurt(fightingSkill.getSkillhurt() + 300000.0);
                                    }
                                }
                                fightingSkill.setSkillblue(0);
                                this.addSkill(fightingSkill);
                            }
                        }
                    }
                } catch (NumberFormatException e2) {
                    e2.printStackTrace();
                }
            }
        }
        this.msg = fightMonsterBean.getMsg();
        try {
            (this.Quality = fightMonsterBean.getQl()).setKzml(this.Quality.getKzml() + CustomFunction.XS((long) this.hp_z, 0.4) + 5.0);
            if (fightMonsterBean.getMonsterai() != null && !fightMonsterBean.getMonsterai().equals("")) {
                String[] v = fightMonsterBean.getMonsterai().split("\\|");
                for (int k = v.length - 1; k >= 0; --k) {
                    this.addAI(v[k]);
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(this.model);
        buffer.append("_1_1");
        if (ColorScheme != null) {
            buffer.append("_");
            buffer.append(ColorScheme);
        }
        if (isTX) {
            buffer.append("&jx_6_-1");
        }
        this.model = buffer.toString();
    }

    public String[] GetWeapons(String rolename) {
        if (rolename.equals("纯阳子")) {
            return new String[]{"扇", "书"};
        }
        if (rolename.equals("夺命妖")) {
            return new String[]{"刀", "幡"};
        }
        if (rolename.equals("飞剑侠")) {
            return new String[]{"剑", "拳套"};
        }
        if (rolename.equals("飞燕女")) {
            return new String[]{"鞭", "钩"};
        }
        if (rolename.equals("骨精灵")) {
            return new String[]{"剑", "棍"};
        }
        if (rolename.equals("红拂女")) {
            return new String[]{"剑", "鞭"};
        }
        if (rolename.equals("狐美人")) {
            return new String[]{"爪", "鞭"};
        }
        if (rolename.equals("虎头怪")) {
            return new String[]{"棍", "枪"};
        }
        if (rolename.equals("混天魔")) {
            return new String[]{"刀", "斧头"};
        }
        if (rolename.equals("祭剑魂")) {
            return new String[]{"刀", "剑"};
        }
        if (rolename.equals("剑侠客")) {
            return new String[]{"剑", "锤"};
        }
        if (rolename.equals("精灵仙")) {
            return new String[]{"刀", "拳套"};
        }
        if (rolename.equals("镜花影")) {
            return new String[]{"剑", "飘带"};
        }
        if (rolename.equals("九尾狐")) {
            return new String[]{"钩", "爪"};
        }
        if (rolename.equals("巨魔王")) {
            return new String[]{"刀", "枪"};
        }
        if (rolename.equals("绝影魔")) {
            return new String[]{"刀", "枪"};
        }
        if (rolename.equals("猎魂引")) {
            return new String[]{"枪", "拳套"};
        }
        if (rolename.equals("玲珑女")) {
            return new String[]{"鞭", "刀"};
        }
        if (rolename.equals("龙战将")) {
            return new String[]{"棍", "爪"};
        }
        if (rolename.equals("媚灵狐")) {
            return new String[]{"刀", "双环"};
        }
        if (rolename.equals("猛壮士")) {
            return new String[]{"斧头", "拳套"};
        }
        if (rolename.equals("墨衣行")) {
            return new String[]{"鞭", "钩"};
        }
        if (rolename.equals("南冠客")) {
            return new String[]{"刀", "剑"};
        }
        if (rolename.equals("霓裳仙")) {
            return new String[]{"灯笼", "飘带"};
        }
        if (rolename.equals("逆天魔")) {
            return new String[]{"斧头", "棍"};
        }
        if (rolename.equals("俏千金")) {
            return new String[]{"枪", "刀"};
        }
        if (rolename.equals("青阳使")) {
            return new String[]{"枪", "棍"};
        }
        if (rolename.equals("神天兵")) {
            return new String[]{"锤", "枪"};
        }
        if (rolename.equals("神秀生")) {
            return new String[]{"棍", "剑"};
        }
        if (rolename.equals("霜月灵")) {
            return new String[]{"刀", "钩"};
        }
        if (rolename.equals("无崖子")) {
            return new String[]{"幡", "书"};
        }
        if (rolename.equals("武尊神")) {
            return new String[]{"枪", "爪"};
        }
        if (rolename.equals("舞天姬")) {
            return new String[]{"棍", "飘带"};
        }
        if (rolename.equals("逍遥生")) {
            return new String[]{"剑", "扇"};
        }
        if (rolename.equals("小蛮妖")) {
            return new String[]{"刀", "钩"};
        }
        if (rolename.equals("玄剑娥")) {
            return new String[]{"剑", "枪"};
        }
        if (rolename.equals("玄天姬")) {
            return new String[]{"双环", "飘带"};
        }
        if (rolename.equals("燕山雪")) {
            return new String[]{"刀", "剑"};
        }
        if (rolename.equals("夜溪灵")) {
            return new String[]{"灯笼", "双环"};
        }
        if (rolename.equals("英女侠")) {
            return new String[]{"刀", "棍"};
        }
        if (rolename.equals("幽梦影")) {
            return new String[]{"双环", "飘带"};
        }
        if (rolename.equals("云中君")) {
            return new String[]{"双环", "飘带"};
        }
        if (rolename.equals("智圣仙")) {
            return new String[]{"浮尘", "剑"};
        }
        if (rolename.equals("紫薇神")) {
            return new String[]{"幡", "剑"};
        }
        if (rolename.equals("沧浪君")) {
            return new String[]{"书", "剑"};
        }
        if (rolename.equals("莫解语")) {
            return new String[]{"灯笼", "剑"};
        }
        if (rolename.equals("龙渊客")) {
            return new String[]{"枪", "刀"};
        }
        if (rolename.equals("忘忧子")) {
            return new String[]{"弓", "双环"};
        }
        if (rolename.equals("骊珠儿")) {
            return new String[]{"锤", "钩"};
        }
        if (rolename.equals("木兰行")) {
            return new String[]{"弓", "枪"};
        }
        return null;
    }

    public long getWq(String weapon) {
        long type = -1L;
        if (weapon.equals("剑")) {
            type = 1L;
        } else if (weapon.equals("扇")) {
            type = 2L;
        } else if (weapon.equals("锤")) {
            type = 3L;
        } else if (weapon.equals("斧头")) {
            type = 4L;
        } else if (weapon.equals("拳套")) {
            type = 5L;
        } else if (weapon.equals("书")) {
            type = 6L;
        } else if (weapon.equals("棍")) {
            type = 7L;
        } else if (weapon.equals("鞭")) {
            type = 8L;
        } else if (weapon.equals("钩")) {
            type = 9L;
        } else if (weapon.equals("刀")) {
            type = 10L;
        } else if (weapon.equals("双环")) {
            type = 11L;
        } else if (weapon.equals("枪")) {
            type = 12L;
        } else if (weapon.equals("幡")) {
            type = 13L;
        } else if (weapon.equals("爪")) {
            type = 14L;
        } else if (weapon.equals("浮尘")) {
            type = 15L;
        } else if (weapon.equals("飘带")) {
            type = 16L;
        } else if (weapon.equals("灯笼")) {
            type = 17L;
        } else if (weapon.equals("弓")) {
            type = 18L;
        }
        return type;
    }

    public int skilltype(int id) {
        if (id == 3034 || id == 3040 || (id >= 1800 && id <= 1803) || (id >= 1810 && id <= 1819) || id == 1208 || id == 1209 || id == 1213 || id == 1222 || id == 1226 || (id >= 1845 && id <= 1846) || (id >= 1855 && id <= 1857) || (id >= 1859 && id <= 1860) || id == 1235) {
            return 1;
        }
        if (id >= 1820 && id <= 1827) {
            return 2;
        }
        if (id >= 1511 && id <= 1524) {
            return 3;
        }
        if (id >= 1300 && id <= 1334) {
            return 4;
        }
        if (id == 1207) {
            return 1207;
        }
        return 0;
    }

    public void addSkill(FightingSkill skill) {
        this.addSkill(skill, 1);
    }

    public void addSkill(FightingSkill skill, int currentRound) {
        if (this.skills == null) {
            this.skills = new ArrayList<>();
        }
        if (skill.getSkillid() == 1289) {
            return;
        }
        if (skill.getSkillid() == 1838 || skill.getSkillid() == 1333 || skill.getSkillid() == 1334 || skill.getSkillid() == 7027) {
            int surplus = 3 - (currentRound - 1);
            if (surplus > 0) {
                AddState addState = new AddState();
                addState.setStatename("冷却");
                addState.setStateEffect((double) skill.getSkillid());
                addState.setSurplus(surplus);
                this.addStates.add(addState);
            }
        }
        if (skill.getSkillid() == 1606 || skill.getSkillid() == 1608 || skill.getSkillid() == 1611 || skill.getSkillid() == 1612) {
            int surplus = 5 - (currentRound - 1);
            if (surplus > 0) {
                AddState addState = new AddState();
                addState.setStatename("冷却");
                addState.setStateEffect((double) skill.getSkillid());
                addState.setSurplus(surplus);
                this.addStates.add(addState);
            }
        }
        if (skill.getSkillid() == 7019) {
            int surplus = 10 - (currentRound - 1);
            if (surplus > 0) {
                AddState addState = new AddState();
                addState.setStatename("冷却");
                addState.setStateEffect((double) skill.getSkillid());
                addState.setSurplus(surplus);
                this.addStates.add(addState);
            }
        }
        if (skill.getSkillid() == 6022 || skill.getSkillid() == 6024) {
            AddState addState2 = new AddState(skill.getSkillid() + "", 0.0, 9999);
            this.addStates.add(addState2);
        }

        if (skill.getSkillid() == 6045) {
            AddState addState = new AddState(skill.getSkillid() + "", 1.50, 9999);
            this.addStates.add(addState);
        }
        if (skill.getSkillid() == 1865) {
            skill.setSkillbeidong(1);
            skill.setSkilltype(TypeUtil.BB_QZ);
        }
        if (skill.getSkillid() == 9223 || skill.getSkillid() == 9342) {
            FightingSkill skill2 = null;
            if (skill.getSkillid() == 9223) {
                skill2 = this.getSkillId(1040);
            } else if (skill.getSkillid() == 9342) {
                skill2 = this.getSkillId(1075);
            }
            if (skill2 != null) {
                int bule = skill2.getSkillblue();
                bule = (int) ((double) bule - skill.getSkillhurt());
                if (bule < 0) {
                    bule = 0;
                }
                skill2.setSkillblue(bule);
            }
            return;
        } else {
            this.skills.add(skill);
            return;
        }
    }

    public FightingSkill getSkillId(int id) {
        if (this.skills == null) {
            return null;
        }
        for (int i = this.skills.size() - 1; i >= 0; --i) {
            if (((FightingSkill) this.skills.get(i)).getSkillid() == id) {
                return (FightingSkill) this.skills.get(i);
            }
        }
        return null;
    }

    public void neidang(String type, int lvl) {
        int i = 0;
        while (i < this.addStates.size()) {
            if (((AddState) this.addStates.get(i)).getStatename().indexOf(type) != -1) {
                if (((AddState) this.addStates.get(i)).getStateEffect() < (double) lvl) {
                    ((AddState) this.addStates.get(i)).setStatename(type + lvl);
                    ((AddState) this.addStates.get(i)).setStateEffect((double) lvl);
                }
                return;
            } else {
                ++i;
            }
        }
        AddState addState = new AddState(type + lvl, (double) lvl, 9999);
        this.addStates.add(addState);
    }

    public void addAI(String v) {
        if (this.ais == null) {
            this.ais = new ArrayList<>();
        }
        String[] vs = v.split("=");
        if (vs[0].equals("状态")) {
            vs = vs[1].split("-");
            if (vs[0].equals("物理狂暴")) {
                this.sp += 600;
                getZDSKILL(1831, 50000000);
                getZDSKILL(1833, 50000000);
            } else if (vs[0].equals("仙法狂暴")) {
                this.sp += 600;
                this.Quality.kuangbao(1);
            }
            this.addStates.add(new AddState(vs[0], 0, Integer.parseInt(vs[1])));
        } else if (vs[0].equals("条件")) {
            // 条件=死亡-1000&死亡-1001=逃跑(救人-1001)(狂暴)
            List<AICondition> aiConditions = new ArrayList<>();
            String[] vt = vs[1].split("&");
            for (int j = 0; j < vt.length; j++) {
                String[] vts = vt[j].split("-");
                if (vts[0].equals("概率")) {
                    aiConditions.add(new AICondition(AI.AI_BILITY, Integer
                            .parseInt(vts[1])));
                } else if (vts[0].equals("死亡")) {
                    if (vts[1].startsWith("召唤")) {
                        aiConditions.add(new AICondition(AI.AI_ROUND, vts[1] + "=" + vts[2]));
                    } else {
                        aiConditions.add(new AICondition(AI.AI_DEATH, Integer
                                .parseInt(vts[1])));
                    }
                } else if (vts[0].equals("未死亡")) {
                    if (vts[1].startsWith("召唤")) {
                        aiConditions.add(new AICondition(AI.AI_ROUND, vts[1] + "=" + vts[2]));
                    } else {
                        aiConditions.add(new AICondition(AI.AI_NODEATH, Integer
                                .parseInt(vts[1])));
                    }
                } else if (vts[0].equals("回合")) {
                    if (vts[1].startsWith("X")) {
                        aiConditions.add(new AICondition(AI.AI_ROUND, vts[1] + "=" + vts[2]));
                    } else {
                        aiConditions.add(new AICondition(AI.AI_ROUND, Integer
                                .parseInt(vts[1])));
                    }

                } else if (vts[0].equals("指定回合")) {
                    aiConditions.add(new AICondition(AI.AI_ZD_ROUND, Integer
                            .parseInt(vts[1])));
                } else if (vts[0].equals("召唤")) {
                    AICondition aiCondition = new AICondition(AI.AI_TYPE_ZH, Integer
                            .parseInt(vts[1]), vs[2]);
                    aiConditions.add(aiCondition);
                    if (vts.length == 3) {
                        if (vts[2].equals("0")) {
                            aiCondition.setB(true);
                        } else if (vts[2].equals("1")) {
                            aiCondition.setB(false);
                        }
                    }

                } else if (vts[0].equals("法术")) {
                    aiConditions.add(new AICondition(AI.AI_SKILL, Integer
                            .parseInt(vts[1])));
                } else if (vts[0].equals("承受法术")) {
                    AICondition aiCondition = new AICondition(AI.AI_BEARSKILL,
                            vts[1]);
                    if (vts.length >= 3) {
                        aiCondition.setY(Integer.parseInt(vts[2]));
                    }
                    aiConditions.add(aiCondition);
                } else if (vts[0].equals("模仿技能")) {
                    AICondition aiCondition = new AICondition(AI.AI_ZDBEARSKILL,
                            vts[1]);
                    if (vts.length >= 3) {
                        aiCondition.setY(Integer.parseInt(vts[2]));
                    }
                    aiConditions.add(aiCondition);
                } else if (vts[0].equals("召唤")) {
                    AICondition aiCondition = new AICondition(AI.AI_TYPE_ZH, Integer
                            .parseInt(vts[1]), vs[2]);
                    aiConditions.add(aiCondition);
                    if (vts.length == 3) {
                        if (vts[2].equals("0")) {
                            aiCondition.setB(true);
                        } else if (vts[2].equals("1")) {
                            aiCondition.setB(false);
                        }
                    }

                } else if (vts[0].equals("携带状态")) {
                    AICondition aiCondition = new AICondition(AI.AI_CARRYSTATE, vts[1]);
                    if (vts.length >= 3) {
                        aiCondition.setY(Integer.parseInt(vts[2]));
                    }
                    aiConditions.add(aiCondition);
                }
            }
            AI ai = null;
            vt = vs[2].split("-");
            if (vt[0].equals("出手状态") || vt[0].equals("回合状态")) {
                int value = 0;
                if (vt.length >= 4) {
                    value = Integer.parseInt(vt[3]);
                }
                ai = new AI(vt[0], vt[1], Integer.parseInt(vt[2]),
                        aiConditions, value);
            } else if (vt[0].equals("模仿技能")) {
                int value = 0;
                if (vt.length >= 4) {
                    value = Integer.parseInt(vt[3]);
                }
                ai = new AI(vt[0], Integer.parseInt(vt[1]), value, aiConditions);
            } else if (vt[0].equals("召唤")) {
//                int value = 0;
//                if (vt.length >= 4) {
//                    value = Integer.parseInt(vt[3]);
//                }
                try {
                    ai = new AI(vt[0], Integer.parseInt(vt[3]), Integer.parseInt(vt[2]), aiConditions);
                } catch (NumberFormatException e) {
                    ai = new AI(vt[0], 987654321, Integer.parseInt(vt[2]), aiConditions, vt[3]);
                }
            } else if (vt[0].equals("变身")) {
                int value = 0;
                if (vt.length >= 4) {
                    value = Integer.parseInt(vt[3]);
                }
                ai = new AI(vt[0], 0, Integer.parseInt(vt[1]), aiConditions);
            } else if (vt[0].equals("降低状态")) {
                int value = 0;
                if (vt.length >= 4) {
                    value = Integer.parseInt(vt[3]);
                }
                ai = new AI(vt[0], Integer.parseInt(vt[1]), Integer.parseInt(vt[2]), aiConditions);
            } else if (vt[0].equals("法宝必中")) {
                int value = 0;
                if (vt.length >= 4) {
                    value = Integer.parseInt(vt[3]);
                }
                ai = new AI(vt[0], Integer.parseInt(vt[1]), Integer.parseInt(vt[2]), aiConditions);
            } else if (vt[0].equals("破除隐身")) {
                int value = 0;
                if (vt.length >= 4) {
                    value = Integer.parseInt(vt[3]);
                }
                ai = new AI(vt[0], Integer.parseInt(vt[1]), Integer.parseInt(vt[2]), aiConditions);
            } else if (vt[0].equals("未死亡")) {
                ai = new AI(vt[0], 0, Integer.parseInt(vt[1]), aiConditions);
                ai.setState(vs[3]);
            } else if (vt[0].equals("指定攻击")) {
                ai = new AI(vt[0], 0, Integer.parseInt(vt[1]), aiConditions);
                ai.setState(vt[3]);
            } else if (vt[0].equals("死亡变身")) {
                int value = 0;
                if (vt.length >= 4) {
                    value = Integer.parseInt(vt[3]);
                }
                ai = new AI(vt[0], 0, Integer.parseInt(vt[1]), aiConditions);
                if (vt.length == 3) {
                    if (vt[2].equals("0")) {
                        ai.getAiConditions().get(0).setB(true);
                    } else if (vt[2].equals("1")) {
                        ai.getAiConditions().get(0).setB(false);
                    }
                }
            } else if (vt.length == 1) {
                ai = new AI(vt[0], 0, 0, aiConditions);
            } else if (vt.length == 2) {
                ai = new AI(vt[0], Integer.parseInt(vt[1]), 0, aiConditions);
            } else if (vt.length == 3) {
                ai = new AI(vt[0], Integer.parseInt(vt[1]),
                        Integer.parseInt(vt[2]), aiConditions);
            }
            if (ai != null) {
                this.ais.add(ai);
            }
        } else if (vs[0].equals("指令")) {
            if (this.ais == null) {
                this.ais = new ArrayList<>();
            }
            this.ais.add(new AI(vs[0], vs));
        } else {
            if (vs.length <= 1) {
                return;
            }
            String[] vs1 = vs[1].split("&");
            if (vs1.length <= 1) {
                return;
            }
            List<AICondition> aiConditions = new ArrayList<>();
            aiConditions.add(new AICondition(AI.AI_BILITY, Integer
                    .parseInt(vs1[0])));
            int aiman = 0;
            int value = Integer.parseInt(vs1[1]);
            if (vs[0].equals("加血")) {
                aiman = this.id;
            } else if (vs[0].equals("救人")) {
                vs[0] = "加血";
            }
            if (this.ais == null) {
                this.ais = new ArrayList<>();
            }
            this.ais.add(new AI(vs[0], aiman, value, aiConditions));
        }
    }

    public FightingSkill skillname(String skillname) {
        for (int i = 0; i < this.skills.size(); ++i) {
            if (this.skills.get(i).getSkillname().equals(skillname)) {
                return this.skills.get(i);
            }
        }
        return null;
    }

    public FightingSkill findRemoveSkill(String skillname) {
        for (int i = 0; i < this.skills.size(); ++i) {
            if (this.skills.get(i).getSkillname().equals(skillname)) {
                FightingSkill skill = (FightingSkill) this.skills.get(i);
                this.skills.remove(i);
                return skill;
            }
        }
        return null;
    }

    public FightingSkill skillId(int id) {
        for (int i = this.skills.size() - 1; i >= 0; --i) {
            if (this.skills.get(i).getSkillid() == id) {
                return this.skills.get(i);
            }
        }
        return null;
    }

    public FightingSkill skillId(String id) {
        return this.skillId(Integer.parseInt(id));
    }

    public FightingSkill skillIds(int... ids) {
        for (int i = this.skills.size() - 1; i >= 0; --i) {
            for (int id : ids) {
                if (this.skills.get(i).getSkillid() == id) {
                    return this.skills.get(i);
                }
            }
        }
        return null;
    }

    public FightingSkill getSkillName(String skillname) {
        for (int i = 0; i < this.skills.size(); ++i) {
            FightingSkill skill = this.skills.get(i);
            if (skill.getSkillbeidong() == 0 && skill.getSkillname().equals(skillname)) {
                return skill;
            }
        }
        return null;
    }

    public FightingSkill getSkillName() {
        for (int i = this.skills.size() - 1; i >= 0; --i) {
            FightingSkill skill = this.skills.get(i);
            if (skill.getSkillbeidong() == 0) {
                if (this.addStates != null) {
                    List<AddState> list = (List) this.addStates.stream().filter(addState/* come.tool.FightingData.AddState, */ -> addState.getStatename().equals("冷却") && (int) addState.getStateEffect() == skill.getSkillid()).collect(Collectors.toList());
                    if (list.size() > 0) {
                        continue;
                    }
                }
                return skill;
            }
        }
        return null;
    }

    public void getFightingState(FightingState org) {
        org.setCamp(this.camp);
        org.setMan(this.man);
        org.setStartState(TypeUtil.JN);
    }

    public void ChangeDataZS(FightingState state) {
        state.setCamp(this.camp);
        state.setMan(this.man);
        state.setStartState(TypeUtil.JN);
        state.setHp_Change(-this.hp);
        this.hp = 0;
        this.States = 1;
        this.addnq(-5, state);
    }

    public void ChangeData(ChangeFighting change, FightingState state) {
        state.setCamp(this.camp);
        state.setMan(this.man);
        if (change.getChangehp() < 0) {
            state.setStartState("被攻击");
            if (xzstate("振魂鼓") != null) {
                double p = xzstate("振魂鼓").getStateEffect() * xzstate("振魂鼓").getStateEffect3() / 100.0D;
                double p1 = (xzstate("振魂鼓").getSurplus() >= 997) ? (xzstate("振魂鼓").getStateEffect4() / 100.0D) : 0.0D;
                double p3 = xzstate("振魂鼓").getStateEffect() * xzstate("振魂鼓").getStateEffect2() / 100.0D;
                change.setChangehp((int) (change.getChangehp() * (1.0D - p - p1 - p3)));
            }
            if (xzstate("淬魂锤") != null) {
                double p1 = (xzstate("淬魂锤").getSurplus() >= 997) ? (xzstate("淬魂锤").getStateEffect4() / 100.0D) : 0.0D;
                double p3 = xzstate("淬魂锤").getStateEffect() * xzstate("淬魂锤").getStateEffect2() / 100.0D;
                change.setChangehp((int) (change.getChangehp() * (1.0D + p1 + p3)));
            }
            if (this.type == 0) {
                FightingSkill skill = this.skillId(9387);
                if (skill != null) {
                    int maxhp = (int) ((double) this.hp_z * skill.getSkillhurt() / 100.0);
                    if (-change.getChangehp() > maxhp) {
                        change.setChangehp(-maxhp);
                    }
                }
            } else if (this.type == 1 && this.huoyue >= 700.0) {
                FightingSkill skill = this.skillId(1322);
                if (skill != null) {
                    int maxhp = (int) ((double) this.hp_z * (100.0 - skill.getSkillgain()) / 100.0);
                    if (-change.getChangehp() > maxhp) {
                        change.setChangehp(-maxhp);
                    }
                }
            }
            if (this.type == 0 && change.getSkill(9283) == null && !this.dfxy(TypeUtil.MH, "惑", new String[]{TypeUtil.TY_G_10099, TypeUtil.TY_G_10104}) && !this.dfxy("三尸虫", "蛊", new String[]{TypeUtil.TY_G_10089, TypeUtil.TY_G_10094})) {
                FightingSkill fightingSkill = change.getSkill(9286);
                this.addnq(((fightingSkill != null) ? (-(int) fightingSkill.getSkillhurt()) : 0) + 3, state);
            }
        } else if (change.getChangehp() > 0) {
            if (xzstate("断生契") != null) {
                double xs = 1 - (xzstate("断生契").getStateEffect3() / 100);
                change.setChangehp((int) (change.getChangehp() * xs));
            }
            if (xzstate("重伤") != null) {
                change.setChangehp((int) (change.getChangehp() * (1.0D - xzstate("重伤").getStateEffect2() / 100.0D * xzstate("重伤").getStateEffect())));
            }
            if (this.States != 0 && this.xzstate(TypeUtil.BB_DHSM) != null) {
                change.setChangehp(0);
                change.setChangemp(0);
            } else {
                AddState addState = this.xzstate(TypeUtil.TY_GH_XGNC);
                if (addState != null) {
                    change.setChangehp(0);
                    change.setChangemp(0);
                } else {
                    addState = this.xzstate(TypeUtil.TY_ZS_FH);
                    if (addState != null) {
                        change.setChangehp((int) ((double) change.getChangehp() * (1.0 - addState.getStateEffect() / 100.0)));
                    }
                }
            }
        }
        if (change.getChangehp() != 0) {
            int changehp = this.hp + change.getChangehp();
            if (changehp <= 0) {
                this.hp = 0;
                this.States = 1;
                state.setExtsy("die");
                this.addnq(-5, state);
            } else if (changehp > this.hp_z) {
                this.hp = this.hp_z;
                this.States = 0;
            } else {
                this.hp = changehp;
                this.States = 0;
            }
            state.setHp_Change(change.getChangehp());
        }
        if (this.States == 1 &&
                xzstate("断生契") != null && xzstate("断生契").getStateEffect3() < 2.0D &&
                Battlefield.isV(xzstate("断生契").getStateEffect())) {
            xzstate("断生契").setSurplus(xzstate("断生契").getSurplus() + 1);
            xzstate("断生契").setStateEffect3(xzstate("断生契").getStateEffect3() + 1.0D);
        }
        if (change.getChangemp() < 0) {
            FightingSkill skill = this.getAppendSkill(9246);
            if (skill != null) {
                change.setChangemp((int) ((double) change.getChangemp() * (1.0 - skill.getSkillhurt() / 100.0)));
            }
        }
        if (change.getChangemp() != 0) {
            int changemp = this.mp + change.getChangemp();
            if (changemp > this.mp_z) {
                this.mp = this.mp_z;
            } else if (changemp > 0) {
                this.mp = changemp;
            } else {
                this.mp = 0;
            }
            state.setMp_Change(change.getChangemp());
        }
        if (change.getChangetype2() != null && change.getChangetype2().equals("非控制减益")) {
            state.setEndState_2(change.getChangetype2());
            this.RemoveAbnormal(state, ManData.values4);
        }
        if (change.getChangetype().equals("")) {
            return;
        }
        String types = change.getChangetype();
        if (types.equals("清除状态")) {
            state.setEndState_2(types);
            this.RemoveAbnormal(state, ManData.values1);
            return;
        }
        if (types.equals("清除异常状态")) {
            state.setEndState_2(types);
            this.RemoveAbnormal(state, ManData.values2);
            return;
        }
        if (types.equals("清除五行")) {
            state.setEndState_2(types);
            this.RemoveAbnormal(ManData.values3);
            return;
        }
        if (types.equals(TypeUtil.TY_SSC_LFHX)) {
            state.setEndState_2("清除异常状态");
            this.RemoveAbnormal(state, ManData.values2);
            if (change.getChangevlaue() <= 2.0) {
                return;
            }
        } else if (types.equals(TypeUtil.BB_E_HNYG)) {
            state.setEndState_2("清除状态");
            this.RemoveAbnormal(state, ManData.values1);
        } else if (types.equals("流连忘返")) {
            AddState addState = new AddState();
            addState.setStatename(change.getChangetype());
            addState.setStateEffect(change.getChangevlaue2());
            addState.setStateEffect2(change.getChangevlaue());
            this.addStates.add(addState);
        }
        if (types.equals("遗忘") || types.equals("昏睡") || types.equals("混乱")) {
            this.RemoveAbnormal(new String[]{"遗忘", "昏睡", "混乱", "封印"});
        } else if (types.equals("封印")) {
            this.RemoveAbnormal(new String[]{"遗忘", "昏睡", "混乱", "封印", "中毒"});
        } else if (types.equals("金") || types.equals("木") || types.equals("水") || types.equals("土") || types.equals("火")) {
            this.RemoveAbnormal(ManData.values3);
        } else if (types.equals("流连忘返") || types.equals("庇护")) {
            RemoveAbnormal("流连忘返");
            RemoveAbnormal("庇护");
        } else if (types.equals(TypeUtil.TZ_MXJX)) {
            this.RemoveAbnormal(new String[]{"遗忘", "昏睡", "混乱", TypeUtil.TZ_MXJX});
        } else if (types.equals(TypeUtil.TZ_HGFZ)) {
            this.RemoveAbnormal(new String[]{TypeUtil.TZ_HGFZ, "遗忘", "封印", "中毒", "昏睡", "混乱", TypeUtil.FB_JGE, TypeUtil.FB_QW});
        } else if (types.equals(TypeUtil.JS) || types.equals(TypeUtil.LL)) {
            if (this.getAppendSkill(9804) != null) {
                return;
            }
            this.RemoveAbnormal(new String[]{types});
        } else if (!types.equals("扶摇") && !types.equals(TypeUtil.L_LL) && !types.equals(TypeUtil.TY_L_GL_PYGQ)) {
            this.RemoveAbnormal(new String[]{types});
        }
        if (types.equals("遗忘")) {
            double a = 65.0;
            double b = 65.0;
            double c = 0.0;
            a += change.getChangevlaue2();
            b += change.getChangevlaue2();
            c += change.getChangevlaue2();
            if (change.getSkills() != null) {
                for (int i = change.getSkills().size() - 1; i >= 0; --i) {
                    FightingSkill skill2 = (FightingSkill) change.getSkills().get(i);
                    if (skill2.getSkillid() == 9346) {
                        b += skill2.getSkillhurt();
                        change.getSkills().remove(i);
                    } else if (skill2.getSkillid() == 9349) {
                        c += skill2.getSkillhurt();
                        change.getSkills().remove(i);
                    }
                }
                if (change.getSkills().size() == 0) {
                    change.setSkill(null);
                }
            }
            AddState addState2 = new AddState();
            addState2.setStatename(change.getChangetype());
            addState2.setStateEffect(-1.0);
            addState2.setSurplus(change.getChangesum());
            addState2.setSkills(change.getSkills());
            addState2.setManId(change.getManId());
            this.addStates.add(addState2);
            for (int j = 0; j < this.skills.size(); ++j) {
                if (((FightingSkill) this.skills.get(j)).getSkillbeidong() != 1) {
                    int skillId = ((FightingSkill) this.skills.get(j)).getSkillid();
                    if ((skillId >= 1001 && skillId <= 1100) || (skillId >= 1600 && skillId <= 2000)) {
                        if (Battlefield.isV(a)) {
                            AddState addState3 = new AddState();
                            addState3.setStatename(change.getChangetype());
                            addState3.setStateEffect((double) skillId);
                            addState3.setSurplus(change.getChangesum());
                            this.addStates.add(addState3);
                        }
                    } else if (skillId >= 5001 && skillId <= 5015) {
                        if (Battlefield.isV(b)) {
                            AddState addState3 = new AddState();
                            addState3.setStatename(change.getChangetype());
                            addState3.setStateEffect((double) skillId);
                            addState3.setSurplus(change.getChangesum());
                            this.addStates.add(addState3);
                        }
                    } else if (skillId >= 1200 && skillId <= 1300 && Battlefield.isV(c)) {
                        AddState addState3 = new AddState();
                        addState3.setStatename(change.getChangetype());
                        addState3.setStateEffect((double) skillId);
                        addState3.setSurplus(change.getChangesum());
                        this.addStates.add(addState3);
                    }
                }
            }
        } else if (types.equals("扶摇") || types.equals(TypeUtil.L_LL) || types.equals(TypeUtil.TY_L_GL_PYGQ)) {
            int lx = types.equals("扶摇") ? 1 : 1;
            AddState addState4 = this.xzstate(types);
            if (addState4 == null) {
                addState4 = new AddState();
                this.addStates.add(addState4);
            } else if (lx == 0) {
                this.UP(state, 0, addState4.getStateEffect() / 100.0);
                this.UP(state, 1, addState4.getStateEffect2() / 100.0);
            } else {
                this.UPModel(state, null);
                this.UP(state, 0, -addState4.getStateEffect() / 100.0);
            }
            addState4.setStatename(change.getChangetype());
            addState4.setStateEffect(change.getChangevlaue());
            addState4.setStateEffect2(change.getChangevlaue2());
            addState4.setSurplus(change.getChangesum());
            addState4.setSkills(change.getSkills());
            addState4.setManId(change.getManId());
            if (lx == 0) {
                this.UP(state, 0, -addState4.getStateEffect() / 100.0);
                this.UP(state, 1, -addState4.getStateEffect2() / 100.0);
            } else {
                if (types.equals(TypeUtil.L_LL)) {
                    this.UPModel(state, CreepsMixdeal.getL_LL(this.model));
                }
                this.UP(state, 0, addState4.getStateEffect() / 100.0);
            }
        } else if (types.equals(TypeUtil.SH_4010)) {
            int lx = types.equals(TypeUtil.SH_4010) ? 1 : 1;
            AddState addState4 = this.xzstate(types);
            if (addState4 == null) {
                addState4 = new AddState();
                this.addStates.add(addState4);
            } else if (lx == 0) {
                this.UP(state, 0, addState4.getStateEffect() / 100.0);
                this.UP(state, 1, addState4.getStateEffect2() / 100.0);
            } else {
                this.UPModel(state, null);
                this.UP(state, 0, -addState4.getStateEffect() / 100.0);
            }
            addState4.setStatename(change.getChangetype());
            addState4.setStateEffect(change.getChangevlaue());
            addState4.setStateEffect2(change.getChangevlaue2());
            addState4.setSurplus(change.getChangesum());
            addState4.setSkills(change.getSkills());
            addState4.setManId(change.getManId());
            if (lx == 0) {
                this.UP(state, 0, -addState4.getStateEffect() / 100.0);
                this.UP(state, 1, -addState4.getStateEffect2() / 100.0);
            } else {
                if (types.equals(TypeUtil.SH_4010)) {
                    this.UPModel(state, CreepsMixdeal.getL_LL(this.model));
                }
                this.UP(state, 0, addState4.getStateEffect() / 100.0);
            }
        } else {
            AddState addState5 = new AddState();
            addState5.setStatename(change.getChangetype());
            addState5.setStateEffect(change.getChangevlaue());
            addState5.setStateEffect2(change.getChangevlaue2());
            addState5.setSurplus(change.getChangesum());
            addState5.setSkills(change.getSkills());
            addState5.setManId(change.getManId());
            this.addStates.add(addState5);
        }
        state.setEndState_1(change.getChangetype());
    }

    public void clearAddStates() {
        for (int i = this.addStates.size() - 1; i >= 0; --i) {
            boolean isRemove = true;
            String stateName = ((AddState) this.addStates.get(i)).getStatename();
            int n = -1;
            if (stateName.equals("6045")) {
                isRemove = false;
            } else {
                switch (stateName.hashCode()) {
                    case 1656442: {
                        if (stateName.equals("6022")) {
                            n = 0;
                            break;
                        } else {
                            break;
                        }
                    }
                    case 1656444: {
                        if (stateName.equals("6024")) {
                            n = 1;
                            break;
                        } else {
                            break;
                        }
                    }
                }
                switch (n) {
                    case 0:
                    case 1: {
                        isRemove = false;
                        break;
                    }
                    default: {
                        if (stateName.startsWith("tj") || stateName.startsWith("mj") || stateName.startsWith("xl") || stateName.startsWith("rj")) {
                            isRemove = false;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }
            if (isRemove) {
                this.addStates.remove(i);
            }
        }
    }

    public void RemoveAbnormal(String... values) {
        for (int i = this.addStates.size() - 1; i >= 0; --i) {
            String statename = ((AddState) this.addStates.get(i)).getStatename();
            for (int j = 0; j < values.length; ++j) {
                if (statename.equals(values[j])) {
                    this.addStates.remove(i);
                }
            }
        }
    }

    public void RemoveAbnormal(FightingState state, String... values) {
        List<AddState> rAddStates = null;
        for (int i = this.addStates.size() - 1; i >= 0; --i) {
            AddState addState = (AddState) this.addStates.get(i);
            String statename = addState.getStatename();
            for (int j = 0; j < values.length; ++j) {
                if (statename.equals(values[j])) {
                    if (addState.getStateEffect2() == 9999.0 && addState.getSurplus() != 0) {
                        state.setState_3(values[j]);
                    } else {
                        this.addStates.remove(i);
                        if (addState.getSkills() != null) {
                            if (rAddStates == null) {
                                rAddStates = new ArrayList<>();
                            }
                            rAddStates.add(addState);
                        }
                    }
                }
            }
        }
        if (rAddStates != null) {
            MixDeal.rid(this, state, rAddStates);
        }
    }

    public boolean chongfustate(AddState addState) {
        for (int i = 0; i < this.addStates.size(); ++i) {
            if (((AddState) this.addStates.get(i)).getStatename().equals(addState.getStatename())) {
                this.addStates.remove(this.addStates.get(i));
                return true;
            }
        }
        return false;
    }

    public AddState xzstate(String type) {
        if (this.addStates == null) {
            return null;
        }
        for (int i = 0; i < this.addStates.size(); ++i) {
            if (((AddState) this.addStates.get(i)).getStatename().equals(type)) {
                return (AddState) this.addStates.get(i);
            }
        }
        return null;
    }

    public ManData bb(String bbid) {
        ManData petData = null;
        int id = -1;
        if (!bbid.equals("召回")) {
            id = Integer.parseInt(bbid);
        }
        if (id != -1) {
            for (int i = 0; i < this.pets.size(); ++i) {
                if (((FightingSummon) this.pets.get(i)).getPlay() == 1) {
                    ((FightingSummon) this.pets.get(i)).setPlay(2);
                }
                if (id == ((FightingSummon) this.pets.get(i)).getHang().getId().intValue()) {
                    ((FightingSummon) this.pets.get(i)).setPlay(1);
                    petData = ((FightingSummon) this.pets.get(i)).getPet(this.isAi);
                }
            }
        }
        return petData;
    }

    public ManData lingbao(String lId) {
        ManData lingbao = null;
        int id = -1;
        if (!lId.equals("召回")) {
            id = Integer.parseInt(lId);
        }
        if (id != -1) {
            for (int i = 0; i < this.lings.size(); ++i) {
                if (((FightingLingbao) this.lings.get(i)).getPlay() == 1) {
                    ((FightingLingbao) this.lings.get(i)).setPlay(2);
                }
                if (id == ((FightingLingbao) this.lings.get(i)).getLingbaonData().getId()) {
                    ((FightingLingbao) this.lings.get(i)).setPlay(1);
                    lingbao = ((FightingLingbao) this.lings.get(i)).getLingbaonData();
                }
            }
        }
        return lingbao;
    }

    public List<String> ztstlist(FightingManData fightingManData) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < this.addStates.size(); ++i) {
            AddState addState = (AddState) this.addStates.get(i);
            if (!addState.getStatename().equals("冷却") && !addState.getStatename().equals("回蓝") && !addState.getStatename().equals("仙法狂暴") && !addState.getStatename().equals("物理狂暴") && !addState.getStatename().equals("怨气") && !addState.getStatename().equals("复活")) {
                if (addState.getStatename().equals("隐身")) {
                    fightingManData.setAlpha(0.3f);
                } else if (!list.contains(addState.getStatename())) {
                    list.add(addState.getStatename());
                }
            }
        }
        return list;
    }

    public List<String> skilluse() {
        List<String> s = new ArrayList<>();
        for (int i = 0; i < this.skills.size(); ++i) {
            if (((FightingSkill) this.skills.get(i)).getSkillbeidong() == 0 && !this.SkillCooling(((FightingSkill) this.skills.get(i)).getSkillid())) {
                s.add(((FightingSkill) this.skills.get(i)).getSkillname());
            }
        }
        return s;
    }

    public boolean SkillCooling(int skillid) {
        for (int i = 0; i < this.addStates.size(); ++i) {
            AddState addState = (AddState) this.addStates.get(i);
            if (addState.getStatename().equals("遗忘")) {
                if (addState.getStateEffect() == (double) skillid) {
                    return true;
                }
            } else if (addState.getStatename().equals("冷却") && addState.getStateEffect() == (double) skillid) {
                return true;
            }
        }
        return false;
    }

    public boolean SkillCooling(FightingSkill skill, Battlefield battlefield) {
        for (int i = 0, length = this.addStates.size(); i < length; ++i) {
            AddState addState = (AddState) this.addStates.get(i);
            if (addState.getStateEffect() == (double) skill.getSkillid()) {
                if (addState.getStatename().equals("遗忘")) {
                    battlefield.systemMsg(this, null, 6, skill);
                    return true;
                }
                if (addState.getStatename().equals("冷却")) {
                    battlefield.systemMsg(this, null, 7, skill);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean zuoyong0(int death, int nocamp, int Stealth, int lingbao, PathPoint point, int fengyin, int yao, int hs) {
        return this.States != 2 && this.type != 3 && this.type != 4 && this.camp != nocamp && !this.zhiding(point) && this.DemandLingbao(lingbao) && this.Demanddeath(death) && this.fengyin(fengyin, "封印") && this.fengyin(hs, "昏睡") && this.fengyin(Stealth, "隐身") && this.fengyin(Stealth, "无敌") && this.fengyin(Stealth, "乾坤破阵") && this.Demandyao(yao);
    }

    public boolean zuoyong(int death, int nocamp, int Stealth, int lingbao, PathPoint point, int fengyin, int yao, int hs) {
        return this.States != 2 && this.type != 4 && this.camp != nocamp && !this.zhiding(point) && this.DemandLingbao(lingbao) && this.Demanddeath(death) && this.fengyin(fengyin, "封印") && this.fengyin(hs, "昏睡") && this.fengyin(Stealth, "隐身") && this.fengyin(Stealth, "无敌") && this.fengyin(Stealth, "乾坤破阵") && this.Demandyao(yao);
    }

    public boolean zuoyong1(int death, int nocamp, int Stealth, int lingbao, PathPoint point, int fengyin, int yao, int hs) {
        return this.States != 2 && this.type != 4 && this.camp != nocamp && !this.zhiding(point) && this.DemandLingbao(lingbao) && this.Demanddeath(death) && this.fengyin(fengyin, "封印") && this.fengyin(hs, "昏睡") && this.fengyin(Stealth, "隐身") && this.fengyin(Stealth, "乾坤破阵") && this.Demandyao(yao);
    }

    public boolean zuoyong2(int death, int nocamp, int Stealth, int lingbao, PathPoint point, int fengyin, int yao, int hs) {
        return this.States != 2 && this.type != 3 && this.type != 4 && this.camp != nocamp && !this.zhiding(point) && this.DemandLingbao(lingbao) && this.Demanddeath(death) && this.fengyin(fengyin, "封印") && this.fengyin(hs, "昏睡") && this.fengyin(Stealth, "隐身") && this.fengyin(Stealth, "乾坤破阵") && this.Demandyao(yao);
    }

    public boolean Demandyao(int yao) {
        return yao == 0 || this.xzstate("归墟") == null;
    }

    public boolean zhiding(PathPoint point) {
        return point.getX() == this.camp && point.getY() == this.man;
    }

    public boolean Demanddeath(int death) {
        if (death == 0) {
            if (this.States == 0) {
                return true;
            }
        } else {
            if (death == 1) {
                return true;
            }
            if (death == 2 && this.States == 1) {
                return true;
            }
        }
        return false;
    }

    public boolean fengyin(int fengyin, String type) {
        if (fengyin == 0) {
            if (this.xzstate(type) == null) {
                return true;
            }
        } else {
            if (fengyin == 1) {
                return true;
            }
            if (fengyin == 2 && this.xzstate(type) != null) {
                return true;
            }
        }
        return false;
    }

    public boolean DemandLingbao(int Lingbao) {
        if (Lingbao == 0) {
            if (this.type != 3) {
                return true;
            }
        } else if (Lingbao == 1) {
            if (this.type == 3) {
                return true;
            }
        } else if (Lingbao == 2) {
            if (this.type == 1) {
                return true;
            }
        } else if (Lingbao == 3) {
            if (this.type == 0) {
                return true;
            }
        } else if (Lingbao == 4) {
            if (this.type == 0 || this.type == 1) {
                return true;
            }
        } else if (Lingbao == 5 && (this.type == 0 || this.type == 2)) {
            return true;
        }
        return false;
    }

    public boolean isLicense(FightingSkill skill) {
        if (skill.getSkillid() >= 5001 && skill.getSkillid() <= 5015) {
            return this.yqz >= skill.getSkillblue();
        }
        if (skill.getSkillid() >= 9000 && skill.getSkillid() <= 10166) {
            return this.nqz >= skill.getSkillblue();
        }
        if (skill.getSkillname().equals("兵临城下")) {
            return this.mp >= (int) ((double) this.mp_z * this.getBlcxKc(2)) && this.hp > (int) ((double) this.hp_z * this.getBlcxKc(1));
        }
        if (skill.getSkillname().equals("奋蹄扬威") || skill.getSkillname().equals("势如破竹") || skill.getSkillname().equals("黑夜蒙蔽") || skill.getSkillname().equals("无极")) {
            return this.hp > (int) ((double) this.hp_z * 0.5);
        }
        return this.mp >= skill.getSkillblue();
    }

    public boolean daijia(FightingState fightingState, Battlefield battlefield) {
        if (this.States == 1) {
            return true;
        }
        fightingState.setCamp(this.camp);
        fightingState.setMan(this.man);
        int i = this.skills.size() - 1;
        while (i >= 0) {
            FightingSkill skill = (FightingSkill) this.skills.get(i);
            if (skill.getSkillname().equals(fightingState.getEndState())) {
                if (skill.getSkillid() >= 5001 && skill.getSkillid() <= 5015) {
                    this.yqz -= skill.getSkillblue();
                    fightingState.setYq_c(-skill.getSkillblue());
                    return false;
                }
                if (skill.getSkillid() >= 9000 && skill.getSkillid() <= 10166) {
                    this.nqz -= skill.getSkillblue();
                    fightingState.setNq_c(-skill.getSkillblue());
                    return false;
                }
                MixDeal.clbf(skill, this, battlefield);
                if (this.States == 1) {
                    return true;
                }
                if (skill.getSkillname().equals("兵临城下")) {
                    double cmp = this.getBlcxKc(2);
                    double chp = this.getBlcxKc(1);
                    this.mp += (int) ((double) (-this.mp_z) * cmp);
                    this.hp += (int) ((double) (-this.hp_z) * chp);
                    fightingState.setHp_Change((int) ((double) (-this.hp_z) * chp));
                    fightingState.setMp_Change((int) ((double) (-this.mp_z) * cmp));
                } else if (skill.getSkillname().equals("奋蹄扬威") || skill.getSkillname().equals("黑夜蒙蔽") || skill.getSkillname().equals("无极")) {
                    this.hp += (int) ((double) (-this.hp_z) * 0.5);
                    fightingState.setHp_Change((int) ((double) (-this.hp_z) * 0.5));
                } else if (skill.getSkillname().equals("势如破竹")) {
                    this.hp += (int) ((double) (-this.hp_z) * 0.1);
                    fightingState.setHp_Change((int) ((double) (-this.hp_z) * 0.1));
                } else if (skill.getSkilltype().equals(TypeUtil.BB_SS) || skill.getSkilltype().equals(TypeUtil.BB_E_YHSS)) {
                    fightingState.setHp_Change(-this.hp);
                    this.States = 1;
                    this.hp = 0;
                } else {
                    if (skill.getSkillid() >= 1001 && skill.getSkillid() <= 1100) {
                        this.addnq(2, fightingState);
                    }
                    this.mp -= skill.getSkillblue();
                    fightingState.setMp_Change(-skill.getSkillblue());
                }
                SummonType.xianzhi(this, skill);
                return false;
            } else {
                --i;
            }
        }
        return true;
    }

    public boolean daijia(FightingSkill skill, FightingState fightingState, Battlefield battlefield) {
        this.daijia(skill, fightingState, battlefield, 0);
        return false;
    }

    public boolean daijia(FightingSkill skill, FightingState fightingState, Battlefield battlefield, int consume) {
        if (this.States == 1) {
            return true;
        }
        fightingState.setCamp(this.camp);
        fightingState.setMan(this.man);
        if (skill.getSkillid() >= 5001 && skill.getSkillid() <= 5015) {
            if (this.getSkillType("8070") != null) {
                if (Fabao.isJGSB) {
                    return false;
                }
                this.yqz -= skill.getSkillblue();
                fightingState.setYq_c(-skill.getSkillblue());
                return false;
            } else {
                this.yqz -= skill.getSkillblue();
                fightingState.setYq_c(-skill.getSkillblue());
                return false;
            }
        } else {
            if (skill.getSkillid() >= 9000 && skill.getSkillid() <= 10166) {
                this.nqz -= skill.getSkillblue();
                fightingState.setNq_c(-skill.getSkillblue());
                return false;
            }
            MixDeal.clbf(skill, this, battlefield);
            if (this.States == 1) {
                return true;
            }
            if (skill.getSkillname().equals("兵临城下")) {
                double cmp = this.getBlcxKc(2);
                double chp = this.getBlcxKc(1);
                this.mp += (int) ((double) (-this.mp_z) * cmp);
                this.hp += (int) ((double) (-this.hp_z) * chp);
                fightingState.setHp_Change((int) ((double) (-this.hp_z) * chp));
                fightingState.setMp_Change((int) ((double) (-this.mp_z) * cmp));
            } else if (skill.getSkillname().equals("奋蹄扬威") || skill.getSkillname().equals("黑夜蒙蔽") || skill.getSkillname().equals("无极")) {
                this.hp += (int) ((double) (-this.hp_z) * 0.5);
                fightingState.setHp_Change((int) ((double) (-this.hp_z) * 0.5));
            } else if (skill.getSkillname().equals("势如破竹")) {
                this.hp += (int) ((double) (-this.hp_z) * 0.1);
                fightingState.setHp_Change((int) ((double) (-this.hp_z) * 0.1));
            } else if (skill.getSkilltype().equals(TypeUtil.BB_SS)) {
                fightingState.setHp_Change(-this.hp);
                this.States = 1;
                this.hp = 0;
            } else {
                int skillblue = skill.getSkillblue();
                FightingSkill ms = this.getSkillType("9185");
                if (skill.getSkillid() == 1025 && ms != null) {
                    int i = ms.getSkilllvl() * 100 + 100;
                    skillblue -= i;
                }
                this.mp -= skillblue;
                if (consume == 0) {
                    fightingState.setMp_Change(-skillblue);
                } else {
                    fightingState.setMp_Change((int) ((double) (-skillblue * consume) / 100.0));
                }
            }
            SummonType.xianzhi(this, skill);
            return false;
        }
    }

    public int capture() {
        if (this.type == 2 && Battlefield.random.nextInt(100) < 30) {
            return this.zs;
        }
        return -1;
    }

    public double getfz(long ap) {
        if (this.States != 0) {
            return 0.0;
        }
        double roleffzl = this.Quality.getRoleffzl();
        double roleffzcd = this.Quality.getRoleffzcd();
        if (roleffzcd > 200.0) {
            roleffzcd = 200.0;
        }
        roleffzl += this.getFamencsJv(3, new String[]{"明镜止水"});
        roleffzcd += this.getFamencsJv(3, new String[]{"明镜止水"});
        AddState addState = this.xzstate("气聚神凝");
        if (addState != null) {
            roleffzl += this.getFamencsJv(3, new String[]{"气聚神凝"});
            roleffzcd += this.getFamencsJv(3, new String[]{"气聚神凝"});
        }
        if ((double) (Battlefield.random.nextInt(100) + 1) < roleffzl && this.xzstate("保护") == null) {
            return (double) ap * (roleffzcd / 100.0);
        }
        return 0.0;
    }

    public double getsx(int i, String type) {
        if (i == 0) {
            return this.getk(type);
        }
        if (i == 1) {
            return this.geth(type);
        }
        if (i == 2) {
            return this.getq(type);
        }
        if (i == 3) {
            return this.gets(type);
        }
        if (i == 4) {
            return this.getqt(type);
        }
        return 0.0;
    }

    public double getk(String type) {
        double xiao = 0.0;
        AddState addState = this.xzstate("抗" + type);
        if (addState != null) {
            xiao += addState.getStateEffect();
        }
        if (type.equals("封印") || type.equals("昏睡") || type.equals("遗忘") || type.equals("混乱")) {
            if (xzstate("玄鳞甲") != null) {
                xiao += xzstate("玄鳞甲").getStateEffect2();
            }
            if (xzstate("斗魂帆") != null) {
                xiao += xzstate("斗魂帆").getStateEffect();
            }
            if (xzstate("长生幡") != null) {
                xiao += xzstate("长生幡").getStateEffect();
            }
            if (xzstate("屠巫剑") != null) {
                xiao -= xzstate("屠巫剑").getStateEffect();
            }
            if (xzstate(TypeUtil.L_LL) != null) {
                xiao += xzstate(TypeUtil.L_LL).getStateEffect4();
            }
            if (xzstate("幽魂灯") != null) {
                int huihe = xzstate("幽魂灯").getSurplus();
                switch (huihe) {
                    case 1:
                        xiao -= xzstate("幽魂灯").getStateEffect3();
                        break;
                    case 2:
                        xiao -= xzstate("幽魂灯").getStateEffect2();
                        break;
                    case 3:
                    case 4:
                        xiao -= xzstate("幽魂灯").getStateEffect();
                        break;
                }
                if (getType() != 0 && getType() != 1) {
                    xiao *= 0.8D;
                }
            }
        }
        if (type.equals("普通攻击")) {
            addState = this.xzstate(TypeUtil.BB_E_HNYG);
            if (addState != null) {
                xiao += addState.getStateEffect();
            }
            FightingSkill skill_4022 = this.getSkillType("4022");
            if (skill_4022 != null) {
                xiao -= skill_4022.getValue1();
            }
            FightingSkill skill = this.getAppendSkill(9347);
            if (skill != null) {
                xiao -= skill.getSkillhurt();
            }
            skill = this.getAppendSkill(9704);
            if (skill != null) {
                xiao -= 20.0;
            }
            addState = this.xzstate(TypeUtil.KX);
            if (addState != null) {
                if (getSkillType("4004") != null) {
                    if (Battlefield.isV(getSkillType("4004").getSkillgain())) {
                        double p = Math.min((1.0D - getSkillType("4004").getSkillhurt()) * addState.getStateEffect(), 45.0D);
                        double c = 0.0D;
                        if (xzstate("玉虚令") != null) {
                            c = xzstate("玉虚令").getStateEffect2() / 100.0D;
                        }
                        xiao += addState.getStateEffect() * (1.0D - c) - p;
                    }
                } else {
                    double p = 0.0D;
                    if (xzstate("玉虚令") != null) {
                        p = xzstate("玉虚令").getStateEffect2() / 100.0D;
                    }
                    xiao += addState.getStateEffect() * (1.0D - p);
                }
            }
            addState = this.xzstate("清心静气");
            if (addState != null) {
                int skillId = (int) addState.getStateEffect();
                xiao += FaMenUtil.getDouble((int) addState.getStateEffect(), (int) this.getFmsld(skillId), 2);
            }
            addState = this.xzstate("以静制动");
            if (addState != null) {
                xiao += this.getFamencsJv(2, new String[]{addState.getStatename()});
            }
            xiao += this.Quality.getRolekwl();
            xiao += this.Quality.getKlsh();
            return xiao;
        } else if (type.equals("抗致命率")) {
            xiao += this.Quality.getKzml();
            FightingSkill skill2 = this.getAppendSkill(10043);
            if (skill2 != null) {
                xiao += skill2.getSkillhurt();
            }
            addState = xzstate("流连忘返");
            if (addState != null && id != (int) addState.getStateEffect()) {
                int petid = (int) addState.getStateEffect();
                RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(new BigDecimal(petid));
                xiao += GetShkx(pet, pet.getQl(), 1);
            }
            xiao += Quality.getRolekwl();
            return xiao;
        } else {
            if (type.equals("抗仙法狂暴")) {
                FightingSkill skill2 = this.getAppendSkill(10044);
                if (skill2 != null && (int) this.nqyy <= 0) {
                    this.Quality.addR_XFKB(skill2.getSkillhurt());
                }
                ++this.nqyy;
            } else if (type.equals("风")) {
                xiao += this.Quality.getRolekff();
                addState = this.xzstate("减人仙");
                if (addState != null) {
                    if (addState.getStateEffect() == 2.0) {
                        xiao -= 25.0;
                    } else {
                        xiao -= 20.0;
                    }
                }
                FightingSkill skill_4022 = this.getSkillType("4022");
                if (skill_4022 != null) {
                    xiao -= skill_4022.getValue1();
                }
                addState = this.xzstate(TypeUtil.KX);
                if (addState != null) {
                    xiao += addState.getStateEffect();
                }
                addState = this.xzstate("清心静气");
                if (addState != null) {
                    int skillId2 = (int) addState.getStateEffect();
                    xiao += FaMenUtil.getDouble((int) addState.getStateEffect(), (int) this.getFmsld(skillId2), 2);
                }
                addState = this.xzstate(TypeUtil.TY_FY_FDSJ);
                if (addState != null) {
                    xiao -= addState.getStateEffect();
                }
                addState = this.xzstate(TypeUtil.TY_HL_WSLT);
                if (addState != null) {
                    xiao -= addState.getStateEffect();
                }
                addState = this.xzstate(TypeUtil.BB_E_HNYG);
                if (addState != null) {
                    xiao += addState.getStateEffect();
                }
                addState = this.xzstate(TypeUtil.BB_RHTY);
                if (addState != null) {
                    xiao += addState.getStateEffect() * 4.0;
                }
                FightingSkill skill = this.getAppendSkill(9152);
                if (skill != null) {
                    xiao -= skill.getSkillhurt() * 3.0;
                }
                skill = this.getAppendSkill(9129);
                if (skill != null) {
                    xiao -= skill.getSkillhurt();
                }
                skill = this.getAppendSkill(9704);
                if (skill != null) {
                    xiao -= 20.0;
                }
                addState = this.xzstate("以静制动");
                if (addState != null) {
                    xiao += this.getFamencsJv(1, new String[]{addState.getStatename()});
                }
                double p = 0.0D;
                if (xzstate("玉虚令") != null) {
                    p = xzstate("玉虚令").getStateEffect2() / 100.0D;
                }
                xiao *= 1.0D - p;
                xiao += this.getFamencsJv(2, new String[]{"暴虎冯河"});
                addState = xzstate("流连忘返");
                if (addState != null && id != (int) addState.getStateEffect()) {
                    int petid = (int) addState.getStateEffect();
                    RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(new BigDecimal(petid));
                    xiao += GetShkx(pet, pet.getQl(), 4);
                }
                return xiao;
            } else if (type.equals("雷")) {
                xiao += this.Quality.getRoleklf();
                addState = this.xzstate("减人仙");
                if (addState != null) {
                    if (addState.getStateEffect() == 2.0) {
                        xiao -= 25.0;
                    } else {
                        xiao -= 20.0;
                    }
                }
                FightingSkill skill_4022 = this.getSkillType("4022");
                if (skill_4022 != null) {
                    xiao -= skill_4022.getValue1();
                }
                addState = this.xzstate(TypeUtil.KX);
                if (addState != null) {
                    xiao += addState.getStateEffect();
                }
                addState = this.xzstate("清心静气");
                if (addState != null) {
                    int skillId2 = (int) addState.getStateEffect();
                    xiao += FaMenUtil.getDouble((int) addState.getStateEffect(), (int) this.getFmsld(skillId2), 2);
                }
                addState = this.xzstate(TypeUtil.TY_FY_FDSJ);
                if (addState != null) {
                    xiao -= addState.getStateEffect();
                }
                addState = this.xzstate(TypeUtil.TY_HL_WSLT);
                if (addState != null) {
                    xiao -= addState.getStateEffect();
                }
                addState = this.xzstate(TypeUtil.BB_E_HNYG);
                if (addState != null) {
                    xiao += addState.getStateEffect();
                }
                addState = this.xzstate(TypeUtil.BB_RHTY);
                if (addState != null) {
                    xiao += addState.getStateEffect() * 4.0;
                }
                FightingSkill skill = this.getAppendSkill(9152);
                if (skill != null) {
                    xiao -= skill.getSkillhurt() * 3.0;
                }
                skill = this.getAppendSkill(9129);
                if (skill != null) {
                    xiao -= skill.getSkillhurt();
                }
                skill = this.getAppendSkill(9704);
                if (skill != null) {
                    xiao -= 20.0;
                }
                addState = this.xzstate("以静制动");
                if (addState != null) {
                    xiao += this.getFamencsJv(1, new String[]{addState.getStatename()});
                }
                xiao += this.getFamencsJv(2, new String[]{"暴虎冯河"});
                addState = xzstate("流连忘返");
                if (addState != null && id != (int) addState.getStateEffect()) {
                    int petid = (int) addState.getStateEffect();
                    RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(new BigDecimal(petid));
                    xiao += GetShkx(pet, pet.getQl(), 4);
                }
                return xiao;
            } else if (type.equals("水")) {
                xiao += this.Quality.getRoleksf();
                addState = this.xzstate("减人仙");
                if (addState != null) {
                    if (addState.getStateEffect() == 2.0) {
                        xiao -= 25.0;
                    } else {
                        xiao -= 20.0;
                    }
                }
                FightingSkill skill_4022 = this.getSkillType("4022");
                if (skill_4022 != null) {
                    xiao -= skill_4022.getValue1();
                }
                addState = this.xzstate(TypeUtil.KX);
                if (addState != null) {
                    xiao += addState.getStateEffect();
                }
                addState = this.xzstate("清心静气");
                if (addState != null) {
                    int skillId2 = (int) addState.getStateEffect();
                    xiao += FaMenUtil.getDouble((int) addState.getStateEffect(), (int) this.getFmsld(skillId2), 2);
                }
                addState = xzstate("螭纹佩");
                if (addState != null) {
                    xiao -= addState.getStateEffect2();
                }
                addState = this.xzstate(TypeUtil.TY_FY_FDSJ);
                if (addState != null) {
                    xiao -= addState.getStateEffect();
                }
                addState = this.xzstate(TypeUtil.TY_HL_WSLT);
                if (addState != null) {
                    xiao -= addState.getStateEffect();
                }
                addState = this.xzstate(TypeUtil.BB_E_HNYG);
                if (addState != null) {
                    xiao += addState.getStateEffect();
                }
                addState = this.xzstate(TypeUtil.BB_RHTY);
                if (addState != null) {
                    xiao += addState.getStateEffect() * 4.0;
                }
                FightingSkill skill = this.getAppendSkill(9152);
                if (skill != null) {
                    xiao -= skill.getSkillhurt() * 3.0;
                }
                skill = this.getAppendSkill(9129);
                if (skill != null) {
                    xiao -= skill.getSkillhurt();
                }
                skill = this.getAppendSkill(9704);
                if (skill != null) {
                    xiao -= 20.0;
                }
                addState = this.xzstate("以静制动");
                if (addState != null) {
                    xiao += this.getFamencsJv(1, new String[]{addState.getStatename()});
                }
                xiao += this.getFamencsJv(2, new String[]{"暴虎冯河"});
                addState = xzstate("螭纹佩");
                if (addState != null && xiao > 0.0D) {
                    xiao *= 1.0D - addState.getStateEffect3() / 100.0D * ((getType() == 0 || getType() == 1) ? 1.5D : 1.0D);
                }
                double p = 0.0D;
                if (xzstate("玉虚令") != null) {
                    p = xzstate("玉虚令").getStateEffect2() / 100.0D;
                }
                xiao *= 1.0D - p;
                addState = xzstate("流连忘返");
                if (addState != null && id != (int) addState.getStateEffect()) {
                    int petid = (int) addState.getStateEffect();
                    RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(new BigDecimal(petid));
                    xiao += GetShkx(pet, pet.getQl(), 4);
                }
                return xiao;
            } else if (type.equals("火")) {
                xiao += this.Quality.getRolekhf();
                addState = this.xzstate("减人仙");
                if (addState != null) {
                    if (addState.getStateEffect() == 2.0) {
                        xiao -= 25.0;
                    } else {
                        xiao -= 20.0;
                    }
                }
                FightingSkill skill_4022 = this.getSkillType("4022");
                if (skill_4022 != null) {
                    xiao -= skill_4022.getValue1();
                }
                addState = this.xzstate(TypeUtil.KX);
                if (addState != null) {
                    xiao += addState.getStateEffect();
                }
                addState = this.xzstate("清心静气");
                if (addState != null) {
                    int skillId2 = (int) addState.getStateEffect();
                    xiao += FaMenUtil.getDouble((int) addState.getStateEffect(), (int) this.getFmsld(skillId2), 2);
                }
                addState = this.xzstate(TypeUtil.TY_FY_FDSJ);
                if (addState != null) {
                    xiao -= addState.getStateEffect();
                }
                addState = this.xzstate(TypeUtil.TY_HL_WSLT);
                if (addState != null) {
                    xiao -= addState.getStateEffect();
                }
                addState = this.xzstate(TypeUtil.BB_E_HNYG);
                if (addState != null) {
                    xiao += addState.getStateEffect();
                }
                addState = this.xzstate(TypeUtil.BB_RHTY);
                if (addState != null) {
                    xiao += addState.getStateEffect() * 4.0;
                }
                FightingSkill skill = this.getAppendSkill(9152);
                if (skill != null) {
                    xiao -= skill.getSkillhurt() * 3.0;
                }
                skill = this.getAppendSkill(9129);
                if (skill != null) {
                    xiao -= skill.getSkillhurt();
                }
                skill = this.getAppendSkill(9704);
                if (skill != null) {
                    xiao -= 20.0;
                }
                addState = this.xzstate("以静制动");
                if (addState != null) {
                    xiao += this.getFamencsJv(1, new String[]{addState.getStatename()});
                }
                xiao += this.getFamencsJv(2, new String[]{"暴虎冯河"});

                double p = 0.0D;
                if (xzstate("玉虚令") != null) {
                    p = xzstate("玉虚令").getStateEffect2() / 100.0D;
                }
                xiao *= 1.0D - p;
                addState = xzstate("流连忘返");
                if (addState != null && id != (int) addState.getStateEffect()) {
                    int petid = (int) addState.getStateEffect();
                    RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(new BigDecimal(petid));
                    xiao += GetShkx(pet, pet.getQl(), 5);
                }
                return xiao;
            } else if (type.equals("混乱")) {
                xiao += this.Quality.getRolekhl() + this.bhws;
                addState = this.xzstate("减人仙");
                if (addState != null) {
                    if (addState.getStateEffect() == 2.0) {
                        xiao -= 12.0;
                    } else {
                        xiao -= 8.0;
                    }
                }
                addState = this.xzstate(TypeUtil.KX);
                if (addState != null) {
                    xiao += addState.getStateEffect2();
                }
                addState = this.xzstate(TypeUtil.TY_R_BMJM);
                if (addState != null) {
                    xiao += addState.getStateEffect();
                }
                addState = xzstate("螭纹佩");
                if (addState != null) {
                    xiao -= addState.getStateEffect();
                }
                xiao += this.getFamencsJv(1, new String[]{"明镜止水"});
                addState = this.xzstate("气聚神凝");
                if (addState != null) {
                    xiao += this.getFamencsJv(1, new String[]{"气聚神凝"});
                }
                FightingSkill skill2 = this.getAppendSkill(TypeUtil.TY_JS_LLHZ);
                if (skill2 != null) {
                    xiao += skill2.getSkillhurt();
                }
                addState = this.xzstate(TypeUtil.TY_F_CZDF);
                if (addState != null) {
                    xiao -= addState.getStateEffect2();
                }
                addState = this.xzstate(TypeUtil.BB_E_HNYG);
                if (addState != null) {
                    xiao += addState.getStateEffect2();
                }
                addState = this.xzstate(TypeUtil.BB_RHTY);
                if (addState != null) {
                    xiao += addState.getStateEffect();
                }
                xiao += this.getFamencsJv(3, new String[]{"暴虎冯河"});
                return xiao;
            } else if (type.equals("封印")) {
                xiao += this.Quality.getRolekfy() + this.bhws;
                addState = this.xzstate("减人仙");
                if (addState != null) {
                    if (addState.getStateEffect() == 2.0) {
                        xiao -= 12.0;
                    } else {
                        xiao -= 8.0;
                    }
                }
                addState = this.xzstate(TypeUtil.TY_R_BMJM);
                if (addState != null) {
                    xiao += addState.getStateEffect();
                }
                FightingSkill skill_4022 = this.getSkillType("4022");
                if (skill_4022 != null) {
                    xiao += skill_4022.getValue2();
                }
                addState = this.xzstate(TypeUtil.KX);
                if (addState != null) {
                    xiao += addState.getStateEffect2();
                }
                xiao += this.getFamencsJv(1, new String[]{"明镜止水"});
                addState = this.xzstate("气聚神凝");
                if (addState != null) {
                    xiao += this.getFamencsJv(1, new String[]{"气聚神凝"});
                }
                addState = this.xzstate(TypeUtil.TY_FY_ARXH);
                if (addState != null) {
                    xiao -= addState.getStateEffect2();
                }
                addState = this.xzstate(TypeUtil.BB_E_HNYG);
                if (addState != null) {
                    xiao += addState.getStateEffect2();
                }
                addState = this.xzstate(TypeUtil.BB_RHTY);
                if (addState != null) {
                    xiao += addState.getStateEffect();
                }
                xiao += this.getFamencsJv(3, new String[]{"暴虎冯河"});
                FightingSkill skill = this.getAppendSkill(TypeUtil.TY_JS_LLHZ);
                if (skill != null) {
                    xiao += skill.getSkillhurt();
                }
                return xiao;
            } else if (type.equals("昏睡")) {
                xiao += this.Quality.getRolekhs() + this.bhws;
                addState = this.xzstate("减人仙");
                if (addState != null) {
                    if (addState.getStateEffect() == 2.0) {
                        xiao -= 12.0;
                    } else {
                        xiao -= 8.0;
                    }
                }
                FightingSkill skill_4022 = this.getSkillType("4022");
                if (skill_4022 != null) {
                    xiao += skill_4022.getValue2();
                }
                addState = this.xzstate(TypeUtil.TY_R_BMJM);
                if (addState != null) {
                    xiao += addState.getStateEffect();
                }
                addState = this.xzstate(TypeUtil.KX);
                if (addState != null) {
                    xiao += addState.getStateEffect2();
                }
                xiao += this.getFamencsJv(1, new String[]{"明镜止水"});
                addState = this.xzstate("气聚神凝");
                if (addState != null) {
                    xiao += this.getFamencsJv(1, new String[]{"气聚神凝"});
                }
                addState = this.xzstate(TypeUtil.BB_E_HNYG);
                if (addState != null) {
                    xiao += addState.getStateEffect2();
                }
                addState = this.xzstate(TypeUtil.BB_RHTY);
                if (addState != null) {
                    xiao += addState.getStateEffect();
                }
                FightingSkill skill = this.getAppendSkill(TypeUtil.TY_JS_LLHZ);
                if (skill != null) {
                    xiao += skill.getSkillhurt();
                }
                xiao += this.getFamencsJv(3, new String[]{"暴虎冯河"});
                return xiao;
            } else if (type.equals("中毒")) {
                xiao += this.Quality.getRolekzd();
                addState = this.xzstate("减人仙");
                if (addState != null) {
                    if (addState.getStateEffect() == 2.0) {
                        xiao -= 12.0;
                    } else {
                        xiao -= 8.0;
                    }
                }
                addState = this.xzstate(TypeUtil.KX);
                if (addState != null) {
                    xiao += addState.getStateEffect2();
                }
                addState = xzstate("流连忘返");
                if (addState != null && id != (int) addState.getStateEffect()) {
                    int petid = (int) addState.getStateEffect();
                    RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(new BigDecimal(petid));
                    xiao += GetShkx(pet, pet.getQl(), 6);
                }
                return xiao;
            } else if (type.equals("震慑")) {
                xiao += this.Quality.getRolekzs();
                addState = this.xzstate("减魔鬼");
                if (addState != null) {
                    if (addState.getStateEffect() == 2.0) {
                        xiao -= 15.0;
                    } else {
                        xiao -= 10.0;
                    }
                }
                addState = this.xzstate(TypeUtil.MH);
                if (addState != null) {
                    xiao += addState.getStateEffect2();
                }
                FightingSkill skill2 = this.getAppendSkill(9152);
                if (skill2 != null) {
                    xiao -= skill2.getSkillhurt();
                }
                addState = xzstate("流连忘返");
                if (addState != null && id != (int) addState.getStateEffect()) {
                    int petid = (int) addState.getStateEffect();
                    RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(new BigDecimal(petid));
                    xiao += GetShkx(pet, pet.getQl(), 7);
                }
                return xiao;
            } else if (type.equals("遗忘")) {
                xiao += this.Quality.getRolekyw() + this.bhws;
                addState = this.xzstate("减魔鬼");
                if (addState != null) {
                    if (addState.getStateEffect() == 2.0) {
                        xiao -= 15.0;
                    } else {
                        xiao -= 10.0;
                    }
                }
                addState = this.xzstate(TypeUtil.MH);
                if (addState != null) {
                    xiao += addState.getStateEffect();
                }
                addState = this.xzstate(TypeUtil.MHD);
                if (addState != null) {
                    xiao -= addState.getStateEffect() / 2;
                }

                addState = this.xzstate(TypeUtil.TY_R_BMJM);
                if (addState != null) {
                    xiao += addState.getStateEffect();
                }
                addState = this.xzstate("明镜止水");
                if (addState != null) {
                    xiao += addState.getStateEffect() * 3.0;
                }
                addState = this.xzstate("气聚神凝");
                if (addState != null) {
                    xiao += this.getFamencsJv(1, new String[]{"气聚神凝"});
                }
                addState = this.xzstate(TypeUtil.BB_E_HNYG);
                if (addState != null) {
                    xiao += addState.getStateEffect2();
                }
                addState = this.xzstate(TypeUtil.BB_RHTY);
                if (addState != null) {
                    xiao += addState.getStateEffect();
                }
                FightingSkill skill2 = this.getAppendSkill(TypeUtil.TY_JS_LLHZ);
                if (skill2 != null) {
                    xiao += skill2.getSkillhurt();
                }
                return xiao;
            } else if (type.equals("鬼火")) {
                xiao += this.Quality.getRolekgh();
                addState = this.xzstate("减魔鬼");
                if (addState != null) {
                    if (addState.getStateEffect() == 2.0) {
                        xiao -= 25.0;
                    } else {
                        xiao -= 20.0;
                    }
                }
                addState = this.xzstate(TypeUtil.MH);
                if (addState != null) {
                    xiao += addState.getStateEffect();
                }
                addState = this.xzstate(TypeUtil.MHD);
                if (addState != null) {
                    xiao -= addState.getStateEffect() / 2;
                }
                addState = this.xzstate("以静制动");
                if (addState != null) {
                    xiao += this.getFamencsJv(1, new String[]{addState.getStatename()});
                }
                addState = this.xzstate("清心静气");
                if (addState != null) {
                    int skillId3 = (int) addState.getStateEffect();
                    xiao += FaMenUtil.getDouble((int) addState.getStateEffect(), (int) this.getFmsld(skillId3), 2);
                }
                addState = this.xzstate(TypeUtil.TY_FY_FDSJ);
                if (addState != null) {
                    xiao -= addState.getStateEffect();
                }
                addState = this.xzstate(TypeUtil.TY_HL_WSLT);
                if (addState != null) {
                    xiao -= addState.getStateEffect();
                }
                addState = this.xzstate(TypeUtil.BB_E_HNYG);
                if (addState != null) {
                    xiao += addState.getStateEffect();
                }
                addState = this.xzstate(TypeUtil.BB_RHTY);
                if (addState != null) {
                    xiao += addState.getStateEffect() * 4.0;
                }
                FightingSkill skill2 = this.getAppendSkill(9152);
                if (skill2 != null) {
                    xiao -= skill2.getSkillhurt() * 3.0;
                }
                skill2 = this.getAppendSkill(9129);
                if (skill2 != null) {
                    xiao -= skill2.getSkillhurt();
                }
                skill2 = this.getAppendSkill(9704);
                if (skill2 != null) {
                    xiao -= 20.0;
                }
                addState = xzstate("流连忘返");
                if (addState != null && id != (int) addState.getStateEffect()) {
                    int petid = (int) addState.getStateEffect();
                    RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(new BigDecimal(petid));
                    xiao += GetShkx(pet, pet.getQl(), 8);
                }
                return xiao;
            } else if (type.equals("三尸")) {
                xiao += this.Quality.getRoleksc();
                addState = this.xzstate("减魔鬼");
                if (addState != null) {
                    if (addState.getStateEffect() == 2.0) {
                        xiao -= 2000.0;
                    } else {
                        xiao -= 1000.0;
                    }
                }
                addState = this.xzstate(TypeUtil.MH);
                if (addState != null) {
                    xiao += addState.getStateEffect() * 100.0;
                }
                addState = this.xzstate(TypeUtil.MHD);
                if (addState != null) {
                    xiao -= addState.getStateEffect() / 2;
                }
                addState = this.xzstate(TypeUtil.BB_RHTY);
                if (addState != null) {
                    xiao += addState.getStateEffect() * 100.0;
                }
                FightingSkill skill2 = this.getAppendSkill(9152);
                if (skill2 != null) {
                    xiao -= skill2.getSkillhurt() * 500.0;
                }
                addState = xzstate("流连忘返");
                if (addState != null && id != (int) addState.getStateEffect()) {
                    int petid = (int) addState.getStateEffect();
                    RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(new BigDecimal(petid));
                    xiao += GetShkx(pet, pet.getQl(), 7);
                }
                return xiao;
            } else if (type.equals("无")) {
                return this.Quality.getK_wsxsh();
            }
            return xiao;
        }
    }

    public double GetShkx(RoleSummoning pet, Ql ql, int type) {
        double xiao = 0;
        long qm = pet.getFriendliness();
        if (ql != null) {
            xiao += AddShkx(ql, qm, type);
        }
        return xiao;
    }

    public double AddShkx(Ql ql, long qm, int type) {
        double xiao = 0;
        switch (type) {
            case 1: // 物理抗性
                if (ql.getRolekwl() > Quality.getRolekwl()) {
                    xiao += (ql.getRolekwl() - Quality.getRolekwl()) * ((20 + CustomFunction.XS(qm, 0.76)) / 100D);
                }
                break;
            case 2: // 风法抗性
                if (ql.getRolekff() > Quality.getRolekff()) {
                    xiao += (ql.getRolekff() - Quality.getRolekff()) * ((20 + CustomFunction.XS(qm, 0.76)) / 100D);
                }
                break;
            case 3: // 雷法抗性
                if (ql.getRoleklf() > Quality.getRoleklf()) {
                    xiao += (ql.getRoleklf() - Quality.getRoleklf()) * ((20 + CustomFunction.XS(qm, 0.76)) / 100D);
                }
                break;
            case 4: // 水法抗性
                if (ql.getRoleksf() > Quality.getRoleksf()) {
                    xiao += (ql.getRoleksf() - Quality.getRoleksf()) * ((20 + CustomFunction.XS(qm, 0.76)) / 100D);
                }
                break;
            case 5: // 火法抗性
                if (ql.getRolekhf() > Quality.getRolekhf()) {
                    xiao += (ql.getRolekhf() - Quality.getRolekhf()) * ((20 + CustomFunction.XS(qm, 0.76)) / 100D);
                }
                break;
            case 6: // 中毒抗性
                if (ql.getRolekzd() > Quality.getRolekzd()) {
                    xiao += (ql.getRolekzd() - Quality.getRolekzd()) * ((20 + CustomFunction.XS(qm, 0.76)) / 100D);
                }
                break;
            case 7: // 震慑抗性
                if (ql.getRolekzs() > Quality.getRolekzs()) {
                    xiao += (ql.getRolekzs() - Quality.getRolekzs()) * ((20 + CustomFunction.XS(qm, 0.76)) / 100D);
                }
                break;
            case 8: // 鬼火抗性
                if (ql.getRolekgh() > Quality.getRolekgh()) {
                    xiao += (ql.getRolekgh() - Quality.getRolekgh()) * ((20 + CustomFunction.XS(qm, 0.76)) / 100D);
                }
                break;
            case 9: // 三尸抗性
                if (ql.getRoleksc() > Quality.getRoleksc()) {
                    xiao += (ql.getRoleksc() - Quality.getRoleksc()) * ((20 + CustomFunction.XS(qm, 0.76)) / 100D);
                }
                break;
            default:
                break;
        }
        return xiao;
    }

    public double geth(String type) {
        double xiao = 0.0;
        AddState addState = this.xzstate("忽视" + type);
        if (addState != null) {
            xiao += addState.getStateEffect();
        }
        if (type.equals(TypeUtil.PTGJ) || type.equals("反击")) {
            addState = this.xzstate(TypeUtil.TY_LL_BS);
            if (addState != null) {
                xiao += addState.getStateEffect();
            }
            if (this.getstat("金石") != null) {
                this.pwlcd = 50.0;
                this.pwljl = 50.0;
            }
            this.pwljl += this.getFamencsJv(2, new String[]{"云飞烟灭"});
            addState = this.xzstate("困兽之斗-牛");
            double kdzs = 0.0;
            if (addState != null) {
                int fmId = (int) addState.getStateEffect();
                int fmsld = (int) addState.getStateEffect2();
                kdzs = FaMenUtil.getDouble(fmId, fmsld, 2);
            }
            double djpjl = 0.0;
            double djpcd = 0.0;
            if (type.equals("反击")) {
                djpjl = this.Quality.getFjhfyl();
                djpcd = this.Quality.getFjhfyv();
            }
            if ((double) (Battlefield.random.nextInt(100) + 1) < this.Quality.getRolehsfyl() + xiao + djpjl + this.pwljl + kdzs) {
                xiao += this.Quality.getRolehsfyv() + this.pwlcd;
                xiao += this.getFamencsJv(2, new String[]{"云飞烟灭"});
                xiao += kdzs;
                xiao += djpcd;
            }
            if (this.noMandata != null) {
                AddState xzstate = this.noMandata.xzstate(TypeUtil.KX);
                if (xzstate != null) {
                    FightingSkill skill_4004 = this.getSkillType("4004");
                    if (skill_4004 != null) {
                        xiao += skill_4004.getValue1();
                    }
                }
            }
            addState = this.xzstate(TypeUtil.FB_DSC);
            if (addState != null) {
                xiao += addState.getStateEffect();
            }
            addState = this.xzstate(TypeUtil.FB_DSC);
            if (addState != null) {
                xiao += addState.getStateEffect();
            }
            return xiao;
        } else if (type.equals("风")) {
            if ((double) (Battlefield.random.nextInt(100) + 1) < this.Quality.getRolehsxfkl() + this.hsjv) {
                xiao += this.Quality.getRolehsxfcd();
            }
            addState = this.xzstate(TypeUtil.TY_F_HKJF);
            if (addState != null) {
                xiao += addState.getStateEffect();
            }
            if (this.noMandata != null) {
                AddState xzstate2 = this.noMandata.xzstate(TypeUtil.KX);
                if (xzstate2 != null) {
                    FightingSkill skill_4005 = this.getSkillType("4005");
                    if (skill_4005 != null) {
                        xiao += skill_4005.getValue1();
                    }
                }
                AddState xzstate3 = this.noMandata.xzstate("混乱");
                AddState xzstate4 = this.noMandata.xzstate("昏睡");
                AddState xzstate5 = this.noMandata.xzstate("遗忘");
                if (xzstate3 != null || xzstate4 != null || xzstate5 != null) {
                    FightingSkill skill_4006 = this.getSkillType("4008");
                    if (skill_4006 != null) {
                        xiao += skill_4006.getValue1();
                    }
                }
            }
            xiao += this.Quality.getRolehsff();
            return xiao;
        } else if (type.equals("雷")) {
            if ((double) (Battlefield.random.nextInt(100) + 1) < this.Quality.getRolehsxfkl() + this.hsjv) {
                xiao += this.Quality.getRolehsxfcd();
            }
            if (this.noMandata != null) {
                AddState xzstate2 = this.noMandata.xzstate(TypeUtil.KX);
                if (xzstate2 != null) {
                    FightingSkill skill_4005 = this.getSkillType("4005");
                    if (skill_4005 != null) {
                        xiao += skill_4005.getValue1();
                    }
                }
                AddState xzstate3 = this.noMandata.xzstate("混乱");
                AddState xzstate4 = this.noMandata.xzstate("昏睡");
                AddState xzstate5 = this.noMandata.xzstate("遗忘");
                if (xzstate3 != null || xzstate4 != null || xzstate5 != null) {
                    FightingSkill skill_4006 = this.getSkillType("4008");
                    if (skill_4006 != null) {
                        xiao += skill_4006.getValue1();
                    }
                }
            }
            xiao += this.Quality.getRolehslf();
            return xiao;
        } else if (type.equals("水")) {
            if ((double) (Battlefield.random.nextInt(100) + 1) < this.Quality.getRolehsxfkl() + this.hsjv) {
                xiao += this.Quality.getRolehsxfcd();
            }
            if (this.noMandata != null) {
                AddState xzstate2 = this.noMandata.xzstate(TypeUtil.KX);
                if (xzstate2 != null) {
                    FightingSkill skill_4005 = this.getSkillType("4005");
                    if (skill_4005 != null) {
                        xiao += skill_4005.getValue1();
                    }
                }
                AddState xzstate3 = this.noMandata.xzstate("混乱");
                AddState xzstate4 = this.noMandata.xzstate("昏睡");
                AddState xzstate5 = this.noMandata.xzstate("遗忘");
                if (xzstate3 != null || xzstate4 != null || xzstate5 != null) {
                    FightingSkill skill_4006 = this.getSkillType("4008");
                    if (skill_4006 != null) {
                        xiao += skill_4006.getValue1();
                    }
                }
            }
            xiao += this.Quality.getRolehssf();
            return xiao;
        } else if (type.equals("火")) {
            if ((double) (Battlefield.random.nextInt(100) + 1) < this.Quality.getRolehsxfkl() + this.hsjv) {
                xiao += this.Quality.getRolehsxfcd();
            }
            if (this.noMandata != null) {
                AddState xzstate2 = this.noMandata.xzstate(TypeUtil.KX);
                if (xzstate2 != null) {
                    FightingSkill skill_4007 = this.getSkillType("4005");
                    if (skill_4007 != null) {
                        xiao += skill_4007.getValue1();
                    }
                }
                AddState xzstate3 = this.noMandata.xzstate("混乱");
                AddState xzstate4 = this.noMandata.xzstate("昏睡");
                AddState xzstate5 = this.noMandata.xzstate("遗忘");
                if (xzstate3 != null || xzstate4 != null || xzstate5 != null) {
                    FightingSkill skill_4006 = this.getSkillType("4008");
                    if (skill_4006 != null) {
                        xiao += skill_4006.getValue1();
                    }
                }
            }
            xiao += this.Quality.getRolehshf();
            return xiao;
        } else {
            if (type.equals("混乱")) {
                xiao += this.Quality.getRolehshl() * 2.0;
                return xiao;
            }
            if (type.equals("封印")) {
                xiao += this.Quality.getRolehsfy() * 2.0;
                return xiao;
            }
            if (type.equals("昏睡")) {
                xiao += this.Quality.getRolehshs() * 2.0;
                return xiao;
            }
            if (type.equals("中毒")) {
                xiao += this.Quality.getRolehszd();
                return xiao;
            }
            if (type.equals("鬼火")) {
                xiao += this.Quality.getRolehsgh();
                addState = this.xzstate(TypeUtil.TY_GH_BQCH);
                if (addState != null) {
                    xiao += addState.getStateEffect();
                }
                if (this.noMandata != null) {
                    AddState xzstate6 = this.noMandata.xzstate("火");
                    AddState xzstate7 = this.noMandata.xzstate("水");
                    AddState xzstate8 = this.noMandata.xzstate("遗忘");
                    if (xzstate6 != null || xzstate7 != null || xzstate8 != null) {
                        FightingSkill skill_4008 = this.getSkillType("4008");
                        if (skill_4008 != null) {
                            xiao += skill_4008.getValue1();
                        }
                    }
                }
                return xiao;
            } else if (type.equals("遗忘")) {
                addState = this.xzstate(TypeUtil.TY_YW_GFZJ);
                if (addState != null) {
                    xiao += addState.getStateEffect();
                }
                addState = this.xzstate(TypeUtil.TY_YW_MBSH);
                if (addState != null) {
                    xiao += addState.getStateEffect();
                }
                xiao += this.Quality.getRolehsyw() * 2.0;
                return xiao;
            } else if (type.equals("震慑")) {
                xiao += this.Quality.getRolehszs();
                addState = this.xzstate(TypeUtil.TY_ZS_ZY);
                if (addState != null) {
                    xiao += addState.getStateEffect();
                }
                return xiao;
            } else {
                if (type.equals(TypeUtil.MH)) {
                    xiao += this.Quality.getQmh();
                    return xiao;
                }
                return xiao;
            }
        }
    }

    public double getq(String type) {
        double xiao = 0.0;
        if (type.equals("震慑")) {
            AddState addState = this.xzstate("强震慑");
            if (addState != null) {
                xiao += addState.getStateEffect();
            }
            xiao += this.Quality.getRoleqzs();
            FightingSkill skill = this.getSkillType(TypeUtil.TY_RH_JZGC_SS);
            if (skill == null) {
                skill = this.getSkillType(TypeUtil.TY_RH_JZGC_SG);
            }
            if (skill == null) {
                skill = this.getSkillType(TypeUtil.TY_RH_JZGC_SP);
            }
            if (skill != null) {
                xiao = skill.getSkillhurt();
            }
            addState = this.xzstate(TypeUtil.TY_ZS_LXGY);
            if (addState != null) {
                xiao += addState.getStateEffect();
            }
            addState = this.xzstate(TypeUtil.TY_ZS_NCPL);
            if (addState != null) {
                xiao += addState.getStateEffect();
            }
            return xiao;
        } else {
            if (type.equals("风")) {
                xiao += this.Quality.getRoleqff();
                return xiao;
            }
            if (type.equals("雷")) {
                xiao += this.Quality.getRoleqlf();
                return xiao;
            }
            if (type.equals("水")) {
                xiao += this.Quality.getRoleqsf();
                return xiao;
            }
            if (type.equals("火")) {
                xiao += this.Quality.getRoleqhf();
                return xiao;
            }
            if (type.equals("混乱")) {
                xiao += this.Quality.getRoleqhl();
                return xiao;
            }
            if (type.equals("封印")) {
                xiao += this.Quality.getRoleqfy();
                AddState addState = this.xzstate(TypeUtil.TY_FY_ARXH);
                if (addState != null) {
                    xiao += addState.getStateEffect2();
                }
                return xiao;
            } else {
                if (type.equals("昏睡")) {
                    xiao += this.Quality.getRoleqhs();
                    return xiao;
                }
                if (type.equals("中毒")) {
                    xiao += this.Quality.getRoleqzd();
                    return xiao;
                }
                if (type.equals("遗忘")) {
                    xiao += this.Quality.getRolestrongforget();
                    return xiao;
                }
                if (type.equals("鬼火")) {
                    xiao += this.Quality.getRolegstronghostfire();
                    return xiao;
                }
                if (type.equals("三尸")) {
                    AddState addState = this.xzstate("强三尸虫");
                    if (addState != null) {
                        xiao += addState.getStateEffect();
                    }
                    xiao += this.Quality.getRolestrongbodyblood();
                    return xiao;
                } else if (type.equals("三尸回血")) {
                    xiao += this.Quality.getRolestrongbodyblooddeep();
                    FightingSkill skill2 = this.getAppendSkill(9345);
                    if (skill2 != null) {
                        xiao -= skill2.getSkillhurt();
                    }
                    AddState addState2 = this.xzstate(TypeUtil.TY_H_JSYY);
                    if (addState2 != null) {
                        xiao -= addState2.getStateEffect();
                    }
                    return xiao;
                } else if (type.equals("力量")) {
                    xiao += this.Quality.getJqgjfs();
                    FightingSkill skill2 = this.getSkillType(TypeUtil.TY_RH_JZGC_SG);
                    if (skill2 == null) {
                        skill2 = this.getSkillType(TypeUtil.TY_RH_JZGC_GS);
                        if (skill2 == null) {
                            skill2 = this.getSkillType(TypeUtil.TY_RH_JZGC_GP);
                        }
                        if (skill2 != null) {
                            xiao = skill2.getSkillhurt();
                        }
                    } else {
                        xiao = skill2.getSkillgain();
                    }
                    return xiao;
                } else if (type.equals("抗性") || type.equals(TypeUtil.MH)) {
                    xiao += this.Quality.getJqfyfs();
                    if (type.equals("抗性")) {
                        FightingSkill skill2 = this.getSkillType(TypeUtil.TY_RH_JZGC_GP);
                        if (skill2 == null) {
                            skill2 = this.getSkillType(TypeUtil.TY_RH_JZGC_SP);
                        }
                        if (skill2 != null) {
                            xiao = skill2.getSkillgain();
                        }
                    }
                    return xiao;
                } else if (type.equals("加速")) {
                    xiao += this.Quality.getJqsdfs();
                    FightingSkill skill2 = this.getSkillType(TypeUtil.TY_RH_JZGC_SS);
                    if (skill2 == null) {
                        skill2 = this.getSkillType(TypeUtil.TY_RH_JZGC_GS);
                    }
                    if (skill2 != null) {
                        xiao = skill2.getSkillgain();
                    }
                    return xiao;
                } else {
                    if (type.equals("霹雳")) {
                        xiao += this.Quality.getQlpl();
                        return xiao;
                    }
                    if (type.equals("沧波")) {
                        xiao += this.Quality.getQlcb();
                        return xiao;
                    }
                    if (type.equals("扶摇")) {
                        xiao += this.Quality.getQlfy();
                        return xiao;
                    }
                    if (type.equals("甘霖")) {
                        xiao += this.Quality.getQlglv();
                        return xiao;
                    }
                    if (type.equals("甘霖回血")) {
                        AddState addState = this.xzstate(type);
                        if (addState != null) {
                            xiao += addState.getStateEffect();
                        }
                        xiao += this.Quality.getQlglc();
                        return xiao;
                    } else {
                        return xiao;
                    }
                }
            }
        }
    }

    public double gets(String type) {
        if (type.equals("无")) {
            return this.Quality.getRolewsxsh();
        }
        if (type.equals("风")) {
            return this.Quality.getRoleffsh();
        }
        if (type.equals("雷")) {
            return this.Quality.getRolelfsh();
        }
        if (type.equals("水")) {
            return this.Quality.getRolesfsh();
        }
        if (type.equals("火")) {
            return this.Quality.getRolehfsh();
        }
        if (type.equals("中毒")) {
            return this.Quality.getRolezdsh();
        }
        if (type.equals("三尸")) {
            return this.Quality.getRolesssh();
        }
        if (type.equals("鬼火")) {
            return this.Quality.getRoleghsh();
        }
        return 0.0;
    }

    public double getqt(String type) {
        double xiao = 0.0;
        if (type.equals(TypeUtil.SX_SBL)) {
            xiao += this.Quality.getRolefdsl();
            FightingSkill skill = this.getAppendSkill(9603);
            if (skill != null) {
                xiao += skill.getSkillhurt();
            }
            skill = this.getAppendSkill(9226);
            if (skill != null) {
                xiao += skill.getSkillhurt();
            }
            AddState addState = this.xzstate(TypeUtil.BB_TJTT);
            if (addState != null) {
                xiao -= addState.getStateEffect();
            }
        }
        return xiao;
    }

    public double getds(int skillID) {
        skillID -= 1001;
        skillID /= 5;
        if (skillID == 0) {
            return this.Quality.getDhl();
        }
        if (skillID == 1) {
            return this.Quality.getDfy();
        }
        if (skillID == 2) {
            return this.Quality.getDhs();
        }
        if (skillID == 3) {
            return this.Quality.getDdf();
        }
        if (skillID == 4) {
            return this.Quality.getDzs();
        }
        if (skillID == 8) {
            return this.Quality.getDff();
        }
        if (skillID == 9) {
            return this.Quality.getDlf();
        }
        if (skillID == 10) {
            return this.Quality.getDsf();
        }
        if (skillID == 11) {
            return this.Quality.getDhf();
        }
        if (skillID == 12) {
            return this.Quality.getDgh();
        }
        if (skillID == 13) {
            return this.Quality.getDsc();
        }
        if (skillID == 14) {
            return this.Quality.getDyw();
        }
        return 0.0;
    }

    public double getMz(int skillID) {
        skillID -= 1001;
        skillID /= 5;
        double mz = this.Quality.getFsmz();
        if (skillID == 0) {
            mz = this.Quality.getMhl();
        } else if (skillID == 1) {
            mz = this.Quality.getMfy();
        } else if (skillID == 2) {
            mz = this.Quality.getMhs();
        } else if (skillID == 3) {
            mz = this.Quality.getMdf();
        } else if (skillID == 4) {
            mz = this.Quality.getMzs();
        } else if (skillID == 8) {
            mz = this.Quality.getMff();
        } else if (skillID == 9) {
            mz = this.Quality.getMlf();
        } else if (skillID == 10) {
            mz = this.Quality.getMsf();
        } else if (skillID == 11) {
            mz = this.Quality.getMhf();
        } else if (skillID == 12) {
            mz = this.Quality.getMgh();
        } else if (skillID == 13) {
            mz = this.Quality.getMsc();
        } else if (skillID == 14) {
            mz = this.Quality.getMyw();
        }
        return mz;
    }

    public double getjs(String type) {
        double value = this.Quality.getEjs();
        if (type.equals("风")) {
            value += this.Quality.getJff();
        } else if (type.equals("火")) {
            value += this.Quality.getJhf();
        } else if (type.equals("水")) {
            value += this.Quality.getJsf();
        } else if (type.equals("雷")) {
            value += this.Quality.getJlf();
        } else if (type.equals("鬼火")) {
            value += this.Quality.getJgh();
        }
        AddState addState = this.xzstate("清心静气");
        if (addState != null) {
            int skillId = (int) addState.getStateEffect();
            value += FaMenUtil.getDouble((int) addState.getStateEffect(), (int) this.getFmsld(skillId), 3);
        }
        addState = this.xzstate("以静制动");
        if (addState != null) {
            value += this.getFamencsJv(3, new String[]{addState.getStatename()});
        }
        return value;
    }

    public FightingSkill getSkillType(String type) {
        if (this.skills == null) {
            this.skills = new ArrayList<>();
        }
        for (int i = 0; i < this.skills.size(); ++i) {
            FightingSkill skill = (FightingSkill) this.skills.get(i);
            if (skill.getSkillid() > 1100 && skill.getSkilltype().equals(type)) {
                if (this.SkillCooling(skill.getSkillid())) {
                    return null;
                }
                return skill;
            }
        }
        return null;
    }

    public List<FightingSkill> getSkillTypes(List<String> types) {
        if (this.skills == null) {
            this.skills = new ArrayList<>();
        }
        List<FightingSkill> fightingSkills = new ArrayList<>();
        for (int i = 0; i < this.skills.size(); ++i) {
            FightingSkill skill = (FightingSkill) this.skills.get(i);
            if (types.contains(skill.getSkilltype())) {
                fightingSkills.add(skill);
            }
        }
        return fightingSkills;
    }

    public FightingSkill getSkillType1(String type) {
        if (this.skills == null) {
            this.skills = new ArrayList<>();
        }
        for (int i = 0; i < this.skills.size(); ++i) {
            FightingSkill skill = (FightingSkill) this.skills.get(i);
            if (skill.getSkilltype().equals(type)) {
                return skill;
            }
        }
        return null;
    }

    public FightingSkill getxlnd() {
        this.initAttacks();
        AddAttack attack = (AddAttack) this.attacks.get("乘风破浪");
        if (attack != null && Battlefield.isV(attack.getSkill().getSkillgain())) {
            return attack.getSkill();
        }
        attack = (AddAttack) this.attacks.get("霹雳流星");
        if (attack != null && Battlefield.isV(attack.getSkill().getSkillgain())) {
            return attack.getSkill();
        }
        attack = (AddAttack) this.attacks.get("大海无量");
        if (attack != null && Battlefield.isV(attack.getSkill().getSkillgain())) {
            return attack.getSkill();
        }
        attack = (AddAttack) this.attacks.get("祝融取火");
        if (attack != null && Battlefield.isV(attack.getSkill().getSkillgain())) {
            return attack.getSkill();
        }
        return null;
    }

    public int ljs(double ljjc) {
        int sum = 0;
        if (this.getstat("青峰") != null) {
            ljjc += 30.0;
        }
        if ((double) (Battlefield.random.nextInt(100) + 1) < this.Quality.getRolefljl() + ljjc) {
            sum = (int) this.Quality.getRolefljv();
        }
        FightingSkill skill = null;
        if (sum != 0) {
            skill = this.getSkillType("浪子回头");
            if (skill == null) {
                return sum;
            }
            double hurt = skill.getSkillhurt();
            skill = this.getSkillType(TypeUtil.BB_E_QHLZHT);
            if (skill != null) {
                hurt *= 1.0 + skill.getSkillgain() / 100.0;
            }
            if (Battlefield.isV(hurt)) {
                sum *= 2;
            }
            if (sum > 15) {
                sum = 15;
            }
        }
        skill = this.getAppendSkill(9201);
        if (skill != null && Battlefield.isV(skill.getSkillhurt())) {
            ++sum;
        }
        return sum;
    }

    public String xz() {
        if (this.type == 2 || this.type == 3 || this.type == 4) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < this.skills.size(); ++i) {
            if (((FightingSkill) this.skills.get(i)).getSkillbeidong() == 0) {
                if (buffer.length() != 0) {
                    buffer.append("_");
                } else {
                    buffer.append("技能=");
                }
                String skillName = ((FightingSkill) this.skills.get(i)).getSkillname();
                buffer.append(skillName);
                buffer.append("-");
                if (!this.SkillCooling(((FightingSkill) this.skills.get(i)).getSkillid())) {
                    for (int j = 0; j < BattleData.vs.length; ++j) {
                        if (skillName.equals(BattleData.vs[j])) {
                            buffer.append(1);
                        }
                    }
                    buffer.append(0);
                } else {
                    buffer.append(2);
                }
            }
        }
        if (buffer.length() != 0) {
            buffer.append("|");
        }
        if (this.pets != null) {
            if (this.pets.size() != 0) {
                if (!buffer.toString().equals("")) {
                    buffer.append("|");
                }
                buffer.append("召唤兽=");
            }
            for (int i = 0; i < this.pets.size(); ++i) {
                if (i != 0) {
                    buffer.append("_");
                }
                buffer.append(((FightingSummon) this.pets.get(i)).getHang().getId());
                buffer.append("-");
                buffer.append(((FightingSummon) this.pets.get(i)).getPlay());
            }
        }
        if (this.lings != null) {
            if (this.lings.size() != 0) {
                if (!buffer.toString().equals("")) {
                    buffer.append("|");
                }
                buffer.append("灵宝=");
            }
            for (int i = 0; i < this.lings.size(); ++i) {
                if (i != 0) {
                    buffer.append("_");
                }
                buffer.append(((FightingLingbao) this.lings.get(i)).getLingbaonData().getId());
                buffer.append("-");
                buffer.append(((FightingLingbao) this.lings.get(i)).getPlay());
            }
        }
        return this.XZ = buffer.toString();
    }

    public String xz2() {
        if (this.type == 2 || this.type == 3 || this.type == 4) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        LOOP:
        for (int i = 0; i < this.skills.size(); ++i) {
            if (((FightingSkill) this.skills.get(i)).getSkillbeidong() == 0) {
                if (buffer.length() != 0) {
                    buffer.append("_");
                } else {
                    buffer.append("技能=");
                }
                String skillName = ((FightingSkill) this.skills.get(i)).getSkillname();
                buffer.append(skillName);
                buffer.append("-");
                if (!this.SkillCooling(((FightingSkill) this.skills.get(i)).getSkillid())) {
                    int j = 0;
                    while (j < BattleData.vs.length) {
                        if (skillName.equals(BattleData.vs[j])) {
                            buffer.append(1);
                            continue LOOP;
                        } else {
                            ++j;
                        }
                    }
                    buffer.append(0);
                } else {
                    buffer.append(2);
                }
            }
        }
        if (this.pets != null) {
            if (this.pets.size() != 0) {
                if (!buffer.toString().equals("")) {
                    buffer.append("|");
                }
                buffer.append("召唤兽=");
            }
            for (int i = 0; i < this.pets.size(); ++i) {
                if (i != 0) {
                    buffer.append("_");
                }
                buffer.append(((FightingSummon) this.pets.get(i)).getHang().getId());
                buffer.append("-");
                buffer.append(((FightingSummon) this.pets.get(i)).getPlay());
            }
        }
        if (this.lings != null) {
            if (this.lings.size() != 0) {
                if (!buffer.toString().equals("")) {
                    buffer.append("|");
                }
                buffer.append("灵宝=");
            }
            for (int i = 0; i < this.lings.size(); ++i) {
                if (i != 0) {
                    buffer.append("_");
                }
                buffer.append(((FightingLingbao) this.lings.get(i)).getLingbaonData().getId());
                buffer.append("-");
                buffer.append(((FightingLingbao) this.lings.get(i)).getPlay());
            }
        }
        String XZS = buffer.toString();
        if (this.XZ != null && this.XZ.equals(XZS)) {
            return null;
        }
        return this.XZ = XZS;
    }

    public FightingSkill getlingskill(int size, int type) {
        int gl = 0;
        FightingSkill zb = null;
        for (int i = this.skills.size() - 1; i >= 0; --i) {
            FightingSkill skill = (FightingSkill) this.skills.get(i);
            if (size >= skill.getSkilllvl() && MixDeal.getyx(skill.getSkillid(), type) && !"高级挣扎".equals(skill.getSkillname()) && !"低级挣扎".equals(skill.getSkillname()) && !"招魂".equals(skill.getSkillname())) {
                int bx = Battlefield.random.nextInt(100);
                if (gl <= bx) {
                    gl = bx;
                    zb = skill;
                }
            }
        }
        return zb;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCamp() {
        return this.camp;
    }

    public void setCamp(int camp) {
        this.camp = camp;
    }

    public int getMan() {
        return this.man;
    }

    public void setMan(int man) {
        this.man = man;
    }

    public String getManname() {
        return this.manname;
    }

    public void setManname(String manname) {
        this.manname = manname;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMp() {
        return this.mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getZHP_Z() {
        return (this.original != null) ? this.original.getHp_z() : this.hp_z;
    }

    public int getHp_z() {
        return this.hp_z;
    }

    public void setHp_z(int hp_z) {
        this.hp_z = hp_z;
    }

    public int getMp_z() {
        return this.mp_z;
    }

    public void setMp_z(int mp_z) {
        this.mp_z = mp_z;
    }

    public int getAp() {
        int jc = 0;
        AddState addState = this.xzstate(TypeUtil.LL);
        if (addState != null) {
            jc = (int) ((double) jc + (double) this.ap * addState.getStateEffect() / 100.0);
        }
        addState = this.xzstate(TypeUtil.TZ_PFCZ);
        if (addState != null) {
            jc = (int) ((double) jc + addState.getStateEffect());
        }
        addState = this.xzstate(TypeUtil.TY_GH_QYBY);
        if (addState != null) {
            jc = (int) ((double) jc - (double) this.ap * addState.getStateEffect() / 100.0);
        }
        addState = this.xzstate(TypeUtil.L_LL);
        if (addState != null) {
            jc = (int) ((double) jc + (double) this.ap * addState.getStateEffect() / 100.0);
        }
        addState = this.xzstate(TypeUtil.TY_L_PL_SLJL);
        if (addState != null) {
            jc = (int) ((double) jc + addState.getStateEffect());
        }
        FightingSkill skill = this.getSkillType("杀身成仁");
        if (skill != null) {
            double hurt = skill.getSkillhurt();
            skill = this.getSkillType(TypeUtil.BB_E_QHSSCR);
            if (skill != null) {
                hurt *= 1.0 + skill.getSkillgain() / 100.0;
            }
            jc = (int) ((double) jc + (double) (this.hp_z - this.hp) * hurt / 100.0);
        }
        skill = this.getAppendSkill(9811);
        if (skill != null) {
            jc = (int) ((double) jc - (double) this.ap * (skill.getSkillhurt() + 20.0) / 100.0);
        }
        skill = this.getSkillType("4002");
        if (skill != null) {
            int mpZ = (int) ((double) this.getMp_z() * (skill.getValue1() / 100.0));
            if (mpZ > 15000) {
                jc += 15000;
            } else {
                jc += mpZ;
            }
        }
        return this.ap + jc;
    }

    public int huoAp() {
        return this.ap;
    }

    public void setAp(int ap) {
        this.ap = ap;
    }

    public int getSp() {
        if (this.type == 3) {
            AddState addState = this.xzstate(TypeUtil.TY_JS_YYCF);
            int jc = 0;
            if (addState != null) {
                jc = (int) ((double) jc + (double) this.sp * addState.getStateEffect() / 100.0);
            }
            return this.sp + this.sp2 + jc;
        } else {
            if (this.xzstate(TypeUtil.TZ_YSHY) != null) {
                return 99999;
            }
            AddState addState = this.xzstate(TypeUtil.JS);
            int jc = 0;
            if (addState != null) {
                jc = (int) ((double) jc + (double) this.sp * addState.getStateEffect() / 100.0);
            }
            if (this.xzstate("荆棘束身") != null) {
                return 0;
            }
            addState = this.xzstate("减速");
            if (addState != null) {
                jc = (int) ((double) jc - (double) this.sp * addState.getStateEffect() / 100.0);
            }
            addState = this.xzstate("9164");
            if (addState != null) {
                jc = (int) ((double) jc - (double) this.sp * addState.getStateEffect() / 100.0);
            }
            addState = this.xzstate(TypeUtil.TY_FY_ZYCX);
            if (addState != null) {
                jc = (int) ((double) jc - (double) this.sp * addState.getStateEffect() / 100.0);
            }
            addState = this.xzstate(TypeUtil.TY_F_WHSF);
            if (addState != null) {
                jc = (int) ((double) jc - (double) this.sp * addState.getStateEffect() / 100.0);
            }
            addState = this.xzstate(TypeUtil.TY_GH_QYBY);
            if (addState != null) {
                jc = (int) ((double) jc - (double) this.sp * addState.getStateEffect() / 200.0);
            }
            addState = this.xzstate("知耻后勇");
            if (addState != null) {
                jc += 400;
            }
            jc += this.getFamencsValue(2, new String[]{"披荆斩棘"});
            return this.sp + this.sp2 + jc;
        }
    }

    public void updateLings() {
        if (this.type == 0) {
            RoleData roleData = RolePool.getLineRoleData(BigDecimal.valueOf((long) this.id));
            for (Hang helpF : roleData.getHelpFs()) {
                int i = 0;
                while (i < this.lings.size()) {
                    if (((FightingLingbao) this.lings.get(i)).getLingbaonData().getId() == helpF.getId().intValue()) {
                        this.lings.add(0, this.lings.remove(i));
                        break;
                    } else {
                        ++i;
                    }
                }
            }
        }
    }

    public void updatePets() {
        if (this.type == 0) {
            RoleData roleData = RolePool.getLineRoleData(BigDecimal.valueOf((long) this.id));
            if (roleData != null) {
                String helpBb = roleData.getPackRecord().getHelpBb();
                if (helpBb != null && !helpBb.equals("")) {
                    String[] vs = helpBb.split("\\|");
                    for (int i = vs.length - 1; i >= 0; --i) {
                        int id = Integer.parseInt(vs[i]);
                        int j = 0;
                        while (j < this.pets.size()) {
                            FightingSummon pet = (FightingSummon) this.pets.get(j);
                            if (pet.getHang().getId().intValue() == id) {
                                pet = (FightingSummon) this.pets.remove(j);
                                this.pets.add(0, pet);
                                break;
                            } else {
                                ++j;
                            }
                        }
                    }
                }
            }
        }
    }

    public void updateZhiYuan() {
        RoleData roleData = RolePool.getLineRoleData(BigDecimal.valueOf((long) this.id));
        this.setZy(roleData.getLoginResult().getScoretype("支援").intValue());
        this.setLzy(roleData.getLoginResult().getScoretype("灵宝支援").intValue());
    }

    public void setSp(int sp) {
        this.sp = sp;
    }

    public List<FightingSkill> getSkills() {
        return this.skills;
    }

    public void setSkills(List<FightingSkill> skills) {
        this.skills = skills;
    }

    public List<FightingSkill> getSkillsCopy() {
        return this.skillsCopy;
    }

    public List<FightingSummon> getPets() {
        return this.pets;
    }

    public void setPets(List<FightingSummon> pets) {
        this.pets = pets;
    }

    public List<AddState> getAddStates() {
        return this.addStates;
    }

    public void setAddStates(List<AddState> addStates) {
        this.addStates = addStates;
    }

    public int getStates() {
        return this.States;
    }

    public void setStates(int states) {
        this.States = states;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ql getQuality() {
        if (this.Quality == null) {
            this.Quality = new Ql();
        }
        return this.Quality;
    }

    public void setQuality(Ql quality) {
        this.Quality = quality;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getQihe() {
        return this.qihe;
    }

    public void setQihe(long qihe) {
        this.qihe = qihe;
    }

    public double getHuoyue() {
        return this.huoyue;
    }

    public void setHuoyue(double huoyue) {
        this.huoyue = huoyue;
    }

    public double getShanghai() {
        return this.shanghai + 1.0;
    }

    public void setShanghai(double shanghai) {
        this.shanghai = shanghai;
    }

    public double getKangluobao() {
        return this.kangluobao;
    }

    public void setKangluobao(double kangluobao) {
        this.kangluobao = kangluobao;
    }

    public double getYuanzhu() {
        return this.yuanzhu;
    }

    public void setYuanzhu(double yuanzhu) {
        this.yuanzhu = yuanzhu;
    }

    public List<FightingLingbao> getLings() {
        return this.lings;
    }

    public void setLings(List<FightingLingbao> lings) {
        this.lings = lings;
    }

    public int getLvl() {
        return this.lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getZs() {
        return this.zs;
    }

    public void setZs(int zs) {
        this.zs = zs;
    }

    public String getPetType() {
        return this.petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public int getSp2() {
        return this.sp2;
    }

    public void setSp2(int sp2) {
        this.sp2 = sp2;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ManData getChild() {
        return this.Child;
    }

    public void setChild(ManData child) {
        this.Child = child;
    }

    public double getvalue(int type) {
        if (type < 2) {
            return (double) this.hp / (double) this.hp_z;
        }
        if (type < 4) {
            return (double) this.mp / (double) this.mp_z;
        }
        if (type < 6) {
            return (double) this.ap;
        }
        return (double) this.sp;
    }

    public int getyylz() {
        int i = this.addStates.size() - 1;
        while (i >= 0) {
            AddState addState = (AddState) this.addStates.get(i);
            if (addState.getStatename().equals("阴阳逆转")) {
                addState.setStateEffect(addState.getStateEffect() - 1.0);
                if (addState.getStateEffect() <= 0.0) {
                    this.addStates.remove(i);
                    return 2;
                }
                return 1;
            } else {
                --i;
            }
        }
        return 0;
    }

    public AddState fhy() {
        if (this.xzstate("归墟") != null) {
            return null;
        }
        for (int i = this.addStates.size() - 1; i >= 0; --i) {
            AddState addState = (AddState) this.addStates.get(i);
            if (addState.getStatename().equals("回魂咒")) {
                this.addStates.remove(i);
                return addState;
            }
            if (addState.getStatename().equals("复活")) {
                this.addStates.remove(i);
                return addState;
            }
        }
        return null;
    }

    public FightingSkill getChildSkill(String type) {
        for (int i = this.skills.size() - 1; i >= 0; --i) {
            FightingSkill skill = (FightingSkill) this.skills.get(i);
            if (skill.getSkilltype().equals(type) && (double) Battlefield.random.nextInt(100) < skill.getSkillhitrate()) {
                return skill;
            }
        }
        return null;
    }

    public int getWLKHSkill() {
        if (this.States != 0 || this.type == 3 || this.type == 4) {
            return 0;
        }
        int hurt = 0;
        AddState addState = null;
        int i = this.addStates.size() - 1;
        while (i >= 0) {
            if (!((AddState) this.addStates.get(i)).getStatename().equals("雾里看花")) {
                --i;
            } else {
                addState = (AddState) this.addStates.get(i);
                this.addStates.remove(i);
                break;
            }
        }
        if (addState != null) {
            hurt = (int) ((double) (this.hp_z - this.hp) * addState.getStateEffect() / 100.0);
            if (hurt <= 0) {
                hurt = 1;
            }
        }
        return hurt;
    }

    public boolean addAddState(String type, int lus, double... value) {
        AddState addState = null;
        for (int i = this.addStates.size() - 1; i >= 0; i--) {
            addState = this.addStates.get(i);
            if (addState.getStatename().equals(type)) {
                double[] l = value;
                for (int j = 0; j < l.length; j++) {
                    if (j == 0)
                        addState.setStateEffect(l[j]);

                    if (j == 1)
                        addState.setStateEffect2(l[j]);
                    if (j == 2)
                        addState.setStateEffect3(l[j]);
                    if (j == 3)
                        addState.setStateEffect4(l[j]);
                    if (j == 4)
                        addState.setStateEffect5(l[j]);
                    if (j == 5)
                        addState.setStateEffect6(l[j]);
                    if (j == 6)
                        addState.setStateEffect7(l[j]);
                }
                addState.setSkills(null);
                addState.setSurplus(lus);
                return false;
            }
        }
        this.addStates.add(new AddState(type, lus, value));
        return true;
    }

    public boolean addAddState(String type, double e1, double e2, int lus) {
        AddState addState = null;
        for (int i = this.addStates.size() - 1; i >= 0; --i) {
            addState = (AddState) this.addStates.get(i);
            if (addState.getStatename().equals(type)) {
                addState.setStateEffect(e1);
                addState.setStateEffect2(e2);
                addState.setSkills(null);
                addState.setSurplus(lus);
                return false;
            }
        }
        this.addStates.add(new AddState(type, e1, e2, lus));
        return true;
    }

    public boolean addAddState(String type, double e1, double e2, double e3, int lus) {
        AddState addState = null;
        for (int i = this.addStates.size() - 1; i >= 0; --i) {
            addState = (AddState) this.addStates.get(i);
            if (addState.getStatename().equals(type)) {
                addState.setStateEffect(e1);
                addState.setStateEffect2(e2);
                addState.setStateEffect3(e3);
                addState.setSkills(null);
                addState.setSurplus(lus);
                return false;
            }
        }
        this.addStates.add(new AddState(type, e1, e2, lus));
        return true;
    }

    public AddState addAddState2(String type, double e1, double e2, int lus) {
        AddState addState = null;
        for (int i = this.addStates.size() - 1; i >= 0; --i) {
            addState = (AddState) this.addStates.get(i);
            if (addState.getStatename().equals(type)) {
                addState.setStateEffect(addState.getStateEffect() + e1);
                addState.setStateEffect2(addState.getStateEffect2() + e2);
                addState.setSkills(null);
                addState.setSurplus(lus);
                return addState;
            }
        }
        addState = new AddState(type, e1, e2, lus);
        this.addStates.add(new AddState(type, e1, e2, lus));
        return addState;
    }

    public boolean deleteState(String type) {
        String[] vs = {type};
        if (type.equals("清除异常状态")) {
            vs = new String[]{"遗忘", "封印", "中毒", "昏睡", "混乱", TypeUtil.FB_JGE, TypeUtil.FB_QW};
        }
        boolean l = false;
        for (int i = this.addStates.size() - 1; i >= 0; --i) {
            AddState addState = (AddState) this.addStates.get(i);
            int j = 0;
            while (j < vs.length) {
                if (addState.getStatename().equals(vs[j])) {
                    this.addStates.remove(i);
                    l = true;
                    break;
                } else {
                    ++j;
                }
            }
        }
        return l;
    }

    public void addBear(String type) {
        if (this.bearSkills != null) {
            this.bearSkills.add(type);
        }
    }

    public boolean isBear(String type) {
        return this.bearSkills != null && this.bearSkills.contains(type);
    }

    public List<String> getBearSkills() {
        return this.bearSkills;
    }

    public void removeBear() {
        if (this.bearSkills != null) {
            this.bearSkills.clear();
        }
    }

    public FightingSkill getZDSKILL(int id, int sld) {
        int skillId = id;
        for (int i = this.skills.size() - 1; i >= 0; --i) {
            if (((FightingSkill) this.skills.get(i)).getSkillid() == id) {
                return (FightingSkill) this.skills.get(i);
            }
        }
        if (id == 100) {
            skillId = 1030;
        } else if (id == 101) {
            skillId = 1035;
        } else if (id == 102) {
            skillId = 1205;
        } else if (id == 103) {
            skillId = 1203;
        } else if (id == 104) {
            skillId = 1056;
        }
        Skill skill = GameServer.getSkill(skillId + "");
        if (skill != null) {
            FightingSkill fightingSkill = new FightingSkill(skill, this.lvl, 0, (double) sld, (long) sld, 1);
            if (id >= 100 && id <= 104) {
                fightingSkill.setSkillid(id);
                fightingSkill.setSkillsum(10);
                fightingSkill.setSkillcontinued(100);
                if (id == 104) {
                    fightingSkill.setSkillhurt(fightingSkill.getSkillhurt() + 300000.0);
                }
            }
            fightingSkill.setSkillblue(0);
            this.skills.add(fightingSkill);
            return fightingSkill;
        } else {
            return null;
        }
    }

    public int getSr() {
        return this.sr;
    }

    public void setSr(int sr) {
        this.sr = sr;
    }

    public List<AI> getAis() {
        return this.ais;
    }

    public void setAis(List<AI> ais) {
        this.ais = ais;
    }

    public void addyq(int v, FightingState org) {
        int maxyq = 500;
        FightingSkill TY_RH_NHNT = this.getSkillType(TypeUtil.TY_RH_NHNT);
        if (TY_RH_NHNT != null) {
            maxyq += (int) TY_RH_NHNT.getSkillhurt();
        }
        if (this.yqz + v > maxyq) {
            v = maxyq - this.yqz;
        } else if (this.yqz + v < 0) {
            v = -this.yqz;
        }
        this.yqz += v;
        org.setYq_c(v);
    }

    public void addnq(int v, FightingState org) {
        if (this.type == 0) {
            if (this.nqz + v > 100) {
                v = 100 - this.nqz;
            } else if (this.nqz + v < 0) {
                v = -this.nqz;
            }
            this.nqz += v - (int) ((double) (v * this.nqzad) / 100.0);
            org.setNq_c(v - (int) ((double) (v * this.nqzad) / 100.0));
        }
    }

    public void addnq1(int v, FightingState org) {
        if (this.type == 0) {
            if (this.nqz + v > 100) {
                v = 100 - this.nqz;
            } else if (this.nqz + v < 0) {
                v = -this.nqz;
            }
            org.setNq_c(v);
        }
    }

    public void getnqzad(int v) {
        this.nqzad = v;
    }

    public int getlvl() {
        return this.lvl + this.zs * 25;
    }

    public int getYqz() {
        return this.yqz;
    }

    public int getNqz() {
        return this.nqz;
    }

    public long getMoney() {
        return this.money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public int getSpellSum() {
        return this.spellSum;
    }

    public void setSpellSum(int spellSum) {
        this.spellSum = spellSum;
    }

    public double getSpellJC() {
        double xs = 1.0;
        FightingSkill skill = this.getSkillType(TypeUtil.TZ_SSBX);
        if (skill != null) {
            ++this.spellSum;
            if (this.spellSum > 5) {
                this.spellSum = 1;
            }
            xs += skill.getSkillhurt() / 100.0 * (double) this.spellSum;
        }
        for (int i = 0; i <= this.skills.size() - 1; ++i) {
            if (((FightingSkill) this.skills.get(i)).getSkillid() == 8048) {
                skill = (FightingSkill) this.skills.get(i);
                if (skill != null) {
                    xs *= 0.8 + (double) Battlefield.random.nextInt(50) / 100.0;
                }
            }
        }
        return xs;
    }

    public double getWGTB() {
        FightingSkill skill = this.getSkillType(TypeUtil.TZ_WGTB);
        if (skill != null) {
            double lx = this.shanghai;
            AddState addState = this.xzstate(TypeUtil.TY_GH_QYBY);
            if (addState != null) {
                lx -= lx * addState.getStateEffect() / 100.0;
            }
            if (lx > 1209.0) {
                lx = 1209.0;
            }
            if (lx >= 609.0) {
                return lx * (skill.getSkillhitrate() + lx * skill.getSkillhurt()) / 2.0;
            }
        }
        return 0.0;
    }

    public FightingState endState(Battlefield battlefield) {
        FightingState fightingState = null;
        List<AddState> rAddStates = null;
        boolean is = false;
        for (int i = this.addStates.size() - 1; i >= 0; --i) {
            AddState addState = (AddState) this.addStates.get(i);
            if (addState.isEnd()) {
                this.addStates.remove(i);
                String statename = addState.getStatename();
                if (addState.getSkills() != null || statename.equals("扶摇") || statename.equals(TypeUtil.L_LL) || statename.equals(TypeUtil.SH_4010) || statename.equals(TypeUtil.TY_L_GL_PYGQ) || statename.equals("无敌")) {
                    if (rAddStates == null) {
                        rAddStates = new ArrayList<>();
                    }
                    rAddStates.add(addState);
                }
                if (!statename.equals("冷却")) {
                    if (statename.equals("乾坤破阵")) {
                        fightingState = new FightingState();
                        ChangeFighting changeFightinghf = new ChangeFighting();
                        int v = this.getHp() / 2;
                        changeFightinghf.setChangehp(-v);
                        this.ChangeData(changeFightinghf, fightingState);
                        fightingState.setEndState_2(statename);
                    } else if (statename.equals("1243")) {
                        fightingState = new FightingState();
                        fightingState.setStartState("代价");
                        fightingState.setCamp(this.getCamp());
                        fightingState.setMan(this.getMan());
                        fightingState.setEndState_2("1241");
                    } else if (statename.equals("7028")) {
                        fightingState = new FightingState();
                        fightingState.setStartState("代价");
                        fightingState.setCamp(this.getCamp());
                        fightingState.setMan(this.getMan());
                        fightingState.setEndState_2("7208");
                        this.setSkills(addState.getSkills());
                    } else if (statename.equals(TypeUtil.TZ_YGZG)) {
                        this.Quality.addkr(addState.getStateEffect());
                        this.Quality.addks(addState.getStateEffect2());
                    } else if (statename.equals(TypeUtil.TZ_PHQY)) {
                        this.Quality.addkr(addState.getStateEffect());
                    } else if (statename.equals(TypeUtil.TZ_XYXG)) {
                        this.Quality.addks(addState.getStateEffect());
                    } else if (statename.equals(TypeUtil.BB_E_XYLX)) {
                        this.Quality.addks(-40.0);
                    } else if (statename.equals("遗忘")) {
                        for (AddState addState1 : this.addStates) {
                            if (addState1.getStatename().equals("忘情石")) {
                                addState1.setSurplus(0);
                                is = true;
                            }
                        }
                    } else if (statename.equals("斗魂幡")) {
                        for (FightingSkill fightingSkill : this.skills) {
                            if (fightingSkill.lingshisld != 0.0D) {
                                fightingSkill.lingshisld = 0.0D;
                            }
                        }
                    } else if (statename.equals("仙法狂暴")) {
                        this.sp -= 600;
                        this.Quality.kuangbao(-1);
                    } else if (statename.equals("物理狂暴")) {
                        this.sp -= 600;
                        for (int j = this.skills.size() - 1; j >= 0; --j) {
                            int id = ((FightingSkill) this.skills.get(i)).getSkillid();
                            if (id == 1831 || id == 1833) {
                                this.skills.remove(i);
                            }
                        }
                    } else if (statename.equals("9857")) {
                        this.Quality.addhshl(addState, false);
                    } else if (statename.equals("9860")) {
                        this.Quality.addqhl(addState, false);
                    } else if (statename.equals(TypeUtil.TY_R_SMMF)) {
                        this.Quality.addhsfy(addState, false);
                    } else if (statename.equals(TypeUtil.TY_R_HRRM)) {
                        this.Quality.addhshs(addState, false);
                    } else if (statename.equals("灵识_毒")) {
                        this.Quality.addlszt_d(addState, false);
                    } else if (statename.equals("灵识_睡")) {
                        this.Quality.addlszt_s(addState, false);
                    } else if (statename.equals("灵识_封")) {
                        this.Quality.addlszt_f(addState, false);
                    } else if (statename.equals("灵识_混")) {
                        this.Quality.addlszt_h(addState, false);
                    } else if (statename.equals("10080")) {
                        for (int k = 0; k < this.skills.size(); ++k) {
                            FightingSkill skill2 = (FightingSkill) this.skills.get(k);
                            if (skill2 != null && skill2.getSkillid() >= 1001 && skill2.getSkillid() <= 1100) {
                                ((FightingSkill) this.skills.get(k)).setSkillsld(((FightingSkill) this.skills.get(k)).getSkillsld() - addState.getStateEffect());
                            }
                        }
                    } else if (statename.equals("7027")) {
                        List<FightingSkill> noMySkills = addState.getSkills2();
                        for (FightingSkill noMySkill : noMySkills) {
                            this.skills.remove(noMySkill);
                        }
                        List<FightingSkill> mySkills = addState.getSkills();
                        for (FightingSkill mySkill : mySkills) {
                            this.skills.add(mySkill);
                        }
                    }
                    if (fightingState == null) {
                        fightingState = new FightingState();
                        fightingState.setCamp(this.camp);
                        fightingState.setMan(this.man);
                        fightingState.setStartState("代价");
                    }
                    fightingState.setEndState_2(statename);
                } else if (addState.getStatename().equals("锁元珠") && addState.getStateEffect4() == 0.0D &&
                        addState.getSurplus() < 998) {
                    if (fightingState == null) {
                        fightingState = new FightingState();
                        fightingState.setCamp(this.camp);
                        fightingState.setMan(this.man);
                    }
                    UP(fightingState, 0, addState.getStateEffect() * addState.getStateEffect2() * (1.0D + addState.getStateEffect3() / 100.0D) / 100.0D);
                    UP(fightingState, 0, -addState.getStateEffect() * addState.getStateEffect2() / 100.0D);
                    addState.setStateEffect4(1.0D);
                }
            }
        }
        if (fightingState != null && rAddStates != null) {
            MixDeal.rid(this, fightingState, rAddStates);
        }
        this.skill_ss_mp.clear();
        this.skill_ss_mp.putAll(this.skill_ss_tmp_mp);
        this.skill_ss_tmp_mp.clear();
        return fightingState;
    }

    public FightingSkill getFeedback() {
        for (int i = this.skills.size() - 1; i >= 0; --i) {
            FightingSkill skill = (FightingSkill) this.skills.get(i);
            if ((skill.getSkilltype().equals(TypeUtil.BB_CWFB) || skill.getSkilltype().equals(TypeUtil.BB_FBZS)) && skill.getSkillhitrate() > (double) Battlefield.random.nextInt(100)) {
                return skill;
            }
        }
        return null;
    }

    public AddState getGainState() {
        AddState addState = null;
        for (int i = this.addStates.size() - 1; i >= 0; --i) {
            if (((AddState) this.addStates.get(i)).getType() == 0 && (addState == null || !Battlefield.isV(40.0))) {
                addState = (AddState) this.addStates.get(i);
            }
        }
        return addState;
    }

    public AddState getControlState() {
        for (int i = this.addStates.size() - 1; i >= 0; --i) {
            AddState addState = (AddState) this.addStates.get(i);
            if (addState.getStatename().equals("封印") || addState.getStatename().equals("昏睡") || addState.getStatename().equals("遗忘") || addState.getStatename().equals(TypeUtil.BB_SS) || addState.getStatename().equals("混乱")) {
                return addState;
            }
        }
        return null;
    }

    public boolean RemoveNegativeState(FightingState state) {
        for (int i = this.addStates.size() - 1; i >= 0; --i) {
            AddState addState = (AddState) this.addStates.get(i);
            String statename = addState.getStatename();
            for (int j = 0; j < ManData.values2.length; ++j) {
                if (statename.equals(ManData.values2[j])) {
                    this.RemoveAbnormal(state, new String[]{statename});
                    state.setEndState_2(statename);
                    return true;
                }
            }
        }
        return false;
    }

    public FightingSkill getAppendSkill(int id) {
        for (int i = this.addStates.size() - 1; i >= 0; --i) {
            FightingSkill skill = ((AddState) this.addStates.get(i)).getSkill(id);
            if (skill != null) {
                return skill;
            }
        }
        return null;
    }

    public List<FightingSkill> getAppendSkillByType(String type, List<Integer> ids) {
        AddState state = this.xzstate(type);
        if (state != null) {
            return state.getSkills(ids);
        }
        return new ArrayList<>();
    }

    public FightingSkill getAppendSkill(String id) {
        if (StringUtils.isNotBlank(id)) {
            return this.getAppendSkill(Integer.parseInt(id));
        }
        return null;
    }

    public List<FightingSkill> getAppendSkillByType(String type, String[] ids) {
        if (StringUtils.isNotBlank(type) && ids != null) {
            List<Integer> idList = new ArrayList<>();
            for (String id : ids) {
                if (StringUtils.isNotBlank(id)) {
                    idList.add(Integer.valueOf(id));
                }
            }
            return this.getAppendSkillByType(type, idList);
        } else {
            return new ArrayList<>();
        }
    }

    public void RemoveAppendSkill(int id) {
        int i = this.addStates.size() - 1;
        while (i >= 0) {
            FightingSkill skill = ((AddState) this.addStates.get(i)).getSkill(id);
            if (skill != null) {
                ((AddState) this.addStates.get(i)).getSkills().remove(skill);
                if (((AddState) this.addStates.get(i)).getSkills().size() == 0) {
                    ((AddState) this.addStates.get(i)).setSkills(null);
                }
                return;
            } else {
                --i;
            }
        }
    }

    public BigDecimal getSe() {
        return this.se;
    }

    public void setSe(BigDecimal se) {
        this.se = se;
    }

    public BigDecimal getXk_id() {
        return this.xk_id;
    }

    public void setXk_id(BigDecimal xk_id) {
        this.xk_id = xk_id;
    }

    public BaseStar getBaseStar() {
        return this.baseStar;
    }

    public void setBaseStar(BaseStar baseStar) {
        this.baseStar = baseStar;
    }

    public Double getExpXS() {
        if (this.expXS == null) {
            this.expXS = Double.valueOf(1.0);
        }
        return this.expXS;
    }

    public void setExpXS(Double expXS) {
        this.expXS = expXS;
    }

    public Double getExp2XS() {
        return this.exp2XS;
    }

    public void setExp2XS(Double exp2xs) {
        this.exp2XS = exp2xs;
    }

    public String getLingXi() {
        return this.lingxi;
    }

    public void setLingXi(String lingxi) {
        this.lingxi = lingxi;
    }

    public AddAttack getAttacks(String type) {
        return (AddAttack) this.initAttacks().get(type);
    }

    public Map<String, AddAttack> initAttacks() {
        if (this.attacks == null) {
            this.attacks = new HashMap<>();
            for (int i = this.skills.size() - 1; i >= 0; --i) {
                FightingSkill skill = (FightingSkill) this.skills.get(i);
                String skillname = skill.getSkillname();
                String skilltype = skill.getSkilltype();
                if (skillname.equals("乘风破浪") || skillname.equals("霹雳流星") || skillname.equals("大海无量") || skillname.equals("祝融取火")) {
                    this.attacks.put(skillname, new AddAttack(skillname, skill, new FightingSkill[0]));
                    this.skills.remove(i);
                } else if (skilltype.equals(TypeUtil.BB_LHFM)) {
                    this.attacks.put(skilltype, new AddAttack(skilltype, skill, new FightingSkill[0]));
                    this.skills.remove(i);
                } else if (skillname.equals("浩然正气")) {
                    FightingSkill skill2 = this.getSkillType(TypeUtil.BB_E_YNYD);
                    if (skill2 != null) {
                        this.attacks.put(skillname, new AddAttack(skillname, skill, new FightingSkill[]{skill2}));
                    } else {
                        this.attacks.put(skillname, new AddAttack(skillname, skill, new FightingSkill[0]));
                    }
                    this.skills.remove(i);
                } else if (skillname.equals("隔山打牛")) {
                    FightingSkill skill2 = this.getSkillType(TypeUtil.BB_E_LWKD);
                    if (skill2 != null) {
                        this.attacks.put(skillname, new AddAttack(skillname, skill, new FightingSkill[]{skill2}));
                    } else {
                        this.attacks.put(skillname, new AddAttack(skillname, skill, new FightingSkill[0]));
                    }
                    this.skills.remove(i);
                } else if (skillname.equals("暗影离魂") || skillname.equals("亢龙有悔")) {
                    this.attacks.put(skillname, new AddAttack(skillname, skill, new FightingSkill[0]));
                    this.skills.remove(i);
                }
            }
        }
        return this.attacks;
    }

    public void UP(FightingState state, int type, double xs) {
        if (xs == 0.0) {
            return;
        }
        if (this.original == null) {
            this.original = new Original(this.hp_z, this.mp_z, this.model);
        }
        if (type == 0) {
            this.hp_z = (int) ((double) this.original.getHp_z() * this.original.upXS(0, xs));
            if (this.hp_z <= 0) {
                this.hp_z = 1;
            }
            state.setUp("HP", this.hp_z);
            if (this.hp > this.hp_z) {
                state.setHp_Change(this.hp_z - this.hp);
                this.hp = this.hp_z;
            }
        } else if (type == 1) {
            this.mp_z = (int) ((double) this.original.getMp_z() * this.original.upXS(1, xs));
            if (this.mp_z <= 0) {
                this.mp_z = 1;
            }
            state.setUp("MP", this.mp_z);
            if (this.mp > this.mp_z) {
                state.setHp_Change(this.mp_z - this.mp);
                this.mp = this.mp_z;
            }
        }
    }

    public void UPModel(FightingState state, String skin) {
        if (this.original == null) {
            this.original = new Original(this.hp_z, this.mp_z, this.model);
        }
        if (skin != null) {
            state.setSkin(this.model = skin);
        } else {
            state.setSkin(this.model = this.original.getModel());
        }
    }

    public void TY_L_GL_MYJS() {
        for (int i = this.addStates.size() - 1; i >= 0; --i) {
            AddState addState = (AddState) this.addStates.get(i);
            if (addState.getStatename().equals("冷却") && addState.getStateEffect() >= 9000.0 && addState.getStateEffect() < 10167.0) {
                this.addStates.remove(i);
            }
        }
    }

    public void addSkillIfConditionMet(String skillName, double value) {
        if (this.skill_ss_tmp_mp.containsKey(skillName)) {
            this.skill_ss_tmp_mp.put(skillName, Double.valueOf(value));
        }
    }

    public Double containsSkillIfConditionMet(String skillName) {
        return (Double) this.skill_ss_mp.remove(skillName);
    }

    public RoleSummoning getPet(boolean isH, boolean isM, boolean isHS, boolean isMS) {
        int i = this.pets.size() - 1;
        while (i >= 0) {
            FightingSummon summon = (FightingSummon) this.pets.get(i);
            if (summon.getPlay() == 1 || summon.getPlay() == 2) {
                RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(summon.getHang().getId());
                if (pet != null) {
                    if (isH) {
                        pet.setBasishp(summon.getPet().getHp());
                    } else {
                        pet.setBasishp(summon.getPet().getHp_z());
                    }
                    if (isHS) {
                        pet.setBasishp(summon.getPet().getHp());
                    } else {
                        pet.setBasishp(summon.getPet().getHp_z());
                    }
                    if (isM) {
                        pet.setBasismp(summon.getPet().getMp());
                    } else {
                        pet.setBasismp(summon.getPet().getMp_z());
                    }
                    if (isMS) {
                        pet.setBasismp(summon.getPet().getMp());
                    } else {
                        pet.setBasismp(summon.getPet().getMp_z());
                    }
                }
                return pet;
            } else {
                --i;
            }
        }
        return null;
    }

    public void initLXBuff() {
        AddState zt = this.xzstate("11006");
        if (zt != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(zt.getStatename(), 1, (int) zt.getStateEffect()))) {
            this.mz += (double) LingXiUtil.getNumberBySkillId(zt.getStatename(), 2, (int) zt.getStateEffect());
        }
        zt = this.xzstate("11007");
        if (zt != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(zt.getStatename(), 1, (int) zt.getStateEffect()))) {
            this.hsfz += (double) LingXiUtil.getNumberBySkillId(zt.getStatename(), 2, (int) zt.getStateEffect());
        }
        zt = this.xzstate("战意");
        if (zt != null) {
            this.mz += zt.getStateEffect();
            this.hsfz += zt.getStateEffect();
        }
        zt = this.xzstate("破甲");
        if (zt != null) {
            this.mz += zt.getStateEffect();
            this.hsfz += zt.getStateEffect();
            if (zt.getStatename().equals("破甲")) {
                this.pwlcd += zt.getStateEffect() * 3.0;
                this.pwljl += zt.getStateEffect() * 3.0;
                this.getAddStates().remove(zt);
                int count = (int) zt.getStateEffect() - 1;
                if (count > 0) {
                    zt.setStateEffect((double) count);
                    zt.setType(1);
                    zt.setStatename("破甲");
                    zt.setSurplus(50);
                    this.getAddStates().add(zt);
                } else {
                    zt.setStateEffect(0.0);
                    zt.setType(1);
                    zt.setStatename("破甲");
                    zt.setSurplus(0);
                    this.getAddStates().add(zt);
                }
            }
        }
    }

    public void addDun(long ap, FightingState ace) {
        if (this.getStates() != 0) {
            return;
        }
        AddState zt = this.xzstate("11010");
        if (zt != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId("11010", 1, (int) zt.getStateEffect()))) {
            int dun = LingXiUtil.getNumberBySkillId("11010", 2, (int) zt.getStateEffect());
            long hp = ap * (long) dun / 100L;
            int currHP = this.getHp();
            int maxHP = this.getHp_z();
            int queshao = maxHP - currHP;
            if (queshao > 0) {
                ChangeFighting fighting = new ChangeFighting();
                if ((long) queshao > hp) {
                    fighting.setChangehp((int) hp);
                    hp = 0L;
                } else {
                    fighting.setChangehp(queshao);
                    hp -= (long) queshao;
                }
                this.ChangeData(fighting, ace);
            }
            if (hp > 0L) {
                AddState addState = this.xzstate("化险为夷");
                if (addState == null) {
                    addState = new AddState();
                    addState.setStateEffect((hp > (long) maxHP) ? ((double) maxHP) : ((double) hp));
                } else {
                    this.getAddStates().remove(addState);
                    hp = (long) ((double) hp + addState.getStateEffect());
                    addState.setStateEffect((hp > (long) maxHP) ? ((double) maxHP) : ((double) hp));
                }
                addState.setType(1);
                addState.setStatename("化险为夷");
                addState.setSurplus(3);
                this.getAddStates().add(addState);
            }
        }
    }

    public void addZhanyi(List<FightingState> xieStates, FightingState fightingState) {
        if (this.getStates() != 0) {
            return;
        }
        int idx = 1;
        AddState zt = this.xzstate("11012");
        if (zt == null) {
            idx = 2;
            zt = this.xzstate("11013");
        }
        if (zt != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(zt.getStatename(), 1, (int) zt.getStateEffect()))) {
            AddState addState = this.xzstate((idx == 1) ? "战意" : "破甲");
            if (addState == null) {
                addState = new AddState();
                addState.setStateEffect(2.0);
            } else {
                this.getAddStates().remove(addState);
                int count = (int) addState.getStateEffect() + 2;
                addState.setStateEffect((count > 12) ? 12.0 : ((double) count));
            }
            addState.setType(1);
            addState.setStatename((idx == 1) ? "战意" : "破甲");
            addState.setSurplus(50);
            this.getAddStates().add(addState);
            if (fightingState != null) {
                fightingState.setEndState_1((idx == 1) ? "战意" : "破甲");
            } else {
                fightingState = new FightingState();
                fightingState.setCamp(this.camp);
                fightingState.setMan(this.man);
                fightingState.setEndState_1((idx == 1) ? "战意" : "破甲");
                xieStates.add(fightingState);
            }
        }
    }

    public int executeHmsz(List<FightingState> xieStates) {
        int result = 0;
        int idx = 1;
        AddState zt = this.xzstate("11018");
        if (zt == null) {
            idx = 2;
            zt = this.xzstate("11019");
        }
        if (zt != null) {
            int count = 0;
            if (Battlefield.isV((double) LingXiUtil.getNumberBySkillId(zt.getStatename(), 1, (int) zt.getStateEffect()))) {
                AddState addState = this.xzstate((idx == 1) ? "战意" : "破甲");
                if (addState == null) {
                    addState = new AddState();
                    addState.setStateEffect(2.0);
                } else {
                    this.getAddStates().remove(addState);
                    count = (int) addState.getStateEffect() + 2;
                    addState.setStateEffect((count > 12) ? 12.0 : ((double) count));
                }
                addState.setType(1);
                addState.setStatename((idx == 1) ? "战意" : "破甲");
                addState.setSurplus(50);
                this.getAddStates().add(addState);
                FightingState fightingState = new FightingState();
                fightingState.setCamp(this.camp);
                fightingState.setMan(this.man);
                fightingState.setEndState_1((idx == 1) ? "战意" : "破甲");
                xieStates.add(fightingState);
            }
            if (this.cd == 0 && count >= 12) {
                int val = LingXiUtil.getNumberBySkillId(zt.getStatename(), 2, (int) zt.getStateEffect());
                this.mz += (double) val;
                this.hsfz += (double) val;
                result = idx;
                this.cd = 5;
            }
        }
        return result;
    }

    public void executeJdfj(List<FightingState> xieStates) {
        AddState zt = this.xzstate("11017");
        if (zt != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(zt.getStatename(), 1, (int) zt.getStateEffect()))) {
            AddState addState = this.xzstate("破甲");
            if (addState == null) {
                addState = new AddState();
                addState.setStateEffect(2.0);
            } else {
                this.getAddStates().remove(addState);
                int count = (int) addState.getStateEffect() + 2;
                addState.setStateEffect((count > 12) ? 12.0 : ((double) count));
            }
            addState.setType(1);
            addState.setStatename("破甲");
            addState.setSurplus(50);
            this.getAddStates().add(addState);
            FightingState fightingState = new FightingState();
            fightingState.setCamp(this.camp);
            fightingState.setMan(this.man);
            fightingState.setEndState_1("破甲");
            xieStates.add(fightingState);
        }
    }

    public static void main(String[] args) {
        System.out.println(2L);
        System.out.println(3L);
    }

    public boolean executeZhanyi() {
        AddState zt = this.xzstate("战意");
        if (zt != null && (Battlefield.isV(zt.getStateEffect() * 3.5) || (this.cd == 5 && zt.getStateEffect() == 12.0 && this.xzstate("11018") != null))) {
            this.getAddStates().remove(zt);
            int count = (int) zt.getStateEffect() - 2;
            if (count > 0) {
                zt.setStateEffect((double) count);
                zt.setType(1);
                zt.setStatename("战意");
                zt.setSurplus(50);
                this.getAddStates().add(zt);
            } else {
                zt.setStateEffect(0.0);
                zt.setType(1);
                zt.setStatename("战意");
                zt.setSurplus(0);
                this.getAddStates().add(zt);
            }
            if (this.cd == 5) {
                --this.cd;
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean executeNbkj(int count) {
        if (count > 2) {
            return false;
        }
        AddState nbkj = this.xzstate("11014");
        if (nbkj != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(nbkj.getStatename(), 1, (int) nbkj.getStateEffect()))) {
            this.kb += (double) LingXiUtil.getNumberBySkillId(nbkj.getStatename(), 2, (int) nbkj.getStateEffect());
            return true;
        }
        return false;
    }

    public int executeQtpl(ManData noData, List<FightingState> zls) {
        if (noData.getHp() < 300000) {
            return 0;
        }
        AddState qtpl = this.xzstate("11015");
        if (qtpl != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(qtpl.getStatename(), 1, (int) qtpl.getStateEffect()))) {
            FightingState ace = new FightingState();
            ace.setCamp(this.camp);
            ace.setMan(this.man);
            ace.setText("看我的晴天霹雳");
            zls.add(0, ace);
            return LingXiUtil.getNumberBySkillId(qtpl.getStatename(), 2, (int) qtpl.getStateEffect());
        }
        return 0;
    }

    public void executeRfzs(ManData noman, FightingState ace, List<FightingState> zls, Battlefield battlefield) {
        FightingSkill skill = this.skillname("如释重负");
        if (skill != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl()))) {
            String state = LingXiUtil.BUFF[Battlefield.random.nextInt(4)];
            noman.RemoveAbnormal(new String[]{state});
            ace.setEndState_2(state);
        }
        skill = this.skillname("耳聪目明");
        if (skill != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl()))) {
            List<ManData> datas = battlefield.getZWYS(noman);
            Iterator<ManData> iterator = datas.iterator();
            if (iterator.hasNext()) {
                ManData data = (ManData) iterator.next();
                FightingState manstate = new FightingState();
                manstate.setCamp(this.camp);
                manstate.setMan(this.man);
                manstate.setText("看我的耳聪目明");
                zls.add(manstate);
                data.RemoveAbnormal(new String[]{"隐身"});
                FightingState org = new FightingState();
                org.setCamp(data.getCamp());
                org.setMan(data.getMan());
                org.setEndState_2("隐身");
                zls.add(org);
                return;
            }
        }
    }

    public void executeLhcj() {
        FightingSkill skill = this.findRemoveSkill("连环出击");
        if (skill != null) {
            double gl = 0.0;
            if (this.kangluobao >= 450.0) {
                AddState zt = this.xzstate("11025");
                if (zt != null) {
                    gl = (double) LingXiUtil.getNumberBySkillId(zt.getStatename(), 1, (int) zt.getStateEffect());
                }
            }
            if (Battlefield.isV((double) LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl()) + gl)) {
                this.ljv += LingXiUtil.getNumberBySkillId(skill.getSkillname(), 2, skill.getSkilllvl());
            }
        }
    }

    public void executeYztc(Battlefield battlefield, FightingState fightingState) {
        FightingSkill skill = this.findRemoveSkill("与子同仇");
        if (skill != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl()))) {
            AddState addState = this.xzstate("战意");
            if (addState == null) {
                addState = new AddState();
                addState.setStateEffect(3.0);
            } else {
                this.getAddStates().remove(addState);
                int count = (int) addState.getStateEffect() + 3;
                addState.setStateEffect((count > 12) ? 12.0 : ((double) count));
            }
            addState.setType(1);
            addState.setStatename("战意");
            addState.setSurplus(50);
            this.getAddStates().add(addState);
            if (fightingState != null) {
                fightingState.setEndState_1("战意");
            } else {
                List<FightingState> zls = new ArrayList<>();
                FightingState ace = new FightingState();
                ace.setCamp(this.getCamp());
                ace.setMan(this.getMan());
                ace.setEndState_1("战意");
                zls.add(ace);
                FightingEvents event = new FightingEvents();
                event.setAccepterlist(zls);
                battlefield.NewEvents.add(event);
            }
        }
    }

    public int executeJtpa(List<FightingState> zls, int maxg) {
        if (this.kangluobao >= 450.0) {
            AddState zt = this.xzstate("11025");
            if (zt != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(zt.getStatename(), 2, (int) zt.getStateEffect()))) {
                FightingState manstate = new FightingState();
                manstate.setCamp(this.camp);
                manstate.setMan(this.man);
                manstate.setText("看我的惊涛拍岸");
                zls.add(manstate);
                this.jtpa = true;
                int cs = LingXiUtil.getNumberBySkillId(zt.getStatename(), 4, (int) zt.getStateEffect());
                return (maxg < cs) ? maxg : cs;
            }
        }
        this.jtpa = false;
        return maxg;
    }

    public double getJtpaXs(double xs) {
        AddState zt = this.xzstate("11025");
        if (zt != null) {
            return (double) LingXiUtil.getNumberBySkillId(zt.getStatename(), 5, (int) zt.getStateEffect()) / 100.0;
        }
        return xs;
    }

    public boolean getJtpaGs() {
        AddState zt = this.xzstate("11025");
        return zt != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(zt.getStatename(), 3, (int) zt.getStateEffect()));
    }

    public boolean executeYbwh(int idx) {
        FightingSkill skill = this.skillname("有备无患");
        if (skill != null) {
            if (idx == 3) {
                return Battlefield.isV(60.0);
            }
            return Battlefield.isV((double) LingXiUtil.getNumberBySkillId(skill.getSkillname(), idx, skill.getSkilllvl()));
        } else {
            return false;
        }
    }

    public void executeJgbg(List<FightingState> zls) {
        FightingSkill skill = this.skillname("将功补过");
        if (skill != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl()))) {
            AddState addState = this.xzstate("战意");
            if (addState == null) {
                addState = new AddState();
                addState.setStateEffect(3.0);
            } else {
                this.getAddStates().remove(addState);
                int count = (int) addState.getStateEffect() + 3;
                addState.setStateEffect((count > 12) ? 12.0 : ((double) count));
            }
            addState.setType(1);
            addState.setStatename("战意");
            addState.setSurplus(50);
            this.getAddStates().add(addState);
            if (zls.size() > 0 && ((FightingState) zls.get(zls.size() - 1)).getStartState().equals("移动")) {
                ((FightingState) zls.get(zls.size() - 1)).setEndState_1("战意");
            } else {
                FightingState fightingState = new FightingState();
                fightingState.setCamp(this.camp);
                fightingState.setMan(this.man);
                fightingState.setEndState_1("战意");
                zls.add(fightingState);
            }
        }
    }

    public void executeYwwq(FightingState fightingState) {
        FightingSkill skill = this.skillname("一往无前");
        if (skill != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl()))) {
            AddState addState = this.xzstate("破甲");
            if (addState == null) {
                addState = new AddState();
                addState.setStateEffect(2.0);
            } else {
                this.getAddStates().remove(addState);
                int count = (int) addState.getStateEffect() + 2;
                addState.setStateEffect((count > 12) ? 12.0 : ((double) count));
            }
            addState.setType(1);
            addState.setStatename("破甲");
            addState.setSurplus(50);
            this.getAddStates().add(addState);
            fightingState.setEndState_1("破甲");
        }
    }

    public boolean executeJtcl(Battlefield battlefield) {
        if (this.kangluobao >= 450.0 && battlefield.CurrentRound > 3) {
            FightingSkill skill = this.findRemoveSkill("卷土重来");
            if (skill != null) {
                double gl = 0.0;
                AddState addState = this.xzstate("战意");
                if (addState != null) {
                    gl = addState.getStateEffect() * 1.1;
                }
                if (Battlefield.isV((double) LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl()) + gl)) {
                    return this.jtcl = true;
                }
            }
        }
        AddState addState2 = this.xzstate("兽魂俯首");
        if (addState2 != null) {
            int fmId = (int) addState2.getStateEffect();
            int fmSld = (int) addState2.getStateEffect2();
            if (Battlefield.isV(FaMenUtil.getDouble(fmId, fmSld, 2))) {
                return this.fhft = true;
            }
        }
        return false;
    }

    public void loadLingXiSkill() {
        this.hp = this.hp_z;
        this.mp = this.mp_z;
        this.clearBuff();
        if (this.lingxi == null || this.lingxi.equals("")) {
            return;
        }
        List<Skill> skills = LingXiUtil.getLingXiSkill(this.lingxi);
        for (Skill skill : skills) {
            if (skill != null) {
                this.findRemoveSkill(skill.getSkillname());
                if (this.jtcl && "卷土重来".equals(skill.getSkillname())) {
                    this.jtcl = false;
                    continue;
                } else {
                    FightingSkill fightingSkill = new FightingSkill(skill, this.lvl, this.zs, (double) (this.lvl * 50), 0L, 0);
                    fightingSkill.setSkillbeidong(1);
                    this.addSkill(fightingSkill);
                    if (skill.getSkillid() == 11006 || skill.getSkillid() == 11007 || skill.getSkillid() == 11010 || skill.getSkillid() == 11012 || skill.getSkillid() == 11013 || skill.getSkillid() == 11014 || skill.getSkillid() == 11015 || skill.getSkillid() == 11017 || skill.getSkillid() == 11018 || skill.getSkillid() == 11019 || skill.getSkillid() == 11025) {
                        AddState addState = new AddState();
                        addState.setType(1);
                        addState.setStatename(skill.getSkillid() + "");
                        addState.setStateEffect((double) skill.getSkilllevel());
                        addState.setSurplus(3);
                        this.addStates.add(addState);
                    } else {
                        continue;
                    }
                }
            }
        }
    }

    public double getBlcxKc(int type) {
        if (this.kangluobao >= 450.0) {
            FightingSkill skill = this.skillname("奋不顾身");
            if (skill != null) {
                return (double) LingXiUtil.getNumberBySkillId(skill.getSkillname(), type, skill.getSkilllvl()) / 100.0;
            }
        }
        return (type == 1) ? 0.5 : 0.2;
    }

    public int executeFbgs(int idx, FightingState org) {
        if (idx == 7 && this.skillname("兵临城下") == null) {
            return 0;
        }
        if (this.kangluobao >= 450.0) {
            FightingSkill skill = this.skillname("奋不顾身");
            if (skill != null) {
                if (idx == 0) {
                    String state = (Battlefield.random.nextInt(10) > 4) ? "战意" : "破甲";
                    AddState addState = this.xzstate(state);
                    if (addState == null) {
                        addState = new AddState();
                        addState.setStateEffect(2.0);
                    } else {
                        this.getAddStates().remove(addState);
                        int count = (int) addState.getStateEffect() + 2;
                        addState.setStateEffect((count > 12) ? 12.0 : ((double) count));
                    }
                    addState.setType(1);
                    addState.setStatename(state);
                    addState.setSurplus(50);
                    this.getAddStates().add(addState);
                    org.setText("看我的奋不顾身");
                    org.setEndState_1(state);
                } else {
                    return LingXiUtil.getNumberBySkillId(skill.getSkillname(), idx, skill.getSkilllvl());
                }
            }
        }
        return 0;
    }

    public void executeBmll() {
        AddState zt = this.xzstate("11027");
        if (zt != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(zt.getStatename(), 1, (int) zt.getStateEffect()))) {
            this.qf += (double) LingXiUtil.getNumberBySkillId(zt.getStatename(), 2, (int) zt.getStateEffect()) / 100.0;
        }
    }

    public boolean executeYmjr() {
        FightingSkill skill = this.skillname("一鸣惊人");
        return skill != null && Battlefield.isV(LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl()) + 25);//我把5改成了25是不是加了20几率
    }

    public int executeDksj(int idx) {
        FightingSkill skill = this.skillname("大开杀戒");
        if (skill != null) {
            return LingXiUtil.getNumberBySkillId(skill.getSkillname(), idx, skill.getSkilllvl());
        }
        return 0;
    }

    public int executeQyzs() {
        FightingSkill skill = this.skillname("青云直上");
        if (skill != null) {
            return LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl());
        }
        return 0;
    }

    public int executeZxcg(int idx) {
        FightingSkill skill = this.skillname("锥心刺骨");
        if (skill != null) {
            return LingXiUtil.getNumberBySkillId(skill.getSkillname(), idx, skill.getSkilllvl());
        }
        return 0;
    }

    public boolean executeAbbs(Battlefield battlefield) {
        if (battlefield.CurrentRound < 4) {
            return false;
        }
        FightingSkill skill = this.skillname("哀兵必胜");
        if (skill != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl()))) {
            this.hsfz += 35.0;
            this.hs += 15.0;
            this.fsmz += 25.0;
            return true;
        }
        return false;
    }

    public void executeYfct(List<FightingState> zls) {
        FightingSkill skill = this.skillname("一飞冲天");
        if (skill != null) {
            int jc = LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl());
            this.fskbcd += (double) jc;
            this.fskbjl += (double) jc;
            AddState addState = new AddState();
            addState.setStatename("一飞冲天");
            addState.setSurplus(3);
            addState.setStateEffect((double) jc);
            this.getAddStates().add(addState);
            FightingState ace = new FightingState();
            ace.setCamp(this.camp);
            ace.setMan(this.man);
            ace.setText("看我的一飞冲天");
            ace.setEndState_1("一飞冲天");
            zls.add(ace);
        }
        skill = this.skillname("展翅欲飞");
        if (skill != null) {
            int jc = LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl());
            this.hs += (double) jc;
            this.hsjv += (double) jc;
            AddState addState = new AddState();
            addState.setStatename("展翅欲飞");
            addState.setSurplus(3);
            addState.setStateEffect((double) jc);
            this.getAddStates().add(addState);
            FightingState ace = new FightingState();
            ace.setCamp(this.camp);
            ace.setMan(this.man);
            ace.setText("看我的展翅欲飞");
            ace.setEndState_1("展翅欲飞");
            zls.add(ace);
        }
    }

    public void executeYfct2() {
        AddState zt = this.xzstate("一飞冲天");
        if (zt != null) {
            this.fskbcd += zt.getStateEffect();
            this.fskbjl += zt.getStateEffect();
        }
        zt = this.xzstate("展翅欲飞");
        if (zt != null) {
            this.hs += zt.getStateEffect();
            this.hsjv += zt.getStateEffect();
        }
    }

    public void executePhbd(FightingEvents events) {
        FightingSkill skill = this.skillname("飘忽不定");
        if (skill != null && this.getSkillType("隐身") != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl()))) {
            FightingState state = new FightingState();
            state.setCamp(this.camp);
            state.setMan(this.man);
            state.setEndState_1("隐身");
            AddState addState = new AddState();
            addState.setStatename("隐身");
            addState.setSurplus(3);
            this.getAddStates().add(addState);
            events.getAccepterlist().add(state);
        }
    }

    public void executeSdef(Battlefield battlefield) {
        AddState zt = this.xzstate("11035");
        if (zt != null) {
            int my = 0;
            int it = 0;
            for (ManData data : battlefield.fightingdata) {
                if (data.getStates() == 0 && data.getType() < 3) {
                    if (data.getCamp() == this.getCamp()) {
                        ++my;
                    } else {
                        ++it;
                    }
                }
            }
            if (my < it) {
                this.fskbcd += (double) LingXiUtil.getNumberBySkillId(zt.getStatename(), 1, (int) zt.getStateEffect());
            }
        }
    }

    public void executeQfyx(ManData noData, List<FightingState> zls) {
        FightingSkill skill = this.skillname("灵光灌顶");
        if (skill != null) {
            int hpb = LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl());
            int shp = this.hp_z - this.hp;
            int bfb = (int) ((double) shp / (double) this.hp_z * 100.0);
            double bl = (double) bfb / (double) hpb;
            this.hs += 3.0 * bl;
        }
        if (noData.getHp() < 300000) {
            return;
        }
        AddState zt = this.xzstate("11031");
        if (zt != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(zt.getStatename(), 1, (int) zt.getStateEffect()))) {
            this.fskbcd += (double) LingXiUtil.getNumberBySkillId(zt.getStatename(), 2, (int) zt.getStateEffect());
            FightingState ace = new FightingState();
            ace.setCamp(this.camp);
            ace.setMan(this.man);
            ace.setText("看我的清风盈袖");
            zls.add(0, ace);
        }
        zt = this.xzstate("11032");
        if (zt != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(zt.getStatename(), 1, (int) zt.getStateEffect()))) {
            this.hs += (double) LingXiUtil.getNumberBySkillId(zt.getStatename(), 2, (int) zt.getStateEffect());
            FightingState ace = new FightingState();
            ace.setCamp(this.camp);
            ace.setMan(this.man);
            ace.setText("看我的月共潮生");
            zls.add(0, ace);
        }
    }

    public void executeJjgs(List<FightingState> xieStates, FightingState fightingState) {
        AddState zt = this.xzstate("11033");
        if (zt != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(zt.getStatename(), 1, (int) zt.getStateEffect()))) {
            this.fskbcd += (double) LingXiUtil.getNumberBySkillId(zt.getStatename(), 2, (int) zt.getStateEffect());
            if (fightingState != null) {
                fightingState.setText("看我的节节高升");
            } else {
                fightingState = new FightingState();
                fightingState.setCamp(this.camp);
                fightingState.setMan(this.man);
                fightingState.setText("看我的节节高升");
                xieStates.add(fightingState);
            }
        }
        zt = this.xzstate("11034");
        if (zt != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(zt.getStatename(), 1, (int) zt.getStateEffect()))) {
            this.hs += (double) LingXiUtil.getNumberBySkillId(zt.getStatename(), 2, (int) zt.getStateEffect());
            if (fightingState != null) {
                fightingState.setText("看我的步步为营");
            } else {
                fightingState = new FightingState();
                fightingState.setCamp(this.camp);
                fightingState.setMan(this.man);
                fightingState.setText("看我的步步为营");
                xieStates.add(fightingState);
            }
        }
    }

    public void addFaDun(long fs, FightingState ace) {
        if (this.getStates() != 0) {
            return;
        }
        AddState zt = this.xzstate("11029");
        if (zt != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(zt.getStatename(), 1, (int) zt.getStateEffect()))) {
            int dun = LingXiUtil.getNumberBySkillId(zt.getStatename(), 2, (int) zt.getStateEffect());
            long hp = fs * (long) dun / 100L;
            int currHP = this.getHp();
            int maxHP = this.getHp_z();
            int maxDun = this.getMp_z() / 100 * LingXiUtil.getNumberBySkillId(zt.getStatename(), 3, (int) zt.getStateEffect());
            int queshao = maxHP - currHP;
            if (queshao > 0) {
                ChangeFighting fighting = new ChangeFighting();
                if ((long) queshao > hp) {
                    fighting.setChangehp((int) hp);
                    hp = 0L;
                } else {
                    fighting.setChangehp(queshao);
                    hp -= (long) queshao;
                }
                this.ChangeData(fighting, ace);
            }
            if (hp > 0L) {
                AddState addState = this.xzstate("风荷送香");
                if (addState == null) {
                    addState = new AddState();
                    addState.setStateEffect((hp > (long) maxDun) ? ((double) maxDun) : ((double) hp));
                } else {
                    this.getAddStates().remove(addState);
                    hp = (long) ((double) hp + addState.getStateEffect());
                    addState.setStateEffect((hp > (long) maxDun) ? ((double) maxDun) : ((double) hp));
                }
                addState.setType(1);
                addState.setStatename("风荷送香");
                addState.setSurplus(3);
                this.getAddStates().add(addState);
            }
        }
    }

    public void executeQzzq(ManData noman, FightingState ace, List<FightingState> zls, Battlefield battlefield) {
        FightingSkill skill = this.skillname("清者自清");
        if (skill != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl()))) {
            String state = LingXiUtil.BUFF[Battlefield.random.nextInt(4)];
            noman.RemoveAbnormal(new String[]{state});
            ace.setEndState_2(state);
        }
        skill = this.skillname("洞浊其相");
        if (skill != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl()))) {
            List<ManData> datas = battlefield.getYSDR(noman);
            if (datas.size() != 0) {
                ManData data = (ManData) datas.get(Battlefield.random.nextInt(datas.size()));
                FightingState manstate = new FightingState();
                manstate.setCamp(this.camp);
                manstate.setMan(this.man);
                manstate.setText("看我的洞浊其相");
                zls.add(manstate);
                data.RemoveAbnormal(new String[]{"隐身"});
                FightingState org = new FightingState();
                org.setCamp(data.getCamp());
                org.setMan(data.getMan());
                org.setEndState_2("隐身");
                zls.add(org);
                return;
            }
        }
    }

    public boolean executeCfhy() {
        FightingSkill skill = this.skillname("春风化雨");
        return skill != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl()));
    }

    public boolean executeHfzy() {
        FightingSkill skill = this.skillname("惠风在衣");
        return skill != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl()));
    }

    public boolean executeSxtg() {
        FightingSkill skill = this.skillname("上下天光");
        return skill != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl()));
    }

    public boolean executeZyfy() {
        FightingSkill skill = this.skillname("左右逢源");
        return skill != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl()));
    }

    public boolean executeBxwj() {
        FightingSkill skill = this.skillname("冰消瓦解");
        return skill != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl()));
    }

    public int executeZyff(int idx) {
        FightingSkill skill = this.skillname("紫燕翻飞");
        if (skill != null) {
            return LingXiUtil.getNumberBySkillId(skill.getSkillname(), idx, skill.getSkilllvl());
        }
        return 0;
    }

    public int executeYszd(int idx) {
        if (idx == 3) {
            AddState addState = this.xzstate("一矢中的");
            if (addState != null) {
                return (int) addState.getStateEffect();
            }
            return 0;
        } else {
            FightingSkill skill = this.skillname("一矢中的");
            if (skill != null) {
                if (idx == 1) {
                    return LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl());
                }
                AddState addState2 = this.xzstate("暗渡失效");
                if (addState2 != null) {
                    return (int) addState2.getStateEffect();
                }
            }
            return 0;
        }
    }

    public void executeYzsg(Battlefield battlefield, FightingState fightingState) {
        FightingSkill skill = this.skillname("银装素裹");
        if (skill != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl()))) {
            int value = LingXiUtil.getNumberBySkillId(skill.getSkillname(), 2, skill.getSkilllvl());
            this.Quality.setRolekhl(this.Quality.getRolekhl() + (double) value);
            this.Quality.setRolekhs(this.Quality.getRolekhs() + (double) value);
            this.Quality.setRolekfy(this.Quality.getRolekfy() + (double) value);
            this.Quality.setRolekyw(this.Quality.getRolekzd() + (double) value);
            if (fightingState != null) {
                fightingState.setText("看我的银装素裹");
            } else {
                List<FightingState> zls = new ArrayList<>();
                FightingState ace = new FightingState();
                ace.setCamp(this.getCamp());
                ace.setMan(this.getMan());
                ace.setText("看我的银装素裹");
                zls.add(ace);
                FightingEvents event = new FightingEvents();
                event.setAccepterlist(zls);
                battlefield.NewEvents.add(event);
            }
        }
        skill = this.skillname("傲雪凌霜");
        if (skill != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl()))) {
            int value = LingXiUtil.getNumberBySkillId(skill.getSkillname(), 2, skill.getSkilllvl());
            this.Quality.setRolekff(this.Quality.getRolekff() + (double) value);
            this.Quality.setRoleklf(this.Quality.getRoleklf() + (double) value);
            this.Quality.setRoleksf(this.Quality.getRoleksf() + (double) value);
            this.Quality.setRolekhf(this.Quality.getRolekhf() + (double) value);
            this.Quality.setRolekgh(this.Quality.getRolekgh() + (double) value);
            if (fightingState != null) {
                fightingState.setText("看我的傲雪凌霜");
            } else {
                List<FightingState> zls = new ArrayList<>();
                FightingState ace = new FightingState();
                ace.setCamp(this.getCamp());
                ace.setMan(this.getMan());
                ace.setText("看我的傲雪凌霜");
                zls.add(ace);
                FightingEvents event = new FightingEvents();
                event.setAccepterlist(zls);
                battlefield.NewEvents.add(event);
            }
        }
    }

    public int executeBfbd(int idx) {
        FightingSkill skill = this.skillname("八风不动");
        if (skill != null) {
            return LingXiUtil.getNumberBySkillId(skill.getSkillname(), idx, skill.getSkilllvl());
        }
        return 0;
    }

    public void executeXzst(Battlefield battlefield) {
        FightingSkill skill = this.skillname("雪中送炭");
        if (skill != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl()))) {
            int wei = battlefield.Datapathhuo(this.getCamp(), this.getMan() - 5);
            if (wei != -1) {
                ManData master = (ManData) battlefield.fightingdata.get(wei);
                master.RemoveAbnormal(ManData.values1);
                FightingEvents moveEvents = new FightingEvents();
                List<FightingState> zls = new ArrayList<>();
                FightingState ace = new FightingState();
                ace.setCamp(this.getCamp());
                ace.setMan(this.getMan());
                ace.setText("看我的雪中送炭");
                zls.add(ace);
                FightingState ace2 = new FightingState();
                ace2.setCamp(master.getCamp());
                ace2.setMan(master.getMan());
                ace2.setEndState_2("清除异常状态");
                zls.add(ace2);
                moveEvents.setAccepterlist(zls);
                battlefield.NewEvents.add(moveEvents);
            }
        }
    }

    public int executeBfly(Battlefield battlefield) {
        FightingSkill skill = this.skillname("八方来援");
        if (skill != null) {
            int my = 0;
            for (ManData data : battlefield.fightingdata) {
                if (data.getStates() == 0 && data.getType() == 1 && data.getCamp() == this.getCamp()) {
                    ++my;
                }
            }
            if (my <= LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl())) {
                return LingXiUtil.getNumberBySkillId(skill.getSkillname(), 2, skill.getSkilllvl());
            }
        }
        return 0;
    }

    public int executeBdxz(Battlefield battlefield) {
        FightingSkill skill = this.skillname("拔刀相助");
        if (skill != null) {
            int my = 0;
            for (ManData data : battlefield.fightingdata) {
                if (data.getStates() == 0 && data.getType() == 0 && data.getCamp() == this.getCamp()) {
                    ++my;
                }
            }
            if (my <= LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl())) {
                return LingXiUtil.getNumberBySkillId(skill.getSkillname(), 2, skill.getSkilllvl());
            }
        }
        return 0;
    }

    public void addHuDun2(int idx, List<FightingState> list) {
        FightingSkill skill = this.skillname("吉人天相");
        if (skill != null && Battlefield.isV(skill.getSkillhitrate() * 10.0)) {
            this.addAddState("吉人天相", (double) this.getHp_z() * 0.3, 0.0, 3);
            FightingState org = new FightingState();
            org.setCamp(this.getCamp());
            org.setMan(this.getMan());
            org.setEndState_1("吉人天相");
            org.setText("#G吉人天相");
            list.add(org);
        }
    }

    public void executeBhnl(Battlefield battlefield) {
        FightingSkill skill = this.skillname("碧荷凝露");
        if (skill != null) {
            int my = 0;
            for (ManData data : battlefield.fightingdata) {
                if (data.getStates() == 0 && data.getType() == 0 && data.getCamp() == this.getCamp()) {
                    ++my;
                }
            }
            if (my <= 2 && Battlefield.isV((double) (LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl()) + 100))) {
                int[] yao = {21000, 42000, 0, 0};
                FightingEvents moveEvents = new FightingEvents();
                List<FightingState> zls = new ArrayList<>();
                List<ManData> ds = MixDeal.get(true, this, 2, battlefield.nomy(this.getCamp()), 1, 0, 0, 0, 1, 0, battlefield, 1);
                if (ds.size() > 0) {
                    ManData data2 = (ManData) ds.get(0);
                    ChangeFighting changeFighting = battlefield.Typeyao(data2, yao);
                    FightingState yaostate = new FightingState();
                    yaostate.setStartState("药");
                    data2.ChangeData(changeFighting, yaostate);
                    zls.add(yaostate);
                    FightingState say = new FightingState();
                    say.setCamp(this.getCamp());
                    say.setMan(this.getMan());
                    say.setText("看我的碧荷凝露");
                    zls.add(say);
                    moveEvents.setAccepterlist(zls);
                    battlefield.NewEvents.add(moveEvents);
                }
            }
        }
    }

    public void executeYcbb(Battlefield battlefield) {
        FightingSkill skill = this.skillname("有仇必报");
        if (skill != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl()))) {
            FightingEvents moveEvents = new FightingEvents();
            List<FightingState> zls = new ArrayList<>();
            FightingState ace = new FightingState();
            ace.setCamp(this.getCamp());
            ace.setMan(this.getMan());
            ace.setText("看我的有仇必报");
            zls.add(ace);
            moveEvents.setAccepterlist(zls);
            battlefield.NewEvents.add(moveEvents);
            for (ManData data : battlefield.fightingdata) {
                if (data.getStates() == 0 && data.getType() <= 2 && data.getCamp() != this.getCamp()) {
                    data.getQuality().setRolekff(data.getQuality().getRolekff() - 2.0);
                    data.getQuality().setRoleklf(data.getQuality().getRoleklf() - 2.0);
                    data.getQuality().setRoleksf(data.getQuality().getRoleksf() - 2.0);
                    data.getQuality().setRolekhf(data.getQuality().getRolekhf() - 2.0);
                    data.getQuality().setRolekgh(data.getQuality().getRolekgh() - 2.0);
                }
            }
        }
    }

    public void executeBbxb(ManData data, List<FightingState> list, FightingState diren) {
        FightingSkill skill = this.findRemoveSkill("步步相逼");
        if (skill != null) {
            AddState state = data.xzstate("步步相逼");
            if (state == null) {
                data.addAddState("步步相逼", 0.0, 0.0, 999);
                int value = LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl());
                data.getQuality().setRolekff(data.getQuality().getRolekff() - (double) value);
                data.getQuality().setRoleklf(data.getQuality().getRoleklf() - (double) value);
                data.getQuality().setRoleksf(data.getQuality().getRoleksf() - (double) value);
                data.getQuality().setRolekhf(data.getQuality().getRolekhf() - (double) value);
                data.getQuality().setRolekgh(data.getQuality().getRolekgh() - (double) value);
                diren.setEndState_1("步步相逼");
                FightingState org = new FightingState();
                org.setCamp(this.getCamp());
                org.setMan(this.getMan());
                org.setText("看我的步步相逼");
                list.add(0, org);
            }
        }
    }

    public void addFaDun2(int idx, List<FightingState> list) {
        FightingSkill skill = this.findRemoveSkill("焕然新生");
        if (skill != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(skill.getSkillname(), idx, skill.getSkilllvl()))) {
            this.addAddState("焕然新生", (double) this.getMp_z(), 0.0, 3);
            FightingState org = new FightingState();
            org.setCamp(this.getCamp());
            org.setMan(this.getMan());
            org.setEndState_1("焕然新生");
            org.setText("看我的焕然新生");
            list.add(org);
        }
    }

    public void executePrcc(int idx, List<FightingState> list) {
        FightingSkill skill = this.skillname("飘然出尘");
        if (skill != null) {
            if (Battlefield.isV((double) LingXiUtil.getNumberBySkillId(skill.getSkillname(), idx, skill.getSkilllvl()))) {
                this.addAddState("隐身", 0.0, 0.0, LingXiUtil.getNumberBySkillId(skill.getSkillname(), 3, skill.getSkilllvl()));
                FightingState state = new FightingState();
                state.setCamp(this.getCamp());
                state.setMan(this.getMan());
                state.setEndState_1("隐身");
                state.setText("看我的飘然出尘");
                list.add(state);
            } else if (Battlefield.isV((double) LingXiUtil.getNumberBySkillId(skill.getSkillname(), 4, skill.getSkilllvl()))) {
                this.addAddState(TypeUtil.BB_RHTY, 5.0, 0.0, 2);
                FightingState state = new FightingState();
                state.setCamp(this.getCamp());
                state.setMan(this.getMan());
                state.setText("看我的飘然出尘");
                state.setStartState("法术攻击");
                state.setSkillskin(TypeUtil.BB_RHTY);
                state.setEndState_1(TypeUtil.BB_RHTY);
                this.addAddState(TypeUtil.BB_RHTY, skill.getSkillhurt(), 0.0, 2);
                list.add(state);
            }
        }
    }

    public int executeCsmy(List<FightingState> list) {
        FightingSkill skill = this.findRemoveSkill("春色满园");
        if (skill != null && Battlefield.isV((double) LingXiUtil.getNumberBySkillId(skill.getSkillname(), 1, skill.getSkilllvl()))) {
            FightingState org = new FightingState();
            org.setCamp(this.getCamp());
            org.setMan(this.getMan());
            org.setText("看我的春色满园");
            list.add(org);
            return LingXiUtil.getNumberBySkillId(skill.getSkillname(), 2, skill.getSkilllvl());
        }
        return 0;
    }

    public boolean executeAdfh() {
        FightingSkill skill = this.skillname("挨打反火");
        if (skill != null) {
            skill = this.skillname("挨打反火");
            if (skill != null) {
                return true;
            }
        }
        return false;
    }

    public boolean executeSwbz() {
        FightingSkill skill = this.skillname("死亡爆炸");
        return skill != null;
    }

    public void before(List<FightingState> xieStates, FightingState fightingState) {
        this.addZhanyi(xieStates, fightingState);
        this.executeBmll();
        this.initLXBuff();
        this.executeJjgs(xieStates, fightingState);
        this.executeLhcj();
        this.executeYfct2();
    }

    public String getSkin() {
        return this.skin;
    }

    public void clearBuff() {
        if (this.type > 2) {
            return;
        }
        this.jtpa = false;
        this.fbgs = true;
        this.ymjr = false;
        this.fskbbj = false;
        this.yijibiming = true;
        this.zm = 0.0;
        this.kb = 0.0;
        this.mz = 0.0;
        this.pwljl = 0.0;
        this.pwlcd = 0.0;
        this.hsfz = 0.0;
        this.ljv = 0;
        this.qf = 0.0;
        this.hs = 0.0;
        this.fsmz = 0.0;
        this.fskbcd = 0.0;
        this.fskbjl = 0.0;
        this.hsjv = 0.0;
        this.bhws = 0.0;
        this.jlss = this.tempJlss;
        this.tempJlss = 0;
        this.jlmh = this.tempJlmh;
        this.tempJlmh = 0;
        this.jlgh = this.tempJlgh;
        this.tempJlgh = 0;
        this.jlyw = this.tempJlyw;
        this.tempJlyw = 0;
        this.getQuality().initTempQuality();
        int cd;
        if (this.cd != 0) {
            this.cd = (cd = this.cd) - 1;
        } else {
            cd = 0;
        }
        this.cd = cd;
        this.initFmSkill();
        this.nrlrjs = 0;
    }

    public int getzFMsld() {
        return this.zFMsld;
    }

    public void setzFMsld(int zFMsld) {
        this.zFMsld = zFMsld;
    }

    public int getbFMsld() {
        return this.bFMsld;
    }

    public void setbFMsld(int bFMsld) {
        this.bFMsld = bFMsld;
    }

    public int getFamencs() {
        return this.famencs;
    }

    public void setFamencs(int famencs) {
        this.famencs = famencs;
    }

    public FightingState famenBuff(FightingState org, String type) {
        if (this.famencs == 0) {//法门为0 清空法门特效
            // 判断是否已经有BUFF
            AddState addState = this.xzstate(type);
            if (addState != null) {
                this.getAddStates().remove(addState);//删掉状态
                if (org == null) {
                    org = new FightingState();
                }
                org.setCamp(this.camp);
                org.setMan(this.man);
                org.setEndState_2(type);//状态
            }
        } else {
            AddState addState = this.xzstate(type);
            if (addState == null) {
                addState = new AddState(type, (double) this.famencs, 9999);
                addState.setType(0);//0增益 1减益
            } else {
                this.getAddStates().remove(addState);
                addState.setStateEffect((double) this.famencs);
            }
            this.getAddStates().add(addState);
            if (org == null) {
                org = new FightingState();
            }
            org.setCamp(this.camp);
            org.setMan(this.man);
            org.setEndState_1(type); //状态
        }
        return org;
    }

    /**
     * 云飞烟灭 披荆斩棘 暴虎冯河
     * <p>
     * 每次物理攻击杀死敌方目标后获得1重法门，每回合最多获得1重
     */
    public void fmPTGJ(FightingState org) {
        this.fmPTGJ(org, new String[]{"云飞烟灭", "披荆斩棘", "暴虎冯河"});
    }

    public void fmPTGJ(FightingState org, String... skillNames) {
        int i = 0;
        while (i < skillNames.length) {
            FightingSkill skill = this.skillname(skillNames[i]);
            if (skill != null) {
                if (this.huiHeFamencs < 1) {
                    ++this.huiHeFamencs;
                    ++this.famencs;
                    if (this.famencs > 5) {
                        this.famencs = 5;
                    }
                    this.famenBuff(org, skillNames[i]);
                    break;
                } else {
                    break;
                }
            } else {
                ++i;
            }
        }
    }

    public void fmGJ(FightingState org) {
        this.fmBGJ(org, 5, new String[]{"一气呵成"});
        if (!this.isSpellAttack) {
            this.isSpellAttack = true;
        }
    }

    public void tgyh(FightingState org) {
        if (!this.isSpellAttack) {
            this.fmBGJ(org, 3, new String[]{"韬光养晦"});
        }
    }

    /**
     * 以静制动 明镜止水 妙法莲华
     * <p>
     * 每次受到敌方的师门法术和物理攻击时获得1重法门
     */
    public void fmBGJ(FightingState org) {
        this.fmBGJ(org, 5, new String[]{"以静制动", "明镜止水", "妙法莲华"});
        this.isFmAttack = true;
    }

    public void fmBGJ(FightingState org, int max, String... skillNames) {
        FightingSkill skill = null;
        int i = 0;
        while (i < skillNames.length) {
            skill = this.skillname(skillNames[i]);
            if (skill != null) {
                if (skill.getSkillname().equals("明镜止水")) {
                    AddState addState = this.getstat("气聚神凝");
                    if (addState != null && !addState.isEnd()) {
                        break;
                    }
                }
                ++this.famencs;
                if (this.famencs > max) {
                    this.famencs = max;
                }
                this.famenBuff(org, skillNames[i]);
                break;
            } else {
                ++i;
            }
        }
    }

    /**
     * 云飞烟灭 披荆斩棘 暴虎冯河
     * <p>
     * 每重法门提高自身{7535}点攻击力
     */
    public int getFmAddAp() {
        return this.getFmAddAp(new String[]{"云飞烟灭", "披荆斩棘", "暴虎冯河"});
    }

    public int getFmAddAp(String... skillNames) {
        for (int i = 0; i < skillNames.length; ++i) {
            FightingSkill skill = this.skillname(skillNames[i]);
            if (skill != null) {
                int ap = FaMenUtil.getInt(skill.getSkillid(), this.getFmsld(false), 1);
                return ap * this.famencs;
            }
        }
        return 0;
    }

    /**
     * 神力加身 力挽狂澜 势如破竹
     * <p>
     * 攻击时有21.7%几率分裂攻击另一个目标(与符文不冲突)，同时每重法门额外增加15.6%分裂攻击(非符文)几率
     */
    public double getFmfljv() {
        return this.getFmfljv(new String[]{"神力加身", "力挽狂澜", "势如破竹"});
    }

    public double getFmfljv(String... skillNames) {
        for (int i = 0; i < skillNames.length; ++i) {
            FightingSkill skill = this.skillname(skillNames[i]);
            if (skill != null) {
                double jv = FaMenUtil.getDouble(skill.getSkillid(), this.getFmsld(false), 2);
                double addjv = FaMenUtil.getDouble(skill.getSkillid(), this.getFmsld(false), 3);
                return jv + addjv * (double) this.famencs;
            }
        }
        return 0.0;
    }

    /**
     * 法门通用方法 - 获取法门几率 被动层数叠加几率
     */
    public double getFmJv(int idx, String... skillNames) {
        for (int i = 0; i < skillNames.length; ++i) {
            FightingSkill skill = this.skillname(skillNames[i]);
            if (skill != null) {
                boolean is = skill.getSkillid() % 2 == 0;
                return FaMenUtil.getDouble(skill.getSkillid(), this.getFmsld(is), idx);
            }
        }
        return 0.0;
    }

    /**
     * 法门通用方法 - 被动层数叠加几率
     */
    public double getFamencsJv(int idx, String... skillNames) {
        for (int i = 0; i < skillNames.length; ++i) {
            FightingSkill skill = this.skillname(skillNames[i]);
            if (skill != null) {
                boolean is = skill.getSkillid() % 2 == 0;
                double value = FaMenUtil.getDouble(skill.getSkillid(), this.getFmsld(is), idx);
                return (double) this.famencs * value;
            }
        }
        return 0.0;
    }

    public int getFmValue(int idx, String... skillNames) {
        for (int i = 0; i < skillNames.length; ++i) {
            FightingSkill skill = this.skillname(skillNames[i]);
            if (skill != null) {
                boolean is = skill.getSkillid() % 2 == 0;
                return FaMenUtil.getInt(skill.getSkillid(), this.getFmsld(is), idx);
            }
        }
        return 0;
    }

    public int getFamencsValue(int idx, String... skillNames) {
        for (int i = 0; i < skillNames.length; ++i) {
            FightingSkill skill = this.skillname(skillNames[i]);
            if (skill != null) {
                boolean is = skill.getSkillid() % 2 == 0;
                int value = FaMenUtil.getInt(skill.getSkillid(), this.getFmsld(is), idx);
                return this.famencs * value;
            }
        }
        return 0;
    }

    /**
     * 回合结束法门处理状态
     */
    public void endFaMen(Battlefield battlefield) {
        FightingSkill skill = this.getFmSkill(new String[]{"云飞烟灭", "披荆斩棘", "暴虎冯河"});
        if (skill != null) {
            if (this.isFmPhyAttack) {// 使用主动技能层数-3
                this.subFamencs(3);
            } else if (!this.isPhyAttack) { // 没有普通攻击层数-1
                this.subFamencs(1);
            }
            FightingState org2 = this.famenBuff(null, skill.getSkillname());
            if (org2 != null) {
                battlefield.NewEvents.add(new FightingEvents(Arrays.asList(new FightingState[]{org2})));
            }
        }
        skill = this.getFmSkill(new String[]{"以静制动", "明镜止水", "妙法莲华"});
        if (skill != null && !this.isFmAttack) {// 没有受敌方攻击层数-1
            this.subFamencs(1);
            FightingState org2 = this.famenBuff(null, skill.getSkillname());
            if (org2 != null) {
                battlefield.NewEvents.add(new FightingEvents(Arrays.asList(new FightingState[]{org2})));
            }
        }
        skill = this.getFmSkill(new String[]{"一气呵成"});
        if (skill != null && !this.isSpellAttack) {// 没有受敌方攻击层数-1
            this.subFamencs(1);
            FightingState org2 = this.famenBuff(null, skill.getSkillname());
            if (org2 != null) {
                battlefield.NewEvents.add(new FightingEvents(Arrays.asList(new FightingState[]{org2})));
            }
        }
        if (this.isSpellAttack) {
            if (this.skillname("韬光养晦") != null) {
                this.famencs = 0;
            }
        } else {
            FightingState org2 = new FightingState();
            this.tgyh(org2);
            if (org2 != null) {
                battlefield.NewEvents.add(new FightingEvents(Arrays.asList(new FightingState[]{org2})));
            }
        }
        for (ManData data : battlefield.getBattleRoleDataByCamp(battlefield.nomy(this.getCamp()))) {
            double fmJv = data.getFmJv(1, new String[]{"人鬼殊途"});
            if (fmJv > 0.0 && this.attackDie > 0) {// 取最大值
                FightingState org3 = new FightingState();
                AddState addState = this.xzstate("人鬼殊途");
                if (addState == null) {
                    addState = new AddState("人鬼殊途", (double) this.famencs, 1);
                    addState.setType(1);// 0增益 1减益
                    addState.setStateEffect2(fmJv);
                } else {
                    this.getAddStates().remove(addState);
                    addState.setStateEffect((double) this.famencs);
                    if (addState.getStateEffect2() < fmJv) {
                        addState.setStateEffect2(fmJv);
                    }
                }
                this.getAddStates().add(addState);
                org3.setEndState_1("人鬼殊途");
                battlefield.NewEvents.add(new FightingEvents(Arrays.asList(new FightingState[]{org3})));
            }
        }
        this.isPhyAttack = false;
        this.isSpellAttack = false;
        this.isFmPhyAttack = false;
        this.isFmAttack = false;
        this.huiHeFamencs = 0;
        this.attackDie = 0;
    }

    /**
     * 初始化法门累计伤害
     * <p>
     * 利刃加身 该状态下目标每次受到伤害时都会额外受到等量的伤害，每次不超过100475点，每回合内最多受到228655点伤害
     */
    public void initFmSkill() {
        this.initFmSkill(new String[]{"利刃加身"});
    }

    public void initFmSkill(String... skillNames) {
        for (int i = 0; i < skillNames.length; ++i) {
            AddState addState = this.xzstate(skillNames[i]);
            if (addState != null) {// 初始化利刃加身
                int fmId = (int) addState.getStateEffect();
                int fmSld = (int) addState.getStateEffect2();
                addState.getStatename().getClass();
            }
        }
    }

    //判断兽魂俯首 hp_z mp_z ap sp
    public int fmshfssx() {
        if (this.type == 1) {
            AddState addState = this.xzstate("兽魂俯首");
            if (addState != null) {
                int type = 1;
                double max = this.huoyue;
                if (max < this.shanghai) {
                    type = 2;
                    max = this.shanghai;
                }
                if (max < this.kangluobao) {
                    type = 3;
                    max = this.kangluobao;
                }
                if (max < this.yuanzhu) {
                    type = 4;
                }
                int fmId = (int) addState.getStateEffect();
                int fmSld = (int) addState.getStateEffect2();
                double xs = 1.0 + FaMenUtil.getDouble(fmId + "", fmSld, 1);
                switch (type) {
                    case 1: {
                        if (this.ewhuoyue == 0) {
                            this.ewhuoyue = this.hp_z;
                            this.hp_z = (int) ((double) this.hp_z * (1.0 + xs));
                            break;
                        } else {
                            break;
                        }
                    }
                    case 2: {
                        if (this.ewshanghai == 0) {
                            this.ewshanghai = this.mp_z;
                            this.mp_z = (int) ((double) this.mp_z * (1.0 + xs));
                            break;
                        } else {
                            break;
                        }
                    }
                    case 3: {
                        if (this.ewkangluobao == 0) {
                            this.ewkangluobao = this.ap;
                            this.ap = (int) ((double) this.ap * (1.0 + xs));
                            break;
                        } else {
                            break;
                        }
                    }
                    case 4: {
                        if (this.ewyuanzhu == 0) {
                            this.ewyuanzhu = this.sp;// 支援 敏捷
                            this.sp = (int) ((double) this.sp * (1.0 + xs / 2.0));
                            break;
                        } else {
                            break;
                        }
                    }
                }
                return type;
            } else {
                if (this.ewhuoyue != 0) {
                    this.hp_z = this.ewhuoyue;
                    this.ewhuoyue = 0;
                    return 1;
                }
                if (this.ewshanghai != 0) {
                    this.mp_z = this.ewshanghai;
                    this.ewshanghai = 0;
                    return 2;
                }
                if (this.ewkangluobao != 0) {
                    this.ap = this.ewkangluobao;
                    this.ewkangluobao = 0;
                    return 3;
                }
                if (this.ewyuanzhu != 0) {
                    this.sp = this.ewyuanzhu;
                    this.ewyuanzhu = 0;
                    return 4;
                }
            }
        }
        return 0;
    }

    public FightingSkill getFmSkill(String... skillNames) {
        for (int i = 0; i < skillNames.length; ++i) {
            FightingSkill skill = this.skillname(skillNames[i]);
            if (skill != null) {
                return skill;
            }
        }
        return null;
    }

    public void subFamencs(int sum) {
        this.famencs -= sum;
        if (this.famencs < 0) {
            this.famencs = 0;
        }
    }
    /******************** 法门结束 ********************/

    /******************** 天赋开始 ********************/
    /**
     * 获取天赋几率
     */
    public double getTalentProbability(ManData manData) {
        double probability = 0.0;
        if (manData.talentLvl <= this.talentLvl) {
            probability = 7.0;
            probability += (double) ((this.talentLvl - manData.talentLvl) * 5);
        }
        AddState addState = this.getstat("封印");
        if (addState != null) {
            List<FightingSkill> skills = addState.getSkills();
            int i = 0;
            while (i < skills.size()) {
                // 9867|笼禽槛兽|被封印召唤兽的召唤兽天赋触发几率下降#R{公式一}%#Y(仅在与玩家之间战斗有效)
                if (skills.get(i).getSkilltype().equals(TypeUtil.TY_R_LQLS)) {
                    probability -= skills.get(i).getSkillhurt();
                    if (probability < 0.0) {
                        probability = 0.0;
                        break;
                    } else {
                        break;
                    }
                } else {
                    ++i;
                }
            }
        }
        return probability;
    }

    /**
     * 获取天赋等级
     */
    public int getTalentLvl() {
        return this.talentLvl;
    }
    /******************** 天赋结束 ********************/

    /******************** 天演策开始 ********************/
    // 定风息焰-被魅惑的敌方人物，受到伤害时{公式零零}%几率无法获得怒气(仅在与玩家之间战斗有效)
    public boolean dfxy(String type, String name, String... skillTypes) {
        List<FightingSkill> skills = this.getAppendSkillByType(type, skillTypes);
        int i = 0;
        while (i < skills.size()) {
            FightingSkill skill = skills.get(i);
            if (Battlefield.getSkillIsV(skill, name)) {
                if (type.equals("三尸虫")) {
                    this.deleteState(type);
                }
                return true;
            } else {
                ++i;
            }
        }
        return false;
    }

    public void dfxy(List<FightingSkill> skills, String... skillTypes) {
        if (skills != null) {
            for (FightingSkill skill : skills) {
                if (Arrays.asList(skillTypes).contains(skill.getSkilltype()) && Battlefield.getSkillIsV(skill, "冥")) {
                    this.nqz -= 3;
                    if (this.nqz < 0) {
                        this.nqz = 0;
                    } else {
                        continue;
                    }
                }
            }
        }
    }

    public int getfamencs() {
        return this.famencs;
    }

    public void setfamencs(int famencs) {
        this.famencs = famencs;
    }

    public int getLingbaotianfujineng() {
        return this.lingbaotianfujineng;
    }

    public Integer getFmsld(String skillname) {
        Skill skill = GameServer.getSkill(skillname);
        if (skill == null) {
            return 0;
        }
        return (skill.getSkillid() % 2 == 0) ? this.fmsld : this.fmsld2;
    }

    public Integer getFmsld(int skillId) {
        return (skillId % 2 == 0) ? this.fmsld : this.fmsld2;
    }

    /**
     * @param is 是否是主动技能
     * @return
     */
    public int getFmsld(boolean is) {
        if (is) {
            return (this.fmsld != null) ? ((int) this.fmsld) : 0;
        }
        return (this.fmsld2 != null) ? ((int) this.fmsld2) : 0;
    }

    public void setFmsld(Integer fmsld) {
        this.fmsld = fmsld;
    }

    public int getNrlrjs() {
        return this.nrlrjs;
    }

    public void setNrlrjs(int nrlrjs) {
        this.nrlrjs = nrlrjs;
    }

    public int getNrjycz() {
        return this.nrjycz;
    }

    public void setNrjycz(int nrjycz) {
        this.nrjycz = nrjycz;
    }

    public int getNrfhht() {
        return this.nrfhht;
    }

    public void setNrfhht(int nrfhht) {
        this.nrfhht = nrfhht;
    }

    public int getDffmsld() {
        return this.dffmsld;
    }

    public void setDffmsld(int dffmsld) {
        this.dffmsld = dffmsld;
    }

    private String getPetSkillswlLevel(String petSkillswl, String skillId) {
        String[] split = petSkillswl.split("\\|");
        Optional<String> first = Arrays.stream(split).filter(item/* java.lang.String, */ -> item.contains(skillId)).findFirst();
        if (first != null) {
            String s = (String) first.get();
            return s.split("=")[1];
        }
        return null;
    }

    public static String getPetSkillswl() {
        return ManData.petSkillswl;
    }

    public void setPetSkillswl(String petSkillswl) {
        ManData.petSkillswl = petSkillswl;
    }

    public boolean getTPZS() {
        return this.TPZS;
    }

    public void setTPZS(boolean TPZS) {
        this.TPZS = TPZS;
    }

    public boolean getbianshen() {
        return this.bianshen;
    }

    public void setbianshen(boolean bianshen) {
        this.bianshen = bianshen;
    }

    public boolean getQKZT() {
        return this.QKZT;
    }

    public void setQKZT(boolean QKZT) {
        this.QKZT = QKZT;
    }

    public int getLingbaoshanxian() {
        return this.lingbaoshanxian;
    }

    public void setLingbaoshanxian(int lingbaoshanxian) {
        this.lingbaoshanxian = lingbaoshanxian;
    }

    public boolean isQHFY() {
        return this.QHFY;
    }

    public void setQHFY(boolean QHFY) {
        this.QHFY = QHFY;
    }

    /**
     * 寻找被动技能
     */
    public FightingSkill getbeidongSkillName(String skillname) {
        for (int i = 0; i < this.skills.size(); ++i) {
            FightingSkill skill = (FightingSkill) this.skills.get(i);
            if (skill.getSkillbeidong() == 1 && skill.getSkillname().equals(skillname)) {
                return skill;
            }
        }
        return null;
    }

    public int getXingtai() {
        return this.xingtai;
    }

    public void setXingtai(int xingtai) {
        this.xingtai = xingtai;
    }

    public String[] getHZT() {
        return this.HZT;
    }

    public AddState getstat(String name) {
        if (this.getAddStates() != null && this.getAddStates().size() != 0) {
            for (AddState addState : this.getAddStates()) {
                if (addState.getStatename().equals(name)) {
                    return addState;
                }
            }
        }
        return null;
    }

    public boolean isyuanzhu() {
        if (this.type != 3) {
            return false;
        }
        if (this.yuanzhu >= 100.0) {
            this.yuanzhu /= 2.0;
        }
        return (double) Battlefield.random.nextInt(20) < this.yuanzhu;
    }

    public int getHF() {
        return this.HF;
    }

    public void setHF(int HF) {
        this.HF = HF;
    }

    public int getZy() {
        return this.zy;
    }

    public void setZy(int zy) {
        this.zy = zy;
    }

    public int getLzy() {
        return this.lzy;
    }

    public void setLzy(int lzy) {
        this.lzy = lzy;
    }

    public String getSfPet() {
        return this.sfPet;
    }

    public void setSfPet(String sfPet) {
        this.sfPet = sfPet;
    }

    public FightingEvents getFightingEvents() {
        return this.fightingEvents;
    }

    public void setFightingEvents(FightingEvents fightingEvents) {
        this.fightingEvents = fightingEvents;
    }

    public ManData getBaohu() {
        return this.baohu;
    }

    public void setBaohu(ManData baohu) {
        this.baohu = baohu;
    }

    public long getMoney2() {
        return this.money2;
    }

    public void setMoney2(long money2) {
        this.money2 = money2;
    }

    public Integer getFzSum() {
        return this.fzSum;
    }

    public void setFzSum(Integer fzSum) {
        this.fzSum = fzSum;
    }

    public Integer getFmsld2() {
        return this.fmsld2;
    }

    public void setFmsld2(Integer fmsld2) {
        this.fmsld2 = fmsld2;
    }

    public void setLingbaotianfujineng(int lingbaotianfujineng) {
        this.lingbaotianfujineng = lingbaotianfujineng;
    }

    public boolean isPhyAttack() {
        return this.isPhyAttack;
    }

    public void setPhyAttack(boolean phyAttack) {
        this.isPhyAttack = phyAttack;
    }

    public boolean isSpellAttack() {
        return this.isSpellAttack;
    }

    public void setSpellAttack(boolean spellAttack) {
        this.isSpellAttack = spellAttack;
    }

    public boolean isFmAttack() {
        return this.isFmAttack;
    }

    public void setFmAttack(boolean fmAttack) {
        this.isFmAttack = fmAttack;
    }

    public boolean isFmPhyAttack() {
        return this.isFmPhyAttack;
    }

    public void setFmPhyAttack(boolean fmPhyAttack) {
        this.isFmPhyAttack = fmPhyAttack;
    }

    public int addAttackDie() {
        return this.attackDie++;
    }

    public int getDeparture() {
        return this.departure;
    }

    public void setDeparture(int departure) {
        this.departure = departure;
    }

    public int getConsumedNq() {
        return this.consumedNq;
    }

    public void setConsumedNq(int consumedNq) {
        this.consumedNq = consumedNq;
    }

    public int getSkill_count_z() {
        return this.skill_count_z;
    }

    public void setSkill_count_z(int skill_count_z) {
        this.skill_count_z = skill_count_z;
    }

    public int getSkill_count_s() {
        return this.skill_count_s;
    }

    public void setSkill_count_s(int skill_count_s) {
        this.skill_count_s = skill_count_s;
    }

    public int getSkill_count_g() {
        return this.skill_count_g;
    }

    public void setSkill_count_g(int skill_count_g) {
        this.skill_count_g = skill_count_g;
    }

    public int getSkill_count_p() {
        return this.skill_count_p;
    }

    public void setSkill_count_p(int skill_count_p) {
        this.skill_count_p = skill_count_p;
    }

    public static Object[] calculateTotalAttackValue(DefaultListModel<String> shuxingList, int lvl, String value) {
        int fixedAttackValue = 0;
        double percentageAttackValue = 0.0;
        if (lvl >= shuxingList.size()) {
            lvl = shuxingList.getSize();
        }
        for (int i = 0; i < lvl; ++i) {
            String attribute = (String) shuxingList.get(i);
            if (attribute.contains(value)) {
                String[] parts = attribute.split(value);
                if (parts.length >= 2) {
                    String valuePart = parts[1].trim();
                    if (valuePart.endsWith("+")) {
                        valuePart = valuePart.substring(1);
                    }
                    if (valuePart.endsWith("%")) {
                        double percentageValue = Double.parseDouble(valuePart.substring(0, valuePart.length() - 1));
                        percentageAttackValue += percentageValue;
                    } else {
                        try {
                            int fixedValue = Integer.parseInt(valuePart);
                            fixedAttackValue += fixedValue;
                        } catch (NumberFormatException ex) {
                        }
                    }
                }
            }
        }
        return new Object[]{Integer.valueOf(fixedAttackValue), Double.valueOf(percentageAttackValue)};
    }

    public int getBeatenNum() {
        return this.beatenNum;
    }

    public void setBeatenNum(int beatenNum) {
        this.beatenNum = beatenNum;
    }

    public List<String> getTFSkills() {
        return this.TFSkills;
    }

    public boolean isHS() {
        return this.isHS;
    }

    public void setHS(boolean HS) {
        this.isHS = HS;
    }

    static {
        ManData.values1 = new String[]{"减人仙", "减魔鬼", "庇护", "遗忘", "封印", "中毒", "昏睡", "混乱", "金", "木", "水", "火", "土", "力量", "抗性", "加速", "smmh", "1243", "加狂暴", "1889", "流连忘返"};
        ManData.values2 = new String[]{"遗忘", "封印", "中毒", "昏睡", "混乱", TypeUtil.FB_JGE, TypeUtil.FB_QW, TypeUtil.BB_LHFM, TypeUtil.BB_DHSM, TypeUtil.BB_SS, TypeUtil.BB_百草竞发};
        ManData.values3 = new String[]{"金", "木", "水", "火", "土"};
        ManData.values4 = new String[]{"减速", "减人仙", "减人仙", "中毒"};
        ManData.shuxingmap = new HashMap<>();
        List<String> zhuque = new ArrayList<>();
        zhuque.add("1级 法力 +500");
        zhuque.add("2级 法力 +0.5%");
        zhuque.add("3级 气血 +500");
        zhuque.add("4级 忽视仙法鬼火+1.0%");
        zhuque.add("5级 法力 +500");
        zhuque.add("6级 法力 +0.5%");
        zhuque.add("7级 气血 +500");
        zhuque.add("8级 仙法鬼火命中率+0.5%");
        zhuque.add("9级 法力 +500");
        zhuque.add("10级 法力 +0.5%");
        zhuque.add("11级 气血 +500");
        zhuque.add("12级 忽视抗仙法鬼火+1.0%");
        zhuque.add("13级 法力 +500");
        zhuque.add("14级 法力 +0.5%");
        zhuque.add("15级 气血 +500");
        zhuque.add("16级 仙法鬼火命中率+0.5%");
        zhuque.add("17级 法力 +500");
        zhuque.add("18级 法力 +0.5%");
        zhuque.add("19级 气血 +500");
        zhuque.add("20级 忽视仙法鬼火+1.0%");
        zhuque.add("21级 法力 +500");
        zhuque.add("22级 法力 +0.5%");
        zhuque.add("23级 气血 +500");
        zhuque.add("24级 仙法鬼火命中率+0.5%");
        ManData.shuxingmap.put("朱雀", zhuque);
        List<String> qinglong = new ArrayList<>();
        qinglong.add("1级 速度 +2");
        qinglong.add("2级 物理吸收率+0.5%");
        qinglong.add("3级 气血 +500");
        qinglong.add("4级 速度 +0.2%");
        qinglong.add("5级 速度 +2");
        qinglong.add("6级 物理吸收率+0.5%");
        qinglong.add("7级 法力 +500");
        qinglong.add("8级 法术躲闪几率+0.5%");
        qinglong.add("9级 速度 +2");
        qinglong.add("10级 物理吸收率+0.5%");
        qinglong.add("11级 气血 +500");
        qinglong.add("12级 速度 +0.2%");
        qinglong.add("13级 速度 +2");
        qinglong.add("14级 物理吸收率+0.5%");
        qinglong.add("15级 法力 +500");
        qinglong.add("16级 法术躲闪几率+0.5%");
        qinglong.add("17级 速度 +2");
        qinglong.add("18级 物理吸收率+0.5%");
        qinglong.add("19级 气血 +500");
        qinglong.add("20级 速度 +0.2%");
        qinglong.add("21级 速度 +2");
        qinglong.add("22级 物理吸收率+0.5%");
        qinglong.add("23级 法力 +500");
        qinglong.add("24级 法术躲闪几率+0.5%");
        ManData.shuxingmap.put("青龙", qinglong);
        List<String> xuanwu = new ArrayList<>();
        xuanwu.add("1级 气血 +500");
        xuanwu.add("2级 气血 +0.5%");
        xuanwu.add("3级 法力 +500");
        xuanwu.add("4级 抗致命率+1.0%");
        xuanwu.add("5级 气血 +500");
        xuanwu.add("6级 气血 +0.5%");
        xuanwu.add("7级 法力 +500");
        xuanwu.add("8级 抗仙法鬼火狂暴率+1.0%");
        xuanwu.add("9级 气血 +500");
        xuanwu.add("10级 攻击 +0.5%");
        xuanwu.add("11级 法力 +500");
        xuanwu.add("12级 抗致命率+1.0%");
        xuanwu.add("13级 气血 +500");
        xuanwu.add("14级 气血 +0.5%");
        xuanwu.add("15级 法力 +500");
        xuanwu.add("16级 抗仙法鬼火狂暴率+1.0%");
        xuanwu.add("17级 气血 +500");
        xuanwu.add("18级 气血 +0.5%");
        xuanwu.add("19级 法力 +500");
        xuanwu.add("20级 抗致命率+1.0%");
        xuanwu.add("21级 气血 +500");
        xuanwu.add("22级 气血 +0.5%");
        xuanwu.add("23级 法力 +500");
        xuanwu.add("24级 抗仙法鬼火狂暴率+1.0%");
        ManData.shuxingmap.put("玄武", xuanwu);
        List<String> baihu = new ArrayList<>();
        baihu.add("1级 攻击 +200");
        baihu.add("2级 攻击 +0.5%");
        baihu.add("3级 气血 +500");
        baihu.add("4级 狂暴率+1.0%");
        baihu.add("5级 攻击 +200");
        baihu.add("6级 攻击 +0.5%");
        baihu.add("7级 气血 +500");
        baihu.add("8级 命中率+1.0%");
        baihu.add("9级 攻击 +200");
        baihu.add("10级 攻击 +0.5%");
        baihu.add("11级 气血 +500");
        baihu.add("12级 狂暴率+1.0%");
        baihu.add("13级 攻击 +200");
        baihu.add("14级 攻击 +0.5%");
        baihu.add("15级 气血 +500");
        baihu.add("16级 命中率+1.0%");
        baihu.add("17级 攻击 +200");
        baihu.add("18级 攻击 +0.5%");
        baihu.add("19级 气血 +500");
        baihu.add("20级 狂暴率+1.0%");
        baihu.add("21级 攻击 +200");
        baihu.add("22级 攻击 +0.5%");
        baihu.add("23级 气血 +500");
        baihu.add("24级 命中率+1.0%");
        ManData.shuxingmap.put("白虎", baihu);
    }

    public String getRace_name() {
        return race_name;
    }

    public void setRace_name(String race_name) {
        this.race_name = race_name;
    }

    public Boolean getDZ() {
        return isDZ;
    }

    public void setDZ(Boolean DZ) {
        isDZ = DZ;
    }

    public int xy = 3; //xuanbao

    public int getXy() {
        return this.xy;
    }

    public void setXy(int xy) {
        this.xy = xy;
    }

    public void addXYZ(int idx) {
        this.xy = Math.min(Math.max(this.xy + idx, 0), 20);
    }

    public void addXYZ(int idx, FightingState org) {
        this.xy = Math.min(Math.max(this.xy + idx, 0), 20);
        org.setXy_c(xy);
    }
}
