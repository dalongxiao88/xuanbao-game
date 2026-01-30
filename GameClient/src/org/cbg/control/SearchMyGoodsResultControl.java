package org.cbg.control;

import org.cbg.panel.TraslationMyMainCardJpanel;
import org.cbg.until.TraslationTableMygoodsUntil;
import org.cbg.entity.Roleorder;
import org.come.until.GsonUtil;
import org.cbg.bean.SearchOrderResultBean;
import org.cbg.frame.TrslationMainJframe;
import org.come.action.FromServerAction;

public class SearchMyGoodsResultControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        TrslationMainJframe trslationMainJframe = TrslationMainJframe.getTrslationMainJframe();
        SearchOrderResultBean sa = (SearchOrderResultBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, SearchOrderResultBean.class);
        trslationMainJframe.setGoodsGeshuMax(0);
        for (int i = 0; i < sa.getRoleorders().size(); ++i) {
            if ((int)((Roleorder)sa.getRoleorders().get(i)).getStatus() == 3) {
                trslationMainJframe.setGoodsGeshuMax(trslationMainJframe.getGoodsGeshuMax() + 1);
            }
        }
        TraslationMyMainCardJpanel traslationMyMainCardJpanel = TrslationMainJframe.getTrslationMainJframe().getTrslationMainJpanel().getTranslationMainCardJpanel().getTraslationMyMainJpanel().getTraslationMyMainCardJpanel();
        trslationMainJframe.setGoodsGouxuangeshu(0);
        TraslationTableMygoodsUntil.setTableModel(traslationMyMainCardJpanel.getTraslationMyMainMygoodsJpanel().getjScrollPane(), sa.getRoleorders());
        traslationMyMainCardJpanel.getTraslationMyMainMygoodsJpanel().setPage(((int)sa.getTotal() == 0) ? 1 : ((int)sa.getTotal()));
        traslationMyMainCardJpanel.getCardLayout().show(traslationMyMainCardJpanel, "mygoods");
    }
}
