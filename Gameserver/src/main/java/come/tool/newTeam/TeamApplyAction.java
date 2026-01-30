package come.tool.newTeam;

import org.come.server.GolemServer;
import org.come.until.GsonUtil;
import java.math.BigDecimal;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class TeamApplyAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        TeamBean bean = TeamUtil.getTeam(loginResult.getTroop_id());
        if (bean != null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你已经有队伍了"));
            return;
        }
        bean = TeamUtil.getTeamRole(new BigDecimal(message));
        if (bean == null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你申请的人还没有队伍"));
            return;
        }
        if (bean.getTeamSize() >= 5) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你申请的队伍已满"));
            return;
        }
        if (bean.applyTeam(loginResult.getTeamRole())) {
            String teamName = bean.getTeamName();
            ChannelHandlerContext tCtx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(teamName);
            if (tCtx != null) {
                SendMessage.sendMessageToSlef(tCtx, Agreement.getAgreement().team2Agreement(GsonUtil.getGsonUtil().getgson().toJson(loginResult.getTeamRole())));
            }
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("#W你申请加入#R" + teamName + "#W队伍"));
        }
        else {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你已经在申请列表中"));
        }
    }
}
