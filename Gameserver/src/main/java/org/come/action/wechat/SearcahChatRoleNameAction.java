package org.come.action.wechat;

import org.come.entity.RoleTable;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.LoginResult;
import org.come.server.GameServer;
import org.come.bean.Role_bean;
import org.come.until.AllServiceUtil;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class SearcahChatRoleNameAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        if (message == null) {
            return;
        }
        RoleTable roleInfo = AllServiceUtil.getRoleTableService().selectRoleTableByRoleName(message);
        if (roleInfo != null) {
            Role_bean bean = new Role_bean();
            if (GameServer.getRoleNameMap().get(roleInfo.getRolename()) != null) {
                LoginResult lastInfo = (LoginResult)GameServer.getAllLoginRole().get(GameServer.getRoleNameMap().get(roleInfo.getRolename()));
                bean.setGangname(lastInfo.getGangname());
                bean.setGrade(lastInfo.getGrade());
                bean.setRace_name(lastInfo.getRace_name());
                bean.setRole_id(lastInfo.getRole_id());
                bean.setRolename(lastInfo.getRolename());
                bean.setTitle(lastInfo.getTitle());
            }
            else {
                bean.setGangname(roleInfo.getGangname());
                bean.setGrade(roleInfo.getGrade());
                bean.setRace_name(roleInfo.getRacename());
                bean.setRole_id(roleInfo.getRole_id());
                bean.setRolename(roleInfo.getRolename());
                bean.setTitle(roleInfo.getTitle());
            }
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().searcahChatRoleNameAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean)));
        }
    }
}
