package org.wing.mouseListener;

import org.come.Frame.TestpackJframe;
import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import org.come.until.GoodsListFromServerUntil;
import java.awt.event.MouseEvent;
import org.wing.panel.WingMainPanel;
import java.awt.event.MouseAdapter;

public class ChooseWingGoodsMouseListener extends MouseAdapter
{
    private WingMainPanel wingMainPanel;
    
    public ChooseWingGoodsMouseListener(WingMainPanel wingMainPanel) {
        this.wingMainPanel = wingMainPanel;
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if (this.wingMainPanel.getWingGoods() == null) {
            return;
        }
        Goodstable goodstable = GoodsListFromServerUntil.getRgid(this.wingMainPanel.getWingGoods());
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
