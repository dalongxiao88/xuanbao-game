package come.tool.FightingSpellAction;

import java.util.List;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.SummonType;
import come.tool.FightingData.MixDeal;
import java.util.ArrayList;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.PK_MixDeal;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public class TY_HS_SSXXAction implements SpellAction
{
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        if (!PK_MixDeal.isPK(battlefield.BattleType)) {
            return;
        }
        List<FightingSkill> skills = ControlAction.getSkills(manData, skill, battlefield.BattleType);
        skills = ControlAction.addSkill(manData.getSkillType(TypeUtil.TY_HS_WXMS), skills);
        List<FightingState> Accepterlist = new ArrayList<>();
        List<ManData> datas = MixDeal.getjieshou(events, skill, manData, Accepterlist, battlefield);
        FightingState Originator = events.getOriginator();
        events.setOriginator(null);
        SummonType.xianzhi(manData, skill);
        if (manData.daijia(skill, Originator, battlefield)) {
            return;
        }
        ChangeFighting changeFighting = new ChangeFighting();
        changeFighting.setChangetype("昏睡");
        changeFighting.setChangesum(5);
        FightingPackage.ChangeProcess(changeFighting, null, manData, Originator, "昏睡", Accepterlist, battlefield);
        Originator.setStartState("法术攻击");
        if (datas.size() != 0) {
            ManData data = (ManData)datas.get(0);
            if (Battlefield.isV(skill.getSkillhurt())) {
                FightingState fightingState = new FightingState();
                ChangeFighting fighting = new ChangeFighting();
                fighting.setChangetype("昏睡");
                fighting.setChangesum(5);
                fighting.setSkills(skills);
                FightingPackage.ChangeProcess(fighting, null, data, fightingState, "昏睡", Accepterlist, battlefield);
            }
        }
        events.setAccepterlist(Accepterlist);
        battlefield.NewEvents.add(events);
    }
}
