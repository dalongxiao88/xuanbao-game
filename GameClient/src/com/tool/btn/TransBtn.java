package com.tool.btn;

import org.come.until.Util;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.come.Jpanel.TransJpanel;

public class TransBtn extends MoBanBtn
{
    private TransJpanel transJpanel;
    private int p;
    
    public TransBtn(String iconpath, int type, int p, TransJpanel transJpanel) {
        super(iconpath, type);
        this.transJpanel = transJpanel;
        this.p = p;
    }
    
    public TransBtn(String iconpath, int type, Color[] colors, Font font, String text, int p, TransJpanel transJpanel) {
        super(iconpath, type, colors);
        if (text != null) {
            this.setText(text);
            this.setFont(font);
            this.setVerticalTextPosition(0);
            this.setHorizontalTextPosition(0);
        }
        this.transJpanel = transJpanel;
        this.p = p;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.p == 0 || this.p == 1) {
            this.transJpanel.cJpanelType(this.p);
        }
        else if (this.p == 2) {
            if (Util.isCanBuyOrno()) {
                return;
            }
            this.transJpanel.queDing();
        }
    }
}
