package org.come.control;

import org.come.bean.PathPoint;
import com.tool.image.ImageMixDeal;
import com.tool.image.ManimgAttribute;
import org.come.until.GsonUtil;
import org.come.bean.RoleMoveBean;
import org.come.action.FromServerAction;

public class MoveControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        RoleMoveBean bean = (RoleMoveBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, RoleMoveBean.class);
        if (bean.getRole() != null) {
            ManimgAttribute attribute = (ManimgAttribute)ImageMixDeal.Playerimgmap.get(bean.getRole());
            if (attribute != null) {
                attribute.setMovejiange(0L);
                attribute.getRoleShow().setX(((PathPoint)bean.getPaths().get(0)).getX());
                attribute.getRoleShow().setY(((PathPoint)bean.getPaths().get(0)).getY());
                attribute.getRoleShow().setPlayer_Paths(bean.getPaths());
            }
        }
    }
}
