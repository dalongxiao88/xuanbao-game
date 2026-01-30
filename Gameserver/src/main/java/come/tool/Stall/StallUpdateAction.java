package come.tool.Stall;

import org.come.until.GsonUtil;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class StallUpdateAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        Stall stall = (Stall)GsonUtil.getGsonUtil().getgson().fromJson(message, Stall.class);
        StallPool.getPool().updateStall(stall);
    }
}
