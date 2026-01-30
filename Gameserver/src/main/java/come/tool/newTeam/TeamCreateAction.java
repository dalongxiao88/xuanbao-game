package come.tool.newTeam;

import org.come.until.GsonUtil;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class TeamCreateAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        TeamBean teamBean = TeamUtil.getTeam(loginResult.getTroop_id());
        if (teamBean != null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你已经有队伍了"));
            return;
        }
        teamBean = TeamUtil.addTeam(loginResult.getTeamRole());
        loginResult.setTroop_id(teamBean.getTeamId());
        loginResult.setTeamInfo(teamBean.getTeamInfo());
        loginResult.initTeamRole(loginResult.getTeamRole());
        StringBuffer buffer = new StringBuffer();
        buffer.append(teamBean.getTeamId());
        buffer.append("#");
        buffer.append(teamBean.getTeamInfo());
        String msg = Agreement.getAgreement().team3Agreement(buffer.toString());
        SendMessage.sendMessageToMapRoles(loginResult.getMapid(), msg);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().team1Agreement(GsonUtil.getGsonUtil().getgson().toJson(teamBean)));
    }
}
