package org.come.action.buy;

import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.entity.Goodstable;
import io.netty.channel.ChannelHandlerContext;

public class AddGoodUtil
{
    public static void addGood(ChannelHandlerContext ctx, Goodstable goodstable) {
        if (goodstable == null) {
            return;
        }
        String msg = Agreement.getAgreement().AddGood(GsonUtil.getGsonUtil().getgson().toJson(goodstable));
        SendMessage.sendMessageToSlef(ctx, msg);
    }
}
