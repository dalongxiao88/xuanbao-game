package come.tool.FightingDataAction;

import java.util.Iterator;
import come.tool.FightingData.GroupBuff;
import java.util.List;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.PK_MixDeal;
import java.util.ArrayList;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public class TJ_XFLZAction implements DataAction
{
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        int g = 0;
        type = TypeUtil.PTGJ;
        FightingSkill skill = manData.getSkillId(450051);
        List<ManData> datas = MixDeal.getdaji(1, manData.getCamp(), fightingEvents, battlefield);
        List<FightingState> Accepterlist = new ArrayList<>();
        FightingState Originator = fightingEvents.getOriginator();
        Originator.setStartState("法术攻击");
        Originator.setSkillsy(skill.getSkillname());
        if (manData.daijia(skill, Originator, battlefield)) {
            return;
        }
        Originator.setText("儿郎们,给我冲!");
        if (fightingEvents.getOriginator() != null) {
            Accepterlist.add(Originator);
        }
        int xj = 9;
        if (xj > 0) {
            xj *= 3;
        }
        double ljl = manData.getQuality().getRolefljl() + (double)manData.ljv - (double)((((ManData)datas.get(0)).getSkillType(TypeUtil.TJ_YCDY) == null) ? 0 : 15);
        int ljv = (int)manData.getQuality().getRolefljv();
        FightingSkill skill2 = manData.getAppendSkill(9811);
        if (skill2 != null) {
            ljl -= skill2.getSkillhurt();
        }
        ljl *= 0.7;
        ljv = (int)((double)ljv * 0.7);
        int maxg = 1 + (((double)Battlefield.random.nextInt(100) < ljl) ? ljv : 0);
        if (PK_MixDeal.isPK(battlefield.BattleType)) {
            GroupBuff buff = battlefield.getBuff(((ManData)datas.get(0)).getCamp(), TypeUtil.BB_QZ);
            if (buff != null && maxg > 3) {
                maxg = 3;
            }
        }
        ManData data = (ManData)datas.get(0);
        data.setBeatenNum(0);
        List<ManData> listjl = battlefield.getDieManData(data);
        for (ManData data2 : listjl) {
            data2.setBeatenNum(0);
        }
        for (int i = 0; i < maxg + xj; ++i) {
            if (manData.getStates() != 0) {
                return;
            }
            FightingEvents events1 = new FightingEvents();
            events1.setSp(Integer.valueOf(50));
            List<FightingState> Accepterlist2 = new ArrayList<>();
            if (data.getStates() != 0 || data.getHp() <= 0) {
                List<ManData> list1 = battlefield.getDieManData(data);
                if (list1.size() > 0) {
                    data = (ManData)list1.get(0);
                }
                else {
                    break;
                }
            }
            if (i != 0 && Battlefield.isV(50.0)) {
                List<ManData> list2 = battlefield.getDieManData(data);
                if (list2.size() > 0 && manData.getStates() == 0) {
                    data = (ManData)list2.get(Battlefield.random.nextInt(list2.size()));
                    if (data.getStates() == 0) {
                        FightingState fightingState2 = new FightingState();
                        FightingState ace2 = new FightingState();
                        ace2.setCamp(data.getCamp());
                        ace2.setMan(data.getMan());
                        ace2.setStartState("被攻击");
                        ChangeFighting changeFighting2 = new ChangeFighting();
                        int bs = data.getBeatenNum();
                        if (bs == 0) {
                            bs = 1;
                        }
                        changeFighting2.setChangehp(-manData.getAp());
                        FightingPackage.ChangeProcess(changeFighting2, manData, data, fightingState2, "霹雳end", Accepterlist2, battlefield);
                        PhyAttack.neidan(type, manData, data, (long)manData.getAp(), battlefield, Accepterlist2, g, 0, 0.0);
                        PhyAttack.neidan(type, manData, data, (long)manData.getAp(), battlefield, Accepterlist2, g, 0, 0.0);
                        fightingState2.setSkillskin("450051");
                        data.setBeatenNum(data.getBeatenNum() + 1);
                        if (Battlefield.isV(10.0) && manData.getStates() == 0) {
                            List<ManData> list3 = battlefield.getDieManData(data);
                            if (list3.size() > 0) {
                                data = (ManData)list3.get(Battlefield.random.nextInt(list3.size()));
                                if (data.getStates() == 0) {
                                    FightingState fightingState3 = new FightingState();
                                    FightingState ace3 = new FightingState();
                                    ace3.setCamp(data.getCamp());
                                    ace3.setMan(data.getMan());
                                    ace3.setStartState("被攻击");
                                    ChangeFighting changeFighting3 = new ChangeFighting();
                                    bs = data.getBeatenNum();
                                    if (bs == 0) {
                                        bs = 1;
                                    }
                                    changeFighting3.setChangehp(-(manData.getAp() / bs));
                                    FightingPackage.ChangeProcess(changeFighting3, manData, data, fightingState3, "霹雳end", Accepterlist2, battlefield);
                                    PhyAttack.neidan(type, manData, data, (long)manData.getAp(), battlefield, Accepterlist2, g, 0, 0.0);
                                    fightingState3.setSkillskin("450051");
                                    data.setBeatenNum(data.getBeatenNum() + 1);
                                }
                            }
                        }
                    }
                }
            }
            if (data.getStates() == 0) {
                FightingState fightingState4 = new FightingState();
                FightingState ace4 = new FightingState();
                ace4.setCamp(data.getCamp());
                ace4.setMan(data.getMan());
                ace4.setStartState("被攻击");
                ChangeFighting changeFighting4 = new ChangeFighting();
                int bs2 = data.getBeatenNum();
                if (bs2 == 0) {
                    bs2 = 1;
                }
                changeFighting4.setChangehp(-(manData.getAp() / bs2));
                FightingPackage.ChangeProcess(changeFighting4, manData, data, fightingState4, "霹雳end", Accepterlist2, battlefield);
                PhyAttack.neidan(type, manData, data, (long)manData.getAp(), battlefield, Accepterlist2, g, 0, 0.0);
                fightingState4.setSkillskin("450051");
                fightingState4.setSp(Double.valueOf(0.1));
                data.setBeatenNum(data.getBeatenNum() + 1);
            }
            if (events1.getOriginator() != null) {
                Accepterlist2.add(Originator);
            }
            if (i == 0) {
                Accepterlist2.addAll(Accepterlist);
            }
            events1.setOriginator(null);
            events1.setAccepterlist(Accepterlist2);
            battlefield.NewEvents.add(events1);
            ++g;
        }
    }
    
    public FightingSkill getskill(int x) {
        List<FightingSkill> list = new ArrayList<>();
        FightingSkill fightingSkill = new FightingSkill();
        fightingSkill.setSkillid(1059);
        fightingSkill.setSkillbeidong(1);
        fightingSkill.setSkilltype("火");
        fightingSkill.setSkillsum(1);
        list.add(fightingSkill);
        return (FightingSkill)list.get(0);
    }
}
