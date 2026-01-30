package org.come.action.title;

import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class TitleChangeAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        roleInfo.setTitle(message);
        SendMessage.sendMessageToMapRoles(roleInfo.getMapid(), Agreement.getAgreement().TitleChangeAgreement(GsonUtil.getGsonUtil().getgson().toJson(roleInfo.getRoleShow())));
    }
}
