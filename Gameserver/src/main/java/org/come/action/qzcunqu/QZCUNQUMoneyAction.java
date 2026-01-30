package org.come.action.qzcunqu;

import org.come.until.GsonUtil;
import org.come.action.monitor.MonitorUtil;
import come.tool.Stall.AssetUpdate;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import java.math.RoundingMode;
import java.math.BigDecimal;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class QZCUNQUMoneyAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        String[] v = message.split("=");
        if (v.length != 2) {
            return;
        }
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        if (v[0].startsWith("CUN")) {
            BigDecimal money = new BigDecimal(v[1]);
            BigDecimal sxmoney = money.multiply(new BigDecimal("0.01")).setScale(0, RoundingMode.HALF_UP);
            if (money.compareTo(new BigDecimal("100")) < 0) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("存钱最少100银两"));
                return;
            }
            if (loginResult.getGold().compareTo(money.add(sxmoney)) < 0) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("自己有多少银两心里没点数吗？"));
                return;
            }
            AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
            AssetUpdate assetUpdate2 = new AssetUpdate(AssetUpdate.USEGOOD);
            assetUpdate.updata("D=-" + money.add(sxmoney));
            loginResult.setGold(loginResult.getGold().subtract(money.add(sxmoney)));
            MonitorUtil.getMoney().useD(money.add(sxmoney).longValue());
            loginResult.setMoneyshop(loginResult.getMoneyshop().add(money));
            assetUpdate.setMsg("#G存入金钱:#R" + money + "#G，扣除:#R" + sxmoney + "#G手续费。");
            assetUpdate2.updata("CUN=" + money);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
        }
        else if (v[0].startsWith("QU")) {
            BigDecimal money = new BigDecimal(v[1]);
            if (loginResult.getMoneyshop().compareTo(money) < 0) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("自己有多少银两存款心里没点数吗"));
                return;
            }
            AssetUpdate assetUpdate3 = new AssetUpdate(AssetUpdate.USEGOOD);
            AssetUpdate assetUpdate4 = new AssetUpdate(AssetUpdate.USEGOOD);
            assetUpdate3.updata("D=" + money);
            loginResult.setGold(loginResult.getGold().add(money));
            loginResult.setMoneyshop(loginResult.getMoneyshop().subtract(money));
            assetUpdate3.setMsg("#G取出金钱:#R" + money);
            assetUpdate4.updata("QU=-" + money);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate3)));
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate4)));
        }
    }
}
