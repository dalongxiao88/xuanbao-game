package come.tool.FightingSpellAction;

import java.util.List;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingDataAction.DataActionType;
import java.util.ArrayList;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public class BB_HSSFAction implements SpellAction
{
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        FightingState org = new FightingState();
        org.setEndState(skill.getSkillname());
        if (manData.daijia(org, battlefield)) {
            return;
        }
        org.setCamp(manData.getCamp());
        org.setMan(manData.getMan());
        org.setStartState("药");
        FightingEvents fightingEvents = new FightingEvents();
        List<FightingState> States = new ArrayList<>();
        States.add(org);
        fightingEvents.setAccepterlist(States);
        battlefield.NewEvents.add(fightingEvents);
        DataActionType.getActionById(1).analysisAction(manData, events, TypeUtil.BB_E_HSSF, battlefield);
    }
}
