package come.tool.FightingDataAction;

import come.tool.FightingData.*;
import come.tool.FightingSpellAction.SpellActionType;
import org.come.model.Skill;
import org.come.server.GameServer;

import java.util.ArrayList;
import java.util.List;

/**
 * 青白娘子
 *
 * @author 随风
 */
public class QBNZ implements DataAction {

    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents,
                               String type, Battlefield battlefield) {
        //素手 白娘子牺牲自身当前气血的20%，对己方一个单位进行气血回复，回复量为所牺牲气血的一定比例。此技能需处于白娘子形态下方可使用。
        //青鳞 小青牺牲自身当前气血的20%，对敌方一个单位施放特殊的鹤顶红粉，造成的毒法伤害为基础伤害并额外附加一定比例的所牺牲气血值，毒法持续两回合。此技能需处于小青形态下方可使用。
        //度厄 青白娘子天生内蕴双灵，进入战场时以白娘子形态示人。使用技能后可回复自身25%的气血，并从当前白娘子/小青形态切换至另一形态。
        // TODO Auto-generated method stub
        if (type.equals(TypeUtil.BB_DE)) {//度厄
            FightingEvents events2 = new FightingEvents();
            List<FightingState> fightingStates = new ArrayList<>();
            FightingSkill skill = manData.getSkillType(type);
            //技能生效 扣除代价
            FightingState Originator = fightingEvents.getOriginator();
            if (manData.daijia(skill, Originator, battlefield)) {
                return;
            }//扣除代价
            fightingStates.add(Originator);


            FightingState fightingState = new FightingState();
            ChangeFighting changeFighting = new ChangeFighting();
            changeFighting.setChangehp((int) (manData.getHp_z() * 0.25));
            manData.ChangeData(changeFighting, fightingState);
            fightingState.setStartState("法术攻击");
            fightingState.setSkillsy(skill.getSkillname());

            String[] m = manData.getModel().split("_");
            int sk = 0;
            for (int i = 0; i < m.length; i++) {
                if (m[i] != null) {
                    if (m[i].equals("605")) {
                        sk = 606;
                        break;
                    } else if (m[i].equals("606")) {
                        sk = 605;
                        break;
                    } else if (m[i].equals("607")) {
                        sk = 608;
                        break;
                    } else if (m[i].equals("608")) {
                        sk = 607;
                        break;
                    }
                }
            }
            if(sk == 606||sk == 608){
                for (int i = 0; i < manData.getSkills().size(); i++) {
                    FightingSkill oldSkill = manData.getSkills().get(i);
                    if (oldSkill.getSkilltype() != null && oldSkill.getSkilltype().equals(TypeUtil.BB_SUSHOU)) {
                        Skill skilllll = GameServer.getSkill("1289");
                        FightingSkill NewSkill = new FightingSkill(skilllll, manData.getlvl(), manData.getZs(), 1, 1, 0);
                        NewSkill.setSkillcontinued(2);
                        manData.getSkills().set(i, NewSkill); // 替换元素
                        break;
                    }
                }
            } else if(sk == 607||sk == 605){
                for (int i = 0; i < manData.getSkills().size(); i++) {
                    FightingSkill oldSkill = manData.getSkills().get(i);
                    if (oldSkill.getSkilltype() != null && oldSkill.getSkilltype().equals(TypeUtil.BB_QL)) {
                        Skill skilllll = GameServer.getSkill("1288");
                        FightingSkill NewSkill = new FightingSkill(skilllll, manData.getlvl(), manData.getZs(), 1, 1, 0);
                        manData.getSkills().set(i, NewSkill); // 替换元素
                        break;
                    }
                }
            }
            if (sk != 0) {
                manData.UPModel(fightingState, sk + "");
            }
            fightingStates.add(fightingState);
            events2.setAccepterlist(fightingStates);
            battlefield.NewEvents.add(events2);
            return;
        } else if (type.equals("青鳞")) {
            FightingEvents events2 = new FightingEvents();
            List<FightingState> fightingStates = new ArrayList<>();
            FightingSkill skill = manData.getSkillType(type);
            //技能生效 扣除代价
            FightingState Originator = fightingEvents.getOriginator();
            if (manData.daijia(skill, Originator, battlefield)) {
                return;
            }//扣除代价
//            fightingStates.add(Originator);


            FightingState fightingState = new FightingState();
            ChangeFighting changeFighting = new ChangeFighting();
            int hhpp = (int) (manData.getHp() * 0.20);
            changeFighting.setChangehp(-hhpp);
            manData.ChangeData(changeFighting, fightingState);
            fightingState.setStartState(null);
            fightingStates.add(fightingState);
            events2.setAccepterlist(fightingStates);
            battlefield.NewEvents.add(events2);


            Skill skilllll = GameServer.getSkill("1019");
            FightingSkill fightingSkill = new FightingSkill(skilllll, manData.getlvl(), manData.getZs(), 10000, 1, 0);
            fightingSkill.setSkillcontinued(2);//2回合
            fightingSkill.setSkillblue(0);
            double qds = manData.getQuality().getQzds();
            manData.getQuality().setQzds(qds + hhpp * 0.0001*skill.getSkillhurt());
            SpellActionType.getActionById(1).spellAction(manData, fightingSkill, fightingEvents, battlefield);
            manData.getQuality().setQzds(qds);//还原强度
            return;
        } else if (type.equals("素手")) {
            FightingEvents events2 = new FightingEvents();
            List<FightingState> fightingStates = new ArrayList<>();
            FightingSkill skill = manData.getSkillType(type);
            //技能生效 扣除代价
            FightingState Originator = fightingEvents.getOriginator();
            if (manData.daijia(skill, Originator, battlefield)) {
                return;
            }//扣除代价
            fightingStates.add(Originator);


            FightingState fightingState = new FightingState();
            ChangeFighting changeFighting = new ChangeFighting();
            int hhpp = (int) (manData.getHp() * 0.2 * 0.01*skill.getSkillhurt());
            changeFighting.setChangehp(-(int) (manData.getHp() * 0.2));
            manData.ChangeData(changeFighting, fightingState);
            fightingState.setStartState("法术攻击");
            fightingStates.add(fightingState);
//            events2.setAccepterlist(fightingStates);
//            battlefield.NewEvents.add(events2);


            //选人判断
            ManData data = null;
            if (fightingEvents.getAccepterlist() != null && fightingEvents.getAccepterlist().size() != 0) {
                FightingState ac = fightingEvents.getAccepterlist().get(0);
                if (ac.getCamp() != manData.getCamp() || ac.getMan() != manData.getMan()) {
                    int path = battlefield.Datapathhuo(ac.getCamp(), ac.getMan());
                    if (path != -1) {
                        data = battlefield.fightingdata.get(path);
                    }
                }
            }
            if (data == null) {
                List<Integer> lists = new ArrayList<>();
                for (int i = 0; i < battlefield.fightingdata.size(); i++) {
                    ManData data2 = battlefield.fightingdata.get(i);
                    if (data2.getStates() == 0 && data2.getType() != 3 && data2.getType() != 4) {
                        if (data2.getCamp() == manData.getCamp() && data2.getMan() == manData.getMan()) continue;
                        lists.add(i);
                    }
                }
                if (lists.size() != 0)
                    data = battlefield.fightingdata.get(Battlefield.random.nextInt(lists.size()));

            }
            if (data == null) {
                Originator = fightingEvents.getOriginator();
                if (manData.daijia(Originator, battlefield)) {
                    return;
                }
                Originator.setStartState("法术攻击");
                fightingEvents.setOriginator(null);
                List<FightingState> Accepterlist = new ArrayList<>();
                Accepterlist.add(Originator);
                fightingEvents.setAccepterlist(Accepterlist);
                battlefield.NewEvents.add(fightingEvents);
                return;
            }

            FightingState fightingState1 = new FightingState();
            ChangeFighting changeFighting1 = new ChangeFighting();
            changeFighting1.setChangehp((int) (hhpp*1.0));//素手技能系数
            data.ChangeData(changeFighting1, fightingState1);
            fightingState1.setSkillskin("1288");//素手特效
            fightingStates.add(fightingState1);
            events2.setAccepterlist(fightingStates);
            battlefield.NewEvents.add(events2);

            return;
        }


    }
}
