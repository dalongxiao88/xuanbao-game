package com.tool.btn;

import org.come.Frame.GoodDetailedJframe;
import org.come.until.FormsManagement;
import org.come.Frame.ZhuFrame;
import org.come.until.Util;
import java.awt.event.MouseEvent;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import org.come.model.Shop;
import org.come.model.Eshop;

public class ShopBuyBtn extends MoBanBtn
{
    private int caozuo;
    private Eshop eshop;
    private Shop shop;
    
    public ShopBuyBtn(String iconpath, int type, String text, int caozuo, Eshop eshop) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.eshop = eshop;
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT);
        this.setForeground(Color.white);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public ShopBuyBtn(String iconpath, int type, String text, int caozuo, Shop shop) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.shop = shop;
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT);
        this.setForeground(Color.white);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        switch (this.caozuo) {
            case 1: {
                if (this.eshop == null) {
                    return;
                }
                if (!Util.canBuyOrno) {
                    ZhuFrame.getZhuJpanel().addPrompt2("背包没有解锁!!");
                    FormsManagement.showForm(33);
                    return;
                }
                GoodDetailedJframe.getGoodDetailedJframe().getGoodDetailedJpanel().gainGoodsMessage(this.eshop);
                FormsManagement.showForm(44);
            }
            case 2: {
                if (this.shop == null) {
                    return;
                }
                if (!Util.canBuyOrno) {
                    ZhuFrame.getZhuJpanel().addPrompt2("背包没有解锁!!");
                    FormsManagement.showForm(33);
                    return;
                }
                GoodDetailedJframe.getGoodDetailedJframe().getGoodDetailedJpanel().gainGoodsMessage(this.shop);
                FormsManagement.showForm(44);
                break;
            }
        }
    }
    
    public int getCaozuo() {
        return this.caozuo;
    }
    
    public void setCaozuo(int caozuo) {
        this.caozuo = caozuo;
    }
    
    public Eshop getEshop() {
        return this.eshop;
    }
    
    public void setEshop(Eshop eshop) {
        this.eshop = eshop;
    }
    
    public Shop getShop() {
        return this.shop;
    }
    
    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
