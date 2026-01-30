package come.tool.FightingSpellAction;

import org.come.model.Skill;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.FightingState;
import java.util.ArrayList;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.SummonType;
import org.come.server.GameServer;
import java.util.Random;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;
import come.tool.FightingData.FightingEvents;
import java.util.List;

public class TJ_MJFSAction implements SpellAction
{
    public List<FightingEvents> NewEvents;
    
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        Random rand = new Random();
        Skill skillXls = GameServer.getSkill("1271");
        int jl = (int)(skillXls.getGrow1() + 1.0);
        SummonType.xianzhi(manData, skill);
        List<ManData> datas = MixDeal.getdaji((int)skillXls.getValue1(), manData.getCamp(), events, battlefield);
        List<FightingState> Accepterlist = new ArrayList<>();
        FightingState Originator = events.getOriginator();
        Originator.setStartState("法术攻击");
        Originator.setSkillsy(skill.getSkillname());
        if (manData.daijia(skill, Originator, battlefield)) {
            return;
        }
        Originator.setText("苦海无涯！回头是岸！#167");
        Originator.setSkillskin("11279");
        for (int i = 0; i < datas.size(); ++i) {
            FightingState fightingState = new FightingState();
            ManData data = (ManData)datas.get(i);
            ChangeFighting fighting = new ChangeFighting();
            fighting.setChangetype("技能");
            fightingState.setSkillskin("112791");
            int is = (int)(manData.getShanghai() * (double)skillXls.getSkilllevel() * skillXls.getGrow() * skillXls.getValue() + (double)(manData.getMp_z() / 50));
            fighting.setChangehp(-is);
            FightingPackage.ChangeProcess(fighting, null, data, fightingState, "技能", Accepterlist, battlefield);
            int ysl = rand.nextInt(100) + 1;
            if (ysl < jl) {
                data.addAddState(TypeUtil.TZ_HTSA, 0.0, 0.0, 2);
                fightingState.setCamp(data.getCamp());
                fightingState.setMan(data.getMan());
                fightingState.setEndState_1(TypeUtil.TZ_HTSA);
                fightingState.setSkillskin("6025");
                fightingState.setStartState("代价");
                Accepterlist.add(fightingState);
            }
        }
        if (events.getOriginator() != null) {
            Accepterlist.add(Originator);
        }
        events.setOriginator(null);
        events.setAccepterlist(Accepterlist);
        battlefield.NewEvents.add(events);
    }
}
