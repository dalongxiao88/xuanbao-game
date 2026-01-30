package org.come.protocol;

import org.come.entity.Baby;
import org.come.until.AllServiceUtil;
import java.math.BigDecimal;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class BabyReleaseAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        String[] vs = message.split("\\|");
        if (vs != null && vs.length > 0) {
            BigDecimal babyIdn = new BigDecimal(vs[0]);
            Baby baby = AllServiceUtil.getBabyService().selectBabyById(babyIdn);
            if (baby == null) {
                return;
            }
            if (baby.getRoleid().compareTo(loginResult.getRole_id()) != 0) {
                return;
            }
            AllServiceUtil.getBabyService().deleteBabySingle(babyIdn);
        }
    }
}
