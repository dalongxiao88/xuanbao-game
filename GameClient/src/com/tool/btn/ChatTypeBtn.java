package com.tool.btn;

import org.come.until.MessagrFlagUntil;
import org.come.Frame.ChatHistoryJframe;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.come.Jpanel.ChatHistoryJpanel;

public class ChatTypeBtn extends MoBanBtn
{
    private ChatHistoryJpanel chatHistoryJpanel;
    private int caozuo;
    
    public ChatTypeBtn(String iconpath, int type, int caozuo, String text, Color[] colors, Font font, ChatHistoryJpanel chatHistoryJpanel) {
        super(iconpath, type, colors);
        this.caozuo = caozuo;
        this.chatHistoryJpanel = chatHistoryJpanel;
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
        ChatHistoryJframe.getChatHistoryJframe().getChatHistoryJpanel().updateType(this.caozuo);
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE12);
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
    }
}
