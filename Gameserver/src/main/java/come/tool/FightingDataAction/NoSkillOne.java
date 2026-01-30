package come.tool.FightingDataAction;

import java.util.List;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.FightingState;
import java.util.ArrayList;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public class NoSkillOne implements DataAction
{
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        FightingEvents hhe = new FightingEvents();
        List<FightingState> hhs = new ArrayList<>();
        FightingState hh = new FightingState();
        ChangeFighting hhc = new ChangeFighting();
        hhc.setChangetype("化无");
        hhc.setChangevlaue((double)manData.getCamp() + 0.1 * (double)manData.getMan());
        int path = battlefield.Datapathhuo(fightingEvents.getOriginator().getCamp(), fightingEvents.getOriginator().getMan());
        if (path != -1) {
            ManData Data = (ManData)battlefield.fightingdata.get(path);
            Data.ChangeData(hhc, hh);
            hh.setStartState("代价");
            hhs.add(hh);
            hhe.setAccepterlist(hhs);
            battlefield.NewEvents.add(battlefield.xzsx(manData.getCamp(), manData.getMan()), hhe);
        }
    }
}
