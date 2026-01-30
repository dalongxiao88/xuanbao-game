package org.come.action.reward;

import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.bean.RewardListBean;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class ObtainarticleAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        RewardListBean bean = new RewardListBean();
        bean.setRewardHalls(GameServer.rewardList);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().obtainarticleAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean)));
    }
}
