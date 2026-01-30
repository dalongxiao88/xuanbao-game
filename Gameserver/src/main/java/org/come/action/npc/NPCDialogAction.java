package org.come.action.npc;

import org.come.handler.SendMessage;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import org.come.protocol.Agreement;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class NPCDialogAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        String msg = Agreement.getAgreement().NPCDialogAgreement(message);
        LoginResult roleInfo = GameServer.getAllLoginRole().get(ctx);
        String[] roles = null;
        if (roleInfo.getTeamInfo() != null && !roleInfo.getTeamInfo().equals("")) {
            roles = GameServer.getAllLoginRole().get(ctx).getTeamInfo().split("\\|");
        }
        else {
            roles = new String[] { roleInfo.getRolename() };
        }
        for (int i = 0; i < roles.length; ++i) {
            SendMessage.sendMessageByRoleName(roles[i], msg);
        }
    }
}
