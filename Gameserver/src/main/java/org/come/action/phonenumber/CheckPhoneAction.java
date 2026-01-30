package org.come.action.phonenumber;

import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.until.HttpClientSend;
import org.come.server.GameServer;
import org.come.until.GsonUtil;
import org.come.extInterBean.CheckPhoneReqBean;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class CheckPhoneAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        CheckPhoneReqBean req = (CheckPhoneReqBean)GsonUtil.getGsonUtil().getgson().fromJson(message, CheckPhoneReqBean.class);
        String phone = req.getPhone();
        String code = req.getCode();
        String loginCheck = GameServer.getLoginCheck();
        String url = loginCheck + "checkVers.ajax";
        String param = "phone=" + phone + "&code=" + code;
        String sendPost = HttpClientSend.sendPost(url, param);
        if (sendPost.equals("error")) {
            int i = 0;
            while (i < 3) {
                sendPost = HttpClientSend.sendPost(url, param);
                if (sendPost.equals("error")) {
                    ++i;
                }
                else {
                    break;
                }
            }
        }
        else if (sendPost.equals("notphone") || sendPost.equals("notcode")) {
            return;
        }
        String msg = Agreement.getAgreement().checkPhoneRetrunAgreement(sendPost);
        SendMessage.sendMessageToSlef(ctx, msg);
    }
}
