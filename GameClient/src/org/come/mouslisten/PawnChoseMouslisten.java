package org.come.mouslisten;

import java.util.ArrayList;
import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import org.come.Frame.TestpackJframe;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.util.List;
import org.come.Jpanel.PawnJfpanel;
import java.awt.event.MouseListener;

public class PawnChoseMouslisten implements MouseListener
{
    private int goodsPlace;
    private PawnJfpanel jfpanel;
    public static List<String> list;
    
    public PawnChoseMouslisten(int goodsPlace, PawnJfpanel jfpanel) {
        this.goodsPlace = goodsPlace;
        this.jfpanel = jfpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        PawnChoseMouslisten.list.add(this.goodsPlace + "");
        Music.addyinxiao("关闭窗口.mp3");
        this.jfpanel.xuanzhong(GoodsListFromServerUntil.getGoodslist()[this.goodsPlace + GoodsListFromServerUntil.Pagenumber * 24], this.goodsPlace, e.getButton() == 3);
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        PawnChoseMouslisten.list.remove(this.goodsPlace + "");
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        Goodstable goodstable = GoodsListFromServerUntil.getGoodslist()[this.goodsPlace + GoodsListFromServerUntil.Pagenumber * 24];
        if (goodstable != null) {
            this.jfpanel.PaintingText(this.goodsPlace);
            TestpackJframe.getTestpackJframe().getJpac().PaintingText(this.goodsPlace);
            ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        this.jfpanel.ClearText(this.goodsPlace);
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
    
    static {
        PawnChoseMouslisten.list = new ArrayList<>();
    }
}
