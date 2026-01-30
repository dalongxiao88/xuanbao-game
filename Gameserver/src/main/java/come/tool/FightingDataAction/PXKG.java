package come.tool.FightingDataAction;

import come.tool.FightingData.AddState;
import come.tool.FightingData.GroupBuff;
import come.tool.FightingData.FightingSkill;
import java.util.List;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.FightingState;
import java.util.ArrayList;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public class PXKG implements DataAction
{
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        List<ManData> nomyDatas = MixDeal.getdaji(10, type.equals("混沌技") ? battlefield.nomy(manData.getCamp()) : manData.getCamp(), fightingEvents, battlefield);
        nomyDatas.remove(manData);
        if (nomyDatas.size() == 0) {
            return;
        }
        double mzjc = 0.0;
        double ljjc = 0.0;
        double dsjc = 0.0;
        double dsl = 0.0;
        FightingSkill skill2 = manData.getAppendSkill(9203);
        if (skill2 != null) {
            mzjc += skill2.getSkillhurt();
            skill2 = null;
        }
        GroupBuff buff = battlefield.getBuff(manData.getMan(), TypeUtil.YBYT);
        if (buff != null) {
            mzjc += buff.getValue();
        }
        buff = battlefield.getBuff(manData.getMan(), TypeUtil.BB_E_HYMB);
        if (buff != null) {
            dsjc += buff.getValue();
        }
        AddState addState = manData.xzstate("沧波");
        if (addState != null) {
            mzjc -= addState.getStateEffect();
        }
        FightingSkill skill3 = manData.getAppendSkill(9347);
        if (skill3 != null) {
            mzjc -= skill3.getSkillhurt();
            ljjc -= skill3.getSkillhurt();
        }
        skill3 = manData.getAppendSkill(9811);
        if (skill3 != null) {
            ljjc -= skill3.getSkillhurt();
        }
        int maxg = PhyAttack.GMax(manData, (ManData)nomyDatas.get(0), ljjc, battlefield);
        long Zap = (long)manData.getAp();
        for (int i = 0; i < maxg && manData.getStates() == 0; ++i) {
            FightingEvents gjEvents = new FightingEvents();
            List<FightingState> zls = new ArrayList<>();
            FightingState gjz = new FightingState();
            gjz.setCamp(manData.getCamp());
            gjz.setMan(manData.getMan());
            gjz.setSkillsy("attack");
            gjz.setStartState(TypeUtil.PTGJ);
            gjz.setEndState("3");
            zls.add(gjz);
            for (int j = nomyDatas.size() - 1; j >= 0; --j) {
                ManData data = (ManData)nomyDatas.get(j);
                if (data.getStates() != 0) {
                    nomyDatas.remove(j);
                }
                else {
                    FightingState ace = new FightingState();
                    ace.setCamp(data.getCamp());
                    ace.setMan(data.getMan());
                    zls.add(ace);
                    if (!Battlefield.isV(dsl + dsjc - manData.getQuality().getRolefmzl() - mzjc - manData.mz)) {
                        ChangeFighting acec = new ChangeFighting();
                        long ap = (long)PhyAttack.Hurt(Zap, i + 1, manData, data, type, ace, zls, battlefield, 0.0, 0.0);
                        acec.setChangehp((int)(-ap));
                        FightingPackage.ChangeProcess(acec, manData, data, ace, TypeUtil.PTGJ, zls, battlefield);
                        PhyAttack.neidan(type, manData, data, ap, battlefield, zls, i + 1, 0, 0.0);
                    }
                    else {
                        ace.setStartState("技能");
                        ace.setProcessState("躲闪");
                    }
                }
            }
            gjEvents.setAccepterlist(zls);
            battlefield.NewEvents.add(gjEvents);
        }
    }
}
