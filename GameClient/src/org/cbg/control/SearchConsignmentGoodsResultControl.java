package org.cbg.control;

import org.cbg.panel.TraslationWantSendCardJpanel;
import org.come.until.GsonUtil;
import org.cbg.bean.SearchGoodsResultBean;
import org.cbg.frame.TrslationMainJframe;
import org.come.action.FromServerAction;

public class SearchConsignmentGoodsResultControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        TraslationWantSendCardJpanel traslationWantSendCardJpanel = TrslationMainJframe.getTrslationMainJframe().getTrslationMainJpanel().getTranslationMainCardJpanel().getTraslationWantSentJpanel().getTraslationWantSendCardJpanel();
        SearchGoodsResultBean bean = (SearchGoodsResultBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, SearchGoodsResultBean.class);
        traslationWantSendCardJpanel.getTraslationWantSentYijishangpinJpanel().MyShow(bean);
    }
}
