package come.tool.FightingSpellAction;

import java.util.List;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.ChangeFighting;
import java.util.ArrayList;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;
import come.tool.FightingDataAction.DataAction;

public class QC implements DataAction
{
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        FightingEvents gjEvents = new FightingEvents();
        List<FightingState> zls = new ArrayList<>();
        ChangeFighting hf = new ChangeFighting();
        hf.setChangetype("清除异常状态");
        FightingState ace = new FightingState();
        manData.ChangeData(hf, ace);
        ace.setStartState(TypeUtil.JN);
        zls.add(ace);
        gjEvents.setAccepterlist(zls);
        ((FightingState)gjEvents.getAccepterlist().get(0)).setSkillskin("1294");
        battlefield.NewEvents.add(gjEvents);
    }
}
