package org.come.action.Fly;

import come.tool.Role.RoleData;
import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;
import org.come.entity.Fly;
import come.tool.Role.RolePool;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class FlyUpdateAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        RoleData data = RolePool.getRoleData(loginResult.getRole_id());
        Fly fly = (Fly)GsonUtil.getGsonUtil().getgson().fromJson(message, Fly.class);
        Fly flyRedis = AllServiceUtil.getFlyService().selectFlysByMID(fly.getMid());
        if (flyRedis == null || data == null || loginResult.getRole_id().compareTo(flyRedis.getRoleid()) != 0) {
            return;
        }
        flyRedis.setFlystate(fly.getFlystate());
        flyRedis.setExp(fly.getExp());
        flyRedis.setFlyname(fly.getFlyname());
        AllServiceUtil.getFlyService().updateFlyRedis(flyRedis);
    }
}
