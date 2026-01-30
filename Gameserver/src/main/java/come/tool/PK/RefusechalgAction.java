package come.tool.PK;

import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class RefusechalgAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult login = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (login == null) {
            return;
        }
        PkMatch pkMatch = PKPool.getPkPool().seekPkMatch(login.getRole_id());
        if (pkMatch != null) {
            PKPool.getPkPool().cancelPkMatch(pkMatch);
        }
    }
}
