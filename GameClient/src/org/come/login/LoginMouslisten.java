package org.come.login;

import org.come.bean.AccountBinding;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.entity.AreaQuery;
import org.come.test.Main;
import java.net.URI;
import org.come.socket.GameClient;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoginMouslisten implements MouseListener
{
    private int i;
    private SpriteBtn btn;
    private LoginJpanel loginJpanel;
    
    public LoginMouslisten(int i, SpriteBtn btn, LoginJpanel loginJpanel) {
        this.i = i;
        this.btn = btn;
        this.loginJpanel = loginJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (this.btn.isChoose()) {
            if (this.btn.getZhen() != 2) {
                this.btn.btn(2);
                if (this.i == 0) {
                    SpriteBtn btn = new SpriteBtn("resource/NewUi/按钮_勾选.钩子", 399, 379, true);
                    btn.setBounds(400, 370, 19, 19);
                    this.loginJpanel.getLoginView().setBtngouzi1(btn);
                }
                else if (this.i == 1) {
                    SpriteBtn btn = new SpriteBtn("resource/NewUi/小透明.png", 10, 338, true);
                    btn.setBounds(362, 134, 19, 19);
                    this.loginJpanel.getLoginView().setBtngouzi2(btn);
                    KeyView keyView = this.loginJpanel.getLoginView().getKeyView();
                    if (keyView == null) {
                        keyView = new KeyView(this.loginJpanel.getLoginView());
                        keyView.setBounds(150, 440, 700, 170);
                        this.loginJpanel.getLoginView().setKeyView(keyView);
                        this.loginJpanel.getLoginView().add(keyView, 0);
                    }
                    keyView.setVisible(true);
                }
            }
            else {
                this.btn.btn(0);
                if (this.i == 0) {
                    this.loginJpanel.getLoginView().setBtngouzi1(null);
                }
                else if (this.i == 1) {
                    this.loginJpanel.getLoginView().setBtngouzi2(null);
                    KeyView keyView2 = this.loginJpanel.getLoginView().getKeyView();
                    if (keyView2 != null) {
                        keyView2.setVisible(false);
                    }
                }
            }
        }
        else {
            this.btn.btn(2);
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        if (!this.btn.isChoose()) {
            this.btn.btn(0);
            if (this.i == -1) {
                System.exit(0);
            }
            else if (this.i == 2) {
                RegisterView registerView = this.loginJpanel.getLoginView().getRegisterView();
                if (registerView == null) {
                    registerView = new RegisterView(this.loginJpanel);
                    registerView.setBounds(50, 100, 424, 406);
                    this.loginJpanel.getLoginView().setRegisterView(registerView);
                    this.loginJpanel.getLoginView().add(registerView, 0);
                }
                else {
                    registerView.setVisible(!registerView.isVisible());
                }
            }
            else if (this.i == 3) {
                try {
                    Desktop desktop = Desktop.getDesktop();
                    URI uri = new URI("" + GameClient.potAndIpStrings[5]);
                    desktop.browse(uri);
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            else if (this.i == 4) {
                DownView downView = this.loginJpanel.getLoginView().getDownView();
                if (downView == null) {
                    downView = new DownView(this.loginJpanel);
                    downView.setBounds(400, 342, 213, 122);
                    this.loginJpanel.getLoginView().setDownView(downView);
                    this.loginJpanel.getLoginView().add(downView, 0);
                }
                else {
                    downView.setVisible(!downView.isVisible());
                }
            }
            else if (this.i == 5) {
                this.loginJpanel.getLoginView().login();
            }
            else if (this.i == 6) {
                this.loginJpanel.getLoginView().getRegisterView().zc();
            }
            else if (this.i == 7) {
                IphoneView iphoneView = this.loginJpanel.getLoginView().getIphoneView();
                if (iphoneView.getArrBean().equals(iphoneView.getVerificationText().getText())) {
                    Main.frame.getLoginJpanel().setLogin_uid(GameClient.userid);
                    AreaQuery areaQuery = new AreaQuery();
                    areaQuery.setRe_id("");
                    areaQuery.setReName("");
                    areaQuery.setUserId(GameClient.userid);
                    String sendmes = Agreement.getAgreement().getareaAgreement(GsonUtil.getGsonUtil().getgson().toJson(areaQuery));
                    SendMessageUntil.toServer(sendmes);
                }
                else {
                    iphoneView.getLabMsgTip().setText("验证码错误");
                }
            }
            else if (this.i == 8) {
                BindAccountView accountView = this.loginJpanel.getAreaView().getAccountView();
                if (accountView == null) {
                    accountView = new BindAccountView(this.loginJpanel);
                    accountView.setBounds(150, 220, 424, 278);
                    this.loginJpanel.getAreaView().setAccountView(accountView);
                    this.loginJpanel.getAreaView().add(accountView, 0);
                }
                else {
                    accountView.setVisible(true);
                }
                this.loginJpanel.getAreaView().getAccountTipView().setVisible(false);
            }
            else if (this.i == 9) {
                AccountBinding accountBinding = new AccountBinding();
                accountBinding.setType("autoBinding");
                accountBinding.setUsername(SendMessageUntil.account.getAc_account());
                accountBinding.setPassword(SendMessageUntil.account.getAc_pasw());
                accountBinding.setSafely(SendMessageUntil.account.getAc_safely());
                accountBinding.setTuiji(SendMessageUntil.account.getAc_tuijian());
                accountBinding.setFlag(SendMessageUntil.account.getAc_flag());
                accountBinding.setPhone(SendMessageUntil.account.getAc_phone());
                String res = GsonUtil.getGsonUtil().getgson().toJson(accountBinding);
                String content = Agreement.getAgreement().Account_BindingAgreement(res);
                SendMessageUntil.toServer(content);
            }
            else if (this.i == 10) {
                BindAccountView accountView = this.loginJpanel.getAreaView().getAccountView();
                String username = accountView.getTextAcc().getText();
                String password = new String(accountView.getTextPwd().getText());
                String safety = new String(accountView.getTextScode().getText());
                AccountBinding accountBinding2 = new AccountBinding();
                accountBinding2.setType("binding");
                accountBinding2.setUsername(username);
                accountBinding2.setPassword(password);
                accountBinding2.setSafely(safety);
                accountBinding2.setFlag(SendMessageUntil.account.getAc_flag());
                String res2 = GsonUtil.getGsonUtil().getgson().toJson(accountBinding2);
                String content2 = Agreement.getAgreement().Account_BindingAgreement(res2);
                SendMessageUntil.toServer(content2);
            }
            else if (this.i == 11) {
                BindAccountView accountView = this.loginJpanel.getAreaView().getAccountView();
                if (accountView == null) {
                    accountView = new BindAccountView(this.loginJpanel);
                    accountView.setBounds(150, 220, 424, 278);
                    this.loginJpanel.getAreaView().setAccountView(accountView);
                    this.loginJpanel.getAreaView().add(accountView, 0);
                }
                accountView.setVisible(false);
            }
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if (this.btn.getZhen() != 2) {
            this.btn.btn(1);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        if (this.btn.getZhen() != 2) {
            this.btn.btn(0);
        }
    }
}
