package org.come.control;

import org.come.until.FormsManagement;
import org.come.Frame.GoodsExchangeJframe;
import org.come.until.GsonUtil;
import org.come.bean.GoodSEarchBean;
import org.come.action.FromServerAction;

public class GoodsForGoodsSearchAction implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        GoodSEarchBean goodsbean = (GoodSEarchBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, GoodSEarchBean.class);
        GoodsExchangeJframe.getGoodDetailedJframe().getGoodsExchangeJpanel().removeAll();
        GoodsExchangeJframe.getGoodDetailedJframe().getGoodsExchangeJpanel().initGoodsList(goodsbean.getReturnListBean());
        FormsManagement.showForm(90);
    }
}
