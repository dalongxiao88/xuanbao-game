package org.come.extInterface;

import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;
import org.come.extInterBean.SalesGoodsChangeOrderReqBean;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class SalesGoodsChangeOrder implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        SalesGoodsChangeOrderReqBean request = (SalesGoodsChangeOrderReqBean)GsonUtil.getGsonUtil().getgson().fromJson(message, SalesGoodsChangeOrderReqBean.class);
        String Sale_id = request.getSale_id();
        String type = request.getType();
        String Sale_id_statues = "Sale_id_statues=error";
        if (Sale_id != null && type != null) {
            AllServiceUtil.getSalegoodsService().updateFlag(new BigDecimal(Sale_id), Integer.valueOf(type));
            Sale_id_statues = "Sale_id_statues=success";
        }
        String msg = Agreement.getAgreement().salesGoodsChangeOrderAgreement(Sale_id_statues);
        SendMessage.sendMessageToSlef(ctx, msg);
    }
}
