package org.come.action.monster;

import org.come.server.GameServer;
import org.come.bean.LoginResult;
import org.come.tool.WriteOut;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class AddMonsterAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        WriteOut.addtxt("放妖协议头", 9999L);
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        WriteOut.addtxt("放妖协议头:" + loginResult.getRole_id() + ":" + loginResult.getRolename(), 9999L);
    }
}
