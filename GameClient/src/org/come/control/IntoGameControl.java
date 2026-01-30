package org.come.control;

import org.come.bean.LoginResult;
import com.tool.Stall.StallBean;
import org.come.thread.LoadYeguaiRunnable;
import org.come.until.Util;
import com.tool.role.RoleData;
import com.tool.image.ManimgAttribute;
import org.come.bean.RoleShow;
import com.tool.image.ImageMixDeal;
import org.come.until.GsonUtil;
import org.come.bean.GetClientUserMesageBean;
import org.come.action.FromServerAction;

public class IntoGameControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        GetClientUserMesageBean list2 = (GetClientUserMesageBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, GetClientUserMesageBean.class);
        if (list2.getIsmap() == 1) {
            try {
                ImageMixDeal.Playerimgmap.clear();
                ImageMixDeal.mapMonsterlist.clear();
                ImageMixDeal.stalls.clear();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (list2.getRoleShows().size() != 0) {
            for (int i = list2.getRoleShows().size() - 1; i >= 0; --i) {
                RoleShow roleShow = (RoleShow)list2.getRoleShows().get(i);
                if (roleShow != null) {
                    ManimgAttribute attribute = ImageMixDeal.huoquLogin(roleShow.getRolename());
                    if (attribute == null) {
                        ImageMixDeal.Playerimgmap.put(roleShow.getRolename(), new ManimgAttribute(roleShow, 1));
                    }
                    else {
                        attribute.setRoleShow(roleShow);
                        attribute.changeskin(null);
                        attribute.CZmove();
                        if (attribute.getLeixing() == 0) {
                            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
                            loginResult.setX(Long.valueOf((long)roleShow.getX()));
                            loginResult.setY(Long.valueOf((long)roleShow.getY()));
                            loginResult.setMapid(roleShow.getMapid());
                            Util.dogame(loginResult);
                        }
                    }
                }
            }
        }
        if (list2.getMonster() != null) {
            LoadYeguaiRunnable loadYeguaiRunnable = new LoadYeguaiRunnable(list2.getMonster());
            Thread t = new Thread(loadYeguaiRunnable);
            t.start();
        }
        if (list2.getStallBeans() != null) {
            for (int i = list2.getStallBeans().size() - 1; i >= 0; --i) {
                ImageMixDeal.stall((StallBean)list2.getStallBeans().get(i));
            }
        }
        ImageMixDeal.upScene(list2.getSceneMsg());
//        System.gc();//可以不用这个
    }
}
