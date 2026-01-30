package come.tool.FightingDataAction;

import come.tool.FightingData.AddState;
import java.util.List;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingSpellAction.HurtAction;
import come.tool.FightingData.MixDeal;
import come.tool.FightingSpellAction.ControlAction;
import come.tool.FightingData.FightingState;
import java.util.ArrayList;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public class LingTing implements DataAction
{
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        if (manData.xzstate("封印") != null || manData.xzstate("混乱") != null) {
            return;
        }
        FightingSkill gqlx = manData.getSkillType(TypeUtil.BB_GQLX);
        if ((double)Battlefield.random.nextInt(100) > gqlx.getSkillgain()) {
            return;
        }
        List<ManData> datas = new ArrayList<>();
        for (int i = 0; i < battlefield.fightingdata.size(); ++i) {
            if (manData.getCamp() != ((ManData)battlefield.fightingdata.get(i)).getCamp()) {
                AddState addState = ((ManData)battlefield.fightingdata.get(i)).xzstate("1238");
                if (addState != null) {
                    datas.add(battlefield.fightingdata.get(i));
                }
            }
        }
        if (datas.size() == 0) {
            return;
        }
        FightingSkill skill = manData.getSkillName("烈火骄阳");
        if (skill == null) {
            return;
        }
        List<FightingState> Accepterlist = new ArrayList<>();
        FightingState chat = new FightingState();
        chat.setCamp(manData.getCamp());
        chat.setMan(manData.getMan());
        chat.setText("悲莫悲兮生别离，乐莫乐兮归来去。");
        Accepterlist.add(chat);
        FightingState Originator = new FightingState();
        Originator.setEndState(skill.getSkillname());
        Originator.setCamp(manData.getCamp());
        Originator.setMan(manData.getMan());
        double jc = manData.getSpellJC();
        double kbl = 35.0;
        List<FightingSkill> skills = ControlAction.getSkills(manData, skill, battlefield.BattleType);
        for (int j = datas.size() - 1; j >= 0; --j) {
            ManData data = (ManData)datas.get(j);
            if (data.getStates() == 0 && data.xzstate("封印") == null) {
                FightingState Accepter = MixDeal.DSMY(manData, data, skill.getSkillid(), battlefield);
                if (Accepter == null) {
                    data.addBear(skill.getSkilltype());
                    Accepter = new FightingState();
                    double jdsh = gqlx.getSkillhurt() * (double)manData.getLvl() / 15000.0 + (double)(manData.getMp_z() * manData.getLvl() / 1000);
                    HurtAction.hurt((int)skill.getSkillhurt(), jc, jdsh, kbl, skills, Accepterlist, Accepter, manData, data, skill, battlefield);
                }
                else {
                    Accepterlist.add(Accepter);
                }
                Accepter.setSkillskin("801");
            }
        }
        Originator.setStartState("法术攻击");
        Originator.setSkillsy(skill.getSkillname());
        Accepterlist.add(Originator);
        FightingEvents Events = new FightingEvents();
        Events.setAccepterlist(Accepterlist);
        battlefield.NewEvents.add(Events);
    }
}
