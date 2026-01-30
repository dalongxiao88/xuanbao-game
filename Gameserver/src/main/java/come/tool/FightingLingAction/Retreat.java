package come.tool.FightingLingAction;

import java.util.Iterator;
import come.tool.FightingData.SummonType;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.MixDeal;
import java.util.Random;
import java.util.ArrayList;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.PK_MixDeal;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingSkill;
import java.util.List;
import come.tool.FightingData.ManData;

public class Retreat implements LingAction
{
    @Override
    public void lingAction(ManData manData, List<ManData> help, FightingSkill skill, Battlefield battlefield) {
        if (!PK_MixDeal.isPK(battlefield.BattleType)) {
            return;
        }
        if (manData.SkillCooling(skill.getSkillid())) {
            return;
        }
        FightingSkill zhaoHun = manData.getSkillId(3029);
        if (zhaoHun != null) {
            FightingEvents fightingEvents = new FightingEvents();
            List<FightingState> Accepterlist = new ArrayList<>();
            skill.setSkillsum(2);
            List<ManData> nomydata = MixDeal.get(false, null, 0, manData.getCamp(), 0, 3, 0, 0, new Random().nextBoolean() ? 1 : 2, skill.getCamp(), battlefield, 1);
            MixDeal.move(manData.getCamp(), manData.getMan(), "移动", "-1|-1", battlefield);
            fightingEvents = new FightingEvents();
            FightingState org = new FightingState();
            org.setCamp(manData.getCamp());
            org.setMan(manData.getMan());
            org.setStartState("法术攻击");
            org.setText("乾坤破阵#2");
            Accepterlist.add(org);
            for (ManData data : nomydata) {
                FightingState org2 = new FightingState();
                org2.setCamp(data.getCamp());
                org2.setMan(data.getMan());
                MixDeal.move(data.getCamp(), data.getMan(), "闪现", "-1|-1", battlefield);
                data.addAddState(skill.getSkilltype(), 0.5, 0.0, 2);
                ChangeFighting changeFighting = new ChangeFighting();
                changeFighting.setChangetype(skill.getSkilltype());
                changeFighting.setChangevlaue(skill.getSkillgain());
                changeFighting.setChangesum(2);
                FightingPackage.ChangeProcess(changeFighting, null, data, org2, skill.getSkilltype(), Accepterlist, battlefield);
            }
            fightingEvents.setAccepterlist(Accepterlist);
            battlefield.NewEvents.add(fightingEvents);
            MixDeal.move(manData.getCamp(), manData.getMan(), "移动", manData.getCamp() + "|" + (manData.getMan() - (manData.getType() - 1) * 5) + "|50|50", battlefield);
        }
        for (ManData fightingdatum : battlefield.getFightingdata()) {
            if (fightingdatum.getCamp() == manData.getCamp() && fightingdatum.getType() == 3) {
                SummonType.xianzhi(manData, skill);
            }
        }
    }
}
