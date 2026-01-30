package come.tool.FightingDataAction;

import come.tool.FightingData.GroupBuff;
import java.util.List;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.FightingState;
import java.util.Random;
import come.tool.FightingData.PK_MixDeal;
import come.tool.FightingData.FightingSkill;
import java.util.ArrayList;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public class LianSheng implements DataAction
{
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        boolean isHSSF = TypeUtil.BB_E_HSSF.equals(type);
        if (!type.equals("兵临城下")) {
            type = TypeUtil.PTGJ;
        }
        FightingSkill fightingSkill = manData.getSkillType(TypeUtil.BB_TJLH);
        int nocamp = MixDeal.getcamp(type, manData.getCamp(), battlefield.nomy(manData.getCamp()));
        ManData noman = PhyAttack.getdaji(nocamp, fightingEvents, battlefield, manData, fightingSkill.getSkillhurt());
        if (noman == null) {
            return;
        }
        long zap = 0L;
        long zap2 = 0L;
        long zap3 = 0L;
        List<FightingEvents> gjEventss = new ArrayList<>();
        FightingSkill skill_4 = null;
        FightingSkill skill_5 = null;
        FightingSkill skill_6 = null;
        FightingSkill skill_7 = null;
        FightingSkill skill_8 = null;
        FightingSkill skill_9 = null;
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
                else if (sl.getSkilltype().equals(TypeUtil.BB_E_LHLX)) {
                    skill_9 = sl;
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
        double ljl = manData.getQuality().getRolefljl() - (double)((noman.getSkillType(TypeUtil.TJ_YCDY) == null) ? 0 : 15);
        int ljv = (int)manData.getQuality().getRolefljv();
        FightingSkill skill5 = manData.getAppendSkill(9811);
        if (skill5 != null) {
            ljl -= skill5.getSkillhurt();
        }
        long lastAP = (long)manData.getAp();
        boolean zhui = false;
        boolean fen = false;
        ManData fenman = null;
        int maxg = 1;
        int nbkj = 0;
        int g = 0;
        boolean zy = false;
        long sbke = 0L;
        boolean jgbg = maxg == 1;
        while (noman.getStates() == 0 && g < maxg) {
            Random rand = new Random();
            int lj = rand.nextInt(100) + 1;
            if (lj <= 21 && maxg == 1) {
                maxg = PhyAttack.GMax(manData, noman, 0.0, battlefield);
                if (maxg > 5) {
                    maxg = 5;
                }
                if (PK_MixDeal.isPK(battlefield.BattleType)) {
                    GroupBuff buff = battlefield.getBuff(noman.getCamp(), TypeUtil.BB_QZ);
                    if (buff != null && maxg > 2) {
                        maxg = 2;
                    }
                }
            }
            FightingEvents gjEvents = new FightingEvents();
            List<FightingState> list = new ArrayList<>();
            int hmgs = manData.executeHmsz(list);
            if (hmgs == 2) {
                sbke = (long)manData.getAp();
            }
            else {
                sbke = 0L;
            }
            if (g == 0 && maxg > 1) {
                maxg = manData.executeJtpa(list, maxg);
                jgbg = false;
            }
            FightingState gjz = new FightingState();
            gjz.setCamp(manData.getCamp());
            gjz.setSkillsy(fightingSkill.getSkillname());
            gjz.setMan(manData.getMan());
            if (g >= 1) {
                gjz.setStartState("特效2");
            }
            else {
                gjz.setStartState("特效1");
            }
            fightingEvents.setAccepterlist(list);
            battlefield.NewEvents.add(fightingEvents);
            if (zhui) {
                if (manData.executeNbkj(nbkj++)) {
                    FightingState gjz2 = new FightingState();
                    gjz2.setCamp(manData.getCamp());
                    gjz2.setMan(manData.getMan());
                    gjz2.setText("看我的怒不可揭");
                    gjz2.setStartState("代价");
                    list.add(0, gjz2);
                }
                gjz.setSkillskin("1831");
            }
            else if (fen) {
                if (manData.executeNbkj(nbkj++)) {
                    FightingState gjz2 = new FightingState();
                    gjz2.setCamp(manData.getCamp());
                    gjz2.setMan(manData.getMan());
                    gjz2.setText("看我的怒不可揭");
                    gjz2.setStartState("代价");
                    list.add(0, gjz2);
                }
                gjz.setSkillskin("1832");
            }
            else if (zy) {
                if (manData.executeNbkj(nbkj++)) {
                    FightingState gjz3 = new FightingState();
                    gjz3.setCamp(manData.getCamp());
                    gjz3.setMan(manData.getMan());
                    gjz3.setText("看我的怒不可揭");
                    gjz3.setStartState("代价");
                    list.add(0, gjz3);
                }
                FightingState gjz2 = new FightingState();
                gjz2.setCamp(manData.getCamp());
                gjz2.setMan(manData.getMan());
                gjz2.setText("看我的冲冠一怒");
                gjz2.setStartState("代价");
                list.add(0, gjz2);
                gjz.setSkillskin("cgyn");
            }
            list.add(gjz);
            gjEvents.setAccepterlist(list);
            gjEventss.add(gjEvents);
            FightingEvents gjEvents2 = new FightingEvents();
            List<FightingState> zls = new ArrayList<>();
            gjEvents2.setAccepterlist(zls);
            gjEventss.add(gjEvents2);
            long ap = (long)(int)Math.ceil((double)manData.getAp() * 1.2);
            ap += zap + sbke;
            zhui = false;
            ++g;
            FightingState ace = new FightingState();
            ace.setStartState("被攻击");
            ace.setSkillskin("1237");
            if (skill_7 != null && Battlefield.isV(skill_7.getSkillgain())) {
                ap += zap2;
                gjz.setText("大圣附身#2");
            }
            else if (skill_8 != null && Battlefield.isV(skill_8.getSkillgain())) {
                ap += zap3;
                gjz.setText("乾坤一掷#2");
            }
            ap = (long)PhyAttack.Hurt(ap, g, manData, noman, type, ace, zls, battlefield, 10.0, 10.0);
            lastAP = ap / 4L;
            manData.addDun(ap, gjz);
            if (skill_9 != null) {
                if (skill_9.getSkillhurt() > 0.0) {
                    lastAP = (long)((double)lastAP + (double)(lastAP * 100L) / skill_9.getSkillhurt());
                }
                if (Battlefield.isV(skill_9.getSkillgain())) {
                    gjz.setStartState("特效1");
                    ace.setSkillskin("1237");
                    gjz.setText("莲火流星，爆#2");
                    List<ManData> datas = battlefield.getZW2(noman);
                    for (int k = datas.size() - 1; k >= 0; --k) {
                        FightingState ace2 = new FightingState();
                        ManData nomyData2 = (ManData)datas.get(k);
                        ace2.setSkillskin("1241");
                        if (nomyData2.getStates() == 0) {
                            ChangeFighting acec1 = new ChangeFighting();
                            int currAp = PhyAttack.Hurt(lastAP, g, manData, nomyData2, type, ace2, zls, battlefield, 10.0, 10.0);
                            acec1.setChangehp(-currAp);
                            FightingPackage.ChangeProcess(acec1, null, nomyData2, ace2, type, zls, battlefield);
                        }
                    }
                }
            }
            ChangeFighting acec2 = new ChangeFighting();
            acec2.setChangehp((int)(-ap));
            if (skill_5 != null && Battlefield.isV(skill_5.getSkillgain())) {
                FightingPackage.ChangeProcess(acec2, null, noman, ace, TypeUtil.ZSSH, zls, battlefield);
                ace.setText("不堪一击的选手#2");
            }
            else {
                FightingPackage.ChangeProcess(acec2, null, noman, ace, TypeUtil.PTGJ, zls, battlefield);
            }
            PhyAttack.feedback(type, manData, ap, battlefield, zls);
            PhyAttack.neidan(type, manData, noman, ap, battlefield, zls, g, 0, 0.0);
            if (zap != 0L && zzs != null) {
                ace.setText("合力一击#2");
                for (int l = 0; l < zzs.size(); ++l) {
                    ManData zz = (ManData)zzs.get(l);
                    zls.add(MixDeal.skillmove(noman, zz, "9"));
                }
            }
            if (skill_6 != null && (isHSSF || Battlefield.isV(skill_6.getSkillgain() / 20.0))) {
                List<ManData> datas2 = battlefield.getZW(noman);
                for (int m = datas2.size() - 1; m >= 0; --m) {
                    FightingState ace3 = new FightingState();
                    ManData nomyData3 = (ManData)datas2.get(m);
                    if (nomyData3.getStates() == 0) {
                        ChangeFighting acec3 = new ChangeFighting();
                        ap = (long)PhyAttack.Hurt((long)((double)manData.getAp() * skill_6.getSkillgain() / 100.0), g, manData, nomyData3, type, ace3, zls, battlefield, 10.0, 10.0);
                        acec3.setChangehp((int)(-ap));
                        FightingPackage.ChangeProcess(acec3, null, nomyData3, ace3, type, zls, battlefield);
                    }
                }
            }
            if (noman.getStates() != 0 || noman.xzstate("封印") != null) {
                g = maxg;
                if (noman.getStates() != 0) {
                    manData.executeYwwq(gjz);
                }
            }
            zy = false;
            if (g == maxg && manData != null && manData.getStates() == 0 && manData.executeZhanyi()) {
                List<ManData> zjdata = MixDeal.get(false, noman, 0, manData.getCamp(), 0, 0, 0, 0, 1, -1, battlefield, 1);
                if (zjdata.size() != 0) {
                    noman = (ManData)zjdata.get(0);
                    zy = true;
                    g = 0;
                    maxg = 1;
                    continue;
                }
            }
            fen = false;
            FightingSkill skill6 = manData.getSkillType("分裂");
            if (g == maxg && skill6 != null && (double)Battlefield.random.nextInt(100) < manData.getSkillType("分裂").getSkillhurt()) {
                List<ManData> zjdata2 = MixDeal.get(false, noman, 0, manData.getCamp(), 0, 0, 0, 0, 1, -1, battlefield, 1);
                if (zjdata2.size() != 0) {
                    noman = (ManData)zjdata2.get(0);
                    fen = true;
                    g = 0;
                    maxg = 1;
                    continue;
                }
            }
            if (noman.getStates() != 0) {
                if (nocamp == manData.getCamp() || nocamp == -1) {
                    List<ManData> datas3 = battlefield.getZW(noman);
                    FightingEvents gjEventsZhui = new FightingEvents();
                    List<FightingState> listZhui = new ArrayList<>();
                    for (int i2 = datas3.size() - 1; i2 >= 0; --i2) {
                        FightingState ace4 = new FightingState();
                        ManData nomyData4 = (ManData)datas3.get(i2);
                        if (nomyData4.getStates() == 0) {
                            ChangeFighting acec4 = new ChangeFighting();
                            acec4.setChangehp((int)(-lastAP));
                            FightingPackage.ChangeProcess(acec4, null, nomyData4, ace4, type, listZhui, battlefield);
                        }
                    }
                    gjEventsZhui.setAccepterlist(listZhui);
                    gjEventss.add(gjEventsZhui);
                }
                FightingSkill skillFen = manData.getSkillType("追击");
                if (skillFen != null && (double)Battlefield.random.nextInt(100) < skillFen.getSkillhurt()) {
                    List<ManData> zjdata3 = MixDeal.get(false, noman, 0, manData.getCamp(), 0, 0, 0, 0, 1, -1, battlefield, 1);
                    if (zjdata3.size() != 0) {
                        noman = (ManData)zjdata3.get(0);
                        zhui = true;
                        g = 0;
                        maxg = 1;
                    }
                    else {
                        continue;
                    }
                }
                else {
                    continue;
                }
            }
        }
        for (int i3 = 0; i3 < gjEventss.size(); ++i3) {
            battlefield.NewEvents.add(gjEventss.get(i3));
        }
        if (jgbg) {
            manData.executeJgbg(((FightingEvents)battlefield.NewEvents.get(battlefield.NewEvents.size() - 1)).getAccepterlist());
        }
    }
}
