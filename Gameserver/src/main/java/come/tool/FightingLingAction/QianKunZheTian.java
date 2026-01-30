package come.tool.FightingLingAction;

import come.tool.FightingData.GroupBuff;
import java.util.Iterator;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.AddState;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.FightingState;
import java.util.ArrayList;
import come.tool.FightingData.SummonType;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingSkill;
import java.util.List;
import come.tool.FightingData.ManData;

public class QianKunZheTian implements LingAction
{
    @Override
    public void lingAction(ManData manData, List<ManData> help, FightingSkill skill, Battlefield battlefield) {
        if (battlefield.battleData.getRound() <= 5) {
            return;
        }
        if (manData.SkillCooling(skill.getSkillid())) {
            return;
        }
        FightingEvents events = new FightingEvents();
        if (Battlefield.random.nextInt(100) <= 30) {
            return;
        }
        for (ManData fightingdatum : battlefield.getFightingdata()) {
            if (fightingdatum.getCamp() == manData.getCamp() && fightingdatum.getType() == 3) {
                SummonType.xianzhi(fightingdatum, skill);
            }
        }
        List<FightingState> Accepterlist = new ArrayList<>();
        FightingState Originator = new FightingState();
        if (manData.daijia(skill, Originator, battlefield)) {
            return;
        }
        List<GroupBuff> buffs = battlefield.addBuff(manData, skill);
        Originator.setStartState("法术攻击");
        Originator.setSkillsy("乾坤遮天");
        Originator.setMan(manData.getMan());
        Originator.setCamp(manData.getCamp());
        if (buffs != null) {
            Originator.setBuff(MixDeal.getBuffStr(buffs, true));
        }
        events.setOriginator(null);
        Accepterlist.add(Originator);
        events.setAccepterlist(Accepterlist);
        battlefield.NewEvents.add(events);
        ChangeFighting changeFighting = new ChangeFighting();
        //添加无敌特效
        List<ManData> nomydata = MixDeal.get(false, null, 1, battlefield.nomy(manData.getCamp()), 1, 0, 0, 0, 15, skill.getCamp(), battlefield, 1);
        FightingEvents fightingEvents2 = new FightingEvents();
        List<FightingState> Accepterlist2 = new ArrayList<>();
        for (ManData nomydatum : nomydata) {
            FightingState org3 = new FightingState();
            org3.setCamp(nomydatum.getCamp());
            org3.setMan(nomydatum.getMan());
            changeFighting = new ChangeFighting();
            changeFighting.setChangetype("无敌");
            changeFighting.setChangevlaue(skill.getSkillgain());
            changeFighting.setChangesum(skill.getSkillcontinued());
            AddState addState = new AddState();
            addState.setStatename("无敌");
            addState.setSurplus(1);
            manData.addAddState("无敌", 0.0, 0.0, 1);
            changeFighting.setChangesum(1);
            FightingPackage.ChangeProcess(changeFighting, null, nomydatum, org3, skill.getSkilltype(), Accepterlist2, battlefield);
            //添加封印效果
            Accepterlist2.add(org3);
        }
        fightingEvents2.setAccepterlist(Accepterlist2);
        battlefield.NewEvents.add(fightingEvents2);
        SummonType.xianzhi(manData, skill); //个人限制
        for (ManData fightingdatum : battlefield.fightingdata) { // 全队限制
            if (fightingdatum.getType() == manData.getType() && fightingdatum.getCamp() == manData.getCamp()) {
                SummonType.xianzhi(manData, skill); //乾坤遮天回合限制
            }
        }
    }
}
