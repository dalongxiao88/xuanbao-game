package org.soaring.btn;

import org.come.bean.Meridians;
import com.tool.role.RoleData;
import java.math.BigDecimal;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.come.Jpanel.CharacterMeridiansMainJpanel;
import com.tool.btn.MoBanBtn;

public class CharacterMeridiansBtn extends MoBanBtn
{
    private int caozuo;
    private CharacterMeridiansMainJpanel characterMeridiansMainJpanel;
    
    public CharacterMeridiansBtn(String iconpath, int type, Color[] colors, Font font, String text, int caozuo, CharacterMeridiansMainJpanel characterMeridiansMainJpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.caozuo = caozuo;
        this.characterMeridiansMainJpanel = characterMeridiansMainJpanel;
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
            int chooseNum = this.characterMeridiansMainJpanel.getChooseNum();
            if (chooseNum == -1) {
                ZhuFrame.getZhuJpanel().addPrompt2("请先选择一个经脉");
                return;
            }
            String attr = (this.characterMeridiansMainJpanel.getLockAttr().getIcon() == null) ? "0" : "1";
            String quality = (this.characterMeridiansMainJpanel.getLockQuality().getIcon() == null) ? "0" : "1";
            SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("X2" + (chooseNum + 1) + "_" + attr + "_" + quality));
        }
        else if (this.caozuo == 1) {
            Meridians m = this.characterMeridiansMainJpanel.getMeridians();
            if (this.characterMeridiansMainJpanel.getMoneyType() == 0) {
                this.characterMeridiansMainJpanel.setMoneyType(1);
                this.characterMeridiansMainJpanel.getBtnSeepUp().setText("仙玉修炼");
                if (m != null) {
                    this.characterMeridiansMainJpanel.setMoney(new BigDecimal(m.getMoney()));
                }
                this.characterMeridiansMainJpanel.getLabxianyu().setVisible(true);
                this.characterMeridiansMainJpanel.getLabxianyu().setText(String.valueOf(RoleData.getRoleData().getLoginResult().getCodecard()));
            }
            else {
                this.characterMeridiansMainJpanel.setMoneyType(0);
                this.characterMeridiansMainJpanel.getBtnSeepUp().setText("金币修炼");
                if (m != null) {
                    this.characterMeridiansMainJpanel.setMoney(new BigDecimal(m.getGold()));
                }
                this.characterMeridiansMainJpanel.getLabxianyu().setVisible(false);
            }
        }
        else if (this.caozuo == 2) {
            int chooseNum = this.characterMeridiansMainJpanel.getChooseNum();
            if (chooseNum == -1) {
                ZhuFrame.getZhuJpanel().addPrompt2("请先选择一个经脉");
                return;
            }
            if (this.characterMeridiansMainJpanel.getMoneyType() == 0) {
                SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("X3" + (chooseNum + 1)));
            }
            else if (this.characterMeridiansMainJpanel.getMoneyType() == 1) {
                SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("X4" + (chooseNum + 1)));
            }
        }
    }
}
