package org.cbg.control;

import org.come.until.FormsManagement;
import org.cbg.frame.TraslationCommodityJFrame;
import org.come.until.GsonUtil;
import org.cbg.bean.BuyBtnGetBean;
import org.come.action.FromServerAction;

public class SearchBuyResultControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        BuyBtnGetBean sa = (BuyBtnGetBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, BuyBtnGetBean.class);
        TraslationCommodityJFrame.getTraslationCommodityJFrame().getTraslationCommodityMainJPane().getNickname().setText(sa.getGoodsRoleName());
        TraslationCommodityJFrame.getTraslationCommodityJFrame().getTraslationCommodityMainJPane().getShoucangrenshu().setText(sa.getInGetPeoSum() + "人收藏");
        FormsManagement.showForm(79);
    }
}
