package come.tool.FightingDataAction;

import java.util.List;
import java.util.ArrayList;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public class FM_PTGJ implements DataAction
{
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        FightingState org = new FightingState();
        org.setEndState(type);
        org.setCamp(manData.getCamp());
        org.setMan(manData.getMan());
        FightingEvents events = new FightingEvents();
        List<FightingState> States = new ArrayList<>();
        States.add(org);
        events.setAccepterlist(States);
        battlefield.NewEvents.add(events);
        DataActionType.getActionById(1).analysisAction(manData, fightingEvents, type, battlefield);
        manData.setFmPhyAttack(true);
    }
}
