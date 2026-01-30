package org.come.action.Fly;

import java.util.List;
import java.math.BigDecimal;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.bean.FlyResult;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;
import org.come.entity.Fly;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class FlyGetAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        Fly fly = (Fly)GsonUtil.getGsonUtil().getgson().fromJson(message, Fly.class);
        AllServiceUtil.getFlyService().insertFly(fly);
        BigDecimal roleID = ((LoginResult)GameServer.getAllLoginRole().get(ctx)).getRole_id();
        List<Fly> flys = AllServiceUtil.getFlyService().selectFlysByRoleID(roleID);
        FlyResult flyResult = new FlyResult();
        flyResult.setFlys(flys);
        String msg = Agreement.getAgreement().FlyAgreement(GsonUtil.getGsonUtil().getgson().toJson(flyResult));
        SendMessage.sendMessageToSlef(ctx, msg);
    }
}
