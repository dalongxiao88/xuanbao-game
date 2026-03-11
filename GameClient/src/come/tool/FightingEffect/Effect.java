package come.tool.FightingEffect;

import come.tool.Fighting.StateProgress;
import come.tool.Fighting.FightingState;

/**
 * 战斗特效处理接口。
 */
public interface Effect
{
    StateProgress analysisAction(FightingState fightingState, int effectType);
}
