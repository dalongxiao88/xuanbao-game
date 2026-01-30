package come.tool.Stall;

import org.come.server.GameServer;
import org.come.bean.LoginResult;
import org.come.until.GsonUtil;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class StallBuyAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        try {
            StallBuy stallBuy = (StallBuy)GsonUtil.getGsonUtil().getgson().fromJson(message, StallBuy.class);
            StallPool.getPool().BuyStall((LoginResult)GameServer.getAllLoginRole().get(ctx), stallBuy);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
