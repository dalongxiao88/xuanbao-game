package org.come.control;

import java.util.Iterator;
import java.util.List;
import org.come.bean.GangResultBean;
import org.come.Jpanel.FactionCardJpanel;
import org.come.until.FormsManagement;
import com.tool.role.RoleProperty;
import org.come.bean.LoginResult;
import java.util.ArrayList;
import org.come.Frame.FactionMainJframe;
import org.come.Frame.ZhuFrame;
import com.tool.role.RoleData;
import org.come.until.GsonUtil;
import org.come.bean.GangGiveMoneyBean;
import org.come.action.FromServerAction;

public class GangGiveMoneyControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        GangGiveMoneyBean changeBean = (GangGiveMoneyBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, GangGiveMoneyBean.class);
        RoleData.getRoleData().setLoginResult(changeBean.getLoginResult());
        ZhuFrame.getZhuJpanel().addPrompt2("感谢你为帮派做出的贡献");
        FactionCardJpanel factionCardJpanel = FactionMainJframe.getFactionMainJframe().getFactionMainJpanel().getFactionCardJpanel();
        factionCardJpanel.getGangResultBean().setGang(changeBean.getGang());
        GangResultBean gangResultBean = factionCardJpanel.getGangResultBean();
        List<LoginResult> logins = gangResultBean.getRoleTables();
        List<LoginResult> newLogins = new ArrayList<>();
        for (LoginResult login : logins) {
            if (login.getRole_id().compareTo(changeBean.getLoginResult().getRole_id()) == 0) {
                login = changeBean.getLoginResult();
                newLogins.add(login);
            }
            else {
                newLogins.add(login);
            }
        }
        gangResultBean.setRoleTables(newLogins);
        factionCardJpanel.getFactionMemberJpanel().showMenuMessage(gangResultBean);
        RoleProperty.ResetBp();
        FormsManagement.HideForm(112);
    }
}
