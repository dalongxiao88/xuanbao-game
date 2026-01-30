package org.come.control;

import org.come.bean.GangResultBean;
import org.come.Jpanel.FactionCardJpanel;
import org.come.bean.RoleShow;
import org.come.bean.LoginResult;
import org.come.Frame.FactionMainJframe;
import org.come.until.FormsManagement;
import org.come.Frame.ZhuFrame;
import com.tool.image.ImageMixDeal;
import com.tool.role.RoleData;
import org.come.until.GsonUtil;
import org.come.bean.GangChangeBean;
import org.come.action.FromServerAction;

public class GangChangeControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        GangChangeBean changeBean = (GangChangeBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, GangChangeBean.class);
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
        loginResult.setGang_id(changeBean.getGangid());
        loginResult.setGangname(changeBean.getGangName());
        String post = loginResult.getGangpost();
        loginResult.setGangpost(changeBean.getPost());
        roleShow.setGang_id(changeBean.getGangid());
        roleShow.setGangname(changeBean.getGangName());
        if (changeBean.getMsg() != null) {
            ZhuFrame.getZhuJpanel().addPrompt2(changeBean.getMsg());
        }
        if (FormsManagement.getInternalForm2(48) != null && FormsManagement.getInternalForm(48).getFrame().isVisible()) {
            FactionCardJpanel factionCardJpanel = FactionMainJframe.getFactionMainJframe().getFactionMainJpanel().getFactionCardJpanel();
            GangResultBean gangResultBean = factionCardJpanel.getGangResultBean();
            if ("帮主".equals(changeBean.getPost())) {
                for (int i = 0; i < gangResultBean.getRoleTables().size(); ++i) {
                    LoginResult loginResult2 = (LoginResult)gangResultBean.getRoleTables().get(i);
                    if ("帮主".equals(loginResult2.getGangpost())) {
                        loginResult2.setGangpost(post);
                    }
                }
            }
            int i = 0;
            while (i < gangResultBean.getRoleTables().size()) {
                LoginResult loginResult2 = (LoginResult)gangResultBean.getRoleTables().get(i);
                if (loginResult2.getRole_id().compareTo(loginResult.getRole_id()) == 0) {
                    loginResult2.setGang_id(changeBean.getGangid());
                    loginResult2.setGangname(changeBean.getGangName());
                    post = loginResult2.getGangpost();
                    loginResult2.setGangpost(changeBean.getPost());
                    break;
                }
                else {
                    ++i;
                }
            }
            factionCardJpanel.getFactionMemberJpanel().showMenuMessage(gangResultBean);
            factionCardJpanel.getFactionMemberJpanel().showBtn(false);
            factionCardJpanel.getFactionMemberJpanel().showBtn(true);
        }
    }
}
