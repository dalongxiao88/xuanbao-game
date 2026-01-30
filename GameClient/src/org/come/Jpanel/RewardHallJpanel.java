package org.come.Jpanel;

import javax.swing.BorderFactory;
import org.come.mouslisten.ShopFyMouslisten;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.Util;
import java.awt.Graphics;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import com.tool.btn.LotteryBtn;
import java.math.BigDecimal;
import org.come.model.Shop;
import javax.swing.JLabel;
import org.come.entity.Goodstable;
import java.util.List;
import javax.swing.JPanel;

public class RewardHallJpanel extends JPanel
{
    private List<Goodstable> listGoods;
    private List<JLabel> pages;
    private int index;
    private Shop shop;
    private int xz;
    private BigDecimal Unitprice;
    private BigDecimal Totalsum;
    private BigDecimal lotteryNum;
    private LotteryBtn btnlottery;
    public JLabel[] GoodsListLabel;
    private int Flag;
    private int count;
    private ImageIcon icon;
    private ImageIcon image;
    
    public RewardHallJpanel() throws Exception {
        this.index = 0;
        this.xz = -1;
        this.GoodsListLabel = new JLabel[24];
        this.Flag = 0;
        this.count = 1;
        this.image = new ImageIcon("inkImg/button/B162.png");
        this.setPreferredSize(new Dimension(380, 387));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 59);
        offBtn.setBounds(343, 10, 25, 25);
        this.add(offBtn);
        this.Unitprice = new BigDecimal(50);
        this.Totalsum = new BigDecimal(0);
        int val = this.Totalsum.divide(this.Unitprice).intValue();
        this.lotteryNum = new BigDecimal(val);
        (this.btnlottery = new LotteryBtn("inkImg/button/32.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "抽奖", this)).setBounds(190, 336, 60, 26);
        this.add(this.btnlottery);
        for (int i = 0; i < 24; ++i) {
            this.GoodsListLabel[i] = new JLabel();
            if (this.Flag < 6 && this.count == 1) {
                this.GoodsListLabel[i].setBounds(49 + this.Flag * 51, 33 + (this.count - 1) * 51, 48, 48);
                ++this.Flag;
                this.add(this.GoodsListLabel[i]);
            }
            if (this.Flag < 6 && this.count == 2) {
                this.GoodsListLabel[i].setBounds(49 + this.Flag * 51, 33 + (this.count - 1) * 51, 48, 48);
                ++this.Flag;
                this.add(this.GoodsListLabel[i]);
            }
            if (this.Flag < 6 && this.count == 3) {
                this.GoodsListLabel[i].setBounds(49 + this.Flag * 51, 33 + (this.count - 1) * 51, 48, 48);
                ++this.Flag;
                this.add(this.GoodsListLabel[i]);
            }
            if (this.Flag < 6 && this.count == 4) {
                this.GoodsListLabel[i].setBounds(49 + this.Flag * 51, 33 + (this.count - 1) * 51, 48, 48);
                ++this.Flag;
                this.add(this.GoodsListLabel[i]);
            }
            else if (this.Flag == 6) {
                this.Flag = 0;
                ++this.count;
            }
        }
        this.image.setImage(this.image.getImage().getScaledInstance(38, 20, 1));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/background/S82.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 380, 387, this);
        Util.drawPrice(g, this.Unitprice, 165, 272);
        Util.drawPrice(g, this.lotteryNum, 165, 296);
        Util.drawPrice(g, this.Totalsum, 165, 321);
    }
    
    public void ShoppingsetIcon(int index) {
        this.index = index;
        this.fysum();
        this.xz = -1;
        for (int i = 0; i < this.GoodsListLabel.length; ++i) {
            this.GoodsListLabel[i].setIcon(null);
            this.GoodsListLabel[i].setBorder(null);
        }
        this.shop = null;
        for (int size = (this.listGoods.size() > (index + 1) * 24) ? 24 : (this.listGoods.size() - index * 24), j = 0; j < size; ++j) {
            ImageIcon image = GoodsListFromServerUntil.imgpathAdaptive(((Goodstable)this.listGoods.get(index * 24 + j)).getSkin(), 49, 49);
            this.GoodsListLabel[j].setIcon(image);
        }
    }
    
    public void fysum() {
        int sum = (this.listGoods.size() - 1) / 24 + 1;
        if (this.pages == null) {
            this.pages = new ArrayList<>();
        }
        for (int i = 0; i < sum && i < 8; ++i) {
            if (i >= this.pages.size()) {
                if (sum > 8 && i == 0) {
                    this.pages.add(this.fyjlb("首页", i));
                }
                else if (sum > 8 && i == 7) {
                    this.pages.add(this.fyjlb("尾页", i));
                }
                else if (sum > 8) {
                    if (this.index == 0) {
                        this.pages.add(this.fyjlb(i + "", i));
                    }
                    else if (this.index > sum - 5) {
                        int q = sum - 6;
                        this.pages.add(this.fyjlb(i + q + "", i));
                    }
                    else {
                        this.pages.add(this.fyjlb(i + this.index - 1 + "", i));
                    }
                }
                else {
                    this.pages.add(this.fyjlb(i + 1 + "", i));
                }
            }
            else if (sum > 8 && i == 0) {
                ((JLabel)this.pages.get(i)).setText("首页");
            }
            else if (sum > 8 && i == 7) {
                ((JLabel)this.pages.get(i)).setText("尾页");
            }
            else if (sum > 8) {
                if (this.index == 0) {
                    ((JLabel)this.pages.get(i)).setText(i + "");
                }
                else if (this.index > sum - 5) {
                    int q = sum - 6;
                    ((JLabel)this.pages.get(i)).setText(i + q + "");
                }
                else {
                    ((JLabel)this.pages.get(i)).setText(i + this.index - 1 + "");
                }
            }
            else {
                ((JLabel)this.pages.get(i)).setText(i + 1 + "");
            }
        }
        if (this.pages.size() > sum) {
            for (int i = this.pages.size() - 1; i >= sum; --i) {
                this.remove((Component)this.pages.get(i));
                this.pages.remove(i);
            }
        }
    }
    
    public JLabel fyjlb(String text, int i) {
        JLabel lab = new JLabel();
        lab.setBounds(47 + i * 41, 238, 39, 23);
        lab.setIcon(this.image);
        lab.setBackground(new Color(0, 0, 0, 0));
        lab.setText(text);
        lab.setForeground(Color.WHITE);
        lab.setHorizontalTextPosition(0);
        lab.setFont(UIUtils.TEXT_FONT1);
        lab.addMouseListener(new ShopFyMouslisten(lab, null, this));
        this.add(lab);
        return lab;
    }
    
    public void PaintingText(int shopPlace) {
        this.GoodsListLabel[shopPlace].setBorder(BorderFactory.createLineBorder(Color.red));
    }
    
    public void ClearText(int shopPlace) {
        if (this.xz != shopPlace) {
            this.GoodsListLabel[shopPlace].setBorder(null);
        }
    }
    
    public void showshop(List<Goodstable> listGoods) {
        this.listGoods = listGoods;
        if (this.listGoods == null) {
            this.listGoods = new ArrayList<>();
        }
        this.ShoppingsetIcon(0);
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public BigDecimal getUnitprice() {
        return this.Unitprice;
    }
    
    public void setUnitprice(BigDecimal unitprice) {
        this.Unitprice = unitprice;
    }
    
    public BigDecimal getTotalsum() {
        return this.Totalsum;
    }
    
    public void setTotalsum(BigDecimal totalsum) {
        this.Totalsum = totalsum;
    }
    
    public Shop getShop() {
        return this.shop;
    }
    
    public void setShop(Shop shop) {
        this.shop = shop;
    }
    
    public BigDecimal getLotteryNum() {
        return this.lotteryNum;
    }
    
    public void setLotteryNum(BigDecimal lotteryNum) {
        this.lotteryNum = lotteryNum;
    }
    
    public LotteryBtn getBtnlottery() {
        return this.btnlottery;
    }
    
    public void setBtnlottery(LotteryBtn btnlottery) {
        this.btnlottery = btnlottery;
    }
    
    public JLabel[] getGoodsListLabel() {
        return this.GoodsListLabel;
    }
    
    public void setGoodsListLabel(JLabel[] goodsListLabel) {
        this.GoodsListLabel = goodsListLabel;
    }
    
    public List<Goodstable> getListGoods() {
        return this.listGoods;
    }
    
    public void setListGoods(List<Goodstable> listGoods) {
        this.listGoods = listGoods;
    }
}
