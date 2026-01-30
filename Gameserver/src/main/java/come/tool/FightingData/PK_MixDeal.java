package come.tool.FightingData;

import java.util.List;
import java.util.ArrayList;

public class PK_MixDeal
{
    public static boolean isPK(int type) {
        return (type >= 5 && type <= 14) || type == 21 || (type >= 31 && type <= 33) || type == 101 || type == 102|| type == 104;
    }
    
    public static boolean isBB(int type) {
        return type == 34;
    }
    
    public static boolean isArena(int type) {
        return type == 101;
    }
    
    public static boolean isPal(int type) {
        return !isBB(type) && type != 4 && type != 101 && type != 102 && type != 31 && !isPK(type);
    }
    
    public static void PK_ST(Battlefield battle) {
        if (!isPK(battle.BattleType)) {
            return;
        }
        GroupBuff buff = battle.getBuff(1, TypeUtil.BB_SGQX);
        if (buff != null) {
            PK_SG(buff, battle);
            return;
        }
        buff = battle.getBuff(1, TypeUtil.BB_TM);
        if (buff != null) {
            PK_TM(buff, battle);
            return;
        }
    }
    
    public static void PK_SG(GroupBuff buff, Battlefield battle) {
        List<FightingState> Accepterlist = new ArrayList<>();
        for (int i = battle.Events.size() - 1; i >= 0; --i) {
            FightingState state = ((FightingEvents)battle.Events.get(i)).getOriginator();
            if (state.getStartState().equals(TypeUtil.JN)) {
                int path = battle.Datapathhuo(state.getCamp(), state.getMan());
                if (path != -1) {
                    ManData data = (ManData)battle.fightingdata.get(path);
                    FightingState org = new FightingState();
                    data.getFightingState(org);
                    org.setHp_Change(-20000);
                    data.setHp(data.getHp() - 20000);
                    if (data.getHp() <= 0) {
                        data.setHp(0);
                        data.setStates(1);
                        MixDeal.DeathSkill(data, org, battle);
                    }
                    Accepterlist.add(org);
                }
            }
        }
        if (Accepterlist.size() != 0) {
            FightingEvents fightingEvents = new FightingEvents();
            fightingEvents.setAccepterlist(Accepterlist);
            battle.NewEvents.add(fightingEvents);
        }
    }
    
    public static void PK_TM(GroupBuff buff, Battlefield battle) {
        List<ManData> datas = new ArrayList<>();
        for (int i = battle.fightingdata.size() - 1; i >= 0; --i) {
            ManData data = (ManData)battle.fightingdata.get(i);
            if (data.getType() == 1 && data.getStates() == 0) {
                datas.add(data);
            }
        }
        if (datas.size() != 0) {
            List<FightingState> Accepterlist = new ArrayList<>();
            for (int j = datas.size() - 1; j >= 0; --j) {
                ManData data2 = (ManData)datas.get(j);
                ManData jz = battle.getPetParents(data2);
                int hurt = Battlefield.random.nextInt(15000) + 20000;
                FightingSkill skill2 = data2.skillId(9221);
                if (skill2 != null) {
                    hurt = (int)((double)hurt * (100.0 - skill2.getSkillhurt()) / 100.0);
                }
                if (jz != null && jz.getStates() == 0) {
                    FightingState og = new FightingState();
                    jz.getFightingState(og);
                    if (jz.getHp() >= hurt) {
                        og.setHp_Change(-hurt);
                        jz.setHp(jz.getHp() - hurt);
                        hurt = 0;
                        if (jz.getHp() <= 0) {
                            jz.setStates(1);
                        }
                    }
                    else {
                        og.setHp_Change(-jz.getHp());
                        hurt -= jz.getHp();
                        jz.setHp(0);
                        jz.setStates(1);
                    }
                    if (jz.getStates() == 1) {
                        MixDeal.DeathSkill(jz, og, battle);
                    }
                    Accepterlist.add(og);
                }
                if (hurt >= 0) {
                    FightingState og = new FightingState();
                    data2.getFightingState(og);
                    og.setHp_Change(-hurt);
                    data2.setHp(data2.getHp() - hurt);
                    if (data2.getHp() <= 0) {
                        data2.setHp(0);
                        data2.setStates(1);
                        MixDeal.DeathSkill(data2, og, battle);
                    }
                    Accepterlist.add(og);
                }
            }
            FightingEvents fightingEvents = new FightingEvents();
            fightingEvents.setAccepterlist(Accepterlist);
            battle.NewEvents.add(fightingEvents);
        }
    }
    
    public static void PK_YX(ManData data, FightingSkill skill, Battlefield battle) {
        int nocamp = battle.nomy(data.getCamp());
        List<GroupBuff> buffs = null;
        GroupBuff buff = battle.getBuff(nocamp, TypeUtil.BB_YH);
        if (buff != null) {
            if (buffs == null) {
                buffs = new ArrayList<>();
            }
            buffs.add(buff);
        }
        if (data.getType() == 0 && skill.getSkillid() > 1000 && skill.getSkillid() < 1100) {
            buff = battle.getBuff(nocamp, TypeUtil.BB_XR);
            if (buff != null) {
                if (buffs == null) {
                    buffs = new ArrayList<>();
                }
                buffs.add(buff);
            }
        }
        if (buffs == null) {
            return;
        }
        FightingState og = new FightingState();
        data.getFightingState(og);
        for (int i = 0; i < buffs.size(); ++i) {
            buff = (GroupBuff)buffs.get(i);
            battle.buffs.remove(buff);
            if (buff.getBuffType().equals(TypeUtil.BB_YH)) {
                int hurt = (int)((double)skill.getSkillblue() * buff.getValue() / 100.0);
                if (data.getMp() < hurt) {
                    hurt = data.getMp();
                }
                og.setMp_Change(-hurt);
                data.setMp(data.getMp() - hurt);
            }
            else {
                int hurt = (int)((double)skill.getSkillblue() * buff.getValue() / 100.0);
                FightingSkill skill2 = data.skillId(9221);
                if (skill2 != null) {
                    hurt = (int)((double)hurt * (100.0 - skill2.getSkillhurt()) / 100.0);
                }
                og.setHp_Change(-hurt);
                data.setHp(data.getHp() - hurt);
            }
        }
        og.setBuff(MixDeal.getBuffStr(buffs, false));
        if (data.getHp() <= 0) {
            data.setHp(0);
            data.setStates(1);
            MixDeal.DeathSkill(data, og, battle);
        }
        FightingEvents hhe = new FightingEvents();
        List<FightingState> hhs = new ArrayList<>();
        hhs.add(og);
        hhe.setAccepterlist(hhs);
        battle.NewEvents.add(hhe);
    }
    
    public static void PK_MSKK(ManData data, FightingSkill skill, Battlefield battle) {
        int nocamp = battle.nomy(data.getCamp());
        List<GroupBuff> buffs = null;
        GroupBuff buff = battle.getBuff(nocamp, "妙手空空");
        if (buff != null) {
            battle.buffs.remove(buff);
            List<FightingState> hhs = new ArrayList<>();
            if (buff.getData().getStates() == 0) {
                if (buff.getData().getYuanzhu() < 500.0) {
                    return;
                }
                buff.getData().getSkills().add(skill);
                FightingState hhze = new FightingState();
                hhze.setCamp(buff.getData().getCamp());
                hhze.setMan(buff.getData().getMan());
                hhze.setStartState(TypeUtil.JN);
                ChangeFighting changeFighting = new ChangeFighting();
                changeFighting.setChangetype("隐身");
                changeFighting.setChangesum(2);
                buff.getData().ChangeData(changeFighting, hhze);
                hhze.setText("妙手空空#2");
                hhze.setSkillskin("1249");
                hhs.add(hhze);
            }
            FightingState fs = new FightingState();
            fs.setCamp(data.getCamp());
            fs.setMan(data.getMan());
            fs.setStartState(TypeUtil.JN);
            hhs.add(fs);
            FightingEvents hhe = new FightingEvents();
            hhe.setAccepterlist(hhs);
            battle.NewEvents.add(hhe);
        }
    }
    
    public static boolean PK_HW(ManData data, FightingSkill skill, Battlefield battle) {
        if (skill.getSkillid() >= 5001 && skill.getSkillid() <= 5015) {
            return false;
        }
        if (skill.getSkilltype().equals("魔界内丹")) {
            return false;
        }
        if (skill.getSkillid() == 22006 || skill.getSkillid() == 3035 || skill.getSkillid() == 22000 || skill.getSkillid() == 22008 || skill.getSkillid() == 22012 || skill.getSkillid() == 22026 || skill.getSkillid() == 22032 || skill.getSkillid() == 22034 || skill.getSkillid() == 9389 || skill.getSkillid() == 9110 || skill.getSkillid() == 9111 || skill.getSkillid() == 11024 || skill.getSkillid() == 23009 || skill.getSkillid() == 11036) {
            return false;
        }
        int nocamp = battle.nomy(data.getCamp());
        GroupBuff buff = battle.getBuff(nocamp, TypeUtil.BB_HW);
        if (buff != null) {
            battle.buffs.remove(buff);
            List<FightingState> hhs = new ArrayList<>();
            battle.systemMsg(data, hhs, 8, skill);
            if (buff.getData().getStates() == 0) {
                FightingState hhze = new FightingState();
                hhze.setCamp(buff.getData().getCamp());
                hhze.setMan(buff.getData().getMan());
                hhze.setStartState(TypeUtil.JN);
                hhze.setSkillskin("1828");
                hhs.add(hhze);
            }
            FightingState fs = new FightingState();
            fs.setCamp(data.getCamp());
            fs.setMan(data.getMan());
            fs.setStartState(TypeUtil.JN);
            List<GroupBuff> buffs = new ArrayList<>();
            buffs.add(buff);
            fs.setBuff(MixDeal.getBuffStr(buffs, false));
            hhs.add(fs);
            FightingEvents hhe = new FightingEvents();
            hhe.setAccepterlist(hhs);
            battle.NewEvents.add(hhe);
            return true;
        }
        else {
            return false;
        }
    }
}
