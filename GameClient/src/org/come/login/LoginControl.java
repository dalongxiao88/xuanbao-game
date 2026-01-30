package org.come.login;

import org.come.bean.LoginResult;
import io.netty.util.internal.StringUtil;
import org.come.bean.LoginRoleInfo;
import org.come.until.GsonUtil;
import org.come.entity.ChooseArea;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.socket.GameClient;
import org.come.test.Main;
import org.come.action.FromServerAction;

public class LoginControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        if ("rigistersuccess".equals(type)) {
            this.RegisterSuccess();
        }
        else if ("rigistererror".equals(type)) {
            this.RegisterError(mes);
        }
        else if ("loginsuccess".equals(type)) {
            this.loginSuccess(mes);
        }
        else if ("loginerror".equals(type)) {
            this.loginError(mes);
        }
        else if ("createsuccess".equals(type)) {
            this.createRoleSuccess(mes);
        }
        else if ("createerror".equals(type)) {
            this.createRoleError(mes);
        }
    }
    
    public void RegisterSuccess() {
        Main.frame.getLoginJpanel().getLoginView().getRegisterView().setVisible(false);
        Main.frame.getLoginJpanel().getLoginView().setMsg("注册成功");
    }
    
    public void RegisterError(String mes) {
        Main.frame.getLoginJpanel().getLoginView().getRegisterView().getLabMsgTip().setText(mes);
    }
    
    public void loginSuccess(String info) {
        String[] mesArr = info.split("\\|");
        String atid = mesArr[1];
        String mes = mesArr[0];
        GameClient.atid = atid;
        System.out.println("登录成功返回代理id: " + atid);
        Main.frame.getLoginJpanel().setLogin_uid(mes);
        LoginRoleInfo roleInfo = Main.frame.getRoleInfo();
        if (roleInfo.getRoleId() != null) {
            String sendmes = Agreement.getAgreement().enterGameAgreement(roleInfo.getRoleId().toString());
            SendMessageUntil.toServer(sendmes);
        }
        else {
            Main.frame.getRoleInfo().setAtId(GameClient.atid);
            Main.frame.getLoginJpanel().setLogin_uid(mes);
            ChooseArea chooseArea = new ChooseArea();
            chooseArea.setQid(GameClient.tempreId.toString());
            chooseArea.setUid(Main.frame.getLoginJpanel().getLogin_uid());
            Main.frame.getLoginJpanel().setRa_id(GameClient.tempreId.toString());
            String sendmes2 = Agreement.getAgreement().uidAndQidForRoleAgreement(GsonUtil.getGsonUtil().getgson().toJson(chooseArea));
            SendMessageUntil.toServer(sendmes2);
        }
    }
    
    public void loginError(String mes) {
        if (StringUtil.isNullOrEmpty(mes)) {
            if (Main.frame.getLoginJpanel().getLoginView() != null) {
                Main.frame.getLoginJpanel().getLoginView().setMsg("登录失败，请检查你的用户名密码是否正确");
            }
        }
        else {
            Main.frame.getLoginJpanel().getLoginView().setMsg(mes);
        }
    }
    
    public void createRoleSuccess(String mes) {
        Main.frame.getLoginJpanel().getCreateView().setMsg("创建成功，正在进入游戏");
        LoginResult loginResult = (LoginResult)GsonUtil.getGsonUtil().getgson().fromJson(mes, LoginResult.class);
        String sendmes = Agreement.getAgreement().enterGameAgreement(loginResult.getRole_id().toString());
        SendMessageUntil.toServer(sendmes);
    }
    
    public void createRoleError(String mes) {
        Main.frame.getLoginJpanel().getCreateView().setMsg(mes);
    }
}
