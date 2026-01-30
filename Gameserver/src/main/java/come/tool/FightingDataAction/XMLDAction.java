package come.tool.FightingDataAction;

import java.util.List;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.FightingState;
import java.util.ArrayList;
import come.tool.FightingData.PK_MixDeal;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;
import come.tool.FightingSpellAction.SpellAction;

public class XMLDAction implements SpellAction
{
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        if (!PK_MixDeal.isPK(battlefield.BattleType)) {
            return;
        }
        FightingState Originator = events.getOriginator();
        if (manData.daijia(skill, Originator, battlefield)) {
            return;
        }
        FightingEvents qz = new FightingEvents();
        List<FightingState> dStates = new ArrayList<>();
        FightingState daijia = new FightingState();
        ChangeFighting changeFighting = new ChangeFighting();
        changeFighting.setChangetype("清除异常状态");
        manData.ChangeData(changeFighting, daijia);
        daijia.setStartState("代价");
        dStates.add(daijia);
        qz.setAccepterlist(dStates);
        battlefield.NewEvents.add(qz);
        FightingEvents hr = new FightingEvents();
        List<FightingState> hrac = new ArrayList<>();
        List<ManData> datas = MixDeal.getjieshou(hr, skill, manData, null, battlefield);
        for (int i = 0; i < datas.size(); ++i) {
            FightingSkill skill2 = ((ManData)datas.get(i)).getSkillType(TypeUtil.TY_R_BMJM);
            if (skill2 != null) {
                ((ManData)datas.get(i)).addAddState(TypeUtil.TY_R_BMJM, skill2.getSkillhurt(), 0.0, 1);
            }
            FightingState ac = new FightingState();
            ac.setSkillskin("1829");
            ac.setStartState(TypeUtil.JN);
            hrac.add(ac);
        }
    }
}
