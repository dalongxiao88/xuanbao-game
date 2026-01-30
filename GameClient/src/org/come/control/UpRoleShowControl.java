package org.come.control;

import org.come.entity.RoleSummoning;
import com.tool.btn.AircraftBtn;
import java.util.stream.Collectors;
import com.tool.image.ManimgAttribute;
import org.apache.commons.collections.CollectionUtils;
import com.tool.image.ImageMixDeal;
import org.come.until.GsonUtil;
import org.come.bean.RoleShow;
import org.come.action.FromServerAction;

public class UpRoleShowControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        RoleShow roleShow = (RoleShow)GsonUtil.getGsonUtil().getgson().fromJson(mes, RoleShow.class);
        ManimgAttribute attribute = ImageMixDeal.huoquLogin(roleShow.getRolename());
        if (attribute != null) {
            roleShow.setX(attribute.getRoleShow().getX());
            roleShow.setY(attribute.getRoleShow().getY());
            attribute.setRoleShow(roleShow);
            attribute.changeskin(null);
        }
//        if (attribute.getRoleShow()!=null
//        &&attribute.getRoleShow()==ImageMixDeal.userimg.getRoleShow()) {
//            ImageMixDeal.userimg.getRoleShow().setSpecies_id(attribute.getRoleShow().getSpecies_id());
//        }
        if (CollectionUtils.isNotEmpty(roleShow.getShowRoleSummoningList())) {
            ImageMixDeal.petMap.put(roleShow.getRolename(), roleShow.getShowRoleSummoningList().stream().filter(e/* org.come.entity.RoleSummoning, */ -> e.isShow()).map(e/* org.come.entity.RoleSummoning, */ -> new ManimgAttribute(e, 1)).collect(Collectors.toList()));
        }
        AircraftBtn.quickFlyZD();
    }
}
