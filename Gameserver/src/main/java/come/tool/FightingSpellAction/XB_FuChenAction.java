package come.tool.FightingSpellAction;

import come.tool.FightingData.*;
import come.tool.FightingDataAction.PhyAttack;
import java.util.*;

public class XB_FuChenAction implements SpellAction {

    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        Random random = new Random();
        int sum = random.nextInt(2) + 5;
        skill.setSkillsum(sum);
        skill.setSkilltype("风");

        // 获取目标阵营和数据
        int nocamp = MixDeal.getcamp("螭纹佩", manData.getCamp(), battlefield.nomy(manData.getCamp()));
        ManData nomyData = PhyAttack.getdaji(nocamp, events, battlefield, manData, 0.0);

        List<FightingState> accepterList = new ArrayList<>();
        List<ManData> targets = MixDeal.getjieshou(events, skill, manData, accepterList, battlefield);

        if (targets.isEmpty()) {
            handleNoTarget(manData, skill, events, accepterList, battlefield);
        } else if (!manData.daijia(skill, new FightingState(), battlefield)) {
            if (nomyData != null) {
                processSkillEffect(manData, skill, events, targets, nomyData, battlefield);
            }
        }
    }

    private void handleNoTarget(ManData manData, FightingSkill skill, FightingEvents events,
                                List<FightingState> accepterList, Battlefield battlefield) {
        FightingState originator = events.getOriginator();
        if (!manData.daijia(skill, originator, battlefield)) {
            originator.setStartState("法术攻击");
            originator.setSkillsy(skill.getSkillname());
            events.setOriginator(null);
            accepterList.add(originator);
            events.setAccepterlist(accepterList);
            battlefield.NewEvents.add(events);
        }
    }

    private void processSkillEffect(ManData manData, FightingSkill skill, FightingEvents events,
                                    List<ManData> targets, ManData nomyData, Battlefield battlefield) {
        Random random = new Random();
        int mainTargetMan = nomyData.getMan();
        int mainTargetCamp = nomyData.getCamp();
        double spellCoefficient = manData.getSpellJC();
        double physicalDodge = manData.getWGTB();

        // 创建移动效果事件
        createMoveEffect(manData, targets, battlefield);

        // 处理状态转移效果
        boolean isSpecialTarget = targets.get(0).getType() != 0 && targets.get(0).getType() != 1;
        boolean hasStateTransfer = Battlefield.isV(isSpecialTarget ? skill.getP1() * 0.5 : skill.getP1());
        boolean hasStateExtract = Battlefield.isV(isSpecialTarget ? skill.getSkillgain() + skill.getS1() * 0.5 : skill.getSkillgain() + skill.getS1());

        if (hasStateExtract) {
            processStateTransfer(manData, skill, targets, mainTargetCamp, mainTargetMan, hasStateTransfer, isSpecialTarget, battlefield);
        }

        // 处理伤害效果
        processDamageEffect(manData, skill, targets, spellCoefficient, physicalDodge, battlefield);
    }

    private void createMoveEffect(ManData manData, List<ManData> targets, Battlefield battlefield) {
        FightingEvents moveEvents = new FightingEvents();
        List<FightingState> moveStates = new ArrayList<>();

        // 添加施法者状态
        FightingState casterState = new FightingState();
        casterState.setStartState("法术攻击");
        casterState.setCamp(manData.getCamp());
        casterState.setMan(manData.getMan());
        casterState.setSkillskin("30013");
        moveStates.add(casterState);

        // 添加目标移动状态
        for (ManData target : targets) {
            FightingState moveState = new FightingState();
            moveState.setCamp(target.getCamp());
            moveState.setMan(target.getMan());
            moveState.setStartState("移动");
            moveState.setEndState(manData.getCamp() + "|" + manData.getMan() + "|" + 8);
            moveStates.add(moveState);
        }

        moveEvents.setAccepterlist(moveStates);
        battlefield.NewEvents.add(moveEvents);
    }

    private void processStateTransfer(ManData manData, FightingSkill skill, List<ManData> targets,
                                      int mainTargetCamp, int mainTargetMan, boolean hasStateTransfer,
                                      boolean isSpecialTarget, Battlefield battlefield) {
        Random random = new Random();
        List<AddState> extractedStates = new ArrayList<>();
        List<Integer> targetIndices = new ArrayList<>();

        // 提取状态
        double extractChance = isSpecialTarget ? skill.getE1() * 0.5 : skill.getE1();
        for (int i = 0; i < targets.size(); i++) {
            ManData target = targets.get(i);
            targetIndices.add(i);
            boolean isMainTarget = target.getCamp() == mainTargetCamp && target.getMan() == mainTargetMan;
            if (isMainTarget && Battlefield.isV(isSpecialTarget ? skill.getSkillgain1() + skill.getS1() * 0.5 : skill.getSkillgain1() + skill.getS1())) {
                if (Battlefield.isV(skill.getE1())) {
                    extractedStates.addAll(extractStates(target, hasStateTransfer, extractChance));
                }
            } else {
                extractedStates.addAll(extractStates(target, hasStateTransfer, extractChance));
            }
        }

        Map<Integer, AddState> strengthStates = new HashMap<>();
        Map<Integer, AddState> resistanceStates = new HashMap<>();
        Map<Integer, AddState> speedStates = new HashMap<>();
        Map<Integer, AddState> healStates = new HashMap<>();
        Map<Integer, AddState> fbJjlStates = new HashMap<>();
        Map<Integer, AddState> fbHlzStates = new HashMap<>();
        Map<Integer, AddState> fbYmgsStates = new HashMap<>();
        Map<Integer, AddState> fbDscStates = new HashMap<>();
        Map<Integer, AddState> fbJqbStates = new HashMap<>();

        for (AddState state : extractedStates) {
            String stateName = state.getStatename();
            Map<Integer, AddState> targetMap = getStateTargetMap(stateName, strengthStates, resistanceStates,
                    speedStates, healStates, fbJjlStates, fbHlzStates, fbYmgsStates, fbDscStates, fbJqbStates);

            if (targetMap != null) {
                int randomIndex;
                do {
                    randomIndex = random.nextInt(targets.size());
                } while (targetMap.get(randomIndex) != null);
                targetMap.put(randomIndex, state);
            }
        }

        // 应用状态到目标
        applyStatesToTargets(targets, targetIndices, strengthStates, resistanceStates, speedStates, healStates,
                fbJjlStates, fbHlzStates, fbYmgsStates, fbDscStates, fbJqbStates, hasStateTransfer, battlefield);
    }

    private Map<Integer, AddState> getStateTargetMap(String stateName, Map<Integer, AddState>... stateMaps) {
        switch (stateName) {
            case "力量": return stateMaps[0];
            case "抗性": return stateMaps[1];
            case "加速": return stateMaps[2];
            case "甘霖": return stateMaps[3];
            case "fbJjl": return stateMaps[4];
            case "fbHlz": return stateMaps[5];
            case "fbYmgs": return stateMaps[6];
            case "fbDsc": return stateMaps[7];
            case "fbJqb": return stateMaps[8];
            default: return null;
        }
    }

    private void applyStatesToTargets(List<ManData> targets, List<Integer> targetIndices,
                                      Map<Integer, AddState> strengthStates, Map<Integer, AddState> resistanceStates,
                                      Map<Integer, AddState> speedStates, Map<Integer, AddState> healStates,
                                      Map<Integer, AddState> fbJjlStates, Map<Integer, AddState> fbHlzStates,
                                      Map<Integer, AddState> fbYmgsStates, Map<Integer, AddState> fbDscStates,
                                      Map<Integer, AddState> fbJqbStates, boolean hasStateTransfer,
                                      Battlefield battlefield) {
        Collections.shuffle(targetIndices);
        Map<String, List<AddState>> targetStateMap = new HashMap<>();

        // 收集每个目标的状态
        for (int index : targetIndices) {
            ManData target = targets.get(index);
            List<AddState> stateList = new ArrayList<>();

            addStateToList(strengthStates, index, stateList);
            addStateToList(speedStates, index, stateList);
            addStateToList(resistanceStates, index, stateList);
            addStateToList(healStates, index, stateList);
            addStateToList(fbJjlStates, index, stateList);
            addStateToList(fbHlzStates, index, stateList);
            addStateToList(fbYmgsStates, index, stateList);
            addStateToList(fbDscStates, index, stateList);
            addStateToList(fbJqbStates, index, stateList);
            targetStateMap.put(target.getManname(), stateList);
        }

        // 创建状态应用事件
        FightingEvents stateEvents = new FightingEvents();
        List<FightingState> stateList = new ArrayList<>();
        String[] stateTypes = hasStateTransfer ?
                new String[]{"力量", "抗性", "加速", "甘霖", "fbJjl", "fbHlz", "fbYmgs", "fbDsc", "fbJqb"} :
                new String[]{"力量", "抗性", "加速", "甘霖"};

        // 应用状态到每个目标
        for (ManData target : targets) {
            // 添加状态移除标记
            for (String stateType : stateTypes) {
                FightingState removeState = new FightingState();
                removeState.setCamp(target.getCamp());
                removeState.setMan(target.getMan());
                removeState.setEndState_2(stateType);
                stateList.add(removeState);
            }

            target.RemoveAbnormal(null, stateTypes);

            // 添加新状态
            List<AddState> statesToAdd = targetStateMap.get(target.getManname());
            if (statesToAdd != null && !statesToAdd.isEmpty()) {
                for (AddState state : statesToAdd) {
                    FightingState addStateMarker = new FightingState();
                    addStateMarker.setCamp(target.getCamp());
                    addStateMarker.setMan(target.getMan());
                    target.addAddState(state.getStatename(), state.getStateEffect(), state.getStateEffect2(), state.getSurplus());
                    addStateMarker.setEndState_1(state.getStatename());
                    stateList.add(addStateMarker);
                }
            }
        }

        stateEvents.setAccepterlist(stateList);
        battlefield.NewEvents.add(stateEvents);
    }

    private void addStateToList(Map<Integer, AddState> stateMap, int index, List<AddState> stateList) {
        if (stateMap.get(index) != null) {
            stateList.add(stateMap.get(index));
        }
    }

    private void processDamageEffect(ManData manData, FightingSkill skill, List<ManData> targets,
                                     double spellCoefficient, double physicalDodge, Battlefield battlefield) {
        FightingEvents damageEvents = new FightingEvents();
        List<FightingState> damageStates = new ArrayList<>();

        for (ManData target : targets) {
            FightingState moveState = new FightingState();
            double baseDamage = skill.getSkillhurt();
            int resistance = 0;

            moveState.setCamp(target.getCamp());
            moveState.setMan(target.getMan());
            moveState.setStartState("移动");
            moveState.setEndState(target.getCamp() + "|" + target.getMan());

            ChangeFighting damageChange = new ChangeFighting();
            FightingState damageMarker = new FightingState();
            damageMarker.setCamp(target.getCamp());
            damageMarker.setMan(target.getMan());

            // 计算抗性加成
            double resistanceBonus = MixDeal.addition(resistance, damageMarker, manData, target, "风");

            // PVP伤害调整
            if (PK_MixDeal.isPK(battlefield.BattleType)) {
                baseDamage *= 0.7;
            }

            // 计算最终伤害
            int finalDamage = Calculation.getCalculation().SMHurt(manData, target, baseDamage, physicalDodge, "风",
                    manData.getCamp() == 1 ? battlefield.MyDeath : battlefield.NoDeath);

            if (resistanceBonus != 0.0) {
                finalDamage = (int) (finalDamage * resistanceBonus);
            }

            finalDamage = (int) (finalDamage * spellCoefficient);
            damageChange.setChangehp(-finalDamage);

            FightingPackage.ChangeProcess(damageChange, null, target, damageMarker, "风", damageStates, battlefield);
            damageStates.add(moveState);
        }

        damageEvents.setAccepterlist(damageStates);
        battlefield.NewEvents.add(damageEvents);
    }

    public List<AddState> extractStates(ManData target, boolean hasSpecialStates, Double extractChance) {
        List<AddState> extractedStates = new ArrayList<>();
        String stateTypes = hasSpecialStates ? "力量,抗性,加速,甘霖,fbJjl,fbHlz,fbYmgs,fbDsc,fbJqb" : "力量,抗性,加速,甘霖";

        for (int i = target.getAddStates().size() - 1; i >= 0; i--) {
            AddState state = target.getAddStates().get(i);
            String stateName = state.getStatename();

            if (stateTypes.contains(stateName)) {
                double chanceModifier = (target.getType() != 0 && target.getType() != 2) ? 0.5 : 1.0;
                if (Battlefield.isV((100.0 - extractChance) * chanceModifier)) {
                    extractedStates.add(state);
                    target.getAddStates().remove(i);
                }
            }
        }

        return extractedStates;
    }

    public void addstata(ManData data, List<AddState> addStates, Battlefield battlefield) {
        FightingEvents stateEvents = new FightingEvents();
        List<FightingState> stateMarkers = new ArrayList<>();

        for (AddState state : addStates) {
            FightingState marker = new FightingState();
            marker.setCamp(data.getCamp());
            marker.setMan(data.getMan());
            data.addAddState(state.getStatename(), state.getStateEffect(), state.getStateEffect2(), state.getSurplus());
            marker.setEndState_1(state.getStatename());
            stateMarkers.add(marker);
        }

        stateEvents.setAccepterlist(stateMarkers);
        battlefield.NewEvents.add(stateEvents);
    }
}