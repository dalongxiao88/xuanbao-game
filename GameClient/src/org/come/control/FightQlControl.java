package org.come.control;

import com.tool.btn.JpanelOnJalbelBtn;
import org.come.until.GsonUtil;
import com.tool.role.Ql;
import org.come.action.FromServerAction;

public class FightQlControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        try {
            if (mes == null || mes.equals("")) {
                return;
            }
            Ql ql = (Ql)GsonUtil.getGsonUtil().getgson().fromJson(mes, Ql.class);
            JpanelOnJalbelBtn.testReflect(ql);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
