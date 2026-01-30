package come.tool.FightingSpellAction;

import come.tool.FightingData.GroupBuff;
import java.util.List;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.FightingState;
import come.tool.FightingDataAction.PhyAttack;
import come.tool.FightingData.PK_MixDeal;
import come.tool.FightingData.TypeUtil;
import java.util.ArrayList;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public class BB_SRPZAction implements SpellAction
{
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        List<ManData> nomyDatas = MixDeal.getdaji(2, manData.getCamp(), events, battlefield);
        if (nomyDatas.size() == 0) {
            return;
        }
        MixDeal.BB_SRPZ(nomyDatas, battlefield, manData, null, skill);
        MixDeal.skillmove(nomyDatas, battlefield, manData, "10");
        List<FightingEvents> gjEventss = new ArrayList<>();
        long zap = 0L;
        long zap2 = 0L;
        long zap3 = 0L;
        FightingSkill skill_4 = null;
        FightingSkill skill_5 = null;
        FightingSkill skill_6 = null;
        FightingSkill skill_7 = null;
        FightingSkill skill_8 = null;
        for (int i = manData.getSkills().size() - 1; i >= 0; --i) {
            FightingSkill sl = (FightingSkill)manData.getSkills().get(i);
            if (sl.getSkillbeidong() == 1) {
                if (sl.getSkilltype().equals(TypeUtil.BB_E_TCDK)) {
                    skill_4 = sl;
                }
                else if (sl.getSkilltype().equals(TypeUtil.BB_E_YJBM)) {
                    if (!PK_MixDeal.isPK(battlefield.BattleType)) {
                        skill_5 = sl;
                    }
                }
                else if (sl.getSkilltype().equals(TypeUtil.BB_E_HSSF)) {
                    skill_6 = sl;
                }
                else if (sl.getSkilltype().equals(TypeUtil.BB_E_DSFS)) {
                    skill_7 = sl;
                    zap2 = (long)(manData.getKangluobao() * 175.0);
                }
                else if (sl.getSkilltype().equals(TypeUtil.BB_E_QKYZ)) {
                    skill_8 = sl;
                    zap3 = PhyAttack.getMoney(manData, battlefield);
                    if (zap3 >= 500000L) {
                        zap3 = 500000L;
                    }
                    zap3 = (long)((double)zap3 / 2.8);
                }
            }
        }
        List<ManData> zzs = null;
        if (skill_4 != null && Battlefield.isV(skill_4.getSkillgain())) {
            zzs = new ArrayList<>();
            for (int j = battlefield.fightingdata.size() - 1; j >= 0; --j) {
                ManData data = (ManData)battlefield.fightingdata.get(j);
                if (data.getType() == 1 && data.getCamp() == manData.getCamp() && data.getStates() == 0 && data.getMan() != manData.getMan()) {
                    zap += (long)data.getAp();
                    zzs.add(data);
                }
            }
            if (zap >= 150000L) {
                zap = 150000L;
            }
        }
        int Zap = manData.getAp();
        for (int k = nomyDatas.size() - 1; k >= 0; --k) {
            ManData data2 = (ManData)nomyDatas.get(k);
            int maxg = (int)(1.0 + (manData.getQuality().getRolefljv() - 1.0) * 0.6);
            if (maxg <= 2) {
                maxg = 2;
            }
            if (PK_MixDeal.isPK(battlefield.BattleType)) {
                GroupBuff buff = battlefield.getBuff(data2.getCamp(), TypeUtil.BB_QZ);
                if (buff != null && maxg > 3) {
                    maxg = 3;
                }
            }
            int g = 0;
            while (data2.getStates() == 0 && g < maxg) {
                if (gjEventss.size() <= g) {
                    FightingEvents gjEvents = new FightingEvents();
                    List<FightingState> zls1 = new ArrayList<>();
                    gjEvents.setAccepterlist(zls1);
                    gjEventss.add(gjEvents);
                }
                List<FightingState> zls2 = ((FightingEvents)gjEventss.get(g)).getAccepterlist();
                ++g;
                long ap = (k == 0) ? ((long)Zap) : ((long)((double)Zap * skill.getSkillhurt() / 100.0));
                if (k == 0) {
                    ap += zap;
                }
                FightingState ace = new FightingState();
                if (skill_7 != null && Battlefield.isV(skill_7.getSkillgain())) {
                    ap += zap2;
                    ace.setText("大圣附身#2");
                    ace.setSkillskin(TypeUtil.BB_E_DSFS);
                }
                else if (skill_8 != null && Battlefield.isV(skill_8.getSkillgain())) {
                    ap += zap3;
                    ace.setText("乾坤一掷#2");
                    ace.setSkillskin(TypeUtil.BB_E_QKYZ);
                }
                ace.setStartState("被攻击");
                ap = (long)PhyAttack.Hurt(ap, 1, manData, data2, TypeUtil.PTGJ, ace, zls2, battlefield, 0.0, 0.0);
                ChangeFighting acec = new ChangeFighting();
                acec.setChangehp((int)(-ap));
                if (k == 0 && skill_5 != null && Battlefield.isV(skill_5.getSkillgain())) {
                    FightingPackage.ChangeProcess(acec, null, data2, ace, TypeUtil.ZSSH, zls2, battlefield);
                    ace.setText("不堪一击的选手#2");
                }
                else {
                    FightingPackage.ChangeProcess(acec, null, data2, ace, TypeUtil.PTGJ, zls2, battlefield);
                }
                if (k == 0) {
                    PhyAttack.feedback(TypeUtil.PTGJ, manData, ap, battlefield, zls2);
                }
                PhyAttack.neidan(TypeUtil.PTGJ, manData, data2, (long)Zap, battlefield, zls2, (k == 0) ? 3 : 4, k, skill.getSkillgain());
                if (k == 0 && skill_6 != null && Battlefield.isV(skill_6.getSkillgain() / 20.0)) {
                    List<ManData> datas = battlefield.getZW(data2);
                    for (int l = datas.size() - 1; l >= 0; --l) {
                        FightingState ace2 = new FightingState();
                        ManData nomyData2 = (ManData)datas.get(l);
                        if (nomyData2.getStates() == 0) {
                            ChangeFighting acec2 = new ChangeFighting();
                            ap = (long)PhyAttack.Hurt((long)((double)Zap * skill_6.getSkillgain() / 100.0), g, manData, nomyData2, TypeUtil.PTGJ, ace2, zls2, battlefield, 0.0, 0.0);
                            acec2.setChangehp((int)(-ap));
                            FightingPackage.ChangeProcess(acec2, null, nomyData2, ace2, TypeUtil.PTGJ, zls2, battlefield);
                        }
                    }
                }
                FightingState move = MixDeal.skillmove(data2, manData, "9");
                zls2.add(move);
                if (k == 0 && zap != 0L && zzs != null) {
                    move.setText("合力一击#2");
                    for (int m = 0; m < zzs.size(); ++m) {
                        ManData zz = (ManData)zzs.get(m);
                        zls2.add(MixDeal.skillmove(data2, zz, "9"));
                    }
                }
            }
        }
        for (int k = 0; k < gjEventss.size(); ++k) {
            battlefield.NewEvents.add(gjEventss.get(k));
        }
    }
}
