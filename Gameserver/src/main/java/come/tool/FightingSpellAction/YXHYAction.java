package come.tool.FightingSpellAction;

import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.Sepcies_MixDeal;
import come.tool.FightingData.Calculation;
import come.tool.FightingData.ChangeFighting;
import java.util.Iterator;
import come.tool.FightingData.SummonType;
import come.tool.FightingData.AddState;
import come.tool.FightingData.FightingState;
import java.util.stream.Collectors;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import org.come.until.GsonUtil;
import come.tool.FightingData.MixDeal;
import java.util.ArrayList;
import come.tool.FightingData.PK_MixDeal;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public class YXHYAction implements SpellAction
{
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        if (!PK_MixDeal.isPK(battlefield.BattleType)) {
            return;
        }
        List<FightingState> Accepterlist = new ArrayList<>();
        skill.setSkillsum(1);
        List<ManData> datas = MixDeal.getjieshou(events, skill, manData, Accepterlist, battlefield, Boolean.valueOf(false), 3);
        if (datas.size() == 0) {
            return;
        }
        ManData noManData = (ManData)datas.get(0);
        AddState xzstate = noManData.xzstate("7027");
        if (xzstate != null) {
            return;
        }
        List<FightingSkill> noMySkills = noManData.getSkills();
        List<FightingSkill> skills = manData.getSkills();
        String noMyMes = GsonUtil.getGsonUtil().getgson().toJson(noMySkills);
        String myMes = GsonUtil.getGsonUtil().getgson().toJson(skills);
        List<FightingSkill> TmpMySkills = (List)GsonUtil.getGsonUtil().getgson().fromJson(myMes, new TypeToken<List<FightingSkill>>() {}.getType());
        List<FightingSkill> TmpnNoMySkills = (List)GsonUtil.getGsonUtil().getgson().fromJson(noMyMes, new TypeToken<List<FightingSkill>>() {}.getType());
        List<FightingSkill> mySmSkills = (List)skills.stream().filter(item/* come.tool.FightingData.FightingSkill, */ -> item.getSkillid() >= 1001 && item.getSkillid() <= 1100).collect(Collectors.toList());
        List<FightingSkill> noMySmSkills = (List)noMySkills.stream().filter(item/* come.tool.FightingData.FightingSkill, */ -> item.getSkillid() >= 1001 && item.getSkillid() <= 1100).collect(Collectors.toList());
        for (FightingSkill mySmSkill : mySmSkills) {
            manData.getSkills().remove(mySmSkill);
            noManData.getSkills().add(mySmSkill);
        }
        for (FightingSkill noMySmSkill : noMySmSkills) {
            noManData.getSkills().remove(noMySmSkill);
            manData.getSkills().add(noMySmSkill);
        }
        FightingEvents Events = new FightingEvents();
        FightingState Originator = new FightingState();
        Originator.setStartState("法术攻击");
        Originator.setCamp(manData.getCamp());
        Originator.setMan(manData.getMan());
        Accepterlist.add(Originator);
        AddState addState = new AddState();
        addState.setStatename("7027");
        addState.setType(1);
        addState.setStateEffect((double)noManData.getCamp());
        addState.setStateEffect2((double)noManData.getMan());
        addState.setSurplus(3);
        addState.setSkills(noMySmSkills);
        addState.setSkills2(mySmSkills);
        noManData.getAddStates().add(addState);
        FightingState Originator2 = new FightingState();
        Originator2.setStartState("被攻击");
        Originator2.setCamp(noManData.getCamp());
        Originator2.setMan(noManData.getMan());
        Originator2.setEndState_1("7027");
        Originator2.setSkillskin("7027");
        Accepterlist.add(Originator2);
        Events = new FightingEvents();
        List<FightingState> list = new ArrayList<>();
        addState = new AddState();
        addState.setStatename("7027");
        addState.setType(2);
        addState.setStateEffect((double)manData.getCamp());
        addState.setStateEffect2((double)manData.getMan());
        addState.setSurplus(3);
        addState.setSkills(mySmSkills);
        addState.setSkills2(noMySmSkills);
        manData.getAddStates().add(addState);
        FightingState Originator3 = new FightingState();
        Originator3.setStartState("代价");
        Originator3.setCamp(manData.getCamp());
        Originator3.setMan(manData.getMan());
        Originator3.setEndState_1("7027");
        Accepterlist.add(Originator3);
        Events.setAccepterlist(Accepterlist);
        battlefield.NewEvents.add(Events);
        SummonType.xianzhi(manData, skill);
    }
    
    public static int hurt(int hurt, double jc, double wg, double kbl, List<FightingSkill> skills, List<FightingState> list, FightingState fState, ManData manData, ManData data, FightingSkill skill, Battlefield battlefield) {
        int syhp = data.getHp();
        double kbxs = MixDeal.addition(kbl, fState, manData, data, skill.getSkilltype());
        if (kbxs != 0.0) {
            hurt = (int)((double)hurt * kbxs);
            wg *= kbxs;
        }
        ChangeFighting changeFighting = new ChangeFighting();
        if (skills != null) {
            for (int i = skills.size() - 1; i >= 0; --i) {
                FightingSkill skill2 = (FightingSkill)skills.get(i);
                int id = skill2.getSkillid();
                if (id == 9263) {
                    if (data.getType() == 1) {
                        jc *= 1.0 + skill2.getSkillhurt() / 100.0;
                    }
                }
                else if (id == 9267) {
                    if (kbxs != 0.0) {
                        AddState addState = data.getGainState();
                        if (addState != null) {
                            data.getAddStates().remove(addState);
                            fState.setEndState_2(addState.getStatename());
                        }
                    }
                }
                else if (id == 9269) {
                    if (kbxs != 0.0 && Battlefield.random.nextInt(4) == 0) {
                        manData.getQuality().setRolewxqkm(manData.getQuality().getRolewxqkm() + 100.0);
                        List<ManData> ksyss = battlefield.getZW(data);
                        ChangeFighting fighting = new ChangeFighting();
                        for (int k = ksyss.size() - 1; k >= 0; --k) {
                            int hurt2 = (int)(skill2.getSkillhurt() + (double)Battlefield.random.nextInt(10000));
                            ManData data2 = (ManData)ksyss.get(k);
                            hurt2 = Calculation.getCalculation().SMHurt(manData, data, (double)hurt2, 0.0, skill.getSkilltype(), (manData.getCamp() == 1) ? battlefield.MyDeath : battlefield.NoDeath);
                            FightingState Accepter2 = new FightingState();
                            fighting.setChangehp(-hurt2);
                            int y = data2.getStates();
                            data2.ChangeData(fighting, Accepter2);
                            list.add(Accepter2);
                            if (data2.getStates() == 1 && y != data2.getStates()) {
                                MixDeal.DeathSkill(data2, Accepter2, battlefield);
                            }
                        }
                        manData.getQuality().setRolewxqkm(manData.getQuality().getRolewxqkm() - 100.0);
                    }
                }
                else if (id == 9283) {
                    if (Battlefield.isV(skill2.getSkillhurt())) {
                        changeFighting.setSkill(skill2);
                    }
                }
                else if (id == 9323) {
                    AddState addState = data.getControlState();
                    if (addState != null) {
                        jc *= 1.0 + skill2.getSkillhurt() / 100.0;
                    }
                }
                else if (id == 9361) {
                    if (Sepcies_MixDeal.getRace(data.getSe()) != 10004) {
                        jc *= 1.0 + skill2.getSkillhurt() / 100.0;
                    }
                }
                else if (id == 9363) {
                    if (Battlefield.isV(skill2.getSkillhurt())) {
                        AddState addState = data.getGainState();
                        if (addState != null) {
                            data.getAddStates().remove(addState);
                            fState.setEndState_2(addState.getStatename());
                            int j = skills.size() - 1;
                            while (j >= 0) {
                                skill2 = (FightingSkill)skills.get(j);
                                if (skill2.getSkillid() == 9366) {
                                    if (Battlefield.isV(skill2.getSkillhurt())) {
                                        List<ManData> datas = MixDeal.get(true, manData, 0, data.getCamp(), 0, 0, 0, 0, 1, -1, battlefield, 1);
                                        if (datas.size() != 0) {
                                            ManData data3 = (ManData)datas.get(0);
                                            ChangeFighting changeFighting2 = new ChangeFighting();
                                            changeFighting2.setChangetype(addState.getStatename());
                                            changeFighting2.setChangevlaue(addState.getStateEffect());
                                            changeFighting2.setChangevlaue2(addState.getStateEffect2());
                                            FightingState fState2 = new FightingState();
                                            FightingPackage.ChangeProcess(changeFighting2, null, data3, fState2, addState.getStatename(), list, battlefield);
                                        }
                                        break;
                                    }
                                    else {
                                        break;
                                    }
                                }
                                else {
                                    --j;
                                }
                            }
                        }
                    }
                }
                else if (id == 9367) {
                    if (Battlefield.isV(33.0)) {
                        data.addAddState(TypeUtil.TY_GH_QYBY, skill.getSkillhurt(), 0.0, 2);
                    }
                }
                else if (id == 9286) {
                    changeFighting.setSkill(skill2);
                }
                else if (id == 9307 && manData.getvalue(6) < data.getvalue(6)) {
                    data.addAddState(TypeUtil.TY_F_CFWL_S, 0.0, 0.0, 2);
                    FightingState accepter = new FightingState(data.getCamp(), data.getMan(), "代价");
                    accepter.setText("好大的妖风#24");
                    accepter.setEndState_1(TypeUtil.TY_F_CFWL_S);
                    list.add(accepter);
                    skill2.setSkillgain(skill2.getSkillgain() - 1.0);
                    if (skill2.getSkillgain() <= 0.0) {
                        skills.remove(i);
                    }
                }
            }
        }
        if (PK_MixDeal.isPK(battlefield.BattleType)) {
            hurt = (int)((double)hurt * 0.7);
        }
        if (manData.getType() == 1 && skill.getSkillid() > 1040 && skill.getSkillid() <= 1065) {
            hurt += manData.getLvl() * 100;
        }
        hurt = Calculation.getCalculation().SMHurt(manData, data, (double)hurt, wg, skill.getSkilltype(), (manData.getCamp() == 1) ? battlefield.MyDeath : battlefield.NoDeath);
        hurt = (int)((double)hurt * jc);
        changeFighting.setChangehp(-hurt);
        FightingPackage.ChangeProcess(changeFighting, manData, data, fState, skill.getSkilltype(), list, battlefield);
        int cjdg = 50;
        AddState addState2 = manData.xzstate("气吞山河");
        if (addState2 != null) {
            cjdg = 100;
        }
        FightingSkill skill3 = manData.skillId(22013);
        if (skill3 != null && Battlefield.isV((double)cjdg)) {
            double fmsh = (double)((int)manData.getFmsld("催筋断骨") / 600);
            ChangeFighting changeFighting3 = new ChangeFighting();
            addState2 = manData.xzstate("气吞山河");
            if (addState2 != null) {
                double fmsh2 = (double)((int)manData.getFmsld("气吞山河") / 500);
                fmsh += fmsh2;
            }
            double zssh = (double)hurt * fmsh / 100.0;
            changeFighting3.setChangehp((int)(-zssh));
            FightingState fState2 = new FightingState();
            FightingPackage.ChangeProcess(changeFighting3, manData, data, fState2, "至圣", list, battlefield);
        }
        if (kbxs != 0.0 && !manData.fskbbj) {
            manData.fskbbj = true;
        }
        if (skills != null) {
            for (int l = skills.size() - 1; l >= 0; --l) {
                FightingSkill skill4 = (FightingSkill)skills.get(l);
                int id2 = skill4.getSkillid();
                if (id2 == 9266) {
                    if (data.getType() == 1 && data.getStates() == 1) {
                        ManData parents = battlefield.getPetParents(data);
                        if (parents != null && parents.getStates() == 0) {
                            int hurt3 = (int)(skill4.getSkillhurt() + (double)Battlefield.random.nextInt(5000));
                            ChangeFighting fighting2 = new ChangeFighting();
                            FightingState Accepter3 = new FightingState();
                            fighting2.setChangehp(-hurt3);
                            int y2 = parents.getStates();
                            parents.ChangeData(fighting2, Accepter3);
                            list.add(Accepter3);
                            if (parents.getStates() == 1 && y2 != parents.getStates()) {
                                MixDeal.DeathSkill(parents, Accepter3, battlefield);
                            }
                        }
                    }
                }
                else if (id2 == 9281 || id2 == 9301 || id2 == 9321) {
                    if (data.getStates() == 0) {
                        data.addAddState(skill4.getSkilltype(), skill4.getSkillhurt(), 0.0, 2);
                    }
                }
                else if (id2 == 9284 || id2 == 9304 || id2 == 9324) {
                    if (Battlefield.isV(skill4.getSkillhurt())) {
                        addState2 = data.getGainState();
                        if (addState2 != null) {
                            if (id2 == 9324) {
                                addState2.isEnd();
                            }
                            else {
                                data.getAddStates().remove(addState2);
                                fState.setEndState_2(addState2.getStatename());
                                if (id2 == 9304) {
                                    List<ManData> datas2 = MixDeal.get(true, manData, 0, data.getCamp(), 0, 0, 0, 0, 10, -1, battlefield, 1);
                                    int m = datas2.size() - 1;
                                    while (m >= 0) {
                                        ManData data4 = (ManData)datas2.get(m);
                                        if (data4.xzstate(addState2.getStatename()) == null) {
                                            ChangeFighting changeFighting4 = new ChangeFighting();
                                            changeFighting4.setChangetype(addState2.getStatename());
                                            changeFighting4.setChangevlaue(addState2.getStateEffect());
                                            changeFighting4.setChangevlaue2(addState2.getStateEffect2());
                                            FightingState fState3 = new FightingState();
                                            FightingPackage.ChangeProcess(changeFighting4, null, data4, fState3, addState2.getStatename(), list, battlefield);
                                            break;
                                        }
                                        else {
                                            --m;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else if (id2 == 9303) {
                    if (data.getType() == 1 && data.getStates() == 1) {
                        manData.addAddState2(skill4.getSkilltype(), skill4.getSkillhurt(), 0.0, 2);
                    }
                }
                else if (id2 == 9306) {
                    if (data.getStates() == 1) {
                        data.addAddState2(skill4.getSkilltype(), skill4.getSkillhurt(), 0.0, 2);
                    }
                }
                else if (id2 == 9326) {
                    SummonType.xianzhi(manData, skill);
                    int hurt2 = (int)skill4.getSkillhurt() + Battlefield.random.nextInt(3000);
                    if (kbxs != 0.0) {
                        hurt2 = (int)skill4.getSkillhurt() + Battlefield.random.nextInt(3000);
                    }
                    addState2 = data.addAddState2(skill4.getSkilltype(), (kbxs != 0.0) ? 2.0 : 1.0, (double)hurt2, 9999);
                    if (addState2.getStateEffect() >= 5.0) {
                        battlefield.isHSJY = true;
                    }
                    fState.setEndState_1(TypeUtil.TY_H_HSJY);
                }
                else if (id2 == 9362) {
                    if (data.getStates() == 1 && kbxs != 0.0) {
                        manData.addAddState2(skill4.getSkilltype(), skill4.getSkillhurt(), 0.0, 2);
                    }
                }
                else if (id2 == 9371) {
                    if (data.getStates() == 1 && Battlefield.isV(skill4.getSkillhurt())) {
                        data.addAddState2(skill4.getSkilltype(), skill4.getSkillhurt(), 0.0, 2);
                    }
                }
                else if (id2 == 9369) {
                    if (data.getvalue(0) > 80.0 - skill4.getSkillhurt() / 40.0) {
                        data.addAddState2(skill4.getSkilltype(), skill4.getSkillhurt(), 0.0, 2);
                    }
                }
                else if (id2 == 9370) {
                    if (data.getStates() == 0 && data.getvalue(0) < skill4.getSkillhurt() / 100.0 && Battlefield.isV(20.0)) {
                        int hurt2 = (int)skill4.getSkillhurt() * 20 + Battlefield.random.nextInt(15000);
                        ChangeFighting fighting3 = new ChangeFighting();
                        FightingState Accepter2 = new FightingState();
                        fighting3.setChangehp(-hurt2);
                        data.ChangeData(fighting3, Accepter2);
                        list.add(Accepter2);
                    }
                }
                else if (id2 == 9327 && data.getStates() == 1) {
                    int hurt2 = -changeFighting.getChangehp() - syhp;
                    hurt2 = (int)((double)hurt2 * (skill4.getSkillhurt() / 100.0));
                    List<ManData> datas3 = MixDeal.get(false, null, 0, manData.getCamp(), 0, 0, 0, 0, 1, 0, battlefield, 1);
                    if (datas3.size() != 0) {
                        ManData data4 = (ManData)datas3.get(0);
                        ChangeFighting changeFighting4 = new ChangeFighting();
                        changeFighting4.setChangehp(-hurt2);
                        FightingState fState3 = new FightingState();
                        FightingPackage.ChangeProcess(changeFighting4, null, data4, fState3, "火", list, battlefield);
                    }
                }
            }
        }
        return -changeFighting.getChangehp();
    }
    
    public static void addTZState(ManData data, FightingSkill tz_yg, FightingSkill tz_cb, FightingSkill tz_ph, FightingSkill tz_xy, FightingState Accepter) {
        if (tz_yg != null) {
            AddState addState = new AddState();
            addState.setStatename(tz_yg.getSkilltype());
            addState.setStateEffect(tz_yg.getSkillhurt());
            addState.setStateEffect2(tz_yg.getSkillgain());
            addState.setSurplus(tz_yg.getSkillcontinued());
            Accepter.setEndState_1(addState.getStatename());
            data.getAddStates().add(addState);
            data.getQuality().addkr(-tz_yg.getSkillhurt());
            data.getQuality().addks(-tz_yg.getSkillgain());
        }
        if (tz_cb != null && Battlefield.isV(tz_cb.getSkillhitrate())) {
            AddState addState = new AddState();
            addState.setStatename(tz_cb.getSkilltype());
            addState.setStateEffect(tz_cb.getSkillhurt());
            addState.setSurplus(tz_cb.getSkillcontinued());
            Accepter.setEndState_1(addState.getStatename());
            data.getAddStates().add(addState);
            data.setSp2((int)((double)data.getSp2() - tz_cb.getSkillhurt()));
        }
        if (tz_ph != null) {
            double v = tz_ph.getSkillhurt();
            if (data.xzstate(tz_ph.getSkilltype()) == null && Battlefield.isV(tz_ph.getSkillhitrate())) {
                v *= 2.0;
            }
            AddState addState2 = new AddState();
            addState2.setStatename(tz_ph.getSkilltype());
            addState2.setStateEffect(v);
            addState2.setSurplus(tz_ph.getSkillcontinued());
            Accepter.setEndState_1(addState2.getStatename());
            data.getAddStates().add(addState2);
            data.getQuality().addkr(-v);
        }
        if (tz_xy != null) {
            double v = tz_xy.getSkillhurt();
            if (data.xzstate(tz_xy.getSkilltype()) == null && (double)Battlefield.random.nextInt(100) < tz_xy.getSkillhitrate()) {
                v *= 2.0;
            }
            AddState addState2 = new AddState();
            addState2.setStatename(tz_xy.getSkilltype());
            addState2.setStateEffect(v);
            addState2.setSurplus(tz_xy.getSkillcontinued());
            Accepter.setEndState_1(addState2.getStatename());
            data.getAddStates().add(addState2);
            data.getQuality().addks(-v);
        }
    }
}
