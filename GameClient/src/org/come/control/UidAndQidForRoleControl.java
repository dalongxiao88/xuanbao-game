package org.come.control;

import org.come.test.Main;
import org.come.until.GsonUtil;
import org.come.entity.RoleTableList;
import org.come.action.FromServerAction;

public class UidAndQidForRoleControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        RoleTableList roleTableList = (RoleTableList)GsonUtil.getGsonUtil().getgson().fromJson(mes, RoleTableList.class);
        Main.frame.getLoginJpanel().loginSuccess(roleTableList);
    }
}
