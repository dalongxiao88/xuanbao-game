package come.tool.FightingLingAction;

import java.util.Iterator;
import come.tool.FightingData.SummonType;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.FightingState;
import java.util.ArrayList;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingSkill;
import java.util.List;
import come.tool.FightingData.ManData;

public class Blood implements LingAction
{
    @Override
    public void lingAction(ManData manData, List<ManData> help, FightingSkill skill, Battlefield battlefield) {
        int sum = skill.getSkillsum();
        int fengyin = 0;
        if (skill.getSkillid() == 3032) {
            fengyin = 1;
        }
        int camp = battlefield.nomy(manData.getCamp());
        List<ManData> nomydata = MixDeal.get(false, null, 0, camp, 0, 4, 1, 0, sum, skill.getCamp(), battlefield, 1);
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
            ChangeFighting changeFighting = new ChangeFighting();
            changeFighting.setChangehp(hurt);
            if (skill.getSkillcontinued() != 0) {
                changeFighting.setChangetype(skill.getSkilltype());
                changeFighting.setChangesum(skill.getSkillcontinued());
                changeFighting.setChangevlaue(skill.getSkillgain());
            }
            if (skill.getSkillid() == 3020) {
                if ((double)Battlefield.random.nextInt(100) < skill.getSkillgain()) {
                    changeFighting.setChangetype("清除异常状态");
                }
            }
            else if (skill.getSkillid() == 3032) {
                changeFighting.setChangehp((int)(skill.getSkillgain() * (double)data.getHp_z()));
                changeFighting.setChangetype("清除异常状态");
            }
            else if (skill.getSkillid() == 3023) {
                changeFighting.setChangevlaue2(skill.getSkillgain());
            }
            FightingState org3 = new FightingState();
            org3.setCamp(data.getCamp());
            org3.setMan(data.getMan());
            org3.setSkillskin(skill.getSkillid() + "");
            org3.setStartState(TypeUtil.JN);
            FightingPackage.ChangeProcess(changeFighting, null, data, org3, skill.getSkilltype(), Accepterlist, battlefield);
            if (skill.getSkillid() == 3014 && data.getvalue(0) <= 0.33) {
                FightingState org4 = new FightingState();
                org4.setCamp(data.getCamp());
                org4.setMan(data.getMan());
                org4.setStartState("代价");
                FightingPackage.ChangeProcess(changeFighting, null, data, org4, skill.getSkilltype(), Accepterlist, battlefield);
            }
            else if (skill.getSkillid() == 3015 && data.getvalue(0) <= 0.66) {
                FightingState org4 = new FightingState();
                org4.setCamp(data.getCamp());
                org4.setMan(data.getMan());
                org4.setStartState("代价");
                changeFighting.setChangehp(changeFighting.getChangehp() / 2);
                FightingPackage.ChangeProcess(changeFighting, null, data, org4, skill.getSkilltype(), Accepterlist, battlefield);
            }
        }
        fightingEvents.setAccepterlist(Accepterlist);
        battlefield.NewEvents.add(fightingEvents);
        for (ManData fightingdatum : battlefield.getFightingdata()) {
            if (fightingdatum.getCamp() == manData.getCamp() && fightingdatum.getType() == 3) {
                SummonType.xianzhi(manData, skill);
            }
        }
    }
}
