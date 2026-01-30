package org.come.action.sys;

import org.come.until.GsonUtil;
import java.math.BigDecimal;
import come.tool.Stall.AssetUpdate;
import org.come.action.monitor.MonitorUtil;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class updateXianyuAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (message.split("=")[0].equals("xianyu")) {
            int xyh = (Integer.parseInt(message.split("=")[1]) + Integer.parseInt(message.split("=")[1])) * 2 * 50;
            long xh = Long.parseLong(xyh + "");
            if (xh < 0L) {
                xh = -xh;
            }
            if (xh > loginResult.getCodecard().longValue()) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你仙玉不足" + xh));
                return;
            }
            MonitorUtil.getMoney().useX(xh);
            AssetUpdate assetUpdate = null;
            StringBuffer buffer = new StringBuffer();
            if (assetUpdate == null) {
                assetUpdate = new AssetUpdate(25);
            }
            assetUpdate.setData("X=" + -xh);
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append("#W花费#G[" + xh + "]#W仙玉");
            loginResult.setCodecard(new BigDecimal(loginResult.getCodecard().longValue() - xh));
            assetUpdate.setMsg(buffer.toString());
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
    }
    
    private BigDecimal BigDecimal(String string) {
        return null;
    }
}
