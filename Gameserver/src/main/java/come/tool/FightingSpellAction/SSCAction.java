package come.tool.FightingSpellAction;

import cn.hutool.core.collection.ListUtil;
import java.util.Iterator;
import java.util.List;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.Calculation;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.FightingState;
import java.util.Arrays;
import come.tool.FightingData.AddState;
import come.tool.FightingData.PK_MixDeal;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.MixDeal;
import java.util.ArrayList;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public class SSCAction implements SpellAction
{
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        String type = skill.getSkilltype();
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
        }
        else {
            int die = 0;
            double qssc = 0.0;
            double qsschf = 0.0;
            FightingSkill skill2 = null;
            FightingSkill skill3 = null;
            FightingSkill skill4 = null;
            FightingSkill skill5 = null;
            FightingSkill skill6 = null;
            FightingSkill skill7 = manData.skillId(22025);
            skill2 = manData.skillId(9382);
            if (skill2 != null) {
                for (int i = 0; i < battlefield.fightingdata.size(); ++i) {
                    ManData data = (ManData)battlefield.fightingdata.get(i);
                    if (data.getType() != 3 && data.getType() != 4 && data.getCamp() == manData.getCamp() && (data.getType() != 1 || data.getStates() == 0) && data.getvalue(0) < 0.7) {
                        skill2 = null;
                        break;
                    }
                }
                if (skill2 != null) {
                    qssc = skill2.getSkillhurt();
                    qsschf = -skill2.getSkillhurt() / 125.0;
                }
                skill2 = null;
            }
            FightingSkill skill8 = manData.getSkillType(TypeUtil.TY_G_10071);
            if (skill8 != null && manData.xzstate("骨盾") != null) {
                qssc += skill8.getSkillhurt();
            }
            FightingSkill skill9 = manData.getSkillType(TypeUtil.TY_G_10073);
            if (skill9 != null && manData.getvalue(0) > 0.3) {
                qssc += skill9.getSkillhurt();
            }
            AddState addState = manData.xzstate("灼烧");
            if (addState != null) {
                qsschf = -addState.getStateEffect() / 100.0;
            }
            if (PK_MixDeal.isPK(battlefield.BattleType)) {
                FightingSkill TY_G_10074 = manData.getSkillType(TypeUtil.TY_G_10074);
                if (TY_G_10074 != null && Battlefield.isV(TY_G_10074.getSkillhurt())) {
                    manData.getQuality().addxghw(TY_G_10074);
                    manData.getSkills().remove(TY_G_10074);
                }
            }
            if (skill.getSkillid() == 1070 && datas.size() <= 2) {
                skill2 = manData.skillId(9381);
                if (skill2 != null) {
                    skill3 = manData.skillId(9383);
                    if (skill3 != null) {
                        skill4 = manData.skillId(9386);
                    }
                }
            }
            int num = skill.getSkillsum();
            if ((manData.jlss != 0 && skill.getSkillid() == 1068) || skill.getSkillid() == 1070) {
                skill.setSkillsum((int)battlefield.skillNum(skill, num));
            }
            FightingState Originator2 = events.getOriginator();
            if (manData.daijia(skill, Originator2, battlefield)) {
                return;
            }
            Originator2.setStartState("法术攻击");
            Originator2.setSkillsy(skill.getSkillname());
            int mzSize = 0;
            List<Integer> hfl = new ArrayList<>();
            List<Integer> hfl2 = null;
            List<FightingSkill> skills = ControlAction.getSkills(manData, skill, battlefield.BattleType);
            for (int j = 0; j < datas.size(); ++j) {
                ManData data2 = (ManData)datas.get(j);
                FightingState Accepter = MixDeal.DSMY(manData, data2, skill.getSkillid(), battlefield);
                if (Accepter != null) {
                    Accepterlist.add(Accepter);
                }
                else {
                    ++mzSize;
                    data2.addBear(type);
                    if (skills != null) {
                        AddState state = new AddState("三尸虫", 0.0, 0.0, 1);
                        state.setSkills(skills);
                        data2.getAddStates().add(state);
                    }
                    double addQFS = 0.0;
                    double addQF = 0.0;
                    double addKX = 0.0;
                    if (PK_MixDeal.isPK(battlefield.BattleType) && skill.getSkilltype().equals("三尸虫")) {
                        List<FightingSkill> skillList = manData.getSkillTypes(Arrays.asList(new String[] { TypeUtil.TY_G_10090, TypeUtil.TY_G_10095 }));
                        for (FightingSkill fightingSkill : skillList) {
                            if (data2.getType() == 0 && !battlefield.hasPet(data2)) {
                                addQF += Battlefield.getSkillhurt(fightingSkill, "蛊");
                            }
                        }
                    }
                    Accepter = new FightingState();
                    Accepter.setStartState(TypeUtil.JN);
                    ChangeFighting nomychange = new ChangeFighting();
                    int sh = Calculation.getCalculation().sssh(manData, data2, MixDeal.addition(Accepter, skill.getSkillhurt() + qssc, manData, type), addQFS, addQF, addKX);
                    nomychange.setChangehp(-sh);
                    FightingSkill TY_G_10075 = manData.getSkillType(TypeUtil.TY_G_10077);
                    if (TY_G_10075 != null) {
                        qsschf -= TY_G_10075.getSkillhurt();
                        manData.getSkills().remove(TY_G_10075);
                    }
                    int Vampire = Calculation.getCalculation().sshx(manData, data2, skill.getSkillgain() + qsschf, (double)sh);
                    hfl.add(Integer.valueOf(Vampire));
                    FightingPackage.ChangeProcess(nomychange, manData, data2, Accepter, type, Accepterlist, battlefield);
                    if (skill2 != null && Battlefield.isV(skill2.getSkillhurt())) {
                        FightingState org = new FightingState();
                        org.setStartState(TypeUtil.JN);
                        nomychange.setChangehp(-sh);
                        nomychange.setChangemp(0);
                        FightingPackage.ChangeProcess(nomychange, manData, data2, org, type, Accepterlist, battlefield);
                        if (skill3 != null && Battlefield.isV(skill3.getSkillhurt())) {
                            if (hfl2 == null) {
                                hfl2 = new ArrayList<>();
                            }
                            hfl2.add(Integer.valueOf(hfl.size() - 1));
                        }
                    }
                    if (data2.getStates() != 0) {
                        manData.addAttackDie();
                        ++die;
                    }
                }
                Accepter.setSkillskin(skill.getSkillid() + "");
            }
            if (events.getOriginator() != null) {
                Accepterlist.add(Originator2);
            }
            events.setOriginator(null);
            if (Accepterlist.size() != 0) {
                events.setAccepterlist(Accepterlist);
                battlefield.NewEvents.add(events);
            }
            MixDeal.passiveSpellAction(manData, skill.getSkilltype(), Accepterlist, battlefield, datas, (double)(mzSize / datas.size()));
            if (die >= 3) {
                skill5 = manData.skillId(9385);
                if (skill5 != null) {
                    skill6 = manData.skillId(9388);
                }
            }
            santiaochong(hfl, hfl2, manData, skill4, skill5, skill6, skill7, battlefield);
            return;
        }
    }
    
    public static void santiaochong(List<Integer> hps, List<Integer> hps2, ManData myData, FightingSkill skill4, FightingSkill skill5, FightingSkill skill6, FightingSkill skill7, Battlefield battlefield) {
        if (hps.size() == 0) {
            return;
        }
        int camp = myData.getCamp();
        FightingEvents fEvents = new FightingEvents();
        List<FightingState> Accepterlist = new ArrayList<>();
        List<ManData> datas = minhp(camp, hps.size(), battlefield);
        for (int i = 0; i < hps.size() && i < datas.size(); ++i) {
            FightingState fightingState = new FightingState();
            int hp = (int)hps.get(i);
            ManData data = (ManData)datas.get(i);
            AddState addState = data.xzstate("伤害加深");
            if (addState != null) {
                hp = (int)((double)hp * (1.0 - addState.getStateEffect() / 100.0));
            }
            addState = data.xzstate("紫燕翻飞");
            if (addState != null) {
                hp = (int)((double)hp * (addState.getStateEffect() / 100.0));
            }
            FightingSkill TY_G_10070 = myData.getSkillType(TypeUtil.TY_G_10070);
            if (TY_G_10070 != null) {
                hp = (int)((double)hp * (1.0 + TY_G_10070.getSkillhurt() / 100.0));
            }
            ChangeFighting changeFighting = new ChangeFighting();
            changeFighting.setChangehp(hp);
            TY_G_10070 = myData.getSkillType(TypeUtil.TY_G_10072);
            if (TY_G_10070 != null && Battlefield.isV(TY_G_10070.getSkillhurt())) {
                changeFighting.setChangemp((int)TY_G_10070.getSkillgain());
            }
            if (skill5 != null && Battlefield.isV(skill5.getSkillhurt()) && data.RemoveNegativeState(fightingState) && skill6 != null) {
                changeFighting.setChangemp((int)skill6.getSkillhurt());
            }
            if (skill7 != null && data.getvalue(0) < 0.6) {
                double jl = myData.getFmJv(1, new String[] { "血蛊佑身" });
                if (data.getHp() + hp > data.getHp_z() && jl > (double)Battlefield.random.nextInt(100)) {
                    changeFighting.setChangetype("血蛊护盾");
                    changeFighting.setChangesum(3);
                    double bl = myData.getFmJv(2, new String[] { "血蛊佑身" });
                    double v = bl * (double)(data.getHp() + hp - data.getHp_z());
                    int fmJv = myData.getFmValue(1, new String[] { "血蛊佑身" });
                    if (v > (double)fmJv) {
                        v = (double)fmJv;
                    }
                    changeFighting.setChangevlaue(v);
                }
            }
            data.ChangeData(changeFighting, fightingState);
            fightingState.setStartState("药");
            Accepterlist.add(fightingState);
        }
        if (hps2 != null) {
            for (int i = 0; i < hps2.size(); ++i) {
                int p = (int)hps2.get(i);
                if (p < hps.size() && p < datas.size()) {
                    FightingState fightingState2 = new FightingState();
                    int hp2 = (int)hps.get(p);
                    ManData data2 = (ManData)datas.get(p);
                    if (skill4 != null && Battlefield.isV(skill4.getSkillhurt())) {
                        List<ManData> datas2 = minhp(camp, 1, battlefield);
                        if (datas2.size() != 0) {
                            data2 = (ManData)datas2.get(0);
                        }
                    }
                    AddState addState2 = data2.xzstate("伤害加深");
                    if (addState2 != null) {
                        hp2 = (int)((double)hp2 * (1.0 - addState2.getStateEffect() / 100.0));
                    }
                    addState2 = data2.xzstate("紫燕翻飞");
                    if (addState2 != null) {
                        hp2 = (int)((double)hp2 * (addState2.getStateEffect() / 100.0));
                    }
                    ChangeFighting changeFighting = new ChangeFighting();
                    changeFighting.setChangehp(hp2);
                    data2.ChangeData(changeFighting, fightingState2);
                    fightingState2.setStartState("药");
                    Accepterlist.add(fightingState2);
                }
            }
        }
        if (Accepterlist.size() != 0) {
            fEvents.setAccepterlist(Accepterlist);
            battlefield.NewEvents.add(fEvents);
        }
    }
    
    public static List<ManData> minhp(int camp, int sum, Battlefield battlefield) {
        List<ManData> datas = new ArrayList<>();
        for (int i = 0; i < battlefield.fightingdata.size(); ++i) {
            ManData data = (ManData)battlefield.fightingdata.get(i);
            if (data.getCamp() == camp && data.getType() != 3 && data.getType() != 4 && (data.getType() != 1 || data.getStates() == 0) && data.xzstate("归墟") == null) {
                datas.add(data);
            }
        }
        if (datas.size() <= sum) {
            return datas;
        }
        boolean a = false;
        for (int j = 0; j < datas.size(); ++j) {
            for (int k = 1; k < datas.size(); ++k) {
                ManData data2 = (ManData)datas.get(k - 1);
                double value1 = data2.getvalue(1);
                ManData data3 = (ManData)datas.get(k);
                double value2 = data3.getvalue(1);
                if ((a && value1 < value2) || (!a && value1 > value2)) {
                    datas.set(k - 1, data3);
                    datas.set(k, data2);
                }
            }
        }
        return ListUtil.sub(datas, 0, sum);
    }
}
