package org.come.control;

import com.tool.role.RoleData;
import com.tool.image.ImageMixDeal;
import org.come.Jpanel.FrameMessageChangeJpanel;
import org.come.until.GsonUtil;
import org.come.action.FromServerAction;

public class MarryControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        if (type.equals("marry")) {
            this.marry(mes);
        }
        else if (type.equals("makelove")) {
            this.makelove(mes);
        }
        else {
            this.unMarry(mes);
        }
    }
    
    public void marry(String mes) {
        String MarrayName = (String)GsonUtil.getGsonUtil().getgson().fromJson(mes, String.class);
        FrameMessageChangeJpanel.addtext(6, "恭喜您，已经和" + MarrayName + "结为夫妻！！", null, null);
        ImageMixDeal.userimg.Dialogue("恭喜您，已经和" + MarrayName + "结为夫妻！！");
        RoleData.getRoleData().getLoginResult().setMarryObject(MarrayName);
    }
    
    public void unMarry(String mes) {
        String MarrayName = (String)GsonUtil.getGsonUtil().getgson().fromJson(mes, String.class);
        FrameMessageChangeJpanel.addtext(6, "您和" + MarrayName + "已经解除婚约", null, null);
        ImageMixDeal.userimg.Dialogue("您和" + MarrayName + "已经解除婚约");
        RoleData.getRoleData().getLoginResult().setMarryObject(null);
    }
    
    public void makelove(String mes) {
        ImageMixDeal.userimg.Dialogue("已经洞房！！");
        if (RoleData.getRoleData().getLoginResult().getSex().equals("女")) {
            RoleData.getRoleData().getLoginResult().setHavebaby(Integer.valueOf(100));
            RoleData.getRoleData().getLoginResult().setBabyState("备孕");
            ImageMixDeal.userimg.Dialogue("开始备孕");
        }
    }
}
