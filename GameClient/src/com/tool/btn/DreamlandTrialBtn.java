package com.tool.btn;

import org.come.Frame.ExchangeAwardJframe;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.FormsManagement;
import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.come.Jpanel.DreamlandTrialMainJpanel;

public class DreamlandTrialBtn extends MoBanBtn
{
    private static final long serialVersionUID = 4456878086173461936L;
    private int caozuo;
    private DreamlandTrialMainJpanel dreamlandTrialMainJpanel;
    
    public DreamlandTrialBtn(String iconpath, int type, int caozuo, DreamlandTrialMainJpanel dreamlandTrialMainJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.dreamlandTrialMainJpanel = dreamlandTrialMainJpanel;
    }
    
    public DreamlandTrialBtn(String iconpath, int type, Color[] colors, Font font, String text, int caozuo, DreamlandTrialMainJpanel dreamlandTrialMainJpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
        this.caozuo = caozuo;
        this.dreamlandTrialMainJpanel = dreamlandTrialMainJpanel;
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
            if (this.dreamlandTrialMainJpanel.getPageNow() <= 1) {
                ZhuFrame.getZhuJpanel().addPrompt2("已经是首页了");
                return;
            }
            this.dreamlandTrialMainJpanel.showLvlTier(Integer.valueOf(1));
        }
        else if (this.caozuo == 1) {
            if (this.dreamlandTrialMainJpanel.getPageNow() <= 1) {
                ZhuFrame.getZhuJpanel().addPrompt2("已经是首页了");
                return;
            }
            this.dreamlandTrialMainJpanel.showLvlTier(Integer.valueOf(this.dreamlandTrialMainJpanel.getPageNow() - 1));
        }
        else if (this.caozuo == 2) {
            if (this.dreamlandTrialMainJpanel.getPageNow() >= this.dreamlandTrialMainJpanel.getPageMax()) {
                ZhuFrame.getZhuJpanel().addPrompt2("已经是末页了");
                return;
            }
            this.dreamlandTrialMainJpanel.showLvlTier(Integer.valueOf(this.dreamlandTrialMainJpanel.getPageNow() + 1));
        }
        else if (this.caozuo == 3) {
            if (this.dreamlandTrialMainJpanel.getPageNow() >= this.dreamlandTrialMainJpanel.getPageMax()) {
                ZhuFrame.getZhuJpanel().addPrompt2("已经是末页了");
                return;
            }
            this.dreamlandTrialMainJpanel.showLvlTier(Integer.valueOf(this.dreamlandTrialMainJpanel.getPageMax()));
        }
        else if (this.caozuo == 4) {
            if (this.dreamlandTrialMainJpanel.getChooseNum() == -1) {
                ZhuFrame.getZhuJpanel().addPrompt2("请先选择关卡");
                return;
            }
            int num = (this.dreamlandTrialMainJpanel.getPageNow() - 1) * 6 + this.dreamlandTrialMainJpanel.getChooseNum() + 1;
            FormsManagement.HideForm(111);
            SendMessageUntil.toServer(Agreement.getAgreement().hjslAgreement("P" + num));
        }
        else if (this.caozuo == 5) {
            ExchangeAwardJframe.getExchangeAwardJframe().getAwardJpanel().use(5, null);
        }
        else if (this.caozuo == 6) {
            SendMessageUntil.toServer(Agreement.getAgreement().hjslAgreement("R"));
            this.dreamlandTrialMainJpanel.showLvlTier(Integer.valueOf(1));
        }
    }
}
