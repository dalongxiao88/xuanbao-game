package come.tool.FightingSpellAction;

import java.util.List;
import org.come.model.Skill;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.FightingState;
import java.util.ArrayList;
import come.tool.FightingData.MixDeal;
import org.come.server.GameServer;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public class TJ_LFAction implements SpellAction
{
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        Skill skillXls = GameServer.getSkill("1268");
        List<ManData> datas = MixDeal.getdaji((int)skillXls.getValue1(), manData.getCamp(), events, battlefield);
        List<FightingState> Accepterlist = new ArrayList<>();
        FightingState Originator = events.getOriginator();
        Originator.setStartState("法术攻击");
        Originator.setSkillsy(skill.getSkillname());
        if (manData.daijia(skill, Originator, battlefield)) {
            return;
        }
        Originator.setText("群魔乱世，雷罚净化！#24");
        Originator.setSkillskin("11276");
        for (int i = 0; i < datas.size(); ++i) {
            FightingState fightingState2 = new FightingState();
            ManData data2 = (ManData)datas.get(i);
            ChangeFighting fighting2 = new ChangeFighting();
            fighting2.setChangetype("技能");
            fightingState2.setSkillskin("112661");
            int is = (int)(manData.getShanghai() * (double)skillXls.getSkilllevel() * skillXls.getGrow() * skillXls.getValue() + (double)(manData.getMp_z() / 50));
            fighting2.setChangehp(-is);
            FightingPackage.ChangeProcess(fighting2, null, data2, fightingState2, "技能", Accepterlist, battlefield);
            FightingState fightingState3 = new FightingState();
            ManData data3 = (ManData)datas.get(i);
            ChangeFighting fighting3 = new ChangeFighting();
            fighting3.setChangetype("技能");
            fighting3.setChangehp(-is / 2);
            FightingPackage.ChangeProcess(fighting3, null, data3, fightingState3, "至圣", Accepterlist, battlefield);
        }
        if (events.getOriginator() != null) {
            Accepterlist.add(Originator);
        }
        events.setOriginator(null);
        events.setAccepterlist(Accepterlist);
        battlefield.NewEvents.add(events);
    }
}
