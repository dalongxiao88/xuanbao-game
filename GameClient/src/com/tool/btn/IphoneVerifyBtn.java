package com.tool.btn;

import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.bean.PhoneNumberSGBean;
import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import java.awt.Color;
import org.come.Jpanel.IphoneVerifyPanel;

public class IphoneVerifyBtn extends MoBanBtn
{
    private int caozuo;
    private IphoneVerifyPanel iphoneVerifyPanel;
    
    public IphoneVerifyBtn(String iconpath, int type, int caozuo, IphoneVerifyPanel iphoneVerifyPanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.iphoneVerifyPanel = iphoneVerifyPanel;
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.setForeground(Color.white);
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.caozuo != 1 && this.caozuo != 2) {
            if (this.caozuo == 3) {
                String verification = this.iphoneVerifyPanel.getVerificationText().getText();
                if (verification == null || "".equals(verification) || this.iphoneVerifyPanel.getVerification().equals("error") || !this.iphoneVerifyPanel.getVerification().equals(verification)) {
                    ZhuFrame.getZhuJpanel().addPrompt2("验证码为空或者不正确");
                    return;
                }
                PhoneNumberSGBean numberSGBean = new PhoneNumberSGBean();
                String iphone = this.iphoneVerifyPanel.getIphoneText().getText();
                if (iphone == null || "".equals(iphone) || !iphone.equals(this.iphoneVerifyPanel.getIphoneNumber())) {
                    ZhuFrame.getZhuJpanel().addPrompt2("手机号为空或者不正确");
                    return;
                }
                numberSGBean.setPhone(iphone);
                String safety = this.iphoneVerifyPanel.getSafetyText().getText();
                if (safety == null || "".equals(safety)) {
                    ZhuFrame.getZhuJpanel().addPrompt2("安全码不能为空");
                    return;
                }
                numberSGBean.setSafenumber(safety);
                String sendmes = null;
                if (this.iphoneVerifyPanel.getTypeMenu() == 1) {
                    sendmes = Agreement.getAgreement().PhoneBangAgreement(GsonUtil.getGsonUtil().getgson().toJson(numberSGBean));
                }
                else if (this.iphoneVerifyPanel.getTypeMenu() == 2) {
                    sendmes = Agreement.getAgreement().UnPhoneBangAgreement(GsonUtil.getGsonUtil().getgson().toJson(numberSGBean));
                }
                SendMessageUntil.toServer(sendmes);
            }
            else if (this.caozuo == 4) {
                this.iphoneVerifyPanel.sendIphonegetVerification();
            }
        }
        else {
            this.iphoneVerifyPanel.changeMenu(this.caozuo);
        }
    }
}
