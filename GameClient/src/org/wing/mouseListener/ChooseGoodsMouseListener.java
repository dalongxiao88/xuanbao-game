package org.wing.mouseListener;

import org.come.Frame.TestpackJframe;
import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import org.wing.panel.WingMainPanel;
import java.awt.event.MouseAdapter;

public class ChooseGoodsMouseListener extends MouseAdapter
{
    private WingMainPanel wingMainPanel;
    
    public ChooseGoodsMouseListener(WingMainPanel wingMainPanel) {
        this.wingMainPanel = wingMainPanel;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (this.wingMainPanel.getRightType() == 4) {
            return;
        }
        if (e.getButton() == 3 && this.wingMainPanel.getChosegoods() != null) {
            this.wingMainPanel.setChosegoods(null);
            this.wingMainPanel.getChooseGoodsImg().setIcon(null);
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        Goodstable goodstable = this.wingMainPanel.getChosegoods();
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
