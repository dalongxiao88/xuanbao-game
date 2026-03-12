package come.tool.FightingLingAction;

import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingSkill;
import java.util.List;
import come.tool.FightingData.ManData;

public interface LingAction
{
    void lingAction(ManData manData, List<ManData> targets, FightingSkill fightingSkill, Battlefield battlefield);
}
