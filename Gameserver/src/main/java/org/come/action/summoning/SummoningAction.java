package org.come.action.summoning;

import org.come.entity.RoleSummoning;
import java.util.List;
import java.math.BigDecimal;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.PetResultBean;
import org.come.until.AllServiceUtil;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class SummoningAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        BigDecimal roleid = ((LoginResult)GameServer.getAllLoginRole().get(ctx)).getRole_id();
        List<RoleSummoning> roleSummonings = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRoleID(roleid);
        PetResultBean petResultBean = new PetResultBean();
        petResultBean.setRoleSummonings(roleSummonings);
        String msg = Agreement.getAgreement().petAgreement(GsonUtil.getGsonUtil().getgson().toJson(petResultBean));
        SendMessage.sendMessageToSlef(ctx, msg);
    }
}
