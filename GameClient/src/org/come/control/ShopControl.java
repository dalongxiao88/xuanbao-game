package org.come.control;

import org.come.model.Shop;
import org.come.until.FormsManagement;
import java.math.BigDecimal;
import org.come.Frame.ShoppingBuyJframe;
import org.come.until.UserMessUntil;
import java.util.List;
import org.come.action.FromServerAction;

public class ShopControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        String[] vs = mes.split("\\|");
        List<Shop> npcshop = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get(vs[0]);
        ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().showshop(npcshop, vs[0], new BigDecimal(vs[1]));
        FormsManagement.showForm(23);
    }
}
