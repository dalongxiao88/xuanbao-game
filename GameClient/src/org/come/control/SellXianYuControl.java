package org.come.control;

import org.come.Frame.DianKaJiaoYijframe;
import org.come.until.GsonUtil;
import org.come.bean.SearchSellXianYuResultBean;
import org.come.action.FromServerAction;

public class SellXianYuControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        if (mes.startsWith("MYLIST")) {
            String[] result = mes.split("\\|");
            if (result[1] != null) {
                SearchSellXianYuResultBean sa = (SearchSellXianYuResultBean)GsonUtil.getGsonUtil().getgson().fromJson(result[1], SearchSellXianYuResultBean.class);
                DianKaJiaoYijframe.getDianKaJiaoYijframe().getDianKaJiaoYiJpanel().getCardJpanel().getJishouJpanel().resetTableData(sa);
            }
        }
        else if (mes.startsWith("ALLLIST")) {
            String[] result = mes.split("\\|");
            if (result[1] != null) {
                SearchSellXianYuResultBean sa = (SearchSellXianYuResultBean)GsonUtil.getGsonUtil().getgson().fromJson(result[1], SearchSellXianYuResultBean.class);
                DianKaJiaoYijframe.getDianKaJiaoYijframe().getDianKaJiaoYiJpanel().getCardJpanel().getGoumaiJpanel().resetTableData(sa);
            }
        }
    }
}
