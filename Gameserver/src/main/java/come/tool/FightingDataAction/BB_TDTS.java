package come.tool.FightingDataAction;

import java.util.List;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.FightingState;
import java.util.ArrayList;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public class BB_TDTS implements DataAction
{
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        String skin = fightingEvents.getOriginator().getEndState();
        if (skin == null) {
            return;
        }
        int camp = fightingEvents.getOriginator().getCamp();
        ManData data = null;
        for (int i = battlefield.fightingdata.size() - 1; i >= 0; --i) {
            ManData data2 = (ManData)battlefield.fightingdata.get(i);
            if (data2.getStates() == 0 && data2.getType() == 1 && data2.getCamp() != camp && (data2.getSkin() != null && skin.equals(data2.getSkin()) && (data == null || Battlefield.isV(40.0)))) {
                data = data2;
            }
        }
        if (data == null) {
            return;
        }
        fightingEvents.setOriginator(null);
        List<FightingState> zls = new ArrayList<>();
        FightingState ace = new FightingState();
        ChangeFighting fighting = new ChangeFighting();
        FightingPackage.ChangeProcess(fighting, null, data, ace, TypeUtil.ZSSH, zls, battlefield);
        ace.setSkillskin(TypeUtil.BB_TDTS);
        fightingEvents.setAccepterlist(zls);
        battlefield.NewEvents.add(fightingEvents);
    }
}
