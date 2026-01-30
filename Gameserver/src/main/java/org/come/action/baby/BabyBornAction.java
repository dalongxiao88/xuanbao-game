package org.come.action.baby;

import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class BabyBornAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        ((LoginResult)GameServer.getAllLoginRole().get(ctx)).setHavebaby(null);
        String msg = Agreement.getAgreement().BabyCustodayAgreement("抚养");
        SendMessage.sendMessageToSlef(ctx, msg);
    }
}
