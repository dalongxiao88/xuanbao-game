package come.tool.FightingSpellAction;

import java.util.List;

import come.tool.FightingData.AddState;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.Calculation;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.MixDeal;

import java.util.ArrayList;

import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.PK_MixDeal;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public class ZSAction implements SpellAction {
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        FightingSkill skill2 = null;
        FightingSkill skill3 = null;
        FightingSkill skill4 = null;
        FightingSkill skill5 = null;
        FightingSkill skill6 = null;
        FightingSkill skill7 = null;
        FightingSkill skill8 = null;
        FightingSkill skill9 = null;
        FightingSkill skill10 = null;
        FightingSkill skill11 = null;
        FightingSkill skill12 = null;
        FightingSkill skill13 = null;
        FightingSkill skill14 = null;
        FightingSkill skill15 = null;
        FightingSkill skill16 = null;
        FightingSkill skill17 = null;
        boolean isAdd = false;
        boolean isWSDS = false;
        if (PK_MixDeal.isPK(battlefield.BattleType)) {
            skill2 = manData.getSkillType(TypeUtil.TY_ZS_SD);
            skill3 = manData.getSkillType(TypeUtil.TY_ZS_ZM);
            if (skill3 != null && !Battlefield.isV(skill3.getSkillhurt())) {
                skill3 = null;
            }
            if (skill3 != null) {
                skill4 = manData.getSkillType(TypeUtil.TY_ZS_BH);
                if (skill4 != null && !Battlefield.isV(skill4.getSkillhurt())) {
                    skill4 = null;
                }
                if (skill4 != null) {
                    skill5 = manData.getSkillType(TypeUtil.TY_ZS_RX);
                    if (skill5 != null && !Battlefield.isV(skill5.getSkillhurt())) {
                        skill5 = null;
                    }
                }
            }
            if (skill.getSkillid() == 1024) {
                skill6 = manData.getSkillType(TypeUtil.TY_ZS_TX);
            }
            skill10 = manData.getSkillType(TypeUtil.TY_ZS_ZY);
            skill11 = manData.getSkillType(TypeUtil.TY_ZS_SS);
            skill12 = manData.getSkillType(TypeUtil.TY_ZS_LXGY);
            skill13 = manData.getSkillType(TypeUtil.TY_ZS_YPTJ);
            skill14 = manData.getSkillType(TypeUtil.TY_ZS_NCPL);
            skill15 = manData.getSkillType(TypeUtil.TY_RH_DDYS_SS);
            skill16 = manData.getSkillType(TypeUtil.TY_RH_DDYS_SG);
            skill17 = manData.getSkillType(TypeUtil.TY_RH_DDYS_SP);
            skill7 = manData.getSkillType(TypeUtil.TY_ZS_FH);
            skill9 = manData.getSkillId(22019);
            if (skill.getSkillid() == 1025) {
                if (manData.getSkill_count_z() == 0 && (skill15 != null || skill16 != null || skill17 != null)) {
                    double v = 0.0;
                    if (skill15 != null) {
                        v = skill15.getSkillhurt();
                    }
                    if (skill16 != null) {
                        v = skill16.getSkillhurt();
                    }
                    if (skill17 != null) {
                        v = skill17.getSkillhurt();
                    }
                    if (Battlefield.isV(v)) {
                        skill.setSkillsum(skill.getSkillsum() + 1);
                        isAdd = true;
                    }
                }
                manData.setSkill_count_z(1);
            }
        }
        double qzsjc = 0.0;
        double upMin = 0.0;
        double upMax = 50.0;
        double MPXs = skill.getSkillhurt() + 8.0;
        double MPHurt = 0.0;
        skill8 = manData.getSkillType(TypeUtil.TY_ZS_FY2);
        if (skill8 != null) {
            qzsjc -= skill8.getSkillhurt();
            upMax = 50.0 + skill8.getSkillhurt();
        }
        if (skill.getSkillid() == 1025) {
            AddState addState = manData.xzstate(TypeUtil.TY_ZS_YL);
            if (addState != null) {
                upMin = addState.getStateEffect();
                upMax = addState.getStateEffect2();
            }
            addState = manData.xzstate(TypeUtil.TY_ZS_FY1);
            if (addState != null) {
                MPXs = addState.getStateEffect();
            }
        }
        if (upMin > upMax) {
            upMin = upMax;
        }
        if (upMax > 55.0) {
            upMax = 55.0;
        }
        skill8 = manData.getSkillType(TypeUtil.TY_ZS_GY);
        if (skill8 != null && !Battlefield.isV(skill8.getSkillhurt())) {
            skill8 = null;
        }
        List<FightingState> Accepterlist = new ArrayList<>();
        List<ManData> datas = MixDeal.getjieshou(events, skill, manData, Accepterlist, battlefield);
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
        } else {
            int consume = 0;
            Double value = manData.containsSkillIfConditionMet(skill.getSkilltype());
            if (value != null) {
                consume = value.intValue();
            }
            if (skill.getSkillid() == 1025) {
                FightingSkill TY_RH_NDQB_SS = manData.getSkillType(TypeUtil.TY_RH_JFZL_SS);
                if (TY_RH_NDQB_SS == null) {
                    TY_RH_NDQB_SS = manData.getSkillType(TypeUtil.TY_RH_JFZL_SG);
                }
                if (TY_RH_NDQB_SS == null) {
                    TY_RH_NDQB_SS = manData.getSkillType(TypeUtil.TY_RH_JFZL_SP);
                }
                if (TY_RH_NDQB_SS != null && Battlefield.isV(TY_RH_NDQB_SS.getSkillhurt())) {
                    isWSDS = true;
                }
            }
            FightingState Originator2 = events.getOriginator();
            if (manData.daijia(skill, Originator2, battlefield, consume)) {
                return;
            }
            Originator2.setStartState("法术攻击");
            Originator2.setSkillsy(skill.getSkillname());
            int mzSize = 0;
            for (int i = 0; i < datas.size(); ++i) {
                ManData data = (ManData) datas.get(i);
                FightingState Accepter = null;
                if (!isWSDS && (skill2 == null || !Battlefield.isV(skill2.getSkillhurt()))) {
                    Accepter = MixDeal.DSMY(manData, data, skill.getSkillid(), battlefield);
                }
                if (Accepter == null) {
                    ++mzSize;
                    data.addBear("震慑");
                    if (skill7 != null) {
                        data.addAddState(skill7.getSkilltype(), skill7.getSkillhurt(), 0.0, 0);
                    }
                    if (PK_MixDeal.isPK(battlefield.BattleType) && skill10 != null) {
                        manData.addAddState2(skill10.getSkilltype(), skill10.getSkillhurt(), 0.0, 9999);
                        AddState addState2 = manData.xzstate(TypeUtil.TY_ZS_ZY);
                        if (addState2.getStateEffect() >= skill10.getSkillgain()) {
                            addState2.setStateEffect(skill10.getSkillgain());
                            if (skill12 != null) {
                                manData.addAddState2(skill12.getSkilltype(), skill12.getSkillhurt(), 0.0, 9999);
                                addState2 = manData.xzstate(TypeUtil.TY_ZS_LXGY);
                                if (addState2 != null && addState2.getStateEffect() >= skill12.getSkillgain()) {
                                    addState2.setStateEffect(skill12.getSkillgain());
                                }
                            }
                        }
                    }
                    ChangeFighting changeFighting = new ChangeFighting();
                    int hp = data.getHp();
                    int mp = data.getMp();
                    double hpjichu = skill.getSkillhurt();
                    double mpjichu = MPXs;
                    double hurt = 0.0;
                    if (skill6 != null && data.getType() == 1) {
                        hpjichu -= skill6.getSkillhurt();
                    }
                    hpjichu = Calculation.getCalculation().mozs2(manData, data, hpjichu, qzsjc);
                    if (hpjichu < upMin) {
                        hpjichu = upMin;
                    } else if (hpjichu > upMax) {
                        hpjichu = upMax;
                    }
                    hpjichu -= data.getQuality().getK_zshp();
                    hpjichu = ((hpjichu > 0.0) ? hpjichu : 0.0);
                    mpjichu -= data.getQuality().getK_zsmp();
                    FightingSkill skill18 = data.getAppendSkill(9404);
                    if (skill18 != null) {
                        mpjichu -= skill18.getSkillhurt();
                    }
                    if (manData.getSkillId(30035) != null && skill.getSkillid() == 1024) {
                        mpjichu += manData.getSkillId(30035).getE1();
                    }
                    if (PK_MixDeal.isPK(battlefield.BattleType) && skill11 != null) {
                        mpjichu += skill11.getSkillhurt();
                        hpjichu -= skill11.getSkillgain();
                    }
                    if (!PK_MixDeal.isPK(battlefield.BattleType) && skill13 != null && data.getStates() == 0 && (double) data.getHp() < (double) data.getHp_z() * skill13.getSkillhurt() / 100.0) {
                        hpjichu = skill13.getSkillgain();
                    }
                    mpjichu = ((mpjichu > 0.0) ? mpjichu : 0.0);
                    double ffsh = manData.xxyfs;
                    double fffy = 0;
                    if (data.xzstate("冥河纱") != null) {
                        fffy += data.xzstate("冥河纱").getStateEffect2();
                    }
                    if (data.xzstate("凝霜印") != null) {
                        fffy -= Math.min(data.xzstate("凝霜印").getStateEffect3() * data.xzstate("凝霜印").getStateEffect5(), data.xzstate("凝霜印").getStateEffect4());
                    }
                    if (data.xzstate("赤炎锁") != null) {
                        fffy -= data.xzstate("赤炎锁").getStateEffect();
                    }
                    if (data.xzstate("散灵须") != null) {
                        fffy -= data.xzstate("散灵须").getStateEffect2();
                    }
                    if (manData.getSkillId(30014) != null && data.xzstate("赤炎锁") != null) {
                        ffsh += manData.getSkillId(30014).getP1();
                    }
                    if (manData.getSkillId(30014) != null && data.xzstate("赤炎锁") != null) {
                        ffsh += manData.getSkillId(30014).getP1();
                    }
                    int Hurt = (int) (hpjichu * (double) hp / 100.0+ ffsh - fffy);
                    if (manData.xzstate("振魂鼓") != null) {
                        double p = manData.xzstate("振魂鼓").getStateEffect() * manData.xzstate("振魂鼓").getStateEffect2() / 100.0D;
                        double p2 = manData.xzstate("振魂鼓").getStateEffect() * manData.xzstate("振魂鼓").getStateEffect3() / 100.0D;
                        double p1 = (manData.xzstate("振魂鼓").getSurplus() >= 997) ? (manData.xzstate("振魂鼓").getStateEffect4() / 100.0D) : 0.0D;
                        Hurt = (int) (Hurt * (1.0D + p + p1 + p2));
                    }
                    if (manData.xzstate("淬魂锤") != null) {
                        double p1 = (manData.xzstate("淬魂锤").getSurplus() >= 997) ? (manData.xzstate("淬魂锤").getStateEffect4() / 100.0D) : 0.0D;
                        double p2 = manData.xzstate("淬魂锤").getStateEffect3() / 100.0D;
                        Hurt = (int) (Hurt * (1.0D - p1 - p2));
                    }
                    int MpHurt = (int) (mpjichu * (double) mp / 100.0);
                    if ((double) MpHurt > MPHurt) {
                        MPHurt = (double) MpHurt;
                    }
                    if (Hurt <= 0) {
                        Hurt = 1;
                    }
                    if (skill9 != null) {
                        double bl = manData.getFmJv(3, new String[]{"困兽之斗"});
                        Hurt += (int) ((double) data.getHp_z() * (bl / 100.0));
                        if (Hurt > data.getHp_z()) {
                            Hurt = data.getHp_z();
                        }
                    }
                    if (skill.getSkillid() == 1025) {
                        AddState addState3 = manData.xzstate(TypeUtil.TY_ZS_FY1);
                        if (addState3 != null) {
                            MpHurt += MpHurt * (i + 1) * (int) addState3.getStateEffect() / 100 / (i + 1);
                            if (skill14 != null) {
                                manData.addAddState(skill14.getSkilltype(), skill14.getSkillhurt(), 0.0, 0);
                            }
                        }
                    }
                    changeFighting.setChangehp(-Hurt);
                    changeFighting.setChangemp(-MpHurt);
                    Accepter = new FightingState();
                    FightingPackage.ChangeProcess(changeFighting, manData, data, Accepter, "震慑", Accepterlist, battlefield);
                } else {
                    Accepterlist.add(Accepter);
                }
                Accepter.setSkillskin(skill.getSkillid() + "");
                if (data.getStates() != 0) {
                    manData.addAttackDie();
                }
            }
            if (events.getOriginator() != null) {
                Accepterlist.add(Originator2);
            }
            events.setOriginator(null);
            if (Accepterlist.size() != 0) {
                events.setAccepterlist(Accepterlist);
                battlefield.NewEvents.add(events);
            }
            MixDeal.passiveSpellAction(manData, skill.getSkilltype(), Accepterlist, battlefield, datas, (double) (mzSize / datas.size()));
            if (skill8 != null) {
                datas = MixDeal.get(true, manData, 0, battlefield.nomy(manData.getCamp()), 0, 0, 0, 0, 1, 3, battlefield, 1);
                if (datas.size() != 0) {
                    ManData data2 = (ManData) datas.get(0);
                    FightingEvents events2 = new FightingEvents();
                    List<FightingState> fightingStates = new ArrayList<>();
                    FightingState fightingState = new FightingState();
                    ChangeFighting changeFighting2 = new ChangeFighting();
                    changeFighting2.setChangemp((int) skill8.getSkillhurt() * 500);
                    data2.ChangeData(changeFighting2, fightingState);
                    fightingState.setStartState("药");
                    fightingStates.add(fightingState);
                    events2.setAccepterlist(fightingStates);
                    battlefield.NewEvents.add(events2);
                }
            }
            if (skill3 != null) {
                MPXs = MPXs * (skill3.getSkillhurt() / 2.0 + 10.0) / 100.0;
                ManData data2 = manData;
                if (skill4 != null) {
                    datas = MixDeal.get(true, manData, 0, battlefield.nomy(manData.getCamp()), 0, 0, 0, 0, 1, 3, battlefield, 1);
                    if (datas.size() != 0) {
                        data2 = (ManData) datas.get(0);
                    }
                }
                FightingEvents events2 = new FightingEvents();
                List<FightingState> fightingStates = new ArrayList<>();
                FightingState fightingState = new FightingState();
                ChangeFighting changeFighting2 = new ChangeFighting();
                changeFighting2.setChangemp((int) MPHurt);
                if (skill5 != null) {
                    changeFighting2.setChangehp(changeFighting2.getChangemp());
                }
                data2.ChangeData(changeFighting2, fightingState);
                fightingState.setStartState("药");
                fightingStates.add(fightingState);
                events2.setAccepterlist(fightingStates);
                battlefield.NewEvents.add(events2);
            }
            if (isAdd) {
                skill.setSkillsum(skill.getSkillsum() - 1);
            }
            return;
        }
    }

    public static ChangeFighting TypeHurtCurrent(ManData myData, ManData nomyData, double jichu) {
        ChangeFighting changeFighting = new ChangeFighting();
        int hp = nomyData.getHp();
        int mp = nomyData.getMp();
        double hpjichu = Calculation.getCalculation().mozs(myData, nomyData, jichu);
        hpjichu -= nomyData.getQuality().getK_zshp();
        hpjichu = ((hpjichu > 0.0) ? hpjichu : 0.0);
        jichu -= nomyData.getQuality().getK_zsmp();
        jichu = ((jichu > 0.0) ? jichu : 0.0);
        int Hurt = (int) (hpjichu * (double) hp / 100.0);
        int MpHurt = (int) (jichu * (double) mp / 100.0);
        if (Hurt <= 0) {
            Hurt = 1;
        }
        changeFighting.setChangehp(-Hurt);
        changeFighting.setChangemp(-MpHurt);
        return changeFighting;
    }
}
