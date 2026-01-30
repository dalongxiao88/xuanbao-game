package org.come.control;

import org.come.bean.LoginResult;
import com.tool.role.RoleData;
import org.come.bean.MapMonsterBean;
import java.math.BigDecimal;
import org.come.bean.PathPoint;
import com.tool.image.ManimgAttribute;
import com.tool.image.ImageMixDeal;
import org.come.action.FromServerAction;

public class BattleStateControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        int path = mes.indexOf("|");
        if (path == -1) {
            if (mes.startsWith("R")) {
                this.upRole(mes.substring(1));
            }
            else if (mes.startsWith("M")) {
                this.upMonster(mes.substring(1));
            }
            else if (mes.startsWith("BSKValve")) {
                this.upBSK(mes.substring(9));
            }
            return;
        }
        else {
            if (mes.startsWith("R")) {
                this.upRole(mes.substring(1, path));
            }
            else if (mes.startsWith("M")) {
                this.upMonster(mes.substring(1, path));
            }
            mes = mes.substring(path + 1);
            if (mes.startsWith("R")) {
                this.upRole(mes.substring(1));
            }
            else if (mes.startsWith("M")) {
                this.upMonster(mes.substring(1));
            }
            return;
        }
    }
    
    public void upRole(String mes) {
        String[] v = mes.split("#");
        int fighting = Integer.parseInt(v[0]);
        for (int i = 1; i < v.length; ++i) {
            ManimgAttribute attribute = ImageMixDeal.huoquLogin(v[i]);
            if (attribute != null) {
                attribute.getRoleShow().setFighting(fighting);
                attribute.getRoleShow().getPlayer_Paths().clear();
            }
        }
    }
    
    public void upMonster(String mes) {
        String[] v = mes.split("#");
        for (int i = 0; i < v.length; ++i) {
            String[] vs = v[i].split("\\^");
            int type = Integer.parseInt(vs[1]);
            if (type == 2) {
                ImageMixDeal.removeMonster(Integer.parseInt(vs[0]));
            }
            else {
                ManimgAttribute monster = ImageMixDeal.getMonster(Integer.parseInt(vs[0]));
                if (monster != null) {
                    MapMonsterBean bean = monster.getMapMonsterBean();
                    bean.setType(type);
                    for (int j = 2; j < vs.length; ++j) {
                        if (vs[j].startsWith("H")) {
                            int HPath = vs[j].indexOf(",");
                            if (HPath != -1) {
                                int x = Integer.parseInt(vs[j].substring(1, HPath));
                                int y = Integer.parseInt(vs[j].substring(HPath + 1));
                                if (bean.getHP() != null) {
                                    bean.getHP().setX(x);
                                    bean.getHP().setY(y);
                                }
                                else {
                                    bean.setHP(new PathPoint(x, y));
                                }
                            }
                        }
                        else if (vs[j].startsWith("G")) {
                            ManimgAttribute attribute = ImageMixDeal.huoquLogin(new BigDecimal(vs[j].substring(1)));
                            if (attribute != null) {
                                bean.setFollow(attribute.getName());
                            }
                        }
                        else if (vs[j].startsWith("Z")) {
                            int HPath = vs[j].indexOf(",");
                            if (HPath != -1) {
                                bean.setX(Integer.valueOf(Integer.parseInt(vs[j].substring(1, HPath))));
                                bean.setY(Integer.valueOf(Integer.parseInt(vs[j].substring(HPath + 1))));
                                monster.setX((int)bean.getX());
                                monster.setY((int)bean.getY());
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void upBSK(String mes) {
        double bskvalve = Double.parseDouble(mes);
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        loginResult.setBskvalue(Double.valueOf(bskvalve));
    }
}
