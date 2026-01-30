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

public class TJ_DZQKAction implements SpellAction
{
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        Skill skillXls = GameServer.getSkill("1267");
        List<ManData> datas = MixDeal.getdaji((int)skillXls.getValue1(), manData.getCamp(), events, battlefield);
        List<FightingState> Accepterlist = new ArrayList<>();
        FightingState Originator = events.getOriginator();
        Originator.setStartState("法术攻击");
        Originator.setSkillsy(skill.getSkillname());
        if (manData.daijia(skill, Originator, battlefield)) {
            return;
        }
        Originator.setText("群魔乱舞，倒转乾坤！#24");
        Originator.setSkillskin("11270");
        for (int i = 0; i < datas.size(); ++i) {
            FightingState fightingState = new FightingState();
            ManData data = (ManData)datas.get(i);
            ChangeFighting fighting = new ChangeFighting();
            fighting.setChangetype("技能");
            fightingState.setSkillskin("112701");
            int is = (int)(manData.getShanghai() * (double)skillXls.getSkilllevel() * skillXls.getGrow() * skillXls.getValue() + (double)(manData.getMp_z() / 50));
            fighting.setChangehp(-is);
            FightingPackage.ChangeProcess(fighting, null, data, fightingState, "技能", Accepterlist, battlefield);
        }
        if (events.getOriginator() != null) {
            Accepterlist.add(Originator);
        }
        events.setOriginator(null);
        events.setAccepterlist(Accepterlist);
        battlefield.NewEvents.add(events);
    }
}
