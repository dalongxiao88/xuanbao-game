package org.come.action.pack;

import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class PackLockAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        ((LoginResult)GameServer.getAllLoginRole().get(ctx)).setPassword(message);
    }
}
