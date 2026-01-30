package come.tool.FightingSpellAction;

import java.util.List;
import come.tool.FightingData.FightingState;
import java.util.ArrayList;
import org.come.tool.Arith;
import com.gl.util.FaMenUtil;
import come.tool.FightingData.SummonType;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public class FM_QXAction implements SpellAction
{
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        if (skill.getSkillid() == 22026 && manData.getfamencs() <= 0) {
            return;
        }
        FightingState Originator = events.getOriginator();
        SummonType.xianzhi(manData, skill);
        int huiheSum = 0;
        int skillId = skill.getSkillid();
        int fmsld = (int)manData.getFmsld(skill.getSkillid());
        if (skill.getSkillid() == 22000) {
            huiheSum = FaMenUtil.getInt(skillId, fmsld, 1);
            double floor = FaMenUtil.getDouble(skillId + "", fmsld, 4);
            double replyHp = Arith.mul((double)manData.getHp_z(), floor);
            manData.addAddState("回血", (replyHp <= 0.0) ? 1.0 : replyHp, 0.0, huiheSum);
        }
        else if (skill.getSkillid() == 22012) {
            huiheSum = FaMenUtil.getInt(skillId, fmsld, 2);
        }
        else if (skill.getSkillid() == 22026) {
            huiheSum = FaMenUtil.getInt(skillId, fmsld, 1);
            manData.setfamencs(0);
        }
        else if (skill.getSkillid() == 22032) {
            huiheSum = FaMenUtil.getInt(skillId, fmsld, 2);
        }
        else if (skill.getSkillid() == 22034) {
            huiheSum = FaMenUtil.getInt(skillId, fmsld, 4);
        }
        manData.addAddState(skill.getSkilltype(), (double)skillId, (double)fmsld, huiheSum);
        Originator.setStartState("法术攻击");
        Originator.setEndState_1(skill.getSkilltype());
        events.setOriginator(null);
        List<FightingState> Accepterlist = new ArrayList<>();
        Accepterlist.add(Originator);
        events.setAccepterlist(Accepterlist);
        battlefield.NewEvents.add(events);
    }
}
