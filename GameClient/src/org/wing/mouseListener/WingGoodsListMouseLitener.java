package org.wing.mouseListener;

import org.come.Frame.TestpackJframe;
import org.come.Frame.ZhuFrame;
import org.come.entity.Goodstable;
import org.come.until.GoodsListFromServerUntil;
import java.awt.event.MouseEvent;
import org.wing.panel.WingMainPanel;
import java.awt.event.MouseAdapter;

public class WingGoodsListMouseLitener extends MouseAdapter
{
    private int type;
    private WingMainPanel wingMainPanel;
    
    public WingGoodsListMouseLitener(int type, WingMainPanel wingMainPanel) {
        this.type = type;
        this.wingMainPanel = wingMainPanel;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 3) {
            if (this.type + (this.wingMainPanel.getPageNumber() - 1) * 25 >= GoodsListFromServerUntil.getWingGoodsList().size()) {
                return;
            }
            Goodstable goodstable = (Goodstable)GoodsListFromServerUntil.getWingGoodsList().get(this.type + (this.wingMainPanel.getPageNumber() - 1) * 25);
            this.wingMainPanel.changeChooseWingGoods(goodstable, this.type + (this.wingMainPanel.getPageNumber() - 1) * 25);
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if (this.type + (this.wingMainPanel.getPageNumber() - 1) * 25 >= GoodsListFromServerUntil.getWingGoodsList().size()) {
            return;
        }
        Goodstable goodstable = (Goodstable)GoodsListFromServerUntil.getWingGoodsList().get(this.type + (this.wingMainPanel.getPageNumber() - 1) * 25);
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
