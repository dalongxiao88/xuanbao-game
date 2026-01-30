package org.come.action.Fly;

import org.come.entity.Fly;
import java.util.List;
import java.math.BigDecimal;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.FlyResult;
import org.come.until.AllServiceUtil;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class FlyAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult login =(LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (login == null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("角色掉线，请重连"));
            return;
        }
        BigDecimal roleID = login.getRole_id();
        List<Fly> flys = AllServiceUtil.getFlyService().selectFlysByRoleID(roleID);
        FlyResult flyResult = new FlyResult();
        flyResult.setFlys(flys);
        String msg = Agreement.getAgreement().FlyAgreement(GsonUtil.getGsonUtil().getgson().toJson(flyResult));
        SendMessage.sendMessageToSlef(ctx, msg);
    }
}
