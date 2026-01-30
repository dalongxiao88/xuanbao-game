package come.tool.FightingDataAction;

import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public interface DataAction
{
    void analysisAction(ManData p0, FightingEvents p1, String p2, Battlefield p3);
}
