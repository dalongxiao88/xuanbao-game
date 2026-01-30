package org.come.action.baby;

import org.come.entity.Baby;
import java.util.List;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.until.AllServiceUtil;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.bean.BabyListBean;
import org.come.action.IAction;

public class BabyAction implements IAction
{
    private BabyListBean babyListBean;
    
    public BabyAction() {
        this.babyListBean = new BabyListBean();
    }
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        List<Baby> babys = AllServiceUtil.getBabyService().selectBabyByRolename(loginResult.getRole_id());
        this.babyListBean.setBabyList(babys);
        String msg = Agreement.getAgreement().getBaby(GsonUtil.getGsonUtil().getgson().toJson(this.babyListBean));
        SendMessage.sendMessageToSlef(ctx, msg);
    }
}
