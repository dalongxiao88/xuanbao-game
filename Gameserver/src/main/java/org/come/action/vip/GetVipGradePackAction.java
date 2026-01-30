package org.come.action.vip;

import org.come.entity.PayvipBean;
import java.util.List;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.until.GsonUtil;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class GetVipGradePackAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        List<PayvipBean> payVipList = GameServer.getPayvipList();
        String mes = GsonUtil.getGsonUtil().getgson().toJson(payVipList);
        String sendmes = Agreement.getAgreement().GetvipgradepackAgreement(mes);
        SendMessage.sendMessageToSlef(ctx, sendmes);
    }
}
