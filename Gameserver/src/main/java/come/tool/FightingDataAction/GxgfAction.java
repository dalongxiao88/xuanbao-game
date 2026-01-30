package come.tool.FightingDataAction;

import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.AddState;
import cn.hutool.core.util.RandomUtil;
import come.tool.FightingData.Sepcies_MixDeal;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.PK_MixDeal;
import java.util.List;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.ManData;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.GxgfInterface;

public class GxgfAction implements GxgfInterface
{
    @Override
    public void gxgf(FightingState org2, ManData mydata, ManData data, ChangeFighting changeFighting, Battlefield battlefield, List<FightingState> list) {
        if (changeFighting.getChangehp() >= 0) {
            return;
        }
        AddState addState = null;
        FightingSkill skill = null;
        if (PK_MixDeal.isPK(battlefield.BattleType)) {
            skill = data.getAppendSkill(9232);
            if (skill != null && data.getvalue(0) < skill.getSkillhurt() / 100.0 && -changeFighting.getChangehp() >= data.getHp()) {
                org2.setProcessState("免疫");
                org2.setStartState("防御");
                skill = data.getSkillType(TypeUtil.TY_JS_BHJF);
                if (skill == null || !Battlefield.isV(skill.getSkillhurt())) {
                    data.RemoveAbnormal(new String[] { TypeUtil.JS });
                    org2.setEndState_2(TypeUtil.JS);
                }
                changeFighting.setChangehp(0);
                data.RemoveAppendSkill(9232);
                return;
            }
            else {
                addState = data.xzstate(TypeUtil.TY_KX_JJCS);
                if (addState != null) {
                    if (addState.getStateEffect() > (double)(-changeFighting.getChangehp())) {
                        addState.setStateEffect(addState.getStateEffect() + (double)changeFighting.getChangehp());
                        org2.setProcessState("吸收  " + -changeFighting.getChangehp());
                        org2.setStartState("防御");
                        changeFighting.setChangehp(0);
                    }
                    else {
                        data.RemoveAbnormal(new String[] { addState.getStatename() });
                        FightingState org3 = new FightingState();
                        org3.setCamp(data.getCamp());
                        org3.setMan(data.getMan());
                        org3.setStartState("防御");
                        org3.setProcessState("吸收  " + (int)addState.getStateEffect());
                        list.add(org3);
                        changeFighting.setChangehp((int)((double)changeFighting.getChangehp() + addState.getStateEffect()));
                        FightingSkill skill2 = addState.getSkill(9247);
                        if (skill2 != null && Battlefield.isV(skill2.getSkillhurt())) {
                            data.RemoveAbnormal(org3, ManData.values2);
                            org3.setEndState_2("清除异常状态");
                        }
                        if (mydata != null && mydata.getType() != 3 && mydata.getType() != 4) {
                            skill2 = addState.getSkill(9244);
                            if (skill2 != null && Battlefield.isV(skill2.getSkillhurt())) {
                                ChangeFighting change = new ChangeFighting();
                                change.setChangehp(-(int)(skill2.getSkillhurt() * 800.0));
                                FightingState org4 = new FightingState();
                                org4.setCamp(mydata.getCamp());
                                org4.setMan(mydata.getMan());
                                org2.setStartState("被攻击");
                                FightingPackage.ChangeProcess(change, data, mydata, org4, "盾破", list, battlefield);
                            }
                        }
                    }
                    return;
                }
                else {
                    addState = data.xzstate(TypeUtil.TY_MH_RSSQ);
                    if (addState != null) {
                        int maxHurt = (int)(addState.getStateEffect2() - addState.getStateEffect());
                        if (maxHurt > 0) {
                            if (maxHurt > -changeFighting.getChangehp()) {
                                addState.setStateEffect(addState.getStateEffect() + (double)changeFighting.getChangehp());
                                org2.setProcessState("吸收  " + -changeFighting.getChangehp());
                                org2.setStartState("防御");
                                changeFighting.setChangehp(0);
                            }
                            else {
                                addState.setStateEffect(addState.getStateEffect() + (double)maxHurt);
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
                    }
                    else if (Sepcies_MixDeal.getRace(data.getSe()) == 10003) {
                        addState = data.xzstate(TypeUtil.TY_L_ZXLT);
                        if (addState != null) {
                            if (addState.getStateEffect() > (double)(-changeFighting.getChangehp())) {
                                addState.setStateEffect(addState.getStateEffect() + (double)changeFighting.getChangehp());
                                org2.setProcessState("吸收  " + -changeFighting.getChangehp());
                                org2.setStartState("防御");
                                changeFighting.setChangehp(0);
                            }
                            else {
                                data.RemoveAbnormal(new String[] { addState.getStatename() });
                                FightingState org3 = new FightingState();
                                org3.setCamp(data.getCamp());
                                org3.setMan(data.getMan());
                                org3.setStartState("防御");
                                org3.setProcessState("吸收  " + (int)addState.getStateEffect());
                                org3.setEndState_2(addState.getStatename());
                                list.add(org3);
                                changeFighting.setChangehp((int)((double)changeFighting.getChangehp() + addState.getStateEffect()));
                            }
                            if (mydata != null) {
                                ChangeFighting fighting = new ChangeFighting();
                                FightingState Accepter2 = new FightingState();
                                Accepter2.setStartState("代价");
                                if (Battlefield.isV(50.0)) {
                                    fighting.setChangehp(-(int)addState.getStateEffect2());
                                }
                                else {
                                    fighting.setChangemp(-(int)addState.getStateEffect2());
                                }
                                mydata.ChangeData(fighting, Accepter2);
                                list.add(Accepter2);
                            }
                            return;
                        }
                        else {
                            addState = data.xzstate(TypeUtil.TY_S_XCWM);
                            if (addState != null) {
                                if (addState.getStateEffect() > (double)(-changeFighting.getChangehp())) {
                                    addState.setStateEffect(addState.getStateEffect() + (double)changeFighting.getChangehp());
                                    org2.setProcessState("吸收  " + -changeFighting.getChangehp());
                                    org2.setStartState("防御");
                                    changeFighting.setChangehp(0);
                                }
                                else {
                                    data.RemoveAbnormal(new String[] { addState.getStatename() });
                                    FightingState org3 = new FightingState();
                                    org3.setCamp(data.getCamp());
                                    org3.setMan(data.getMan());
                                    org3.setStartState("防御");
                                    org3.setProcessState("吸收  " + (int)addState.getStateEffect());
                                    org3.setEndState_2(addState.getStatename());
                                    list.add(org3);
                                    changeFighting.setChangehp((int)((double)changeFighting.getChangehp() + addState.getStateEffect()));
                                }
                                return;
                            }
                            else {
                                addState = data.xzstate(TypeUtil.TY_F_YYFQ);
                                if (addState != null) {
                                    if (addState.getStateEffect() > (double)(-changeFighting.getChangehp())) {
                                        addState.setStateEffect(addState.getStateEffect() + (double)changeFighting.getChangehp());
                                        org2.setProcessState("吸收  " + -changeFighting.getChangehp());
                                        org2.setStartState("防御");
                                        changeFighting.setChangehp(0);
                                    }
                                    else {
                                        data.RemoveAbnormal(new String[] { addState.getStatename() });
                                        FightingState org3 = new FightingState();
                                        org3.setCamp(data.getCamp());
                                        org3.setMan(data.getMan());
                                        org3.setStartState("防御");
                                        org3.setProcessState("吸收  " + (int)addState.getStateEffect());
                                        org3.setEndState_2(addState.getStatename());
                                        list.add(org3);
                                        changeFighting.setChangehp((int)((double)changeFighting.getChangehp() + addState.getStateEffect()));
                                    }
                                    if (mydata != null && Battlefield.isV(addState.getStateEffect2())) {
                                        mydata.addAddState(TypeUtil.TY_F_YYFQ_S, 0.0, 0.0, 2);
                                    }
                                    return;
                                }
                                else {
                                    addState = data.xzstate(TypeUtil.TY_H_ZSMH);
                                    if (addState != null) {
                                        if (addState.getStateEffect() > (double)(-changeFighting.getChangehp())) {
                                            addState.setStateEffect(addState.getStateEffect() + (double)changeFighting.getChangehp());
                                            org2.setProcessState("吸收  " + -changeFighting.getChangehp());
                                            org2.setStartState("防御");
                                            changeFighting.setChangehp(0);
                                        }
                                        else {
                                            data.RemoveAbnormal(new String[] { addState.getStatename() });
                                            FightingState org3 = new FightingState();
                                            org3.setCamp(data.getCamp());
                                            org3.setMan(data.getMan());
                                            org3.setStartState("防御");
                                            org3.setProcessState("吸收  " + (int)addState.getStateEffect());
                                            org3.setEndState_2(addState.getStatename());
                                            list.add(org3);
                                            changeFighting.setChangehp((int)((double)changeFighting.getChangehp() + addState.getStateEffect()));
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
            }
        }
        else {
            addState = data.xzstate(TypeUtil.TY_L_XSNJ);
            if (addState != null) {
                if (addState.getStateEffect() > 0.0) {
                    if (addState.getStateEffect() > (double)(-changeFighting.getChangehp())) {
                        addState.setStateEffect(addState.getStateEffect() + (double)changeFighting.getChangehp());
                        org2.setProcessState("吸收  " + -changeFighting.getChangehp());
                        org2.setStartState("防御");
                        changeFighting.setChangehp(0);
                    }
                    else {
                        FightingState org3 = new FightingState();
                        org3.setCamp(data.getCamp());
                        org3.setMan(data.getMan());
                        org3.setStartState("防御");
                        org3.setProcessState("吸收  " + (int)addState.getStateEffect());
                        list.add(org3);
                        changeFighting.setChangehp((int)((double)changeFighting.getChangehp() + addState.getStateEffect()));
                    }
                }
                if (mydata != null) {
                    FightingSkill skill3 = addState.getSkill(9265);
                    if (skill3 != null) {
                        ChangeFighting change2 = new ChangeFighting();
                        int hurt = (int)skill3.getSkillhurt() - 5000;
                        if (hurt > 0) {
                            hurt = Battlefield.random.nextInt(hurt);
                        }
                        hurt = (int)((double)hurt + skill3.getSkillhurt());
                        FightingSkill skill4 = addState.getSkill(9268);
                        if (skill4 != null && Battlefield.isV(skill4.getSkillhurt())) {
                            hurt = (int)((double)hurt + (double)data.getHp() * 0.1);
                        }
                        change2.setChangehp(-hurt);
                        FightingState org6 = new FightingState();
                        org6.setCamp(mydata.getCamp());
                        org6.setMan(mydata.getMan());
                        org6.setStartState("被攻击");
                        FightingPackage.ChangeProcess(change2, null, mydata, org6, TypeUtil.TY_L_DZXY, list, battlefield);
                    }
                    if (changeFighting.getChangehp() < 0) {
                        skill3 = addState.getSkill(9302);
                        if (skill3 != null) {
                            ChangeFighting change2 = new ChangeFighting();
                            int hurt = (int)((double)changeFighting.getChangehp() * skill3.getSkillhurt() / 100.0);
                            if (hurt < 0) {
                                change2.setChangehp(hurt);
                                FightingState org4 = new FightingState();
                                org4.setCamp(mydata.getCamp());
                                org4.setMan(mydata.getMan());
                                org4.setStartState("被攻击");
                                FightingPackage.ChangeProcess(change2, null, mydata, org4, TypeUtil.TY_L_DZXY, list, battlefield);
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
            }
            else {
                FightingState org5 = new FightingState();
                data.setNrfhht(0);
                org5.setCamp(data.getCamp());
                org5.setMan(data.getMan());
                org5.setStartState("防御");
                org5.setProcessState("吸收  " + fhht);
                org5.setEndState_2("法魂护体");
                list.add(org5);
                data.RemoveAbnormal(new String[] { "法魂护体" });
            }
        }
        addState = data.xzstate("血蛊护盾");
        if (addState != null) {
            if (addState.getStateEffect() > (double)(-changeFighting.getChangehp())) {
                addState.setStateEffect(addState.getStateEffect() + (double)changeFighting.getChangehp());
                org2.setProcessState("吸收  " + -changeFighting.getChangehp());
                org2.setStartState("防御");
                changeFighting.setChangehp(0);
            }
            else {
                FightingState org5 = new FightingState();
                org5.setCamp(data.getCamp());
                org5.setMan(data.getMan());
                org5.setStartState("防御");
                org5.setProcessState("吸收  " + (int)addState.getStateEffect());
                org5.setEndState_2(addState.getStatename());
                list.add(org5);
                changeFighting.setChangehp((int)((double)changeFighting.getChangehp() + addState.getStateEffect()));
                data.RemoveAbnormal(new String[] { addState.getStatename() });
            }
            return;
        }
        else {
            addState = data.xzstate("骨盾");
            if (addState == null) {
                addState = data.xzstate(TypeUtil.TY_L_GL_YMFZ);
            }
            if (addState != null) {
                if (addState.getStateEffect() > (double)(-changeFighting.getChangehp())) {
                    addState.setStateEffect(addState.getStateEffect() + (double)changeFighting.getChangehp());
                    org2.setProcessState("吸收  " + -changeFighting.getChangehp());
                    org2.setStartState("防御");
                    changeFighting.setChangehp(0);
                }
                else {
                    data.RemoveAbnormal(new String[] { addState.getStatename() });
                    FightingState org5 = new FightingState();
                    org5.setCamp(data.getCamp());
                    org5.setMan(data.getMan());
                    org5.setStartState("防御");
                    org5.setProcessState("吸收  " + (int)addState.getStateEffect());
                    org5.setEndState_2(addState.getStatename());
                    list.add(org5);
                    if (addState.getStatename().equals("骨盾")) {
                        changeFighting.setChangehp((int)((double)changeFighting.getChangehp() + addState.getStateEffect()));
                        if (mydata != null && mydata.getType() != 3 && mydata.getType() != 4) {
                            ChangeFighting change = new ChangeFighting();
                            change.setChangehp((int)(-addState.getStateEffect2()));
                            FightingState org4 = new FightingState();
                            org4.setCamp(mydata.getCamp());
                            org4.setMan(mydata.getMan());
                            org4.setStartState("被攻击");
                            FightingPackage.ChangeProcess(change, data, mydata, org4, "盾破", list, battlefield);
                        }
                    }
                }
                return;
            }
            else {
                addState = data.xzstate(TypeUtil.BB_E_百战重生);
                if (addState != null) {
                    if (addState.getSurplus() > 0) {
                        FightingState org5 = new FightingState();
                        org5.setCamp(data.getCamp());
                        org5.setMan(data.getMan());
                        org5.setStartState("防御");
                        org5.setProcessState("吸收  " + changeFighting.getChangehp());
                        list.add(org5);
                        changeFighting.setChangehp(-changeFighting.getChangehp());
                        addState.setSurplus(addState.getSurplus() - 1);
                        if (addState.getSurplus() <= 0) {
                            data.deleteState(TypeUtil.BB_E_百战重生);
                        }
                    }
                    return;
                }
                else {
                    addState = data.xzstate(TypeUtil.BB_YYDQ);
                    FightingSkill skill_BB_E_百战重生 = data.getSkillType(TypeUtil.BB_E_百战重生);
                    if (addState != null) {
                        FightingState org7 = new FightingState();
                        org7.setCamp(data.getCamp());
                        org7.setMan(data.getMan());
                        double eff = addState.getStateEffect();
                        if (skill_BB_E_百战重生 != null) {
                            eff += skill_BB_E_百战重生.getSkillhurt();
                        }
                        int effect = (int)(0.01 * eff * (double)changeFighting.getChangehp());
                        org7.setStartState("防御");
                        org7.setProcessState("吸收  " + Math.abs(effect));
                        list.add(org7);
                        changeFighting.setChangehp(changeFighting.getChangehp() + Math.abs(effect));
                        data.addAddState(TypeUtil.BB_E_百战重生, 0.0, 0.0, 2);
                        return;
                    }
                    else {
                        FightingSkill yydq = data.getSkillType(TypeUtil.BB_YYDQ);
                        if (yydq != null && (float)Math.abs(changeFighting.getChangehp()) > (float)data.getHp() * 0.3f && Math.abs(changeFighting.getChangehp()) < data.getHp()) {
                            double r = yydq.getSkillgain();
                            if (skill_BB_E_百战重生 != null) {
                                r += skill_BB_E_百战重生.getSkillgain();
                            }
                            if ((double)RandomUtil.randomInt(100) < r) {
                                FightingState org8 = new FightingState();
                                org8.setCamp(data.getCamp());
                                org8.setMan(data.getMan());
                                org8.setStartState(TypeUtil.JN);
                                org8.setSkillskin(TypeUtil.BB_YYDQ);
                                org8.setEndState_1(TypeUtil.BB_YYDQ);
                                list.add(org8);
                                data.addAddState(TypeUtil.BB_YYDQ, yydq.getSkillhurt(), 0.0, yydq.getSkillcontinued());
                            }
                        }
                        return;
                    }
                }
            }
        }
    }
}
