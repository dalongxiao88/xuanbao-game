package come.tool.FightingSpellAction;

import java.util.List;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.MixDeal;
import java.util.ArrayList;
import come.tool.FightingData.PK_MixDeal;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public class TY_FY_ZYCXAction implements SpellAction
{
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        if (!PK_MixDeal.isPK(battlefield.BattleType)) {
            return;
        }
        FightingState Originator = events.getOriginator();
        events.setOriginator(null);
        if (manData.daijia(skill, Originator, battlefield)) {
            return;
        }
        List<FightingState> Accepterlist = new ArrayList<>();
        List<ManData> datas = MixDeal.get(false, manData, 0, -1, 0, 0, 2, 0, 10, -1, battlefield, 1);
        for (int j = 0; j < datas.size(); ++j) {
            ((ManData)datas.get(j)).addAddState(TypeUtil.TY_FY_ZYCX, skill.getSkillhurt(), 0.0, 3);
            FightingState f = new FightingState();
            ManData data = (ManData)datas.get(j);
            f.setCamp(data.getCamp());
            f.setMan(data.getMan());
            f.setEndState_1("1221");
            Accepterlist.add(f);
        }
        manData.addAddState(TypeUtil.TY_FY_ZYCX, skill.getSkillhurt(), 0.0, 3);
        FightingState f2 = new FightingState();
        f2.setCamp(manData.getCamp());
        f2.setMan(manData.getMan());
        f2.setEndState_1("1221");
        Accepterlist.add(f2);
        FightingEvents ksevents = new FightingEvents();
        ksevents.setCurrentRound(battlefield.CurrentRound);
        Originator.setCamp(manData.getCamp());
        Originator.setMan(manData.getMan());
        Originator.setStartState("法术攻击");
        Accepterlist.add(Originator);
        ksevents.setAccepterlist(Accepterlist);
        battlefield.NewEvents.add(ksevents);
    }
}
