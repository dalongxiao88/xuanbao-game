package come.tool.FightingLingAction;

import come.tool.FightingData.AddState;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.FightingState;
import java.util.ArrayList;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingSkill;
import java.util.List;
import come.tool.FightingData.ManData;

public class Directinjury implements LingAction
{
    @Override
    public void lingAction(ManData manData, List<ManData> help, FightingSkill skill, Battlefield battlefield) {
        int sum = skill.getSkillsum();
        if (skill.getSkillid() == 3004 && Battlefield.random.nextInt(3) == 0) {
            ++sum;
        }
        List<ManData> nomydata = MixDeal.get(false, null, 0, manData.getCamp(), 0, 0, 0, 0, sum, skill.getCamp(), battlefield, 1);
        FightingEvents fightingEvents = new FightingEvents();
        List<FightingState> Accepterlist = new ArrayList<>();
        FightingState org = new FightingState();
        org.setCamp(manData.getCamp());
        org.setMan(manData.getMan());
        org.setStartState("法术攻击");
        Accepterlist.add(org);
        for (int i = help.size() - 1; i >= 0; --i) {
            FightingState org2 = new FightingState();
            org2.setCamp(((ManData)help.get(i)).getCamp());
            org2.setMan(((ManData)help.get(i)).getMan());
            org2.setStartState("法术攻击");
            Accepterlist.add(org2);
        }
        for (int i = nomydata.size() - 1; i >= 0; --i) {
            int hurt = (int)(skill.getSkillhurt() * manData.getShanghai());
            ManData data = (ManData)nomydata.get(i);
            if (((ManData)nomydata.get(i)).getType() == 1) {
                hurt = (int)((double)hurt - data.getQuality().getRoleklb());
                if (hurt < 0) {
                    hurt = 1;
                }
            }
            else {
                hurt = (int)((double)hurt * (1.0 - data.getQuality().getRoleklb() / 100.0));
            }
            FightingSkill skill2 = data.getAppendSkill(9224);
            if (skill2 != null && Battlefield.isV(skill2.getSkillhurt())) {
                hurt = 0;
            }
            ChangeFighting changeFighting = new ChangeFighting();
            if (skill.getSkillid() == 3002) {
                changeFighting.setChangehp(-(Battlefield.random.nextInt(hurt) + 1));
            }
            else if (skill.getSkillid() == 3003) {
                changeFighting.setChangehp((int)((double)(-hurt) * (1.0 - data.getvalue(2))));
            }
            else if (skill.getSkillid() == 3004) {
                changeFighting.setChangehp((int)((double)(-hurt) * data.getvalue(2)));
            }
            else if (skill.getSkillid() == 3007) {
                changeFighting.setChangehp(-hurt);
                changeFighting.setChangemp(-(hurt >> 1));
            }
            else if (skill.getSkillid() == 3008) {
                changeFighting.setChangehp(-hurt);
                changeFighting.setChangemp(-(hurt << 1));
            }
            else {
                changeFighting.setChangehp(-hurt);
            }
            if (skill.getSkillcontinued() != 0) {
                changeFighting.setChangetype(skill.getSkilltype());
                changeFighting.setChangesum(skill.getSkillcontinued());
                changeFighting.setChangevlaue(skill.getSkillgain());
            }
            FightingState org3 = new FightingState();
            org3.setCamp(data.getCamp());
            org3.setMan(data.getMan());
            org3.setSkillskin(skill.getSkillid() + "");
            org3.setStartState("被攻击");
            FightingPackage.ChangeProcess(changeFighting, null, data, org3, skill.getSkilltype(), Accepterlist, battlefield);
            if (skill.getSkillid() == 3005 && data.getvalue(0) >= 0.66) {
                FightingState org4 = new FightingState();
                org4.setCamp(data.getCamp());
                org4.setMan(data.getMan());
                org4.setStartState("代价");
                changeFighting.setChangehp(-hurt);
                FightingPackage.ChangeProcess(changeFighting, null, data, org4, skill.getSkilltype(), Accepterlist, battlefield);
            }
            else if (skill.getSkillid() == 3006 && data.getvalue(0) <= 0.33) {
                FightingState org4 = new FightingState();
                org4.setCamp(data.getCamp());
                org4.setMan(data.getMan());
                org4.setStartState("代价");
                changeFighting.setChangehp(-hurt / 2);
                FightingPackage.ChangeProcess(changeFighting, null, data, org4, skill.getSkilltype(), Accepterlist, battlefield);
            }
            if (data.getStates() != 0) {
                FightingSkill skill3 = data.getSkillType(TypeUtil.TY_JS_TTHG);
                if (skill3 != null) {
                    AddState addState = data.getstat("抗灵宝");
                    double totalStateEffect;
                    double stateEffect = totalStateEffect = skill3.getSkillhurt();
                    if (addState != null) {
                        totalStateEffect += addState.getStateEffect();
                    }
                    if (totalStateEffect > skill3.getSkillgain()) {
                        stateEffect = totalStateEffect - skill3.getSkillgain();
                    }
                    if (stateEffect > 0.0) {
                        data.addAddState2("抗灵宝", skill3.getSkillhurt(), 0.0, 99999);
                        data.getQuality().setRoleklb(data.getsx(0, "抗灵宝伤害"));
                    }
                }
            }
        }
        fightingEvents.setAccepterlist(Accepterlist);
        battlefield.NewEvents.add(fightingEvents);
    }
}
