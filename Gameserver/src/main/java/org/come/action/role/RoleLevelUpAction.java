package org.come.action.role;

import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class RoleLevelUpAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        SendMessage.sendMessageToMapRoles(loginResult.getMapid(), Agreement.getAgreement().RoleLevelUpAgreement(message));
    }
}
