package come.tool.FightingDataAction;

import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public class Confusion implements DataAction
{
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        fightingEvents.setAccepterlist(null);
        if (manData.getHp() > 0) {
            fightingEvents.getOriginator().setStartState("普通攻击");
            DataActionType.getActionById(1).analysisAction(manData, fightingEvents, "混乱技", battlefield);
            return;
        }
        fightingEvents.getOriginator().setStartState("防御");
    }
}
