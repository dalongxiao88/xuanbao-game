package org.come.action.reward;

import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.bean.RewardDrawingBean;
import java.util.Date;
import org.come.entity.RewardHall;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;
import org.come.entity.Goodstable;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class ThrowinarticleAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        Goodstable goodstable = (Goodstable)GsonUtil.getGsonUtil().getgson().fromJson(message, Goodstable.class);
        Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(goodstable.getRgid());
        if (good == null) {
            return;
        }
        if ((int)good.getUsetime() < (int)goodstable.getUsetime()) {
            return;
        }
        good.setUsetime(goodstable.getUsetime());
        goodstable = good;
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        RewardHall hall = new RewardHall();
        hall.setGoodstable(GsonUtil.getGsonUtil().getgson().toJson(goodstable));
        hall.setThrowtime(new Date());
        hall.setRoleId(roleInfo.getRole_id());
        AllServiceUtil.getRewardHallMallService().insert(hall);
        GameServer.rewardList.add(hall);
        RewardDrawingBean bean = new RewardDrawingBean();
        bean.setRewardHall(hall);
        SendMessage.sendMessageToAllRoles(Agreement.getAgreement().drawnitemsAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean)));
    }
}
