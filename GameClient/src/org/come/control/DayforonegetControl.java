package org.come.control;

import java.util.List;
import org.come.Frame.EveryDayOddsJframe;
import java.util.ArrayList;
import org.come.until.GsonUtil;
import org.come.bean.ChongjipackBean;
import org.come.action.FromServerAction;

public class DayforonegetControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        ChongjipackBean chongjipackBean = (ChongjipackBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, ChongjipackBean.class);
        List<ChongjipackBean> chongjipackBeans = new ArrayList<>();
        chongjipackBeans.add(chongjipackBean);
        EveryDayOddsJframe.getEveryDayOddsJframe().getEveryDayOddsJpanel().showGoods(chongjipackBeans);
    }
}
