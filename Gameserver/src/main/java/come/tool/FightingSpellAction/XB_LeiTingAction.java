

package come.tool.FightingSpellAction;

import come.tool.FightingData.Battlefield;
import come.tool.FightingData.Calculation;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.ManData;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.PK_MixDeal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class XB_LeiTingAction implements SpellAction {
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        List<FightingState> Accepterlist = new ArrayList<>();
        List<ManData> datas = MixDeal.get(true, manData, 0, manData.getCamp(), 0, 0, 0, 0, 10, 0, battlefield, 0);
        if (datas.size() == 0) {
            FightingState Originator = events.getOriginator();
            if (!manData.daijia(skill, Originator, battlefield)) {
                Originator.setStartState("法术攻击");
                Originator.setSkillsy(skill.getSkillname());
                events.setOriginator(null);
                Accepterlist.add(Originator);
                events.setAccepterlist(Accepterlist);
                battlefield.NewEvents.add(events);
            }
        } else if (!manData.daijia(skill, new FightingState(), battlefield)) {
            int q = skill.getP1() == 0.0 ? 0 : (int)((double)manData.getXy() / skill.getP1());
            int s = skill.getP1() == 0.0 ? 0 : (int)(skill.getP1() / 2.0);
            int sum1 = 5 + (q <= 0 ? 0 : s * q);
            FightingState fightingState = new FightingState();
            fightingState.setStartState("法术攻击");
            fightingState.setCamp(manData.getCamp());
            fightingState.setMan(manData.getMan());
            fightingState.setSkillskin("30016");
            Accepterlist.add(fightingState);
            manData.addXYZ((int)((double)(-q) * skill.getP1()));
            Random random = new Random();
            double jc = manData.getSpellJC();
            double wg = manData.getWGTB();
            Map<String, Integer> integerMap = new HashMap<>();
            manData.isjv = true;
            int sum2 = (int)skill.getS2();

            FightingState Originator;
            for(int i = 0; i <= 100 && sum1 > 0; ++i) {
                Originator = new FightingState();
                Originator.setCamp(manData.getCamp());
                Originator.setMan(manData.getMan());
                if (i == 0) {
                    Originator.setXy_c((int)((double)(-q) * skill.getP1()));
                }

                FightingEvents moveEvents1 = new FightingEvents();
                List<FightingState> moves1 = new ArrayList<>();
                ManData data = datas.get(random.nextInt(datas.size()));
                if (integerMap.get(data.getId() + "") == null) {
                    integerMap.put(data.getId() + "", 1);
                } else {
                    if (integerMap.get(data.getId() + "") >= 2 || data.getStates() != 0) {
                        continue;
                    }

                    integerMap.put(data.getId() + "", integerMap.get(data.getId() + "") + 1);
                }

                --sum1;
                FightingState move = new FightingState();
                int hurt = (int)(skill.getSkillgain() * (1.0 + (data.getType() == 1 ? skill.getE1() / 100.0 : 0.0)));
                int kbl = 0;
                move.setCamp(data.getCamp());
                move.setMan(data.getMan());
                move.setSkillskin("3003");
                ChangeFighting changeFighting = new ChangeFighting();
                FightingState fightingState1 = new FightingState();
                fightingState1.setCamp(data.getCamp());
                fightingState1.setMan(data.getMan());
                double kb = MixDeal.addition((double)kbl, fightingState1, manData, data, "雷");
                if (PK_MixDeal.isPK(battlefield.BattleType)) {
                    hurt = (int)((double)hurt * 0.7);
                }

                hurt = Calculation.getCalculation().SMHurt(manData, data, (double)hurt, wg, "雷", manData.getCamp() == 1 ? battlefield.MyDeath : battlefield.NoDeath);
                if (kb != 0.0) {
                    hurt = (int)((double)hurt * kb);
                }

                hurt = (int)((double)hurt * jc);
                changeFighting.setChangehp(-hurt);
                FightingPackage.ChangeProcess(changeFighting, manData, data, fightingState1, "雷", moves1, battlefield);
                Originator.setStartState("法术攻击");
                moves1.add(Originator);
                moves1.add(move);
                moveEvents1.setAccepterlist(moves1);
                battlefield.NewEvents.add(moveEvents1);
                if (data.getStates() == 1 && sum2 > 0 && Battlefield.isV(skill.getS1())) {
                    ++sum1;
                    --sum2;
                }
            }

            manData.isjv = false;
            if (manData.fzsh != 0L) {
                FightingEvents moveEvents1 = new FightingEvents();
                Originator = new FightingState();
                List<FightingState> moves2 = new ArrayList<>();
                Originator.setCamp(manData.getCamp());
                Originator.setMan(manData.getMan());
                ChangeFighting changeFighting = new ChangeFighting();
                changeFighting.setChangehp((int)manData.fzsh);
                FightingPackage.ChangeProcess(changeFighting, null, manData, Originator, "反震", moves2, battlefield);
                Originator.setStartState("stand");
                moveEvents1.setAccepterlist(moves2);
                battlefield.NewEvents.add(moveEvents1);
            }

        }
    }
}
