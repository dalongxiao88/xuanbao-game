package org.come.control;

import com.tool.btn.RoleMsgBtn;
import org.come.Frame.AddFriendJframe;
import org.come.until.GsonUtil;
import org.come.bean.Role_bean;
import org.come.action.FromServerAction;

public class SearcahChatControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        if (type.equals("searcahChatRoleId") || type.equals("searcahChatRoleName")) {
            Role_bean bean = (Role_bean)GsonUtil.getGsonUtil().getgson().fromJson(mes, Role_bean.class);
            AddFriendJframe.getAddFriendJframe().getAddFriendJpanel().CF(bean);
            RoleMsgBtn.role_bean = bean;
        }
        else if (type.equals("searchChatRecorde")) {}
    }
}
