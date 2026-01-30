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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class XB_FuxiQingAction implements SpellAction {

    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        List<FightingState> accepterList = new ArrayList<>();
        List<ManData> targetDatas = MixDeal.getjieshou(events, skill, manData, accepterList, battlefield);

        if (targetDatas.isEmpty()) {
            handleNoTargetCase(manData, skill, events, accepterList, battlefield);
        } else {
            handleWithTargets(manData, skill, events, targetDatas, accepterList, battlefield);
        }
    }

    private void handleNoTargetCase(ManData manData, FightingSkill skill, FightingEvents events,
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

    private void handleWithTargets(ManData manData, FightingSkill skill, FightingEvents events,
                                   List<ManData> targetDatas, List<FightingState> accepterList,
                                   Battlefield battlefield) {
        // 获取主要目标
        FightingState firstTargetState = events.getAccepterlist().get(0);
        ManData primaryTarget = battlefield.getdata(firstTargetState.getCamp(), firstTargetState.getMan());

        FightingEvents moveEvents = new FightingEvents();
        List<FightingState> moveStates = new ArrayList<>();

        // 添加施法者动作状态
        addCasterActionState(manData, moveStates);

        Random random = new Random();
        double spellCoefficient = manData.getSpellJC();
        double physicalDodge = manData.getWGTB();
        int additionalAttackCount = 0;

        // 处理每个目标
        for (ManData targetData : targetDatas) {
            FightingState targetMove = processSingleTarget(manData, targetData, primaryTarget, skill,
                    random, spellCoefficient, physicalDodge,
                    moveStates, battlefield);

            moveStates.add(targetMove);

            // 检查是否触发额外攻击
            if (shouldTriggerAdditionalAttack(targetData, additionalAttackCount, skill)) {
                triggerAdditionalAttack(manData, moveStates);
                additionalAttackCount++;
            }
        }

        moveEvents.setAccepterlist(moveStates);
        battlefield.NewEvents.add(moveEvents);
    }

    private void addCasterActionState(ManData manData, List<FightingState> moveStates) {
        FightingState casterState = new FightingState();
        casterState.setStartState("法术攻击");
        casterState.setCamp(manData.getCamp());
        casterState.setMan(manData.getMan());
        moveStates.add(casterState);
    }

    private FightingState processSingleTarget(ManData manData, ManData targetData, ManData primaryTarget,
                                              FightingSkill skill, Random random, double spellCoefficient,
                                              double physicalDodge, List<FightingState> moveStates,
                                              Battlefield battlefield) {
        FightingState targetMove = new FightingState();
        int baseDamage = calculateBaseDamage(skill, random);

        // 设置目标状态
        targetMove.setCamp(targetData.getCamp());
        targetMove.setMan(targetData.getMan());
        targetMove.setSkillskin("3003");

        // 计算伤害
        int finalDamage = calculateFinalDamage(manData, targetData, primaryTarget, skill,
                baseDamage, physicalDodge, battlefield);

        // 应用伤害效果
        applyDamageEffect(targetData, finalDamage, moveStates, battlefield);

        return targetMove;
    }

    private int calculateBaseDamage(FightingSkill skill, Random random) {
        double minDamage = skill.getSkillgain();
        double maxDamage = skill.getSkillhurt();
        return (int) (minDamage + random.nextInt((int) (maxDamage - minDamage)));
    }

    private int calculateFinalDamage(ManData manData, ManData targetData, ManData primaryTarget,
                                     FightingSkill skill, int baseDamage, double physicalDodge,
                                     Battlefield battlefield) {
        int damage = baseDamage;

        // 主要目标伤害加成
        if (primaryTarget == targetData) {
            damage = (int) (damage * (1.0 + skill.getS1() / 100.0));
        }

        // 特殊效果伤害加成
        if (Battlefield.isV(skill.getE1())) {
            damage = (int) (damage * 1.5);
        }

        if (targetData.getType() == 2) {
            damage = (int) (damage * 1.3);
        }
        return Calculation.getCalculation().SMHurt(manData, targetData, damage, physicalDodge,
                "伏羲琴", manData.getCamp() == 1 ? battlefield.MyDeath : battlefield.NoDeath);
    }

    private void applyDamageEffect(ManData targetData, int damage, List<FightingState> moveStates,
                                   Battlefield battlefield) {
        ChangeFighting changeFighting = new ChangeFighting();
        changeFighting.setChangehp(-damage);

        FightingState damageState = new FightingState();
        damageState.setCamp(targetData.getCamp());
        damageState.setMan(targetData.getMan());

        FightingPackage.ChangeProcess(changeFighting, null, targetData, damageState,
                "伏羲琴", moveStates, battlefield);
    }

    private boolean shouldTriggerAdditionalAttack(ManData targetData, int currentCount, FightingSkill skill) {
        return targetData.getStates() == 1 &&
                currentCount < 3 &&
                Battlefield.isV(skill.getP1());
    }

    private void triggerAdditionalAttack(ManData manData, List<FightingState> moveStates) {
        FightingState additionalState = new FightingState();
        additionalState.setCamp(manData.getCamp());
        additionalState.setMan(manData.getMan());
        additionalState.setXy_c(1);
        moveStates.add(additionalState);
        manData.addXYZ(1);
    }
}