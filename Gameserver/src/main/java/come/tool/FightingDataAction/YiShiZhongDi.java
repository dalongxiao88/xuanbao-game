package come.tool.FightingDataAction;

import come.tool.FightingData.ChangeFighting;
import java.util.List;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.AddState;
import java.util.ArrayList;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public class YiShiZhongDi implements DataAction
{
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        FightingSkill fightingSkill = manData.skillname(fightingEvents.getOriginator().getEndState());
        if (fightingSkill == null) {
            return;
        }
        String skilltype = fightingSkill.getSkilltype();
        if (!skilltype.equals("一矢中的")) {
            return;
        }
        FightingSkill ad = manData.skillname("暗渡陈仓");
        if (ad == null) {
            battlefield.systemMsg(manData, null, 999, fightingSkill);
            return;
        }
        int camp = -1;
        int man = -1;
        if (fightingEvents.getAccepterlist() != null && fightingEvents.getAccepterlist().size() != 0) {
            camp = ((FightingState)fightingEvents.getAccepterlist().get(0)).getCamp();
            man = ((FightingState)fightingEvents.getAccepterlist().get(0)).getMan();
        }
        int path = battlefield.Datapath(camp, man);
        ManData data = null;
        if (path != -1) {
            data = (ManData)battlefield.fightingdata.get(path);
        }
        if (data == null || data.getStates() != 0) {
            return;
        }
        List<FightingState> list = new ArrayList<>();
        if (data.getStates() != 0) {
            return;
        }
        FightingState mys = fightingEvents.getOriginator();
        mys.setStartState("法术攻击");
        mys.setSkillsy("一矢中的");
        mys.setCamp(manData.getCamp());
        mys.setMan(manData.getMan());
        list.add(mys);
        fightingEvents.setOriginator(null);
        AddState addState = new AddState();
        addState.setStateEffect(ad.getSkillhurt());
        addState.setType(1);
        addState.setStatename("暗渡失效");
        addState.setSurplus(3);
        manData.addAddState("暗渡失效", ad.getSkillhurt(), 0.0, 3);
        manData.addAddState("冷却", (double)fightingSkill.getSkillid(), 0.0, 5);
        double xs = ad.getSkillhurt() * (double)manData.executeYszd(1) / 100.0;
        data.addAddState("一矢中的", xs, 0.0, 2);
        FightingState firend = new FightingState();
        firend.setCamp(data.getCamp());
        firend.setMan(data.getMan());
        firend.setEndState_1("一矢中的");
        list.add(firend);
        fightingEvents.setOriginator(null);
        fightingEvents.setAccepterlist(list);
        battlefield.NewEvents.add(fightingEvents);
    }
    
    public ChangeFighting TypeGain(String type, double xs) {
        ChangeFighting changeFighting = new ChangeFighting();
        changeFighting.setChangetype(type);
        changeFighting.setChangesum(2);
        changeFighting.setChangevlaue(xs);
        return changeFighting;
    }
}
