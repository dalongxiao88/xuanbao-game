package com.tool.btn;

import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.OptionsJpanel;

public class OptionsBtn extends MoBanBtn
{
    private OptionsJpanel jpanel;
    
    public OptionsBtn(String iconpath, int type, String text, OptionsJpanel jpanel) {
        super(iconpath, type, UIUtils.COLOR_BTNPUTONG);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY16);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.jpanel = jpanel;
    }
    
    public OptionsBtn(String iconpath, int type, String text, OptionsJpanel jpanel, String sm) {
        super(iconpath, type, UIUtils.COLOR_BLACK);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY16);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.jpanel = jpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        this.jpanel.tipBox(this.getText().equals("确定"));
    }
}
