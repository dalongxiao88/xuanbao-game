package org.come.Jpanel;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import org.come.until.CutButtonImage;
import java.awt.Dimension;
import org.come.entity.Goodstable;
import org.cbg.mouslisten.GodsForGodMouslisten;
import org.come.bean.GoodsForGoodsBean;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GoodForGoodJpanel extends JPanel
{
    private JLabel goodsImg;
    private JLabel gooname;
    private JLabel goodType;
    private GoodsForGoodsBean goodsBean;
    private final GodsForGodMouslisten goomouslinte;
    
    public Goodstable GetGoodstable(String goodis, GoodsForGoodsBean goodsBean) {
        for (int i = 0; i < goodsBean.getDelGoodstables().size(); ++i) {
            String goodsmes = "" + ((Goodstable)goodsBean.getGetApplyGoods().get(i)).getGoodsid();
            if (goodsmes.equals(goodis)) {
                return (Goodstable)goodsBean.getGetApplyGoods().get(i);
            }
        }
        return null;
    }
    
    public GoodForGoodJpanel(GoodsForGoodsBean goodsBean) {
        this.setPreferredSize(new Dimension(400, 40));
        this.setLayout(null);
        this.setOpaque(false);
        Goodstable goodstable = (Goodstable)goodsBean.getGetApplyGoods().get(0);
        (this.goodsImg = new JLabel()).setBounds(0, 0, 40, 40);
        ImageIcon image = CutButtonImage.getImage("img/item/" + goodstable.getSkin() + ".png", 40, 40);
        this.goodsImg.setIcon(image);
        this.goomouslinte = new GodsForGodMouslisten(goodstable);
        this.goodsImg.addMouseListener(this.goomouslinte);
        this.add(this.goodsImg);
        (this.gooname = new JLabel(goodsBean.getNameString())).setBounds(125, 5, 100, 40);
        Font f = new Font("宋体", 0, 16);
        this.gooname.setFont(f);
        this.gooname.setForeground(Color.yellow);
        this.add(this.gooname);
        (this.goodType = new JLabel(this.typereturn(Integer.valueOf(goodsBean.getType())))).setBounds(250, 5, 100, 40);
        this.goodType.setFont(f);
        this.goodType.setForeground(Color.yellow);
        this.add(this.goodType);
    }
    
    public String typereturn(Integer typea) {
        if ((int)typea == 1) {
            return "杂物 ";
        }
        if ((int)typea == 2) {
            return "套装";
        }
        if ((int)typea == 3) {
            return "仙器";
        }
        return ((int)typea == 4) ? "宠物" : null;
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
    
    public JLabel getGoodType() {
        return this.goodType;
    }
    
    public void setGoodType(JLabel goodType) {
        this.goodType = goodType;
    }
}
