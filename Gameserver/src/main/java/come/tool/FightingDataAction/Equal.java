package come.tool.FightingDataAction;

import java.util.List;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.ChangeFighting;
import java.util.ArrayList;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.AddState;
import come.tool.FightingData.PK_MixDeal;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public class Equal implements DataAction
{
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        FightingSkill skill = manData.skillname(fightingEvents.getOriginator().getEndState());
        if (skill == null) {
            return;
        }
        if (!PK_MixDeal.isPK(battlefield.BattleType)) {
            return;
        }
        AddState addState = new AddState();
        addState.setStatename("冷却");
        addState.setStateEffect(7012.0);
        addState.setSurplus(15);
        manData.getAddStates().add(addState);
        List<ManData> datas = MixDeal.get(false, null, 1, -1, 0, 0, 0, 0, 20, -1, battlefield, 1);
        for (int i = datas.size() - 1; i >= 0; --i) {
            if (((ManData)datas.get(i)).getType() >= 2) {
                datas.remove(i);
            }
        }
        int camp = manData.getCamp();
        int hpb = 0;
        int mpb = 0;
        for (int j = 0; j < datas.size(); ++j) {
            hpb += ((ManData)datas.get(j)).getHp_z();
            mpb += ((ManData)datas.get(j)).getMp_z();
        }
        hpb /= datas.size();
        mpb /= datas.size();
        fightingEvents.setAccepterlist(null);
        fightingEvents.setOriginator(null);
        List<FightingState> acclist = new ArrayList<>();
        ChangeFighting fighting = new ChangeFighting();
        for (int k = 0; k < datas.size(); ++k) {
            ManData data = (ManData)datas.get(k);
            int hpv = hpb;
            int mpv = mpb;
            if (data.getCamp() == camp) {
                hpv = (int)((double)hpv * skill.getSkillgain() / 100.0);
                mpv = (int)((double)mpv * skill.getSkillgain() / 100.0);
            }
            else {
                hpv = (int)((double)hpv * skill.getSkillhurt() / 100.0);
                mpv = (int)((double)mpv * skill.getSkillhurt() / 100.0);
            }
            hpv -= data.getHp();
            mpv -= data.getMp();
            fighting.setChangehp(hpv);
            fighting.setChangemp(mpv);
            FightingState Fman = new FightingState();
            Fman.setSkillskin(skill.getSkillid() + "");
            data.ChangeData(fighting, Fman);
            acclist.add(Fman);
        }
        fightingEvents.setAccepterlist(acclist);
        battlefield.NewEvents.add(fightingEvents);
    }
}
