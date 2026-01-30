package com.tool.btn;

import org.come.entity.Goodstable;
import java.math.BigDecimal;
import org.come.until.FormsManagement;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import org.come.until.GoodsListFromServerUntil;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.come.Jpanel.QuackGameJpanel;

public class QuackGameBtn extends MoBanBtn
{
    private QuackGameJpanel gameJpanel;
    
    public QuackGameBtn(String iconpath, int type, Color[] colors, Font font, String text, QuackGameJpanel gameJpanel) {
        super(iconpath, type, colors);
        this.gameJpanel = gameJpanel;
        this.setText(text);
        this.setFont(font);
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
        if ("一键召唤".equals(this.getText())) {
            BigDecimal rgid = null;
            int i = 0;
            while (i < GoodsListFromServerUntil.getGoodslist().length) {
                Goodstable goodstable = GoodsListFromServerUntil.getGoodslist()[i];
                if (goodstable != null && (long)goodstable.getType() == 889L) {
                    rgid = goodstable.getRgid();
                    goodstable.goodxh(1);
                    this.gameJpanel.kyNum = (int)goodstable.getUsetime();
                    if (this.gameJpanel.kyNum <= 0) {
                        GoodsListFromServerUntil.Deletebiaoid(rgid);
                        break;
                    }
                    else {
                        break;
                    }
                }
                else {
                    ++i;
                }
            }
            if (rgid == null) {
                ZhuFrame.getZhuJpanel().addPrompt("你没有通灵宝券，快去购买吧！！");
                return;
            }
            String sendMes = Agreement.getFiveMsgAgreement("G" + rgid);
            SendMessageUntil.toServer(sendMes);
            this.gameJpanel.getGameBtn().setBtn(-1);
        }
        else if ("再来一次".equals(this.getText())) {
            this.gameJpanel.getGameBtn().setText("一键召唤");
            this.gameJpanel.reset();
        }
        else if ("?".equals(this.getText())) {
            if (!FormsManagement.getframe(72).isVisible()) {
                FormsManagement.showForm(72);
            }
            else {
                FormsManagement.HideForm(72);
            }
        }
    }
}
