package org.come.mouslisten;

import java.awt.event.MouseEvent;
import org.come.Jpanel.LingbaoCardJpanel;
import java.awt.event.MouseListener;

public class ChoseLingbaoTypeMouslisten implements MouseListener
{
    private LingbaoCardJpanel lingbaoCardJpanel;
    private String type;
    
    public ChoseLingbaoTypeMouslisten(String type, LingbaoCardJpanel lingbaoCardJpanel) {
        this.type = type;
        this.lingbaoCardJpanel = lingbaoCardJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (this.type.equals("灵宝装备")) {
            LingFaFanYeMouslisten.shijian = true;
            this.lingbaoCardJpanel.getCar().show(this.lingbaoCardJpanel, "l1");
        }
        else if (this.type.equals("灵宝属性")) {
            LingFaFanYeMouslisten.shijian = false;
            this.lingbaoCardJpanel.getCar().show(this.lingbaoCardJpanel, "l2");
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
