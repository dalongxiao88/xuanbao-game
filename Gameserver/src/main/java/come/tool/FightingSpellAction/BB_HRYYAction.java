package come.tool.FightingSpellAction;

import java.util.Iterator;
import java.util.List;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.FightingManData;
import come.tool.FightingData.AddState;
import come.tool.FightingData.FightingSummon;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.FightingState;
import java.util.ArrayList;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public class BB_HRYYAction implements SpellAction
{
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        if (!Battlefield.isV(skill.getSkillgain())) {
            return;
        }
        if (!manData.getSkills().remove(skill)) {
            return;
        }
        FightingEvents fightingEvents = new FightingEvents();
        List<FightingState> Accepterlist = new ArrayList<>();
        FightingState fightingState = new FightingState();
        fightingState.setStartState(TypeUtil.JN);
        fightingState.setCamp(manData.getCamp());
        fightingState.setMan(manData.getMan());
        fightingState.setText("忽如一夜春风来,千树万树梨花开!#2");
        Accepterlist.add(fightingState);
        List<ManData> pets = new ArrayList<>();
        for (int i = battlefield.fightingdata.size() - 1; i >= 0; --i) {
            ManData data = (ManData)battlefield.fightingdata.get(i);
            if (data.getType() == 0 && data.getCamp() == manData.getCamp() && data.getMan() + 5 != manData.getMan()) {
                for (int j = 0; j < data.getPets().size(); ++j) {
                    if (((FightingSummon)data.getPets().get(j)).getPlay() == 0) {
                        int p = ((FightingSummon)data.getPets().get(j)).getsx(10000.0);
                        if (p != 0 && p != 1) {
                            ManData petData = ((FightingSummon)data.getPets().get(j)).getPet(data.isAi);
                            battlefield.Addbb(petData, petData.getCamp(), petData.getMan());
                            AddState chdd = null;
                            List<AddState> dellist = new ArrayList<>();
                            for (AddState addState : petData.getAddStates()) {
                                if (addState.getStatename().equals("冷却") && ((int)addState.getStateEffect() == 1608 || (int)addState.getStateEffect() == 1606 || (int)addState.getStateEffect() == 1838 || (int)addState.getStateEffect() == 1611 || (int)addState.getStateEffect() == 1612) && battlefield.CurrentRound >= 5) {
                                    chdd = addState;
                                }
                                else if (addState.getStatename().equals("冷却") && ((int)addState.getStateEffect() == 1608 || (int)addState.getStateEffect() == 1606 || (int)addState.getStateEffect() == 1838 || (int)addState.getStateEffect() == 1611 || (int)addState.getStateEffect() == 1612) && battlefield.CurrentRound <= 5) {
                                    int surplus = addState.getSurplus();
                                    addState.setSurplus(surplus - battlefield.CurrentRound + 1);
                                }
                                if (chdd != null) {
                                    dellist.add(chdd);
                                }
                            }
                            for (AddState addState : dellist) {
                                petData.getAddStates().remove(addState);
                            }
                            FightingState state = new FightingState();
                            state.setStartState("召唤");
                            state.setCamp(petData.getCamp());
                            state.setMan(petData.getMan());
                            petData.setStates(0);
                            FightingManData fightingManData = new FightingManData();
                            fightingManData.setModel(petData.getModel());
                            fightingManData.setSpeciesid(petData.getSpeciesid());
                            fightingManData.setManname(petData.getManname());
                            fightingManData.setCamp(petData.getCamp());
                            fightingManData.setMan(petData.getMan());
                            fightingManData.setHp_Current((long)petData.getHp());
                            fightingManData.setHp_Total((long)petData.getHp_z());
                            fightingManData.setMp_Current(petData.getMp());
                            fightingManData.setMp_Total(petData.getMp_z());
                            fightingManData.setState_1(petData.xz());
                            fightingManData.setType(petData.getType());
                            fightingManData.setManname(petData.getManname());
                            fightingManData.setZs(petData.getZs());
                            fightingManData.setMsg(petData.getMsg());
                            fightingManData.setYqz(petData.getYqz());
                            fightingManData.setNqz(petData.getNqz());
                            fightingManData.setStates(petData.ztstlist(fightingManData));
                            fightingManData.setId(petData.getId());
                            if (petData.getSkillType("隐身") != null) {
                                state.setEndState_1("隐身");
                                AddState addState2 = new AddState();
                                addState2.setStatename("隐身");
                                addState2.setSurplus(3);
                                petData.getAddStates().add(addState2);
                                fightingManData.setAlpha(0.3f);
                            }
                            state.setFightingManData(fightingManData);
                            Accepterlist.add(state);
                            MixDeal.Approach(petData, state, battlefield);
                            pets.add(petData);
                            break;
                        }
                    }
                }
            }
        }
        fightingEvents.setAccepterlist(Accepterlist);
        battlefield.NewEvents.add(fightingEvents);
        if (pets.size() > 0) {
            int my = 0;
            int it = 0;
            for (ManData data2 : battlefield.fightingdata) {
                if (data2.getStates() == 0 && data2.getType() < 3) {
                    if (data2.getCamp() == manData.getCamp()) {
                        ++my;
                    }
                    else {
                        ++it;
                    }
                }
            }
            for (ManData petData2 : pets) {
                petData2.executeYzsg(battlefield, null);
                if (my < it) {
                    petData2.executeYztc(battlefield, null);
                }
                if (Battlefield.isV((double)petData2.executeBfbd(1))) {
                    int wei = battlefield.Datapathhuo(petData2.getCamp(), petData2.getMan() - 5);
                    if (wei != -1) {
                        ManData master = (ManData)battlefield.fightingdata.get(wei);
                        int kx = petData2.executeBfbd(2);
                        int si = Battlefield.random.nextInt(12) + 1;
                        switch (si) {
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
                        ace.setCamp(petData2.getCamp());
                        ace.setMan(petData2.getMan());
                        ace.setText("看我的八风不动");
                        zls.add(ace);
                        FightingEvents event = new FightingEvents();
                        event.setAccepterlist(zls);
                        battlefield.NewEvents.add(event);
                    }
                    else {
                        continue;
                    }
                }
            }
        }
    }
}
