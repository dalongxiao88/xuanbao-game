package come.tool.FightingDataAction;

import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.ChangeFighting;
import java.util.Iterator;
import java.util.List;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.SummonType;
import come.tool.FightingData.AddState;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.MixDeal;
import java.util.ArrayList;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.PK_MixDeal;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public class Spell implements DataAction
{
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        FightingSkill fightingSkill = manData.skillname(fightingEvents.getOriginator().getEndState());
        if (fightingSkill == null) {
            return;
        }
        if (fightingSkill.getSkillid() == 1839 && !PK_MixDeal.isPK(battlefield.BattleType)) {
            return;
        }
        String skilltype = fightingSkill.getSkilltype();
        if (skilltype.equals("魔界内丹")) {
            DataActionType.getActionById(12).analysisAction(manData, fightingEvents, skilltype, battlefield);
            return;
        }
        if (skilltype.equals("一矢中的")) {
            DataActionType.getActionById(36).analysisAction(manData, fightingEvents, skilltype, battlefield);
            return;
        }
        if (skilltype.equals("兵临城下") || skilltype.equals("奋蹄扬威")) {
            DataActionType.getActionById(16).analysisAction(manData, fightingEvents, skilltype, battlefield);
            return;
        }
        if (skilltype.equals("神力加身") || skilltype.equals("力挽狂澜") || skilltype.equals("势如破竹")) {
            DataActionType.getActionById(37).analysisAction(manData, fightingEvents, skilltype, battlefield);
            return;
        }
        if (skilltype.equals("移花接木")) {
            DataActionType.getActionById(18).analysisAction(manData, fightingEvents, skilltype, battlefield);
            return;
        }
        if (skilltype.equals(TypeUtil.TZ_TGYJ)) {
            DataActionType.getActionById(26).analysisAction(manData, fightingEvents, skilltype, battlefield);
            return;
        }
        if (skilltype.equals(TypeUtil.TZ_YSTR)) {
            DataActionType.getActionById(27).analysisAction(manData, fightingEvents, skilltype, battlefield);
            return;
        }
        if (fightingSkill.getSkillid() >= 1287&&fightingSkill.getSkillid() <= 1289 ) {
            DataActionType.getActionById(44).analysisAction(manData, fightingEvents, skilltype, battlefield);
            return;
        }
        List<FightingState> Accepterlist = new ArrayList<>();
        List<ManData> datas = MixDeal.getjieshou(fightingEvents, fightingSkill, manData, Accepterlist, battlefield);
        if (skilltype.equals(TypeUtil.BB_E_YHSS) && Battlefield.isV(fightingSkill.getSkillgain())) {
            int path = battlefield.Datapathhuo(((FightingState)fightingEvents.getAccepterlist().get(0)).getCamp(), ((FightingState)fightingEvents.getAccepterlist().get(0)).getMan());
            ManData m = (ManData)battlefield.fightingdata.get(path);
            if (m != null && m.getCamp() != manData.getCamp()) {
                List<AddState> rlist = new ArrayList<>();
                for (int i = m.getAddStates().size() - 1; i >= 0; --i) {
                    if (((AddState)m.getAddStates().get(i)).getStatename().equals("加速") || ((AddState)m.getAddStates().get(i)).getStatename().equals("力量") || ((AddState)m.getAddStates().get(i)).getStatename().equals("抗性") || ((AddState)m.getAddStates().get(i)).getStatename().equals(TypeUtil.BB_SS) || ((AddState)m.getAddStates().get(i)).getStatename().equals("smmh")) {
                        rlist.add(m.getAddStates().get(i));
                    }
                }
                FightingState fightingState = new FightingState();
                fightingState.setCamp(m.getCamp());
                fightingState.setMan(m.getMan());
                for (AddState state : rlist) {
                    fightingState.setEndState_2(state.getStatename());
                    m.getAddStates().remove(state);
                    AddState xzstate = m.xzstate(state.getStatename());
                    if (xzstate == null) {
                        manData.getAddStates().add(state);
                        FightingState fightingState2 = new FightingState();
                        fightingState2.setCamp(manData.getCamp());
                        fightingState2.setMan(manData.getMan());
                        fightingState2.setEndState_1(state.getStatename());
                        Accepterlist.add(fightingState2);
                    }
                }
                Accepterlist.add(fightingState);
                datas.clear();
            }
            else if (skilltype.equals(TypeUtil.BB_E_YHSS)) {
                AddState addState = m.getControlState();
                if (addState != null) {
                    manData.getAddStates().add(addState);
                    FightingState fightingState3 = new FightingState();
                    fightingState3.setCamp(manData.getCamp());
                    fightingState3.setMan(manData.getMan());
                    fightingState3.setEndState_1(addState.getStatename());
                    FightingState fightingState4 = new FightingState();
                    fightingState4.setMan(m.getMan());
                    fightingState4.setCamp(m.getCamp());
                    fightingState4.setEndState_2(addState.getStatename());
                    m.getAddStates().remove(addState);
                    Accepterlist.add(fightingState3);
                    Accepterlist.add(fightingState4);
                }
                datas.clear();
            }
        }
        if (datas.size() == 0) {
            FightingState Originator = fightingEvents.getOriginator();
            if (manData.daijia(fightingSkill, Originator, battlefield)) {
                return;
            }
            Originator.setStartState("法术攻击");
            Originator.setSkillsy(fightingSkill.getSkillname());
            fightingEvents.setOriginator(null);
            Accepterlist.add(Originator);
            fightingEvents.setAccepterlist(Accepterlist);
            battlefield.NewEvents.add(fightingEvents);
            return;
        }
        else {
            FightingSkill bb_e = null;
            if (skilltype.equals("减速")) {
                bb_e = manData.getSkillType(TypeUtil.BB_E_QHJianS);
            }
            else if (skilltype.equals("庇护")) {
                bb_e = manData.getSkillType(TypeUtil.BB_E_QHZBTX);
            }else if (skilltype.equals("流连忘返")) {
                bb_e=manData.getSkillType("灯火阑珊");
            }
            int consume = 0;
            AddState addState = manData.xzstate("10054");
            if (addState != null) {
                battlefield.systemMsg(manData, null, 1, null);
                return;
            }
            ManData parent = battlefield.getPetParents(manData);
            if (parent != null) {
                FightingSkill parskill = parent.getSkillType("10056");
                if (parskill != null) {
                    consume = (int)parskill.getSkillhurt();
                }
            }
            FightingState Originator2 = fightingEvents.getOriginator();
            if (bb_e != null) {
                Originator2.setText(bb_e.getSkillname() + "#2");
            }
            int state2 = manData.getStates();
            if (manData.daijia(fightingSkill, Originator2, battlefield)) {
                return;
            }
            if (state2 == 0 && state2 != manData.getStates()) {
                state2 = 1;
            }
            else {
                state2 = 0;
            }
            LOOP: {
                if (fightingSkill.getSkillid() == 1607) {
                    int j = 0;
                    while (j < datas.size()) {
                        if (((ManData)datas.get(j)).getCamp() == manData.getCamp() && manData.getMan() == ((ManData)datas.get(j)).getMan()) {
                            break LOOP;
                        }
                        else {
                            ++j;
                        }
                    }
                    while (datas.size() > 1) {
                        datas.remove(1);
                    }
                    datas.add(manData);
                }
            }
            Originator2.setStartState("法术攻击");
            Originator2.setSkillsy(fightingSkill.getSkillname());
            int zyff = 0;
            if (skilltype.equals("五行") && Battlefield.isV((double)manData.executeZyff(1))) {
                zyff = manData.executeZyff(2);
            }
            int tempkx = 0;
            if (fightingSkill.getSkillid() == 1608 || fightingSkill.getSkillid() == 1612) {
                tempkx = manData.executeCsmy(Accepterlist);
            }
            for (int k = 0; k < datas.size(); ++k) {
                ManData data = (ManData)datas.get(k);
                FightingState Accepter = new FightingState();
                if (skilltype.equals("减人仙") || skilltype.equals("减魔鬼") || skilltype.equals(TypeUtil.BB_SS) || skilltype.equals(TypeUtil.BB_E_YHSS) || skilltype.equals(TypeUtil.BB_DHSM)) {
                    getBBNoHurt(Accepterlist, (data.getCamp() == manData.getCamp() && manData.getMan() == data.getMan()) ? Originator2 : Accepter, manData, data, fightingSkill, skilltype, battlefield);
                }
                else if (skilltype.equals("减速") || skilltype.equals("庇护") || skilltype.equals(TypeUtil.TZ_CXYF) || skilltype.equals(TypeUtil.TZ_FHJY) || skilltype.equals(TypeUtil.TZ_HGFZ) || skilltype.equals(TypeUtil.TZ_HJBF) || skilltype.equals(TypeUtil.TZ_XSJS) || skilltype.equals(TypeUtil.TZ_YSHY) || skilltype.equals("23004") || skilltype.equals("流连忘返")) {//增益判断
                    getmostate(Accepterlist, (data.getCamp() == manData.getCamp() && manData.getMan() == data.getMan()) ? Originator2 : Accepter, manData, data, fightingSkill, skilltype, battlefield, bb_e);
                }
                else if (skilltype.equals("五行") || skilltype.equals("隐身技") || skilltype.equals("解除控制")) {
                    SummonType.Fiveelements(Accepterlist, (data.getCamp() == manData.getCamp() && manData.getMan() == data.getMan()) ? Originator2 : Accepter, manData, data, fightingSkill, skilltype, battlefield);
                    if (data.getSp() > manData.getSp()) {
                        ManData removeMandata = null;
                        for (ManData item : battlefield.getWaitList()) {
                            if (item.getManname().equals(data.getManname())) {
                                removeMandata = item;
                                break;
                            }
                        }
                        battlefield.getWaitList().remove(removeMandata);
                    }
                    if (tempkx > 0) {
                        data.bhws = (double)tempkx;
                    }
                    if (zyff > 0) {
                        data.addAddState("紫燕翻飞", (double)zyff, 0.0, 1);
                        Accepter.setEndState_1("紫燕翻飞");
                    }
                }
                else if (skilltype.equals("回血技")) {
                    SummonType.Disposable(Accepterlist, (data.getCamp() == manData.getCamp() && manData.getMan() == data.getMan()) ? Originator2 : Accepter, manData, data, fightingSkill, skilltype, battlefield);
                }
                else if (skilltype.equals(TypeUtil.TZ_BDBQ) || skilltype.equals(TypeUtil.TZ_MXJX)) {
                    getTZState(Accepterlist, (data.getCamp() == manData.getCamp() && manData.getMan() == data.getMan()) ? Originator2 : Accepter, manData, data, fightingSkill, skilltype, battlefield);
                }
                else if (skilltype.equals(TypeUtil.TZ_HJYL) || skilltype.equals(TypeUtil.TZ_JLZT)) {
                    getTZ1(Accepterlist, (data.getCamp() == manData.getCamp() && manData.getMan() == data.getMan()) ? Originator2 : Accepter, manData, data, fightingSkill, skilltype, battlefield);
                }
                else if (skilltype.equals(TypeUtil.TZ_YYJH)) {
                    getTZ2(Accepterlist, (data.getCamp() == manData.getCamp() && manData.getMan() == data.getMan()) ? Originator2 : Accepter, manData, data, fightingSkill, skilltype, battlefield);
                }
                else if (skilltype.equals(TypeUtil.TZ_PFCZ)) {
                    getTZ3(Accepterlist, (data.getCamp() == manData.getCamp() && manData.getMan() == data.getMan()) ? Originator2 : Accepter, manData, data, fightingSkill, skilltype, battlefield);
                }
                else if (skilltype.equals(TypeUtil.TJ_LJXS)) {
                    getTJ1(Accepterlist, (data.getCamp() == manData.getCamp() && manData.getMan() == data.getMan()) ? Originator2 : Accepter, manData, data, fightingSkill, skilltype, battlefield);
                }
                else if (skilltype.equals(TypeUtil.BB_CNHK)) {
                    BB_CNHK(Accepterlist, (data.getCamp() == manData.getCamp() && manData.getMan() == data.getMan()) ? Originator2 : Accepter, manData, data, fightingSkill, skilltype, battlefield);
                }
                Accepter.setStartState(TypeUtil.JN);
                if (data.getCamp() == manData.getCamp() && manData.getMan() == data.getMan()) {
                    Originator2.setStartState("法术攻击");
                    fightingEvents.setOriginator(null);
                    if (fightingSkill.getSkillid() != 1607 && fightingSkill.getSkillid() != 1877) {
                        Originator2.setSkillskin(fightingSkill.getSkillid() + "");
                    }
                }
                else if (fightingSkill.getSkillid() != 1607 && fightingSkill.getSkillid() != 1877) {
                    Accepter.setSkillskin(fightingSkill.getSkillid() + "");
                }
                if (manData.getSkillType("8074") != null) {
                    addState = data.xzstate("8074");
                    if (data.getStates() == 1 && addState == null) {
                        getTZ4(Accepterlist, Accepter, manData, data, fightingSkill, skilltype, battlefield);
                    }
                }
            }
            if (fightingEvents.getOriginator() != null) {
                Accepterlist.add(Originator2);
            }
            fightingEvents.setOriginator(null);
            if (Accepterlist.size() != 0) {
                fightingEvents.setAccepterlist(Accepterlist);
                battlefield.NewEvents.add(fightingEvents);
            }
            if (state2 == 1) {
                MixDeal.DeathSkill(manData, Originator2, battlefield);
            }
            return;
        }
    }
    
    public static void getTZ4(List<FightingState> Accepterlist, FightingState fightingState, ManData manData, ManData nomyData, FightingSkill fightingSkill, String skilltype, Battlefield battlefield) {
        ChangeFighting changeFighting = new ChangeFighting();
        changeFighting.setChangetype("8074");
        changeFighting.setChangesum(3);
        changeFighting.setChangevlaue(8.0);
        nomyData.ChangeData(changeFighting, fightingState);
    }
    
    public static void getBBNoHurt(List<FightingState> Accepterlist, FightingState fightingState, ManData manData, ManData nomyData, FightingSkill fightingSkill, String skilltype, Battlefield battlefield) {
        nomyData.addBear(skilltype);
        ChangeFighting changeFighting = battlefield.BBNoHurt(manData, nomyData, skilltype, fightingSkill);
        FightingPackage.ChangeProcess(changeFighting, null, nomyData, fightingState, skilltype, Accepterlist, battlefield);
    }
    
    public static void getmostate(List<FightingState> Accepterlist, FightingState fightingState, ManData manData, ManData nomyData, FightingSkill fightingSkill, String skilltype, Battlefield battlefield, FightingSkill bb_e) {
        ChangeFighting changeFighting = battlefield.TypeGain(manData, skilltype, fightingSkill);
        if (bb_e != null) {
            if (bb_e.getSkilltype().equals(TypeUtil.BB_E_QHZBTX)) {
                changeFighting.setChangevlaue2(changeFighting.getChangevlaue2() + bb_e.getSkillgain());
                changeFighting.setSkill(bb_e);
            }else if (bb_e.getSkilltype().equals("灯火阑珊")) {
                changeFighting.setChangevlaue2(changeFighting.getChangevlaue2()+bb_e.getSkillhurt());
            }
            else {
                changeFighting.setChangevlaue(changeFighting.getChangevlaue() + bb_e.getSkillgain());
            }
        }
        nomyData.ChangeData(changeFighting, fightingState);
        Accepterlist.add(fightingState);
    }
    
    public static void getTZState(List<FightingState> Accepterlist, FightingState fightingState, ManData manData, ManData nomyData, FightingSkill fightingSkill, String skilltype, Battlefield battlefield) {
        ChangeFighting changeFighting = new ChangeFighting();
        changeFighting.setChangetype(skilltype);
        if (fightingSkill.getSkillhurt() > (double)Battlefield.random.nextInt(100)) {
            changeFighting.setChangesum(fightingSkill.getSkillcontinued() + 1);
        }
        else {
            changeFighting.setChangesum(fightingSkill.getSkillcontinued());
        }
        nomyData.ChangeData(changeFighting, fightingState);
        Accepterlist.add(fightingState);
    }
    
    public static void getTZ1(List<FightingState> Accepterlist, FightingState fightingState, ManData manData, ManData nomyData, FightingSkill fightingSkill, String skilltype, Battlefield battlefield) {
        ChangeFighting changeFighting = new ChangeFighting();
        if (skilltype.equals(TypeUtil.TZ_HJYL)) {
            int hurt = (int)(-(nomyData.getShanghai() * (fightingSkill.getSkillhitrate() + nomyData.getShanghai() * fightingSkill.getSkillhurt()) / 2.0));
            changeFighting.setChangemp(hurt);
            changeFighting.setChangehp((int)((double)changeFighting.getChangemp() * 0.6));
        }
        else {
            int hurt = (int)(-(nomyData.getHuoyue() * (fightingSkill.getSkillhitrate() + nomyData.getHuoyue() * fightingSkill.getSkillhurt()) / 2.0));
            changeFighting.setChangehp(hurt);
            changeFighting.setChangemp((int)((double)changeFighting.getChangehp() * 0.6));
        }
        FightingPackage.ChangeProcess(changeFighting, manData, nomyData, fightingState, skilltype, Accepterlist, battlefield);
    }
    
    public static void getTZ2(List<FightingState> Accepterlist, FightingState fightingState, ManData manData, ManData nomyData, FightingSkill fightingSkill, String skilltype, Battlefield battlefield) {
        ChangeFighting changeFighting = new ChangeFighting();
        changeFighting.setChangehp((int)fightingSkill.getSkillhurt());
        changeFighting.setChangemp((int)fightingSkill.getSkillhurt());
        nomyData.ChangeData(changeFighting, fightingState);
        Accepterlist.add(fightingState);
        List<ManData> datas = battlefield.getZW(nomyData);
        for (int i = 0; i < datas.size(); ++i) {
            ManData data = (ManData)datas.get(i);
            FightingState fdata = new FightingState();
            data.ChangeData(changeFighting, fdata);
            fdata.setStartState("药");
            Accepterlist.add(fdata);
        }
    }
    
    public static void getTZ3(List<FightingState> Accepterlist, FightingState fightingState, ManData manData, ManData nomyData, FightingSkill fightingSkill, String skilltype, Battlefield battlefield) {
        ChangeFighting changeFighting = new ChangeFighting();
        int hurt = (int)(0.8 * (double)nomyData.getHp());
        changeFighting.setChangehp(-hurt);
        changeFighting.setChangetype(skilltype);
        changeFighting.setChangevlaue((double)hurt * fightingSkill.getSkillhurt() / 100.0);
        changeFighting.setChangesum(fightingSkill.getSkillcontinued());
        nomyData.ChangeData(changeFighting, fightingState);
    }
    
    public static void getTJ1(List<FightingState> Accepterlist, FightingState fightingState, ManData manData, ManData nomyData, FightingSkill fightingSkill, String skilltype, Battlefield battlefield) {
        fightingState.setCamp(nomyData.getCamp());
        fightingState.setMan(nomyData.getMan());
        nomyData.addyq(-300, fightingState);
        Accepterlist.add(fightingState);
    }
    
    public static void BB_CNHK(List<FightingState> Accepterlist, FightingState fightingState, ManData manData, ManData nomyData, FightingSkill fightingSkill, String skilltype, Battlefield battlefield) {
        fightingState.setCamp(nomyData.getCamp());
        fightingState.setMan(nomyData.getMan());
        nomyData.RemoveAbnormal(new String[] { "冷却" });
        Accepterlist.add(fightingState);
    }
}
