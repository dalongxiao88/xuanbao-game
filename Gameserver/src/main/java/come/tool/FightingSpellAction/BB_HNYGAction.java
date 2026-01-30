package come.tool.FightingSpellAction;

import java.util.List;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.FightingState;
import java.util.ArrayList;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public class BB_HNYGAction implements SpellAction
{
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        ManData parent = battlefield.getPetParents(manData);
        if (parent == null) {
            return;
        }
        if (!manData.getSkills().remove(skill)) {
            return;
        }
        FightingEvents fightingEvents = new FightingEvents();
        List<FightingState> Accepterlist = new ArrayList<>();
        FightingState fightingState = new FightingState();
        fightingState.setStartState(TypeUtil.JN);
        fightingState.setCamp(manData.getCamp());
        fightingState.setMan(manData.getMan());
        ChangeFighting changeFighting = new ChangeFighting();
        changeFighting.setChangetype(skill.getSkilltype());
        changeFighting.setChangehp((int)((double)manData.getHp_z() * 0.66));
        changeFighting.setChangevlaue(66.0);
        changeFighting.setChangevlaue2(33.0);
        changeFighting.setChangesum(2);
        manData.ChangeData(changeFighting, fightingState);
        fightingState.setText("患难与共!#2");
        Accepterlist.add(fightingState);
        FightingState State2 = new FightingState();
        State2.setStartState(TypeUtil.JN);
        State2.setCamp(parent.getCamp());
        State2.setMan(parent.getMan());
        changeFighting = new ChangeFighting();
        changeFighting.setChangetype(skill.getSkilltype());
        changeFighting.setChangehp((int)((double)parent.getHp_z() * 0.66));
        changeFighting.setChangevlaue(66.0);
        changeFighting.setChangevlaue2(33.0);
        changeFighting.setChangesum(2);
        parent.ChangeData(changeFighting, State2);
        Accepterlist.add(State2);
        fightingEvents.setAccepterlist(Accepterlist);
        battlefield.NewEvents.add(fightingEvents);
    }
}
