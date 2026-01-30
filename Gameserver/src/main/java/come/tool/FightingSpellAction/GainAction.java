package come.tool.FightingSpellAction;

import come.tool.FightingData.ChangeFighting;
import java.util.Iterator;
import java.util.List;
import come.tool.FightingData.AddState;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.Calculation;
import come.tool.FightingData.FightingState;
import java.util.Arrays;
import come.tool.FightingData.PK_MixDeal;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.MixDeal;
import java.util.ArrayList;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public class GainAction implements SpellAction
{
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        List<FightingSkill> skills = ControlAction.getSkills(manData, skill, battlefield.BattleType);
        String type = skill.getSkilltype();
        List<FightingState> Accepterlist = new ArrayList<>();
        List<ManData> datas = MixDeal.getjieshou(events, skill, manData, Accepterlist, battlefield);
        if (skill.getSkillid() >= 1000 && skill.getSkillid() <= 1099) {
            datas.removeIf(e -> (e.xzstate("屠巫剑") != null));
        }
        if (datas.size() == 0) {
            FightingState Originator = events.getOriginator();
            if (manData.daijia(skill, Originator, battlefield)) {
                return;
            }//扣除代价
            Originator.setStartState("法术攻击");
            Originator.setSkillsy(skill.getSkillname());
            events.setOriginator(null);
            Accepterlist.add(Originator);
            events.setAccepterlist(Accepterlist);
            battlefield.NewEvents.add(events);
            return;
        }
        if (datas.size() == 0) {
            FightingState Originator = events.getOriginator();
            if (manData.daijia(skill, Originator, battlefield)) {
                return;
            }
            Originator.setStartState("法术攻击");
            Originator.setSkillsy(skill.getSkillname());
            events.setOriginator(null);
            Accepterlist.add(Originator);
            events.setAccepterlist(Accepterlist);
            battlefield.NewEvents.add(events);
            return;
        }
        else {
            double qiang = 0.0;
            boolean is = false;
            boolean isAdd = false;
            if (skill.getSkillid() == 1029) {
                AddState addState = manData.xzstate(TypeUtil.TY_LL_QXGR);
                if (addState != null) {
                    qiang += addState.getStateEffect();
                }
            }
            else if (skill.getSkillid() == 1030) {
                double v = 0.0;
                FightingSkill TY_RH_DDYS_SG = manData.getSkillType(TypeUtil.TY_RH_JZGC_SG);
                if (TY_RH_DDYS_SG != null) {
                    qiang += TY_RH_DDYS_SG.getSkillhurt();
                }
                TY_RH_DDYS_SG = manData.getSkillType(TypeUtil.TY_RH_DDYS_SG);
                if (TY_RH_DDYS_SG == null) {
                    TY_RH_DDYS_SG = manData.getSkillType(TypeUtil.TY_RH_DDYS_GS);
                }
                if (TY_RH_DDYS_SG == null) {
                    TY_RH_DDYS_SG = manData.getSkillType(TypeUtil.TY_RH_DDYS_GP);
                }
                if (TY_RH_DDYS_SG != null && PK_MixDeal.isPK(battlefield.BattleType) && manData.getSkill_count_g() == 0 && Battlefield.isV(TY_RH_DDYS_SG.getSkillhurt())) {
                    skill.setSkillsum(skill.getSkillsum() + 1);
                    isAdd = true;
                }
                manData.setSkill_count_g(1);
                TY_RH_DDYS_SG = manData.getSkillType(TypeUtil.TY_RH_JZGC_GS);
                if (TY_RH_DDYS_SG != null) {
                    qiang += TY_RH_DDYS_SG.getSkillhurt();
                }
                TY_RH_DDYS_SG = manData.getSkillType(TypeUtil.TY_RH_JZGC_GP);
                if (TY_RH_DDYS_SG != null) {
                    qiang += TY_RH_DDYS_SG.getSkillhurt();
                }
                AddState addState2 = manData.xzstate(TypeUtil.TY_LL_BS);
                if (addState2 != null) {
                    skills = ControlAction.addSkill(manData.skillId(9207), skills);
                }
            }
            else if (skill.getSkillid() == 1039) {
                AddState addState = manData.xzstate(TypeUtil.TY_JS_YWDJ);
                if (addState != null) {
                    FightingSkill TY_JS_LLHZ = manData.getSkillType(TypeUtil.TY_JS_LLHZ);
                    if (TY_JS_LLHZ != null) {
                        skills = ControlAction.addSkill(manData.skillId(9827), skills);
                    }
                    skills = ControlAction.addSkill(manData.skillId(9231), skills);
                }
            }
            else if (skill.getSkillid() == 1040) {
                FightingSkill TY_RH_DDYS_SS = manData.getSkillType(TypeUtil.TY_RH_JZGC_SS);
                if (TY_RH_DDYS_SS != null) {
                    qiang += TY_RH_DDYS_SS.getSkillhurt();
                }
                TY_RH_DDYS_SS = manData.getSkillType(TypeUtil.TY_RH_DDYS_SS);
                if (TY_RH_DDYS_SS == null) {
                    TY_RH_DDYS_SS = manData.getSkillType(TypeUtil.TY_RH_DDYS_GS);
                }
                if (TY_RH_DDYS_SS != null && PK_MixDeal.isPK(battlefield.BattleType) && manData.getSkill_count_s() == 0 && Battlefield.isV(TY_RH_DDYS_SS.getSkillhurt())) {
                    skill.setSkillsum(skill.getSkillsum() + 1);
                    isAdd = true;
                }
                manData.setSkill_count_s(1);
                TY_RH_DDYS_SS = manData.getSkillType(TypeUtil.TY_RH_JZGC_GS);
                if (TY_RH_DDYS_SS != null) {
                    qiang += TY_RH_DDYS_SS.getSkillhurt();
                }
                AddState addState3 = manData.xzstate(TypeUtil.TY_JS_KHCH);
                if (addState3 != null) {
                    skills = ControlAction.addSkill(manData.skillId(9232), skills);
                }
            }
            else if (skill.getSkillid() == 1034) {
                AddState addState = manData.xzstate(TypeUtil.TY_KX_YZYT);
                if (addState != null) {
                    qiang += addState.getStateEffect();
                    skills = ControlAction.addSkill(manData.skillId(9250), skills);
                }
            }
            else if (skill.getSkillid() == 1035) {
                FightingSkill TY_RH_DDYS_SS = manData.getSkillType(TypeUtil.TY_RH_JZGC_GP);
                if (TY_RH_DDYS_SS != null) {
                    qiang += TY_RH_DDYS_SS.getSkillhurt();
                }
                TY_RH_DDYS_SS = manData.getSkillType(TypeUtil.TY_RH_DDYS_GP);
                if (TY_RH_DDYS_SS == null) {
                    TY_RH_DDYS_SS = manData.getSkillType(TypeUtil.TY_RH_DDYS_SP);
                }
                if (TY_RH_DDYS_SS != null && PK_MixDeal.isPK(battlefield.BattleType) && manData.getSkill_count_p() == 0 && Battlefield.isV(TY_RH_DDYS_SS.getSkillhurt())) {
                    skill.setSkillsum(skill.getSkillsum() + 1);
                    isAdd = true;
                }
                manData.setSkill_count_p(1);
                TY_RH_DDYS_SS = manData.getSkillType(TypeUtil.TY_RH_JZGC_SP);
                if (TY_RH_DDYS_SS != null) {
                    qiang += TY_RH_DDYS_SS.getSkillhurt();
                }
                AddState addState3 = manData.xzstate(TypeUtil.TY_KX_HYCF);
                if (addState3 != null) {
                    qiang += addState3.getStateEffect();
                }
            }
            FightingSkill skill2 = null;
            FightingSkill skill3 = null;
            FightingSkill skill4 = null;
            FightingSkill skill5 = null;
            if (skill.getSkillid() == 1034) {
                skill2 = manData.skillId(9241);
                if (skill2 != null) {
                    skill3 = manData.skillId(9244);
                    skill4 = manData.skillId(9247);
                }
            }
            if (PK_MixDeal.isPK(battlefield.BattleType) && manData.getstat(TypeUtil.TY_LL_BS) != null) {
                skill5 = manData.skillId(TypeUtil.TY_LL_JBZH);
            }
            FightingSkill bb_e_qhjs = null;
            if (skill.getSkilltype().equals(TypeUtil.JS)) {
                bb_e_qhjs = manData.getSkillType(TypeUtil.BB_E_QHJS);
            }
            int consume = 0;
            Double value = manData.containsSkillIfConditionMet(skill.getSkilltype());
            if (value != null) {
                consume = value.intValue();
            }
            int num = skill.getSkillsum();
            if ((manData.jlmh != 0 && skill.getSkillid() == 1078) || skill.getSkillid() == 1080) {
                skill.setSkillsum((int)battlefield.skillNum(skill, num));
            }
            FightingState Originator2 = events.getOriginator();
            if (bb_e_qhjs != null) {
                Originator2.setText("强化加速#2");
            }
            int state = manData.getStates();
            if (manData.daijia(skill, Originator2, battlefield, consume)) {
                return;
            }
            if (state == 0 && state != manData.getStates()) {
                state = 1;
            }
            else {
                state = 0;
            }
            Originator2.setStartState("法术攻击");
            Originator2.setSkillsy(skill.getSkillname());
            for (int i = 0; i < datas.size(); ++i) {
                ManData data = (ManData)datas.get(i);
                data.addBear(type);
                double addQF = 0.0;
                if (PK_MixDeal.isPK(battlefield.BattleType) && skill.getSkilltype().equals(TypeUtil.MH)) {
                    List<FightingSkill> skillList = manData.getSkillTypes(Arrays.asList(new String[] { TypeUtil.TY_G_10100, TypeUtil.TY_G_10105 }));
                    for (FightingSkill fightingSkill : skillList) {
                        if (data.getType() == 1 && !battlefield.hasMaster(data)) {
                            addQF += Battlefield.getSkillhurt(fightingSkill, "惑");
                        }
                    }
                }
                FightingState Accepter = new FightingState();
                double xs = Calculation.getCalculation().mofa(skill.getSkillgain(), manData, type, qiang + addQF);
                FightingSkill skill6 = skill.clone();
                FightingSkill skill_4018 = data.getSkillType("4018");
                if (skill_4018 != null && Battlefield.isV(skill_4018.getValue1()) && (type == "力量" || type == "抗性" || type == "加速" || type == "smmh")) {
                    skill6.setSkillcontinued(skill6.getSkillcontinued() + 1);
                }
                ChangeFighting change = this.TypeGain(manData, data, type, skill6, xs, skills, battlefield, is);
                if (change != null && skill != null) {
                    change.setSkill(skill);
                }
                if (bb_e_qhjs != null) {
                    change.setChangevlaue(change.getChangevlaue() + bb_e_qhjs.getSkillgain());
                }
                FightingPackage.ChangeProcess(change, manData, data, Accepter, type, Accepterlist, battlefield);
                Accepter.setSkillskin(skill.getSkillid() + "");
                if (skill2 != null && Battlefield.isV(skill2.getSkillhurt())) {
                    AddState addState4 = new AddState(skill2.getSkilltype(), skill2.getSkillhurt() / 6.0 * 4000.0, skill2.getSkillhurt() / 6.0 * 4000.0, 3);
                    if (skill3 != null) {
                        addState4.setSkill(skill3);
                    }
                    if (skill4 != null) {
                        addState4.setSkill(skill4);
                    }
                    data.getAddStates().add(addState4);
                }
                if (skill5 != null) {
                    AddState addState4 = data.getstat(skill5.getSkilltype());
                    double stateEffect1;
                    double stateEffect2;
                    if (addState4 == null) {
                        List<AddState> addStates = data.getAddStates();
                        String skilltype = skill2.getSkilltype();
                        stateEffect1 = 0.0;
                        addStates.add(addState4 = new AddState(skilltype, stateEffect1, stateEffect2 = 0.0, 9999));
                    }
                    else {
                        stateEffect1 = addState4.getStateEffect();
                        stateEffect2 = addState4.getStateEffect2();
                    }
                    stateEffect1 += skill5.getSkillhurt();
                    double maxStateEffect1 = skill5.getSkillhurt();
                    if (stateEffect1 > maxStateEffect1) {
                        stateEffect1 = maxStateEffect1;
                    }
                    stateEffect2 += skill5.getSkillgain1();
                    double maxStateEffect2 = skill5.getSkillhitrate();
                    if (stateEffect2 > maxStateEffect2) {
                        stateEffect2 = maxStateEffect2;
                    }
                    addState4.setStateEffect(stateEffect1);
                    addState4.setStateEffect2(stateEffect2);
                }
            }
            if (isAdd) {
                skill.setSkillsum(skill.getSkillsum() - 1);
            }
            if (events.getOriginator() != null) {
                Accepterlist.add(Originator2);
            }
            events.setOriginator(null);
            if (Accepterlist.size() != 0) {
                events.setAccepterlist(Accepterlist);
                battlefield.NewEvents.add(events);
            }
            if (state == 1) {
                MixDeal.DeathSkill(manData, Originator2, battlefield);
            }
            return;
        }
    }
    
    public ChangeFighting TypeGain(ManData mydata, ManData data, String type, FightingSkill skill, double xs, List<FightingSkill> skills, Battlefield battlefield, boolean is) {
        ChangeFighting changeFighting = new ChangeFighting();
        changeFighting.setChangetype(type);
        changeFighting.setChangesum(skill.getSkillcontinued());
        if (skills != null) {
            for (int i = skills.size() - 1; i >= 0; --i) {
                FightingSkill skill2 = (FightingSkill)skills.get(i);
                int id = skill2.getSkillid();
                if (id == 9202) {
                    if (Battlefield.isV(skill2.getSkillhurt())) {
                        changeFighting.setChangesum(changeFighting.getChangesum() + 1);
                    }
                }
                else if (id == 9243) {
                    if (Battlefield.isV(skill2.getSkillhurt()) && data.xzstate(TypeUtil.KX) != null) {
                        changeFighting.setChangesum(changeFighting.getChangesum() + 1);
                    }
                }
                else if (id == 9403) {
                    if (Battlefield.isV(skill2.getSkillhurt()) && data.xzstate(TypeUtil.MH) != null) {
                        changeFighting.setChangesum(changeFighting.getChangesum() + 1);
                    }
                }
                else if (id == 9228) {
                    if (data.getType() == 0 && Battlefield.isV(skill2.getSkillhurt())) {
                        ManData data2 = battlefield.getSeek(data, 3);
                        if (data2 != null) {
                            data2.addAddState(skill2.getSkilltype(), 10.0, 0.0, 3);
                        }
                    }
                }
                else if (id == 9204 || id == 9242 || id == 9402) {
                    if (data.getType() == 1) {
                        xs += skill2.getSkillhurt();
                    }
                }
                else if (id == 9222) {
                    xs += skill2.getSkillhurt();
                }
                else if (id == 9225) {
                    if (data.xzstate(TypeUtil.JS) != null) {
                        xs += skill2.getSkillhurt();
                    }
                }
                else if (id == 9248) {
                    AddState addState = data.xzstate("封印");
                    if (addState != null) {
                        addState.isEnd();
                    }
                    addState = data.xzstate("混乱");
                    if (addState != null) {
                        addState.isEnd();
                    }
                    addState = data.xzstate("昏睡");
                    if (addState != null) {
                        addState.isEnd();
                    }
                    addState = data.xzstate("遗忘");
                    if (addState != null) {
                        addState.isEnd();
                    }
                }
                else if (id == 9401) {
                    xs += skill2.getSkillhurt();
                }
                else if (id == 9820) {
                    if (Battlefield.isV(skill2.getSkillhurt()) && skill.getSkillid() == 1038) {
                        skill.setSkillsum(6);
                    }
                }
                else if (id == 9407) {
                    if (Battlefield.isV(skill2.getSkillhurt())) {
                        changeFighting.setChangetype2("非控制减益");
                    }
                }
                else if (id == 10040) {
                    if (data.xzstate("封印") != null || data.xzstate("混乱") != null || data.xzstate("遗忘") != null || data.xzstate("昏睡") != null) {
                        xs += skill.getSkillhurt();
                    }
                }
                else if (id == 10041) {
                    if (Battlefield.isV(skill2.getSkillhurt()) && skill.getSkillid() == 1033) {
                        skill.setSkillsum(6);
                    }
                }
                else if (id == 10042) {
                    if (Battlefield.isV(skill2.getSkillhurt())) {
                        AddState addState = data.xzstate(TypeUtil.FB_JGE);
                        if (addState != null) {
                            addState.isEnd();
                        }
                        addState = data.xzstate(TypeUtil.FB_QW);
                        if (addState != null) {
                            addState.isEnd();
                        }
                    }
                }
                else if (id == 10050) {
                    if (Battlefield.isV(skill2.getSkillgain())) {
                        changeFighting.setChangesum(changeFighting.getChangesum() + 1);
                        if (Battlefield.isV(skill2.getSkillhurt())) {
                            changeFighting.setChangesum(changeFighting.getChangesum() + 2);
                        }
                    }
                }
                else if (id == 22019) {
                    if (data.getType() == 1) {
                        if (skill.getSkillid() >= 1026 && skill.getSkillid() <= 1030) {
                            data.addAddState("困兽之斗-牛", (double)id, (double)(int)mydata.getFmsld2(), changeFighting.getChangesum());
                        }
                        else if (skill.getSkillid() >= 1036 && skill.getSkillid() <= 1040) {
                            data.addAddState("困兽之斗-速", (double)id, (double)(int)mydata.getFmsld2(), changeFighting.getChangesum());
                        }
                    }
                }
                else {
                    changeFighting.setSkill(skill2);
                }
            }
        }
        changeFighting.setChangevlaue(xs);
        if (type.equals(TypeUtil.KX)) {
            changeFighting.setChangevlaue2(xs / 3.0);
        }
        else if (type.equals(TypeUtil.MH)) {
            if (data.getCamp() != mydata.getCamp()) {
                changeFighting.setChangetype(TypeUtil.MHD);
            }
            changeFighting.setChangevlaue2(xs / 2.0);
        }
        return changeFighting;
    }
}
