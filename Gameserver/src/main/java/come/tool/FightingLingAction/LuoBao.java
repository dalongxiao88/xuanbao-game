package come.tool.FightingLingAction;

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

public class LuoBao implements LingAction
{
    @Override
    public void lingAction(ManData manData, List<ManData> help, FightingSkill skill, Battlefield battlefield) {
        int sum = skill.getSkillsum();
        List<ManData> nomydata = MixDeal.get(false, null, 0, manData.getCamp(), 0, 1, 0, 0, sum, skill.getCamp(), battlefield, 1);
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
            ManData data = (ManData)nomydata.get(i);
            double hurt = skill.getSkillhurt() + manData.getShanghai() - data.getKangluobao();
            ChangeFighting changeFighting = new ChangeFighting();
            if ((double)Battlefield.random.nextInt(100) < hurt) {
                changeFighting.setChangehp(-data.getHp_z());
            }
            FightingState org3 = new FightingState();
            org3.setCamp(data.getCamp());
            org3.setMan(data.getMan());
            org3.setSkillskin(skill.getSkillid() + "");
            FightingPackage.ChangeProcess(changeFighting, null, data, org3, skill.getSkilltype(), Accepterlist, battlefield);
            org3.setStartState(TypeUtil.JN);
        }
        fightingEvents.setAccepterlist(Accepterlist);
        battlefield.NewEvents.add(fightingEvents);
    }
}
