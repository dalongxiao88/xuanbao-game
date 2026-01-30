package come.tool.FightingSpellAction;

import java.util.List;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.ChangeFighting;
import java.util.ArrayList;
import come.tool.FightingData.SummonType;
import come.tool.FightingData.PK_MixDeal;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public class TY_MYAction implements SpellAction
{
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        if (skill.getSkillid() == 9262 && PK_MixDeal.isPK(battlefield.BattleType)) {
            return;
        }
        if (skill.getSkillid() == 9262 || skill.getSkillid() == 9389) {
            SummonType.xianzhi(manData, skill);
        }
        FightingState Originator = events.getOriginator();
        events.setOriginator(null);
        if (manData.daijia(skill, Originator, battlefield)) {
            return;
        }
        List<FightingState> Accepterlist = new ArrayList<>();
        Originator.setCamp(manData.getCamp());
        Originator.setMan(manData.getMan());
        ChangeFighting changeFighting = new ChangeFighting();
        if (skill.getSkillid() == 9389) {
            if (skill.getSkillhurt() >= 2.0) {
                changeFighting.setChangehp((int)((double)manData.getHp_z() * 0.6));
                changeFighting.setChangemp((int)((double)manData.getMp_z() * 0.6));
            }
            changeFighting.setChangetype(TypeUtil.TY_SSC_LFHX);
            changeFighting.setChangevlaue(skill.getSkillhurt());
            changeFighting.setChangevlaue2(skill.getSkillhurt());
            Originator.setSkillskin(skill.getSkilltype());
        }
        else if (skill.getSkillid() == 9262) {
            changeFighting.setChangetype(TypeUtil.TY_L_XSNJ);
            changeFighting.setChangevlaue((double)manData.getMp_z() * skill.getSkillhurt() / 100.0);
            changeFighting.setChangevlaue2(changeFighting.getChangevlaue());
            changeFighting.setSkills(ControlAction.getSkills(manData, skill, battlefield.BattleType));
        }
        else if (skill.getSkillid() == 9111) {
            double xssp = skill.getSkillhurt();
            FightingSkill TY_R_SCRX = manData.getSkillType(TypeUtil.TY_R_LRBS);
            if (TY_R_SCRX != null) {
                xssp = skill.getSkillhurt() * TY_R_SCRX.getSkillhurt() / 100.0;
            }
            manData.addAddState(TypeUtil.TY_FY_ARXH, xssp, 0.0, 2);
        }
        FightingPackage.ChangeProcess(changeFighting, null, manData, Originator, TypeUtil.JN, Accepterlist, battlefield);
        Originator.setStartState("法术攻击");
        FightingEvents ksevents = new FightingEvents();
        ksevents.setCurrentRound(battlefield.CurrentRound);
        ksevents.setAccepterlist(Accepterlist);
        battlefield.NewEvents.add(ksevents);
    }
}
