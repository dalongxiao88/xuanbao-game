package org.come.control;

import org.come.Frame.TransJframe;
import org.come.until.GsonUtil;
import org.come.bean.GoodTrans2;
import org.come.action.FromServerAction;

public class TransGoodControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        GoodTrans2 goodTrans2 = (GoodTrans2)GsonUtil.getGsonUtil().getgson().fromJson(mes, GoodTrans2.class);
        TransJframe.getTransJframe().getTransJpanel().changTransGood(goodTrans2);
    }
}
