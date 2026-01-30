package org.come.Jpanel;

import javax.swing.ImageIcon;
import org.come.entity.Goodstable;
import java.awt.Color;
import java.awt.Font;
import org.come.until.CutButtonImage;
import java.awt.Dimension;
import org.cbg.mouslisten.GodsForGodMouslisten;
import org.come.bean.GoodsForGoodsBean;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GoodsForDelGoodsJpanel extends JPanel
{
    private JLabel goodsImg;
    private JLabel gooname;
    private JLabel goonameS;
    private GoodsForGoodsBean goodsBean;
    private GodsForGodMouslisten goomouslinte;
    private final GoodsExchangeJpanel goodsExchangeJpanel;
    
    public GoodsForDelGoodsJpanel(GoodsExchangeJpanel goodsExchangeJpanel) {
        this.goodsExchangeJpanel = goodsExchangeJpanel;
        this.setPreferredSize(new Dimension(400, 85));
        this.setLayout(null);
        this.setOpaque(false);
    }
    
    public void initGoodMes(GoodsForGoodsBean goodsBean) {
        String[] mesa = goodsBean.getDelte().split("\\|");
        for (int i = 0; i < mesa.length; ++i) {
            String[] mesa2 = mesa[i].split("\\=");
            Goodstable goodstable = this.GetGoodstable(mesa2[0], goodsBean);
            (this.goodsImg = new JLabel()).setBounds(2, i * 65, 60, 60);
            ImageIcon image = CutButtonImage.getImage("img/item/" + goodstable.getSkin() + ".png", 60, 60);
            this.goodsImg.setIcon(image);
            this.goomouslinte = new GodsForGodMouslisten(goodstable);
            this.goodsImg.addMouseListener(this.goomouslinte);
            this.add(this.goodsImg);
            (this.goonameS = new JLabel(goodstable.getGoodsname())).setBounds(65, 5 + i * 65, 100, 40);
            Font f = new Font("宋体", 0, 10);
            this.goonameS.setFont(f);
            this.goonameS.setForeground(Color.yellow);
            this.add(this.goonameS);
            (this.gooname = new JLabel(mesa2[1])).setBounds(127, 5 + i * 65, 100, 40);
            this.gooname.setFont(f);
            this.gooname.setForeground(Color.yellow);
            this.add(this.gooname);
        }
    }
    
    public Goodstable GetGoodstable(String goodis, GoodsForGoodsBean goodsBean) {
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
