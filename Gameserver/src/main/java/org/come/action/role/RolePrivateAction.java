package org.come.action.role;

import come.tool.Role.RoleData;
import org.come.until.GsonUtil;
import come.tool.Role.PrivateData;
import come.tool.Role.RolePool;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class RolePrivateAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        PrivateData privateData = (PrivateData)GsonUtil.getGsonUtil().getgson().fromJson(message, PrivateData.class);
        privateData.setBorn(roleData.getPrivateData().getBorn());
        roleData.upPrivateData(privateData);
    }
}
