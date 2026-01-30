package org.come.mouslisten;

import java.util.ArrayList;
import org.come.Frame.ZhuFrame;
import org.come.Frame.CollectionJadeJframe;
import org.come.Frame.ExchangeValueJframe;
import org.come.entity.Goodstable;
import javax.swing.Icon;
import org.come.entity.PartJade;
import java.math.BigDecimal;
import org.come.until.AccessSuitMsgUntil;
import javax.swing.ImageIcon;
import org.come.until.GoodsListFromServerUntil;
import java.awt.event.MouseEvent;
import java.util.List;
import org.come.Jpanel.ExchangeValueJpanel;
import java.awt.event.MouseListener;

public class YuFuGoodsMouslisten implements MouseListener
{
    private int goodPlace;
    private ExchangeValueJpanel valueJpanel;
    public static List<String> list;
    
    public YuFuGoodsMouslisten(int goodPlace, ExchangeValueJpanel valueJpanel) {
        this.goodPlace = goodPlace;
        this.valueJpanel = valueJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (this.valueJpanel != null) {
            YuFuGoodsMouslisten.list.add(this.goodPlace + "");
            if (e.getButton() == 3) {
                Goodstable goodstable = GoodsListFromServerUntil.getGoodslist()[this.goodPlace + GoodsListFromServerUntil.Pagenumber * 24];
                if (goodstable != null) {
                    if ((long)goodstable.getType() == 505L) {
                        this.valueJpanel.getLabTzyf().setIcon(new ImageIcon(new ImageIcon("img/item/" + goodstable.getSkin() + ".png").getImage().getScaledInstance(54, 51, 10)));
                        this.valueJpanel.getTextNum().setText("1");
                        ExchangeValueJpanel.khdlxz = new BigDecimal(AccessSuitMsgUntil.exvalue);
                        this.valueJpanel.getJadeorGoodstableBean().setType(6);
                        this.valueJpanel.getJadeorGoodstableBean().setGoodstable(goodstable);
                        this.valueJpanel.getJadeorGoodstableBean().setPartJade((PartJade)null);
                    }
                    else {
                        this.valueJpanel.getLabTzyf().setIcon((Icon)null);
                        this.valueJpanel.getTextNum().setText("");
                        ExchangeValueJpanel.khdlxz = null;
                        this.valueJpanel.getJadeorGoodstableBean().setType(0);
                        this.valueJpanel.getJadeorGoodstableBean().setGoodstable((Goodstable)null);
                        this.valueJpanel.getJadeorGoodstableBean().setPartJade((PartJade)null);
                    }
                }
            }
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.valueJpanel != null) {
            YuFuGoodsMouslisten.list.remove(this.goodPlace + "");
        }
        else {
            YuFuGoodsMouslisten.list.remove(this.goodPlace + "");
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        int index = this.goodPlace + GoodsListFromServerUntil.Pagenumber * 24;
        if (GoodsListFromServerUntil.getGoodslist()[index] != null) {
            if (this.valueJpanel != null) {
                ExchangeValueJframe.getExchangeValueJframe().getValueJpanel().PaintingText(this.goodPlace);
            }
            else {
                CollectionJadeJframe.getCollectionJadeJframe().getJadeJpanel().PaintingText(this.goodPlace);
            }
            Goodstable goodstable = GoodsListFromServerUntil.getGoodslist()[index];
            ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        if (this.valueJpanel != null) {
            ExchangeValueJframe.getExchangeValueJframe().getValueJpanel().ClearText();
        }
        else {
            CollectionJadeJframe.getCollectionJadeJframe().getJadeJpanel().ClearText();
        }
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
    
    static {
        YuFuGoodsMouslisten.list = new ArrayList<>();
    }
}
