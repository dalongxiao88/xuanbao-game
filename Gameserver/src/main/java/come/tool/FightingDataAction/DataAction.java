package come.tool.FightingDataAction;

import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public interface DataAction
{
    void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield);
}
