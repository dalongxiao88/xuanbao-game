package org.come.action.sys;

import org.come.tool.WriteOut;
import java.net.InetSocketAddress;
import org.come.entity.UserTable;
import org.come.entity.Ipaddressmac;
import java.util.List;
import org.come.until.sendsms;
import org.come.bean.LoginResult;
import java.util.ArrayList;
import org.come.until.GsonUtil;
import org.come.bean.LoginUserBean;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.until.AllServiceUtil;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class LoginAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        if (GameServer.OPEN) {
            return;
        }
        String clientIP = getIP(ctx);
        List<Ipaddressmac> ipaddressmac = AllServiceUtil.getIpaddressmacService().selectIpaddressmac(clientIP);
        if (ipaddressmac.size() != 0) {
            String msg = Agreement.getAgreement().erroLoginAgreement();
            SendMessage.sendMessageToSlef(ctx, msg);
            return;
        }
        LoginUserBean loginUserBean = (LoginUserBean)GsonUtil.getGsonUtil().getgson().fromJson(message, LoginUserBean.class);
        String username = loginUserBean.getUsername();
        String userpwd = loginUserBean.getPassword();
        String serverMes = loginUserBean.getServerMeString();
        serverMes = null;
        if (userpwd == null || userpwd.equals("")) {
            return;
        }
        UserTable userTable = AllServiceUtil.getUserTableService().findUserByUserNameAndUserPassword(username, userpwd);
        if (userTable != null) {
            if (userTable.getSafety() == null) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().bindingMobileAgreement("绑定"));
                return;
            }
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
            List<LoginResult> list = AllServiceUtil.getUserTableService().findRoleByUserNameAndPassword(username, userpwd, serverMes);
            if (null == list || list.size() == 0) {
                list = new ArrayList<>();
                LoginResult loginResult = new LoginResult();
                loginResult.setUser_id(userTable.getUser_id());
                loginResult.setUserName(username);
                loginResult.setUserPwd(userpwd);
                list.add(loginResult);
            }
            String flag = "0";
            if (GameServer.isCode && userTable.getPhonenumber() != null && !"".equals(userTable.getPhonenumber())) {
                String sendUNtil = sendsms.sendUNtil(userTable.getPhonenumber());
                if (sendUNtil.equals("error")) {
                    flag = "-1";
                }
                else if (sendUNtil.equals("logup")) {
                    flag = "-1";
                }
                else {
                    flag = Integer.parseInt(sendUNtil) + "";
                }
            }
            String selectAtid = AllServiceUtil.getOpenareatableService().selectAtid(userTable.getQid() + "");
            int Mapid = 100;
            String mes = Agreement.getAgreement().successLoginAgreement(userTable.getUser_id() + "|" + selectAtid + "|" + flag + "|" + Mapid);
            SendMessage.sendMessageToSlef(ctx, mes);
        }
        else {
            String msg2 = Agreement.getAgreement().erroLoginAgreement();
            SendMessage.sendMessageToSlef(ctx, msg2);
        }
    }
    
    public static String getIP(ChannelHandlerContext ctx) {
        String IP = null;
        try {
            InetSocketAddress insocket = (InetSocketAddress)ctx.channel().remoteAddress();
            IP = insocket.getAddress().getHostAddress();
        }
        catch (Exception e) {
            WriteOut.addtxt("IP获取异常", 9999L);
        }
        return IP;
    }
}
