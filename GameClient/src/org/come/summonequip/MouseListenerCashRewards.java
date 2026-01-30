package org.come.summonequip;

import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import org.come.until.GoodsListFromServerUntil;
import org.come.model.Shop;
import org.come.until.UserMessUntil;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseListenerCashRewards implements MouseListener
{
    private int index;
    private JpanelCashRewardsMain jpanelCashRewardsMain;
    
    public MouseListenerCashRewards(int index, JpanelCashRewardsMain jpanelCashRewardsMain) {
        this.index = index;
        this.jpanelCashRewardsMain = jpanelCashRewardsMain;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (this.jpanelCashRewardsMain.getGoodsType() == 1) {
            if (this.index > ((List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("600")).size()) {
                return;
            }
            this.jpanelCashRewardsMain.setChooseShop((Shop)((List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("600")).get(this.index));
            if (this.jpanelCashRewardsMain.getChooseShop() != null) {
                this.jpanelCashRewardsMain.getChooseGoods().setIcon(GoodsListFromServerUntil.imgpathAdaptive(this.jpanelCashRewardsMain.getChooseShop().getShopskin(), 55, 53));
            }
        }
        else if (this.jpanelCashRewardsMain.getGoodsType() == 2) {
            if (this.index > ((List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("601")).size()) {
                return;
            }
            this.jpanelCashRewardsMain.setChooseShop((Shop)((List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("601")).get(this.index));
            if (this.jpanelCashRewardsMain.getChooseShop() != null) {
                this.jpanelCashRewardsMain.getChooseGoods().setIcon(GoodsListFromServerUntil.imgpathAdaptive(this.jpanelCashRewardsMain.getChooseShop().getShopskin(), 55, 53));
            }
        }
        else if (this.jpanelCashRewardsMain.getGoodsType() == 3) {
            if (this.index > ((List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("602")).size()) {
                return;
            }
            this.jpanelCashRewardsMain.setChooseShop((Shop)((List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("602")).get(this.index));
            if (this.jpanelCashRewardsMain.getChooseShop() != null) {
                this.jpanelCashRewardsMain.getChooseGoods().setIcon(GoodsListFromServerUntil.imgpathAdaptive(this.jpanelCashRewardsMain.getChooseShop().getShopskin(), 55, 53));
            }
        }
        this.jpanelCashRewardsMain.getTextNum().setText("1");
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        List<Shop> shopList = null;
        if (this.jpanelCashRewardsMain.getGoodsType() == 1) {
            shopList = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("600");
        }
        else if (this.jpanelCashRewardsMain.getGoodsType() == 2) {
            shopList = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("601");
        }
        else if (this.jpanelCashRewardsMain.getGoodsType() == 3) {
            shopList = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("602");
        }
        if (shopList == null) {
            return;
        }
        if (this.index >= shopList.size()) {
            return;
        }
        Shop shop = (Shop)shopList.get(this.index);
        if (shop != null) {
            Goodstable goodstable = UserMessUntil.getgoodstable(shop.getShopiid());
            if (goodstable != null && (long)goodstable.getType() == 112L) {
                shop.setValue(goodstable.getValue());
            }
            ZhuFrame.getZhuJpanel().creatgoodtext(shop);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
}
