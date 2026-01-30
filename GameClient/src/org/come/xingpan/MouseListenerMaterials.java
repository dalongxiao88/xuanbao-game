package org.come.xingpan;

import org.come.Frame.ZhuFrame;
import org.come.Frame.TestpackJframe;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class MouseListenerMaterials extends MouseAdapter
{
    private int i;
    private JpanelXingCardMain jpanelXingCardMain;
    
    public MouseListenerMaterials(int i, JpanelXingCardMain jpanelXingCardMain) {
        this.i = i;
        this.jpanelXingCardMain = jpanelXingCardMain;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        TestpackJframe.getTestpackJframe().getJpac().ClearText();
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
}
