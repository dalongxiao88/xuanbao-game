package come.tool.FightingDataAction;

import come.tool.FightingData.AddState;
import come.tool.FightingData.FightingSkill;
import java.util.List;
import come.tool.FightingLingAction.LingActionType;
import come.tool.FightingData.FightingState;
import java.util.ArrayList;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public class Lingbao implements DataAction
{
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        if (manData.xzstate(TypeUtil.TZ_SGJQ) != null) {
            return;
        }
        List<ManData> ful = battlefield.getFu(manData);
        FightingSkill skill = battlefield.getBaoSkill(manData);
        if (skill == null) {
            return;
        }
        int zltype = 0;
        int id = skill.getSkillid();
        if (id >= 3001 && id <= 3010) {
            zltype = 1;
        }
        else if (id == 3011 || id == 3012) {
            zltype = 2;
        }
        else if ((id >= 3013 && id <= 3023) || id == 3032) {
            zltype = 3;
        }
        else if (id == 3033) {
            zltype = 4;
        }
        else if (id == 3026) {
            zltype = 5;
        }
        else if (id >= 3029 && id <= 3031) {
            zltype = 6;
        }
        else if (id == 3024) {
            zltype = 7;
        }
        else if (id == 3027) {
            zltype = 9;
        }
        else if (id == 3028) {
            zltype = 8;
        }
        List<FightingState> Accepterlist = new ArrayList<>();
        FightingState org = new FightingState();
        org.setCamp(manData.getCamp());
        org.setMan(manData.getMan());
        org.setEndState_2("清除灵宝发动");
        Accepterlist.add(org);
        for (int i = ful.size() - 1; i >= 0; --i) {
            FightingState org2 = new FightingState();
            org2.setCamp(((ManData)ful.get(i)).getCamp());
            org2.setMan(((ManData)ful.get(i)).getMan());
            org2.setEndState_2("清除灵宝发动");
            Accepterlist.add(org2);
        }
        FightingEvents fightingEvents2 = new FightingEvents();
        fightingEvents2.setAccepterlist(Accepterlist);
        battlefield.NewEvents.add(fightingEvents2);
        int size = battlefield.NewEvents.size();
        if (zltype == 0) {
            return;
        }
        LingActionType.getActionById(zltype).lingAction(manData, ful, skill, battlefield);
        int size2 = battlefield.NewEvents.size();
        if (size2 > size) {
            if (skill.getSkilllvl() >= 4) {
                FightingEvents fightingEvents3 = new FightingEvents();
                FightingState org3 = new FightingState();
                org3.setCamp(1);
                org3.setMan(1);
                org3.setStartState("发动灵宝合击");
                fightingEvents3.setOriginator(org3);
                battlefield.NewEvents.add(battlefield.NewEvents.size() - 1, fightingEvents3);
            }
            FightingSkill zh = manData.getSkillType("招魂");
            if (zh != null) {
                LingActionType.getActionById(10).lingAction(manData, ful, skill, battlefield);
            }
            AddState gjhl = manData.xzstate("高级回灵");
            if (gjhl != null) {
                LingActionType.getActionById(11).lingAction(manData, ful, skill, battlefield);
            }
            if (skill.getSkilllvl() >= 4) {
                FightingEvents fightingEvents4 = new FightingEvents();
                FightingState org4 = new FightingState();
                org4.setCamp(1);
                org4.setMan(1);
                org4.setStartState("结束灵宝合击");
                fightingEvents4.setOriginator(org4);
                battlefield.NewEvents.add(fightingEvents4);
            }
        }
    }
    
    public static List<ManData> getHelp(List<ManData> datas, ManData manData) {
        List<ManData> ful = new ArrayList<>();
        for (int i = datas.size() - 1; i >= 0; --i) {
            ManData data = (ManData)datas.get(i);
            if (data.getType() == 3 && data.getCamp() == manData.getCamp() && data.getStates() == 0 && data.getMan() != manData.getMan() && isxy(data.getQihe()) && data.xzstate(TypeUtil.TZ_SGJQ) == null) {
                ful.add(data);
            }
        }
        return ful;
    }
    
    public FightingSkill getskill(int size) {
        return null;
    }
    
    public static boolean isxy(long qh) {
        return (double)Battlefield.random.nextInt(100) < Math.pow((double)qh, 0.16) + 80.0;
    }
    
    public static void main(String[] args) {
        long qh = 5000000L;
        System.out.println(Math.pow((double)qh, 0.16) + 10.0);
    }
}
