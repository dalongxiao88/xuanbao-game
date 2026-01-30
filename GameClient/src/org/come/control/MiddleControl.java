package org.come.control;

import org.come.bean.PrivateData;
import org.come.bean.LoginResult;
import org.come.Frame.ActivityJframe;
import org.come.until.FormsManagement;
import com.tool.role.RoleData;
import org.come.until.GsonUtil;
import org.come.bean.Middle;
import org.come.action.FromServerAction;

public class MiddleControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        Middle middle = (Middle)GsonUtil.getGsonUtil().getgson().fromJson(mes, Middle.class);
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        PrivateData data = RoleData.getRoleData().getPrivateData();
        loginResult.setTaskDaily(middle.getTaskDaily());
        data.setTaskComplete(middle.getTaskComplete());
        loginResult.setDaypaysum(middle.getDaypaysum());
        loginResult.setDaygetorno(middle.getDaygetorno());
        loginResult.setDayandpayorno(middle.getDayandpayorno());
        loginResult.setVipget(middle.getVipget());
        loginResult.setDayfirstinorno(middle.getDayfirstinorno());
        if (FormsManagement.getInternalForm2(40) != null && FormsManagement.getframe(40).isVisible()) {
            ActivityJframe.getActivityJframe().getActivityJpanel().refreshView();
        }
    }
}
