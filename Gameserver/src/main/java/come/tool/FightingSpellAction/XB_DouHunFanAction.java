

package come.tool.FightingSpellAction;

import come.tool.FightingData.Battlefield;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.ManData;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.SummonType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.come.action.role.RoleTransAction;

public class XB_DouHunFanAction implements SpellAction {


    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        skill.setSkillsum(1);
        SummonType.xianzhi(manData, skill);
        List<FightingState> Accepterlist = new ArrayList<>();
        List<ManData> datas = MixDeal.getjieshou(events, skill, manData, Accepterlist, battlefield);
        if (datas.isEmpty() || datas.get(0).getType() != 0) {
            return;
        }
        ManData targetData = datas.get(0);
        if (!isValidState(manData) || !isValidState(targetData)) {
            return;
        }
        if (manData.daijia(skill, new FightingState(), battlefield)) {
            return;
        }
        int skillIdForMan = getSkillIdForRole(manData);
        int skillIdForTarget = getSkillIdForRole(targetData);
        FightingSkill manSkill = findSkillById(manData, skillIdForMan);
        FightingSkill targetSkill = findSkillById(targetData, skillIdForTarget);
        int battleCount = skill.getSkillcontinued() + (Battlefield.isV(skill.e1) ? 1 : 0);
        manData.addAddState("斗魂帆", skill.getS1(), skill.getP1(), 1);
        BattleResult battleResult = executeBattle(manData, targetData, manSkill, targetSkill, battleCount, battlefield);
        processBattleResult(battleResult, manData, targetData, skillIdForMan, skillIdForTarget, skill, battlefield);
    }

    private boolean isValidState(ManData manData) {
        return manData.xzstate("封印") == null &&
                manData.xzstate("遗忘") == null &&
                manData.xzstate("昏睡") == null &&
                manData.xzstate("混乱") == null;
    }

    private int getSkillIdForRole(ManData manData) {
        String roleType = RoleTransAction.getSepciesN(manData.getSe());
        Map<Integer, Double> skillMap = new HashMap<>();
        switch (roleType) {
            case "男鬼":
                return manData.getQuality().getRolehsgh() > manData.getQuality().getRolestrongbodyblooddeep() ? 1064 : 1069;

            case "女鬼":
                return manData.getQuality().getRolehsgh() > manData.getQuality().getRolehsyw() ? 1064 : 1074;

            case "男人":
                skillMap.put(1004, manData.getQuality().getRolehshl());
                skillMap.put(1014, manData.getQuality().getRolehshs());
                skillMap.put(1009, manData.getQuality().getRolehsfy());
                return getMaxSkillId(skillMap);

            case "女人":
                skillMap.put(1019, manData.getQuality().getQzds());
                skillMap.put(1014, manData.getQuality().getRolehshs());
                skillMap.put(1009, manData.getQuality().getRolehsfy());
                return getMaxSkillId(skillMap);

            case "男龙":
            case "女龙":
                return 1084;

            case "男仙":
                skillMap.put(1044, manData.getQuality().getRolehsff());
                skillMap.put(1054, manData.getQuality().getRolehssf());
                skillMap.put(1049, manData.getQuality().getRolehslf());
                return getMaxSkillId(skillMap);

            case "女仙":
                skillMap.put(1059, manData.getQuality().getRolehshf());
                skillMap.put(1054, manData.getQuality().getRolehssf());
                skillMap.put(1049, manData.getQuality().getRolehslf());
                return getMaxSkillId(skillMap);

            case "男魔":
            case "女魔":
            default:
                return 1024;
        }
    }

    private int getMaxSkillId(Map<Integer, Double> skillMap) {
        int maxSkillId = 0;
        double maxValue = Double.NEGATIVE_INFINITY;

        for (Map.Entry<Integer, Double> entry : skillMap.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
                maxSkillId = entry.getKey();
            }
        }

        return maxSkillId;
    }

    private FightingSkill findSkillById(ManData manData, int skillId) {
        for (FightingSkill fightingSkill : manData.getSkills()) {
            if (fightingSkill.getSkillid() == skillId) {
                return fightingSkill.clone();
            }
        }
        return null;
    }

    private BattleResult executeBattle(ManData manData, ManData targetData, FightingSkill manSkill,
                                       FightingSkill targetSkill, int battleCount, Battlefield battlefield) {
        long initialHpMan = manData.getHp();
        long initialHpTarget = targetData.getHp();

        for (int i = 0; i < battleCount; i++) {
            if (checkBattleEnd(manData, targetData)) {
                return determineLoserAndWinner(manData, targetData);
            }

            if (manSkill != null) {
                executeSkill(manData, targetData, manSkill, battlefield);
            }

            if (checkBattleEnd(manData, targetData)) {
                return determineLoserAndWinner(manData, targetData);
            }

            if (targetSkill != null) {
                executeSkill(targetData, manData, targetSkill, battlefield);
            }
        }

        if (manData.getStates() == 0 && targetData.getStates() == 0) {
            manData.RemoveAbnormal("斗魂帆");
            long hpLossMan = initialHpMan - manData.getHp();
            long hpLossTarget = initialHpTarget - targetData.getHp();

            return hpLossMan > hpLossTarget ?
                    new BattleResult(targetData, manData) :
                    new BattleResult(manData, targetData);
        }

        return determineLoserAndWinner(manData, targetData);
    }

    private boolean checkBattleEnd(ManData manData, ManData targetData) {
        return manData.getStates() != 0 || targetData.getStates() != 0 ||
                hasControlState(manData) || hasControlState(targetData);
    }

    private boolean hasControlState(ManData manData) {
        return manData.xzstate("混乱") != null ||
                manData.xzstate("封印") != null ||
                manData.xzstate("昏睡") != null ||
                manData.xzstate("遗忘") != null;
    }

    private BattleResult determineLoserAndWinner(ManData manData, ManData targetData) {
        if (targetData.getStates() != 0 || hasControlState(targetData)) {
            return new BattleResult(targetData, manData);
        } else if (manData.getStates() != 0 || hasControlState(manData)) {
            return new BattleResult(manData, targetData);
        }
        return null;
    }

    private void executeSkill(ManData caster, ManData target, FightingSkill skill, Battlefield battlefield) {
        FightingEvents skillEvents = new FightingEvents();
        FightingState originatorState = new FightingState();

        originatorState.setCamp(caster.getCamp());
        originatorState.setMan(caster.getMan());
        originatorState.setStartState("技能");
        originatorState.setEndState(skill.getSkillname());
        skillEvents.setOriginator(originatorState);

        skill.getFightingManData().clear();
        skill.getFightingManData().add(target);

        int skillId = skill.getSkillid();
        int actionType = getActionType(skillId);

        if (actionType != -1) {
            SpellActionType.getActionById(actionType).spellAction(caster, skill, skillEvents, battlefield);
        }
    }

    private int getActionType(int skillId) {
        if ((skillId >= 1001 && skillId <= 1020) || (skillId >= 1071 && skillId <= 1075)) {
            return 1;
        } else if (skillId >= 1041 && skillId <= 1065) {
            return 11;
        } else if (skillId >= 1021 && skillId <= 1025) {
            return 4;
        } else if ((skillId >= 1026 && skillId <= 1040) || (skillId >= 1076 && skillId <= 1080) ||
                skillId == 1214 || (skillId >= 1297 && skillId <= 1299)) {
            return 5;
        } else if (skillId >= 1066 && skillId <= 1070) {
            return 8;
        } else if ((skillId >= 1081 && skillId <= 1090) || (skillId >= 1096 && skillId <= 1100)) {
            return 20;
        }
        return -1;
    }

    private void processBattleResult(BattleResult result, ManData manData, ManData targetData,
                                     int skillIdMan, int skillIdTarget, FightingSkill skill, Battlefield battlefield) {
        if (result == null) return;

        updateSkillPower(result.winner, result.winner.equals(manData) ? skillIdMan : skillIdTarget,  skill.getSkillhurt());
        updateSkillPower(result.loser, result.loser.equals(manData) ? skillIdMan : skillIdTarget,  -skill.getSkillhurt());

        List<FightingState> removeStates = new ArrayList<>();
        ChangeFighting changeFighting = new ChangeFighting();
        changeFighting.setChangetype("斗魂幡");
        changeFighting.setChangesum(4);

//        FightingState accepter = new FightingState();
//        accepter.setText("本次决斗"+result.winner.getManname()+"胜出");
        FightingState winnerState = new FightingState();
        winnerState.setText("本次斗魂幡决斗中获得了胜利！");
        FightingState loserState = new FightingState();
        loserState.setText("本次斗魂幡决斗中不幸落败！");
        FightingEvents finalEvents = new FightingEvents();
        finalEvents.setAccepterlist(removeStates);

        FightingPackage.ChangeProcess(changeFighting, null, result.winner, winnerState, "斗魂幡", removeStates, battlefield);
        FightingPackage.ChangeProcess(changeFighting, null, result.loser, loserState, "斗魂幡", removeStates, battlefield);

        battlefield.NewEvents.add(finalEvents);
    }

    private void updateSkillPower(ManData manData, int baseSkillId, double powerChange) {
        for (FightingSkill fightingSkill : manData.getSkills()) {
            if (fightingSkill.getSkillid() >= baseSkillId - 4 && fightingSkill.getSkillid() <= baseSkillId + 1) {
                fightingSkill.lingshisld = powerChange;
            }
        }
    }

    private static class BattleResult {
        final ManData loser;
        final ManData winner;

        BattleResult(ManData loser, ManData winner) {
            this.loser = loser;
            this.winner = winner;
        }
    }
}