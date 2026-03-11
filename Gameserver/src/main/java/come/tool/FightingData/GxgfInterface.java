package come.tool.FightingData;

import java.util.List;

/**
 * 攻击效果附加处理接口。
 */
public interface GxgfInterface
{
    void gxgf(FightingState fightingState, ManData attacker, ManData defender, ChangeFighting changeFighting, Battlefield battlefield, List<FightingState> historyStates);
}
