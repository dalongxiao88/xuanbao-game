package org.come.mouslisten;

import java.awt.event.MouseEvent;
import org.come.Jpanel.RewardHallJpanel;
import org.come.Jpanel.ShoppingBuyJpanel;
import javax.swing.JLabel;
import java.awt.event.MouseListener;

public class ShopFyMouslisten implements MouseListener
{
    private JLabel jLabel;
    private ShoppingBuyJpanel buyJpanel;
    private RewardHallJpanel rewardHallJpanel;
    
    public ShopFyMouslisten() {
    }
    
    public ShopFyMouslisten(JLabel jLabel, ShoppingBuyJpanel buyJpanel, RewardHallJpanel rewardHallJpanel) {
        this.jLabel = jLabel;
        this.buyJpanel = buyJpanel;
        this.rewardHallJpanel = rewardHallJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (this.buyJpanel != null) {
            int i = 0;
            if (this.jLabel.getText().equals("首页")) {
                i = 0;
            }
            else if (this.jLabel.getText().equals("尾页")) {
                i = (this.buyJpanel.getShops().size() - 1) / 24;
            }
            else {
                i = Integer.parseInt(this.jLabel.getText()) - 1;
            }
            if (i != this.buyJpanel.getIndex()) {
                this.buyJpanel.ShoppingsetIcon(i);
            }
        }
        if (this.rewardHallJpanel != null) {
            int i = 0;
            if (this.jLabel.getText().equals("首页")) {
                i = 0;
            }
            else if (this.jLabel.getText().equals("尾页")) {
                i = (this.rewardHallJpanel.getListGoods().size() - 1) / 24;
            }
            else {
                i = Integer.parseInt(this.jLabel.getText()) - 1;
            }
            if (i != this.rewardHallJpanel.getIndex()) {
                this.rewardHallJpanel.ShoppingsetIcon(i);
            }
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
