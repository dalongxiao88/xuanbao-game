package come.tool.FightingData;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.come.model.PalData;

import java.util.stream.Collectors;

import org.come.tool.Arith;
import org.come.model.Skill;
import org.apache.commons.lang3.StringUtils;
import come.tool.Battle.BattleThreadPool;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import come.tool.FightingDataAction.Lingbao;
import come.tool.Battle.BattleStatePolicy;
import come.tool.FightingSpellAction.SpellActionType;
import come.tool.Mixdeal.CreepsMixdeal;
import org.come.entity.Record;
import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;
import org.come.tool.TalentTool;
import come.tool.Battle.FightingStatistics;
import come.tool.FightingDataAction.Petdll;
import org.come.tool.WriteOut;
import org.come.handler.MainServerHandler;
import come.tool.Calculation.BaseQl;
import org.come.entity.StarPalace;
import come.tool.FightingDataAction.DataActionType;
import come.tool.Battle.BattleMixDeal;
import org.come.server.GameServer;
import come.tool.Calculation.BaseStar;
import org.come.bean.PathPoint;
import come.tool.Battle.BattleData;
import come.tool.Good.DropModel;

import static come.tool.FightingData.AI_MixDeal.isAIFull;

/**
 * 战场
 */

public class Battlefield {
    public static Random random;
    public static FightingEvents currZl;
    public static List<ManData> jiohuan;
    public DropModel dropModel;
    public int DropXS;//默认0 奖励系数
    public int ndXS;//默认0 难度系数
    public int CurrentRound; //当前回合
    public FightingSkill YLFY1; //记录召唤兽闪现几率
    public FightingSkill YLFY2;
    public double summonOrobability1;
    public double summonOrobability2;
    public String yename;//主怪名
    /**
     * 回合开始前指令数
     */
    public int elsum;
    public BattleData battleData;
    //1阵营死亡人数
    public int MyDeath;
    //其他阵营死亡人数
    public int NoDeath;
    public int chaosSp;//乱敏区间
    public boolean isWLKH;//雾里看花处理
    public boolean isHSJY; //火上浇油(火种)处理
    public PathPoint bbDeathPoint;//死亡抗性添加处理//当前回合宝宝死亡记录
    public List<ManData> fightingdata; //存处于战斗中的数据
    public int BattleType;//战斗类型
    public List<FightingEvents> Events;//存放当前回合的战斗事件
    public List<FightingEvents> baohu;//保护指令
    public List<FightingEvents> erwai;//额外指令
    public List<FightingEvents> NewEvents;
    public int cfzjsp;//判定机率解除混乱
    public int trzr;//泰然自若计数
    public List<ManData> waitList;//还未出手的单位
    public boolean skillActivated;// 标记技能是否已经被获取并生效_混
    public boolean skillActi;// 标记技能是否已经被获取并生效_冰
    public boolean skillActisleep;// 标记技能是否已经被获取并生效_睡
    /**
     * 记录当前回合释放的法术
     */
    public Map<Integer, Integer> JLSkills;
    public List<PathPoint> huayu; //存放化羽死亡单位//第一个死亡单位阵营第二个伤害值
    public List<String> recordList; //存放化羽死亡单位//第一个死亡单位阵营第二个伤害值
    private int buffid;//群体buff
    public static int overSp;
    public static int overcamp1sp;
    public static int overcamp2sp;
    public List<GroupBuff> buffs;
    public List<ManData> AIdaya;
    public List<FightingSkill> buffskill;
    public List<FightingSkill> buffskill1;
    public List<PathPoint> fangyu;
    List<ManData> lingbao1;
    List<ManData> lingbao2;
    public ManData ling1;
    public ManData ling2;
    public List<ManData> ful1;
    public FightingSkill skill1;
    public int zltype1;
    public List<ManData> ful2;
    public FightingSkill skill2;
    public int zltype2;

    public Battlefield(BattleData battleData, int BattleType) {
        this.YLFY1 = null;
        this.YLFY2 = null;
        this.summonOrobability1 = 0.0;
        this.summonOrobability2 = 0.0;
        this.cfzjsp = 0;
        this.trzr = 0;
        this.skillActivated = false;
        this.skillActi = false;
        this.skillActisleep = false;
        this.buffid = 0;
        this.buffs = new ArrayList<>();
        this.AIdaya = new ArrayList<>();
        this.buffskill = null;
        this.buffskill1 = null;
        this.fangyu = new ArrayList<>();
        this.lingbao1 = null;
        this.lingbao2 = null;
        this.ling1 = null;
        this.ling2 = null;
        this.ful1 = null;
        this.skill1 = null;
        this.zltype1 = 0;
        this.ful2 = null;
        this.skill2 = null;
        this.zltype2 = 0;
        this.battleData = battleData;
        this.BattleType = BattleType;
        this.fightingdata = new ArrayList<>();
        this.Events = new ArrayList<>();
        this.baohu = new ArrayList<>();
        this.erwai = new ArrayList<>();
        this.huayu = new ArrayList<>();
        this.recordList = new ArrayList<>();
        this.JLSkills = new HashMap<>();
        this.waitList = new ArrayList<>();
        this.buffs = new ArrayList<>();
        this.buffid = 0;
        this.chaosSp = 300;
        Battlefield.jiohuan = new ArrayList<>();
    }

    //初始化战斗参数
    public void init(BaseStar star1, BaseStar star2) {
        StarPalace palace1 = null;
        StarPalace palace2 = null;
        boolean isBB = false;
        if (star1 != null) {
            palace1 = GameServer.getStarPalace(star1.getVs()[1]);
        }
        if (star2 != null) {
            palace2 = GameServer.getStarPalace(star2.getVs()[1]);
        }
        //义薄云天  化无  悬刃 遗患 讨命 明察秋毫 双管齐下 牵制
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData data = (ManData) this.fightingdata.get(i);
            if (data.getType() != 3 && data.getType() != 4) {
                if (data.getType() == 0) {
                    if (data.getCamp() == 1) {
                        if (star1 != null && palace1 != null) {
                            if (data.getMan() == star1.getMan()) {
                                this.addBuff(new GroupBuff(this.getBuffid(), "星阵", data, (double) BattleMixDeal.getID(star1.getVs()[1])));
                            }
                            this.addXK_SX(star1, palace1.getVs(), data);
                        }
                    } else if (star2 != null && palace2 != null) {
                        if (data.getMan() == star2.getMan()) {
                            this.addBuff(new GroupBuff(this.getBuffid(), "星阵", data, (double) BattleMixDeal.getID(star2.getVs()[1])));
                        }
                        this.addXK_SX(star2, palace2.getVs(), data);
                    }
                }
                //找敌方最高速度
                if (PK_MixDeal.isPK(this.BattleType) && data.getType() == 0) {
                    ManData manData = this.maxsp(data.getCamp());//最高Sp
                    if (manData != null) {
                        isBB = (manData.getSp() != data.getSp());
                    }
                }
                for (int j = data.getSkills().size() - 1; j >= 0; --j) {
                    FightingSkill skill = (FightingSkill) data.getSkills().get(j);
                    int id = skill.getSkillid();
                    if (id >= 1041 && id <= 1065) {
                        if (skill.getSkillhurt() > 42000.0) {
                            skill.setSkillhurt(42000.0);
                        }
                    } else if (id >= 1066 && id <= 1070) {
                        if (skill.getSkillhurt() > 20000.0) {
                            skill.setSkillhurt(20000.0);
                        }
                    } else if ((id >= 1081 && id <= 1090) || (id >= 1096 && id <= 1100)) {
                        if (skill.getSkillhurt() > 65.0) {
                            skill.setSkillhurt(65.0);
                        }
                    } else if (skill.getSkilltype().equals(TypeUtil.YBYT) || skill.getSkilltype().equals(TypeUtil.BB_XLHC)) {
                        this.addBuff(new GroupBuff(this.getBuffid(), skill.getSkilltype(), data, skill.getSkillhurt()));
                    } else if (PK_MixDeal.isPK(this.BattleType)) {
                        if (skill.getSkilltype().equals(TypeUtil.BB_HW)) {
                            this.addBuff(new GroupBuff(this.getBuffid(), skill.getSkilltype(), data, skill.getSkillhurt()));
                            data.getSkills().remove(j);
                        } else if (skill.getSkilltype().equals(TypeUtil.BB_XR)) {
                            this.addBuff(new GroupBuff(this.getBuffid(), skill.getSkilltype(), data, skill.getSkillhurt()));
                            data.getSkills().remove(j);
                        } else if (skill.getSkilltype().equals(TypeUtil.BB_YH)) {
                            this.addBuff(new GroupBuff(this.getBuffid(), skill.getSkilltype(), data, skill.getSkillhurt()));
                            data.getSkills().remove(j);
                        } else if (skill.getSkilltype().equals(TypeUtil.BB_TM)) {
                            this.addBuff(new GroupBuff(this.getBuffid(), skill.getSkilltype(), data, skill.getSkillhurt()));
                        } else if (skill.getSkilltype().equals("妙手空空") && isV(30.0)) {
                            this.addBuff(new GroupBuff(this.getBuffid(), skill.getSkilltype(), data, skill.getSkillhurt()));
                        } else if (skill.getSkilltype().equals(TypeUtil.BB_MCQH)) {
                            this.addBuff(new GroupBuff(this.getBuffid(), skill.getSkilltype(), data, skill.getSkillhurt()));
                        } else if (skill.getSkilltype().equals(TypeUtil.BB_SGQX)) {
                            this.addBuff(new GroupBuff(this.getBuffid(), skill.getSkilltype(), data, skill.getSkillhurt()));
                        } else if (skill.getSkilltype().equals(TypeUtil.BB_QZ)) {
                            this.addBuff(new GroupBuff(this.getBuffid(), skill.getSkilltype(), data, skill.getSkillhurt()));
                        } else if (skill.getSkilltype().equals(TypeUtil.TY_JS_JTSB)) {
                            int sp = (int) skill.getSkillhurt();
                            if (this.chaosSp > sp) {
                                this.chaosSp = sp;
                            }
                            data.getSkills().remove(j);
                        } else if (skill.getSkilltype().equals(TypeUtil.TY_JS_YZFS)) {
                            data.setSp((int) ((double) data.getSp() + skill.getSkillhurt()));
                            data.getSkills().remove(j);
                        } else if (skill.getSkilltype().equals(TypeUtil.TY_JS_YZFS) && isBB) {
                            data.addAddState("鹞子翻身", 10.0, skill.getSkillhurt(), 99);
                            data.getSkills().remove(j);
                        } else if (skill.getSkilltype().equals(TypeUtil.TY_JS_YZFS)) {
                            data.setSp((int) ((double) data.getSp2() + skill.getSkillhurt()));
                            data.getSkills().remove(j);
                        } else if (id == 1867) {// 以牙还牙处理
                            if (this.NewEvents == null) {
                                this.NewEvents = new ArrayList<>();
                            }
                            DataActionType.getActionById(30).analysisAction(data, null, skill.getSkilltype(), this);
                        }
                    } else if (id == 8054) {//8054	法术波动  穷奇的法术秒10设置
                        data.getSkills().remove(j);
                        for (int k = data.getSkills().size() - 1; k >= 0; --k) {
                            FightingSkill skillTwo = (FightingSkill) data.getSkills().get(k);
                            if (skillTwo.getSkillid() > 1000 && skillTwo.getSkillid() <= 1100) {
                                skillTwo.setSkillsum(10);
                            }
                        }
                    } else if (skill.getSkilltype().equals(TypeUtil.TY_SSC_MCQT)) {
                        data.getSkills().remove(j);
                    }
                }
                this.extracted(data);//吐刚茹柔
            }
        }
    }

    private void extracted(ManData data) {
        //对慢于自己的单位，混乱法术命中提升{5.0}%点；对快于自己的单位，忽视抗混提升{2.5}%点。
        FightingSkill TY_R_SJCR = data.getSkillType("9858");
        if (TY_R_SJCR != null) {
            int mySpeed = data.getSp(); // 自己的速度
            double hitIncrease = 0.0;// 命中提升值
            double resistIgnoreIncrease = 0.0;// 抗混忽视提升值
            for (int j = this.fightingdata.size() - 1; j >= 0; --j) {
                ManData target = (ManData) this.fightingdata.get(j);
                if (data.getType() != 3 && data.getType() != 4 && target.getCamp() == 0) {
                    int targetSpeed = target.getSp();// 目标单位的速度
                    if (targetSpeed < mySpeed) { // 如果目标单位速度慢于自己，增加混乱法术命中率
                        hitIncrease = TY_R_SJCR.getSkillhurt() * 2.0;// 5.0%提升
                    } else {
                        // 如果目标单位速度快于自己，增加忽视抗混能力
                        resistIgnoreIncrease = TY_R_SJCR.getSkillhurt(); // 2.5%提升
                    }
                }
            }
            // 应用命中提升和抗混忽视提升到自己的单位
            data.getQuality().setRoleqhl(data.getQuality().getRoleqhl() + hitIncrease);
            data.getQuality().setRolehshl(data.getQuality().getRolehshl() + resistIgnoreIncrease);
        }
    }

    /**
     * 添加星阵属性
     */
    public void addXK_SX(BaseStar star, BaseQl[] vs, ManData data) {
        for (int i = 0; i < vs.length; ++i) {
            if (vs[i].getKey().equals("经验加成")) {
                data.setExpXS(Double.valueOf((double) data.getExpXS() + vs[i].getValue() * star.getXs() / 100.0));
            } else {
                GetqualityUntil.AddR(data.getQuality(), vs[i].getKey(), vs[i].getValue() * star.getXs());
            }
        }
        int path = FXFightingpath(data.getMan());
        //		星阵属性 星阵属性=星阵名称=星阵的五行属性=宫位属性星卡
        if (star.getVs().length > path + 3) {
            StarPalace palace = GameServer.getStarPalace(star.getVs()[path + 3]);
            if (palace != null) {
                for (int j = 0; j < palace.getVs().length; ++j) {
                    if (palace.getVs()[j].getKey().equals("经验加成")) {
                        data.setExpXS(data.getExpXS() + palace.getVs()[j].getValue() * star.getXs() / 100.0);
                    } else {
                        GetqualityUntil.AddR(data.getQuality(), palace.getVs()[j].getKey(), palace.getVs()[j].getValue() * star.getXs());
                    }
                }
            }
        }
        if (star.getVs()[1].equals("金牛") || star.getVs()[1].equals("苍狼")) {
            for (int k = 0; k < data.getPets().size(); ++k) {
                FightingSummon bbData = data.getPets().get(k);
                bbData.setStar(vs, star.getXs());
            }
        }
    }

    /**
     * 水陆属性修正
     */
    public void sldh() {
        int max = 0;
        for (int i = 0, length = this.fightingdata.size(); i < length; ++i) {
            ManData manData = (ManData) this.fightingdata.get(i);
            if (manData.getType() == 0 && max < manData.getlvl()) {
                max = manData.getlvl();
            }
        }
        for (int i = 0, length = this.fightingdata.size(); i < length; ++i) {
            ManData manData = (ManData) this.fightingdata.get(i);
            if (manData.getType() == 0 && max > manData.getlvl()) {
                int cha = max - manData.getlvl();
                if (cha > 50) {
                    cha = 50;
                }
                GetqualityUntil.AddR(manData.getQuality(), "伤害减免", 0.2 * (double) cha);
                GetqualityUntil.AddR(manData.getQuality(), "伤害加深", 0.3 * (double) cha);
            }
        }
    }

    public int getBuffid() {
        return ++this.buffid;
    }

    public boolean addBuff(GroupBuff buff) {
        //判断是全场唯一 还是阵营唯一
        boolean b = true;
        if (buff.getBuffType().equals(TypeUtil.BB_SGQX) || buff.getBuffType().equals(TypeUtil.BB_TM)) {
            b = false;//全场唯一
        }
        for (int i = this.buffs.size() - 1; i >= 0; --i) {
            GroupBuff buff2 = (GroupBuff) this.buffs.get(i);
            if ((!b || buff.getCamp() == buff2.getCamp()) && buff2.getBuffType().equals(buff.getBuffType())) {
                if (buff.getBuffType().equals(TypeUtil.YBYT)) {
                    if (buff2.getValue() < buff.getValue()) {
                        buff2.setData(buff.getData());
                        buff2.setValue(buff.getValue());
                    }
                } else if (buff.getBuffType().equals(TypeUtil.BB_E_HYMB)) {
                    buff2.setValue(buff.getValue());
                    buff2.setValue2(buff.getValue2());
                }
                return false;
            }
        }
        this.buffs.add(buff);
        return true;
    }

    //获取阵营的buff
    public GroupBuff getBuff(int camp, String type) {
        //双管齐下 讨命 无视阵营
        boolean b = true;
        if (type.equals(TypeUtil.BB_SGQX) || type.equals(TypeUtil.BB_TM)) {
            b = false;//全场唯一
        }
        for (int i = this.buffs.size() - 1; i >= 0; --i) {
            GroupBuff buff = (GroupBuff) this.buffs.get(i);
            if ((!b || camp == buff.getCamp()) && buff.getBuffType().equals(type)) {
                return buff;
            }
        }
        return null;
    }

    public GroupBuff getNoCampBuff(int camp, String type) {
        //全场唯一
        boolean b = !type.equals(TypeUtil.BB_SGQX) && !type.equals(TypeUtil.BB_TM);
        for (int i = this.buffs.size() - 1; i >= 0; --i) {
            GroupBuff buff = (GroupBuff) this.buffs.get(i);
            if ((!b || camp != buff.getCamp()) && buff.getBuffType().equals(type)) {
                return buff;
            }
        }
        return null;
    }

    // 获得角色信息
    public List<GroupBuff> ClearBuff(ManData data) {
        List<GroupBuff> list = null;
        for (int i = this.buffs.size() - 1; i >= 0; --i) {
            GroupBuff buff = (GroupBuff) this.buffs.get(i);
            if (data == buff.getData() && !buff.getBuffType().equals(TypeUtil.BB_HW) && !buff.getBuffType().equals(TypeUtil.BB_SGQX)) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(buff);
                this.buffs.remove(i);
            }
        }
        return list;
    }

    /**
     * 清除单个buff
     */
    public List<GroupBuff> ClearBuff(GroupBuff groupBuff) {
        if (this.buffs.remove(groupBuff)) {
            List<GroupBuff> list = new ArrayList<>();
            list.add(groupBuff);
            return list;
        }
        return null;
    }

    //判断是否有摸个类型的buff添加
    public GroupBuff addbuff(int camp, String type) {
        //双管齐下 讨命 全阵营选出一个
        boolean b = true;
        if (type.equals(TypeUtil.BB_SGQX) || type.equals(TypeUtil.BB_TM)) {
            b = false;//全场唯一
        }
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData data = (ManData) this.fightingdata.get(i);
            if (data.getStates() == 0 && data.getType() == 1 && (!b || camp == data.getCamp())) {
                FightingSkill skill = data.getSkillType(type);
                if (skill != null) {
                    GroupBuff buff = new GroupBuff(this.getBuffid(), type, data, skill.getSkillhurt());
                    if (this.addBuff(buff)) {
                        return buff;
                    }
                    return null;
                }
            }
        }
        return null;
    }

    //单个添加
    public List<GroupBuff> addBuff(ManData data) {
        List<GroupBuff> addbuffs = null;
        for (int j = data.getSkills().size() - 1; j >= 0; --j) {
            FightingSkill fightingSkill = (FightingSkill) data.getSkills().get(j);
            GroupBuff buff = null;
            if (fightingSkill.getSkilltype().equals(TypeUtil.YBYT) || fightingSkill.getSkilltype().equals(TypeUtil.BB_XLHC)) {
                buff = new GroupBuff(this.getBuffid(), fightingSkill.getSkilltype(), data, fightingSkill.getSkillhurt());
            } else if (PK_MixDeal.isPK(this.BattleType)) {
                if (fightingSkill.getSkilltype().equals(TypeUtil.BB_HW)) {
                    buff = new GroupBuff(this.getBuffid(), fightingSkill.getSkilltype(), data, fightingSkill.getSkillhurt());
                    data.getSkills().remove(j);
                } else if (fightingSkill.getSkilltype().equals(TypeUtil.BB_XR)) {
                    buff = new GroupBuff(this.getBuffid(), fightingSkill.getSkilltype(), data, fightingSkill.getSkillhurt());
                    data.getSkills().remove(j);
                } else if (fightingSkill.getSkilltype().equals(TypeUtil.BB_YH)) {
                    buff = new GroupBuff(this.getBuffid(), fightingSkill.getSkilltype(), data, fightingSkill.getSkillhurt());
                    data.getSkills().remove(j);
                } else if (fightingSkill.getSkilltype().equals(TypeUtil.BB_TM)) {
                    buff = new GroupBuff(this.getBuffid(), fightingSkill.getSkilltype(), data, fightingSkill.getSkillhurt());
                } else if (fightingSkill.getSkilltype().equals(TypeUtil.BB_MCQH)) {
                    buff = new GroupBuff(this.getBuffid(), fightingSkill.getSkilltype(), data, fightingSkill.getSkillhurt());
                } else if (fightingSkill.getSkilltype().equals(TypeUtil.BB_SGQX)) {
                    buff = new GroupBuff(this.getBuffid(), fightingSkill.getSkilltype(), data, fightingSkill.getSkillhurt());
                } else if (fightingSkill.getSkilltype().equals("妙手空空") && isV(30.0)) {
                    buff = new GroupBuff(this.getBuffid(), fightingSkill.getSkilltype(), data, fightingSkill.getSkillhurt());
                }
            }
            if (buff != null && this.addBuff(buff)) {
                if (addbuffs == null) {
                    addbuffs = new ArrayList<>();
                }
                addbuffs.add(buff);
            }
        }
        return addbuffs;
    }

    public List<GroupBuff> addBuff(ManData data, FightingSkill skill) {
        List<GroupBuff> addbuffs = null;
        GroupBuff buff = new GroupBuff(this.getBuffid(), skill.getSkilltype(), data, skill.getSkillgain());
        if (skill.getSkillid() == 1313) {
            buff.setValue2(3.0);
        } else if (skill.getSkillid() == 1234) {
            buff.setValue2((double) skill.getSkillcontinued());
        } else if (skill.getSkillid() == 23009) {
            buff.setValue2((double) skill.getSkillcontinued());
        } else if (skill.getSkillid() == 3028) {
            buff.setValue2(1.0);
        }
        if (this.addBuff(buff)) {
            if (addbuffs == null) {
                addbuffs = new ArrayList<>();
            }
            addbuffs.add(buff);
        }
        return addbuffs;
    }

    /**
     * buff反转交替
     */
    public void reverseBuff(ManData data, FightingState fightingState) {
        //化无，明察秋毫，噤若寒蝉，悬刃， 遗患
        StringBuffer buffer = null;
        for (int i = this.buffs.size() - 1; i >= 0; --i) {
            GroupBuff buff = (GroupBuff) this.buffs.get(i);
            if (buff.getCamp() != data.getCamp()) {
                String type = buff.getBuffType();
                if (type.equals(TypeUtil.BB_HW) || type.equals(TypeUtil.BB_MCQH) || type.equals(TypeUtil.BB_XLHC) || type.equals(TypeUtil.BB_XR) || type.equals(TypeUtil.BB_YH)) {
                    if (buffer == null) {
                        buffer = new StringBuffer();
                    }
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append("1=");
                    buffer.append(buff.getBuffId());
                    buffer.append("=");
                    buffer.append(buff.getCamp());
                    buffer.append("=");
                    buffer.append(buff.getBuffType());
                    if (this.getBuff(data.getCamp(), type) == null) {
                        buff.setCamp(data.getCamp());
                        buffer.append("|0=");
                        buffer.append(buff.getBuffId());
                        buffer.append("=");
                        buffer.append(buff.getCamp());
                        buffer.append("=");
                        buffer.append(buff.getBuffType());
                    } else {
                        this.buffs.remove(i);
                    }
                }
            }
        }
        if (buffer != null) {
            fightingState.setBuff(buffer.toString());
        }
    }

    /**
     * 接收战斗回合事件
     */
    public List<FightingEvents> ReceiveEvent() {
        //正式处理战斗数据
        try {
            this.elsum = 0;
            this.CurrentRound = this.battleData.getRound();
            for (ManData data : this.fightingdata) {
                data.setHF(this.CurrentRound);
            }
            this.baohu.clear();
            this.erwai.clear();
            this.huayu.clear();
            this.JLSkills.clear();
            this.recordList.clear();
            if (this.NewEvents == null) {
                this.NewEvents = new ArrayList<>();
            } else {
                this.NewEvents.clear();
            }
            this.Events = (List<FightingEvents>) this.battleData.getPolicyMap().get(this.CurrentRound);
            this.waitSell();
            this.RoundDeal();
        } catch (Exception e) {
            e.printStackTrace();
            WriteOut.addtxt("战斗报错:" + MainServerHandler.getErrorMessage(e), 9999L);
        }
        return this.NewEvents;
    }

    /**
     * 正式处理回合数据
     *
     * @throws InterruptedException
     */
    public void RoundDeal() throws InterruptedException {
        //判断人物是否存在回蓝符回血符
        GroupBuff XZBuff1 = this.getBuff(1, "星阵");
        GroupBuff XZBuff2 = this.getBuff(this.nomy(1), "星阵");
        List<FightingState> BT = null;
        boolean isS = false;
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData data = (ManData) this.fightingdata.get(i);
            if (data.getType() == 0) {
                // 重新排序召唤兽和灵宝出场顺序
                data.updatePets();
                data.updateLings();
                data.updateZhiYuan();
            }
            if (data.getStates() == 1) {
                isS = true;
            }
            if (data.getStates() == 0 && data.getType() != 3 && data.getType() != 4) {
                BT = this.Rid(data, BT);
                if (XZBuff1 != null && data.getCamp() == 1) {
                    if (BT == null) {
                        BT = new ArrayList<>();
                    }
                    FightingState fightingState = new FightingState();
                    data.getFightingState(fightingState);
                    fightingState.setSkillskin((int) XZBuff1.getValue() + "");
                    BT.add(fightingState);
                    XZBuff1 = null;
                } else if (XZBuff2 != null && data.getCamp() != 1) {
                    if (BT == null) {
                        BT = new ArrayList<>();
                    }
                    FightingState fightingState = new FightingState();
                    data.getFightingState(fightingState);
                    fightingState.setSkillskin((int) XZBuff2.getValue() + "");
                    BT.add(fightingState);
                    XZBuff2 = null;
                }
            }
        }
        if (BT != null) {
            FightingEvents ksevents = new FightingEvents();
            ksevents.setCurrentRound(this.CurrentRound);
            ksevents.setAccepterlist(BT);
            this.NewEvents.add(ksevents);
        }
        if (isS) {
            //扶伤处理
            List<FightingState> FS = null;
            for (int j = this.fightingdata.size() - 1; j >= 0; --j) {
                ManData data2 = (ManData) this.fightingdata.get(j);
                if (data2.getStates() == 0 && data2.getType() != 3 && data2.getType() != 4 && data2.xzstate("封印") == null) {
                    FightingSkill skill = data2.getSkillType(TypeUtil.BB_FS);
                    if (skill != null && skill.getSkillhitrate() * 1.5 >= (double) Battlefield.random.nextInt(100)) {
                        List<ManData> datas = MixDeal.get(false, null, 2, this.nomy(data2.getCamp()), 1, 0, 1, 0, 1, -1, this, 1);
                        if (datas.size() != 0) {
                            data2 = (ManData) datas.get(0);
                            if (FS == null) {
                                FS = new ArrayList<>();
                            }
                            FightingState fs2 = new FightingState();
                            fs2.setStartState(TypeUtil.JN);
                            fs2.setSkillskin(skill.getSkilltype());
                            ChangeFighting fighting = new ChangeFighting();
                            fighting.setChangehp((int) ((double) data2.getHp_z() * 0.5));
                            FightingPackage.ChangeProcess(fighting, null, data2, fs2, TypeUtil.JN, FS, this);
                        }
                    }
                }
            }
            if (FS != null) {
                FightingEvents ksevents2 = new FightingEvents();
                ksevents2.setCurrentRound(this.CurrentRound);
                ksevents2.setAccepterlist(FS);
                this.NewEvents.add(ksevents2);
            }
        }
        int[] yao = new int[4];
        List<FightingState> acStates = null;
        for (int k = this.fightingdata.size() - 1; k >= 0; --k) {
            ManData data3 = (ManData) this.fightingdata.get(k);
            if (data3.getStates() == 0 && data3.getType() != 3 && data3.getType() != 4) {
                FightingSkill skill2 = data3.getSkillType("1291");
                if (skill2 != null) {
                    if (data3.xzstate(Petdll.Mypetdll.mypetdll.bianshen(5, 2)) != null) {
                        data3.deleteState(Petdll.Mypetdll.mypetdll.bianshen(5, 2));
                    } else if (data3.xzstate(Petdll.Mypetdll.mypetdll.bianshen(7, 2)) != null) {
                        data3.deleteState(Petdll.Mypetdll.mypetdll.bianshen(7, 2));
                    } else {
                        data3.deleteState(Petdll.Mypetdll.mypetdll.bianshen(6, 2));
                    }
                    data3.addAddState(Petdll.Mypetdll.mypetdll.bianshen(Battlefield.random.nextInt(3) + 5, 2), 0.0, 0.0, 3);
                    FightingEvents zl = new FightingEvents();
                    FightingState accepter = new FightingState();
                    accepter.setCamp(data3.getCamp());
                    accepter.setMan(data3.getMan());
                    List<FightingState> Accepterlist = new ArrayList<>();
                    accepter.setEndState_1(Petdll.Mypetdll.mypetdll.bianshen(Battlefield.random.nextInt(3) + 5, 2));
                    Accepterlist.add(accepter);
                    zl.setAccepterlist(Accepterlist);
                    this.NewEvents.add(zl);
                }
                FightingSkill skill3 = data3.getSkillType("1292");
                if (skill3 != null) {
                    if (data3.getHF() % 4 == 0) {
                        data3.addAddState(Petdll.Mypetdll.mypetdll.bianshen(1, 5), 0.0, 0.0, 2);
                        FightingEvents zl2 = new FightingEvents();
                        FightingState accepter2 = new FightingState();
                        accepter2.setCamp(data3.getCamp());
                        accepter2.setMan(data3.getMan());
                        List<FightingState> Accepterlist2 = new ArrayList<>();
                        accepter2.setEndState_1(Petdll.Mypetdll.mypetdll.bianshen(1, 5));
                        accepter2.setSkillskin(Petdll.Mypetdll.mypetdll.bianshen(2, 5));
                        Accepterlist2.add(accepter2);
                        zl2.setAccepterlist(Accepterlist2);
                        this.NewEvents.add(zl2);
                        data3.UPModel(accepter2, Petdll.Mypetdll.mypetdll.bianshen(3, 5));
                    } else if (data3.getstat(Petdll.Mypetdll.mypetdll.bianshen(1, 5)) == null && data3.getHF() % 2 == 0 && data3.getHF() != 2) {
                        data3.addAddState(Petdll.Mypetdll.mypetdll.bianshen(2, 5), 0.0, 0.0, 2);
                        FightingEvents zl2 = new FightingEvents();
                        FightingState accepter2 = new FightingState();
                        accepter2.setCamp(data3.getCamp());
                        accepter2.setMan(data3.getMan());
                        List<FightingState> Accepterlist2 = new ArrayList<>();
                        accepter2.setEndState_1(Petdll.Mypetdll.mypetdll.bianshen(2, 5));
                        accepter2.setSkillskin(Petdll.Mypetdll.mypetdll.bianshen(1, 5));
                        Accepterlist2.add(accepter2);
                        zl2.setAccepterlist(Accepterlist2);
                        this.NewEvents.add(zl2);
                        data3.UPModel(accepter2, Petdll.Mypetdll.mypetdll.bianshen(4, 5));
                    }
                }
                FightingSkill skill_4010 = data3.getSkillType("4010");
                AddState sh_4010 = data3.xzstate(TypeUtil.SH_4010);
                if (skill_4010 != null && sh_4010 == null && isV(skill_4010.getValue1()) && this.battleData.getRound() > 3) {
                    SummonType.xianzhi(data3, skill_4010);
                    FightingEvents events2 = new FightingEvents();
                    List<FightingState> Accepterlist3 = new ArrayList<>();
                    FightingState state = new FightingState();
                    ChangeFighting change = new ChangeFighting();
                    change.setChangetype(TypeUtil.SH_4010);
                    change.setChangevlaue(skill_4010.getValue2());
                    change.setChangesum(3);
                    data3.ChangeData(change, state);
                    state.setStartState("代价");
                    Accepterlist3.add(state);
                    events2.setAccepterlist(Accepterlist3);
                    this.NewEvents.add(events2);
                    FightingState fightingState2 = new FightingState();
                    if (acStates == null) {
                        acStates = new ArrayList<>();
                    }
                    acStates.add(fightingState2);
                    fightingState2.setCamp(data3.getCamp());
                    fightingState2.setMan(data3.getMan());
                    fightingState2.setStartState("代价");
                    fightingState2.setProcessState("战气如虹");
                    data3.addnq(10, fightingState2);
                    fightingState2.setStartState(TypeUtil.JN);
                    yao[0] = (int) ((double) data3.getZHP_Z() * skill_4010.getValue2());
                    ChangeFighting changeFighting = this.Typeyao(data3, yao);
                    data3.ChangeData(changeFighting, (FightingState) acStates.get(acStates.size() - 1));
                    yao[0] = 0;
                }
                AddState addState = null;
                if (data3.getType() == 0) {
                    addState = data3.xzstate("回蓝");
                    FightingState fightingState3 = new FightingState();
                    if (acStates == null) {
                        acStates = new ArrayList<>();
                    }
                    acStates.add(fightingState3);
                    fightingState3.setCamp(data3.getCamp());
                    fightingState3.setMan(data3.getMan());
                    fightingState3.setStartState("代价");
                    data3.addnq(10, fightingState3);
                    if (addState != null) {
                        fightingState3.setStartState(TypeUtil.JN);
                        yao[1] = (int) addState.getStateEffect();
                        ChangeFighting changeFighting2 = this.Typeyao(data3, yao);
                        data3.ChangeData(changeFighting2, acStates.get(acStates.size() - 1));
                        yao[1] = 0;
                    }
                    if (data3.getSkillType(TypeUtil.TJ_SY) != null) {
                        data3.addyq(20, fightingState3);//怨气每回合加
                    }
                    if (data3.getSkillType(TypeUtil.TJ_CN) != null) {
                        data3.addnq(5, fightingState3);//怨气每回合加
                    }
                    addState = data3.xzstate("回血");
                    if (addState != null) {
                        FightingState fightingState4 = new FightingState();
                        if (acStates == null) {
                            acStates = new ArrayList<>();
                        }
                        acStates.add(fightingState4);
                        fightingState4.setCamp(data3.getCamp());
                        fightingState4.setMan(data3.getMan());
                        fightingState4.setStartState("代价");
                        fightingState4.setProcessState("清心静气");
                        data3.addnq(10, fightingState4);
                        if (addState != null) {
                            fightingState4.setStartState(TypeUtil.JN);
                            yao[0] = (int) addState.getStateEffect();
                            ChangeFighting changeFighting3 = this.Typeyao(data3, yao);
                            data3.ChangeData(changeFighting3, acStates.get(acStates.size() - 1));
                            yao[0] = 0;
                        }
                    }
                }
                addState = data3.xzstate(TypeUtil.FB_BGZ);
                if (addState != null) {
                    if (acStates == null) {
                        acStates = new ArrayList<>();
                    }
                    FightingState fightingState3 = new FightingState();
                    fightingState3.setStartState(TypeUtil.JN);
                    acStates.add(fightingState3);
                    ChangeFighting changeFighting2 = new ChangeFighting();
                    changeFighting2.setChangemp((int) (-addState.getStateEffect()));
                    data3.ChangeData(changeFighting2, (FightingState) acStates.get(acStates.size() - 1));
                }
                FightingSkill skill4 = data3.getSkillType(TypeUtil.BB_LSDC);
                if (skill4 != null) {
                    int hmp = (int) ((double) data3.getMp_z() * skill4.getSkillhurt() / 100.0);
                    if (hmp != 0) {
                        if (acStates == null) {
                            acStates = new ArrayList<>();
                        }
                        FightingState fightingState5 = new FightingState();
                        fightingState5.setStartState("代价");
                        acStates.add(fightingState5);
                        ChangeFighting changeFighting4 = new ChangeFighting();
                        changeFighting4.setChangemp(hmp);
                        data3.ChangeData(changeFighting4, fightingState5);
                    }
                }
                //全队只生效一次！只生效一次
                FightingSkill TY_R_SJCR = data3.getSkillType("9855");
                if (TY_R_SJCR != null && !this.skillActivated) {
                    double sacrificeAmount = data3.getQuality().getRolehshl() * 0.1;// 牺牲的抗混数值，即抗混数值的10%
                    double increaseAmount = sacrificeAmount * TY_R_SJCR.getSkillhurt() / 100.0; // 增加的抗混乱值等于牺牲数值乘以技能的伤害值
                    // 牺牲自己，忽视抗混数值的10%
                    data3.getQuality().setRolehshl(data3.getQuality().getRolehshl() - sacrificeAmount);
                    // 增加整队的抗混乱值
                    this.increaseTeamRolekhl(increaseAmount, "混");
                    this.skillActivated = true;// 将标记设置为已经被获取并生效
                }
                //全队只生效一次！只生效一次
                TY_R_SJCR = data3.getSkillType(TypeUtil.TY_R_KJZR);
                if (TY_R_SJCR != null && !this.skillActi) {
                    double sacrificeAmount = data3.getQuality().getRolehsfy() * 0.1;
                    double increaseAmount = sacrificeAmount * TY_R_SJCR.getSkillhurt() / 100.0;
                    data3.getQuality().setRolehsfy(data3.getQuality().getRolehsfy() - sacrificeAmount);
                    this.increaseTeamRolekhl(increaseAmount, "封");
                    this.skillActi = true;// 将标记设置为已经被获取并生效
                }
                TY_R_SJCR = data3.getSkillType(TypeUtil.TY_R_XSJW);
                if (TY_R_SJCR != null && !this.skillActisleep) {
                    double sacrificeAmount = data3.getQuality().getRolehshs() * 0.1;// 牺牲的抗睡数值，即抗睡数值的10%
                    double increaseAmount = sacrificeAmount * TY_R_SJCR.getSkillhurt() / 100.0;// 增加的抗混乱值等于牺牲数值乘以技能的伤害值
                    // 牺牲自己，忽视抗睡数值的10%
                    data3.getQuality().setRolehshs(data3.getQuality().getRolehshs() - sacrificeAmount);
                    // 增加整队的抗睡乱值
                    this.increaseTeamRolekhl(increaseAmount, "睡");
                    this.skillActisleep = true;// 将标记设置为已经被获取并生效
                }
                if (data3.getType() == 0) {
                    this.Lz(data3);
                }
            }
        }
        if (acStates != null) {
            FightingEvents ksevents3 = new FightingEvents();
            ksevents3.setCurrentRound(this.CurrentRound);
            ksevents3.setAccepterlist(acStates);
            this.NewEvents.add(ksevents3);
        }
        List<ManData> glDataList = this.L_GL_New();
        ManData myData = null;
        List<FightingState> xieStates = new ArrayList<>();
        //处理数据前先判断是否存在回合开始被动技能 身上没有不良状态
        for (int l = 0; l < this.fightingdata.size(); ++l) {
            ManData manData = (ManData) this.fightingdata.get(l);
            manData.clearBuff();
            if (manData.getType() != 4 && manData.getType() != 3) {
                if (manData.getStates() == 0) {
                    if (manData.getType() == 1 && manData.getMan() >= 5 && manData.getMan() < 10) {
                        FightingSkill sk1 = manData.getSkillType("忠诚");
                        FightingSkill sk2 = manData.getSkillType(TypeUtil.BB_LY);
                        if (sk1 != null || sk2 != null) {
                            int path = this.Datapathhuo(manData.getCamp(), manData.getMan() - 5);
                            if (path != -1) {
                                ManData data4 = (ManData) this.fightingdata.get(path);
                                if (sk1 != null) {
                                    FightingState fightingState5 = new FightingState();
                                    fightingState5.setStartState(TypeUtil.JN);
                                    fightingState5.setSkillskin("1804");
                                    xieStates.add(fightingState5);
                                    yao[2] = 5;
                                    ChangeFighting changeFighting4 = this.Typeyao(data4, yao);
                                    data4.ChangeData(changeFighting4, fightingState5);
                                    yao[2] = 0;
                                }
                                if (sk2 != null) {
                                    FightingState fightingState5 = new FightingState();
                                    fightingState5.setStartState(TypeUtil.JN);
                                    fightingState5.setSkillskin(sk2.getSkilltype());
                                    xieStates.add(fightingState5);
                                    ChangeFighting changeFighting4 = this.Typeyao(data4, yao);
                                    data4.ChangeData(changeFighting4, fightingState5);
                                    data4.addyq(20, fightingState5);
                                }
                            }
                        }
                    }
                    FightingSkill DY = manData.getSkillType(TypeUtil.BB_DY);
                    if (DY != null && isV(DY.getSkillhitrate())) {
                        List<ManData> datas2 = MixDeal.get(false, null, 0, this.nomy(manData.getCamp()), 1, 0, 1, 0, 1, 3, this, 1);
                        if (datas2.size() != 0) {
                            ManData data5 = (ManData) datas2.get(0);
                            if (data5.getvalue(3) < 0.3) {
                                FightingState fightingState6 = new FightingState();
                                fightingState6.setStartState(TypeUtil.JN);
                                fightingState6.setSkillskin(DY.getSkilltype());
                                yao[3] = 50;
                                ChangeFighting changeFighting3 = this.Typeyao(data5, yao);
                                data5.ChangeData(changeFighting3, fightingState6);
                                yao[3] = 0;
                                xieStates.add(fightingState6);
                            }
                        }
                    }

                    AddState addState2 = manData.xzstate("沧溟露");
                    if (addState2 != null) {
                        FightingState fightingState1 = new FightingState();
                        fightingState1.setCamp(manData.getCamp());
                        fightingState1.setMan(manData.getMan());
                        ChangeFighting changeFighting1 = new ChangeFighting();
                        changeFighting1.setChangehp((int) (addState2.getStateEffect() / 100.0D * manData.getHp_z()));
                        changeFighting1.setChangemp((int) (addState2.getStateEffect() / 100.0D * manData.getMp_z()));
                        manData.ChangeData(changeFighting1, fightingState1);
                        xieStates.add(fightingState1);
                    }
                    DY = manData.getAppendSkill(9325);
                    if (DY != null) {
                        FightingState fightingState7 = new FightingState();
                        fightingState7.setStartState(TypeUtil.JN);
                        ChangeFighting changeFighting5 = new ChangeFighting();
                        changeFighting5.setChangehp(-(int) ((double) manData.getHp_z() * DY.getSkillhurt() / 100.0));
                        manData.ChangeData(changeFighting5, fightingState7);
                        xieStates.add(fightingState7);
                    }
                    FightingSkill MYL = manData.getSkillType(TypeUtil.TY_L_10109);
                    if (MYL != null && myData != null && glDataList != null) {
                        for (int m = 0; m < glDataList.size(); ++m) {
                            ManData data4 = (ManData) glDataList.get(m);
                            if (data4 != null && data4.xzstate("甘霖") != null) {
                                FightingState fightingState5 = new FightingState();
                                if (acStates == null) {
                                    acStates = new ArrayList<>();
                                }
                                acStates.add(fightingState5);
                                fightingState5.setCamp(data4.getCamp());
                                fightingState5.setMan(data4.getMan());
                                fightingState5.setStartState("代价");
                                data4.addnq((int) (13.0 * MYL.getSkillhurt() / 100.0), fightingState5);
                                myData.getSkills().remove(MYL);
                            }
                        }
                    }
                    MYL = manData.getSkillType(TypeUtil.TY_L_10110);
                    if (MYL != null && glDataList != null) {
                        for (int m = 0; m < glDataList.size(); ++m) {
                            ManData data4 = (ManData) glDataList.get(m);
                            if (data4 != null && data4.xzstate("甘霖") != null) {
                                data4.getQuality().setK_qk(data4.getQuality().getK_qk() + MYL.getSkillhurt());
                                manData.getSkills().remove(MYL);
                            }
                        }
                    }
                    if (manData.getSkillType("自医") != null && manData.getvalue(1) < 0.3 && Battlefield.random.nextInt(100) < 30) {
                        FightingState fightingState3 = new FightingState();
                        fightingState3.setStartState(TypeUtil.JN);
                        fightingState3.setSkillskin("1805");
                        yao[2] = 50;
                        ChangeFighting changeFighting2 = this.Typeyao(manData, yao);
                        manData.ChangeData(changeFighting2, fightingState3);
                        yao[2] = 0;
                        xieStates.add(fightingState3);
                    }
                    FightingSkill skill4 = manData.getSkillId(23005);
                    if (skill4 != null && isV(30.0)) {
                        FightingState fightingState6 = new FightingState();
                        fightingState6.setStartState(TypeUtil.JN);
                        ChangeFighting changeFighting3 = new ChangeFighting();
                        changeFighting3.setChangetype("清除异常状态");
                        manData.ChangeData(changeFighting3, fightingState6);
                        fightingState6.setText("神清气正#2");
                        xieStates.add(fightingState6);
                    }
                    skill4 = manData.getSkillId(23007);
                    int wei = this.Datapathhuo(manData.getCamp(), manData.getMan() + 5);
                    if (skill4 != null && wei == -1 && isV(30.0)) {
                        FightingState fightingState5 = new FightingState();
                        ChangeFighting changeFighting4 = new ChangeFighting();
                        fightingState5.setStartState(TypeUtil.JN);
                        changeFighting4.setChangetype("知耻后勇");
                        changeFighting4.setChangesum(1);
                        manData.ChangeData(changeFighting4, fightingState5);
                        fightingState5.setText("知耻后勇#2");
                        xieStates.add(fightingState5);
                    }
                    addState2 = manData.xzstate("中毒");
                    if (addState2 != null) {
                        //判断是否有厚积薄发
                        AddState HJBF = manData.xzstate(TypeUtil.TZ_HJBF);
                        FightingState fightingState8 = new FightingState();
                        fightingState8.setStartState(TypeUtil.JN);
                        fightingState8.setCamp(manData.getCamp());
                        fightingState8.setMan(manData.getMan());
                        ChangeFighting changeFighting6 = this.TypeHurtTotal(addState2.getStateEffect());
                        if (HJBF != null) {
                            changeFighting6.setChangehp(changeFighting6.getChangehp() * addState2.getSurplus());
                        }
//                        // 曼陀罗处理
//                        AddState addState3 = manData.xzstate(TypeUtil.TJ_MTL);
//                        if (addState3 != null) {
//                            int changehp = changeFighting6.getChangehp();
//                            changeFighting6.setChangemp((int)((double)changehp * addState3.getStateEffect()));
//                        }
//                        addState3 = manData.xzstate(TypeUtil.TJ_FSSS);
//                        if (addState3 != null) {
//                            int changehp = changeFighting6.getChangehp();
//                            changeFighting6.setChangemp((int)((double)changehp * addState3.getStateEffect()));
//                        }
                        FightingPackage.ChangeProcess(changeFighting6, null, manData, fightingState8, "中毒", xieStates, this);
                        if (HJBF != null) {
                            fightingState8.setEndState_2(addState2.getStatename());
                            fightingState8.setEndState_2(TypeUtil.TZ_HJBF);
                            manData.getAddStates().remove(addState2);
                            manData.getAddStates().remove(HJBF);
                        }
                        if (manData.getStates() != 0) {
                            FightingSkill skill5 = manData.getSkillType(TypeUtil.TY_R_ZTBD);
                            if (skill5 != null) {
                                FightingState gjz = new FightingState();
                                gjz.setCamp(manData.getCamp());
                                gjz.setMan(manData.getMan());
                                gjz.setStartState("代价");
                                manData.addnq((int) skill5.getSkillhurt(), gjz);
                            }
                        }
                    }
                    addState2 = manData.xzstate("燃烧");
                    if (addState2 != null) {
                        FightingState fightingState9 = new FightingState();
                        fightingState9.setStartState(TypeUtil.JN);
                        fightingState9.setCamp(manData.getCamp());
                        fightingState9.setMan(manData.getMan());
                        ChangeFighting changeFighting = this.TypeHurtTotal(addState2.getStateEffect());
                        FightingPackage.ChangeProcess(changeFighting, null, manData, fightingState9, "火", xieStates, this);
                    }
                    addState2 = manData.xzstate(TypeUtil.TY_ZD_AHBY);
                    if (addState2 != null && addState2.getSurplus() <= 1) {
                        int hutr = 0;
                        FightingSkill TY_R_CYMM = manData.getSkillType(TypeUtil.TY_R_CYMM);
                        if (TY_R_CYMM != null) {
                            hutr = (int) TY_R_CYMM.getSkillhurt();
                        }
                        FightingState fightingState10 = new FightingState();
                        fightingState10.setStartState(TypeUtil.JN);
                        fightingState10.setCamp(manData.getCamp());
                        fightingState10.setMan(manData.getMan());
                        ChangeFighting changeFighting7 = this.TypeHurtTotal(addState2.getStateEffect() * (double) hutr / 100.0);
                        FightingPackage.ChangeProcess(changeFighting7, null, manData, fightingState10, "中毒", xieStates, this);
                    }
                    addState2 = manData.xzstate(TypeUtil.TY_H_ZSMH_S);
                    if (addState2 != null) {
                        FightingState fightingState9 = new FightingState();
                        fightingState9.setStartState(TypeUtil.JN);
                        fightingState9.setCamp(manData.getCamp());
                        fightingState9.setMan(manData.getMan());
                        ChangeFighting changeFighting = new ChangeFighting();
                        //护盾存在期间每个攻击自己的敌方目标都会受到灼烧,处于该状态下的目标下回合开始前随机受到自已气血上限（0.5%*等级）~（1.5%*等级）的伤害,护盾最多持续3回合。(仅在与玩家之间战斗有效
                        int hurt = (int) ((double) manData.getHp_z() * addState2.getStateEffect() / 100.0);
                        hurt = (int) ((double) hurt * (1.0 + (double) Battlefield.random.nextInt(200) / 200.0));
                        changeFighting.setChangehp(-hurt);
                        FightingPackage.ChangeProcess(changeFighting, null, manData, fightingState9, TypeUtil.TY_H_ZSMH, xieStates, this);
                    }
                    addState2 = manData.xzstate("毒针轻刺");
                    if (addState2 != null) {
                        FightingState fightingState9 = new FightingState();
                        fightingState9.setStartState(TypeUtil.JN);
                        fightingState9.setCamp(manData.getCamp());
                        fightingState9.setMan(manData.getMan());
                        ChangeFighting changeFighting = new ChangeFighting();
                        changeFighting.setChangehp((int) (-addState2.getStateEffect()));
                        FightingPackage.ChangeProcess(changeFighting, null, manData, fightingState9, "毒针轻刺", xieStates, this);
                    }
                    addState2 = manData.xzstate(TypeUtil.TY_G_10080);
                    //战斗开始判断状态是否存在如果存在增加技能熟练度，存在2回合，那么2回合结束如何恢复技能熟练度呢？
                    if (addState2 != null && manData.getType() == 0 && manData.getStates() == 0) {
                        for (int k2 = 0; k2 < manData.getSkills().size(); ++k2) {
                            FightingSkill skill6 = (FightingSkill) manData.getSkills().get(k2);
                            if (skill6 != null && skill6.getSkillid() >= 1001 && skill6.getSkillid() <= 1100) {
                                ((FightingSkill) manData.getSkills().get(k2)).setSkillsld(manData.getSkills().get(k2).getSkillsld() + addState2.getStateEffect());
                            }
                        }
                    }
                    //初始战斗加载神通被动技能加入状态中
                    FightingSkill skill7 = manData.getSkillType(TypeUtil.TY_YW_MDHL);
                    FightingSkill skill6 = manData.getSkillType("10057");
                    addState2 = manData.xzstate("遗忘");
                    if (skill7 != null && skill6 != null && addState2 != null && manData.getType() == 0 && isV(skill6.getSkillhurt())) {
                        manData.addAddState(skill7.getSkilltype(), skill7.getSkillhurt(), 0.0, 2);
                    }
                    //怒火冲冠|怒气达到50以上时，加强昏睡、加强混乱根据当前怒气值获得临时提升，最多{1}%点）
                    String[] s = new String[]{TypeUtil.TY_R_NHCG_SH, TypeUtil.TY_R_NHCG_FH, TypeUtil.TY_R_NHCG_FS, TypeUtil.TY_R_NHCG_FD, TypeUtil.TY_R_NHCG_SD};
                    int length = s.length;
                    int n = 0;
                    while (n < length) {
                        String string = s[n];
                        skill4 = manData.getSkillType(string);
                        if (skill4 != null && manData.getNqz() >= 50) {
                            manData.getQuality().addnhcg(skill4, splitSkillNames(skill4.getSkillname()), manData.getNqz());
                            break;
                        } else {
                            ++n;
                        }
                    }
                    //10021|集灵盛怒(雷火)|怒气达到50以上时，忽视抗雷、忽视抗火根据当前怒气值获得临时提升，最多{公式一}%点（对应100怒气）(仅在与玩家之间战斗有效)
                    String[] jlsn = new String[]{"9981", "9991", "10021", "10031", "9971"};
                    int length2 = jlsn.length;
                    int n2 = 0;
                    while (n2 < length2) {
                        String string2 = jlsn[n2];
                        skill4 = manData.getSkillType(string2);
                        if (skill4 != null && manData.getNqz() >= 50) {
                            manData.getQuality().addjlsn(skill4, splitSkillNames(skill4.getSkillname()), manData.getNqz());
                            break;
                        } else {
                            ++n2;
                        }
                    }
                    //10020|集灵释怒(雷火)|每消耗100怒气，本场战斗忽视抗雷、忽视抗火永久增加{公式一}%点。最多增加{公式零零}%点(仅在与玩家之间战斗有效)
                    if (PK_MixDeal.isPK(this.BattleType)) {
                        // 计算已消耗的100怒气单位
                        int consumedNqUnits = manData.getConsumedNq() / 100;
                        // 判断已消耗的100怒气单位是否大于0
                        if (consumedNqUnits > 0) {
                            // 在这里进行消耗100怒气单位后的逻辑判断
                            String[] L = new String[]{"9970", "9980", "9990", "10020", "10030"};
                            int length3 = L.length;
                            int n3 = 0;
                            while (n3 < length3) {
                                String string3 = L[n3];
                                skill4 = manData.getSkillType(string3);
                                if (skill4 != null) {
                                    manData.getQuality().addjl(skill4, splitSkillNames(skill4.getSkillname()), consumedNqUnits);
                                    break;
                                } else {
                                    ++n3;
                                }
                            }
                        }
                    }
                    //10022|孤勇破军(雷火)|出手时若没有召唤兽，自身忽视抗雷、忽视抗火临时提升{公式一}%点。
                    String[] gypj;
                    for (String string4 : gypj = new String[]{"9972", "9982", "9992", "10022", "10032"}) {
                        skill4 = manData.getSkillType(string4);
                        if (skill4 != null) {
                            int ren = this.Datapathhuo(manData.getCamp(), manData.getMan());
                            if (ren == -1) {
                                manData.getQuality().addgypj(skill4, splitSkillNames(skill4.getSkillname()));
                                manData.getSkills().remove(skill4);
                                break;
                            }
                        }
                    }
                    //10023|离雁余威(雷火)|自己每离场1个召唤兽，本场战斗忽视抗雷、忽视抗火永久增加{0.1}%点，最多增加{5}%点。
                    String[] lyyw = new String[]{"9973", "9983", "9993", "10023", "10033"};
                    int length5 = lyyw.length;
                    int n5 = 0;
                    while (n5 < length5) {
                        String string3 = lyyw[n5];
                        skill4 = manData.getSkillType(string3);
                        if (skill4 != null) {
                            manData.getQuality().addlyyw(skill4, splitSkillNames(skill4.getSkillname()), manData.getDeparture());
                            break;
                        } else {
                            ++n5;
                        }
                    }
                    String[] d = {TypeUtil.TY_R_GXSZ_SH, TypeUtil.TY_R_GXSZ_FH, TypeUtil.TY_R_GXSZ_FS, TypeUtil.TY_R_GXSZ_FD, TypeUtil.TY_R_GXSZ_SD};
                    //处于昏睡状态的单位，通过回合前自动增加、受到伤害、自己施法获得的怒气衰减{公式一}%；处于中毒状态的单位，通过回合前自动增加、受到伤害、自己施法获得的怒气衰减{公式零零}%。
                    int dataSize = this.fightingdata.size();
                    int length6 = d.length;
                    int n6 = 0;
                    while (n6 < length6) {
                        String string5 = d[n6];
                        skill4 = manData.getSkillType(string5);
                        if (skill4 != null) {
                            for (int k3 = dataSize - 1; k3 >= 0; --k3) {
                                //整个战场中寻找身上有状态的玩家以及NPC或者怪物
                                ManData data6 = (ManData) this.fightingdata.get(k3);
                                if (data6.getType() != 3 && data6.getType() != 4 && data6.getCamp() != 1) {
                                    if (data6.xzstate("昏睡") != null || data6.xzstate("封印") != null || data6.xzstate("混乱") != null) {
                                        data6.getnqzad((int) skill4.getSkillhurt());
                                    }
                                    if (data6.xzstate("中毒") != null) {
                                        data6.getnqzad((int) skill4.getSkillgain());
                                    }
                                }
                            }
                            break;
                        } else {
                            ++n6;
                        }
                    }
                    if (manData.getSkillType("顾影自怜") != null && MixDeal.isgyzl(manData.getCamp(), this)) {
                        FightingState fightingState11 = new FightingState();
                        ChangeFighting changeFighting8 = new ChangeFighting();
                        changeFighting8.setChangetype("清除异常状态");
                        manData.ChangeData(changeFighting8, fightingState11);
                        skill4 = manData.getSkillType(TypeUtil.BB_E_QHHYZL);
                        if (skill4 != null && isV(skill4.getSkillgain()) && (skill4.getSkillcontinued() == 0 || this.CurrentRound >= skill4.getSkillcontinued() + 5)) {
                            skill4.setSkillcontinued(this.CurrentRound);
                            changeFighting8.setSkills(null);
                            changeFighting8.setChangetype("隐身");
                            changeFighting8.setChangesum(1);
                            manData.ChangeData(changeFighting8, fightingState11);
                            fightingState11.setText("强化顾影自怜#2");
                        }
                        fightingState11.setStartState("代价");
                        xieStates.add(fightingState11);
                    }
                    if (manData.getSkillType("行云流水") != null) {
                        for (int k4 = 0; k4 <= manData.getAddStates().size() - 1; ++k4) {
                            if (((AddState) manData.getAddStates().get(k4)).getStatename().equals("隐身")) {
                                manData.setSp(Math.toIntExact((long) ((double) manData.getSp() * 0.25 + (double) manData.getSp())));
                            }
                        }
                    }
                    if (manData.getType() == 0 && PK_MixDeal.isPK(this.BattleType)) {
                        //判断是否有时过境迁
                        skill4 = manData.getSkillType(TypeUtil.TZ_SGJQ);
                        if (skill4 != null && skill4.getSkillhurt() > (double) Battlefield.random.nextInt(100)) {
                            List<ManData> datas3 = MixDeal.get(false, null, 1, manData.getCamp(), 1, 1, 1, 0, 1, -1, this, 1);
                            if (datas3.size() != 0) {
                                ((ManData) datas3.get(0)).addAddState(TypeUtil.TZ_SGJQ, 0.0, 0.0, 1);
                            }
                        }
                    }
                    // 灵犀-回合开始前被动处理
                    if (manData.getType() < 3) {
                        manData.before(xieStates, null);
                    }
                    switch (manData.fmshfssx()) {// 判断兽魂俯首
                        case 1: {
                            FightingState accepter3 = new FightingState();
                            accepter3.setUp("HP", manData.getHp_z());
                            this.NewEvents.add(new FightingEvents(Arrays.asList(new FightingState[]{accepter3})));
                            break;
                        }
                        case 2: {
                            FightingState accepter3 = new FightingState();
                            accepter3.setUp("MP", manData.getMp_z());
                            this.NewEvents.add(new FightingEvents(Arrays.asList(new FightingState[]{accepter3})));
                            break;
                        }
                    }
                } else if (manData.getStates() == 1) {
                    //判断是否有复活药
                    AddState addState4 = manData.fhy();
                    if (addState4 != null) {
                        FightingState fightingState7 = new FightingState();
                        fightingState7.setStartState(TypeUtil.JN);
                        fightingState7.setSkillskin("1509");
                        fightingState7.setCamp(manData.getCamp());
                        fightingState7.setMan(manData.getMan());
                        fightingState7.setText("复活");
                        ChangeFighting changeFighting5 = new ChangeFighting();
                        changeFighting5.setChangehp((int) addState4.getStateEffect());
                        FightingPackage.ChangeProcess(changeFighting5, null, manData, fightingState7, "复活", xieStates, this);
                    }
                }
                if (manData.getAppendSkill(9612) != null) {
                    AddState addState4 = manData.xzstate("甘霖");
                    List<ManData> datas2 = MixDeal.get(true, manData, 2, this.nomy(manData.getCamp()), 0, 0, 0, 0, 1, -1, this, 1);
                    if (datas2.size() != 0 && addState4 != null) {
                        ManData data7 = (ManData) datas2.get(0);
                        FightingState fightingState6 = new FightingState();
                        ChangeFighting changeFighting3 = new ChangeFighting();
                        changeFighting3.setSkills(null);
                        changeFighting3.setChangetype("甘霖附加");
                        changeFighting3.setChangesum(addState4.getSurplus());
                        changeFighting3.setChangevlaue(addState4.getStateEffect());
                        data7.ChangeData(changeFighting3, fightingState6);
                        fightingState6.setStartState("代价");
                        xieStates.add(fightingState6);
                    }
                    double iv = 100.0;
                    FightingSkill TY_L_10117 = manData.getSkillType(TypeUtil.TY_L_10117);
                    if (TY_L_10117 != null) {
                        iv = TY_L_10117.getSkillhurt();
                    }
                    if (addState4 != null && isV(iv)) {
                        addState4.setSurplus(0);
                    }
                }
                if (manData.getCamp() == 0 && manData.xzstate("中毒") != null) {
                    FightingSkill TY_R_HDJN = manData.getSkillType(TypeUtil.TY_R_HDJN);
                    if (TY_R_HDJN != null) {
                        FightingState fightingState7 = new FightingState();
                        fightingState7.setCamp(manData.getCamp());
                        fightingState7.setMan(manData.getMan());
                        fightingState7.setStartState("代价");
                        manData.addnq((int) TY_R_HDJN.getSkillhurt(), fightingState7);
                    }
                }
                if (PK_MixDeal.isPK(this.BattleType)) {// 仅在与玩家之间战斗有效
                    // 微步凌波 如果对方最快单位速度高出自己速度超过50，则自己单次增加{公式一}点速度，最多增加{公式六十五}点速度(仅在与玩家之间战斗有效)
                    FightingSkill skill8 = manData.getAppendSkill(TypeUtil.TY_JS_WBLB);
                    if (skill8 != null) {
                        List<ManData> datas2 = MixDeal.get(false, manData, 0, this.nomy(manData.getCamp()), 1, 0, 0, 0, 1, 6, this, 1);
                        int m = 0;
                        while (m < datas2.size()) {
                            ManData data4 = (ManData) datas2.get(m);
                            if (data4.getSp() > manData.getSp() + 50) {
                                double totalStateEffect;
                                double stateEffect = totalStateEffect = skill8.getSkillhurt();
                                AddState addState5 = data4.getstat("微步凌波");
                                if (addState5 != null) {
                                    totalStateEffect = addState5.getStateEffect();
                                }
                                if (totalStateEffect > skill8.getSkillgain()) {
                                    stateEffect = totalStateEffect - skill8.getSkillgain();
                                }
                                data4.addAddState("微步凌波", stateEffect, 0.0, 99);
                                manData.setSp(manData.getSp() + (int) stateEffect);
                                break;
                            } else {
                                ++m;
                            }
                        }
                    }
                }
            }
        }
        if (xieStates.size() != 0) {
            FightingEvents ksevents4 = new FightingEvents();
            ksevents4.setCurrentRound(this.CurrentRound);
            ksevents4.setAccepterlist(xieStates);
            this.NewEvents.add(ksevents4);
        }
        //初始化指令处理
        if (this.CurrentRound == 1) {
            DataActionType.getActionById(32).analysisAction(null, null, null, this);
        }
        AI_MixDeal.AI_KS(this);
        for (int l = this.fightingdata.size() - 1; l >= 0; --l) {
            ManData data8 = (ManData) this.fightingdata.get(l);
            if (data8.getStates() == 1) {
                AI_MixDeal.AI_Die(this, data8);
            }
        }
        PK_MixDeal.PK_ST(this);
        this.guilei();//指令归类
        this.ZLCL();//指令处理
        MixDeal.flee(this, 1);//忽如一夜判断
        this.ZLCL();//忽如一夜后续指令处理
        this.L_GL();//甘霖回血处理
        this.L_GLFJ();//甘霖附加回血处理
        this.fangyu.clear();
        this.huifu();
        this.huifu1();
        this.XP_YYBJ();
        this.XP_WYQS();
        MixDeal.HNYG(this);//患难与共判断
        this.SXBuff();//buff刷新
        try {
            Map<String, FightingStatistics> fightingStatisticsMap = this.battleData.getFightingForesee().getFightingStatisticsMap(this);
            if (fightingStatisticsMap != null) {
                fightingStatisticsMap.forEach((k, v)/* java.lang.String,come.tool.Battle.FightingStatistics, */ -> {
                    if (v != null) {
                        v.setPetNum(0);
                        v.setLingNum(0);
                    }
                    return;
                });
            }
        } catch (Exception e) {
            System.out.printf("统计初始化报错", new Object[0]);
            e.printStackTrace();
            System.out.printf("--------------------------------------------", new Object[0]);
        }
        if (PK_MixDeal.isPK(this.BattleType)) {
            this.dfxy();// 定风息焰处理-遗忘 被遗忘的人物，回合末{公式一}%几率损失2点怒气
        }
        for (int l = this.fightingdata.size() - 1; l >= 0; --l) {
            if (((ManData) this.fightingdata.get(l)).getType() == 1) {
                this.fightingdata.remove(l);
            }
        }
        for (int j2 = this.fightingdata.size() - 1; j2 >= 0; --j2) {
            ManData fighting2 = (ManData) this.fightingdata.get(j2);
            if (fighting2.getType() == 0) {
                int k5 = 0;
                while (k5 < fighting2.getPets().size()) {
                    if (((FightingSummon) fighting2.getPets().get(k5)).getPlay() == 1) {
                        ManData data9 = ((FightingSummon) fighting2.getPets().get(k5)).getPet(fighting2.isAi);
                        if (data9 != null) {
                            this.fightingdata.add(data9);
                            break;
                        } else {
                            break;
                        }
                    } else {
                        ++k5;
                    }
                }
                try {
                    for (k5 = 0; k5 < fighting2.getPets().size(); ++k5) {
                        if (((FightingSummon) fighting2.getPets().get(k5)).getPlay() == 2) {
                            Map<String, FightingStatistics> fightingStatisticsMap2 = this.battleData.getFightingForesee().getFightingStatisticsMap(this);
                            FightingStatistics fightingStatistics = (FightingStatistics) fightingStatisticsMap2.get(fighting2.getCamp() + "");
                            fightingStatistics.setPetNum(fightingStatistics.getPetNum() + 1);
                        }
                    }
                    for (k5 = 0; k5 < fighting2.getLings().size(); ++k5) {
                        if (((FightingLingbao) fighting2.getLings().get(k5)).getPlay() == 2) {
                            Map<String, FightingStatistics> fightingStatisticsMap2 = this.battleData.getFightingForesee().getFightingStatisticsMap(this);
                            FightingStatistics fightingStatistics = (FightingStatistics) fightingStatisticsMap2.get(fighting2.getCamp() + "");
                            fightingStatistics.setLingNum(fightingStatistics.getLingNum() + 1);
                        }
                    }
                } catch (Exception e2) {
                    System.out.printf("战斗统计报错。", new Object[0]);
                    e2.printStackTrace();
                }
            }
        }
        //清算状态
        List<FightingState> removeStates = new ArrayList<>();
        for (int j3 = 0; j3 < this.fightingdata.size(); ++j3) {
            ManData manData2 = (ManData) this.fightingdata.get(j3);
            if (manData2.getStates() != 2) {
                FightingSkill skill_4011 = manData2.getSkillType("4017");
                if (skill_4011 != null) {
                    if (isV(skill_4011.getValue2())) {
                        AddState xzstate = manData2.xzstate("混乱");
                        if (xzstate != null && xzstate.getSurplus() > 0) {
                            int surplus = xzstate.getSurplus();
                            if (surplus >= 2) {
                                xzstate.setSurplus(xzstate.getSurplus() - 2);
                            } else {
                                xzstate.setSurplus(0);
                            }
                        }
                        xzstate = manData2.xzstate("封印");
                        if (xzstate != null && xzstate.getSurplus() > 0) {
                            int surplus = xzstate.getSurplus();
                            if (surplus >= 2) {
                                xzstate.setSurplus(xzstate.getSurplus() - 2);
                            } else {
                                xzstate.setSurplus(0);
                            }
                        }
                        xzstate = manData2.xzstate("昏睡");
                        if (xzstate != null && xzstate.getSurplus() > 0) {
                            int surplus = xzstate.getSurplus();
                            if (surplus >= 2) {
                                xzstate.setSurplus(xzstate.getSurplus() - 2);
                            } else {
                                xzstate.setSurplus(0);
                            }
                        }
                        xzstate = manData2.xzstate("遗忘");
                        if (xzstate != null && xzstate.getSurplus() > 0) {
                            int surplus = xzstate.getSurplus();
                            if (surplus >= 2) {
                                xzstate.setSurplus(xzstate.getSurplus() - 2);
                            } else {
                                xzstate.setSurplus(0);
                            }
                        }
                    } else if (isV(skill_4011.getValue1())) {
                        AddState xzstate = manData2.xzstate("混乱");
                        if (xzstate != null && xzstate.getSurplus() > 0) {
                            int surplus = xzstate.getSurplus();
                            if (surplus >= 2) {
                                xzstate.setSurplus(xzstate.getSurplus() - 2);
                            } else {
                                xzstate.setSurplus(0);
                            }
                        }
                        xzstate = manData2.xzstate("封印");
                        if (xzstate != null && xzstate.getSurplus() > 0) {
                            int surplus = xzstate.getSurplus();
                            if (surplus >= 1) {
                                xzstate.setSurplus(xzstate.getSurplus() - 1);
                            } else {
                                xzstate.setSurplus(0);
                            }
                        }
                        xzstate = manData2.xzstate("昏睡");
                        if (xzstate != null && xzstate.getSurplus() > 0) {
                            int surplus = xzstate.getSurplus();
                            if (surplus >= 1) {
                                xzstate.setSurplus(xzstate.getSurplus() - 1);
                            } else {
                                xzstate.setSurplus(0);
                            }
                        }
                        xzstate = manData2.xzstate("遗忘");
                        if (xzstate != null && xzstate.getSurplus() > 0) {
                            int surplus = xzstate.getSurplus();
                            if (surplus >= 1) {
                                xzstate.setSurplus(xzstate.getSurplus() - 1);
                            } else {
                                xzstate.setSurplus(0);
                            }
                        }
                    }
                }
                this.struggle(manData2, removeStates);
                FightingState fightingState3 = manData2.endState(this);
                if (fightingState3 != null) {
                    removeStates.add(fightingState3);
                }
                if (manData2.getType() == 0) {
                    manData2.endFaMen(this);
                } else if (manData2.getType() == 3) {
                    FightingState state2 = new FightingState();
                    state2.setCamp(manData2.getCamp());
                    state2.setMan(manData2.getMan());
                    state2.setEndState_2("清除灵宝发动");
                    removeStates.add(state2);
                }
            }
        }
        for (int j = 0; j < this.fightingdata.size(); j++) { // 添加结束状态
            ManData manData = this.fightingdata.get(j);
            if (manData.getAis() != null) {
                for (AI ai : manData.getAis()) {
                    if (ai.getType() == AI.AI_TYPE_SWBS) {
                        int ndXS1 = battleData.getBattlefield().ndXS;
                        FightingEvents fightingEvents = new FightingEvents();
                        Double XS;
                        if (ndXS1 == 0) {
                            XS = 1.0;
                        } else if (ndXS1 == 1) {
                            XS = 1.8;
                        } else if (ndXS1 == 2) {
                            XS = 2.8;
                        } else if (ndXS1 == 3) {
                            XS = 4.0;
                        } else {
                            XS = 5.4;
                        }

                        ManData data2 = getMonster(ai.getAiConditions().get(0).getY());
                        if (manData.getStates() == 1 && manData == data2) {

                            fightingEvents = new FightingEvents();
                            FightingState fightingState = new FightingState();
                            fightingState.setStartState("召回");
                            fightingState.setCamp(data2.getCamp());
                            fightingState.setMan(data2.getMan());
                            data2.setStates(2);
                            fightingEvents.setOriginator(fightingState);
                            NewEvents.add(fightingEvents);
                            MixDeal.zhaohui(data2, fightingState, this);
                            this.fightingdata.remove(data2);
                            String monsterId = ai.getValue() + "";

                            ManData test = BattleThreadPool.loadCreep(monsterId, "测试", this.battleData.getMaxLvl(), this.battleData, XS, data2.getMan());
                            this.zhaohuan(test, "召唤", this, "40001");
                            break;
                        }
                    } else if (ai.getType() == AI.AI_TYPE_BS && isAIFull(ai.getAiConditions(), this, manData, ai)) {
                        Boolean isSw = true;
//                        for (AICondition aiCondition : ai.getAiConditions()) {
//                            if (aiCondition.getX() == 1010) {
//                                isSw = true;
//                            }
//                        }
                        if (isSw) {
                            int ndXS1 = battleData.getBattlefield().ndXS;
                            FightingEvents fightingEvents = new FightingEvents();
                            Double XS;
                            if (ndXS1 == 0) {
                                XS = 1.0;
                            } else if (ndXS1 == 1) {
                                XS = 1.8;
                            } else if (ndXS1 == 2) {
                                XS = 2.8;
                            } else if (ndXS1 == 3) {
                                XS = 4.0;
                            } else {
                                XS = 5.4;
                            }
                            fightingEvents = new FightingEvents();
                            FightingState fightingState = new FightingState();
                            fightingState.setStartState("召回");
                            fightingState.setCamp(manData.getCamp());
                            fightingState.setMan(manData.getMan());
                            manData.setStates(2);
                            fightingEvents.setOriginator(fightingState);
                            NewEvents.add(fightingEvents);
                            MixDeal.zhaohui(manData, fightingState, this);
                            fightingdata.remove(manData);
                            String monsterId = ai.getValue() + "";

                            ManData test = BattleThreadPool.loadCreep(monsterId, "测试", battleData.getMaxLvl(), battleData, XS, manData.getMan());
                            zhaohuan(test, "召唤", this, "40001");
                        }
                    }
                }
            }
            if (manData.getStates() == 2) {
                continue;
            }
            TalentTool.triggerTalentSkill(manData, removeStates, this);
        }
        if (removeStates.size() != 0) {
            FightingEvents ksevents = new FightingEvents();
            ksevents.setCurrentRound(this.CurrentRound);
            ksevents.setAccepterlist(removeStates);
            this.NewEvents.add(ksevents);
        }
        List<FightingState> SXStates = null;
        for (int j = 0; j < this.fightingdata.size(); j++) {
            ManData manData = this.fightingdata.get(j);
            manData.removeBear();
            if (manData.getStates() == 2) continue;
            if (manData.getType() >= 2) continue;
            String v = manData.xz2();
            if (v != null) {
                if (SXStates == null) SXStates = new ArrayList<>();
                FightingState fightingState = new FightingState();
                fightingState.setCamp(manData.getCamp());
                fightingState.setMan(manData.getMan());
                fightingState.setStartState("限制刷新");
                fightingState.setEndState(v);
                SXStates.add(fightingState);
            }
        }
        if (SXStates != null) {
            FightingEvents ksevents = new FightingEvents();
            ksevents.setCurrentRound(this.CurrentRound);
            ksevents.setAccepterlist(SXStates);
            this.NewEvents.add(ksevents);
        }

        for (int j = 0; j < this.fightingdata.size(); j++) { // 添加结束状态
            ManData manData = this.fightingdata.get(j);
            if (manData.isHS() && manData.getType() == 2 && manData.getStates() >= 1) {
                FightingEvents fightingEvents = new FightingEvents();
                FightingState fightingState = new FightingState();
                fightingState.setStartState("召回");
                fightingState.setCamp(manData.getCamp());
                fightingState.setMan(manData.getMan());
                fightingEvents.setOriginator(fightingState);
                NewEvents.add(fightingEvents);
//                MixDeal.zhaohui(manData, fightingState, this);
                fightingdata.remove(manData);
            }
        }
    }

    private List<ManData> L_GL_New() {
        List<ManData> resultList = new ArrayList<>();
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData data = (ManData) this.fightingdata.get(i);
            if (data.xzstate("甘霖") != null || data.xzstate("扶摇") != null) {
                resultList.add(data);
            }
        }
        return resultList.isEmpty() ? null : resultList;
    }

    private void L_GLFJ() {
        List<FightingState> Accepterlist = null;
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData data = (ManData) this.fightingdata.get(i);
            if ((data.getType() != 1 || data.getStates() == 0) && data.getHp() != data.getHp_z() && data.getStates() != 2) {
                AddState addState = data.xzstate("甘霖附加");
                if (addState != null) {
                    ChangeFighting change = new ChangeFighting();
                    FightingState state = new FightingState();
                    change.setChangehp((int) addState.getStateEffect());
                    data.ChangeData(change, state);
                    state.setStartState(TypeUtil.JN);
                    if (Accepterlist == null) {
                        Accepterlist = new ArrayList<>();
                    }
                    Accepterlist.add(state);
                }
            }
        }
        if (Accepterlist == null) {
            return;
        }
        FightingEvents events = new FightingEvents();
        events.setAccepterlist(Accepterlist);
        this.NewEvents.add(events);
    }

    /**
     * 返回有没有召唤兽
     */
    public boolean hasPet(ManData data) {
        if (data.getType() != 0) {
            return false;
        }// 如果角色类型不是0（玩家），则不可能是召唤兽的主人
        int man = data.getMan();
        man += 5;
        for (ManData parent : this.fightingdata) {
            if (parent.getType() == 1 && parent.getMan() == man && parent.getCamp() == data.getCamp()) {
                // 如果有同阵营的对应类型召唤兽存在，返回该召唤兽的数据
                return true;
            }
        }
        return false;// 没有找到同阵营的对应类型的召唤兽
    }

    /**
     * 返回召唤兽主任是否活着
     */
    public boolean hasMaster(ManData data) {
        if (data.getType() != 1) {
            return false;
        }
        int man = data.getMan();
        man -= 5;
        for (ManData parent : this.fightingdata) {
            if (parent.getType() == 0 && parent.getMan() == man && parent.getCamp() == data.getCamp()) {
                return true;
            }
        }
        return false;
    }

    // 整个战场中寻找没有召唤兽的人物
    private ManData extracted() {
        int dataSize = this.fightingdata.size();
        for (int k = dataSize - 1; k >= 0; --k) {
            //整个战场中寻找没有召唤兽的人物
            ManData data = (ManData) this.fightingdata.get(k);
            //己方队伍
            if (data.getCamp() == 1 && !this.hasPet(data) && data.getType() != 1) {
                return data;
            }
        }
        return null;
    }

    private void dfxy(ManData data, String... skillTypes) {
        List<FightingSkill> skills = data.getAppendSkillByType("遗忘", skillTypes);
        for (int i = 0; i < skills.size(); ++i) {
            FightingSkill skill = (FightingSkill) skills.get(i);
            if (getSkillIsV(skill, "忘")) {
                data.nqz -= 2;
            }
        }
    }

    private void dfxy() {
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData data = (ManData) this.fightingdata.get(i);
            if (data.getType() <= 0 && data.getStates() == 0) {
                this.dfxy(data, new String[]{TypeUtil.TY_G_10084, TypeUtil.TY_G_10094, TypeUtil.TY_G_10099});
            }
        }
    }

    private void Lz(ManData myData) {
        //判断自身异常状态
        String lastChar = "";
        String[] lz;
        for (String fhld : lz = new String[]{TypeUtil.TY_X_9946, TypeUtil.TY_X_9955, TypeUtil.TY_X_9964, TypeUtil.TY_X_10013}) {
            FightingSkill TY_RH_NDQB_SS = myData.getSkillType(fhld);
            if (TY_RH_NDQB_SS != null) {
                double isv;
                if (myData.xzstate("封印") != null || myData.xzstate("混乱") != null || myData.xzstate("遗忘") != null || myData.xzstate("昏睡") != null) {
                    isv = TY_RH_NDQB_SS.getSkillhurt() / 2.0;
                } else {
                    isv = TY_RH_NDQB_SS.getSkillhurt();
                }
                if (isV(isv)) {
                    lastChar = (lastChar = TY_RH_NDQB_SS.getSkillname().substring(TY_RH_NDQB_SS.getSkillname().length() - 1));
                    int n = -1;
                    switch (lastChar.hashCode()) {
                        case 38647: {
                            if (lastChar.equals("雷")) {
                                n = 0;
                                break;
                            } else {
                                break;
                            }
                        }
                        case 39118: {
                            if (lastChar.equals("风")) {
                                n = 1;
                                break;
                            } else {
                                break;
                            }
                        }
                        case 27700: {
                            if (lastChar.equals("水")) {
                                n = 2;
                                break;
                            } else {
                                break;
                            }
                        }
                        case 28779: {
                            if (lastChar.equals("火")) {
                                n = 3;
                                break;
                            } else {
                                break;
                            }
                        }
                    }
                    switch (n) {
                        case 0: {
                            if (myData.tl < 10.0) {
                                myData.getQuality().addtl(-myData.tl * 2.0);
                                ++myData.tl;
                                myData.getQuality().addtl(myData.tl * 2.0);
                                break;
                            } else {
                                break;
                            }
                        }
                        case 1: {
                            if (myData.tf < 10.0) {
                                myData.getQuality().addtf(-myData.tf * 2.0);
                                ++myData.tf;
                                myData.getQuality().addtf(myData.tf * 2.0);
                                break;
                            } else {
                                break;
                            }
                        }
                        case 2: {
                            if (myData.ts < 10.0) {
                                myData.getQuality().addts(-myData.ts * 2.0);
                                ++myData.ts;
                                myData.getQuality().addts(myData.ts * 2.0);
                                break;
                            } else {
                                break;
                            }
                        }
                        case 3: {
                            if (myData.tl < 10.0) {
                                myData.getQuality().addth(-myData.tl * 2.0);
                                ++myData.th;
                                myData.getQuality().addth(myData.th * 2.0);
                                break;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    // 增加整队的抗混乱值
    private void increaseTeamRolekhl(double increaseAmount, String type) {
        // 实现增加整队抗混乱值
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData data = (ManData) this.fightingdata.get(i);
            if (data.getStates() == 0 && data.getType() != 3 && data.getType() != 4) {
                double currentRolekhl = data.getQuality().getRolekhl();
                double currentRolekfy = data.getQuality().getRolekfy();
                double currentRolekhs = data.getQuality().getRolekhs();
                if (type.equals("混")) {
                    data.getQuality().setRolekhl(currentRolekhl + increaseAmount);
                } else if (type.equals("冰")) {
                    data.getQuality().setRolekfy(currentRolekfy + increaseAmount);
                } else if (type.equals("睡")) {
                    data.getQuality().setRolekhs(currentRolekhs + increaseAmount);
                }
            }
        }
    }

    /**
     * 甘霖处理
     */
    private void L_GL() {
        List<ManData> manDataList = new ArrayList<>();
        List<ManData> manDataList1 = new ArrayList<>();
        List<FightingState> Accepterlist = null;
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData data = (ManData) this.fightingdata.get(i);
            if ((data.getType() != 1 || data.getStates() == 0) && data.getHp() != data.getHp_z() && data.getStates() != 2) {
                AddState addState = data.xzstate("甘霖");
                if (addState != null) {
                    int mysum = this.Surplus(data.getCamp());
                    if (mysum != 0) {
                        ChangeFighting change = new ChangeFighting();
                        FightingState state = new FightingState();
                        int states = data.getStates();
                        if (states == 0) {
                            FightingSkill skill = addState.getSkill(9602);
                            if (skill != null && isV(10.0)) {
                                change.setChangemp((int) skill.getSkillhurt());
                            }
                        } else {
                            FightingSkill skill = addState.getSkill(9604);
                            if (skill != null && isV(20.0)) {
                                change.setChangetype(TypeUtil.TY_L_GL_YMFZ);
                                change.setChangevlaue((double) (int) (addState.getStateEffect() * skill.getSkillhurt() / 100.0));
                                change.setChangesum(3);
                            }
                        }
                        //甘霖回复HP值..
                        FightingSkill TY_L_10113 = data.getSkillType(TypeUtil.TY_L_10113);
                        if (TY_L_10113 != null) {
                            double qgl = (double) data.getHp() * TY_L_10113.getSkillhurt() / 100.0;
                            data.getQuality().setQlglv(data.getQuality().getQlglv() + qgl);
                            data.getSkills().remove(TY_L_10113);
                        }
                        boolean ismp = false;
                        TY_L_10113 = data.getSkillType(TypeUtil.TY_L_10142);
                        if (TY_L_10113 != null && isV(TY_L_10113.getSkillhurt())) {
                            ismp = true;
                        }
                        TY_L_10113 = data.getSkillType(TypeUtil.TY_L_10152);
                        if (TY_L_10113 != null && isV(TY_L_10113.getSkillhurt())) {
                            ismp = true;
                        }
                        TY_L_10113 = data.getSkillType(TypeUtil.TY_L_10162);
                        if (TY_L_10113 != null && isV(TY_L_10113.getSkillhurt())) {
                            ismp = true;
                        }
                        if (ismp) {
                            change.setChangemp((int) (addState.getStateEffect() * 0.05));
                        }
                        change.setChangehp((int) addState.getStateEffect());
                        data.ChangeData(change, state);
                        state.setStartState(TypeUtil.JN);
                        if (states == 1) {
                            state.setEndState_2("甘霖");
                        }
                        if (states == 1) {
                            FightingSkill skill2 = addState.getSkill(9608);
                            if (skill2 == null || !isV(skill2.getSkillhurt())) {
                                data.RemoveAbnormal(state, new String[]{"甘霖"});
                            }
                            skill2 = addState.getSkill(9605);
                            if (skill2 != null && isV(skill2.getSkillhurt())) {
                                data.addyq(10, state);
                            }
                            skill2 = addState.getSkill(9607);
                            if (skill2 != null && isV(skill2.getSkillhurt())) {
                                data.RemoveNegativeState(state);
                            }
                            FightingSkill TY_L_10114 = data.getSkillType(TypeUtil.TY_L_10146);
                            if (TY_L_10114 == null) {
                                TY_L_10114 = data.getSkillType(TypeUtil.TY_L_10156);
                            }
                            if (TY_L_10114 == null) {
                                TY_L_10114 = data.getSkillType(TypeUtil.TY_L_10166);
                            }
                            if (TY_L_10114 != null) {
                                int maxHp = data.getHp_z();
                                maxHp = (int) ((double) maxHp - data.yyhomax);
                                int maxAddHp = (int) ((double) maxHp * TY_L_10114.getSkillhurt() / 100.0);
                                if (data.yyhomax < (double) maxAddHp) {
                                    data.yyhomax += (double) maxHp * TY_L_10114.getSkillhurt() / 100.0;
                                    if (data.yyhomax > (double) maxAddHp) {
                                        data.yyhomax = (double) maxAddHp;
                                    }
                                    data.setHp_z(maxHp + (int) data.yyhomax);
                                }
                            }
                            data.getAddStates().remove(addState);
                        }
                        if (Accepterlist == null) {
                            Accepterlist = new ArrayList<>();
                        }
                        Accepterlist.add(state);
                        FightingSkill skill2 = addState.getSkill(9606);
                        if (skill2 != null && data.getvalue(0) <= 0.3 && isV(skill2.getSkillhurt())) {
                            FightingState state2 = new FightingState();
                            data.ChangeData(change, state2);
                            state2.setStartState(TypeUtil.JN);
                            Accepterlist.add(state2);
                        }
                    }
                }
            }
        }

        List<ManData> synchronizedList = Collections.synchronizedList(manDataList1);
        if (synchronizedList.size() != 0 && manDataList.size() != 0) for (ManData manData : manDataList) {
            double p = 0.0D;
            double d = 0.0D;
            double c = 0.0D;
            double p1 = 0.0D;
            double p2 = 0.0D;
            for (ManData datas1 : this.fightingdata) {
                if (datas1.getCamp() == manData.getCamp()) p++;
                if (datas1.getStates() != 0) d++;
                if (datas1.xzstate("混乱") != null || datas1.xzstate("封印") != null || datas1.xzstate("昏睡") != null)
                    c++;
            }
            p2 = (d / p > 0.6D) ? manData.getSkillId(30030).getP1() : 0.0D;
            if (d / p < 0.6D && c / p < 0.6D) p1 = manData.getSkillId(30030).getS1();
            if (manData.getSkillId(30030) != null && isV(manData.getSkillId(30030).getSkillgain() + p1 + p2)) {
//                if (manData.getSkillId(30030) != null) {
                int sum = 1 + (isV(manData.getSkillId(30030).getE1()) ? 1 : 0);
                ManData manData1 = null;
                for (int j = 0; j < sum; j++) {
                    if (j == 0) {
                        if (manDataList1.isEmpty()) continue;
                    } else {
                        List<ManData> manDataList2 = new ArrayList<>();
                        for (ManData manData2 : this.fightingdata) {
                            if (manData2.xzstate("隐身") == null && (manData2.xzstate("封印") != null || manData2.xzstate("混乱") != null || manData2.xzstate("昏睡") != null) && (manData2.getType() == 0 || manData2.getType() == 1) && manData2.getCamp() == manData.getCamp())
                                manDataList2.add(manData2);
                        }
                        manData1 = manDataList2.isEmpty() ? null : manDataList2.get(random.nextInt(manDataList2.size()));
                    }
                    ManData data = synchronizedList.isEmpty() ? null : synchronizedList.get(random.nextInt(synchronizedList.size()));
                    if (manData1 != null) {
                        data = manData1;
                    } else if (j != 0) {
                        continue;
                    }
                    FightingState fightingState = new FightingState();
                    fightingState.setMan(data.getMan());
                    fightingState.setCamp(data.getCamp());
                    fightingState.setSkillskin("1608");
                    fightingState.setText("#G定心钵#2");
                    data.RemoveAbnormal(fightingState, "混乱", "封印", "昏睡");
                    fightingState.setEndState_2("清除冰混睡异常状态");
                    if (Accepterlist == null) Accepterlist = new ArrayList<>();
                    Accepterlist.add(fightingState);
                    if (data.xzstate("混乱") == null && data.xzstate("封印") == null && data.xzstate("昏睡") == null)
                        manDataList1.remove(data);
                    continue;
                }
            }
        }

        for (ManData data : this.fightingdata) {
            if (data.xzstate("暮眠钟") != null && random.nextInt(108) < data.xzstate("暮眠钟").getStateEffect()) {
                FightingState fightingState = new FightingState();
                fightingState.setMan(data.getMan());
                fightingState.setCamp(data.getCamp());
                if (isV(data.xzstate("暮眠钟").getStateEffect2())) data.addAddState("重度昏睡", 0.0D, 0.0D, 2);
                data.addAddState("昏睡", 0.0D, 0.0D, 2);
                fightingState.setEndState_1("昏睡");
                if (Accepterlist == null) Accepterlist = new ArrayList<>();
                Accepterlist.add(fightingState);
            }
        }
        for (ManData data : this.fightingdata) {
            if (data.xzstate("引蕴瓶") != null && isV(data.xzstate("引蕴瓶").getStateEffect() + ((data.getStates() == 0) ? data.xzstate("引蕴瓶").getStateEffect2() : 0.0D))) {
                FightingState fightingState = new FightingState();
                fightingState.setMan(data.getMan());
                fightingState.setCamp(data.getCamp());
                int k = 1;
                if (isV(data.xzstate("引蕴瓶").getStateEffect3())) k++;
                data.addXYZ(k);
                fightingState.setXy_c(k);
                if (Accepterlist == null) Accepterlist = new ArrayList<>();
                Accepterlist.add(fightingState);
            }
            if (data.xzstate("凝霜印") != null && data.xzstate("封印") != null && isV(data.xzstate("凝霜印").getStateEffect6()))
                data.xzstate("凝霜印").setStateEffect5(data.xzstate("凝霜印").getStateEffect5() + 1.0D);
        }
        for (ManData manData : this.fightingdata) {
            if (manData.getSkillId(30001) != null) {
                if (manData.getStates() != 0) {
                    if (manData.ddhe <= 0) {
                        if (Accepterlist == null) Accepterlist = new ArrayList<>();
                        FightingState fightingState = new FightingState();
                        fightingState.setStartState(TypeUtil.JN);
                        fightingState.setSkillskin("1509");
                        fightingState.setCamp(manData.getCamp());
                        fightingState.setMan(manData.getMan());
                        fightingState.setText("复活");
                        ChangeFighting changeFighting = new ChangeFighting();
                        changeFighting.setChangehp((int) (manData.getHp_z() * (0.5D + manData.getSkillId(30001).getS1() / 100.0D)));
                        changeFighting.setChangemp((int) (manData.getMp_z() * manData.getSkillId(30001).getS1() / 100.0D));
                        fightingState.setEndState_1("长生幡");
                        manData.addAddState("长生幡", manData.getSkillId(30001).getP1(), manData.getSkillId(30001).getE1(), 2);
                        FightingPackage.ChangeProcess(changeFighting, null, manData, fightingState, "复活", Accepterlist, this);
                        manData.ddhe = (int) manData.getSkillId(30001).getSkillgain() + manData.getSkillId(30001).getSkillcontinued();
                        manData.ddhe_ = manData.ddhe;
                        continue;
                    }
//                    NChatBean nChatBean = new NChatBean();
//                    nChatBean.setMessage("长生幡复活回合数:" + manData.ddhe);
//                    nChatBean.setId(6);
                    for (ManData data : this.fightingdata) {
                        if (data.getType() == 0)
                            SendMessage.sendMessageByRoleName(data.getManname(), Agreement.getAgreement().PromptAgreement("#Y" + manData.getManname() + "#G长生幡复活回合数:#R" + manData.ddhe));
                    }
                    manData.ddhe--;
                    continue;
                }
                manData.ddhe = manData.ddhe_;
            }
        }

        for (ManData data : this.fightingdata) {
            if (data.xzstate("沧溟露") != null) {
                AddState addState2 = data.xzstate("沧溟露");
                FightingState fightingState1 = new FightingState();
                fightingState1.setMan(data.getMan());
                fightingState1.setCamp(data.getCamp());
                if (isV(addState2.getStateEffect3())) {
                    data.RemoveAbnormal(fightingState1, ManData.values);
                    fightingState1.setEndState_2("清除异常状态师门");
                }
                data.addyq((int) addState2.getStateEffect4(), fightingState1);
                data.addnq((int) addState2.getStateEffect5(), fightingState1);
                if (Accepterlist == null) Accepterlist = new ArrayList<>();
                Accepterlist.add(fightingState1);
            }
        }
        if (Accepterlist == null) {
            return;
        }
        FightingEvents events = new FightingEvents();
        events.setAccepterlist(Accepterlist);
        this.NewEvents.add(events);
    }

    private void XP_YYBJ() {
        List<FightingState> Accepterlist = null;
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData data = (ManData) this.fightingdata.get(i);
            if ((data.getType() != 1 || data.getStates() == 0) && data.getMp() != data.getMp_z() && data.getStates() != 2) {
                FightingSkill skill = data.getSkillId(23001);
                if (skill != null) {
                    ChangeFighting change = new ChangeFighting();
                    FightingState state = new FightingState();
                    int states = data.getStates();
                    if (states == 0) {
                        if (isV(30.0)) {
                            change.setChangemp(10000);
                            state.setText("源源不绝#2");
                        }
                        data.ChangeData(change, state);
                        state.setStartState(TypeUtil.JN);
                        if (Accepterlist == null) {
                            Accepterlist = new ArrayList<>();
                        }
                        Accepterlist.add(state);
                    }
                }
            }
        }
        if (Accepterlist == null) {
            return;
        }
        FightingEvents events = new FightingEvents();
        events.setAccepterlist(Accepterlist);
        this.NewEvents.add(events);
    }

    private void XP_WYQS() {
        List<FightingState> Accepterlist = null;
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData data = (ManData) this.fightingdata.get(i);
            if ((data.getType() != 1 || data.getStates() == 0) && data.getMp() != data.getMp_z() && data.getStates() != 2) {
                FightingSkill skill = data.getSkillId(23006);
                if (skill != null) {
                    ChangeFighting change = new ChangeFighting();
                    FightingState state = new FightingState();
                    int states = data.getStates();
                    if (states == 0) {
                        if (isV(30.0)) {
                            change.setChangetype("隐身");
                            change.setChangesum(2);
                            state.setText("雾掩青山#2");
                        }
                        data.ChangeData(change, state);
                        state.setStartState(TypeUtil.JN);
                        if (Accepterlist == null) {
                            Accepterlist = new ArrayList<>();
                        }
                        Accepterlist.add(state);
                    }
                }
            }
        }
        if (Accepterlist == null) {
            return;
        }
        FightingEvents events = new FightingEvents();
        events.setAccepterlist(Accepterlist);
        this.NewEvents.add(events);
    }

    /**
     * buff刷新
     */
    public void SXBuff() {
        List<GroupBuff> removeBuffs = null;
        //判定双管齐下是否取消
        GroupBuff buff = this.getBuff(1, TypeUtil.BB_SGQX);
        if (buff != null && buff.getData().getStates() != 0) {
            this.buffs.remove(buff);
            GroupBuff buff2 = this.addbuff(1, TypeUtil.BB_SGQX);
            if (buff2 == null) {
                if (removeBuffs == null) {
                    removeBuffs = new ArrayList<>();
                }
                removeBuffs.add(buff);
            }
        }
        //判断黑夜蒙蔽
        for (int i = this.buffs.size() - 1; i >= 0; --i) {
            buff = this.buffs.get(i);
            if (buff.getBuffType().equals(TypeUtil.BB_E_HYMB) || buff.getBuffType().equals(TypeUtil.BB_FBSF) || buff.getBuffType().equals(TypeUtil.LB_QKZT) || buff.getBuffType().equals("23009")) {
                buff.setValue2(buff.getValue2() - 1.0);
                if (buff.getValue2() <= 0.0) {
                    this.buffs.remove(buff);
                    if (removeBuffs == null) {
                        removeBuffs = new ArrayList<>();
                    }
                    removeBuffs.add(buff);
                }
            }
        }
        if (removeBuffs != null) {//找一个还活着的单位 当替身
            int i = this.fightingdata.size() - 1;
            while (i >= 0) {
                ManData data = (ManData) this.fightingdata.get(i);
                if (data.getStates() == 0) {
                    FightingEvents hhe = new FightingEvents();
                    List<FightingState> hhs = new ArrayList<>();
                    FightingState fs = new FightingState();
                    fs.setCamp(data.getCamp());
                    fs.setMan(data.getMan());
                    fs.setStartState("代价");
                    fs.setBuff(MixDeal.getBuffStr(removeBuffs, false));
                    hhs.add(fs);
                    hhe.setAccepterlist(hhs);
                    this.NewEvents.add(hhe);
                    break;
                } else {
                    --i;
                }
            }
        }
    }

    /**
     * 指令处理
     */
    public void ZLCL() {
        int size = 0;
        while (this.waitList.size() != 0 || this.erwai.size() != 0 || this.huayu.size() != 0) {
            if (++size > 999999) {
                WriteOut.addtxt("战斗循环运算过多", 9999L);
                WriteOut.addtxt("1:" + GsonUtil.getGsonUtil().getgson().toJson(this.Events), 9999L);
                WriteOut.addtxt("2:" + GsonUtil.getGsonUtil().getgson().toJson(this.erwai), 9999L);
                WriteOut.addtxt("3:" + GsonUtil.getGsonUtil().getgson().toJson(this.huayu), 9999L);
                break;
            }
            // 先判断战斗是否结束
            else if (this.endFighting(((ManData) this.fightingdata.get(0)).getCamp()) != -1) {
                break;
            } else if (this.huayu.size() != 0) {
                // 化羽归尘处理
                DataActionType.getActionById(19).analysisAction(null, null, null, this);
                this.huayu.clear();
                MixDeal.flee(this, 0);// 作鸟兽散判断
            } else {
                FightingEvents zl = null;
                if (this.erwai.size() != 0) {
                    zl = (FightingEvents) this.erwai.remove(0);
                    if (zl != null && zl.getOriginator() != null) {
                        String endState = zl.getOriginator().getEndState();
                        if ("兵临城下".equals(endState) || "烈火骄阳".equals(endState)) {
                            zl.setEw(true);
                        }
                    }
                } else {
                    zl = this.SpBubble();
                }
                if (zl == null) {
                    break;
                } else {
                    Battlefield.currZl = zl;
                    // 出手人发起的类型
                    String type = zl.getOriginator().getStartState();
                    if (type.equals("防御") || type.equals("保护")) {
                        int wei = -1;
                        for (int i = 0; i < this.fightingdata.size(); ++i) {
                            if (this.fightingdata.get(i).getCamp() == zl.getOriginator().getCamp() && this.fightingdata.get(i).getMan() == zl.getOriginator().getMan()) {
                                wei = i;
                            }
                        }
                        if (wei != -1) {
                            ManData myData = this.fightingdata.get(wei);
                            AddState zt = myData.xzstate("混乱");
                            if (zt != null) {
                                DataActionType.getActionById(8).analysisAction(myData, zl, zl.getOriginator().getStartState(), this);
                            }
                            AddState zt2 = myData.xzstate("封印");
                            if ((zt2 != null || TalentTool.getStateByLvl(myData, 3) != null) && type.equals("保护")) {
                                zl.getOriginator().setStartState("防御");
                            } else {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else if (type.equals("复活")) {
                        DataActionType.getActionById(17).analysisAction(null, zl, zl.getOriginator().getStartState(), this);
                    } else if (type.equals(TypeUtil.BB_GQLX)) {//TODO 灵听归去来兮处理
                        int wei = -1;
                        for (int i = 0; i < this.fightingdata.size(); ++i) {
                            if (this.fightingdata.get(i).getCamp() == zl.getOriginator().getCamp() && ((ManData) this.fightingdata.get(i)).getMan() == zl.getOriginator().getMan()) {
                                wei = i;
                            }
                        }
                        if (wei != -1) {
                            ManData myData = this.fightingdata.get(wei);
                            for (int j = myData.getSkills().size() - 1; j >= 0; --j) {
                                FightingSkill skill2 = myData.getSkills().get(j);
                                if (skill2.getSkilltype().equals(TypeUtil.BB_GQLX)) {
                                    DataActionType.getActionById(35).analysisAction(myData, zl, zl.getOriginator().getStartState(), this);
                                }
                            }
                        } else {
                            continue;
                        }
                    } else if (type.equals("振羽惊雷")) {
                        DataActionType.getActionById(39).analysisAction(MixDeal.ZYJL, zl, zl.getOriginator().getStartState(), this);
                        MixDeal.ZYJL = null;
                    } else if (type.equals("遗产")) {
                        DataActionType.getActionById(9).analysisAction(null, zl, zl.getOriginator().getStartState(), this);
                    } else if (type.equals(TypeUtil.BB_JS) || type.equals(TypeUtil.BB_DT)) {
                        DataActionType.getActionById(10).analysisAction(null, zl, zl.getOriginator().getStartState(), this);
                    } else if (type.equals(TypeUtil.BB_RHTY)) {
                        DataActionType.getActionById(29).analysisAction(null, zl, zl.getOriginator().getStartState(), this);
                    } else if (type.equals(TypeUtil.BB_YAHY)) {
                        DataActionType.getActionById(30).analysisAction(null, zl, zl.getOriginator().getStartState(), this);
                    } else if (type.equals(TypeUtil.BB_TDTS)) {
                        DataActionType.getActionById(31).analysisAction(null, zl, zl.getOriginator().getStartState(), this);
                    } else if (type.equals(TypeUtil.BB_NZQK)) {
                        DataActionType.getActionById(33).analysisAction(null, zl, zl.getOriginator().getStartState(), this);
                    } else if (type.equals("碧荷凝露")) {
                        int wei = this.Datapathsi(zl.getOriginator().getCamp(), zl.getOriginator().getMan());
                        if (wei != -1) {
                            ManData data = this.fightingdata.get(wei);
                            data.executeBhnl(this);
                        } else {
                            continue;
                        }
                    } else if (type.startsWith("闪现&")) {
                        int wei = this.Datapath(zl.getOriginator().getCamp(), zl.getOriginator().getMan());
                        if (wei != -1) {
                            ManData data = ((ManData) this.fightingdata.get(wei)).bb(type.split("\\&")[1]);
                            DataActionType.getActionById(5).analysisAction(data, zl, "闪现", this);
                            for (int x = data.getSkills().size() - 1; x >= 0; --x) {
                                FightingSkill skill3 = (FightingSkill) data.getSkills().get(x);
                                int id = skill3.getSkillid();
                                if (id >= 1041 && id <= 1065) {
                                    if (skill3.getSkillhurt() > 42000.0) {
                                        skill3.setSkillhurt(42000.0);
                                    }
                                } else if (id >= 1066 && id <= 1070) {
                                    if (skill3.getSkillhurt() > 20000.0) {
                                        skill3.setSkillhurt(20000.0);
                                    }
                                } else if ((id >= 1081 && id <= 1090) || (id >= 1096 && id <= 1100)) {
                                    if (skill3.getSkillhurt() > 65.0) {
                                        skill3.setSkillhurt(65.0);
                                    }
                                } else if (skill3.getSkilltype().equals(TypeUtil.YBYT) || skill3.getSkilltype().equals(TypeUtil.BB_XLHC)) {
                                    this.addBuff(new GroupBuff(this.getBuffid(), skill3.getSkilltype(), data, skill3.getSkillhurt()));
                                } else if (PK_MixDeal.isPK(this.BattleType)) {
                                    if (skill3.getSkilltype().equals(TypeUtil.BB_HW)) {
                                        this.addBuff(new GroupBuff(this.getBuffid(), skill3.getSkilltype(), data, skill3.getSkillhurt()));
                                        data.getSkills().remove(x);
                                    } else if (skill3.getSkilltype().equals(TypeUtil.BB_XR)) {
                                        this.addBuff(new GroupBuff(this.getBuffid(), skill3.getSkilltype(), data, skill3.getSkillhurt()));
                                        data.getSkills().remove(x);
                                    } else if (skill3.getSkilltype().equals(TypeUtil.BB_YH)) {
                                        this.addBuff(new GroupBuff(this.getBuffid(), skill3.getSkilltype(), data, skill3.getSkillhurt()));
                                        data.getSkills().remove(x);
                                    } else if (skill3.getSkilltype().equals(TypeUtil.BB_TM)) {
                                        this.addBuff(new GroupBuff(this.getBuffid(), skill3.getSkilltype(), data, skill3.getSkillhurt()));
                                    } else if (skill3.getSkilltype().equals("妙手空空") && isV(30.0)) {
                                        this.addBuff(new GroupBuff(this.getBuffid(), skill3.getSkilltype(), data, skill3.getSkillhurt()));
                                    } else if (skill3.getSkilltype().equals(TypeUtil.BB_MCQH)) {
                                        this.addBuff(new GroupBuff(this.getBuffid(), skill3.getSkilltype(), data, skill3.getSkillhurt()));
                                    } else if (skill3.getSkilltype().equals(TypeUtil.BB_SGQX)) {
                                        this.addBuff(new GroupBuff(this.getBuffid(), skill3.getSkilltype(), data, skill3.getSkillhurt()));
                                    } else if (skill3.getSkilltype().equals(TypeUtil.BB_QZ)) {
                                        this.addBuff(new GroupBuff(this.getBuffid(), skill3.getSkilltype(), data, skill3.getSkillhurt()));
                                    } else if (skill3.getSkilltype().equals(TypeUtil.TY_JS_JTSB)) {
                                        int sp = (int) skill3.getSkillhurt();
                                        if (this.chaosSp > sp) {
                                            this.chaosSp = sp;
                                        }
                                        data.getSkills().remove(x);
                                    } else if (skill3.getSkilltype().equals(TypeUtil.TY_JS_YZFS)) {
                                        data.setSp((int) ((double) data.getSp() + skill3.getSkillhurt()));
                                        data.getSkills().remove(x);
                                    } else if (id == 1867) {// 以牙还牙处理
                                        DataActionType.getActionById(30).analysisAction(data, null, skill3.getSkilltype(), this);
                                    }
                                } else if (id == 8054) {//8054	法术波动  穷奇的法术秒10设置
                                    data.getSkills().remove(x);
                                    for (int k = data.getSkills().size() - 1; k >= 0; --k) {
                                        FightingSkill skillTwo = (FightingSkill) data.getSkills().get(k);
                                        if (skillTwo.getSkillid() > 1000 && skillTwo.getSkillid() <= 1100) {
                                            skillTwo.setSkillsum(10);
                                        }
                                    }
                                } else if (skill3.getSkilltype().equals(TypeUtil.TY_SSC_MCQT)) {
                                    data.getSkills().remove(x);
                                }
                            }
                        } else {
                            continue;
                        }
                    } else if (type.startsWith("ls&")) {
                        int wei = this.Datapath(zl.getOriginator().getCamp(), zl.getOriginator().getMan());
                        if (wei != -1) {
                            ManData bbData = ((ManData) this.fightingdata.get(wei)).lingbao(type.split("\\&")[1]);
                            DataActionType.getActionById(42).analysisAction(bbData, zl, "闪现", this);
                        } else {
                            continue;
                        }
                    } else {
                        int path = -1;
                        if (zl.getOriginator().getMan() < 5) {
                            path = this.Datapath(zl.getOriginator().getCamp(), zl.getOriginator().getMan());
                        } else {
                            path = this.Datapathhuo(zl.getOriginator().getCamp(), zl.getOriginator().getMan());
                        }
                        if (path == -1) {
                            continue;
                        } else {
                            ManData myData2 = (ManData) this.fightingdata.get(path);
                            if (myData2.getType() != 4) {// 为孩子处理
                                if (myData2.getType() == 3) {// 灵宝处理
                                    DataActionType.getActionById(6).analysisAction(myData2, zl, zl.getOriginator().getStartState(), this);
                                } else if (type.equals("召回")) {
                                    int wei2 = this.Datapathhuo(myData2.getCamp(), myData2.getMan() + 5);
                                    int ren = this.Datapathhuo(myData2.getCamp(), myData2.getMan());
                                    if (wei2 != -1) {

                                        MixDeal.ys(myData2, false, this);
                                        myData2 = this.fightingdata.get(wei2);
                                        for (int l = myData2.getSkills().size() - 1; l >= 0; --l) {
                                            FightingSkill skill4 = myData2.getSkills().get(l);
                                            if (skill4.getSkilltype().equals(TypeUtil.BB_GQLX)) {//TODO 灵听归去来兮处理
                                                DataActionType.getActionById(35).analysisAction(myData2, zl, zl.getOriginator().getStartState(), this);
                                            }
                                        }
                                        if (ren != -1) {
                                            ManData Data = this.fightingdata.get(ren);
                                            //召回时判断召唤兽身上是否有加速状态，且主角的天演测技能快马扬鞭不是NULL
                                            MixDeal.Asw(Data, myData2, this);
                                        }
                                        DataActionType.getActionById(5).analysisAction(myData2, zl, type, this);
                                    }
                                } else if (myData2.getStates() == 0 && type.startsWith("召唤&")) {
                                    int wei2 = this.Datapathhuo(myData2.getCamp(), myData2.getMan() + 5);
                                    int ren = this.Datapathhuo(myData2.getCamp(), myData2.getMan());
                                    if (ren != -1) {
                                        ManData manData = this.fightingdata.get(ren);
                                    }
                                    if (wei2 != -1) {
                                        MixDeal.ys(myData2, false, this);
                                        myData2 = this.fightingdata.get(wei2);
                                        for (int l = myData2.getSkills().size() - 1; l >= 0; --l) {
                                            FightingSkill skill4 = myData2.getSkills().get(l);
                                            if (skill4.getSkilltype().equals(TypeUtil.BB_GQLX)) {//TODO 灵听归去来兮处理
                                                DataActionType.getActionById(35).analysisAction(myData2, zl, zl.getOriginator().getStartState(), this);
                                            }
                                        }
                                    }
                                    this.CSGZ(this.fightingdata.get(path), zl);
                                } else if (myData2.getStates() == 0 && type.startsWith("召唤灵宝&")) {
                                    this.CSGZ((ManData) this.fightingdata.get(path), zl);
                                } else if (myData2.getStates() == 0) {
                                    // 死亡处理
                                    if (type.equals("逃跑")) {
                                        AddState zt3 = myData2.xzstate("混乱");
                                        if (zt3 != null) {
                                            DataActionType.getActionById(8).analysisAction(myData2, zl, type, this);
                                        } else {
                                            MixDeal.ys(myData2, false, this);
                                            DataActionType.getActionById(3).analysisAction(myData2, zl, type, this);
                                            if (this.battleData.getBattleType() == 4) {
                                                int m = 0;
                                                while (m < this.fightingdata.size()) {
                                                    ManData data2 = (ManData) this.fightingdata.get(m);
                                                    if (data2.getType() == 0) {
                                                        data2.setMoney(-1L);
                                                        break;
                                                    } else {
                                                        ++m;
                                                    }
                                                }
                                            }
                                        }
                                    } else if (this.BattleType == 4 && type.equals("偷钱")) {
                                        DataActionType.getActionById(25).analysisAction(myData2, zl, type, this);
                                    } else {
                                        this.CSGZ(myData2, zl);
                                    }
                                }
                            }
                            AI_MixDeal.AI_End(this, myData2);
                            if (this.isWLKH) {
                                this.HurtWLKH();
                            }//雾里看花处理
                            if (this.isHSJY) {
                                this.HurtHSJY(myData2);
                            }//火上浇油处理
                            if (this.bbDeathPoint != null && (this.bbDeathPoint.getX() != 0 || this.bbDeathPoint.getY() != 0)) {
                                this.BBDeathPoint();//死亡宝宝加抗性技能处理
                            }
                            Boolean b = Boolean.FALSE;
                            if (type.equals("技能")) {
                                b = Boolean.TRUE;
                                if (zl != null && zl.getOriginator() != null) {
                                    String endState2 = zl.getOriginator().getEndState();
                                    if ("气吞山河".equals(endState2) || "清心静气".equals(endState2) || "凝神一击".equals(endState2) || "行气如虹".equals(endState2) || "气聚神凝".equals(endState2) || "神龙摆尾".equals(endState2) || "流风回雪".equals(endState2) || "天罡八卦".equals(endState2) || "逆鳞".equals(endState2)) {
                                        b = Boolean.FALSE;
                                    }
                                }
                            }
                            if (myData2.getMan() < 5 && ((boolean) b || type.equals(TypeUtil.PTGJ)) && myData2.xzstate("混乱") == null && myData2.xzstate("昏睡") == null) {
                                int wei3 = this.Datapathhuo(myData2.getCamp(), myData2.getMan() + 5);
                                if (wei3 == -1) {
                                    MixDeal.zhszy(this, myData2);//召唤兽支援判断
                                }
                                wei3 = this.Datapathhuo(myData2.getCamp(), myData2.getMan() + 10);
                                if (wei3 == -1) {
                                    MixDeal.lingBaozy(this, myData2);//召唤兽支援判断
                                }
                            }
                        }
                    }
                }
            }
        }
        MixDeal.flee(this, 0);//作鸟兽散判断
    }

    /**
     * 出手规则
     */
    public void CSGZ(ManData myData, FightingEvents zl) {
        //出手人发起的类型
        String type = zl.getOriginator().getStartState();
        String skillName = type.equals("技能") ? zl.getOriginator().getEndState() : "";
        //		type = "防御";

        //判断单位身上的状态
        int h = 0;
        int s = 0;
        int q = 0;
        int j = 0;
        int ss = 0;
        int y = 0;
        int ht = 0;
        int lhfm = 0;
        int bh = 0;
        if (myData.getAddStates() != null && (zl.getOriginator().getEndState() == null || !zl.getOriginator().getEndState().equals("流风回雪"))) {
            for (int i = myData.getAddStates().size() - 1; i >= 0; --i) {
                String Stype = ((AddState) myData.getAddStates().get(i)).getStatename();
                if (!Arrays.asList(BattleData.vs).contains(skillName)) {
                    if (h == 0 && Stype.equals("混乱")) {
                        h = 1;
                        type = TypeUtil.PTGJ;
                    } else if (s == 0 && Stype.equals("昏睡")) {
                        s = 1;
                    } else if (q == 0 && Stype.equals(TypeUtil.FB_QW)) {//情网
                        q = 1;
                    } else if (j == 0 && Stype.equals(TypeUtil.FB_JGE)) {//金箍儿
                        j = 1;
                    } else if (ss == 0 && Stype.equals(TypeUtil.BB_SS)) {//舍身取义
                        ss = 1;
                    } else if (y == 0 && Stype.equals("遗忘")) {
                        y = 1;
                    } else if (ht == 0 && Stype.equals(TypeUtil.TZ_HTSA)) {//回头是岸
                        ht = 1;
                    } else if (lhfm == 0 && Stype.equals(TypeUtil.BB_LHFM)) {//灵魂封魔
                        lhfm = 1;
                    } else {
                        if (Stype.equals(TypeUtil.TY_F_CFWL_S)) {//长风万里给提示结束
                            FightingState accepter = new FightingState(myData.getCamp(), myData.getMan(), TypeUtil.JN);
                            accepter.setText("我已经被风吹走了");
                            zl.setOriginator(null);
                            zl.setAccepterlist(null);
                            List<FightingState> Accepterlist = new ArrayList<>();
                            Accepterlist.add(accepter);
                            zl.setAccepterlist(Accepterlist);
                            this.NewEvents.add(zl);
                            return;
                        }
                        if (Stype.equals("乾坤破阵")) {//乾坤破阵给提示结束
                            FightingState accepter = new FightingState(myData.getCamp(), myData.getMan(), TypeUtil.JN);
                            accepter.setText("我人呢#24");
                            zl.setOriginator(null);
                            zl.setAccepterlist(null);
                            List<FightingState> Accepterlist = new ArrayList<>();
                            Accepterlist.add(accepter);
                            zl.setAccepterlist(Accepterlist);
                            this.NewEvents.add(zl);
                            return;
                        }
                        if (Stype.equals("封印")) {//封印直接结束
                            this.Events.add(zl);
                            return;
                        }
                        if (Stype.endsWith("天赋")) {
                            switch (TalentTool.getTalentLvl(Stype)) {
                                case 5: {
                                    y = 2;
                                }
                                case 4: {
                                    q = 2;
                                }
                                case 3: {
                                    bh = 1;
                                }
                                case 2: {
                                    j = 2;
                                }
                                case 1: {
                                    j = 3;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (ss == 1 && !type.equals(TypeUtil.PTGJ)) { // 舍身取义
            this.systemMsg(myData, null, 2, null);
            return;
        }
        if (type.equals(TypeUtil.PTGJ)) {
            if (myData.getType() == 1) {
                GroupBuff buff = this.getNoCampBuff(myData.getCamp(), TypeUtil.BB_XLHC);
                if (buff != null && this.recordList.contains(TypeUtil.PTGJ + "_" + myData.getCamp())) {
                    return;
                }
                this.recordList.add(TypeUtil.PTGJ + "_" + myData.getCamp());
            }
            if (q >= 1) {
                this.systemMsg(myData, null, (q == 1) ? 0 : 24, null);
                return;
            }//情网
            MixDeal.ys(myData, false, this);
            DataActionType.getActionById((h == 1) ? 8 : 1).analysisAction(myData, zl, type, this);
            return;
        } else if (type.equals("药")) {
            if (myData.getType() == 1) {
                GroupBuff buff = this.getNoCampBuff(myData.getCamp(), TypeUtil.BB_XLHC);
                if (buff != null && this.recordList.contains("药_" + myData.getCamp())) {
                    return;
                }
                this.recordList.add("药_" + myData.getCamp());
            }
            if (y >= 1) {
                this.systemMsg(myData, null, (y == 1) ? 0 : 25, null);
                return;
            }
            MixDeal.ys(myData, false, this);
            DataActionType.getActionById(4).analysisAction(myData, zl, type, this);
            return;
        } else if (type.startsWith("召唤&") || type.startsWith("召唤灵宝&")) {
            String[] vs = type.split("\\&");
            try {
                MixDeal.ys(myData, false, this);
                ManData data;
                if (vs[0].equals("召唤")) {
                    data = myData.bb(vs[1]);
                } else {
                    data = myData.lingbao(vs[1]);
                }
                DataActionType.getActionById(5).analysisAction(data, zl, vs[0], this);
                for (int x = data.getSkills().size() - 1; x >= 0; --x) {
                    FightingSkill skill = (FightingSkill) data.getSkills().get(x);
                    int id = skill.getSkillid();
                    if (id >= 1041 && id <= 1065) {
                        if (skill.getSkillhurt() > 42000.0) {
                            skill.setSkillhurt(42000.0);
                        }
                    } else if (id >= 1066 && id <= 1070) {
                        if (skill.getSkillhurt() > 20000.0) {
                            skill.setSkillhurt(20000.0);
                        }
                    } else if ((id >= 1081 && id <= 1090) || (id >= 1096 && id <= 1100)) {
                        if (skill.getSkillhurt() > 65.0) {
                            skill.setSkillhurt(65.0);
                        }
                    } else if (skill.getSkilltype().equals(TypeUtil.YBYT) || skill.getSkilltype().equals(TypeUtil.BB_XLHC)) {
                        this.addBuff(new GroupBuff(this.getBuffid(), skill.getSkilltype(), data, skill.getSkillhurt()));
                    } else if (PK_MixDeal.isPK(this.BattleType)) {
                        if (skill.getSkilltype().equals(TypeUtil.BB_HW)) {
                            this.addBuff(new GroupBuff(this.getBuffid(), skill.getSkilltype(), data, skill.getSkillhurt()));
                            data.getSkills().remove(x);
                        } else if (skill.getSkilltype().equals(TypeUtil.BB_XR)) {
                            this.addBuff(new GroupBuff(this.getBuffid(), skill.getSkilltype(), data, skill.getSkillhurt()));
                            data.getSkills().remove(x);
                        } else if (skill.getSkilltype().equals(TypeUtil.BB_YH)) {
                            this.addBuff(new GroupBuff(this.getBuffid(), skill.getSkilltype(), data, skill.getSkillhurt()));
                            data.getSkills().remove(x);
                        } else if (skill.getSkilltype().equals(TypeUtil.BB_TM)) {
                            this.addBuff(new GroupBuff(this.getBuffid(), skill.getSkilltype(), data, skill.getSkillhurt()));
                        } else if (skill.getSkilltype().equals("妙手空空") && isV(30.0)) {
                            this.addBuff(new GroupBuff(this.getBuffid(), skill.getSkilltype(), data, skill.getSkillhurt()));
                        } else if (skill.getSkilltype().equals(TypeUtil.BB_MCQH)) {
                            this.addBuff(new GroupBuff(this.getBuffid(), skill.getSkilltype(), data, skill.getSkillhurt()));
                        } else if (skill.getSkilltype().equals(TypeUtil.BB_SGQX)) {
                            this.addBuff(new GroupBuff(this.getBuffid(), skill.getSkilltype(), data, skill.getSkillhurt()));
                        } else if (skill.getSkilltype().equals(TypeUtil.BB_QZ)) {
                            this.addBuff(new GroupBuff(this.getBuffid(), skill.getSkilltype(), data, skill.getSkillhurt()));
                        } else if (skill.getSkilltype().equals(TypeUtil.TY_JS_JTSB)) {
                            int sp = (int) skill.getSkillhurt();
                            if (this.chaosSp > sp) {
                                this.chaosSp = sp;
                            }
                            data.getSkills().remove(x);
                        } else if (skill.getSkilltype().equals(TypeUtil.TY_JS_YZFS)) {
                            data.setSp((int) ((double) data.getSp() + skill.getSkillhurt()));
                            data.getSkills().remove(x);
                        } else if (id == 1867) {// 以牙还牙处理
                            DataActionType.getActionById(30).analysisAction(data, null, skill.getSkilltype(), this);
                        }
                    } else if (id == 8054) {//8054	法术波动  穷奇的法术秒10设置
                        data.getSkills().remove(x);
                        for (int k = data.getSkills().size() - 1; k >= 0; --k) {
                            FightingSkill skillTwo = data.getSkills().get(k);
                            if (skillTwo.getSkillid() > 1000 && skillTwo.getSkillid() <= 1100) {
                                skillTwo.setSkillsum(10);
                            }
                        }
                    } else if (skill.getSkilltype().equals(TypeUtil.TY_SSC_MCQT)) {
                        data.getSkills().remove(x);
                    }
                }
                if (myData.getCamp() == 1) {
                    Battlefield.overcamp1sp = myData.getSp();
                } else {
                    Battlefield.overcamp2sp = myData.getSp();
                }
            } catch (Exception e) {
                StringBuffer buffer = new StringBuffer();
                buffer.append("战斗类型:");
                buffer.append(this.BattleType);
                buffer.append("人物属性:");
                buffer.append(myData.getType() + "_" + myData.getCamp() + "_" + myData.getMan() + "_" + myData.getId() + "_" + myData.getManname());
                buffer.append("报错代码:" + MainServerHandler.getErrorMessage(e));
                AllServiceUtil.getRecordService().insert(new Record(0, buffer.toString()));
            }
            return;
        } else {
            if (type.equals("捕捉")) {
                MixDeal.ys(myData, false, this);
                DataActionType.getActionById(7).analysisAction(myData, zl, type, this);
                return;
            }
            if (type.equals("防御") || type.equals("保护")) {
                DataActionType.getActionById(8).analysisAction(myData, zl, type, this);
                return;
            }
            if (s == 1) {
                WriteOut.addtxt("昏睡进入:" + GsonUtil.getGsonUtil().getgson().toJson(zl), 9999L);
                return;
            }
            FightingSkill skill2 = myData.getSkillName(zl.getOriginator().getEndState());
            if (skill2 == null) {
                return;
            }
            if (ss == 1 || lhfm == 1 || (skill2.getSkillid() >= 1001 && skill2.getSkillid() <= 1100 && (j == 1 || ht == 1))) {
                //舍生取义 灵魂封魔 金箍儿 回头是岸
                this.systemMsg(myData, null, (ss == 1) ? 2 : ((lhfm == 1) ? 3 : ((j == 1) ? 4 : 5)), skill2);
                return;
            }
            if (j >= 2 && CreepsMixdeal.neiDanNames.contains(skill2.getSkillname())) {
                this.systemMsg(myData, null, 22, skill2);
                return;
            }
            if (j >= 3 && skill2.getSkillid() >= 1200 && skill2.getSkillid() < 1300) {
                this.systemMsg(myData, null, 21, skill2);
                return;
            }
            //9812 平波息浪 施放一个强力沧海横流，同时使沧波状态下的目标在使用5阶和4阶师门法术时有{公式一}%几率减弱为3阶和1阶法术，持续3回合。
            if (skill2.getSkillid() >= 1001 && skill2.getSkillid() <= 1100 && (skill2.getSkilllvl() == 4 || skill2.getSkilllvl() == 5)) {
                FightingSkill skill3 = myData.getAppendSkill(9812);
                if (skill3 != null && isV(skill3.getSkillhurt())) {
                    skill2 = myData.skillId((skill2.getSkilllvl() == 4) ? (skill2.getSkillid() - 3) : (skill2.getSkillid() - 2));
                    if (skill2 == null) {
                        return;
                    }
                }
            }
            if (myData.getType() == 1) {
                GroupBuff buff2 = this.getNoCampBuff(myData.getCamp(), TypeUtil.BB_XLHC);
                if (buff2 != null && this.recordList.contains(skill2.getSkilltype() + "_" + myData.getCamp())) {
                    return;
                }
                this.recordList.add(skill2.getSkilltype() + "_" + myData.getCamp());
            }
            if (!myData.isLicense(skill2)) {
                this.systemMsg(myData, null, 9, skill2);
                return;
            }
            if (myData.SkillCooling(skill2, this)) {
                return;
            }
            if (PK_MixDeal.isPK(this.BattleType)) {
                //先遗患 悬刃
                PK_MixDeal.PK_YX(myData, skill2, this);
                if (myData.getStates() != 0) {
                    return;
                }
                if (!myData.isLicense(skill2)) {
                    this.systemMsg(myData, null, 9, skill2);
                    return;
                }
                if (!zl.isEw() && PK_MixDeal.PK_HW(myData, skill2, this)) {
                    return;
                }
            }
            if (PK_MixDeal.isPK(this.BattleType)) {

                PK_MixDeal.PK_MSKK(myData, skill2, this);
                if (myData.getStates() != 0) {
                    return;
                }
            }
            int id2 = skill2.getSkillid();
            if (id2 >= 1021 && id2 <= 1025) {
                FightingSkill TY_RH_NDQB_SS = myData.getSkillType(TypeUtil.TY_RH_NDQB_SS);//两得其便（慑速）
                if (TY_RH_NDQB_SS == null) {
                    TY_RH_NDQB_SS = myData.getSkillType(TypeUtil.TY_RH_NDQB_SG);//两得其便（慑攻）
                }
                if (TY_RH_NDQB_SS == null) {
                    TY_RH_NDQB_SS = myData.getSkillType(TypeUtil.TY_RH_NDQB_SP);//两得其便（慑盘）
                }
                if (TY_RH_NDQB_SS != null) {
                    myData.addSkillIfConditionMet("震慑", TY_RH_NDQB_SS.getSkillhurt());
                }
            } else if (id2 >= 1026 && id2 <= 1030) {
                FightingSkill TY_RH_NDQB_SS = myData.getSkillType(TypeUtil.TY_RH_NDQB_SG);//两得其便（慑攻）
                if (TY_RH_NDQB_SS == null) {
                    TY_RH_NDQB_SS = myData.getSkillType(TypeUtil.TY_RH_NDQB_GS);//两得其便（攻速）
                }
                if (TY_RH_NDQB_SS == null) {
                    TY_RH_NDQB_SS = myData.getSkillType(TypeUtil.TY_RH_NDQB_GP);//两得其便（攻盘）
                }
                if (TY_RH_NDQB_SS != null) {
                    myData.addSkillIfConditionMet("加攻", TY_RH_NDQB_SS.getSkillhurt());
                }
            } else if (id2 >= 1031 && id2 <= 1035) {
                FightingSkill TY_RH_NDQB_SS = myData.getSkillType(TypeUtil.TY_RH_NDQB_SP);//两得其便（慑盘）
                if (TY_RH_NDQB_SS == null) {
                    TY_RH_NDQB_SS = myData.getSkillType(TypeUtil.TY_RH_NDQB_GP);//两得其便（攻盘）
                }
                if (TY_RH_NDQB_SS != null) {
                    myData.addSkillIfConditionMet("抗性", TY_RH_NDQB_SS.getSkillhurt());
                }
            } else if (id2 >= 1036 && id2 <= 1040) {
                FightingSkill TY_RH_NDQB_SS = myData.getSkillType(TypeUtil.TY_RH_NDQB_SS);//两得其便（慑速）
                if (TY_RH_NDQB_SS == null) {
                    TY_RH_NDQB_SS = myData.getSkillType(TypeUtil.TY_RH_NDQB_GS);//两得其便（攻速）
                }
                if (TY_RH_NDQB_SS != null) {
                    myData.addSkillIfConditionMet("加速", TY_RH_NDQB_SS.getSkillhurt());
                }
            }
            ++this.trzr;
            FightingSkill TY_RH_NDQB_SS = myData.getSkillType(TypeUtil.TY_R_TRZR);
            if (TY_RH_NDQB_SS != null && this.trzr == 5) {
                myData.getQuality().setRolehshs(myData.getQuality().getRolehshs() + TY_RH_NDQB_SS.getSkillhurt());
                myData.getSkills().remove(TY_RH_NDQB_SS);
                this.trzr = 0;
            }
            if ((id2 >= 1011 && id2 <= 1015) || (id2 >= 1071 && id2 <= 1075)) {
                this.trzr = 0;
            }
            TY_RH_NDQB_SS = myData.getSkillType("10063");
            if (TY_RH_NDQB_SS != null && this.trzr == 3) {
                myData.getQuality().setRolehsgh(myData.getQuality().getRolehsgh() + TY_RH_NDQB_SS.getSkillhurt());
                myData.getSkills().remove(TY_RH_NDQB_SS);
                this.trzr = 0;
            }
            TY_RH_NDQB_SS = myData.getSkillType("10065");
            if (id2 >= 1066 && id2 <= 1075 && TY_RH_NDQB_SS != null) {
                myData.lxzt = 0;
            }
            //见微知著
            jwzz(myData, id2);
            //御微知著
            ywzz(myData, id2);
            //知此识彼
            zcsb(myData, id2);
            //心荡神摇
            this.Xdsy(myData, skill2);
            //意动神飞
            this.Ydsf(myData);
            //温故知新
            wgzx(myData, id2);
            //一箭双雕
            appyjSkillEffect(myData, id2);
            //两全其美
            applqSkillEffect(myData, id2);
            //进道若退
            this.castSpell(skill2, myData, zl);
            //鬼影重重
            this.Ghostshadows(skill2, myData);
            //龙举云兴
            TY_L_Lgyx(skill2, myData);
            AddState addState = myData.xzstate("强化沧波");
            if (addState != null) {
                List<FightingState> Accepterlist2 = new ArrayList<>();
                List<ManData> datas = MixDeal.get(true, myData, 0, myData.getCamp(), 0, 0, 0, 0, 1, -1, this, 1);
                if (datas.size() != 0) {
                    FightingState Accepter = MixDeal.DSMY(myData, (ManData) datas.get(0), skill2.getSkillid(), this);
                    if (Accepter != null) {
                        Accepter.setStartState("法术攻击");
                        Accepter.setSkillsy(skill2.getSkillname());
                        Accepterlist2.add(Accepter);
                        FightingEvents Events = new FightingEvents();
                        Events.setAccepterlist(Accepterlist2);
                        this.NewEvents.add(Events);
                    }
                }
            }
            Boolean b = Boolean.FALSE;
            //释放师门法术判断是否添加回头是岸
            if (id2 >= 1001 && id2 <= 1100) {
                this.TZ_HTSA(myData);
                FightingSkill mSkill = myData.getSkillId(8056);
                if (mSkill != null && isV(mSkill.getSkillhitrate()) && !PK_MixDeal.isPK(this.BattleType)) {
                    skill2 = GsonUtil.getGsonUtil().getgson().fromJson(GsonUtil.getGsonUtil().getgson().toJson(skill2), FightingSkill.class);
                    skill2.setSkillsum(skill2.getSkillsum() + 1);
                    b = Boolean.TRUE;
                }
            }
            MixDeal.ys(myData, MixDeal.isTZ_XMST(id2), this);
            if (myData.xzstate("玄瞳鉴") != null && myData.xzstate("玄瞳鉴").getStateEffect2() <= 0.0D) {
                myData.RemoveAbnormal("玄瞳鉴");
                FightingState state = new FightingState();
                state.setEndState_2("玄瞳鉴");
                state.setMan(myData.getMan());
                state.setCamp(myData.getCamp());
                List<FightingState> fightingStateList = new ArrayList<>();
                fightingStateList.add(state);
                FightingEvents events = new FightingEvents();
                events.setAccepterlist(fightingStateList);
                this.NewEvents.add(events);
            }
            FightingSkill skill = skill2;
            Integer id = skill.getSkillid();
            this.JLSkills.put(id2, myData.getCamp());
            if ((id2 >= 1001 && id2 <= 1020) || (id2 >= 1071 && id2 <= 1075)) {//控制法术
                SpellActionType.getActionById(1).spellAction(myData, skill2, zl, this);
            } else if ((id2 >= 1041 && id2 <= 1065) || id2 == 8055) {//伤害法术
                SpellActionType.getActionById(11).spellAction(myData, skill2, zl, this);
            } else if (id2 >= 1021 && id2 <= 1025) {//震慑
                SpellActionType.getActionById(4).spellAction(myData, skill2, zl, this);
            } else if ((id2 >= 1026 && id2 <= 1040) || (id2 >= 1076 && id2 <= 1080) || id2 == 1214 || (id2 >= 1280 && id2 <= 1282)) {//增益法术
                SpellActionType.getActionById(5).spellAction(myData, skill2, zl, this);
            } else if (id2 >= 1066 && id2 <= 1070) {//三尸虫法术
                SpellActionType.getActionById(8).spellAction(myData, skill2, zl, this);
            } else if ((id2 >= 1081 && id2 <= 1090) || (id2 >= 1096 && id2 <= 1100)) {//霹雳法术
                SpellActionType.getActionById(20).spellAction(myData, skill2, zl, this);
            } else if (id2 >= 1091 && id2 <= 1095) {//甘霖法术
                SpellActionType.getActionById(21).spellAction(myData, skill2, zl, this);
            } else if (id2 == 7027) {
                SpellActionType.getActionById(47).spellAction(myData, skill2, zl, this);
            } else if (id2 == 3035) {//逆鳞
                SpellActionType.getActionById(22).spellAction(myData, skill2, zl, this);
            } else if (id2 == 1230) {//势如破竹
                SpellActionType.getActionById(13).spellAction(myData, skill2, zl, this);
            } else if (id2 == 1313 || id2 == 1234 || id2 == 23009) {//黑夜蒙蔽 or 防不慎防
                SpellActionType.getActionById(17).spellAction(myData, skill2, zl, this);
            } else if (id2 == 1333) {//无极
                SpellActionType.getActionById(18).spellAction(myData, skill2, zl, this);
            } else if (id2 == 1334) {//横扫四方
                SpellActionType.getActionById(19).spellAction(myData, skill2, zl, this);
            } else if (id2 == 1250) {//新增技能 偷梁换柱
                SpellActionType.getActionById(26).spellAction(myData, skill2, zl, this);
            } else if (id2 == 1251) {//新增技能 遮天蔽日
                SpellActionType.getActionById(27).spellAction(myData, skill2, zl, this);
            } else if (id2 == 1252) {//新增技能 大闹天宫
                SpellActionType.getActionById(28).spellAction(myData, skill2, zl, this);
            } else if (id2 == 1254) {//新增技能 万剑归宗
                SpellActionType.getActionById(29).spellAction(myData, skill2, zl, this);
            } else if (id2 == 1260) {//新增技能 万佛朝宗
                SpellActionType.getActionById(11).spellAction(myData, skill2, zl, this);
            } else if (id2 == 1261) {//新增技能 一刀流
                SpellActionType.getActionById(31).spellAction(myData, skill2, zl, this);
            } else if (id2 == 1262) {//新增技能 泰山压顶
                SpellActionType.getActionById(32).spellAction(myData, skill2, zl, this);
            } else if (id2 == 1263) {//新增技能 金身不灭
                SpellActionType.getActionById(33).spellAction(myData, skill2, zl, this);
            } else if (id2 == 1264) {//新增技能 万箭穿心
                SpellActionType.getActionById(34).spellAction(myData, skill2, zl, this);
            } else if (id2 == 1265) {//新增技能 困世法笼
                SpellActionType.getActionById(35).spellAction(myData, skill2, zl, this);
            } else if (id2 == 1266) {//新增技能 直捣黄龙
                SpellActionType.getActionById(36).spellAction(myData, skill2, zl, this);
            } else if (id2 == 1267) {//新增技能 倒转乾坤
                SpellActionType.getActionById(37).spellAction(myData, skill2, zl, this);
            } else if (id2 == 1268) {//新增技能 雷罚
                SpellActionType.getActionById(38).spellAction(myData, skill2, zl, this);
            } else if (id2 == 1269) {//新增技能 神罚
                SpellActionType.getActionById(39).spellAction(myData, skill2, zl, this);
            } else if (id2 == 1270) {//新增技能 藏锋蓄势
                SpellActionType.getActionById(40).spellAction(myData, skill2, zl, this);
            } else if (id2 == 1271) {//新增技能 魔尽佛生
                SpellActionType.getActionById(41).spellAction(myData, skill2, zl, this);
            } else if (id2 == 1272) {//新增技能 炎阳神罚
                SpellActionType.getActionById(42).spellAction(myData, skill2, zl, this);
            } else if (id2 == 1273) {//新增技能 气化三清
                SpellActionType.getActionById(43).spellAction(myData, skill2, zl, this);
            } else if (id2 == 23004) {
                SpellActionType.getActionById(44).spellAction(myData, skill2, zl, this);
            } else if (id2 >= 5001 && id2 <= 5015) {//法宝
                DataActionType.getActionById(22).analysisAction(myData, zl, type, this);
            } else if (id2 >= 9000 && id2 <= 10166) {//天演策通用
                if (id2 == 9110) {//知盈处虚
                    SpellActionType.getActionById(9).spellAction(myData, skill2, zl, this);
                } else if (id2 == 9126) {//生死相许
                    SpellActionType.getActionById(3).spellAction(myData, skill2, zl, this);
                } else if (id2 == 9209) {//惊才艳艳
                    SpellActionType.getActionById(6).spellAction(myData, skill2, zl, this);
                } else if (id2 == 9389 || id2 == 9262) {//流风回雪 销声匿迹
                    SpellActionType.getActionById(10).spellAction(myData, skill2, zl, this);
                } else if (id2 == 9412) {//弱水三千
                    SpellActionType.getActionById(7).spellAction(myData, skill2, zl, this);
                } else if (id2 == 9270) {//疾风迅雷
                    SpellActionType.getActionById(12).spellAction(myData, skill2, zl, this);
                } else if (id2 == 9132) {//行眠立盹
                    SpellActionType.getActionById(48).spellAction(myData, skill2, zl, this);
                } else if (id2 == 10080) {//佳期如梦
                    SpellActionType.getActionById(49).spellAction(myData, skill2, zl, this);
                } else if (id2 == Integer.parseInt(TypeUtil.TY_L_10129)) {//阑风长雨
                    SpellActionType.getActionById(51).spellAction(myData, skill2, zl, this);
                } else if (id2 == Integer.parseInt(TypeUtil.TY_L_10138)) {//趁波逐浪
                    SpellActionType.getActionById(52).spellAction(myData, skill2, zl, this);
                } else if (id2 == Integer.parseInt(TypeUtil.TY_L_10121)) {//雷车动地
                    SpellActionType.getActionById(50).spellAction(myData, skill2, zl, this);
                } else {//强力师门技能的大招
                    SpellActionType.getActionById(2).spellAction(myData, skill2, zl, this);
                }
            } else if (id2 >= 22000 && id2 <= 23000) {
                //       清心静气    气吞山河    行气如虹    神龙摆尾    气聚神凝         回合前施加状态
                if (id2 == 22000 || id2 == 22012 || id2 == 22032 || id2 == 22034 || id2 == 22026) {
                    SpellActionType.getActionById(24).spellAction(myData, skill2, zl, this);
                } //    神力加身     力挽狂澜     势如破竹
                if (id2 == 22006 || id2 == 22016 || id2 == 22022) {
                    DataActionType.getActionById(2).analysisAction(myData, zl, type, this);
                }//     利刃加身    神不守舍     无坚不摧    失魂落魄    鱼龙潜跃     幻影迷踪    凝神一击     兽魂俯首    法魂护体     刚柔兼备
                if (id2 == 22002 || id2 == 22004 || id2 == 22010 || id2 == 22028 || id2 == 22030 || id2 == 22014 || id2 == 22008 || id2 == 22018 || id2 == 22024 || id2 == 22020) {
                    SpellActionType.getActionById(25).spellAction(myData, skill2, zl, this);
                }
            } else if (id2 == 450050) {
                SpellActionType.getActionById(53).spellAction(myData, skill2, zl, this);
            } else if (id2 == 30000) {
                SpellActionType.getActionById(32).spellAction(myData, skill, zl, this);
            } else if (id2 == 30002) {
                skill.setSkillsum(1);
                List<FightingState> Accepterlist = new ArrayList<>();
                List<ManData> datas = MixDeal.getjieshou(zl, skill, myData, Accepterlist, this);
                if (datas.size() == 0) return;
                if (datas.get(0).getType() != 0) return;
                List<FightingState> removeStates = new ArrayList<>();
                FightingState fightingState = new FightingState();
                if (myData.daijia(skill, fightingState, this)) return;
                fightingState.setStartState("法术攻击");
                fightingState.setCamp(myData.getCamp());
                fightingState.setMan(myData.getMan());
                removeStates.add(fightingState);
                FightingState Accepter1 = new FightingState();
                FightingEvents fightingEvents = new FightingEvents();
                Accepter1.setMan((datas.get(0)).getMan());
                Accepter1.setCamp(datas.get(0).getCamp());
                Accepter1.setEndState_1("断生契");
                double sum = (datas.get(0).getHp() / (datas.get(0)).getHp_z()) + skill.getP1();
                (datas.get(0)).addAddState("断生契", skill.getS1(), skill.getE1(), 3 + (isV(sum) ? (isV(50.0D) ? 1 : 2) : 0));
                datas.get(0).xzstate("断生契").setStateEffect3(skill.getSkillgain());
                removeStates.add(Accepter1);
                fightingEvents.setAccepterlist(removeStates);
                this.NewEvents.add(fightingEvents);
            } else if (id2 == 30003) {
                skill.setSkillsum(1);
                List<FightingState> Accepterlist = new ArrayList<>();
                List<ManData> datas = MixDeal.getjieshou(zl, skill, myData, Accepterlist, this);
                if (datas.size() == 0) return;
                if (datas.get(0).getType() != 0) return;
                List<FightingState> removeStates = new ArrayList<>();
                FightingState fightingState = new FightingState();
                if (myData.daijia(skill, fightingState, this)) return;
                fightingState.setStartState("法术攻击");
                fightingState.setCamp(myData.getCamp());
                fightingState.setMan(myData.getMan());
                removeStates.add(fightingState);
                FightingState Accepter1 = new FightingState();
                Accepter1.setMan(datas.get(0).getMan());
                Accepter1.setCamp(datas.get(0).getCamp());
                Accepter1.setEndState_1("屠巫剑");
                datas.get(0).addAddState("屠巫剑", skill.getP1(), skill.getE1() / 100.0D, skill.getSkillcontinued());
                if (isV(skill.getS1())) if (datas.get(0).xzstate("加速") != null) {
                    datas.get(0).RemoveAbnormal("加速");
                    Accepter1.setEndState_2("加速");
                } else if (datas.get(0).xzstate("抗性") != null) {
                    datas.get(0).RemoveAbnormal("抗性");
                    Accepter1.setEndState_2("抗性");
                } else if (datas.get(0).xzstate("力量") != null) {
                    datas.get(0).RemoveAbnormal("力量");
                    Accepter1.setEndState_2("力量");
                } else if (datas.get(0).xzstate("甘霖") != null) {
                    datas.get(0).RemoveAbnormal("甘霖");
                    Accepter1.setEndState_2("甘霖");
                }
                removeStates.add(Accepter1);
                FightingEvents fightingEvents = new FightingEvents();
                fightingEvents.setAccepterlist(removeStates);
                this.NewEvents.add(fightingEvents);
            } else if (id2 == 30004) {
                skill.setSkillsum(1);
                FightingEvents fightingEvents1 = new FightingEvents();
                List<FightingState> removeStates = new ArrayList<>();
                FightingState fightingState = new FightingState();
                if (myData.daijia(skill, fightingState, this)) return;
                fightingState.setStartState("法术攻击");
                fightingState.setCamp(myData.getCamp());
                fightingState.setMan(myData.getMan());
                removeStates.add(fightingState);
                fightingEvents1.setAccepterlist(removeStates);
                this.NewEvents.add(fightingEvents1);
                List<FightingState> Accepterlist = new ArrayList<>();
                List<ManData> datas = MixDeal.getjieshou(zl, skill, myData, Accepterlist, this);
                if (datas.size() == 0) return;
                FightingEvents fightingEvents = new FightingEvents();
                List<FightingState> fightingStateList = new ArrayList<>();
                for (ManData data : datas) {
                    FightingState Accepter1 = new FightingState();
                    Accepter1.setMan(data.getMan());
                    Accepter1.setCamp(data.getCamp());
                    Accepter1.setEndState_1("玄瞳鉴");
                    data.addAddState("玄瞳鉴", skill.getS1(), skill.getSkillcontinued() + skill.getE1(), 6 + (int) skill.getP1());
                    fightingStateList.add(Accepter1);
                }
                fightingEvents.setAccepterlist(fightingStateList);
                this.NewEvents.add(fightingEvents);
            } else if (id2 == 30005) {
                FightingState fightingState = new FightingState();
                if (myData.daijia(skill, fightingState, this)) return;
                SpellActionType.getActionById(54).spellAction(myData, skill, zl, this);
            } else if (id2 == 30008) {
                FightingState fightingStatexuan = new FightingState();
                if (myData.daijia(skill, fightingStatexuan, this)) return;
                skill.setSkillsum(1);
                List<FightingState> Accepterlist = new ArrayList<>();
                List<ManData> datas = MixDeal.getjieshou(zl, skill, myData, Accepterlist, this);
                if (datas.size() == 0) return;
                if (datas.get(0).xzstate("冷却") != null && myData.xzstate("冷却").getStateEffect() == 30008.0D) {
                    SendMessage.sendMessageByRoleName(myData.getManname(), Agreement.getAgreement().PromptAgreement("技能处于冷却状态"));
                    return;
                }
                if (datas.get(0).xzstate("振魂鼓") != null) {
                    if (datas.get(0).xzstate("振魂鼓").getStateEffect() >= 3.0D) {
                        SendMessage.sendMessageByRoleName(myData.getManname(), Agreement.getAgreement().PromptAgreement("该角色此状态已满"));
                        return;
                    }
                    List<FightingState> removeStates = new ArrayList<>();
                    FightingState fightingState = new FightingState();
                    fightingState.setStartState("法术攻击");
                    fightingState.setCamp(myData.getCamp());
                    fightingState.setMan(myData.getMan());
                    removeStates.add(fightingState);
                    FightingState Accepter1 = new FightingState();
                    FightingEvents fightingEvents = new FightingEvents();
                    fightingEvents.setAccepterlist(removeStates);
                    Accepter1.setMan(datas.get(0).getMan());
                    Accepter1.setCamp(datas.get(0).getCamp());
                    Accepter1.setEndState_1("振魂鼓");
                    datas.get(0).xzstate("振魂鼓").setStateEffect(((ManData) datas.get(0)).xzstate("振魂鼓").getStateEffect() + 1.0D);
                    if (datas.get(0).xzstate("振魂鼓").getStateEffect() >= 3.0D) {
                        myData.addAddState("冷却", 30008.0D, 0.0D, 999);
                    } else {
                        myData.addAddState("冷却", 30008.0D, 0.0D, 5);
                    }
                    this.NewEvents.add(fightingEvents);
                } else {
                    List<FightingState> removeStates = new ArrayList<>();
                    FightingState fightingState = new FightingState();
                    fightingState.setStartState("法术攻击");
                    fightingState.setCamp(myData.getCamp());
                    fightingState.setMan(myData.getMan());
                    removeStates.add(fightingState);
                    FightingState Accepter1 = new FightingState();
                    FightingEvents fightingEvents = new FightingEvents();
                    fightingEvents.setAccepterlist(removeStates);
                    Accepter1.setMan(datas.get(0).getMan());
                    Accepter1.setCamp(datas.get(0).getCamp());
                    Accepter1.setEndState_1("振魂鼓");
                    datas.get(0).addAddState("振魂鼓", 999, 1.0D, skill.getSkillgain() + skill.getS1(), skill.getSkillgain1() + skill.getP1(), skill.getE1());
                    myData.addAddState("冷却", 30008.0D, 0.0D, 5);
                    this.NewEvents.add(fightingEvents);
                }
            } else if (id2 == 30009) {
                FightingState fightingStatexuan = new FightingState();
                if (myData.daijia(skill, fightingStatexuan, this)) {
                    return;
                }

                skill.setSkillsum(1);
                List<FightingState> Accepterlist = new ArrayList<>();
                List<ManData> datas = MixDeal.getjieshou(zl, skill, myData, Accepterlist, this);
                if (datas.size() == 0) return;
                if (myData.xzstate("冷却") != null && myData.xzstate("冷却").getStateEffect() == 30009.0D)
                    return;
                if (datas.get(0).xzstate("淬魂锤") != null) {
                    if (datas.get(0).xzstate("淬魂锤").getStateEffect() >= 3.0D) {
                        SendMessage.sendMessageByRoleName(myData.getManname(), Agreement.getAgreement().PromptAgreement("改角色此状态已满"));
                        return;
                    }
                    List<FightingState> removeStates = new ArrayList<>();
                    FightingState fightingState = new FightingState();
                    fightingState.setStartState("法术攻击");
                    fightingState.setCamp(myData.getCamp());
                    fightingState.setMan(myData.getMan());
                    removeStates.add(fightingState);
                    FightingState Accepter1 = new FightingState();
                    FightingEvents fightingEvents = new FightingEvents();
                    fightingEvents.setAccepterlist(removeStates);
                    Accepter1.setMan(datas.get(0).getMan());
                    Accepter1.setCamp(datas.get(0).getCamp());
                    Accepter1.setEndState_1("淬魂锤");
                    datas.get(0).xzstate("淬魂锤").setStateEffect(datas.get(0).xzstate("淬魂锤").getStateEffect() + 1.0D);
                    if (datas.get(0).xzstate("淬魂锤").getStateEffect() >= 3.0D) {
                        myData.addAddState("冷却", 30009.0D, 0.0D, 999);
                    } else {
                        myData.addAddState("冷却", 30009.0D, 0.0D, 5);
                    }
                    this.NewEvents.add(fightingEvents);
                } else {
                    List<FightingState> removeStates = new ArrayList<>();
                    FightingState fightingState = new FightingState();
                    fightingState.setStartState("法术攻击");
                    fightingState.setCamp(myData.getCamp());
                    fightingState.setMan(myData.getMan());
                    removeStates.add(fightingState);
                    FightingState Accepter1 = new FightingState();
                    FightingEvents fightingEvents = new FightingEvents();
                    fightingEvents.setAccepterlist(removeStates);
                    Accepter1.setMan(datas.get(0).getMan());
                    Accepter1.setCamp(datas.get(0).getCamp());
                    Accepter1.setEndState_1("淬魂锤");
                    datas.get(0).addAddState("淬魂锤", 999, 1.0D, skill.getSkillgain() + skill.getS1(), skill.getSkillgain1() + skill.getP1(), skill.getE1());
                    myData.addAddState("冷却", 30009.0D, 0.0D, 5);
                    this.NewEvents.add(fightingEvents);
                }
            } else if (id2 == 30013) {
                SpellActionType.getActionById(55).spellAction(myData, skill, zl, this);
            } else if (id2 == 30016) {
                SpellActionType.getActionById(56).spellAction(myData, skill, zl, this);
            } else if (id2 == 30017) {
                SpellActionType.getActionById(57).spellAction(myData, skill, zl, this);
            } else if (id2 == 30019) {
                List<FightingState> Accepterlist = new ArrayList<>();
                FightingState Originator = zl.getOriginator();
                if (myData.daijia(skill, Originator, this)) return;
                Originator.setStartState("法术攻击");
                Originator.setSkillsy(skill.getSkillname());
                zl.setOriginator(null);
                Accepterlist.add(Originator);
                zl.setAccepterlist(Accepterlist);
                this.NewEvents.add(zl);
                List<FightingState> fightingStateList = new ArrayList<>();
                FightingEvents fightingEvents = new FightingEvents();
                for (ManData data : this.fightingdata) {
                    if (data.getCamp() != myData.getCamp() && data.xzstate("遗忘") != null) {
                        FightingState Accepter1 = new FightingState();
                        Accepter1.setCamp(data.getCamp());
                        Accepter1.setMan(data.getMan());
                        data.addAddState("忘情石", 0.0D, 0.0D, Math.min((int) (myData.getSkillId(30019).getSkillcontinued() + myData.getSkillId(30019).getP1()), data.xzstate("遗忘").getSurplus()));
                        Accepter1.setEndState_1("忘情石");
                        fightingStateList.add(Accepter1);
                        Map<Integer, AddState> addStateMap = new HashMap<>();
                        for (AddState state : data.getAddStates()) {
                            if (state.getStatename().equals("遗忘") && state.getStateEffect() != -1.0D) {
                                addStateMap.put((int) state.getStateEffect(), state);
                                System.out.println(state.getStateEffect());
                            }
                        }
                        for (FightingSkill fightingSkill : data.getSkills()) {
                            if (addStateMap.get(fightingSkill.getSkillid()) == null && isV(skill.getE1())) {
                                AddState state = new AddState();
                                state.setStatename("遗忘");
                                System.err.println("技能ID:" + fightingSkill.getSkillid());
                                state.setStateEffect(fightingSkill.getSkillid());
                                state.setType(-1);
                                state.setSurplus(data.xzstate("遗忘").getSurplus());
                                data.getAddStates().add(state);
                            }
                        }
                    }
                }
                fightingEvents.setAccepterlist(fightingStateList);
                this.NewEvents.add(fightingEvents);
            } else if (id2 == 30020) {
                ManData nomyData = getdata(zl.getAccepterlist().get(0).getCamp(), zl.getAccepterlist().get(0).getMan());
                List<FightingState> Accepterlist = new ArrayList<>();
                FightingState Originator = zl.getOriginator();
                if (myData.daijia(skill, Originator, this)) return;
                Originator.setStartState("法术攻击");
                Originator.setSkillsy(skill.getSkillname());
                zl.setOriginator(null);
                Accepterlist.add(Originator);
                zl.setAccepterlist(Accepterlist);
                this.NewEvents.add(zl);
                List<FightingState> fightingStateList = new ArrayList<>();
                FightingEvents fightingEvents = new FightingEvents();
                FightingState Accepter1 = new FightingState();
                Accepter1.setCamp(nomyData.getCamp());
                Accepter1.setMan(nomyData.getMan());
                nomyData.addAddState("冥河纱", skill.getS1(), skill.getE1(), (int) (skill.getSkillcontinued() + skill.getP1()));
                Accepter1.setEndState_1("冥河纱");
                fightingStateList.add(Accepter1);
                fightingEvents.setAccepterlist(fightingStateList);
                this.NewEvents.add(fightingEvents);
            } else if (id2 == 30021) {
                FightingState fightingStatexuan = new FightingState();
                if (myData.daijia(skill, fightingStatexuan, this)) return;
                skill.setSkillsum(1 + (isV(skill.getE1()) ? 1 : 0));
                skill.setSkilltype("三尸虫");
                SpellActionType.getActionById(8).spellAction(myData, skill, zl, this);
            } else if (id == 30022) {
                ManData nomyData = getdata(zl.getAccepterlist().get(0).getCamp(), zl.getAccepterlist().get(0).getMan());
                List<FightingState> Accepterlist = new ArrayList<>();
                FightingState Originator = zl.getOriginator();
                if (myData.daijia(skill, Originator, this)) return;
                Originator.setStartState("法术攻击");
                Originator.setSkillsy(skill.getSkillname());
                zl.setOriginator(null);
                Accepterlist.add(Originator);
                zl.setAccepterlist(Accepterlist);
                this.NewEvents.add(zl);
                if (nomyData.getCamp() == myData.getCamp()) return;
                List<FightingState> fightingStateList = new ArrayList<>();
                FightingEvents fightingEvents = new FightingEvents();
                FightingState Accepter1 = new FightingState();
                Accepter1.setCamp(nomyData.getCamp());
                Accepter1.setMan(nomyData.getMan());
                nomyData.addAddState("幽魂灯", 3 + (isV(skill.getE1()) ? 1 : 0), skill.getSkillgain(), skill.getSkillgain1(), skill.getSkillhurt(), skill.getS1(), skill.getP1());
                Accepter1.setEndState_1("幽魂灯");
                fightingStateList.add(Accepter1);
                fightingEvents.setAccepterlist(fightingStateList);
                this.NewEvents.add(fightingEvents);
            } else if (id == 30024) {
                FightingState fightingStatexuan = new FightingState();
                if (myData.daijia(skill, fightingStatexuan, this)) return;
                skill.setSkillsum(1);
                skill.setSkillhurt(skill.getSkillgain());
                skill.setSkilltype(TypeUtil.L_PL);
                myData.isjv = true;
                List<ManData> datas = new ArrayList<>();
                ManData nomyData = getdata(zl.getAccepterlist().get(0).getCamp(), zl.getAccepterlist().get(0).getMan());
                for (ManData data : this.fightingdata) {
                    if ((data.getType() == 0 || data.getType() == 1 || data.getType() == 2) && data.getCamp() != myData.getCamp())
                        datas.add(data);
                }
                datas.removeIf(e -> (e.getCamp() == nomyData.getCamp() && e.getMan() == nomyData.getMan()));
                skill.setFightingManData(Collections.singletonList(nomyData));
                FightingState fightingState = zl.getOriginator();
                int i = 0;
                while (i < skill.getSkillcontinued() + (isV(skill.getP1()) ? 1 : 0)) {
                    int c = 0;
                    while (c < 3 && i < skill.getSkillcontinued() + (isV(skill.getP1()) ? 1 : 0) && skill.getFightingManData().get(0).getStates() == 0) {
                        FightingEvents zl1 = new FightingEvents();
                        zl1.setOriginator(fightingState);
                        SpellActionType.getActionById(20).spellAction(myData, skill, zl1, this);
                        c++;
                        i++;
                    }
                    if (datas.size() <= 0) return;
                    ManData data = datas.get(random.nextInt(datas.size()));
                    skill.setFightingManData(Collections.singletonList(data));
                    datas.remove(data);
                }
                myData.isjv = false;
                if (myData.fzsh != 0L) {
                    FightingEvents moveEvents1 = new FightingEvents();
                    FightingState fightingState1 = new FightingState();
                    List<FightingState> moves2 = new ArrayList<>();
                    fightingState1.setCamp(myData.getCamp());
                    fightingState1.setMan(myData.getMan());
                    ChangeFighting changeFighting = new ChangeFighting();
                    changeFighting.setChangehp((int) myData.fzsh);
                    FightingPackage.ChangeProcess(changeFighting, null, myData, fightingState1, "反震", moves2, this);
                    fightingState1.setStartState("stand");
                    moveEvents1.setAccepterlist(moves2);
                    this.NewEvents.add(moveEvents1);
                }
            } else if (id == 30025) {
                ManData nomyData = getdata((zl.getAccepterlist().get(0)).getCamp(), (zl.getAccepterlist().get(0)).getMan());
                List<FightingState> Accepterlist = new ArrayList<>();
                FightingState Originator = zl.getOriginator();
                if (myData.daijia(skill, Originator, this)) return;
                Originator.setStartState("法术攻击");
                Originator.setSkillsy(skill.getSkillname());
                zl.setOriginator(null);
                Accepterlist.add(Originator);
                zl.setAccepterlist(Accepterlist);
                this.NewEvents.add(zl);
                if (nomyData.getCamp() != myData.getCamp()) return;
                List<FightingState> fightingStateList = new ArrayList<>();
                FightingEvents fightingEvents = new FightingEvents();
                FightingState Accepter1 = new FightingState();
                Accepter1.setCamp(nomyData.getCamp());
                Accepter1.setMan(nomyData.getMan());
                nomyData.addAddState("沧溟露", 4, skill.getSkillgain1(), skill.getS1(), skill.getP1(), skill.getE1(), skill.getE2());
                Accepter1.setHp_Change((int) (skill.getSkillgain() / 100.0D * nomyData.getHp_z()));
                Accepter1.setMp_Change((int) (skill.getSkillgain() / 100.0D * nomyData.getMp_z()));
                Accepter1.setEndState_1("沧溟露");
                fightingStateList.add(Accepter1);
                fightingEvents.setAccepterlist(fightingStateList);
                this.NewEvents.add(fightingEvents);
            } else if (id == 30026) {
                ManData nomyData = getdata(zl.getAccepterlist().get(0).getCamp(), zl.getAccepterlist().get(0).getMan());
                List<FightingState> Accepterlist = new ArrayList<>();
                FightingState Originator = zl.getOriginator();
                if (myData.daijia(skill, Originator, this)) return;
                if (nomyData.xzstate("锁元珠") != null) {
                    double d = 0.0D;
                    d = nomyData.xzstate("锁元珠").getStateEffect();
                    if (d >= 5.0D) {
                        SendMessage.sendMessageByRoleName(myData.getManname(), Agreement.getAgreement().PromptAgreement("层数已达到上限"));
                        return;
                    }
                }
                Originator.setStartState("法术攻击");
                Originator.setSkillsy(skill.getSkillname());
                zl.setOriginator(null);
                Accepterlist.add(Originator);
                zl.setAccepterlist(Accepterlist);
                this.NewEvents.add(zl);
                if (nomyData.getCamp() == myData.getCamp()) return;
                if (nomyData.getType() != 0) return;
                List<FightingState> fightingStateList = new ArrayList<>();
                FightingEvents fightingEvents = new FightingEvents();
                FightingState Accepter1 = new FightingState();
                Accepter1.setCamp(nomyData.getCamp());
                Accepter1.setMan(nomyData.getMan());
                double cengshu = 0.0D;
                if (nomyData.xzstate("锁元珠") != null) {
                    double sp = nomyData.xzstate("锁元珠").getSurplus();
                    cengshu = nomyData.xzstate("锁元珠").getStateEffect();
                    if (cengshu >= 5.0D) {
                        SendMessage.sendMessageByRoleName(myData.getManname(), Agreement.getAgreement().PromptAgreement("层数已达到上限"));
                        return;
                    }
                    if (sp >= 998.0D) {
                        nomyData.UP(Accepter1, 0, cengshu * nomyData.xzstate("锁元珠").getStateEffect2() * (1.0D + nomyData.xzstate("锁元珠").getStateEffect3() / 100.0D) / 100.0D);
                    } else {
                        nomyData.UP(Accepter1, 0, cengshu * nomyData.xzstate("锁元珠").getStateEffect2() / 100.0D);
                    }
                    cengshu++;
                }
                nomyData.addAddState("重伤", 6.0D, skill.getS1(), 3);
                nomyData.addAddState("锁元珠", (cengshu == 0.0D) ? 1.0D : cengshu, skill.getSkillgain(), skill.getE1(), 999);
                double ps = -(skill.getSkillgain() * (1.0D + skill.getE1() / 100.0D) / 100.0D * ((cengshu == 0.0D) ? 1.0D : cengshu));
                nomyData.UP(Accepter1, 0, ps);
                nomyData.xzstate("锁元珠").setStateEffect4(0.0D);
                Accepter1.setEndState_1("锁元珠");
                Accepter1.setMp_Change((int) (-skill.getP1() / 100.0D * nomyData.getMp_z()));
                fightingStateList.add(Accepter1);
                fightingEvents.setAccepterlist(fightingStateList);
                this.NewEvents.add(fightingEvents);
            } else if (id == 30027) {
                ManData nomyData = getdata(zl.getAccepterlist().get(0).getCamp(), zl.getAccepterlist().get(0).getMan());
                if (myData.getType() != 0) return;
                List<FightingState> Accepterlist = new ArrayList<>();
                FightingState Originator = zl.getOriginator();
                if (myData.daijia(skill, Originator, this)) return;
                Originator.setStartState("法术攻击");
                Originator.setSkillsy(skill.getSkillname());
                zl.setOriginator(null);
                Accepterlist.add(Originator);
                zl.setAccepterlist(Accepterlist);
                this.NewEvents.add(zl);
                if (nomyData.getCamp() == myData.getCamp()) return;
                List<FightingState> fightingStateList = new ArrayList<>();
                FightingEvents fightingEvents = new FightingEvents();
                FightingState Accepter1 = new FightingState();
                Accepter1.setCamp(nomyData.getCamp());
                Accepter1.setMan(nomyData.getMan());
                if (nomyData.xzstate("散灵须") != null)
                    nomyData.UP(Accepter1, 1, nomyData.xzstate("散灵须").getStateEffect3() / 100.0D);
                nomyData.addAddState("散灵须", skill.getS1(), skill.getP1(), skill.getSkillgain(), 3);
                nomyData.UP(Accepter1, 1, -skill.getSkillgain() / 100.0D);
                Accepter1.setEndState_1("散灵须");
                fightingStateList.add(Accepter1);
                if (skill.getE1() != 0.0D && myData.huodeMp / myData.chushiMP < skill.getE2() / 100.0D) {
                    FightingState Accepter2 = new FightingState();
                    Accepter2.setCamp(myData.getCamp());
                    Accepter2.setMan(myData.getMan());
                    int hp = (int) (Integer.parseInt(Accepter1.getUp().split("=")[1]) * skill.getE1() / 100.0D);
                    myData.huodeMp += hp;
                    double xs = hp / myData.chushiMP;
                    myData.UP(Accepter2, 1, xs);
                    fightingStateList.add(Accepter2);
                }
                fightingEvents.setAccepterlist(fightingStateList);
                this.NewEvents.add(fightingEvents);
            } else if (id == 30032) {
                ManData nomyData = getdata(zl.getAccepterlist().get(0).getCamp(), zl.getAccepterlist().get(0).getMan());
                List<FightingState> Accepterlist = new ArrayList<>();
                FightingState Originator = zl.getOriginator();
                if (myData.daijia(skill, Originator, this)) return;
                Originator.setStartState("法术攻击");
                Originator.setSkillsy(skill.getSkillname());
                zl.setOriginator(null);
                Accepterlist.add(Originator);
                zl.setAccepterlist(Accepterlist);
                this.NewEvents.add(zl);
                double addHS = 0.0D, addQF = 0.0D, addKX = 0.0D;
                if (PK_MixDeal.isPK(this.BattleType) && skill.getSkilltype().equals("遗忘")) {
                    List<FightingSkill> skillList = myData.getSkillTypes(Arrays.asList(TypeUtil.TY_G_10085, TypeUtil.TY_G_10095, TypeUtil.TY_G_10100));
                    for (FightingSkill fightingSkill : skillList) {
                        if (nomyData.getType() == 0 && !hasPet(nomyData)) addHS += getSkillhurt(fightingSkill, "忘");
                    }
                }
                String[] skillTypes = {TypeUtil.TY_R_YJSD_SH, TypeUtil.TY_R_YJSD_FH, TypeUtil.TY_R_YJSD_FS, TypeUtil.TY_R_YJSD_FD, TypeUtil.TY_R_YJSD_SD};
                for (String skill1 : skillTypes) {
                    FightingSkill skillType = myData.getSkillType(skill1);
                    if (skillType != null) if (skill.getSkillid() == 1004 && myData.xzstate("灵识_混") != null) {
                        addHS = myData.xzstate("灵识_混").getStateEffect();
                    } else if (skill.getSkillid() == 1009 && myData.xzstate("灵识_封") != null) {
                        addHS = myData.xzstate("灵识_封").getStateEffect();
                    } else if (skill.getSkillid() == 1014 && myData.xzstate("灵识_睡") != null) {
                        addHS = myData.xzstate("灵识_睡").getStateEffect();
                    }
                }
                String[] skillTypes2 = {TypeUtil.TY_R_LQQM_SH, TypeUtil.TY_R_LQQM_FH, TypeUtil.TY_R_LQQM_FS, TypeUtil.TY_R_LQQM_FD, TypeUtil.TY_R_LQQM_DS};
                for (String skill21 : skillTypes2) {
                    FightingSkill skillType2 = myData.getSkillType(skill21);
                    if (skillType2 != null) if (skill.getSkillid() == 1005 && myData.xzstate("灵识_混") != null) {
                        addHS = myData.xzstate("灵识_混").getStateEffect();
                    } else if (skill.getSkillid() == 1010 && myData.xzstate("灵识_封") != null) {
                        addHS = myData.xzstate("灵识_封").getStateEffect();
                    } else if (skill.getSkillid() == 1015 && myData.xzstate("灵识_睡") != null) {
                        addHS = myData.xzstate("灵识_睡").getStateEffect();
                    }
                }
                List<FightingState> fightingStateList = new ArrayList<>();
                double vluae = Calculation.getCalculation().renfa_value(myData, nomyData, skill.getSkillgain() + skill.getS1(), "昏睡", addHS, addQF, addKX);
                FightingState fightingState = new FightingState();
                fightingState.setCamp(nomyData.getCamp());
                fightingState.setMan(nomyData.getMan());
                fightingState.setEndState_1("暮眠钟");
                nomyData.addAddState("暮眠钟", vluae, skill.getE1(), 2 + (isV(skill.getP1()) ? 1 : 0));
                if (random.nextInt(108) < vluae) {
                    nomyData.addAddState("昏睡", 0.0D, 0.0D, 2);
                    fightingState.setEndState_1("昏睡");
                }
                if (isV(skill.getE1())) nomyData.addAddState("重度昏睡", 0.0D, 0.0D, 2);
                fightingStateList.add(fightingState);
                FightingEvents fightingEvents = new FightingEvents();
                fightingEvents.setAccepterlist(fightingStateList);
                this.NewEvents.add(fightingEvents);
            } else if (id == 30033) {
                ManData nomyData = getdata(zl.getAccepterlist().get(0).getCamp(), ((FightingState) zl.getAccepterlist().get(0)).getMan());
                List<FightingState> Accepterlist = new ArrayList<>();
                FightingState Originator = zl.getOriginator();
                if (myData.daijia(skill, Originator, this)) return;
                Originator.setStartState("法术攻击");
                Originator.setSkillsy(skill.getSkillname());
                zl.setOriginator(null);
                Accepterlist.add(Originator);
                zl.setAccepterlist(Accepterlist);
                this.NewEvents.add(zl);
                if (nomyData.getCamp() != myData.getCamp()) return;
                List<FightingState> fightingStateList = new ArrayList<>();
                FightingState fightingState = new FightingState();
                fightingState.setCamp(nomyData.getCamp());
                fightingState.setMan(nomyData.getMan());
                fightingState.setEndState_1("夔灵鼓");
                nomyData.addAddState("夔灵鼓", skill.getSkillcontinued(), skill.getSkillgain() + skill.getP1(), skill.getS1(), 5 + (int) skill.getE1());
                fightingStateList.add(fightingState);
                FightingEvents fightingEvents = new FightingEvents();
                fightingEvents.setAccepterlist(fightingStateList);
                this.NewEvents.add(fightingEvents);
            } else if (id == 30034) {
                ManData nomyData = getdata(zl.getAccepterlist().get(0).getCamp(), zl.getAccepterlist().get(0).getMan());
                List<FightingState> Accepterlist = new ArrayList<>();
                FightingState Originator = zl.getOriginator();
                if (myData.daijia(skill, Originator, this)) return;
                Originator.setStartState("法术攻击");
                Originator.setSkillsy(skill.getSkillname());
                zl.setOriginator(null);
                Accepterlist.add(Originator);
                zl.setAccepterlist(Accepterlist);
                this.NewEvents.add(zl);
                if (nomyData.getCamp() != myData.getCamp()) return;
                List<FightingState> fightingStateList = new ArrayList<>();
                FightingState fightingState = new FightingState();
                fightingState.setCamp(nomyData.getCamp());
                fightingState.setMan(nomyData.getMan());
                fightingState.setEndState_1("玄鳞甲");
                int k = 1;
                if (nomyData.getHuoyue() > 600.0D) k = (int) (k + nomyData.getHuoyue() / 600.0D);
                nomyData.addAddState("玄鳞甲", (skill.getSkillcontinued() + (isV(skill.getS1()) ? 1 : 0)), skill.getSkillgain() + skill.getP1() * k, 3 + (int) skill.getE1());
                fightingStateList.add(fightingState);
                FightingEvents fightingEvents = new FightingEvents();
                fightingEvents.setAccepterlist(fightingStateList);
                this.NewEvents.add(fightingEvents);
            } else if (id == 30036) {
                ManData nomyData = getdata(zl.getAccepterlist().get(0).getCamp(), zl.getAccepterlist().get(0).getMan());
                List<FightingState> Accepterlist = new ArrayList<>();
                FightingState Originator = zl.getOriginator();
                if (myData.daijia(skill, Originator, this)) return;
                Originator.setStartState("法术攻击");
                Originator.setSkillsy(skill.getSkillname());
                zl.setOriginator(null);
                Accepterlist.add(Originator);
                zl.setAccepterlist(Accepterlist);
                this.NewEvents.add(zl);
                if (nomyData.getCamp() != myData.getCamp()) return;
                List<FightingState> fightingStateList = new ArrayList<>();
                FightingState fightingState = new FightingState();
                fightingState.setCamp(nomyData.getCamp());
                fightingState.setMan(nomyData.getMan());
                fightingState.setEndState_1("引蕴瓶");
                nomyData.addAddState("引蕴瓶", skill.getSkillgain(), skill.getS1(), skill.getP1(), skill.getSkillcontinued() + (int) skill.getE1());
                fightingStateList.add(fightingState);
                FightingEvents fightingEvents = new FightingEvents();
                fightingEvents.setAccepterlist(fightingStateList);
                this.NewEvents.add(fightingEvents);
            } else if (id == 30037) {
                List<FightingState> Accepterlist = new ArrayList<>();
                FightingState Originator = zl.getOriginator();
                if (myData.daijia(skill, Originator, this)) return;
                Originator.setStartState("法术攻击");
                Originator.setSkillsy(skill.getSkillname());
                zl.setOriginator(null);
                Accepterlist.add(Originator);
                zl.setAccepterlist(Accepterlist);
                this.NewEvents.add(zl);
                skill.setSkillsum(7);
                List<ManData> datas = MixDeal.getjieshou(zl, skill, myData, Accepterlist, this);
                List<FightingState> fightingStateList = new ArrayList<>();
                for (ManData nomyData : datas) {
                    FightingState fightingState = new FightingState();
                    fightingState.setCamp(nomyData.getCamp());
                    fightingState.setMan(nomyData.getMan());
                    fightingState.setEndState_1("千丝网");
                    nomyData.addAddState("千丝网", skill.getSkillgain1(), skill.getS1(), skill.getE1(), 3 + (int) skill.getP1());
                    fightingStateList.add(fightingState);
                }
                FightingEvents fightingEvents = new FightingEvents();
                fightingEvents.setAccepterlist(fightingStateList);
                this.NewEvents.add(fightingEvents);
            } else {//通用
                DataActionType.getActionById(2).analysisAction(myData, zl, type, this);
            }
            if (id2 >= 1001 && id2 <= 1100 && (boolean) b) {
                for (FightingEvents newEvent : this.NewEvents) {
                    if (newEvent.getAccepterlist() != null) {
                        for (FightingState fightingState : newEvent.getAccepterlist()) {
                            if (fightingState.getStartState().equals("法术攻击") && skill2.getSkillname().equals(fightingState.getEndState())) {
                                fightingState.setText("锦上添花#46");
                                break;
                            }
                        }
                    }
                }
            }
            if (myData.getStates() == 0 && id2 >= 1001 && id2 <= 1100) {//9809	月沉碧海	沧波状态下的单位在施放师门法术后有20%几率受到本次施放耗蓝{公式一}%的法力伤害。
                FightingSkill ycbh = myData.getAppendSkill(9809);
                if (ycbh != null && isV(20.0)) {
                    FightingState state = new FightingState();
                    ChangeFighting change = new ChangeFighting();
                    change.setChangemp((int) ((double) (-skill2.getSkillblue()) * ycbh.getSkillhurt() / 100.0));
                    state.setStartState(TypeUtil.JN);
                    myData.ChangeData(change, state);
                    FightingEvents events = new FightingEvents();
                    List<FightingState> Accepterlist3 = new ArrayList<>();
                    Accepterlist3.add(state);
                    events.setAccepterlist(Accepterlist3);
                    this.NewEvents.add(events);
                }
            }
            //释放天演册法术-10047有技能的75%几率清除附加状态
            List<ManData> datas = MixDeal.getjieshou(zl, skill2, myData, null, this);
            if (id2 == 10047) {
                FightingEvents qz = new FightingEvents();
                List<FightingState> dStates = new ArrayList<>();
                for (int l = this.fightingdata.size() - 1; l >= 0; --l) {
                    ManData data2 = this.fightingdata.get(l);
                    if (data2.getCamp() == myData.getCamp()) {
                        if (data2.xzstate("混乱") != null || data2.xzstate("封印") != null || data2.xzstate("昏睡") != null) {
                            double probability = skill2.getSkillhurt();
                            if (datas.get(0) != null) {
                                probability += skill2.getSkillgain();
                            }//主目标 也就是技能释放在谁身上为主目标
                            if (isV(probability)) {
                                FightingState daijia = new FightingState();
                                ChangeFighting changeFighting = new ChangeFighting();
                                changeFighting.setChangetype("清除异常状态");
                                data2.ChangeData(changeFighting, daijia);
                                changeFighting.setChangetype("抗性");
                                changeFighting.setChangesum(7);
                                data2.ChangeData(changeFighting, daijia);
                                daijia.setStartState("代价");
                                dStates.add(daijia);
                                qz.setAccepterlist(dStates);
                                this.NewEvents.add(qz);
                            }
                        } //东曦既驾
                        FightingSkill skill4 = myData.getSkillType(TypeUtil.TY_KX_DXJJ);
                        if (data2.xzstate(TypeUtil.FB_QW) != null || (data2.xzstate(TypeUtil.FB_JGE) != null && skill4 != null)) {
                            double probability = skill4.getSkillhurt();
                            if (datas.get(0) != null) {
                                probability += skill2.getSkillgain();
                            }//主目标 也就是技能释放在谁身上为主目标
                            if (isV(probability)) {
                                FightingState daijia2 = new FightingState();
                                ChangeFighting changeFighting2 = new ChangeFighting();
                                changeFighting2.setChangetype("清除法宝状态");
                                data2.ChangeData(changeFighting2, daijia2);
                                daijia2.setStartState("代价");
                                dStates.add(daijia2);
                                qz.setAccepterlist(dStates);
                                this.NewEvents.add(qz);
                            }
                        }
                    }
                }
            }
            if (id2 >= 1001 && id2 <= 1100) {
                this.XP_GLRX(myData);
            }
        }
    }

    /**
     * 判断给予系统提示
     */
    public void systemMsg(ManData data, List<FightingState> list, int type, FightingSkill skill) {
        if (data.getType() != 0 && data.getType() != 1) {
            return;
        }
        //情网 遗忘 舍生取义 灵魂封魔 金箍儿 回头是岸  遗忘 冷却 化无  法力 怨气  怒气		999  暗渡陈仓
        //0   1   2       3       4     5       6   7   8    9   9    9
        StringBuffer buffer = new StringBuffer((data.getType() == 0) ? "#W你" : ("#W你的召唤兽#R" + data.getManname() + "#Y"));
        if (type == 999) {
            buffer.append("#Y没有携带#G暗渡陈仓#Y内丹，无法释放#G一矢中的");
        } else if (type == 9) {
            if (skill.getSkillid() >= 5001 && skill.getSkillid() <= 5015) {
                buffer.append("#R怨气");
            } else if (skill.getSkillid() >= 9000 && skill.getSkillid() <= 10166) {
                buffer.append("#R怒气");
            } else {
                buffer.append("#R法力");
            }
            buffer.append("#W不足,无法施放#R");
            buffer.append(skill.getSkillname());
        } else {
            if (type > 20 && type <= 25) {
                buffer.append("#W你已被天赋压制");
            } else {
                buffer.append("#W处于#R");
                buffer.append((type == 0) ? "情网" : ((type == 2) ? "舍生取义" : ((type == 3) ? "灵魂封魔" : ((type == 4) ? "金箍儿" : ((type == 5) ? "回头是岸" : ((type == 7) ? "冷却" : ((type == 8) ? "化无" : ((type == 11) ? "偷梁换柱" : "遗忘"))))))));
                buffer.append("#W状态,");
            }
            if (skill != null || type == 21 || type == 22) {
                buffer.append("无法施放#R");
                buffer.append(skill.getSkillname());
            } else if (type == 1 || type == 25) {
                buffer.append("无法使用#G药品");
            } else if (type == 0 || type == 24) {
                buffer.append("无法进行#G物理攻击");
            } else if (type == 11) {
                buffer.append("无法进行#G物理攻击，吃药，施法");
            } else if (type == 2) {
                buffer.append("只能进行#G物理攻击");
            } else if (type == 23) {
                buffer.append("无法进行#G保护");
            }
        }
        FightingState fightingState = new FightingState(data.getCamp(), (data.getMan() < 5) ? data.getMan() : (data.getMan() - 5), "代价");
        fightingState.setMsg(buffer.toString());
        if (list == null) {
            FightingEvents events = new FightingEvents();
            list = new ArrayList<>();
            list.add(fightingState);
            events.setAccepterlist(list);
            this.NewEvents.add(events);
            return;
        }
        list.add(fightingState);
    }

    /**
     * 判断回头是岸
     */
    public void TZ_HTSA(ManData data) {
        int camp = data.getCamp();
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData data2 = this.fightingdata.get(i);
            if (camp != data2.getCamp() && data2.getStates() == 0) {
                FightingSkill skill = data2.getSkillType(TypeUtil.TZ_HTSA);//回头是岸概率
                if (skill != null && skill.getSkillhurt() > (double) Battlefield.random.nextInt(140)) {
                    data.addAddState(TypeUtil.TZ_HTSA, 0.0, 0.0, 2);
                    FightingEvents zl = new FightingEvents();
                    FightingState accepter = new FightingState();
                    accepter.setCamp(data.getCamp());
                    accepter.setMan(data.getMan());
                    List<FightingState> Accepterlist = new ArrayList<>();
                    accepter.setEndState_1(TypeUtil.TZ_HTSA);
                    accepter.setSkillskin("6025");
                    accepter.setStartState("代价");
                    Accepterlist.add(accepter);
                    zl.setAccepterlist(Accepterlist);
                    this.NewEvents.add(zl);
                    return;
                }
            }
        }
    }

    public void XP_GLRX(ManData data) {
        int camp = data.getCamp();
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData data2 = this.fightingdata.get(i);
            if (camp == data2.getCamp() && data2.getStates() == 0) {
                FightingSkill skill = data.getSkillType("甘霖瑞雪");
                if (skill != null && isV(20.0)) {
                    FightingEvents zl = new FightingEvents();
                    FightingState accepter = new FightingState();
                    accepter.setCamp(data.getCamp());
                    accepter.setMan(data.getMan());
                    List<FightingState> Accepterlist = new ArrayList<>();
                    accepter.setStartState("药");
                    int[] yao = new int[4];
                    if (isV(50.0)) {
                        yao[2] = 100;
                    } else {
                        yao[3] = 100;
                    }
                    ChangeFighting changeFighting = this.Typeyao(data2, yao);
                    data2.ChangeData(changeFighting, accepter);
                    Accepterlist.add(accepter);
                    zl.setAccepterlist(Accepterlist);
                    this.NewEvents.add(zl);
                    return;
                }
            }
        }
    }

    //判断是否为防御
    public boolean fypd(int camp, int man) {
        for (int i = 0; i < this.baohu.size(); ++i) {
            if (this.baohu.get(i).getOriginator().getStartState().equals("防御")) {
                FightingState state = this.baohu.get(i).getOriginator();
                if (state.getCamp() == camp && state.getMan() == man) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否为保护
     */
    public ManData bhpd(int camp, int man) {
        for (int i = 0; i < this.baohu.size(); ++i) {
            for (int p = this.fightingdata.size() - 1; p >= 0; --p) {
                if (TalentTool.getStateByLvl(this.fightingdata.get(p), 3) != null || this.fightingdata.get(p).xzstate("封印") != null || this.fightingdata.get(p).xzstate("昏睡") != null) {
                    return null;
                }
            }
            if (this.baohu.get(i).getOriginator().getStartState().equals("保护")) {
                FightingState state = this.baohu.get(i).getAccepterlist().get(0);
                if (state.getCamp() == camp && state.getMan() == man) {
                    int nomypath = this.Datapathhuo(this.baohu.get(i).getOriginator().getCamp(), this.baohu.get(i).getOriginator().getMan());
                    if (nomypath != -1) {
                        return this.fightingdata.get(nomypath);
                    }
                }
            }
        }
        return null;
    }

    public ManData bhpd1(int camp, int man) {
        for (int i = 0; i < this.baohu.size(); ++i) {
            for (int p = this.fightingdata.size() - 1; p >= 0; --p) {
                if (this.fightingdata.get(p).xzstate("封印") != null || ((ManData) this.fightingdata.get(p)).xzstate("昏睡") != null) {
                    return null;
                }
            }
            if (this.baohu.get(i).getOriginator().getStartState().equals("保护")) {
                FightingState state = this.baohu.get(i).getAccepterlist().get(0);
                if (state.getCamp() == camp && state.getMan() == man) {
                    this.baohu.remove(i);
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * 寻找指令中最近的闪现指令 没有就是0
     */
    public int xzsx(int camp, int man) {
        for (int i = 0; i < this.NewEvents.size(); ++i) {
            if (((FightingEvents) this.NewEvents.get(i)).getOriginator() != null && ((FightingEvents) this.NewEvents.get(i)).getOriginator().getStartState().equals("闪现") && (((FightingEvents) this.NewEvents.get(i)).getOriginator().getCamp() == camp && ((FightingEvents) this.NewEvents.get(i)).getOriginator().getMan() == man)) {
                return i + 1;
            }
        }
        return 0;
    }

    /**
     * 中毒
     */
    public ChangeFighting TypeHurtTotal(double vlaue) {
        ChangeFighting changeFighting = new ChangeFighting();
        changeFighting.setChangehp((int) (-vlaue));
        return changeFighting;
    }

    /**
     * 处理药品类型的事件
     */
    public ChangeFighting Typeyao(ManData data, int[] yao) {
        ChangeFighting changeFighting = new ChangeFighting();
        if (data.xzstate("归墟") != null) {
            return changeFighting;
        }
        //接收人的总生命
        int hp_z = data.getHp_z();
        //接收人的总蓝量
        int mp_z = data.getMp_z();
        int changehp = yao[0] + yao[2] * hp_z / 100;
        if (changehp > hp_z || changehp < 0) {
            changehp = hp_z;
        }
        int changemp = yao[1] + yao[3] * mp_z / 100;
        changeFighting.setChangehp(changehp);
        changeFighting.setChangemp(changemp);
        return changeFighting;
    }

    public ChangeFighting BBNoHurt(ManData myData, ManData nomyData, String type, FightingSkill fightingSkill) {
        ChangeFighting changeFighting = new ChangeFighting();
        if (type.equals("减人仙") || type.equals("减魔鬼")) {
            changeFighting.setChangetype(type);
            changeFighting.setChangesum(fightingSkill.getSkillcontinued());
            changeFighting.setChangevlaue(fightingSkill.getSkillhurt());
            return changeFighting;
        }
        if (type.equals(TypeUtil.BB_E_YHSS)) {
            if (isV(fightingSkill.getSkillgain())) {
                changeFighting.setChangetype("清除异常状态");
                changeFighting.setChangehp(nomyData.getHp_z() / 2);
                changeFighting.setChangemp(nomyData.getMp_z() / 2);
            }
            return changeFighting;
        } else {
            if (nomyData.xzstate(TypeUtil.TZ_MXJX) != null) {
                return changeFighting;
            }
            if (isV(fightingSkill.getSkillhitrate())) {
                changeFighting.setChangetype(type);
                changeFighting.setChangesum(fightingSkill.getSkillcontinued());
                changeFighting.setChangevlaue(fightingSkill.getSkillhurt());
            }
            return changeFighting;
        }
    }

    /**
     * 处理增益类型的事件
     */
    public ChangeFighting TypeGain(ManData data, String type, FightingSkill fightingSkill) {
        ChangeFighting changeFighting = new ChangeFighting();
        changeFighting.setChangetype(type);
        changeFighting.setChangesum(fightingSkill.getSkillcontinued());
        if (type.equals("庇护")) {
            changeFighting.setChangevlaue((double) data.getId());
            changeFighting.setChangevlaue2(25.0);
        } else if (type.equals("流连忘返")) {
            changeFighting.setChangevlaue(data.getId());
            changeFighting.setChangevlaue2(fightingSkill.getSkillhurt());
        } else {
            changeFighting.setChangevlaue(Calculation.getCalculation().mofa(fightingSkill.getSkillgain(), data, type));
            if (type.equals(TypeUtil.KX)) {
                changeFighting.setChangevlaue2(changeFighting.getChangevlaue() / 3.0);
            } else if (type.equals(TypeUtil.MH)) {
                changeFighting.setChangevlaue2(changeFighting.getChangevlaue() / 2.0);
            }
        }
        return changeFighting;
    }

    //获取逃跑的人数据
    public ManData taopao(int camp, int man) {
        for (int i = 0; i < this.fightingdata.size(); ++i) {
            if (this.fightingdata.get(i).getCamp() == camp && this.fightingdata.get(i).getMan() == man && this.fightingdata.get(i).getType() == 0) {
                return this.fightingdata.get(i);
            }
        }
        return null;
    }

    /**
     * 根据阵营和位置找到 数据存放位置
     */
    public int Datapath(int camp, int man) {
        for (int i = 0; i < this.fightingdata.size(); ++i) {
            if (this.fightingdata.get(i).getCamp() == camp && this.fightingdata.get(i).getMan() == man && this.fightingdata.get(i).getStates() != 2) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据阵营和位置找到和活的 数据存放位置
     */
    public int Datapathhuo(int camp, int man) {
        for (int i = 0; i < this.fightingdata.size(); ++i) {
            if (((ManData) this.fightingdata.get(i)).getCamp() == camp && ((ManData) this.fightingdata.get(i)).getMan() == man && ((ManData) this.fightingdata.get(i)).getStates() == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据阵营和位置找到和死的 数据存放位置
     */
    public int Datapathsi(int camp, int man) {
        for (int i = 0; i < this.fightingdata.size(); ++i) {
            if (((ManData) this.fightingdata.get(i)).getCamp() == camp && ((ManData) this.fightingdata.get(i)).getMan() == man && (((ManData) this.fightingdata.get(i)).getStates() == 2 || ((ManData) this.fightingdata.get(i)).getStates() == 1)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据阵营和位置找到 回合数据存放位置
     */
    public int RoundDatapath(int camp, int man) {
        for (int i = 0; i < this.Events.size(); ++i) {
            if (this.Events.get(i).getOriginator().getCamp() == camp && this.Events.get(i).getOriginator().getMan() == man) {
                return i;
            }
        }
        return -1;
    }

    //根据id获取对应野怪
    public ManData getMonster(int id) {
        if (id == 0) {//id为0时找血最少的
            int t = 0;// 血最少的单位
            int hp = 0;
            for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
                ManData data = this.fightingdata.get(i);
                if (hp == 0 || data.getHp() < hp) {
                    t = i;
                    hp = data.getHp();
                }
            }
            return this.fightingdata.get(t);
        } else {
            for (int j = this.fightingdata.size() - 1; j >= 0; --j) {
                ManData data2 = this.fightingdata.get(j);
                if (data2.getType() == 2 && data2.getId() == id) {
                    return data2;
                }
            }
            return null;
        }
    }

    public PathPoint instructionspath2(int camp, int noskill, ManData data) {
        // 一个x存阵营 一个y存位置
        int size = 0;
        while (++size <= 999) {
            int path = Battlefield.random.nextInt(this.fightingdata.size());
            ManData nodata = this.fightingdata.get(path);
            if (camp != -1) {
                if (noskill == 1) {
                    if (nodata.getCamp() == camp) {
                        return new PathPoint(nodata.getCamp(), nodata.getMan());
                    }
                } else if (nodata.getCamp() == camp && nodata != data) {
                    return new PathPoint(nodata.getCamp(), nodata.getMan());
                }
            } else {
                if (noskill == 1) {
                    return new PathPoint(nodata.getCamp(), nodata.getMan());
                }
                if (nodata != data) {
                    return new PathPoint(nodata.getCamp(), nodata.getMan());
                }
            }
        }
        return new PathPoint(data.getCamp(), data.getMan());
    }

    /**
     * 找到敌方阵营的编号
     */
    public int nomy(int camp) {
        for (int i = 0; i < this.fightingdata.size(); ++i) {
            if (this.fightingdata.get(i).getCamp() != camp) {
                return this.fightingdata.get(i).getCamp();
            }
        }
        return -1;
    }

    /**
     * 根据当前状态能生成的攻击指令
     */
    public FightingSkill mayinstructions2(ManData data) {
        if (data.getType() != 2 || data.getStates() != 0) {
            return null;
        }
        if (isV((double) data.getSr()) && data.xzstate("物理狂暴") == null) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < data.getSkills().size(); ++i) {
                FightingSkill skill = data.getSkills().get(i);
                if (skill.getSkillbeidong() == 0 && !data.SkillCooling(skill.getSkillid())) {
                    list.add(i);
                }
            }
            if (list.size() == 0) {
                return null;
            }
            return data.getSkills().get(list.get(Battlefield.random.nextInt(list.size())));
        } else {
            return null;
        }
    }

    public FightingSkill mayinstructions2(ManData data, Integer skillId) {
        if (data.getType() != 2 || data.getStates() != 0) {
            return null;
        }
        if (isV((double) data.getSr()) && data.xzstate("物理狂暴") == null) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < data.getSkills().size(); ++i) {
                FightingSkill skill = (FightingSkill) data.getSkills().get(i);
                if (skill.getSkillbeidong() == 0 && !data.SkillCooling(skill.getSkillid()) && skill.getSkillid() != (int) skillId) {
                    list.add(i);
                }
            }
            if (list.size() == 0) {
                return null;
            }
            return (FightingSkill) data.getSkills().get((int) list.get(Battlefield.random.nextInt(list.size())));
        } else {
            return null;
        }
    }

    public void lingbao() {
        this.lingbao1 = null;
        this.lingbao2 = null;
        BattleStatePolicy.lingbao = false;
        for (ManData manData : this.fightingdata) {
            if (manData.getType() == 3) {
                if (manData.getCamp() == 1) {
                    if (this.lingbao1 == null) {
                        this.lingbao1 = new ArrayList<>();
                    }
                    this.lingbao1.add(manData);
                } else {
                    if (this.lingbao2 == null) {
                        this.lingbao2 = new ArrayList<>();
                    }
                    this.lingbao2.add(manData);
                }
            }
        }
        this.ling1 = this.getling(this.lingbao1);
        if (this.ling1 != null) {
            ManData parents = this.getLingParents(this.ling1);
            if (parents.getStates() != 0) {
                FightingSkill djzz = this.ling1.getSkillName("低级挣扎");
                if (djzz != null) {
                    djzz.setSkilllvl(33);
                } else if (djzz == null) {
                    djzz = this.ling1.getSkillName("高级挣扎");
                    if (djzz != null) {
                        djzz.setSkilllvl(50);
                    }
                    if (djzz != null) {
                        if (new Random().nextInt(100) <= djzz.getSkilllvl()) {
                            this.ling1 = null;
                        }
                    } else {
                        this.ling1 = null;
                    }
                }
            }
        }
        this.ling2 = this.getling(this.lingbao2);
        if (this.ling2 != null) {
            ManData parents = this.getLingParents(this.ling2);
            if (parents.getStates() != 0) {
                FightingSkill djzz = this.ling2.getSkillName("低级挣扎");
                if (djzz != null) {
                    djzz.setSkilllvl(33);
                } else if (djzz == null) {
                    djzz = this.ling2.getSkillName("高级挣扎");
                    if (djzz != null) {
                        djzz.setSkilllvl(50);
                    }
                    if (djzz != null) {
                        if (new Random().nextInt(100) <= djzz.getSkilllvl()) {
                            this.ling2 = null;
                        }
                    } else {
                        this.ling2 = null;
                    }
                }
            }
        }
        if (this.ling1 != null && this.ling1.xzstate(TypeUtil.TZ_SGJQ) == null) {
            this.ful1 = Lingbao.getHelp(this.fightingdata, this.ling1);
            this.skill1 = this.ling1.getlingskill(this.ful1.size() + 1, this.BattleType);
            if (this.skill1 != null) {
                this.zltype1 = 0;
                int id = this.skill1.getSkillid();
                if (id >= 3001 && id <= 3010) {
                    this.zltype1 = 1;
                } else if (id == 3011 || id == 3012) {
                    this.zltype1 = 2;
                } else if ((id >= 3013 && id <= 3023) || id == 3032) {
                    this.zltype1 = 3;
                } else if (id == 3033) {
                    this.zltype1 = 4;
                } else if (id == 3026) {
                    this.zltype1 = 5;
                } else if (id >= 3029 && id <= 3031) {
                    this.zltype1 = 6;
                } else if (id == 3024) {
                    this.zltype1 = 7;
                } else if (id == 3027) {
                    this.zltype1 = 8;
                } else if (id == 3028) {
                    this.zltype1 = 11;
                } else if (id == 3025) {
                    this.zltype1 = 9;
                }
            }
        }
        if (this.ling2 != null && this.ling2.xzstate(TypeUtil.TZ_SGJQ) == null) {
            this.ful2 = Lingbao.getHelp(this.fightingdata, this.ling2);
            this.skill2 = this.ling2.getlingskill(this.ful2.size() + 1, this.BattleType);
            if (this.skill2 != null) {
                this.zltype2 = 0;
                int id = this.skill2.getSkillid();
                if (id >= 3001 && id <= 3010) {
                    this.zltype2 = 1;
                } else if (id == 3011 || id == 3012) {
                    this.zltype2 = 2;
                } else if ((id >= 3013 && id <= 3023) || id == 3032) {
                    this.zltype2 = 3;
                } else if (id == 3033) {
                    this.zltype2 = 4;
                } else if (id == 3026) {
                    this.zltype2 = 5;
                } else if (id >= 3029 && id <= 3031) {
                    this.zltype2 = 6;
                } else if (id == 3024) {
                    this.zltype2 = 7;
                } else if (id == 3027) {
                    this.zltype2 = 8;
                } else if (id == 3028) {
                    this.zltype2 = 11;
                } else if (id == 3025) {
                    this.zltype2 = 9;
                }
            }
        }
        try {
            StringBuffer buffer1 = new StringBuffer();
            StringBuffer buffer2 = new StringBuffer();
            if (this.ling1 != null && this.zltype1 != 0 && this.skill1 != null) {
                buffer1.append(this.ling1.getCamp() + "-" + this.ling1.getMan());
                if (this.ful1 != null) {
                    for (ManData manData2 : this.ful1) {
                        buffer1.append("|");
                        buffer1.append(manData2.getCamp() + "-" + manData2.getMan());
                    }
                }
            }
            if (this.ling2 != null && this.zltype2 != 0 && this.skill2 != null) {
                buffer2.append(this.ling2.getCamp() + "-" + this.ling2.getMan());
                if (this.ful2 != null) {
                    for (ManData manData2 : this.ful2) {
                        buffer2.append("|");
                        buffer2.append(manData2.getCamp() + "-" + manData2.getMan());
                    }
                }
            }
            if (buffer1.length() > 0 || buffer2.length() > 0) {
                for (String string : this.battleData.getParticipantlist()) {
                    if (buffer1.length() > 0) {
                        SendMessage.sendMessageByRoleName(string, Agreement.FithtlingbaoAgreement(buffer1.toString()));
                    }
                    if (buffer2.length() > 0) {
                        SendMessage.sendMessageByRoleName(string, Agreement.FithtlingbaoAgreement(buffer2.toString()));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ManData> getFu(ManData data) {
        if (data.getCamp() == 1) {
            return this.ful1;
        }
        return this.ful2;
    }

    public FightingSkill getBaoSkill(ManData data) {
        if (data.getCamp() == 1) {
            return this.skill1;
        }
        return this.skill2;
    }

    public ManData fu(ManData data) {
        for (ManData manData : this.fightingdata) {
            if (manData.getLings() != null) {
                for (FightingLingbao lingbao : manData.getLings()) {
                    if (lingbao.getPlay() == 1 && lingbao.getLingbaonData() == data) {
                        return manData;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 等待出手序列
     */
    public void waitSell() {
        List<ManData> lingbao1 = null;
        List<ManData> lingbao2 = null;
        this.waitList.clear();
        boolean isBB = PK_MixDeal.isBB(this.BattleType);
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData data = (ManData) this.fightingdata.get(i);
            if (data.getType() != 4 && data.getStates() != 2) {
                if (data.getStates() == 1 && data.getType() == 0) {
                    ManData pet = this.getSeek(data, 1);
                    if (pet == null || pet.getStates() == 1) {
                        FightingEvents events = this.getWaitEvent(data.getCamp(), data.getMan());
                        if (events == null) {
                            continue;
                        } else {
                            String type = events.getOriginator().getStartState();
                            //防御 召唤 逃跑
                            if (!type.equals("防御") && !type.equals("逃跑") && !type.startsWith("召唤")) {
                                continue;
                            }
                        }
                    }
                } else if (data.getStates() == 1 && data.getType() != 0 && data.getType() != 2) {
                    continue;
                }
                if (data.getType() == 3) {
                    if (data.getCamp() == 1) {
                        if (lingbao1 == null) {
                            lingbao1 = new ArrayList<>();
                        }
                        lingbao1.add(data);
                    } else {
                        if (lingbao2 == null) {
                            lingbao2 = new ArrayList<>();
                        }
                        lingbao2.add(data);
                    }
                } else if (!isBB || data.getMan() >= 5) {
                    this.waitList.add(data);
                }
            }
        }
        try {
            if (this.ling1 != null) {
                this.waitList.add(this.ling1);
            }
        } catch (Exception e) {
            this.waitList.add(this.ling1);
        }
        try {
            if (this.ling2 != null) {
                this.waitList.add(this.ling2);
            }
        } catch (Exception e) {
            this.waitList.add(this.ling2);
        }
    }

    /**
     * 判断灵宝中出手的人
     */
    public ManData getling(List<ManData> lingbao1) {
        if (lingbao1 == null) {
            return null;
        }
        for (int i = lingbao1.size() - 1; i >= 0; --i) {
            ManData data = lingbao1.get(i);
            if (!this.chufa(data.getHuoyue())) {
                lingbao1.remove(i);
            }
        }
        if (lingbao1.size() == 0) {
            return null;
        }
        return lingbao1.get(Battlefield.random.nextInt(lingbao1.size()));
    }

    /**
     * 判断灵宝是否触发
     */
    public boolean chufa(double huoyue) {
        return (double) Battlefield.random.nextInt(10000) < huoyue;
    }

    /**
     * 初始化归类
     */
    public void guilei() {
        boolean isBB = PK_MixDeal.isBB(this.BattleType);
        List<ManData> haizi = null;
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData data = this.fightingdata.get(i);
            if (data.getType() == 4 && !isBB) {
                if (haizi == null) {
                    haizi = new ArrayList<>();
                }
                haizi.add(data);
            }
            data.setSp2(Battlefield.random.nextInt(this.chaosSp));
            for (String xin : data.yinian) {
                if (xin.equals("腾云")) {
                    data.setSp2(data.getSp2() + 300);
                    break;
                }
            }
            for (String xin : data.yinian) {
                if (xin.equals("追云")) {
                    data.ljv += 16;
                    break;
                }
            }
            for (String xin : data.yinian) {
                if (xin.equals("踏云")) {
                    data.zm += 16.0;
                    break;
                }
            }
            for (String xin : data.yinian) {
                if (xin.equals("潜云")) {
                    data.pwljl += 50.0;
                    break;
                }
            }
            for (String xin : data.yinian) {
                if (xin.equals("纵云")) {
                    data.pwlcd += 50.0;
                    break;
                }
            }
        }
        if (haizi != null) {
            List<FightingState> fightingStates = null;
            for (int j = haizi.size() - 1; j >= 0; --j) {
                ManData child = haizi.get(j);
                ManData data2 = this.getParents(child);
                if (data2 != null) {
                    FightingSkill skill = child.getChildSkill("加敏序");
                    if (skill != null) {
                        if (fightingStates == null) {
                            fightingStates = new ArrayList<>();
                        }
                        fightingStates.add(MixDeal.getChildSkill(child, skill.getSkillname()));
                        data2.setSp2((int) skill.getSkillhurt());
                        MixDeal.get(false, data2, 0, this.nomy(data2.getCamp()), 1, 0, 0, 0, 1, 6, this, 1);
                    }
                    skill = child.getChildSkill("免疫物理");
                    if (skill != null) {
                        if (fightingStates == null) {
                            fightingStates = new ArrayList<>();
                        }
                        fightingStates.add(MixDeal.getChildSkill(child, skill.getSkillname()));
                        AddState addState = new AddState();
                        addState.setSurplus(1);
                        addState.setStatename(skill.getSkilltype());
                        addState.setStateEffect(skill.getSkillhurt());
                        AddState addState2 = new AddState();
                        addState2.setSurplus(1);
                        addState2.setStatename("抗普通攻击");
                        addState2.setStateEffect(skill.getSkillgain());
                        data2.getAddStates().add(addState);
                        data2.getAddStates().add(addState2);
                    }
                }
            }
            if (fightingStates != null) {
                FightingEvents ksevents = new FightingEvents();
                ksevents.setCurrentRound(this.CurrentRound);
                ksevents.setAccepterlist(fightingStates);
                this.NewEvents.add(ksevents);
            }
        }
        for (int i = this.Events.size() - 1; i >= 0; --i) {
            FightingEvents event = this.Events.get(i);
            if (event == null) {
                this.Events.remove(i);
            } else {
                FightingState org = event.getOriginator();
                if (org.getStartState().equals("防御")) {
                    this.fangyu.add(new PathPoint(org.getMan(), org.getCamp()));
                } else if (org.getStartState().equals("保护")) {
                    this.baohu.add(event);
                }// 9110|知盈处虚|牺牲自己（4%*等级）的速度，使本回合内已被封印命中的单位的速度降低（4%*等级），持续3回合，回合开始前使用。（仅在与玩家之间战斗有效。）
                // 9389|流风回雪|回合开始前使用,使用后清除身上所有负面状态（等级2并回复自身血法上限的60%；等级三同时免疫物理攻击，持续1回合；等级四改为同时免疫所有攻击，持续1回合
                // 等级5改为同时免疫所有的攻击和控制，持续1回合）
                else if (org.getEndState() != null && (org.getEndState().equals("清心静气") || org.getEndState().equals("凝神一击") || org.getEndState().equals("气吞山河") || org.getEndState().equals("行气如虹") || org.getEndState().equals("气聚神凝") || org.getEndState().equals("神龙摆尾") || org.getEndState().equals("知盈处虚") || org.getEndState().equals("流风回雪") || org.getEndState().equals("逆鳞") || org.getEndState().equals("天罡八卦"))) {
                    this.Events.remove(i);
                    this.erwai.add(event);
                }
            }
        }
    }

    /**
     * 敏捷度排序
     */
    public FightingEvents SpBubble() {
        List<PathPoint> ren = new ArrayList<>();
        for (int i = this.waitList.size() - 1; i >= 0; --i) {
            ManData data = this.waitList.get(i);
            if (data.getStates() != 2) {
                ren.add(new PathPoint(i, data.getSp()));
            }
        }
        for (int i = 0; i < ren.size() - 1; ++i) {
            for (int j = 1; j < ren.size() - i; ++j) {
                if (ren.get(j - 1).getY() < ren.get(j).getY()) {
                    PathPoint a = ren.get(j - 1);
                    ren.set(j - 1, ren.get(j));
                    ren.set(j, a);
                }
            }
        }
        int i = 0;
        for (i = 0; i < ren.size(); i++) {
            ManData data = this.waitList.get(ren.get(i).getX());
            FightingEvents events = getWaitEvent(data.getCamp(), data.getMan());
            if (events != null) {
                if (events.getOriginator() != null) {
                    String type = events.getOriginator().getStartState();
                    if (data.xzstate("昏睡") != null && !type.equals("药") && !type.equals("召回") && type.indexOf("召唤") == -1 && !type.equals("逃跑"))
                        continue;
                    if (data.xzstate("封印") != null && !type.equals("召回") && !type.equals("逃跑")) {
                        data.setFightingEvents(events);
                        this.Events.remove(events);
                        continue;
                    }
                }
                this.Events.remove(events);
            } else {
                if (data.xzstate("封印") != null || data.xzstate("昏睡") != null)
                    continue;
                if (data.getFightingEvents() != null) {
                    data.setFightingEvents(null);
                    this.waitList.remove(ren.get(i).getX());
                    return data.getFightingEvents();
                }
                events = createEvent(data);
            }
            this.waitList.remove(ren.get(i).getX());
            return events;
        }
        return null;
    }

    /**
     * 获取出手的指令
     */
    public FightingEvents getWaitEvent(int camp, int man) {
        for (int i = this.Events.size() - 1; i >= 0; --i) {
            FightingEvents events = this.Events.get(i);
            if (camp == events.getOriginator().getCamp() && man == events.getOriginator().getMan()) {
                return events;
            }
        }
        try {
            int wei = this.Datapathhuo(camp, man);
            ManData manData = this.fightingdata.get(wei);
            if (manData.getFightingEvents() != null) {
                return manData.getFightingEvents();
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    /**
     * 单人竞技场智能施法处理
     */
    public void oneArenaRole(FightingEvents events, ManData data) {
        if (data.getStates() != 0) {//死亡状态普通攻击吧
            FightingState originator = events.getOriginator();
            originator.setStartState(TypeUtil.PTGJ);
            PathPoint pathPoint = this.instructionspath2(this.nomy(data.getCamp()), 0, data);
            List<FightingState> accepterlist = new ArrayList<>();
            FightingState accepter = new FightingState();
            accepter.setCamp(pathPoint.getX());
            accepter.setMan(pathPoint.getY());
            accepterlist.add(accepter);
            events.setAccepterlist(accepterlist);
            return;
        }
        //人物判断  一直释放加成最高的师门系技能
        FightingSkill fightingSkill = null;
        ManData manData = null;
        if (data.getType() == 0) {
            int xl = 0;
            for (int i = 0; i < data.getSkills().size(); ++i) {
                FightingSkill skill = (FightingSkill) data.getSkills().get(i);
                if (skill.getSkillbeidong() == 0 && skill.getSkillblue() <= data.getMp() && skill.getSkillid() > 1000 && skill.getSkillid() <= 1100 && !data.SkillCooling(skill.getSkillid())) {
                    if (fightingSkill == null) {
                        fightingSkill = skill;
                        xl = (skill.getSkillid() - 1001) / 5;
                    } else {
                        int oxl = (skill.getSkillid() - 1001) / 5;
                        if (xl != oxl) {
                            if (data.getQuality().isSMXL(oxl, xl)) {//强力系列替换
                                fightingSkill = skill;
                                xl = oxl;
                            }
                        } else if (skill.getSkillid() > fightingSkill.getSkillid()) {
                            fightingSkill = skill;
                        }
                    }
                }
            }
        } else {//召唤兽判断
            if (this.CurrentRound == 1) {
                for (int j = 0; j < data.getSkills().size(); ++j) {
                    FightingSkill skill2 = data.getSkills().get(j);
                    if (skill2.getSkillbeidong() == 0 && skill2.getSkillblue() <= data.getMp()) {
                        int id = skill2.getSkillid();
                        if (((id >= 1600 && id <= 1604) || id == 1607 || id == 1200) && !data.SkillCooling(skill2.getSkillid())) {
                            fightingSkill = skill2;
                            break;
                        }
                    }
                }
            }
            if (fightingSkill != null) {
                //判断自己阵营中是否有人处于死亡状态  判断是否有拉人技能  1606 1611
                //判断自己阵营中是否有人处于异常状态  判断是否有解除异常技能 1608 1612
                FightingSkill skillSW = null;
                FightingSkill skillYC = null;
                for (int k = 0; k < data.getSkills().size(); ++k) {
                    FightingSkill skill3 = data.getSkills().get(k);
                    if (skill3.getSkillbeidong() == 0 && skill3.getSkillblue() <= data.getMp()) {
                        int id2 = skill3.getSkillid();
                        if ((id2 == 1606 || id2 == 1608 || id2 == 1611 || id2 == 1612) && !data.SkillCooling(skill3.getSkillid())) {
                            if (id2 == 1606 || id2 == 1611) {
                                skillSW = skill3;
                            } else {
                                skillYC = skill3;
                            }
                        }
                    }
                }
                ManData SW = null;
                ManData YC = null;
                if (skillSW != null || skillYC != null) {
                    for (int l = 0; l < this.fightingdata.size(); ++l) {
                        ManData data2 = (ManData) this.fightingdata.get(l);
                        if (data2.getCamp() == data.getCamp() && data2.getStates() != 2) {
                            if (data2.getStates() == 1) {
                                if (skillSW != null) {
                                    SW = data2;
                                    break;
                                }
                            } else if (skillYC != null && YC == null && (data2.xzstate("封印") != null || data2.xzstate("混乱") != null || data2.xzstate("昏睡") != null || data2.xzstate("遗忘") != null)) {
                                YC = data2;
                            }
                        }
                    }
                }
                if (SW != null) {
                    manData = SW;
                    fightingSkill = skillSW;
                } else if (YC != null) {
                    manData = YC;
                    fightingSkill = skillYC;
                }
            }
        }
        FightingState originator2 = events.getOriginator();
        PathPoint pathPoint2 = null;
        if (manData != null) {
            pathPoint2 = new PathPoint(manData.getCamp(), manData.getMan());
        } else if (fightingSkill != null) {
            originator2.setStartState(TypeUtil.JN);
            originator2.setEndState(fightingSkill.getSkillname());
            if (!fightingSkill.Goodskill()) {//随机敌方阵营
                pathPoint2 = this.instructionspath2(this.nomy(data.getCamp()), 1, data);
            } else {
                pathPoint2 = this.instructionspath2(data.getCamp(), 1, data);
            }
        } else {
            originator2.setStartState(TypeUtil.PTGJ);
            pathPoint2 = this.instructionspath2(this.nomy(data.getCamp()), 0, data);
        }
        List<FightingState> accepterlist2 = new ArrayList<>();
        FightingState accepter2 = new FightingState();
        accepter2.setCamp(pathPoint2.getX());
        accepter2.setMan(pathPoint2.getY());
        accepterlist2.add(accepter2);
        events.setAccepterlist(accepterlist2);
    }

    /**
     * 武神山智能施法处理
     */
    public void wssBattle(FightingEvents events, ManData data) {
        if (data.getStates() != 0) {//死亡状态普通攻击吧
            FightingState originator = events.getOriginator();
            originator.setStartState(TypeUtil.PTGJ);
            PathPoint pathPoint = this.instructionspath2(this.nomy(data.getCamp()), 0, data);
            List<FightingState> accepterlist = new ArrayList<>();
            FightingState accepter = new FightingState();
            accepter.setCamp(pathPoint.getX());
            accepter.setMan(pathPoint.getY());
            accepterlist.add(accepter);
            events.setAccepterlist(accepterlist);
            return;
        }//人物判断  一直释放加成最高的师门系技能
        FightingSkill fightingSkill = null;
        ManData manData = null;
        if (data.getMan() <= 4) {
            int xl = 0;
            for (int i = 0; i < data.getSkills().size(); ++i) {
                FightingSkill skill = (FightingSkill) data.getSkills().get(i);
                if (skill.getSkillbeidong() == 0 && skill.getSkillblue() <= data.getMp() && skill.getSkillid() > 1000 && skill.getSkillid() <= 1100 && !data.SkillCooling(skill.getSkillid())) {
                    if (fightingSkill == null) {
                        fightingSkill = skill;
                        xl = (skill.getSkillid() - 1001) / 5;
                    } else {
                        int oxl = (skill.getSkillid() - 1001) / 5;
                        if (xl != oxl) {
                            if (data.getQuality().isSMXL(oxl, xl)) {
                                fightingSkill = skill;
                                xl = oxl;
                            }
                        } else if (skill.getSkillid() > fightingSkill.getSkillid()) {
                            fightingSkill = skill;
                        }
                    }
                }
            }
        } else {
            List<FightingSkill> listskill = new ArrayList<>();
            for (int i = 0; i < data.getSkills().size(); ++i) {
                FightingSkill skill = (FightingSkill) data.getSkills().get(i);
                if (skill.getSkillbeidong() == 0 && skill.getSkillblue() <= data.getMp()) {
                    int id = skill.getSkillid();
                    if (this.CurrentRound == 1) {
                        if ((id < 1600 || id > 1604) && id != 1607) {
                            if (id == 1200) {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    }
                    if (!data.SkillCooling(skill.getSkillid())) {
                        if (skill.getSkillsum() == 5) {
                            skill.setSkillsum(2);
                        }
                        listskill.add(skill);
                    }
                }
            }
            if (listskill != null && listskill.size() > 0) {
                fightingSkill = listskill.get(Battlefield.random.nextInt(listskill.size()));
            }
            if (fightingSkill != null) {
                FightingSkill skillSW = null;
                FightingSkill skillYC = null;
                for (int j = 0; j < data.getSkills().size(); ++j) {
                    FightingSkill skill2 = data.getSkills().get(j);
                    if (skill2.getSkillbeidong() == 0 && skill2.getSkillblue() <= data.getMp()) {
                        int id2 = skill2.getSkillid();
                        if ((id2 == 1606 || id2 == 1608 || id2 == 1611 || id2 == 1612) && !data.SkillCooling(skill2.getSkillid())) {
                            if (id2 == 1606 || id2 == 1611) {
                                skillSW = skill2;
                            } else {
                                skillYC = skill2;
                            }
                        }
                    }
                }
                ManData SW = null;
                ManData YC = null;
                if (skillSW != null || skillYC != null) {
                    for (int k = 0; k < this.fightingdata.size(); ++k) {
                        ManData data2 = this.fightingdata.get(k);
                        if (data2.getCamp() == data.getCamp() && data2.getStates() != 2) {
                            if (data2.getStates() == 1) {
                                if (skillSW != null) {
                                    SW = data2;
                                    break;
                                }
                            } else if (skillYC != null && YC == null && (data2.xzstate("封印") != null || data2.xzstate("混乱") != null || data2.xzstate("昏睡") != null || data2.xzstate("遗忘") != null)) {
                                YC = data2;
                            }
                        }
                    }
                }
                if (SW != null) {
                    manData = SW;
                    fightingSkill = skillSW;
                } else if (YC != null) {
                    manData = YC;
                    fightingSkill = skillYC;
                }
            }
        }
        FightingState originator2 = events.getOriginator();
        PathPoint pathPoint2 = null;
        if (manData != null) {
            pathPoint2 = new PathPoint(manData.getCamp(), manData.getMan());
        } else if (fightingSkill != null) {
            originator2.setStartState(TypeUtil.JN);
            originator2.setEndState(fightingSkill.getSkillname());
            if (!fightingSkill.Goodskill()) {
                pathPoint2 = this.instructionspath2(this.nomy(data.getCamp()), 1, data);
            } else {
                pathPoint2 = this.instructionspath2(data.getCamp(), 1, data);
            }
        } else {
            originator2.setStartState(TypeUtil.PTGJ);
            pathPoint2 = this.instructionspath2(this.nomy(data.getCamp()), 0, data);
        }
        List<FightingState> accepterlist2 = new ArrayList<>();
        FightingState accepter2 = new FightingState();
        accepter2.setCamp(pathPoint2.getX());
        accepter2.setMan(pathPoint2.getY());
        accepterlist2.add(accepter2);
        events.setAccepterlist(accepterlist2);
    }

    public FightingEvents createEvent(ManData data) {
        FightingEvents events = new FightingEvents();
        FightingState originator = new FightingState();
        originator.setCamp(data.getCamp());
        originator.setMan(data.getMan());
        events.setOriginator(originator);
        if (BattleType == 4 && data.getStates() == 0 && data.getType() == 2) {
//            if (CurrentRound >= 3 && random.nextInt(100) < 70) {
            if (random.nextInt(100) < 10) {
                originator.setStartState("逃跑");
            } else {
                originator.setStartState("偷钱");
            }
            return events;
        } else if (BattleType == 101 && data.getCamp() == 2) {
            if (data.getType() == 0 || data.getType() == 1) {//智能指令
                oneArenaRole(events, data);
                return events;
            }
        } else if (BattleType == 886 && data.getCamp() == 0) {
            if (data.getType() == 2) {//智能指令
                wssBattle(events, data);
                return events;
            }
        } else if (data.isPalPet && data.getZs() >= 1) {
            if (data.getType() == 2) {//伙伴召唤兽智能指令
                wssBattle(events, data);
                return events;
            }
        }
        AI ai = AI_MixDeal.AI_ZL(this, data);
        if (ai != null && !(ai.getType() == AI.AI_TYPE_SKILL || ai.getType() == AI.AI_TYPE_AUTOMATE || ai.getType() == AI.AI_TYPE_BS || ai.getType() == AI.AI_TYPE_ZH || ai.getType() == AI.AI_ZDGJ || ai.getType() == AI.AI_TYPE_MF || ai.getType() == AI.AI_NODEATH || ai.getType() == AI.AI_SUBSTATE)) {
            if (ai.getType() == AI.AI_TYPE_FY) {
                originator.setStartState("防御");
            } else if (ai.getType() == AI.AI_TYPE_TP) {
                originator.setStartState("逃跑");
            } else if (ai.getType() == AI.AI_TYPE_SWBS) {
                if (ai.getAiConditions().get(0).getB()) {
                    int ndXS1 = battleData.getBattlefield().ndXS;
                    FightingEvents fightingEvents = new FightingEvents();
                    Double XS;
                    if (ndXS1 == 0) {
                        XS = 1.0;
                    } else if (ndXS1 == 1) {
                        XS = 1.8;
                    } else if (ndXS1 == 2) {
                        XS = 2.8;
                    } else if (ndXS1 == 3) {
                        XS = 4.0;
                    } else {
                        XS = 5.4;
                    }
//                List<FightingState> Accepterlist = new ArrayList<>();
//                fightingEvents = new FightingEvents();
//                FightingState org1 = new FightingState();
//                org1.setCamp(data.getCamp());
//                org1.setMan(data.getMan());
//                org1.setStartState("法术攻击");
////                    org1.setText("兄弟们，揍人了#47");
//                Accepterlist.add(org1);
//                fightingEvents.setAccepterlist(Accepterlist);
//                this.NewEvents.add(fightingEvents);
                    ManData data2 = getMonster(ai.getAiConditions().get(0).getY());
                    fightingEvents = new FightingEvents();
                    FightingState fightingState = new FightingState();
                    fightingState.setStartState("召回");
                    fightingState.setCamp(data2.getCamp());
                    fightingState.setMan(data2.getMan());
                    data2.setStates(2);
                    fightingEvents.setOriginator(fightingState);
                    NewEvents.add(fightingEvents);
                    MixDeal.zhaohui(data2, fightingState, this);
                    fightingdata.remove(data2);
                    String monsterId = ai.getValue() + "";

                    ManData test = BattleThreadPool.loadCreep(monsterId, "测试", battleData.getMaxLvl(), battleData, XS, data2.getMan());
                    zhaohuan(test, "召唤", this, "40001");
                } else {
                    ai.getAiConditions().get(0).setB(true);
                }

            } else if (ai.getType() == AI.AI_TYPE_HELP) {
                int YCamp = -1;
                int Yman = -1;
                ManData Ydata = getMonster(ai.getMan());
                if (Ydata != null) {
                    YCamp = Ydata.getCamp();
                    Yman = Ydata.getMan();
                } else {
                    PathPoint pathPoint = instructionspath2(data.getCamp(), 1, data);
                    YCamp = pathPoint.getX();
                    Yman = pathPoint.getY();
                }
                originator.setStartState("药");
                originator.setEndState("HP%=" + ai.getValue());
                List<FightingState> accepterlist = new ArrayList<>();
                FightingState accepter = new FightingState();
                accepter.setCamp(YCamp);
                accepter.setMan(Yman);
                accepterlist.add(accepter);
                events.setAccepterlist(accepterlist);
            }
            return events;
        }

        Boolean isSKill = false;

//        if (ai!= null &&ai.getType() == AI.AI_TYPE_BS) {
//            Boolean isSw = true;
//            for (AICondition aiCondition : ai.getAiConditions()) {
//                if (aiCondition.getX() == 1010) {
//                    isSw = false;
//                }
//            }
//            if (isSw) {
//                int ndXS1 = battleData.getBattlefield().ndXS;
//                FightingEvents fightingEvents = new FightingEvents();
//                Double XS;
//                if (ndXS1 == 1) {
//                    XS = 1.8;
//                } else if (ndXS1 == 2) {
//                    XS = 2.8;
//                } else if (ndXS1 == 3) {
//                    XS = 4.0;
//                } else {
//                    XS = 5.4;
//                }
////                List<FightingState> Accepterlist = new ArrayList<>();
////                fightingEvents = new FightingEvents();
////                FightingState org1 = new FightingState();
////                org1.setCamp(data.getCamp());
////                org1.setMan(data.getMan());
////                org1.setStartState("法术攻击");
//////                    org1.setText("兄弟们，揍人了#47");
////                Accepterlist.add(org1);
////                fightingEvents.setAccepterlist(Accepterlist);
////                this.NewEvents.add(fightingEvents);
//                fightingEvents = new FightingEvents();
//                FightingState fightingState = new FightingState();
//                fightingState.setStartState("召回");
//                fightingState.setCamp(data.getCamp());
//                fightingState.setMan(data.getMan());
//                data.setStates(2);
//                fightingEvents.setOriginator(fightingState);
//                NewEvents.add(fightingEvents);
//                MixDeal.zhaohui(data, fightingState, this);
//                fightingdata.remove(data);
//                String monsterId = ai.getValue() + "";
//
//                ManData test = BattleThreadPool.loadCreep(monsterId, "测试", battleData.getMaxLvl(), battleData, XS, data.getMan());
//                zhaohuan(test, "召唤", this, "40001");
//            } else {
//                isSKill = true;
//            }
//
//        }
        if (ai != null && ai.getType() == AI.AI_NODEATH) {
            ManData monster = getMonster(ai.getValue());
            if (monster.getStates() == 0) {
                PathPoint pathPoint = null;
                Skill skill3 = GameServer.getSkill(ai.getState().split("-")[1]);
//                    new FightingSkill(skill3,);
                FightingSkill skill = new FightingSkill(skill3, data.getlvl(), data.getZs(), 25000, 0, 0);
                data.getSkills().add(skill);
                if (skill != null) {
                    originator.setStartState(TypeUtil.JN);
                    originator.setEndState(skill.getSkillname());
                    if (!skill.Goodskill()) {//随机敌方阵营
                        pathPoint = instructionspath2(nomy(data.getCamp()), 1, data);
                    } else {
                        pathPoint = instructionspath2(data.getCamp(), 1, data);
                    }
                } else {
                    originator.setStartState(skill.getSkillname());
                    pathPoint = instructionspath2(nomy(data.getCamp()), 0, data);
                }

                List<FightingState> accepterlist = new ArrayList<>();
                FightingState accepter = new FightingState();
                accepter.setCamp(pathPoint.getX());
                accepter.setMan(pathPoint.getY());
                accepterlist.add(accepter);
                events.setAccepterlist(accepterlist);
                return events;
            } else {
                isSKill = true;
            }
        }
        if (ai != null && ai.getType() == AI.AI_TYPE_BS) {
            isSKill = true;
        }

        if (ai != null && ai.getType() == AI.AI_TYPE_ZH) {

            int ndXS1 = battleData.getBattlefield().ndXS;
            Double XS;
            if (ndXS1 == 0) {
                XS = 1.0;
            } else if (ndXS1 == 1) {
                XS = 1.8;
            } else if (ndXS1 == 2) {
                XS = 2.8;
            } else if (ndXS1 == 3) {
                XS = 4.0;
            } else {
                XS = 5.4;
            }
            String monsterId = ai.getMan() + "";
            Boolean b1 = true;
            Boolean b2 = false;
            if (ai.getAiConditions() != null) {
                for (AICondition aiCondition : ai.getAiConditions()) {
//                    if (!aiCondition.getB()) {
//                        aiCondition.setB(true);
//                        b1 = false;
//                        isSKill = true;
//                        continue;
//                    }
                    if (aiCondition.getX() == 1) {
                        ManData data2 = getMonster(aiCondition.getY());
                        if (data2 != null && data2.getStates() > 0) {
                            FightingEvents fightingEvents = new FightingEvents();

                            if (!b2) {
                                List<FightingState> Accepterlist = new ArrayList<>();
                                fightingEvents = new FightingEvents();
                                FightingState org1 = new FightingState();
                                org1.setCamp(data.getCamp());
                                org1.setMan(data.getMan());
                                org1.setStartState("法术攻击");
//                    org1.setText("兄弟们，揍人了#47");
                                Accepterlist.add(org1);
                                fightingEvents.setAccepterlist(Accepterlist);
                                this.NewEvents.add(fightingEvents);
                            }
                            fightingEvents = new FightingEvents();
                            FightingState fightingState = new FightingState();
                            fightingState.setStartState("召回");
                            fightingState.setCamp(data2.getCamp());
                            fightingState.setMan(data2.getMan());
                            data2.setStates(2);
                            fightingEvents.setOriginator(fightingState);
                            NewEvents.add(fightingEvents);
                            MixDeal.zhaohui(data2, fightingState, this);
                            fightingdata.remove(data2);
                            monsterId = ai.getValue() + "";

                            //查询场上是否看放置怪物
                            List<ManData> manData = this.exitMandaByCmap(data2.getCamp());
                            int zhNumb = 10 - manData.size();
                            List<Integer> manList = new ArrayList<>();
                            for (int j = 0; j < 10; j++) {
                                Boolean b = false;
                                for (ManData manDatum : manData) {
                                    if (manDatum.getMan() == j) {
                                        b = true;
                                        break;
                                    }
                                }
                                if (!b)
                                    manList.add(j);
                            }
                            //获取怪物位置
                            int num = ai.getMan();
                            if (num >= zhNumb) {
                                num = zhNumb;
                            }
                            for (int i1 = 0; i1 < num; i1++) {
                                int c = new Random().nextInt(manList.size());
                                int i = manList.get(c);
                                manList.remove(c);
                                //放入怪物
                                if (ai.getState() == null) {
                                    ManData test = BattleThreadPool.loadCreep(monsterId, "测试", battleData.getMaxLvl(), battleData, XS, i);
                                    zhaohuan(test, "召唤", this, "40003");
                                } else {
                                    String[] newmonster = ai.getState().split("~");
                                    if (monsterId.equals("987654321")) {
                                        ManData test = BattleThreadPool.loadCreep(newmonster[Battlefield.random.nextInt(newmonster.length)], "测试", battleData.getMaxLvl(), battleData, XS, i);
                                        zhaohuan(test, "召唤", this, "40003");
                                    } else {
                                        ManData test = BattleThreadPool.loadCreep(monsterId, "测试", battleData.getMaxLvl(), battleData, XS, i);
                                        zhaohuan(test, "召唤", this, "40003");
                                    }
                                }

                            }
//                            for (AICondition aiCondition : ai.getAiConditions()) {
                            aiCondition.setB(false);
//                            }

//                            ManData test = BattleThreadPool.loadCreep(monsterId, "测试", battleData.getMaxLvl(), battleData, XS, data2.getMan());
//                            zhaohuan(test, "召唤", this, "40003");
                            b1 = false;
                            b2 = true;
                            isSKill = true;
//                                aiCondition.setY(test.getId());
//                                return  events;
                        } else {
                            return events;
                        }
                    }

                }
                if (b2) {
                    return events;
                }
            }
            if (b1) {
                //查询场上是否看放置怪物
                List<ManData> manData = this.exitMandaByCmap(data.getCamp());
                int zhNumb = 10 - manData.size();
                List<Integer> manList = new ArrayList<>();
                for (int j = 0; j < 10; j++) {
                    Boolean b = false;
                    for (ManData manDatum : manData) {
                        if (manDatum.getMan() == j) {
                            b = true;
                            break;
                        }
                    }
                    if (!b)
                        manList.add(j);
                }
                //获取怪物位置
                int num = ai.getValue();
                if (num >= zhNumb) {
                    num = zhNumb;
                }
                if (num > 0) {
                    List<FightingState> Accepterlist = new ArrayList<>();
                    FightingEvents fightingEvents = new FightingEvents();
                    FightingState org1 = new FightingState();
                    org1.setCamp(data.getCamp());
                    org1.setMan(data.getMan());
                    org1.setStartState("法术攻击");
//                    org1.setText("兄弟们，揍人了#47");
                    Accepterlist.add(org1);
                    fightingEvents.setAccepterlist(Accepterlist);
                    this.NewEvents.add(fightingEvents);
                }

                for (int i1 = 0; i1 < num; i1++) {
                    int c = new Random().nextInt(manList.size());
                    int i = manList.get(c);
                    manList.remove(c);
                    //放入怪物
                    if (ai.getState() == null) {
                        ManData test = BattleThreadPool.loadCreep(monsterId, "测试", battleData.getMaxLvl(), battleData, XS, i);
                        zhaohuan(test, "召唤", this, "40003");
                    } else {
                        String[] newmonster = ai.getState().split("~");
                        if (monsterId.equals("987654321")) {
                            ManData test = BattleThreadPool.loadCreep(newmonster[Battlefield.random.nextInt(newmonster.length)], "测试", battleData.getMaxLvl(), battleData, XS, i);
                            zhaohuan(test, "召唤", this, "40003");
                        } else {
                            ManData test = BattleThreadPool.loadCreep(monsterId, "测试", battleData.getMaxLvl(), battleData, XS, i);
                            zhaohuan(test, "召唤", this, "40003");
                        }
                    }
//                    ManData test = BattleThreadPool.loadCreep(monsterId, "测试", battleData.getMaxLvl(), battleData, XS, i);
//                    zhaohuan(test, "召唤", this, "40003");
                }
                for (AICondition aiCondition : ai.getAiConditions()) {
                    aiCondition.setB(false);
                }
                return events;

//                if(i){
//
//                }
                //放入怪物
            }

        }
        PathPoint pathPoint = null;
        if (ai != null && ai.getType() == AI.AI_ZDGJ) {
            if (ai.getState().equals("仙") || ai.getState().equals("魔") || ai.getState().equals("人") || ai.getState().equals("鬼") || ai.getState().equals("龙")) {
                pathPoint = instructionspathZZ(data.getCamp(), data, ai.getState() + "族");
            } else if (ai.getState().equals("召唤兽")) {
                pathPoint = instructionspathzh(data.getCamp(), data, ai.getState());
            } else if (ai.getState().equals("队长")) {
                pathPoint = instructionspathDZ(data.getCamp(), data, ai.getState() + "族");
            }
            isSKill = true;
        }
        //            return events;
        String mayname = TypeUtil.PTGJ;
        FightingSkill skill = null;
        if (ai != null || (ai != null && isSKill)) {
            if (ai.getType() == AI.AI_TYPE_SKILL || ai.getType() == AI.AI_TYPE_MF || isSKill) {
                skill = data.getZDSKILL(ai.getMan(), ai.getValue());
                if (data.getSkills().size() > 0) {
                    if (skill == null && isSKill) {
                        skill = data.getSkills().get(new Random().nextInt(data.getSkills().size()));
                    }
                }
                if (ai.getType() == AI.AI_TYPE_MF) {
                    if (String.valueOf(ai.getValue()) != null) {
                        Skill skill3 = GameServer.getSkill(String.valueOf(ai.getValue()));
//                    new FightingSkill(skill3,);
                        skill = new FightingSkill(skill3, data.getlvl(), data.getZs(), 25000, 0, 0);
                        data.getSkills().add(skill);
                    }
                }
                if (skill != null && skill.isBD()) {
                    skill = null;
                }
                if (skill != null) {
                    AICondition tmpAicondtion = null;
                    if (ai.getAiConditions() != null && ai.getAiConditions().size() >= 1) {
                        for (AICondition aiCondition : ai.getAiConditions()) {
                            if (StringUtils.isNotBlank(aiCondition.getSy()) && aiCondition.getSy().startsWith("X")) {
                                tmpAicondtion = aiCondition;
                                break;
                            }

                        }
                    }
                    if (tmpAicondtion != null) {
//                        String[] v = tmpAicondtion.getSy().split("=");
//                        Integer xh = Integer.parseInt(v[0].substring(1));
//                        Integer hh = Integer.parseInt(v[1]);
//                        if (battleData.getRound() % xh == hh) {
//                            mayname = skill.getSkillname();
//                        } else {
                        FightingSkill skillId = data.getSkillId(ai.getMan());
                        if (skillId == null) {
                            Skill skill3 = GameServer.getSkill(ai.getMan() + "");
//                    new FightingSkill(skill3,);
                            skillId = new FightingSkill(skill3, data.getlvl(), data.getZs(), ai.getValue(), 0, 0);
                            data.getSkills().add(skillId);
                        }
                        mayname = skillId.getSkillname();
//                        }
                    } else
                        mayname = skill.getSkillname();
                }
            } else if (ai.getType() == AI.AI_TYPE_AUTOMATE) {
                AIAutomate aiAutomate = ai.getAiAutomate(BattleType);
                if (aiAutomate != null) {
                    if (aiAutomate.getType().equals("防御") || aiAutomate.getType().equals("逃跑")) {
                        originator.setStartState(aiAutomate.getType());
                        return events;
                    }
                    mayname = aiAutomate.getType();
                    if (aiAutomate.getBs() == 1) {
                        for (int i = 0; i < aiAutomate.getSkills().length; i++) {
                            skill = data.getSkillId(aiAutomate.getSkills()[i]);
                            if (skill != null) {
                                if (skill.getSkillbeidong() == 1 || data.SkillCooling(skill.getSkillid()) || !data.isLicense(skill)) {
                                    skill = null;
                                    continue;
                                }
                                mayname = skill.getSkillname();
                                break;
                            }
                        }
                    }
                }
            }
        } else {
            skill = mayinstructions2(data);
            if (skill != null) {
                mayname = skill.getSkillname();
            }
        }
        if (AIdaya.size() != 0) {
            for (int j = 0; j <= AIdaya.size() - 1; j++) {
                if (data == AIdaya.get(j))
                    for (int h = 0; h <= data.getSkills().size() - 1; h++) {
                        skill = data.getSkillId(data.getSkills().get(h).getSkillid());
                        mayname = skill.getSkillname();
                        break;
                    }

            }
        }
        if (data.isAi) {//机器人
            if (data.getType() == 0) {
                if (data.getSkills().size() == 1) {
                    skill = data.getSkills().get(0);
                    if (data.getMp() >= skill.getSkillblue() || getSeek(data, 1) != null) {
                        mayname = skill.getSkillname();
                    } else {
                        originator.setStartState("药");
                        originator.setEndState("MP%=80");
                        List<FightingState> accepterlist = new ArrayList<>();
                        FightingState accepter = new FightingState();
                        accepter.setCamp(data.getCamp());
                        accepter.setMan(data.getMan());
                        accepterlist.add(accepter);
                        events.setAccepterlist(accepterlist);
                        return events;
                    }
                } else {
                    for (int i = 0; i < data.getSkills().size(); i++) {
                        if (GameServer.random.nextInt(100) <= 70 || i == data.getSkills().size() - 1) {
                            skill = data.getSkillId(data.getSkills().get(i).getSkillid());
                            if (data.getMp() >= skill.getSkillblue()) {
                                mayname = skill.getSkillname();
                                break;
                            } else {
                                originator.setStartState("药");
                                originator.setEndState("MP%=80");
                                List<FightingState> accepterlist = new ArrayList<>();
                                FightingState accepter = new FightingState();
                                accepter.setCamp(data.getCamp());
                                accepter.setMan(data.getMan());
                                accepterlist.add(accepter);
                                events.setAccepterlist(accepterlist);
                                return events;
                            }
                        }
                    }
                }
            } else {
                skill = data.getSkillName();
                if (skill == null || data.getMp() < skill.getSkillblue()) {
                    ManData manData = getPetParents(data);
                    if (manData.getHp() * 10 <= manData.getHp_z()) { // 主人血量低于百分之十拉血
                        originator.setStartState("药");
                        originator.setEndState("MP%=80");
                        List<FightingState> accepterlist = new ArrayList<>();
                        FightingState accepter = new FightingState();
                        accepter.setCamp(manData.getCamp());
                        accepter.setMan(manData.getMan());
                        accepterlist.add(accepter);
                        events.setAccepterlist(accepterlist);
                        return events;
                    } else if (manData.getMp() * 10 <= manData.getMp_z()) {
                        // 主人蓝低于百分之十拉蓝 或者主人无法施法时给主人拉蓝
                        originator.setStartState("药");
                        originator.setEndState("HP%=80");
                        List<FightingState> accepterlist = new ArrayList<>();
                        FightingState accepter = new FightingState();
                        accepter.setCamp(manData.getCamp());
                        accepter.setMan(manData.getMan());
                        accepterlist.add(accepter);
                        events.setAccepterlist(accepterlist);
                        return events;
                    }
                } else {
                    mayname = skill.getSkillname();
                }
            }

        }


        if (skill != null) {
            originator.setStartState(TypeUtil.JN);
            originator.setEndState(mayname);
            if (!skill.Goodskill()) {//随机敌方阵营
                if (pathPoint == null)
                    pathPoint = instructionspath2(nomy(data.getCamp()), 1, data);
            } else {
                pathPoint = instructionspath2(data.getCamp(), 1, data);
            }
        } else {
            originator.setStartState(mayname);
            if (pathPoint == null)
                pathPoint = instructionspath2(nomy(data.getCamp()), 0, data);
        }

        List<FightingState> accepterlist = new ArrayList<>();
        FightingState accepter = new FightingState();
        accepter.setCamp(pathPoint.getX());
        accepter.setMan(pathPoint.getY());
        accepterlist.add(accepter);
        events.setAccepterlist(accepterlist);
        return events;
    }

    /**
     * 可以指挥的数量
     * 场上非野怪的数量
     */
    public int noyesum() {
        boolean isArena = PK_MixDeal.isArena(this.BattleType);
        int sum = this.fightingdata.size();
        for (int i = sum - 1; i >= 0; --i) {
            ManData data = this.fightingdata.get(i);
            if (data.getStates() == 2) {
                --sum;
            } else if (data.getType() == 2 || data.getType() == 3 || data.getType() == 4) {
                --sum;
            } else if (isArena && data.getCamp() != 1) {
                --sum;
            } else if (data.isAi) {
                --sum;
            } else if (data.getType() == 1 && data.getStates() == 1) {
                --sum;
            } else if (data.getType() == 1) {
                ManData manData1 = this.getPetParents(data);
                if (this.AIdaya.size() != 0) {
                    for (int l = 0; l <= this.AIdaya.size() - 1; ++l) {
                        if (manData1 == this.AIdaya.get(l)) {
                            --sum;
                        }
                    }
                }
            } else if (data.getAddStates().size() != 0) {
                for (int k = 0; k <= data.getAddStates().size() - 1; ++k) {
                    if (data.getAddStates().get(k).getStatename().equals("乾坤伏魔") || data.getAddStates().get(k).getStatename().equals("造化之舟")) {
                        --sum;
                    }
                }
            }
            if (this.AIdaya.size() != 0) {
                for (int j = 0; j <= this.AIdaya.size() - 1; ++j) {
                    if (data == this.AIdaya.get(j)) {
                        --sum;
                    }
                }
            }
        }
        sum += this.elsum;
        return sum;
    }

    /**
     * 场上玩家的数量
     */
    public int noyenosumomsum() {
        int sum = 0;
        for (int i = 0; i < this.fightingdata.size(); ++i) {
            if (this.fightingdata.get(i).getType() == 0 && this.fightingdata.get(i).getStates() != 2) {
                ++sum;
            }
        }
        return sum;
    }

    /**
     * 根据List<ManData>生成 List<FightingManData>
     */
    public List<FightingManData> Transformation() {
        List<FightingManData> fightingManDatas = new ArrayList<>();
        try {
            for (int i = 0; i < this.fightingdata.size(); ++i) {
                ManData data = this.fightingdata.get(i);
                if (data.getStates() != 2 && (data.getStates() != 1 || data.getType() == 0 || data.getType() == 2)) {
                    FightingManData fightingManData = new FightingManData();
                    fightingManData.setModel(data.getModel());
                    fightingManData.setManname(data.getManname());
                    fightingManData.setCamp(data.getCamp());
                    fightingManData.setMan(data.getMan());
                    fightingManData.setSkin(data.getSkin());
                    fightingManData.setHp_Current((long) data.getHp());
                    fightingManData.setHp_Total((long) data.getHp_z());
                    fightingManData.setMp_Current(data.getMp());
                    fightingManData.setMp_Total(data.getMp_z());
                    fightingManData.setState_1(data.xz());
                    fightingManData.setType(data.getType());
                    fightingManData.setId(data.getId());
                    fightingManData.setZs(data.getZs());
                    fightingManData.setPetType(data.getPetType());
                    fightingManData.setStates(data.ztstlist(fightingManData));
                    fightingManData.setMsg(data.getMsg());
                    fightingManData.setYqz(data.getYqz());
                    fightingManData.setNqz(data.getNqz());
                    fightingManData.setSpeciesid(data.getSpeciesid());
                    fightingManDatas.add(fightingManData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fightingManDatas;
    }

    /**
     * 结束判断 判断阵营是否还有活人
     * -1代表未结束战斗
     * 传回当前相同数则胜利
     * 其他为失败
     */
    public int endFighting(int mycamp) {//战斗回合上限
        int nomycamp = this.nomy(mycamp);
        int mysum = this.Surplus(mycamp);
        int nomysum = this.Surplus(nomycamp);
        int he = 120;
        boolean pk = PK_MixDeal.isPK(this.BattleType);
        if (PK_MixDeal.isPK(this.BattleType) && this.BattleType != 101) {
            he = 40;
        }
        if (this.CurrentRound >= he) {
            if (PK_MixDeal.isPK(this.BattleType) && this.BattleType != 101) {
                try {
                    if (mysum > nomysum) {
                        return mycamp;
                    }
                    if (mysum == nomysum) {
                        Map<String, FightingStatistics> fightingStatisticsMap = this.battleData.getFightingForesee().getFightingStatisticsMap(this);
                        FightingStatistics fightingStatistics1 = fightingStatisticsMap.get(mycamp + "");
                        FightingStatistics fightingStatistics2 = fightingStatisticsMap.get(nomycamp + "");
                        if (fightingStatistics1.getPetNum() < fightingStatistics2.getPetNum()) {
                            return mycamp;
                        }
                        if (fightingStatistics1.getPetNum() == fightingStatistics2.getPetNum()) {
                            if (fightingStatistics1.getLingNum() < fightingStatistics2.getLingNum()) {
                                return mycamp;
                            }
                            if (fightingStatistics1.getLingNum() == fightingStatistics2.getLingNum()) {
                                return Battlefield.random.nextBoolean() ? mycamp : nomycamp;
                            }
                            return nomycamp;
                        } else {
                            return nomycamp;
                        }
                    } else {
                        return nomycamp;
                    }
                } catch (Exception e) {
                    return Battlefield.random.nextBoolean() ? mycamp : nomycamp;
                }
            }
            if ((this.BattleType == 21 || this.BattleType == 33 || this.BattleType == 102) && this.CurrentRound >= 60) {
                return nomycamp;
            }
            if (PK_MixDeal.isArena(this.BattleType) && this.CurrentRound > 10) {
                return nomycamp;
            }
            this.battleData.setWinCamp(-3);
            BattleThreadPool.removeBattleData(this.battleData);
            if (mysum > 0) {
                return mycamp;
            }
            if (nomysum > 0) {
                return nomycamp;
            }
            return Battlefield.random.nextBoolean() ? mycamp : nomycamp;
        } else {
            if ((this.BattleType == 21 || this.BattleType == 33 || this.BattleType == 102) && this.CurrentRound >= 60) {
                return nomycamp;
            }
            if (PK_MixDeal.isArena(this.BattleType) && this.CurrentRound > 10) {
                return nomycamp;
            }
            if (mysum > 0 && nomysum > 0) {
                return -1;
            }
            if (mysum > 0) {
                return mycamp;
            }
            if (nomysum > 0) {
                return nomycamp;
            }
            return Battlefield.random.nextBoolean() ? mycamp : nomycamp;
        }
    }

    /**
     * 判断阵营剩余的人数
     * 不为逃跑的人
     * 不为小孩和灵宝
     */
    public int Surplus(int camp) {
        boolean isBB = PK_MixDeal.isBB(this.BattleType);
        int sum = 0;
        int pSum = 0;
        boolean is = true;
        for (int i = 0; i < this.fightingdata.size(); ++i) {
            ManData data = (ManData) this.fightingdata.get(i);
            if (data.getType() != 3 && data.getType() != 4 && data.getCamp() == camp && (!isBB || data.getMan() >= 5)) {
                if (camp != 0 && data.getType() == 2) {
                    if (data.getStates() == 0) {
                        ++pSum;
                    }
                } else if (data.getStates() == 0) {
                    ++sum;
                } else if (is && data.getStates() == 1) {
                    is = false;
                }
            }
        }
        if (sum == 0) {
            return is ? sum : (sum + pSum);
        }
        return sum;
    }

    public int Surplus(int camp, int type) {
        boolean isBB = PK_MixDeal.isBB(this.BattleType);
        int sum = 0;
        int pSum = 0;
        boolean is = true;
        for (int i = 0; i < this.fightingdata.size(); ++i) {
            ManData data = (ManData) this.fightingdata.get(i);
            if (data.getType() == type && (!isBB || data.getMan() >= 5)) {
                if (camp != 0 && data.getType() == 2) {
                    if (data.getStates() == 0) {
                        ++pSum;
                    }
                } else if (data.getStates() == 0) {
                    ++sum;
                    break;
                } else if (is && data.getStates() == 1) {
                    is = false;
                }
            }
        }
        if (sum == 0) {
            return is ? sum : (sum + pSum);
        }
        return sum;
    }

    /**
     * 找出阵营中蓝量最低 且还活着的人
     */
    public ManData minmp(int camp) {
        int mp = 10000000;
        int path = -1;
        for (int i = 0; i < this.fightingdata.size(); ++i) {
            ManData data = this.fightingdata.get(i);
            if (data.getType() != 3 && data.getType() != 4 && data.getCamp() == camp && (data.getStates() == 0 && mp > data.getMp())) {
                mp = data.getMp();
                path = i;
            }
        }
        if (path != -1) {
            return this.fightingdata.get(path);
        }
        return null;
    }

    /**
     * 找到是否存在指定位置 不存在添加在后面
     */
    public boolean Addbb(ManData bbData, int mycamp, int myman) {
        for (int i = 0; i < this.fightingdata.size(); ++i) {
            int camp = this.fightingdata.get(i).getCamp();
            int man = this.fightingdata.get(i).getMan();
            if (bbData != null) {
                if (camp == bbData.getCamp() && man == bbData.getMan()) {
                    int path = this.Datapath(camp, man - 5);
                    this.Petplay(this.fightingdata.get(i), this.fightingdata.get(path), 2);
                    this.fightingdata.get(i).clearAddStates();
                    this.fightingdata.set(i, bbData);
                    this.Petplay(this.fightingdata.get(i), this.fightingdata.get(path), 1);
                    return true;
                }
            } else if (camp == mycamp && man == myman) {
                ((ManData) this.fightingdata.get(i)).setStates(2);
                ((ManData) this.fightingdata.get(i)).clearAddStates();
                this.Petplay((ManData) this.fightingdata.get(i), (ManData) this.fightingdata.get(this.Datapath(camp, man - 5)), 2);
                return true;
            }
        }
        if (bbData != null) {
            this.Petplay(bbData, (ManData) this.fightingdata.get(this.Datapath(bbData.getCamp(), bbData.getMan() - 5)), 1);
            this.fightingdata.add(bbData);
            return true;
        }
        return false;
    }

    /**
     * 找到是否存在指定位置 不存在添加在后面
     */
    public boolean AddLB(ManData bbData, int mycamp, int myman) {
        for (int i = 0; i < this.fightingdata.size(); ++i) {
            int camp = this.fightingdata.get(i).getCamp();
            int man = this.fightingdata.get(i).getMan();
            if (bbData != null) {
                if (camp == bbData.getCamp() && man == bbData.getMan()) {
                    int path = this.Datapath(camp, man - 10);
                    this.Petplay(this.fightingdata.get(i), this.fightingdata.get(path), 2);
                    this.fightingdata.get(i).clearAddStates();
                    this.fightingdata.set(i, bbData);
                    this.Petplay(this.fightingdata.get(i), this.fightingdata.get(path), 1);
                    return true;
                }
            } else if (camp == mycamp && man == myman) {
                this.fightingdata.get(i).setStates(2);
                this.fightingdata.get(i).clearAddStates();
                this.Petplay(this.fightingdata.get(i), this.fightingdata.get(this.Datapath(camp, man - 10)), 2);
                return true;
            }
        }
        if (bbData != null) {
            this.Petplay(bbData, this.fightingdata.get(this.Datapath(bbData.getCamp(), bbData.getMan() - 10)), 1);
            this.fightingdata.add(bbData);
            return true;
        }
        return false;
    }

    /**
     * 设置召唤兽的play
     */
    public void Petplay(ManData petdata, ManData roledata, int play) {
        if (petdata.getType() == 1) {
            for (int i = 0; i < roledata.getPets().size(); ++i) {
                if (petdata.getId() == roledata.getPets().get(i).getHang().getId().intValue()) {
                    roledata.getPets().get(i).setPlay(play);
                }
            }
        } else if (petdata.getType() == 3) {
            for (int i = 0; i < roledata.getLings().size(); ++i) {
                if (petdata.getId() == roledata.getLings().get(i).getLingbaonData().getId()) {
                    roledata.getLings().get(i).setPlay(play);
                }
            }
        }
    }

    /**
     * 根据队伍获得靠中间的战斗位置
     */
    public static int Fightingpath(int camp, int man) {
        if (camp == 0) {
            if (man == 0) {
                return 2;
            }
            if (man == 1) {
                return 1;
            }
            if (man == 2) {
                return 3;
            }
            if (man == 3) {
                return 0;
            }
            if (man == 4) {
                return 4;
            }
            if (man == 5) {
                return 7;
            }
            if (man == 6) {
                return 6;
            }
            if (man == 7) {
                return 8;
            }
            if (man == 8) {
                return 5;
            }
            return 9;
        } else {
            if (man == 0) {
                return 2;
            }
            if (man == 1) {
                return 1;
            }
            if (man == 2) {
                return 3;
            }
            if (man == 3) {
                return 0;
            }
            return 4;
        }
    }

    /**
     * 反向解析根据队伍获得靠中间的战斗位置
     */
    public static int FXFightingpath(int man) {
        if (man == 0) {
            return 3;
        }
        if (man == 1) {
            return 1;
        }
        if (man == 2) {
            return 0;
        }
        if (man == 3) {
            return 2;
        }
        return 4;
    }

    /**
     * 返回该单位的孩子
     */
    public ManData getChild(ManData data) {
        if (data.getType() != 0) {
            return null;
        }
        int man = data.getMan() + 15;
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData Child = this.fightingdata.get(i);
            if (Child.getCamp() == data.getCamp() && Child.getMan() == man) {
                return Child;
            }
        }
        return null;
    }

    /**
     * 返回孩子的家长
     */
    public ManData getParents(ManData data) {
        if (data.getType() != 4) {
            return null;
        }
        int man = data.getMan() - 15;
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData Parents = this.fightingdata.get(i);
            if (Parents.getCamp() == data.getCamp() && Parents.getMan() == man) {
                return Parents;
            }
        }
        return null;
    }

    /**
     * 返回召唤兽的家长
     */
    public ManData getPetParents(ManData data) {
        if (data.getType() != 1) {
            return null;
        }
        int man = data.getMan() - 5;
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData Parents = (ManData) this.fightingdata.get(i);
            if (Parents.getCamp() == data.getCamp() && Parents.getMan() == man) {
                return Parents;
            }
        }
        return null;
    }

    /**
     * 返回灵宝的家长
     */
    public ManData getLingParents(ManData data) {
        if (data.getType() != 3) {
            return null;
        }
        int man = data.getMan() - 10;
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData Parents = this.fightingdata.get(i);
            if (Parents.getCamp() == data.getCamp() && Parents.getMan() == man) {
                return Parents;
            }
        }
        return null;
    }

    /**
     * 家长寻找单位  1召唤兽 3灵宝 4小孩
     */
    public ManData getSeek(ManData data, int type) {
        if (data.getType() != 0) {
            return null;
        }
        int man = data.getMan();
        if (type == 1) {
            man += 5;
        } else if (type == 3) {
            man += 10;
        } else if (type == 4) {
            man += 15;
        }
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData Parents = this.fightingdata.get(i);
            if (Parents.getType() == type && Parents.getMan() == man && Parents.getCamp() == data.getCamp()) {
                return Parents;
            }
        }
        return data;
    }

    /**
     * 挣扎处理
     */
    public void struggle(ManData data, List<FightingState> states) {
        ManData child = this.getChild(data);
        AddState zt = data.xzstate("混乱");
        FightingSkill skill = null;
        if (zt != null && child != null) {
            skill = child.getChildSkill("解混乱");
            if (skill != null) {
                states.add(MixDeal.getChildSkill(child, skill.getSkillname()));
                FightingState state2 = new FightingState();
                state2.setCamp(data.getCamp());
                state2.setMan(data.getMan());
                state2.setStartState("代价");
                state2.setEndState_2("混乱");
                data.getAddStates().remove(zt);
                zt.rid(data, state2);
                ChangeFighting fighting = new ChangeFighting();
                fighting.setChangetype("抗混乱");
                fighting.setChangevlaue(skill.getSkillgain());
                fighting.setChangesum(skill.getSkillcontinued());
                data.ChangeData(fighting, state2);
                states.add(state2);
                zt = null;
            }
        }
        if (zt != null && (double) Battlefield.random.nextInt(888) < data.getsx(0, "混乱")) {
            FightingState state2 = new FightingState();
            state2.setCamp(data.getCamp());
            state2.setMan(data.getMan());
            state2.setStartState("代价");
            state2.setEndState_2("混乱");
            states.add(state2);
            data.getAddStates().remove(zt);
            zt.rid(data, state2);
        }
        zt = data.xzstate("封印");
        if (zt != null && child != null) {
            skill = child.getChildSkill("解封印");
            if (skill != null) {
                states.add(MixDeal.getChildSkill(child, skill.getSkillname()));
                FightingState state2 = new FightingState();
                state2.setCamp(data.getCamp());
                state2.setMan(data.getMan());
                state2.setStartState("代价");
                state2.setEndState_2("封印");
                data.getAddStates().remove(zt);
                zt.rid(data, state2);
                ChangeFighting fighting = new ChangeFighting();
                fighting.setChangetype("抗封印");
                fighting.setChangevlaue(skill.getSkillgain());
                fighting.setChangesum(skill.getSkillcontinued());
                data.ChangeData(fighting, state2);
                states.add(state2);
                zt = null;
            }
        }
        if (zt != null && (double) Battlefield.random.nextInt(888) < data.getsx(0, "封印")) {
            FightingState state2 = new FightingState();
            state2.setCamp(data.getCamp());
            state2.setMan(data.getMan());
            state2.setStartState("代价");
            state2.setEndState_2("封印");
            states.add(state2);
            data.getAddStates().remove(zt);
            zt.rid(data, state2);
        }
        zt = data.xzstate("昏睡");
        if (zt != null && child != null) {
            skill = child.getChildSkill("解昏睡");
            if (skill != null) {
                states.add(MixDeal.getChildSkill(child, skill.getSkillname()));
                FightingState state2 = new FightingState();
                state2.setCamp(data.getCamp());
                state2.setMan(data.getMan());
                state2.setStartState("代价");
                state2.setEndState_2("昏睡");
                data.getAddStates().remove(zt);
                zt.rid(data, state2);
                ChangeFighting fighting = new ChangeFighting();
                fighting.setChangetype("抗昏睡");
                fighting.setChangevlaue(skill.getSkillgain());
                fighting.setChangesum(skill.getSkillcontinued());
                data.ChangeData(fighting, state2);
                states.add(state2);
                zt = null;
            }
        }
        if (zt != null && (double) Battlefield.random.nextInt(888) < data.getsx(0, "昏睡")) {
            FightingState state2 = new FightingState();
            state2.setCamp(data.getCamp());
            state2.setMan(data.getMan());
            state2.setStartState("代价");
            state2.setEndState_2("昏睡");
            states.add(state2);
            data.getAddStates().remove(zt);
            zt.rid(data, state2);
        }
        zt = data.xzstate("中毒");
        if (zt != null && child != null) {
            skill = child.getChildSkill("解中毒");
            if (skill != null) {
                states.add(MixDeal.getChildSkill(child, skill.getSkillname()));
                FightingState state2 = new FightingState();
                state2.setCamp(data.getCamp());
                state2.setMan(data.getMan());
                state2.setStartState("代价");
                state2.setEndState_2("中毒");
                data.getAddStates().remove(zt);
                if (data.getStates() == 0) {
                    ChangeFighting fighting = new ChangeFighting();
                    fighting.setChangehp((int) ((double) data.getHp_z() * skill.getSkillgain() / 100.0));
                    data.ChangeData(fighting, state2);
                }
                states.add(state2);
                zt = null;
            }
        }
        if (zt != null && (double) Battlefield.random.nextInt(888) < data.getsx(0, "中毒")) {
            FightingState state2 = new FightingState();
            state2.setCamp(data.getCamp());
            state2.setMan(data.getMan());
            state2.setStartState("代价");
            state2.setEndState_2("中毒");
            states.add(state2);
            data.getAddStates().remove(zt);
        }
        zt = data.xzstate(TypeUtil.FB_JGE);
        if (zt != null) {
            FightingSkill sMM = data.getSkillType(TypeUtil.TY_FY_MHNY);
            if (skill != null && isV(sMM.getSkillhurt())) {
                FightingState state3 = new FightingState();
                state3.setCamp(data.getCamp());
                state3.setMan(data.getMan());
                state3.setStartState("代价");
                state3.setEndState_2(TypeUtil.TY_FY_MHNY);
                states.add(state3);
                data.getAddStates().remove(zt);
            }
        }
        if (data.getType() == 1) {
            skill = data.getSkillType(TypeUtil.BB_E_XYLX);
            if (skill != null && isV(skill.getSkillgain()) && data.getStates() == 0) {
                ManData parent = this.getPetParents(data);
                if (parent != null && parent.getvalue(0) < skill.getSkillgain() / 100.0) {
                    FightingState state4 = new FightingState();
                    state4.setCamp(data.getCamp());
                    state4.setMan(data.getMan());
                    state4.setStartState("代价");
                    states.add(state4);
                    if (GameServer.random.nextBoolean()) {
                        FightingState state5 = new FightingState();
                        ChangeFighting changeFighting = new ChangeFighting();
                        changeFighting.setChangehp(parent.getHp_z() / 2);
                        changeFighting.setChangemp(parent.getMp_z() / 2);
                        parent.ChangeData(changeFighting, state5);
                        state5.setStartState("代价");
                        state4.setText("为主人回血#2");//新修心有灵犀加抗性问题
                        states.add(state5);
                    } else {// 心有灵犀增加抗性
                        FightingState ace = new FightingState(parent.getCamp(), parent.getMan(), TypeUtil.JN);
                        ace.setEndState_1(TypeUtil.BB_E_XYLX);
                        states.add(ace);
                        parent.addAddState(TypeUtil.BB_E_XYLX, 40.0, 0.0, 1);
                        parent.getQuality().addks(40.0);
                        state4.setText("为主人提抗#2");//新修心有灵犀回合问题
                    }
                }
            }
        }
        zt = data.xzstate("化险为夷");// 身上有盾
        if (zt != null && data.executeYbwh(1)) {
            data.RemoveAbnormal(ManData.values1);
            FightingState state2 = new FightingState();
            state2.setCamp(data.getCamp());
            state2.setMan(data.getMan());
            state2.setStartState("代价");
            state2.setEndState_2("清除异常状态");
            states.add(state2);
        }
        zt = data.xzstate("焕然新生");  // 盾持续回合为最后一回合
        if (zt != null && zt.getOngoing() == 2) {
            data.RemoveAbnormal(ManData.values1);
            FightingState state2 = new FightingState();
            state2.setCamp(data.getCamp());
            state2.setMan(data.getMan());
            state2.setStartState("代价");
            state2.setEndState_2("清除异常状态");
            states.add(state2);
        }
    }

    /**
     * 摆脱处理
     */
    public List<FightingState> Rid(ManData data, List<FightingState> states) {
        AddState zt = data.xzstate("混乱");
        FightingSkill skill = null;
        if (zt != null) {
            skill = data.getSkillType(TypeUtil.BB_QMS);
            if (skill != null && skill.getSkillhitrate() > (double) Battlefield.random.nextInt(100)) {
                if (states == null) {
                    states = new ArrayList<>();
                }
                FightingState state2 = new FightingState();
                state2.setCamp(data.getCamp());
                state2.setMan(data.getMan());
                state2.setStartState(TypeUtil.JN);
                state2.setSkillskin(skill.getSkilltype());
                state2.setEndState_2(zt.getStatename());
                states.add(state2);
                data.RemoveAbnormal(state2, new String[]{zt.getStatename()});
            }
        }
        if (zt != null && data.getType() == 2 && isV(data.getQuality().getHlzz())) {
            if (states == null) {
                states = new ArrayList<>();
            }
            FightingState state2 = new FightingState();
            state2.setCamp(data.getCamp());
            state2.setMan(data.getMan());
            state2.setStartState(TypeUtil.JN);
            state2.setSkillskin("1849");
            state2.setText("混乱挣扎");
            state2.setEndState_2(zt.getStatename());
            states.add(state2);
            data.RemoveAbnormal(state2, new String[]{zt.getStatename()});
        }
        zt = data.xzstate("封印");
        if (zt != null) {
            double xs = 0.0;
            skill = data.getSkillType(TypeUtil.BB_TKS);
            if (skill != null) {
                xs += skill.getSkillhitrate();
            }
            skill = data.getSkillType(TypeUtil.BB_E_BXJL);
            if (skill != null) {
                xs += skill.getSkillgain();
            }
            if (isV(xs)) {
                if (states == null) {
                    states = new ArrayList<>();
                }
                FightingState state3 = new FightingState();
                state3.setCamp(data.getCamp());
                state3.setMan(data.getMan());
                state3.setStartState(TypeUtil.JN);
                state3.setSkillskin(TypeUtil.BB_TKS);
                state3.setEndState_2(zt.getStatename());
                if (skill != null) {
                    state3.setText("冰雪精灵#2");
                }
                states.add(state3);
                data.RemoveAbnormal(state3, new String[]{zt.getStatename()});
            }
        }
        if (zt != null && data.getType() == 2 && isV(data.getQuality().getBhzz())) {
            if (states == null) {
                states = new ArrayList<>();
            }
            FightingState state2 = new FightingState();
            state2.setCamp(data.getCamp());
            state2.setMan(data.getMan());
            state2.setStartState(TypeUtil.JN);
            state2.setSkillskin("1849");
            state2.setText("封印挣扎");
            state2.setEndState_2(zt.getStatename());
            states.add(state2);
            data.RemoveAbnormal(state2, new String[]{zt.getStatename()});
        }
        zt = data.xzstate("遗忘");
        if (zt != null) {
            skill = data.getSkillType(TypeUtil.BB_QXS);
            if (skill != null && skill.getSkillhitrate() > (double) Battlefield.random.nextInt(100)) {
                if (states == null) {
                    states = new ArrayList<>();
                }
                FightingState state2 = new FightingState();
                state2.setCamp(data.getCamp());
                state2.setMan(data.getMan());
                state2.setStartState(TypeUtil.JN);
                state2.setSkillskin(skill.getSkilltype());
                state2.setEndState_2(zt.getStatename());
                states.add(state2);
                data.RemoveAbnormal(state2, new String[]{zt.getStatename()});
            }
        }
        if (zt != null && data.getType() == 2 && isV(data.getQuality().getYwzz())) {
            if (states == null) {
                states = new ArrayList<>();
            }
            FightingState state2 = new FightingState();
            state2.setCamp(data.getCamp());
            state2.setMan(data.getMan());
            state2.setStartState(TypeUtil.JN);
            state2.setSkillskin("1849");
            state2.setText("遗忘挣扎");
            state2.setEndState_2(zt.getStatename());
            states.add(state2);
            data.RemoveAbnormal(state2, new String[]{zt.getStatename()});
        }
        zt = data.xzstate("昏睡");
        if (zt != null && data.getType() == 2 && isV(data.getQuality().getYwzz())) {
            if (states == null) {
                states = new ArrayList<>();
            }
            FightingState state2 = new FightingState();
            state2.setCamp(data.getCamp());
            state2.setMan(data.getMan());
            state2.setStartState(TypeUtil.JN);
            state2.setSkillskin("1849");
            state2.setText("昏睡挣扎");
            state2.setEndState_2(zt.getStatename());
            states.add(state2);
            data.RemoveAbnormal(state2, new String[]{zt.getStatename()});
        }
        return states;
    }

    public void BBDeathPoint() {
        if (this.bbDeathPoint == null) {
            return;
        }
        List<FightingState> zls = null;
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData data = this.fightingdata.get(i);
            if (data.getType() == 1 && data.getStates() == 0 && ((this.bbDeathPoint.getX() != 0 && data.getCamp() == 1) || (this.bbDeathPoint.getY() != 0 && data.getCamp() != 1))) {
                int death = (data.getCamp() == 1) ? this.bbDeathPoint.getX() : this.bbDeathPoint.getY();
                FightingSkill skill = data.getSkillType(TypeUtil.BB_FLSQ);
                if (skill != null) {
                    int num = skill.getSkillsum();
                    if (num == 0) {//添加特效
                        if (zls == null) {
                            zls = new ArrayList<>();
                        }
                        data.addAddState(TypeUtil.BB_FLSQ, 0.0, 0.0, 9999);
                        FightingState ace = new FightingState(data.getCamp(), data.getMan(), TypeUtil.JN);
                        ace.setEndState_1(TypeUtil.BB_FLSQ);
                        zls.add(ace);
                    }
                    skill.setSkillsum(skill.getSkillsum() + death);
                    if (skill.getSkillsum() >= 15) {
                        skill.setSkillsum(15);
                        data.getSkills().remove(skill);
                    }
                    String petSkillswl = ManData.getPetSkillswl();
                    int skillid = new FightingSkill().getSkillid();
                    String petSkillswlLevel = getPetSkillswlLevel(petSkillswl, skillid + "");
                    if (StringUtils.isNotBlank(petSkillswlLevel)) {
                        double add = Arith.mul(Integer.parseInt(petSkillswlLevel), 0.4);
                        data.getQuality().addK_FHSLG(skill.getSkillhurt() * (double) (skill.getSkillsum() - num) + add);
                    } else {
                        data.getQuality().addK_FHSLG(skill.getSkillhurt() * (double) (skill.getSkillsum() - num));
                    }
                }
                skill = data.getSkillType(TypeUtil.BB_LAHM);
                if (skill != null) {
                    int num = skill.getSkillsum();
                    skill.setSkillsum(skill.getSkillsum() + death);
                    if (skill.getSkillsum() >= 10) {
                        skill.setSkillsum(10);
                        data.getSkills().remove(skill);
                    }
                    data.getQuality().addK_BHSY(skill.getSkillhurt() * (double) (skill.getSkillsum() - num));
                }
            }
        }
        if (zls != null) {
            FightingEvents fightingEvents = new FightingEvents();
            fightingEvents.setCurrentRound(this.CurrentRound);
            fightingEvents.setAccepterlist(zls);
            this.NewEvents.add(fightingEvents);
        }
        this.bbDeathPoint.setX(0);
        this.bbDeathPoint.setY(0);
    }

    /**
     * 火上浇油状态处理
     */
    public void HurtHSJY(ManData manData) {
        ChangeFighting changeFighting = new ChangeFighting();
        List<FightingState> hsjystate = null;
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData data = this.fightingdata.get(i);
            if (data.getStates() == 0 && data.getType() != 3 && data.getType() != 4) {
                AddState addState = data.xzstate(TypeUtil.TY_H_HSJY);
                if (addState != null) {
                    FightingSkill skill = manData.getSkillType(TypeUtil.TY_X_10017);
                    if (skill != null && isV(skill.getSkillhurt() * addState.getStateEffect2())) {
                        addState.setStateEffect2(1.0);
                    } else {
                        data.getAddStates().remove(addState);
                    }
                    int hurt = (int) addState.getStateEffect2();
                    if (hsjystate == null) {
                        hsjystate = new ArrayList<>();
                    }
                    changeFighting.setChangehp(-hurt);
                    FightingState org2 = new FightingState();
                    FightingPackage.ChangeProcess(changeFighting, null, data, org2, TypeUtil.TY_H_HSJY, hsjystate, this);
                    org2.setCamp(data.getCamp());
                    org2.setMan(data.getMan());
                    org2.setStartState(TypeUtil.JN);
                    org2.setEndState_2(TypeUtil.TY_H_HSJY);
                }
            }
        }
        if (hsjystate != null) {
            FightingEvents ksevents = new FightingEvents();
            ksevents.setCurrentRound(this.CurrentRound);
            ksevents.setAccepterlist(hsjystate);
            this.NewEvents.add(ksevents);
        }
        this.isHSJY = false;
    }

    /**
     * 雾里看花状态处理
     */
    public void HurtWLKH() {
        ChangeFighting changeFighting = new ChangeFighting();
        List<FightingState> wlkhstate = null;
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData data = this.fightingdata.get(i);
            int hurt = data.getWLKHSkill();
            if (hurt > 0) {
                if (hurt > 123456) {
                    hurt = 123456;
                }
                if (wlkhstate == null) {
                    wlkhstate = new ArrayList<>();
                }
                changeFighting.setChangehp(-hurt);
                FightingState org2 = new FightingState();
                FightingPackage.ChangeProcess(changeFighting, null, data, org2, "雾里看花", wlkhstate, this);
                org2.setCamp(data.getCamp());
                org2.setMan(data.getMan());
                org2.setStartState(TypeUtil.JN);
            }
        }
        if (wlkhstate != null) {
            FightingEvents ksevents = new FightingEvents();
            ksevents.setCurrentRound(this.CurrentRound);
            ksevents.setAccepterlist(wlkhstate);
            this.NewEvents.add(ksevents);
        }
        this.isWLKH = false;
    }

    /**
     * 获取被打击人周围2格内单位
     *
     * @param data 被打击人
     * @param
     * @return
     */
    public List<ManData> getZW2(ManData data) {
        List<ManData> datas = new ArrayList<>();
        int camp = data.getCamp();
        int man = data.getMan();
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData noData = this.fightingdata.get(i);
            if (noData.getType() != 3 && noData.getType() != 4 && noData.getStates() == 0 && noData.getCamp() == camp) {
                int noman = noData.getMan();
                if (man != noman && ((noman == man - 6 || noman == man - 5 || (noman == man - 4 && man > 4) || (noman == man - 1 && man != 5) || (noman == man - 2 && (man > 6 || man < 5)) || (noman == man + 1 && man != 4) || (noman == man + 2 && (man > 4 || man < 3)) || (noman == man + 4 && man < 5 && man != 0) || noman == man + 5 || noman == man + 6) && noData.xzstate("封印") == null && noData.xzstate("隐身") == null)) {
                    datas.add(noData);
                }
            }
        }
        return datas;
    }

    public List<ManData> getZW(ManData data) {
        List<ManData> datas = new ArrayList<>();
        int camp = data.getCamp();
        int man = data.getMan();
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData noData = this.fightingdata.get(i);
            if (noData.getType() != 3 && noData.getType() != 4 && noData.getStates() == 0 && noData.getCamp() == camp) {
                int noman = noData.getMan();
                if (man != noman && (man == noman + 1 || man == noman - 1 || man == noman + 5 || man == noman - 5) && (man != 5 || noman != 4) && (man != 4 || noman != 5) && (noData.xzstate("封印") == null && noData.xzstate("隐身") == null)) {
                    datas.add(noData);
                }
            }
        }
        return datas;
    }

    /**
     * 获取该单位的真同列单位
     */
    public ManData getTLzhen(int camp, int man) {
        List<ManData> datas = new ArrayList<>();
        for (int i = fightingdata.size() - 1; i >= 0; i--) {
            ManData noData = fightingdata.get(i);
            if (noData.getType() == 3 || noData.getType() == 4) continue;
            if (noData.getStates() != 0) continue;
            if (noData.getCamp() != camp) continue;
            int noman = noData.getMan();
            if (noData.getType() == 2) {
                if ((man == noman + 5 || man == noman - 5)) {
                    if (noData.xzstate("封印") == null && noData.xzstate("隐身") == null) {
                        return noData;
                    }
                }
            } else {
                if ((man == noman + 5 || man == noman - 5)) {
                    if (noData.xzstate("封印") == null && noData.xzstate("隐身") == null) {
                        return noData;
                    }
                }
            }

        }
        return null;
    }

    /**
     * 获取该单位的真同排单位
     */
    public List<ManData> getTPzhen(int camp, int man) {
        List<ManData> datas = new ArrayList<>();
        for (int i = fightingdata.size() - 1; i >= 0; i--) {
            ManData noData = fightingdata.get(i);
            if (noData.getType() == 3 || noData.getType() == 4) continue;
            if (noData.getStates() != 0) continue;
            if (noData.getCamp() != camp) continue;
            int noman = noData.getMan();
            if (noData.getType() == 2) {
                if (noman <= 4 && man <= 4) {
                    if (noData.xzstate("封印") == null && noData.xzstate("隐身") == null) {
                        datas.add(noData);
                    }

                } else if (noman >= 5 && man >= 5) {
                    if (noData.xzstate("封印") == null && noData.xzstate("隐身") == null) {
                        datas.add(noData);
                    }
                }
            } else {
                if (noman <= 5 && man <= 5) {
                    if (noData.xzstate("封印") == null && noData.xzstate("隐身") == null) {
                        datas.add(noData);
                    }
                } else if (noman >= 6 && man >= 6) {
                    if (noData.xzstate("封印") == null && noData.xzstate("隐身") == null) {
                        datas.add(noData);
                    }
                }
            }
        }
        return datas;
    }

    /**
     * 获取该单位的周围单位
     */
    public List<ManData> getQCRL(ManData data) {
        List<ManData> datas = new ArrayList<>();
        int camp = data.getCamp();
        int man = data.getMan();
        int v = 50;
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData noData = this.fightingdata.get(i);
            if (noData.getType() != 3 && noData.getType() != 4 && noData.getStates() == 0 && noData.getCamp() == camp) {
                int noman = noData.getMan();
                if (man != noman && (man == noman + 1 || man == noman - 1 || man == noman + 5 || man == noman - 5) && (man != 5 || noman != 4) && (man != 4 || noman != 5) && (noData.xzstate("封印") == null && noData.xzstate("隐身") == null && isV((double) v))) {
                    datas.add(noData);
                    v += 10;
                }
            }
        }
        return datas;
    }

    /**
     * 获取该单位的一个周围单位
     */
    public ManData getZW1(ManData data) {
        int camp = data.getCamp();
        int man = data.getMan();
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData noData = this.fightingdata.get(i);
            if (noData.getType() != 3 && noData.getType() != 4 && noData.getStates() == 0 && noData.getCamp() == camp) {
                int noman = noData.getMan();
                if (man != noman && (man == noman + 1 || man == noman - 1 || man == noman + 5 || man == noman - 5) && (man != 5 || noman != 4) && (man != 4 || noman != 5) && (noData.xzstate("封印") == null && noData.xzstate("隐身") == null)) {
                    return noData;
                }
            }
        }
        return null;
    }

    /**
     * 获取该单位的周围一格的隐身单位
     */
    public List<ManData> getZWYS(ManData data) {
        List<ManData> datas = new ArrayList<>();
        int camp = data.getCamp();
        int man = data.getMan();
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData noData = this.fightingdata.get(i);
            if (noData.getType() != 3 && noData.getType() != 4 && noData.getStates() == 0 && noData.getCamp() == camp) {
                int noman = noData.getMan();
                if (man != noman && (man == noman + 1 || man == noman - 1 || man == noman + 5 || man == noman - 5) && (man != 5 || noman != 4) && (man != 4 || noman != 5) && noData.xzstate("隐身") != null) {
                    datas.add(noData);
                }
            }
        }
        return datas;
    }

    /**
     * 获取一个与目标同阵营的隐身单位
     */
    public List<ManData> getYSDR(ManData data) {
        List<ManData> datas = new ArrayList<>();
        int camp = data.getCamp();
        int man = data.getMan();
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData noData = this.fightingdata.get(i);
            if (noData.getType() != 3 && noData.getType() != 4 && noData.getStates() == 0 && noData.getCamp() == camp) {
                int noman = noData.getMan();
                if (man != noman && noData.xzstate("隐身") != null) {
                    datas.add(noData);
                }
            }
        }
        return datas;
    }

    //新增灵宝技能
    public static void Delejiaohuan(ManData manData, ManData manData1) {
        Battlefield.jiohuan.add(manData);
        Battlefield.jiohuan.add(manData1);
    }

    public void huifu() {
        if (Battlefield.jiohuan.size() == 0) {
            return;
        }
        if (Battlefield.jiohuan.get(0).xzstate("精神错乱") != null) {
            return;
        }
        System.out.print("开始交换");
        Battlefield.jiohuan.get(0).setSkills(this.buffskill);
        Battlefield.jiohuan.get(1).setSkills(this.buffskill1);
        Battlefield.jiohuan.clear();
        this.buffskill.clear();
        this.buffskill1.clear();
    }

    public void huifu1() {
        boolean te = true;
        List<ManData> manData2 = new ArrayList<>();
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            if (this.fightingdata.get(i).xzstate("乾坤遮天") != null || this.fightingdata.get(i).xzstate("天蓬转世") != null) {
                te = false;
            } else {
                this.fightingdata.get(i).setQKZT(false);
                manData2.add(this.fightingdata.get(i));
            }
        }
        if (te) {
            for (int l = this.fightingdata.size() - 1; l >= 0; --l) {
                if (this.fightingdata.get(l).getbianshen()) {
                    this.att();
                }
            }
        }
    }

    public ManData getdata(int camp, int man) {
        for (ManData data : fightingdata) {
            if (data.getCamp() == camp && data.getMan() == man)
                return data;
        }
        return null;
    }

    /**
     * 被攻击
     */
    public void att() {
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            FightingEvents events = new FightingEvents();
            FightingState org3 = new FightingState();
            List<FightingState> Accepterlist = new ArrayList<>();
            org3.setCamp(this.fightingdata.get(i).getCamp());
            org3.setMan(this.fightingdata.get(i).getMan());
            org3.setStartState("代价");
            Accepterlist.add(org3);
            this.fightingdata.get(i).setbianshen(false);
            events.setAccepterlist(Accepterlist);
            this.NewEvents.add(events);
        }
    }

    /**
     * 获取该单位的同排单位
     */
    public List<ManData> getTP(int camp, FightingEvents events) {
        List<ManData> datas = new ArrayList<>();
        int v = 0;
        if (events.getAccepterlist() != null && events.getAccepterlist().size() != 0) {
            v = events.getAccepterlist().get(0).getMan() / 5;
        }
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData noData = this.fightingdata.get(i);
            if (noData.getType() != 3 && noData.getType() != 4 && noData.getStates() == 0 && noData.getCamp() == camp) {
                int noman = noData.getMan();
                if ((v == noman || v == noman - 1 || v == noman - 2 || v == noman - 3 || v == noman - 4) && noData.xzstate("封印") == null && noData.xzstate("隐身") == null) {
                    datas.add(noData);
                }
            }
        }
        return datas;
    }

    /**
     * 获取偷钱金额
     */
    public long getStealing() {
        long money = 0L;
        for (int i = 0; i < this.fightingdata.size(); ++i) {
            ManData data = this.fightingdata.get(i);
            if (data.getType() == 0) {
                money = data.getMoney();
            } else if (data.getType() == 2 && data.getStates() == 2) {
                money = 0L;
                break;
            }
        }
        return money;
    }

    /**
     * 百分制概率
     */
    public static boolean isV(double value) {
        return value > (double) Battlefield.random.nextInt(100);
    }

    public static boolean isV(double value, int value1) {
        return value > (double) Battlefield.random.nextInt(value1);
    }

    /**
     * 根据名字获取对应mandata
     */
    public ManData getBattleEndData(String name) {
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData manData = this.fightingdata.get(i);
            if (manData.getType() == 0 && manData.getStates() != 2 && manData.getManname().equals(name)) {
                return manData;
            }
        }
        return null;
    }

    /**
     * 获取同阵营玩家
     */
    public List<ManData> getBattleRoleDataByCamp(int camp) {
        return (List) this.fightingdata.stream().filter(manData/* come.tool.FightingData.ManData, */ -> manData.getType() == 0 && manData.getCamp() == camp).collect(Collectors.toList());
    }

    /**
     * 根据名字获取对应mandata
     */
    public ManData getBattleEndData(String name, int camp) {
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData manData = this.fightingdata.get(i);
            if (manData.getManname().equals(name)) {
                return manData;
            }
        }
        return null;
    }

    /**
     * true 表示会掉 判断战斗类型是否会掉血法忠诚
     */
    public boolean isFightType() {
        return this.BattleType != 5 && this.BattleType != 11 && this.BattleType != 12 && this.BattleType != 31 && this.BattleType != 33 && this.BattleType != 34;
    }

    /**
     * 判断是否有该种族
     */
    public boolean isRace(int value) {
        int sex = value % 2;
        int race = (value <= 2) ? 10001 : ((value <= 4) ? 10002 : ((value <= 6) ? 10003 : ((value <= 8) ? 10004 : 10005)));
        String raceSting = null;
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData manData = this.fightingdata.get(i);
            if (manData.getCamp() == 1) {
                if (manData.getType() == 0) {//判断人物
                    if (Sepcies_MixDeal.getSex(manData.getSe()) == sex && Sepcies_MixDeal.getRace(manData.getSe()) == race) {
                        return true;
                    }
                } else if (manData.getType() == 2) {//判断伙伴
//				男人-1,女人-2,男魔-3,女魔-4,男仙-5,女仙-6,男鬼-7,女鬼-8,男龙-9,女龙-10
//				魔族（男） 仙族（男） 人族（男） 鬼族（男）
//				仙族（女） 魔族（女） 鬼族（男） 仙族（女）
//				人族（男） 鬼族（女）
                    PalData palData = GameServer.getPalData(manData.getId());
                    if (palData != null) {
                        if (raceSting == null) {
                            raceSting = ((value == 1) ? "人族（男）" : ((value == 2) ? "人族（女）" : ((value == 3) ? "魔族（男）" : ((value == 4) ? "魔族（女）" : ((value == 5) ? "仙族（男）" : ((value == 6) ? "仙族（女）" : ((value == 7) ? "鬼族（男）" : ((value == 8) ? "鬼族（女）" : ((value == 9) ? "龙族（男）" : "龙族（女）")))))))));
                        }
                        if (palData.getTrait().equals(Integer.valueOf(value))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public List<ManStateData> getManState() {
        List<ManStateData> reslut = new ArrayList<>();
        if (this.fightingdata != null && this.fightingdata.size() > 0) {
            for (ManData man : this.fightingdata) {
                ManStateData manStateData = new ManStateData();
                manStateData.setAddStates(man.getAddStates());
                manStateData.setCamp(man.getCamp());
                manStateData.setId(man.getId());
                manStateData.setManname(man.getManname());
                manStateData.setMan(man.getMan());
                manStateData.setType(man.getType());
                manStateData.setStates(man.getStates());
                reslut.add(manStateData);
            }
        }
        return reslut;
    }

    private static String getPetSkillswlLevel(String petSkillswl, String skillId) {
        try {
            String[] split = petSkillswl.split("\\|");
            Optional<String> first = Arrays.stream(split).filter(item/* java.lang.String, */ -> item.contains(skillId)).findFirst();
            if (first != null) {
                String s = first.get();
                return s.split("=")[1];
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 找到是否存在指定位置 不存在添加在后面
     */
    public boolean AddLingbao(ManData bbData, int mycamp, int myman) {
        for (int i = 0; i < this.fightingdata.size(); ++i) {
            int camp = this.fightingdata.get(i).getCamp();
            int man = this.fightingdata.get(i).getMan();
            if (bbData != null) {
                if (camp == bbData.getCamp() && man == bbData.getMan()) {
                    int path = this.Datapath(camp, man - 10);
                    this.Petplay(this.fightingdata.get(i), this.fightingdata.get(path), 2);
                    this.fightingdata.get(i).clearAddStates();
                    this.fightingdata.set(i, bbData);
                    this.Petplay(this.fightingdata.get(i), this.fightingdata.get(path), 1);
                    return true;
                }
            } else if (camp == mycamp && man == myman) {
                this.fightingdata.get(i).setStates(2);
                this.fightingdata.get(i).clearAddStates();
                this.Petplay(this.fightingdata.get(i), this.fightingdata.get(this.Datapath(camp, man - 10)), 2);
                return true;
            }
        }
        if (bbData != null) {
            this.Petplay(bbData, this.fightingdata.get(this.Datapath(bbData.getCamp(), bbData.getMan() - 10)), 1);
            this.fightingdata.add(bbData);
        }
        return false;
    }

    public boolean islingbaoskill(ManData data, String mes) {
        AddState addState = data.xzstate(mes);
        return addState != null;
    }

    public List<ManData> getJiohuan() {
        return Battlefield.jiohuan;
    }

    public void setJiohuan(List<ManData> jiohuan) {
    }

    public List<FightingSkill> getBuffskill() {
        return this.buffskill;
    }

    public void setBuffskill(List<FightingSkill> buffskill) {
        this.buffskill = buffskill;
    }

    public List<FightingSkill> getBuffskill1() {
        return this.buffskill1;
    }

    public void setBuffskill1(List<FightingSkill> buffskill1) {
        this.buffskill1 = buffskill1;
    }

    public List<ManData> getWaitList() {
        return this.waitList;
    }

    public void setWaitList(List<ManData> waitList) {
        this.waitList = waitList;
    }

    private static void jwzz(ManData myData, String[] skillTypes) {
        int length = skillTypes.length;
        int i = 0;
        while (i < length) {
            String skillType = skillTypes[i];
            FightingSkill skill = myData.getSkillType(skillType);
            if (skill != null && isV(30.0)) {
                myData.getQuality().addHSRF(skill, splitSkillNames(skill.getSkillname()));
                myData.getSkills().remove(skill);
                break;
            } else {
                ++i;
            }
        }
    }

    private static void jwzz(ManData myData, int id) {
        String[] skillTypes = null;
        if (id >= 1016 && id <= 1020) {//毒法
            skillTypes = new String[]{TypeUtil.TY_R_JWZZ_DF, TypeUtil.TY_R_JWZZ_DS};
        } else if (id >= 1011 && id <= 1015) {//昏睡
            skillTypes = new String[]{TypeUtil.TY_R_JWZZ_SH, TypeUtil.TY_R_JWZZ_SF, TypeUtil.TY_R_JWZZ_SD};
        } else if (id >= 1006 && id <= 1010) {//封印
            skillTypes = new String[]{TypeUtil.TY_R_JWZZ_FH, TypeUtil.TY_R_JWZZ_FS, TypeUtil.TY_R_JWZZ_FD};
        } else if (id >= 1001 && id <= 1005) {//混乱
            skillTypes = new String[]{TypeUtil.TY_R_JWZZ_HS, TypeUtil.TY_R_JWZZ_HF};
        }
        if (skillTypes != null) {
            jwzz(myData, skillTypes);
        }
    }

    //10024|御微知著(雷火)|释放雷法时，自身忽视抗雷有30%几率增加，数值等于{公式一}%点加自身忽视抗火数值的{公式零零}%。
    private static void wgzx(ManData myData, String[] skillTypes) {
        int length = skillTypes.length;
        int i = 0;
        while (i < length) {
            String skillType = skillTypes[i];
            FightingSkill skill = myData.getSkillType(skillType);
            if (skill != null && isV(30.0)) {
                myData.getQuality().adQRF(skill, splitSkillNames(skill.getSkillname()));
                myData.getSkills().remove(skill);
                break;
            } else {
                ++i;
            }
        }
    }

    private static void wgzx(ManData myData, int id) {
        String[] skillTypes = null;
        if (id >= 1016 && id <= 1020) {
            skillTypes = new String[]{TypeUtil.TY_R_WGZX_DF, TypeUtil.TY_R_WGZX_DS};
        } else if (id >= 1011 && id <= 1015) {
            skillTypes = new String[]{TypeUtil.TY_R_WGZX_SH, TypeUtil.TY_R_WGZX_SF, TypeUtil.TY_R_WGZX_SD};
        } else if (id >= 1006 && id <= 1010) {
            skillTypes = new String[]{TypeUtil.TY_R_WGZX_FH, TypeUtil.TY_R_WGZX_FS, TypeUtil.TY_R_WGZX_FD};
        } else if (id >= 1001 && id <= 1005) {
            skillTypes = new String[]{TypeUtil.TY_R_WGZX_HS, TypeUtil.TY_R_WGZX_HF};
        }
        if (skillTypes != null) {
            wgzx(myData, skillTypes);
        }
    }

    private static void ywzz(ManData myData, String[] skillTypes) {
        int length = skillTypes.length;
        int i = 0;
        while (i < length) {
            String skillType = skillTypes[i];
            FightingSkill skill = myData.getSkillType(skillType);
            if (skill != null && isV(30.0)) {
                myData.getQuality().addHSXF(skill, splitSkillNames(skill.getSkillname()));
                myData.getSkills().remove(skill);
                break;
            } else {
                ++i;
            }
        }
    }

    private static void ywzz(ManData myData, int id) {
        String[] skillTypes = null;
        if (id >= 1041 && id <= 1045) {//风法
            skillTypes = new String[]{"9985", "9995"};
        } else if (id >= 1046 && id <= 1050) {//雷法
            skillTypes = new String[]{"9974", "9984", "10024"};
        } else if (id >= 1051 && id <= 1055) {//水法
            skillTypes = new String[]{"9975", "9994", "10034"};
        } else if (id >= 1056 && id <= 1060) {//火法
            skillTypes = new String[]{"10025", "10035"};
        }
        if (skillTypes != null) {
            ywzz(myData, skillTypes);
        }
    }

    //9986|知此识彼(雷风)|释放雷法时，自身加强雷有30%几率增加，数值等于{公式一}%点加自身加强风数值的{公式零零}%。
    private static void zcsb(ManData myData, String[] skillTypes) {
        int length = skillTypes.length;
        int i = 0;
        while (i < length) {
            String skillType = skillTypes[i];
            FightingSkill skill = myData.getSkillType(skillType);
            if (skill != null && isV(30.0)) {
                myData.getQuality().addQXF(skill, splitSkillNames(skill.getSkillname()));
                myData.getSkills().remove(skill);
                break;
            } else {
                ++i;
            }
        }
    }

    private static void zcsb(ManData myData, int id) {
        String[] skillTypes = null;
        if (id >= 1041 && id <= 1045) {
            skillTypes = new String[]{"9987", "9997"};
        } else if (id >= 1046 && id <= 1050) {
            skillTypes = new String[]{"9976", "9986", "10026"};
        } else if (id >= 1051 && id <= 1055) {
            skillTypes = new String[]{"9977", "9996", "10036"};
        } else if (id >= 1056 && id <= 1060) {
            skillTypes = new String[]{"10027", "10037"};
        }
        if (skillTypes != null) {
            zcsb(myData, skillTypes);
        }
    }

    public static void TY_L_Lgyx(FightingSkill skill, ManData myData) {
        String[] lz = {TypeUtil.TY_L_10144, TypeUtil.TY_L_10145, TypeUtil.TY_L_10149, TypeUtil.TY_L_10150, TypeUtil.TY_L_10154, TypeUtil.TY_L_10155, TypeUtil.TY_L_10159, TypeUtil.TY_L_10160, TypeUtil.TY_L_10164, TypeUtil.TY_L_10165};
        int id = skill.getSkillid();
        for (String s : lz) {
            FightingSkill jdrt = myData.getSkillType(s);
            if (jdrt != null && isV(50.0)) {
                String name = splitSkillNames(jdrt.getSkillname());
                if (name.startsWith("雨") && id >= 1091 && id <= 1095) {//每次使用甘霖
                    myData.getQuality().addLZQF(jdrt, name);
                } else if (name.startsWith("涌") && id >= 1086 && id <= 1090) {
                    myData.getQuality().addLZQF(jdrt, name);//每次使用沧波
                } else if (name.startsWith("霹") && id >= 1081 && id <= 1085) {//每次使用霹雳
                    myData.getQuality().addLZQF(jdrt, name);
                }//每次使用扶摇
                else if (name.startsWith("摇") && id >= 1096 && id <= 1100) {//每次使用扶摇
                    myData.getQuality().addLZQF(jdrt, name);
                }
            }
        }
    }

    private void Ghostshadows(FightingSkill skill, ManData myData) {
        String[] lz = {TypeUtil.TY_G_10087, TypeUtil.TY_G_10088, TypeUtil.TY_G_10092, TypeUtil.TY_G_10093, TypeUtil.TY_G_10097, TypeUtil.TY_G_10098, TypeUtil.TY_G_10102, TypeUtil.TY_G_10103, TypeUtil.TY_G_10107, TypeUtil.TY_G_10108};
        int id = skill.getSkillid();
        for (String s : lz) {
            FightingSkill jdrt = myData.getSkillType(s);
            if (jdrt != null) {
                String name = splitSkillNames(jdrt.getSkillname());
                if (((name.startsWith("忘") && (id == 1073 || id == 1075)) || (name.startsWith("冥") && (id == 1063 || id == 1065)) || (name.startsWith("蛊") && (id == 1068 || id == 1070)) || (name.startsWith("惑") && (id == 1078 || id == 1080))) && isV(jdrt.getSkillhurt())) {
                    if (name.endsWith("忘")) {
                        myData.tempJlyw = 1;
                    } else if (name.endsWith("冥")) {
                        myData.tempJlgh = 1;
                    } else if (name.endsWith("蛊")) {
                        myData.tempJlss = 1;
                    } else if (name.endsWith("惑")) {
                        myData.tempJlmh = 1;
                    }
                }
            }
        }
    }

    private void castSpell(FightingSkill skill, ManData data, FightingEvents zl) {
        String[] lz = {TypeUtil.TY_G_10086, TypeUtil.TY_G_10091, TypeUtil.TY_G_10096, TypeUtil.TY_G_10101, TypeUtil.TY_G_10106};
        int id = skill.getSkillid();
        int length = lz.length;
        int i = 0;
        while (i < length) {
            String s = lz[i];
            FightingSkill jdrt = data.getSkillType(s);
            if (jdrt != null && !data.isLicense(skill) && isV(jdrt.getSkillhurt())) {//触发法力不足
                String name = splitSkillNames(jdrt.getSkillname());
                if (name.contains("忘")) {
                    if (id == 1075) {//遗忘5
                        skill = data.skillId(1073);//改变遗忘3
                        SpellActionType.getActionById(1).spellAction(data, skill, zl, this);
                        break;
                    } else if (id == 1074) {//遗忘4
                        skill = data.skillId(1072);//改变遗忘2
                        SpellActionType.getActionById(1).spellAction(data, skill, zl, this);
                        break;
                    } else {
                        break;
                    }
                } else if (name.contains("冥")) {
                    if (id == 1065) {//鬼火5
                        skill = data.skillId(1063);//改变鬼火3
                        SpellActionType.getActionById(11).spellAction(data, skill, zl, this);
                        break;
                    } else if (id == 1064) {//鬼火4
                        skill = data.skillId(1062);//改变鬼火2
                        SpellActionType.getActionById(11).spellAction(data, skill, zl, this);
                        break;
                    } else {
                        break;
                    }
                } else if (name.contains("蛊")) {
                    if (id == 1070) {//蛊5
                        skill = data.skillId(1068);//改变3
                        SpellActionType.getActionById(8).spellAction(data, skill, zl, this);
                        break;
                    } else if (id == 1069) {//4
                        skill = data.skillId(1067);//2
                        SpellActionType.getActionById(8).spellAction(data, skill, zl, this);
                        break;
                    } else {
                        break;
                    }
                } else if (name.contains("惑")) {
                    if (id == 1080) {//惑5
                        skill = data.skillId(1078);//改变3
                        SpellActionType.getActionById(1).spellAction(data, skill, zl, this);
                        break;
                    } else if (id == 1079) {//4
                        skill = data.skillId(1077);//2
                        SpellActionType.getActionById(1).spellAction(data, skill, zl, this);
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            } else {
                ++i;
            }
        }
    }

    private static void applqSkillEffect(ManData myData, int id) {
        String[] skillTypes = new String[]{TypeUtil.TY_R_LQQM_SH, TypeUtil.TY_R_LQQM_FH, TypeUtil.TY_R_LQQM_FS, TypeUtil.TY_R_LQQM_FD, TypeUtil.TY_R_LQQM_DS};
        int length = skillTypes.length;
        int i = 0;
        while (i < length) {
            String skill = skillTypes[i];
            FightingSkill skillType = myData.getSkillType(skill);
            if (skillType != null && isV(30.0)) {
                switch (id) {
                    case 1019: {
                        applyState(myData, "灵通_毒", myData.ltd, 0.0, skillType.getSkillgain());
                        break;
                    }
                    case 1014: {
                        applyState(myData, "灵通_睡", myData.lts, skillType.getSkillhurt(), 0.0);
                        break;
                    }
                    case 1009: {
                        applyState(myData, "灵通_封", myData.ltf, skillType.getSkillhurt(), 0.0);
                        break;
                    }
                    case 1004: {
                        applyState(myData, "灵通_混", myData.lth, skillType.getSkillhurt(), 0.0);
                        break;
                    }
                    case 1020: {
                        applyQualityEffect(myData, "灵通_毒");
                        break;
                    }
                    case 1010: {
                        applyQualityEffect(myData, "灵通_封");
                        break;
                    }
                    case 1005: {
                        applyQualityEffect(myData, "灵通_混");
                        break;
                    }
                    case 1015: {
                        applyQualityEffect(myData, "灵通_睡");
                        break;
                    }
                }
                break;
            } else {
                ++i;
            }
        }
    }

    private static void applyQualityEffect(ManData myData, String stateName) {
        AddState addState = myData.xzstate(stateName);
        if (addState != null) {
            int n = -1;
            switch (stateName.hashCode()) {
                case 893134936: {
                    if (stateName.equals("灵通_毒")) {
                        n = 0;
                        break;
                    } else {
                        break;
                    }
                }
                case 893130887: {
                    if (stateName.equals("灵通_封")) {
                        n = 1;
                        break;
                    } else {
                        break;
                    }
                }
                case 893135485: {
                    if (stateName.equals("灵通_混")) {
                        n = 2;
                        break;
                    } else {
                        break;
                    }
                }
                case 893137895: {
                    if (stateName.equals("灵通_睡")) {
                        n = 3;
                        break;
                    } else {
                        break;
                    }
                }
            }
            switch (n) {
                case 0: {
                    myData.getQuality().addlszt_d(addState, true);
                    break;
                }
                case 1: {
                    myData.getQuality().addlszt_f(addState, true);
                    break;
                }
                case 2: {
                    myData.getQuality().addlszt_h(addState, true);
                    break;
                }
                case 3: {
                    myData.getQuality().addlszt_s(addState, true);
                    break;
                }
            }
        }
    }

    private static void appyjSkillEffect(ManData myData, int id) {
        String[] skillTypes = new String[]{TypeUtil.TY_R_YJSD_SH, TypeUtil.TY_R_YJSD_FH, TypeUtil.TY_R_YJSD_FS, TypeUtil.TY_R_YJSD_FD, TypeUtil.TY_R_YJSD_SD};
        int length = skillTypes.length;
        int i = 0;
        while (i < length) {
            String skill = skillTypes[i];
            FightingSkill skillType = myData.getSkillType(skill);
            if (skillType != null && isV(30.0)) {
                switch (id) {
                    case 1020: {
                        applyState(myData, "灵识_毒", myData.lsd, 0.0, skillType.getSkillgain());
                        break;
                    }
                    case 1015: {
                        applyState(myData, "灵识_睡", myData.lss, skillType.getSkillhurt(), 0.0);
                        break;
                    }
                    case 1010: {
                        applyState(myData, "灵识_封", myData.lsf, skillType.getSkillhurt(), 0.0);
                        break;
                    }
                    case 1005: {
                        applyState(myData, "灵识_混", myData.lsh, skillType.getSkillhurt(), 0.0);
                        break;
                    }
                    case 1019: {
                        applqQualityEffect(myData, "灵识_毒");
                        break;
                    }
                    case 1009: {
                        applqQualityEffect(myData, "灵识_封");
                        break;
                    }
                    case 1004: {
                        applqQualityEffect(myData, "灵识_混");
                        break;
                    }
                    case 1014: {
                        applqQualityEffect(myData, "灵识_睡");
                        break;
                    }
                }
                break;
            } else {
                ++i;
            }
        }
    }

    private static void applyState(ManData myData, String stateName, int value, double skillGain, double skillHurt) {
        if (value <= 10) {
            ++value;
            if (skillHurt != 0.0) {
                myData.addAddState(stateName, 0.0, (double) value * skillGain / 100.0, 99);
            } else if (skillGain != 0.0) {
                myData.addAddState(stateName, (double) value * skillHurt / 100.0, 0.0, 99);
            }
        }
    }

    private static void applqQualityEffect(ManData myData, String stateName) {
        AddState addState = myData.xzstate(stateName);
        if (addState != null) {
            int n = -1;
            switch (stateName.hashCode()) {
                case 892070148: {
                    if (stateName.equals("灵识_毒")) {
                        n = 0;
                        break;
                    } else {
                        break;
                    }
                }
                case 892066099: {
                    if (stateName.equals("灵识_封")) {
                        n = 1;
                        break;
                    } else {
                        break;
                    }
                }
                case 892070697: {
                    if (stateName.equals("灵识_混")) {
                        n = 2;
                        break;
                    } else {
                        break;
                    }
                }
                case 892073107: {
                    if (stateName.equals("灵识_睡")) {
                        n = 3;
                        break;
                    } else {
                        break;
                    }
                }
            }
            switch (n) {
                case 0: {
                    myData.getQuality().addlszt_d(addState, true);
                    break;
                }
                case 1: {
                    myData.getQuality().addlszt_f(addState, true);
                    break;
                }
                case 2: {
                    myData.getQuality().addlszt_h(addState, true);
                    break;
                }
                case 3: {
                    myData.getQuality().addlszt_s(addState, true);
                    break;
                }
            }
        }
    }

    public static double getSkillhurt(FightingSkill skill, String name) {
        String names = splitSkillNames(skill.getSkillname());
        if (names.startsWith(name)) {
            return skill.getSkillhurt();
        }
        if (names.endsWith(name)) {
            return skill.getSkillgain();
        }
        return 0.0;
    }

    public static boolean getSkillIsV(FightingSkill skill, String name) {
        return isV(getSkillhurt(skill, name));
    }

    public static String splitSkillNames(String input) {
        String patternString = "\\(([^)]+)\\)";// 使用正则表达式找到括号内的内容
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(input);
        String contentInBrackets = null;
        if (matcher.find()) { // 提取括号内的文本，无需分割
            contentInBrackets = matcher.group(1);
        }
        return contentInBrackets;
    }

    private void Ydsf(ManData myData) {
        FightingSkill TY_G_10078 = myData.getSkillType(TypeUtil.TY_G_10079);
        if (TY_G_10078 != null) {
            for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
                ManData data2 = this.fightingdata.get(i);
                if (data2.getCamp() == 0 && data2.xzstate(TypeUtil.MH) != null) {
                    FightingEvents fEvents = new FightingEvents();
                    List<FightingState> Accepterlist = new ArrayList<>();
                    FightingState fightingState = new FightingState();
                    ChangeFighting changeFighting = new ChangeFighting();
                    changeFighting.setChangemp((int) ((double) data2.getMp_z() * TY_G_10078.getSkillhurt() / 100.0));
                    data2.ChangeData(changeFighting, fightingState);
                    fightingState.setStartState("代价");
                    Accepterlist.add(fightingState);
                    fEvents.setAccepterlist(Accepterlist);
                    this.NewEvents.add(fEvents);
                }
            }
        }
    }

    /**
     * 找出敌方阵营中SP最高 且还活着的人 始终是对面的
     */
    public ManData maxsp(int camp) {
        int sp = 500;
        int path = -1;
        for (int i = 0; i < this.fightingdata.size(); ++i) {
            ManData data = this.fightingdata.get(i);
            if (camp == 2) {
                if (data.getSp() > sp) {
                    sp = data.getSp();
                    path = i;
                }
            } else if (camp == 1 && data.getSp() > sp) {
                sp = data.getSp();
                path = i;
            }
        }
        if (path != -1) {
            return (ManData) this.fightingdata.get(path);
        }
        return null;
    }

    private void Xdsy(ManData data, FightingSkill skill) {
        int camp = data.getCamp();
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData data2 = this.fightingdata.get(i);
            if (camp != data2.getCamp() && data2.getStates() == 0 && PK_MixDeal.isPK(this.BattleType) && data2.xzstate(TypeUtil.MH) != null) {
                FightingSkill TY_G_10078 = data2.getSkillType(TypeUtil.TY_G_10078);
                if (TY_G_10078 != null) {
                    double hf = skill.getSkillhurt() * TY_G_10078.getSkillhurt() / 100.0;
                    FightingEvents fEvents = new FightingEvents();
                    List<FightingState> Accepterlist = new ArrayList<>();
                    FightingState fightingState = new FightingState();
                    ChangeFighting changeFighting = new ChangeFighting();
                    changeFighting.setChangemp((int) hf);
                    data.ChangeData(changeFighting, fightingState);
                    fightingState.setStartState("药");
                    Accepterlist.add(fightingState);
                    fEvents.setAccepterlist(Accepterlist);
                    this.NewEvents.add(fEvents);
                }
            }
        }
    }

    public Integer skillNum(FightingSkill skill, int num) {
        int id = skill.getSkillid();
        switch (id) {
            case 1063:
            case 1065:
            case 1070:
            case 1073:
            case 1078: {
                return 6;
            }
            case 1075:
            case 1080: {
                return 7;
            }
            case 1068: {
                return 5;
            }
            default: {
                return num;
            }
        }
    }

    public List<ManData> getFightingdata() {
        return this.fightingdata;
    }

    public void setFightingdata(List<ManData> fightingdata) {
        this.fightingdata = fightingdata;
    }

    public List<ManData> getDieManData(ManData data) {
        List<ManData> datas = new ArrayList<>();
        int camp = data.getCamp();
        int man = data.getMan();
        for (int i = this.fightingdata.size() - 1; i >= 0; --i) {
            ManData noData = this.fightingdata.get(i);
            if (noData.getType() != 3 && noData.getType() != 4 && noData.getStates() == 0 && noData.getHp() > 0 && noData.getCamp() == camp) {
                int noman = noData.getMan();
                if (man != noman && (noData.xzstate("封印") == null && noData.xzstate("隐身") == null)) {
                    datas.add(noData);
                }
            }
        }
        return datas;
    }

    public List<ManData> exitMandaByCmap(int camp) {
        List<ManData> objects = new ArrayList<>();
        for (ManData data : this.fightingdata) {
            if (data.getStates() == 0 && data.getType() < 3 && data.getCamp() == camp) {
                objects.add(data);
            }
        }
        return objects;
    }

    public void zhaohuan(ManData manData, String type, Battlefield battlefield, String skillSkin) {
        FightingEvents fightingEvents = new FightingEvents();
        FightingState fightingState = new FightingState();
        if (!manData.jtcl && !manData.fhft) {
            manData.executeYzsg(null, fightingState);
            manData.clearAddStates();
        }
        if (manData.getType() == 1) {
            manData.getSkills().clear();
            for (FightingSkill fightingSkill : manData.getSkillsCopy()) {
                manData.addSkill(fightingSkill, battlefield.CurrentRound);
            }
            if (manData.jtcl) {
                manData.loadLingXiSkill();
                fightingState.setText("看我的卷土重来");
            } else if (manData.fhft) {
                manData.loadLingXiSkill();
            }
        }
        fightingState.setStartState(type);
        fightingState.setCamp(manData.getCamp());
        fightingState.setMan(manData.getMan());
        manData.setStates(0);
        FightingManData fightingManData = new FightingManData();
        fightingManData.setModel(manData.getModel());
        fightingManData.setManname(manData.getManname());
        fightingManData.setCamp(manData.getCamp());
        fightingManData.setMan(manData.getMan());
        fightingManData.setHp_Current((long) manData.getHp());
        fightingManData.setHp_Total((long) manData.getHp_z());
        fightingManData.setMp_Current(manData.getMp());
        fightingManData.setMp_Total(manData.getMp_z());
        fightingManData.setState_1(manData.xz());
        fightingManData.setType(manData.getType());
        fightingManData.setSpeciesid(manData.getSpeciesid());
        fightingManData.setManname(manData.getManname());
        fightingManData.setZs(manData.getZs());
        fightingManData.setMsg(manData.getMsg());
        fightingManData.setYqz(manData.getYqz());
        fightingManData.setNqz(manData.getNqz());
        fightingManData.setStates(manData.ztstlist(fightingManData));
        fightingManData.setId(manData.getId());
        if (manData.getSkillType("隐身") != null) {
            fightingState.setEndState_1("隐身");
            AddState addState = new AddState();
            addState.setStatename("隐身");
            addState.setSurplus(3);
            manData.getAddStates().add(addState);
            fightingManData.setAlpha(0.3f);
        }
        if (manData.fhft) {
            manData.fhft = false;
        }
        int my = 0;
        int it = 0;
        for (ManData data : battlefield.fightingdata) {
            if (data.getStates() == 0 && data.getType() < 3) {
                if (data.getCamp() == manData.getCamp()) {
                    ++my;
                } else {
                    ++it;
                }
            }
        }
        if (my < it) {
            manData.executeYztc(null, fightingState);
        }
        fightingState.setFightingManData(fightingManData);
        fightingEvents.setOriginator(fightingState);
        fightingState.setSkillskin(skillSkin);
        MixDeal.Approach(manData, fightingState, battlefield);
        battlefield.NewEvents.add(fightingEvents);
        if (isV((double) manData.executeBfbd(1))) {
            int wei = battlefield.Datapathhuo(manData.getCamp(), manData.getMan() - 5);
            if (wei != -1) {
                ManData master = (ManData) battlefield.fightingdata.get(wei);
                int kx = manData.executeBfbd(2);
                int i = Battlefield.random.nextInt(12) + 1;
                switch (i) {
                    case 1: {
                        master.getQuality().setRolekwl(master.getQuality().getRolekwl() + (double) kx);
                        break;
                    }
                    case 2: {
                        master.getQuality().setRolekzs(master.getQuality().getRolekzs() + (double) kx);
                        break;
                    }
                    case 3: {
                        master.getQuality().setRolekff(master.getQuality().getRolekff() + (double) kx);
                        break;
                    }
                    case 4: {
                        master.getQuality().setRoleklf(master.getQuality().getRoleklf() + (double) kx);
                        break;
                    }
                    case 5: {
                        master.getQuality().setRoleksf(master.getQuality().getRoleksf() + (double) kx);
                        break;
                    }
                    case 6: {
                        master.getQuality().setRolekhf(master.getQuality().getRolekhf() + (double) kx);
                        break;
                    }
                    case 7: {
                        master.getQuality().setRolekhl(master.getQuality().getRolekhl() + (double) kx);
                        break;
                    }
                    case 8: {
                        master.getQuality().setRolekhs(master.getQuality().getRolekhs() + (double) kx);
                        break;
                    }
                    case 9: {
                        master.getQuality().setRolekfy(master.getQuality().getRolekfy() + (double) kx);
                        break;
                    }
                    case 10: {
                        master.getQuality().setRolekzd(master.getQuality().getRolekzd() + (double) kx);
                        break;
                    }
                    case 11: {
                        master.getQuality().setRolekyw(master.getQuality().getRolekyw() + (double) kx);
                        break;
                    }
                    default: {
                        master.getQuality().setRolekgh(master.getQuality().getRolekgh() + (double) kx);
                        break;
                    }
                }
                List<FightingState> zls = new ArrayList<>();
                FightingState ace = new FightingState();
                ace.setCamp(manData.getCamp());
                ace.setMan(manData.getMan());
                ace.setText("看我的八风不动");
                zls.add(ace);
                FightingEvents event = new FightingEvents();
                event.setAccepterlist(zls);
                battlefield.NewEvents.add(event);
            }
        }
        FightingSkill cjdw = manData.getSkillId(1887);
        if (cjdw != null && PK_MixDeal.isPK(battlefield.BattleType)) {
            FightingEvents fe2 = new FightingEvents();
            List<FightingState> ac2 = new ArrayList<>();
            List<ManData> datas = MixDeal.get(false, null, 0, battlefield.nomy(manData.getCamp()), 0, 4, 1, 0, 1, 1, battlefield, 1);
            ManData parents = null;
            if (datas.size() > 0) {
                parents = (ManData) datas.get(0);
            }
            if (parents != null && parents.getStates() == 0) {
                FightingState Originator = new FightingState();
                if (isV(cjdw.getSkillgain())) {
                    parents.RemoveNegativeState(Originator);
                }
                FightingState fs2 = new FightingState();
                fs2.setStartState("法术攻击");
                fs2.setMan(manData.getMan());
                fs2.setCamp(manData.getCamp());
                FightingState fs3 = new FightingState();
                fs3.setStartState("代价");
                fs3.setSkillskin("1887");
                fs3.setMan(parents.getMan());
                fs3.setCamp(parents.getCamp());
                Originator.setStartState("代价");
                Originator.setCamp(parents.getCamp());
                Originator.setMan(parents.getMan());
                ChangeFighting fighting = new ChangeFighting();
                fighting.setChangehp((int) ((double) parents.getHp_z() * 0.5));
                fighting.setChangemp((int) ((double) parents.getMp_z() * 0.5));
                FightingPackage.ChangeProcess(fighting, null, parents, Originator, TypeUtil.JN, ac2, battlefield);
                ac2.add(fs2);
                ac2.add(fs3);
                fe2.setAccepterlist(ac2);
                battlefield.NewEvents.add(fe2);
            }
        }
        FightingSkill skill = manData.getSkillType(TypeUtil.BB_XFDG);
        if (skill != null) {
            List<ManData> datas2 = MixDeal.get(false, null, 0, battlefield.nomy(manData.getCamp()), 1, 0, 1, 0, 1, 1, battlefield, 1);
            if (datas2.size() == 0) {
                return;
            }
            ManData data2 = (ManData) datas2.get(0);
            FightingEvents fe3 = new FightingEvents();
            FightingState fs4 = new FightingState();
            fs4.setStartState(TypeUtil.JN);
            fs4.setSkillskin(skill.getSkilltype());
            List<FightingState> ac3 = new ArrayList<>();
            ChangeFighting fighting2 = new ChangeFighting();
            fighting2.setChangehp((int) ((double) data2.getHp_z() * 0.7));
            fighting2.setChangemp((int) ((double) data2.getMp_z() * 0.1));
            FightingPackage.ChangeProcess(fighting2, null, data2, fs4, TypeUtil.JN, ac3, battlefield);
            fe3.setAccepterlist(ac3);
            battlefield.NewEvents.add(fe3);
        }
        skill = manData.getSkillType(TypeUtil.BB_MSRX);
        if (skill != null) {
            List<ManData> datas2 = MixDeal.get(false, null, 0, battlefield.nomy(manData.getCamp()), 1, 0, 1, 0, 1, 3, battlefield, 1);
            if (datas2.size() == 0) {
                return;
            }
            ManData data2 = (ManData) datas2.get(0);
            FightingEvents fe3 = new FightingEvents();
            FightingState fs4 = new FightingState();
            fs4.setStartState(TypeUtil.JN);
            fs4.setSkillskin(skill.getSkilltype());
            List<FightingState> ac3 = new ArrayList<>();
            ChangeFighting fighting2 = new ChangeFighting();
            fighting2.setChangehp((int) ((double) data2.getHp_z() * 0.1));
            fighting2.setChangemp((int) ((double) data2.getMp_z() * 0.7));
            FightingPackage.ChangeProcess(fighting2, null, data2, fs4, TypeUtil.JN, ac3, battlefield);
            fe3.setAccepterlist(ac3);
            battlefield.NewEvents.add(fe3);
        }
        if (manData.getSkillType("1336") != null && isV(manData.getSkillType("1336").getSkillgain())) {
            FightingEvents fightingEvents2 = new FightingEvents();
            FightingState fightingState2 = new FightingState();
            fightingState2.setStartState("法术攻击");
            fightingState2.setSkillskin("1007");
            fightingState2.setEndState_1("封印");
            fightingState2.setText("强化封印#2");
            ChangeFighting changeFighting = new ChangeFighting();
            changeFighting.setChangetype("封印");
            changeFighting.setChangesum(1);
            changeFighting.setChangevlaue2(9999.0);
            manData.ChangeData(changeFighting, fightingState2);
            manData.setQHFY(true);
            List<FightingState> ac4 = new ArrayList<>();
            ac4.add(fightingState2);
            fightingEvents2.setAccepterlist(ac4);
            battlefield.NewEvents.add(fightingEvents2);
        }
        FightingSkill skill_4010 = manData.getSkillType("4010");
        AddState sh_4010 = manData.xzstate(TypeUtil.SH_4010);
        if (skill_4010 != null && sh_4010 == null && isV(skill_4010.getValue1())) {
            SummonType.xianzhi(manData, skill_4010);
            FightingEvents events2 = new FightingEvents();
            List<FightingState> Accepterlist2 = new ArrayList<>();
            FightingState state = new FightingState();
            ChangeFighting change = new ChangeFighting();
            change.setChangetype(TypeUtil.SH_4010);
            change.setChangevlaue(skill_4010.getValue2());
            change.setChangesum(3);
            manData.ChangeData(change, state);
            state.setStartState("代价");
            Accepterlist2.add(state);
            events2.setAccepterlist(Accepterlist2);
            FightingState fightingState3 = new FightingState();
            Accepterlist2.add(fightingState3);
            fightingState3.setCamp(manData.getCamp());
            fightingState3.setMan(manData.getMan());
            fightingState3.setStartState("代价");
            fightingState3.setProcessState("战气如虹");
            manData.addnq(10, fightingState3);
            fightingState3.setStartState(TypeUtil.JN);
            int[] yao = new int[4];
            yao[0] = (int) ((double) manData.getZHP_Z() * skill_4010.getValue2());
            ChangeFighting changeFighting2 = battlefield.Typeyao(manData, yao);
            manData.ChangeData(changeFighting2, Accepterlist2.get(Accepterlist2.size() - 1));
            yao[0] = 0;
            battlefield.NewEvents.add(events2);
        } else if (skill_4010 != null) {
            manData.getSkills().remove(skill_4010);
        }
    }

    static {
        Battlefield.random = new Random();
        Battlefield.currZl = null;
        Battlefield.overSp = 0;
        Battlefield.overcamp1sp = 0;
        Battlefield.overcamp2sp = 0;
    }

    //获取种族
    public PathPoint instructionspathZZ(int camp, ManData manData, String race_name) {
        List<ManData> raceMandatas = new ArrayList<>();
        for (ManData fightingdatum : fightingdata) {
            if (fightingdatum.getType() == 0 && fightingdatum.getRace_name() != null && fightingdatum.getRace_name().equals(race_name) && fightingdatum.getCamp() != camp) {
                raceMandatas.add(fightingdatum);
            }

        }
        if (raceMandatas.size() <= 0) {
            return null;
        }
        ManData data = raceMandatas.get(new Random().nextInt(raceMandatas.size()));
        return new PathPoint(data.getCamp(), data.getMan());
    }

    //获取种族
    public PathPoint instructionspathDZ(int camp, ManData manData, String race_name) {
        List<ManData> raceMandatas = new ArrayList<>();
        for (ManData fightingdatum : fightingdata) {
            if (fightingdatum.getDZ() && fightingdatum.getCamp() != camp) {
                raceMandatas.add(fightingdatum);
                break;
            }

        }
        if (raceMandatas.size() <= 0) {
            return null;
        }
        ManData data = raceMandatas.get(0);
        return new PathPoint(data.getCamp(), data.getMan());
    }

    //获取种族
    public PathPoint instructionspathzh(int camp, ManData manData, String race_name) {
        List<ManData> raceMandatas = new ArrayList<>();
        for (ManData fightingdatum : fightingdata) {
            if (fightingdatum.getType() == 1 && fightingdatum.getCamp() != camp) {
                raceMandatas.add(fightingdatum);
                break;
            }

        }
        if (raceMandatas.size() <= 0) {
            return null;
        }
        ManData data = raceMandatas.get(0);
        return new PathPoint(data.getCamp(), data.getMan());
    }

    public void XB_ZhuChouSuo(ManData data, ManData nodata, double probability) {
        if ((data.getType() == 2 || nodata.getType() == 2) && isV(100.0D - nodata.getSkillId(30018).getSkillgain() - probability))
            return;
        data.addAddState("诛仇索", nodata.getSkillId(30018).getSkillhurt(), nodata.getSkillId(30018).getP1(), nodata.getSkillId(30018).getE1(), 999);
//        System.out.println(nodata.getSkillId(30018).getE1());
//        System.out.println(data.xzstate("诛仇索").getStateEffect3());
        FightingEvents zl = new FightingEvents();
        FightingState accepter = new FightingState();
        accepter.setCamp(data.getCamp());
        accepter.setMan(data.getMan());
        accepter.setText("#G你被#Y" + data.getManname() + "#G的诛仇索命中");
        List<FightingState> Accepterlist = new ArrayList<>();
        accepter.setEndState_1("诛仇索");
        Accepterlist.add(accepter);
        zl.setAccepterlist(Accepterlist);
        this.NewEvents.add(zl);
    }
}
