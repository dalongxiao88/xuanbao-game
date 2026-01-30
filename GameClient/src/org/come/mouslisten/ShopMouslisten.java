package org.come.mouslisten;

import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import org.come.until.UserMessUntil;
import org.come.model.Shop;
import java.awt.event.MouseEvent;
import org.come.Jpanel.ShoppingBuyJpanel;
import java.awt.event.MouseListener;

public class ShopMouslisten implements MouseListener
{
    private int shopPlace;
    private ShoppingBuyJpanel shoppingBuyJpanel;
    
    public ShopMouslisten(int shopPlace, ShoppingBuyJpanel shoppingBuyJpanel) {
        this.shoppingBuyJpanel = shoppingBuyJpanel;
        this.shopPlace = shopPlace;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Shop shop = this.shoppingBuyJpanel.getShop(this.shopPlace);
        if (shop != null) {
            this.shoppingBuyJpanel.xuanzhong(shop, this.shopPlace);
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        Shop shop = this.shoppingBuyJpanel.getShop(this.shopPlace);
        if (shop != null) {
            String text = shop.getShoptext();
            Goodstable goodstable = UserMessUntil.getgoodstable(shop.getShopiid());
            if (goodstable != null && (long)goodstable.getType() == 112L) {
                shop.setValue(goodstable.getValue());
            }
            this.shoppingBuyJpanel.PaintingText(this.shopPlace);
            ZhuFrame.getZhuJpanel().creatgoodtext(shop);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        this.shoppingBuyJpanel.ClearText(this.shopPlace);
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
    
    public int getShopPlace() {
        return this.shopPlace;
    }
    
    public void setShopPlace(int shopPlace) {
        this.shopPlace = shopPlace;
    }
}
