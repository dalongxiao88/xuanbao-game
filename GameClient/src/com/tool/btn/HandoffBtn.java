package com.tool.btn;

import java.awt.event.MouseEvent;
import org.come.Jpanel.SummonJpanel;

public class HandoffBtn extends MoBanBtn
{
    private String handoffType;
    private SummonJpanel panel;
    
    public HandoffBtn(String iconpath, String handoffType, int type, SummonJpanel panel) {
        super(iconpath, type);
        this.handoffType = handoffType;
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
        this.panel.show(this.handoffType);
    }
}
