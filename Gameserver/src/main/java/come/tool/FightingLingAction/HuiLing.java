package come.tool.FightingLingAction;

import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.FightingEvents;
import java.util.ArrayList;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingSkill;
import java.util.List;
import come.tool.FightingData.ManData;

public class HuiLing implements LingAction
{
    @Override
    public void lingAction(ManData manData, List<ManData> help, FightingSkill skill, Battlefield battlefield) {
        ManData data = battlefield.getLingParents(manData);
        List<FightingState> Accepterlist = new ArrayList<>();
        FightingEvents moveEvents = new FightingEvents();
        List<FightingState> Accepterlist2 = new ArrayList<>();
        FightingState Accepter = new FightingState();
        Accepter.setStartState("代价");
        ChangeFighting changeFightinghf = new ChangeFighting();
        changeFightinghf.setChangehp(1);
        changeFightinghf.setChangemp(4000);
        data.ChangeData(changeFightinghf, Accepter);
        Accepterlist.add(Accepter);
        moveEvents.setAccepterlist(Accepterlist);
        battlefield.NewEvents.add(moveEvents);
    }
}
