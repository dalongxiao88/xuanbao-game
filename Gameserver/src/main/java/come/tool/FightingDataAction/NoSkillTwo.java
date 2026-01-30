package come.tool.FightingDataAction;

import java.util.List;
import come.tool.FightingData.AddState;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.FightingState;
import java.util.ArrayList;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public class NoSkillTwo implements DataAction
{
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        AddState addState = manData.xzstate("化无");
        if (addState == null) {
            return;
        }
        FightingEvents hhe = new FightingEvents();
        List<FightingState> hhs = new ArrayList<>();
        ManData hhz = this.gethhz(addState.getStateEffect(), battlefield);
        if (hhz != null) {
            FightingState hhze = new FightingState();
            hhze.setCamp(hhz.getCamp());
            hhze.setMan(hhz.getMan());
            hhze.setStartState(TypeUtil.JN);
            hhze.setSkillskin("1828");
            hhs.add(hhze);
        }
        FightingState fs = new FightingState();
        manData.RemoveAbnormal("化无".split("\\|"));
        fs.setCamp(manData.getCamp());
        fs.setMan(manData.getMan());
        fs.setEndState_2("化无");
        fs.setStartState(TypeUtil.JN);
        hhs.add(fs);
        hhe.setAccepterlist(hhs);
        battlefield.NewEvents.add(hhe);
    }
    
    public ManData gethhz(double ren, Battlefield battlefield) {
        try {
            int camp = (int)ren;
            int man = (int)(ren * 10.0 % 10.0);
            int path = battlefield.Datapathhuo(camp, man);
            if (path != -1) {
                return (ManData)battlefield.fightingdata.get(path);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
