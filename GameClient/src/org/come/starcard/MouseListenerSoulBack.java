package org.come.starcard;

import org.come.Frame.TestpackJframe;
import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import org.come.until.GoodsListFromServerUntil;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class MouseListenerSoulBack extends MouseAdapter
{
    private int i;
    private JpanelSoulBackMain jpanelSoulBackMain;
    
    public MouseListenerSoulBack(int i, JpanelSoulBackMain jpanelSoulBackMain) {
        this.i = i;
        this.jpanelSoulBackMain = jpanelSoulBackMain;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Goodstable starCardGoods = GoodsListFromServerUntil.getStarCardGoods(this.i);
        if (starCardGoods != null && this.jpanelSoulBackMain != null) {
            if ((int)starCardGoods.getStatus() == 1) {
                ZhuFrame.getZhuJpanel().addPrompt2("该星卡已装备，无法魂归");
                return;
            }
            if (starCardGoods.getGoodlock() == 1) {
                ZhuFrame.getZhuJpanel().addPrompt2("该星卡已上锁，无法魂归");
                return;
            }
            this.jpanelSoulBackMain.addListNum(starCardGoods);
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        Goodstable goodstable = GoodsListFromServerUntil.getStarCardGoods(this.i);
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
