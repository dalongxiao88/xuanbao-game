package come.tool.FightingLingAction;

import java.util.Iterator;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.FightingState;
import java.util.ArrayList;
import come.tool.FightingData.SummonType;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingSkill;
import java.util.List;
import come.tool.FightingData.ManData;

public class TianPengZhuanShi implements LingAction
{
    @Override
    public void lingAction(ManData manData, List<ManData> help, FightingSkill skill, Battlefield battlefield) {
        int sum = help.size();
        sum = ((sum == 0) ? 3 : (sum + 2));
        FightingEvents fightingEvents = new FightingEvents();
        SummonType.xianzhi(manData, skill);
        List<FightingState> Accepterlist = new ArrayList<>();
        FightingState org = new FightingState();
        org.setCamp(manData.getCamp());
        org.setMan(manData.getMan());
        org.setStartState("法术攻击");
        org.setText("看我的 #R天蓬转世#46");
        Accepterlist.add(org);
        fightingEvents.setAccepterlist(Accepterlist);
        battlefield.NewEvents.add(fightingEvents);
        FightingEvents fightingEvents2 = new FightingEvents();
        FightingState org2 = new FightingState();
        List<FightingState> Accepterlist2 = new ArrayList<>();
        org2.setCamp(manData.getCamp());
        org2.setMan(manData.getMan());
        org2.setStartState(TypeUtil.JN);
        org2.setSkillskin(skill.getSkillid() + "");
        Accepterlist2.add(org2);
        battlefield.NewEvents.add(fightingEvents2);
        List<ManData> nomydata = MixDeal.get(false, null, 0, manData.getCamp(), 0, 0, 0, 0, sum, 6, battlefield, 1);
        FightingEvents fightingEvents3 = new FightingEvents();
        List<FightingState> Accepterlist3 = new ArrayList<>();
        for (ManData nomydatum : nomydata) {
            FightingState org3 = new FightingState();
            org3.setCamp(nomydatum.getCamp());
            org3.setMan(nomydatum.getMan());
            org3.setStartState("被攻击");
            org3.setSkin("400149");
            org3.setText("嚎~嚎~#29#29");
            org3.setSkillskin("7035");
            ChangeFighting changeFighting = new ChangeFighting();
            changeFighting.setChangetype(skill.getSkilltype());
            changeFighting.setChangevlaue(skill.getSkillgain());
            changeFighting.setChangesum(skill.getSkillcontinued());
            FightingPackage.ChangeProcess(changeFighting, null, nomydatum, org3, skill.getSkilltype(), Accepterlist3, battlefield);
            Accepterlist3.add(org3);
        }
        fightingEvents3.setAccepterlist(Accepterlist3);
        battlefield.NewEvents.add(fightingEvents3);
    }
}
