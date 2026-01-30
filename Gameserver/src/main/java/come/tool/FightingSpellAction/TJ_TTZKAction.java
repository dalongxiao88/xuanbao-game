package come.tool.FightingSpellAction;

import java.util.List;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.FightingState;
import java.util.Collections;
import java.util.ArrayList;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public class TJ_TTZKAction implements SpellAction
{
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        List<ManData> datas = MixDeal.getdaji(10, manData.getCamp(), events, battlefield);
        List<FightingState> Accepterlist = new ArrayList<>();
        FightingState Originator = events.getOriginator();
        Originator.setSkillsy(skill.getSkillname());
        Originator.setSkillskin("ttzk");
        if (manData.daijia(skill, Originator, battlefield)) {
            return;
        }
        if (events.getOriginator() != null) {
            Accepterlist.add(Originator);
        }
        Collections.shuffle(datas);
        List<ManData> dataz = new ArrayList<>();
        int x = skill.getSkillsum();
        if (datas.size() < x) {
            x = datas.size();
        }
        for (int i = 0; i < x; ++i) {
            if (datas.get(i) != null) {
                dataz.add(datas.get(i));
            }
        }
        for (int maxg = 2, j = 0; j < maxg; ++j) {
            if (j == 0) {
                if (manData.getStates() != 0) {
                    break;
                }
                else {
                    FightingEvents gjEvents = new FightingEvents();
                    List<FightingState> zls = new ArrayList<>();
                    FightingState gjz = new FightingState();
                    gjz.setCamp(manData.getCamp());
                    gjz.setMan(manData.getMan());
                    gjz.setSkillsy("magic");
                    gjz.setStartState(TypeUtil.JN);
                    gjz.setEndState("3");
                    gjz.setText("乾坤袋,收！");
                    zls.add(Originator);
                    zls.add(gjz);
                    for (int k = dataz.size() - 1; k >= 0; --k) {
                        ManData data = (ManData)dataz.get(k);
                        if (data.getStates() != 0) {
                            datas.remove(k);
                        }
                        else {
                            FightingState ace = new FightingState();
                            ace.setCamp(data.getCamp());
                            ace.setMan(data.getMan());
                            FightingState move = new FightingState();
                            move.setCamp(data.getCamp());
                            move.setMan(data.getMan());
                            move.setStartState("移动");
                            move.setEndState(manData.getCamp() + "|" + manData.getMan() + "|3");
                            zls.add(move);
                            gjEvents.setAccepterlist(zls);
                        }
                    }
                    battlefield.NewEvents.add(gjEvents);
                }
            }
            else if (manData.getStates() != 0) {
                break;
            }
            else {
                FightingEvents gjEvents = new FightingEvents();
                List<FightingState> zls = new ArrayList<>();
                FightingState gjz = new FightingState();
                gjz.setCamp(manData.getCamp());
                gjz.setMan(manData.getMan());
                gjz.setSkillsy("magic");
                gjz.setStartState(TypeUtil.JN);
                gjz.setEndState("3");
                gjz.setEndState("呸！真难吃#74");
                zls.add(gjz);
                for (int k = dataz.size() - 1; k >= 0; --k) {
                    ManData data = (ManData)dataz.get(k);
                    if (data.getStates() != 0) {
                        datas.remove(k);
                    }
                    else {
                        FightingState ace = new FightingState();
                        ace.setCamp(data.getCamp());
                        ace.setMan(data.getMan());
                        ChangeFighting acec = new ChangeFighting();
                        FightingPackage.ChangeProcess(acec, manData, data, ace, TypeUtil.ZSSH, zls, battlefield);
                    }
                }
                gjEvents.setAccepterlist(zls);
                battlefield.NewEvents.add(gjEvents);
                for (int k = dataz.size() - 1; k >= 0; --k) {
                    ManData data = (ManData)dataz.get(k);
                    MixDeal.move(data.getCamp(), data.getMan(), "瞬移", data.getCamp() + "|" + data.getMan(), battlefield);
                }
            }
        }
    }
}
