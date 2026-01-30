package come.tool.FightingDataAction;

import java.util.List;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.MixDeal;
import org.come.bean.PathPoint;
import java.util.ArrayList;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public class Huayu implements DataAction
{
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        List<PathPoint> yi = battlefield.huayu;
        List<PathPoint> er = new ArrayList<>();
        int camp = ((PathPoint)yi.get(0)).getX();
        for (int i = yi.size() - 1; i >= 0; --i) {
            if (((PathPoint)yi.get(i)).getX() != camp) {
                er.add(yi.get(i));
                yi.remove(i);
            }
        }
        fightingEvents = new FightingEvents();
        List<FightingState> Accepterlist = new ArrayList<>();
        if (yi.size() != 0) {
            int camp2 = battlefield.nomy(((PathPoint)yi.get(0)).getX());
            List<ManData> datas = MixDeal.get(false, null, 0, camp2, 1, 0, 0, 0, yi.size(), -1, battlefield, 1);
            for (int j = datas.size() - 1; j >= 0; --j) {
                ManData data = (ManData)datas.get(j);
                ChangeFighting changeFighting = new ChangeFighting();
                changeFighting.setChangehp(((PathPoint)yi.get(j)).getY());
                FightingState org2 = new FightingState();
                org2.setCamp(data.getCamp());
                org2.setMan(data.getMan());
                org2.setStartState("被攻击");
                FightingPackage.ChangeProcess(changeFighting, null, data, org2, "化羽", Accepterlist, battlefield);
            }
        }
        if (er.size() != 0) {
            List<ManData> datas2 = MixDeal.get(false, null, 0, camp, 1, 0, 0, 0, er.size(), -1, battlefield, 1);
            for (int k = datas2.size() - 1; k >= 0; --k) {
                ManData data2 = (ManData)datas2.get(k);
                ChangeFighting changeFighting2 = new ChangeFighting();
                changeFighting2.setChangehp(((PathPoint)er.get(k)).getY());
                FightingState org3 = new FightingState();
                org3.setCamp(data2.getCamp());
                org3.setMan(data2.getMan());
                org3.setStartState("被攻击");
                FightingPackage.ChangeProcess(changeFighting2, null, data2, org3, "化羽", Accepterlist, battlefield);
            }
        }
        if (Accepterlist.size() != 0) {
            fightingEvents.setAccepterlist(Accepterlist);
            battlefield.NewEvents.add(fightingEvents);
        }
    }
}
