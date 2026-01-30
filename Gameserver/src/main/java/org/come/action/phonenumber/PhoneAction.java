package org.come.action.phonenumber;

import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.until.GsonUtil;
import org.come.entity.Phone;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class PhoneAction implements IAction
{
    private static String URL;
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        Phone phone = (Phone)GsonUtil.getGsonUtil().getgson().fromJson(message, Phone.class);
        String mes = "111111";
        String phoneVersion = "eror";
        String msg = Agreement.getAgreement().PhoneNumberReturnAgreement(phoneVersion + "," + mes);
        SendMessage.sendMessageToSlef(ctx, msg);
    }
    
    static {
        PhoneAction.URL = "http://103.85.86.54:8080/TestMaven/sendphonenumber";
    }
}
