package org.come.action.summoning;

import org.come.entity.RoleSummoning;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.until.AllServiceUtil;
import java.math.BigDecimal;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class PetInfoAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        BigDecimal rgid = new BigDecimal(message);
        RoleSummoning roleSummoning = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(rgid);
        String msg = Agreement.getAgreement().PetInfoAgreement(GsonUtil.getGsonUtil().getgson().toJson(roleSummoning));
        SendMessage.sendMessageToSlef(ctx, msg);
    }
}
