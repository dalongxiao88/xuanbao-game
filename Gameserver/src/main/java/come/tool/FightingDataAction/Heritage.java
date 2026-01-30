package come.tool.FightingDataAction;

import java.util.List;
import come.tool.FightingData.FightingState;
import java.util.ArrayList;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public class Heritage implements DataAction
{
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        ManData data = battlefield.minmp(fightingEvents.getOriginator().getCamp());
        if (data == null) {
            return;
        }
        int mp = (int)((double)data.getMp_z() * Double.parseDouble(fightingEvents.getOriginator().getEndState()));
        mp = ((mp <= 0) ? 1 : mp);
        ChangeFighting hf = new ChangeFighting();
        hf.setChangemp(mp);
        FightingEvents gjEvents = new FightingEvents();
        List<FightingState> zls = new ArrayList<>();
        FightingState ace = new FightingState();
        data.ChangeData(hf, ace);
        ace.setStartState("药");
        zls.add(ace);
        gjEvents.setAccepterlist(zls);
        battlefield.NewEvents.add(gjEvents);
    }
}
