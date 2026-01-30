package come.tool.FightingDataAction;

import java.util.List;
import java.util.Iterator;
import come.tool.FightingData.SummonType;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.PK_MixDeal;
import java.util.ArrayList;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.AddState;
import come.tool.FightingData.FightingManData;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.FightingState;
import org.come.until.GsonUtil;
import java.math.BigDecimal;
import come.tool.Stall.AssetUpdate;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public class PetZhZh implements DataAction
{
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        if (manData == null) {
            return;
        }
        int camp = manData.getCamp();
        int man = manData.getMan();
        int bbType = type.startsWith("召唤灵宝") ? 1 : 0;
        try {
            if (type.equals("召回")) {
                this.zhaohui(manData, battlefield);
            }
            else {
                int path = battlefield.Datapathhuo(camp, man);
                int rolePath = battlefield.Datapath(camp, (bbType == 0) ? (man - 5) : (man - 10));
                if (path != -1) {
                    ManData data = (ManData)battlefield.fightingdata.get(path);
                    if (manData.getId() == data.getId()) {
                        SendMessage.sendMessageByRoleName(((ManData)battlefield.fightingdata.get(rolePath)).getManname(), Agreement.getAgreement().PromptAgreement("这只" + (type.equals("召唤") ? "召唤兽" : "灵宝") + "已经上场！"));
                        return;
                    }
                    this.zhaohui(data, battlefield);
                }
                if (manData.getStates() == 2) {
                    SendMessage.sendMessageByRoleName(((ManData)battlefield.fightingdata.get(rolePath)).getManname(), Agreement.getAgreement().PromptAgreement("这只召唤兽已经上过场了！"));
                    return;
                }
                if ((bbType == 0 && battlefield.Addbb(manData, manData.getCamp(), manData.getMan())) || (bbType == 1 && battlefield.AddLB(manData, manData.getCamp(), manData.getMan()))) {
                    this.zhaohuan(manData, type, battlefield);
                    AssetUpdate assetUpdate = new AssetUpdate();
                    if (bbType == 0) {
                        assetUpdate.setSummoningId(BigDecimal.valueOf((long)manData.getId()));
                    }
                    else {
                        assetUpdate.setBaoId(BigDecimal.valueOf((long)manData.getId()));
                    }
                    SendMessage.sendMessageByRoleName(((ManData)battlefield.fightingdata.get(rolePath)).getManname(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void zhaohuan(ManData manData, String type, Battlefield battlefield) {
        FightingEvents fightingEvents = new FightingEvents();
        FightingState fightingState = new FightingState();
        if (!manData.jtcl && !manData.fhft) {
            manData.executeYzsg(null, fightingState);
            manData.clearAddStates();
        }
        if (manData.getType() == 1) {
            manData.getSkills().clear();
            for (FightingSkill fightingSkill : manData.getSkillsCopy()) {
                manData.addSkill(fightingSkill, battlefield.CurrentRound);
            }
            if (manData.jtcl) {
                manData.loadLingXiSkill();
                fightingState.setText("看我的卷土重来");
            }
            else if (manData.fhft) {
                manData.loadLingXiSkill();
            }
        }
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
        fightingManData.setSpeciesid(manData.getSpeciesid());
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
        if (manData.fhft) {
            manData.fhft = false;
        }
        int my = 0;
        int it = 0;
        for (ManData data : battlefield.fightingdata) {
            if (data.getStates() == 0 && data.getType() < 3) {
                if (data.getCamp() == manData.getCamp()) {
                    ++my;
                }
                else {
                    ++it;
                }
            }
        }
        if (my < it) {
            manData.executeYztc(null, fightingState);
        }
        fightingState.setFightingManData(fightingManData);
        fightingEvents.setOriginator(fightingState);
        MixDeal.Approach(manData, fightingState, battlefield);
        battlefield.NewEvents.add(fightingEvents);
        if (Battlefield.isV((double)manData.executeBfbd(1))) {
            int wei = battlefield.Datapathhuo(manData.getCamp(), manData.getMan() - 5);
            if (wei != -1) {
                ManData master = (ManData)battlefield.fightingdata.get(wei);
                int kx = manData.executeBfbd(2);
                int i = Battlefield.random.nextInt(12) + 1;
                switch (i) {
                    case 1: {
                        master.getQuality().setRolekwl(master.getQuality().getRolekwl() + (double)kx);
                        break;
                    }
                    case 2: {
                        master.getQuality().setRolekzs(master.getQuality().getRolekzs() + (double)kx);
                        break;
                    }
                    case 3: {
                        master.getQuality().setRolekff(master.getQuality().getRolekff() + (double)kx);
                        break;
                    }
                    case 4: {
                        master.getQuality().setRoleklf(master.getQuality().getRoleklf() + (double)kx);
                        break;
                    }
                    case 5: {
                        master.getQuality().setRoleksf(master.getQuality().getRoleksf() + (double)kx);
                        break;
                    }
                    case 6: {
                        master.getQuality().setRolekhf(master.getQuality().getRolekhf() + (double)kx);
                        break;
                    }
                    case 7: {
                        master.getQuality().setRolekhl(master.getQuality().getRolekhl() + (double)kx);
                        break;
                    }
                    case 8: {
                        master.getQuality().setRolekhs(master.getQuality().getRolekhs() + (double)kx);
                        break;
                    }
                    case 9: {
                        master.getQuality().setRolekfy(master.getQuality().getRolekfy() + (double)kx);
                        break;
                    }
                    case 10: {
                        master.getQuality().setRolekzd(master.getQuality().getRolekzd() + (double)kx);
                        break;
                    }
                    case 11: {
                        master.getQuality().setRolekyw(master.getQuality().getRolekyw() + (double)kx);
                        break;
                    }
                    default: {
                        master.getQuality().setRolekgh(master.getQuality().getRolekgh() + (double)kx);
                        break;
                    }
                }
                List<FightingState> zls = new ArrayList<>();
                FightingState ace = new FightingState();
                ace.setCamp(manData.getCamp());
                ace.setMan(manData.getMan());
                ace.setText("看我的八风不动");
                zls.add(ace);
                FightingEvents event = new FightingEvents();
                event.setAccepterlist(zls);
                battlefield.NewEvents.add(event);
            }
        }
        FightingSkill cjdw = manData.getSkillId(1887);
        if (cjdw != null && PK_MixDeal.isPK(battlefield.BattleType)) {
            FightingEvents fe2 = new FightingEvents();
            List<FightingState> ac2 = new ArrayList<>();
            List<ManData> datas = MixDeal.get(false, null, 0, battlefield.nomy(manData.getCamp()), 0, 4, 1, 0, 1, 1, battlefield, 1);
            ManData parents = null;
            if (datas.size() > 0) {
                parents = (ManData)datas.get(0);
            }
            if (parents != null && parents.getStates() == 0) {
                FightingState Originator = new FightingState();
                if (Battlefield.isV(cjdw.getSkillgain())) {
                    parents.RemoveNegativeState(Originator);
                }
                FightingState fs2 = new FightingState();
                fs2.setStartState("法术攻击");
                fs2.setMan(manData.getMan());
                fs2.setCamp(manData.getCamp());
                FightingState fs3 = new FightingState();
                fs3.setStartState("代价");
                fs3.setSkillskin("1887");
                fs3.setMan(parents.getMan());
                fs3.setCamp(parents.getCamp());
                Originator.setStartState("代价");
                Originator.setCamp(parents.getCamp());
                Originator.setMan(parents.getMan());
                ChangeFighting fighting = new ChangeFighting();
                fighting.setChangehp((int)((double)parents.getHp_z() * 0.5));
                fighting.setChangemp((int)((double)parents.getMp_z() * 0.5));
                FightingPackage.ChangeProcess(fighting, null, parents, Originator, TypeUtil.JN, ac2, battlefield);
                ac2.add(fs2);
                ac2.add(fs3);
                fe2.setAccepterlist(ac2);
                battlefield.NewEvents.add(fe2);
            }
        }
        FightingSkill skill = manData.getSkillType(TypeUtil.BB_XFDG);
        if (skill != null) {
            List<ManData> datas2 = MixDeal.get(false, null, 0, battlefield.nomy(manData.getCamp()), 1, 0, 1, 0, 1, 1, battlefield, 1);
            if (datas2.size() == 0) {
                return;
            }
            ManData data2 = (ManData)datas2.get(0);
            FightingEvents fe3 = new FightingEvents();
            FightingState fs4 = new FightingState();
            fs4.setStartState(TypeUtil.JN);
            fs4.setSkillskin(skill.getSkilltype());
            List<FightingState> ac3 = new ArrayList<>();
            ChangeFighting fighting2 = new ChangeFighting();
            fighting2.setChangehp((int)((double)data2.getHp_z() * 0.7));
            fighting2.setChangemp((int)((double)data2.getMp_z() * 0.1));
            FightingPackage.ChangeProcess(fighting2, null, data2, fs4, TypeUtil.JN, ac3, battlefield);
            fe3.setAccepterlist(ac3);
            battlefield.NewEvents.add(fe3);
        }
        skill = manData.getSkillType(TypeUtil.BB_MSRX);
        if (skill != null) {
            List<ManData> datas2 = MixDeal.get(false, null, 0, battlefield.nomy(manData.getCamp()), 1, 0, 1, 0, 1, 3, battlefield, 1);
            if (datas2.size() == 0) {
                return;
            }
            ManData data2 = (ManData)datas2.get(0);
            FightingEvents fe3 = new FightingEvents();
            FightingState fs4 = new FightingState();
            fs4.setStartState(TypeUtil.JN);
            fs4.setSkillskin(skill.getSkilltype());
            List<FightingState> ac3 = new ArrayList<>();
            ChangeFighting fighting2 = new ChangeFighting();
            fighting2.setChangehp((int)((double)data2.getHp_z() * 0.1));
            fighting2.setChangemp((int)((double)data2.getMp_z() * 0.7));
            FightingPackage.ChangeProcess(fighting2, null, data2, fs4, TypeUtil.JN, ac3, battlefield);
            fe3.setAccepterlist(ac3);
            battlefield.NewEvents.add(fe3);
        }
        if (manData.getSkillType("1336") != null && Battlefield.isV(manData.getSkillType("1336").getSkillgain())) {
            FightingEvents fightingEvents2 = new FightingEvents();
            FightingState fightingState2 = new FightingState();
            fightingState2.setStartState("法术攻击");
            fightingState2.setSkillskin("1007");
            fightingState2.setEndState_1("封印");
            fightingState2.setText("强化封印#2");
            ChangeFighting changeFighting = new ChangeFighting();
            changeFighting.setChangetype("封印");
            changeFighting.setChangesum(1);
            changeFighting.setChangevlaue2(9999.0);
            manData.ChangeData(changeFighting, fightingState2);
            manData.setQHFY(true);
            List<FightingState> ac4 = new ArrayList<>();
            ac4.add(fightingState2);
            fightingEvents2.setAccepterlist(ac4);
            battlefield.NewEvents.add(fightingEvents2);
        }
        FightingSkill skill_4010 = manData.getSkillType("4010");
        AddState sh_4010 = manData.xzstate(TypeUtil.SH_4010);
        if (skill_4010 != null && sh_4010 == null && Battlefield.isV(skill_4010.getValue1())) {
            SummonType.xianzhi(manData, skill_4010);
            FightingEvents events2 = new FightingEvents();
            List<FightingState> Accepterlist2 = new ArrayList<>();
            FightingState state = new FightingState();
            ChangeFighting change = new ChangeFighting();
            change.setChangetype(TypeUtil.SH_4010);
            change.setChangevlaue(skill_4010.getValue2());
            change.setChangesum(3);
            manData.ChangeData(change, state);
            state.setStartState("代价");
            Accepterlist2.add(state);
            events2.setAccepterlist(Accepterlist2);
            FightingState fightingState3 = new FightingState();
            Accepterlist2.add(fightingState3);
            fightingState3.setCamp(manData.getCamp());
            fightingState3.setMan(manData.getMan());
            fightingState3.setStartState("代价");
            fightingState3.setProcessState("战气如虹");
            manData.addnq(10, fightingState3);
            fightingState3.setStartState(TypeUtil.JN);
            int[] yao = new int[4];
            yao[0] = (int)((double)manData.getZHP_Z() * skill_4010.getValue2());
            ChangeFighting changeFighting2 = battlefield.Typeyao(manData, yao);
            manData.ChangeData(changeFighting2, (FightingState)Accepterlist2.get(Accepterlist2.size() - 1));
            yao[0] = 0;
            battlefield.NewEvents.add(events2);
        }
        else if (skill_4010 != null) {
            manData.getSkills().remove(skill_4010);
        }
    }
    
    public void zhaohui(ManData manData, Battlefield battlefield) {
        manData.executeXzst(battlefield);
        manData.executeYcbb(battlefield);
        FightingEvents fightingEvents = new FightingEvents();
        FightingState fightingState = new FightingState();
        fightingState.setStartState("召回");
        fightingState.setCamp(manData.getCamp());
        fightingState.setMan(manData.getMan());
        manData.setStates(2);
        fightingEvents.setOriginator(fightingState);
        battlefield.NewEvents.add(fightingEvents);
        MixDeal.zhaohui(manData, fightingState, battlefield);
        FightingSkill skill = manData.skillname("碧荷凝露");
        if (skill != null) {
            int my = 0;
            for (ManData data : battlefield.fightingdata) {
                if (data.getStates() == 0 && data.getType() == 0 && data.getCamp() == manData.getCamp()) {
                    ++my;
                }
            }
            if (my <= 2) {
                MixDeal.first(manData, "碧荷凝露", battlefield);
            }
        }
    }
}
