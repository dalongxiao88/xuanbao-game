package org.come.control;

import org.come.Frame.ShoppingBuyJframe;
import org.come.Frame.DuiHuanLingLiJframe;
import org.come.action.FromServerAction;

public class ShopPriceControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        String[] v = mes.split("\\|");
        if (v[1].equals("1")) {
            String s = v[0];
            int n = -1;
            switch (s.hashCode()) {
                case 49: {
                    if (s.equals("1")) {
                        n = 0;
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
            switch (n) {
                case 0: {
                    DuiHuanLingLiJframe.getDuiHuanLingLiJframe().getDuiHuanLingLiJpanel().updatePrice(v[2], Long.parseLong(v[3]));
                    break;
                }
                default: {
                    ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().priceSX(v[2], Long.parseLong(v[3]));
                    break;
                }
            }
        }
    }
}
