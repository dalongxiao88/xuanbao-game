package org.come.action.bring;

import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;
import org.come.until.AchievemUtil;

public class MarryAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        LoginResult otherRole = (LoginResult)GameServer.getAllLoginRole().get(GameServer.getRoleNameMap().get(message));
        if (roleInfo.getMarryObject() != null && !roleInfo.getMarryObject().equals("")) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你自己都已经有对象了"));
            return;
        }
        if (otherRole.getMarryObject() != null && !otherRole.getMarryObject().equals("")) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("对方有对象了"));
            return;
        }
        otherRole.setMarryObject(roleInfo.getRolename());
        roleInfo.setMarryObject(otherRole.getRolename());
        SendMessage.sendMessageByRoleName(roleInfo.getRolename(), Agreement.marryAgreement(otherRole.getRolename()));
        SendMessage.sendMessageByRoleName(otherRole.getRolename(), Agreement.marryAgreement(roleInfo.getRolename()));
        AchievemUtil.detectionAchievem(roleInfo, "结婚");
        AchievemUtil.detectionAchievem(otherRole, "结婚");
    }
}
