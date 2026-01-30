package org.come.mouslisten;

import java.util.ArrayList;
import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import org.come.Frame.TestpackJframe;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.util.List;
import org.come.Jpanel.GiveJpanel;
import java.awt.event.MouseListener;

public class GiveGoodsMouslisten implements MouseListener
{
    private int goodsPlace;
    private GiveJpanel giveJpanel;
    public static List<String> list;
    
    public GiveGoodsMouslisten(int goodsPlace, GiveJpanel giveJpanel) {
        this.goodsPlace = goodsPlace;
        this.giveJpanel = giveJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        GiveGoodsMouslisten.list.add(this.goodsPlace + "");
        Music.addyinxiao("关闭窗口.mp3");
        this.giveJpanel.xuanzhong(GoodsListFromServerUntil.getGoodslist()[this.goodsPlace + GoodsListFromServerUntil.Pagenumber * 24], this.goodsPlace);
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        GiveGoodsMouslisten.list.remove(this.goodsPlace + "");
        Music.addyinxiao("关闭窗口.mp3");
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        Goodstable goodstable = GoodsListFromServerUntil.getGoodslist()[this.goodsPlace + GoodsListFromServerUntil.Pagenumber * 24];
        if (goodstable != null) {
            this.giveJpanel.PaintingText(this.goodsPlace);
            TestpackJframe.getTestpackJframe().getJpac().PaintingText(this.goodsPlace);
            ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        this.giveJpanel.ClearText(this.goodsPlace);
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
    
    public int getGoodsPlace() {
        return this.goodsPlace;
    }
    
    public void setGoodsPlace(int goodsPlace) {
        this.goodsPlace = goodsPlace;
    }
    
    static {
        GiveGoodsMouslisten.list = new ArrayList<>();
    }
}
