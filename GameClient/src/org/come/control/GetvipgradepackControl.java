package org.come.control;

import org.come.Frame.RechargeVIPJframe;
import org.come.bean.PayvipBean;
import com.google.gson.reflect.TypeToken;
import org.come.until.GsonUtil;
import java.util.List;
import org.come.action.FromServerAction;

public class GetvipgradepackControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        List<PayvipBean> payvipBeanList = (List)GsonUtil.getGsonUtil().getgson().fromJson(mes, new TypeToken<List<PayvipBean>>() {}.getType());
        RechargeVIPJframe.getRechargeVIPJpanel().showGoodsList(payvipBeanList);
    }
}
