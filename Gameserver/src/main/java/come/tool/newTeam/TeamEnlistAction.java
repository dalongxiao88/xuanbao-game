package come.tool.newTeam;

import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.until.GsonUtil;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class TeamEnlistAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        if (message.length() != 0) {
            LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
            TeamBean bean = TeamUtil.getTeam(loginResult.getTroop_id());
            if (bean != null && bean.isCaptian(loginResult.getRole_id())) {
                TeamBean teamBean = (TeamBean)GsonUtil.getGsonUtil().getgson().fromJson(message, TeamBean.class);
                bean.upEnlist(teamBean);
                TeamUtil.addEnlist(bean);
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("招募发布成功"));
            }
        }
        else {
            SendMessage.sendMessageToSlef(ctx, TeamUtil.getEnlist());
        }
    }
}
