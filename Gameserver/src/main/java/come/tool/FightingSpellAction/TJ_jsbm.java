package come.tool.FightingSpellAction;

import java.util.List;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.ChangeFighting;
import java.util.ArrayList;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.GetqualityUntil;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public class TJ_jsbm implements SpellAction
{
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        GetqualityUntil.AddR(manData.getQuality(), "抗风", 50.9);
        GetqualityUntil.AddR(manData.getQuality(), "抗雷", 50.9);
        GetqualityUntil.AddR(manData.getQuality(), "抗水", 50.9);
        GetqualityUntil.AddR(manData.getQuality(), "抗火", 50.9);
        GetqualityUntil.AddR(manData.getQuality(), "抗鬼火", 50.9);
        GetqualityUntil.AddR(manData.getQuality(), "物理吸收率", 100.1);
        FightingEvents fe2 = new FightingEvents();
        FightingState fs2 = new FightingState();
        fs2.setStartState(TypeUtil.JN);
        List<FightingState> ac2 = new ArrayList<>();
        ChangeFighting fighting = new ChangeFighting();
        fighting.setChangesum(10);
        fighting.setChangevlaue(20.0);
        fs2.setStartState(TypeUtil.JN);
        fs2.setSkillskin(TypeUtil.TJ_jsbm);
        fighting.setChangesum(5);
        fs2.setStartState("法术攻击");
        fs2.setEndState_1(TypeUtil.TJ_jsbm);
        FightingPackage.ChangeProcess(fighting, manData, manData, fs2, TypeUtil.JN, ac2, battlefield);
        fe2.setAccepterlist(ac2);
        battlefield.NewEvents.add(fe2);
    }
}
