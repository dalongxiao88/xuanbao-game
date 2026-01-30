package come.tool.FightingLingAction;

import java.util.Iterator;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.FightingEvents;
import java.util.ArrayList;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingSkill;
import java.util.List;
import come.tool.FightingData.ManData;

public class ZhaoHun implements LingAction
{
    @Override
    public void lingAction(ManData manData, List<ManData> help, FightingSkill skill, Battlefield battlefield) {
        FightingSkill zhaoHun = manData.getSkillId(3036);
        if (zhaoHun != null) {
            List<ManData> nomydata = MixDeal.get(false, null, 2, battlefield.nomy(manData.getCamp()), 1, 0, 0, 0, 1, -1, battlefield, 1);
            for (ManData data : nomydata) {
                List<FightingState> Accepterlist = new ArrayList<>();
                FightingEvents moveEvents = new FightingEvents();
                List<FightingState> Accepterlist2 = new ArrayList<>();
                FightingState Accepter = new FightingState();
                Accepter.setStartState("代价");
                Accepter.setText("招魂");
                ChangeFighting changeFightinghf = new ChangeFighting();
                changeFightinghf.setChangehp((int)((double)data.getHp_z() * 0.2));
                data.ChangeData(changeFightinghf, Accepter);
                Accepterlist.add(Accepter);
                moveEvents.setAccepterlist(Accepterlist);
                battlefield.NewEvents.add(moveEvents);
            }
        }
    }
}
