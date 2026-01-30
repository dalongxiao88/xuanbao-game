package org.come.action.friend;

import org.come.entity.Friendtable;
import java.util.List;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.bean.FriendResultBean;
import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;
import org.come.entity.Friend;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class AddFriendAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        Friend friend = (Friend)GsonUtil.getGsonUtil().getgson().fromJson(message, Friend.class);
        boolean isSuccess = AllServiceUtil.getFriendService().addFriend(friend);
        if (isSuccess) {
            List<Friendtable> friendtables = AllServiceUtil.getFriendtableService().selectFriendsByID(friend.getRoleid());
            FriendResultBean friendResultBean = new FriendResultBean();
            friendResultBean.setFriendtables(friendtables);
            String msg = Agreement.getAgreement().friendAgreement(GsonUtil.getGsonUtil().getgson().toJson(friendResultBean));
            SendMessage.sendMessageToSlef(ctx, msg);
        }
    }
}
