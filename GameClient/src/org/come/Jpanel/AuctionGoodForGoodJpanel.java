package org.come.Jpanel;

import java.awt.Graphics;
import org.come.until.MessagrFlagUntil;
import javax.swing.BorderFactory;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import org.come.until.Util;
import java.math.BigDecimal;
import java.awt.Font;
import org.come.until.CutButtonImage;
import java.awt.Color;
import java.awt.Dimension;
import org.come.entity.Goodstable;
import org.come.bean.GoodsForGoodsBean;
import javax.swing.ImageIcon;
import org.cbg.mouslisten.GodsForGodMouslisten;
import org.come.bean.AuctionGoodsForGoodsBean;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AuctionGoodForGoodJpanel extends JPanel
{
    private JLabel goodsImg;
    private JLabel gooname;
    private JLabel goodType;
    private JLabel bbackground;
    private JLabel select;
    private JLabel StartingPrice;
    private AuctionGoodsForGoodsBean goodsBean;
    private JLabel time;
    private final GodsForGodMouslisten goomouslinte;
    private ImageIcon icon;
    
    public Goodstable GetGoodstable(String goodis, GoodsForGoodsBean goodsBean) {
        for (int i = 0; i < goodsBean.getDelGoodstables().size(); ++i) {
            String goodsmes = "" + ((Goodstable)goodsBean.getGetApplyGoods().get(i)).getGoodsid();
            if (goodsmes.equals(goodis)) {
                return (Goodstable)goodsBean.getGetApplyGoods().get(i);
            }
        }
        return null;
    }
    
    public AuctionGoodForGoodJpanel(AuctionGoodsForGoodsBean goodsBean) {
        this.goodsBean = goodsBean;
        this.setPreferredSize(new Dimension(425, 43));
        this.setLayout(null);
        this.setOpaque(false);
        (this.bbackground = new JLabel()).setBounds(5, 0, 410, 43);
        (this.select = new JLabel()).setBounds(0, 0, 405, 43);
        this.select.setBackground(new Color(128, 128, 128, 42));
        Goodstable goodstable = (Goodstable)goodsBean.getGetApplyGoods().get(0);
        JLabel background = new JLabel();
        background.setBounds(9, 2, 38, 38);
        ImageIcon backgroundImage = CutButtonImage.getImage("inkimg/danxin/goodse/9_11.png", 38, 38);
        background.setIcon(backgroundImage);
        (this.goodsImg = new JLabel()).setBounds(9, 2, 38, 38);
        ImageIcon image = CutButtonImage.getImage("img/item/" + goodstable.getSkin() + ".png", 38, 38);
        this.goodsImg.setIcon(image);
        this.goomouslinte = new GodsForGodMouslisten(goodstable);
        this.goodsImg.addMouseListener(this.goomouslinte);
        this.add(this.goodsImg);
        this.add(background);
        (this.gooname = new JLabel(goodsBean.getAuctionExchange().getTime())).setBounds(325, 0, 160, 43);
        Font f = new Font("宋体", 0, 13);
        this.gooname.setFont(f);
        this.gooname.setForeground(Color.yellow);
        this.add(this.gooname);
        (this.StartingPrice = new JLabel(goodsBean.getNameString())).setBounds(120, 0, 150, 43);
        (this.time = new JLabel(goodsBean.getAuctionExchange().getWeek())).setBounds(262, 0, 160, 43);
        this.time.setFont(f);
        this.time.setForeground(Color.yellow);
        this.add(this.time);
        String[] mesa = goodsBean.getDelte().split("\\|");
        for (int i = 0; i < mesa.length; ++i) {
            if (mesa[i].startsWith("D")) {
                String[] mesa2 = mesa[i].split("=");
                if (mesa2.length > 1) {
                    Color priceColor = Util.getPriceColor(new BigDecimal(mesa2[1]));
                    this.StartingPrice.setForeground(priceColor);
                    String s = ((int)goodsBean.getAuctionExchange().getMoneyType() == 1) ? "(银两)" : "(仙玉)";
                    if (goodsBean.getAuctionExchange().getType() == 1) {
                        this.StartingPrice.setText(new DecimalFormat().format(Long.valueOf(mesa2[1])) + s);
                    }
                }
            }
        }
        this.StartingPrice.setFont(f);
        this.add(this.StartingPrice);
        (this.goodType = new JLabel(goodsBean.getNameString())).setBounds(51, 0, 90, 43);
        this.goodType.setFont(f);
        this.goodType.setForeground(Color.yellow);
        this.add(this.goodType);
        this.add(this.bbackground);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                AuctionGoodForGoodJpanel.this.goodsImg.setBounds(9, 1, 38, 38);
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                AuctionGoodForGoodJpanel.this.goodsImg.setBounds(9, 0, 38, 38);
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                AuctionGoodForGoodJpanel.this.bbackground.setBorder(BorderFactory.createLineBorder(new Color(35, 148, 218, 208), 2));
                if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE1)) {
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE12);
                }
                AuctionGoodForGoodJpanel.this.bbackground.setOpaque(false);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                AuctionGoodForGoodJpanel.this.bbackground.setOpaque(false);
                AuctionGoodForGoodJpanel.this.bbackground.setBorder(null);
                if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE12)) {
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                }
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/hongmu/exchange/12.png");
        }
        g.drawImage(this.icon.getImage(), 0, 40, 357, 43, this);
    }
    
    public String typereturn(Integer typea, AuctionGoodsForGoodsBean goodsBean) {
        if ((int)typea == 4) {
            return "(召唤兽)";
        }
        if ((int)typea == 1) {
            return "(装备）";
        }
        if ((int)typea == 3) {
            return "(稀珍)";
        }
        if ((int)typea == 5) {
            return "(宝图)";
        }
        return ((int)typea == 2) ? "(杂物)" : null;
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
    
    @Override
    public synchronized void addMouseListener(MouseListener l) {
        super.addMouseListener(l);
        this.goodType.addMouseListener(l);
        this.gooname.addMouseListener(l);
        this.goodsImg.addMouseListener(l);
        this.select.addMouseListener(l);
        this.bbackground.addMouseListener(l);
    }
}
