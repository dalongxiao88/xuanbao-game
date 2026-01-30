package org.come.action.sys;

import org.come.server.GameServer;
import org.come.bean.LoginResult;
import org.come.until.GsonUtil;
import org.come.bean.Middle;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class MiddleAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        Middle middle = (Middle)GsonUtil.getGsonUtil().getgson().fromJson(message, Middle.class);
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        loginResult.setTaskDaily(middle.getTaskDaily());
    }
}
