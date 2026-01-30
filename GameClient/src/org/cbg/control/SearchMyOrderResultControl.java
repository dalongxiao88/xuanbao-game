package org.cbg.control;

import org.cbg.panel.TraslationMyMainCardJpanel;
import org.cbg.until.TraslationTableMyorderUntil;
import org.cbg.frame.TrslationMainJframe;
import org.come.until.GsonUtil;
import org.cbg.bean.SearchOrderResultBean;
import org.come.action.FromServerAction;

public class SearchMyOrderResultControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        SearchOrderResultBean sa = (SearchOrderResultBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, SearchOrderResultBean.class);
        TraslationMyMainCardJpanel traslationMyMainCardJpanel = TrslationMainJframe.getTrslationMainJframe().getTrslationMainJpanel().getTranslationMainCardJpanel().getTraslationMyMainJpanel().getTraslationMyMainCardJpanel();
        TraslationTableMyorderUntil.TableModel(traslationMyMainCardJpanel.getTraslationMyMainMyorderJpanel().getjScrollPane(), sa.getRoleorders());
        traslationMyMainCardJpanel.getTraslationMyMainMyorderJpanel().setPage(((int)sa.getTotal() == 0) ? 1 : ((int)sa.getTotal()));
        traslationMyMainCardJpanel.getCardLayout().show(traslationMyMainCardJpanel, "myorder");
    }
}
