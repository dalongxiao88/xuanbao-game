package org.come.action.mount;

import come.tool.Role.RoleData;
import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;
import org.come.entity.Mount;
import come.tool.Role.RolePool;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class MountUpdateAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        RoleData data = RolePool.getRoleData(loginResult.getRole_id());
        Mount mount = (Mount)GsonUtil.getGsonUtil().getgson().fromJson(message, Mount.class);
        Mount mountRedis = AllServiceUtil.getMountService().selectMountsByMID(mount.getMid());
        if (mountRedis == null || data == null || loginResult.getRole_id().compareTo(mountRedis.getRoleid()) != 0) {
            return;
        }
        mountRedis.setSid(mount.getSid());
        mountRedis.setOthrersid(mount.getOthrersid());
        mountRedis.setSid3(mount.getSid3());
        mountRedis.setShouhu(mount.getShouhu());
        mountRedis.setSh(mount.getSh());
        AllServiceUtil.getMountService().updateMountRedis(mountRedis);
        data.MPet(mount, true);
    }
}
