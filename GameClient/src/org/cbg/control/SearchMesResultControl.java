package org.cbg.control;

import org.cbg.panel.TraslationMyMessageJpanel;
import org.cbg.until.TraslationTableMyMessageUntil;
import org.cbg.frame.TrslationMainJframe;
import org.come.until.GsonUtil;
import org.cbg.bean.SearchMesResultBean;
import org.come.action.FromServerAction;

public class SearchMesResultControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        SearchMesResultBean sa = (SearchMesResultBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, SearchMesResultBean.class);
        TraslationMyMessageJpanel traslationMyMessageJpanel = TrslationMainJframe.getTrslationMainJframe().getTrslationMainJpanel().getTranslationMainCardJpanel().getTraslationMyMessageJpanel();
        TraslationTableMyMessageUntil.TableModel(traslationMyMessageJpanel.getjScrollPane(), sa.getMessages());
        traslationMyMessageJpanel.setPage(((int)sa.getTotal() == 0) ? 1 : ((int)sa.getTotal()));
        TrslationMainJframe.getTrslationMainJframe().setXiaoxiGeshuMax(sa.getMessages().size());
        TrslationMainJframe.getTrslationMainJframe().setGoodsGouxuangeshu(0);
        TrslationMainJframe.getTrslationMainJframe().getTrslationMainJpanel().getTranslationMainCardJpanel().getCardLayout().show(TrslationMainJframe.getTrslationMainJframe().getTrslationMainJpanel().getTranslationMainCardJpanel(), "imes");
    }
}
