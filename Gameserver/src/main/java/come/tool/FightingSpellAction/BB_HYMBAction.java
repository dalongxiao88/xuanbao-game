package come.tool.FightingSpellAction;

import java.util.Iterator;
import come.tool.FightingData.GroupBuff;
import come.tool.FightingData.FightingState;
import java.util.List;
import come.tool.FightingData.AddState;
import come.tool.FightingData.MixDeal;
import java.util.ArrayList;
import come.tool.FightingData.SummonType;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public class BB_HYMBAction implements SpellAction
{
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        if (skill.getSkillid() == 23009 && battlefield.CurrentRound <= 3) {
            return;
        }
        if (skill.getSkillid() == 23009 && manData.SkillCooling(23009)) {
            return;
        }
        if (skill.getSkillid() == 23009) {
            SummonType.xianzhi(manData, skill);
        }
        List<FightingState> Accepterlist = new ArrayList<>();
        FightingState Originator = events.getOriginator();
        if (manData.daijia(skill, Originator, battlefield)) {
            return;
        }
        List<GroupBuff> buffs = battlefield.addBuff(manData, skill);
        Originator.setStartState("法术攻击");
        Originator.setSkillsy(skill.getSkillname());
        if (buffs != null) {
            Originator.setBuff(MixDeal.getBuffStr(buffs, true));
        }
        events.setOriginator(null);
        Accepterlist.add(Originator);
        events.setAccepterlist(Accepterlist);
        battlefield.NewEvents.add(events);
        for (ManData fightingdatum : battlefield.getFightingdata()) {
            if (fightingdatum.getCamp() == manData.getCamp() && fightingdatum.getType() == 0) {
                AddState addState = null;
                addState = new AddState();
                addState.setStatename("冷却");
                addState.setStateEffect(23009.0);
                addState.setSurplus(5);
                fightingdatum.getAddStates().add(addState);
            }
        }
    }
}
