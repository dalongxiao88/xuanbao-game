package org.come.action.title;

import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class TitleExpireAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        roleInfo.setTitle("");
        SendMessage.sendMessageToMapRoles(roleInfo.getMapid(), Agreement.getAgreement().TitleChangeAgreement(GsonUtil.getGsonUtil().getgson().toJson(roleInfo.getRoleShow())));
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你的称谓【" + message + "】过期了！"));
    }
}
