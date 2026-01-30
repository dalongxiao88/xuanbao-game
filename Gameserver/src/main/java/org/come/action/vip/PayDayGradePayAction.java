package org.come.action.vip;

import org.come.entity.ChongjipackBean;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.until.GsonUtil;
import org.come.server.GameServer;
import java.util.List;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class PayDayGradePayAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        List<ChongjipackBean> chongjipack = (List<ChongjipackBean>)GameServer.getPackgradecontrol().get(Integer.valueOf(4));
        String mes = GsonUtil.getGsonUtil().getgson().toJson(chongjipack);
        String sendmes = Agreement.getAgreement().PaydaygradepayAgreement(mes);
        SendMessage.sendMessageToSlef(ctx, sendmes);
    }
}
