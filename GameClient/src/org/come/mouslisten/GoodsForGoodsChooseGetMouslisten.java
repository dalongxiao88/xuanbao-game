package org.come.mouslisten;

import com.tool.btn.GoodsExchangeBtn;
import javax.swing.border.Border;
import java.awt.event.MouseEvent;
import org.come.bean.GoodsForGoodsBean;
import org.come.Jpanel.GoodsForDelGoodsJpanel;
import org.come.Jpanel.GoodForGoodJpanel;
import java.awt.event.MouseListener;

public class GoodsForGoodsChooseGetMouslisten implements MouseListener
{
    private GoodForGoodJpanel[] goodForGoodJpanel;
    private int chosetype;
    private GoodsForDelGoodsJpanel goodsForDelGoodsJpanel;
    private GoodsForGoodsBean goodsbean;
    
    public GoodsForGoodsChooseGetMouslisten(GoodForGoodJpanel[] goodForGoodJpanel, int chosei, GoodsForDelGoodsJpanel goodsForDelGoodsJpanel, GoodsForGoodsBean goodsbean) {
        this.chosetype = chosei;
        this.goodForGoodJpanel = goodForGoodJpanel;
        this.goodsForDelGoodsJpanel = goodsForDelGoodsJpanel;
        this.goodsbean = goodsbean;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        for (int i = 0; i < this.goodForGoodJpanel.length; ++i) {
            if (i != this.chosetype) {
                this.goodForGoodJpanel[i].setBorder((Border)null);
                this.goodForGoodJpanel[i].repaint();
            }
        }
        this.goodsForDelGoodsJpanel.initGoodMes(this.goodsbean);
        this.goodsForDelGoodsJpanel.repaint();
        GoodsExchangeBtn.goodsbean = this.goodsbean;
        this.goodsForDelGoodsJpanel.removeAll();
        this.goodsForDelGoodsJpanel.initGoodMes(this.goodsbean);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
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
