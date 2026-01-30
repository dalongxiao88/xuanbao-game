package org.cbg.mouslisten;

import org.come.Frame.ZhuFrame;
import org.come.until.MessagrFlagUntil;
import java.awt.event.MouseEvent;
import org.come.entity.Goodstable;
import java.awt.event.MouseListener;

public class GodsForGodMouslisten implements MouseListener
{
    private Goodstable goosGoodstable;
    
    public GodsForGodMouslisten(Goodstable goosGoodstable) {
        this.goosGoodstable = goosGoodstable;
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
    
    @Override
    public void mouseEntered(MouseEvent e) {
        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE12);
        if (this.goosGoodstable != null) {
            ZhuFrame.getZhuJpanel().creatgoodtext(this.goosGoodstable);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
        if (this.goosGoodstable != null) {
            ZhuFrame.getZhuJpanel().cleargoodtext();
        }
    }
}
