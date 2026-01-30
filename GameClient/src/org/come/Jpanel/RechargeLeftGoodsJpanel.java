package org.come.Jpanel;

import org.come.until.GoodsListFromServerUntil;
import java.awt.Color;
import java.awt.Graphics;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import org.come.until.CutButtonImage;
import org.come.mouslisten.ShopingImgBorderMosulisten;
import javax.swing.ImageIcon;
import org.come.model.Eshop;
import javax.swing.JLabel;
import com.tool.btn.ShopBuyBtn;
import javax.swing.JPanel;

public class RechargeLeftGoodsJpanel extends JPanel
{
    private ShopBuyBtn btnBuy;
    private JLabel labBorder;
    private JLabel labImg;
    private String name;
    private String value;
    private String buyTime;
    private Eshop eshop;
    private ImageIcon icon;
    private ShopingImgBorderMosulisten shopingImgBorderMosulisten;
    
    public RechargeLeftGoodsJpanel(Eshop eshop) {
        this.icon = CutButtonImage.getImage("img/xy2uiimg/物品框_90×90.png", -1, -1);
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(200, 90));
            this.setLayout(null);
            this.setOpaque(true);
            this.setBackground(UIUtils.Color_BACK);
            this.shopingImgBorderMosulisten = new ShopingImgBorderMosulisten(eshop);
            (this.labImg = new JLabel()).setBounds(0, 0, 90, 90);
            this.labImg.addMouseListener(this.shopingImgBorderMosulisten);
            this.add(this.labImg);
            this.icon = CutButtonImage.getImage("inkImg/background/50.png", -1, -1);
            (this.labBorder = new JLabel()).setBounds(0, 0, 90, 90);
            this.labBorder.setIcon(this.icon);
            this.add(this.labBorder);
            (this.btnBuy = new ShopBuyBtn("img/xy2uiimg/文字按钮_购买_w34,h51.png", 1, "", 10, eshop)).setBounds(100, 70, 34, 17);
            this.add(this.btnBuy);
        }
        else {
            this.setPreferredSize(new Dimension(200, 90));
            this.setLayout(null);
            this.setOpaque(true);
            this.setBackground(UIUtils.Color_BACK);
            this.shopingImgBorderMosulisten = new ShopingImgBorderMosulisten(eshop);
            (this.labImg = new JLabel()).setBounds(0, 0, 90, 90);
            this.labImg.addMouseListener(this.shopingImgBorderMosulisten);
            this.add(this.labImg);
            this.icon = CutButtonImage.getImage("img/xy2uiimg/物品框_90×90.png", -1, -1);
            (this.labBorder = new JLabel()).setBounds(0, 0, 90, 90);
            this.labBorder.setIcon(this.icon);
            this.add(this.labBorder);
            (this.btnBuy = new ShopBuyBtn("img/xy2uiimg/文字按钮_购买_w34,h51.png", 1, "", 10, eshop)).setBounds(100, 70, 34, 17);
            this.add(this.btnBuy);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(UIUtils.TEXT_FONT2);
        g.setColor(Color.white);
        if (this.name != null) {
            g.drawString(this.name, 100, 18);
        }
        g.setColor(Color.red);
        if (this.value != null) {
            g.drawString(this.value + "仙玉", 100, 38);
        }
        g.setColor(Color.white);
        if (this.buyTime != null) {
            g.drawString(this.buyTime + "次数", 100, 58);
        }
    }
    
    public void clearWindow() {
        this.name = "";
        this.value = "";
        this.labImg.setIcon(null);
        this.btnBuy.setBtn(0);
        this.btnBuy.setIcon(null);
        this.btnBuy.setText("");
        this.labBorder.setIcon(null);
        this.shopingImgBorderMosulisten.setEshop(null);
        this.btnBuy.setEshop(null);
    }
    
    public void addEshopMessage(Eshop eshop) {
        if (eshop == null) {
            return;
        }
        if (MyIsif.getStyle().equals("水墨")) {
            this.name = eshop.getEshopname();
            this.value = eshop.getEshopprice();
            this.labImg.setIcon(GoodsListFromServerUntil.imgpathAdaptive(eshop.getEshopskin(), 90, 90));
            this.btnBuy.setBtn(1);
            try {
                this.btnBuy.setIcons(CutButtonImage.cuts("img/xy2uiimg/文字按钮_购买_w34,h51.png"));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            this.icon = CutButtonImage.getImage("inkImg/background/50.png", -1, -1);
            this.labBorder.setIcon(this.icon);
            this.shopingImgBorderMosulisten.setEshop(eshop);
            this.btnBuy.setEshop(eshop);
        }
        else {
            this.name = eshop.getEshopname();
            this.value = eshop.getEshopprice();
            this.labImg.setIcon(GoodsListFromServerUntil.imgpathAdaptive(eshop.getEshopskin(), 90, 90));
            this.btnBuy.setBtn(1);
            try {
                this.btnBuy.setIcons(CutButtonImage.cuts("img/xy2uiimg/文字按钮_购买_w34,h51.png"));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            this.icon = CutButtonImage.getImage("img/xy2uiimg/物品框_90×90.png", -1, -1);
            this.labBorder.setIcon(this.icon);
            this.shopingImgBorderMosulisten.setEshop(eshop);
            this.btnBuy.setEshop(eshop);
        }
    }
    
    public ShopBuyBtn getBtnBuy() {
        return this.btnBuy;
    }
    
    public void setBtnBuy(ShopBuyBtn btnBuy) {
        this.btnBuy = btnBuy;
    }
    
    public JLabel getLabImg() {
        return this.labImg;
    }
    
    public void setLabImg(JLabel labImg) {
        this.labImg = labImg;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public JLabel getLabBorder() {
        return this.labBorder;
    }
    
    public void setLabBorder(JLabel labBorder) {
        this.labBorder = labBorder;
    }
    
    public ShopingImgBorderMosulisten getShopingImgBorderMosulisten() {
        return this.shopingImgBorderMosulisten;
    }
    
    public void setShopingImgBorderMosulisten(ShopingImgBorderMosulisten shopingImgBorderMosulisten) {
        this.shopingImgBorderMosulisten = shopingImgBorderMosulisten;
    }
    
    public Eshop getEshop() {
        return this.eshop;
    }
    
    public void setEshop(Eshop eshop) {
        this.eshop = eshop;
    }
}
