package come.tool.FightingDataAction;

import java.util.List;
import come.tool.FightingData.AddState;
import java.util.ArrayList;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public class ZiCang implements DataAction
{
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        FightingState org = new FightingState();
        org.setEndState(type);
        if (manData.daijia(org, battlefield)) {
            return;
        }
        org.setCamp(manData.getCamp());
        org.setMan(manData.getMan());
        org.setStartState("药");
        if (type.equals("兵临城下")) {
            manData.executeFbgs(0, org);
        }
        FightingEvents events = new FightingEvents();
        List<FightingState> States = new ArrayList<>();
        States.add(org);
        events.setAccepterlist(States);
        battlefield.NewEvents.add(events);
        DataActionType.getActionById(1).analysisAction(manData, fightingEvents, type, battlefield);
        if (manData.getStates() == 0 && type.equals("兵临城下") && Battlefield.isV((double)manData.executeFbgs(6, null))) {
            AddState addState = new AddState();
            addState.setStatename("隐身");
            addState.setSurplus(Battlefield.random.nextInt(2) + 2);
            manData.getAddStates().add(addState);
            FightingEvents events2 = new FightingEvents();
            List<FightingState> yin = new ArrayList<>();
            FightingState org2 = new FightingState();
            org2.setCamp(manData.getCamp());
            org2.setMan(manData.getMan());
            org2.setEndState_1("隐身");
            yin.add(org2);
            events2.setAccepterlist(yin);
            battlefield.NewEvents.add(events2);
        }
    }
}
