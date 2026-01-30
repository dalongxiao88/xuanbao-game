package org.come.xingpan;

import org.come.Frame.TestpackJframe;
import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import org.come.until.GoodsListFromServerUntil;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class MouseListenerSoulBack extends MouseAdapter
{
    private int i;
    private JpanelXingBackMain JpanelXingBackMain;
    
    public MouseListenerSoulBack(int i, JpanelXingBackMain JpanelXingBackMain) {
        this.i = i;
        this.JpanelXingBackMain = JpanelXingBackMain;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Goodstable starCardGoods = GoodsListFromServerUntil.getStarCardGoods(this.i);
        if (starCardGoods != null && this.JpanelXingBackMain != null) {
            if ((int)starCardGoods.getStatus() == 1) {
                ZhuFrame.getZhuJpanel().addPrompt2("该星卡已装备，无法魂归");
                return;
            }
            if (starCardGoods.getGoodlock() == 1) {
                ZhuFrame.getZhuJpanel().addPrompt2("该星卡已上锁，无法魂归");
                return;
            }
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
