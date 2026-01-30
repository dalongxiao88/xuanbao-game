package com.tool.btn;

import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.come.Jpanel.TtCjJpanel;

public class TtCjBtn extends MoBanBtn
{
    private int caozuo;
    private TtCjJpanel panel;
    
    public TtCjBtn(String iconpath, int type, Color[] colors, String text, Font font, int caozuo, TtCjJpanel panel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
        this.caozuo = caozuo;
        this.panel = panel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
    }
    
    public int getCaozuo() {
        return this.caozuo;
    }
    
    public void setCaozuo(int caozuo) {
        this.caozuo = caozuo;
    }
    
    public TtCjJpanel getPanel() {
        return this.panel;
    }
    
    public void setPanel(TtCjJpanel panel) {
        this.panel = panel;
    }
}
