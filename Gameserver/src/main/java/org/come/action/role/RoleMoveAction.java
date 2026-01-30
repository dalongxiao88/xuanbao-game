package org.come.action.role;

import come.tool.Role.RoleData;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.bean.PathPoint;
import org.come.until.GsonUtil;
import org.come.bean.RoleMoveBean;
import come.tool.Role.RolePool;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class RoleMoveAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (roleInfo == null) {
            return;
        }
        RoleData roleData = RolePool.getRoleData(roleInfo.getRole_id());
        if (roleData == null) {
            return;
        }
        RoleMoveBean roleMoveBean = (RoleMoveBean)GsonUtil.getGsonUtil().getgson().fromJson(message, RoleMoveBean.class);
        PathPoint point = (PathPoint)roleMoveBean.getPaths().get(roleMoveBean.getPaths().size() - 1);
        roleInfo.setX(new Long((long)point.getX()));
        roleInfo.setY(new Long((long)point.getY()));
        roleMoveBean.setRole(roleInfo.getRolename());
        String msg = Agreement.getAgreement().moveAgreement(GsonUtil.getGsonUtil().getgson().toJson(roleMoveBean));
        SendMessage.sendMessageToMapRoles(roleInfo.getMapid(), msg);
    }
}
