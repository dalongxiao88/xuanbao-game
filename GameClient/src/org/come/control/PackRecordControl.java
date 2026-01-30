package org.come.control;

import java.util.List;
import org.come.until.UserMessUntil;
import org.come.bean.RoleSuitBean;
import org.come.Jpanel.AlreadyRecordedJpanel;
import org.come.Frame.AlreadyRecordedJframe;
import com.tool.role.RoleData;
import org.come.action.FromServerAction;

public class PackRecordControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String types) {
        String cd = mes.substring(0, 1);
        String ab = mes.substring(1);
        int type = Integer.parseInt(cd);
        if (type != 0 && type != 1 && type != 2 && type != 3 && type == 4) {
            RoleData.getRoleData().getPackRecord().setCollect(ab);
            AlreadyRecordedJframe.getAlreadyRecordedJframe().getRecordedJpanel().getListModel().removeAllElements();
            List<Integer> listSuit = RoleData.getRoleData().getPackRecord().accessCollect();
            AlreadyRecordedJpanel.suitNum = 0;
            if (listSuit != null && listSuit.size() > 0) {
                AlreadyRecordedJpanel.suitNum = listSuit.size();
                for (int i = 0; i < listSuit.size(); ++i) {
                    AlreadyRecordedJframe.getAlreadyRecordedJframe().getRecordedJpanel().getListModel().add(i, ((RoleSuitBean)UserMessUntil.getAllSuit().getRolesuit().get(listSuit.get(i))).getSname());
                }
            }
        }
    }
}
