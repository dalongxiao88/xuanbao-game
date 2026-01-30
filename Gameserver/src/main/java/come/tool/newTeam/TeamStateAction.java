package come.tool.newTeam;

import come.tool.Role.RolePool;
import java.math.BigDecimal;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class TeamStateAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("哎呀！卡了一下，请重新上号刷新"));
            return;
        }
        TeamBean bean = TeamUtil.getTeam(loginResult.getTroop_id());
        if (bean == null) {
            return;
        }
        if (message.startsWith("L")) {
            if (bean.isCaptian(loginResult.getRole_id())) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你是队长无法离队"));
                return;
            }
            TeamRole teamRole = bean.getTeamRole(loginResult.getRole_id());
            if (teamRole == null) {
                return;
            }
            if (teamRole.getState() != 0) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你已经是离队状态"));
                return;
            }
            bean.stateLeave(teamRole, -1);
        }
        else if (message.startsWith("R")) {
            if (bean.isCaptian(loginResult.getRole_id())) {
                return;
            }
            TeamRole teamRole = bean.getTeamRole(loginResult.getRole_id());
            if (teamRole == null) {
                return;
            }
            if (teamRole.getState() == 0) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你已在队伍中"));
                return;
            }
            String teamName = bean.getTeamName();
            ChannelHandlerContext tCtx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(teamName);
            LoginResult login = (tCtx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(tCtx)) : null;
            if (login == null) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("队长不在线"));
            }
            else if ((long)login.getMapid() != (long)loginResult.getMapid()) {
                StringBuffer buffer = new StringBuffer();
                buffer.append("你的队长在#G");
                buffer.append(GameServer.getMapName(login.getMapid().toString()));
                buffer.append("#Y,只有与队长同地图才能归队");
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(buffer.toString()));
                buffer.setLength(0);
                buffer.append("你的队员");
                buffer.append(loginResult.getRolename());
                buffer.append("想要回归队伍,还是等等他吧");
                SendMessage.sendMessageToSlef(tCtx, Agreement.getAgreement().PromptAgreement(buffer.toString()));
            }
            else {
                bean.stateCome(teamRole);
            }
        }
        else if (message.startsWith("C")) {
            BigDecimal roleId = new BigDecimal(message.substring(1));
            LoginResult login2 = RolePool.getLoginResult(roleId);
            ChannelHandlerContext tCtx = (login2 != null) ? ((ChannelHandlerContext)GameServer.getRoleNameMap().get(login2.getRolename())) : null;
            if (tCtx != null) {
                StringBuffer buffer2 = new StringBuffer();
                buffer2.append("你的队长在#G");
                buffer2.append(GameServer.getMapName(loginResult.getMapid().toString()));
                buffer2.append("#Y召你归队");
                SendMessage.sendMessageToSlef(tCtx, Agreement.getAgreement().PromptAgreement(buffer2.toString()));
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("已向对方发出召集"));
            }
            else {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("对方不在线"));
            }
        }
    }
}
