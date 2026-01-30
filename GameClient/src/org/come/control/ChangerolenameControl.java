package org.come.control;

import com.tool.image.ManimgAttribute;
import org.come.until.GoodsListFromServerUntil;
import com.tool.role.RoleData;
import com.tool.image.ImageMixDeal;
import org.come.Frame.ZhuFrame;
import org.come.until.GsonUtil;
import org.come.bean.ChangeRoleNameBean;
import org.come.action.FromServerAction;

public class ChangerolenameControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        ChangeRoleNameBean nameBean = (ChangeRoleNameBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, ChangeRoleNameBean.class);
        if (!nameBean.isFlag()) {
            ZhuFrame.getZhuJpanel().addPrompt2("你修改的名称已被其他玩家使用");
            return;
        }
        if (nameBean.getOldName().equals(ImageMixDeal.userimg.getRoleShow().getRolename())) {
            ImageMixDeal.userimg.getRoleShow().setRolename(nameBean.getNewName());
            ImageMixDeal.userimg.setName(nameBean.getNewName());
            ImageMixDeal.username = nameBean.getNewName();
            RoleData.getRoleData().getLoginResult().setRolename(nameBean.getNewName());
            GoodsListFromServerUntil.Uerbiaoid(nameBean.getRgid());
        }
        else {
            ManimgAttribute manimgAttribute = (ManimgAttribute)ImageMixDeal.Playerimgmap.get(nameBean.getOldName());
            manimgAttribute.setName(nameBean.getNewName());
            String marry = RoleData.getRoleData().getLoginResult().getMarryObject();
            if (marry != null && marry.equals(nameBean.getOldName())) {
                RoleData.getRoleData().getLoginResult().setMarryObject(nameBean.getNewName());
            }
            ImageMixDeal.Playerimgmap.put(nameBean.getNewName(), manimgAttribute);
            ImageMixDeal.Playerimgmap.remove(nameBean.getOldName());
        }
    }
}
