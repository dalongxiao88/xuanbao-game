package come.tool.FightingDataAction;

import come.tool.FightingData.AddState;
import java.util.List;
import come.tool.FightingData.TypeUtil;
import java.util.ArrayList;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public class Transfer implements DataAction
{
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        ManData data = null;
        if (fightingEvents.getAccepterlist() != null && fightingEvents.getAccepterlist().size() != 0) {
            FightingState ac = (FightingState)fightingEvents.getAccepterlist().get(0);
            if (ac.getCamp() != manData.getCamp() || ac.getMan() != manData.getMan()) {
                int path = battlefield.Datapathhuo(ac.getCamp(), ac.getMan());
                if (path != -1) {
                    data = (ManData)battlefield.fightingdata.get(path);
                }
            }
        }
        if (data == null) {
            List<Integer> lists = new ArrayList<>();
            for (int i = 0; i < battlefield.fightingdata.size(); ++i) {
                ManData data2 = (ManData)battlefield.fightingdata.get(i);
                if (data2.getStates() == 0 && data2.getType() != 3 && data2.getType() != 4 && (data2.getCamp() != manData.getCamp() || data2.getMan() != manData.getMan())) {
                    lists.add(Integer.valueOf(i));
                }
            }
            if (lists.size() != 0) {
                data = (ManData)battlefield.fightingdata.get(Battlefield.random.nextInt(lists.size()));
            }
        }
        if (data == null) {
            FightingState Originator = fightingEvents.getOriginator();
            if (manData.daijia(Originator, battlefield)) {
                return;
            }
            Originator.setStartState("法术攻击");
            Originator.setSkillsy("移花接木");
            fightingEvents.setOriginator(null);
            List<FightingState> Accepterlist = new ArrayList<>();
            Accepterlist.add(Originator);
            fightingEvents.setAccepterlist(Accepterlist);
            battlefield.NewEvents.add(fightingEvents);
            return;
        }
        else {
            List<FightingState> myfStates = new ArrayList<>();
            FightingState Originator2 = fightingEvents.getOriginator();
            if (manData.daijia(Originator2, battlefield)) {
                return;
            }
            Originator2.setStartState("法术攻击");
            Originator2.setSkillsy("移花接木");
            myfStates.add(Originator2);
            List<FightingState> nofStates = new ArrayList<>();
            FightingState acc = new FightingState();
            acc.setCamp(data.getCamp());
            acc.setMan(data.getMan());
            acc.setStartState(TypeUtil.JN);
            acc.setSkillskin("1215");
            nofStates.add(acc);
            List<AddState> myStates = this.tiqu(manData, myfStates);
            List<AddState> noStates = this.tiqu(data, nofStates);
            this.add(manData, noStates, myfStates);
            this.add(data, myStates, nofStates);
            for (int j = 0; j < nofStates.size(); ++j) {
                myfStates.add(nofStates.get(j));
            }
            FightingEvents events = new FightingEvents();
            events.setAccepterlist(myfStates);
            battlefield.NewEvents.add(events);
            return;
        }
    }
    
    public List<AddState> tiqu(ManData data, List<FightingState> fightingStates) {
        int jl = 0;
        boolean isyw = false;
        List<AddState> States = new ArrayList<>();
        for (int i = data.getAddStates().size() - 1; i >= 0; --i) {
            AddState addState = (AddState)data.getAddStates().get(i);
            String type = addState.getStatename();
            if (type.equals("力量") || type.equals("加速") || type.equals("抗性") || type.equals("遗忘") || type.equals("混乱") || type.equals("封印") || type.equals("昏睡") || type.equals("中毒")) {
                if ((type.equals("遗忘") && !isyw) || !type.equals("遗忘")) {
                    FightingState fightingState = null;
                    if (fightingStates.size() <= jl) {
                        fightingState = new FightingState();
                        fightingState.setStartState("代价");
                        fightingState.setCamp(data.getCamp());
                        fightingState.setMan(data.getMan());
                        fightingStates.add(fightingState);
                    }
                    else {
                        fightingState = (FightingState)fightingStates.get(jl);
                    }
                    fightingState.setEndState_2(type);
                    ++jl;
                }
                States.add(addState);
                data.getAddStates().remove(i);
                if (type.equals("遗忘")) {
                    isyw = true;
                }
            }
        }
        return States;
    }
    
    public void add(ManData data, List<AddState> addStates, List<FightingState> fightingStates) {
        int jl = 0;
        boolean isyw = false;
        for (int i = addStates.size() - 1; i >= 0; --i) {
            AddState addState = (AddState)addStates.get(i);
            String type = addState.getStatename();
            if ((type.equals("遗忘") && !isyw) || !type.equals("遗忘")) {
                FightingState fightingState = null;
                if (fightingStates.size() <= jl) {
                    fightingState = new FightingState();
                    fightingState.setStartState("代价");
                    fightingState.setCamp(data.getCamp());
                    fightingState.setMan(data.getMan());
                    fightingStates.add(fightingState);
                }
                else {
                    fightingState = (FightingState)fightingStates.get(jl);
                }
                fightingState.setEndState_1(type);
                ++jl;
            }
            data.getAddStates().add(addState);
            if (type.equals("遗忘")) {
                isyw = true;
            }
        }
    }
}
