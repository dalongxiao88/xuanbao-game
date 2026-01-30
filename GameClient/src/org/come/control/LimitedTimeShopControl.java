package org.come.control;

import org.come.Frame.LimitedTimeShopFrame;
import org.come.until.GsonUtil;
import org.come.bean.LimitedTimeShopBean;
import org.come.action.FromServerAction;

public class LimitedTimeShopControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        LimitedTimeShopBean limitedTimeShopBean = (LimitedTimeShopBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, LimitedTimeShopBean.class);
        LimitedTimeShopFrame.getLimitedTimeShopFrame().getLimitedTimeShopJpanel().showShopGoodsView(limitedTimeShopBean);
    }
}
