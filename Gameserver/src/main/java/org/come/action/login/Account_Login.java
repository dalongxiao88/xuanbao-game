package org.come.action.login;

import org.come.entity.RoleTableNew;
import java.util.List;
import java.math.BigDecimal;
import org.come.entity.UserTable;
import org.come.entity.RoleTableList;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;
import org.come.bean.LoginUserBean;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class Account_Login implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        System.out.println("进入手机");
        if (GameServer.OPEN) {
            return;
        }
        LoginUserBean user = (LoginUserBean)GsonUtil.getGsonUtil().getgson().fromJson(message, LoginUserBean.class);
        String username = user.getUsername();
        String userpwd = user.getPassword();
        if (userpwd == null || userpwd.equals("")) {
            return;
        }
        UserTable userTable = AllServiceUtil.getUserTableService().findUserByUserNameAndUserPassword(username, userpwd);
        if (userTable != null) {
            String msgs = Agreement.getAgreement().inlineLoginAgreement();
            if (GameServer.getInlineUserNameMap().get(username) != null) {
                ChannelHandlerContext ctx2 = (ChannelHandlerContext)GameServer.getInlineUserNameMap().get(username);
                if (ctx2 != ctx || GameServer.getAllLoginRole().get(ctx2) != null) {
                    SendMessage.sendMessageByUserName(username, msgs);
                    GameServer.userDown((ChannelHandlerContext)GameServer.getInlineUserNameMap().get(username));
                }
            }
            GameServer.getInlineUserNameMap().put(username, ctx);
            GameServer.getSocketUserNameMap().put(ctx, username);
            String selectAtid = AllServiceUtil.getOpenareatableService().selectAtid(userTable.getQid() + "");
            String belongId = AllServiceUtil.getOpenareatableService().selectBelong(userTable.getQid() + "");
            if (belongId == null) {
                return;
            }
            BigDecimal uid = userTable.getUser_id();
            List<RoleTableNew> role = AllServiceUtil.getRegionService().selectRole(uid, Integer.valueOf(belongId));
            RoleTableList list = new RoleTableList();
            list.setRoleList(role);
            list.setAtid(selectAtid);
            list.setUserid(userTable.getUser_id() + "");
            list.setUsername(userTable.getUsername());
            list.setPasw(userTable.getUserpwd());
            String content = GsonUtil.getGsonUtil().getgson().toJson(list);
            String mes = Agreement.getAgreement().successLoginAgreement(content);
            SendMessage.sendMessageToSlef(ctx, mes);
        }
        else {
            String msg = Agreement.getAgreement().erroLoginAgreement();
            SendMessage.sendMessageToSlef(ctx, msg);
        }
    }
}
