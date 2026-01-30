package come.tool.Stall;

import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class StallGetAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        int id = Integer.parseInt(message);
        Stall stall = (Stall)StallPool.getPool().getAllStall().get(Integer.valueOf(id));
        if (stall != null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
        }
    }
}
