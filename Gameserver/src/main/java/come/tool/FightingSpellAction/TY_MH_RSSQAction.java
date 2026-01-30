package come.tool.FightingSpellAction;

import java.util.List;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.MixDeal;
import java.util.ArrayList;
import come.tool.FightingData.SummonType;
import come.tool.FightingData.PK_MixDeal;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public class TY_MH_RSSQAction implements SpellAction
{
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        if (!PK_MixDeal.isPK(battlefield.BattleType)) {
            return;
        }
        SummonType.xianzhi(manData, skill);
        List<FightingState> Accepterlist = new ArrayList<>();
        List<ManData> datas = MixDeal.getjieshou(events, skill, manData, Accepterlist, battlefield);
        FightingState Originator = events.getOriginator();
        events.setOriginator(null);
        if (manData.daijia(skill, Originator, battlefield)) {
            return;
        }
        Originator.setStartState("法术攻击");
        if (datas.size() != 0) {
            ManData data = (ManData)datas.get(0);
            if (Battlefield.isV(skill.getSkillhurt())) {
                FightingState fightingState = new FightingState();
                ChangeFighting fighting = new ChangeFighting();
                fighting.setChangehp((int)((double)data.getHp_z() * skill.getSkillhurt() / 100.0));
                fighting.setChangetype(TypeUtil.TY_MH_RSSQ);
                fighting.setChangevlaue2((double)(int)((double)(data.getHp_z() + data.getMp_z()) * (skill.getSkillhurt() + 20.0) / 100.0));
                fighting.setChangesum(2);
                FightingPackage.ChangeProcess(fighting, null, data, fightingState, "昏睡", Accepterlist, battlefield);
            }
        }
        events.setAccepterlist(Accepterlist);
        battlefield.NewEvents.add(events);
    }
}
