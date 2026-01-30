package come.tool.FightingDataAction;

import java.util.List;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.ChangeFighting;
import java.util.ArrayList;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.AddState;
import come.tool.FightingData.FightingManData;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public class LingBaoZhZh implements DataAction
{
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        if (manData == null) {
            return;
        }
        int camp = manData.getCamp();
        int man = manData.getMan();
        if (type.equals("召回")) {
            this.zhaohui(manData, battlefield);
        }
        else {
            int path = battlefield.Datapathhuo(camp, man);
            if (path != -1) {
                ManData data = (ManData)battlefield.fightingdata.get(path);
                this.zhaohui(data, battlefield);
            }
            battlefield.AddLingbao(manData, manData.getCamp(), manData.getMan());
            this.zhaohuan(manData, type, battlefield);
        }
    }
    
    public void zhaohuan(ManData manData, String type, Battlefield battlefield) {
        FightingEvents fightingEvents = new FightingEvents();
        FightingState fightingState = new FightingState();
        fightingState.setStartState(type);
        fightingState.setCamp(manData.getCamp());
        fightingState.setMan(manData.getMan());
        manData.setStates(0);
        FightingManData fightingManData = new FightingManData();
        fightingManData.setModel(manData.getModel());
        fightingManData.setManname(manData.getManname());
        fightingManData.setCamp(manData.getCamp());
        fightingManData.setMan(manData.getMan());
        fightingManData.setHp_Current((long)manData.getHp());
        fightingManData.setHp_Total((long)manData.getHp_z());
        fightingManData.setMp_Current(manData.getMp());
        fightingManData.setMp_Total(manData.getMp_z());
        fightingManData.setState_1(manData.xz());
        fightingManData.setType(manData.getType());
        fightingManData.setManname(manData.getManname());
        fightingManData.setZs(manData.getZs());
        fightingManData.setMsg(manData.getMsg());
        fightingManData.setYqz(manData.getYqz());
        fightingManData.setNqz(manData.getNqz());
        fightingManData.setStates(manData.ztstlist(fightingManData));
        fightingManData.setId(manData.getId());
        if (manData.getSkillType("隐身") != null) {
            fightingState.setEndState_1("隐身");
            AddState addState = new AddState();
            addState.setStatename("隐身");
            addState.setSurplus(3);
            manData.getAddStates().add(addState);
            fightingManData.setAlpha(0.3f);
        }
        fightingState.setFightingManData(fightingManData);
        fightingEvents.setOriginator(fightingState);
        MixDeal.Approach(manData, fightingState, battlefield);
        battlefield.NewEvents.add(fightingEvents);
        FightingSkill skill = manData.getSkillType(TypeUtil.BB_XFDG);
        if (skill != null) {
            List<ManData> datas = MixDeal.get(false, null, 0, battlefield.nomy(manData.getCamp()), 1, 0, 1, 0, 1, 1, battlefield, 1);
            if (datas.size() == 0) {
                return;
            }
            ManData data = (ManData)datas.get(0);
            FightingEvents fe2 = new FightingEvents();
            FightingState fs2 = new FightingState();
            fs2.setStartState(TypeUtil.JN);
            fs2.setSkillskin(skill.getSkilltype());
            List<FightingState> ac2 = new ArrayList<>();
            ChangeFighting fighting = new ChangeFighting();
            fighting.setChangehp((int)((double)data.getHp_z() * 0.7));
            fighting.setChangemp((int)((double)data.getMp_z() * 0.1));
            FightingPackage.ChangeProcess(fighting, null, data, fs2, TypeUtil.JN, ac2, battlefield);
            fe2.setAccepterlist(ac2);
            battlefield.NewEvents.add(fe2);
        }
        skill = manData.getSkillType(TypeUtil.BB_MSRX);
        if (skill != null) {
            List<ManData> datas = MixDeal.get(false, null, 0, battlefield.nomy(manData.getCamp()), 1, 0, 1, 0, 1, 3, battlefield, 1);
            if (datas.size() == 0) {
                return;
            }
            ManData data = (ManData)datas.get(0);
            FightingEvents fe2 = new FightingEvents();
            FightingState fs2 = new FightingState();
            fs2.setStartState(TypeUtil.JN);
            fs2.setSkillskin(skill.getSkilltype());
            List<FightingState> ac2 = new ArrayList<>();
            ChangeFighting fighting = new ChangeFighting();
            fighting.setChangehp((int)((double)data.getHp_z() * 0.1));
            fighting.setChangemp((int)((double)data.getMp_z() * 0.7));
            FightingPackage.ChangeProcess(fighting, null, data, fs2, TypeUtil.JN, ac2, battlefield);
            fe2.setAccepterlist(ac2);
            battlefield.NewEvents.add(fe2);
        }
    }
    
    public void zhaohui(ManData manData, Battlefield battlefield) {
        FightingEvents fightingEvents = new FightingEvents();
        FightingState fightingState = new FightingState();
        fightingState.setStartState("召回");
        fightingState.setCamp(manData.getCamp());
        fightingState.setMan(manData.getMan());
        manData.setStates(2);
        fightingEvents.setOriginator(fightingState);
        battlefield.NewEvents.add(fightingEvents);
        MixDeal.zhaohui(manData, fightingState, battlefield);
    }
}
