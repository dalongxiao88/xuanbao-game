package com.tool.btn;

import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.TestPetJpanel;

public class WspSupportBtn extends MoBanBtn
{
    private TestPetJpanel jpanels;
    private int x;
    
    public WspSupportBtn(String iconpath, int type, int x, TestPetJpanel jpanelS) {
        super(iconpath, type);
        this.x = x;
        this.jpanels = jpanelS;
    }
    
    public WspSupportBtn(String iconpath, int type, int x, String text, TestPetJpanel jpanelS) {
        super(iconpath, type, UIUtils.COLOR_BTNTEXT);
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.x = x;
        this.jpanels = jpanelS;
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
}
