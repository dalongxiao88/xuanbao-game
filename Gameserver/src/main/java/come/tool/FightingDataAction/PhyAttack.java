package come.tool.FightingDataAction;

import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.model.Skill;
import org.come.server.GameServer;
import java.util.Map;
import org.come.tool.Arith;
import come.tool.FightingSpellAction.ZSAction;
import come.tool.FightingSpellAction.SSCAction;
import org.come.tool.TalentTool;
import java.math.BigDecimal;
import org.come.bean.PathPoint;
import come.tool.FightingData.Calculation;
import com.gl.util.FaMenUtil;
import java.util.Iterator;
import java.util.List;
import come.tool.FightingData.GroupBuff;
import come.tool.FightingData.AddAttack;
import come.tool.FightingSpellAction.ControlAction;
import come.tool.FightingSpellAction.HurtAction;
import come.tool.FightingData.AddState;
import java.util.Random;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.FightingState;
import java.util.ArrayList;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.PK_MixDeal;
import come.tool.FightingSpellAction.SpellActionType;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;
import come.tool.FightingData.FightingSkill;

public class PhyAttack implements DataAction
{
    public static FightingSkill[] skills;
    
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        if ("普通攻击".equals(type) || "混乱技".equals(type)) {
            manData.setPhyAttack(true);
        }
        int z = 0;
        int fl = 0;
        int fmfl = 0;
        int zwzb = 0;
        int gssh = 0;
        FightingSkill skill = manData.getSkillType(TypeUtil.TJ_PXKG);
        if (skill != null) {
            DataActionType.getActionById(28).analysisAction(manData, fightingEvents, type, battlefield);
            return;
        }
        skill = manData.getSkillType("1252");
        if (skill != null && (double)Battlefield.random.nextInt(100) < skill.getSkillgain()) {
            SpellActionType.getActionById(28).spellAction(manData, skill, fightingEvents, battlefield);
            return;
        }
        gssh = manData.getAp();
        skill = manData.getSkillType(TypeUtil.BB_TJLH);
        if (skill != null && (double)Battlefield.random.nextInt(100) < skill.getSkillgain()) {
            DataActionType.getActionById(34).analysisAction(manData, fightingEvents, type, battlefield);
            return;
        }
        FightingSkill skill_BB_LHFM = null;
        ManData baodata = null;
        ManData baodata2 = null;
        boolean isHSSF = TypeUtil.BB_E_HSSF.equals(type);
        if (isHSSF) {
            type = TypeUtil.PTGJ;
        }
        if (type.equals("分裂")) {
            fl = 1;
            type = TypeUtil.PTGJ;
        }
        else if (type.equals("法门分裂")) {
            fmfl = 1;
            type = TypeUtil.PTGJ;
        }
        else if (type.equals("追击")) {
            z = 1;
            type = TypeUtil.PTGJ;
        }
        else {
            AddAttack addAttack = null;
            if (!PK_MixDeal.isPK(battlefield.BattleType)) {
                FightingSkill skillxflz = manData.getSkillId(450051);
                if (skillxflz != null && Battlefield.isV(skillxflz.getSkillgain()) && manData.xzstate("混乱") == null) {
                    DataActionType.getActionById(43).analysisAction(manData, fightingEvents, type, battlefield);
                    return;
                }
            }
            if (!type.equals("混乱技")) {
                addAttack = manData.getAttacks("暗影离魂");
                if (addAttack != null) {
                    skill = addAttack.getSkill();
                    if (skill.getSkillhitrate() <= (double)battlefield.CurrentRound) {
                        skill.setSkillhitrate((double)(battlefield.CurrentRound + 3));
                        DataActionType.getActionById(20).analysisAction(manData, fightingEvents, type, battlefield);
                        return;
                    }
                }
                FightingSkill skill2 = manData.getSkillType(TypeUtil.BB_XYLL);
                if (skill2 != null && (double)Battlefield.random.nextInt(100) < skill2.getSkillgain()) {
                    DataActionType.getActionById(38).analysisAction(manData, fightingEvents, type, battlefield);
                    return;
                }
                addAttack = manData.getAttacks(TypeUtil.BB_LHFM);
                if (addAttack != null) {
                    skill = addAttack.getSkill();
                    if (skill.getSkillhitrate() <= (double)battlefield.CurrentRound) {
                        skill.setSkillhurt(1.0);
                        skill.setSkillhitrate((double)(battlefield.CurrentRound + 10));
                        skill_BB_LHFM = skill;
                    }
                }
            }
            skill = null;
            addAttack = manData.getAttacks("亢龙有悔");
            if (addAttack != null) {
                skill = addAttack.getSkill();
            }
            AddAttack addAttack2 = null;
            if (!type.equals("混乱技")) {
                addAttack2 = manData.getAttacks("天降流火");
                if (addAttack2 != null) {
                    skill = addAttack2.getSkill();
                    if (skill.getSkillhitrate() <= (double)battlefield.CurrentRound) {
                        skill.setSkillhitrate((double)(battlefield.CurrentRound + 2));
                        DataActionType.getActionById(37).analysisAction(manData, fightingEvents, type, battlefield);
                        return;
                    }
                }
                addAttack2 = manData.getAttacks(TypeUtil.BB_LHFM);
                if (addAttack2 != null) {
                    skill = addAttack2.getSkill();
                    if (skill.getSkillhitrate() <= (double)battlefield.CurrentRound) {
                        skill.setSkillhurt(1.0);
                        skill.setSkillhitrate((double)(battlefield.CurrentRound + 10));
                        skill_BB_LHFM = skill;
                    }
                }
            }
            skill = null;
            addAttack2 = manData.getAttacks("亢龙有悔");
            if (addAttack2 != null) {
                skill = addAttack2.getSkill();
            }
            AddAttack addAttack3 = null;
            if (!type.equals("混乱技")) {
                addAttack3 = manData.getAttacks("万箭齐发");
                if (addAttack3 != null) {
                    skill = addAttack3.getSkill();
                    if (skill.getSkillhitrate() <= (double)battlefield.CurrentRound) {
                        skill.setSkillhitrate((double)(battlefield.CurrentRound + 3));
                        DataActionType.getActionById(38).analysisAction(manData, fightingEvents, type, battlefield);
                        return;
                    }
                }
                addAttack3 = manData.getAttacks(TypeUtil.BB_LHFM);
                if (addAttack3 != null) {
                    skill = addAttack3.getSkill();
                    if (skill.getSkillhitrate() <= (double)battlefield.CurrentRound) {
                        skill.setSkillhurt(1.0);
                        skill.setSkillhitrate((double)(battlefield.CurrentRound + 10));
                        skill_BB_LHFM = skill;
                    }
                }
            }
            skill = null;
            addAttack2 = manData.getAttacks("亢龙有悔");
            if (addAttack2 != null) {
                skill = addAttack2.getSkill();
            }
            AddAttack addAttack4 = null;
            if (!type.equals("混乱技")) {
                addAttack4 = manData.getAttacks("乳来神掌");
                if (addAttack4 != null) {
                    skill = addAttack4.getSkill();
                    if (skill.getSkillhitrate() <= (double)battlefield.CurrentRound) {
                        skill.setSkillhitrate((double)(battlefield.CurrentRound + 3));
                        DataActionType.getActionById(39).analysisAction(manData, fightingEvents, type, battlefield);
                        return;
                    }
                }
                addAttack4 = manData.getAttacks(TypeUtil.BB_LHFM);
                if (addAttack4 != null) {
                    skill = addAttack4.getSkill();
                    if (skill.getSkillhitrate() <= (double)battlefield.CurrentRound) {
                        skill.setSkillhurt(1.0);
                        skill.setSkillhitrate((double)(battlefield.CurrentRound + 10));
                        skill_BB_LHFM = skill;
                    }
                }
            }
            skill = null;
            addAttack2 = manData.getAttacks("亢龙有悔");
            if (addAttack2 != null) {
                skill = addAttack2.getSkill();
            }
            AddAttack addAttack5 = null;
            if (!type.equals("混乱技")) {
                addAttack3 = manData.getAttacks("测试二");
                if (addAttack3 != null) {
                    skill = addAttack3.getSkill();
                    if (skill.getSkillhitrate() <= (double)battlefield.CurrentRound) {
                        skill.setSkillhitrate((double)(battlefield.CurrentRound + 1));
                        DataActionType.getActionById(40).analysisAction(manData, fightingEvents, type, battlefield);
                        return;
                    }
                }
                addAttack3 = manData.getAttacks(TypeUtil.BB_LHFM);
                if (addAttack3 != null) {
                    skill = addAttack3.getSkill();
                    if (skill.getSkillhitrate() <= (double)battlefield.CurrentRound) {
                        skill.setSkillhurt(1.0);
                        skill.setSkillhitrate((double)(battlefield.CurrentRound + 10));
                        skill_BB_LHFM = skill;
                    }
                }
            }
            skill = null;
            addAttack2 = manData.getAttacks("亢龙有悔");
            if (addAttack2 != null) {
                skill = addAttack2.getSkill();
            }
            AddAttack addAttack6 = null;
            if (!type.equals("混乱技")) {
                addAttack3 = manData.getAttacks("测试三");
                if (addAttack3 != null) {
                    skill = addAttack3.getSkill();
                    if (skill.getSkillhitrate() <= (double)battlefield.CurrentRound) {
                        skill.setSkillhitrate((double)(battlefield.CurrentRound + 1));
                        DataActionType.getActionById(41).analysisAction(manData, fightingEvents, type, battlefield);
                        return;
                    }
                }
                addAttack3 = manData.getAttacks(TypeUtil.BB_LHFM);
                if (addAttack3 != null) {
                    skill = addAttack3.getSkill();
                    if (skill.getSkillhitrate() <= (double)battlefield.CurrentRound) {
                        skill.setSkillhurt(1.0);
                        skill.setSkillhitrate((double)(battlefield.CurrentRound + 10));
                        skill_BB_LHFM = skill;
                    }
                }
            }
            skill = null;
            addAttack4 = manData.getAttacks("亢龙有悔");
            if (addAttack4 != null) {
                skill = addAttack4.getSkill();
            }
        }
        FightingSkill skill3 = manData.getSkillType("雾里看花");
        double mzjc = 0.0;
        double ljjc = 0.0;
        double dsjc = 0.0;
        double dsl = 0.0;
        if (manData.xzstate("夔灵鼓") != null) {
            mzjc += manData.xzstate("夔灵鼓").getStateEffect2();
        }
        if (manData.xzstate("散灵须") != null) {
            mzjc -= manData.xzstate("散灵须").getStateEffect();
        }
        ManData child = battlefield.getChild(manData);
        FightingSkill skill4 = manData.getAppendSkill(9203);
        if (skill4 != null) {
            mzjc += skill4.getSkillhurt();
            skill4 = null;
        }
        if (child != null) {
            skill4 = child.getChildSkill("强普攻");
            if (skill4 != null) {
                mzjc = 10000.0;
                ljjc = skill4.getSkillgain();
            }
        }
        AddState addState = manData.getstat(TypeUtil.TY_LL_JBZH);
        if (addState != null) {
            mzjc += addState.getStateEffect();
        }
        GroupBuff buff = battlefield.getBuff(manData.getMan(), TypeUtil.YBYT);
        if (buff != null) {
            mzjc += buff.getValue();
        }
        buff = battlefield.getBuff(manData.getMan(), TypeUtil.BB_E_HYMB);
        if (buff != null) {
            dsjc += buff.getValue();
        }
        int g = 0;
        int maxf = 0;
        int f = 0;
        long ap = (long)manData.getAp();
        addState = manData.xzstate("沧波");
        if (addState != null) {
            mzjc -= addState.getStateEffect();
        }
        int nocamp = MixDeal.getcamp(type, manData.getCamp(), battlefield.nomy(manData.getCamp()));
        FightingSkill skill5 = manData.getSkillType("水中探月");
        FightingSkill skill6 = manData.getSkillType("剑荡八荒");
        FightingSkill skill7 = manData.getSkillType("枪出如龙");
        FightingSkill skill8 = manData.getSkillType("大威天龙");
        FightingSkill skill9 = manData.getSkillType("大日如来神掌");
        FightingSkill skill10 = manData.getSkillType(TypeUtil.BB_TJTT);
        FightingSkill skill11 = manData.getAppendSkill(9347);
        if (skill11 != null) {
            mzjc -= skill11.getSkillhurt();
            ljjc -= skill11.getSkillhurt();
        }
        mzjc += manData.getQuality().getRolehsds() + (double)manData.executeYszd(3) - (double)manData.executeYszd(2);
        FightingSkill adcc = manData.getSkillType1("暗渡陈仓");
        if (adcc != null) {
            mzjc += adcc.getSkillhurt();
        }
        skill11 = manData.getAppendSkill(9811);
        if (skill11 != null) {
            ljjc -= skill11.getSkillhurt();
        }
        skill11 = manData.getAppendSkill(9146);
        FightingSkill skill12 = manData.getAppendSkill(9150);
        if (skill5 != null && (double)Battlefield.random.nextInt(100) > skill5.getSkillhurt()) {
            skill5 = null;
        }
        if (skill6 != null && (double)Battlefield.random.nextInt(100) > skill6.getSkillhurt()) {
            skill6 = null;
        }
        if (nocamp != manData.getCamp() && nocamp != -1) {
            skill5 = null;
            skill6 = null;
        }
        AddState state1 = manData.xzstate(TypeUtil.TZ_FHJY);
        if (nocamp != manData.getCamp() && nocamp != -1) {
            state1 = null;
        }
        double _4000 = 0.0;
        if (manData.xzstate("混乱") != null && manData.getSkillType("4000") != null) {
            _4000 = manData.getSkillType("4000").getSkillgain();
        }
        ManData nomyData = getdaji(nocamp, fightingEvents, battlefield, manData, (skill11 != null) ? skill11.getSkillhurt() : ((skill12 != null) ? skill12.getSkillhurt() : 0.0));
        if (manData.getSkillType("4003") != null && nomyData != null) {
            int l = (manData.getSp() - nomyData.getSp()) / 500;
            if (l >= 0) {
                ap += (long)Math.min((double)l * manData.getSkillType("4003").getSkillhurt() * (double)ap, 18000.0);
            }
        }
        List<ManData> guiwei = new ArrayList<>();
        int maxg = GMax(manData, nomyData, ljjc, battlefield);
        if (skill_BB_LHFM != null) {
            maxg = 1;
        }
        FightingSkill skill13 = null;
        long zap = 0L;
        long zap2 = 0L;
        long zap3 = 0L;
        FightingSkill skill_1 = null;
        FightingSkill skill_2 = null;
        FightingSkill skill_3 = null;
        FightingSkill skill_4 = null;
        FightingSkill skill_5 = null;
        FightingSkill skill_6 = null;
        FightingSkill skill_7 = null;
        FightingSkill skill_8 = null;
        FightingSkill skill_9 = null;
        FightingSkill skill_10 = null;
        FightingSkill xyskill_1 = null;
        FightingSkill xyskill_2 = null;
        FightingSkill xyskill_3 = null;
        FightingSkill xyskill_4 = null;
        FightingSkill xyskill_5 = null;
        FightingSkill xyskill_6 = null;
        FightingSkill xyskill_7 = null;
        FightingSkill xyskill_8 = null;
        FightingSkill xyskill_9 = null;
        FightingSkill xyskill_10 = null;
        FightingSkill syskill = null;
        FightingSkill bcjf = null;
        Boolean bcf = Boolean.valueOf(false);
        FightingSkill skill14 = null;
        FightingSkill skill15 = null;
        int attackType = 0;
        FightingSkill MYFH = null;//明月分辉
        FightingEvents sxsEvents = null;
        if (manData.getType() == 1) {
            for (int i = manData.getSkills().size() - 1; i >= 0; --i) {
                FightingSkill sl = (FightingSkill)manData.getSkills().get(i);
                if (sl.getSkilltype().equals(TypeUtil.XY_SY)) {
                    xyskill_1 = sl;
                }
                else if (sl.getSkilltype().equals(TypeUtil.XY_MW)) {
                    xyskill_2 = sl;
                }
                else if (sl.getSkilltype().equals(TypeUtil.XY_QX)) {
                    xyskill_3 = sl;
                }
                else if (sl.getSkilltype().equals(TypeUtil.XY_XX)) {
                    xyskill_4 = sl;
                }
                else if (sl.getSkilltype().equals(TypeUtil.XY_ST)) {
                    xyskill_5 = sl;
                }
                else if (sl.getSkilltype().equals(TypeUtil.XY_LT)) {
                    xyskill_6 = sl;
                }
                else if (sl.getSkilltype().equals(TypeUtil.XY_YIX)) {
                    xyskill_7 = sl;
                }
                else if (sl.getSkilltype().equals(TypeUtil.XY_YX)) {
                    xyskill_8 = sl;
                }
                else if (sl.getSkilltype().equals(TypeUtil.XY_WY)) {
                    xyskill_9 = sl;
                }
                else if (sl.getSkilltype().equals(TypeUtil.XY_LS)) {
                    xyskill_10 = sl;
                }else if (sl.getSkilltype().equals(TypeUtil.BB_MYFH)) {
                    MYFH = sl;
                }
            }
        }
        for (int i = manData.getSkills().size() - 1; i >= 0; --i) {
            FightingSkill sl = (FightingSkill)manData.getSkills().get(i);
            if (sl.getSkillbeidong() == 1) {
                if (sl.getSkilltype().equals(TypeUtil.BB_E_JWXF)) {
                    skill_1 = sl;
                }
                else if (sl.getSkilltype().equals(TypeUtil.BB_E_YFFS)) {
                    skill_2 = sl;
                }
                else if (sl.getSkilltype().equals(TypeUtil.BB_E_YGZQ)) {
                    skill_3 = sl;
                }
                else if (sl.getSkilltype().equals(TypeUtil.BB_E_TCDK)) {
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
                    zap3 = getMoney(manData, battlefield);
                    if (zap3 >= 500000L) {
                        zap3 = 500000L;
                    }
                    zap3 = (long)((double)zap3 / 2.8);
                }
                else if (sl.getSkilltype().equals(TypeUtil.BB_CZQG)) {
                    skill_9 = sl;
                    if (manData.getSkin() != null && manData.getSkin().equals("400318")) {
                        attackType = 1;
                    }
                }
                else if (sl.getSkilltype().equals(TypeUtil.TZ_ZWZB)) {
                    skill_10 = sl;
                }
                else if (sl.getSkilltype().equals(TypeUtil.SY_DSST)) {
                    syskill = sl;
                }
                else if (sl.getSkilltype().equals(TypeUtil.BB_百草竞发)) {
                    bcjf = sl;
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
            if (zap >= 250000L) {
                zap = 250000L;
            }
        }
        long Zap = (long)manData.getAp();
        int nbkj = 0;
        boolean nb = false;
        boolean zy = false;
        int nbzy = 1;
        long sbke = 0L;
        boolean jgbg = maxg == 1;
        int hsfjv = manData.executeYszd(3) - manData.executeYszd(2);
        boolean isST = true;
        boolean isLT = true;
        boolean isSYST = true;
        List<ManData> mubiao1 = new ArrayList<>();
        while (nomyData != null && g < maxg) {
            if (baodata != null) {
                nomyData = baodata2;
            }
            if (g == 1) {
                skill13 = nomyData.getSkillType(TypeUtil.TY_SSC_YSGX);
            }
            else if (g == 0) {
                zwzb = 0;
                dsl = nomyData.getsx(4, TypeUtil.SX_SBL);
            }
            dsl -= manData.getFamencsJv(3, new String[] { "云飞烟灭" });
            addState = manData.xzstate("神龙摆尾");
            if (addState != null) {
                dsl -= (double)manData.getFmValue(3, new String[] { "神龙摆尾" });
            }
            if (maxg > 1 && skill != null) {
                if (fl == 1) {
                    DataActionType.getActionById(21).analysisAction(manData, fightingEvents, "分裂", battlefield);
                }
                else if (fmfl == 1) {
                    DataActionType.getActionById(21).analysisAction(manData, fightingEvents, "法门分裂", battlefield);
                }
                else if (z != 0) {
                    DataActionType.getActionById(21).analysisAction(manData, fightingEvents, "追击", battlefield);
                }
                else {
                    DataActionType.getActionById(21).analysisAction(manData, fightingEvents, isHSSF ? TypeUtil.BB_E_HSSF : TypeUtil.PTGJ, battlefield);
                }
                return;
            }
            else {
                guiwei.clear();
                int gjw = 3;
                if (g == 0) {
                    if (skill4 != null) {
                        ((FightingEvents)battlefield.NewEvents.get(battlefield.NewEvents.size() - 1)).getAccepterlist().add(MixDeal.getChildSkill(child, skill4.getSkillname()));
                        skill4 = null;
                    }
                    if (skill10 != null) {
                        MixDeal.move(manData.getCamp(), manData.getMan(), "瞬移", nomyData.getCamp() + "|" + nomyData.getMan() + "|" + gjw, battlefield);
                    }
                    else if (skill3 != null) {
                        MixDeal.move(manData.getCamp(), manData.getMan(), "遁地", nomyData.getCamp() + "|" + nomyData.getMan() + "|" + gjw, battlefield);
                    }
                    else if (attackType == 1) {
                        sxsEvents = MixDeal.sxsmove(nomyData, manData, battlefield);
                    }
                    else if (xyskill_5 != null && Battlefield.isV((xyskill_7 != null) ? 32.0 : 24.0) && manData.getCamp() != nomyData.getCamp()) {
                        FightingEvents gjEvents1 = new FightingEvents();
                        List<FightingState> list = new ArrayList<>();
                        FightingState gjz = new FightingState();
                        gjz = new FightingState();
                        gjz.setCamp(manData.getCamp());
                        gjz.setMan(manData.getMan());
                        gjz.setSkillskin("12524");
                        list.add(gjz);
                        gjz = new FightingState();
                        gjz.setCamp(manData.getCamp());
                        gjz.setMan(manData.getMan());
                        gjz.setSkillskin("12523");
                        gjz.setStartState("移动");
                        gjz.setSkin("500197");
                        gjz.setEndState(nomyData.getCamp() + "|" + nomyData.getMan() + "|" + gjw);
                        list.add(gjz);
                        gjEvents1.setAccepterlist(list);
                        battlefield.NewEvents.add(gjEvents1);
                        isST = false;
                    }
                    else if (xyskill_6 != null && Battlefield.isV((xyskill_8 != null) ? 32.0 : 24.0) && manData.getCamp() != nomyData.getCamp()) {
                        FightingEvents gjEvents1 = new FightingEvents();
                        List<FightingState> list = new ArrayList<>();
                        FightingState gjz = new FightingState();
                        gjz = new FightingState();
                        gjz.setCamp(manData.getCamp());
                        gjz.setMan(manData.getMan());
                        gjz.setSkillskin("12524");
                        list.add(gjz);
                        gjz = new FightingState();
                        gjz.setCamp(manData.getCamp());
                        gjz.setMan(manData.getMan());
                        gjz.setSkillskin("12523");
                        gjz.setStartState("移动");
                        gjz.setSkin("500198");
                        gjz.setEndState(nomyData.getCamp() + "|" + nomyData.getMan() + "|" + gjw);
                        list.add(gjz);
                        gjEvents1.setAccepterlist(list);
                        battlefield.NewEvents.add(gjEvents1);
                        isLT = false;
                        ljjc += manData.getQuality().getRolefljl() * 0.12;
                        if (maxg == 1) {
                            maxg = GMax(manData, nomyData, ljjc, battlefield);
                        }
                    }
                    else if (syskill != null && Battlefield.isV(55.0) && manData.getCamp() != nomyData.getCamp()) {
                        FightingEvents gjEvents1 = new FightingEvents();
                        List<FightingState> list = new ArrayList<>();
                        FightingState gjz = new FightingState();
                        gjz = new FightingState();
                        gjz.setCamp(manData.getCamp());
                        gjz.setMan(manData.getMan());
                        gjz.setSkillskin("12524");
                        list.add(gjz);
                        gjz = new FightingState();
                        gjz.setCamp(manData.getCamp());
                        gjz.setMan(manData.getMan());
                        gjz.setStartState("移动");
                        gjz.setEndState(nomyData.getCamp() + "|" + nomyData.getMan() + "|" + gjw);
                        list.add(gjz);
                        gjEvents1.setAccepterlist(list);
                        battlefield.NewEvents.add(gjEvents1);
                        isSYST = false;
                    }
                    else {
                        MixDeal.move(manData.getCamp(), manData.getMan(), "移动", nomyData.getCamp() + "|" + nomyData.getMan() + "|" + gjw, battlefield);
                    }
                    if (skill_9 != null && skill_9.getSkillcontinued() == 1) {
                        skill_9.setSkillcontinued(0);
                        ((FightingState)((FightingEvents)battlefield.NewEvents.get(battlefield.NewEvents.size() - 1)).getAccepterlist().get(0)).setSkillskin("1233");
                    }
                    else if (fl == 1 || fmfl == 1) {
                        ((FightingState)((FightingEvents)battlefield.NewEvents.get(battlefield.NewEvents.size() - 1)).getAccepterlist().get(0)).setSkillskin("1832");
                    }
                    else if (z != 0) {
                        ((FightingState)((FightingEvents)battlefield.NewEvents.get(battlefield.NewEvents.size() - 1)).getAccepterlist().get(0)).setSkillskin("1831");
                    }

                }
                FightingSkill TY_LL_LBYM = manData.getSkillType(TypeUtil.TY_LL_LBYM);
                if (TY_LL_LBYM != null && nomyData.getStates() == 0) {
                    manData.addAddState(TY_LL_LBYM.getSkilltype(), TY_LL_LBYM.getSkillhurt(), 0.0, 2);
                    addState = manData.xzstate(TypeUtil.TY_LL_LBYM);
                    if (addState != null) {
                        manData.zm += addState.getStateEffect();
                    }
                }
                FightingEvents gjEvents2 = new FightingEvents();
                List<FightingState> zls = new ArrayList<>();
                FightingState ace = new FightingState();
                FightingState bao = new FightingState();
                if (manData.xzstate("夔灵鼓") != null) {
                    if (manData.xzstate("夔灵鼓").getStateEffect() > 0.0D &&
                            !Battlefield.isV(manData.xzstate("夔灵鼓").getStateEffect3())) {
                        manData.xzstate("夔灵鼓").setStateEffect(manData.xzstate("夔灵鼓").getStateEffect() - 1.0D);
                    }
                    if (manData.xzstate("夔灵鼓").getStateEffect() == 0.0D) {
                        manData.RemoveAbnormal(ace, "夔灵鼓");
                        FightingState zhui = new FightingState();
                        zhui.setCamp(manData.getCamp());
                        zhui.setMan(manData.getMan());
                        zhui.setEndState_2("夔灵鼓");
                        zls.add(zhui);
                    }
                }
                ace.setSpeciesid(nomyData.getSe());
                int hmgs = manData.executeHmsz(zls);
                if (hmgs == 2) {
                    sbke = Zap;
                }
                else {
                    sbke = 0L;
                }
                if (g == 0 && maxg > 1) {
                    maxg = manData.executeJtpa(zls, maxg);
                    jgbg = false;
                }
                boolean sb = false;
                int bb_e = 0;
                if (skill_1 != null && Battlefield.isV(skill_1.getSkillgain())) {
                    bb_e = 1;
                }
                else if (skill_2 != null && Battlefield.isV(skill_2.getSkillgain())) {
                    bb_e = 2;
                }
                else if (skill_7 != null && Battlefield.isV(skill_7.getSkillgain())) {
                    bb_e = 7;
                }
                else if (skill_8 != null && Battlefield.isV(skill_8.getSkillgain())) {
                    bb_e = 8;
                }
                ++g;
                int ltap = 0;
                if (!isLT && (g == 1 || g % 3 == 0)) {
                    ltap = (int)((double)manData.getAp() * 0.4);
                }
                int lsap = 0;
                if (!isLT && xyskill_10 != null) {
                    lsap = (int)((double)manData.getAp() * 0.3);
                }
                if (g > 1 && skill13 != null && Battlefield.isV(skill13.getSkillhurt())) {
                    manData.executeJgbg(zls);
                    g = maxg;
                    ace.setCamp(nomyData.getCamp());
                    ace.setMan(nomyData.getMan());
                    ace.setStartState(TypeUtil.JN);
                    ace.setSkillskin(skill13.getSkilltype());
                    zls.add(ace);
                }
                else {
                    int ewai = 0;
                    if (manData.getSkillType("4003") != null && nomyData != null) {
                        int k = (manData.getSp() - nomyData.getSp()) / 500;
                        if (k >= 0) {
                            ewai += (int)Math.min((double)k * manData.getSkillType("4003").getSkillgain(), 4.5);
                        }
                    }
                    if (bb_e == 1 || skill5 != null || skill6 != null || !Battlefield.isV(dsl + dsjc - manData.getQuality().getRolefmzl() - mzjc - manData.mz + (double)manData.executeYszd(3) - manData.mz - (double)ewai)) {
                        if (skill_BB_LHFM != null && g == 1) {
                            FightingState lhfm = new FightingState();
                            ChangeFighting change = new ChangeFighting();
                            if (Battlefield.isV(skill_BB_LHFM.getSkillgain())) {
                                change.setChangetype(skill_BB_LHFM.getSkilltype());
                                change.setChangesum(skill_BB_LHFM.getSkillcontinued());
                            }
                            nomyData.ChangeData(change, lhfm);
                            lhfm.setSkillskin(skill_BB_LHFM.getSkilltype());
                            lhfm.setStartState(TypeUtil.JN);
                            zls.add(lhfm);
                        }
                        ChangeFighting acec = new ChangeFighting();
                        if (skill3 != null) {
                            wulikanhua(acec, skill3, battlefield);
                        }
                        ap = (long)Hurt(sbke + Zap + zap + ((bb_e == 7) ? zap2 : ((bb_e == 8) ? zap3 : 0L)), g, manData, nomyData, type, ace, zls, battlefield, 0.0, 0.0);
                        for (String xin : manData.yinian) {
                            if (xin.equals("圣佑") && Battlefield.random.nextInt(100) < 28) {
                                int gscamp = battlefield.nomy(nomyData.getCamp());
                                List<ManData> gsdatas = MixDeal.get(false, nomyData, 0, gscamp, 0, 0, 0, 0, Battlefield.random.nextInt(2) + 3, -1, battlefield, 1);
                                for (int m = gsdatas.size() - 1; m >= 0; --m) {
                                    ManData bjData = (ManData)gsdatas.get(m);
                                    ChangeFighting bjacec = new ChangeFighting();
                                    FightingState bjace = new FightingState();
                                    ap = (long)Hurt((long)manData.getAp(), g, manData, bjData, TypeUtil.PTGJ, null, zls, null, 0.0, 0.0);
                                    if (manData.getSkin().equals("500196")) {}
                                    bjace.setSkillskin("12521");
                                    ap = (long)((double)ap * 0.7);
                                    bjacec.setChangehp((int)(-ap));
                                    if (m == 0 && skill4 != null) {
                                        bjace.setText("乱舞狂刀，杀#2");
                                    }
                                    FightingPackage.ChangeProcess(bjacec, null, bjData, bjace, type, zls, battlefield);
                                }
                                break;
                            }
                        }
                        if (bcjf != null) {
                            AddState zt1 = nomyData.xzstate(bcjf.getSkilltype());
                            if (Battlefield.isV(bcjf.getSkillgain()) && !(boolean)bcf) {
                                bcf = Boolean.valueOf(true);
                                List<ManData> datas1 = MixDeal.get(false, null, 0, manData.getCamp(), 0, 0, 0, 0, 2 + new Random().nextInt(6), -1, battlefield, 1);
                                for (ManData item : datas1) {
                                    if (item.xzstate(bcjf.getSkilltype()) != null) {
                                        continue;
                                    }
                                    else {
                                        item.addBear(bcjf.getSkilltype());
                                        addState = new AddState();
                                        addState.setStatename(bcjf.getSkilltype());
                                        addState.setStateEffect(bcjf.getSkillhurt());
                                        addState.setStateEffect2(bcjf.getSkillgain());
                                        addState.setSurplus(5);
                                        FightingState fState1 = new FightingState();
                                        fState1.setCamp(item.getCamp());
                                        fState1.setMan(item.getMan());
                                        fState1.setStartState("代价");
                                        fState1.setEndState_1("1241");
                                        zls.add(fState1);
                                        item.getAddStates().add(addState);
                                    }
                                }
                            }
                            if (Battlefield.isV(bcjf.getSkillgain() / 2.0)) {
                                FightingSkill flydSkill = manData.getSkillName("风雷涌动");
                                int fashang = 0;
                                for (ManData fightingdatum : battlefield.fightingdata) {
                                    if (fightingdatum.getCamp() != manData.getCamp() && fightingdatum.xzstate(bcjf.getSkilltype()) != null) {
                                        ChangeFighting changeFighting2 = new ChangeFighting();
                                        FightingState fState2 = new FightingState();
                                        fState2.setCamp(manData.getCamp());
                                        fState2.setMan(manData.getMan());
                                        fState2.setStartState("被攻击");
                                        fState2.setSkin("900096");
                                        zls.add(0, fState2);
                                        FightingState fState3 = new FightingState();
                                        fashang = HurtAction.hurt((int)flydSkill.getSkillhurt(), 1.0, 0.0, 0.0, manData.getSkills(), zls, fState3, manData, fightingdatum, flydSkill, battlefield);
                                        fState3.setEndState_2("1241");
                                        fState3.setSkillskin("1044");
                                        fightingdatum.RemoveAbnormal(new String[] { bcjf.getSkilltype() });
                                    }
                                }
                            }
                        }
                        for (String xin : manData.yinian) {
                            if (xin.equals("魔威") && Battlefield.random.nextInt(100) < 28) {
                                ChangeFighting bjacec2 = new ChangeFighting();
                                FightingState bjace2 = new FightingState();
                                ap = (long)Hurt((long)manData.getAp(), g, manData, nomyData, TypeUtil.PTGJ, null, zls, null, 0.0, 0.0);
                                if (manData.getSkin().equals("500196")) {}
                                bjace2.setSkillskin("12522");
                                ap = (long)((double)ap * 0.7);
                                bjacec2.setChangehp((int)(-ap));
                                FightingPackage.ChangeProcess(bjacec2, null, nomyData, bjace2, type, zls, battlefield);
                                break;
                            }
                        }
                        boolean shentong = true;
                        for (ManData data2 : battlefield.fightingdata) {
                            if (data2.getstat("分身") != null) {
                                shentong = false;
                            }
                        }
                        for (String xin2 : manData.yinian) {
                            if (xin2.equals("神通") && Battlefield.random.nextInt(100) < 28 && shentong) {
                                int gscamp2 = battlefield.nomy(nomyData.getCamp());
                                List<ManData> gsdatas2 = MixDeal.get(false, nomyData, 0, gscamp2, 0, 0, 0, 0, Battlefield.random.nextInt(2) + 3, -1, battlefield, 1);
                                for (int i2 = gsdatas2.size() - 1; i2 >= 0; --i2) {
                                    ManData bjData2 = (ManData)gsdatas2.get(i2);
                                    ChangeFighting bjacec3 = new ChangeFighting();
                                    FightingState bjace3 = new FightingState();
                                    if (manData.getSkin().equals("500196")) {}
                                    bjace3.setSkillskin("12524");
                                    bjData2.addAddState("分身", 0.0, 0.0, 1);
                                    FightingEvents zl = new FightingEvents();
                                    FightingState accepter = new FightingState();
                                    accepter.setCamp(bjData2.getCamp());
                                    accepter.setMan(bjData2.getMan());
                                    List<FightingState> Accepterlist = new ArrayList<>();
                                    accepter.setEndState_1("分身");
                                    Accepterlist.add(accepter);
                                    zl.setAccepterlist(Accepterlist);
                                    battlefield.NewEvents.add(zl);
                                    FightingPackage.ChangeProcess(bjacec3, null, bjData2, bjace3, type, zls, battlefield);
                                }
                                break;
                            }
                        }
                        List<ManData> gsdatas3 = new ArrayList<>();
                        for (ManData data3 : battlefield.fightingdata) {
                            if (data3.getstat("分身") != null && Battlefield.random.nextInt(100) < 33) {
                                gsdatas3.add(data3);
                            }
                        }
                        if (manData.getSkillType("1293") != null && gsdatas3 != null) {
                            for (int i3 = gsdatas3.size() - 1; i3 >= 0; --i3) {
                                ManData bjData3 = (ManData)gsdatas3.get(i3);
                                ChangeFighting bjacec4 = new ChangeFighting();
                                FightingState bjace4 = new FightingState();
                                ap = (long)Hurt((long)manData.getAp(), g, manData, bjData3, TypeUtil.PTGJ, null, zls, null, 0.0, 0.0);
                                if (manData.getSkin().equals("500196")) {}
                                bjace4.setSkillskin("12525");
                                ap = (long)((double)ap * 0.7);
                                bjacec4.setChangehp((int)(-ap));
                                FightingPackage.ChangeProcess(bjacec4, null, bjData3, bjace4, type, zls, battlefield);
                            }
                        }
                        if (ap > 0L && manData.getCamp() != nomyData.getCamp() && nomyData.getStates() == 0 && nomyData.executeAdfh()) {
                            FightingSkill adfh = nomyData.getSkillName("烈火骄阳");
                            List<FightingSkill> skills = ControlAction.getSkills(nomyData, adfh, battlefield.BattleType);
                            ManData mubiao2 = getdaji(nomyData.getCamp(), null, battlefield, nomyData, 0.0);
                            FightingState Accepter = MixDeal.DSMY(nomyData, mubiao2, type, adfh.getSkillid(), battlefield);
                            if (Accepter == null) {
                                mubiao2.addBear(adfh.getSkilltype());
                                Accepter = new FightingState();
                                HurtAction.hurt(0, 1.0, 1000000.0, 0.0, skills, zls, Accepter, nomyData, mubiao2, adfh, battlefield);
                            }
                            else {
                                zls.add(Accepter);
                            }
                            Accepter.setSkillskin(adfh.getSkillid() + "");
                            FightingState Originator = new FightingState();
                            Originator.setEndState(adfh.getSkillname());
                            Originator.setCamp(nomyData.getCamp());
                            Originator.setMan(nomyData.getMan());
                            Originator.setStartState("法术攻击");
                            Originator.setText("上一个敢靠近我的人已经被他的队友打死了，怎么还有人不听话#55");
                            Originator.setSkillsy(adfh.getSkillname());
                            zls.add(Originator);
                        }
                        baodata = battlefield.bhpd(nomyData.getCamp(), nomyData.getMan());
                        if (dhs(type, manData.getCamp(), nomyData.getCamp())) {
                            baodata = null;
                        }
                        if (skill5 != null) {
                            baodata = null;
                        }
                        if (skill6 != null) {
                            baodata = null;
                        }
                        if (baodata == null) {
                            acec.setChangehp((int)(-((double)ap + (isST ? 0.0 : ((double)ap * 0.3)) + (double)ltap + (double)lsap)));
                            if (skill_5 != null && Battlefield.isV(skill_5.getSkillgain()) && (double)new Random().nextInt(100) > nomyData.getQuality().getKyjbm()) {
                                FightingPackage.ChangeProcess(acec, (bb_e == 2) ? null : manData, nomyData, ace, TypeUtil.ZSSH, zls, battlefield);
                                ace.setText("不堪一击的选手#2");
                            }
                            else if (skill8 != null && Battlefield.isV(50.0) && (double)nomyData.getHp_z() * 0.5 > (double)nomyData.getHp() && nomyData.getKangluobao() >= 500.0) {
                                FightingPackage.ChangeProcess(acec, (bb_e == 2) ? null : manData, nomyData, ace, TypeUtil.ZSSH, zls, battlefield);
                                ace.setSkillskin("1248");
                                ace.setText("大威天龙#2");
                            }
                            else {
                                FightingPackage.ChangeProcess(acec, (bb_e == 2) ? null : manData, nomyData, ace, TypeUtil.PTGJ, zls, battlefield);
                                FightingSkill skill_11 = manData.getSkillType("4007");
                                if (nomyData != null && skill_11 != null && maxg == 1 && Battlefield.isV(skill_11.getValue1())) {
                                    AddState xzstate = nomyData.xzstate("抗普通攻击");
                                    if (xzstate != null) {
                                        ChangeFighting acec2 = new ChangeFighting();
                                        FightingState ace2 = new FightingState();
                                        acec2.setChangehp((int)(-((double)ap + (isST ? 0.0 : ((double)ap * 0.3)) + (double)ltap + (double)lsap)));
                                        AddState xzstate2 = nomyData.xzstate("免疫物理");
                                        xzstate2.setStatename("暂停免疫物理");
                                        FightingPackage.ChangeProcess(acec2, (bb_e == 2) ? null : manData, nomyData, ace2, TypeUtil.PTGJ, zls, battlefield);
                                        xzstate2.setStatename("免疫物理");
                                    }
                                }
                            }
                        }
                        else {
                            baodata.setBaohu(nomyData);
                            List<FightingState> States = null;
                            addState = baodata.xzstate("隐身");
                            if (addState != null) {
                                baodata.getAddStates().remove(addState);
                                FightingState org = new FightingState();
                                org.setCamp(baodata.getCamp());
                                org.setMan(baodata.getMan());
                                org.setStartState("代价");
                                org.setEndState_2("隐身");
                                States = new ArrayList<>();
                                States.add(org);
                            }
                            if (States != null) {
                                FightingEvents fightingEvents2 = new FightingEvents();
                                fightingEvents2.setAccepterlist(States);
                                battlefield.NewEvents.add(fightingEvents2);
                            }
                            MixDeal.move(baodata.getCamp(), baodata.getMan(), "移动", nomyData.getCamp() + "|" + nomyData.getMan(), battlefield);
                            ChangeFighting baoc = new ChangeFighting();
                            if (skill3 != null) {
                                wulikanhua(baoc, skill3, battlefield);
                            }
                            baodata.addAddState("保护", 0.0, 0.0, 1);
                            baodata2 = nomyData;
                            nomyData = baodata;
                            acec.setChangehp(-(int)ap);
                            FightingPackage.ChangeProcess(acec, (bb_e == 2) ? null : manData, nomyData, ace, TypeUtil.PTGJ, zls, battlefield);
                        }
                        ace.setSkillskin((bb_e == 7) ? TypeUtil.BB_E_DSFS : ((bb_e == 8) ? TypeUtil.BB_E_QKYZ : null));
                        if (skill9 != null && Battlefield.isV(50.0)) {
                            drrlsz(type, manData, nomyData, ap, battlefield, zls);
                            List<ManData> datas2 = battlefield.getZW(nomyData);
                            for (int i4 = datas2.size() - 1; i4 >= 0; --i4) {
                                ManData nomyData2 = (ManData)datas2.get(i4);
                                if (nomyData2.getStates() == 0) {
                                    drrlsz(type, manData, nomyData2, ap, battlefield, zls);
                                }
                            }
                        }
                        feedback(type, manData, ap, battlefield, zls);
                        neidan(type, manData, nomyData, ap, battlefield, zls, g, 0, (double)gssh);
                        gssh /= 2;
                        if (skill_6 != null && (isHSSF || Battlefield.isV(skill_6.getSkillgain() / 20.0))) {
                            List<ManData> datas2 = battlefield.getZW(nomyData);
                            for (int i4 = datas2.size() - 1; i4 >= 0; --i4) {
                                FightingState ace3 = new FightingState();
                                ManData nomyData3 = (ManData)datas2.get(i4);
                                if (nomyData3.getStates() == 0) {
                                    ChangeFighting acec3 = new ChangeFighting();
                                    ap = (long)Hurt((long)((double)sbke + (double)Zap * skill_6.getSkillgain() / 100.0), g, manData, nomyData3, type, ace3, zls, battlefield, 0.0, 0.0);
                                    acec3.setChangehp((int)(-ap));
                                    FightingPackage.ChangeProcess(acec3, (bb_e == 2) ? null : manData, nomyData3, ace3, type, zls, battlefield);
                                }
                            }
                        }
                        if (skill5 != null) {
                            List<ManData> datas2 = battlefield.getZW(nomyData);
                            for (int i4 = datas2.size() - 1; i4 >= 0; --i4) {
                                FightingState ace3 = new FightingState();
                                ManData nomyData3 = (ManData)datas2.get(i4);
                                if (nomyData3.getStates() == 0) {
                                    ChangeFighting acec3 = new ChangeFighting();
                                    if (skill3 != null) {
                                        wulikanhua(acec3, skill3, battlefield);
                                    }
                                    ap = (long)Hurt(sbke + Zap, g, manData, nomyData3, type, ace3, zls, battlefield, 0.0, 0.0);
                                    acec3.setChangehp((int)(-ap));
                                    FightingPackage.ChangeProcess(acec3, (bb_e == 2) ? null : manData, nomyData3, ace3, type, zls, battlefield);
                                }
                            }
                        }
                        else if (skill6 != null) {
                            List<ManData> datas2 = battlefield.getZW(nomyData);
                            for (int i4 = datas2.size() - 1; i4 >= 0; --i4) {
                                FightingState ace3 = new FightingState();
                                ManData nomyData3 = (ManData)datas2.get(i4);
                                if (nomyData3.getStates() == 0) {
                                    ChangeFighting acec3 = new ChangeFighting();
                                    ap = (long)Hurt(sbke + Zap, g, manData, nomyData3, "普通攻击", ace3, zls, battlefield, 0.0, 0.0);
                                    acec3.setChangehp((int)(-ap));
                                    ace3.setSkillskin("1244");
                                    FightingPackage.ChangeProcess(acec3, (bb_e == 2) ? null : manData, nomyData3, ace3, "普通攻击", zls, battlefield);
                                }
                            }
                        }
                        else if (skill6 != null) {
                            List<ManData> datas2 = battlefield.getZW(nomyData);
                            for (int i4 = datas2.size() - 1; i4 >= 0; --i4) {
                                FightingState ace3 = new FightingState();
                                ManData nomyData3 = (ManData)datas2.get(i4);
                                if (nomyData3.getStates() == 0) {
                                    ChangeFighting acec3 = new ChangeFighting();
                                    ap = (long)Hurt(sbke + Zap, g, manData, nomyData3, "普通攻击", ace3, zls, battlefield, 0.0, 0.0);
                                    acec3.setChangehp((int)(-ap));
                                    ace3.setSkillskin("1244");
                                    FightingPackage.ChangeProcess(acec3, (bb_e == 2) ? null : manData, nomyData3, ace3, "普通攻击", zls, battlefield);
                                }
                            }
                        }
                        else if (skill7 != null && Battlefield.isV(skill7.getSkillgain())) {
                            List<ManData> datas2 = battlefield.getQCRL(nomyData);
                            for (int i4 = datas2.size() - 1; i4 >= 0; --i4) {
                                FightingState ace3 = new FightingState();
                                ManData nomyData3 = (ManData)datas2.get(i4);
                                if (nomyData3.getStates() == 0) {
                                    ChangeFighting acec3 = new ChangeFighting();
                                    Zap = (long)manData.getAp() + (long)((double)manData.getAp() * skill7.getSkillhurt() / 100.0 * 1.0 * 1.0);
                                    ap = (long)Hurt(sbke + Zap, g, manData, nomyData3, "普通攻击", ace3, zls, battlefield, 0.0, 0.0);
                                    acec3.setChangehp((int)(-ap));
                                    ace3.setSkillskin("1082");
                                    FightingPackage.ChangeProcess(acec3, (bb_e == 2) ? null : manData, nomyData3, ace3, "霹雳", zls, battlefield);
                                }
                            }
                        }
                        else if (state1 != null) {
                            List<ManData> datas2 = battlefield.getZW(nomyData);
                            for (int i4 = datas2.size() - 1; i4 >= 0; --i4) {
                                FightingState ace3 = new FightingState();
                                ManData nomyData3 = (ManData)datas2.get(i4);
                                if (nomyData3.getStates() == 0) {
                                    ChangeFighting acec3 = new ChangeFighting();
                                    if (skill3 != null) {
                                        wulikanhua(acec3, skill3, battlefield);
                                    }
                                    ap = (long)Hurt(sbke + Zap, g, manData, nomyData3, type, ace3, zls, battlefield, 0.0, 0.0);
                                    ap = (long)((double)ap * state1.getStateEffect() / 100.0);
                                    acec3.setChangehp((int)(-ap));
                                    FightingPackage.ChangeProcess(acec3, (bb_e == 2) ? null : manData, nomyData3, ace3, type, zls, battlefield);
                                }
                            }
                        } else if (MYFH != null) {
                            AddState HL = manData.xzstate(TypeUtil.HL);
                            if (HL == null && nomyData.getCamp() == manData.getCamp()) {

                            } else {
                                double uu = 1;
                                if (PK_MixDeal.isPK(battlefield.BattleType)) {
                                    uu = 0.5;
                                }
                                long shanghai = 70000;//基础7W
                                long liliang = (long) manData.getKangluobao();
                                shanghai = (long) (liliang * 200) + shanghai;//力量点数X200+70000

                                if (gjEvents2.getAccepterlist() == null) {
                                    gjEvents2.setAccepterlist(new ArrayList<>());
                                }

                                if (nomyData.xzstate("1286") != null && nomyData.xzstate("12861") != null) {
                                    List<ManData> datas = battlefield.fightingdata;
                                    int gscamp = nomyData.getCamp();

                                    for (int j = datas.size() - 1; j >= 0; j--) {
                                        ManData data = datas.get(j);
                                        if (data.getType() == 4 || data.getType() == 3) {
                                            continue;
                                        }
                                        if (data.getStates() == 2) {
                                            continue;
                                        }
                                        if (data.getCamp() != gscamp) {
                                            continue;
                                        }

                                        if (data.xzstate("分辉") != null) {
                                            ChangeFighting bjacec = new ChangeFighting();
                                            bjacec.setChangehp((int) -(shanghai * ((100 - g * 7) * 0.01) * uu / datas.size()));//分辉爆炸伤害=标记伤害/标记人数
                                            FightingState bjace = new FightingState();
                                            bjace.setCamp(data.getCamp());
                                            bjace.setMan(data.getMan());
                                            bjace.setSkillskin("分辉");
                                            data.RemoveAbnormal(bjace, "分辉", "1286", "12861");
                                            bjace.setEndState_2("分辉");
                                            gjEvents2.getAccepterlist().add(bjace);//关键点，用最高级的FightingEvents来装填数据，可以让客户端快读释放
                                            FightingPackage.ChangeProcess(bjacec, null, data, bjace, type, zls, battlefield);

                                        }
                                    }
                                }
                                if (Battlefield.random.nextInt(100) < MYFH.getSkillgain()) {//同排
                                    nomyData.addAddState(MYFH.getSkilltype(), nomyData.getId(), nomyData.getId(), 2);
                                    List<ManData> xymzs = battlefield.getTPzhen(nomyData.getCamp(), nomyData.getMan());
                                    for (int i = xymzs.size() - 1; i >= 0; i--) {
                                        ManData bjData = xymzs.get(i);
                                        if (bjData == null) continue;
                                        ChangeFighting bjacec = new ChangeFighting();
                                        bjacec.setChangehp((int) -(shanghai * ((100 - g * 7) * 0.01) * uu));
                                        FightingState bjace = new FightingState();
                                        bjace.setSkillskin("1286");

                                        ChangeFighting change = new ChangeFighting();
                                        FightingState state = new FightingState();
                                        change.setChangetype("分辉");
                                        change.setChangesum(2);
                                        bjData.ChangeData(change, state);
                                        if (gjEvents2.getAccepterlist() == null) {
                                            gjEvents2.setAccepterlist(new ArrayList<>());
                                        }
                                        gjEvents2.getAccepterlist().add(state);

                                        FightingPackage.ChangeProcess(bjacec, null, bjData, bjace, type, zls, battlefield);
                                    }
                                }
                                if (Battlefield.random.nextInt(100) < MYFH.getSkillgain() * 0.5) {//同列
                                    nomyData.addAddState(MYFH.getSkilltype() + "1", nomyData.getId(), nomyData.getId(), 2);


                                    int gscamp = nomyData.getCamp();
                                    int man = nomyData.getMan();
                                    ManData bjData = battlefield.getTLzhen(gscamp, man);
                                    if (bjData != null) {
                                        ChangeFighting bjacec = new ChangeFighting();
                                        bjacec.setChangehp((int) -(shanghai * ((100 - g * 7) * 0.01) * uu));
                                        FightingState bjace = new FightingState();
                                        bjace.setSkillskin("1286");
                                        ChangeFighting change = new ChangeFighting();
                                        FightingState state = new FightingState();
                                        change.setChangetype("分辉");
                                        change.setChangesum(2);
                                        bjData.ChangeData(change, state);
                                        if (gjEvents2.getAccepterlist() == null) {
                                            gjEvents2.setAccepterlist(new ArrayList<>());
                                        }
                                        gjEvents2.getAccepterlist().add(state);
                                        FightingPackage.ChangeProcess(bjacec, null, bjData, bjace, type, zls, battlefield);
                                    }
                                }
                            }
                        }
                    }
                    else {
                        if (g == 1) {
                            manData.executeJgbg(zls);
                            maxg = 1;
                        }
                        ace.setCamp(nomyData.getCamp());
                        ace.setMan(nomyData.getMan());
                        ace.setStartState("瞬移");
                        ace.setProcessState("躲闪");
                        ace.setEndState(nomyData.getCamp() + "|" + nomyData.getMan() + "|" + getdir(gjw));
                        zls.add(ace);
                        guiwei.add(nomyData);
                        sb = true;
                    }
                }
                if (manData.getCamp() == nomyData.getCamp()) {
                    gjw = getdir(gjw);
                }
                FightingState gjz2 = null;
                if (attackType == 1) {
                    gjz2 = MixDeal.skillmove(nomyData, manData, "9", -1);
                }
                else if (attackType != 2) {
                    gjz2 = new FightingState();
                    gjz2.setCamp(manData.getCamp());
                    gjz2.setMan(manData.getMan());
                    if (skill6 == null) {
                        gjz2.setStartState(TypeUtil.PTGJ);
                    }
                    else if (manData.getSkin() != null && manData.getSkin().equals("500191")) {
                        gjz2.setStartState("特效1");
                    }
                    else {
                        gjz2.setStartState(TypeUtil.PTGJ);
                    }
                    if (skill5 == null) {
                        gjz2.setStartState(TypeUtil.PTGJ);
                    }
                    else if (manData.getSkin() != null && manData.getSkin().equals("400315")) {
                        gjz2.setStartState("特效" + g);
                    }
                    else {
                        gjz2.setStartState(TypeUtil.PTGJ);
                    }
                    gjz2.setEndState(gjw + "");
                }
                gjz2.setSkillsy("attack");
                if (bb_e == 1) {
                    gjz2.setText("箭无虚发#2");
                }
                else if (bb_e == 2) {
                    gjz2.setText("一帆风顺#2");
                }
                else if (bb_e == 7) {
                    gjz2.setText("大圣附身#2");
                }
                else if (bb_e == 8) {
                    gjz2.setText("乾坤一掷#2");
                }
                if (isST && isLT) {
                    this.xysy(xyskill_1, xyskill_3, manData, nomyData, gjz2, zls, battlefield, nocamp, xyskill_9, ap);
                    this.xymw(xyskill_2, xyskill_4, manData, nomyData, gjz2, zls, battlefield, lsap, ap);
                }
                if (!isST || !isSYST) {
                    List<ManData> datas3 = MixDeal.get(false, manData, 0, nocamp, 0, 0, 0, 0, 10, -1, battlefield, 0);
                    if (g == 1) {
                        int size = 0;
                        if (datas3.size() != 0) {
                            if (fl > 0 || fmfl > 0 || z > 0 || nb || zy) {
                                size = 0;
                            }
                            else {
                                size = new Random().nextInt(5) + 2 + ((xyskill_9 != null) ? (new Random().nextInt(2) + 1) : 0);
                            }
                            int ii = 0;
                            while (ii < size) {
                                if (datas3.size() == 0) {
                                    break;
                                }
                                else {
                                    ManData data2 = (ManData)datas3.get(new Random().nextInt(datas3.size()));
                                    if (data2.getStates() != 0 || data2.getMan() == nomyData.getMan()) {
                                        datas3.remove(data2);
                                    }
                                    else {
                                        mubiao1.add(data2);
                                        datas3.remove(data2);
                                    }
                                    ++ii;
                                }
                            }
                        }
                    }
                    for (ManData data4 : mubiao1) {
                        if (data4.getStates() != 0) {
                            continue;
                        }
                        else {
                            FightingState ggg = new FightingState();
                            if (g == 1) {
                                ggg.setCamp(data4.getCamp());
                                ggg.setMan(data4.getMan());
                                ggg.setSkillskin("60005");
                                zls.add(ggg);
                            }
                            ggg = new FightingState();
                            ggg.setCamp(data4.getCamp());
                            ggg.setMan(data4.getMan());
                            if (Battlefield.isV((g == 1) ? 100.0 : (isSYST ? 33.0 : 35.0))) {
                                if (!isSYST) {
                                    if (manData.getModel().split("_")[0].equals("500195")) {
                                        ggg.setSkillskin("60006");
                                    }
                                    else {
                                        ggg.setSkillskin("60007");
                                    }
                                }
                                else {
                                    ggg.setSkillskin("60008");
                                }
                                ChangeFighting changeFighting3 = new ChangeFighting();
                                long currAp = (long)Hurt((long)(manData.getAp() + (int)((double)manData.getAp() * 0.3)), g, manData, data4, TypeUtil.PTGJ, null, zls, battlefield, 0.0, 0.0);
                                changeFighting3.setChangehp((int)(-currAp));
                                FightingPackage.ChangeProcess(changeFighting3, null, data4, ggg, TypeUtil.PTGJ, zls, battlefield);
                            }
                            else {
                                if (!isSYST) {
                                    if (manData.getModel().split("_")[0].equals("500195")) {
                                        ggg.setSkillskin("60009");
                                    }
                                    else {
                                        ggg.setSkillskin("60010");
                                    }
                                }
                                else {
                                    ggg.setSkillskin("60011");
                                }
                                zls.add(ggg);
                            }
                        }
                    }
                }
                if ((!isLT && (g % 3 == 0 || g == 1)) || !isSYST) {
                    List<ManData> datas3 = battlefield.getZW(nomyData);
                    for (ManData data4 : datas3) {
                        FightingState ggg = new FightingState();
                        ggg.setCamp(data4.getCamp());
                        ggg.setMan(data4.getMan());
                        ChangeFighting changeFighting3 = new ChangeFighting();
                        long currAp = 0L;
                        if (bb_e == 7 || bb_e == 8) {
                            currAp = (long)Hurt(sbke + Zap + zap, g, manData, data4, type, null, zls, battlefield, 0.0, 0.0);
                        }
                        else {
                            currAp = (long)Hurt(ap + (long)ltap + (long)lsap, g, manData, data4, TypeUtil.PTGJ, null, zls, battlefield, 0.0, 0.0);
                        }
                        changeFighting3.setChangehp((int)(-currAp));
                        FightingPackage.ChangeProcess(changeFighting3, null, data4, ggg, TypeUtil.PTGJ, zls, battlefield);
                    }
                }
                zls.add(gjz2);
                if (zap != 0L && zzs != null) {
                    gjz2.setText("合力一击#2");
                    for (int i5 = 0; i5 < zzs.size(); ++i5) {
                        ManData data5 = (ManData)zzs.get(i5);
                        zls.add(MixDeal.skillmove(nomyData, data5, "9"));
                    }
                }
                if (!sb) {
                    manData.addDun(ap, gjz2);
                }
                gjEvents2.setAccepterlist(zls);
                battlefield.NewEvents.add(gjEvents2);
                if (guiwei.size() != 0) {
                    FightingEvents moveEvents = new FightingEvents();
                    List<FightingState> moves = new ArrayList<>();
                    for (int j2 = 0; j2 < guiwei.size(); ++j2) {
                        FightingState move = new FightingState();
                        move.setCamp(((ManData)guiwei.get(j2)).getCamp());
                        move.setMan(((ManData)guiwei.get(j2)).getMan());
                        move.setStartState("瞬移");
                        move.setEndState(((ManData)guiwei.get(j2)).getCamp() + "|" + ((ManData)guiwei.get(j2)).getMan());
                        moves.add(move);
                        moveEvents.setAccepterlist(moves);
                    }
                    moveEvents.setAccepterlist(moves);
                    battlefield.NewEvents.add(moveEvents);
                }
                if (bb_e != 2 && skill5 == null && skill_9 == null && guiwei.size() == 0 && nomyData.getStates() == 0 && manData.getStates() == 0) {
                    if (maxf == 0) {
                        boolean isOk = true;
                        if (type.equals("神力加身")) {
                            double dsjv = manData.getFamencsJv(4, new String[] { type });
                            if ((double)(Battlefield.random.nextInt(10000) + 1) <= dsjv * 100.0) {
                                isOk = false;
                            }
                        }
                        if (isOk) {
                            double fjl = nomyData.getQuality().getRoleffjl() - manData.getQuality().getRolehsfj() - (double)hsfjv;
                            maxf = AttackSum(fjl, nomyData.getQuality().getRoleffjv());
                        }
                    }
                    if (f < maxf) {
                        ++f;
                        FightingEvents fjEvents = new FightingEvents();
                        List<FightingState> fjs = new ArrayList<>();
                        FightingState fj = new FightingState();
                        ChangeFighting fjc = new ChangeFighting();
                        ap = (long)Hurt((long)nomyData.getAp(), g, nomyData, manData, "反击", fj, zls, battlefield, 0.0, 0.0);
                        fjc.setChangehp(-nomyData.getAp());
                        FightingPackage.ChangeProcess(fjc, nomyData, manData, fj, "反击", fjs, battlefield);
                        FightingState fjgjz = new FightingState();
                        fjgjz.setCamp(nomyData.getCamp());
                        fjgjz.setMan(nomyData.getMan());
                        fjgjz.setStartState(TypeUtil.PTGJ);
                        fjgjz.setEndState("3");
                        fjs.add(fjgjz);
                        fjEvents.setAccepterlist(fjs);
                        battlefield.NewEvents.add(fjEvents);
                    }
                }
                if (nomyData.getQuality().getLffj() > 0.0) {
                    Random random = new Random();
                    int ljjl = (int)nomyData.getQuality().getLffj();
                    int fjjl = (int)nomyData.getQuality().getFffj();
                    int hjjl = (int)nomyData.getQuality().getHffj();
                    int sjjl = (int)nomyData.getQuality().getSffj();
                    String lei = "";
                    String skillId = "";
                    if (fjjl >= random.nextInt(100)) {
                        lei = "风";
                        skillId = "1044";
                        FightingState fightingState = new FightingState();
                        fightingState.setStartState("代价");
                        ChangeFighting changeFighting4 = new ChangeFighting();
                        changeFighting4.setChangehp((int)((double)(-manData.getMp_z()) * 0.05));
                        FightingPackage.ChangeProcess(changeFighting4, null, manData, fightingState, lei, zls, battlefield);
                        fightingState.setSkillskin(skillId);
                    }
                    else if (ljjl >= random.nextInt(100)) {
                        lei = "雷";
                        skillId = "1049";
                        FightingState fightingState = new FightingState();
                        fightingState.setStartState("代价");
                        ChangeFighting changeFighting4 = new ChangeFighting();
                        changeFighting4.setChangehp((int)((double)(-manData.getMp_z()) * 0.05));
                        FightingPackage.ChangeProcess(changeFighting4, null, manData, fightingState, lei, zls, battlefield);
                        fightingState.setSkillskin(skillId);
                    }
                    else if (hjjl >= random.nextInt(100)) {
                        lei = "火";
                        skillId = "1059";
                        FightingState fightingState = new FightingState();
                        fightingState.setStartState("代价");
                        ChangeFighting changeFighting4 = new ChangeFighting();
                        changeFighting4.setChangehp((int)((double)(-manData.getMp_z()) * 0.05));
                        FightingPackage.ChangeProcess(changeFighting4, null, manData, fightingState, lei, zls, battlefield);
                        fightingState.setSkillskin(skillId);
                    }
                    else if (sjjl >= random.nextInt(100)) {
                        lei = "水";
                        skillId = "1054";
                        FightingState fightingState = new FightingState();
                        fightingState.setStartState("代价");
                        ChangeFighting changeFighting4 = new ChangeFighting();
                        changeFighting4.setChangehp((int)((double)(-manData.getMp_z()) * 0.05));
                        FightingPackage.ChangeProcess(changeFighting4, null, manData, fightingState, lei, zls, battlefield);
                        fightingState.setSkillskin(skillId);
                    }
                }
                if (skill5 != null) {
                    maxg = 1 + manData.ljs(ljjc);
                    if (maxg > 3) {
                        maxg = 3;
                    }
                }
                if (nomyData.getStates() != 0 && g < maxg && zwzb < 2 && skill_10 != null && Battlefield.isV(skill_10.getSkillhurt())) {
                    ++zwzb;
                    List<ManData> datas3 = MixDeal.get(false, manData, 0, battlefield.nomy(nomyData.getCamp()), 0, 0, 0, 0, 1, 1, battlefield, 1);
                    if (datas3.size() != 0) {
                        nomyData = (ManData)datas3.get(0);
                        skill13 = nomyData.getSkillType(TypeUtil.TY_SSC_YSGX);
                        dsl = nomyData.getsx(4, TypeUtil.SX_SBL);
                        dsjc = 0.0;
                        FightingSkill skill16 = nomyData.getAppendSkill(9226);
                        if (skill16 != null) {
                            dsjc = skill16.getSkillhurt();
                        }
                        addState = nomyData.xzstate(TypeUtil.BB_TJTT);
                        if (addState != null) {
                            dsjc -= addState.getStateEffect();
                        }
                        if (skill10 != null) {
                            MixDeal.move(manData.getCamp(), manData.getMan(), "瞬移", nomyData.getCamp() + "|" + nomyData.getMan() + "|" + 3, battlefield);
                            continue;
                        }
                        else if (skill3 != null) {
                            MixDeal.move(manData.getCamp(), manData.getMan(), "遁地", nomyData.getCamp() + "|" + nomyData.getMan() + "|" + 3, battlefield);
                            continue;
                        }
                        else if (attackType == 1) {
                            sxsEvents = MixDeal.sxsmove(nomyData, manData, battlefield);
                            continue;
                        }
                        else {
                            MixDeal.move(manData.getCamp(), manData.getMan(), "移动", nomyData.getCamp() + "|" + nomyData.getMan() + "|" + 3, battlefield);
                            continue;
                        }
                    }
                }
                if (nomyData.getStates() != 0 || nomyData.xzstate("封印") != null) {
                    g = maxg;
                    if (nomyData.getStates() != 0) {
                        manData.executeYwwq(gjz2);
                    }
                    if (nomyData.getStates() != 0) {
                        manData.fmPTGJ(gjz2);
                    }
                    manData.addAttackDie();
                }
                boolean is = true;
                boolean isGW = false;
                if (skill_BB_LHFM != null && skill_BB_LHFM.getSkillhurt() < (double)skill_BB_LHFM.getSkillsum()) {
                    skill_BB_LHFM.setSkillhurt(skill_BB_LHFM.getSkillhurt() + 1.0);
                    maxg = 1;
                    g = 0;
                    maxf = 0;
                    fl = 0;
                    fmfl = 0;
                    skill13 = null;
                    nomyData = getdaji(nocamp, null, battlefield, manData, (skill11 != null) ? skill11.getSkillhurt() : ((skill12 != null) ? skill12.getSkillhurt() : 0.0));
                    if (nomyData != null) {
                        dsjc = 0.0;
                        FightingSkill skill17 = nomyData.getAppendSkill(9226);
                        if (skill17 != null) {
                            dsjc = skill17.getSkillhurt();
                        }
                        addState = nomyData.xzstate(TypeUtil.BB_TJTT);
                        if (addState != null) {
                            dsjc -= addState.getStateEffect();
                        }
                    }
                    is = false;
                    zap = 0L;
                    zzs = null;
                    if (skill_4 != null && Battlefield.isV(skill_4.getSkillgain())) {
                        zzs = new ArrayList<>();
                        for (int i6 = battlefield.fightingdata.size() - 1; i6 >= 0; --i6) {
                            ManData data2 = (ManData)battlefield.fightingdata.get(i6);
                            if (data2.getType() == 1 && data2.getCamp() == manData.getCamp() && data2.getStates() == 0 && data2.getMan() != manData.getMan()) {
                                zap += (long)data2.getAp();
                                zzs.add(data2);
                            }
                        }
                        if (zap >= 250000L) {
                            zap = 250000L;
                        }
                    }
                }
                nb = false;
                zy = false;
                if (is && g == maxg && manData != null && manData.getStates() == 0 && nbzy == 1 && manData.executeZhanyi()) {
                    gssh = manData.getAp();
                    List<ManData> datas4 = MixDeal.get(false, nomyData, 0, nocamp, 0, 0, 0, 0, 1, -1, battlefield, 1);
                    if (datas4.size() != 0) {
                        nomyData = (ManData)datas4.get(0);
                        if (nomyData != null) {
                            fl = 0;
                            fmfl = 0;
                            maxg = 1;
                            g = 0;
                            maxf = 0;
                            skill13 = null;
                            nb = manData.executeNbkj(nbkj++);
                            zy = true;
                            nbzy = 0;
                            dsjc = 0.0;
                            FightingSkill skill18 = nomyData.getAppendSkill(9226);
                            if (skill18 != null) {
                                dsjc = skill18.getSkillhurt();
                            }
                            if (skill5 != null) {
                                maxg = GMax(manData, nomyData, ljjc, battlefield);
                                if (maxg > 3) {
                                    maxg = 3;
                                }
                            }
                            else {
                                maxg = GMax(manData, nomyData, ljjc, battlefield);
                            }
                            isGW = true;
                        }
                        is = false;
                        zap = 0L;
                        zzs = null;
                        if (skill_4 != null && Battlefield.isV(skill_4.getSkillgain())) {
                            zzs = new ArrayList<>();
                            for (int i3 = battlefield.fightingdata.size() - 1; i3 >= 0; --i3) {
                                ManData data3 = (ManData)battlefield.fightingdata.get(i3);
                                if (data3.getType() == 1 && data3.getCamp() == manData.getCamp() && data3.getStates() == 0 && data3.getMan() != manData.getMan()) {
                                    zap += (long)data3.getAp();
                                    zzs.add(data3);
                                }
                            }
                            if (zap >= 250000L) {
                                zap = 250000L;
                            }
                        }
                    }
                }
                if (is && manData != null && manData.getStates() == 0 && nomyData.getStates() != 0) {
                    if (z < 3 && manData.getSkillType("追击") != null) {
                        if ((double)(Battlefield.random.nextInt(100) + 1) < manData.getSkillType("追击").getSkillhurt()) {
                            nomyData = getdaji(nocamp, null, battlefield, manData, (skill11 != null) ? skill11.getSkillhurt() : ((skill12 != null) ? skill12.getSkillhurt() : 0.0));
                            if (nomyData != null) {
                                ++z;
                                maxg = 1;
                                g = 0;
                                maxf = 0;
                                fl = 0;
                                fmfl = 0;
                                skill13 = null;
                                nb = manData.executeNbkj(nbkj++);
                                dsjc = 0.0;
                                FightingSkill skill17 = nomyData.getAppendSkill(9226);
                                if (skill17 != null) {
                                    dsjc = skill17.getSkillhurt();
                                }
                                addState = nomyData.xzstate(TypeUtil.BB_TJTT);
                                if (addState != null) {
                                    dsjc -= addState.getStateEffect();
                                }
                                if (skill5 != null) {
                                    maxg = GMax(manData, nomyData, ljjc, battlefield);
                                    if (maxg > 3) {
                                        maxg = 3;
                                    }
                                }
                                else {
                                    maxg = GMax(manData, nomyData, ljjc, battlefield);
                                }
                                isGW = true;
                            }
                            is = false;
                            zap = 0L;
                            zzs = null;
                            if (skill_4 != null && Battlefield.isV(skill_4.getSkillgain())) {
                                zzs = new ArrayList<>();
                                for (int i6 = battlefield.fightingdata.size() - 1; i6 >= 0; --i6) {
                                    ManData data2 = (ManData)battlefield.fightingdata.get(i6);
                                    if (data2.getType() == 1 && data2.getCamp() == manData.getCamp() && data2.getStates() == 0 && data2.getMan() != manData.getMan()) {
                                        zap += (long)data2.getAp();
                                        zzs.add(data2);
                                    }
                                }
                                if (zap >= 250000L) {
                                    zap = 250000L;
                                }
                            }
                        }
                    }
                    else if (z > 0 && skill_3 != null && Battlefield.isV(skill_3.getSkillgain())) {
                        gssh = manData.getAp();
                        skill_3 = null;
                        gjz2.setText("一鼓作气#2");
                        ++z;
                        maxg = 1;
                        g = 0;
                        maxf = 0;
                        skill13 = null;
                        nomyData = getdaji(nocamp, null, battlefield, manData, (skill11 != null) ? skill11.getSkillhurt() : ((skill12 != null) ? skill12.getSkillhurt() : 0.0));
                        if (nomyData != null) {
                            dsjc = 0.0;
                            FightingSkill skill17 = nomyData.getAppendSkill(9226);
                            if (skill17 != null) {
                                dsjc = skill17.getSkillhurt();
                            }
                            addState = nomyData.xzstate(TypeUtil.BB_TJTT);
                            if (addState != null) {
                                dsjc -= addState.getStateEffect();
                            }
                            if (skill5 != null) {
                                maxg = GMax(manData, nomyData, ljjc, battlefield);
                                if (maxg > 3) {
                                    maxg = 3;
                                }
                            }
                            else {
                                maxg = GMax(manData, nomyData, ljjc, battlefield);
                            }
                            isGW = true;
                        }
                        is = false;
                        zap = 0L;
                        zzs = null;
                        if (skill_4 != null && Battlefield.isV(skill_4.getSkillgain())) {
                            zzs = new ArrayList<>();
                            for (int i6 = battlefield.fightingdata.size() - 1; i6 >= 0; --i6) {
                                ManData data2 = (ManData)battlefield.fightingdata.get(i6);
                                if (data2.getType() == 1 && data2.getCamp() == manData.getCamp() && data2.getStates() == 0 && data2.getMan() != manData.getMan()) {
                                    zap += (long)data2.getAp();
                                    zzs.add(data2);
                                }
                            }
                            if (zap >= 250000L) {
                                zap = 250000L;
                            }
                        }
                    }
                }
                if (is && g == maxg && nomyData != null) {
                    int flType = 0;
                    if (fl == 0 && manData.getSkillType("分裂") != null && (double)(Battlefield.random.nextInt(100) + 1) < manData.getSkillType("分裂").getSkillhurt()) {
                        flType = 1;
                    }
                    if ((type.equals("神力加身") || type.equals("力挽狂澜") || type.equals("势如破竹")) && flType == 0 && fmfl == 0 && manData.getFmfljv() > 0.0 && (double)(Battlefield.random.nextInt(10000) + 1) <= manData.getFmfljv() * 100.0) {
                        flType = 2;
                    }
                    if (skill_9 != null && nomyData.getStates() == 0 && skill_9.getSkillhitrate() < (double)battlefield.CurrentRound && Battlefield.isV(skill_9.getSkillgain())) {
                        skill_9.setSkillhitrate((double)battlefield.CurrentRound);
                        skill_9.setSkillcontinued(1);
                        g = 0;
                        isGW = true;
                    }
                    else if (flType > 0) {
                        gssh = manData.getAp();
                        int x = 0;
                        ManData nomyData4;
                        do {
                            nomyData4 = getdaji(nocamp, null, battlefield, manData, (skill11 != null) ? skill11.getSkillhurt() : ((skill12 != null) ? skill12.getSkillhurt() : 0.0));
                            ++x;
                        } while (nomyData4 != null && nomyData4.getManname().equals(nomyData.getManname()) && x < 10);
                        if (nomyData4 != null) {
                            nomyData = nomyData4;
                        }
                        if (nomyData != null) {
                            if (flType == 1) {
                                ++fl;
                            }
                            else {
                                ++fmfl;
                            }
                            maxg = 1;
                            g = 0;
                            maxf = 0;
                            skill13 = null;
                            nb = manData.executeNbkj(nbkj++);
                            dsjc = 0.0;
                            FightingSkill skill19 = nomyData.getAppendSkill(9226);
                            if (skill19 != null) {
                                dsjc = skill19.getSkillhurt();
                            }
                            if (skill5 != null) {
                                maxg = GMax(manData, nomyData, ljjc, battlefield);
                                if (maxg > 3) {
                                    maxg = 3;
                                }
                            }
                            else {
                                maxg = GMax(manData, nomyData, ljjc, battlefield);
                            }
                            isGW = true;
                        }
                        zap = 0L;
                        zzs = null;
                        if (skill_4 != null && Battlefield.isV(skill_4.getSkillgain())) {
                            zzs = new ArrayList<>();
                            for (int m = battlefield.fightingdata.size() - 1; m >= 0; --m) {
                                ManData data6 = (ManData)battlefield.fightingdata.get(m);
                                if (data6.getType() == 1 && data6.getCamp() == manData.getCamp() && data6.getStates() == 0 && data6.getMan() != manData.getMan()) {
                                    zap += (long)data6.getAp();
                                    zzs.add(data6);
                                }
                            }
                            if (zap >= 250000L) {
                                zap = 250000L;
                            }
                        }
                    }
                    else if ((fl == 1 || fmfl == 1) && skill_3 != null && Battlefield.isV(skill_3.getSkillgain())) {
                        skill_3 = null;
                        gjz2.setText("一鼓作气#2");
                        if (fl == 1) {
                            ++fl;
                        }
                        else {
                            ++fmfl;
                        }
                        maxg = 1;
                        g = 0;
                        maxf = 0;
                        skill13 = null;
                        nomyData = getdaji(nocamp, null, battlefield, manData, (skill11 != null) ? skill11.getSkillhurt() : ((skill12 != null) ? skill12.getSkillhurt() : 0.0));
                        if (nomyData != null) {
                            dsjc = 0.0;
                            FightingSkill skill18 = nomyData.getAppendSkill(9226);
                            if (skill18 != null) {
                                dsjc = skill18.getSkillhurt();
                            }
                            if (skill5 != null) {
                                maxg = GMax(manData, nomyData, ljjc, battlefield);
                                if (maxg > 3) {
                                    maxg = 3;
                                }
                            }
                            else {
                                maxg = GMax(manData, nomyData, ljjc, battlefield);
                            }
                            isGW = true;
                        }
                        zap = 0L;
                        zzs = null;
                        if (skill_4 != null && Battlefield.isV(skill_4.getSkillgain())) {
                            zzs = new ArrayList<>();
                            for (int i3 = battlefield.fightingdata.size() - 1; i3 >= 0; --i3) {
                                ManData data3 = (ManData)battlefield.fightingdata.get(i3);
                                if (data3.getType() == 1 && data3.getCamp() == manData.getCamp() && data3.getStates() == 0 && data3.getMan() != manData.getMan()) {
                                    zap += (long)data3.getAp();
                                    zzs.add(data3);
                                }
                            }
                            if (zap >= 250000L) {
                                zap = 250000L;
                            }
                        }
                    }
                }
                if (nomyData == null || nomyData.getStates() != 0 || manData.getStates() != 0) {
                    g = maxg;
                }
                if (isGW) {
                    if (attackType == 1) {
                        if (sxsEvents != null) {
                            battlefield.NewEvents.add(sxsEvents);
                        }
                    }
                    else {
                        MixDeal.move(manData.getCamp(), manData.getMan(), "移动", manData.getCamp() + "|" + manData.getMan(), battlefield);
                        if (baodata != null) {
                            MixDeal.move(baodata.getCamp(), baodata.getMan(), "移动", baodata.getCamp() + "|" + baodata.getMan(), battlefield);
                        }
                    }
                }
                else if (g >= maxg && manData.getStates() == 0) {
                    if (skill3 != null) {
                        MixDeal.move(manData.getCamp(), manData.getMan(), "遁地", manData.getCamp() + "|" + manData.getMan(), battlefield);
                    }
                    else if (skill10 != null) {
                        if (Battlefield.isV(skill10.getSkillgain())) {
                            MixDeal.BB_TJTT(manData, Zap, nocamp, skill10, battlefield);
                        }
                        else {
                            MixDeal.move(manData.getCamp(), manData.getMan(), "瞬移", manData.getCamp() + "|" + manData.getMan(), battlefield);
                        }
                    }
                    else if (attackType == 1) {
                        if (sxsEvents != null) {
                            battlefield.NewEvents.add(sxsEvents);
                        }
                    }
                    else {
                        MixDeal.move(manData.getCamp(), manData.getMan(), "移动", manData.getCamp() + "|" + manData.getMan(), battlefield);
                        if (baodata != null) {
                            MixDeal.move(baodata.getCamp(), baodata.getMan(), "移动", baodata.getCamp() + "|" + baodata.getMan(), battlefield);
                        }
                    }
                }
                else if (g >= maxg) {
                    if (attackType == 1) {
                        if (sxsEvents != null) {
                            battlefield.NewEvents.add(sxsEvents);
                        }
                    }
                    else {
                        MixDeal.move(manData.getCamp(), manData.getMan(), "瞬移", manData.getCamp() + "|" + manData.getMan(), battlefield);
                        if (baodata != null) {
                            MixDeal.move(baodata.getCamp(), baodata.getMan(), "移动", baodata.getCamp() + "|" + baodata.getMan(), battlefield);
                        }
                    }
                }
                if (nb || zy) {
                    FightingEvents gjEvents3 = new FightingEvents();
                    List<FightingState> zls2 = new ArrayList<>();
                    if (nb) {
                        FightingState gjz3 = new FightingState();
                        gjz3.setCamp(manData.getCamp());
                        gjz3.setMan(manData.getMan());
                        gjz3.setStartState("代价");
                        zls2.add(0, gjz3);
                    }
                    if (zy) {
                        FightingState gjz3 = new FightingState();
                        gjz3.setCamp(manData.getCamp());
                        gjz3.setMan(manData.getMan());
                        gjz3.setStartState("代价");
                        gjz3.setSkillskin("cgyn");
                        zls2.add(0, gjz3);
                        AddState addState2 = new AddState();
                        addState2.setStatename("冲冠一怒");
                        addState2.setType(11018);
                        addState2.setSurplus(1);
                        manData.getAddStates().add(addState2);
                    }
                    gjEvents3.setAccepterlist(zls2);
                    battlefield.NewEvents.add(gjEvents3);
                }
                else {
                    continue;
                }
            }
        }
        if (baodata != null) {
            battlefield.bhpd1(baodata2.getCamp(), baodata2.getMan());
        }
        if (MYFH != null) {
            List<ManData> datas = battlefield.fightingdata;
            int gscamp = nomyData.getCamp();
            FightingEvents zl = new FightingEvents();
            zl.setAccepterlist(new ArrayList<>());

            for (int j = datas.size() - 1; j >= 0; j--) {
                ManData data = datas.get(j);
                if (data.getType()==4||data.getType()==3){ continue; }
                if (data.getStates()==2){ continue; }
                if (data.getCamp() != gscamp ) { continue; }
                FightingState bjace = new FightingState();
                bjace.setCamp(data.getCamp());
                bjace.setMan(data.getMan());
                data.RemoveAbnormal(bjace,"分辉","1286","12861");
                bjace.setEndState_2("分辉");
                zl.getAccepterlist().add(bjace);

            }
            if (zl.getAccepterlist().size()>0) {
                battlefield.NewEvents.add(zl);
            }
        }
        if (jgbg && battlefield.NewEvents != null && battlefield.NewEvents.size() != 0) {
            manData.executeJgbg(((FightingEvents)battlefield.NewEvents.get(battlefield.NewEvents.size() - 1)).getAccepterlist());
        }
    }
    
    private void xymw(FightingSkill xyskill_2, FightingSkill xyskill_4, ManData manData, ManData nomyData, FightingState gjz, List<FightingState> zls, Battlefield battlefield, int lsap, long ap) {
        if (xyskill_2 != null && Battlefield.isV((xyskill_4 != null) ? 56.0 : 28.0) && manData.getCamp() != nomyData.getCamp()) {
            gjz = new FightingState();
            gjz.setCamp(manData.getCamp());
            gjz.setMan(manData.getMan());
            gjz.setSkillskin("60012");
            gjz.setEndState("3");
            gjz.setStartState(TypeUtil.PTGJ);
            ChangeFighting changeFighting = new ChangeFighting();
            FightingState ggg = new FightingState();
            long currAp = (long)Hurt(ap + (long)lsap, 1, manData, nomyData, TypeUtil.PTGJ, ggg, zls, battlefield, 0.0, 0.0);
            changeFighting.setChangehp((int)(-currAp));
            ggg.setSkillsy("hit");
            ggg.setStartState("被攻击");
            nomyData.ChangeData(changeFighting, ggg);
            zls.add(ggg);
        }
    }
    
    private void xysy(FightingSkill xyskill_1, FightingSkill xyskill_3, ManData manData, ManData nomyData, FightingState gjz, List<FightingState> zls, Battlefield battlefield, int nocamp, FightingSkill xyskill_9, long ap) {
        if (xyskill_1 != null && Battlefield.isV((xyskill_3 != null) ? 36.0 : 28.0) && manData.getCamp() != nomyData.getCamp()) {
            gjz.setSkillskin("60013");
            zls.add(gjz);
            List<ManData> datas = MixDeal.get(false, manData, 0, nocamp, 0, 0, 0, 0, 10, -1, battlefield, 0);
            List<ManData> mubiao = new ArrayList<>();
            if (datas.size() != 0) {
                int size = new Random().nextInt(3) + 3 + ((xyskill_9 != null) ? (new Random().nextInt(2) + 1) : 0);
                int ii = 0;
                while (ii < size) {
                    if (datas.size() == 0) {
                        break;
                    }
                    else {
                        ManData data = (ManData)datas.get(new Random().nextInt(datas.size()));
                        if (data.getStates() != 0 || data.getMan() == nomyData.getMan()) {
                            datas.remove(data);
                        }
                        else {
                            mubiao.add(data);
                            datas.remove(data);
                        }
                        ++ii;
                    }
                }
            }
            for (ManData data2 : mubiao) {
                FightingState ggg = new FightingState();
                ggg.setCamp(data2.getCamp());
                ggg.setMan(data2.getMan());
                ggg.setSkillskin("60014");
                ChangeFighting changeFighting = new ChangeFighting();
                long currAp = (long)Hurt((long)((double)ap * 0.7), 1, manData, data2, TypeUtil.PTGJ, null, zls, battlefield, 0.0, 0.0);
                changeFighting.setChangehp((int)(-currAp));
                FightingPackage.ChangeProcess(changeFighting, null, data2, ggg, TypeUtil.PTGJ, zls, battlefield);
            }
        }
    }
    
    public static int Hurt(long ap, int g, ManData mydaData, ManData nomaData, String type, FightingState ace, List<FightingState> zls, Battlefield battlefield, double zm, double kb) {
        double xs = 1.0;
        boolean isZM = false;
        AddState addState = mydaData.xzstate("加狂暴");
        if (!type.equals("反击") && dhs(type, nomaData.getCamp(), mydaData.getCamp())) {
            xs = 0.0;
        }
        else if (ace != null) {
            double jc = mydaData.getQuality().getRolefzml() - nomaData.getQuality().getKzml();
            jc += zm + mydaData.zm + (double)mydaData.executeQtpl(nomaData, zls);
            ap += (long)mydaData.getFmAddAp();
            if (type.equals("神力加身") || type.equals("力挽狂澜") || type.equals("势如破竹")) {
                FightingSkill skill = mydaData.getSkillName(type);
                if (skill != null) {
                    ap += (long)FaMenUtil.getInt(skill.getSkillid(), mydaData.getFmsld(true), 1);
                }
            }
            addState = nomaData.xzstate("千丝网");
            if (addState != null) {
                jc -= addState.getStateEffect2();
            }
            if (battlefield.CurrentRound == 1) {
                FightingSkill TY_R_JZFZ = mydaData.getSkillType(TypeUtil.TY_R_JZFZ);
                if (TY_R_JZFZ != null) {
                    jc -= TY_R_JZFZ.getSkillhurt() / 100.0;
                }
            }
            if (PK_MixDeal.isPK(battlefield.BattleType)) {
                FightingSkill TY_L_10127 = mydaData.getSkillType(TypeUtil.TY_L_10127);
                if (TY_L_10127 != null && nomaData.xzstate("扶摇") == null && Battlefield.isV(TY_L_10127.getSkillhurt())) {
                    nomaData.addAddState("扶摇", 0.0, 0.0, 2);
                }
                TY_L_10127 = mydaData.getSkillType(TypeUtil.TY_L_10136);
                if (TY_L_10127 != null && nomaData.xzstate("沧波") == null && Battlefield.isV(TY_L_10127.getSkillhurt())) {
                    nomaData.addAddState("沧波", 0.0, 0.0, 2);
                }
                TY_L_10127 = mydaData.getSkillType(TypeUtil.TY_L_10128);
                if (TY_L_10127 != null && mydaData.xzstate("扶摇") == null && Battlefield.isV(TY_L_10127.getSkillhurt()) && mydaData.getStates() == 0) {
                    nomaData.addAddState("扶摇", 0.0, 0.0, 2);
                }
                TY_L_10127 = mydaData.getSkillType(TypeUtil.TY_L_10137);
                if (TY_L_10127 != null && mydaData.xzstate("沧波") == null && Battlefield.isV(TY_L_10127.getSkillhurt()) && mydaData.getStates() == 0) {
                    nomaData.addAddState("沧波", 0.0, 0.0, 2);
                }
            }
            if (addState != null) {
                jc += addState.getStateEffect();
            }
            if (Battlefield.isV(jc)) {
                mydaData.executeJdfj(zls);
                ace.setProcessState("致命");
                ap = (long)((double)ap * 1.5);
                isZM = true;
            }
            if (ace.getProcessState() == null || !ace.getProcessState().equals("致命")) {
                jc = mydaData.getQuality().getRolefkbl();
                GroupBuff buff = battlefield.getBuff(mydaData.getCamp(), TypeUtil.YBYT);
                if (buff != null) {
                    jc += buff.getValue();
                }
                if (addState != null) {
                    jc += addState.getStateEffect();
                }
                addState = nomaData.xzstate("千丝网");
                if (addState != null) {
                    jc -= addState.getStateEffect3();
                }
                addState = mydaData.xzstate("神龙摆尾");
                if (addState != null) {
                    jc += (double)mydaData.getFmValue(2, new String[] { "神龙摆尾" });
                }
                if (mydaData.getSkillType("4009") != null && (nomaData.xzstate("混乱") != null || nomaData.xzstate("昏睡") != null || nomaData.xzstate("遗忘") != null)) {
                    mydaData.kb += mydaData.getSkillType("4009").getSkillgain();
                }
                jc += kb + mydaData.kb;
                mydaData.kb = 0.0;
                if (Battlefield.isV(jc)) {
                    ace.setProcessState("狂暴");
                    ap = (long)((double)ap * 1.5);
                }
            }
            if (type.equals(TypeUtil.PTGJ)) {
                ap = rlsz(ap, mydaData, ace);
            }
            else if (type.equals("奋蹄扬威")) {
                ap = (long)((double)ap * 2.5);
                ap = rlsz(ap, mydaData, ace);
            }
            else if (type.equals("兵临城下")) {
                double bs = 2.0;
                if (mydaData.fbgs) {
                    mydaData.fbgs = false;
                    int fbgs = mydaData.executeFbgs(3, null);
                    if (fbgs != 0) {
                        bs = Battlefield.random.nextDouble() * ((double)fbgs - 2.5) + 2.5;
                    }
                }
                else if (Battlefield.isV((double)mydaData.executeFbgs(4, null))) {
                    int fbgs = mydaData.executeFbgs(5, null);
                    if (fbgs != 0) {
                        bs = Battlefield.random.nextDouble() * (double)(fbgs - 1) + 1.0;
                    }
                }
                ap = (long)((double)ap * bs);
                ap = rlsz(ap, mydaData, ace);
            }
            else if (type.equals("混乱技")) {
                ap = rlsz(ap, mydaData, ace);
            }
            FightingSkill skill = mydaData.getSkillType(TypeUtil.TY_R_XYXS);
            if (nomaData.xzstate("昏睡") != null && skill != null) {
                ap = (long)((double)ap * (1.0 - skill.getSkillhurt() / 100.0));
            }
            skill = mydaData.getSkillType(TypeUtil.TY_R_DLEW);
            if (nomaData.xzstate("中毒") != null && skill != null) {
                ap = (long)((double)ap * (1.0 + skill.getSkillhurt() / 100.0));
            }
            if (g > 1) {
                xs *= Math.pow(0.7, (double)(g - 1));
                if (mydaData.jtpa) {
                    xs = mydaData.getJtpaXs(xs);
                }
                if (type.equals("积健为雄") && Battlefield.isV((double)((int)mydaData.getFmsld("积健为雄") / 600))) {
                    xs = 0.7;
                }
            }
            addState = mydaData.getstat(TypeUtil.TY_LL_JBZH);
            if (addState != null) {
                xs *= 1.0 + addState.getStateEffect2();
            }
            mydaData.executeRfzs(nomaData, ace, zls, battlefield);
        }
        return Calculation.getCalculation().PTGJ(mydaData, nomaData, Long.valueOf(ap), type, isZM, xs,battlefield);
    }
    
    public static long rlsz(long ap, ManData mydaData, FightingState ace) {
        FightingSkill skill = mydaData.getSkillType("如来神掌");
        if (skill != null) {
            int sjl = Battlefield.random.nextInt(100);
            if (sjl < 10) {
                ace.setProcessState("四倍");
                ap *= 4L;
            }
            else if (sjl < 30) {
                ace.setProcessState("三倍");
                ap *= 3L;
            }
            else if (sjl < 60) {
                ace.setProcessState("双倍");
                ap *= 2L;
            }
        }
        return ap;
    }
    
    public static int AttackSum(double ljl, double ljs) {
        if ((double)(Battlefield.random.nextInt(100) + 1) < ljl) {
            return (int)ljs;
        }
        return 0;
    }
    
    public static boolean dhs(String type, int camp, int mycamp) {
        return !type.equals("混乱技") && camp == mycamp;
    }
    
    public static int getdir(int dir) {
        if (dir == 7) {
            dir = 3;
        }
        else if (dir == 3) {
            dir = 7;
        }
        else if (dir == 5) {
            dir = 1;
        }
        else if (dir == 1) {
            dir = 5;
        }
        return dir;
    }
    
    public static ManData getdaji(int nocamp, FightingEvents fightingEvents, Battlefield battlefield, ManData data, double jc) {
        if (nocamp != -1) {
            if (fightingEvents != null && fightingEvents.getAccepterlist() != null && fightingEvents.getAccepterlist().size() != 0) {
                int path = battlefield.Datapathhuo(((FightingState)fightingEvents.getAccepterlist().get(0)).getCamp(), ((FightingState)fightingEvents.getAccepterlist().get(0)).getMan());
                if (path != -1) {
                    ManData data2 = (ManData)battlefield.fightingdata.get(path);
                    if (data2 != data && data2.zuoyong(0, -1, 0, 0, new PathPoint(-1, -1), 0, 0, 1)) {
                        return data2;
                    }
                }
            }
            List<ManData> datas = MixDeal.get(false, data, 0, nocamp, 0, 0, 0, 0, 1, -1, battlefield, 1);
            if (datas.size() != 0) {
                return (ManData)datas.get(0);
            }
            return null;
        }
        else {
            if (Battlefield.isV(50.0 - jc)) {
                nocamp = data.getCamp();
            }
            else {
                nocamp = battlefield.nomy(data.getCamp());
            }
            List<ManData> datas = MixDeal.get(false, data, 0, nocamp, 0, 0, 0, 0, 1, -1, battlefield, 1);
            if (datas.size() == 0) {
                datas = MixDeal.get(false, data, 0, battlefield.nomy(data.getCamp()), 0, 0, 0, 0, 1, -1, battlefield, 1);
            }
            if (datas.size() != 0) {
                return (ManData)datas.get(0);
            }
            return null;
        }
    }
    
    public static void wulikanhua(ChangeFighting fighting, FightingSkill skill, Battlefield battlefield) {
        fighting.setChangetype(skill.getSkilltype());
        fighting.setChangesum(1);
        fighting.setChangevlaue(skill.getSkillhurt());
        battlefield.isWLKH = true;
    }
    
    public static int GMax(ManData manData, ManData nomyData, double jc, Battlefield battlefield) {
        jc += (double)manData.ljv;
        AddState addState = manData.xzstate("神龙摆尾");
        if (addState != null) {
            jc += (double)manData.getFmValue(1, new String[] { "神龙摆尾" });
        }
        if (nomyData == null) {
            return 1 + manData.ljs(jc);
        }
        int maxg = 1 + manData.ljs(jc - (double)((nomyData.getSkillType(TypeUtil.TJ_YCDY) == null) ? 0 : 15));
        if (PK_MixDeal.isPK(battlefield.BattleType) && manData.getType() == 0) {
            GroupBuff buff = battlefield.getBuff(nomyData.getCamp(), TypeUtil.BB_QZ);
            if (buff != null && maxg > 3) {
                maxg = 3;
            }
        }
        return maxg;
    }
    
    public static void feedback(String type, ManData manData, long hurt, Battlefield battlefield, List<FightingState> zls) {
        if (type.equals("混乱") || manData.getMan() < 5 || manData.getMan() > 9) {
            return;
        }
        ManData data = battlefield.getPetParents(manData);
        if (data == null || data.getStates() != 0) {
            return;
        }
        FightingSkill skill = manData.getFeedback();
        if (skill == null) {
            return;
        }
        FightingState fightingState = new FightingState();
        fightingState.setStartState("代价");
        fightingState.setSkillskin(skill.getSkilltype());
        ChangeFighting fighting = new ChangeFighting();
        if (skill.getSkilltype().equals(TypeUtil.BB_CWFB)) {
            fighting.setChangehp((int)((double)hurt * 0.3));
        }
        else {
            fighting.setChangemp((int)((double)hurt * 0.3));
        }
        FightingPackage.ChangeProcess(fighting, manData, data, fightingState, TypeUtil.JN, zls, battlefield);
    }
    
    public static void drrlsz(String type, ManData manData, ManData nomyData, long hurt, Battlefield battlefield, List<FightingState> zls) {
        FightingState fightingState = new FightingState();
        fightingState.setStartState("代价");
        fightingState.setSkillskin("1253");
        fightingState.setText("大日如来神掌#2");
        fightingState.setProcessState("吸收  ");
        ChangeFighting changeFighting = new ChangeFighting();
        changeFighting.setChangemp((int)((double)(-hurt) * 0.3));
        changeFighting.setChangemp((int)((double)hurt * 0.3));
        FightingPackage.ChangeProcess(changeFighting, null, nomyData, fightingState, TypeUtil.JN, zls, battlefield);
    }
    
    public static void neidan(String type, ManData manData, ManData nomyData, long ap, Battlefield battlefield, List<FightingState> zls, int g, int z, double gsjc) {
        FightingEvents fightingEvents = new FightingEvents();
        AddState xzstate1 = nomyData.xzstate("含情脉脉");
        Boolean b = Boolean.valueOf(false);
        if (xzstate1 != null && Battlefield.isV(nomyData.getQuality().getSfhqmm()) && !(boolean)b) {
            b = Boolean.valueOf(true);
            BigDecimal skillId = new BigDecimal(xzstate1.getStateEffect2());
            FightingSkill skill = xzstate1.getSkill(skillId.intValue());
            FightingState fightingState = new FightingState();
            fightingState.setCamp(nomyData.getCamp());
            fightingState.setMan(nomyData.getMan());
            fightingEvents.setOriginator(fightingState);
            SpellActionType.getActionById(5).spellAction(nomyData, skill, fightingEvents, battlefield);
        }
        xzstate1 = nomyData.xzstate("乾坤借速");
        if (xzstate1 != null && Battlefield.isV(nomyData.getQuality().getSfqkjs()) && !(boolean)b) {
            b = Boolean.valueOf(true);
            BigDecimal skillId = new BigDecimal(xzstate1.getStateEffect2());
            FightingSkill skill = xzstate1.getSkill(skillId.intValue());
            FightingState fightingState = new FightingState();
            fightingState.setCamp(nomyData.getCamp());
            fightingState.setMan(nomyData.getMan());
            fightingEvents.setOriginator(fightingState);
            SpellActionType.getActionById(5).spellAction(nomyData, skill, fightingEvents, battlefield);
        }
        xzstate1 = nomyData.xzstate("魔神附身");
        if (xzstate1 != null && Battlefield.isV(nomyData.getQuality().getSfmsfs()) && !(boolean)b) {
            b = Boolean.valueOf(true);
            BigDecimal skillId = new BigDecimal(xzstate1.getStateEffect2());
            FightingSkill skill = xzstate1.getSkill(skillId.intValue());
            FightingState fightingState = new FightingState();
            fightingState.setCamp(nomyData.getCamp());
            fightingState.setMan(nomyData.getMan());
            fightingEvents.setOriginator(fightingState);
            SpellActionType.getActionById(5).spellAction(nomyData, skill, fightingEvents, battlefield);
        }
        if (fightingEvents.getAccepterlist() != null && fightingEvents.getAccepterlist().size() > 0) {
            ((FightingState)fightingEvents.getAccepterlist().get(fightingEvents.getAccepterlist().size() - 1)).setStartState("代价");
        }
        if (dhs(type, manData.getCamp(), nomyData.getCamp())) {
            return;
        }
        boolean isNeiDan = TalentTool.getStateByLvl(manData, 2) == null;
        Map<String, AddAttack> attacks = manData.initAttacks();
        if (nomyData.getStates() == 0) {
            FightingSkill fightingSkill = manData.getxlnd();
            if (isNeiDan && fightingSkill != null) {
                FightingState fightingState2 = new FightingState();
                ChangeFighting changeFighting = new ChangeFighting();
                int Hurt = Calculation.getCalculation().SMHurt(manData, nomyData, fightingSkill.getSkillhurt(), 0.0, fightingSkill.getSkilltype(), (manData.getCamp() == 1) ? battlefield.MyDeath : battlefield.NoDeath);
                FightingSkill skill2 = manData.getSkillType("法天相地");
                if (skill2 != null && manData.getShanghai() >= 500.0) {
                    FightingState gjz2 = new FightingState();
                    gjz2.setCamp(manData.getCamp());
                    gjz2.setMan(manData.getMan());
                    gjz2.setSkillskin("1246");
                    zls.add(0, gjz2);
                    Hurt += (int)skill2.getSkillhurt();
                    if (Battlefield.isV(10.0)) {
                        fightingState2.setProcessState("狂暴");
                        Hurt = (int)((double)Hurt * 1.5);
                    }
                }
                changeFighting.setChangehp(-Hurt);
                FightingPackage.ChangeProcess(changeFighting, null, nomyData, fightingState2, fightingSkill.getSkilltype(), zls, battlefield);
                fightingState2.setSkillskin(fightingSkill.getSkillid() + "");
            }
        }
        if (nomyData.getStates() == 0) {
            AddAttack attack = (AddAttack)attacks.get("浩然正气");
            if (isNeiDan && attack != null) {
                FightingSkill skill3 = attack.getSkill();
                FightingSkill skill4 = null;
                if (attack.getAddSkill() != null && attack.getAddSkill().length >= 1) {
                    skill4 = attack.getAddSkill()[0];
                }
                double xs = skill3.getSkillgain() + ((skill4 != null) ? skill4.getSkillgain() : 0.0);
                double value = (double)Battlefield.random.nextInt(100);
                if (xs > value) {
                    FightingState fightingState3 = new FightingState();
                    ChangeFighting hr = new ChangeFighting();
                    ap = Calculation.getCalculation().hrzq(manData, nomyData, skill3.getSkillhurt());
                    ap = (long)((double)ap - nomyData.getQuality().getK_ndhr());
                    if (ap <= 1L) {
                        ap = 1L;
                    }
                    hr.setChangehp((int)(-ap));
                    fightingState3.setSkillskin("浩然正气");
                    if (skill4 != null) {
                        fightingState3.setText((value > skill3.getSkillgain()) ? "一年一度#2" : null);
                    }
                    FightingPackage.ChangeProcess(hr, null, nomyData, fightingState3, TypeUtil.ND, zls, battlefield);
                }
            }
        }
        AddAttack attack = (AddAttack)attacks.get("隔山打牛");
        if (isNeiDan && attack != null) {
            FightingSkill skill3 = attack.getSkill();
            if (Battlefield.isV(skill3.getSkillgain())) {
                int size = 1;
                if (manData.jtpa && manData.getJtpaGs()) {
                    ++size;
                }
                boolean luoshu = false;
                if (manData.getstat("洛书") != null) {
                    ++size;
                    luoshu = true;
                }
                int gscamp = battlefield.nomy(nomyData.getCamp());
                FightingSkill skill5 = null;
                if (attack.getAddSkill() != null && attack.getAddSkill().length >= 1) {
                    skill5 = attack.getAddSkill()[0];
                    if (skill5 != null && Battlefield.isV(skill5.getSkillgain())) {
                        ++size;
                        if (Battlefield.isV(skill5.getSkillgain() * 0.7)) {
                            ++size;
                        }
                        if (Battlefield.isV(skill5.getSkillgain() * 0.3)) {
                            ++size;
                        }
                    }
                    else {
                        skill5 = null;
                    }
                }
                int type2 = -1;
                if (!luoshu) {
                    type2 = 0;
                }
                List<ManData> gsdatas = MixDeal.get(false, nomyData, 0, gscamp, 0, 0, 0, 0, size, type2, battlefield, 1);
                for (int i = gsdatas.size() - 1; i >= 0; --i) {
                    ManData bjData = (ManData)gsdatas.get(i);
                    ChangeFighting bjacec = new ChangeFighting();
                    FightingState bjace = new FightingState();
                    ap = (long)Hurt((long)(int)((double)manData.getAp() * skill3.getSkillhurt() / 100.0), g, manData, bjData, TypeUtil.PTGJ, null, zls, null, 0.0, 0.0);
                    ap = (long)((double)ap * 0.7 / (double)g);
                    ap = (long)((double)ap - nomyData.getQuality().getK_ndgs());
                    if (luoshu) {
                        ap = (long)((double)ap * 0.7) / 2L;
                    }
                    bjacec.setChangehp((int)(-ap));
                    if (i == 0 && skill5 != null) {
                        bjace.setText("乱舞狂刀，杀#2");
                    }
                    FightingPackage.ChangeProcess(bjacec, null, bjData, bjace, type, zls, battlefield);
                    if (luoshu) {
                        ChangeFighting bjacec2 = new ChangeFighting();
                        FightingState bjace2 = new FightingState();
                        bjacec2.setChangehp((int)(-ap));
                        FightingPackage.ChangeProcess(bjacec2, null, bjData, bjace2, "至圣", zls, battlefield);
                    }
                }
            }
        }
        AddState addState = manData.xzstate(TypeUtil.FB_YMGS);
        if (addState != null) {
            int gscamp2 = battlefield.nomy(nomyData.getCamp());
            List<ManData> gsdatas2 = MixDeal.get(false, nomyData, 0, gscamp2, 0, 0, 0, 0, 1, -1, battlefield, 1);
            if (gsdatas2.size() != 0) {
                ManData bjData2 = (ManData)gsdatas2.get(0);
                ChangeFighting bjacec3 = new ChangeFighting();
                FightingState bjace3 = new FightingState();
                ap = (long)Hurt((long)(int)((double)manData.getAp() * addState.getStateEffect() / 100.0), g, manData, bjData2, TypeUtil.PTGJ, null, zls, null, 0.0, 0.0);
                bjacec3.setChangehp((int)(-ap));
                FightingPackage.ChangeProcess(bjacec3, null, bjData2, bjace3, type, zls, battlefield);
            }
        }
        FightingSkill TY_R_FGHX = nomyData.getSkillType(TypeUtil.TY_R_FGHX);
        if (TY_R_FGHX != null && Battlefield.isV(TY_R_FGHX.getSkillhurt())) {
            FightingSkill skill6 = nomyData.skillId(1019);
            FightingEvents fjEvents = new FightingEvents();
            List<FightingState> fjs = new ArrayList<>();
            FightingState fj = new FightingState();
            ChangeFighting fjc = new ChangeFighting();
            int hurt = (int)((double)Calculation.getCalculation().getzdsh(manData, nomyData, skill6, 0.0) * TY_R_FGHX.getSkillgain() / 100.0);
            fjc.setChangehp(-hurt);
            FightingPackage.ChangeProcess(fjc, nomyData, manData, fj, TypeUtil.JN, fjs, battlefield);
            FightingState fjgjz = new FightingState();
            fjgjz.setCamp(manData.getCamp());
            fjgjz.setMan(manData.getMan());
            fjgjz.setStartState(TypeUtil.JN);
            fjgjz.setSkillskin("1019");
            fjs.add(fjgjz);
            fjEvents.setAccepterlist(fjs);
            battlefield.NewEvents.add(fjEvents);
        }
        if (z == 0) {
            FightingSkill TY_G_10069 = manData.getSkillType(TypeUtil.TY_G_10069);
            if (TY_G_10069 != null && PK_MixDeal.isPK(battlefield.BattleType) && Battlefield.isV(TY_G_10069.getSkillhurt())) {
                FightingSkill skill2 = PhyAttack.skills[8];
                FightingState fightingState4 = new FightingState();
                fightingState4.setStartState("代价");
                ChangeFighting changeFighting2 = new ChangeFighting();
                int sh = Calculation.getCalculation().sssh(manData, nomyData, MixDeal.addition(fightingState4, skill2.getSkillhurt(), manData, type));
                FightingPackage.ChangeProcess(changeFighting2, null, nomyData, fightingState4, "三尸虫", zls, battlefield);
                fightingState4.setSkillskin("1069");
                int Vampire = Calculation.getCalculation().sshx(manData, nomyData, TY_G_10069.getSkillgain(), (double)sh);
                List<ManData> datas = SSCAction.minhp(manData.getCamp(), 1, battlefield);
                for (int j = 0; j < 1 && j < datas.size(); ++j) {
                    FightingState f2 = new FightingState();
                    ManData data = (ManData)datas.get(j);
                    AddState s2 = data.xzstate("伤害加深");
                    if (s2 != null) {
                        Vampire = (int)((double)Vampire * (1.0 - s2.getStateEffect() / 100.0));
                    }
                    changeFighting2.setChangehp(Vampire);
                    data.ChangeData(changeFighting2, f2);
                    f2.setStartState("药");
                    zls.add(f2);
                }
            }
            for (int k = 0; k < 10; ++k) {
                double jc = 0.0;
                String lei = null;
                String id = null;
                boolean chimang = false;
                if (k == 0) {
                    jc = manData.getQuality().getF_xf();
                    lei = "风";
                    id = "1044";
                }
                else if (k == 1) {
                    jc = manData.getQuality().getF_xh();
                    lei = "火";
                    id = "1059";
                }
                else if (k == 2) {
                    jc = manData.getQuality().getF_xs();
                    lei = "水";
                    id = "1054";
                }
                else if (k == 3) {
                    jc = manData.getQuality().getF_xl();
                    lei = "雷";
                    id = "1049";
                }
                else if (k == 4) {
                    jc = manData.getQuality().getF_d() - nomyData.getQuality().getKfjzd();
                    lei = "中毒";
                    id = "1019";
                }
                else if (k == 5) {
                    jc = manData.getQuality().getF_h() - nomyData.getQuality().getKfjhl();
                    lei = "混乱";
                    id = "1004";
                }
                else if (k == 6) {
                    jc = manData.getQuality().getF_f() - nomyData.getQuality().getKfjfy();
                    lei = "封印";
                    id = "1009";
                }
                else if (k == 7) {
                    jc = manData.getQuality().getF_zs();
                    lei = "震慑";
                    id = "1024";
                }
                else if (k == 8) {
                    jc = manData.getQuality().getF_sc();
                    if (type.equals("力挽狂澜")) {
                        jc += manData.getFamencsJv(4, new String[] { type });
                    }
                    lei = "三尸虫";
                    id = "1069";
                }
                else if (k == 9) {
                    if (manData.getstat("赤芒") != null) {
                        jc = 30.0;
                    }
                    chimang = true;
                }
                AddState xzstate2 = nomyData.xzstate(TypeUtil.TY_SSC_LFHX);
                if (jc > (double)Battlefield.random.nextInt(100) && xzstate2 == null) {
                    FightingSkill skill7 = null;
                    if (k != 9) {
                        skill7 = PhyAttack.skills[k];
                    }
                    if (k < 4) {
                        FightingState fightingState5 = new FightingState();
                        fightingState5.setStartState("代价");
                        ChangeFighting changeFighting3 = new ChangeFighting();
                        int Hurt2 = Calculation.getCalculation().SMHurt(manData, nomyData, MixDeal.addition(fightingState5, skill7.getSkillhurt(), manData, lei), 0.0, lei, (manData.getCamp() == 1) ? battlefield.MyDeath : battlefield.NoDeath);
                        changeFighting3.setChangehp(-Hurt2);
                        FightingPackage.ChangeProcess(changeFighting3, null, nomyData, fightingState5, lei, zls, battlefield);
                        fightingState5.setSkillskin(id);
                    }
                    else if (k == 4) {
                        FightingState fightingState5 = new FightingState();
                        fightingState5.setStartState("代价");
                        ChangeFighting changeFighting3 = new ChangeFighting();
                        changeFighting3.setChangetype(lei);
                        changeFighting3.setChangesum(1);
                        int up = Calculation.getCalculation().getzdup(manData, skill7, 0.0, nomyData);
                        long hurt2 = (long)Calculation.getCalculation().getzdsh(manData, nomyData, skill7, 0.0);
                        FightingSkill fightingSkill2 = manData.getSkillType(TypeUtil.TZ_SJDG);
                        if (fightingSkill2 != null) {
                            up = (int)((double)up * (1.0 + fightingSkill2.getSkillhurt() / 100.0));
                            changeFighting3.setChangesum(2);
                        }
                        if (hurt2 > (long)up) {
                            hurt2 = (long)up;
                        }
                        hurt2 = (long)((double)hurt2 - nomyData.getQuality().getKzds());
                        if (hurt2 <= 0L) {
                            hurt2 = 1L;
                        }
                        changeFighting3.setChangehp((int)(-hurt2));
                        fightingSkill2 = manData.getSkillType(TypeUtil.TJ_MTL);
                        if (fightingSkill2 != null) {
//                            changeFighting3.setChangemp((int)((double)changeFighting3.getChangehp() * 0.15));
//                            nomyData.addAddState(TypeUtil.TJ_MTL, 0.15, 0.0, (int)(hurt2 / 2L));
                            changeFighting3.setChangemp(-(int)((double)nomyData.getMp_z() * 0.40));
                            nomyData.addAddState(TypeUtil.TJ_MTL, 0.40, 0.0, (int)(nomyData.getMp_z() / 2L));

                        }
                        changeFighting3.setChangevlaue((double)(hurt2 / 2L));
                        fightingSkill2 = manData.getSkillType(TypeUtil.TJ_FSSS);
                        if (fightingSkill2 != null) {
//                            changeFighting3.setChangemp((int)((double)changeFighting3.getChangehp() * 0.2));
//                            nomyData.addAddState(TypeUtil.TJ_FSSS, 0.2, 0.0, (int)(hurt2 / 2L));
                            changeFighting3.setChangemp(-(int)((double)nomyData.getMp_z() * 0.45));
                            nomyData.addAddState(TypeUtil.TJ_FSSS, 0.45, 0.0, (int)(nomyData.getMp_z() / 2L));
                        }
                        changeFighting3.setChangevlaue((double)(hurt2 / 2L));
                        FightingPackage.ChangeProcess(changeFighting3, null, nomyData, fightingState5, lei, zls, battlefield);
                        fightingState5.setSkillskin(id);
                    }
                    else if (k == 7) {
                        FightingState fightingState5 = new FightingState();
                        fightingState5.setStartState("代价");
                        ChangeFighting changeFighting3 = ZSAction.TypeHurtCurrent(manData, nomyData, skill7.getSkillhurt());
                        FightingPackage.ChangeProcess(changeFighting3, null, nomyData, fightingState5, lei, zls, battlefield);
                        fightingState5.setSkillskin(id);
                    }
                    else if (k == 8) {
                        FightingState fightingState5 = new FightingState();
                        fightingState5.setStartState("代价");
                        ChangeFighting changeFighting3 = new ChangeFighting();
                        int sh2 = Calculation.getCalculation().sssh(manData, nomyData, MixDeal.addition(fightingState5, skill7.getSkillhurt(), manData, type));
                        changeFighting3.setChangehp(-sh2);
                        FightingPackage.ChangeProcess(changeFighting3, null, nomyData, fightingState5, lei, zls, battlefield);
                        fightingState5.setSkillskin(id);
                        int vampire;
                        if (type.equals("力挽狂澜")) {
                            vampire = (int)((double)sh2 * Arith.div(manData.getFamencsJv(5, new String[] { type }), 100.0, 2));
                        }
                        else {
                            vampire = Calculation.getCalculation().sshx(manData, nomyData, skill7.getSkillgain(), (double)sh2);
                        }
                        List<ManData> datas2 = SSCAction.minhp(manData.getCamp(), 1, battlefield);
                        for (int l = 0; l < 1 && l < datas2.size(); ++l) {
                            FightingState f3 = new FightingState();
                            ManData data2 = (ManData)datas2.get(l);
                            AddState s3 = data2.xzstate("伤害加深");
                            if (s3 != null) {
                                vampire = (int)((double)vampire * (1.0 - s3.getStateEffect() / 100.0));
                            }
                            changeFighting3.setChangehp(vampire);
                            data2.ChangeData(changeFighting3, f3);
                            f3.setStartState("药");
                            zls.add(f3);
                        }
                    }
                    else if (chimang) {
                        FightingState fightingState5 = new FightingState();
                        fightingState5.setStartState("代价");
                        ChangeFighting changeFighting3 = new ChangeFighting();
                        int sh2 = Calculation.getCalculation().sssh(manData, nomyData, MixDeal.addition(fightingState5, (double)manData.getAp(), manData, type));
                        sh2 = (int)((double)manData.getHp() * 0.02);
                        changeFighting3.setChangehp(-sh2);
                        FightingPackage.ChangeProcess(changeFighting3, null, nomyData, fightingState5, "三尸虫", zls, battlefield);
                        id = "1069";
                        fightingState5.setSkillskin(id);
                        int Vampire2 = Calculation.getCalculation().sshx(manData, nomyData, 2500.0, (double)sh2);
                        List<ManData> datas2 = SSCAction.minhp(manData.getCamp(), 1, battlefield);
                        for (int l = 0; l < 1 && l < datas2.size(); ++l) {
                            FightingState f3 = new FightingState();
                            ManData data2 = (ManData)datas2.get(l);
                            AddState s3 = data2.xzstate("伤害加深");
                            if (s3 != null) {
                                Vampire2 = (int)((double)Vampire2 * (1.0 - s3.getStateEffect() / 100.0));
                            }
                            changeFighting3.setChangehp(sh2 * 10);
                            data2.ChangeData(changeFighting3, f3);
                            f3.setStartState("药");
                            zls.add(f3);
                        }
                    }
                    else {
                        FightingState fightingState5 = new FightingState();
                        fightingState5.setStartState("代价");
                        ChangeFighting changeFighting3 = new ChangeFighting();
                        changeFighting3.setChangetype(lei);
                        changeFighting3.setChangesum(1);
                        FightingPackage.ChangeProcess(changeFighting3, null, nomyData, fightingState5, lei, zls, battlefield);
                        fightingState5.setSkillskin(id);
                    }
                }
            }
        }
    }
    
    public static void initSkill() {
        PhyAttack.skills = new FightingSkill[9];
        for (int i = 0; i < 9; ++i) {
            int id = 0;
            if (i == 0) {
                id = 1044;
            }
            else if (i == 1) {
                id = 1059;
            }
            else if (i == 2) {
                id = 1054;
            }
            else if (i == 3) {
                id = 1049;
            }
            else if (i == 4) {
                id = 1019;
            }
            else if (i == 5) {
                id = 1004;
            }
            else if (i == 6) {
                id = 1009;
            }
            else if (i == 7) {
                id = 1024;
            }
            else if (i == 8) {
                id = 1069;
            }
            Skill skill = GameServer.getSkill(id + "");
            PhyAttack.skills[i] = new FightingSkill(skill, 120, 3, 1.0, 0L, 0);
            if (PhyAttack.skills[i].getSkillcontinued() != 0) {
                PhyAttack.skills[i].setSkillcontinued(1);
            }
        }
    }
    
    public static long getMoney(ManData data, Battlefield battlefield) {
        if (data.getType() == 1) {
            data = battlefield.getPetParents(data);
        }
        else if (data.getType() != 0) {
            return 0L;
        }
        if (data == null) {
            return 0L;
        }
        ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(data.getManname());
        if (ctx == null) {
            return 0L;
        }
        LoginResult login = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (login == null) {
            return 0L;
        }
        return login.getGold().longValue();
    }
}
