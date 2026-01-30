package com.tool.btn;

import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.come.Jpanel.TtBmJpanel;

public class TtBmbtn extends MoBanBtn
{
    private int caozuo;
    private TtBmJpanel ttBmJpanel;
    
    public TtBmbtn(String iconpath, int type, Color[] colors, Font font, String text, int caozuo, TtBmJpanel ttBmJpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.caozuo = caozuo;
        this.ttBmJpanel = ttBmJpanel;
    }
    
    public TtBmbtn(String iconpath, int type, Color[] colors, Font font, String text) {
        super(iconpath, type, colors);
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
        if (this.caozuo == 1) {
            String sendmes = Agreement.getAgreement().addFrientAgreement("RA");
            SendMessageUntil.toServer(sendmes);
        }
        else if (this.caozuo == 2) {
            String sendmes = Agreement.getAgreement().addFrientAgreement("RD");
            SendMessageUntil.toServer(sendmes);
        }
    }
}
