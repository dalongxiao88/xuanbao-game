package come.tool.FightingSpellAction;

import come.tool.FightingDataAction.Spell;
import java.util.Arrays;
import com.gl.util.FaMenUtil;
import come.tool.FightingData.Sepcies_MixDeal;
import java.util.Iterator;
import java.util.List;
import cn.hutool.core.util.RandomUtil;
import come.tool.FightingData.Calculation;
import come.tool.FightingData.AddState;
import java.util.Random;
import org.come.tool.CustomFunction;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.SummonType;
import come.tool.FightingData.PK_MixDeal;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.MixDeal;
import java.util.ArrayList;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public class HurtAction implements SpellAction
{
    private boolean iszxcgone;
    
    public HurtAction() {
        this.iszxcgone = false;
    }
    
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        String skilltype = skill.getSkilltype();
        FightingSkill tz_yg = null;
        FightingSkill tz_cb = null;
        FightingSkill tz_ph = null;
        FightingSkill tz_xy = null;
        FightingSkill ksys = null;
        FightingSkill gqlx = null;
        FightingSkill bcjf = null;
        FightingSkill yqcs = null;
        FightingSkill yqcs2 = null;
        FightingSkill bb_e_ttym = null;
        FightingSkill bb_e_flbd = null;
        FightingSkill sglx = null;
        Boolean bcf = Boolean.valueOf(false);
        for (int i = manData.getSkills().size() - 1; i >= 0; --i) {
            FightingSkill skill2 = (FightingSkill)manData.getSkills().get(i);
            String lei = skill2.getSkilltype();
            if (lei.equals(TypeUtil.TZ_YGZG)) {
                tz_yg = skill2;
            }
            else if (lei.equals(TypeUtil.TZ_CBNX)) {
                tz_cb = skill2;
            }
            else if (lei.equals(TypeUtil.TZ_PHQY)) {
                tz_ph = skill2;
            }
            else if (lei.equals(TypeUtil.TZ_XYXG)) {
                tz_xy = skill2;
            }
            else if (lei.equals(TypeUtil.BB_KSYS)) {
                ksys = skill2;
            }
            else if (!lei.equals(TypeUtil.BB_YQCS)) {
                if (lei.equals("1888")) {
                    yqcs2 = skill2;
                }
                else if (lei.equals(TypeUtil.BB_E_TTYM)) {
                    bb_e_ttym = skill2;
                }
                else if (lei.equals(TypeUtil.BB_E_FLBD)) {
                    bb_e_flbd = skill2;
                }
                else if (lei.equals(TypeUtil.BB_GQLX)) {
                    gqlx = skill2;
                }
                else if (lei.equals(TypeUtil.BB_百草竞发)) {
                    bcjf = skill2;
                }
                else if (lei.equals(TypeUtil.BB_SGLX)) {
                    sglx = skill2;
                }
            }
        }
        int bb_size = 0;
        if (bb_e_ttym != null) {
            if (Battlefield.isV(bb_e_ttym.getSkillhurt())) {
                bb_size += 2;
            }
            else if (Battlefield.isV(bb_e_ttym.getSkillgain())) {
                ++bb_size;
            }
        }
        else if (bb_e_flbd != null) {
            if (Battlefield.isV(bb_e_flbd.getSkillhurt())) {
                bb_size += 2;
            }
            else if (Battlefield.isV(bb_e_flbd.getSkillgain())) {
                ++bb_size;
            }
        }
        List<FightingState> Accepterlist = new ArrayList<>();
        int addNum = 0;
        if (manData.jlgh != 0 && (skill.getSkillid() == 1063 || skill.getSkillid() == 1065)) {
            addNum = manData.jlgh;
        }
        if (bb_size > 0) {
            skill.setSkillsum(skill.getSkillsum() + bb_size);
        }
        if (addNum > 0) {
            skill.setSkillsum(skill.getSkillsum() + addNum);
        }
        List<ManData> datas = MixDeal.getjieshou(events, skill, manData, Accepterlist, battlefield);
        if (bb_size > 0) {
            skill.setSkillsum(skill.getSkillsum() - bb_size);
        }
        if (addNum > 0) {
            skill.setSkillsum(skill.getSkillsum() - addNum);
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
            return;
        }
        else {
            if (manData.ymjr) {
                FightingState ace = events.getOriginator();
                ace.setCamp(manData.getCamp());
                ace.setMan(manData.getMan());
                ace.setText("看我的一鸣惊人");
                Accepterlist.add(ace);
            }
            double dds = 1.0;
            boolean jl = false;
            double jc = manData.getSpellJC();
            double wg = manData.getWGTB();
            if (bb_size != 0 && manData.getShanghai() >= 450.0) {
                if (bb_e_ttym != null) {
                    wg += (double)((Battlefield.random.nextInt(1000) + 400) * 40);
                }
                else if (bb_e_flbd != null) {
                    wg += manData.getShanghai() * 40.0;
                }
            }
            double kbl = 0.0;
            FightingSkill TY_RH_NDQB_SS = manData.getSkillType(TypeUtil.TY_X_9939);
            if (TY_RH_NDQB_SS != null && !manData.methodExecuted) {
                manData.getQuality().setRoleffzl(manData.getQuality().getRoleffzl() + TY_RH_NDQB_SS.getSkillhurt());
            }
            if (skill.getSkillid() == 1049) {
                FightingSkill skill3 = manData.skillId(9261);
                if (skill3 != null) {
                    jc *= 1.0 + skill3.getSkillhurt() / 100.0;
                }
                skill3 = manData.skillId(9264);
                if (skill3 != null) {
                    kbl += skill3.getSkillhurt();
                }
            }
            List<FightingSkill> skills = ControlAction.getSkills(manData, skill, battlefield.BattleType);
            boolean isTZ = tz_yg != null || tz_cb != null || tz_ph != null || tz_xy != null;
            int sum = 1;
            if (Battlefield.isV(manData.getQuality().getRolexfljl())) {
                sum = (int)((double)sum + manData.getQuality().getRolexfljs());
                dds = 0.85;
            }
            if (skill.getSkillid() >= 1040 && skill.getSkillid() <= 1065 && manData.executeAbbs(battlefield)) {
                FightingState ace2 = new FightingState();
                ace2.setCamp(manData.getCamp());
                ace2.setMan(manData.getMan());
                ace2.setText("看我的哀兵必败");
                Accepterlist.add(ace2);
                ++sum;
            }
            int totalHurt = 0;
            FightingSkill skill4 = null;
            if (PK_MixDeal.isPK(battlefield.BattleType)) {
                if (skill.getSkillid() == 1050) {
                    skill4 = manData.skillId(9271);
                    if (skill4 != null && !Battlefield.isV(skill4.getSkillhurt())) {
                        skill4 = null;
                    }
                }
                else if (skill.getSkillid() == 1055) {
                    skill4 = manData.skillId(9288);
                    if (skill4 != null && !Battlefield.isV(skill4.getSkillhurt())) {
                        skill4 = null;
                    }
                    AddState addState = manData.xzstate(TypeUtil.TY_S_LPYJ);
                    if (addState != null) {
                        skills = ControlAction.addSkill(manData.skillId(9286), skills);
                    }
                }
                else if (skill.getSkillid() == 1045) {
                    skill4 = manData.skillId(9308);
                    if (skill4 != null && !Battlefield.isV(skill4.getSkillhurt())) {
                        skill4 = null;
                    }
                    AddState addState = manData.xzstate(TypeUtil.TY_F_CFWL);
                    if (addState != null) {
                        FightingSkill skill5 = manData.skillId(9307);
                        skills = ControlAction.addSkill(skill5, skills);
                        if (skill5 != null) {
                            double ts = 0.0;
                            FightingSkill XS3WS = manData.getSkillType(TypeUtil.TY_X_9958);
                            if (XS3WS != null) {
                                ts = XS3WS.getSkillhurt() / 100.0;
                            }
                            jc *= (1.0 + ts) * skill5.getSkillhurt() / (double)((datas.size() < 5) ? 5 : datas.size()) / 100.0;
                            skill5.setSkillgain(1.0);
                            if (Battlefield.isV((skill5.getSkillhurt() - 350.0) / 5.0 * 2.0)) {
                                skill5.setSkillgain(2.0);
                            }
                        }
                    }
                }
                else if (skill.getSkillid() == 1060) {
                    skill4 = manData.skillId(9328);
                    if (skill4 != null && !Battlefield.isV(skill4.getSkillhurt())) {
                        skill4 = null;
                    }
                    AddState addState = manData.xzstate(TypeUtil.TY_X_10016);
                    if (addState != null) {
                        FightingSkill skill5 = manData.skillId(10016);
                        skills = ControlAction.addSkill(skill5, skills);
                        if (skill5 != null) {
                            double ts = 0.0;
                            FightingSkill XS3WS = manData.getSkillType(TypeUtil.TY_X_10019);
                            if (XS3WS != null) {
                                ts = XS3WS.getSkillhurt() / 100.0;
                            }
                            jc *= (1.0 + ts) * skill5.getSkillhurt() / (double)((datas.size() < 5) ? 5 : datas.size()) / 100.0;
                        }
                    }
                }
                else if (skill.getSkillid() >= 1061 && skill.getSkillid() <= 1065) {
                    skill4 = manData.skillId(9364);
                }
            }
            else {
                if (skill.getSkillid() == 9372) {
                    SummonType.xianzhi(manData, skill);
                }
                if (skill.getSkillid() == 1065) {
                    AddState addState = manData.xzstate(TypeUtil.TY_GH_XXYX);
                    if (addState != null) {
                        jc = 1.0 - (addState.getStateEffect() * 0.05 + ((addState.getStateEffect() <= 2.0) ? (addState.getStateEffect() * 0.05) : 0.1));
                    }
                    FightingSkill XS3WS = manData.getSkillType("10061");
                    if (XS3WS != null) {
                        ((ManData)datas.get(0)).getQuality().setRolekgh(((ManData)datas.get(0)).getQuality().getRolekgh() - XS3WS.getSkillhurt() / 100.0);
                        manData.getSkills().remove(XS3WS);
                    }
                }
                else if (skill.getSkillid() >= 1061 && skill.getSkillid() <= 1065) {
                    FightingSkill XS3WS = manData.getSkillType("10059");
                    if (XS3WS != null) {
                        manData.getQuality().setRoleffzl(manData.getQuality().getRoleffzl() + XS3WS.getSkillhurt() / 100.0);
                        manData.getSkills().remove(XS3WS);
                    }
                }
            }
            boolean hscs = false;
            double hscszv = 0.0;
            this.Passive(manData, skill, datas);
            if (skill.getSkillid() == 1055) {
                AddState addState = manData.xzstate(TypeUtil.TY_S_HSCS);
                if (addState != null) {
                    sum = 2;
                    dds = 1.0;
                    jc *= addState.getStateEffect() / 100.0;
                    hscszv = addState.getStateEffect() / 100.0;
                    jl = true;
                    hscs = true;
                }
            }
            if (datas.size() > 1) {
                ManData data = (ManData)datas.remove(0);
                datas.add(data);
            }
            boolean isyqcs = false;
            boolean isyqcs2 = false;
            double yshurt = 0.0;
            double shbs = 1.0;
            if (sum > 1 && skill.getSkillid() >= 1040 && skill.getSkillid() <= 1065) {
                shbs += (double)manData.executeQyzs() / 100.0;
            }
            int j = 0;
            while (j < sum) {
                if (manData.getStates() != 0) {
                    break;
                }
                else {
                    if (j != 0) {
                        Accepterlist = new ArrayList<>();
                    }
                    FightingState Originator2 = new FightingState();
                    Originator2.setEndState(skill.getSkillname());
                    if (j == 0) {
                        if (bb_size != 0) {
                            Originator2.setText((bb_e_ttym != null) ? "听天由命#2" : "法力波动#2");
                        }
                        if (!manData.isLicense(skill)) {
                            break;
                        }
                        else if (manData.daijia(skill, Originator2, battlefield)) {
                            return;
                        }
                    }
                    else {
                        Originator2.setCamp(manData.getCamp());
                        Originator2.setMan(manData.getMan());
                    }
                    int maxHurt = 0;
                    int mzSize = 0;
                    String skin = null;
                    isyqcs = (yqcs != null && Battlefield.isV(yqcs.getSkillhitrate()));
                    isyqcs2 = (yqcs2 != null && Battlefield.isV(yqcs2.getSkillhitrate()));
                    FightingState Accepter = new FightingState();
                    for (int k = datas.size() - 1; k >= 0; --k) {
                        ManData data2 = (ManData)datas.get(k);
                        if (data2.getStates() != 0) {
                            data2 = MixDeal.getjieshou(skill, manData, datas, battlefield);
                            if (data2 != null) {
                                datas.set(k, data2);
                                FightingSkill XS3WS = manData.getSkillType("10062");
                                if (XS3WS != null) {
                                    FightingState Accepter2 = new FightingState();
                                    ChangeFighting fighting = new ChangeFighting();
                                    fighting.setChangemp((int)((double)manData.getMp_z() * XS3WS.getSkillhurt() / 100.0));
                                    Accepter2.setSkillskin(skin);
                                    FightingPackage.ChangeProcess(fighting, manData, data2, Accepter2, skill.getSkilltype(), Accepterlist, battlefield);
                                }
                                AddState addState = manData.xzstate(TypeUtil.TY_GH_XXYX);
                                if (addState != null) {
                                    XS3WS = manData.getSkillType("10068");
                                    if (XS3WS != null) {
                                        FightingState fightingState = new FightingState();
                                        fightingState.setCamp(manData.getCamp());
                                        fightingState.setMan(manData.getMan());
                                        fightingState.setStartState("代价");
                                        manData.addnq((int)XS3WS.getSkillhurt(), fightingState);
                                    }
                                }
                            }
                            else {
                                datas.remove(k);
                                continue;
                            }
                        }
                        Accepter = MixDeal.DSMY(manData, data2, skill.getSkillid(), battlefield);
                        if (Accepter == null) {
                            ++mzSize;
                            data2.addBear(skilltype);
                            Accepter = new FightingState();
                            if (isTZ) {
                                addTZState(data2, tz_yg, tz_cb, tz_ph, tz_xy, Accepter);
                                isTZ = false;
                            }
                            double hurt = skill.getSkillhurt();
                            if (skill.getSkillid() == 1065) {
                                AddState addState = manData.xzstate("10065");
                                if (addState != null) {
                                    double maxhurt = addState.getStateEffect() * (double)manData.lxzt;
                                    if (maxhurt >= addState.getStateEffect2()) {
                                        hurt = addState.getStateEffect2();
                                    }
                                    else {
                                        hurt += maxhurt;
                                    }
                                    yshurt += addState.getStateEffect3();
                                    FightingSkill TY_G_10066 = manData.getSkillType("10066");
                                    if (TY_G_10066 != null) {
                                        yshurt = TY_G_10066.getSkillhurt();
                                    }
                                    ChangeFighting change = new ChangeFighting();
                                    change.setChangemp(-(int)yshurt);
                                    FightingState org3 = new FightingState();
                                    org3.setCamp(manData.getCamp());
                                    org3.setMan(manData.getMan());
                                    FightingPackage.ChangeProcess(change, data2, manData, org3, TypeUtil.JN, Accepterlist, battlefield);
                                }
                            }
                            if (isyqcs && k == datas.size() - 1) {
                                skin = yqcs.getSkilltype();
                                if (manData.getMp_z() > 100000) {
                                    hurt += (CustomFunction.XS((long)manData.getMp_z(), 4200.0) - 15000.0) * (double)(PK_MixDeal.isPK(battlefield.BattleType) ? 1 : 2);
                                }
                            }
                            for (int l = 1; l < sum; ++l) {
                                hurt *= dds;
                            }
                            for (int l = 1; l < sum; ++l) {
                                FightingSkill TY_X_9967 = manData.getSkillType(TypeUtil.TY_X_9967);
                                if (TY_X_9967 != null && l == 2 && jl) {
                                    hurt *= 1.0 + TY_X_9967.getSkillhurt() / 100.0;
                                }
                                hurt *= dds;
                            }
                            if (k == datas.size() - 1 && j == 0) {
                                manData.executeSdef(battlefield);
                                manData.executeQfyx(data2, Accepterlist);
                            }
                            if (sum > 1) {
                                hurt *= shbs;
                            }
                            if (j > 0 && hscs) {
                                double decreasedHurt = hurt * (1.0 - hscszv);
                                hurt -= decreasedHurt;
                            }
                            int fashang = 0;
                            fashang = hurt(j, (int)hurt, jc, wg, kbl, skills, Accepterlist, Accepter, manData, data2, skill, battlefield, j);
                            totalHurt += fashang;
                            if (maxHurt < fashang) {
                                maxHurt = fashang;
                            }
                            if (k == datas.size() - 1) {
                                manData.executeQzzq(data2, Accepter, Accepterlist, battlefield);
                                manData.addFaDun((long)fashang, Originator2);
                            }
                            if (data2.getStates() == 0 && skill.getSkillid() > 1040 && skill.getSkillid() < 1065 && skill.getSkillid() % 5 == 4) {
                                if (sum < 2) {
                                    int zxcg = manData.executeZxcg(1);
                                    if (zxcg > 0 && Battlefield.isV((double)zxcg + manData.getShanghai() / 66.6)) {
                                        FightingState say = new FightingState();
                                        say.setCamp(manData.getCamp());
                                        say.setMan(manData.getMan());
                                        say.setText("看我的锥心刺骨");
                                        Accepterlist.add(0, say);
                                        int zssh = manData.executeZxcg(2) * manData.getMp_z() / 100;
                                        ChangeFighting changeFighting2 = new ChangeFighting();
                                        changeFighting2.setChangehp(-zssh);
                                        FightingState fState2 = new FightingState();
                                        FightingPackage.ChangeProcess(changeFighting2, null, data2, fState2, "至圣", Accepterlist, battlefield);
                                    }
                                }
                                else if (!this.iszxcgone) {
                                    this.iszxcgone = true;
                                    int zxcg = manData.executeZxcg(1);
                                    if (zxcg > 0 && Battlefield.isV((double)zxcg + manData.getShanghai() / 66.6)) {
                                        FightingState say = new FightingState();
                                        say.setCamp(manData.getCamp());
                                        say.setMan(manData.getMan());
                                        say.setText("看我的锥心刺骨");
                                        Accepterlist.add(0, say);
                                        int zssh = manData.executeZxcg(2) * manData.getMp_z() / 100;
                                        ChangeFighting changeFighting2 = new ChangeFighting();
                                        changeFighting2.setChangehp(-zssh);
                                        FightingState fState2 = new FightingState();
                                        FightingPackage.ChangeProcess(changeFighting2, null, data2, fState2, "至圣", Accepterlist, battlefield);
                                    }
                                }
                            }
                            if (bcjf != null) {
                                AddState zt1 = data2.xzstate(bcjf.getSkilltype());
                                if (Battlefield.isV(bcjf.getSkillgain()) && !(boolean)bcf) {
                                    bcf = Boolean.valueOf(true);
                                    List<ManData> datas2 = MixDeal.get(false, null, 0, manData.getCamp(), 0, 0, 0, 0, 2 + new Random().nextInt(6), -1, battlefield, 1);
                                    for (ManData item : datas2) {
                                        item.addBear(bcjf.getSkilltype());
                                        AddState addState = new AddState();
                                        addState.setStatename(bcjf.getSkilltype());
                                        addState.setStateEffect(bcjf.getSkillhurt());
                                        addState.setStateEffect2(bcjf.getSkillgain());
                                        addState.setSurplus(bcjf.getSkillcontinued());
                                        FightingState fState3 = new FightingState();
                                        fState3.setCamp(item.getCamp());
                                        fState3.setMan(item.getMan());
                                        fState3.setStartState("代价");
                                        fState3.setEndState_1("1241");
                                        Accepterlist.add(fState3);
                                        item.getAddStates().add(addState);
                                    }
                                }
                                if (Battlefield.isV(bcjf.getSkillgain() / 2.0)) {
                                    for (ManData fightingdatum : battlefield.fightingdata) {
                                        if (fightingdatum.getCamp() != manData.getCamp() && fightingdatum.xzstate(bcjf.getSkilltype()) != null) {
                                            ChangeFighting changeFighting2 = new ChangeFighting();
                                            FightingState fState3 = new FightingState();
                                            fState3.setCamp(manData.getCamp());
                                            fState3.setMan(manData.getMan());
                                            fState3.setStartState("被攻击");
                                            fState3.setSkin("900096");
                                            Accepterlist.add(0, fState3);
                                            changeFighting2.setChangehp(-fashang);
                                            FightingState fState4 = new FightingState();
                                            fState4.setEndState_2("1241");
                                            fState4.setSkillskin("1044");
                                            FightingPackage.ChangeProcess(changeFighting2, null, fightingdatum, fState4, "额外", Accepterlist, battlefield);
                                            fightingdatum.RemoveAbnormal(new String[] { bcjf.getSkilltype() });
                                        }
                                    }
                                }
                            }
                            if (j == sum - 1 && gqlx != null) {
                                AddState zt2 = data2.xzstate(gqlx.getSkilltype());
                                if (zt2 == null) {
                                    data2.addBear(gqlx.getSkilltype());
                                    AddState addState = new AddState();
                                    addState.setStatename(gqlx.getSkilltype());
                                    addState.setStateEffect(gqlx.getSkillhurt());
                                    addState.setStateEffect2(gqlx.getSkillgain());
                                    addState.setSurplus(gqlx.getSkillcontinued());
                                    Accepter.setEndState_1(addState.getStatename());
                                    data2.getAddStates().add(addState);
                                }
                                else if (data2.getStates() == 0) {
                                    FightingState Accepter3 = new FightingState();
                                    double gqlxbj = (double)manData.getMp_z() * 0.12 * (double)manData.getLvl() / 100.0;
                                    hurt(0, jc, gqlxbj, kbl, skills, Accepterlist, Accepter3, manData, data2, skill, battlefield);
                                }
                            }
                        }
                        else {
                            Accepterlist.add(Accepter);
                        }
                        Accepter.setSkillskin((skin != null) ? skin : (skill.getSkillid() + ""));
                        if (j == 0) {
                            if (ksys != null) {
                                double gl = ksys.getSkillhitrate();
                                if (skill.getSkilllvl() == 2) {
                                    gl *= 1.2;
                                }
                                else if (skill.getSkilllvl() == 3) {
                                    gl *= 1.45;
                                }
                                else if (skill.getSkilllvl() == 4) {
                                    gl *= 2.0;
                                }
                                if (gl > (double)Battlefield.random.nextInt(100)) {
                                    List<ManData> ksyss = battlefield.getZW(data2);
                                    ChangeFighting fighting2 = new ChangeFighting();
                                    int hurt2 = (int)(ksys.getSkillhurt() / 100.0 * (double)manData.getMp_z() / (double)ksyss.size());
                                    for (int m = ksyss.size() - 1; m >= 0; --m) {
                                        ManData data3 = (ManData)ksyss.get(m);
                                        FightingState Accepter4 = new FightingState();
                                        fighting2.setChangehp(-hurt2);
                                        int y = data3.getStates();
                                        data3.ChangeData(fighting2, Accepter4);
                                        Accepterlist.add(Accepter4);
                                        if (data3.getStates() == 1 && y != data3.getStates()) {
                                            MixDeal.DeathSkill(data3, Accepter4, battlefield);
                                        }
                                    }
                                }
                            }
                            else if (isyqcs && k == datas.size() - 1) {
                                int size = 1;
                                for (int k2 = 0; k2 < 4; ++k2) {
                                    if (Battlefield.isV(yqcs.getSkillhitrate())) {
                                        ++size;
                                    }
                                }
                                List<ManData> datas3 = MixDeal.get(false, data2, 0, manData.getCamp(), 0, 0, 0, 0, size, -1, battlefield, 0);
                                for (int l = 0; l < datas3.size(); ++l) {
                                    int hurt3 = (int)skill.getSkillhurt();
                                    if (manData.getMp_z() > 100000) {
                                        hurt3 = (int)((double)hurt3 + (CustomFunction.XS((long)manData.getMp_z(), 4200.0) - 15000.0) * (double)(PK_MixDeal.isPK(battlefield.BattleType) ? 1 : 2));
                                    }
                                    for (int l2 = 0; l2 <= l; ++l2) {
                                        hurt3 = (int)((double)hurt3 * 0.8);
                                    }
                                    ManData data4 = (ManData)datas3.get(l);
                                    FightingState Accepter5 = new FightingState();
                                    hurt3 = Calculation.getCalculation().SMHurt(manData, data4, (double)hurt3, 0.0, skill.getSkilltype(), (manData.getCamp() == 1) ? battlefield.MyDeath : battlefield.NoDeath);
                                    ChangeFighting fighting3 = new ChangeFighting();
                                    fighting3.setChangehp(-hurt3);
                                    fighting3.setChangemp((int)((double)(-hurt3) * 0.2));
                                    Accepter5.setSkillskin(skin);
                                    FightingPackage.ChangeProcess(fighting3, manData, data4, Accepter5, skill.getSkilltype(), Accepterlist, battlefield);
                                }
                            }
                        }
                        FightingSkill skill6 = manData.skillId(Integer.parseInt(TypeUtil.BB_SGLX));
                        if (skill6 != null && (double)Battlefield.random.nextInt(100) <= skill6.getSkillgain()) {
                            FightingState say2 = new FightingState();
                            say2.setCamp(manData.getCamp());
                            say2.setMan(manData.getMan());
                            say2.setText("咩~");
                            Accepterlist.add(0, say2);
                            int i2 = RandomUtil.randomInt(1, 5);
                            this.sylg(manData, skill, battlefield, Accepterlist, maxHurt, data2, skill6, i2);
                        }
                        if (data2.getStates() != 0) {
                            manData.addAttackDie();
                            if (PK_MixDeal.isPK(battlefield.BattleType)) {
                                data2.dfxy(skills, new String[] { TypeUtil.TY_G_10084, TypeUtil.TY_G_10089, TypeUtil.TY_G_10104 });
                            }
                        }
                    }
                    if (manData.fskbbj && maxHurt > 0 && Battlefield.isV((double)manData.executeDksj(2))) {
                        ManData friend = battlefield.getZW1((ManData)datas.get(datas.size() - 1));
                        if (friend != null) {
                            Accepter = new FightingState();
                            Accepter.setSkillskin(skin);
                            ChangeFighting changeFighting3 = new ChangeFighting();
                            changeFighting3.setChangehp(-maxHurt);
                            FightingState say3 = new FightingState();
                            say3.setCamp(manData.getCamp());
                            say3.setMan(manData.getMan());
                            say3.setText("看我的大开杀戒");
                            Accepterlist.add(0, say3);
                            FightingPackage.ChangeProcess(changeFighting3, manData, friend, Accepter, skill.getSkilltype(), Accepterlist, battlefield);
                        }
                    }
                    Originator2.setStartState("法术攻击");
                    Originator2.setSkillsy(skill.getSkillname());
                    Accepterlist.add(Originator2);
                    FightingEvents Events = new FightingEvents();
                    Events.setAccepterlist(Accepterlist);
                    battlefield.NewEvents.add(Events);
                    if (mzSize > 0) {
                        MixDeal.passiveSpellAction(manData, skill.getSkilltype(), Accepterlist, battlefield, datas, (double)(mzSize / datas.size()));
                    }
                    if (Accepterlist.size() == 1) {
                        break;
                    }
                    else {
                        manData.fmGJ(Originator2);
                        ++j;
                    }
                }
            }
            if (manData.ymjr) {
                manData.ymjr = false;
                manData.executePhbd((FightingEvents)battlefield.NewEvents.get(battlefield.NewEvents.size() - 1));
            }
            if (skill4 != null && totalHurt > 0 && manData.getStates() == 0) {
                if (skill4.getSkillid() == 9364) {
                    ChangeFighting fighting4 = new ChangeFighting();
                    FightingState Accepter6 = new FightingState();
                    Accepter6.setStartState("药");
                    totalHurt = (int)((double)totalHurt * (skill4.getSkillhurt() / 100.0));
                    fighting4.setChangemp(totalHurt);
                    manData.ChangeData(fighting4, Accepter6);
                    Accepterlist.add(0, Accepter6);
                }
                else {
                    totalHurt = (int)((double)totalHurt * 0.2);
                    if (totalHurt > manData.getHp_z()) {
                        totalHurt = manData.getHp_z();
                    }
                    ChangeFighting fighting4 = new ChangeFighting();
                    FightingState Accepter6 = new FightingState();
                    Accepter6.setStartState("代价");
                    fighting4.setChangetype(skill4.getSkilltype());
                    fighting4.setChangevlaue((double)totalHurt);
                    if (skill4.getSkillid() == 9271) {
                        fighting4.setChangevlaue2((skill.getSkillhurt() - 15.0) / 2.0 * 2000.0);
                    }
                    else if (skill4.getSkillid() == 9328) {
                        fighting4.setChangevlaue2((skill.getSkillhurt() - 15.0) / 2.0 * 0.5);
                    }
                    else {
                        fighting4.setChangevlaue2((skill.getSkillhurt() - 15.0) / 2.0 * 5.0);
                    }
                    manData.ChangeData(fighting4, Accepter6);
                    Accepterlist.add(Accepter6);
                }
            }
            FightingSkill skill_百草竞发 = manData.getSkillType(TypeUtil.BB_百草竞发);
            if (skill_百草竞发 != null) {
                RandomUtil.randomInt(11);
            }
            this.iszxcgone = false;
            return;
        }
    }
    
    private void Passive(ManData manData, FightingSkill skill, List<ManData> datas) {
        String[] wskx;
        for (String wskxs : wskx = new String[] { TypeUtil.TY_X_9962, TypeUtil.TY_X_9963, TypeUtil.TY_X_9942, TypeUtil.TY_X_9944, TypeUtil.TY_X_9953, TypeUtil.TY_X_9954, TypeUtil.TY_X_10010, TypeUtil.TY_X_10012, TypeUtil.TY_X_9945 }) {
            FightingSkill TY_RH_NDQB_SS = manData.getSkillType(wskxs);
            if (TY_RH_NDQB_SS != null && !manData.methodExecuted) {
                switch (skill.getSkillid()) {
                    case 1045: {
                        ((ManData)datas.get(0)).getQuality().setRolekff(((ManData)datas.get(0)).getQuality().getRolekff() - TY_RH_NDQB_SS.getSkillhurt());
                        break;
                    }
                    case 1050: {
                        ((ManData)datas.get(0)).getQuality().setRoleklf(((ManData)datas.get(0)).getQuality().getRoleklf() - TY_RH_NDQB_SS.getSkillhurt());
                        break;
                    }
                    case 1055: {
                        ((ManData)datas.get(0)).getQuality().setRoleksf(((ManData)datas.get(0)).getQuality().getRoleksf() - TY_RH_NDQB_SS.getSkillhurt());
                        break;
                    }
                    case 1060: {
                        ((ManData)datas.get(0)).getQuality().setRolekhf(((ManData)datas.get(0)).getQuality().getRolekhf() - TY_RH_NDQB_SS.getSkillhurt());
                        break;
                    }
                }
            }
        }
    }
    
    private void sylg(ManData manData, FightingSkill skill, Battlefield battlefield, List<FightingState> accepterlist, int maxHurt, ManData data, FightingSkill skill3, int times) {
        if (times <= 0) {
            return;
        }
        ManData friend = battlefield.getZW1(data);
        if (friend == null) {
            return;
        }
        FightingState Accepter = new FightingState();
        Accepter.setSkillskin(skill3.getSkilltype());
        Accepter.setSglxTag(Integer.valueOf(data.getMan()));
        ChangeFighting changeFighting2 = new ChangeFighting();
        changeFighting2.setChangehp(-maxHurt);
        FightingPackage.ChangeProcess(changeFighting2, manData, friend, Accepter, skill.getSkilltype(), accepterlist, battlefield);
        --times;
        this.sylg(manData, skill, battlefield, accepterlist, maxHurt, friend, skill3, times);
    }
    
    public static int hurt(int hurt, double jc, double wg, double kbl, List<FightingSkill> skills, List<FightingState> list, FightingState fState, ManData manData, ManData data, FightingSkill skill, Battlefield battlefield) {
        return hurt(0, hurt, jc, wg, kbl, skills, list, fState, manData, data, skill, battlefield, 1);
    }
    
    public static int hurt(int count, int hurt, double jc, double wg, double kbl, List<FightingSkill> skills, List<FightingState> list, FightingState fState, ManData manData, ManData data, FightingSkill skill, Battlefield battlefield, int index) {
        int syhp = data.getHp();
        double kbxs = MixDeal.addition(kbl, fState, manData, data, skill.getSkilltype());
        if (battlefield.CurrentRound == 1) {
            FightingSkill TY_R_JZFZ = manData.getSkillType(TypeUtil.TY_R_JZFZ);
            if (TY_R_JZFZ != null) {
                kbxs -= TY_R_JZFZ.getSkillhurt() / 100.0;
            }
        }
        if (PK_MixDeal.isPK(battlefield.BattleType)) {
            FightingSkill TY_L_10128 = manData.getSkillType(TypeUtil.TY_L_10128);
            if (TY_L_10128 != null && manData.xzstate("扶摇") == null && Battlefield.isV(TY_L_10128.getSkillhurt()) && manData.getStates() == 0) {
                data.addAddState("扶摇", 0.0, 0.0, 2);
            }
            TY_L_10128 = manData.getSkillType(TypeUtil.TY_L_10137);
            if (TY_L_10128 != null && manData.xzstate("沧波") == null && Battlefield.isV(TY_L_10128.getSkillhurt()) && manData.getStates() == 0) {
                data.addAddState("沧波", 0.0, 0.0, 2);
            }
        }
        int kbhurt = 0;
        if (kbxs != 0.0) {
            kbhurt = (int)((double)hurt * (kbxs - 1.0));
            wg *= kbxs;
        }
        int bjhurt = 0;
        double bjxs = MixDeal.additionBj(list, manData, data, skill.getSkilltype());
        if (bjxs != 0.0) {
            bjhurt = (int)((double)hurt * (bjxs - 1.0));
        }
        hurt += kbhurt;
        hurt += bjhurt;
        ChangeFighting changeFighting = new ChangeFighting();
        if (skills != null) {
            for (int i = skills.size() - 1; i >= 0; --i) {
                FightingSkill skill2 = (FightingSkill)skills.get(i);
                int id = skill2.getSkillid();
                if (id == 9263) {
                    if (data.getType() == 1) {
                        jc *= 1.0 + skill2.getSkillhurt() / 100.0;
                    }
                }
                else if (id == 9267) {
                    if (kbxs != 0.0) {
                        AddState addState = data.getGainState();
                        if (addState != null) {
                            data.getAddStates().remove(addState);
                            fState.setEndState_2(addState.getStatename());
                        }
                    }
                }
                else if (id == 9269) {
                    if (kbxs != 0.0 && Battlefield.random.nextInt(4) == 0) {
                        manData.getQuality().setRolewxqkm(manData.getQuality().getRolewxqkm() + 100.0);
                        List<ManData> ksyss = battlefield.getZW(data);
                        ChangeFighting fighting = new ChangeFighting();
                        for (int k = ksyss.size() - 1; k >= 0; --k) {
                            int hurt2 = (int)(skill2.getSkillhurt() + (double)Battlefield.random.nextInt(10000));
                            ManData data2 = (ManData)ksyss.get(k);
                            hurt2 = Calculation.getCalculation().SMHurt(manData, data, (double)hurt2, 0.0, skill.getSkilltype(), (manData.getCamp() == 1) ? battlefield.MyDeath : battlefield.NoDeath);
                            FightingState Accepter2 = new FightingState();
                            fighting.setChangehp(-hurt2);
                            int y = data2.getStates();
                            data2.ChangeData(fighting, Accepter2);
                            list.add(Accepter2);
                            if (data2.getStates() == 1 && y != data2.getStates()) {
                                MixDeal.DeathSkill(data2, Accepter2, battlefield);
                            }
                        }
                        manData.getQuality().setRolewxqkm(manData.getQuality().getRolewxqkm() - 100.0);
                    }
                }
                else if (id == 9283) {
                    if (Battlefield.isV(skill2.getSkillhurt())) {
                        changeFighting.setSkill(skill2);
                    }
                }
                else if (id == 9323) {
                    AddState addState = data.getControlState();
                    if (addState != null) {
                        jc *= 1.0 + skill2.getSkillhurt() / 100.0;
                    }
                }
                else if (id == 10060) {
                    if (Battlefield.isV(skill2.getSkillhurt())) {
                        manData.addnq(2, fState);
                    }
                }
                else if (id == 10065) {
                    manData.addAddState("10065", skill2.getSkillhurt(), skill2.getSkillgain(), skill2.getSkillgain1(), 3);
                    ++manData.lxzt;
                }
                else if (id == 9361) {
                    if (Sepcies_MixDeal.getRace(data.getSe()) != 10004) {
                        jc *= 1.0 + skill2.getSkillhurt() / 100.0;
                    }
                }
                else if (id == 9363) {
                    if (Battlefield.isV(skill2.getSkillhurt())) {
                        AddState addState = data.getGainState();
                        if (addState != null) {
                            data.getAddStates().remove(addState);
                            fState.setEndState_2(addState.getStatename());
                            int j = skills.size() - 1;
                            while (j >= 0) {
                                skill2 = (FightingSkill)skills.get(j);
                                if (skill2.getSkillid() == 9366) {
                                    if (Battlefield.isV(skill2.getSkillhurt())) {
                                        List<ManData> datas = MixDeal.get(true, manData, 0, data.getCamp(), 0, 0, 0, 0, 1, -1, battlefield, 1);
                                        if (datas.size() != 0) {
                                            ManData data3 = (ManData)datas.get(0);
                                            ChangeFighting changeFighting2 = new ChangeFighting();
                                            changeFighting2.setChangetype(addState.getStatename());
                                            changeFighting2.setChangevlaue(addState.getStateEffect());
                                            changeFighting2.setChangevlaue2(addState.getStateEffect2());
                                            FightingState fState2 = new FightingState();
                                            FightingPackage.ChangeProcess(changeFighting2, null, data3, fState2, addState.getStatename(), list, battlefield);
                                        }
                                        break;
                                    }
                                    else {
                                        break;
                                    }
                                }
                                else {
                                    --j;
                                }
                            }
                        }
                    }
                }
                else if (id == 9367) {
                    if (Battlefield.isV(33.0)) {
                        data.addAddState(TypeUtil.TY_GH_QYBY, skill2.getSkillhurt(), 0.0, 2);
                    }
                }
                else if (id == 9286) {
                    FightingSkill TY_X_9968 = manData.getSkillType(TypeUtil.TY_X_9968);
                    if (TY_X_9968 != null) {
                        data.getnqzad((int)((double)data.getNqz() * TY_X_9968.getSkillhurt() / 100.0));
                    }
                    changeFighting.setSkill(skill2);
                }
                else if (id == Integer.parseInt(TypeUtil.TY_X_9957)) {
                    if (Battlefield.isV(skill2.getSkillhurt()) && PK_MixDeal.isPK(battlefield.BattleType)) {
                        data.RemoveAbnormal(new String[] { TypeUtil.KX, TypeUtil.LL, TypeUtil.JS, TypeUtil.MH, "甘霖" });
                        FightingSkill TY_X_9969 = manData.getSkillType(TypeUtil.TY_X_9960);
                        if (TY_X_9969 != null && Battlefield.isV(TY_X_9969.getSkillhurt())) {
                            data.RemoveAbnormal(new String[] { TypeUtil.FB_JJL, TypeUtil.FB_HLZ, TypeUtil.FB_YMGS, TypeUtil.FB_DSC, TypeUtil.FB_JQB });
                        }
                    }
                }
                else if (id == 9307 && manData.getvalue(6) < data.getvalue(6) && data.getType() == 0) {
                    data.addAddState(TypeUtil.TY_F_CFWL_S, 0.0, 0.0, 2);
                    FightingState accepter = new FightingState(data.getCamp(), data.getMan(), "代价");
                    accepter.setText("好大的妖风#24");
                    accepter.setEndState_1(TypeUtil.TY_F_CFWL_S);
                    list.add(accepter);
                    skill2.setSkillgain(skill2.getSkillgain() - 1.0);
                    if (skill2.getSkillgain() <= 0.0) {
                        skills.remove(i);
                    }
                }
            }
        }
        if (PK_MixDeal.isPK(battlefield.BattleType)) {
            hurt = (int)((double)hurt * 0.7);
        }
        if (manData.getType() == 1 && skill.getSkillid() > 1040 && skill.getSkillid() <= 1065) {
            hurt += manData.getLvl() * 100;
        }
        AddState addState2 = data.xzstate("无坚不摧");
        if (addState2 != null) {
            int fmId = (int)addState2.getStateEffect();
            int fmSld = (int)addState2.getStateEffect2();
            if (addState2.getManId() == manData.getId()) {
                jc *= 1.0 + FaMenUtil.getDouble(fmId + "", fmSld, 3);
            }
            else {
                jc *= 1.0 + FaMenUtil.getDouble(fmId + "", fmSld, 4);
            }
        }
        addState2 = data.xzstate("人鬼殊途");
        if (addState2 != null) {
            jc *= (100.0 - addState2.getStateEffect() * addState2.getStateEffect2()) / 100.0;
        }
        jc *= (100.0 + manData.getFamencsJv(1, new String[] { "韬光养晦" })) / 100.0;
        double addHS = 0.0;
        double addQF = 0.0;
        double addKX = 0.0;
        if (PK_MixDeal.isPK(battlefield.BattleType) && skill.getSkilltype().equals("鬼火")) {
            List<FightingSkill> skillList = manData.getSkillTypes(Arrays.asList(new String[] { TypeUtil.TY_G_10085, TypeUtil.TY_G_10090, TypeUtil.TY_G_10105 }));
            for (FightingSkill fightingSkill : skillList) {
                if (data.getType() == 1 && !battlefield.hasMaster(data)) {
                    addHS += Battlefield.getSkillhurt(fightingSkill, "冥");
                }
            }
        }
        hurt = Calculation.getCalculation().SMHurt(manData, data, (double)hurt, wg, skill.getSkilltype(), (manData.getCamp() == 1) ? battlefield.MyDeath : battlefield.NoDeath, addHS, addQF, addKX);
        hurt = (int)((double)hurt * jc);
        AddState addStates = data.xzstate("电感");
        if (addStates != null && skill.getSkillid() >= 1046 && skill.getSkillid() <= 1050) {
            FightingSkill TY_X_9970 = manData.getSkillType(TypeUtil.TY_X_9951);
            if (TY_X_9970 != null) {
                manData.getQuality().setRolehslf(manData.getQuality().getRolehslf() + TY_X_9970.getSkillhurt());
                manData.getSkills().remove(TY_X_9970);
            }
            hurt += (int)addStates.getStateEffect();
        }
        hurt /= index + 1;
        changeFighting.setChangehp(-hurt);
        data.addBear(skill.getSkillname());
        FightingSkill yqcs = manData.getSkillId(1888);
        if (yqcs != null && Battlefield.isV(yqcs.getSkillhitrate()) && PK_MixDeal.isPK(battlefield.BattleType)) {
            int MpHurt = (int)((double)(-hurt) * 0.1);
            changeFighting.setChangemp(MpHurt);
        }
        FightingPackage.ChangeProcess(changeFighting, manData, data, fState, skill.getSkilltype(), list, battlefield);
        double zssh = 0.0;
        FightingState fState3 = new FightingState();
        ChangeFighting changeFighting3 = new ChangeFighting();
        if (manData.getSkillType("8074") != null) {
            AddState chenshizhuiji = data.xzstate("8074");
            if (data.getStates() == 1 && chenshizhuiji == null) {
                Spell.getTZ4(list, fState, manData, data, null, skill.getSkilltype(), battlefield);
            }
        }
        if (manData.skillname("催筋断骨") != null) {
            double cjdg = manData.getFmJv(1, new String[] { "催筋断骨" });
            int cjdgjv = 50;
            addState2 = manData.xzstate("气吞山河");
            if (addState2 != null) {
                cjdgjv = 100;
                int fmId2 = (int)addState2.getStateEffect();
                int fmSld2 = (int)addState2.getStateEffect2();
                cjdg += FaMenUtil.getDouble(fmId2, fmSld2, 2);
            }
            if (Battlefield.isV((double)cjdgjv) && data.getStates() == 0) {
                ChangeFighting changeFighting4 = new ChangeFighting();
                zssh = (double)hurt * cjdg / 100.0;
                changeFighting4.setChangehp((int)(-zssh));
                FightingState fState4 = new FightingState();
                FightingPackage.ChangeProcess(changeFighting4, manData, data, fState4, "至圣", list, battlefield);
            }
        }
        else {
            ChangeFighting changeFighting5 = new ChangeFighting();
            if (zyzy(manData, data, skill, battlefield, changeFighting5)) {
                FightingState fState5 = new FightingState();
                FightingPackage.ChangeProcess(changeFighting5, manData, data, fState5, "堆加", list, battlefield);
            }
        }
        if (kbxs != 0.0 && !manData.fskbbj) {
            manData.fskbbj = true;
        }
        String[] skillTypes;
        for (String skillType : skillTypes = new String[] { TypeUtil.TY_X_9941, TypeUtil.TY_X_9952, TypeUtil.TY_X_9961, TypeUtil.TY_X_10009 }) {
            FightingSkill T_X = data.getSkillType(skillType);
            if (T_X != null) {
                int nq = (int)(13.0 * T_X.getSkillhurt() / 100.0);
                int skillId = T_X.getSkillid();
                if ((skillId >= 1046 && skillId <= 1050) || (skillId >= 1041 && skillId <= 1045) || (skillId >= 1051 && skillId <= 1055) || (skillId >= 1056 && skillId <= 1060)) {
                    nq += nq + (int)(13.0 * T_X.getSkillhurt() / 100.0);
                }
                data.addnq(nq, fState);
            }
        }
        if (skills != null) {
            for (int l = skills.size() - 1; l >= 0; --l) {
                FightingSkill skill3 = (FightingSkill)skills.get(l);
                int id2 = skill3.getSkillid();
                if (id2 == 9266) {
                    if (data.getType() == 1 && data.getStates() == 1) {
                        ManData parents = battlefield.getPetParents(data);
                        if (parents != null && parents.getStates() == 0) {
                            int hurt3 = (int)(skill3.getSkillhurt() + (double)Battlefield.random.nextInt(5000));
                            ChangeFighting fighting2 = new ChangeFighting();
                            FightingState Accepter3 = new FightingState();
                            fighting2.setChangehp(-hurt3);
                            int y2 = parents.getStates();
                            parents.ChangeData(fighting2, Accepter3);
                            list.add(Accepter3);
                            if (parents.getStates() == 1 && y2 != parents.getStates()) {
                                MixDeal.DeathSkill(parents, Accepter3, battlefield);
                            }
                        }
                    }
                }
                else if (id2 == 9281 || id2 == 9301 || id2 == 9321) {
                    if (data.getStates() == 0) {
                        data.addAddState(skill3.getSkilltype(), skill3.getSkillhurt(), 0.0, 2);
                    }
                }
                else if (id2 == 9284 || id2 == 9304 || id2 == 9324) {
                    if (Battlefield.isV(skill3.getSkillhurt())) {
                        addState2 = data.getGainState();
                        if (addState2 != null) {
                            if (id2 == 9324) {
                                addState2.isEnd();
                            }
                            else {
                                data.getAddStates().remove(addState2);
                                fState.setEndState_2(addState2.getStatename());
                                if (id2 == 9304) {
                                    List<ManData> datas2 = MixDeal.get(true, manData, 0, data.getCamp(), 0, 0, 0, 0, 10, -1, battlefield, 1);
                                    int m = datas2.size() - 1;
                                    while (m >= 0) {
                                        ManData data4 = (ManData)datas2.get(m);
                                        if (data4.xzstate(addState2.getStatename()) == null) {
                                            ChangeFighting changeFighting6 = new ChangeFighting();
                                            changeFighting6.setChangetype(addState2.getStatename());
                                            changeFighting6.setChangevlaue(addState2.getStateEffect());
                                            changeFighting6.setChangevlaue2(addState2.getStateEffect2());
                                            FightingState fState6 = new FightingState();
                                            FightingPackage.ChangeProcess(changeFighting6, null, data4, fState6, addState2.getStatename(), list, battlefield);
                                            break;
                                        }
                                        else {
                                            --m;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else if (id2 == 9303) {
                    if (data.getType() == 1 && data.getStates() == 1) {
                        manData.addAddState2(skill3.getSkilltype(), skill3.getSkillhurt(), 0.0, 2);
                    }
                }
                else if (id2 == 9306) {
                    if (data.getStates() == 1) {
                        int condition = 2;
                        FightingSkill YQDS = manData.getSkillType(TypeUtil.TY_X_9959);
                        if (YQDS != null && Battlefield.isV(YQDS.getSkillhurt())) {
                            condition = 3;
                        }
                        data.addAddState2(skill3.getSkilltype(), skill3.getSkillhurt(), 0.0, condition);
                    }
                }
                else if (id2 == 9947) {
                    if (Battlefield.isV(skill3.getSkillhurt()) && manData.tl <= 9.0) {
                        ++manData.tl;
                        manData.getQuality().addtl(manData.tl);
                    }
                }
                else if (id2 == 9965) {
                    if (Battlefield.isV(skill3.getSkillhurt()) && manData.ts <= 9.0) {
                        ++manData.ts;
                        manData.getQuality().addtl(manData.tl);
                    }
                }
                else if (id2 == 9956) {
                    if (Battlefield.isV(skill3.getSkillhurt()) && manData.tf <= 9.0) {
                        ++manData.tf;
                        manData.getQuality().addtl(manData.tf);
                    }
                }
                else if (id2 == 10014) {
                    if (Battlefield.isV(skill3.getSkillhurt()) && manData.th <= 9.0) {
                        ++manData.th;
                        manData.getQuality().addtl(manData.th);
                    }
                }
                else if (id2 == 9326) {
                    SummonType.xianzhi(manData, skill);
                    int hurt4 = (int)skill3.getSkillhurt() + Battlefield.random.nextInt(3000);
                    if (kbxs != 0.0) {
                        hurt4 = (int)skill3.getSkillhurt() + Battlefield.random.nextInt(3000);
                    }
                    addState2 = data.addAddState2(skill3.getSkilltype(), (kbxs != 0.0) ? 2.0 : 1.0, (double)hurt4, 9999);
                    if (addState2.getStateEffect() >= 5.0) {
                        battlefield.isHSJY = true;
                    }
                    fState.setEndState_1(TypeUtil.TY_H_HSJY);
                }
                else if (id2 == 9362) {
                    if (data.getStates() == 1 && kbxs != 0.0) {
                        manData.addAddState2(skill3.getSkilltype(), skill3.getSkillhurt(), 0.0, 2);
                    }
                }
                else if (id2 == 9371) {
                    int ren = battlefield.Datapathhuo(data.getCamp(), data.getMan());
                    double petlv = 0.0;
                    FightingSkill TY_G_10067 = manData.getSkillType("10067");
                    if (TY_G_10067 != null && ren == -1) {
                        petlv = TY_G_10067.getSkillhurt();
                    }
                    if (data.getStates() == 1 && Battlefield.isV(skill3.getSkillhurt() + petlv)) {
                        data.addAddState2(skill3.getSkilltype(), skill3.getSkillhurt(), 0.0, 2);
                        FightingState Originator2 = new FightingState();
                        Originator2.setStartState("被攻击");
                        Originator2.setCamp(data.getCamp());
                        Originator2.setMan(data.getMan());
                        Originator2.setEndState_1("9371");
                        list.add(Originator2);
                    }
                }
                else if (id2 == 9369) {
                    if (data.getvalue(0) > 80.0 - skill3.getSkillhurt() / 40.0) {
                        data.addAddState2(skill3.getSkilltype(), skill3.getSkillhurt(), 0.0, 2);
                    }
                }
                else if (id2 == 9966) {
                    if (Battlefield.isV(skill3.getSkillhurt())) {
                        addState2 = data.addAddState2("寒冷", 1.0, 0.0, 3);
                        if (addState2.getStateEffect() >= 3.0) {
                            fState.setEndState_1("封印");
                            data.addAddState("封印", 0.0, 0.0, 1);
                            data.RemoveAbnormal(new String[] { "寒冷", "封印" });
                        }
                    }
                }
                else if (id2 == 9370) {
                    if (data.getStates() == 0 && data.getvalue(0) < skill3.getSkillhurt() / 100.0 && Battlefield.isV(20.0)) {
                        int hurt4 = (int)skill3.getSkillhurt() * 20 + Battlefield.random.nextInt(15000);
                        ChangeFighting fighting3 = new ChangeFighting();
                        FightingState Accepter4 = new FightingState();
                        fighting3.setChangehp(-hurt4);
                        data.ChangeData(fighting3, Accepter4);
                        list.add(Accepter4);
                    }
                }
                else if (id2 == 9327) {
                    if (data.getStates() == 1) {
                        int hurt4 = hurt - syhp;
                        hurt4 = (int)((double)hurt4 * (skill3.getSkillhurt() / 100.0));
                        List<ManData> datas3 = MixDeal.get(false, null, 0, manData.getCamp(), 0, 0, 0, 0, 1, 0, battlefield, 1);
                        if (datas3.size() != 0) {
                            ManData data4 = (ManData)datas3.get(0);
                            ChangeFighting changeFighting6 = new ChangeFighting();
                            changeFighting6.setChangehp(-hurt4);
                            FightingState fState6 = new FightingState();
                            FightingPackage.ChangeProcess(changeFighting6, null, data4, fState6, "薪火", list, battlefield);
                        }
                    }
                }
                else if (id2 == 10015) {
                    FightingSkill TY_X_9971 = manData.getSkillType(TypeUtil.TY_X_10018);
                    data.addAddState("燃烧", skill3.getSkillhurt(), 0.0, (TY_X_9971 != null && Battlefield.isV(TY_X_9971.getSkillhurt())) ? 4 : 3);
                }
                else if (id2 == 10016) {
                    data.addAddState("灼烧", 0.0, 0.0, 2);
                    int hurt4 = -changeFighting.getChangehp();
                    hurt4 *= (int)(skill3.getSkillhurt() / 100.0);
                    ChangeFighting changeFighting7 = new ChangeFighting();
                    changeFighting7.setChangehp(-hurt4);
                    FightingState fState7 = new FightingState();
                    FightingPackage.ChangeProcess(changeFighting7, null, data, fState7, "火", list, battlefield);
                }
            }
        }
        return -changeFighting.getChangehp();
    }
    
    private static boolean zyzy(ManData manData, ManData data, FightingSkill skill, Battlefield battlefield, ChangeFighting changeFighting, String[] skillTypes) {
        boolean is = false;
        for (String skillType : skillTypes) {
            FightingSkill skill2 = manData.getSkillType(skillType);
            if (skill2 != null && Battlefield.isV(20.0)) {
                FightingSkill skill3 = null;
                int n = -1;
                switch (skillType.hashCode()) {
                    case 1754625: {
                        if (skillType.equals("9978")) {
                            n = 0;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 1754626: {
                        if (skillType.equals("9979")) {
                            n = 1;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 1754656: {
                        if (skillType.equals("9988")) {
                            n = 2;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 1754657: {
                        if (skillType.equals("9989")) {
                            n = 3;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 1754687: {
                        if (skillType.equals("9998")) {
                            n = 4;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 1754688: {
                        if (skillType.equals("9999")) {
                            n = 5;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 46730231: {
                        if (skillType.equals("10028")) {
                            n = 6;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 46730232: {
                        if (skillType.equals("10029")) {
                            n = 7;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 46730262: {
                        if (skillType.equals("10038")) {
                            n = 8;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 46730263: {
                        if (skillType.equals("10039")) {
                            n = 9;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                }
                switch (n) {
                    case 0: {
                        skill3 = manData.getSkillId(skill.getSkillid() + 5);
                        break;
                    }
                    case 1: {
                        skill3 = manData.getSkillId(skill.getSkillid() - 5);
                        break;
                    }
                    case 2: {
                        skill3 = manData.getSkillId(skill.getSkillid() - 5);
                        break;
                    }
                    case 3: {
                        skill3 = manData.getSkillId(skill.getSkillid() + 5);
                        break;
                    }
                    case 4: {
                        skill3 = manData.getSkillId(skill.getSkillid() - 10);
                        break;
                    }
                    case 5: {
                        skill3 = manData.getSkillId(skill.getSkillid() + 10);
                        break;
                    }
                    case 6: {
                        skill3 = manData.getSkillId(skill.getSkillid() + 10);
                        break;
                    }
                    case 7: {
                        skill3 = manData.getSkillId(skill.getSkillid() - 10);
                        break;
                    }
                    case 8: {
                        skill3 = manData.getSkillId(skill.getSkillid() + 5);
                        break;
                    }
                    case 9: {
                        skill3 = manData.getSkillId(skill.getSkillid() - 5);
                        break;
                    }
                }
                if (skill3 != null) {
                    int hurt = (int)skill3.getSkillhurt();
                    hurt = Calculation.getCalculation().SMHurt(manData, data, (double)hurt, 0.0, skill3.getSkilltype(), (manData.getCamp() == 1) ? battlefield.MyDeath : battlefield.NoDeath);
                    changeFighting.setChangehp((int)((double)changeFighting.getChangehp() - (double)hurt * skill2.getSkillhurt() / 100.0));
                    is = true;
                }
            }
        }
        return is;
    }
    
    private static boolean zyzy(ManData manData, ManData data, FightingSkill skill, Battlefield battlefield, ChangeFighting changeFighting) {
        String[] skillTypes = null;
        if (skill.getSkillid() >= 1041 && skill.getSkillid() <= 1045) {
            skillTypes = new String[] { "9989", "9999" };
        }
        else if (skill.getSkillid() >= 1046 && skill.getSkillid() <= 1050) {
            skillTypes = new String[] { "9978", "9988", "10028" };
        }
        else if (skill.getSkillid() >= 1051 && skill.getSkillid() <= 1055) {
            skillTypes = new String[] { "9979", "9998", "10038" };
        }
        else if (skill.getSkillid() >= 1056 && skill.getSkillid() <= 1060) {
            skillTypes = new String[] { "10029", "10039" };
        }
        return skillTypes != null && zyzy(manData, data, skill, battlefield, changeFighting, skillTypes);
    }
    
    public static void addTZState(ManData data, FightingSkill tz_yg, FightingSkill tz_cb, FightingSkill tz_ph, FightingSkill tz_xy, FightingState Accepter) {
        if (tz_yg != null) {
            AddState addState = new AddState();
            addState.setStatename(tz_yg.getSkilltype());
            addState.setStateEffect(tz_yg.getSkillhurt());
            addState.setStateEffect2(tz_yg.getSkillgain());
            addState.setSurplus(tz_yg.getSkillcontinued());
            Accepter.setEndState_1(addState.getStatename());
            data.getAddStates().add(addState);
            data.getQuality().addkr(-tz_yg.getSkillhurt());
            data.getQuality().addks(-tz_yg.getSkillgain());
        }
        if (tz_cb != null && Battlefield.isV(tz_cb.getSkillhitrate())) {
            AddState addState = new AddState();
            addState.setStatename(tz_cb.getSkilltype());
            addState.setStateEffect(tz_cb.getSkillhurt());
            addState.setSurplus(tz_cb.getSkillcontinued());
            Accepter.setEndState_1(addState.getStatename());
            data.getAddStates().add(addState);
            data.setSp2((int)((double)data.getSp2() - tz_cb.getSkillhurt()));
        }
        if (tz_ph != null) {
            double v = tz_ph.getSkillhurt();
            if (data.xzstate(tz_ph.getSkilltype()) == null && Battlefield.isV(tz_ph.getSkillhitrate())) {
                v *= 2.0;
            }
            AddState addState2 = new AddState();
            addState2.setStatename(tz_ph.getSkilltype());
            addState2.setStateEffect(v);
            addState2.setSurplus(tz_ph.getSkillcontinued());
            Accepter.setEndState_1(addState2.getStatename());
            data.getAddStates().add(addState2);
            data.getQuality().addkr(-v);
        }
        if (tz_xy != null) {
            double v = tz_xy.getSkillhurt();
            if (data.xzstate(tz_xy.getSkilltype()) == null && (double)Battlefield.random.nextInt(100) < tz_xy.getSkillhitrate()) {
                v *= 2.0;
            }
            AddState addState2 = new AddState();
            addState2.setStatename(tz_xy.getSkilltype());
            addState2.setStateEffect(v);
            addState2.setSurplus(tz_xy.getSkillcontinued());
            Accepter.setEndState_1(addState2.getStatename());
            data.getAddStates().add(addState2);
            data.getQuality().addks(-v);
        }
    }
}
