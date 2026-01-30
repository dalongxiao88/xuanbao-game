package org.come.action.friend;

import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;
import org.come.entity.Friend;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class DeleteFriendAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        Friend friend = (Friend)GsonUtil.getGsonUtil().getgson().fromJson(message, Friend.class);
        AllServiceUtil.getFriendService().deleteFriend(friend);
    }
}
