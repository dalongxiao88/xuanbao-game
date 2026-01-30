package org.come.bean;

import org.come.entity.UserTable;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.login.BindAccountTipView;
import org.come.test.Main;
import org.come.until.GsonUtil;
import org.come.action.FromServerAction;

public class Account_BindingControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        BindingReturn bindingReturn = (BindingReturn)GsonUtil.getGsonUtil().getgson().fromJson(mes, BindingReturn.class);
        String returnType = bindingReturn.getType();
        if ("accountError".equals(returnType)) {
            BindAccountTipView accountTipView = Main.frame.getLoginJpanel().getAreaView().getAccountTipView();
            if (accountTipView == null) {
                accountTipView = new BindAccountTipView(Main.frame.getLoginJpanel());
                accountTipView.setBounds(150, 220, 530, 160);
                Main.frame.getLoginJpanel().getAreaView().setAccountTipView(accountTipView);
                Main.frame.getLoginJpanel().getAreaView().add(accountTipView, 0);
            }
            accountTipView.setVisible(false);
            Main.frame.getLoginJpanel().getAreaView().getLabMsgTip().setText(bindingReturn.getMessage());
        }
        else if ("gobinding".equals(returnType)) {
            BindAccountTipView accountTipView = Main.frame.getLoginJpanel().getAreaView().getAccountTipView();
            if (accountTipView == null) {
                accountTipView = new BindAccountTipView(Main.frame.getLoginJpanel());
                accountTipView.setBounds(150, 220, 530, 160);
                Main.frame.getLoginJpanel().getAreaView().setAccountTipView(accountTipView);
                Main.frame.getLoginJpanel().getAreaView().add(accountTipView, 0);
            }
            else {
                accountTipView.setVisible(!accountTipView.isVisible());
            }
        }
        else if ("goGame".equals(returnType)) {
            BindAccountTipView accountTipView = Main.frame.getLoginJpanel().getAreaView().getAccountTipView();
            if (accountTipView == null) {
                accountTipView = new BindAccountTipView(Main.frame.getLoginJpanel());
                accountTipView.setBounds(150, 220, 530, 160);
                Main.frame.getLoginJpanel().getAreaView().setAccountTipView(accountTipView);
                Main.frame.getLoginJpanel().getAreaView().add(accountTipView, 0);
            }
            accountTipView.setVisible(false);
            UserTable usertable = bindingReturn.getUsertable();
            String res = GsonUtil.getGsonUtil().getgson().toJson(usertable);
            String sendmes = Agreement.getAgreement().Account_GologinAgreement(res);
            SendMessageUntil.toServer(sendmes);
        }
    }
}
