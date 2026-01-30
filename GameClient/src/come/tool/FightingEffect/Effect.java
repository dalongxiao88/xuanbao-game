package come.tool.FightingEffect;

import come.tool.Fighting.StateProgress;
import come.tool.Fighting.FightingState;

public interface Effect
{
    StateProgress analysisAction(FightingState p0, int p1);
}
