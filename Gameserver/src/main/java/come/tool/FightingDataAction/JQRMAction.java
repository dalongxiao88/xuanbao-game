package come.tool.FightingDataAction;

import java.util.List;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.MixDeal;
import java.util.ArrayList;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.Calculation;
import come.tool.FightingData.PK_MixDeal;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;
import come.tool.FightingSpellAction.SpellAction;

public class JQRMAction implements SpellAction
{
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        if (!PK_MixDeal.isPK(battlefield.BattleType)) {
            return;
        }
        FightingSkill skill2 = manData.skillId(1080);
        String type = skill.getSkilltype();
        double xs = Calculation.getCalculation().mofa(skill2.getSkillgain(), manData, type);
        ChangeFighting changeFighting = new ChangeFighting();
        FightingEvents hr = new FightingEvents();
        List<FightingState> hrac = new ArrayList<>();
        List<ManData> datas = MixDeal.getjieshou(hr, skill2, manData, null, battlefield);
        for (int i = 0; i < datas.size(); ++i) {
            ManData data = (ManData)datas.get(i);
            FightingSkill TY_G_10083 = data.getSkillType(TypeUtil.TY_G_10083);
            int hh;
            if (TY_G_10083 != null && Battlefield.isV(TY_G_10083.getSkillhurt())) {
                hh = 3;
            }
            else {
                hh = 2;
            }
            data.addAddState(TypeUtil.TY_G_10080, skill.getSkillhurt(), 0.0, hh);
            FightingState ac = new FightingState();
            changeFighting.setChangevlaue2(xs / 2.0);
            changeFighting.setChangetype(TypeUtil.MH);
            changeFighting.setChangesum(hh);
            ((ManData)datas.get(i)).ChangeData(changeFighting, ac);
            ac.setSkillskin("1080");
            ac.setStartState(TypeUtil.JN);
            hrac.add(ac);
            if (data.getType() == 0) {
                ChangeFighting change = new ChangeFighting();
                change.setChangemp(-(int)skill.getSkillhurt());
                FightingState org3 = new FightingState();
                org3.setCamp(manData.getCamp());
                org3.setMan(manData.getMan());
                FightingPackage.ChangeProcess(change, data, manData, org3, TypeUtil.JN, hrac, battlefield);
            }
        }
        FightingState acs = new FightingState();
        acs.setCamp(manData.getCamp());
        acs.setMan(manData.getMan());
        acs.setStartState("法术攻击");
        hrac.add(acs);
        hr.setAccepterlist(hrac);
        battlefield.NewEvents.add(hr);
        manData.getSkills().remove(skill);
    }
}
