package org.come.mouslisten;

import org.come.Frame.ZhuFrame;
import org.come.Frame.TestpackJframe;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import org.come.until.ZxpackFromServerUntil;
import org.come.entity.Goodstable;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ZxpackGoodslisten implements MouseListener
{
    private int goodPlace;
    
    public ZxpackGoodslisten(int goodPlace) {
        this.goodPlace = goodPlace;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Goodstable goodstable = (Goodstable)ZxpackFromServerUntil.getGoodslist().get(this.goodPlace);
        OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.Zxpack, Integer.valueOf(this.goodPlace), "#W确定要选择#R " + goodstable.getGoodsname() + " #W这个道具吗?");
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if (ZxpackFromServerUntil.getGoodslist() != null && !ZxpackFromServerUntil.getGoodslist().isEmpty() && ZxpackFromServerUntil.getGoodslist().size()>this.goodPlace) {
            Goodstable goodstable = (Goodstable)ZxpackFromServerUntil.getGoodslist(). get(this.goodPlace);
            if (goodstable != null) {
                TestpackJframe.getTestpackJframe().getJpac().PaintingText(this.goodPlace);
                ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
            }
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        TestpackJframe.getTestpackJframe().getJpac().ClearText();
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
}
