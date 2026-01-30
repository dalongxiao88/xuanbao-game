package come.tool.Stall;

import org.come.until.GsonUtil;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class StallListingAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        try {
            CommodityBean commodity = (CommodityBean)GsonUtil.getGsonUtil().getgson().fromJson(message, CommodityBean.class);
            if (commodity.getState() == 0) {
                StallPool.getPool().removeCommodity(ctx, commodity, loginResult.getBooth_id().intValue());
            }
            else if (commodity.getState() == 1) {
                StallPool.getPool().addCommodity(ctx, commodity, loginResult.getBooth_id().intValue());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
