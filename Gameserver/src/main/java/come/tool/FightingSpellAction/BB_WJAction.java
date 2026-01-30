package come.tool.FightingSpellAction;

import java.util.List;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingDataAction.PhyAttack;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.TypeUtil;
import come.tool.FightingData.MixDeal;
import java.util.ArrayList;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public class BB_WJAction implements SpellAction
{
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        FightingState org = new FightingState();
        org.setEndState(skill.getSkillname());
        if (manData.daijia(org, battlefield)) {
            return;
        }
        org.setCamp(manData.getCamp());
        org.setMan(manData.getMan());
        org.setStartState("药");
        FightingEvents fightingEvents = new FightingEvents();
        List<FightingState> States = new ArrayList<>();
        States.add(org);
        fightingEvents.setAccepterlist(States);
        battlefield.NewEvents.add(fightingEvents);
        long Hurt = (long)(skill.getSkillhurt() / 100.0 * (double)manData.getAp());
        int Maxsize = (int)skill.getSkillgain();
        int g = 0;
        int maxf = 0;
        int f = 0;
        ManData nomyData = null;
        List<ManData> guiwei = new ArrayList<>();
        List<ManData> nodatas = TY_LL_JCYYAction.getdaji(manData.getCamp(), events, battlefield);
        for (int i = 0; i < Maxsize; ++i) {
            if (nodatas.size() == 0) {
                return;
            }
            nomyData = null;
            if (i == 0) {
                nomyData = (ManData)nodatas.remove(0);
            }
            else {
                nomyData = (ManData)nodatas.remove(Battlefield.random.nextInt(nodatas.size()));
            }
            if (nomyData.getStates() == 0) {
                guiwei.clear();
                MixDeal.move(manData.getCamp(), manData.getMan(), "移动", nomyData.getCamp() + "|" + nomyData.getMan() + "|3", battlefield);
                long hurt = Hurt;
                FightingEvents gjEvents = new FightingEvents();
                List<FightingState> zls = new ArrayList<>();
                FightingState ace = new FightingState();
                FightingState bao = new FightingState();
                if ((double)(Battlefield.random.nextInt(100) + 1) > nomyData.getsx(4, TypeUtil.SX_SBL) - manData.getQuality().getRolefmzl() - manData.mz) {
                    ChangeFighting acec = new ChangeFighting();
                    hurt = (long)PhyAttack.Hurt(hurt, 1, manData, nomyData, TypeUtil.PTGJ, ace, zls, battlefield, 0.0, 0.0);
                    ManData baodata = battlefield.bhpd(nomyData.getCamp(), nomyData.getMan());
                    if (baodata == null) {
                        acec.setChangehp((int)(-hurt));
                        FightingPackage.ChangeProcess(acec, manData, nomyData, ace, TypeUtil.PTGJ, zls, battlefield);
                    }
                    else {
                        ChangeFighting baoc = new ChangeFighting();
                        baoc.setChangehp(-(int)((double)hurt * 0.7));
                        acec.setChangehp(-(int)((double)hurt * 0.4));
                        FightingPackage.ChangeProcess(acec, manData, nomyData, ace, TypeUtil.PTGJ, zls, battlefield);
                        FightingPackage.ChangeProcess(baoc, manData, baodata, bao, TypeUtil.PTGJ, zls, battlefield);
                        ace.setStartState("瞬移");
                        ace.setEndState(nomyData.getCamp() + "|" + nomyData.getMan() + "|" + PhyAttack.getdir(3));
                        bao.setStartState("瞬移");
                        bao.setEndState(nomyData.getCamp() + "|" + nomyData.getMan());
                        guiwei.add(baodata);
                        guiwei.add(nomyData);
                    }
                    PhyAttack.neidan(TypeUtil.PTGJ, manData, nomyData, hurt, battlefield, zls, g, 1, 0.0);
                }
                else {
                    ace.setCamp(nomyData.getCamp());
                    ace.setMan(nomyData.getMan());
                    ace.setStartState("瞬移");
                    ace.setProcessState("躲闪");
                    ace.setEndState(nomyData.getCamp() + "|" + nomyData.getMan() + "|" + PhyAttack.getdir(3));
                    zls.add(ace);
                    guiwei.add(nomyData);
                }
                FightingState gjz = new FightingState();
                gjz.setCamp(manData.getCamp());
                gjz.setMan(manData.getMan());
                gjz.setSkillsy("attack");
                gjz.setStartState(TypeUtil.PTGJ);
                gjz.setEndState("3");
                zls.add(gjz);
                gjEvents.setAccepterlist(zls);
                battlefield.NewEvents.add(gjEvents);
                if (guiwei.size() != 0) {
                    FightingEvents moveEvents = new FightingEvents();
                    List<FightingState> moves = new ArrayList<>();
                    for (int j = 0; j < guiwei.size(); ++j) {
                        FightingState move = new FightingState();
                        move.setCamp(((ManData)guiwei.get(j)).getCamp());
                        move.setMan(((ManData)guiwei.get(j)).getMan());
                        move.setStartState("瞬移");
                        move.setEndState(((ManData)guiwei.get(j)).getCamp() + "|" + ((ManData)guiwei.get(j)).getMan());
                        moves.add(move);
                        moveEvents.setAccepterlist(moves);
                    }
                    moveEvents.setAccepterlist(moves);
                    battlefield.NewEvents.add(moveEvents);
                }
                if (guiwei.size() == 0 && nomyData.getStates() == 0 && manData.getStates() == 0) {
                    if (maxf == 0) {
                        maxf = PhyAttack.AttackSum(nomyData.getQuality().getRoleffjl() - manData.getQuality().getRolehsfj(), nomyData.getQuality().getRoleffjv());
                    }
                    if (f < maxf) {
                        ++f;
                        FightingEvents fjEvents = new FightingEvents();
                        List<FightingState> fjs = new ArrayList<>();
                        FightingState fj = new FightingState();
                        ChangeFighting fjc = new ChangeFighting();
                        hurt = (long)PhyAttack.Hurt((long)nomyData.getAp(), 1, nomyData, manData, "反击", fj, zls, battlefield, 0.0, 0.0);
                        fjc.setChangehp((int)(-hurt));
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
                if (manData.getStates() == 0) {
                    MixDeal.move(manData.getCamp(), manData.getMan(), "移动", manData.getCamp() + "|" + manData.getMan(), battlefield);
                }
                else {
                    MixDeal.move(manData.getCamp(), manData.getMan(), "瞬移", manData.getCamp() + "|" + manData.getMan(), battlefield);
                }
            }
        }
    }
}
