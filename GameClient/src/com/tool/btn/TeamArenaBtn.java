package com.tool.btn;

import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.come.Jpanel.TeamArenaMainJpanel;

public class TeamArenaBtn extends MoBanBtn
{
    private int caozuo;
    private TeamArenaMainJpanel teamArenaMainJpanel;
    
    public TeamArenaBtn(String iconpath, int type, Color[] colors, Font font, String text, int caozuo, TeamArenaMainJpanel teamArenaMainJpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.caozuo = caozuo;
        this.teamArenaMainJpanel = teamArenaMainJpanel;
    }
    
    public TeamArenaBtn(String iconpath, int type, Color[] colors, Font font, String text) {
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
            String sendmes = Agreement.getAgreement().teamArenaAgreement("A");
            SendMessageUntil.toServer(sendmes);
        }
        else if (this.caozuo == 2) {
            String sendmes = Agreement.getAgreement().teamArenaAgreement("D");
            SendMessageUntil.toServer(sendmes);
        }
        if (this.caozuo == 3) {
            String sendmes = Agreement.getAgreement().laddArenaAgreement("A");
            SendMessageUntil.toServer(sendmes);
        }
        else if (this.caozuo == 4) {
            String sendmes = Agreement.getAgreement().laddArenaAgreement("D");
            SendMessageUntil.toServer(sendmes);
        }
    }
}
