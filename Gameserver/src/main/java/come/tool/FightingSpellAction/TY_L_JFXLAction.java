package come.tool.FightingSpellAction;

import java.util.List;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.PK_MixDeal;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.MixDeal;
import java.util.ArrayList;
import come.tool.FightingData.SummonType;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public class TY_L_JFXLAction implements SpellAction
{
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        FightingSkill skillSM = manData.skillId(1049);
        SummonType.xianzhi(manData, skill);
        String skilltype = skillSM.getSkilltype();
        List<FightingState> Accepterlist = new ArrayList<>();
        List<ManData> datas = MixDeal.getjieshou(events, skillSM, manData, Accepterlist, battlefield, 10);
        if (datas.size() == 0) {
            FightingState Originator = events.getOriginator();
            if (manData.daijia(skill, Originator, battlefield)) {
                return;
            }
            Originator.setStartState("法术攻击");
            Originator.setSkillsy(skillSM.getSkillname());
            events.setOriginator(null);
            Accepterlist.add(Originator);
            events.setAccepterlist(Accepterlist);
            battlefield.NewEvents.add(events);
            return;
        }
        else {
            int sum = 3;
            double dds = 1.0;
            double jc = manData.getSpellJC();
            double wg = manData.getWGTB();
            double kbl = 0.0;
            FightingSkill skill2 = manData.skillId(9261);
            if (skill2 != null) {
                jc *= 1.0 + skill2.getSkillhurt() / 100.0;
            }
            skill2 = manData.skillId(9264);
            if (skill2 != null) {
                kbl += skill2.getSkillhurt();
            }
            FightingSkill tz_yg = null;
            FightingSkill tz_cb = null;
            FightingSkill tz_ph = null;
            FightingSkill tz_xy = null;
            FightingSkill ksys = null;
            List<FightingSkill> skills = ControlAction.getSkills(manData, skillSM, battlefield.BattleType);
            for (int i = manData.getSkills().size() - 1; i >= 0; --i) {
                skill2 = (FightingSkill)manData.getSkills().get(i);
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
            }
            boolean isTZ = false;
            boolean isXH = true;
            if (tz_yg != null || tz_cb != null || tz_ph != null || tz_xy != null) {
                isTZ = true;
            }
            int size = 0;
            double electricDamageIncreasePercentage = 0.0;
            while (datas.size() != 0) {
                if (size >= 21) {
                    break;
                }
                else {
                    ManData data = (ManData)datas.remove(0);
                    if (data.getStates() != 0) {
                        continue;
                    }
                    else {
                        int j = 0;
                        while (j < sum) {
                            if (manData.getStates() != 0 || data.getStates() != 0) {
                                boolean isPlayerBattle = PK_MixDeal.isPK(battlefield.BattleType);
                                FightingSkill sk2 = manData.getSkillType(TypeUtil.TY_X_9949);
                                if (isPlayerBattle && sk2 != null) {
                                    electricDamageIncreasePercentage += sk2.getSkillhurt();
                                    if (electricDamageIncreasePercentage > sk2.getSkillgain()) {
                                        electricDamageIncreasePercentage = sk2.getSkillgain();
                                        break;
                                    }
                                    else {
                                        break;
                                    }
                                }
                                else {
                                    break;
                                }
                            }
                            else if (++size > 21) {
                                break;
                            }
                            else {
                                if (!isXH) {
                                    Accepterlist = new ArrayList<>();
                                }
                                FightingState Originator2 = new FightingState();
                                Originator2.setEndState(skillSM.getSkillname());
                                if (isXH) {
                                    isXH = false;
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
                                FightingState Accepter = MixDeal.DSMY(manData, data, skillSM.getSkillid(), battlefield);
                                if (Accepter == null) {
                                    data.addBear(skilltype);
                                    Accepter = new FightingState();
                                    if (isTZ) {
                                        HurtAction.addTZState(data, tz_yg, tz_cb, tz_ph, tz_xy, Accepter);
                                        isTZ = false;
                                    }
                                    double hurt = skillSM.getSkillhurt();
                                    for (int k = 1; k < sum; ++k) {
                                        hurt *= dds;
                                    }
                                    FightingSkill clone = skillSM.clone();
                                    clone.setSkilltype(skill.getSkillname());
                                    HurtAction.hurt((int)hurt, jc, wg, kbl, skills, Accepterlist, Accepter, manData, data, clone, battlefield);
                                    dds -= 0.1;
                                    if (dds <= 0.0) {
                                        dds = 0.1;
                                    }
                                }
                                else {
                                    Accepterlist.add(Accepter);
                                }
                                Accepter.setSkillskin(skillSM.getSkillid() + "");
                                if (j == 0 && ksys != null) {
                                    double gl = ksys.getSkillhitrate();
                                    if (skillSM.getSkilllvl() == 2) {
                                        gl *= 1.2;
                                    }
                                    else if (skillSM.getSkilllvl() == 3) {
                                        gl *= 1.45;
                                    }
                                    else if (skillSM.getSkilllvl() == 4) {
                                        gl *= 2.0;
                                    }
                                    if (gl > (double)Battlefield.random.nextInt(100)) {
                                        List<ManData> ksyss = battlefield.getZW(data);
                                        ChangeFighting fighting = new ChangeFighting();
                                        int hurt2 = (int)(ksys.getSkillhurt() / 100.0 * (double)manData.getMp_z() / (double)ksyss.size());
                                        for (int l = ksyss.size() - 1; l >= 0; --l) {
                                            ManData data2 = (ManData)ksyss.get(l);
                                            FightingState Accepter2 = new FightingState();
                                            fighting.setChangehp(-hurt2);
                                            int y = data2.getStates();
                                            data2.ChangeData(fighting, Accepter2);
                                            Accepterlist.add(Accepter2);
                                            if (data2.getStates() == 1 && y != data2.getStates()) {
                                                MixDeal.DeathSkill(data2, Accepter2, battlefield);
                                            }
                                        }
                                    }
                                }
                                Originator2.setStartState("法术攻击");
                                Originator2.setSkillsy(skillSM.getSkillname());
                                Accepterlist.add(Originator2);
                                FightingEvents Events = new FightingEvents();
                                Events.setAccepterlist(Accepterlist);
                                battlefield.NewEvents.add(Events);
                                if (Accepterlist.size() == 1) {
                                    break;
                                }
                                else {
                                    ++j;
                                }
                            }
                        }
                        if (data.getStates() == 0) {
                            break;
                        }
                        else {
                            continue;
                        }
                    }
                }
            }
            return;
        }
    }
}
