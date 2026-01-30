package org.cbg.control;

import org.cbg.panel.TraslationMyMainCardJpanel;
import org.cbg.until.TraslationTableUntil;
import org.cbg.frame.TrslationMainJframe;
import org.come.until.GsonUtil;
import org.cbg.bean.SearchGoodsResultBean;
import org.come.action.FromServerAction;

public class SearchDesignatedPurchaseResultControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        SearchGoodsResultBean sa = (SearchGoodsResultBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, SearchGoodsResultBean.class);
        TraslationMyMainCardJpanel traslationMyMainCardJpanel = TrslationMainJframe.getTrslationMainJframe().getTrslationMainJpanel().getTranslationMainCardJpanel().getTraslationMyMainJpanel().getTraslationMyMainCardJpanel();
        TraslationTableUntil.setTableModel(traslationMyMainCardJpanel.getTraslationMyMainZhidinggoumaiJpanel().getjScrollPane(), sa.getSalegoods(), TrslationMainJframe.getTrslationMainJframe().getShoucangWupinList());
        traslationMyMainCardJpanel.getTraslationMyMainZhidinggoumaiJpanel().setPage((sa.getTotal() == 0) ? 1 : sa.getTotal());
        traslationMyMainCardJpanel.getCardLayout().show(traslationMyMainCardJpanel, "zhidinggoumai");
    }
}
