package come.tool.FightingData;

import java.util.Arrays;
import java.util.Random;

import come.tool.FightingSpellAction.SSCAction;
import org.apache.commons.lang.StringUtils;
import come.tool.FightingDataAction.GxgfAction;
import com.gl.util.FaMenUtil;
import org.come.bean.PathPoint;

import java.util.List;

public class FightingPackage {
    //数据接受处理ChangeFighting  递归调用情况
    public static void ChangeProcess(ChangeFighting changeFighting, ManData mydata, ManData data, FightingState org2, String type, List<FightingState> list, Battlefield battlefield) {
        list.add(org2);
    //    changeFighting.setChangehp(1);//测试时候打开伤害为加1
        if ((type.equals("沧波") || type.equals("扶摇") || type.equals("霹雳") || type.equals("鬼火") || type.equals("风") || type.equals("雷") || type.equals("水") || type.equals("火") || type.equals("震慑") || type.equals("中毒") || type.equals("三尸虫")) && list.size() > 1 && data.getSkillType("4011") != null && Battlefield.isV(data.getSkillType("4011").getSkillgain())) {
            changeFighting.setChangehp((int) ((double) changeFighting.getChangehp() * (1.0 - data.getSkillType("4011").getSkillhurt() / 100.0)));
        }
        boolean longzu = false;
        if (type.equals("沧波") || type.equals("扶摇") || type.equals("霹雳")) {
            type = "普通攻击";
            longzu = true;
        }
        if ((type.equals("鬼火") || type.equals("风") || type.equals("雷") || type.equals("水") || type.equals("火") || type.equals("三尸虫")) && data.getSkillType("4012") != null) {
            for (int i = 0; i <= battlefield.fangyu.size() - 1; ++i) {
                if (battlefield.fangyu.get(i).getX() == data.getMan() && data.getCamp() == ((PathPoint) battlefield.fangyu.get(i)).getY() && Battlefield.isV(data.getSkillType("4012").getSkillgain())) {
                    changeFighting.setChangehp((int) ((double) changeFighting.getChangehp() * (1.0 - data.getSkillType("4012").getSkillhurt() / 100.0)));
                }
            }
        }
        String n;
        if (type.startsWith("霹雳end")) {
            n = type;
            if (type.endsWith("至圣")) {
                type = "至圣";
            } else {
                type = "普通攻击";
            }
        } else if (type.startsWith("霹雳")) {
            n = type;
            if (type.endsWith("至圣")) {
                type = "至圣";
            } else {
                type = "普通攻击";
            }
        } else if ("神力加身".equals(type)) {
            type = "普通攻击";
            n = "神力加身";
        } else if ("力挽狂澜".equals(type)) {
            type = "普通攻击";
            n = "力挽狂澜";
        } else if ("力挽狂澜".equals(type)) {
            type = "普通攻击";
            n = "势如破竹";
        } else if ("反击".equals(type)) {
            type = "普通攻击";
            n = "反击";
        } else {
            n = type;
        }
        if (data != null && data.xzstate(TypeUtil.LB_TTZS) != null) {
            FightingState fightingState = new FightingState();
            fightingState.setMan(data.getMan());
            fightingState.setCamp(data.getCamp());
            fightingState.setText("我乃天蓬转世#4你敢打我#24嚎~嚎~#29#29");
            list.add(fightingState);
        }
        AddState xzstate = data.xzstate(TypeUtil.TY_SSC_LFHX);
        if (xzstate != null && xzstate.getStateEffect() >= 5.0 && !type.equals("至圣")) {
            changeFighting.setChangehp(0);
            changeFighting.setChangemp(0);
            org2.setProcessState("免疫");
            org2.setMan(data.getMan());
            org2.setCamp(data.getCamp());
            return;
        }
        //获取是否为
        int yylz = 0;
        boolean pks = false;
        if (mydata != null && changeFighting.getChangehp() < 0) {
            yylz = data.getyylz();
            if (TypeUtil.PTGJ.equals(type)) {
                //判断是否有伤害加深
                FightingSkill fightingSkill = mydata.getSkillType("伤害加深");
                if (fightingSkill != null) {
                    double hurt = fightingSkill.getSkillhurt();
                    int skillcontinued = fightingSkill.getSkillcontinued();
                    fightingSkill = mydata.getSkillType(TypeUtil.BB_E_QHQSSW);
                    if (fightingSkill != null && fightingSkill != null) {
                        hurt *= 1.0 + fightingSkill.getSkillgain() / 100.0;
                    }
                    data.addAddState("伤害加深", hurt, 0.0, skillcontinued);
                }
                double fmJv = mydata.getFmJv(1, new String[]{"虎踞龙蟠"});
                if (Battlefield.isV(fmJv)) {
                    data.addAddState("虎踞龙蟠", (double) mydata.skillname("虎踞龙蟠").getSkillid(), (double) (int) mydata.getFmsld2(), 2);
                    org2.setEndState_1("虎踞龙蟠");
                }
                AddState addState = mydata.xzstate(TypeUtil.FB_JQB);
                if (addState != null) {
                    changeFighting.setChangemp(changeFighting.getChangemp() - (int) ((double) data.getAp() * addState.getStateEffect() / 100.0));
                }
                fightingSkill = data.getSkillType(TypeUtil.TJ_JMHM);
                if (fightingSkill != null && 33 > Battlefield.random.nextInt(100)) {
                    changeFighting.setChangehp(changeFighting.getChangehp() / 2);
                }
                //9816	气冲斗牛|被牛人物单位进行物理攻击时，增加{10}%点连击率(仅在与玩家之间战斗有效)
                FightingSkill skill2 = mydata.getAppendSkill(TypeUtil.TY_LL_QCDN);
                if (skill2 != null) {
                    mydata.ljv += (int) skill2.getSkillhurt();
                }
                //9207 拔山 释放一个强力的魔神附身，增加被牛的单位{公式一}%点破物理几率和破物理程度，持续3回合。（仅在与玩家之间战斗有效）
                skill2 = mydata.getAppendSkill(TypeUtil.TY_LL_BS);
                if (skill2 != null) {
                    mydata.pwljl += skill2.getSkillhurt();
                    mydata.pwlcd += skill2.getSkillhurt();
                }
            }
            if (data.getType() == 1) {
                changeFighting.setChangehp((int) ((double) changeFighting.getChangehp() * (1.0 + mydata.getQuality().getQ_zhssh() / 100.0)));
            }
            FightingSkill TY_R_RZRM = mydata.getSkillType(TypeUtil.TY_R_RZRM);
            if (TY_R_RZRM != null && Battlefield.isV(TY_R_RZRM.getSkillhurt()) && changeFighting.getChangehp() < -10) {
                pks = true;
            }
        }
        if (yylz == 0) {
            //利刃加身
            if (changeFighting.getChangehp() < 0) {
                AddState addState2 = data.xzstate("利刃加身");
                if (addState2 != null) {
                    int fmId = (int) addState2.getStateEffect();
                    int fmSld = (int) addState2.getStateEffect2();
                    int nrlrsh = FaMenUtil.getInt(fmId, fmSld, 2);
                    int nrlrshMax = FaMenUtil.getInt(fmId, fmSld, 3);
                    if (data.getNrlrjs() < nrlrshMax) {
                        nrlrshMax -= data.getNrlrjs();
                        if (nrlrsh > nrlrshMax) {
                            nrlrsh = nrlrshMax;
                        }
                        data.setNrlrjs(data.getNrlrjs() + nrlrsh);
                        int sh = changeFighting.getChangehp() - nrlrsh;
                        changeFighting.setChangehp(sh);
                    }
                }
                addState2 = data.xzstate("积羽沉舟");
                if (addState2 != null) {
                    if (data.getNrjycz() > 0) {
                        int nrjycz = data.getNrjycz();
                        int sh2 = changeFighting.getChangehp() - nrjycz;
                        changeFighting.setChangehp(sh2);
                    }
                } else if (!type.equals("积羽沉舟")) {
                    data.setNrjycz(0);
                }
            }
            //黑龙珠 或者 七宝玲珑塔 反弹使用
            ChangeFighting fbft = null;
            //将军令使用
            ChangeFighting jjl = null;
            //投桃报李 使用
            ChangeFighting ttbl = null;
            ChangeFighting tlhz = null;
            JS(mydata, data, changeFighting, org2, n, battlefield);
            if (changeFighting.getChangehp() < 0) {
                //判断是发有雪上加霜
                if (battlefield.CurrentRound > 1) {
                    //判断是否 自动触发回光
                    FightingSkill TZ_HGFZ = data.getSkillType(TypeUtil.TZ_HGFZ);
                    if (TZ_HGFZ != null && data.getvalue(0) < TZ_HGFZ.getSkillgain() / 100.0 && !data.SkillCooling(Integer.parseInt(TypeUtil.TZ_HGFZ))) {
                        data.addAddState(TZ_HGFZ.getSkilltype(), TZ_HGFZ.getSkillgain(), 0.0, 1);
                        data.addAddState("冷却", 6029.0, 0.0, 15);
                        data.RemoveAbnormal(org2, ManData.values2);
                        org2.setEndState_1(TypeUtil.TZ_HGFZ);
                    }
                }
                if (-changeFighting.getChangehp() >= data.getHp() &&
                        data.xzstate("冥河纱") != null) {
                    changeFighting.setChangehp(-(data.getHp() - 10));
                    data.RemoveAbnormal(org2, "冥河纱");
                    org2.setSkillskin("1838");
                    org2.setEndState_2("冥河纱");
                    org2.setText("#Y抵御一次致死#r伤害");
                }
                AddState HGFZ = data.xzstate(TypeUtil.TZ_HGFZ);
                if (HGFZ != null) {
                    changeFighting.setChangehp(-changeFighting.getChangehp());
                } else {
                    //法宝转换伤害
                    zhsh(data, changeFighting);
                    //妙转乾坤
                    flhd(data, changeFighting);
                    pztx(data, changeFighting, battlefield, list);
                    org2.setStartState("被攻击");
                    org2.setSkillsy("hit");
                    //防御伤害减免
                    if (battlefield.fypd(data.getCamp(), data.getMan())) {
                        double xs = 1.0;
                        if (type.equals(TypeUtil.PTGJ)) {
                            org2.setProcessState("防御");
                            org2.setStartState("防御");
                            org2.setSkillsy("defend");
                            xs -= 0.5;
                            if (data.getSkillType(TypeUtil.TJ_JZZ) != null || data.getSkillType(TypeUtil.TJ_XLYT) != null) {
                                xs -= 0.2;
                            }
                        } else if ((type.equals("风") || type.equals("火") || type.equals("水") || type.equals("雷") || type.equals("鬼火") || type.equals("中毒") || type.equals("震慑")) && (data.getSkillType(TypeUtil.TJ_RWJ) != null || data.getSkillType(TypeUtil.TJ_XLYT) != null)) {
                            xs -= 0.2;
                        }
                        changeFighting.setChangehp((int) ((double) changeFighting.getChangehp() * xs));
                    }
                    fbft = fb_fbft(data, changeFighting);
                    jjl = fb_jjl(data, changeFighting);
                    ttbl = bb_e_ttbl(data, changeFighting, battlefield);
                    tlhz = tj_tlhz(data, changeFighting);
                    if (changeFighting.getChangehp() == 0) {
                        changeFighting.setChangehp(-1);
                    }
                }
                if (type.equals("庇护") || type.equals("盾破") || type.equals("反震") || type.equals("至圣") || type.equals("薪火") || type.equals("怒气") || type.equals("怨气") || type.equals("流连忘返")) {
                    org2.setProcessState(type);
                }
                //新的护盾处理
                new GxgfAction().gxgf(org2, mydata, data, changeFighting, battlefield, list);
            }
            //判断是否有盾
//                gxgf(org2, mydata, data, changeFighting, battlefield, list);
            ChildUptake(changeFighting, data, type, list, battlefield);
            int States = data.getStates();
            if (changeFighting.getChangehp() < 0) {//受到伤害则增加怨气
                if (type.equals(TypeUtil.PTGJ) || type.equals("鬼火") || type.equals("震慑") || type.equals("三尸虫")) {
                    boolean isOk = true;
                    //平心-9192 震慑法术{75}%几率不增加目标怒气(仅在与玩家之间战斗有效)
                    if (PK_MixDeal.isPK(battlefield.BattleType) && mydata != null) {
                        FightingSkill skill3 = mydata.getSkillType(TypeUtil.TY_ZS_PX);
                        if (skill3 != null && Battlefield.isV(skill3.getSkillhurt())) {
                            isOk = false;
                        }
                    }
                    if (isOk) {
                        AddState addState2 = data.xzstate("怨气");
                        if (addState2 != null) {
                            data.addyq(75, org2);//加25
                        } else {
                            data.addyq(25, org2);//加25
                        }
                    }
                } else if (type.equals("风") || type.equals("火") || type.equals("水") || type.equals("雷")) {
                    FightingSkill TY_ZS_HY = data.getSkillType(TypeUtil.TY_ZS_HY);
                    if (TY_ZS_HY != null && Battlefield.isV(TY_ZS_HY.getSkillhurt())) {// 增加法力值
                        String[] hp = String.valueOf(changeFighting.getChangehp()).split("-");
                        changeFighting.setChangemp(Integer.parseInt(hp[1]) / 100);
                    }
                    AddState addState2 = data.xzstate("怨气");
                    if (addState2 != null) {
                        data.addyq(75, org2);//加50
                    } else {
                        data.addyq(50, org2);
                    }//加50
                }
                FightingSkill TY_RH_CMLC = data.getSkillType(TypeUtil.TY_RH_CMLC);
                if (TY_RH_CMLC != null) {
                    data.addnq((int) TY_RH_CMLC.getSkillhurt(), org2);
                }
                data.fmBGJ(org2);// 判定 以静制动 明镜止水 妙法莲华
                //霸道哥  根据双方血量来扣除
                if (Math.abs(changeFighting.getChangehp()) > data.getHp()) {
                    changeFighting.setChangehp(-data.getHp());
                }
            }
            if (TypeUtil.ZSSH.equals(type)) {
                data.ChangeDataZS(org2);
            } else {
                data.ChangeData(changeFighting, org2);
            }
            if (changeFighting.getChangehp() < 0 && !type.equals("中毒")) {
                //判断身上是否有昏睡状态
                int j = 0;
                while (j < data.getAddStates().size()) {
                    AddState addState2 = (AddState) data.getAddStates().get(j);
                    if (addState2.getStatename().equals("昏睡")) {
                        FightingSkill skill3 = addState2.getSkill(9124);
                        if (skill3 != null && Battlefield.isV(skill3.getSkillhurt())) {
                            break;
                        } else {
                            skill3 = addState2.getSkill(9127);
                            if (skill3 != null) {
                                addState2.getSkills().remove(skill3);
                                break;
                            } else {
                                org2.setEndState_2("昏睡");
                                data.getAddStates().remove(j);
                                break;
                            }
                        }
                    } else {
                        ++j;
                    }
                }
            }
            //判断是否有毒针
            if (type.equals("毒针轻刺") && changeFighting.getChangehp() < 0) {
                dzqc(changeFighting.getChangehp(), data, battlefield, list);
            }
            if (data.getStates() == 1 && States == 0) {
                if (type.equals("盾破")) {
                    data.getAddStates().add(new AddState("归墟", 0.0, 1));
                    org2.setEndState_1("归墟");
                }
                pztxDie(data, battlefield, list);
                if (mydata != null && mydata.getType() == 1 && type.equals(TypeUtil.PTGJ)) {
                    BB_E_HQYX(mydata, data, list, battlefield);
                }
                if (mydata != null) {
                    FM_jshx(mydata, data, list, battlefield, new String[]{"鱼龙潜跃", "虎踞龙蟠"});
                }
                if (mydata != null) {
                    Battlefield.overSp = mydata.getSp();
                    try {
                        FightingSkill skillId = mydata.getSkillId(6043);
                        if (skillId != null && Battlefield.isV(70.0) && PK_MixDeal.isPK(battlefield.BattleType) && data != null && data.getBearSkills() != null && data.getBearSkills().size() > 0) {
                            String s = (String) data.getBearSkills().get(data.getBearSkills().size() - 1);
                            FightingSkill skillName = mydata.getSkillName(s);
                            if (skillName != null && skillName.getSkillsum() >= 3) {
                                data.addAddState("6043", 30.0, 0.0, 1);
                            }
                        }
                    } catch (Exception e) {
                        System.err.println("技能6043报错");
                        e.printStackTrace();
                    }
                }
                //先判断是否能复活
                MixDeal.DeathSkill(data, org2, battlefield);
                //判断是否有化羽状态
                if (data.xzstate("化羽") != null) {
                    battlefield.huayu.add(new PathPoint(data.getCamp(), changeFighting.getChangehp()));
                }
                if (data.getStates() == 1 && mydata != null) {
                    FightingSkill skill_4019 = mydata.getSkillType("4019");
                    FightingSkill skill_4020 = mydata.getSkillType("4020");
                    List<FightingState> removeStates = list;
                    if (skill_4019 != null || skill_4020 != null) {
                        skill_4019 = ((skill_4019 == null) ? skill_4020 : skill_4019);
                        if (Battlefield.isV(skill_4019.getValue2())) {
                            AddState xzstate2 = data.xzstate("力量");
                            if (xzstate2 != null && xzstate2.getSurplus() > 0) {
                                int surplus = xzstate.getSurplus();
                                if (surplus >= 2) {
                                    xzstate.setSurplus(xzstate.getSurplus() - 2);
                                    if (xzstate2.getSurplus() <= 0) {
                                        FightingState fightingState2 = new FightingState();
                                        fightingState2 = new FightingState();
                                        fightingState2.setStartState("代价");
                                        fightingState2.setCamp(data.getCamp());
                                        fightingState2.setMan(data.getMan());
                                        fightingState2.setEndState_2(xzstate2.getStatename());
                                        removeStates.add(fightingState2);
                                        data.deleteState(xzstate2.getStatename());
                                    }
                                } else {
                                    FightingState fightingState2 = new FightingState();
                                    fightingState2 = new FightingState();
                                    fightingState2.setStartState("代价");
                                    fightingState2.setCamp(data.getCamp());
                                    fightingState2.setMan(data.getMan());
                                    fightingState2.setEndState_2(xzstate2.getStatename());
                                    removeStates.add(fightingState2);
                                    data.deleteState(xzstate2.getStatename());
                                }
                            }
                            xzstate2 = data.xzstate("加速");
                            if (xzstate2 != null && xzstate2.getSurplus() > 0) {
                                int surplus = xzstate2.getSurplus();
                                if (surplus >= 2) {
                                    xzstate2.setSurplus(xzstate2.getSurplus() - 2);
                                    if (xzstate2.getSurplus() <= 0) {
                                        FightingState fightingState2 = new FightingState();
                                        fightingState2 = new FightingState();
                                        fightingState2.setStartState("代价");
                                        fightingState2.setCamp(data.getCamp());
                                        fightingState2.setMan(data.getMan());
                                        fightingState2.setEndState_2(xzstate2.getStatename());
                                        removeStates.add(fightingState2);
                                        data.deleteState(xzstate2.getStatename());
                                    }
                                } else {
                                    FightingState fightingState2 = new FightingState();
                                    fightingState2 = new FightingState();
                                    fightingState2.setStartState("代价");
                                    fightingState2.setCamp(data.getCamp());
                                    fightingState2.setMan(data.getMan());
                                    fightingState2.setEndState_2(xzstate2.getStatename());
                                    removeStates.add(fightingState2);
                                    data.deleteState(xzstate2.getStatename());
                                }
                            }
                            xzstate2 = data.xzstate("抗性");
                            if (xzstate2 != null && xzstate2.getSurplus() > 0) {
                                int surplus = xzstate2.getSurplus();
                                if (surplus >= 2) {
                                    xzstate2.setSurplus(xzstate2.getSurplus() - 2);
                                    if (xzstate2.getSurplus() <= 0) {
                                        FightingState fightingState2 = new FightingState();
                                        fightingState2 = new FightingState();
                                        fightingState2.setStartState("代价");
                                        fightingState2.setCamp(data.getCamp());
                                        fightingState2.setMan(data.getMan());
                                        fightingState2.setEndState_2(xzstate2.getStatename());
                                        removeStates.add(fightingState2);
                                        data.deleteState(xzstate2.getStatename());
                                    }
                                } else {
                                    FightingState fightingState2 = new FightingState();
                                    fightingState2 = new FightingState();
                                    fightingState2.setStartState("代价");
                                    fightingState2.setCamp(data.getCamp());
                                    fightingState2.setMan(data.getMan());
                                    fightingState2.setEndState_2(xzstate2.getStatename());
                                    removeStates.add(fightingState2);
                                    data.deleteState(xzstate2.getStatename());
                                }
                            }
                            xzstate2 = data.xzstate("smmh");
                            if (xzstate2 != null && xzstate2.getSurplus() > 0) {
                                int surplus = xzstate2.getSurplus();
                                if (surplus >= 2) {
                                    xzstate2.setSurplus(xzstate2.getSurplus() - 2);
                                    if (xzstate2.getSurplus() <= 0) {
                                        FightingState fightingState2 = new FightingState();
                                        fightingState2 = new FightingState();
                                        fightingState2.setStartState("代价");
                                        fightingState2.setCamp(data.getCamp());
                                        fightingState2.setMan(data.getMan());
                                        fightingState2.setEndState_2(xzstate2.getStatename());
                                        removeStates.add(fightingState2);
                                        data.deleteState(xzstate2.getStatename());
                                    }
                                } else {
                                    FightingState fightingState2 = new FightingState();
                                    fightingState2 = new FightingState();
                                    fightingState2.setStartState("代价");
                                    fightingState2.setCamp(data.getCamp());
                                    fightingState2.setMan(data.getMan());
                                    fightingState2.setEndState_2(xzstate2.getStatename());
                                    removeStates.add(fightingState2);
                                    data.deleteState(xzstate2.getStatename());
                                }
                            }
                            xzstate2 = data.xzstate("甘霖");
                            if (xzstate2 != null && xzstate2.getSurplus() > 0) {
                                int surplus = xzstate2.getSurplus();
                                if (surplus >= 2) {
                                    xzstate2.setSurplus(xzstate2.getSurplus() - 2);
                                    if (xzstate2.getSurplus() <= 0) {
                                        FightingState fightingState2 = new FightingState();
                                        fightingState2 = new FightingState();
                                        fightingState2.setStartState("代价");
                                        fightingState2.setCamp(data.getCamp());
                                        fightingState2.setMan(data.getMan());
                                        fightingState2.setEndState_2(xzstate2.getStatename());
                                        removeStates.add(fightingState2);
                                        data.deleteState(xzstate2.getStatename());
                                    }
                                } else {
                                    FightingState fightingState2 = new FightingState();
                                    fightingState2 = new FightingState();
                                    fightingState2.setStartState("代价");
                                    fightingState2.setCamp(data.getCamp());
                                    fightingState2.setMan(data.getMan());
                                    fightingState2.setEndState_2(xzstate2.getStatename());
                                    removeStates.add(fightingState2);
                                    data.deleteState(xzstate2.getStatename());
                                }
                            }
                        } else if (Battlefield.isV(skill_4019.getValue1())) {
                            AddState xzstate2 = data.xzstate("力量");
                            if (xzstate2 != null && xzstate2.getSurplus() > 0) {
                                int surplus = xzstate.getSurplus();
                                if (surplus >= 1) {
                                    xzstate.setSurplus(xzstate.getSurplus() - 1);
                                    if (xzstate2.getSurplus() <= 0) {
                                        FightingState fightingState2 = new FightingState();
                                        fightingState2 = new FightingState();
                                        fightingState2.setStartState("代价");
                                        fightingState2.setCamp(data.getCamp());
                                        fightingState2.setMan(data.getMan());
                                        fightingState2.setEndState_2(xzstate2.getStatename());
                                        removeStates.add(fightingState2);
                                        data.deleteState(xzstate2.getStatename());
                                    }
                                } else {
                                    FightingState fightingState2 = new FightingState();
                                    fightingState2 = new FightingState();
                                    fightingState2.setStartState("代价");
                                    fightingState2.setCamp(data.getCamp());
                                    fightingState2.setMan(data.getMan());
                                    fightingState2.setEndState_2(xzstate2.getStatename());
                                    removeStates.add(fightingState2);
                                    data.deleteState(xzstate2.getStatename());
                                }
                            }
                            xzstate2 = data.xzstate("加速");
                            if (xzstate2 != null && xzstate2.getSurplus() > 0) {
                                int surplus = xzstate2.getSurplus();
                                if (surplus >= 1) {
                                    xzstate2.setSurplus(xzstate2.getSurplus() - 1);
                                    if (xzstate2.getSurplus() <= 0) {
                                        FightingState fightingState2 = new FightingState();
                                        fightingState2 = new FightingState();
                                        fightingState2.setStartState("代价");
                                        fightingState2.setCamp(data.getCamp());
                                        fightingState2.setMan(data.getMan());
                                        fightingState2.setEndState_2(xzstate2.getStatename());
                                        removeStates.add(fightingState2);
                                        data.deleteState(xzstate2.getStatename());
                                    }
                                } else {
                                    xzstate2.setSurplus(0);
                                    if (xzstate2.getSurplus() > 0) {
                                        FightingState fightingState2 = new FightingState();
                                        fightingState2 = new FightingState();
                                        fightingState2.setStartState("代价");
                                        fightingState2.setCamp(data.getCamp());
                                        fightingState2.setMan(data.getMan());
                                        fightingState2.setEndState_2(xzstate2.getStatename());
                                        removeStates.add(fightingState2);
                                        data.deleteState(xzstate2.getStatename());
                                    }
                                }
                            }
                            xzstate2 = data.xzstate("抗性");
                            if (xzstate2 != null && xzstate2.getSurplus() <= 0) {
                                int surplus = xzstate2.getSurplus();
                                if (surplus >= 1) {
                                    xzstate2.setSurplus(xzstate2.getSurplus() - 1);
                                    if (xzstate2.getSurplus() > 0) {
                                        FightingState fightingState2 = new FightingState();
                                        fightingState2 = new FightingState();
                                        fightingState2.setStartState("代价");
                                        fightingState2.setCamp(data.getCamp());
                                        fightingState2.setMan(data.getMan());
                                        fightingState2.setEndState_2(xzstate2.getStatename());
                                        removeStates.add(fightingState2);
                                        data.deleteState(xzstate2.getStatename());
                                    }
                                } else {
                                    xzstate2.setSurplus(0);
                                    if (xzstate2.getSurplus() <= 0) {
                                        FightingState fightingState2 = new FightingState();
                                        fightingState2 = new FightingState();
                                        fightingState2.setStartState("代价");
                                        fightingState2.setCamp(data.getCamp());
                                        fightingState2.setMan(data.getMan());
                                        fightingState2.setEndState_2(xzstate2.getStatename());
                                        removeStates.add(fightingState2);
                                        data.deleteState(xzstate2.getStatename());
                                    }
                                }
                            }
                            xzstate2 = data.xzstate("smmh");
                            if (xzstate2 != null && xzstate2.getSurplus() > 0) {
                                int surplus = xzstate2.getSurplus();
                                if (surplus >= 1) {
                                    xzstate2.setSurplus(xzstate2.getSurplus() - 1);
                                    if (xzstate2.getSurplus() <= 0) {
                                        FightingState fightingState2 = new FightingState();
                                        fightingState2 = new FightingState();
                                        fightingState2.setStartState("代价");
                                        fightingState2.setCamp(data.getCamp());
                                        fightingState2.setMan(data.getMan());
                                        fightingState2.setEndState_2(xzstate2.getStatename());
                                        removeStates.add(fightingState2);
                                        data.deleteState(xzstate2.getStatename());
                                    }
                                } else {
                                    xzstate2.setSurplus(0);
                                    if (xzstate2.getSurplus() <= 0) {
                                        FightingState fightingState2 = new FightingState();
                                        fightingState2 = new FightingState();
                                        fightingState2.setStartState("代价");
                                        fightingState2.setCamp(data.getCamp());
                                        fightingState2.setMan(data.getMan());
                                        fightingState2.setEndState_2(xzstate2.getStatename());
                                        removeStates.add(fightingState2);
                                        data.deleteState(xzstate2.getStatename());
                                    }
                                }
                            }
                            xzstate2 = data.xzstate("甘霖");
                            if (xzstate2 != null && xzstate2.getSurplus() > 0) {
                                int surplus = xzstate2.getSurplus();
                                if (surplus >= 1) {
                                    xzstate2.setSurplus(xzstate2.getSurplus() - 1);
                                    if (xzstate2.getSurplus() <= 0) {
                                        FightingState fightingState2 = new FightingState();
                                        fightingState2 = new FightingState();
                                        fightingState2.setStartState("代价");
                                        fightingState2.setCamp(data.getCamp());
                                        fightingState2.setMan(data.getMan());
                                        fightingState2.setEndState_2(xzstate2.getStatename());
                                        removeStates.add(fightingState2);
                                        data.deleteState(xzstate2.getStatename());
                                    }
                                } else {
                                    xzstate2.setSurplus(0);
                                    if (xzstate2.getSurplus() <= 0) {
                                        FightingState fightingState2 = new FightingState();
                                        fightingState2 = new FightingState();
                                        fightingState2.setStartState("代价");
                                        fightingState2.setCamp(data.getCamp());
                                        fightingState2.setMan(data.getMan());
                                        fightingState2.setEndState_2(xzstate2.getStatename());
                                        removeStates.add(fightingState2);
                                        data.deleteState(xzstate2.getStatename());
                                    }
                                }
                            }
                        }
                    }
                }
            }
            //反震处理 返回反震伤害 活人才被反震
            if (mydata != null && !n.contains("反击") && mydata.getStates() == 0 && mydata.getType() != 3 && mydata.getType() != 4) {
                if (changeFighting.getChangehp() < 0 && !type.equals("震慑")&& !type.equals("施法毒")) {
                    int ap = (int) data.getfz((long) changeFighting.getChangehp());
                    if (StringUtils.isNotBlank(n) && n.startsWith("霹雳end")) {
                        ap = (int) mydata.getFzSum();
                        mydata.setFzSum(0);
                    }
                    if ((ap != 0 && !"疾风迅雷".equals(type)) || n.startsWith("霹雳end")) {
                        double wushi = mydata.hsfz;
                        FightingSkill skill4 = mydata.getSkillId(23005);
                        if (skill4 != null) {
                            wushi += 10.0;
                        }
                        if (!Battlefield.isV(wushi)) {
                            boolean isOk2 = true;
                            // 神力加身 15.6%无视敌方反震
                            if (n.equals("神力加身")) {
                                double dsjv = mydata.getFamencsJv(4, new String[]{n});
                                if ((double) (Battlefield.random.nextInt(10000) + 1) <= dsjv * 100.0) {
                                    isOk2 = false;
                                }
                            }
                            AddState as = data.xzstate(TypeUtil.TY_KX_QWTC);
                            FightingSkill skill5 = data.getAppendSkill(10048);
                            if (as != null && skill5 != null) {
                                ChangeFighting change = new ChangeFighting();
                                change.setChangemp((int) ((double) ap * skill5.getSkillhurt() / 100.0));
                                FightingState org3 = new FightingState();
                                org3.setCamp(mydata.getCamp());
                                org3.setMan(mydata.getMan());
                                org3.setStartState("被攻击");
                                ChangeProcess(change, null, mydata, org3, "反震", list, battlefield);
                            }
                            //9206|月涌大江|被牛的单位物理攻击时有（2%*等级）的几率免疫反震伤害。
                            skill4 = data.getAppendSkill(9206);
                            if (skill4 != null && Battlefield.isV(skill4.getSkillhurt())) {
                                isOk2 = false;
                            }
                            if (isOk2) {
                                ChangeFighting f = new ChangeFighting();
                                if (mydata.getSkillType(TypeUtil.TJ_WZKR) != null) {
                                    f.setChangehp(ap / 2);
                                } else {
                                    f.setChangehp(ap);
                                }
                                if ((type.equals("普通攻击") || type.equals("鬼火") || type.equals("风") || type.equals("雷") || type.equals("水") || type.equals("火")) && mydata.getSkillType("4016") != null) {
                                    f.setChangehp((int) ((double) f.getChangehp() * (1.0 - mydata.getSkillType("4016").getSkillgain() / 100.0)));
                                }
                                FightingState org3 = new FightingState();
                                org3.setCamp(mydata.getCamp());
                                org3.setMan(mydata.getMan());
                                if ("霹雳end".equals(n)) {
                                    org3.setStartState("代价");
                                } else {
                                    org3.setStartState("被攻击");
                                }
                                ChangeProcess(f, null, mydata, org3, "反震", list, battlefield);
                                AddState addState2 = data.xzstate("气聚神凝");
                                if (addState2 != null) {
                                    List<ManData> datas = SSCAction.minhp(mydata.getCamp(), 1, battlefield);
                                    if (datas.size() > 0) {
                                        ChangeFighting fs = new ChangeFighting();
                                        fs.setChangehp((int) ((double) changeFighting.getChangehp() * data.getFmJv(5, new String[]{"气聚神凝"}) / 100.0));
                                        FightingState org4 = new FightingState();
                                        org4.setCamp(datas.get(0).getCamp());
                                        org4.setMan(datas.get(0).getMan());
                                        org4.setStartState("被攻击");
                                        ChangeProcess(fs, null, (ManData) datas.get(0), org4, "反震", list, battlefield);
                                    }
                                }
                            }
                        }
                    }
                    if (StringUtils.isNotBlank(n) && "霹雳".equals(n)) {
                        mydata.setFzSum((int) mydata.getFzSum() + ap);
                    }
                }
                if (fbft != null) {
                    FightingState org5 = new FightingState();
                    org5.setCamp(mydata.getCamp());
                    org5.setMan(mydata.getMan());
                    org5.setStartState(TypeUtil.JN);
                    ChangeProcess(fbft, null, mydata, org5, "反射", list, battlefield);
                }
                if (jjl != null) {
                    FightingState org5 = new FightingState();
                    List<ManData> datas2 = MixDeal.get(false, null, 0, data.getCamp(), 0, 0, 0, 0, 1, 0, battlefield, 1);
                    if (datas2.size() != 0) {
                        org5.setCamp(datas2.get(0).getCamp());
                        org5.setMan(datas2.get(0).getMan());
                        org5.setStartState(TypeUtil.JN);
                        ChangeProcess(jjl, null, (ManData) datas2.get(0), org5, "反射", list, battlefield);
                    }
                }
                if (ttbl != null) {
                    FightingState org5 = new FightingState();
                    List<ManData> datas2 = MixDeal.get(false, null, 0, data.getCamp(), 0, 0, 0, 0, 1, 3, battlefield, 1);
                    if (datas2.size() != 0) {
                        org5.setCamp(datas2.get(0).getCamp());
                        org5.setMan(datas2.get(0).getMan());
                        org5.setStartState(TypeUtil.JN);
                        ChangeProcess(ttbl, null, (ManData) datas2.get(0), org5, "反射", list, battlefield);
                    }
                }
            }
        } else {
            ChangeFighting change2 = new ChangeFighting();
            change2.setChangehp(changeFighting.getChangehp());
            changeFighting.setChangehp(0);
            data.ChangeData(changeFighting, org2);
            if (yylz == 2) {
                org2.setEndState_2("阴阳逆转");
            }
            org2.setProcessState("阴阳逆转");
            org2.setStartState("代价");
            //选择对方血最多的单位
            List<ManData> datas3 = MixDeal.get(false, null, 0, data.getCamp(), 0, 0, 0, 0, 1, 0, battlefield, 1);
            if (datas3.size() != 0) {
                FightingState org6 = new FightingState();
                org6.setCamp(datas3.get(0).getCamp());
                org6.setMan(datas3.get(0).getMan());
                org6.setStartState("代价");
                ChangeProcess(change2, null, (ManData) datas3.get(0), org6, "阴阳逆转", list, battlefield);
            }
        }
        if ("至圣".equals(type)) {
            org2.setProcessState("至圣");
        }
        if ("薪火".equals(type)) {
            org2.setProcessState("薪火");
        }
    }

    /**
     * 妙转乾坤(法力护盾)
     */
    public static void flhd(ManData data, ChangeFighting changeFighting) {
        if (changeFighting.getChangehp() >= 0) {
            return;
        }
        FightingSkill skill = data.getSkillType("妙转乾坤");
        if (skill == null) {
            return;
        }
        int symp = data.getMp() + changeFighting.getChangemp();
        if (symp <= 0) {
            return;
        }
        int hurt = -changeFighting.getChangehp() / 2;
        if (symp >= hurt) {
            changeFighting.setChangehp(-hurt);
            changeFighting.setChangemp(-hurt + changeFighting.getChangemp());
        } else {
            changeFighting.setChangehp(changeFighting.getChangehp() + symp);
            changeFighting.setChangemp(changeFighting.getChangemp() - symp);
        }
    }

    /**
     * 骨盾处理
     */
    public static void gxgf(FightingState org2, ManData mydata, ManData data, ChangeFighting changeFighting, Battlefield battlefield, List<FightingState> list) {
        if (changeFighting.getChangehp() >= 0) {
            return;
        }
        AddState addState = null;
        FightingSkill skill = null;
        if (PK_MixDeal.isPK(battlefield.BattleType)) {
            //    		9232|苦海慈航|释放一个强力乾坤借速，    为所有被速的单位增加一个护盾，当被速目标气血百分比低于（20%*等级）时，该护盾可以抵御义词致死伤害，护盾生效时清除被速目标身上的速法效果，护盾最多存在3回合。此技能全队全场只能用一次。（仅在与玩家之间战斗有效）
            skill = data.getAppendSkill(9232);
            if (skill != null && data.getvalue(0) < skill.getSkillhurt() / 100.0 && -changeFighting.getChangehp() >= data.getHp()) {
                data.RemoveAbnormal(new String[]{TypeUtil.JS});
                org2.setProcessState("免疫");
                org2.setStartState("防御");
                org2.setEndState_2(TypeUtil.JS);
                changeFighting.setChangehp(0);
                data.RemoveAppendSkill(9232);
                return;
            }
            addState = data.xzstate(TypeUtil.TY_KX_JJCS);
            if (addState != null) {
                if (addState.getStateEffect() > (double) (-changeFighting.getChangehp())) {
                    addState.setStateEffect(addState.getStateEffect() + (double) changeFighting.getChangehp());
                    org2.setProcessState("吸收  " + -changeFighting.getChangehp());
                    org2.setStartState("防御");
                    changeFighting.setChangehp(0);
                } else {
                    data.RemoveAbnormal(new String[]{addState.getStatename()});
                    FightingState org3 = new FightingState();
                    org3.setCamp(data.getCamp());
                    org3.setMan(data.getMan());
                    org3.setStartState("防御");
                    org3.setProcessState("吸收  " + (int) addState.getStateEffect());
                    list.add(org3);
                    changeFighting.setChangehp((int) ((double) changeFighting.getChangehp() + addState.getStateEffect()));
                    FightingSkill skill2 = addState.getSkill(9247);
                    if (skill2 != null && Battlefield.isV(skill2.getSkillhurt())) {
                        data.RemoveAbnormal(org3, ManData.values2);
                        org3.setEndState_2("清除异常状态");
                    }
                    if (mydata != null && mydata.getType() != 3 && mydata.getType() != 4) {
                        skill2 = addState.getSkill(9244);
                        if (skill2 != null && Battlefield.isV(skill2.getSkillhurt())) {
                            ChangeFighting change = new ChangeFighting();
                            change.setChangehp(-(int) (skill2.getSkillhurt() * 800.0));
                            FightingState org4 = new FightingState();
                            org4.setCamp(mydata.getCamp());
                            org4.setMan(mydata.getMan());
                            org2.setStartState("被攻击");
                            ChangeProcess(change, data, mydata, org4, "盾破", list, battlefield);
                        }
                    }
                }
                return;
            } else {
                addState = data.xzstate(TypeUtil.TY_MH_RSSQ);
                if (addState != null) {
                    int maxHurt = (int) (addState.getStateEffect2() - addState.getStateEffect());
                    if (maxHurt > 0) {
                        if (maxHurt > -changeFighting.getChangehp()) {
                            addState.setStateEffect(addState.getStateEffect() + (double) changeFighting.getChangehp());
                            org2.setProcessState("吸收  " + -changeFighting.getChangehp());
                            org2.setStartState("防御");
                            changeFighting.setChangehp(0);
                        } else {
                            addState.setStateEffect(addState.getStateEffect() + (double) maxHurt);
                            FightingState org5 = new FightingState();
                            org5.setCamp(data.getCamp());
                            org5.setMan(data.getMan());
                            org5.setStartState("防御");
                            org5.setProcessState("吸收  " + maxHurt);
                            list.add(org5);
                            changeFighting.setChangehp(changeFighting.getChangehp() + maxHurt);
                        }
                    }
                    return;
                } else if (Sepcies_MixDeal.getRace(data.getSe()) == 10003) {
                    addState = data.xzstate(TypeUtil.TY_L_ZXLT);
                    if (addState != null) {
                        //9271|紫霄雷霆|在使用天课地灭时有（15%+2%*等级）几率将造成伤害之和的20%(最大不超过自己的气血上限)转化为自己的护盾,
                        //护盾存在期间每次受到一次敌方的攻击，都向敌方释放一道电波，造成（2000*等级）点 气血或者法力伤害，护盾最多持续3回合。( 仅在与玩家之间战斗有效)
                        if (addState.getStateEffect() > (double) (-changeFighting.getChangehp())) {
                            addState.setStateEffect(addState.getStateEffect() + (double) changeFighting.getChangehp());
                            org2.setProcessState("吸收  " + -changeFighting.getChangehp());
                            org2.setStartState("防御");
                            changeFighting.setChangehp(0);
                        } else {
                            data.RemoveAbnormal(new String[]{addState.getStatename()});
                            FightingState org3 = new FightingState();
                            org3.setCamp(data.getCamp());
                            org3.setMan(data.getMan());
                            org3.setStartState("防御");
                            org3.setProcessState("吸收  " + (int) addState.getStateEffect());
                            org3.setEndState_2(addState.getStatename());
                            list.add(org3);
                            changeFighting.setChangehp((int) ((double) changeFighting.getChangehp() + addState.getStateEffect()));
                        }
                        if (mydata != null) {
                            ChangeFighting fighting = new ChangeFighting();
                            FightingState Accepter2 = new FightingState();
                            Accepter2.setStartState("代价");
                            if (Battlefield.isV(50.0)) {
                                fighting.setChangehp(-(int) addState.getStateEffect2());
                            } else {
                                fighting.setChangemp(-(int) addState.getStateEffect2());
                            }
                            mydata.ChangeData(fighting, Accepter2);
                            list.add(Accepter2);
                        }
                        return;
                    } else {
                        addState = data.xzstate(TypeUtil.TY_S_XCWM);
                        if (addState != null) {
                            //9288|仙尘帷幔|在使用九龙冰封时有（15%+2%*等级）几率将造成伤害之和的20%(最大不超过自己的气血上限)转化为自己的护盾,
                            //护盾存在期间自己有（5%*等级）的几率免疫混冰睡忘和金箍控制,护盾消失时随机解除自身一个异常状态,护盾最多持续卖3回合。仅在与玩家之间战斗有效
                            if (addState.getStateEffect() > (double) (-changeFighting.getChangehp())) {
                                addState.setStateEffect(addState.getStateEffect() + (double) changeFighting.getChangehp());
                                org2.setProcessState("吸收  " + -changeFighting.getChangehp());
                                org2.setStartState("防御");
                                changeFighting.setChangehp(0);
                            } else {
                                data.RemoveAbnormal(new String[]{addState.getStatename()});
                                FightingState org3 = new FightingState();
                                org3.setCamp(data.getCamp());
                                org3.setMan(data.getMan());
                                org3.setStartState("防御");
                                org3.setProcessState("吸收  " + (int) addState.getStateEffect());
                                org3.setEndState_2(addState.getStatename());
                                list.add(org3);
                                changeFighting.setChangehp((int) ((double) changeFighting.getChangehp() + addState.getStateEffect()));
                            }
                            return;
                        } else {
                            addState = data.xzstate(TypeUtil.TY_F_YYFQ);
                            if (addState != null) {
                                //9308|云涌风飞|在使用袖里乾坤时有（15%+2%*等级）几率将造成伤害之和的20%(最大不超过自己的气血上限)转化为自己的护盾,
                                //护盾存在期间对自己造成伤害的敌方人物单位有（5%*等级）的几率在下回合使用多体师门法术时目标数减1,护盾最多持续3回合。(仅在与玩家之间战斗有效)
                                if (addState.getStateEffect() > (double) (-changeFighting.getChangehp())) {
                                    addState.setStateEffect(addState.getStateEffect() + (double) changeFighting.getChangehp());
                                    org2.setProcessState("吸收  " + -changeFighting.getChangehp());
                                    org2.setStartState("防御");
                                    changeFighting.setChangehp(0);
                                } else {
                                    data.RemoveAbnormal(new String[]{addState.getStatename()});
                                    FightingState org3 = new FightingState();
                                    org3.setCamp(data.getCamp());
                                    org3.setMan(data.getMan());
                                    org3.setStartState("防御");
                                    org3.setProcessState("吸收  " + (int) addState.getStateEffect());
                                    org3.setEndState_2(addState.getStatename());
                                    list.add(org3);
                                    changeFighting.setChangehp((int) ((double) changeFighting.getChangehp() + addState.getStateEffect()));
                                }
                                if (mydata != null && Battlefield.isV(addState.getStateEffect2())) {
                                    mydata.addAddState(TypeUtil.TY_F_YYFQ_S, 0.0, 0.0, 2);
                                }
                                return;
                            } else {
                                addState = data.xzstate(TypeUtil.TY_H_ZSMH);
                                if (addState != null) {
                                    //9328|照世明火|在使用九阴纯火时有（15%+2%*等级）几率将造成伤害之和的20%(最大不超过自己的气血上限)转化为自己的护盾,
                                    //护盾存在期间每个攻击自己的敌方目标都会受到灼烧,处于该状态下的目标下回合开始前随机受到自已气血上限（0.5%*等级）~（1.5%*等级）的伤害,护盾最多持续3回合。(仅在与玩家之间战斗有效
                                    if (addState.getStateEffect() > (double) (-changeFighting.getChangehp())) {
                                        addState.setStateEffect(addState.getStateEffect() + (double) changeFighting.getChangehp());
                                        org2.setProcessState("吸收  " + -changeFighting.getChangehp());
                                        org2.setStartState("防御");
                                        changeFighting.setChangehp(0);
                                    } else {
                                        data.RemoveAbnormal(new String[]{addState.getStatename()});
                                        FightingState org3 = new FightingState();
                                        org3.setCamp(data.getCamp());
                                        org3.setMan(data.getMan());
                                        org3.setStartState("防御");
                                        org3.setProcessState("吸收  " + (int) addState.getStateEffect());
                                        org3.setEndState_2(addState.getStatename());
                                        list.add(org3);
                                        changeFighting.setChangehp((int) ((double) changeFighting.getChangehp() + addState.getStateEffect()));
                                    }
                                    if (mydata != null) {
                                        mydata.addAddState(TypeUtil.TY_H_ZSMH_S, addState.getStateEffect2(), 0.0, 2);
                                    }
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        } else {
            //    		9262|销声匿迹|给自己释放一个可以持续3回合的护盾，该护盾消耗法力会抵挡所受的气血伤害。每回合最多吸收最大法力值（10%+5%*等级）的伤害。（仅在与NPC之间战斗有效）
            addState = data.xzstate(TypeUtil.TY_L_XSNJ);
            if (addState != null) {
                if (addState.getStateEffect() > 0.0) {
                    if (addState.getStateEffect() > (double) (-changeFighting.getChangehp())) {
                        addState.setStateEffect(addState.getStateEffect() + (double) changeFighting.getChangehp());
                        org2.setProcessState("吸收  " + -changeFighting.getChangehp());
                        org2.setStartState("防御");
                        changeFighting.setChangehp(0);
                    } else {
                        FightingState org3 = new FightingState();
                        org3.setCamp(data.getCamp());
                        org3.setMan(data.getMan());
                        org3.setStartState("防御");
                        org3.setProcessState("吸收  " + (int) addState.getStateEffect());
                        list.add(org3);
                        changeFighting.setChangehp((int) ((double) changeFighting.getChangehp() + addState.getStateEffect()));
                    }
                }
                if (mydata != null) {
                    FightingSkill skill3 = addState.getSkill(9265);
                    if (skill3 != null) {
                        ChangeFighting change2 = new ChangeFighting();
                        int hurt = (int) skill3.getSkillhurt() - 5000;
                        if (hurt > 0) {
                            hurt = Battlefield.random.nextInt(hurt);
                        }
                        hurt = (int) ((double) hurt + skill3.getSkillhurt());
                        FightingSkill skill4 = addState.getSkill(9268);
                        if (skill4 != null && Battlefield.isV(skill4.getSkillhurt())) {
                            hurt = (int) ((double) hurt + (double) data.getHp() * 0.1);
                        }
                        change2.setChangehp(-hurt);
                        FightingState org6 = new FightingState();
                        org6.setCamp(mydata.getCamp());
                        org6.setMan(mydata.getMan());
                        org6.setStartState("被攻击");
                        ChangeProcess(change2, null, mydata, org6, TypeUtil.TY_L_DZXY, list, battlefield);
                    }
                    if (changeFighting.getChangehp() < 0) {
                        skill3 = addState.getSkill(9302);
                        if (skill3 != null) {
                            ChangeFighting change2 = new ChangeFighting();
                            int hurt = (int) ((double) changeFighting.getChangehp() * skill3.getSkillhurt() / 100.0);
                            if (hurt < 0) {
                                change2.setChangehp(hurt);
                                FightingState org4 = new FightingState();
                                org4.setCamp(mydata.getCamp());
                                org4.setMan(mydata.getMan());
                                org4.setStartState("被攻击");
                                ChangeProcess(change2, null, mydata, org4, TypeUtil.TY_L_DZXY, list, battlefield);
                            }
                        }
                    }
                    skill3 = addState.getSkill(9305);
                    if (skill3 != null && Battlefield.isV(skill3.getSkillhurt())) {
                        data.addAddState(TypeUtil.TY_F_WHSF, 20.0, 0.0, 2);
                    }
                    skill3 = addState.getSkill(9322);
                    if (skill3 != null) {
                        data.addAddState(TypeUtil.TY_H_JSYY, skill3.getSkillhurt(), 0.0, 2);
                    }
                    skill3 = addState.getSkill(9325);
                    if (skill3 != null) {
                        data.addAddState(TypeUtil.TY_H_YHRJ, skill3.getSkillhurt(), 0.0, 2);
                    }
                }
            }
        }
        int fhht = data.getNrfhht();
        if (fhht > 0) {
            if (fhht > -changeFighting.getChangehp()) {
                data.setNrfhht(fhht + changeFighting.getChangehp());
                org2.setProcessState("吸收  " + -changeFighting.getChangehp());
                org2.setStartState("防御");
                changeFighting.setChangehp(0);
            } else {
                FightingState org5 = new FightingState();
                org5.setCamp(data.getCamp());
                org5.setMan(data.getMan());
                org5.setStartState("防御");
                org5.setProcessState("吸收  " + fhht);
                org5.setEndState_2("法魂护体");
                list.add(org5);
                data.RemoveAbnormal(new String[]{"法魂护体"});
            }
        }
        addState = data.xzstate("血蛊护盾");
        if (addState != null) {
            if (addState.getStateEffect() > (double) (-changeFighting.getChangehp())) {
                addState.setStateEffect(addState.getStateEffect() + (double) changeFighting.getChangehp());
                org2.setProcessState("吸收  " + -changeFighting.getChangehp());
                org2.setStartState("防御");
                changeFighting.setChangehp(0);
            } else {
                data.RemoveAbnormal(new String[]{addState.getStatename()});
                FightingState org5 = new FightingState();
                org5.setCamp(data.getCamp());
                org5.setMan(data.getMan());
                org5.setStartState("防御");
                org5.setProcessState("吸收  " + (int) addState.getStateEffect());
                list.add(org5);
                changeFighting.setChangehp((int) ((double) changeFighting.getChangehp() + addState.getStateEffect()));
            }
            return;
        } else {
            addState = data.xzstate("骨盾");
            if (addState == null) {
                addState = data.xzstate(TypeUtil.TY_L_GL_YMFZ);
            }
            if (addState != null) {
                if (addState.getStateEffect() > (double) (-changeFighting.getChangehp())) {
                    addState.setStateEffect(addState.getStateEffect() + (double) changeFighting.getChangehp());
                    org2.setProcessState("吸收  " + -changeFighting.getChangehp());
                    org2.setStartState("防御");
                    changeFighting.setChangehp(0);
                } else {
                    data.RemoveAbnormal(new String[]{addState.getStatename()});
                    FightingState org5 = new FightingState();
                    org5.setCamp(data.getCamp());
                    org5.setMan(data.getMan());
                    org5.setStartState("防御");
                    org5.setProcessState("吸收  " + (int) addState.getStateEffect());
                    org5.setEndState_2(addState.getStatename());
                    list.add(org5);
                    if (addState.getStatename().equals("骨盾")) {
                        changeFighting.setChangehp((int) ((double) changeFighting.getChangehp() + addState.getStateEffect()));
                        if (mydata != null && mydata.getType() != 3 && mydata.getType() != 4) {
                            ChangeFighting change = new ChangeFighting();
                            change.setChangehp((int) (-addState.getStateEffect2()));
                            FightingState org4 = new FightingState();
                            org4.setCamp(mydata.getCamp());
                            org4.setMan(mydata.getMan());
                            org4.setStartState("被攻击");
                            ChangeProcess(change, data, mydata, org4, "盾破", list, battlefield);
                        }
                    }
                }
                return;
            } else {
                // 灵犀--化险为夷盾处理
                addState = data.xzstate("化险为夷");
                if (addState == null) {
                    addState = data.xzstate("风荷送香");
                }
                if (addState == null) {
                    addState = data.xzstate("焕然新生");
                }
                if (addState != null) {
                    if (addState.getStateEffect() > (double) (-changeFighting.getChangehp())) {
                        addState.setStateEffect(addState.getStateEffect() + (double) changeFighting.getChangehp());
                        org2.setProcessState("吸收  " + -changeFighting.getChangehp());
                        org2.setStartState("被攻击");
                        changeFighting.setChangehp(0);
                    } else {
                        data.RemoveAbnormal(new String[]{addState.getStatename()});
                        FightingState org5 = new FightingState();
                        org5.setCamp(data.getCamp());
                        org5.setMan(data.getMan());
                        org5.setStartState("被攻击");
                        org5.setProcessState("吸收  " + (int) addState.getStateEffect());
                        org5.setEndState_2(addState.getStatename());
                        if (addState.getStatename().equals("焕然新生")) {
                            data.RemoveAbnormal(ManData.values1);
                            org5.setEndState_2("清除异常状态");
                        }
                        list.add(org5);
                        // 扣除护盾抵挡之后的血量
                        changeFighting.setChangehp((int) ((double) changeFighting.getChangehp() + addState.getStateEffect()));
                        // 有备无患反射处理
                        if (data.executeYbwh(2) && mydata != null && mydata.getType() <= 2) {
                            ChangeFighting change3 = new ChangeFighting();
                            change3.setChangehp((int) (-addState.getStateEffect()));
                            FightingState org7 = new FightingState();
                            org7.setCamp(mydata.getCamp());
                            org7.setMan(mydata.getMan());
                            org7.setStartState(TypeUtil.JN);
                            ChangeProcess(change3, null, mydata, org7, "盾破", list, battlefield);
                            // 如果是后排单位被反射则对他身前单位造成一次伤害
                            if (mydata.getMan() < 5 && data.executeYbwh(3)) {
                                int wei = battlefield.Datapathhuo(mydata.getCamp(), mydata.getMan() + 5);
                                if (wei != -1) {
                                    ManData bbData = battlefield.fightingdata.get(wei);
                                    if (bbData != null && bbData.xzstate("封印") == null && bbData.xzstate("隐身") == null) {
                                        ChangeFighting change4 = new ChangeFighting();
                                        change4.setChangehp((int) (-addState.getStateEffect()));
                                        FightingState org8 = new FightingState();
                                        org8.setCamp(bbData.getCamp());
                                        org8.setMan(bbData.getMan());
                                        org8.setStartState(TypeUtil.JN);
                                        ChangeProcess(change4, null, bbData, org8, "盾破", list, battlefield);
                                    }
                                }
                            }
                        }
                    }
                    return;
                } else {
                    return;
                }
            }
        }
    }

    /**
     * 庇护处理
     */
    public static void pztx(ManData mydata, ChangeFighting changeFighting, Battlefield battlefield, List<FightingState> list) {
        if (changeFighting.getChangehp() >= 0) {
            return;
        }
        AddState addState = mydata.xzstate("庇护");
        if (addState == null) {
            return;
        }
        if (mydata.getType() == 1 && (double) mydata.getId() == addState.getStateEffect()) {
            changeFighting.setChangehp((int) ((double) changeFighting.getChangehp() * (1.0 - addState.getStateEffect2() * 1.4 / 100.0)));
            return;
        }
        for (int i = 0; i < battlefield.fightingdata.size(); ++i) {
            ManData data = (ManData) battlefield.fightingdata.get(i);
            if (data.getStates() == 0 && (data.getType() == 1 && (double) data.getId() == addState.getStateEffect())) {
                ChangeFighting change = new ChangeFighting();
                change.setChangehp((int) ((double) changeFighting.getChangehp() * (addState.getStateEffect2() / 100.0)));
                changeFighting.setChangehp(changeFighting.getChangehp() - change.getChangehp());
                FightingState org3 = new FightingState();
                org3.setCamp(mydata.getCamp());
                org3.setMan(mydata.getMan());
                ChangeProcess(change, null, data, org3, "庇护", list, battlefield);
                return;
            }
        }
    }

    /**
     * 庇护单位死亡处理
     */
    public static void pztxDie(ManData mydata, Battlefield battlefield, List<FightingState> list) {
        AddState addState = mydata.xzstate("庇护");
        if (addState == null) {
            return;
        }
        if (mydata.getType() == 1 && (double) mydata.getId() == addState.getStateEffect()) {
            return;
        }
        FightingSkill skill = addState.getSkill(1319);
        if (skill == null) {
            return;
        }
        for (int i = 0; i < battlefield.fightingdata.size(); ++i) {
            ManData data = battlefield.fightingdata.get(i);
            if (data.getStates() == 0 && (data.getType() == 1 && (double) data.getId() == addState.getStateEffect())) {
                ChangeFighting change = new ChangeFighting();
                change.setChangehp((int) ((double) data.getHp_z() * 0.1));
                FightingState org3 = new FightingState();
                org3.setCamp(mydata.getCamp());
                org3.setMan(mydata.getMan());
                ChangeProcess(change, null, data, org3, "庇护", list, battlefield);
                return;
            }
        }
    }

    /**
     * 毒针处理
     */
    public static void dzqc(int hurt, ManData data, Battlefield battlefield, List<FightingState> list) {
        List<ManData> datas = MixDeal.get(false, null, 0, data.getCamp(), 0, 0, 0, 1, 1, 1, battlefield, 1);
        hurt *= -3;
        if (datas.size() != 0) {
            FightingState org = new FightingState();
            org.setCamp(datas.get(0).getCamp());
            org.setMan(datas.get(0).getMan());
            org.setStartState(TypeUtil.JN);
            ChangeFighting change = new ChangeFighting();
            change.setChangehp(hurt);
            datas.get(0).ChangeData(change, org);
            list.add(org);
        }
    }

    /**
     * 孩子伤害吸收
     */
    public static void ChildUptake(ChangeFighting change, ManData data, String type, List<FightingState> list, Battlefield battlefield) {
        if (!type.equals("风") && !type.equals("火") && !type.equals("水") && !type.equals("雷") && !type.equals("鬼火") && !type.equals("震慑")) {
            return;
        }
        ManData child = battlefield.getChild(data);
        if (child == null) {
            return;
        }
        FightingSkill skill = child.getChildSkill("抗" + type);
        if (skill == null) {
            return;
        }
        list.add(MixDeal.getChildSkill(child, skill.getSkillname()));
        if (type.equals("震慑")) {
            change.setChangemp((int) skill.getSkillgain());
        } else {
            int hp = change.getChangehp();
            hp = (int) ((double) hp + skill.getSkillhurt());
            if (hp >= 0) {
                hp = -1;
            }
            if (-hp >= data.getHp()) {
                hp = -(int) ((double) data.getHp() - skill.getSkillgain());
            }
            if (hp >= 0) {
                hp = 0;
            }
            change.setChangehp(hp);
        }
    }

    //判断是否存在七宝玲珑塔 黑龙珠
    public static ChangeFighting fb_fbft(ManData data, ChangeFighting changeFighting) {
        ChangeFighting fighting = null;
        AddState addState = null;
        if (changeFighting.getChangehp() < 0) {
            //黑龙珠 血反法
            addState = data.xzstate(TypeUtil.FB_HLZ);
            if (addState != null) {
                if (fighting == null) {
                    fighting = new ChangeFighting();
                }
                double xs = addState.getStateEffect() / 100.0;
                fighting.setChangemp((int) ((double) changeFighting.getChangehp() * xs));
                xs = 1.0 - xs;
                changeFighting.setChangehp((int) ((double) changeFighting.getChangehp() * xs));
            }
        }
        if (changeFighting.getChangemp() < 0) {
            //七宝玲珑塔  法反血
            addState = data.xzstate(TypeUtil.FB_QBLLT);
            if (addState != null) {
                if (fighting == null) {
                    fighting = new ChangeFighting();
                }
                double xs = addState.getStateEffect() / 100.0;
                fighting.setChangehp((int) ((double) changeFighting.getChangemp() * xs));
                xs = 1.0 - xs;
                changeFighting.setChangemp((int) ((double) changeFighting.getChangemp() * xs));
            }
        }
        return fighting;
    }

    //法宝转化伤害 银索金铃 锦襕袈裟
    public static void zhsh(ManData data, ChangeFighting changeFighting) {
        AddState addState = null;
        if (changeFighting.getChangehp() < 0) {
            //银索金铃 血转法
            addState = data.xzstate(TypeUtil.FB_YSJL);
            if (addState != null) {
                double xs = addState.getStateEffect() / 100.0;
                changeFighting.setChangemp(changeFighting.getChangemp() + (int) ((double) changeFighting.getChangehp() * xs));
                xs = 1.0 - xs;
                changeFighting.setChangehp((int) ((double) changeFighting.getChangehp() * xs));
            }
        }
        if (changeFighting.getChangemp() < 0) {
            addState = data.xzstate(TypeUtil.FB_JLJS);
            //锦襕袈裟  法转血
            if (addState != null) {
                double xs = addState.getStateEffect() / 100.0;
                changeFighting.setChangehp(changeFighting.getChangehp() + (int) ((double) changeFighting.getChangemp() * xs));
                xs = 1.0 - xs;
                changeFighting.setChangemp((int) ((double) changeFighting.getChangemp() * xs));
            }
        }
    }

    //将军令使用
    public static ChangeFighting fb_jjl(ManData data, ChangeFighting changeFighting) {
        if (changeFighting.getChangemp() >= 0) {
            return null;
        }
        AddState addState = data.xzstate(TypeUtil.FB_JJL);
        if (addState == null) {
            return null;
        }
        ChangeFighting fighting = new ChangeFighting();
        double xs = addState.getStateEffect() / 100.0;
        fighting.setChangehp((int) ((double) changeFighting.getChangehp() * xs));
        xs = 1.0 - xs;
        changeFighting.setChangehp((int) ((double) changeFighting.getChangehp() * xs));
        return fighting;
    }

    //投桃报李使用
    public static ChangeFighting bb_e_ttbl(ManData data, ChangeFighting changeFighting, Battlefield battlefield) {
        if (changeFighting.getChangemp() >= 0) {
            return null;
        }
        FightingSkill skill = data.getSkillType(TypeUtil.BB_E_TTBL);
        if (skill == null) {
            return null;
        }
        if ((double) battlefield.CurrentRound <= skill.getSkillhurt() || !Battlefield.isV(skill.getSkillgain())) {
            return null;
        }
        ChangeFighting fighting = new ChangeFighting();
        double xs = skill.getSkillgain() / 100.0;
        fighting.setChangemp((int) ((double) changeFighting.getChangehp() * xs));
        xs = 1.0 - xs;
        changeFighting.setChangehp((int) ((double) changeFighting.getChangehp() * xs));
        return fighting;
    }

    public static ChangeFighting tj_tlhz(ManData data, ChangeFighting changeFighting) {
        if (changeFighting.getChangehp() >= 0) {
            return null;
        }
        AddState addState = data.xzstate("偷梁换柱");
        if (addState == null) {
            return null;
        }
        ChangeFighting fighting = new ChangeFighting();
        fighting.setChangehp(changeFighting.getChangehp());//伤害全部转移
        changeFighting.setChangehp(0);//自身伤害归0
        return fighting;
    }

    /**
     * 减伤
     */
    public static void JS(ManData myData, ManData data, ChangeFighting changeFighting, FightingState org2, String type, Battlefield battlefield) {
        if (data.getType() == 3 || changeFighting.getChangehp() >= 0) {
            return;
        }
        String type2 = type;
        if (type.startsWith("霹雳end")) {
            if (type.endsWith("至圣")) {
                type = "至圣";
            } else {
                type = "普通攻击";
            }
        } else if (type.startsWith("霹雳")) {
            if (type.endsWith("至圣")) {
                type = "至圣";
            } else {
                type = "普通攻击";
            }
        } else if ("神力加身".equals(type)) {
            type = "普通攻击";
        } else if ("力挽狂澜".equals(type)) {
            type = "普通攻击";
        } else if ("力挽狂澜".equals(type)) {
            type = "普通攻击";
        }
        AddState addState = null;
        FightingSkill skill = null;
        if (type.equals("风") || type.equals("火") || type.equals("水") || type.equals("雷") || type.equals("鬼火")) {
            FightingSkill BDRS = data.getSkillType("不动明王");
            if (BDRS != null && data.getHuoyue() >= 500.0 && (double) data.getHp_z() * 0.3 > (double) data.getHp()) {
                changeFighting.setChangehp(0);
                org2.setSkillskin("1247");
                org2.setProcessState("免疫");
                return;
            }
            skill = data.getAppendSkill(10044);
            if (skill != null) {
                data.getsx(0, "抗仙法狂暴");
                return;
            }
        }
        skill = data.getAppendSkill(9231);
        if (skill != null && Battlefield.isV(skill.getSkillhurt())) {
            changeFighting.setChangehp(0);
            changeFighting.setChangemp(0);
            org2.setProcessState("免疫");
            return;
        }
        //心如止水
        addState = data.xzstate("心如水");
        if (addState != null) {
            double bl = -((double) changeFighting.getChangehp() / (double) data.getHp_z());
            if (bl >= addState.getStateEffect()) {
                changeFighting.setChangehp(0);
                changeFighting.setChangemp(0);
                org2.setProcessState("免疫");
                return;
            }
        }
        //      1323	慧心巧思	若受到的伤害小于最大气血、法力值之和的{公式一}%，则可以免疫这次伤害，每5回合触发一次。有{公式六}%概率在生效一次
        skill = data.getSkillType(TypeUtil.BB_E_HXQS);
        if (skill != null && (skill.getSkillcontinued() == 0 || battlefield.CurrentRound >= skill.getSkillcontinued() + 5)) {
            if (!Battlefield.isV(skill.getSkillgain() / 4.0)) {
                skill.setSkillcontinued(battlefield.CurrentRound);
            }
            double bl = -((double) changeFighting.getChangehp() / (double) (data.getHp_z() + data.getMp_z()));
            if (skill.getSkillgain() > bl) {
                changeFighting.setChangehp(0);
                changeFighting.setChangemp(0);
                org2.setProcessState("免疫");
                SummonType.xianzhi(data, skill);
                return;
            }
        }
        double xs = 100.0;
        if (type.equals(TypeUtil.PTGJ)) {
            addState = data.xzstate(TypeUtil.TY_SSC_LFHX);
            if (addState != null && addState.getStateEffect() >= 3.0) {
                changeFighting.setChangehp(0);
                changeFighting.setChangemp(0);
                org2.setProcessState("免疫");
                return;
            }
            boolean isNoMY = false;// 无视 免疫物理
            //9814	摧刚破坚	物理攻击{公式一}%几率无视金刚护体和铁布衫(仅在与玩家之间战斗有效)
            if (myData != null) {
                skill = myData.getSkillType(TypeUtil.TY_LL_CGPJ);
                if (skill != null && Battlefield.isV(skill.getSkillhurt()) && PK_MixDeal.isPK(battlefield.BattleType)) {
                    isNoMY = true;
                }
            }
            if (!isNoMY) {
                addState = data.xzstate("免疫物理");
                if (addState != null) {
                    addState.setStateEffect(addState.getStateEffect() - 1.0);
                    if (addState.getStateEffect() <= 0.0) {
                        data.getAddStates().remove(addState);
                    }
                    changeFighting.setChangehp(0);
                    changeFighting.setChangemp(0);
                    org2.setProcessState("免疫");
                    return;
                }
            }
            skill = data.getAppendSkill(10043);
            if (skill != null) {
                data.getsx(0, "抗致命率");
                return;
            }
            xs -= data.getQuality().getEwljs();
        }
        // 定力减伤
        xs -= MixDeal.getXS(data.getQihe());
        xs -= data.getjs(type);
        if (myData != null) {
            skill = myData.getSkillId(23002);
            if (skill != null && myData.getSp() > data.getSp()) {
                xs += 10.0;
            }
            skill = data.getSkillId(23002);
            if (skill != null && myData.getSp() < data.getSp()) {
                xs -= 10.0;
            }
            xs += myData.getQuality().getEzs();
            addState = myData.xzstate(TypeUtil.TY_FY_HQQY);
            if (addState != null) {
                xs -= addState.getStateEffect();
            }
            if (type2.equals("势如破竹")) {
                xs = myData.getFamencsJv(4, new String[]{type2});
            }
            skill = myData.getAppendSkill(9344);
            if (skill != null && data.getSkillType(TypeUtil.TY_YW_QQWK) != null) {
                xs -= skill.getSkillhurt();
            }
            skill = myData.getAppendSkill(9148);
            if (skill != null) {
                xs += ((myData.getCamp() == data.getCamp()) ? skill.getSkillhurt() : (-skill.getSkillhurt()));
            }
            if (Sepcies_MixDeal.getRace(myData.getSe()) == 10004) {
                skill = data.getAppendSkill(9408);
                if (skill != null) {
                    xs -= skill.getSkillhurt();
                }
            }
            addState = myData.xzstate(TypeUtil.TY_F_YQDS);
            if (addState != null) {
                xs -= addState.getStateEffect();
            }
            addState = myData.xzstate("失魂落魄");
            if (addState != null) {
                int fmId = (int) addState.getStateEffect();
                int fmSld = (int) addState.getStateEffect2();
                xs -= FaMenUtil.getDouble(fmId, fmSld, 1);
            }
        }
        addState = data.xzstate(TypeUtil.TY_YW_MDHL);
        if (addState != null) {
            xs += addState.getStateEffect();
        }
        addState = data.xzstate(TypeUtil.TY_R_BTXJ);
        if (addState != null) {
            xs += addState.getStateEffect();
        }
        addState = data.xzstate(TypeUtil.TY_R_XYXS);
        if (addState != null) {
            xs -= addState.getStateEffect();
        }
        addState = data.xzstate("玉净散");
        if (addState != null) {
            xs -= addState.getStateEffect();
        }
        if (type.equals("风") || type.equals("水")) {
            skill = data.getAppendSkill(9122);
            if (skill != null) {
                xs += skill.getSkillhurt();
            }
            skill = data.getAppendSkill(9249);
            if (skill != null) {
                xs -= skill.getSkillhurt();
            }
            addState = data.xzstate("风水");
            if (addState != null) {
                xs -= addState.getStateEffect();
            }
            xs -= data.getQuality().getExfjs();
        } else if (type.equals("雷") || type.equals("火")) {
            skill = data.getAppendSkill(9122);
            if (skill != null) {
                xs += skill.getSkillhurt();
            }
            skill = data.getAppendSkill(9249);
            if (skill != null) {
                xs -= skill.getSkillhurt();
            }
            addState = data.xzstate("雷火");
            if (addState != null) {
                xs -= addState.getStateEffect();
            }
            xs -= data.getQuality().getExfjs();
        } else if (type.equals("鬼火") || type.equals(TypeUtil.PTGJ)) {
            skill = data.getAppendSkill(9122);
            if (skill != null) {
                xs += skill.getSkillhurt();
            }
            addState = data.xzstate("鬼力");
            if (addState != null) {
                xs -= addState.getStateEffect();
            }
            if (type.equals(TypeUtil.PTGJ)) {
                skill = data.getAppendSkill(9347);
                if (skill != null) {
                    xs -= skill.getSkillhurt();
                }
            } else {
                xs -= data.getQuality().getExfjs();
            }
        } else if (type.equals("震慑")) {
            xs -= data.getQuality().getEzsjs();
        } else if (type.equals(TypeUtil.TY_X_9950)) {
            skill = data.getAppendSkill(9950);
            if (skill != null) {
                xs -= skill.getSkillgain();
            }
        }
        addState = data.xzstate(TypeUtil.TY_SSC_LFHX);
        if (addState != null) {
            FightingSkill TY_G_10076 = data.getSkillType(TypeUtil.TY_G_10076);
            if (TY_G_10076 != null) {
                xs -= TY_G_10076.getSkillhurt();
                data.getQuality().addK_BHSY(TY_G_10076.getSkillgain());
                data.getSkills().remove(TY_G_10076);
            }
        }
        boolean n = false;
        boolean m = false;
        if (type == "至圣") {
            n = true;
        }
        if (type == TypeUtil.PTGJ) {
            m = true;
        }
        skill = data.getSkillType("鼓音三叠");
        if (skill != null && !n && Battlefield.isV(skill.getSkillgain())) {
            xs -= skill.getSkillhurt();
        }
        if (data.getvalue(0) <= 0.3) {
            skill = data.getSkillType("天见尤怜");
            if (skill != null && !n) {
                xs -= 80.0;
            }
        }
        addState = data.xzstate("伤害加深");
        if (addState != null) {
            xs += addState.getStateEffect();
        }
        addState = data.xzstate(TypeUtil.TZ_XSJS);
        if (addState != null) {
            xs += addState.getStateEffect();
        }
        //		 1309	背向	受到伤害时，有{公式三}%几率背向敌人，以减少{公式一}%受到的物理、法术、灵宝伤害。
        skill = data.getSkillType(TypeUtil.BB_E_BX);
        if (skill != null && Battlefield.isV(skill.getSkillhurt()) && !n) {
            xs -= skill.getSkillgain();
            org2.setText("背向#2");
        }
        Random rod = new Random();
        int p = rod.nextInt(100);
        skill = data.getSkillType("金刚铁甲");
        if (p < 30 && skill != null && m) {
            changeFighting.setChangehp(0);
            changeFighting.setChangemp(0);
            org2.setProcessState("免疫");
            org2.setText("金刚铁甲#2");
        }
        addState = data.xzstate("刚柔兼备");
        if (addState != null) {
            int fmId2 = (int) addState.getStateEffect();
            int fmSld2 = (int) addState.getStateEffect2();
            xs -= FaMenUtil.getDouble(fmId2, fmSld2, 3);
        }
        addState = data.xzstate("流连忘返");
        if (addState != null) {
            xs -= addState.getStateEffect2();
        }
        FightingSkill skill_1890 = data.getSkillType("不动如山");
        if (skill_1890 != null) {
            xs -= skill_1890.getSkillhurt();
        }
        xs -= data.getFamencsJv(3, new String[]{"以静制动"});
        addState = data.xzstate("妙法莲华");
        if (addState != null) {
            xs -= data.getFamencsJv(2, new String[]{"妙法莲华"});
        }
        if (xs != 100.0) {
            xs /= 100.0;
            if (xs < 0.0) {
                xs = 0.0;
            }
            changeFighting.setChangehp((int) Math.round((double) changeFighting.getChangehp() * xs));//修复飞升以后已方互砍免伤的状态
        }
        for (int i = 0; i <= data.getSkills().size() - 1; ++i) {
            if (data.getStates() == 1) {
                return;
            }// 死亡处理
            if (data.getSkills().get(i).getSkillname().equals("振羽惊雷")) {
                if (data.getAddStates().size() != 0) {
                    for (int k = 0; k <= data.getAddStates().size() - 1; ++k) {
                        if ((data.getAddStates().get(k).getStatename().equals("混乱") || data.getAddStates().get(k).getStatename().equals("封印") || data.getAddStates().get(k).getStatename().equals("遗忘") || ((AddState) data.getAddStates().get(k)).getStatename().equals("昏睡") || ((AddState) data.getAddStates().get(k)).getStatename().equals("中毒")) && 50 > Battlefield.random.nextInt(100)) {
                            MixDeal.first(data, data.getSkills().get(i).getSkillname(), battlefield);
                        }
                    }
                } else {
                    data.getSkills().get(i).setSkillhurt((-((FightingSkill) data.getSkills().get(i)).getSkillhurt() > (double) data.getMp_z() * 0.78) ? ((FightingSkill) data.getSkills().get(i)).getSkillhurt() : (((FightingSkill) data.getSkills().get(i)).getSkillhurt() + (double) changeFighting.getChangehp() * 0.5));
                }
            }
        }
    }
    //	1320	黄泉一笑	物理攻击每致死1个目标，可为本方死亡单位回复{公式一}%最大血量(优先主人)，每回合最多可回复2个目标。

    /**
     * 黄泉一笑
     */
    public static void BB_E_HQYX(ManData myData, ManData data, List<FightingState> list, Battlefield battlefield) {
        FightingSkill skill = myData.getSkillType(TypeUtil.BB_E_HQYX);
        if (skill == null) {
            return;
        }
        if (battlefield.CurrentRound > skill.getSkillcontinued()) {
            skill.setSkillbeidong(battlefield.CurrentRound);
            skill.setSkillsum(0);
        }
        if (skill.getSkillsum() >= 2) {
            return;
        }
        List<ManData> datas = MixDeal.get(false, myData, 2, data.getCamp(), 0, 3, 0, 0, 5, -1, battlefield, 0);
        if (datas.size() == 0) {
            return;
        }
        skill.setSkillsum(skill.getSkillsum() + 1);
        data = null;
        int i = 0;
        while (i < datas.size()) {
            if (datas.get(i).getMan() + 5 == myData.getMan()) {
                data = datas.get(i);
                break;
            } else {
                ++i;
            }
        }
        if (data == null) {
            data = (ManData) datas.get(0);
        }
        FightingState org3 = new FightingState();
        org3.setCamp(data.getCamp());
        org3.setMan(data.getMan());
        org3.setStartState(TypeUtil.JN);
        org3.setText("黄泉一笑,起#2");
        ChangeFighting changeFighting = new ChangeFighting();
        changeFighting.setChangehp((int) ((double) data.getHp_z() * skill.getSkillgain() / 100.0));
        ChangeProcess(changeFighting, null, data, org3, "药", list, battlefield);
    }

    public static void FM_jshx(ManData myData, ManData data, List<FightingState> list, Battlefield battlefield, String... types) {
        AddState addState = null;
        int i = 0;
        while (i < types.length) {
            addState = data.xzstate(types[i]);
            if (addState != null) {
                int fmId = (int) addState.getStateEffect();
                int fmSld = (int) addState.getStateEffect2();
                int camp = 0;
                if (myData == null) {
                    camp = battlefield.nomy(data.getCamp());
                } else {
                    camp = myData.getCamp();
                }
                List<ManData> datas1 = SSCAction.minhp(camp, 1, battlefield);
                data = (ManData) datas1.get(0);
                FightingState org3 = new FightingState();
                org3.setStartState("药");
                ChangeFighting changeFighting = new ChangeFighting();
                changeFighting.setChangehp(FaMenUtil.getInt(fmId, fmSld, 2));
                data.ChangeData(changeFighting, org3);
                if (list == null) {
                    FightingEvents moveEvents = new FightingEvents();
                    moveEvents.setAccepterlist(Arrays.asList(new FightingState[]{org3}));
                    battlefield.NewEvents.add(moveEvents);
                    break;
                } else {
                    list.add(org3);
                    break;
                }
            } else {
                ++i;
            }
        }
    }
}
