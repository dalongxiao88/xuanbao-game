package come.tool.Stall;

import java.math.BigDecimal;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import org.come.until.GsonUtil;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class StallAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        try {
            Stall stall = (Stall)GsonUtil.getGsonUtil().getgson().fromJson(message, Stall.class);
            if (stall.getState() == StallPool.NO) {
                stall.setState(StallPool.NO);
                LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
                stall.setRoleid(loginResult.getRole_id());
                stall.setRole(loginResult.getRolename());
                StallPool.getPool().addStall(stall, ctx);
                loginResult.setBooth_id(new BigDecimal(stall.getId()));
            }
            else if (stall.getState() == StallPool.OFF) {
                LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
                loginResult.setBooth_id(null);
                StallPool.getPool().RetreatStall(stall.getId());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
