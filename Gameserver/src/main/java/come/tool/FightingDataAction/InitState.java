package come.tool.FightingDataAction;

import come.tool.FightingData.FightingState;
import java.util.List;
import java.util.ArrayList;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public class InitState implements DataAction
{
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        this.TZ_LZZN(battlefield);
        this.TZ_RYDS(battlefield);
        this.BB_RHTY(battlefield);
    }
    
    public void TZ_LZZN(Battlefield battlefield) {
        for (int i = battlefield.fightingdata.size() - 1; i >= 0; --i) {
            ManData data = (ManData)battlefield.fightingdata.get(i);
            FightingSkill skill = data.getSkillType(TypeUtil.TZ_LZZN);
            if (skill != null) {
                data.getSkills().remove(skill);
                for (int j = 0; j < data.getSkills().size(); ++j) {
                    FightingSkill fightingSkill = (FightingSkill)data.getSkills().get(j);
                    int id = fightingSkill.getSkillid();
                    if ((id >= 1081 && id <= 1090) || (id >= 1096 && id <= 1100)) {
                        fightingSkill.setSkillhurt(fightingSkill.getSkillhurt() + skill.getSkillhurt());
                    }
                }
            }
        }
    }
    
    public void TZ_RYDS(Battlefield battlefield) {
        List<FightingState> Accepterlist = null;
        for (int i = battlefield.fightingdata.size() - 1; i >= 0; --i) {
            ManData data = (ManData)battlefield.fightingdata.get(i);
            if (data.getStates() == 0) {
                FightingSkill skill = data.getSkillType(TypeUtil.TZ_RYDS);
                if (skill != null) {
                    if (Accepterlist == null) {
                        Accepterlist = new ArrayList<>();
                    }
                    FightingState Originator = this.getFightingState(data.getCamp(), data.getMan(), Accepterlist);
                    Originator.setStartState("法术攻击");
                    Originator.setSkillskin("1029");
                    Originator.setEndState_1(TypeUtil.LL);
                    data.addAddState(TypeUtil.LL, skill.getSkillhurt(), 0.0, skill.getSkillcontinued());
                    data.getSkills().remove(skill);
                }
            }
        }
        if (Accepterlist != null) {
            FightingEvents events = new FightingEvents();
            events.setAccepterlist(Accepterlist);
            battlefield.NewEvents.add(events);
        }
    }
    
    public void BB_RHTY(Battlefield battlefield) {
        List<FightingState> Accepterlist = null;
        for (int i = battlefield.fightingdata.size() - 1; i >= 0; --i) {
            ManData data = (ManData)battlefield.fightingdata.get(i);
            if (data.getStates() == 0 && (data.getMan() >= 5 && data.getMan() <= 9)) {
                FightingSkill skill = data.getSkillType(TypeUtil.BB_RHTY);
                if (skill != null) {
                    if (Accepterlist == null) {
                        Accepterlist = new ArrayList<>();
                    }
                    FightingState Originator = this.getFightingState(data.getCamp(), data.getMan(), Accepterlist);
                    Originator.setStartState("法术攻击");
                    Originator.setSkillskin(TypeUtil.BB_RHTY);
                    Originator.setEndState_1(TypeUtil.BB_RHTY);
                    data.addAddState(TypeUtil.BB_RHTY, skill.getSkillhurt(), 0.0, skill.getSkillcontinued());
                    int path = battlefield.Datapathhuo(data.getCamp(), data.getMan() - 5);
                    data = ((path != -1) ? ((ManData)battlefield.fightingdata.get(path)) : null);
                    if (data != null) {
                        FightingState fightingState = this.getFightingState(data.getCamp(), data.getMan(), Accepterlist);
                        fightingState.setEndState_1(TypeUtil.BB_RHTY);
                        data.addAddState(TypeUtil.BB_RHTY, skill.getSkillhurt(), 0.0, skill.getSkillcontinued());
                    }
                    data.getSkills().remove(skill);
                }
            }
        }
        if (Accepterlist != null) {
            FightingEvents events = new FightingEvents();
            events.setAccepterlist(Accepterlist);
            battlefield.NewEvents.add(events);
        }
    }
    
    public FightingState getFightingState(int camp, int man, List<FightingState> Accepterlist) {
        for (int i = 0; i < Accepterlist.size(); ++i) {
            FightingState state = (FightingState)Accepterlist.get(i);
            if (state.getCamp() == camp && state.getMan() == man) {
                return state;
            }
        }
        FightingState state2 = new FightingState(camp, man, TypeUtil.JN);
        Accepterlist.add(state2);
        return state2;
    }
}
