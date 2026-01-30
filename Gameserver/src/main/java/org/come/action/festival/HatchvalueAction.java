package org.come.action.festival;

import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import io.netty.channel.ChannelHandlerContext;
import java.util.concurrent.atomic.AtomicInteger;
import org.come.action.IAction;

public class HatchvalueAction implements IAction
{
    public static AtomicInteger hatch;
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().HatchvalueAgreement(HatchvalueAction.hatch.toString()));
    }
    
    static {
        HatchvalueAction.hatch = new AtomicInteger(0);
    }
}
