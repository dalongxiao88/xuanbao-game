package org.wing.mouseListener;

import org.come.Frame.TestpackJframe;
import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import org.come.until.GoodsListFromServerUntil;
import java.awt.event.MouseEvent;
import org.wing.panel.LHMainPanel;
import java.awt.event.MouseAdapter;

public class LHUpgradeGoodsMouseListener extends MouseAdapter
{
    private int type;
    private LHMainPanel LHMainPanel;
    
    public LHUpgradeGoodsMouseListener(int type, LHMainPanel LHMainPanel) {
        this.type = type;
        this.LHMainPanel = LHMainPanel;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 3) {
            this.LHMainPanel.setChosegoods(GoodsListFromServerUntil.getUpgradeGoods(this.type, this.LHMainPanel));
            if (this.LHMainPanel.getChosegoods() == null) {
                return;
            }
            this.LHMainPanel.getChooseGoodsImg().setIcon(GoodsListFromServerUntil.imgpathAdaptive(this.LHMainPanel.getChosegoods().getSkin(), 58, 56));
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        Goodstable goodstable = GoodsListFromServerUntil.getUpgradeGoods(this.type, this.LHMainPanel);
        if (goodstable != null) {
            ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        TestpackJframe.getTestpackJframe().getJpac().ClearText();
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
}
