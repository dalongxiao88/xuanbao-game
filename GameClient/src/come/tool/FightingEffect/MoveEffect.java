package come.tool.FightingEffect;

import org.come.until.ScrenceUntil;
import java.util.List;
import org.come.bean.PathPoint;
import come.tool.Fighting.FightingMixDeal;
import come.tool.Fighting.Fightingimage;
import java.util.ArrayList;
import come.tool.Fighting.StateProgress;
import come.tool.Fighting.FightingState;

public class MoveEffect implements Effect
{
    @Override
    public StateProgress analysisAction(FightingState State, int path) {
        StateProgress progress = EffectType.getProgress(State, path);
        List<PathPoint> points = new ArrayList<>();
        if (!State.getStartState().equals("瞬移")) {
            points.add(new PathPoint(((Fightingimage)FightingMixDeal.CurrentData.get(path)).getX(), ((Fightingimage)FightingMixDeal.CurrentData.get(path)).getY()));
        }
        points.add(move(State.getEndState()));
        progress.setPath(points);
        progress.setMan(path);
        progress.setType(10);
        return progress;
    }
    
    public static PathPoint move(String v) {
        String[] vs = v.split("\\|");
        int camp = Integer.parseInt(vs[0]);
        int man = Integer.parseInt(vs[1]);
        PathPoint point = Position(camp, FightingMixDeal.camp, man);
        if (vs.length == 3 || vs.length == 4) {
            int dir = Integer.parseInt(vs[2]);
            dir = EffectType.getdir(dir, camp);
            switch (dir) {
                case 1: {
                    point.setX(point.getX() + 70);
                    point.setY(point.getY() - 40);
                    break;
                }
                case 3: {
                    point.setX(point.getX() + 70);
                    point.setY(point.getY() + 30);
                    break;
                }
                case 5: {
                    point.setX(point.getX() - 70);
                    point.setY(point.getY() + 40);
                    break;
                }
                default: {
                    point.setX(point.getX() - 70);
                    point.setY(point.getY() - 30);
                    break;
                }
            }
        }
        if (vs.length == 4) {
            int tmpX = 0;
            int tmpY = 0;
            if (point.getX() > ScrenceUntil.Screen_x / 2) {
                tmpX = 150;
                tmpY = 15;
            }
            else {
                tmpX = -15;
                tmpY = 15;
            }
            point.setX(point.getX() + tmpX);
            point.setY(point.getY() + tmpY);
        }
        else if (vs.length == 2 && "-1".equals(vs[0]) && "-1".equals(vs[1])) {
            point.setX(ScrenceUntil.Screen_x / 2);
            point.setY(ScrenceUntil.Screen_y / 2);
        }
        return point;
    }
    
    public static PathPoint move1(String v) {
        String[] vs = v.split("\\|");
        int camp = Integer.parseInt(vs[0]);
        int man = Integer.parseInt(vs[1]);
        PathPoint point = Position(camp, FightingMixDeal.camp, man);
        if (vs.length == 4) {
            int tmpX = 0;
            int tmpY = 0;
            if (point.getX() > ScrenceUntil.Screen_x / 2) {
                tmpX = 150;
                tmpY = 15;
            }
            else {
                tmpX = -15;
                tmpY = 15;
            }
            point.setX(point.getX() + tmpX);
            point.setY(point.getY() + tmpY);
        }
        else if (vs.length == 2 && "-1".equals(vs[0]) && "-1".equals(vs[1])) {
            point.setX(ScrenceUntil.Screen_x / 2);
            point.setY(ScrenceUntil.Screen_y / 2);
        }
        return point;
    }
    
    public static PathPoint Position(int Camp, int my_Camp, int man) {
        if (ScrenceUntil.Screen_x > 800) {
            if ((Camp == 1 && my_Camp == -1) || Camp == my_Camp) {
                if (man < 5) {
                    return new PathPoint(ScrenceUntil.Screen_x - 140 - 55 * man, ScrenceUntil.Screen_y - 505 + 95 * man);
                }
                man -= 5;
                return new PathPoint(ScrenceUntil.Screen_x - 265 - 55 * man, ScrenceUntil.Screen_y - 540 + 95 * man);
            }
            else {
                if (man < 5) {
                    return new PathPoint(300 - 55 * man, 150 + 95 * man);
                }
                man -= 5;
                return new PathPoint(425 - 55 * man, 170 + 95 * man);
            }
        }
        else if ((Camp == 1 && my_Camp == -1) || Camp == my_Camp) {
            if (man < 5) {
                return new PathPoint(ScrenceUntil.Screen_x - 70 - 65 * man, ScrenceUntil.Screen_y - 325 + 65 * man);
            }
            man -= 5;
            return new PathPoint(ScrenceUntil.Screen_x - 165 - 65 * man, ScrenceUntil.Screen_y - 390 + 65 * man);
        }
        else {
            if (man < 5) {
                return new PathPoint(330 - 65 * man, 150 + 65 * man);
            }
            man -= 5;
            return new PathPoint(425 - 65 * man, 215 + 65 * man);
        }
    }
    
    public static PathPoint Position1(int Camp, int man, int dir) {
        if (ScrenceUntil.Screen_x > 800) {
            if ((Camp == 1 && FightingMixDeal.camp == -1) || Camp == FightingMixDeal.camp) {
                if (man < 5) {
                    return new PathPoint(ScrenceUntil.Screen_x - 140 - 55 * man, ScrenceUntil.Screen_y - 505 + 95 * man);
                }
                man -= 5;
                return new PathPoint(ScrenceUntil.Screen_x - 265 - 55 * man, ScrenceUntil.Screen_y - 540 + 95 * man);
            }
            else {
                if (man < 5) {
                    return new PathPoint(300 - 55 * man, 150 + 95 * man);
                }
                man -= 5;
                return new PathPoint(425 - 55 * man, 170 + 95 * man);
            }
        }
        else if ((Camp == 1 && FightingMixDeal.camp == -1) || Camp == FightingMixDeal.camp) {
            if (man < 5) {
                return new PathPoint(ScrenceUntil.Screen_x - 70 - 65 * man, ScrenceUntil.Screen_y - 325 + 65 * man);
            }
            man -= 5;
            return new PathPoint(ScrenceUntil.Screen_x - 165 - 65 * man, ScrenceUntil.Screen_y - 390 + 65 * man);
        }
        else {
            if (man < 5) {
                return new PathPoint(330 - 65 * man, 150 + 65 * man);
            }
            man -= 5;
            return new PathPoint(425 - 65 * man, 215 + 65 * man);
        }
    }
}
