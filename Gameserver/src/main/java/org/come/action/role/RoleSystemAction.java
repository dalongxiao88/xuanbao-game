package org.come.action.role;

import come.tool.Role.RoleData;
import org.come.until.GsonUtil;
import come.tool.Role.RoleSystem;
import come.tool.Role.RolePool;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class RoleSystemAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        RoleSystem roleSystem = (RoleSystem)GsonUtil.getGsonUtil().getgson().fromJson(message, RoleSystem.class);
        RoleSystem system = roleData.getRoleSystem();
        system.set(roleSystem);
    }
}
