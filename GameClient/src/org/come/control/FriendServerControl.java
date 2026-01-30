package org.come.control;

import org.come.until.JTreeData;
import org.come.Frame.TestfriendlistJframe;
import org.come.until.UserMessUntil;
import org.come.until.GsonUtil;
import org.come.bean.FriendResultBean;
import org.come.action.FromServerAction;

public class FriendServerControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        FriendResultBean friendresult = (FriendResultBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, FriendResultBean.class);
        UserMessUntil.setFriendtables(friendresult.getFriendtables());
        JTreeData.ShowFriendMsg(TestfriendlistJframe.getTestfriendlistJframe().getJflist().getTop(), TestfriendlistJframe.getTestfriendlistJframe().getJflist().getjTree());
    }
}
