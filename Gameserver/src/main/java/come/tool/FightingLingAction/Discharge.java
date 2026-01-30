package come.tool.FightingLingAction;

import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.MixDeal;
import org.come.bean.PathPoint;
import java.util.ArrayList;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingSkill;
import java.util.List;
import come.tool.FightingData.ManData;

public class Discharge implements LingAction
{
    @Override
    public void lingAction(ManData manData, List<ManData> help, FightingSkill skill, Battlefield battlefield) {
        List<ManData> nomydata = new ArrayList<>();
        int id = skill.getSkillid();
        PathPoint paPoint = new PathPoint(-1, -1);
        for (int i = battlefield.fightingdata.size() - 1; i >= 0; --i) {
            ManData data = (ManData)battlefield.fightingdata.get(i);
            if (id == 3011 && data.getSp() > manData.getSp() && id == 3012 && data.getSp() < manData.getSp() && data.zuoyong(0, manData.getCamp(), 1, 0, paPoint, 0, 0, 1)) {
                nomydata.add(data);
            }
        }
        if (nomydata.size() == 0 && skill.getSkillblue() == 1) {
            nomydata = MixDeal.get(false, null, 0, manData.getCamp(), 1, 0, 0, 0, 1, -1, battlefield, 1);
        }
        if (nomydata.size() == 0) {
            return;
        }
        FightingEvents fightingEvents = new FightingEvents();
        List<FightingState> Accepterlist = new ArrayList<>();
        FightingState org = new FightingState();
        org.setCamp(manData.getCamp());
        org.setMan(manData.getMan());
        org.setStartState("法术攻击");
        Accepterlist.add(org);
        for (int j = help.size() - 1; j >= 0; --j) {
            FightingState org2 = new FightingState();
            org2.setCamp(((ManData)help.get(j)).getCamp());
            org2.setMan(((ManData)help.get(j)).getMan());
            org2.setStartState("法术攻击");
            Accepterlist.add(org2);
        }
        for (int j = nomydata.size() - 1; j >= 0; --j) {
            int hurt = (int)skill.getSkillhurt() / nomydata.size();
            ManData data2 = (ManData)nomydata.get(j);
            hurt = (int)((double)hurt * (1.0 - data2.getQuality().getRoleklb() / 100.0));
            FightingSkill skill2 = data2.getAppendSkill(9224);
            if (skill2 != null && Battlefield.isV(skill2.getSkillhurt())) {
                hurt = 0;
            }
            ChangeFighting changeFighting = new ChangeFighting();
            changeFighting.setChangehp(-hurt);
            FightingState org3 = new FightingState();
            org3.setCamp(data2.getCamp());
            org3.setMan(data2.getMan());
            org3.setSkillskin(skill.getSkillid() + "");
            org3.setStartState("被攻击");
            FightingPackage.ChangeProcess(changeFighting, null, data2, org3, skill.getSkilltype(), Accepterlist, battlefield);
        }
        fightingEvents.setAccepterlist(Accepterlist);
        battlefield.NewEvents.add(fightingEvents);
    }
}
