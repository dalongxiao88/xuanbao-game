package org.wing.mouseListener;

import org.come.Frame.TestpackJframe;
import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import org.come.until.GoodsListFromServerUntil;
import java.awt.event.MouseEvent;
import org.wing.panel.LHMainPanel;
import java.awt.event.MouseAdapter;

public class LHChooseWingGoodsMouseListener extends MouseAdapter
{
    private LHMainPanel LHMainPanel;
    
    public LHChooseWingGoodsMouseListener(LHMainPanel LHMainPanel) {
        this.LHMainPanel = LHMainPanel;
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if (this.LHMainPanel.getWingGoods() == null) {
            return;
        }
        Goodstable goodstable = GoodsListFromServerUntil.getRgid(this.LHMainPanel.getWingGoods());
        if (goodstable == null) {
            return;
        }
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
