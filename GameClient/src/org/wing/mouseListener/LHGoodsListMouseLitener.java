package org.wing.mouseListener;

import org.come.Frame.TestpackJframe;
import org.come.Frame.ZhuFrame;
import org.come.entity.Goodstable;
import org.come.until.GoodsListFromServerUntil;
import java.awt.event.MouseEvent;
import org.wing.panel.LHMainPanel;
import java.awt.event.MouseAdapter;

public class LHGoodsListMouseLitener extends MouseAdapter
{
    private int type;
    private LHMainPanel LHMainPanel;
    
    public LHGoodsListMouseLitener(int type, LHMainPanel LHMainPanel) {
        this.type = type;
        this.LHMainPanel = LHMainPanel;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 3) {
            if (this.type + (this.LHMainPanel.getPageNumber() - 1) * 25 >= GoodsListFromServerUntil.getWingGoodsList().size()) {
                return;
            }
            Goodstable goodstable = (Goodstable)GoodsListFromServerUntil.getWingGoodsList().get(this.type + (this.LHMainPanel.getPageNumber() - 1) * 25);
            this.LHMainPanel.changeChooseWingGoods(goodstable, this.type + (this.LHMainPanel.getPageNumber() - 1) * 25);
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if (this.type + (this.LHMainPanel.getPageNumber() - 1) * 25 >= GoodsListFromServerUntil.getWingGoodsList().size()) {
            return;
        }
        Goodstable goodstable = (Goodstable)GoodsListFromServerUntil.getWingGoodsList().get(this.type + (this.LHMainPanel.getPageNumber() - 1) * 25);
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
