package com.tool.btn;

import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import java.time.LocalDate;
import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.come.Jpanel.QiandaoListJpanel;

public class QianDaoBtn extends MoBanBtn
{
    private int day;
    private QiandaoListJpanel qiandaoListJpanel;
    
    public QianDaoBtn(String iconpath, int type, QiandaoListJpanel qiandaoListJpanel) {
        super(iconpath, type);
        this.day = -1;
        this.qiandaoListJpanel = qiandaoListJpanel;
    }
    
    public QianDaoBtn(String iconpath, int type, Color[] colors, int num, int isup, QiandaoListJpanel qiandaoListJpanel) {
        super(iconpath, type, colors, num, isup);
        this.day = -1;
        this.qiandaoListJpanel = qiandaoListJpanel;
    }
    
    public QianDaoBtn(String iconpath, int type, Color[] colors, Font font, String text, QiandaoListJpanel qiandaoListJpanel) {
        super(iconpath, type, colors);
        this.day = -1;
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.qiandaoListJpanel = qiandaoListJpanel;
    }
    
    public QianDaoBtn(String iconpath, int type, Color[] colors, Font font, String text, int day, QiandaoListJpanel qiandaoListJpanel) {
        super(iconpath, type, colors);
        this.day = -1;
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.day = day;
        this.qiandaoListJpanel = qiandaoListJpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.day == -1) {
            if (this.qiandaoListJpanel.getDays().size() == QiandaoListJpanel.getCurrentMonthDay()) {
                ZhuFrame.getZhuJpanel().addPrompt2("#89 您本月已签满！！！");
                return;
            }
            if (this.qiandaoListJpanel.getDays().contains(Integer.valueOf(LocalDate.now().getDayOfMonth()))) {
                ZhuFrame.getZhuJpanel().addPrompt2("#24 您今天已签到过了！！！");
            }
            else {
                this.qiandaoListJpanel.setDay(this.qiandaoListJpanel.getDay() + 1);
                SendMessageUntil.toServer(Agreement.getAgreement().qdAgreement("qd"));
            }
        }
        if (this.day > 0) {
            if (this.qiandaoListJpanel.getDays().size() < this.day) {
                ZhuFrame.getZhuJpanel().addPrompt2("#24 少侠,请签到达到" + this.day + "天时再领取" + this.day + "天奖励！！！");
                return;
            }
            if (this.qiandaoListJpanel.getLqs().contains(Integer.valueOf(this.day))) {
                ZhuFrame.getZhuJpanel().addPrompt2("#24 少侠,该奖励已经领取过了！！！");
            }
            else {
                SendMessageUntil.toServer(Agreement.getAgreement().qdAgreement("select=" + this.day));
            }
        }
    }
}
