package org.come.action.friend;

import java.util.Iterator;
import java.util.List;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.FriendResultBean;
import org.come.server.GameServer;
import org.come.entity.Friendtable;
import org.come.until.AllServiceUtil;
import java.math.BigDecimal;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class FriendAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        BigDecimal roleid = new BigDecimal(message);
        List<Friendtable> friendtables = AllServiceUtil.getFriendtableService().selectFriendsByID(roleid);
        for (Friendtable friendtable : friendtables) {
            if (GameServer.getRoleNameMap().get(friendtable.getRolename()) != null) {
                friendtable.setOnlineState(1);
            }
        }
        FriendResultBean friendResultBean = new FriendResultBean();
        friendResultBean.setFriendtables(friendtables);
        String msg = Agreement.getAgreement().friendAgreement(GsonUtil.getGsonUtil().getgson().toJson(friendResultBean));
        SendMessage.sendMessageToSlef(ctx, msg);
    }
}
