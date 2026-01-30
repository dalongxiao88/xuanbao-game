package org.come.thread;

import java.util.Map;
import com.tool.image.ManimgAttribute;
import come.tool.Fighting.RandomCreepsMovePath;
import java.math.BigDecimal;
import come.tool.Fighting.CreepsMovePath;
import java.util.List;
import org.come.bean.MapMonsterBean;
import org.come.until.AccessTeamInfoUntil;
import org.come.bean.PathPoint;
import java.util.ArrayList;
import java.util.HashMap;
import com.tool.image.ImageMixDeal;

public class LoadYeguaiRunnable implements Runnable
{
    private String monster;
    
    public LoadYeguaiRunnable(String monster) {
        this.monster = monster;
    }
    
    @Override
    public void run() {
        upMonster(this.monster, true);
    }
    
    public static void upMonster(String monster, boolean is) {
        if (is) {
            ImageMixDeal.mapMonsterlist.clear();
        }
        if (monster == null || monster.equals("")) {
            return;
        }
        Map<String, List<PathPoint>> pointMap = null;
        if (monster.startsWith("M")) {
            int path = monster.indexOf("|");
            if (path == -1) {
                return;
            }
            pointMap = new HashMap<>();
            String[] v = monster.substring(1, path).split("#");
            for (int i = 0; i < v.length; ++i) {
                String[] vs = v[i].split("\\^");
                List<PathPoint> points = new ArrayList<>();
                int j = 1;
                while (j + 1 < vs.length) {
                    PathPoint point = new PathPoint(Integer.parseInt(vs[j++]), Integer.parseInt(vs[j++]));
                    points.add(point);
                }
                pointMap.put(vs[0], points);
            }
            monster = monster.substring(path + 1);
        }
        String[] v2 = monster.split("\\|");
        for (int k = 0; k < v2.length; ++k) {
            String[] vs2 = v2[k].split("#");
            String name = null;
            String title = null;
            String[] names = null;
            String[] appellation = null;
            int id = Integer.parseInt(vs2[0]);
            int path2 = vs2[1].indexOf("&");
            if (path2 == -1) {
                name = vs2[1];
                names = AccessTeamInfoUntil.getss(name);
            }
            else {
                name = vs2[1].substring(0, path2);
                names = AccessTeamInfoUntil.getss(name);
                title = vs2[1].substring(path2 + 1);
                appellation = AccessTeamInfoUntil.getss(title);
            }
            int robottype = Integer.parseInt(vs2[3]);
        LOOP:
            for (int l = 4; l < vs2.length; ++l) {
                String[] vss = vs2[l].split("\\^");
                if (vss.length >= 3) {
                    int I = Integer.parseInt(vss[0]);
                    MapMonsterBean bean = new MapMonsterBean();
                    bean.setRobotid(Integer.valueOf(id));
                    bean.setRobotname(name);
                    bean.setRobottitle(title);
                    bean.setRobotskin(vs2[2]);
                    bean.setRobottype(robottype);
                    bean.setI(Integer.valueOf(I));
                    bean.setX(Integer.valueOf(Integer.parseInt(vss[1])));
                    bean.setY(Integer.valueOf(Integer.parseInt(vss[2])));
                    bean.setType(0);
                    for (int m = 3; m < vss.length; ++m) {
                        if (vss[m].startsWith("S")) {
                            bean.setType(Integer.parseInt(vss[m].substring(1)));
                            if (bean.getType() == 2) {
                                continue LOOP;
                            }
                        }
                        else if (vss[m].startsWith("L")) {
                            if (pointMap != null) {
                                int HPath = vss[m].indexOf(",");
                                if (HPath != -1) {
                                    List<PathPoint> points2 = (List<PathPoint>)pointMap.get(vss[m].substring(1, HPath));
                                    if (points2 != null) {
                                        CreepsMovePath movePath = new CreepsMovePath((int)bean.getX(), (int)bean.getY(), Integer.parseInt(vss[m].substring(HPath + 1)), points2);
                                        bean.setMovePath(movePath);
                                    }
                                }
                            }
                        }
                        else if (vss[m].startsWith("H")) {
                            int HPath = vss[m].indexOf(",");
                            if (HPath != -1) {
                                int x = Integer.parseInt(vss[m].substring(1, HPath));
                                int y = Integer.parseInt(vss[m].substring(HPath + 1));
                                if (bean.getHP() != null) {
                                    bean.getHP().setX(x);
                                    bean.getHP().setY(y);
                                }
                                else {
                                    bean.setHP(new PathPoint(x, y));
                                }
                            }
                        }
                        else if (vss[m].startsWith("G")) {
                            ManimgAttribute attribute = ImageMixDeal.huoquLogin(new BigDecimal(vss[m].substring(1)));
                            if (attribute != null) {
                                bean.setFollow(attribute.getName());
                            }
                        }
                    }
                    if (bean.getMovePath() == null) {
                        bean.setMovePath(new RandomCreepsMovePath((int)bean.getX(), (int)bean.getY()));
                    }
                    ManimgAttribute attribute2 = new ManimgAttribute(bean, names, appellation, title);
                    if (bean.getMovePath() != null) {
                        bean.getMovePath().isMove(attribute2, 0L, 0);
                    }
                    ImageMixDeal.mapMonsterlist.add(attribute2);
                }
            }
        }
    }
}
