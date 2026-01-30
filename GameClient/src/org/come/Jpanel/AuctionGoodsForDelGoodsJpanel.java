package org.come.Jpanel;

import org.come.entity.Goodstable;
import java.text.DecimalFormat;
import org.come.bean.AuctionGoodsForGoodsBean;
import java.awt.Dimension;
import org.cbg.mouslisten.GodsForGodMouslisten;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AuctionGoodsForDelGoodsJpanel extends JPanel
{
    private JLabel goodsImg;
    private JLabel gooname;
    private GodsForGodMouslisten[] goomouslinte;
    private final AuctionGoodsExchangeJpanel auctionGoodsExchangeJpanel;
    
    public AuctionGoodsForDelGoodsJpanel(AuctionGoodsExchangeJpanel auctionGoodsExchangeJpanel) {
        this.goomouslinte = new GodsForGodMouslisten[9];
        this.auctionGoodsExchangeJpanel = auctionGoodsExchangeJpanel;
        this.setPreferredSize(new Dimension(400, 85));
        this.setLayout(null);
        this.setOpaque(false);
    }
    
    public void initGoodMes(AuctionGoodsForGoodsBean goodsBean) {
        String[] mesa = goodsBean.getDelte().split("\\|");
        int index = 0;
        for (int i = 0; i < mesa.length; ++i) {
            if (mesa[i].startsWith("D")) {
                String[] mesa2 = mesa[i].split("=");
                if (mesa2.length > 1) {
                    this.auctionGoodsExchangeJpanel.getNeedMoney().setText(new DecimalFormat().format(Long.valueOf(mesa2[1])));
                    this.auctionGoodsExchangeJpanel.getNeedMoney().setVisible(false);
                }
            }
            else {
                ++index;
            }
        }
        for (int i = index; i < 9; ++i) {
            this.auctionGoodsExchangeJpanel.getNeedIcons()[index].setIcon(null);
            this.auctionGoodsExchangeJpanel.getNeedIcons()[index].removeMouseListener(this.goomouslinte[index]);
            this.auctionGoodsExchangeJpanel.getNeedCount()[index].setIcon(null);
            this.auctionGoodsExchangeJpanel.getNeedCountText()[index].setText("");
            ++index;
        }
    }
    
    public Goodstable GetGoodstable(String goodis, AuctionGoodsForGoodsBean goodsBean) {
        for (int i = 0; i < goodsBean.getDelGoodstables().size(); ++i) {
            String goodsmes = "" + ((Goodstable)goodsBean.getDelGoodstables().get(i)).getGoodsid();
            if (goodsmes.equals(goodis)) {
                return (Goodstable)goodsBean.getDelGoodstables().get(i);
            }
        }
        return null;
    }
    
    public JLabel getGoodsImg() {
        return this.goodsImg;
    }
    
    public void setGoodsImg(JLabel goodsImg) {
        this.goodsImg = goodsImg;
    }
    
    public JLabel getGooname() {
        return this.gooname;
    }
    
    public void setGooname(JLabel gooname) {
        this.gooname = gooname;
    }
}
