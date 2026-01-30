package come.tool.FightingSpellAction;

import java.util.List;
import come.tool.FightingData.Calculation;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.ChangeFighting;
import org.come.tool.CustomFunction;
import come.tool.FightingData.PK_MixDeal;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.AddState;
import come.tool.FightingData.MixDeal;
import java.util.ArrayList;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public class BB_BCJF implements SpellAction
{
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
        FightingSkill bb_e_ttym = null;
        FightingSkill bb_e_flbd = null;
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
        skill.setSkillsum(skill.getSkillsum() + bb_size);
        List<ManData> datas = MixDeal.getjieshou(events, skill, manData, Accepterlist, battlefield);
        skill.setSkillsum(skill.getSkillsum() - bb_size);
        datas.clear();
        for (int j = 0; j <= battlefield.fightingdata.size() - 1; ++j) {
            ManData data11 = (ManData)battlefield.fightingdata.get(j);
            for (int k = 0; k <= data11.getAddStates().size() - 1; ++k) {
                if (((AddState)data11.getAddStates().get(k)).getStatename().equals("1243")) {
                    datas.add(data11);
                }
            }
        }
        if (datas.size() == 0) {
            return;
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
            long totalHurt = 0L;
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
                            jc *= skill5.getSkillhurt() / (double)((datas.size() < 5) ? 5 : datas.size()) / 100.0;
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
                }
                else if (skill.getSkillid() >= 1061 && skill.getSkillid() <= 1065) {
                    skill4 = manData.skillId(9364);
                }
            }
            else if (skill.getSkillid() == 1065) {
                AddState addState = manData.xzstate(TypeUtil.TY_GH_XXYX);
                if (addState != null) {
                    jc = 1.0 - (addState.getStateEffect() * 0.05 + ((addState.getStateEffect() <= 2.0) ? (addState.getStateEffect() * 0.05) : 0.1));
                }
            }
            if (skill.getSkillid() == 1055) {
                AddState addState = manData.xzstate(TypeUtil.TY_S_HSCS);
                if (addState != null) {
                    sum = 2;
                    dds = 1.0;
                    jc *= addState.getStateEffect() / 100.0;
                }
            }
            if (datas.size() > 1) {
                ManData data12 = (ManData)datas.remove(0);
                datas.add(data12);
            }
            boolean isyqcs = false;
            double shbs = 1.0;
            if (sum > 1 && skill.getSkillid() >= 1040 && skill.getSkillid() <= 1065) {
                shbs += (double)manData.executeQyzs() / 100.0;
            }
            int l = 0;
            while (l < sum) {
                if (manData.getStates() != 0) {
                    break;
                }
                else {
                    if (l != 0) {
                        Accepterlist = new ArrayList<>();
                    }
                    FightingState Originator2 = new FightingState();
                    Originator2.setEndState(skill.getSkillname());
                    if (l == 0) {
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
                    long maxHurt = 0L;
                    String skin = null;
                    isyqcs = (yqcs != null && Battlefield.isV(yqcs.getSkillhitrate()));
                    for (int m = datas.size() - 1; m >= 0; --m) {
                        ManData data13 = (ManData)datas.get(m);
                        if (data13.getStates() != 0) {
                            data13 = MixDeal.getjieshou(skill, manData, datas, battlefield);
                            if (data13 != null) {
                                datas.set(m, data13);
                            }
                            else {
                                datas.remove(m);
                                continue;
                            }
                        }
                        FightingState Accepter = MixDeal.DSMY(manData, data13, skill.getSkillid(), battlefield);
                        if (Accepter == null) {
                            data13.addBear(skilltype);
                            Accepter = new FightingState();
                            if (isTZ) {
                                HurtAction.addTZState(data13, tz_yg, tz_cb, tz_ph, tz_xy, Accepter);
                                isTZ = false;
                            }
                            double hurt = skill.getSkillhurt();
                            if (isyqcs && m == datas.size() - 1) {
                                skin = yqcs.getSkilltype();
                                if (manData.getMp_z() > 100000) {
                                    hurt += (CustomFunction.XS((long)manData.getMp_z(), 4200.0) - 15000.0) * (double)(PK_MixDeal.isPK(battlefield.BattleType) ? 1 : 2);
                                }
                            }
                            if (isyqcs) {
                                for (int k2 = 1; k2 < sum; ++k2) {
                                    hurt *= dds;
                                }
                            }
                            if (m == datas.size() - 1 && l == 0) {
                                manData.executeSdef(battlefield);
                                manData.executeQfyx(data13, Accepterlist);
                            }
                            if (sum > 1) {
                                hurt *= shbs;
                            }
                            long fashang = 0L;
                            fashang = (long)HurtAction.hurt((int)hurt, jc, wg, kbl, skills, Accepterlist, Accepter, manData, data13, skill, battlefield);
                            totalHurt += fashang;
                            if (maxHurt < fashang) {
                                maxHurt = fashang;
                            }
                            if (m == datas.size() - 1) {
                                manData.executeQzzq(data13, Accepter, Accepterlist, battlefield);
                                manData.addFaDun(fashang, Originator2);
                            }
                            if (data13.getStates() == 0 && skill.getSkillid() > 1040 && skill.getSkillid() < 1065 && skill.getSkillid() % 5 == 4) {
                                int zxcg = manData.executeZxcg(1);
                                if (zxcg > 0 && Battlefield.isV((double)zxcg + manData.getShanghai() / 66.6)) {
                                    FightingState say = new FightingState();
                                    say.setCamp(manData.getCamp());
                                    say.setMan(manData.getMan());
                                    say.setText("看我的锥心刺骨");
                                    Accepterlist.add(0, say);
                                    int zssh = manData.executeZxcg(2) * manData.getMp_z() / 100;
                                    if (data13.getCamp() == manData.getCamp()) {
                                        zssh = -zssh;
                                    }
                                    ChangeFighting changeFighting2 = new ChangeFighting();
                                    changeFighting2.setChangehp(-zssh);
                                    FightingState fState2 = new FightingState();
                                    FightingPackage.ChangeProcess(changeFighting2, null, data13, fState2, (data13.getCamp() == manData.getCamp()) ? skilltype : "至圣", Accepterlist, battlefield);
                                }
                            }
                            if (manData.getSkillId(9369) != null) {
                                Accepter = new FightingState();
                                ChangeFighting changeFighting3 = new ChangeFighting();
                                if (data13.getCamp() == manData.getCamp()) {
                                    hurt = -hurt;
                                }
                                changeFighting3.setChangehp((int)(-hurt));
                                FightingState say = new FightingState();
                                say.setCamp(manData.getCamp());
                                say.setMan(manData.getMan());
                                FightingPackage.ChangeProcess(changeFighting3, manData, data13, Accepter, (data13.getCamp() == manData.getCamp()) ? skilltype : "至圣", Accepterlist, battlefield);
                            }
                            if (l == sum - 1 && gqlx != null) {
                                AddState zt = data13.xzstate(gqlx.getSkilltype());
                                if (zt == null) {
                                    data13.addBear(gqlx.getSkilltype());
                                    AddState addState2 = new AddState();
                                    addState2.setStatename(gqlx.getSkilltype());
                                    addState2.setStateEffect(gqlx.getSkillhurt());
                                    addState2.setStateEffect2(gqlx.getSkillgain());
                                    addState2.setSurplus(gqlx.getSkillcontinued());
                                    Accepter.setEndState_1(addState2.getStatename());
                                    data13.getAddStates().add(addState2);
                                }
                                else if (data13.getStates() == 0) {
                                    FightingState Accepter2 = new FightingState();
                                    double gqlxbj = (double)manData.getMp_z() * 0.12 * (double)manData.getLvl() / 100.0;
                                    HurtAction.hurt(0, jc, gqlxbj, kbl, skills, Accepterlist, Accepter2, manData, data13, skill, battlefield);
                                }
                            }
                        }
                        else {
                            Accepterlist.add(Accepter);
                        }
                        if (data13.getCamp() == manData.getCamp()) {
                            skin = "1243B";
                        }
                        else {
                            skin = null;
                        }
                        Accepter.setSkillskin((skin != null) ? skin : (skill.getSkillid() + ""));
                        if (l == 0) {
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
                                    List<ManData> ksyss = battlefield.getZW(data13);
                                    ChangeFighting fighting = new ChangeFighting();
                                    int hurt2 = (int)(ksys.getSkillhurt() / 100.0 * (double)manData.getMp_z() / (double)ksyss.size());
                                    for (int k3 = ksyss.size() - 1; k3 >= 0; --k3) {
                                        ManData data14 = (ManData)ksyss.get(k3);
                                        FightingState Accepter3 = new FightingState();
                                        fighting.setChangehp(-hurt2);
                                        int y = data14.getStates();
                                        data14.ChangeData(fighting, Accepter3);
                                        Accepterlist.add(Accepter3);
                                        if (data14.getStates() == 1 && y != data14.getStates()) {
                                            MixDeal.DeathSkill(data14, Accepter3, battlefield);
                                        }
                                    }
                                }
                            }
                            else if (isyqcs && m == datas.size() - 1) {
                                int size = 1;
                                for (int k4 = 0; k4 < 4; ++k4) {
                                    if (Battlefield.isV(yqcs.getSkillhitrate())) {
                                        ++size;
                                    }
                                }
                                List<ManData> datas2 = MixDeal.get(false, data13, 0, manData.getCamp(), 0, 0, 0, 0, size, -1, battlefield, 0);
                                for (int k2 = 0; k2 < datas2.size(); ++k2) {
                                    long hurt3 = (long)(int)skill.getSkillhurt();
                                    if (manData.getMp_z() > 100000) {
                                        hurt3 = (long)((double)hurt3 + (CustomFunction.XS((long)manData.getMp_z(), 4200.0) - 15000.0) * (double)(PK_MixDeal.isPK(battlefield.BattleType) ? 1 : 2));
                                    }
                                    for (int l2 = 0; l2 <= k2; ++l2) {
                                        hurt3 = (long)((double)hurt3 * 0.8);
                                    }
                                    ManData data15 = (ManData)datas2.get(k2);
                                    FightingState Accepter4 = new FightingState();
                                    hurt3 = (long)Calculation.getCalculation().SMHurt(manData, data15, (double)hurt3, 0.0, skill.getSkilltype(), (manData.getCamp() == 1) ? battlefield.MyDeath : battlefield.NoDeath);
                                    ChangeFighting fighting2 = new ChangeFighting();
                                    fighting2.setChangehp((int)(-hurt3));
                                    fighting2.setChangemp((int)((double)(-hurt3) * 0.2));
                                    Accepter4.setSkillskin(skin);
                                    FightingPackage.ChangeProcess(fighting2, manData, data15, Accepter4, skill.getSkilltype(), Accepterlist, battlefield);
                                }
                            }
                        }
                    }
                    if (manData.fskbbj && maxHurt > 0L && Battlefield.isV((double)manData.executeDksj(2))) {
                        ManData friend = battlefield.getZW1((ManData)datas.get(datas.size() - 1));
                        if (friend != null) {
                            FightingState Accepter5 = new FightingState();
                            Accepter5.setSkillskin(skin);
                            ChangeFighting changeFighting4 = new ChangeFighting();
                            changeFighting4.setChangehp((int)(-maxHurt));
                            FightingState say2 = new FightingState();
                            say2.setCamp(manData.getCamp());
                            say2.setMan(manData.getMan());
                            say2.setText("看我的大开杀戒");
                            Accepterlist.add(0, say2);
                            FightingPackage.ChangeProcess(changeFighting4, manData, friend, Accepter5, skill.getSkilltype(), Accepterlist, battlefield);
                        }
                    }
                    Originator2.setSkillsy(skill.getSkillname());
                    Accepterlist.add(Originator2);
                    FightingEvents Events = new FightingEvents();
                    Events.setAccepterlist(Accepterlist);
                    battlefield.NewEvents.add(Events);
                    if (Accepterlist.size() == 1) {
                        break;
                    }
                    else {
                        ++l;
                    }
                }
            }
            if (manData.ymjr) {
                manData.ymjr = false;
                manData.executePhbd((FightingEvents)battlefield.NewEvents.get(battlefield.NewEvents.size() - 1));
            }
            if (skill4 != null && totalHurt > 0L && manData.getStates() == 0) {
                if (skill4.getSkillid() == 9364) {
                    ChangeFighting fighting3 = new ChangeFighting();
                    FightingState Accepter6 = new FightingState();
                    Accepter6.setStartState("药");
                    totalHurt = (long)((double)totalHurt * (skill4.getSkillhurt() / 100.0));
                    fighting3.setChangemp((int)totalHurt);
                    manData.ChangeData(fighting3, Accepter6);
                    Accepterlist.add(Accepter6);
                }
                else {
                    totalHurt = (long)((double)totalHurt * 0.2);
                    if (totalHurt > (long)manData.getHp_z()) {
                        totalHurt = (long)manData.getHp_z();
                    }
                    ChangeFighting fighting3 = new ChangeFighting();
                    FightingState Accepter6 = new FightingState();
                    Accepter6.setStartState("代价");
                    fighting3.setChangetype(skill4.getSkilltype());
                    fighting3.setChangevlaue((double)totalHurt);
                    if (skill4.getSkillid() == 9271) {
                        fighting3.setChangevlaue2((skill.getSkillhurt() - 15.0) / 2.0 * 2000.0);
                    }
                    else if (skill4.getSkillid() == 9328) {
                        fighting3.setChangevlaue2((skill.getSkillhurt() - 15.0) / 2.0 * 0.5);
                    }
                    else {
                        fighting3.setChangevlaue2((skill.getSkillhurt() - 15.0) / 2.0 * 5.0);
                    }
                    manData.ChangeData(fighting3, Accepter6);
                    Accepterlist.add(Accepter6);
                }
            }
            return;
        }
    }
}
