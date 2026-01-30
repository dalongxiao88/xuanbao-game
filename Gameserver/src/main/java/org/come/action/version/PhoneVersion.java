package org.come.action.version;

import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.until.AllServiceUtil;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class PhoneVersion implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        String version = AllServiceUtil.getAppVersionService().selectPhoneVersion();
        String msg = Agreement.getAgreement().phoneVersionAgreement(version);
        SendMessage.sendMessageToSlef(ctx, msg);
    }
}
