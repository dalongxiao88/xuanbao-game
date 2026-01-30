package com.tool.btn;

import org.come.Frame.TtCjJframe;
import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.come.Jpanel.TtJpanel;

public class TtOperationBtn extends MoBanBtn
{
    private int caozuo;
    private TtJpanel ttJpanel;
    
    public TtOperationBtn(String iconpath, int type, int caozuo, TtJpanel ttJpanel) {
        super(iconpath, type);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.setCaozuo(caozuo);
        this.setTtJpanel(ttJpanel);
    }
    
    public TtOperationBtn(String iconpath, int type, Color[] colors, String text, Font font, int caozuo, TtJpanel ttJpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
        this.setCaozuo(caozuo);
        this.setTtJpanel(ttJpanel);
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.caozuo != 1) {
            if (this.caozuo == 5) {
                FormsManagement.showForm(607);
                TtCjJframe.getTtCjJframe().getPanel().initData();
            }
            else if (this.caozuo == 6) {
                FormsManagement.showForm(608);
            }
        }
    }
    
    public int getCaozuo() {
        return this.caozuo;
    }
    
    public void setCaozuo(int caozuo) {
        this.caozuo = caozuo;
    }
    
    public TtJpanel getTtJpanel() {
        return this.ttJpanel;
    }
    
    public void setTtJpanel(TtJpanel ttJpanel) {
        this.ttJpanel = ttJpanel;
    }
}
