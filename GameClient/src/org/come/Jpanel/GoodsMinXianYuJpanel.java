package org.come.Jpanel;

import java.awt.Graphics;
import org.come.mouslisten.ShopingImgBorderMosulisten;
import java.awt.Font;
import org.come.mouslisten.ShopingOnlineBuyBtnForXianYuMouslisten;
import java.awt.Color;
import java.awt.Dimension;
import org.come.model.Eshop;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GoodsMinXianYuJpanel extends JPanel
{
    private JLabel buyBtn;
    private JLabel buyCount;
    private JLabel goodsImg;
    private ImageIcon icon;
    private ImageIcon icon2;
    private ImageIcon icon3;
    private ImageIcon icon4;
    
    public GoodsMinXianYuJpanel(Eshop eshop) {
        this.icon2 = new ImageIcon("img/xy2uiimg/59_png.xy2uiimg.pop.png");
        this.icon3 = new ImageIcon("img/xy2uiimg/22_png.xy2uiimg.ibg.png");
        this.icon4 = new ImageIcon("img/xy2uiimg/btn_anniu0000(1).png");
        this.setPreferredSize(new Dimension(80, 100));
        this.setLayout(null);
        (this.buyBtn = new JLabel()).setBounds(50, 80, 30, 20);
        this.buyBtn.setText("  买");
        this.buyBtn.setForeground(Color.yellow);
        this.buyBtn.addMouseListener(new ShopingOnlineBuyBtnForXianYuMouslisten(eshop, this.buyBtn));
        (this.buyCount = new JLabel()).setBounds(5, 78, 60, 20);
        this.buyCount.setText(eshop.getEshopprice());
        this.buyCount.setFont(new Font("宋体", 1, 12));
        this.buyCount.setForeground(Color.yellow);
        (this.goodsImg = new JLabel()).setBounds(10, 15, 60, 60);
        ImageIcon image = new ImageIcon("img/item/" + eshop.getEshopskin() + ".png");
        image.setImage(image.getImage().getScaledInstance(60, 60, 1));
        this.goodsImg.setIcon(image);
        this.goodsImg.addMouseListener(new ShopingImgBorderMosulisten(eshop));
        this.add(this.buyBtn);
        this.add(this.buyCount);
        this.add(this.goodsImg);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("img/xy2uiimg/106_png.xy2uiimg.sitem.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 80, 100, this);
        g.drawImage(this.icon2.getImage(), 0, 78, 50, 20, this);
        g.drawImage(this.icon3.getImage(), 10, 15, 60, 60, this);
        g.drawImage(this.icon4.getImage(), 50, 80, 30, 20, this);
    }
}
