package come.tool.FightingSpellAction;

import java.util.List;
import org.come.model.Skill;
import come.tool.FightingData.AddState;
import come.tool.FightingData.FightingManData;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.FightingState;
import java.util.ArrayList;
import java.util.Random;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.SummonType;
import org.come.server.GameServer;
import come.tool.FightingData.PK_MixDeal;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public class TJ_WJCXAction implements SpellAction
{
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        if (PK_MixDeal.isPK(battlefield.BattleType)) {
            Skill skillXls = GameServer.getSkill("1264");
            SummonType.xianzhi(manData, skill);
            MixDeal.ys(manData, false, battlefield);
            Random rand = new Random();
            int randNumber = rand.nextInt(10) + 1;
            List<ManData> datas = MixDeal.getdaji(randNumber, manData.getCamp(), events, battlefield);
            List<FightingState> Accepterlist = new ArrayList<>();
            FightingState Originator = events.getOriginator();
            Originator.setStartState("法术攻击");
            Originator.setSkillsy(skill.getSkillname());
            if (manData.daijia(skill, Originator, battlefield)) {
                return;
            }
            Originator.setText("剑...来！#2");
            int lj = rand.nextInt(100) + 1;
            if (lj < 21) {
                int sj = rand.nextInt(datas.size() - 1 + 1) + 1;
                --sj;
                for (int i = 0; i < datas.size(); ++i) {
                    if (sj == i) {
                        for (int s = 0; s < 2; ++s) {
                            FightingState fightingState = new FightingState();
                            ManData data = (ManData)datas.get(i);
                            ChangeFighting fighting = new ChangeFighting();
                            fighting.setChangetype("技能");
                            fightingState.setSkillskin("30001");
                            int is = (int)((manData.getShanghai() * (double)skillXls.getSkilllevel() * skillXls.getGrow() * skillXls.getValue() + (double)(manData.getMp_z() / 50)) / 2.5);
                            fighting.setChangehp(-is);
                            FightingPackage.ChangeProcess(fighting, null, data, fightingState, "技能", Accepterlist, battlefield);
                        }
                    }
                    else {
                        FightingState fightingState2 = new FightingState();
                        ManData data2 = (ManData)datas.get(i);
                        ChangeFighting fighting2 = new ChangeFighting();
                        fighting2.setChangetype("技能");
                        fightingState2.setSkillskin("30001");
                        int is2 = (int)((manData.getShanghai() * (double)skillXls.getSkilllevel() * skillXls.getGrow() * skillXls.getValue() + (double)(manData.getMp_z() / 50)) / 2.5);
                        fighting2.setChangehp(-is2);
                        FightingPackage.ChangeProcess(fighting2, null, data2, fightingState2, "技能", Accepterlist, battlefield);
                    }
                }
            }
            else {
                for (int j = 0; j < datas.size(); ++j) {
                    FightingState fightingState3 = new FightingState();
                    ManData data3 = (ManData)datas.get(j);
                    ChangeFighting fighting3 = new ChangeFighting();
                    fighting3.setChangetype("技能");
                    fightingState3.setSkillskin("30001");
                    int is3 = (int)((manData.getShanghai() * (double)skillXls.getSkilllevel() * skillXls.getGrow() * skillXls.getValue() + (double)(manData.getMp_z() / 50)) / 2.5);
                    fighting3.setChangehp(-is3);
                    FightingPackage.ChangeProcess(fighting3, null, data3, fightingState3, "技能", Accepterlist, battlefield);
                }
            }
            int ysl = rand.nextInt(100) + 1;
            if (ysl < 31) {
                FightingManData fightingManData = new FightingManData();
                fightingManData.setModel(manData.getModel());
                fightingManData.setSpeciesid(manData.getSpeciesid());
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
                FightingState fightingState2 = new FightingState();
                AddState addState = new AddState();
                addState.setStatename("隐身");
                addState.setSurplus(3);
                manData.getAddStates().add(addState);
                fightingManData.setAlpha(0.3f);
                fightingState2.setFightingManData(fightingManData);
                Accepterlist.add(fightingState2);
                MixDeal.Approach(manData, fightingState2, battlefield);
                Originator.setCamp(manData.getCamp());
                Originator.setMan(manData.getMan());
                Originator.setEndState_1("隐身");
                Originator.setText("我隐...#2");
            }
            if (events.getOriginator() != null) {
                Accepterlist.add(Originator);
            }
            events.setOriginator(null);
            events.setAccepterlist(Accepterlist);
            battlefield.NewEvents.add(events);
        }
        else {
            Skill skillXls = GameServer.getSkill("1264");
            SummonType.xianzhi(manData, skill);
            MixDeal.ys(manData, false, battlefield);
            Random rand = new Random();
            int randNumber = rand.nextInt(10) + 1;
            List<ManData> datas = MixDeal.getdaji(randNumber, manData.getCamp(), events, battlefield);
            List<FightingState> Accepterlist = new ArrayList<>();
            FightingState Originator = events.getOriginator();
            Originator.setStartState("法术攻击");
            Originator.setSkillsy(skill.getSkillname());
            if (manData.daijia(skill, Originator, battlefield)) {
                return;
            }
            Originator.setText("剑...来！#2");
            int lj = rand.nextInt(100) + 1;
            if (lj < 21) {
                int sj = rand.nextInt(datas.size() - 1 + 1) + 1;
                --sj;
                for (int i = 0; i < datas.size(); ++i) {
                    if (sj == i) {
                        for (int s = 0; s < 2; ++s) {
                            FightingState fightingState = new FightingState();
                            ManData data = datas.get(i);
                            ChangeFighting fighting = new ChangeFighting();
                            fighting.setChangetype("技能");
                            fightingState.setSkillskin("30001");
                            int is = (int)(manData.getShanghai() * (double)skillXls.getSkilllevel() * skillXls.getGrow() * skillXls.getValue() + (double)(manData.getMp_z() / 50));
                            fighting.setChangehp(-is);
                            FightingPackage.ChangeProcess(fighting, null, data, fightingState, "技能", Accepterlist, battlefield);
                        }
                    }
                    else {
                        FightingState fightingState2 = new FightingState();
                        ManData data2 = (ManData)datas.get(i);
                        ChangeFighting fighting2 = new ChangeFighting();
                        fighting2.setChangetype("技能");
                        fightingState2.setSkillskin("30001");
                        int is2 = (int)(manData.getShanghai() * (double)skillXls.getSkilllevel() * skillXls.getGrow() * skillXls.getValue() + (double)(manData.getMp_z() / 50));
                        fighting2.setChangehp(-is2);
                        FightingPackage.ChangeProcess(fighting2, null, data2, fightingState2, "技能", Accepterlist, battlefield);
                    }
                }
            }
            else {
                for (int j = 0; j < datas.size(); ++j) {
                    FightingState fightingState3 = new FightingState();
                    ManData data3 = (ManData)datas.get(j);
                    ChangeFighting fighting3 = new ChangeFighting();
                    fighting3.setChangetype("技能");
                    fightingState3.setSkillskin("30001");
                    int is3 = (int)(manData.getShanghai() * (double)skillXls.getSkilllevel() * skillXls.getGrow() * skillXls.getValue() + (double)(manData.getMp_z() / 50));
                    fighting3.setChangehp(-is3);
                    FightingPackage.ChangeProcess(fighting3, null, data3, fightingState3, "技能", Accepterlist, battlefield);
                }
            }
            int ysl = rand.nextInt(100) + 1;
            if (ysl < 31) {
                FightingManData fightingManData = new FightingManData();
                fightingManData.setModel(manData.getModel());
                fightingManData.setSpeciesid(manData.getSpeciesid());
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
                FightingState fightingState2 = new FightingState();
                AddState addState = new AddState();
                addState.setStatename("隐身");
                addState.setSurplus(3);
                manData.getAddStates().add(addState);
                fightingManData.setAlpha(0.3f);
                fightingState2.setFightingManData(fightingManData);
                Accepterlist.add(fightingState2);
                MixDeal.Approach(manData, fightingState2, battlefield);
                Originator.setCamp(manData.getCamp());
                Originator.setMan(manData.getMan());
                Originator.setEndState_1("隐身");
                Originator.setText("我隐...#2");
            }
            if (events.getOriginator() != null) {
                Accepterlist.add(Originator);
            }
            events.setOriginator(null);
            events.setAccepterlist(Accepterlist);
            battlefield.NewEvents.add(events);
        }
    }
}
