package org.come.action.festival;

import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.NChatBean;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class HatchaddAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        int hatch = HatchvalueAction.hatch.addAndGet(1);
        if (hatch == 500) {
            NChatBean bean = new NChatBean();
            bean.setId(4);
            bean.setMessage("元旦孵蛋成功，请注意接收奖励！！！");
            String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
            SendMessage.sendMessageToAllRoles(msg);
        }
    }
}
