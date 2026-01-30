package come.tool.FightingSpellAction;

import java.util.Iterator;
import come.tool.FightingData.GroupBuff;
import come.tool.FightingData.AddState;
import java.util.List;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingDataAction.PhyAttack;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.PK_MixDeal;
import com.gl.util.FaMenUtil;
import come.tool.FightingData.TypeUtil;
import java.util.ArrayList;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public class L_PLCBFYAction implements SpellAction
{
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        FightingSkill tycSkill = null;
        if (skill.getSkillid() == 9510 || skill.getSkillid() == 10129 || skill.getSkillid() == 9710 || skill.getSkillid() == 9711 || skill.getSkillid() == 9811 || skill.getSkillid() == 9812 || skill.getSkillid() == 9810) {
            tycSkill = skill;
            skill = manData.skillId(MixDeal.getTYSkillId(tycSkill.getSkillid()));
            if (skill == null) {
                return;
            }
        }
        boolean isQTHD = tycSkill != null && tycSkill.getSkillid() == 10129;
        List<FightingState> Accepterlist = new ArrayList<>();
        List<ManData> datas = null;
        if (isQTHD) {
            datas = battlefield.getTP(battlefield.nomy(manData.getCamp()), events);
        }
        else {
            datas = MixDeal.getjieshou(events, skill, manData, Accepterlist, battlefield);
        }
        if (datas.size() == 0) {
            FightingState Originator = events.getOriginator();
            if (manData.daijia(skill, Originator, battlefield)) {
                return;
            }
            Originator.setStartState("法术攻击");
            Originator.setSkillsy(skill.getSkillname());
            events.setOriginator(null);
            Accepterlist.add(Originator);
            events.setAccepterlist(Accepterlist);
            battlefield.NewEvents.add(events);
            if (skill.getSkillid() == 1084) {
                FightingSkill fightingSkill = manData.getSkillType(TypeUtil.TY_L_PL_FQZJ);
                if (fightingSkill != null) {
                    manData.addAddState2(TypeUtil.TY_L_PL_FQZJ, fightingSkill.getSkillhurt(), 0.0, 1);
                }
            }
            return;
        }
        else {
            FightingState Originator = events.getOriginator();
            if (manData.daijia(skill, Originator, battlefield)) {
                return;
            }
            Originator.setStartState("法术攻击");
            Originator.setSkillsy(skill.getSkillname());
            String type = skill.getSkilltype();
            FightingSkill dsSkill = null;
            FightingSkill jgSkill = null;
            double mzjc = manData.getQuality().getRolefmzl() - 40.0 + manData.getQuality().getRolehsds() + (double)manData.executeYszd(3) - (double)manData.executeYszd(2);
            double ljjc = manData.getQuality().getRolefljl();
            double dsjc = 0.0;
            double xs = 1.0;
            AddState addState = manData.xzstate("行气如虹");
            if (addState != null) {
                mzjc += (double)((int)manData.getFmsld("行气如虹") / 600);
                ljjc += (double)((int)manData.getFmsld("行气如虹") / 215);
            }
            addState = manData.xzstate("法门连击");
            if (addState != null) {
                ljjc += (double)((int)manData.getFmsld("法门连击") / 600);
            }
            addState = manData.xzstate("神不守舍");
            if (addState != null) {
                int fmId = (int)addState.getStateEffect();
                int fmsld = (int)addState.getStateEffect2();
                xs += FaMenUtil.getDouble(fmId, fmsld, 1);
            }
            if (manData.xzstate("散灵须") != null) {
                mzjc -= manData.xzstate("散灵须").getStateEffect();
            }
            List<FightingSkill> skills = null;
            FightingSkill skillTwo = null;
            FightingSkill skillfby = null;
            FightingSkill skill2 = null;
            if (type.equals("霹雳")) {
                skills = ControlAction.getL_PL_Skills(manData, skill, battlefield.BattleType);
                FightingSkill fightingSkill2 = manData.getSkillType(TypeUtil.TY_L_PL_JDBX);
                if (fightingSkill2 != null) {
                    mzjc += fightingSkill2.getSkillhurt();
                }
                if (skill.getSkillid() == 1084) {
                    fightingSkill2 = manData.getSkillType(TypeUtil.TY_L_PL_XLTJ);
                    if (fightingSkill2 != null) {
                        ljjc += fightingSkill2.getSkillhurt();
                    }
                    fightingSkill2 = manData.getSkillType(TypeUtil.TY_L_10119);
                    if (fightingSkill2 != null) {
                        skill.setSkillhurt(skill.getSkillhurt() * fightingSkill2.getSkillhurt() / 100.0);
                        manData.getSkills().remove(fightingSkill2);
                    }
                    skills = ControlAction.addSkill(manData.getSkillType(TypeUtil.TY_L_PL_SLJL), skills);
                    addState = manData.xzstate(TypeUtil.TY_L_PL_FQZJ);
                    if (addState != null) {
                        mzjc += addState.getStateEffect();
                    }
                }
                if (PK_MixDeal.isPK(battlefield.BattleType)) {
                    jgSkill = manData.getSkillType(TypeUtil.TY_L_PL_QSYZ);
                }
                if (isQTHD) {}
                fightingSkill2 = manData.getAppendSkill(9811);
                if (fightingSkill2 != null) {
                    ljjc -= fightingSkill2.getSkillhurt();
                }
            }
            else if (type.equals("沧波")) {
                skills = ControlAction.getL_CB_Skills(manData, skill, battlefield.BattleType);
                FightingSkill fightingSkill2 = manData.getSkillType(TypeUtil.TY_L_CB_BLJT);
                if (fightingSkill2 != null) {
                    mzjc += fightingSkill2.getSkillhurt();
                }
                fightingSkill2 = manData.getSkillType(TypeUtil.TY_L_CB_ALXY);
                if (fightingSkill2 != null) {
                    xs += fightingSkill2.getSkillhurt() / 100.0;
                }
                jgSkill = (dsSkill = manData.getSkillType(TypeUtil.TY_L_CB_WBYL));
                if (skill.getSkillid() == 1089) {
                    skillTwo = manData.getSkillType(TypeUtil.TY_L_CB_DHFJ);
                }
            }
            else if (type.equals("扶摇")) {
                skills = ControlAction.getL_FY_Skills(manData, skill, battlefield.BattleType);
                FightingSkill fightingSkill2 = manData.getSkillType(TypeUtil.TY_L_FY_ZFJY);
                if (fightingSkill2 != null) {
                    mzjc += fightingSkill2.getSkillhurt();
                }
                fightingSkill2 = manData.getSkillType(TypeUtil.TY_L_FY_LKTY);
                if (fightingSkill2 != null) {
                    xs += fightingSkill2.getSkillhurt() / 100.0;
                }
                jgSkill = (dsSkill = manData.getSkillType(TypeUtil.TY_L_FY_YSZY));
                if (skill.getSkillid() == 1099) {
                    skillTwo = manData.getSkillType(TypeUtil.TY_L_FY_HHHF);
                }
                skillfby = manData.getSkillType(TypeUtil.TY_L_10162);
                skill2 = manData.getSkillType(TypeUtil.TY_L_10157);
            }
            if (tycSkill != null && (tycSkill.getSkillid() == 9710 || tycSkill.getSkillid() == 9711 || tycSkill.getSkillid() == 9811 || tycSkill.getSkillid() == 9812)) {
                skills = ControlAction.addSkill(tycSkill, skills);
                tycSkill = null;
            }
            double qsx = manData.getsx(2, type);
            int maxG = 1;
            if (isQTHD) {
                xs = tycSkill.getSkillhurt() / 100.0;
            }
            long Zap = (long)((double)manData.getAp() * skill.getSkillhurt() / 100.0 * xs * (1.0 + qsx / 100.0));
            addState = manData.xzstate("沧波");
            if (addState != null) {
                mzjc -= addState.getStateEffect();
            }
            if ("霹雳".equals(type)) {
                maxG += skill.getSkillcontinued();
                addState = manData.xzstate(TypeUtil.L_LL);
                if (addState != null) {
                    Zap = (long)((double)Zap * 1.16);
                }
                addState = manData.xzstate("霹雳连击");
                if (addState != null) {
                    ljjc += addState.getStateEffect();
                }
                if (isQTHD) {
                    maxG = 3;
                }
            }
            mzjc += skill.getSkillgain();
            mzjc += qsx / 2.0;
            FightingSkill skill3 = manData.getAppendSkill(9203);
            if (skill3 != null) {
                mzjc += skill3.getSkillhurt();
                skill3 = null;
            }
            GroupBuff buff = battlefield.getBuff(manData.getMan(), TypeUtil.YBYT);
            if (buff != null) {
                mzjc += buff.getValue();
            }
            buff = battlefield.getBuff(manData.getMan(), TypeUtil.BB_E_HYMB);
            if (buff != null) {
                dsjc += buff.getValue();
            }
            FightingSkill skill4 = manData.getAppendSkill(9347);
            if (skill4 != null) {
                Zap = (long)((double)Zap * (1.0 - skill4.getSkillhurt() / 100.0));
                mzjc -= skill4.getSkillhurt();
                ljjc -= skill4.getSkillhurt();
            }
            double skillhitrate = 0.0;
            if ("沧波".equals(type)) {
                skillhitrate = skill.getSkillhitrate();
                skillhitrate += qsx / 5.0;
            }
            else if ("扶摇".equals(type)) {
                skillhitrate = skill.getSkillhitrate();
                skillhitrate += qsx / 5.0;
            }
            int sw = 0;
            int maxSize = 0;
            for (int i = 0; i < maxG; ++i) {
                int mzSize = 0;
                for (int j = 0; j < datas.size(); ++j) {
                    ManData data = (ManData)datas.get(j);
                    if (data.getStates() == 0) {
                        if (i == 0) {
                            data.addBear(type);
                        }
                        else if (!isQTHD && !Battlefield.isV(ljjc)) {
                            addState = manData.xzstate("积健为雄");
                            if (addState != null) {
                                addState = manData.xzstate("法门连击");
                                if (addState != null) {
                                    continue;
                                }
                                else {
                                    manData.addAddState("法门连击", 0.0, 0.0, 2);
                                    continue;
                                }
                            }
                            else {
                                continue;
                            }
                        }
                        addState = data.xzstate(TypeUtil.TY_L_10122);
                        if (addState != null) {
                            dsjc -= addState.getStateEffect();
                        }
                        boolean is = (dsSkill == null || !Battlefield.isV(dsSkill.getSkillhurt())) && Battlefield.isV(MixDeal.getXS(data.getQihe()) + data.getsx(4, TypeUtil.SX_SBL) + dsjc - mzjc);
                        if (is && type.equals("霹雳")) {
                            addState = data.xzstate(TypeUtil.TY_L_PL_BLJD);
                            if (addState != null && Battlefield.isV(addState.getStateEffect())) {
                                is = false;
                            }
                            if (tycSkill != null && tycSkill.getSkillid() == 9510) {
                                is = false;
                            }
                        }
                        FightingState Accepter = new FightingState();
                        if (is) {
                            Accepter.setCamp(data.getCamp());
                            Accepter.setMan(data.getMan());
                            Accepter.setStartState(TypeUtil.JN);
                            Accepter.setProcessState("躲闪");
                            Accepterlist.add(Accepter);
                        }
                        else {
                            ++mzSize;
                            AddState addState2 = null;
                            if (jgSkill != null && Battlefield.isV(jgSkill.getSkillhurt())) {
                                addState2 = data.xzstate("免疫物理");
                                if (addState2 != null) {
                                    data.getAddStates().remove(addState2);
                                }
                            }
                            long ap = (long)PhyAttack.Hurt(Zap, isQTHD ? 1 : (i + 1), manData, data, TypeUtil.JN, Accepter, Accepterlist, battlefield, 0.0, 0.0);
                            addState2 = manData.xzstate(TypeUtil.TY_L_10148);
                            if (data.getType() == 1 && addState2 != null) {
                                ap += (long)((double)ap * addState2.getStateEffect());
                            }
                            addState2 = manData.xzstate(TypeUtil.TY_L_10153);
                            if (data.getType() == 1 && addState2 != null) {
                                ap += (long)((double)ap * addState2.getStateEffect());
                            }
                            addState2 = manData.xzstate(TypeUtil.TY_L_10158);
                            if (data.getType() == 1 && addState2 != null) {
                                ap += (long)((double)ap * addState2.getStateEffect());
                            }
                            hurt(manData, data, i, skillhitrate, ap, skill, skills, Accepter, Accepterlist, battlefield, Boolean.valueOf(false));
                            if (addState2 != null) {
                                data.getAddStates().add(addState2);
                            }
                            if (data.getStates() == 1) {
                                ++sw;
                                if (tycSkill != null && tycSkill.getSkillid() == 9511) {
                                    int add = (int)((double)data.huoAp() * tycSkill.getSkillhurt() / 100.0);
                                    if (add > 2000) {
                                        add = 2000;
                                    }
                                    tycSkill.setSkillgain(tycSkill.getSkillgain() + (double)add);
                                    if (tycSkill.getSkillgain() < 60000.0) {
                                        manData.setAp(manData.huoAp() + add);
                                    }
                                    FightingSkill TY_L_10123 = manData.getSkillType(TypeUtil.TY_L_10123);
                                    if (TY_L_10123 != null && Battlefield.isV(TY_L_10123.getSkillhurt()) && (skill.getSkillid() < 1081 || skill.getSkillid() > 1085)) {
                                        manData.setAp(manData.huoAp() + add);
                                    }
                                }
                                if (skills != null) {
                                    for (int k = skills.size() - 1; k >= 0; --k) {
                                        FightingSkill tSkill = (FightingSkill)skills.get(k);
                                        int id = tSkill.getSkillid();
                                        if (id == 9506) {
                                            if (Battlefield.isV(tSkill.getSkillhurt())) {
                                                addState = data.xzstate(TypeUtil.KX);
                                                if (addState != null) {
                                                    data.getAddStates().remove(addState);
                                                    Accepter.setEndState_2(addState.getStatename());
                                                }
                                            }
                                        }
                                        else if (id == 9507) {
                                            data.addAddState(type, tSkill.getSkillhurt(), 0.0, 2);
                                        }
                                        else if (id == 9508) {
                                            if (Battlefield.isV(40.0)) {
                                                double add2 = (double)data.huoAp() * tSkill.getSkillhurt() / 100.0;
                                                if (add2 > 15000.0) {
                                                    add2 = 15000.0;
                                                }
                                                data.addAddState(type, add2, 0.0, 2);
                                            }
                                        }
                                        else if (id == 10148) {
                                            manData.addAddState(TypeUtil.TY_L_10148, 0.5, 0.0, 2);
                                        }
                                        else if (id == 10153) {
                                            manData.addAddState(TypeUtil.TY_L_10153, 0.5, 0.0, 2);
                                        }
                                        else if (id == 10158) {
                                            manData.addAddState(TypeUtil.TY_L_10158, 0.5, 0.0, 2);
                                        }
                                        else if (type.equals("扶摇") && (id == 10147 || id == 1052 || id == 10157)) {
                                            if (Battlefield.isV(tSkill.getSkillhurt())) {
                                                FightingState Accepter2 = new FightingState();
                                                ChangeFighting fighting = new ChangeFighting();
                                                fighting.setChangemp((int)((double)ap * 0.01));
                                                FightingPackage.ChangeProcess(fighting, manData, data, Accepter2, TypeUtil.JN, Accepterlist, battlefield);
                                            }
                                        }
                                        else if (data.xzstate("沧波") != null && (id == 10146 || id == 10151)) {
                                            data.getQuality().addLZMZ(Battlefield.splitSkillNames(tSkill.getSkillname()), tSkill.getSkillgain1(), tSkill.getSkillhitrate());
                                        }
                                        else if (type.equals("霹雳") && (id == 10156 || id == 10161)) {
                                            data.getQuality().addLZMZ(Battlefield.splitSkillNames(tSkill.getSkillname()), tSkill.getSkillhurt(), tSkill.getSkillgain());
                                        }
                                        else if (data.xzstate("扶摇") != null && (id == 10161 || id == 10166)) {
                                            int maxHp = data.getHp_z();
                                            maxHp = (int)((double)maxHp - data.fyhomax);
                                            int maxAddHp = (int)((double)maxHp * tSkill.getSkillhitrate() / 100.0);
                                            if (data.fyhomax < (double)maxAddHp) {
                                                data.fyhomax += (double)maxHp * tSkill.getSkillgain1() / 100.0;
                                                if (data.fyhomax > (double)maxAddHp) {
                                                    data.fyhomax = (double)maxAddHp;
                                                }
                                                data.setHp_z(maxHp + (int)data.fyhomax);
                                            }
                                        }
                                    }
                                }
                            }
                            if (skillTwo != null && Battlefield.isV(skillTwo.getSkillhurt())) {
                                FightingState Accepter3 = new FightingState();
                                ChangeFighting fighting2 = new ChangeFighting();
                                fighting2.setChangehp((int)(-ap));
                                FightingPackage.ChangeProcess(fighting2, manData, data, Accepter3, TypeUtil.PTGJ, Accepterlist, battlefield);
                            }
                            if (skillfby != null && Battlefield.isV(skillfby.getSkillhurt())) {
                                FightingState Accepter3 = new FightingState();
                                ChangeFighting fighting2 = new ChangeFighting();
                                fighting2.setChangemp((int)((double)ap * 0.01));
                                FightingPackage.ChangeProcess(fighting2, manData, data, Accepter3, TypeUtil.JN, Accepterlist, battlefield);
                            }
                            if (skill2 != null && Battlefield.isV(skill2.getSkillhurt())) {
                                FightingState Accepter3 = new FightingState();
                                ChangeFighting fighting2 = new ChangeFighting();
                                fighting2.setChangemp((int)((double)ap * 0.01));
                                FightingPackage.ChangeProcess(fighting2, manData, data, Accepter3, TypeUtil.JN, Accepterlist, battlefield);
                            }
                        }
                        if (i == 0) {
                            Accepter.setSkillskin(skill.getSkillid() + "");
                        }
                    }
                }
                if (mzSize > maxSize) {
                    maxSize = mzSize;
                }
            }
            for (ManData data2 : datas) {
                String type2 = "霹雳end";
                FightingState Accepter4 = new FightingState();
                ChangeFighting fighting3 = new ChangeFighting();
                FightingPackage.ChangeProcess(fighting3, manData, data2, Accepter4, type2, Accepterlist, battlefield);
            }
            if (tycSkill != null && tycSkill.getSkillid() == 9510 && sw >= 3) {
                manData.addnq((int)((double)sw * tycSkill.getSkillhurt()), Originator);
            }
            if (events.getOriginator() != null) {
                Accepterlist.add(Originator);
            }
            events.setOriginator(null);
            if (Accepterlist.size() != 0) {
                events.setAccepterlist(Accepterlist);
                battlefield.NewEvents.add(events);
            }
            MixDeal.passiveSpellAction(manData, skill.getSkilltype(), Accepterlist, battlefield, datas, (double)(maxSize / datas.size()));
            if (manData.getStates() != 0) {
                MixDeal.DeathSkill(manData, Originator, battlefield);
            }
            return;
        }
    }
    
    public static void hurt(ManData manData, ManData data, int i, double skillhitrate, long hurt, FightingSkill skill, List<FightingSkill> skills, FightingState Accepter, List<FightingState> Accepterlist, Battlefield battlefield, Boolean b) {
        boolean isFZ = false;
        boolean isZS = false;
        ChangeFighting acec = new ChangeFighting();
        acec.setChangehp((int)(-hurt));
        if ("沧波".equals(skill.getSkilltype())) {
            FightingSkill TY_L_10142 = manData.getSkillType(TypeUtil.TY_L_10142);
            if (TY_L_10142 != null) {
                acec.setChangemp((int)((double)hurt * 0.1));
            }
        }
        FightingSkill TY_L_10143 = manData.getSkillType(TypeUtil.TY_L_10141);
        if (i == 0 && ("沧波".equals(skill.getSkilltype()) || "扶摇".equals(skill.getSkilltype()))) {
            acec.setChangevlaue(skillhitrate);
            acec.setChangetype(skill.getSkilltype());
            acec.setChangesum((TY_L_10143 != null && Battlefield.isV(TY_L_10143.getSkillhurt())) ? (skill.getSkillcontinued() + 1) : skill.getSkillcontinued());
        }
        String typefs = TypeUtil.PTGJ;
        skills = manData.getSkills();
        if (skills != null) {
            for (int j = skills.size() - 1; j >= 0; --j) {
                FightingSkill tSkill = (FightingSkill)skills.get(j);
                int id = tSkill.getSkillid();
                if (id == 9503) {
                    if (data.getType() == 1) {
                        hurt = (long)((double)hurt * (1.0 + tSkill.getSkillhurt() / 100.0));
                        acec.setChangehp((int)(-hurt));
                    }
                }
                else if (id == 9504) {
                    isFZ = Battlefield.isV(tSkill.getSkillhurt());
                }
                else if (id == 9702 || id == 9802) {
                    if (Battlefield.isV(tSkill.getSkillhurt())) {
                        acec.setChangesum(skill.getSkillcontinued() + 1);
                    }
                }
                else if (id == 9704 || id == 9804) {
                    if (Battlefield.isV(tSkill.getSkillhurt())) {
                        acec.setSkill(tSkill);
                    }
                }
                else if (id == 9706 || id == 9806) {
                    AddState addState = data.xzstate(skill.getSkilltype());
                    if (addState != null) {
                        hurt = (long)((double)hurt * (1.0 + tSkill.getSkillhurt() / 100.0));
                        acec.setChangehp((int)(-hurt));
                    }
                }
                else if (id == 9707) {
                    if (data.getType() == 1 && Battlefield.isV(10.0)) {
                        hurt = (long)((double)hurt * (1.0 + tSkill.getSkillhurt() / 100.0));
                        acec.setChangehp((int)(-hurt));
                    }
                }
                else if (id == 9709) {
                    if (data.getvalue(0) <= 0.2) {
                        hurt = (long)((double)hurt * (1.0 + tSkill.getSkillhurt() / 100.0));
                        acec.setChangehp((int)(-hurt));
                    }
                }
                else if (id == 9710) {
                    acec.setChangevlaue2(tSkill.getSkillhurt());
                }
                else if (id == 9711) {
                    double xs = 1.0 - tSkill.getSkillhurt() / (double)data.getZHP_Z();
                    if (xs < 0.0 || xs > 1.0) {
                        xs = 0.0;
                    }
                    acec.setChangevlaue(xs);
                }
                else if (id == 9807 || id == 9809 || id == 9811 || id == 9812 || id == 9712) {
                    acec.setSkill(tSkill);
                }
                else if (id == 9712 || id == 10132) {
                    data.addAddState(tSkill.getSkilltype(), tSkill.getSkillhurt(), 0.0, 2);
                }
                else if (id == 10118) {
                    if ((double)data.getHp_z() * 0.1 < (double)hurt && Battlefield.isV(tSkill.getSkillhurt())) {
                        acec.setSkill(tSkill);
                    }
                }
                else if (id == 10133) {
                    data.getQuality().setRolefmzl(data.getQuality().getRolefmzl() - tSkill.getSkillhurt());
                    manData.getSkills().remove(tSkill);
                }
                else if (id == 10134) {
                    if (manData.xzstate(TypeUtil.L_LL) != null && Battlefield.isV(tSkill.getSkillhurt())) {
                        AddState addState = data.xzstate("免疫物理");
                        if (addState != null) {
                            data.getAddStates().remove(addState);
                        }
                    }
                }
                else if (id == 10130) {
                    FightingSkill skillSM = manData.skillId(1100);
                    FightingSkill TY_L_10144 = manData.getSkillType(TypeUtil.TY_L_10130);
                    if (TY_L_10144 != null) {
                        skillSM.setSkillhitrate(skillSM.getSkillhitrate() + TY_L_10144.getSkillhurt());
                        if (Battlefield.isV(TY_L_10144.getSkillgain())) {
                            AddState addState2 = data.xzstate("免疫物理");
                            if (addState2 != null) {
                                data.getAddStates().remove(addState2);
                            }
                        }
                        manData.getSkills().remove(TY_L_10144);
                    }
                }
                else if (id == 10139) {
                    FightingSkill skillSM = manData.skillId(1090);
                    FightingSkill TY_L_10145 = manData.getSkillType(TypeUtil.TY_L_10139);
                    if (TY_L_10145 != null) {
                        skillSM.setSkillhitrate(skillSM.getSkillhitrate() + TY_L_10145.getSkillhurt());
                        if (Battlefield.isV(TY_L_10145.getSkillgain())) {
                            AddState addState2 = data.xzstate("免疫物理");
                            if (addState2 != null) {
                                data.getAddStates().remove(addState2);
                            }
                        }
                        manData.getSkills().remove(TY_L_10145);
                    }
                }
                else if (id == 10122) {
                    data.addAddState(TypeUtil.TY_L_10122, tSkill.getSkillhurt(), 0.0, 2);
                }
                else if (id == 10126 || id == 10135) {
                    data.tzcf = tSkill.getSkillhurt();
                }
                else if (id == 10125) {
                    if (Battlefield.isV(tSkill.getSkillhurt()) && manData.xzstate(TypeUtil.L_LL) != null) {
                        AddState addState = data.xzstate("免疫物理");
                        if (addState != null) {
                            data.getAddStates().remove(addState);
                        }
                    }
                }
                else if (id == 9810) {
                    double isv = 0.0;
                    FightingSkill TY_L_10146 = manData.getSkillType(TypeUtil.TY_L_10140);
                    if (TY_L_10146 != null) {
                        isv = TY_L_10146.getSkillhurt();
                    }
                    if (Battlefield.isV(20.0 + isv)) {
                        data.addAddState("强化沧波", tSkill.getSkillhurt(), 0.0, 3);
                    }
                }
                else if (id == 22033) {
                    double gailv = manData.getFmJv(1, new String[] { "浩气凌云" });
                    AddState addState2 = manData.xzstate("行气如虹");
                    if (addState2 != null) {
                        gailv += manData.getFmJv(1, new String[] { "行气如虹" });
                    }
                    if (Battlefield.isV(gailv * 3.0)) {
                        if (Battlefield.isV(gailv)) {
                            hurt = (long)((double)hurt * (1.0 + manData.getFmJv(2, new String[] { "浩气凌云" }) / 100.0));
                        }
                        acec.setChangehp((int)(-hurt));
                        isZS = true;
                    }
                }
            }
        }
        String type = ((boolean)b) ? "霹雳end" : "霹雳";
        if (isZS) {
            type += "至圣";
        }
        FightingPackage.ChangeProcess(acec, isFZ ? null : manData, data, Accepter, type, Accepterlist, battlefield);
    }
}
