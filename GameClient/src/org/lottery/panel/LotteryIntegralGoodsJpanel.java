package org.lottery.panel;

import java.awt.Color;
import org.come.until.CutButtonImage;
import java.awt.Graphics;
import org.come.until.GoodsListFromServerUntil;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import org.come.model.Shop;
import org.come.mouslisten.AddTeXiaoMouslisten;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.tool.btn.ShoppingBuyBtn;
import javax.swing.JPanel;

public class LotteryIntegralGoodsJpanel extends JPanel
{
    private ShoppingBuyBtn btnBuy;
    private JLabel labBorder;
    private JLabel labImg;
    public ImageIcon icon;
    private AddTeXiaoMouslisten xiaoMouslisten;
    private Shop shop;
    private ImageIcon iconLine;
    
    public LotteryIntegralGoodsJpanel(Shop shop) {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(250, 65));
            this.setLayout(null);
            this.setOpaque(false);
            this.setBackground(UIUtils.Color_BACK);
            this.shop = shop;
            this.xiaoMouslisten = new AddTeXiaoMouslisten();
            (this.labImg = new JLabel()).setBounds(21, 8, 42, 41);
            this.labImg.setIcon(GoodsListFromServerUntil.imgpathAdaptive(shop.getShopskin(), 42, 41));
            this.labImg.addMouseListener(this.xiaoMouslisten);
            this.add(this.labImg);
            (this.btnBuy = new ShoppingBuyBtn("inkImg/button1/B20.png", 1, "兑换", 4, this, "")).setBounds(180, 20, 60, 26);
            this.add(this.btnBuy);
            (this.labBorder = new JLabel()).setBounds(20, 7, 44, 43);
            this.labBorder.setIcon(this.icon);
            this.add(this.labBorder);
        }
        else {
            this.setPreferredSize(new Dimension(250, 65));
            this.setLayout(null);
            this.setOpaque(false);
            this.setBackground(UIUtils.Color_BACK);
            this.shop = shop;
            this.xiaoMouslisten = new AddTeXiaoMouslisten();
            (this.labImg = new JLabel()).setBounds(21, 8, 42, 41);
            this.labImg.setIcon(GoodsListFromServerUntil.imgpathAdaptive(shop.getShopskin(), 42, 41));
            this.labImg.addMouseListener(this.xiaoMouslisten);
            this.add(this.labImg);
            (this.btnBuy = new ShoppingBuyBtn("inkImg/hongmu/6026.png", 1, "兑换", 4, this)).setBounds(180, 20, 60, 26);
            this.add(this.btnBuy);
            (this.labBorder = new JLabel()).setBounds(20, 7, 44, 43);
            this.labBorder.setIcon(this.icon);
            this.add(this.labBorder);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            this.icon = new ImageIcon("inkImg/danxin/goodse/9_11.png");
            g.drawImage(this.icon.getImage(), 20, 5, 44, 43, this);
            if (this.iconLine == null) {
                this.iconLine = CutButtonImage.getImage("img/xy2uiimg/分割线_w225,h1.png", 225, 1);
            }
            if (this.shop != null) {
                g.drawImage(this.iconLine.getImage(), 14, 62, 225, 1, this);
                g.setFont(UIUtils.TEXT_FONT1);
                g.setColor(Color.white);
                g.drawString(this.shop.getShopname(), 81, 20);
                g.setColor(UIUtils.COLOR_Wing1);
                g.drawString("积分：" + this.shop.getShopprice(), 81, 50);
            }
        }
        else {
            this.icon = new ImageIcon("img/xy2uiimg/物品框_w44,h43.png");
            g.drawImage(this.icon.getImage(), 20, 5, 44, 43, this);
            if (this.iconLine == null) {
                this.iconLine = CutButtonImage.getImage("img/xy2uiimg/分割线_w225,h1.png", 225, 1);
            }
            if (this.shop != null) {
                g.drawImage(this.iconLine.getImage(), 14, 62, 225, 1, this);
                g.setFont(UIUtils.TEXT_FONT1);
                g.setColor(Color.white);
                g.drawString(this.shop.getShopname(), 81, 20);
                g.setColor(UIUtils.COLOR_Wing1);
                g.drawString("积分：" + this.shop.getShopprice(), 81, 50);
            }
        }
    }
    
    public void clearWindow() {
        this.shop = null;
        this.labImg.setIcon(null);
        this.btnBuy.setBtn(0);
        this.btnBuy.setIcon(null);
        this.btnBuy.setText("");
        this.labBorder.setIcon(null);
        this.xiaoMouslisten.setEshop(null);
    }
    
    public void getShopData(Shop shop) {
        this.shop = shop;
        this.labImg.setIcon(GoodsListFromServerUntil.imgpathAdaptive(shop.getShopskin(), 42, 41));
        this.labBorder.setIcon(this.icon);
        this.btnBuy.setBtn(1);
    }
    
    public ShoppingBuyBtn getBtnBuy() {
        return this.btnBuy;
    }
    
    public void setBtnBuy(ShoppingBuyBtn btnBuy) {
        this.btnBuy = btnBuy;
    }
    
    public JLabel getLabImg() {
        return this.labImg;
    }
    
    public void setLabImg(JLabel labImg) {
        this.labImg = labImg;
    }
    
    public JLabel getLabBorder() {
        return this.labBorder;
    }
    
    public void setLabBorder(JLabel labBorder) {
        this.labBorder = labBorder;
    }
    
    public AddTeXiaoMouslisten getXiaoMouslisten() {
        return this.xiaoMouslisten;
    }
    
    public void setXiaoMouslisten(AddTeXiaoMouslisten xiaoMouslisten) {
        this.xiaoMouslisten = xiaoMouslisten;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public Shop getShop() {
        return this.shop;
    }
    
    public void setShop(Shop shop) {
        this.shop = shop;
    }
    
    public ImageIcon getIconLine() {
        return this.iconLine;
    }
    
    public void setIconLine(ImageIcon iconLine) {
        this.iconLine = iconLine;
    }
}
