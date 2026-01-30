package come.tool.teamArena;

import come.tool.newTeam.TeamBean;
import come.tool.newTeam.TeamUtil;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class TeamArenaAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        if (TeamArenaUtil.teamArenaThread == null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("匹配通道未开启!!!#R开放时间为:19:00-20:00"));
            return;
        }
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (roleInfo == null) {
            return;
        }
        TeamBean bean = TeamUtil.getTeam(roleInfo.getTroop_id());
        if (bean == null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你还没有队伍无法参与"));
            return;
        }
        if (message.equals("0")) {
            if (!bean.isCaptian(roleInfo.getRole_id())) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你不是队长"));
                return;
            }
            TeamArenaUtil.addAffirm(ctx, bean);
        }
        else if (message.equals("A")) {
            TeamArenaUtil.confirm(bean, roleInfo, true);
        }
        else if (message.equals("D")) {
            TeamArenaUtil.confirm(bean, roleInfo, false);
        }
    }
}
