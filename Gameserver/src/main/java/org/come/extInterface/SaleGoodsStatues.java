package org.come.extInterface;

import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class SaleGoodsStatues implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String Sale_id) {
        String Type = "Type=4";
        if (Sale_id != null) {
            Integer flag = AllServiceUtil.getSalegoodsService().selectFlag(new BigDecimal(Sale_id));
            if (flag != null) {
                if ((int)flag == 1) {
                    Type = "Type=1";
                }
                else if ((int)flag == 2) {
                    Type = "Type=2";
                }
                else if ((int)flag == 3) {
                    Type = "Type=3";
                }
            }
            else {
                Type = "Type=4";
            }
        }
        String msg = Agreement.getAgreement().saleGoodsStatuesAgreement(Type);
        SendMessage.sendMessageToSlef(ctx, msg);
    }
}
