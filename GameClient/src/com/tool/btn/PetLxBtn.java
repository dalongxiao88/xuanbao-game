package com.tool.btn;

import org.come.Frame.ZhuFrame;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.apache.commons.lang.StringUtils;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import org.come.until.FormsManagement;
import org.come.until.UserMessUntil;
import org.skill.frame.LXiulianMainFrame;
import org.come.until.Music;
import org.come.Frame.PetLxJframe;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import org.skill.panel.LxPanel;

public class PetLxBtn extends MoBanBtn
{
    private static final long serialVersionUID = 7822149256715030997L;
    private int event;
    private LxPanel panel;
    
    public PetLxBtn(String iconpath, int type, int event) {
        super(iconpath, type);
        this.event = event;
    }
    
    public PetLxBtn(String iconpath, int type, String text, int event) {
        super(iconpath, type, UIUtils.COLOR_BTNPUTONG);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY88);
        this.event = event;
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public PetLxBtn(String iconpath, int type, String text, int event, String sm) {
        super(iconpath, type, UIUtils.COLOR_BLACK);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY88);
        this.event = event;
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public PetLxBtn(String iconpath, int type, String text, int event, LxPanel panel) {
        super(iconpath, type, UIUtils.COLOR_BTNPUTONG);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY88);
        this.event = event;
        this.panel = panel;
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public PetLxBtn(String iconpath, int type, String text, int event, LxPanel panel, String sm) {
        super(iconpath, type, UIUtils.COLOR_BLACK);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY88);
        this.event = event;
        this.panel = panel;
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.event < 5) {
            PetLxJframe.getPetLxJframe().getLxPanel().changePanel(this.event);
            Music.addyinxiao("开关窗口.mp3");
        }
        else {
            switch (this.event) {
                case 5: {
                    LXiulianMainFrame.getLXiulianMainFrame().getLXiulianMainPanel().panelGetData(UserMessUntil.getChosePetMes());
                    FormsManagement.showForm(602);
                    break;
                }
                case 6: {
                    OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.XiLingXi, UserMessUntil.getChosePetMes().getSid(), "#Y洗点需消耗#R180000000#Y大话币，会清空所有灵犀技能返还灵犀点数。#R（已开启的灵犀技能格不需要重新开启）");
                    break;
                }
                case 7: {
                    if (this.panel != null) {
                        String lingxi = this.panel.getLingxi();
                        if (StringUtils.isNotEmpty(lingxi)) {
                            String save = Agreement.getAgreement().LingXiAgreement("S&" + UserMessUntil.getChosePetMes().getSid() + "&" + this.panel.getLingxi());
                            SendMessageUntil.toServer(save);
                            break;
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("你还没有点过该类型的任何灵犀技能呢");
                            return;
                        }
                    }
                    else {
                        break;
                    }
                }
                case 8: {
                    String xl = Agreement.getAgreement().LingXiAgreement("X&" + UserMessUntil.getChosePetMes().getSid());
                    SendMessageUntil.toServer(xl);
                    break;
                }
                case 9: {
                    String xl2 = Agreement.getAgreement().LingXiAgreement("Z&" + UserMessUntil.getChosePetMes().getSid());
                    SendMessageUntil.toServer(xl2);
                    break;
                }
                case 10: {
                    this.panel.openSkill();
                    break;
                }
            }
        }
    }
}
