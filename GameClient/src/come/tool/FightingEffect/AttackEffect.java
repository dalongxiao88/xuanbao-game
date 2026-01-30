package come.tool.FightingEffect;

import com.tool.tcp.GetTcpPath;
import come.tool.Fighting.SkillSpell;
import come.tool.Fighting.FightingMixDeal;
import come.tool.Fighting.Fightingimage;
import come.tool.Fighting.StateProgress;
import come.tool.Fighting.FightingState;
import org.come.until.ScrenceUntil;

public class AttackEffect implements Effect
{
    @Override
    public StateProgress analysisAction(FightingState State, int path) {
        StateProgress progress = EffectType.getProgress(State, path);
        progress.setMan(path);
        Fightingimage fightingimage = (Fightingimage)FightingMixDeal.CurrentData.get(path);
        if (State.getStartState().equals("普通攻击")) {
            int dir = Integer.parseInt(State.getEndState());
            dir = EffectType.getdir(dir, (State.getCamp() == FightingMixDeal.camp) ? FightingMixDeal.camp : State.getCamp());
            progress.setDir(dir);
            progress.setDirend(dir);
            progress.setType(9);
        }
        else if (State.getStartState().equals("法术攻击")) {
            progress.setType(5);
        }
        else if (State.getStartState().equals("防御")) {
            progress.setType(6);
        }
        else if (State.getStartState().equals("被攻击")) {
            progress.setType(4);
        }
        else if (State.getStartState().equals("技能")) {
            if (State.getSkillskin() != null && State.getSkillskin().equals("80000")) {
                progress.setMusic("药品");//yinyue
                SkillSpell skillSpell = new SkillSpell();
                int x = -1,y=-1;
                String sucai = "80000";
                if (State.getCamp() == FightingMixDeal.camp) {
                    x= ScrenceUntil.Screen_x / 9 * 5;
                    y= ScrenceUntil.Screen_y/2;
                    sucai="80000";//右
                }
                else {
                    x= ScrenceUntil.Screen_x / 9 * 4;
                    y= ScrenceUntil.Screen_y/2;
                    sucai="80000";
                }
                skillSpell = new SkillSpell(GetTcpPath.getSkillTcp(sucai), x, y);
                progress.setSpell(skillSpell);
            }else if (State.getSkillskin() != null && State.getSkillskin().equals("18499")) {
//                progress.setMusic("药品");//yinyue
                SkillSpell skillSpell = new SkillSpell();
                String sucai = "1849";
                skillSpell = new SkillSpell(GetTcpPath.getSkillTcp(sucai), fightingimage.getX(), fightingimage.getY());
                progress.setSpell(skillSpell);
            }
            else {
                if (progress.getHp_Change() < 0) {
                    progress.setType(4);
                } else {
                    progress.setType(7);
                }
            }
        }
        else if (State.getStartState().equals("药")) {
            if (progress.getHp_Change() > 0 || progress.getMp_Change() > 0) {
                progress.setMusic("药品");
                SkillSpell skillSpell = new SkillSpell();
                skillSpell = new SkillSpell((progress.getHp_Change() > 0) ? GetTcpPath.getSkillTcp("加血") : GetTcpPath.getSkillTcp("加蓝"), fightingimage.getX(), fightingimage.getY());
                progress.setSpell(skillSpell);
            }
            progress.setType(7);
        }
        else if (State.getStartState().equals("代价")) {
            progress.setType(0);
        }
        else if (State.getStartState().equals("限制刷新")) {
            progress.setType(0);
            fightingimage.getFightingManData().setState_1(State.getEndState());
            if (fightingimage.getFightingManData().getCamp() == FightingMixDeal.camp && fightingimage.getFightingManData().getMan() == FightingMixDeal.myman()) {
                FightingMixDeal.buffUtil.SXSkill(fightingimage.getFightingManData().cxxx("技能"));
            }
        }
        else if (State.getStartState().equals("特效1")) {
            progress.setType(3);
        }
        else if (State.getStartState().equals("特效2")) {
            progress.setType(11);
        }
        else if (State.getStartState().equals("特效3")) {
            progress.setType(12);
        }
        else if (State.getStartState().startsWith("特效4")) {
            progress.setType(11);
            String[] vals = State.getStartState().split("\\|");
            int dir2 = Integer.parseInt(vals[1]);
            dir2 = EffectType.getdir(dir2, (State.getCamp() == FightingMixDeal.camp) ? FightingMixDeal.camp : State.getCamp());
            progress.setDir(dir2);
        }
        return progress;
    }
}
