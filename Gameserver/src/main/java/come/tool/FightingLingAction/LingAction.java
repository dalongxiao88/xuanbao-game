package come.tool.FightingLingAction;

import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingSkill;
import java.util.List;
import come.tool.FightingData.ManData;

public interface LingAction
{
    void lingAction(ManData p0, List<ManData> p1, FightingSkill p2, Battlefield p3);
}
