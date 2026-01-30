package com.tool.btn;

import org.come.Jpanel.NewRefiningJpanel;
import org.come.model.Achieve;
import org.come.Jpanel.PartnerMainJpanel;
import org.come.bean.LoginResult;
import org.come.bean.OneArenaRole;
import org.come.Frame.NewRefiningJframe;
import org.come.Frame.ZhuFrame;
import org.come.Frame.PartnerJframe;
import com.tool.role.RoleData;
import org.come.until.FormsManagement;
import org.come.Frame.PartnerArenaExchangeJframe;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.come.Jpanel.PartnerArenaExchangeModelPanel;
import org.come.Jpanel.PartnerArenaModelPanel;
import org.come.Jpanel.PartnerArenaMainPanel;

public class PartnerArenaBtn extends MoBanBtn
{
    private int caozuo;
    private PartnerArenaMainPanel partnerArenaMainPanel;
    private PartnerArenaModelPanel partnerArenaModelPanel;
    private PartnerArenaExchangeModelPanel partnerArenaExchangeModelPanel;
    
    public PartnerArenaBtn(String iconpath, int type, Color[] colors, String text, Font font, int caozuo, PartnerArenaMainPanel partnerArenaMainPanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.partnerArenaMainPanel = partnerArenaMainPanel;
        this.caozuo = caozuo;
    }
    
    public PartnerArenaBtn(String iconpath, int type, Color[] colors, String text, Font font, int caozuo, PartnerArenaModelPanel partnerArenaModelPanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.partnerArenaModelPanel = partnerArenaModelPanel;
        this.caozuo = caozuo;
    }
    
    public PartnerArenaBtn(String iconpath, int type, Color[] colors, String text, Font font, int caozuo, PartnerArenaExchangeModelPanel partnerArenaExchangeModelPanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.partnerArenaExchangeModelPanel = partnerArenaExchangeModelPanel;
        this.caozuo = caozuo;
    }
    
    public PartnerArenaBtn(String iconpath, int type, int caozuo, PartnerArenaMainPanel partnerArenaMainPanel) {
        super(iconpath, type);
        this.partnerArenaMainPanel = partnerArenaMainPanel;
        this.caozuo = caozuo;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.caozuo == 0) {
            String sendmes = Agreement.getAgreement().oneArenaAgreement("3");
            SendMessageUntil.toServer(sendmes);
        }
        else if (this.caozuo == 1) {
            String sendmes = Agreement.getAgreement().oneArenaAgreement("2");
            SendMessageUntil.toServer(sendmes);
        }
        else if (this.caozuo == 2) {
            PartnerArenaExchangeJframe.getPartnerArenaExchangeJframe().getPartnerArenaExchangePanel().showView();
            FormsManagement.showForm(107);
        }
        else if (this.caozuo == 3) {
            OneArenaRole oneArenaRole = this.partnerArenaModelPanel.getOneArenaRole();
            if (oneArenaRole == null) {
                return;
            }
            LoginResult login = RoleData.getRoleData().getLoginResult();
            PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
            int chooseId = mainJpanel.getPalDataChooseId();
            if (chooseId > 0 && !login.getPals().isEmpty()) {
                ZhuFrame.getZhuJpanel().addPrompt2("不能带伙伴参战");
                return;
            }
            String sendmes2 = Agreement.getAgreement().oneArenaAgreement("P" + oneArenaRole.getRoleId());
            SendMessageUntil.toServer(sendmes2);
        }
        else if (this.caozuo == 4) {
            Achieve achieve = this.partnerArenaExchangeModelPanel.getAchieve();
            if (achieve != null) {
                String sendmes3 = Agreement.getAgreement().TaskNAgreement("R4=" + achieve.getId());
                SendMessageUntil.toServer(sendmes3);
            }
        }
        else if (this.caozuo == 5) {
            String sendmes = Agreement.getAgreement().oneArenaAgreement("4");
            SendMessageUntil.toServer(sendmes);
        }
        else if (this.caozuo == 6) {
            NewRefiningJpanel refiningJpanel = NewRefiningJframe.getNewRefiningJframe().getRefiningJpanel();
            if (!NewRefiningJframe.getNewRefiningJframe().isVisible()) {
                refiningJpanel.show(null, 4, false);
            }
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        if (this.caozuo == 3) {
            this.partnerArenaModelPanel.setOpaque(true);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        if (this.caozuo == 3) {
            this.partnerArenaModelPanel.setOpaque(false);
        }
    }
}
