package org.come.action.buy;

import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.model.Shop;
import java.math.BigDecimal;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class ShopPriceAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        String[] v = message.split("\\|");
        int type = Integer.parseInt(v[0]);
        BigDecimal sid = new BigDecimal(v[1]);
        if (type != 0 && type == 1) {
            Shop shop = (Shop)GameServer.getAllShopGoods().get(sid.toString());
            if (shop != null) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().ShopPriceAgreement("0|" + message + "|" + shop.getPrice()));
            }
        }
    }
}
