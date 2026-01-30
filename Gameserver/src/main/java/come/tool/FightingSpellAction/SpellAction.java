package come.tool.FightingSpellAction;

import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public interface SpellAction
{
    void spellAction(ManData p0, FightingSkill p1, FightingEvents p2, Battlefield p3);
}
