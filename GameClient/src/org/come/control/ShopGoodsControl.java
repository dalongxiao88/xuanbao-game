package org.come.control;

import org.come.until.FormsManagement;
import org.come.Frame.ShoppingBuyJframe;
import org.come.until.GsonUtil;
import org.come.bean.ShopGoodsBean;
import org.come.action.FromServerAction;

public class ShopGoodsControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        ShopGoodsBean shopGoodsBean = (ShopGoodsBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, ShopGoodsBean.class);
        ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().getShopGoods(shopGoodsBean);
        FormsManagement.showForm(23);
    }
}
