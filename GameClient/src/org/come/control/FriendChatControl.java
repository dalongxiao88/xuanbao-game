package org.come.control;

import org.come.until.UserMessUntil;
import org.come.until.MessagrFlagUntil;
import org.come.until.GsonUtil;
import org.come.bean.ChatBean;
import org.come.action.FromServerAction;

public class FriendChatControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        ChatBean chatbean = (ChatBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, ChatBean.class);
        MessagrFlagUntil.ReceiveMessage(chatbean, chatbean.getRolename());
        UserMessUntil.setChatFriendName(chatbean.getRolename());
    }
}
