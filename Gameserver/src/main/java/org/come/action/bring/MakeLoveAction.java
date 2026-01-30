package org.come.action.bring;

import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class MakeLoveAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult otherRole = (LoginResult)GameServer.getAllLoginRole().get(GameServer.getRoleNameMap().get(message));
        String msg = Agreement.makeloveAgreement("86400");
        SendMessage.sendMessageToSlef(ctx, msg);
        SendMessage.sendMessageByRoleName(message, msg);
        if (otherRole.getSex().equals("女")) {
            otherRole.setHavebaby(Integer.valueOf(86400));
            otherRole.setMakeloveTime(System.currentTimeMillis());
        }
        else {
            ((LoginResult)GameServer.getAllLoginRole().get(ctx)).setHavebaby(Integer.valueOf(86400));
            ((LoginResult)GameServer.getAllLoginRole().get(ctx)).setMakeloveTime(System.currentTimeMillis());
        }
    }
}
