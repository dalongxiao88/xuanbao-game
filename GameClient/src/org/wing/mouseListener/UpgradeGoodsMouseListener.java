package org.wing.mouseListener;

import org.come.Frame.TestpackJframe;
import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import org.come.until.GoodsListFromServerUntil;
import java.awt.event.MouseEvent;
import org.wing.panel.WingMainPanel;
import java.awt.event.MouseAdapter;

public class UpgradeGoodsMouseListener extends MouseAdapter
{
    private int type;
    private WingMainPanel wingMainPanel;
    
    public UpgradeGoodsMouseListener(int type, WingMainPanel wingMainPanel) {
        this.type = type;
        this.wingMainPanel = wingMainPanel;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 3) {
            this.wingMainPanel.setChosegoods(GoodsListFromServerUntil.getUpgradeGoods(this.type, this.wingMainPanel));
            if (this.wingMainPanel.getChosegoods() == null) {
                return;
            }
            this.wingMainPanel.getChooseGoodsImg().setIcon(GoodsListFromServerUntil.imgpathAdaptive(this.wingMainPanel.getChosegoods().getSkin(), 58, 56));
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        Goodstable goodstable = GoodsListFromServerUntil.getUpgradeGoods(this.type, this.wingMainPanel);
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
