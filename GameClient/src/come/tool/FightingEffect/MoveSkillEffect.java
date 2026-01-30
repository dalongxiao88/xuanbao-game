package come.tool.FightingEffect;

import com.tool.tcp.NewPart;
import java.util.List;
import come.tool.Fighting.ShadowMode;
import com.tool.tcp.SpriteFactory;
import come.tool.Fighting.FightingMove2;
import org.come.bean.PathPoint;
import java.util.ArrayList;
import come.tool.Fighting.SkillSpell;
import come.tool.Fighting.FightingMixDeal;
import come.tool.Fighting.Fightingimage;
import come.tool.Fighting.StateProgress;
import come.tool.Fighting.FightingState;

public class MoveSkillEffect implements Effect
{
    @Override
    public StateProgress analysisAction(FightingState State, int path) {
        Fightingimage fightingimage = (Fightingimage)FightingMixDeal.CurrentData.get(path);
        StateProgress progress = EffectType.getProgress(State, path);
        SkillSpell skillSpell = new SkillSpell();
        if (State.getSkillskin().equals("4000") || State.getSkillskin().equals("1298") || State.getSkillskin().equals("10")) {
            PathPoint pathPoint = State.getSkillskin().equals("1298") ? MoveEffect.move1(State.getEndState()) : MoveEffect.move(State.getEndState());
            List<PathPoint> lists = new ArrayList<>();
            int x = fightingimage.getX();
            int y = fightingimage.getY();
            if (State.getSkillskin().equals("1298")) {
                String[] vs = State.getEndState().split("\\|");
                int dir = Integer.parseInt(vs[2]);
                switch (dir) {
                    case 1: {
                        x += 0;
                        y += 0;
                        break;
                    }
                    case 2: {
                        x -= 130;
                        y -= 70;
                        break;
                    }
                    case 3: {
                        x -= 60;
                        y -= 90;
                        break;
                    }
                    case 5: {
                        x += 0;
                        y += 0;
                        break;
                    }
                    case 6: {
                        x += 130;
                        y += 70;
                        break;
                    }
                    case 7: {
                        x += 60;
                        y += 90;
                        break;
                    }
                }
            }
            lists.add(new PathPoint(x, y));
            lists.add(pathPoint);
            int dir2 = FightingMove2.getdir(pathPoint.getX() - fightingimage.getX(), pathPoint.getY() - fightingimage.getY(), 0);
            NewPart part = null;
            if (State.getSkillskin().equals("4000")) {
                part = SpriteFactory.createPart(State.getSkillskin(), -1, 0, null);
            }
            else if (State.getSkillskin().equals("1298")) {
                part = SpriteFactory.createPart(State.getSkillskin(), -1, 0, null);
            }
            else {
                part = fightingimage.getPart().clonePart();
                part.setAct(Integer.parseInt(State.getSkillskin()));
            }
            ShadowMode shadowMode = new ShadowMode(part, x, y, lists, dir2);
            skillSpell.setShadowMode(shadowMode);
        }
        else {
            String[] vs2 = State.getEndState().split("\\|");
            int path2 = FightingMixDeal.CurrentData(Integer.parseInt(vs2[0]), Integer.parseInt(vs2[1]));
            Fightingimage image = (Fightingimage)FightingMixDeal.CurrentData.get(path2);
            int dir3 = EffectType.getdir(3, image.getFightingManData().getCamp());
            NewPart part2 = fightingimage.getPart().clonePart();
            part2.setAct(Integer.parseInt(State.getSkillskin()));
            if (vs2.length == 2) {
                ShadowMode shadowMode2 = new ShadowMode(part2, image.getX(), image.getY(), null, (dir3 == 3) ? 1 : 0);
                skillSpell.setShadowMode(shadowMode2);
            }
            else {
                ShadowMode shadowMode2 = new ShadowMode(part2, (dir3 == 3) ? (image.getX() + 70) : (image.getX() - 70), (dir3 == 3) ? (image.getY() + 30) : (image.getY() - 30), null, (dir3 == 3) ? 1 : 0);
                skillSpell.setShadowMode(shadowMode2);
            }
        }
        progress.setSpell(skillSpell);
        progress.setMan(path);
        progress.setType(0);
        return progress;
    }
}
