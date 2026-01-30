package org.come.action.gang;

import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import come.tool.newGang.GangUtil;
import org.come.bean.GangListBean;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class GangListAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        GangListBean gangListBean = new GangListBean();
        gangListBean.setGangs(GangUtil.getGangs());
        String msg = Agreement.getAgreement().ganglistAgreement(GsonUtil.getGsonUtil().getgson().toJson(gangListBean));
        SendMessage.sendMessageToSlef(ctx, msg);
    }
}
