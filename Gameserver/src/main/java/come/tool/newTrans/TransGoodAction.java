package come.tool.newTrans;

import org.come.until.GsonUtil;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class TransGoodAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult login = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (login == null) {
            return;
        }
        GoodTrans2 goodTrans2 = (GoodTrans2)GsonUtil.getGsonUtil().getgson().fromJson(message, GoodTrans2.class);
        TransUtil.TransGood(login.getRolename(), goodTrans2);
    }
}
