package org.come.mouslisten;

import java.util.ArrayList;
import com.tool.Stall.CommodityBean;
import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import org.come.until.GoodsListFromServerUntil;
import java.awt.event.MouseEvent;
import java.util.List;
import org.come.Jpanel.spot.stall.SpotStallSellJpanel;
import java.awt.event.MouseListener;

public class TradeChoseGoodsMouslisten implements MouseListener
{
    private int index;
    private SpotStallSellJpanel spotStallSellJpanel;
    public static List<String> list;
    
    public TradeChoseGoodsMouslisten(int index) {
        this.index = index;
    }
    
    public TradeChoseGoodsMouslisten(int index, SpotStallSellJpanel spotStallSellJpanel) {
        this.index = index;
        this.spotStallSellJpanel = spotStallSellJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        Goodstable good = GoodsListFromServerUntil.Getgood(this.index);
        if (good == null) {
            return;
        }
        if (this.spotStallSellJpanel != null) {
            this.spotStallSellJpanel.showSelelctBorder(0, this.index);
        }
        if (GoodsListFromServerUntil.isJY(good)) {
            ZhuFrame.getZhuJpanel().addPrompt2("物品无法交易");
            return;
        }
        if (e.getButton() == 3) {
            CommodityBean commodity = this.spotStallSellJpanel.getCommodity(good);
            commodity.setSum((int)good.getUsetime());
            this.spotStallSellJpanel.setCurrentCommodity(commodity);
            this.spotStallSellJpanel.listing();
        }
        else {
            this.spotStallSellJpanel.setCurrentCommodity(this.spotStallSellJpanel.getCommodity(good));
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        TradeChoseGoodsMouslisten.list.add(this.index + "");
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        TradeChoseGoodsMouslisten.list.remove(this.index + "");
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if (GoodsListFromServerUntil.Getgood(this.index) != null) {
            Goodstable goodstable = GoodsListFromServerUntil.Getgood(this.index);
            ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
            this.spotStallSellJpanel.showEnteredBorder(0, this.index);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        ZhuFrame.getZhuJpanel().cleargoodtext();
        this.spotStallSellJpanel.showEnteredBorder(0, -1);
    }
    
    static {
        TradeChoseGoodsMouslisten.list = new ArrayList<>();
    }
}
