package org.come.mouslisten;

import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import org.come.model.Shop;
import org.come.model.Eshop;
import java.awt.event.MouseListener;

public class ShopingImgBorderMosulisten implements MouseListener
{
    private Eshop eshop;
    private Shop shop;
    
    public ShopingImgBorderMosulisten(Eshop eshop) {
        this.eshop = eshop;
    }
    
    public ShopingImgBorderMosulisten(Shop shop) {
        this.shop = shop;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if (this.eshop != null) {
            ZhuFrame.getZhuJpanel().creatgoodtext(this.eshop);
        }
        else if (this.shop != null) {
            ZhuFrame.getZhuJpanel().creatgoodtext(this.shop);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        ZhuFrame.getZhuJpanel().cleargoodtext();
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
