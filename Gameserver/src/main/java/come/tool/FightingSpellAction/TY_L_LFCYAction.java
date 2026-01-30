package come.tool.FightingSpellAction;

import java.util.List;
import come.tool.FightingDataAction.PhyAttack;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.MixDeal;
import java.util.ArrayList;
import come.tool.FightingData.SummonType;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public class TY_L_LFCYAction implements SpellAction
{
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        SummonType.xianzhi(manData, skill);
        FightingSkill skillSM = manData.skillId(1100);
        String skilltype = skillSM.getSkilltype();
        List<FightingState> Accepterlist = new ArrayList<>();
        List<ManData> datas = MixDeal.getjieshou(events, skillSM, manData, Accepterlist, battlefield);
        if (datas.size() == 0) {
            FightingState Originator = events.getOriginator();
            if (manData.daijia(skillSM, Originator, battlefield)) {
                return;
            }
            Originator.setStartState("法术攻击");
            Originator.setSkillsy(skillSM.getSkillname());
            events.setOriginator(null);
            Accepterlist.add(Originator);
            events.setAccepterlist(Accepterlist);
            battlefield.NewEvents.add(events);
            return;
        }
        else {
            boolean isXH = true;
            double xs = 1.0;
            double qsx = manData.getsx(2, skillSM.getSkilltype());
            long Zap = (long)((double)manData.getAp() * skillSM.getSkillhurt() / 100.0 * xs * (1.0 + qsx / 100.0));
            int sum = 2;
            List<FightingSkill> skills = null;
            if (skilltype.equals("扶摇")) {
                skills = ControlAction.getL_FY_Skills(manData, skill, battlefield.BattleType);
            }
            int i = 0;
            while (i < sum) {
                if (manData.getStates() != 0) {
                    break;
                }
                else {
                    if (!isXH) {
                        Accepterlist = new ArrayList<>();
                    }
                    FightingState Originator2 = new FightingState();
                    Originator2.setEndState(skillSM.getSkillname());
                    if (isXH) {
                        isXH = false;
                        if (!manData.isLicense(skill)) {
                            break;
                        }
                        else if (manData.daijia(skill, Originator2, battlefield)) {
                            return;
                        }
                    }
                    else {
                        Originator2.setCamp(manData.getCamp());
                        Originator2.setMan(manData.getMan());
                    }
                    for (int j = datas.size() - 1; j >= 0; --j) {
                        ManData data = (ManData)datas.get(j);
                        if (data.getStates() != 0) {
                            data = MixDeal.getjieshou(skill, manData, datas, battlefield);
                            if (data != null) {
                                datas.set(j, data);
                            }
                            else {
                                datas.remove(j);
                                continue;
                            }
                        }
                        FightingState Accepter = MixDeal.DSMY(manData, data, skill.getSkillid(), battlefield);
                        if (Accepter == null) {
                            data.addBear(skilltype);
                            Accepter = new FightingState();
                            long ap = (long)((double)PhyAttack.Hurt(Zap, 0, manData, data, TypeUtil.JN, Accepter, Accepterlist, battlefield, 0.0, 0.0) * skill.getSkillhurt() / 100.0);
                            double hurt = skillSM.getSkillhurt() * skill.getSkillhurt() / 100.0;
                            L_PLCBFYAction.hurt(manData, data, i, hurt, ap, skill, skills, Accepter, Accepterlist, battlefield, Boolean.valueOf(false));
                        }
                        else {
                            Accepterlist.add(Accepter);
                        }
                        Accepter.setSkillskin(skillSM.getSkillid() + "");
                    }
                    Originator2.setStartState("法术攻击");
                    Originator2.setSkillsy(skill.getSkillname());
                    Accepterlist.add(Originator2);
                    FightingEvents Events = new FightingEvents();
                    Events.setAccepterlist(Accepterlist);
                    battlefield.NewEvents.add(Events);
                    if (Accepterlist.size() == 1) {
                        break;
                    }
                    else {
                        ++i;
                    }
                }
            }
            return;
        }
    }
}
