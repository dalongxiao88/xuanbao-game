package org.come.control;

import org.come.until.FormsManagement;
import org.come.Frame.FactionMainJframe;
import org.come.until.GsonUtil;
import org.come.bean.GangResultBean;
import org.come.action.FromServerAction;

public class IntogangControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        GangResultBean gangResultBean = (GangResultBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, GangResultBean.class);
        FactionMainJframe.getFactionMainJframe().getFactionMainJpanel().getFactionCardJpanel().showMessage(gangResultBean);
        FormsManagement.showForm(48);
    }
}
