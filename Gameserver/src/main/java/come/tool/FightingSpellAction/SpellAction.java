package come.tool.FightingSpellAction;

import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public interface SpellAction
{
    void spellAction(ManData manData, FightingSkill fightingSkill, FightingEvents fightingEvents, Battlefield battlefield);
}
