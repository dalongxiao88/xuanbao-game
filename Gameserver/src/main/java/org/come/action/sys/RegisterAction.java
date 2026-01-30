package org.come.action.sys;

import org.come.entity.Ipaddressmac;
import java.util.List;
import org.come.redis.RedisCacheUtil;
import java.math.BigDecimal;
import org.come.until.GsonUtil;
import org.come.entity.UserTable;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.until.AllServiceUtil;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class RegisterAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        if (GameServer.registerOnOff == 0) {
            return;
        }
        String clientIP = LoginAction.getIP(ctx);
        List<Ipaddressmac> ipaddressmac = AllServiceUtil.getIpaddressmacService().selectIpaddressmac(clientIP);
        if (ipaddressmac.size() != 0) {
            String msg = Agreement.getAgreement().erroRigisterAgreement("");
            SendMessage.sendMessageToSlef(ctx, msg);
            return;
        }
        UserTable userTable = (UserTable)GsonUtil.getGsonUtil().getgson().fromJson(message, UserTable.class);
        if ("".equals(userTable.getUsername()) || "".equals(userTable.getUserpwd()) || "".equals(userTable.getSafety())) {
            String msg2 = Agreement.getAgreement().erroRigisterAgreement("信息不可为空");
            SendMessage.sendMessageToSlef(ctx, msg2);
            return;
        }
        String checkUserAcc = this.checkUserAcc(userTable.getUsername());
        if (!"true".equals(checkUserAcc)) {
            String msg3 = Agreement.getAgreement().erroRigisterAgreement(checkUserAcc);
            SendMessage.sendMessageToSlef(ctx, msg3);
            return;
        }
        if (userTable.getUserpwd().length() < 8 || userTable.getUserpwd().length() > 16) {
            String msg3 = Agreement.getAgreement().erroRigisterAgreement("密码不可少于8位,不可超过16位");
            SendMessage.sendMessageToSlef(ctx, msg3);
            return;
        }
        if (userTable.getSafety().length() < 6 || userTable.getSafety().length() > 16) {
            String msg3 = Agreement.getAgreement().erroRigisterAgreement("安全码格式有误");
            SendMessage.sendMessageToSlef(ctx, msg3);
            return;
        }
        String tuiji = userTable.getTuiji();
        if (tuiji == null || "".equals(tuiji)) {
            String msg4 = Agreement.getAgreement().erroRigisterAgreement("请输入推荐码");
            SendMessage.sendMessageToSlef(ctx, msg4);
            return;
        }
        List<BigDecimal> sid = AllServiceUtil.getOpenareatableService().selectTuijiNum(tuiji);
        if (sid.size() == 0) {
            String msg5 = Agreement.getAgreement().erroRigisterAgreement("没有找到该推荐码,请检查是否正确!");
            SendMessage.sendMessageToSlef(ctx, msg5);
            return;
        }
        userTable.setQid((BigDecimal)sid.get(0));
        UserTable sameUser = AllServiceUtil.getUserTableService().findUserByUserNameAndUserPassword(userTable.getUsername(), null);
        List<UserTable> sameUser2 = AllServiceUtil.getUserTableService().findUserByPhoneNum(userTable.getPhonenumber());
        if (sameUser == null && sameUser2.size() < 5) {
            userTable.setRegisterip(clientIP);
            userTable.setUser_id(RedisCacheUtil.getUser_pk());
            int isSuccess = AllServiceUtil.getUserTableService().insertIntoUser(userTable);
            if (isSuccess > 0) {
                String msg6 = Agreement.getAgreement().successRigisterAgreement();
                SendMessage.sendMessageToSlef(ctx, msg6);
            }
        }
        else {
            String str = "";
            if (sameUser != null) {
                str = "该账号已存在";
            }
            else if (sameUser2.size() >= 5) {
                str = "该手机号已注册5个账号";
            }
            String msg6 = Agreement.getAgreement().erroRigisterAgreement(str);
            SendMessage.sendMessageToSlef(ctx, msg6);
        }
    }
    
    public String checkUserAcc(String acc) {
        if (acc.length() < 8 || acc.length() > 20) {
            return "账号格式必须为8-20个字母和数字";
        }
        if (this.check(acc)) {
            return "true";
        }
        return "账号不可为纯字母、纯数字或带有符号!";
    }
    
    public boolean check(String acc) {
        String reg = "^(\\d+[A-Za-z]+[A-Za-z0-9]*)|([A-Za-z]+\\d+[A-Za-z0-9]*)$";
        return acc.matches(reg);
    }
}
