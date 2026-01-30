package come.tool.FightingSpellAction;

import come.tool.FightingData.AddState;
import java.util.List;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.MixDeal;
import java.util.ArrayList;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public class TY_L_LCDDAction implements SpellAction
{
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        List<FightingState> Accepterlist = new ArrayList<>();
        List<ManData> datas = MixDeal.getjieshou(events, skill, manData, Accepterlist, battlefield);
        if (datas.size() == 0) {
            FightingState Originator = events.getOriginator();
            Originator.setStartState("法术攻击");
            Originator.setSkillsy(skill.getSkillname());
            events.setOriginator(null);
            Accepterlist.add(Originator);
            events.setAccepterlist(Accepterlist);
            battlefield.NewEvents.add(events);
            return;
        }
        double Zap = manData.getQuality().getF_xh() * skill.getSkillhurt() / 100.0;
        FightingState Originator2 = events.getOriginator();
        Originator2.setStartState("法术攻击");
        Originator2.setSkillsy(skill.getSkillname());
        for (int j = 0; j < datas.size(); ++j) {
            ManData data = (ManData)datas.get(j);
            if (Battlefield.isV(Zap)) {
                FightingState fightingState = new FightingState();
                fightingState.setStartState("代价");
                ChangeFighting changeFighting = new ChangeFighting();
                changeFighting.setChangetype("火");
                changeFighting.setChangesum(1);
                FightingPackage.ChangeProcess(changeFighting, null, data, fightingState, "火", Accepterlist, battlefield);
                fightingState.setSkillskin("1059");
            }
            FightingSkill TY_L_10124 = manData.getSkillType(TypeUtil.TY_L_10124);
            if (TY_L_10124 != null && Battlefield.isV(TY_L_10124.getSkillhurt())) {
                AddState addState = data.xzstate("免疫物理");
                if (addState != null) {
                    data.getAddStates().remove(addState);
                }
            }
        }
        if (events.getOriginator() != null) {
            Accepterlist.add(Originator2);
        }
        events.setOriginator(null);
        if (Accepterlist.size() != 0) {
            events.setAccepterlist(Accepterlist);
            battlefield.NewEvents.add(events);
        }
    }
}
