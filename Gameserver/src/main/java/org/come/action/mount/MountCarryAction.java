package org.come.action.mount;

import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class MountCarryAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        if (message != null) {
            ((LoginResult)GameServer.getAllLoginRole().get(ctx)).setMount_id(Integer.valueOf(Integer.parseInt(message)));
        }
        else {
            ((LoginResult)GameServer.getAllLoginRole().get(ctx)).setMount_id(null);
        }
        SendMessage.sendMessageToMapRoles(((LoginResult)GameServer.getAllLoginRole().get(ctx)).getMapid(), Agreement.getAgreement().MountCarryAgreement(GsonUtil.getGsonUtil().getgson().toJson(GameServer.getAllLoginRole().get(ctx))));
    }
}
