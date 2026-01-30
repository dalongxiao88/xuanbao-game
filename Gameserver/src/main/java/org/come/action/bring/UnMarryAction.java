package org.come.action.bring;

import org.come.entity.RoleTable;
import org.come.until.AllServiceUtil;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class UnMarryAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        ChannelHandlerContext ctx2 = null;
        LoginResult otherRole = null;
        if (roleInfo.getMarryObject() != null) {
            ctx2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(roleInfo.getMarryObject());
        }
        if (ctx2 != null) {
            otherRole = (LoginResult)GameServer.getAllLoginRole().get(ctx2);
        }
        if (otherRole != null) {
            SendMessage.sendMessageByRoleName(otherRole.getRolename(), Agreement.getAgreement().unMarry(roleInfo.getRolename()));
            otherRole.setMarryObject(null);
        }
        else {
            try {
                RoleTable marryRole = AllServiceUtil.getRoleTableService().selectRoleTableByRoleName(roleInfo.getMarryObject());
                if (marryRole != null) {
                    marryRole.setMarryObject(null);
                    if (marryRole.getTitle().indexOf(roleInfo.getRolename()) != -1) {
                        marryRole.setTitle(null);
                    }
                    AllServiceUtil.getRoleTableService().updateByPrimaryKey(marryRole);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        SendMessage.sendMessageByRoleName(roleInfo.getRolename(), Agreement.getAgreement().unMarry(roleInfo.getMarryObject()));
        roleInfo.setMarryObject(null);
    }
}
