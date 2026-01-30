package come.tool.FightingSpellAction;

import come.tool.FightingData.AddState;
import java.util.List;
import org.come.until.GsonUtil;
import come.tool.FightingData.SummonType;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.PK_MixDeal;
import java.util.ArrayList;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public class TYSkillAction implements SpellAction
{
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        FightingState Originator = new FightingState();
        if (manData.daijia(skill, Originator, battlefield)) {
            return;
        }
        List<FightingState> Accepterlist = new ArrayList<>();
        Originator.setCamp(manData.getCamp());
        Originator.setMan(manData.getMan());
        Originator.setStartState("代价");
        FightingEvents ksevents = new FightingEvents();
        ksevents.setCurrentRound(battlefield.CurrentRound);
        Accepterlist.add(Originator);
        ksevents.setAccepterlist(Accepterlist);
        battlefield.NewEvents.add(ksevents);
        int szie = 0;
        if (PK_MixDeal.isPK(battlefield.BattleType)) {
            FightingSkill TY_L_10131 = manData.getSkillType(TypeUtil.TY_L_10131);
            if (TY_L_10131 != null && Battlefield.isV(TY_L_10131.getSkillhurt())) {
                szie = 1;
            }
        }
        FightingSkill skill2 = manData.skillId(MixDeal.getTYSkillId(skill.getSkillid()));
        if (skill2 == null) {
            return;
        }
        int id = skill2.getSkillid();
        if (id == 9130 || id == 9151 || id == 9169 || id == 9170 || id == 9189 || id == 9190 || id == 9207 || id == 9208 || id == 9231 || id == 9232 || id == 9250 || id == 9251 || id == 9252 || id == 9286 || id == 9287 || id == 9307 || id == 9350 || id == 9352) {
            SummonType.xianzhi(manData, skill);
        }
        AddState addState = this.skillState(skill, manData);
        if (addState != null) {
            manData.getAddStates().add(addState);
        }
        if (addState != null && addState.getStatename().equals(TypeUtil.TY_FY_ARXH)) {
            FightingState Originator2 = new FightingState();
            Originator2.setStartState("法术攻击");
            Originator2.setCamp(manData.getCamp());
            Originator2.setMan(manData.getMan());
            Originator2.setEndState_1("9111");
            Accepterlist.add(Originator2);
            SummonType.xianzhi(manData, skill);
            return;
        }
        int num = skill2.getSkillsum();
        skill2.setSkillsum(this.skillNum(skill, num) + szie);
        int skillcontinued = skill2.getSkillcontinued();
        if (!PK_MixDeal.isPK(battlefield.BattleType) && skill.getSkillid() == Integer.parseInt(TypeUtil.TY_LL_QXGR)) {
            FightingSkill TY_LL_CHYJ = manData.getSkillType(TypeUtil.TY_LL_CHYJ);
            if (TY_LL_CHYJ != null && Battlefield.isV(TY_LL_CHYJ.getSkillhurt())) {
                ++skillcontinued;
            }
        }
        skill2.setSkillcontinued(this.skillContinued(skill, skillcontinued));
        if (id == 1025) {
            if (skill != null && skill.getSkillid() == 9189) {
                String json = GsonUtil.getGsonUtil().getgson().toJson(skill2);
                skill2 = (FightingSkill)GsonUtil.getGsonUtil().getgson().fromJson(json, FightingSkill.class);
                skill2.setSkillblue(0);
                FightingState f = new FightingState();
                f.setMan(manData.getMan());
                f.setCamp(manData.getCamp());
                manData.addnq(-skill.getSkillblue(), f);
                Accepterlist.add(f);
            }
            SpellActionType.getActionById(4).spellAction(manData, skill2, events, battlefield);
        }
        else if (id == 1029 || id == 1030 || id == 1034 || id == 1035 || id == 1039 || id == 1040) {
            SpellActionType.getActionById(5).spellAction(manData, skill2, events, battlefield);
        }
        else if (id == 1005 || id == 1010 || id == 1015 || id == 1020 || id == 1075) {
            SpellActionType.getActionById(1).spellAction(manData, skill2, events, battlefield);
        }
        else if (id == 1045 || id == 1055 || id == 1065) {
            SpellActionType.getActionById(11).spellAction(manData, skill2, events, battlefield);
        }
        else if (id == 1094 || id == 1095) {
            SpellActionType.getActionById(21).spellAction(manData, skill, events, battlefield);
        }
        else if (id == 1085 || id == 1099 || id == 1100 || id == 1089 || id == 1090) {
            SpellActionType.getActionById(20).spellAction(manData, skill, events, battlefield);
        }
        if (addState != null) {
            manData.getAddStates().remove(addState);
        }
        skill2.setSkillsum(num);
        skill2.setSkillcontinued(skillcontinued);
    }
    
    public int skillNum(FightingSkill skill, int num) {
        if (skill.getSkillid() == 9130 || skill.getSkillid() == 9151 || skill.getSkillid() == 9171) {
            return (int)skill.getSkillhurt();
        }
        if (skill.getSkillid() == 9352 || skill.getSkillid() == 9307) {
            return 10;
        }
        if (skill.getSkillid() == 9251) {
            return (int)skill.getSkillhurt();
        }
        if (skill.getSkillid() == 9372) {
            return (int)((double)num + skill.getSkillhurt());
        }
        if (skill.getSkillid() == 9612) {
            return (int)((double)num + skill.getSkillgain());
        }
        return num;
    }
    
    public AddState skillState(FightingSkill skill, ManData data) {
        if (skill.getSkillid() == 9169 || skill.getSkillid() == 9350 || skill.getSkillid() == 9352 || skill.getSkillid() == 9207 || skill.getSkillid() == 9208 || skill.getSkillid() == 9231 || skill.getSkillid() == 9232 || skill.getSkillid() == 9250 || skill.getSkillid() == 9252 || skill.getSkillid() == 9286 || skill.getSkillid() == 9287 || skill.getSkillid() == 9307 || skill.getSkillid() == 9372 || skill.getSkillid() == 9132 || skill.getSkillid() == 9957 || skill.getSkillid() == 10016 || skill.getSkillid() == 10080 || skill.getSkillid() == 10121 || skill.getSkillid() == 9810 || skill.getSkillid() == 9812 || skill.getSkillid() == 10047) {
            return new AddState(skill.getSkilltype(), skill.getSkillhurt(), 0);
        }
        if (skill.getSkillid() == 9857 || skill.getSkillid() == 9870 || skill.getSkillid() == 9881 || skill.getSkillid() == 10055) {
            SummonType.xianzhi(data, skill);
            return new AddState(skill.getSkilltype(), skill.getSkillhurt(), skill.getSkillgain(), 0);
        }
        if (skill.getSkillid() == 9170) {
            return new AddState("哀鸿遍野", skill.getSkillhurt(), 0);
        }
        if (skill.getSkillid() == 9111) {
            return new AddState(TypeUtil.TY_FY_ARXH, skill.getSkillhurt(), 2);
        }
        return null;
    }
    
    public int skillContinued(FightingSkill skill, int skillcontinued) {
        if (skill.getSkillid() == 9207 || skill.getSkillid() == 9208 || skill.getSkillid() == 9232 || skill.getSkillid() == 9252) {
            return 3;
        }
        if (skill.getSkillid() == 9231) {
            return 2;
        }
        if (skill.getSkillid() == 9250) {
            return 5;
        }
        if (skill.getSkillid() == 9251) {
            return (int)skill.getSkillgain();
        }
        return skillcontinued;
    }
}
