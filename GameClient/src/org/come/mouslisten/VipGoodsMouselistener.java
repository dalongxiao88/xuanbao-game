package org.come.mouslisten;

import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import org.come.entity.Goodstable;
import java.awt.event.MouseListener;

public class VipGoodsMouselistener implements MouseListener
{
    private Goodstable goodstable;
    
    public VipGoodsMouselistener() {
    }
    
    public VipGoodsMouselistener(Goodstable goodstable) {
        this.goodstable = goodstable;
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if (this.goodstable == null) {
            return;
        }
        ZhuFrame.getZhuJpanel().creatgoodtext(this.goodstable);
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    public Goodstable getGoodstable() {
        return this.goodstable;
    }
    
    public void setGoodstable(Goodstable goodstable) {
        this.goodstable = goodstable;
    }
}
