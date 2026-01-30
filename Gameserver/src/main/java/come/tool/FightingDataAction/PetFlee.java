package come.tool.FightingDataAction;

import java.util.List;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.FightingState;
import java.util.ArrayList;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public class PetFlee implements DataAction
{
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        FightingSkill fightingSkill = manData.getSkillType("作鸟兽散");
        if (fightingSkill == null) {
            return;
        }
        FightingEvents qz = new FightingEvents();
        List<FightingState> dStates = new ArrayList<>();
        FightingState daijia = new FightingState();
        ChangeFighting changeFighting = new ChangeFighting();
        changeFighting.setChangetype("清除状态");
        manData.ChangeData(changeFighting, daijia);
        daijia.setStartState("代价");
        dStates.add(daijia);
        qz.setAccepterlist(dStates);
        battlefield.NewEvents.add(qz);
        FightingEvents hr = new FightingEvents();
        List<FightingState> hrac = new ArrayList<>();
        List<ManData> datas = MixDeal.getjieshou(hr, fightingSkill, manData, null, battlefield);
        for (int i = 0; i < datas.size(); ++i) {
            FightingState ac = new FightingState();
            changeFighting.setChangehp((int)((double)((ManData)datas.get(i)).getHp_z() * 0.5));
            changeFighting.setChangemp((int)((double)((ManData)datas.get(i)).getMp_z() * 0.5));
            ((ManData)datas.get(i)).ChangeData(changeFighting, ac);
            ac.setSkillskin(fightingSkill.getSkillid() + "");
            ac.setStartState(TypeUtil.JN);
            hrac.add(ac);
        }
        FightingState acs = new FightingState();
        acs.setCamp(manData.getCamp());
        acs.setMan(manData.getMan());
        acs.setStartState("法术攻击");
        hrac.add(acs);
        hr.setAccepterlist(hrac);
        battlefield.NewEvents.add(hr);
        DataActionType.getActionById(5).analysisAction(manData, null, "召回", battlefield);
        MixDeal.Appearance(manData, battlefield);
    }
}
