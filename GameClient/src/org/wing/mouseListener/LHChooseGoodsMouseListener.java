package org.wing.mouseListener;

import org.come.Frame.TestpackJframe;
import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import org.wing.panel.LHMainPanel;
import java.awt.event.MouseAdapter;

public class LHChooseGoodsMouseListener extends MouseAdapter
{
    private LHMainPanel LHMainPanel;
    
    public LHChooseGoodsMouseListener(LHMainPanel LHMainPanel) {
        this.LHMainPanel = LHMainPanel;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (this.LHMainPanel.getRightType() == 4) {
            return;
        }
        if (e.getButton() == 3 && this.LHMainPanel.getChosegoods() != null) {
            this.LHMainPanel.setChosegoods(null);
            this.LHMainPanel.getChooseGoodsImg().setIcon(null);
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        Goodstable goodstable = this.LHMainPanel.getChosegoods();
        if (goodstable != null) {
            ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        TestpackJframe.getTestpackJframe().getJpac().ClearText();
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
}
