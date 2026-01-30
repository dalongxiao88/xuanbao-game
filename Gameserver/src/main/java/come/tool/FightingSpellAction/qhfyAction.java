package come.tool.FightingSpellAction;

import java.util.stream.Collectors;
import come.tool.FightingData.Calculation;
import java.util.List;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.AddState;
import come.tool.FightingData.SummonType;
import come.tool.FightingData.PK_MixDeal;
import come.tool.FightingData.MixDeal;
import java.util.ArrayList;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public class qhfyAction implements SpellAction
{
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        int size = 0;
        int spl = 0;
        FightingSkill skill2 = null;
        FightingSkill skill3 = null;
        FightingSkill skill4 = null;
        int num = skill.getSkillsum();
        if ((manData.jlyw != 0 && skill.getSkillid() == 1073) || skill.getSkillid() == 1075) {
            skill.setSkillsum((int)battlefield.skillNum(skill, num));
        }
        String type = skill.getSkilltype();
        List<FightingState> Accepterlist = new ArrayList<>();
        List<ManData> datas = MixDeal.getjieshou(events, skill, manData, Accepterlist, battlefield);
        List<FightingSkill> skills = getSkills(manData, skill, battlefield.BattleType);
        AddState subState = null;
        if (skill.getSkillid() == 1005) {
            AddState addState = manData.xzstate("9857");
            if (PK_MixDeal.isPK(battlefield.BattleType)) {
                if (addState != null) {
                    SummonType.xianzhi(manData, skill, true);
                    manData.addAddState("忽视混乱", addState.getStateEffect(), 0.0, 5);
                    subState = new AddState("抗混乱", -addState.getStateEffect2(), 0.0, 10);
                    manData.getAddStates().remove(addState);
                }
                addState = manData.xzstate("9860");
                if (addState != null) {
                    manData.getQuality().addqhl(addState, true);
                }
            }
            skill2 = manData.getSkillType(TypeUtil.TY_HL_HKDL);
            if (skill2 != null && !Battlefield.isV(skill2.getSkillhurt())) {
                skill2 = null;
            }
        }
        else if (skill.getSkillid() == 1010) {
            AddState addState = manData.xzstate(TypeUtil.TY_R_SMMF);
            if (PK_MixDeal.isPK(battlefield.BattleType) && addState != null) {
                SummonType.xianzhi(manData, skill, true);
                manData.addAddState("忽视封印", addState.getStateEffect(), 0.0, 5);
                subState = new AddState("抗封印", -addState.getStateEffect2(), 0.0, 10);
                manData.getAddStates().remove(addState);
            }
            skill2 = manData.getSkillType(TypeUtil.TY_FY_HMZH);
            if (skill2 != null && !Battlefield.isV(skill2.getSkillhurt())) {
                skill2 = null;
            }
        }
        else if (skill.getSkillid() == 1015) {
            AddState addState = manData.xzstate(TypeUtil.TY_R_HRRM);
            if (PK_MixDeal.isPK(battlefield.BattleType) && addState != null) {
                SummonType.xianzhi(manData, skill, true);
                manData.addAddState("忽视昏睡", addState.getStateEffect(), 0.0, 5);
                subState = new AddState("抗昏睡", -addState.getStateEffect2(), 0.0, 10);
                manData.getAddStates().remove(addState);
            }
            skill2 = manData.getSkillType(TypeUtil.TY_HS_HCTY);
            if (skill2 != null && !Battlefield.isV(skill2.getSkillhurt())) {
                skill2 = null;
            }
        }
        else if (skill.getSkillid() == 1009) {
            skill3 = manData.getSkillType(TypeUtil.TY_FY_XXYJ);
            skill4 = manData.getSkillType(TypeUtil.TY_FY_CJCH);
        }
        else if (skill.getSkillid() == 1004) {
            skill3 = manData.getSkillType(TypeUtil.TY_HL_CYMY);
        }
        else if (skill.getSkilltype().equals("中毒")) {
            if (PK_MixDeal.isPK(battlefield.BattleType)) {
                skill3 = manData.getSkillType(TypeUtil.TY_ZD_CLQL);
            }
        }
        else if (skill.getSkillid() == 1075) {
            AddState addState = manData.xzstate("10055");
            if (PK_MixDeal.isPK(battlefield.BattleType)) {
                SummonType.xianzhi(manData, skill, true);
                manData.addAddState("忽视遗忘", addState.getStateEffect(), 0.0, 5);
                subState = new AddState("抗遗忘", -addState.getStateEffect2(), 0.0, 10);
                manData.getAddStates().remove(addState);
            }
        }
        datas.add(manData);
        FightingState Originator = events.getOriginator();
        int state = manData.getStates();
        if (manData.daijia(skill, Originator, battlefield)) {
            return;
        }
        if (state == 0 && state != manData.getStates()) {
            state = 1;
        }
        else {
            state = 0;
        }
        Originator.setStartState("法术攻击");
        Originator.setSkillsy(skill.getSkillname());
        Originator.setText("强化封印#2");
        for (int i = 0; i < datas.size(); ++i) {
            ManData data = (ManData)datas.get(i);
            FightingState Accepter = MixDeal.DSMY(manData, data, skill.getSkillid(), battlefield);
            if (Accepter != null) {
                Accepterlist.add(Accepter);
            }
            else {
                data.addBear(type);
                if (subState != null) {
                    data.getAddStates().add(subState);
                }
                Accepter = new FightingState();
                ChangeFighting change = this.TypeProbabilityNoHurt(manData, data, skill, skills, battlefield);
                if (!change.getChangetype().equals("")) {
                    ++size;
                    if (data.getType() == 0) {
                        if (skill3 != null && skill3.getSkilltype().equals(TypeUtil.TY_FY_XXYJ) && Battlefield.isV(skill3.getSkillhurt())) {
                            AddState addState2 = data.getGainState();
                            if (addState2 != null) {
                                addState2.setSurplus(addState2.getSurplus() - 1);
                            }
                        }
                        else if (skill3 != null && skill3.getSkilltype().equals(TypeUtil.TY_HL_CYMY) && Battlefield.isV(skill3.getSkillhurt())) {
                            ManData pet = battlefield.getSeek(data, 1);
                            if (pet != null && pet.getStates() == 0 && pet.xzstate("封印") == null) {
                                FightingState fightingState = MixDeal.DSMY(manData, pet, skill.getSkillid(), battlefield);
                                if (fightingState != null) {
                                    Accepterlist.add(fightingState);
                                }
                                else {
                                    pet.addBear(type);
                                    fightingState = new FightingState();
                                    ChangeFighting changeFighting = this.TypeProbabilityNoHurt(manData, pet, skill, skills, battlefield);
                                    FightingPackage.ChangeProcess(changeFighting, null, pet, fightingState, type, Accepterlist, battlefield);
                                }
                                fightingState.setSkillskin(skill.getSkillid() + "");
                            }
                        }
                        else if (skill3 != null && skill3.getSkilltype().equals(TypeUtil.TY_ZD_CLQL) && Battlefield.isV(20.0)) {
                            ManData pet = battlefield.getSeek(data, 1);
                            if (pet != null && pet.getStates() == 0) {
                                FightingState fightingState = new FightingState();
                                ChangeFighting changeFighting = new ChangeFighting();
                                changeFighting.setChangehp((int)((double)change.getChangehp() * skill3.getSkillhurt() / 100.0));
                                FightingPackage.ChangeProcess(changeFighting, null, pet, fightingState, "施法毒", Accepterlist, battlefield);
                            }
                        }
                        if (skill4 != null && Battlefield.isV(skill3.getSkillhurt())) {
                            ManData pet = battlefield.getSeek(data, 1);
                            if (pet != null) {
                                AddState addState3 = pet.getGainState();
                                if (addState3 != null) {
                                    FightingState fightingState2 = new FightingState();
                                    fightingState2.setCamp(pet.getCamp());
                                    fightingState2.setMan(pet.getMan());
                                    fightingState2.setStartState(TypeUtil.JN);
                                    fightingState2.setEndState_2(addState3.getStatename());
                                    pet.getAddStates().remove(addState3);
                                    Accepterlist.add(fightingState2);
                                }
                            }
                        }
                    }
                    else if (data.getType() == 1 && Battlefield.isV(20.0)) {
                        ManData petParents = battlefield.getPetParents(data);
                        FightingState fightingState = new FightingState();
                        if (petParents.getStates() == 0) {
                            FightingPackage.ChangeProcess(change, null, petParents, fightingState, type, Accepterlist, battlefield);
                        }
                    }
                }
                FightingPackage.ChangeProcess(change, manData, data, Accepter, type.equals("中毒") ? "施法毒" : type, Accepterlist, battlefield);
            }
            Accepter.setSkillskin(skill.getSkillid() + "");
        }
        if (events.getOriginator() != null) {
            Accepterlist.add(Originator);
        }
        events.setOriginator(null);
        if (Accepterlist.size() != 0) {
            events.setAccepterlist(Accepterlist);
            battlefield.NewEvents.add(events);
        }
        if (skill2 != null) {
            int mpc = 0;
            if (size >= 3 && skill2.getSkillid() == 9142) {
                mpc = (int)((double)skill.getSkillblue() * skill2.getSkillhurt() * 2.5 / 100.0);
            }
            else if (size >= 4 && skill2.getSkillid() == 9102) {
                mpc = (int)((double)skill.getSkillblue() * skill2.getSkillhurt() * 2.5 / 100.0);
            }
            else if (size >= 5 && skill2.getSkillid() == 9123) {
                mpc = (int)((double)skill.getSkillblue() * skill2.getSkillhurt() * 2.5 / 100.0);
            }
            if (mpc != 0) {
                FightingEvents events2 = new FightingEvents();
                List<FightingState> fightingStates = new ArrayList<>();
                FightingState fightingState3 = new FightingState();
                ChangeFighting changeFighting2 = new ChangeFighting();
                changeFighting2.setChangemp(mpc);
                manData.ChangeData(changeFighting2, fightingState3);
                fightingState3.setStartState("药");
                fightingStates.add(fightingState3);
                events2.setAccepterlist(fightingStates);
                battlefield.NewEvents.add(events2);
            }
        }
        if (skill.getSkillid() >= 1001 && skill.getSkillid() <= 1005) {
            FightingSkill skill5 = manData.getSkillType("9859");
            if (skill5 != null && spl > manData.getSp()) {
                battlefield.cfzjsp = (int)skill5.getSkillhurt();
            }
        }
        if (skill.getSkillid() == 1020) {
            FightingSkill TY_R_CXBC = manData.getSkillType(TypeUtil.TY_R_CXBC);
            if (TY_R_CXBC != null && size <= 5) {
                double bonusPercentage = (double)(5 - size) * TY_R_CXBC.getSkillhurt();
                manData.getQuality().setQzds(manData.getQuality().getQzds() * (1.0 + bonusPercentage / 100.0));
            }
        }
        double Spill = 0.0;
        FightingSkill skill6 = manData.getSkillType(TypeUtil.TY_ZD_FYSQ);
        FightingSkill skill7 = manData.getSkillType(TypeUtil.TY_R_FHLT);
        if (skill6 != null && skill6.getSkillhurt() > (double)size) {
            if (skill7 != null) {
                Spill = skill7.getSkillhurt() / 100.0;
            }
            int v = (int)(skill6.getSkillhurt() - (double)size);
            double max = (double)v * Spill;
            manData.getQuality().setRoleqzd(manData.getQuality().getRoleqzd() + max);
        }
        if (state == 1) {
            MixDeal.DeathSkill(manData, Originator, battlefield);
        }
    }
    
    public static List<FightingSkill> getL_PL_Skills(ManData myData, FightingSkill skill, int type) {
        List<FightingSkill> skills = null;
        if (PK_MixDeal.isPK(type)) {
            skills = addSkill(myData.getSkillType(TypeUtil.TY_L_PL_YGCS), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_L_PL_CYPW), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_L_10118), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_L_10122), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_L_10148), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_L_10153), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_L_10158), skills);
        }
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_PL_SHZX), skills);
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_PL_BLJD), skills);
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_PL_QSYZ), skills);
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_10119), skills);
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_10147), skills);
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_10152), skills);
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_10156), skills);
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_10151), skills);
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_10161), skills);
        return skills;
    }
    
    public static List<FightingSkill> getL_GL_Skills(ManData myData, FightingSkill skill, int type) {
        List<FightingSkill> skills = null;
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_GL_CYSH), skills);
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_GL_XLYS), skills);
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_GL_SYYL), skills);
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_GL_MYPT), skills);
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_GL_CYJY), skills);
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_GL_SNLZ), skills);
        return skills;
    }
    
    public static List<FightingSkill> getL_CB_Skills(ManData myData, FightingSkill skill, int type) {
        List<FightingSkill> skills = null;
        if (PK_MixDeal.isPK(type)) {
            skills = addSkill(myData.getSkillType(TypeUtil.TY_L_CB_WSHJ), skills);
            if (skill.getSkillid() == 1090) {
                skills = addSkill(myData.getSkillType(TypeUtil.TY_L_CB_JTDL), skills);
            }
        }
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_CB_LXFZ), skills);
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_CB_BSQT), skills);
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_CB_YCBH), skills);
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_10133), skills);
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_10134), skills);
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_10135), skills);
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_10139), skills);
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_10147), skills);
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_10146), skills);
        return skills;
    }
    
    public static List<FightingSkill> getL_FY_Skills(ManData myData, FightingSkill skill, int type) {
        List<FightingSkill> skills = null;
        if (PK_MixDeal.isPK(type)) {
            skills = addSkill(myData.getSkillType(TypeUtil.TY_L_FY_LGCQ), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_L_FY_XDSL), skills);
        }
        if (skill.getSkillid() == 1099) {
            skills = addSkill(myData.getSkillType(TypeUtil.TY_L_FY_FDYB), skills);
        }
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_FY_XFCZ), skills);
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_FY_XFCZ), skills);
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_FY_YYZF), skills);
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_10125), skills);
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_10126), skills);
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_10130), skills);
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_10132), skills);
        skills = addSkill(myData.getSkillType(TypeUtil.TY_L_10166), skills);
        return skills;
    }
    
    public static List<FightingSkill> getSkills(ManData myData, FightingSkill skill, int type) {
        List<FightingSkill> skills = null;
        if (skill.getSkilltype().equals("封印")) {
            if (PK_MixDeal.isPK(type)) {
                skills = addSkill(myData.getSkillType(TypeUtil.TY_FY_CHLQ), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_FY_FDSJ), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_FY_DSCB), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_R_LQLS), skills);
            }
            else {
                skills = addSkill(myData.getSkillType(TypeUtil.TY_FY_BFCY), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_FY_HQQY), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_FY_JYST), skills);
            }
        }
        else if (skill.getSkilltype().equals("昏睡") || skill.getSkillid() == 9126) {
            if (PK_MixDeal.isPK(type)) {
                skills = addSkill(myData.getSkillType(TypeUtil.TY_HS_CJKZ), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_HS_YZCW), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_HS_CMBX), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_R_YMHX), skills);
            }
            else {
                skills = addSkill(myData.getSkillType(TypeUtil.TY_HS_MYWS), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_HS_CYWY), skills);
            }
            skills = addSkill(myData.getSkillType(TypeUtil.TY_HS_HLDJ), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_HS_LBCX), skills);
        }
        else if (skill.getSkilltype().equals("混乱")) {
            if (PK_MixDeal.isPK(type)) {
                skills = addSkill(myData.getSkillType(TypeUtil.TY_HL_XLRM), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_HL_XLRM), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_HL_DJDX), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_HL_XSCL), skills);
                skills = addSkill(myData.getSkillType("9856"), skills);
            }
            else {
                skills = addSkill(myData.getSkillType(TypeUtil.TY_HL_HSSL), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_HL_QJSY), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_HL_LXXH), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_HL_YSCL), skills);
            }
        }
        else if (skill.getSkilltype().equals("中毒")) {
            if (PK_MixDeal.isPK(type)) {
                skills = addSkill(myData.getSkillType(TypeUtil.TY_ZD_YASH), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_ZD_LJXF), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_ZD_RLBB), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_ZD_MLYY), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_ZD_FYSQ), skills);
            }
            else {
                skills = addSkill(myData.getSkillType(TypeUtil.TY_ZD_LRHS), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_ZD_BYHY), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_ZD_FXZH), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TZ_SJDG), skills);
            }
        }
        else if (skill.getSkilltype().equals("雷")) {
            if (PK_MixDeal.isPK(type)) {
                skills = addSkill(myData.getSkillType(TypeUtil.TY_L_SNZM), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_L_ALYD), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_X_9947), skills);
            }
            skills = addSkill(myData.getSkillType(TypeUtil.TY_L_FGLY), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_L_LHHL), skills);
        }
        else if (skill.getSkilltype().equals("水")) {
            if (PK_MixDeal.isPK(type)) {
                skills = addSkill(myData.getSkillType(TypeUtil.TY_L_SNZM), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_S_MYBY), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_S_CLZY), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_X_9965), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_X_9966), skills);
            }
            skills = addSkill(myData.getSkillType(TypeUtil.TY_S_LXDD), skills);
        }
        else if (skill.getSkilltype().equals("风")) {
            if (PK_MixDeal.isPK(type)) {
                skills = addSkill(myData.getSkillType(TypeUtil.TY_L_SNZM), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_F_CZDF), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_F_HKJF), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_F_YQDS), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_X_9956), skills);
            }
            skills = addSkill(myData.getSkillType(TypeUtil.TY_F_SXPC), skills);
        }
        else if (skill.getSkilltype().equals("火")) {
            if (PK_MixDeal.isPK(type)) {
                skills = addSkill(myData.getSkillType(TypeUtil.TY_L_SNZM), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_H_DFCY), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_H_HLDX), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_H_HSJY), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_X_10014), skills);
                if (skill.getSkillid() == 1060) {
                    skills = addSkill(myData.getSkillType(TypeUtil.TY_H_XHXC), skills);
                    skills = addSkill(myData.getSkillType(TypeUtil.TY_X_10015), skills);
                    skills = addSkill(myData.getSkillType(TypeUtil.TY_X_10016), skills);
                }
            }
            skills = addSkill(myData.getSkillType(TypeUtil.TY_H_JYYX), skills);
        }
        else if (skill.getSkilltype().equals("鬼火")) {
            if (PK_MixDeal.isPK(type)) {
                skills = addSkill(myData.getSkillType(TypeUtil.TY_GH_YHSX), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_GH_QYBY), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_GH_GYCM), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_GH_XGNC), skills);
                skills = addSkill(myData.getSkillType("10065"), skills);
            }
            else {
                skills = addSkill(myData.getSkillType(TypeUtil.TY_GH_GSSC), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_GH_YXHY), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_GH_BQCH), skills);
                skills = addSkill(myData.getSkillType("10060"), skills);
            }
            skills = addSkill(myData.getSkillType(TypeUtil.TY_GH_YJFR), skills);
        }
        else if (skill.getSkilltype().equals("遗忘")) {
            if (PK_MixDeal.isPK(type)) {
                skills = addSkill(myData.getSkillType(TypeUtil.TY_YW_ZZJM), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_YW_HQMY), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_YW_HLYM), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_YW_BSSX), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_YW_MDHL), skills);
                skills = addSkill(myData.getSkillType("10052"), skills);
                skills = addSkill(myData.getSkillType("10053"), skills);
                skills = addSkill(myData.getSkillType("10054"), skills);
            }
            skills = addSkill(myData.getSkillType(TypeUtil.TZ_WHSY), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_YW_TRWJ), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_YW_QQWK), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_YW_YZJH), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_YW_CLJW), skills);
        }
        else if (skill.getSkilltype().equals(TypeUtil.LL)) {
            if (PK_MixDeal.isPK(type)) {
                skills = addSkill(myData.getSkillType(TypeUtil.TY_LL_TT), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_LL_QCDN), skills);
            }
            else {
                skills = addSkill(myData.getSkillType(TypeUtil.TY_LL_XCPY), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_LL_CY), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_LL_DLTS), skills);
            }
            skills = addSkill(myData.getSkillType(TypeUtil.TY_LL_ZM), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_LL_YYDJ), skills);
        }
        else if (skill.getSkilltype().equals(TypeUtil.JS)) {
            if (PK_MixDeal.isPK(type)) {
                skills = addSkill(myData.getSkillType(TypeUtil.TY_JS_JFJC), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_JS_JCYC), skills);
            }
            else {
                skills = addSkill(myData.getSkillType(TypeUtil.TY_R_BTXJ), skills);
            }
            skills = addSkill(myData.getSkillType(TypeUtil.TY_JS_FYZS), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_JS_PLJH), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_JS_YYCF), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_JS_DGCL), skills);
        }
        else if (skill.getSkilltype().equals(TypeUtil.KX)) {
            if (PK_MixDeal.isPK(type)) {
                skills = addSkill(myData.getSkillType(TypeUtil.TY_KX_CGKX), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_KX_BGBD), skills);
            }
            else {
                skills = addSkill(myData.getSkillType(TypeUtil.TY_KX_XTLH), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_KX_TJQF), skills);
            }
            skills = addSkill(myData.getSkillType(TypeUtil.TY_KX_FHST), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_KX_BFWY), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_KX_WYCM), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_KX_XWYZ), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_KX_SLYZ), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_KX_PY), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_KX_NS), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_KX_QSYX), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_KX_JCSG), skills);
        }
        else if (skill.getSkilltype().equals(TypeUtil.MH)) {
            if (PK_MixDeal.isPK(type)) {
                skills = addSkill(myData.getSkillType(TypeUtil.TY_MH_ZZGX), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_MH_TYMZ), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_MH_HCDN), skills);
                skills = addSkill(myData.getSkillType(TypeUtil.TY_MH_STYY), skills);
            }
            skills = addSkill(myData.getSkillType(TypeUtil.TY_MH_QYMM), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_MH_HTJC), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_MH_LXXY), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_MH_NYYX), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_MH_ZMBW), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_MH_NRYZ), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_MH_RYTH), skills);
        }
        else if (skill.getSkilltype().equals(TypeUtil.TY_L_XSNJ) && !PK_MixDeal.isPK(type)) {
            skills = addSkill(myData.getSkillType(TypeUtil.TY_L_DZXY), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_L_XYQK), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_S_AZLS), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_S_XYHT), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_F_FDZL), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_F_WHSF), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_H_JSYY), skills);
            skills = addSkill(myData.getSkillType(TypeUtil.TY_H_YHRJ), skills);
        }
        return skills;
    }
    
    public static List<FightingSkill> addSkill(FightingSkill skill, List<FightingSkill> skills) {
        if (skill == null) {
            return skills;
        }
        if (skills == null) {
            skills = new ArrayList<>();
        }
        skills.add(skill);
        return skills;
    }
    
    public ChangeFighting TypeProbabilityNoHurt(ManData myData, ManData data, FightingSkill skill, List<FightingSkill> skills, Battlefield battlefield) {
        String type = skill.getSkilltype();
        ChangeFighting changeFighting = new ChangeFighting();
        changeFighting.setChangetype(type);
        changeFighting.setChangesum(skill.getSkillcontinued());
        changeFighting.setChangevlaue(skill.getSkillhurt());
        if (skills != null) {
            for (int i = 0; i < skills.size(); ++i) {
                FightingSkill fightingSkill = (FightingSkill)skills.get(i);
                int id = fightingSkill.getSkillid();
                if (id == 6034) {
                    changeFighting.setChangevlaue2(changeFighting.getChangevlaue2() + fightingSkill.getSkillhurt());
                }
                else if (id == 9341) {
                    if (skill.getSkillid() == 1074) {
                        changeFighting.setChangevlaue2(changeFighting.getChangevlaue2() + fightingSkill.getSkillhurt());
                    }
                }
                else if (id == 9101 || id == 9141) {
                    if (Battlefield.isV(fightingSkill.getSkillhurt())) {
                        changeFighting.setChangesum(changeFighting.getChangesum() + 1);
                    }
                }
                else if (id == 9121) {
                    if (data.xzstate("昏睡") != null) {
                        changeFighting.setChangesum(changeFighting.getChangesum() + 1);
                    }
                }
                else if (id == 9149) {
                    if (data.xzstate("混乱") != null) {
                        changeFighting.setChangehp(-(int)((double)data.getHp_z() * fightingSkill.getSkillhurt() / 100.0));
                    }
                }
                else if (id == 9343) {
                    if (data.getType() == 1 && Battlefield.isV(fightingSkill.getSkillhurt())) {
                        changeFighting.setSkill(fightingSkill);
                    }
                }
                else if (id == 9351) {
                    if (data.getType() == 0 && Battlefield.isV(20.0)) {
                        changeFighting.setSkill(fightingSkill);
                    }
                }
                else if (id == 9866) {
                    if (data.xzstate("封印") == null) {
                        changeFighting.setSkill(fightingSkill);
                    }
                }
                else if (id == 9867) {
                    if (data.xzstate("封印") != null) {
                        changeFighting.setSkill(fightingSkill);
                    }
                }
                else if (id == 9162) {
                    changeFighting.setChangesum((int)((double)changeFighting.getChangesum() - fightingSkill.getSkillhurt()));
                }
                else if (id == 9112 || id == 9125 || id == 9127 || id == 9128 || id == 9131 || id == 9150) {
                    if (Battlefield.isV(fightingSkill.getSkillhurt())) {
                        changeFighting.setSkill(fightingSkill);
                    }
                }
                else if (id == 10052) {
                    if (data.xzstate("遗忘") != null) {
                        changeFighting.setChangemp((int)((double)(-data.getMp()) * fightingSkill.getSkillhurt() / 100.0));
                    }
                }
                else if (id == 10053) {
                    if (data.xzstate("遗忘") != null) {
                        data.setHuoyue(data.getHuoyue() - data.getHuoyue() * fightingSkill.getSkillhurt() / 100.0);
                    }
                }
                else if (id == 10054) {
                    if (data.xzstate("遗忘") != null && Battlefield.isV(fightingSkill.getSkillhurt())) {
                        data.addAddState("10054", 0.0, 0.0, 999);
                    }
                }
                else if (id != 6037 && (id < 9161 || id > 9171)) {
                    changeFighting.setSkill(fightingSkill);
                }
            }
        }
        this.TypeProbabilityNoHurt(myData, skill, "混乱", "9853", 3, changeFighting, battlefield, Boolean.valueOf(true));
        this.TypeProbabilityNoHurt(myData, skill, "混乱", TypeUtil.TY_R_YBXN, 3, changeFighting, battlefield, Boolean.valueOf(true));
        this.TypeProbabilityNoHurt(myData, skill, "混乱", TypeUtil.TY_R_MYXL, 3, changeFighting, battlefield, Boolean.valueOf(true));
        if (type.equals("中毒")) {
            double upxs = 1.0;
            double hurtxs = 1.0;
            double qds = 0.0;
            if (skills != null) {
                for (int j = 0; j < skills.size(); ++j) {
                    FightingSkill fightingSkill2 = (FightingSkill)skills.get(j);
                    int id2 = fightingSkill2.getSkillid();
                    if (id2 == 6037) {
                        upxs += fightingSkill2.getSkillhurt() / 100.0;
                        changeFighting.setChangesum(1);
                    }
                    else if (id2 == 9161) {
                        qds += fightingSkill2.getSkillhurt();
                    }
                    else if (id2 == 9162 || id2 == 9163) {
                        qds += fightingSkill2.getSkillhurt();
                    }
                    else if (id2 == 9164) {
                        if (data.getHp() < data.getHp_z() / 2) {
                            data.addAddState(fightingSkill2.getSkilltype(), fightingSkill2.getSkillhurt(), 0.0, battlefield.CurrentRound + 1);
                        }
                    }
                    else if (id2 == 9165) {
                        if (data.getType() == 1) {
                            qds += fightingSkill2.getSkillhurt();
                        }
                    }
                    else if (id2 == 9166) {
                        qds += fightingSkill2.getSkillhurt();
                        hurtxs -= fightingSkill2.getSkillhurt() / 100.0;
                    }
                    else if (id2 != 9167 || data.xzstate(TypeUtil.TY_ZD_RLBB) != null) {}
                }
            }
            AddState addState = myData.xzstate("哀鸿遍野");
            if (addState != null) {
                data.addAddState(TypeUtil.TY_ZD_AHBY, addState.getStateEffect(), (double)myData.getId(), 3);
            }
            else {
                addState = data.xzstate(TypeUtil.TY_ZD_AHBY);
                if (addState != null && (double)myData.getId() == addState.getStateEffect2()) {
                    addState.setStateEffect(addState.getStateEffect() * 3.0);
                }
            }
            AddState addState2 = myData.xzstate(TypeUtil.TY_ZD_PTGD);
            if (addState2 != null) {
                qds += addState2.getStateEffect();
            }
            int up = Calculation.getCalculation().getzdup(myData, skill, qds, data);
            int hurt = Calculation.getCalculation().getzdsh(myData, data, skill, qds);
            up = (int)((double)up * upxs);
            hurt = (int)((double)hurt * hurtxs);
            if (hurt > up) {
                hurt = up;
            }
            hurt = (int)((double)hurt - data.getQuality().getKzds());
            if (hurt <= 0) {
                hurt = 1;
            }

            changeFighting.setChangehp(-hurt);
            FightingSkill fightingSkill3 = myData.getSkillType(TypeUtil.TJ_MTL);
            if (fightingSkill3 != null) {
//                changeFighting.setChangemp((int)((double)changeFighting.getChangehp() * 0.15));
                changeFighting.setChangemp(-(int)((double)data.getMp_z() * 0.40));

            }
            changeFighting.setChangevlaue((double)(hurt / 2));
            FightingSkill fightingSkillS = myData.getSkillType(TypeUtil.TJ_FSSS);
            if (fightingSkillS != null) {
//                changeFighting.setChangemp((int)((double)changeFighting.getChangehp() * 0.2));
                changeFighting.setChangemp(-(int)((double)data.getMp_z() * 0.45));

            }
            changeFighting.setChangevlaue((double)(hurt / 2));
        }
        return changeFighting;
    }
    
    public static List<ManData> getEnemyUnit(ManData myData, int ren, Battlefield battlefield, String skillType) {
        List<ManData> datas = MixDeal.get(false, null, 1, myData.getCamp(), 1, ren, 1, 0, 5, -1, battlefield, 1);
        return (List)datas.stream().filter(e/* come.tool.FightingData.ManData, */ -> e.getSkillType(skillType) != null).collect(Collectors.toList());
    }
    
    public void TypeProbabilityNoHurt(ManData myData, FightingSkill skill, String type, String skillType, int ren, ChangeFighting changeFighting, Battlefield battlefield, Boolean isPk) {
        if (isPk != null && (boolean)isPk != PK_MixDeal.isPK(battlefield.BattleType)) {
            return;
        }
        if (skill.getSkilltype().equals(type)) {
            List<ManData> enemyUnits = new ArrayList<>();
            int n = -1;
            switch (type.hashCode()) {
                case 892762: {
                    if (type.equals("混乱")) {
                        n = 0;
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
            switch (n) {
                case 0: {
                    enemyUnits = getEnemyUnit(myData, ren, battlefield, skillType);
                    break;
                }
            }
            FightingEvents events = new FightingEvents();
            List<FightingState> zls = new ArrayList<>();
            for (int i = 0; i < enemyUnits.size(); ++i) {
                ManData data = (ManData)enemyUnits.get(i);
                FightingSkill enemySkill = data.getSkillType(skillType);
                if ((skillType.equals("9853") || skillType.equals(TypeUtil.TY_R_YBXN) || skillType.equals(TypeUtil.TY_R_MYXL)) && Battlefield.isV(enemySkill.getSkillhurt())) {
                    FightingState gjz = new FightingState();
                    gjz.setCamp(data.getCamp());
                    gjz.setMan(data.getMan());
                    data.addnq1(2, gjz);
                    zls.add(gjz);
                }
            }
            if (zls.size() > 0) {
                events.setAccepterlist(zls);
                battlefield.NewEvents.add(events);
            }
        }
    }
}
