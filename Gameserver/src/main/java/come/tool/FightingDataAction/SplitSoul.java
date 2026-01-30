package come.tool.FightingDataAction;

import come.tool.FightingData.GroupBuff;
import java.util.List;
import come.tool.FightingData.FightingPackage;
import java.util.Random;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.FightingState;
import java.util.ArrayList;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.PK_MixDeal;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public class SplitSoul implements DataAction
{
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        boolean isHSSF = TypeUtil.BB_E_HSSF.equals(type);
        type = TypeUtil.PTGJ;
        if (PK_MixDeal.isPK(battlefield.BattleType)) {
            FightingSkill fightingSkill = manData.getAttacks("暗影离魂").getSkill();
            int sum = fightingSkill.getSkillsum() + (((double)Battlefield.random.nextInt(100) < fightingSkill.getSkillgain()) ? 1 : 0);
            List<ManData> nomyDatas = MixDeal.getdaji(sum, manData.getCamp(), fightingEvents, battlefield);
            if (nomyDatas.size() == 0) {
                return;
            }
            FightingEvents gjEvents = new FightingEvents();
            List<FightingState> list = new ArrayList<>();
            FightingState gjz = new FightingState();
            gjz.setCamp(manData.getCamp());
            gjz.setMan(manData.getMan());
            gjz.setStartState("特效1");
            list.add(gjz);
            for (int i = nomyDatas.size() - 1; i >= 0; --i) {
                ManData data = (ManData)nomyDatas.get(i);
                FightingState move = new FightingState();
                move.setCamp(manData.getCamp());
                move.setMan(manData.getMan());
                move.setSkillskin("4000");
                move.setStartState("技能移动");
                move.setEndState(data.getCamp() + "|" + data.getMan() + "|3");
                list.add(move);
            }
            gjEvents.setAccepterlist(list);
            battlefield.NewEvents.add(gjEvents);
            long ap = 0L;
            long zap = 0L;
            long zap2 = 0L;
            long zap3 = 0L;
            List<FightingEvents> gjEventss = new ArrayList<>();
            FightingSkill skill_4 = null;
            FightingSkill skill_5 = null;
            FightingSkill skill_6 = null;
            FightingSkill skill_7 = null;
            FightingSkill skill_8 = null;
            for (int j = manData.getSkills().size() - 1; j >= 0; --j) {
                FightingSkill sl = (FightingSkill)manData.getSkills().get(j);
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
                for (int k = battlefield.fightingdata.size() - 1; k >= 0; --k) {
                    ManData data2 = (ManData)battlefield.fightingdata.get(k);
                    if (data2.getType() == 1 && data2.getCamp() == manData.getCamp() && data2.getStates() == 0 && data2.getMan() != manData.getMan()) {
                        zap += (long)data2.getAp();
                        zzs.add(data2);
                    }
                }
                if (zap >= 150000L) {
                    zap = 150000L;
                }
            }
            long sbke = 0L;
            for (int l = nomyDatas.size() - 1; l >= 0; --l) {
                ManData data3 = (ManData)nomyDatas.get(l);
                double ljl = manData.getQuality().getRolefljl() + (double)manData.ljv - (double)((data3.getSkillType(TypeUtil.TJ_YCDY) == null) ? 0 : 15);
                int ljv = (int)manData.getQuality().getRolefljv();
                FightingSkill skill5 = manData.getAppendSkill(9811);
                if (skill5 != null) {
                    ljl -= skill5.getSkillhurt();
                }
                if (l != 0) {
                    ljl *= 0.7;
                    ljv = (int)((double)ljv * 0.7);
                }
                int maxg = 3;
                if (PK_MixDeal.isPK(battlefield.BattleType)) {
                    GroupBuff buff = battlefield.getBuff(data3.getCamp(), TypeUtil.BB_QZ);
                    if (buff != null && maxg > 3) {
                        maxg = 3;
                    }
                }
                int g = 0;
                while (data3.getStates() == 0 && g < maxg) {
                    if (gjEventss.size() <= g) {
                        FightingEvents gjEvents2 = new FightingEvents();
                        List<FightingState> zls1 = new ArrayList<>();
                        gjEvents2.setAccepterlist(zls1);
                        gjEventss.add(gjEvents2);
                    }
                    List<FightingState> zls2 = ((FightingEvents)gjEventss.get(g)).getAccepterlist();
                    int hmgs = manData.executeHmsz(zls2);
                    if (hmgs == 2) {
                        sbke = (long)manData.getAp();
                    }
                    else {
                        sbke = 0L;
                    }
                    if (g == 0 && maxg > 1) {
                        maxg = manData.executeJtpa(list, maxg);
                    }
                    ap = (long)manData.getAp();
                    if (l != 0) {
                        ap = (long)(int)((double)ap * fightingSkill.getSkillhurt() / 100.0);
                    }
                    if (l == 0) {
                        ap += zap;
                    }
                    ++g;
                    FightingState ace = new FightingState();
                    ace.setStartState("被攻击");
                    if (g == 1) {
                        ace.setSkillskin("4001");
                    }
                    else {
                        ace.setSkillskin((g % 3 == 1) ? "4002" : ((g % 3 == 2) ? "4003" : "4004"));
                    }
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
                    ap = (long)PhyAttack.Hurt(sbke + ap, g, manData, data3, type, ace, zls2, battlefield, 0.0, 0.0);
                    ChangeFighting acec = new ChangeFighting();
                    acec.setChangehp((int)(-ap));
                    if (l == 0 && skill_5 != null && Battlefield.isV(skill_5.getSkillgain()) && (double)new Random().nextInt(100) > data3.getQuality().getKyjbm()) {
                        FightingPackage.ChangeProcess(acec, null, data3, ace, TypeUtil.ZSSH, zls2, battlefield);
                        ace.setText("不堪一击的选手#2");
                    }
                    else {
                        FightingPackage.ChangeProcess(acec, null, data3, ace, TypeUtil.PTGJ, zls2, battlefield);
                    }
                    if (l == 0) {
                        PhyAttack.feedback(type, manData, ap, battlefield, zls2);
                    }
                    PhyAttack.neidan(type, manData, data3, ap, battlefield, zls2, g, l, 0.0);
                    if (l == 0 && zap != 0L && zzs != null) {
                        ace.setText("合力一击#2");
                        for (int m = 0; m < zzs.size(); ++m) {
                            ManData zz = (ManData)zzs.get(m);
                            zls2.add(MixDeal.skillmove(data3, zz, "9"));
                        }
                    }
                    if (l == 0 && skill_6 != null && (isHSSF || Battlefield.isV(skill_6.getSkillgain() / 20.0))) {
                        List<ManData> datas = battlefield.getZW(data3);
                        for (int j2 = datas.size() - 1; j2 >= 0; --j2) {
                            FightingState ace2 = new FightingState();
                            ManData nomyData2 = (ManData)datas.get(j2);
                            if (nomyData2.getStates() == 0) {
                                ChangeFighting acec2 = new ChangeFighting();
                                ap = (long)PhyAttack.Hurt((long)((double)sbke + (double)manData.getAp() * skill_6.getSkillgain() / 100.0), g, manData, nomyData2, type, ace2, zls2, battlefield, 0.0, 0.0);
                                acec2.setChangehp((int)(-ap));
                                FightingPackage.ChangeProcess(acec2, null, nomyData2, ace2, type, zls2, battlefield);
                            }
                        }
                    }
                    if (data3.getStates() != 0 || data3.xzstate("封印") != null) {
                        g = maxg;
                        if (data3.getStates() != 0) {
                            manData.executeYwwq(gjz);
                        }
                        else {
                            continue;
                        }
                    }
                }
                manData.addDun(ap, gjz);
            }
            for (int l = 0; l < gjEventss.size(); ++l) {
                battlefield.NewEvents.add(gjEventss.get(l));
            }
            ManData nomyData3 = (ManData)nomyDatas.get(0);
            if (nomyData3.getStates() != 0) {
                FightingSkill skill6 = manData.getSkillType("追击");
                if (skill6 != null && (double)(Battlefield.random.nextInt(100) + 1) < manData.getSkillType("追击").getSkillhurt()) {
                    List<ManData> zjdata = MixDeal.get(false, nomyData3, 0, manData.getCamp(), 0, 0, 0, 0, 1, -1, battlefield, 1);
                    if (zjdata.size() != 0) {
                        nomyData3 = (ManData)zjdata.get(0);
                        FightingEvents events = new FightingEvents();
                        FightingState org = new FightingState();
                        org.setCamp(manData.getCamp());
                        org.setMan(manData.getMan());
                        org.setStartState(TypeUtil.PTGJ);
                        events.setOriginator(org);
                        List<FightingState> acs = new ArrayList<>();
                        FightingState ac = new FightingState();
                        ac.setCamp(nomyData3.getCamp());
                        ac.setMan(nomyData3.getMan());
                        acs.add(ac);
                        manData.executeNbkj(1);
                        events.setAccepterlist(acs);
                        DataActionType.getActionById(1).analysisAction(manData, events, "追击", battlefield);
                        return;
                    }
                }
            }
            if (manData != null && manData.getStates() == 0 && manData.executeZhanyi()) {
                List<ManData> zjdata2 = MixDeal.get(false, nomyData3, 0, manData.getCamp(), 0, 0, 0, 0, 1, -1, battlefield, 1);
                if (zjdata2.size() != 0) {
                    nomyData3 = (ManData)zjdata2.get(0);
                    FightingEvents events2 = new FightingEvents();
                    FightingState org2 = new FightingState();
                    org2.setCamp(manData.getCamp());
                    org2.setMan(manData.getMan());
                    org2.setStartState(TypeUtil.PTGJ);
                    org2.setText("看我的冲冠一怒");
                    org2.setSkillskin("cgyn");
                    events2.setOriginator(org2);
                    List<FightingState> acs2 = new ArrayList<>();
                    FightingState ac2 = new FightingState();
                    ac2.setCamp(nomyData3.getCamp());
                    ac2.setMan(nomyData3.getMan());
                    acs2.add(ac2);
                    manData.executeNbkj(1);
                    events2.setAccepterlist(acs2);
                    DataActionType.getActionById(1).analysisAction(manData, events2, "分裂", battlefield);
                    return;
                }
            }
            FightingSkill skill6 = manData.getSkillType("分裂");
            if (skill6 != null && (double)(Battlefield.random.nextInt(100) + 1) < manData.getSkillType("分裂").getSkillhurt()) {
                List<ManData> zjdata = MixDeal.get(false, nomyData3, 0, manData.getCamp(), 0, 0, 0, 0, 1, -1, battlefield, 1);
                if (zjdata.size() != 0) {
                    nomyData3 = (ManData)zjdata.get(0);
                    FightingEvents events = new FightingEvents();
                    FightingState org = new FightingState();
                    org.setCamp(manData.getCamp());
                    org.setMan(manData.getMan());
                    org.setStartState(TypeUtil.PTGJ);
                    events.setOriginator(org);
                    List<FightingState> acs = new ArrayList<>();
                    FightingState ac = new FightingState();
                    ac.setCamp(nomyData3.getCamp());
                    ac.setMan(nomyData3.getMan());
                    acs.add(ac);
                    manData.executeNbkj(1);
                    events.setAccepterlist(acs);
                    DataActionType.getActionById(1).analysisAction(manData, events, "分裂", battlefield);
                    return;
                }
            }
        }
        else {
            FightingSkill fightingSkill = manData.getAttacks("暗影离魂").getSkill();
            int sum = fightingSkill.getSkillsum() + (((double)Battlefield.random.nextInt(100) < fightingSkill.getSkillgain()) ? 1 : 0);
            sum = 2;
            List<ManData> nomyDatas = MixDeal.getdaji(sum, manData.getCamp(), fightingEvents, battlefield);
            if (nomyDatas.size() == 0) {
                return;
            }
            FightingEvents gjEvents = new FightingEvents();
            List<FightingState> list = new ArrayList<>();
            FightingState gjz = new FightingState();
            gjz.setCamp(manData.getCamp());
            gjz.setMan(manData.getMan());
            gjz.setStartState("特效1");
            list.add(gjz);
            for (int i = nomyDatas.size() - 1; i >= 0; --i) {
                ManData data = (ManData)nomyDatas.get(i);
                FightingState move = new FightingState();
                move.setCamp(manData.getCamp());
                move.setMan(manData.getMan());
                move.setSkillskin("4000");
                move.setStartState("技能移动");
                move.setEndState(data.getCamp() + "|" + data.getMan() + "|3");
                list.add(move);
            }
            gjEvents.setAccepterlist(list);
            battlefield.NewEvents.add(gjEvents);
            long ap = 0L;
            long zap = 0L;
            long zap2 = 0L;
            long zap3 = 0L;
            List<FightingEvents> gjEventss = new ArrayList<>();
            FightingSkill skill_4 = null;
            FightingSkill skill_5 = null;
            FightingSkill skill_6 = null;
            FightingSkill skill_7 = null;
            FightingSkill skill_8 = null;
            for (int j = manData.getSkills().size() - 1; j >= 0; --j) {
                FightingSkill sl = (FightingSkill)manData.getSkills().get(j);
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
                for (int k = battlefield.fightingdata.size() - 1; k >= 0; --k) {
                    ManData data2 = (ManData)battlefield.fightingdata.get(k);
                    if (data2.getType() == 1 && data2.getCamp() == manData.getCamp() && data2.getStates() == 0 && data2.getMan() != manData.getMan()) {
                        zap += (long)data2.getAp();
                        zzs.add(data2);
                    }
                }
                if (zap >= 150000L) {
                    zap = 150000L;
                }
            }
            long sbke = 0L;
            for (int l = nomyDatas.size() - 1; l >= 0; --l) {
                ManData data3 = (ManData)nomyDatas.get(l);
                double ljl = manData.getQuality().getRolefljl() + (double)manData.ljv - (double)((data3.getSkillType(TypeUtil.TJ_YCDY) == null) ? 0 : 15);
                int ljv = (int)manData.getQuality().getRolefljv();
                FightingSkill skill5 = manData.getAppendSkill(9811);
                if (skill5 != null) {
                    ljl -= skill5.getSkillhurt();
                }
                if (l != 0) {
                    ljl *= 0.7;
                    ljv = (int)((double)ljv * 0.7);
                }
                int maxg = 1 + (((double)Battlefield.random.nextInt(100) < ljl) ? ljv : 0);
                if (PK_MixDeal.isPK(battlefield.BattleType)) {
                    GroupBuff buff = battlefield.getBuff(data3.getCamp(), TypeUtil.BB_QZ);
                    if (buff != null && maxg > 3) {
                        maxg = 3;
                    }
                }
                int g = 0;
                while (data3.getStates() == 0 && g < maxg) {
                    if (gjEventss.size() <= g) {
                        FightingEvents gjEvents2 = new FightingEvents();
                        List<FightingState> zls1 = new ArrayList<>();
                        gjEvents2.setAccepterlist(zls1);
                        gjEventss.add(gjEvents2);
                    }
                    List<FightingState> zls2 = ((FightingEvents)gjEventss.get(g)).getAccepterlist();
                    int hmgs = manData.executeHmsz(zls2);
                    if (hmgs == 2) {
                        sbke = (long)manData.getAp();
                    }
                    else {
                        sbke = 0L;
                    }
                    if (g == 0 && maxg > 1) {
                        maxg = manData.executeJtpa(list, maxg);
                    }
                    ap = (long)manData.getAp();
                    if (l != 0) {
                        ap = (long)(int)((double)ap * fightingSkill.getSkillhurt() / 100.0);
                    }
                    if (l == 0) {
                        ap += zap;
                    }
                    ++g;
                    FightingState ace = new FightingState();
                    ace.setStartState("被攻击");
                    if (g == 1) {
                        ace.setSkillskin("4001");
                    }
                    else {
                        ace.setSkillskin((g % 3 == 1) ? "4002" : ((g % 3 == 2) ? "4003" : "4004"));
                    }
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
                    ap = (long)PhyAttack.Hurt(sbke + ap, g, manData, data3, type, ace, zls2, battlefield, 0.0, 0.0);
                    ChangeFighting acec = new ChangeFighting();
                    acec.setChangehp((int)(-ap));
                    if (l == 0 && skill_5 != null && Battlefield.isV(skill_5.getSkillgain())) {
                        FightingPackage.ChangeProcess(acec, null, data3, ace, TypeUtil.ZSSH, zls2, battlefield);
                        ace.setText("不堪一击的选手#2");
                    }
                    else {
                        FightingPackage.ChangeProcess(acec, null, data3, ace, TypeUtil.PTGJ, zls2, battlefield);
                    }
                    if (l == 0) {
                        PhyAttack.feedback(type, manData, ap, battlefield, zls2);
                    }
                    PhyAttack.neidan(type, manData, data3, ap, battlefield, zls2, g, l, 0.0);
                    if (l == 0 && zap != 0L && zzs != null) {
                        ace.setText("合力一击#2");
                        for (int m = 0; m < zzs.size(); ++m) {
                            ManData zz = (ManData)zzs.get(m);
                            zls2.add(MixDeal.skillmove(data3, zz, "9"));
                        }
                    }
                    if (l == 0 && skill_6 != null && (isHSSF || Battlefield.isV(skill_6.getSkillgain() / 20.0))) {
                        List<ManData> datas = battlefield.getZW(data3);
                        for (int j2 = datas.size() - 1; j2 >= 0; --j2) {
                            FightingState ace2 = new FightingState();
                            ManData nomyData2 = (ManData)datas.get(j2);
                            if (nomyData2.getStates() == 0) {
                                ChangeFighting acec2 = new ChangeFighting();
                                ap = (long)PhyAttack.Hurt((long)((double)sbke + (double)manData.getAp() * skill_6.getSkillgain() / 100.0), g, manData, nomyData2, type, ace2, zls2, battlefield, 0.0, 0.0);
                                acec2.setChangehp((int)(-ap));
                                FightingPackage.ChangeProcess(acec2, null, nomyData2, ace2, type, zls2, battlefield);
                            }
                        }
                    }
                    if (data3.getStates() != 0 || data3.xzstate("封印") != null) {
                        g = maxg;
                        if (data3.getStates() != 0) {
                            manData.executeYwwq(gjz);
                        }
                        else {
                            continue;
                        }
                    }
                }
                manData.addDun(ap, gjz);
            }
            for (int l = 0; l < gjEventss.size(); ++l) {
                battlefield.NewEvents.add(gjEventss.get(l));
            }
            ManData nomyData3 = (ManData)nomyDatas.get(0);
            if (nomyData3.getStates() != 0) {
                FightingSkill skill6 = manData.getSkillType("追击");
                if (skill6 != null && (double)(Battlefield.random.nextInt(100) + 1) < manData.getSkillType("追击").getSkillhurt()) {
                    List<ManData> zjdata = MixDeal.get(false, nomyData3, 0, manData.getCamp(), 0, 0, 0, 0, 1, -1, battlefield, 1);
                    if (zjdata.size() != 0) {
                        nomyData3 = (ManData)zjdata.get(0);
                        FightingEvents events = new FightingEvents();
                        FightingState org = new FightingState();
                        org.setCamp(manData.getCamp());
                        org.setMan(manData.getMan());
                        org.setStartState(TypeUtil.PTGJ);
                        events.setOriginator(org);
                        List<FightingState> acs = new ArrayList<>();
                        FightingState ac = new FightingState();
                        ac.setCamp(nomyData3.getCamp());
                        ac.setMan(nomyData3.getMan());
                        acs.add(ac);
                        manData.executeNbkj(1);
                        events.setAccepterlist(acs);
                        DataActionType.getActionById(1).analysisAction(manData, events, "追击", battlefield);
                        return;
                    }
                }
            }
            if (manData != null && manData.getStates() == 0 && manData.executeZhanyi()) {
                List<ManData> zjdata2 = MixDeal.get(false, nomyData3, 0, manData.getCamp(), 0, 0, 0, 0, 1, -1, battlefield, 1);
                if (zjdata2.size() != 0) {
                    nomyData3 = (ManData)zjdata2.get(0);
                    FightingEvents events2 = new FightingEvents();
                    FightingState org2 = new FightingState();
                    org2.setCamp(manData.getCamp());
                    org2.setMan(manData.getMan());
                    org2.setStartState(TypeUtil.PTGJ);
                    org2.setText("看我的冲冠一怒");
                    org2.setSkillskin("cgyn");
                    events2.setOriginator(org2);
                    List<FightingState> acs2 = new ArrayList<>();
                    FightingState ac2 = new FightingState();
                    ac2.setCamp(nomyData3.getCamp());
                    ac2.setMan(nomyData3.getMan());
                    acs2.add(ac2);
                    manData.executeNbkj(1);
                    events2.setAccepterlist(acs2);
                    DataActionType.getActionById(1).analysisAction(manData, events2, "分裂", battlefield);
                    return;
                }
            }
            FightingSkill skill6 = manData.getSkillType("分裂");
            if (skill6 != null && (double)(Battlefield.random.nextInt(100) + 1) < manData.getSkillType("分裂").getSkillhurt()) {
                List<ManData> zjdata = MixDeal.get(false, nomyData3, 0, manData.getCamp(), 0, 0, 0, 0, 1, -1, battlefield, 1);
                if (zjdata.size() != 0) {
                    nomyData3 = (ManData)zjdata.get(0);
                    FightingEvents events = new FightingEvents();
                    FightingState org = new FightingState();
                    org.setCamp(manData.getCamp());
                    org.setMan(manData.getMan());
                    org.setStartState(TypeUtil.PTGJ);
                    events.setOriginator(org);
                    List<FightingState> acs = new ArrayList<>();
                    FightingState ac = new FightingState();
                    ac.setCamp(nomyData3.getCamp());
                    ac.setMan(nomyData3.getMan());
                    acs.add(ac);
                    manData.executeNbkj(1);
                    events.setAccepterlist(acs);
                    DataActionType.getActionById(1).analysisAction(manData, events, "分裂", battlefield);
                    return;
                }
            }
        }
    }
}
