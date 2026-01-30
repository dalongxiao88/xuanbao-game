package org.come.control;

import org.come.until.FormsManagement;
import org.come.until.ZxpackFromServerUntil;
import org.come.until.GsonUtil;
import org.come.bean.ZxpackBean;
import org.come.action.FromServerAction;

public class ZxpackControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        if (mes.startsWith("GETZX")) {
            String[] result = mes.split("=");
            if (result[1] != null) {
                ZxpackBean list = (ZxpackBean)GsonUtil.getGsonUtil().getgson().fromJson(result[1], ZxpackBean.class);
                ZxpackFromServerUntil.setGoods(list.getList());
                FormsManagement.showForm(643);
            }
        }
    }
}
