package org.come.action.role;

import org.come.action.monitor.MonitorUtil;
import java.math.BigDecimal;
import org.come.tool.WriteOut;
import org.come.until.GsonUtil;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class DeductiontaelAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        long jg = Long.parseLong(message);
        if (jg <= 0L) {
            return;
        }
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        long gold = loginResult.getGold().longValue() - jg;
        if (gold < 0L) {
            String v = "扣除银量异常:" + message + ":" + GsonUtil.getGsonUtil().getgson().toJson(loginResult);
            WriteOut.addtxt(v, 9999L);
            gold = 0L;
        }
        loginResult.setGold(new BigDecimal(gold));
        MonitorUtil.getMoney().useD(jg);
    }
}
