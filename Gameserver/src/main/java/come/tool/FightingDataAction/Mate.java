package come.tool.FightingDataAction;

import java.util.Iterator;
import come.tool.FightingData.AddState;
import java.util.List;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.ChangeFighting;
import java.util.ArrayList;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public class Mate implements DataAction
{
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        int nomycamp = battlefield.nomy(fightingEvents.getOriginator().getCamp());
        int ren = 0;
        if (type.equals(TypeUtil.BB_JS)) {
            ren = 2;
        }
        List<ManData> datas = MixDeal.get(true, manData, 1, nomycamp, 1, ren, 1, 0, 10, -1, battlefield, 1);
        if (datas.size() == 0) {
            return;
        }
        FightingEvents gjEvents = new FightingEvents();
        List<FightingState> zls = new ArrayList<>();
        ChangeFighting hf = new ChangeFighting();
        hf.setChangetype("清除异常状态");
        for (int i = 0; i < datas.size(); ++i) {
            ManData data = (ManData)datas.get(i);
            if (data.getType() == 1) {
                AddState addState = data.xzstate("封印");
                if (addState == null || addState.getStateEffect2() != 9999.0 || addState.getSurplus() == 0) {
                    FightingState ace = new FightingState();
                    data.ChangeData(hf, ace);
                    ace.setStartState(TypeUtil.JN);
                    zls.add(ace);
                    int sp = 0;
                    if (data.getCamp() == 1) {
                        sp = Battlefield.overcamp1sp;
                    }
                    else {
                        sp = Battlefield.overcamp2sp;
                    }
                    if (data.getSp() > sp) {
                        ManData removeMandata = null;
                        for (ManData item : battlefield.getWaitList()) {
                            if (item.getManname().equals(data.getManname())) {
                                removeMandata = item;
                                break;
                            }
                        }
                        battlefield.getWaitList().remove(removeMandata);
                    }
                }
            }
        }
        if (zls.size() == 0) {
            int i = 0;
            while (i < battlefield.fightingdata.size()) {
                ManData data = (ManData)battlefield.fightingdata.get(i);
                if (data.getCamp() == fightingEvents.getOriginator().getCamp() && data.getStates() == 0) {
                    FightingState ace2 = new FightingState();
                    ace2.setCamp(data.getCamp());
                    ace2.setMan(data.getMan());
                    ace2.setStartState(TypeUtil.JN);
                    zls.add(ace2);
                    break;
                }
                else {
                    ++i;
                }
            }
        }
        if (zls.size() == 0) {
            return;
        }
        gjEvents.setAccepterlist(zls);
        ((FightingState)gjEvents.getAccepterlist().get(0)).setSkillskin("1830");
        battlefield.NewEvents.add(gjEvents);
    }
}
