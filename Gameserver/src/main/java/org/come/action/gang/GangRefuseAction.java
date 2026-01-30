package org.come.action.gang;

import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class GangRefuseAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (!roleInfo.getGangpost().equals("帮主") && !roleInfo.getGangpost().equals("护法")) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("帮主或者护法才有权限"));
            return;
        }
        if (message.equals("")) {
            AllServiceUtil.getGangapplyService().deleteGangappleGang(roleInfo.getGang_id());
        }
        else {
            BigDecimal roleid = new BigDecimal(message);
            AllServiceUtil.getGangapplyService().deleteGangapple(roleid, roleInfo.getGang_id());
        }
    }
}
