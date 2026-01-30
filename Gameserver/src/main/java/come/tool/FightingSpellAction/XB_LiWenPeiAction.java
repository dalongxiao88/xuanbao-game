package come.tool.FightingSpellAction;

import come.tool.FightingData.*;
import come.tool.FightingDataAction.PhyAttack;
import java.util.ArrayList;
import java.util.List;

public class XB_LiWenPeiAction implements SpellAction {

    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        int targetCamp = MixDeal.getcamp("螭纹佩", manData.getCamp(), battlefield.nomy(manData.getCamp()));
        ManData mainTarget = PhyAttack.getdaji(targetCamp, events, battlefield, manData, 0.0);
        List<FightingState> accepterList = new ArrayList<>();

        if (mainTarget == null) {
            handleNoTarget(manData, skill, events, accepterList, battlefield);
        } else if (!manData.daijia(skill, new FightingState(), battlefield)) {
            // 直接处理技能效果，不再单独创建施法动作
            processSkillEffect(manData, skill, events, mainTarget, battlefield);
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
                                    ManData mainTarget, Battlefield battlefield) {
        double spellCoefficient = manData.getSpellJC();
        double physicalDodge = manData.getWGTB();

        // 创建一个统一的事件来处理所有效果
        List<FightingState> allEffects = new ArrayList<>();

        // 添加施法者状态到同一个事件中
        FightingState casterState = events.getOriginator();
        casterState.setStartState("法术攻击");
        allEffects.add(casterState);

        // 获取作用目标列表（包括主目标和周围目标）
        List<ManData> targets = battlefield.getZW(mainTarget);
        targets.add(mainTarget);

        for (ManData target : targets) {
            FightingState targetState = new FightingState();
            int baseDamage = (int) skill.getSkillgain();

            // 设置目标状态
            targetState.setCamp(target.getCamp());
            targetState.setMan(target.getMan());
            targetState.setSkillskin("1054");

            // 处理主目标的特殊效果
            if (isMainTarget(target, mainTarget)) {
                // 主目标添加特殊状态
                target.addAddState("螭纹佩",
                        (int) (skill.getSkillgain1() + skill.getP1()),
                        skill.getSkillhurt() + skill.getP2(),
                        skill.getS1(), 2);
                targetState.setEndState_1("螭纹佩");
            } else {
                // 周围目标伤害调整
                baseDamage = (int) (baseDamage * (skill.getSkillhitrate() + skill.getE1()) / 100.0);
            }

            // 计算最终伤害
            int finalDamage = calculateFinalDamage(manData, target, baseDamage, physicalDodge,
                    spellCoefficient, battlefield);

            // 应用伤害效果 - 直接在当前事件中处理
            applyDamageEffect(target, finalDamage, targetState, allEffects, battlefield);
//            allEffects.add(targetState);
        }

        // 设置事件的处理结果并添加到战场
        events.setOriginator(null);
        events.setAccepterlist(allEffects);
        battlefield.NewEvents.add(events);
    }

    private boolean isMainTarget(ManData target, ManData mainTarget) {
        return target.getCamp() == mainTarget.getCamp() && target.getMan() == mainTarget.getMan();
    }

    private int calculateFinalDamage(ManData caster, ManData target, int baseDamage,
                                     double physicalDodge, double spellCoefficient,
                                     Battlefield battlefield) {
        int resistance = 0;
        FightingState dummyState = new FightingState();
        dummyState.setCamp(target.getCamp());
        dummyState.setMan(target.getMan());

        // 计算抗性加成
        double resistanceBonus = MixDeal.addition(resistance, dummyState, caster, target, "水");

        // PVP伤害调整
        if (PK_MixDeal.isPK(battlefield.BattleType)) {
            baseDamage = (int) (baseDamage * 0.7);
        }

        // 基础伤害计算
        int damage = Calculation.getCalculation().SMHurt(caster, target, baseDamage, physicalDodge, "水",
                caster.getCamp() == 1 ? battlefield.MyDeath : battlefield.NoDeath);

        // 应用抗性修正
        if (resistanceBonus != 0.0) {
            damage = (int) (damage * resistanceBonus);
        }

        // 应用法术系数
        damage = (int) (damage * spellCoefficient);

        return damage;
    }

    private void applyDamageEffect(ManData target, int damage, FightingState targetState,
                                   List<FightingState> effectStates, Battlefield battlefield) {
        ChangeFighting damageChange = new ChangeFighting();
        damageChange.setChangehp(-damage);

        FightingPackage.ChangeProcess(damageChange, null, target, targetState, "水", effectStates, battlefield);
    }
}