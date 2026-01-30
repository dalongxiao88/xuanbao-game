package org.come.mouslisten;

import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import org.come.until.DDGoodUntil;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TakeBackGoodMouslisten implements MouseListener
{
    private int index;
    
    public TakeBackGoodMouslisten(int index) {
        this.index = index;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 3) {
            DDGoodUntil.Retrieve(this.index);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        Goodstable good = DDGoodUntil.getgood(this.index);
        if (good != null) {
            ZhuFrame.getZhuJpanel().creatgoodtext(good);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
}
