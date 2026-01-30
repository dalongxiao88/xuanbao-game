package come.tool.FightingDataAction;

import java.util.List;
import come.tool.FightingData.AddState;
import come.tool.FightingData.GroupBuff;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.PK_MixDeal;
import come.tool.FightingData.FightingSkill;
import java.util.ArrayList;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public class Kanglong implements DataAction
{
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        boolean isHSSF = TypeUtil.BB_E_HSSF.equals(type);
        FightingSkill skill = manData.getAttacks("亢龙有悔").getSkill();
        int maxg = skill.getSkillsum() + (((double)Battlefield.random.nextInt(100) < skill.getSkillgain() + (double)manData.ljv) ? 1 : 0);
        int g = 0;
        int d = 1;
        double df = skill.getSkillhurt() / 100.0;
        long ap = (long)manData.getAp();
        double mzjc = 0.0;
        double dsjc = 0.0;
        GroupBuff buff = battlefield.getBuff(manData.getMan(), TypeUtil.YBYT);
        if (buff != null) {
            mzjc += buff.getValue();
        }
        buff = battlefield.getBuff(manData.getMan(), TypeUtil.BB_E_HYMB);
        if (buff != null) {
            dsjc += buff.getValue();
        }
        mzjc += manData.getQuality().getRolehsds() + (double)manData.executeYszd(3) - (double)manData.executeYszd(2);
        AddState addState = manData.xzstate("加狂暴");
        if (addState != null) {
            mzjc += addState.getStateEffect();
        }
        int nocamp = MixDeal.getcamp((manData.xzstate("混乱") == null) ? "普通攻击" : "混乱技", manData.getCamp(), battlefield.nomy(manData.getCamp()));
        ManData nomyData = PhyAttack.getdaji(nocamp, fightingEvents, battlefield, manData, 0.0);
        List<ManData> guiwei = new ArrayList<>();
        String type2 = (nocamp == -1) ? "混乱技" : "普通攻击";
        long zap = 0L;
        long zap2 = 0L;
        long zap3 = 0L;
        FightingSkill skill_1 = null;
        FightingSkill skill_2 = null;
        FightingSkill skill_3 = null;
        FightingSkill skill_4 = null;
        FightingSkill skill_5 = null;
        FightingSkill skill_6 = null;
        for (int i = manData.getSkills().size() - 1; i >= 0; --i) {
            FightingSkill sl = (FightingSkill)manData.getSkills().get(i);
            if (sl.getSkillbeidong() == 1) {
                if (sl.getSkilltype().equals(TypeUtil.BB_E_JWXF)) {
                    skill_1 = sl;
                }
                else if (sl.getSkilltype().equals(TypeUtil.BB_E_TCDK)) {
                    skill_2 = sl;
                }
                else if (sl.getSkilltype().equals(TypeUtil.BB_E_YJBM)) {
                    if (!PK_MixDeal.isPK(battlefield.BattleType)) {
                        skill_3 = sl;
                    }
                }
                else if (sl.getSkilltype().equals(TypeUtil.BB_E_HSSF)) {
                    skill_4 = sl;
                }
                else if (sl.getSkilltype().equals(TypeUtil.BB_E_DSFS)) {
                    skill_5 = sl;
                    zap2 = (long)(manData.getKangluobao() * 175.0);
                }
                else if (sl.getSkilltype().equals(TypeUtil.BB_E_QKYZ)) {
                    skill_6 = sl;
                    zap3 = PhyAttack.getMoney(manData, battlefield);
                    if (zap3 >= 500000L) {
                        zap3 = 500000L;
                    }
                    zap3 = (long)((double)zap3 / 2.8);
                }
            }
        }
        List<ManData> zzs = null;
        if (skill_2 != null && Battlefield.isV(skill_2.getSkillgain())) {
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
        int nbkj = 0;
        boolean zy = false;
        boolean nb = false;
        long sbke = 0L;
        boolean jgbg = maxg == 1;
        while (nomyData != null && g < maxg) {
            int gjw = Battlefield.random.nextInt(4) * 2 + 1;
            MixDeal.move(manData.getCamp(), manData.getMan(), "瞬移", nomyData.getCamp() + "|" + nomyData.getMan() + "|" + gjw, battlefield);
            if (g == 0) {
                if (type.equals("分裂")) {
                    ((FightingState)((FightingEvents)battlefield.NewEvents.get(battlefield.NewEvents.size() - 1)).getAccepterlist().get(0)).setSkillskin("1832");
                }
                else if (type.equals("追击")) {
                    ((FightingState)((FightingEvents)battlefield.NewEvents.get(battlefield.NewEvents.size() - 1)).getAccepterlist().get(0)).setSkillskin("1831");
                }
            }
            ++g;
            int bb_e = 0;
            if (skill_1 != null && Battlefield.isV(skill_1.getSkillgain())) {
                bb_e = 1;
            }
            FightingEvents gjEvents = new FightingEvents();
            List<FightingState> zls = new ArrayList<>();
            int hmgs = manData.executeHmsz(zls);
            if (hmgs == 2) {
                sbke = (long)manData.getAp();
            }
            else {
                sbke = 0L;
            }
            if (g == 0 && maxg > 1) {
                maxg = manData.executeJtpa(zls, maxg);
                jgbg = false;
            }
            FightingState ace = new FightingState();
            if (bb_e == 1 || !Battlefield.isV(nomyData.getsx(4, TypeUtil.SX_SBL) - manData.getQuality().getRolefmzl() + dsjc - mzjc - manData.mz)) {
                ap = (long)manData.getAp() + zap + sbke;
                if (skill_5 != null && Battlefield.isV(skill_5.getSkillgain())) {
                    ap += zap2;
                    ace.setText("大圣附身#2");
                    ace.setSkillskin(TypeUtil.BB_E_DSFS);
                }
                else if (skill_6 != null && Battlefield.isV(skill_6.getSkillgain())) {
                    ap += zap3;
                    ace.setText("乾坤一掷#2");
                    ace.setSkillskin(TypeUtil.BB_E_QKYZ);
                }
                ChangeFighting acec = new ChangeFighting();
                ap = (long)PhyAttack.Hurt(ap, d, manData, nomyData, type, ace, zls, battlefield, 0.0, 0.0);
                acec.setChangehp((int)(-ap));
                if (skill_3 != null && Battlefield.isV(skill_3.getSkillgain())) {
                    FightingPackage.ChangeProcess(acec, manData, nomyData, ace, TypeUtil.ZSSH, zls, battlefield);
                    ace.setText("不堪一击的选手#2");
                }
                else {
                    FightingPackage.ChangeProcess(acec, manData, nomyData, ace, TypeUtil.PTGJ, zls, battlefield);
                }
                PhyAttack.feedback(type, manData, ap, battlefield, zls);
                PhyAttack.neidan(type2, manData, nomyData, ap, battlefield, zls, gjw, 0, 0.0);
                if (skill_4 != null && (isHSSF || Battlefield.isV(skill_4.getSkillgain() / 20.0))) {
                    List<ManData> datas = battlefield.getZW(nomyData);
                    for (int k = datas.size() - 1; k >= 0; --k) {
                        FightingState ace2 = new FightingState();
                        ManData nomyData2 = (ManData)datas.get(k);
                        if (nomyData2.getStates() == 0) {
                            ChangeFighting acec2 = new ChangeFighting();
                            ap = (long)PhyAttack.Hurt((long)((double)sbke + (double)manData.getAp() * skill_4.getSkillgain() / 100.0), g, manData, nomyData2, TypeUtil.PTGJ, ace2, zls, battlefield, 0.0, 0.0);
                            acec2.setChangehp((int)(-ap));
                            FightingPackage.ChangeProcess(acec2, (bb_e == 2) ? null : manData, nomyData2, ace2, type, zls, battlefield);
                        }
                    }
                }
            }
            else {
                ace.setCamp(nomyData.getCamp());
                ace.setMan(nomyData.getMan());
                ace.setStartState("瞬移");
                ace.setProcessState("躲闪");
                ace.setEndState(nomyData.getCamp() + "|" + nomyData.getMan() + "|" + PhyAttack.getdir(gjw));
                zls.add(ace);
                guiwei.add(nomyData);
            }
            if (manData.getCamp() == nomyData.getCamp()) {
                gjw = PhyAttack.getdir(gjw);
            }
            FightingState gjz = new FightingState();
            gjz.setCamp(manData.getCamp());
            gjz.setMan(manData.getMan());
            gjz.setStartState("普通攻击");
            gjz.setEndState(gjw + "");
            if (bb_e == 1) {
                gjz.setText("箭无虚发#2");
            }
            zls.add(gjz);
            if (zzs != null) {
                gjz.setText("合力一击#2");
                for (int l = 0; l < zzs.size(); ++l) {
                    ManData data2 = (ManData)zzs.get(l);
                    zls.add(MixDeal.skillmove(nomyData, data2, "9"));
                }
            }
            if (zy) {
                if (nb) {
                    FightingState gjz2 = new FightingState();
                    gjz2.setCamp(manData.getCamp());
                    gjz2.setMan(manData.getMan());
                    gjz2.setText("看我的怒不可揭");
                    gjz2.setStartState("代价");
                    zls.add(0, gjz2);
                }
                FightingState gjz3 = new FightingState();
                gjz3.setCamp(manData.getCamp());
                gjz3.setMan(manData.getMan());
                gjz3.setText("看我的冲冠一怒");
                gjz3.setStartState("代价");
                zls.add(0, gjz3);
                gjz.setSkillskin("cgyn");
            }
            manData.addDun(ap, gjz);
            gjEvents.setAccepterlist(zls);
            battlefield.NewEvents.add(gjEvents);
            if (guiwei.size() != 0) {
                FightingEvents moveEvents = new FightingEvents();
                List<FightingState> moves = new ArrayList<>();
                for (int m = 0; m < guiwei.size(); ++m) {
                    FightingState move = new FightingState();
                    move.setCamp(((ManData)guiwei.get(m)).getCamp());
                    move.setMan(((ManData)guiwei.get(m)).getMan());
                    move.setStartState("瞬移");
                    move.setEndState(((ManData)guiwei.get(m)).getCamp() + "|" + ((ManData)guiwei.get(m)).getMan());
                    moves.add(move);
                    moveEvents.setAccepterlist(moves);
                }
                moveEvents.setAccepterlist(moves);
                battlefield.NewEvents.add(moveEvents);
            }
            if (nomyData.getStates() != 0 || nomyData.xzstate("封印") != null) {
                g = maxg;
                if (nomyData.getStates() != 0) {
                    manData.executeYwwq(gjz);
                }
            }
            int camp = nomyData.getCamp();
            int man = nomyData.getCamp();
            nomyData = PhyAttack.getdaji(nocamp, null, battlefield, manData, 0.0);
            if (nomyData != null && camp == nomyData.getCamp() && man == nomyData.getMan()) {
                ++d;
            }
            if (nomyData == null || nomyData.getStates() != 0 || manData.getStates() != 0) {
                g = maxg;
            }
            if (g >= maxg && manData.getStates() == 0) {
                MixDeal.move(manData.getCamp(), manData.getMan(), "瞬移", manData.getCamp() + "|" + manData.getMan(), battlefield);
            }
            else if (g >= maxg) {
                MixDeal.move(manData.getCamp(), manData.getMan(), "瞬移", manData.getCamp() + "|" + manData.getMan(), battlefield);
            }
            zy = false;
            if (g == maxg && manData != null && manData.getStates() == 0 && manData.executeZhanyi() && nomyData != null && camp == nomyData.getCamp() && man == nomyData.getMan()) {
                zy = true;
                nb = manData.executeNbkj(nbkj++);
                d = 0;
                g = 0;
                maxg = skill.getSkillsum() + (((double)Battlefield.random.nextInt(100) < skill.getSkillgain()) ? 1 : 0);
            }
        }
        if (jgbg) {
            manData.executeJgbg(((FightingEvents)battlefield.NewEvents.get(battlefield.NewEvents.size() - 1)).getAccepterlist());
        }
    }
}
