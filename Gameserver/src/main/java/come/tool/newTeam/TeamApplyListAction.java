package come.tool.newTeam;

import java.util.List;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.action.monitor.TBean;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class TeamApplyListAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        TeamBean bean = TeamUtil.getTeam(loginResult.getTroop_id());
        if (bean != null && bean.isCaptian(loginResult.getRole_id())) {
            TBean<List<TeamRole>> tBean = new TBean(bean.getApplyTeams());
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().team6Agreement(GsonUtil.getGsonUtil().getgson().toJson(tBean)));
        }
    }
}
