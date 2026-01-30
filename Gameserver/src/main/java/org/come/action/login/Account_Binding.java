package org.come.action.login;

import java.util.List;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import java.math.BigDecimal;
import org.come.redis.RedisCacheUtil;
import org.come.entity.UserTable;
import java.net.InetSocketAddress;
import org.come.until.AllServiceUtil;
import org.come.bean.BindingReturn;
import org.come.until.GsonUtil;
import org.come.bean.AccountBinding;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class Account_Binding implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        AccountBinding accountBinding = (AccountBinding)GsonUtil.getGsonUtil().getgson().fromJson(message, AccountBinding.class);
        BindingReturn bindingReturn = new BindingReturn();
        String type = accountBinding.getType();
        if ("getbinding".equals(type)) {
            String flag = accountBinding.getFlag();
            UserTable usertable = AllServiceUtil.getUserTableService().selectByFlag(flag);
            if (usertable == null) {
                bindingReturn.setType("gobinding");
            }
            else {
                Short activity = usertable.getActivity();
                if ((short)activity != 0) {
                    bindingReturn.setType("accountError");
                    bindingReturn.setMessage("该账号在该区已被封");
                }
                else {
                    bindingReturn.setType("goGame");
                    bindingReturn.setUsertable(usertable);
                }
            }
        }
        else if ("binding".equals(type)) {
            String username = accountBinding.getUsername();
            String password = accountBinding.getPassword();
            String safely = accountBinding.getSafely();
            String flag2 = accountBinding.getFlag();
            UserTable usertable2 = AllServiceUtil.getUserTableService().selectByBinding(username, password, safely);
            if (usertable2 != null) {
                Short activity2 = usertable2.getActivity();
                if ((short)activity2 != 0) {
                    bindingReturn.setType("accountError");
                    bindingReturn.setMessage("该账号已被封");
                }
                else if (usertable2.getFlag() != null) {
                    bindingReturn.setType("accountError");
                    bindingReturn.setMessage("该账号已绑定");
                }
                else {
                    usertable2.setFlag(flag2);
                    int updateByBinding = AllServiceUtil.getUserTableService().updateByBinding(usertable2);
                    if (updateByBinding > 0) {
                        bindingReturn.setType("goGame");
                        bindingReturn.setUsertable(usertable2);
                    }
                    else {
                        bindingReturn.setType("accountError");
                        bindingReturn.setMessage("绑定失败");
                    }
                }
            }
            else {
                bindingReturn.setType("accountError");
                bindingReturn.setMessage("该区未找到该账号");
            }
        }
        else if ("autoBinding".equals(type)) {
            InetSocketAddress insocket = (InetSocketAddress)ctx.channel().remoteAddress();
            String username2 = accountBinding.getUsername();
            String password2 = accountBinding.getPassword();
            String safely2 = accountBinding.getSafely();
            String flag3 = accountBinding.getFlag();
            String clientIP = insocket.getAddress().getHostAddress();
            String tuiji = accountBinding.getTuiji();
            String phone = accountBinding.getPhone();
            UserTable usertable3 = AllServiceUtil.getUserTableService().selectByFlag(flag3);
            if (usertable3 != null) {
                return;
            }
            while (true) {
                UserTable result = AllServiceUtil.getUserTableService().findUserByUserNameAndUserPassword(username2, null);
                if (result == null) {
                    break;
                }
                else {
                    username2 += "zr";
                }
            }
            List<BigDecimal> sid = AllServiceUtil.getOpenareatableService().selectTuijiNum(tuiji);
            UserTable userTable = new UserTable();
            userTable.setUser_id(RedisCacheUtil.getUser_pk());
            userTable.setUsername(username2);
            userTable.setUserpwd(password2);
            userTable.setSafety(safely2);
            userTable.setFlag(flag3);
            userTable.setRegisterip(clientIP);
            userTable.setTuiji(tuiji);
            userTable.setQid((BigDecimal)sid.get(0));
            userTable.setPhonenumber(phone);
            int isSuccess = AllServiceUtil.getUserTableService().insertIntoUser(userTable);
            if (isSuccess > 0) {
                bindingReturn.setType("goGame");
                bindingReturn.setUsertable(userTable);
            }
            else {
                bindingReturn.setType("accountError");
                bindingReturn.setMessage("自动绑定失败");
            }
        }
        String result2 = GsonUtil.getGsonUtil().getgson().toJson(bindingReturn);
        String res = Agreement.getAgreement().Account_BindingAgreement(result2);
        SendMessage.sendMessageToSlef(ctx, res);
    }
}
