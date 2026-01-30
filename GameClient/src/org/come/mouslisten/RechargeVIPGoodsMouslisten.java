package org.come.mouslisten;

import org.come.Frame.ZhuFrame;
import org.come.entity.Goodstable;
import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import org.come.bean.GoodsBean;
import org.come.Jpanel.RechargeVIPJpanel;
import java.awt.event.MouseListener;

public class RechargeVIPGoodsMouslisten implements MouseListener
{
    private int index;
    RechargeVIPJpanel rechargeVIPJpanel;
    private GoodsBean goodsBean;
    
    public RechargeVIPGoodsMouslisten(int index) {
        this.index = index;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isMetaDown()) {
            FormsManagement.HideForm(3002);
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        int index = this.index;
        RechargeVIPJpanel rechargeVIPJpanel = this.rechargeVIPJpanel;
        if (index < RechargeVIPJpanel.GoodstableList.size()) {
            RechargeVIPJpanel rechargeVIPJpanel2 = this.rechargeVIPJpanel;
            if (RechargeVIPJpanel.GoodstableList.get(this.index) != null) {
                RechargeVIPJpanel rechargeVIPJpanel3 = this.rechargeVIPJpanel;
                Goodstable goodstable = (Goodstable)RechargeVIPJpanel.GoodstableList.get(this.index);
                ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
            }
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
}
