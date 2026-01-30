package org.come.control;

import org.come.Frame.PartnerArenaJframe;
import org.come.until.GsonUtil;
import org.come.bean.OneArenaBean;
import org.come.action.FromServerAction;

public class OneArenaControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        OneArenaBean arenaBean = (OneArenaBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, OneArenaBean.class);
        PartnerArenaJframe.getPartnerArenaJframe().getPartnerArenaMainPanel().showView(arenaBean);
    }
}
