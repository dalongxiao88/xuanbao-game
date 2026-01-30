package org.cbg.control;

import org.cbg.panel.TraslationWantBuyCardJapel;
import org.cbg.until.TraslationTableUntil;
import org.cbg.frame.TrslationMainJframe;
import org.come.until.GsonUtil;
import org.cbg.bean.SearchCollectionResultBean;
import org.come.action.FromServerAction;

public class SearchCollectionQueryControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        SearchCollectionResultBean sa = (SearchCollectionResultBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, SearchCollectionResultBean.class);
        TraslationWantBuyCardJapel traslationWantBuyCardJapel = TrslationMainJframe.getTrslationMainJframe().getTrslationMainJpanel().getTranslationMainCardJpanel().getTraslationWantBuyJpanel().getTraslationWantBuyCardJapel();
        TraslationTableUntil.setShoucangTableModel(traslationWantBuyCardJapel.getTraslationWantBuyShoucangJpanel().getjScrollPane(), sa.getCollections());
        traslationWantBuyCardJapel.getTraslationWantBuyShoucangJpanel().setPage(((int)sa.getTotal() == 0) ? 1 : ((int)sa.getTotal()));
        traslationWantBuyCardJapel.getCardLayout().show(traslationWantBuyCardJapel, "shoucang");
    }
}
