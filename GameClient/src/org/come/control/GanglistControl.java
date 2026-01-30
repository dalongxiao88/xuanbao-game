package org.come.control;

import org.come.until.FormsManagement;
import org.come.entity.Gang;
import java.util.Vector;
import org.come.Frame.JoinBangJframe;
import org.come.until.GsonUtil;
import org.come.bean.GangListBean;
import org.come.action.FromServerAction;

public class GanglistControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        GangListBean gangListBean = (GangListBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, GangListBean.class);
        for (int size = JoinBangJframe.getJoinBangjframe().getJoinBangjpanel().getTableModel().getRowCount(), j = 0; j < size; ++j) {
            JoinBangJframe.getJoinBangjframe().getJoinBangjpanel().getTableModel().removeRow(0);
        }
        if (gangListBean.getGangs().size() != 0) {
            for (int i = 0; i < gangListBean.getGangs().size(); ++i) {
                Vector<String> veStrings = new Vector<>();
                veStrings.add(((Gang)gangListBean.getGangs().get(i)).getGangname());
                veStrings.add(((Gang)gangListBean.getGangs().get(i)).getFounder());
                veStrings.add(((Gang)gangListBean.getGangs().get(i)).getGanggrade() + "");
                veStrings.add(((Gang)gangListBean.getGangs().get(i)).getGangnumber() + "");
                JoinBangJframe.getJoinBangjframe().getJoinBangjpanel().getTableModel().addRow(veStrings);
            }
            JoinBangJframe.getJoinBangjframe().getJoinBangjpanel().setGangs(gangListBean.getGangs());
        }
        FormsManagement.showForm(28);
    }
}
