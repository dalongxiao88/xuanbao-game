package come.tool.FightingEffect;

import come.tool.Fighting.FightingMixDeal;
import come.tool.Fighting.StateProgress;
import come.tool.Fighting.FightingState;

public class EscapeEffect implements Effect
{
    @Override
    public StateProgress analysisAction(FightingState State, int path) {
        StateProgress progress = EffectType.getProgress(State, path);
        if (State.getStartState().equals("逃跑成功")) {
            progress.setEscape(1);
        }
        progress.setType(1);
        if (State.getCamp() == FightingMixDeal.camp) {
            progress.setDir(3);
            progress.setDirend(7);
        }
        else {
            progress.setDir(7);
            progress.setDirend(3);
        }
        return progress;
    }
}
