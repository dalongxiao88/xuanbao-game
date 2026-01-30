package come.tool.FightingDataAction;

import come.tool.FightingData.AddState;
import come.tool.FightingData.ChangeFighting;
import java.util.List;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.MixDeal;
import java.util.ArrayList;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public class Fabao implements DataAction
{
    public static boolean isJGSB;
    
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        FightingSkill fightingSkill = manData.skillname(fightingEvents.getOriginator().getEndState());
        if (fightingSkill == null) {
            return;
        }
        String skilltype = fightingSkill.getSkilltype();
        if (skilltype.equals(TypeUtil.FB_HD)) {
            DataActionType.getActionById(23).analysisAction(manData, fightingEvents, type, battlefield);
            return;
        }
        List<FightingState> Accepterlist = new ArrayList<>();
        List<ManData> datas = MixDeal.getjieshou(fightingEvents, fightingSkill, manData, Accepterlist, battlefield);
        FightingState Originator = fightingEvents.getOriginator();
        Originator.setStartState("法术攻击");
        Originator.setSkillsy(fightingSkill.getSkillname());
        for (int i = 0; i < datas.size(); ++i) {
            ManData data = (ManData)datas.get(i);
            FightingState Accepter = new FightingState();
            if (data.getCamp() == manData.getCamp() && manData.getMan() == data.getMan()) {
                Accepter = Originator;
            }
            Accepter.setCamp(data.getCamp());
            Accepter.setMan(data.getMan());
            if (skilltype.equals(TypeUtil.FB_BGZ)) {
                this.bgz(Accepterlist, Accepter, manData, data, fightingSkill, battlefield);
            }
            else if (skilltype.equals(TypeUtil.FB_JGE) || skilltype.equals(TypeUtil.FB_QW)) {
                this.kzfb(Accepterlist, Accepter, manData, data, fightingSkill, battlefield);
            }
            else if (skilltype.equals(TypeUtil.FB_BLD) || skilltype.equals(TypeUtil.FB_DSY) || skilltype.equals(TypeUtil.FB_FTY)) {
                this.jzt(Accepterlist, Accepter, data, fightingSkill, battlefield);
            }
            else {
                if (manData.getSkillType("8071") != null && (fightingSkill.getSkillname().equals("幽冥鬼手") || fightingSkill.getSkillname().equals("大势锤") || fightingSkill.getSkillname().equals("绝情鞭")) && data == manData) {
                    fightingSkill.setSkillcontinued(fightingSkill.getSkillcontinued() + 2);
                }
                state(Accepterlist, Accepter, data, fightingSkill, battlefield);
            }
            Accepter.setStartState(TypeUtil.JN);
            if (data.getCamp() == manData.getCamp() && manData.getMan() == data.getMan()) {
                Originator.setStartState("法术攻击");
                fightingEvents.setOriginator(null);
                if (fightingSkill.getSkillid() == 5007 || fightingSkill.getSkillid() == 5010 || fightingSkill.getSkillid() == 5012 || fightingSkill.getSkillid() == 5011) {
                    Originator.setSkillskin(fightingSkill.getSkillid() + "");
                }
            }
            else if (fightingSkill.getSkillid() == 5007 || fightingSkill.getSkillid() == 5010 || fightingSkill.getSkillid() == 5012 || fightingSkill.getSkillid() == 5011) {
                Accepter.setSkillskin(fightingSkill.getSkillid() + "");
            }
            if (data.getCamp() != manData.getCamp() || manData.getMan() != data.getMan()) {
                Accepterlist.add(Accepter);
            }
        }
        if (manData.daijia(fightingSkill, Originator, battlefield)) {
            return;
        }
        if (fightingEvents.getOriginator() != null) {
            Accepterlist.add(Originator);
        }
        fightingEvents.setOriginator(null);
        if (Accepterlist.size() != 0) {
            fightingEvents.setAccepterlist(Accepterlist);
            battlefield.NewEvents.add(fightingEvents);
        }
    }
    
    public void bgz(List<FightingState> Accepterlist, FightingState fightingState, ManData myData, ManData nomyData, FightingSkill skill, Battlefield battlefield) {
        double jichu = skill.getSkillhurt();
        if ((double)Battlefield.random.nextInt(100) < jichu) {
            ChangeFighting changeFighting = new ChangeFighting();
            changeFighting.setChangetype(skill.getSkilltype());
            changeFighting.setChangevlaue(skill.getSkillgain());
            changeFighting.setChangesum(skill.getSkillcontinued());
            nomyData.ChangeData(changeFighting, fightingState);
            Accepterlist.add(fightingState);
        }
    }
    
    public void kzfb(List<FightingState> Accepterlist, FightingState fightingState, ManData myData, ManData nomyData, FightingSkill skill, Battlefield battlefield) {
        double jichu = skill.getSkillhurt();
        FightingSkill skill2 = nomyData.getAppendSkill(9250);
        if (skill2 != null) {
            jichu -= skill2.getSkillhurt() / 6.0;
        }
        if (skill.getSkilltype().equals(TypeUtil.FB_QW)) {
            skill2 = nomyData.getAppendSkill(9205);
            if (skill2 != null) {
                jichu -= skill2.getSkillhurt();
            }
            jichu -= myData.getQuality().getK_qw();
        }
        else if (skill.getSkilltype().equals(TypeUtil.FB_JGE)) {
            jichu -= myData.getQuality().getK_jge();
        }
        double xs = 1.0 + (double)(myData.getlvl() - nomyData.getlvl()) / 150.0;
        jichu *= xs;
        AddState xzstate = nomyData.xzstate(TypeUtil.TY_SSC_LFHX);
        if ((double)Battlefield.random.nextInt(100) < jichu) {
            Fabao.isJGSB = false;
            state(Accepterlist, fightingState, nomyData, skill, battlefield);
        }
        else if (myData.getSkillType("8070") != null && Battlefield.random.nextInt(100) > 50) {
            Fabao.isJGSB = true;
        }
    }
    
    public void jzt(List<FightingState> Accepterlist, FightingState fightingState, ManData nomyData, FightingSkill skill, Battlefield battlefield) {
        if ((double)Battlefield.random.nextInt(100) < skill.getSkillhurt()) {
            String type = "混乱";
            if (skill.getSkilltype().equals(TypeUtil.FB_DSY)) {
                type = "封印";
            }
            else if (skill.getSkilltype().equals(TypeUtil.FB_FTY)) {
                type = "遗忘";
            }
            nomyData.RemoveAbnormal(new String[] { type });
            fightingState.setEndState_2(type);
            Accepterlist.add(fightingState);
        }
    }
    
    public static void state(List<FightingState> Accepterlist, FightingState fightingState, ManData nomyData, FightingSkill skill, Battlefield battlefield) {
        ChangeFighting changeFighting = new ChangeFighting();
        changeFighting.setChangetype(skill.getSkilltype());
        changeFighting.setChangevlaue(skill.getSkillhurt());
        changeFighting.setChangesum(skill.getSkillcontinued());
        nomyData.ChangeData(changeFighting, fightingState);
        Accepterlist.add(fightingState);
    }
}
