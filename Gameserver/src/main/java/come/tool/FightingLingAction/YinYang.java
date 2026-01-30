package come.tool.FightingLingAction;

import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.FightingState;
import java.util.ArrayList;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingSkill;
import java.util.List;
import come.tool.FightingData.ManData;

public class YinYang implements LingAction
{
    @Override
    public void lingAction(ManData manData, List<ManData> help, FightingSkill skill, Battlefield battlefield) {
        ManData data = null;
        int path = -1;
        path = battlefield.Datapathhuo(manData.getCamp(), manData.getMan() - 10);
        if (path == -1) {
            return;
        }
        data = (ManData)battlefield.fightingdata.get(path);
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
        FightingState org3 = new FightingState();
        org3.setCamp(data.getCamp());
        org3.setMan(data.getMan());
        org3.setStartState(TypeUtil.JN);
        org3.setSkillskin(skill.getSkillid() + "");
        ChangeFighting changeFighting = new ChangeFighting();
        changeFighting.setChangetype(skill.getSkilltype());
        changeFighting.setChangevlaue(skill.getSkillgain());
        changeFighting.setChangesum(skill.getSkillcontinued());
        FightingPackage.ChangeProcess(changeFighting, null, data, org3, "增益", Accepterlist, battlefield);
        Accepterlist.add(org3);
        fightingEvents.setAccepterlist(Accepterlist);
        battlefield.NewEvents.add(fightingEvents);
    }
}
