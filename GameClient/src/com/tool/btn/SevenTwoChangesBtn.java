package com.tool.btn;

import org.come.model.aCard;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import com.tool.role.RoleData;
import java.math.BigDecimal;
import org.come.until.UserMessUntil;
import java.awt.event.MouseEvent;
import java.awt.Font;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import org.come.Jpanel.SeventyTwoChangesJpanel;

public class SevenTwoChangesBtn extends MoBanBtn
{
    private int caozuo;
    private SeventyTwoChangesJpanel seventyTwoChangesJpanel;
    
    public SevenTwoChangesBtn(String iconpath, int type, String text, int caozuo, SeventyTwoChangesJpanel seventyTwoChangesJpanel) {
        super(iconpath, type);
        this.setText(text);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
        this.setForeground(Color.yellow);
        this.setFont(UIUtils.TEXT_FONT82);
        this.caozuo = caozuo;
        this.seventyTwoChangesJpanel = seventyTwoChangesJpanel;
    }
    
    public SevenTwoChangesBtn(String iconpath, int type, Color[] colors, Font font, String text, int caozuo, SeventyTwoChangesJpanel seventyTwoChangesJpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
        this.caozuo = caozuo;
        this.seventyTwoChangesJpanel = seventyTwoChangesJpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        switch (this.caozuo) {
            case 0:
            case 1: {
                this.seventyTwoChangesJpanel.changeMenuBtnSeventyTwoChanges(this.caozuo);
                break;
            }
            case 10: {
                this.btnChoose(0);
                break;
            }
            case 11: {
                this.btnChoose(1);
                break;
            }
            case 20: {
                this.seventyTwoChangesJpanel.getACardDatas(UserMessUntil.getaCardMap(), this.seventyTwoChangesJpanel.getChooseMoneyType(), this.seventyTwoChangesJpanel.getSelectNameText().getText().trim());
                break;
            }
        }
    }
    
    public void btnChoose(int type) {
        aCard chooseCard = this.seventyTwoChangesJpanel.getChooseCard();
        if (chooseCard != null) {
            if (chooseCard.getType() == 0) {
                if (new BigDecimal(chooseCard.getMoney()).compareTo(RoleData.getRoleData().getLoginResult().getGold()) > 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("大话币不足");
                    return;
                }
            }
            else if (new BigDecimal(chooseCard.getMoney()).compareTo(RoleData.getRoleData().getLoginResult().getCodecard()) > 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("仙玉不足");
                return;
            }
            String senmes = Agreement.getAgreement().usercardAgreement(type + "|" + chooseCard.getId());
            SendMessageUntil.toServer(senmes);
        }
    }
}
