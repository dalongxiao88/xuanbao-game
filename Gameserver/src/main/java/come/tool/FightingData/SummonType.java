package come.tool.FightingData;

import java.util.List;

public class SummonType
{
    public static void xianzhi(ManData data, FightingSkill skill) {
        xianzhi(data, skill, false);
    }
    
    public static void xianzhi(ManData data, FightingSkill skill, boolean is) {
        int id = skill.getSkillid();
        int hh = 0;
        int sum = 0;
        if (id == 1606 || id == 1608 || id == 1611 || id == 4010 || id == 1612 || id == 1828 || id == 7019 || id == 7013 || id == 7015 || id == 1313 || id == 1314 || id == 1315 || id == 1250 || id == 1251 || id == 1261 || id == 23009) {
            data.getSkills().remove(skill);
        }
        else {
            AddState addState = null;
            if ((id >= 1600 && id <= 1605) || id == 1607|| id == 1279 || id == 1200 || id == 1215 || id == 7012 || id == 7015 || id == 1876 || id == 1332 || id == 1889) {
                addState = new AddState();
                addState.setStatename("冷却");
                addState.setStateEffect((double)id);
                addState.setSurplus(5);//技能冷却回合数
            }
            else if (id == 1606 || id == 7008 || id == 7009 || id == 7026 || id == 1333 || id == 1334 || id == 9610 || id == 1868 || id == 1869 || id == 1877 || id == 7027) {
                addState = new AddState();
                addState.setStatename("冷却");
                addState.setStateEffect((double)id);
                addState.setSurplus(10);
            }
            else if (id == 7002 || id == 7025 || id == 7027) {
                addState = new AddState();
                addState.setStatename("冷却");
                addState.setStateEffect((double)id);
                addState.setSurplus(3);
            }
            else if (id == 1230) {
                addState = new AddState();
                addState.setStatename("冷却");
                addState.setStateEffect((double)id);
                addState.setSurplus(2);
            }
            else if (id == 7016 || id == 3035) {
                addState = new AddState();
                addState.setStatename("冷却");
                addState.setStateEffect((double)id);
                addState.setSurplus(6);
            }
            else if (id == 6029 || id == 9389 || id == 23004) {
                addState = new AddState();
                addState.setStatename("冷却");
                addState.setStateEffect((double)id);
                addState.setSurplus(15);
            }
            else if (id == 22000 || id == 22002 || id == 22004 || id == 22006 || id == 22008 || id == 22010 || id == 22012 || id == 22014 || id == 22016 || id == 22018 || id == 22020 || id == 22022 || id == 22024 || id == 22026 || id == 22028 || id == 22030 || id == 22032 || id == 22034 || id == 3028 || id == 3029 || id == 3032) {
                addState = new AddState();
                addState.setStatename("冷却");
                addState.setStateEffect((double)id);
                addState.setSurplus(10);
            }
            else if (id == 1264) {
                addState = new AddState();
                addState.setStatename("冷却");
                addState.setStateEffect((double)id);
                addState.setSurplus(2);
            }
            else if (is && id == 1005) {
                addState = new AddState();
                addState.setStatename("冷却");
                addState.setStateEffect((double)id);
                FightingSkill skill2 = data.getSkillType("9861");
                if (skill2 != null) {
                    sum = (int)skill2.getSkillhurt();
                }
                addState.setSurplus(10 - sum);
            } else if (id == 30000 || id == 30004 || id == 30005 || id == 30008 || id == 30009 || id == 30017 || id == 30025 || id == 30027 || id == 30026 || id == 30024 || id == 30019 || id == 30022 || id == 30021 || id == 30002 || id == 30020 || id == 30013 || id == 30016 || id == 30033 || id == 30036 || id == 30037 || id == 30034 || id == 30032 || id == 30003) {
                addState = new AddState();
                addState.setStatename("冷却");
                addState.setStateEffect(id);
                addState.setSurplus(10);
            }
            else if (is && id == 1010) {
                addState = new AddState();
                addState.setStatename("冷却");
                addState.setStateEffect((double)id);
                FightingSkill skill2 = data.getSkillType(TypeUtil.TY_R_JLCB);
                if (skill2 != null) {
                    sum = (int)skill2.getSkillhurt();
                }
                addState.setSurplus(10 - sum);
            }
            else if (is && id == 1015) {
                addState = new AddState();
                addState.setStatename("冷却");
                addState.setStateEffect((double)id);
                FightingSkill skill2 = data.getSkillType(TypeUtil.TY_R_CMXY);
                if (skill2 != null) {
                    sum = (int)skill2.getSkillhurt();
                }
                addState.setSurplus(10 - sum);
            }
            else if (is && id == 1075) {
                addState = new AddState();
                addState.setStatename("冷却");
                addState.setStateEffect((double)id);
                FightingSkill skill2 = data.getSkillType("10058");
                if (skill2 != null) {
                    sum = (int)skill2.getSkillhurt();
                }
                addState.setSurplus(10 - sum);
            }
            else if (id == 1270) {
                addState = new AddState();
                addState.setStatename("冷却");
                addState.setStateEffect((double)id);
                addState.setSurplus(4);
            }
            else if (id == 1271) {
                addState = new AddState();
                addState.setStatename("冷却");
                addState.setStateEffect((double)id);
                addState.setSurplus(4);
            }
            else if (id == 1272) {
                addState = new AddState();
                addState.setStatename("冷却");
                addState.setStateEffect((double)id);
                addState.setSurplus(4);
            }
            else if (id == 1273) {
                addState = new AddState();
                addState.setStatename("冷却");
                addState.setStateEffect((double)id);
                addState.setSurplus(4);
            }
            else if (id == 9111 || id == 9126 || id == 9130 || id == 9151 || id == 9169 || id == 9170 || id == 9171 || id == 9189 || id == 9190 || id == 9207 || id == 9208 || id == 9209 || id == 9231 || id == 9232 || id == 9250 || id == 9251 || id == 9252 || id == 9262 || id == 9270 || id == 9286 || id == 9287 || id == 9307 || id == 9326 || id == 9350 || id == 9352 || id == 9372 || id == 9412) {
                addState = new AddState();
                addState.setStatename("冷却");
                addState.setStateEffect((double)id);
                addState.setSurplus(10);
            }
            else if (id == 9857 || id == 9870 || id == 9881 || id == 9132 || id == 9957 || id == 10015 || id == 10016 || id == 10055 || id == 10080 || id == 10121 || id == 10129 || id == 9711 || id == 10138 || id == 9810 || id == 9812) {
                hh = 10;
                if (id == 9857) {
                    FightingSkill skill3 = data.getSkillType("9861");
                    if (skill3 != null) {
                        hh = (int)((double)hh - skill3.getSkillhurt());
                    }
                }
                else if (id == 9870) {
                    FightingSkill skill3 = data.getSkillType(TypeUtil.TY_R_JLCB);
                    if (skill3 != null) {
                        hh = (int)((double)hh - skill3.getSkillhurt());
                    }
                }
                else if (id == 9881) {
                    FightingSkill skill3 = data.getSkillType(TypeUtil.TY_R_CMXY);
                    if (skill3 != null) {
                        hh = (int)((double)hh - skill3.getSkillhurt());
                    }
                }
                else if (id == 10055) {
                    FightingSkill skill3 = data.getSkillType("10058");
                    if (skill3 != null) {
                        hh = (int)((double)hh - skill3.getSkillhurt());
                    }
                }
                addState = new AddState();
                addState.setStatename("冷却");
                addState.setStateEffect((double)id);
                addState.setSurplus(hh);
            }
            else if (id == 450050) {
                addState = new AddState();
                addState.setStatename("冷却");
                addState.setStateEffect((double)id);
                addState.setSurplus(1);
            }
            if (addState != null) {
                data.getAddStates().add(addState);
                if (id == 9857) {
                    AddState addState2 = new AddState();
                    addState2.setStatename("冷却");
                    addState2.setStateEffect(1005.0);
                    addState2.setSurplus(hh);
                    data.getAddStates().add(addState2);
                }
                else if (id == 9870) {
                    AddState addState2 = new AddState();
                    addState2.setStatename("冷却");
                    addState2.setStateEffect(1010.0);
                    addState2.setSurplus(hh);
                    data.getAddStates().add(addState2);
                }
                else if (id == 9881) {
                    AddState addState2 = new AddState();
                    addState2.setStatename("冷却");
                    addState2.setStateEffect(1015.0);
                    addState2.setSurplus(hh);
                    data.getAddStates().add(addState2);
                }
                else if (id == 10055) {
                    AddState addState2 = new AddState();
                    addState2.setStatename("冷却");
                    addState2.setStateEffect(1075.0);
                    addState2.setSurplus(hh);
                    data.getAddStates().add(addState2);
                }
            }
        }
    }
    
    public static void Fiveelements(List<FightingState> Accepterlist, FightingState fightingState, ManData manData, ManData nomyData, FightingSkill fightingSkill, String skilltype, Battlefield battlefield) {
        if (fightingState == null) {
            fightingState = new FightingState();
        }
        int skillid = fightingSkill.getSkillid();
        ChangeFighting nomyChange = new ChangeFighting();
        nomyChange.setChangevlaue(50.0);
        nomyChange.setChangesum(3);
        switch (skillid) {
            case 1600: {
                nomyChange.setChangetype("金");
                break;
            }
            case 1601: {
                nomyChange.setChangetype("木");
                break;
            }
            case 1602: {
                nomyChange.setChangetype("土");
                break;
            }
            case 1603: {
                nomyChange.setChangetype("水");
                break;
            }
            case 1604: {
                nomyChange.setChangetype("火");
                break;
            }
            case 1876: {
                nomyChange.setChangetype(TypeUtil.BB_WYJK);
                break;
            }
            case 1889: {
                nomyChange.setChangetype(TypeUtil.BB_TNFY);
                break;
            }
            case 1605: {
                nomyChange.setChangetype("消除五行");
                break;
            }
            case 1607:
            case 1877:
            case 7019: {
                nomyChange.setChangetype("隐身");
                break;
            }
            case 1224:
            case 1608:
            case 1612:
            case 1830: {
                nomyChange.setChangetype("清除异常状态");
                break;
            }
        }
        nomyChange.setSkill(fightingSkill);
        nomyData.ChangeData(nomyChange, fightingState);
        Accepterlist.add(fightingState);
    }
    
    public static void Disposable(List<FightingState> Accepterlist, FightingState fightingState, ManData manData, ManData nomyData, FightingSkill fightingSkill, String skilltype, Battlefield battlefield) {
        ChangeFighting nomyChange = new ChangeFighting();
        nomyChange.setChangehp((int)((double)nomyData.getHp_z() * fightingSkill.getSkillgain()));
        nomyChange.setChangemp((int)((double)nomyData.getMp_z() * fightingSkill.getSkillgain()));
        nomyData.ChangeData(nomyChange, fightingState);
        Accepterlist.add(fightingState);
    }
}
