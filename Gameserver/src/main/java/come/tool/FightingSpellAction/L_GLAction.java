package come.tool.FightingSpellAction;

import java.util.List;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.TypeUtil;
import java.util.ArrayList;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public class L_GLAction implements SpellAction
{
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        FightingSkill tycSkill = null;
        if (skill.getSkillid() == 9610 || skill.getSkillid() == 9611 || skill.getSkillid() == 9612) {
            tycSkill = skill;
            skill = manData.skillId(MixDeal.getTYSkillId(tycSkill.getSkillid()));
            if (skill == null) {
                return;
            }
        }
        List<FightingState> Accepterlist = new ArrayList<>();
        List<ManData> datas = MixDeal.getjieshou(events, skill, manData, Accepterlist, battlefield);
     	if (skill.getSkillid() >= 1000 && skill.getSkillid() <= 1099) {
            datas.removeIf(e -> (e.xzstate("屠巫剑") != null));
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
            List<FightingSkill> skills = ControlAction.getL_GL_Skills(manData, skill, battlefield.BattleType);
            FightingState Originator2 = events.getOriginator();
            if (manData.daijia(skill, Originator2, battlefield)) {
                return;
            }
            Originator2.setStartState("法术攻击");
            Originator2.setSkillsy(skill.getSkillname());
            String type = skill.getSkilltype();
            double glv = manData.getsx(2, "甘霖");
            double glc = manData.getsx(2, "甘霖回血");
            if (skill.getSkillid() == 1094) {
                FightingSkill skill2 = manData.skillId(9601);
                if (skill2 != null) {
                    glc += skill2.getSkillhurt();
                }
                skills = ControlAction.addSkill(manData.getSkillType(TypeUtil.TY_L_GL_YMFZ), skills);
                skills = ControlAction.addSkill(manData.getSkillType(TypeUtil.TY_L_GL_YLPT), skills);
                skills = ControlAction.addSkill(manData.getSkillType(TypeUtil.TY_L_10115), skills);
            }
            int skillhurt = (int)((skill.getSkillhurt() + glv) * (1.0 + glc / 100.0) * 1.0);
            for (int i = 0; i < datas.size(); ++i) {
                ManData data = (ManData)datas.get(i);
                data.addBear(type);
                FightingState Accepter = new FightingState();
                if (tycSkill != null) {
                    if (tycSkill.getSkillid() == 9610) {
                        data.addnq((int)tycSkill.getSkillhurt(), Accepter);
                        data.TY_L_GL_MYJS();
                    }
                    else if (tycSkill.getSkillid() == 9611) {
                        ChangeFighting fighting = new ChangeFighting();
                        fighting.setChangetype(tycSkill.getSkilltype());
                        fighting.setChangevlaue(tycSkill.getSkillhurt());
                        fighting.setChangesum(3);
                        data.ChangeData(fighting, Accepter);
                    }
                }
                FightingSkill skill3 = skill.clone();
                FightingSkill skill_4018 = data.getSkillType("4018");
                if (skill_4018 != null && Battlefield.isV(skill_4018.getValue1()) && type == "甘霖") {
                    skill3.setSkillcontinued(skill3.getSkillcontinued() + 1);
                }
                ChangeFighting acec = new ChangeFighting();
                if (data.getStates() == 0) {
                    acec.setChangevlaue((double)skillhurt);
                    acec.setChangetype(type);
                    acec.setChangesum(skill3.getSkillcontinued());
                    if (skills != null) {
                        for (int j = skills.size() - 1; j >= 0; --j) {
                            FightingSkill fightingSkill = (FightingSkill)skills.get(j);
                            int id = fightingSkill.getSkillid();
                            if (id == 9603) {
                                if (Battlefield.isV(20.0)) {
                                    acec.setSkill(fightingSkill);
                                }
                            }
                            else if (id >= 9602 && id <= 9608) {
                                acec.setSkill(fightingSkill);
                            }
                            else if (id == 10115) {
                                data.addnq((int)fightingSkill.getSkillhurt(), Accepter);
                            }
                        }
                    }
                    if (tycSkill != null && tycSkill.getSkillid() == 9612) {
                        acec.setSkill(tycSkill);
                    }
                }
                else {
                    acec.setChangehp(skillhurt);
                    if (skills != null) {
                        int j = skills.size() - 1;
                        while (j >= 0) {
                            FightingSkill fightingSkill = (FightingSkill)skills.get(j);
                            if (fightingSkill.getSkillid() == 9608) {
                                acec.setChangehp((int)((double)skillhurt * (1.0 + fightingSkill.getSkillhurt() / 100.0)));
                                break;
                            }
                            else {
                                --j;
                            }
                        }
                    }
                }
                FightingPackage.ChangeProcess(acec, manData, data, Accepter, type, Accepterlist, battlefield);
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
            if (manData.getStates() != 0) {
                MixDeal.DeathSkill(manData, Originator2, battlefield);
            }
            return;
        }
    }
    
    public ChangeFighting L_GLChange(ManData myData, ManData data, FightingSkill skill, List<FightingSkill> skills, Battlefield battlefield) {
        return null;
    }
}
