package org.come.control;

import java.util.List;
import org.come.until.UserMessUntil;
import org.come.bean.RoleSuitBean;
import org.come.Jpanel.AlreadyRecordedJpanel;
import com.tool.role.RoleData;
import org.come.Frame.AlreadyRecordedJframe;
import org.come.Frame.CollectionJadeJframe;
import org.come.until.FormsManagement;
import org.come.Frame.ExchangeValueJframe;
import org.come.action.NpcMenuAction;

public class ExchangeValueControl implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        if (type.equals("我要兑换灵修值")) {
            ExchangeValueJframe.getExchangeValueJframe().getValueJpanel().clearInterface();
            FormsManagement.showForm(63);
        }
        else if (type.equals("我要收录玉符（玉符转符录）")) {
            CollectionJadeJframe.getCollectionJadeJframe().getJadeJpanel().clearInterface();
            FormsManagement.showForm(64);
        }
        else if (type.equals("我要查看已有符录（符录转玉符）")) {
            AlreadyRecordedJframe.getAlreadyRecordedJframe().getRecordedJpanel().clearInterface();
            List<Integer> listSuit = RoleData.getRoleData().getPackRecord().accessCollect();
            AlreadyRecordedJframe.getAlreadyRecordedJframe().getRecordedJpanel().getListModel().removeAllElements();
            AlreadyRecordedJpanel.suitNum = 0;
            if (listSuit != null && listSuit.size() > 0) {
                AlreadyRecordedJpanel.suitNum = listSuit.size();
                for (int i = 0; i < listSuit.size(); ++i) {
                    AlreadyRecordedJframe.getAlreadyRecordedJframe().getRecordedJpanel().getListModel().add(i, ((RoleSuitBean)UserMessUntil.getAllSuit().getRolesuit().get(listSuit.get(i))).getSname());
                }
            }
            FormsManagement.showForm(65);
        }
    }
}
