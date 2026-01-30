package com.tool.btn;

import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import come.tool.Fighting.FightingMixDeal;
import java.awt.event.MouseEvent;
import java.awt.Font;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import org.come.Jpanel.RoleBornJpanel;
import org.come.Jpanel.TesttaskJapnel;
import org.come.Jpanel.TeststateJpanel;

public class JpanelBornlBtn extends MoBanBtn
{
    private int BtnId;
    private TeststateJpanel teststateJpanel;
    private TesttaskJapnel testtaskJapnel;
    private RoleBornJpanel roleBornJpanel;
    
    public JpanelBornlBtn(String iconpath, int type, Color[] colors, int BtnId, String labelName, TeststateJpanel teststateJpanel) {
        super(iconpath, type, colors);
        this.BtnId = BtnId;
        this.teststateJpanel = teststateJpanel;
        this.setText(labelName);
        if (BtnId == 0) {
            this.setFont(UIUtils.TEXT_FONT82);
        }
        else {
            this.setFont(UIUtils.TEXT_FONT221);
        }
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public JpanelBornlBtn(String iconpath, int type, Color[] colors, int BtnId, String labelName, TesttaskJapnel testtaskJapnel) {
        super(iconpath, type, colors);
        this.BtnId = BtnId;
        this.testtaskJapnel = testtaskJapnel;
        this.setText(labelName);
        this.setFont(UIUtils.TEXT_HY88);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public JpanelBornlBtn(String iconpath, int type, Color[] colors, int BtnId, String labelName, RoleBornJpanel roleBornJpanel) {
        super(iconpath, type, colors);
        this.BtnId = BtnId;
        this.roleBornJpanel = roleBornJpanel;
        this.setText(labelName);
        this.setFont(UIUtils.TEXT_HY88);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public JpanelBornlBtn(String iconpath, int type, Color[] colors, int BtnId, String labelName, RoleBornJpanel roleBornJpanel, Font f) {
        super(iconpath, type, colors);
        this.BtnId = BtnId;
        this.roleBornJpanel = roleBornJpanel;
        this.setText(labelName);
        this.setFont(UIUtils.TEXT_FONT2);
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
        switch (this.BtnId) {
            case 11: {
                if (FightingMixDeal.State != 0) {
                    ZhuFrame.getZhuJpanel().addPrompt("战斗中无法切换");
                    return;
                }
                String mes = Agreement.getAgreement().rolechangeAgreement("QXZ");
                SendMessageUntil.toServer(mes);
                break;
            }
        }
    }
}
