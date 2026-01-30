package org.come.extInterface;

import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class GetInlinePeople implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String getMes) {
        String returnmes = "";
        if (getMes.equals("getinlinepeople")) {
            returnmes = "inline=" + GameServer.inlineNum.get() + ":" + GameServer.phoneinlineNum.get();
        }
        else {
            returnmes = "error";
        }
        String msg = Agreement.getAgreement().getInlinePeopleAgreement(returnmes);
        SendMessage.sendMessageToSlef(ctx, msg);
    }
}
